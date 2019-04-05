package com.siatsenko.movieland.service;

import com.siatsenko.movieland.entity.report.Report;
import com.siatsenko.movieland.entity.report.ReportDetail;
import com.siatsenko.movieland.entity.request.ReportRequest;

import java.util.List;

public interface ReportService {

    Report post(ReportRequest reportRequest);

    Report get(int id);

    List<Report> get();

    void delete(int id);

    Report run();

}
