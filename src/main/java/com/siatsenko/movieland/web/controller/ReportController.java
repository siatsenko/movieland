package com.siatsenko.movieland.web.controller;

import com.siatsenko.movieland.entity.report.Report;
import com.siatsenko.movieland.entity.common.Role;
import com.siatsenko.movieland.entity.request.ReportRequest;
import com.siatsenko.movieland.service.ReportDataService;
import com.siatsenko.movieland.service.ReportService;
import com.siatsenko.movieland.web.UserHandler;
import com.siatsenko.movieland.web.annotation.ProtectedBy;
import com.siatsenko.movieland.web.dto.ReportDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ReportController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private ReportService reportService;
    private ReportDataService reportDataService;

    @GetMapping(path = "/report/run", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Report test() {
        Report report = reportService.run();
        return report;
    }

    // POST to add report request
    @ProtectedBy(acceptedRole = Role.ADMIN)
    @PostMapping(path = "/report", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ReportDto postReport(@RequestBody ReportRequest reportRequest) {
        Report report = reportService.post(reportRequest);
        ReportDto reportDto = new ReportDto(report);
        logger.debug("post({}) from user({}) return {}", reportRequest, UserHandler.getCurrentUser(), reportDto);
        return reportDto;
    }

    // GET(id) to get report info
    @ProtectedBy(acceptedRole = Role.ADMIN)
    @GetMapping(path = "/report/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ReportDto getReport(@PathVariable("id") int id) {
        Report report = reportService.get(id);
        ReportDto reportDto = new ReportDto(report);
        logger.debug("get({}) from user({}) return {}", id, UserHandler.getCurrentUser(), reportDto);
        return reportDto;
    }

    // GET()  to get all report info of current user
    @ProtectedBy(acceptedRole = Role.ADMIN)
    @GetMapping(path = "/report", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<ReportDto> getReport() {
        List<Report> reports = reportService.get();
        List<ReportDto> reportDtos = new ArrayList<>();
        for (Report report : reports) {
            ReportDto reportDto = new ReportDto(report);
            reportDtos.add(reportDto);
        }
        logger.debug("get() from user({}) return {}", UserHandler.getCurrentUser(), reportDtos);
        return reportDtos;
    }

    // DELETE to remove report request or report results
    @ProtectedBy(acceptedRole = Role.ADMIN)
    @DeleteMapping(path = "/report/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void deleteReport(@PathVariable("id") int id) {
        reportService.delete(id);
        logger.debug("delete({}) from user({})", id, UserHandler.getCurrentUser());
    }

    @Autowired
    public void setReportService(ReportService reportService) {
        this.reportService = reportService;
    }

    @Autowired
    public void setReportDataService(ReportDataService reportDataService) {
        this.reportDataService = reportDataService;
    }
}
