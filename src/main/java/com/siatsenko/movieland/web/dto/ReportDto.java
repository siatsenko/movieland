package com.siatsenko.movieland.web.dto;

import com.siatsenko.movieland.entity.report.Report;
import com.siatsenko.movieland.entity.report.ReportStatus;

public class ReportDto {
    private int reportId;
    private ReportStatus reportStatus;
    private String link;

    public ReportDto(Report report) {
        this.reportId = report.getId();
        this.reportStatus = report.getStatus();
        this.link = report.getLink();
    }

    public int getReportId() {
        return reportId;
    }

    public ReportStatus getReportStatus() {
        return reportStatus;
    }

    public String getLink() {
        return link;
    }
}
