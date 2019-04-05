package com.siatsenko.movieland.service.impl.report;

import com.siatsenko.movieland.entity.report.ReportDetail;
import com.siatsenko.movieland.service.ReportBuilderService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public abstract class XlsAbstractReportBuilderService implements ReportBuilderService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    abstract void addRow(Row row, ReportDetail lineIn);

    abstract Stream<String> getColumnsStream();

    @Override
    public String buildReportDetail(String fileName, Stream<? extends ReportDetail> stream) throws IOException {
        return buildReportLocal(fileName, stream);
    }

    private String buildReportLocal(String fileName, Stream<? extends ReportDetail> stream) throws IOException {
        String localFileName = getFileName(fileName);
        try (FileOutputStream fileOut = new FileOutputStream(localFileName);) {

            Workbook workbook = new HSSFWorkbook();
            Sheet sheet = workbook.createSheet("Report");

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setFontHeightInPoints((short) 10);
            headerFont.setColor(IndexedColors.DARK_BLUE.getIndex());

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            AtomicInteger count = new AtomicInteger();
            Row headerRow = sheet.createRow(count.getAndIncrement());
            addTableHeader(headerRow, headerCellStyle);

            stream.forEach(line -> {
                        Row row = sheet.createRow(count.getAndIncrement());
                        addRow(row, line);
                    }
            );

            for (int i = 0; i < 9; i++) {
                sheet.autoSizeColumn(i);
            }

            workbook.write(fileOut);
        }
        return localFileName;
    }

    private String getFileName(String fileName) {
        return fileName + ".xls";
    }

    private void addTableHeader(Row headerRow, CellStyle headerCellStyle) {
        AtomicInteger i = new AtomicInteger();
        getColumnsStream()
                .forEach(columnTitle -> {
                    Cell cell = headerRow.createCell(i.getAndIncrement());
                    cell.setCellValue(columnTitle);
                    cell.setCellStyle(headerCellStyle);
                });
    }


}
