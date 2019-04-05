package com.siatsenko.movieland.dao;

import com.siatsenko.movieland.entity.report.ReportMovieDetail;
import com.siatsenko.movieland.entity.report.ReportUserDetail;

import java.time.LocalDate;
import java.util.stream.Stream;

public interface ReportResultsDao {

    Stream<ReportMovieDetail> getAllReportMovieDetail();

    Stream<ReportMovieDetail> getPeriodReportMovieDetail(LocalDate dateFrom, LocalDate dateTo);

    Stream<ReportUserDetail> getTop5UserDetail();

}
