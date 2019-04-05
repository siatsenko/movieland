package com.siatsenko.movieland.service.impl.report;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.siatsenko.movieland.entity.report.ReportDetail;
import com.siatsenko.movieland.entity.report.ReportMovieDetail;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class PdfMovieReportBuilderService extends PdfAbstractReportBuilderService {

    @Override
    void addRow(PdfPTable table, ReportDetail line, Font font) {
        ReportMovieDetail localLine = (ReportMovieDetail) line;
        table.addCell(String.valueOf(localLine.getMovieId()));
        table.addCell(new Phrase(localLine.getTitle(), font));
        table.addCell(new Phrase(localLine.getDescription(), font));
        table.addCell(new Phrase(localLine.getGenre(), font));
        table.addCell(String.valueOf(localLine.getPrice()));
        table.addCell(localLine.getAddDate().toString());
        table.addCell(localLine.getModifiedDate().toString());
        table.addCell(String.valueOf(localLine.getRating()));
        table.addCell(String.valueOf(localLine.getReviewsCount()));
    }

    @Override
    PdfPTable getNewTable() {
        return new PdfPTable(new float[]{1, 7, 10, 3, 1, 2, 2, 1, 1});
    }

    @Override
    Stream<String> getColumnsStream() {
        return Stream.of("movieId", "title", "description", "genre", "price", "addDate", "modifiedDate", "rating", "reviewsCount");
    }

}
