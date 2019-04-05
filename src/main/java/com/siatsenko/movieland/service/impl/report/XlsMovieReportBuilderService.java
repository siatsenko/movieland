package com.siatsenko.movieland.service.impl.report;

import com.siatsenko.movieland.entity.report.ReportDetail;
import com.siatsenko.movieland.entity.report.ReportMovieDetail;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class XlsMovieReportBuilderService extends XlsAbstractReportBuilderService {

    @Override
    void addRow(Row row, ReportDetail line) {
        ReportMovieDetail localLine = (ReportMovieDetail) line;
        int i = 0;
        row.createCell(i++).setCellValue(String.valueOf(localLine.getMovieId()));
        row.createCell(i++).setCellValue(localLine.getTitle());
        row.createCell(i++).setCellValue(localLine.getDescription());
        row.createCell(i++).setCellValue(localLine.getGenre());
        row.createCell(i++).setCellValue(String.valueOf(localLine.getPrice()));
        row.createCell(i++).setCellValue(localLine.getAddDate().toString());
        row.createCell(i++).setCellValue(localLine.getModifiedDate().toString());
        row.createCell(i++).setCellValue(String.valueOf(localLine.getRating()));
        row.createCell(i).setCellValue(String.valueOf(localLine.getReviewsCount()));
    }

    @Override
    Stream<String> getColumnsStream() {
        return Stream.of("movieId", "title", "description", "genre", "price", "addDate", "modifiedDate", "rating", "reviewsCount");
    }

}
