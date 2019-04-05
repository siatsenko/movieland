package com.siatsenko.movieland.service.impl.report;

import com.siatsenko.movieland.entity.report.Report;
import com.siatsenko.movieland.entity.report.ReportOutputType;
import com.siatsenko.movieland.entity.report.ReportType;
import com.siatsenko.movieland.service.ReportBuilderFactory;
import com.siatsenko.movieland.service.ReportBuilderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultReportBuilderFactory implements ReportBuilderFactory {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    PdfMovieReportBuilderService pdfMovieReportBuilderService;
    PdfUserReportBuilderService pdfUserReportBuilderService;
    XlsMovieReportBuilderService xlsMovieReportBuilderService;
    XlsUserReportBuilderService xlsUserReportBuilderService;

    @Override
    public ReportBuilderService getReportBuilderService(Report report) {
        if (report.getOutputType() == ReportOutputType.PDF) {
            if (report.getType() == ReportType.ACTIVE_USERS) {
                return pdfUserReportBuilderService;
            } else {
                return pdfMovieReportBuilderService;
            }
        }
        if (report.getOutputType() == ReportOutputType.XLS) {
            if (report.getType() == ReportType.ACTIVE_USERS) {
                return xlsUserReportBuilderService;
            } else {
                return xlsMovieReportBuilderService;
            }
        }
        logger.debug("getReportBuilderService({}) unsupported case", report);
        return null;
    }

    @Autowired
    public void setPdfMovieReportBuilderService(PdfMovieReportBuilderService pdfMovieReportBuilderService) {
        this.pdfMovieReportBuilderService = pdfMovieReportBuilderService;
    }

    @Autowired
    public void setPdfUserReportBuilderService(PdfUserReportBuilderService pdfUserReportBuilderService) {
        this.pdfUserReportBuilderService = pdfUserReportBuilderService;
    }

    @Autowired
    public void setXlsMovieReportBuilderService(XlsMovieReportBuilderService xlsMovieReportBuilderService) {
        this.xlsMovieReportBuilderService = xlsMovieReportBuilderService;
    }

    @Autowired
    public void setXlsUserReportBuilderService(XlsUserReportBuilderService xlsUserReportBuilderService) {
        this.xlsUserReportBuilderService = xlsUserReportBuilderService;
    }
}
