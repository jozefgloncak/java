package gloncak.jozef;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

public class AppLoad {

    private static class ColumnEntry {
        String column;
        String type;

        ColumnEntry(String column, String type) {
            this.column = column;
            this.type = type;
        }
    }

    private static List<ColumnEntry> colEntries = Arrays.asList(new ColumnEntry("idma", "numeric")
    , new ColumnEntry("metal_level", "numeric")
    , new ColumnEntry("platnost_od", "date")
    , new ColumnEntry("nas_pro_over", "numeric")
    );

    public static void main(String[] args) throws Exception {
        try {
            File file = new File("c:\\Users\\jgloncak\\Documents\\koef.xlsx");
            Workbook wb = WorkbookFactory.create(file);
            Sheet sheet = wb.getSheetAt(0);

            Iterator<Row> rowIter = sheet.rowIterator();
            if (!rowIter.hasNext()) {
                return;
            }

            Row headerRow = rowIter.next();
            Iterator<Cell> iterator = headerRow.cellIterator();
            DataFormatter dataFormatter = new DataFormatter();
            List<String> headerData = new ArrayList<>();
            while (iterator.hasNext()) {
                Cell currCell = iterator.next();
                headerData.add(dataFormatter.formatCellValue(currCell));
                System.out.println(dataFormatter.formatCellValue(currCell));
            }

            Map<String, ColumnEntry> colEntryMap = colEntries.stream().collect(Collectors.toMap(item -> item.column,
                    item -> item));

            while (rowIter.hasNext()) {
                Row currRow = rowIter.next();
                Iterator<Cell> cellIter = currRow.cellIterator();
                int cellIdx = 0;
                while (cellIter.hasNext()) {
                    String colName = headerData.get(cellIdx++);
                    ColumnEntry colEntry = colEntryMap.get(colName.toLowerCase());
                    Cell currCell = cellIter.next();
                    Object val = "";
                    if (colEntry != null) {
                        if ("numeric".equals(colEntry.type)) {
                            val = new BigDecimal(currCell.getNumericCellValue());
                        } else if ("date".equals(colEntry.type)) {
                            val = currCell.getDateCellValue();
                        }
                    }
                    System.out.format("colunm: %s, type: %s, value: %s%n", colName, colEntry == null ? "" : colEntry.type, val);
                }
                System.out.println("=========");
            }



        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
    }
}
