package com.siatsenko.movieland.entity.report;

import com.siatsenko.movieland.entity.common.User;
import com.siatsenko.movieland.entity.request.ReportRequest;
import com.siatsenko.movieland.web.UserHandler;

import java.time.LocalDate;
import java.util.Objects;

public class Report {
    private Integer id;
    private User user;
    private boolean deleted;
    private ReportStatus status;
    private String file;
    private String link;

    private LocalDate dateFrom;
    private LocalDate dateTo;
    private ReportType type;
    private ReportOutputType outputType;

    public Report() {

    }

    public Report(ReportRequest reportRequest) {
        this.dateFrom = reportRequest.getDateFrom();
        this.dateTo = reportRequest.getDateTo();
        this.type = reportRequest.getReportType();
        this.outputType = reportRequest.getReportOutputType();

        this.user = UserHandler.getCurrentUser();
        this.deleted = false;
        this.status = ReportStatus.WAIT;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public void setStatus(ReportStatus status) {
        this.status = status;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public void setType(ReportType type) {
        this.type = type;
    }

    public void setOutputType(ReportOutputType outputType) {
        this.outputType = outputType;
    }

    public Integer getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public ReportStatus getStatus() {
        return status;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public ReportType getType() {
        return type;
    }

    public ReportOutputType getOutputType() {
        return outputType;
    }

    public String getLink() {
        return link;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return Objects.equals(id, report.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", user=" + user +
                ", deleted=" + deleted +
                ", status=" + status +
                ", file='" + file + '\'' +
                ", link='" + link + '\'' +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", type=" + type +
                ", outputType=" + outputType +
                '}';
    }
}
