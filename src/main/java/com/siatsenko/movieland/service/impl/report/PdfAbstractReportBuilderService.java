package com.siatsenko.movieland.service.impl.report;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.siatsenko.movieland.entity.report.ReportDetail;
import com.siatsenko.movieland.service.ReportBuilderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

@Service
public abstract class PdfAbstractReportBuilderService implements ReportBuilderService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    abstract void addRow(PdfPTable table, ReportDetail line, Font font);

    abstract PdfPTable getNewTable();

    abstract Stream<String> getColumnsStream();

    @Override
    public String buildReportDetail(String fileName, Stream<? extends ReportDetail> stream) throws IOException, DocumentException {
        return buildReportLocal(fileName, stream);
    }

    private String buildReportLocal(String fileName, Stream<? extends ReportDetail> stream) throws DocumentException, IOException {
        String localFileName = getFileName(fileName);
        Document document = new Document();
        try (FileOutputStream fileOut = new FileOutputStream(localFileName);) {

            PdfWriter.getInstance(document, fileOut);
            document.setMargins(10.0f, 10.0f, 10.0f, 10.0f);
            document.setPageSize(PageSize.A4.rotate());
            Font font = FontFactory.getFont("TIMES.TTF", "Cp1250", BaseFont.EMBEDDED);
            document.open();
            PdfPTable table = getNewTable();
            table.setComplete(false);
            addTableHeader(table);

            AtomicInteger count = new AtomicInteger();
            stream.forEach(line -> {
                        addRow(table, line, font);
                        if ((count.getAndIncrement() % 10) == 0) {
                            try {
                                document.add(table);
                            } catch (DocumentException e) {
                                e.printStackTrace();
                            }
                        }
                    }
            );
            table.setComplete(true);
            document.add(table);
            document.close();
        }
        return localFileName;
    }

    private String getFileName(String fileName) {
        return fileName + ".pdf";
    }

    private void addTableHeader(PdfPTable table) {
        getColumnsStream()
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
        table.normalizeHeadersFooters();
    }

}
