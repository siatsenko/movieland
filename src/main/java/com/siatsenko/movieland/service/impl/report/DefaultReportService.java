package com.siatsenko.movieland.service.impl.report;

import com.siatsenko.movieland.dao.ReportDao;
import com.siatsenko.movieland.entity.common.User;
import com.siatsenko.movieland.entity.report.*;
import com.siatsenko.movieland.entity.request.ReportRequest;
import com.siatsenko.movieland.exception.ReportServiceBusyException;
import com.siatsenko.movieland.listener.PostContextRefresh;
import com.siatsenko.movieland.service.*;
import com.siatsenko.movieland.web.UserHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Stream;

@Service
public class DefaultReportService implements ReportService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private static final ReentrantReadWriteLock LOCK = new ReentrantReadWriteLock();
    private static final ReentrantReadWriteLock.ReadLock CUSTOM_LOCK = LOCK.readLock();
    private static final ReentrantReadWriteLock.WriteLock GLOBAL_LOCK = LOCK.writeLock();

    @Value("${reports.dir}")
    private String reportsDir;

    private static Map<Integer, Report> reports = new ConcurrentHashMap<>();
    private static BlockingQueue<Report> queue = new LinkedBlockingQueue<>();

    private ReportDao reportDao;
    private ReportDataService reportDataService;
    private ReportBuilderFactory reportBuilderFactory;
    private ReportUploaderService reportUploaderService;
    private ReportSenderService reportSenderService;


    @Override
    @Transactional
    public Report post(ReportRequest reportRequest) {
        Report report = new Report(reportRequest);
        reportDao.upsert(report);
        boolean accepted = queue.offer(report);
        if (!accepted) {
            throw new ReportServiceBusyException("Report service is busy");
        }
        reports.put(report.getId(), report);
        logger.debug("post({}) finished and return result: {}", reportRequest, report);
        return report;
    }

    @Override
    public Report get(int id) {
        return reports.get(id);
    }

    @Override
    public List<Report> get() {
        User user = UserHandler.getCurrentUser();
        List<Report> list = new ArrayList<>();
        for (Map.Entry<Integer, Report> entry : reports.entrySet()) {
            Report report = entry.getValue();
            if (user.getId() == report.getUser().getId()) {
                list.add(report);
            }
        }
        return list;
    }

    @Override
    @Transactional
    public void delete(int id) {
        CUSTOM_LOCK.lock();
        try {
            Report report = reports.remove(id);
            queue.remove(report);
            report.setDeleted(true);
            reportDao.upsert(report);
        } finally {
            CUSTOM_LOCK.unlock();
        }
    }

    @Override
    public Report run() {
        return checkQueue();
    }

    @Scheduled(fixedDelayString = "${scheduled.reports.checkQueueTimeout: 300000}", initialDelayString = "${scheduled.reports.checkQueueTimeout: 300000}")
    public Report runScheduled() {
        return checkQueue();
    }

    @Transactional
    Report checkQueue() {
        Report report = queue.poll();
        if (report != null) {
            make(report);
            return report;
        }
        return null;
    }

    @PostContextRefresh
    @Transactional
    void init() {
        List<Report> list = reportDao.getActiveReports();
        for (Report report : list) {
            reports.put(report.getId(), report);
            if (!(ReportStatus.DONE.equals(report.getStatus()) || report.isDeleted())) {
                try {
                    queue.put(report);
                } catch (InterruptedException e) {
                    logger.debug("init() queue wait was interrupted {}", e);
                }
            }
        }
    }

    // every Monday of July 15:15:15
    @Scheduled(cron = "${scheduled.reports.cleanDone:15 15 15 * 7 MON}")
    @Transactional
    void clean() {
        GLOBAL_LOCK.lock();
        try {
            reportDao.cleanDone();
            Iterator<Map.Entry<Integer, Report>> iterator = reports.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Integer, Report> entry = iterator.next();
                if (entry.getValue().getStatus() == ReportStatus.DONE) {
                    iterator.remove();
                }
            }
        } finally {
            GLOBAL_LOCK.unlock();
        }
    }

    @Transactional
    void setReportStatus(Report report, ReportStatus reportStatus) {
        CUSTOM_LOCK.lock();
        try {
            report.setStatus(reportStatus);
            reportDao.upsert(report);
        } finally {
            CUSTOM_LOCK.unlock();
        }
    }

    private Report make(Report report) {
        if (report == null) {
            logger.debug("make stop - report is null");
            return null;
        }
        stepBuild(report);
        stepUpload(report);
        stepSend(report);

        if (report.getStatus() == ReportStatus.SENT) {
            setReportStatus(report, ReportStatus.DONE);
        }

        return report;
    }

    private void stepBuild(Report report) {
        checkDelete(report);

        if (report.getStatus() == ReportStatus.WAIT) {
            try {
                setReportStatus(report, ReportStatus.BUILDING);
                ReportBuilderService reportBuilderService = reportBuilderFactory.getReportBuilderService(report);
                String reportId = String.valueOf(report.getId());
                String fileName = reportsDir + "\\" + reportId;

                if (report.getType() == ReportType.ACTIVE_USERS) {

                    Stream<ReportUserDetail> stream = reportDataService.getTop5UserDetail();
                    String reportFileName = reportBuilderService.buildReportDetail(fileName, stream);
                    report.setFile(reportFileName);

                } else if (report.getType() == ReportType.ALL_MOVIES) {

                    Stream<ReportMovieDetail> stream = reportDataService.getAllReportMovieDetail();
                    String reportFileName = reportBuilderService.buildReportDetail(fileName, stream);
                    report.setFile(reportFileName);

                } else if (report.getType() == ReportType.PERIOD_MOVIES) {

                    LocalDate dateFrom = report.getDateFrom();
                    LocalDate dateTo = report.getDateTo();

                    Stream<ReportMovieDetail> stream = reportDataService.getPeriodReportMovieDetail(dateFrom, dateTo);
                    String reportFileName = reportBuilderService.buildReportDetail(fileName, stream);
                    report.setFile(reportFileName);
                }
                setReportStatus(report, ReportStatus.BUILT);
            } catch (Exception e) {
                setReportStatus(report, ReportStatus.FAILED);
                logger.debug("stepBuild({}) trows exception {}", report, e);
            }
        }
    }

    private void stepUpload(Report report) {
        checkDelete(report);
        if (report.getStatus() == ReportStatus.BUILT) {
            try {
                setReportStatus(report, ReportStatus.UPLOADING);
                reportUploaderService.upload(report);
                setReportStatus(report, ReportStatus.UPLOADED);
            } catch (Exception e) {
                setReportStatus(report, ReportStatus.FAILED);
                logger.debug("stepUpload({}) trows exception {}", report, e);
            }
        }
    }

    private void stepSend(Report report) {
        checkDelete(report);
        if (report.getStatus() == ReportStatus.UPLOADED) {
            try {
                setReportStatus(report, ReportStatus.SENDING);
                reportSenderService.send(report);
                setReportStatus(report, ReportStatus.SENT);
            } catch (Exception e) {
                setReportStatus(report, ReportStatus.FAILED);
                logger.debug("stepSend({}) trows exception {}", report, e);
            }
        }
    }

    private void checkDelete(Report report) {
        CUSTOM_LOCK.lock();
        try {
            if (report.isDeleted()) {
                setReportStatus(report, ReportStatus.DELETED);
            }
        } finally {
            CUSTOM_LOCK.unlock();
        }
    }

    @Autowired
    public void setReportDao(ReportDao reportDao) {
        this.reportDao = reportDao;
    }

    @Autowired
    public void setReportDataService(ReportDataService reportDataService) {
        this.reportDataService = reportDataService;
    }

    @Autowired
    public void setReportUploaderService(ReportUploaderService reportUploaderService) {
        this.reportUploaderService = reportUploaderService;
    }

    @Autowired
    public void setReportSenderService(ReportSenderService reportSenderService) {
        this.reportSenderService = reportSenderService;
    }

    @Autowired
    public void setReportBuilderFactory(ReportBuilderFactory reportBuilderFactory) {
        this.reportBuilderFactory = reportBuilderFactory;
    }
}
