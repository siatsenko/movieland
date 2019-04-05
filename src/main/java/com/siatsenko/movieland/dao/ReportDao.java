package com.siatsenko.movieland.dao;

import com.siatsenko.movieland.entity.report.Report;

import java.util.List;

public interface ReportDao {

    List<Report> getActiveReports();

    Report upsert(Report report);

    void cleanDone();

}
