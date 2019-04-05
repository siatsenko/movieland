package com.siatsenko.movieland.entity.request;

import com.siatsenko.movieland.entity.report.ReportOutputType;
import com.siatsenko.movieland.entity.report.ReportType;

import java.time.LocalDate;

public class ReportRequest {
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private ReportType reportType;
    private ReportOutputType reportOutputType;

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public ReportType getReportType() {
        return reportType;
    }

    public void setReportType(ReportType reportType) {
        this.reportType = reportType;
    }

    public ReportOutputType getReportOutputType() {
        return reportOutputType;
    }

    public void setReportOutputType(ReportOutputType reportOutputType) {
        this.reportOutputType = reportOutputType;
    }

    @Override
    public String toString() {
        return "ReportRequest{" +
                "dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", reportType=" + reportType +
                ", reportOutputType=" + reportOutputType +
                '}';
    }
}
