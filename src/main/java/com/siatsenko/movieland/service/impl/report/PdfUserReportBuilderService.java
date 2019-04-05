package com.siatsenko.movieland.service.impl.report;

import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPTable;
import com.siatsenko.movieland.entity.report.ReportDetail;
import com.siatsenko.movieland.entity.report.ReportUserDetail;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class PdfUserReportBuilderService extends PdfAbstractReportBuilderService {

    @Override
    void addRow(PdfPTable table, ReportDetail line, Font font) {
        ReportUserDetail localLine = (ReportUserDetail) line;
        table.addCell(String.valueOf(localLine.getUserId()));
        table.addCell(localLine.getEmail().toString());
        table.addCell(String.valueOf(localLine.getReviewsCount()));
        table.addCell(String.valueOf(localLine.getAverageRate()));
    }

    @Override
    PdfPTable getNewTable() {
        return new PdfPTable(new float[]{3, 7, 1, 1});
    }

    @Override
    Stream<String> getColumnsStream() {
        return Stream.of("userId", "email", "reviewsCount", "average_rate");
    }

}
