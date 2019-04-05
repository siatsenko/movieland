package com.siatsenko.movieland.service;

import com.siatsenko.movieland.entity.report.Report;

public interface ReportBuilderFactory {

    ReportBuilderService getReportBuilderService(Report report);

}
