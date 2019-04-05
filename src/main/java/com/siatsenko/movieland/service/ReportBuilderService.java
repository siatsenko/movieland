package com.siatsenko.movieland.service;

import com.siatsenko.movieland.entity.report.ReportDetail;
import java.util.stream.Stream;

public interface ReportBuilderService {

    String buildReportDetail (String fileName, Stream<? extends ReportDetail> stream) throws Exception;

}
