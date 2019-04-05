package com.siatsenko.movieland.service.impl.report;

import com.siatsenko.movieland.dao.ReportResultsDao;
import com.siatsenko.movieland.entity.report.ReportMovieDetail;
import com.siatsenko.movieland.entity.report.ReportUserDetail;
import com.siatsenko.movieland.service.ReportDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.stream.Stream;

@Service
public class DefaultReportDataService implements ReportDataService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private ReportResultsDao reportResultsDao;

    @Override
    public Stream<ReportMovieDetail> getAllReportMovieDetail() {
        return reportResultsDao.getAllReportMovieDetail();
    }

    @Override
    public Stream<ReportMovieDetail> getPeriodReportMovieDetail(LocalDate dateFrom, LocalDate dateTo) {
        return reportResultsDao.getPeriodReportMovieDetail(dateFrom, dateTo);
    }

    @Override
    public Stream<ReportUserDetail> getTop5UserDetail() {
        return reportResultsDao.getTop5UserDetail();
    }

    @Autowired
    public void setReportResultsDao(ReportResultsDao reportResultsDao) {
        this.reportResultsDao = reportResultsDao;
    }
}
