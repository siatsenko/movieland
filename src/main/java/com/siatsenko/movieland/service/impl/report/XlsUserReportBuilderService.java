package com.siatsenko.movieland.service.impl.report;

import com.siatsenko.movieland.entity.report.ReportDetail;
import com.siatsenko.movieland.entity.report.ReportUserDetail;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class XlsUserReportBuilderService extends XlsAbstractReportBuilderService {

    @Override
    void addRow(Row row, ReportDetail line) {
        ReportUserDetail lineLocal = (ReportUserDetail) line;
        int i = 0;
        row.createCell(i++).setCellValue(String.valueOf(lineLocal.getUserId()));
        row.createCell(i++).setCellValue(lineLocal.getEmail());
        row.createCell(i++).setCellValue(String.valueOf(lineLocal.getReviewsCount()));
        row.createCell(i).setCellValue(String.valueOf(lineLocal.getAverageRate()));
    }

    @Override
    Stream<String> getColumnsStream() {
        return Stream.of("userId", "email", "reviewsCount", "average_rate");
    }

}
