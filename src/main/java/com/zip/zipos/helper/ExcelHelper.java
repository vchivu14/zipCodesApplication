package com.zip.zipos.helper;

import com.zip.zipos.models.ZipCity;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    public static boolean hasExcelFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }

    public static List<ZipCity> excelToZipCityDB(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            List<ZipCity> zipCities = new ArrayList<>();
            int rowNumber = 0;
            while(rowIterator.hasNext()) {
                Row currentRow = rowIterator.next();

                //skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellIterator = currentRow.iterator();
                ZipCity zipCity = new ZipCity();

                int cellNumber = 0;
                while (cellIterator.hasNext()) {
                    Cell currentCell = cellIterator.next();
                    switch (cellNumber) {
                        case 0:
                            zipCity.setZipcode((long)currentCell.getNumericCellValue());
                            break;
                        case 1:
                            zipCity.setCity(currentCell.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                    cellNumber++;
                }
                zipCities.add(zipCity);
            }
            workbook.close();
            return zipCities;
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse Excel file: " + e.getMessage());
        }
    }
}
