package gloncak.jozef;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class App {

    private CellStyle headerStyle;
    private CellStyle dateStyle;
    private Workbook wb = new SXSSFWorkbook();

    public static void main(String[] args) throws IOException {
        App app = new App();
        app.generujWorkbook();
    }

    private void generujWorkbook() throws IOException {
        writeSheetData(wb.createSheet("sheetA"));
        writeSheetData(wb.createSheet("sheetB"));

        FileOutputStream fos = new FileOutputStream(new File("wb.xlsx"));
        wb.write(fos);
        wb.close();

    }

    private void writeSheetData(Sheet sheet) {
        sheet.setColumnWidth(1, 13*256);
        sheet.setColumnWidth(2, 5000);

        CellStyle headerStyle = provideHeaderStyle();

        createHeader(Arrays.asList("Meno", "Priezvisko", "Datum narodenia", "Vyska", "Vaha"), sheet.createRow(0), headerStyle);

        createData(Arrays.asList(
                Arrays.asList("Andrej", "Andrejovic", LocalDate.of(1985, 5, 5), 180, 79.5)
                ,Arrays.asList("Barbora", "Barborovicova", LocalDate.of(1990, 12, 1), 165, 54.5)
                ), sheet);
    }

    private void createData(List<List<Object>> data, Sheet sheet) {
        int idx = 1;
        for(List<Object> dataRow : data) {
            Row sheetRow = sheet.createRow(idx++);
            int j = 0;
            for (Object dataItem: dataRow) {
                Cell cell = sheetRow.createCell(j++);
                if (dataItem instanceof String) {
                    cell.setCellValue((String) dataItem);
                } else if (dataItem instanceof LocalDate) {
                    cell.setCellStyle(provideDateStyle());
                    cell.setCellValue((LocalDate) dataItem);
                } else if (dataItem instanceof BigDecimal) {
                    cell.setCellValue(((BigDecimal)dataItem).doubleValue());
                } else if (dataItem instanceof Double) {
                    cell.setCellValue((Double)dataItem);
                } else if (dataItem instanceof Integer) {
                    cell.setCellValue(((Integer)dataItem));
                }
            }
        }
    }


    private CellStyle provideHeaderStyle() {
        if (this.headerStyle == null) {
            CellStyle style = wb.createCellStyle();

            style.setAlignment(HorizontalAlignment.CENTER);
            style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            style.setWrapText(true);
            this.headerStyle = style;
        }
        return this.headerStyle;
    }


    private CellStyle provideDateStyle() {
        if (this.dateStyle == null) {
            CellStyle style = wb.createCellStyle();
            DataFormat format = wb.createDataFormat();
            style.setDataFormat(format.getFormat("yyyy-dd-mm"));
            this.dateStyle = style;
        }
        return this.dateStyle;
    }

    private void createHeader(List<String> headerItems, Row row, CellStyle style) {
        int idx = 0;
        for (String headerItem : headerItems) {
            Cell cell = row.createCell(idx++);
            cell.setCellStyle(style);

            cell.setCellValue(headerItem);
        }
    }
}
