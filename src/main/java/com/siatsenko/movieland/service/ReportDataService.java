package com.siatsenko.movieland.service;

import com.siatsenko.movieland.entity.report.ReportMovieDetail;
import com.siatsenko.movieland.entity.report.ReportUserDetail;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

public interface ReportDataService {

    Stream<ReportMovieDetail> getAllReportMovieDetail();

    Stream<ReportMovieDetail> getPeriodReportMovieDetail(LocalDate dateFrom, LocalDate dateTo);

    Stream<ReportUserDetail> getTop5UserDetail();

}
