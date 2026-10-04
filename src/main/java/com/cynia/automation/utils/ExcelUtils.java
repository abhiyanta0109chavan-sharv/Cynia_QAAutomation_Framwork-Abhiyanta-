package com.cynia.automation.utils;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtils {

    private static final String FILE_PATH =
            "src/test/resources/testdata/LoginTestData.xlsx";

    public static String getCellData(String sheetName,
                                     String testCaseName,
                                     String columnName) {

        try (FileInputStream fis = new FileInputStream(FILE_PATH);
             Workbook workbook = WorkbookFactory.create(fis)) {

            // Print all available sheet names
            System.out.println("========== EXCEL SHEETS ==========");

            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                System.out.println(
                        "Sheet " + i + ": [" + workbook.getSheetName(i) + "]"
                );
            }

            System.out.println("Requested sheet: [" + sheetName + "]");
            System.out.println("=================================");

            Sheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                throw new RuntimeException(
                        "Sheet not found: " + sheetName
                );
            }

            DataFormatter formatter = new DataFormatter();

            Row headerRow = sheet.getRow(0);

            if (headerRow == null) {
                throw new RuntimeException(
                        "Header row is missing in sheet: " + sheetName
                );
            }

            int testCaseColumn = -1;
            int requiredColumn = -1;

            // Find required columns
            for (int i = 0; i < headerRow.getLastCellNum(); i++) {

                String header =
                        formatter.formatCellValue(
                                headerRow.getCell(i)
                        ).trim();

                if (header.equalsIgnoreCase("TestCase")) {
                    testCaseColumn = i;
                }

                if (header.equalsIgnoreCase(columnName)) {
                    requiredColumn = i;
                }
            }

            if (testCaseColumn == -1) {
                throw new RuntimeException(
                        "TestCase column not found"
                );
            }

            if (requiredColumn == -1) {
                throw new RuntimeException(
                        "Column not found: " + columnName
                );
            }

            // Find test case row
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {

                Row row = sheet.getRow(i);

                if (row == null) {
                    continue;
                }

                String testCase =
                        formatter.formatCellValue(
                                row.getCell(testCaseColumn)
                        ).trim();

                if (testCase.equalsIgnoreCase(testCaseName)) {

                    if (row.getCell(requiredColumn) == null) {
                        return "";
                    }

                    return formatter.formatCellValue(
                            row.getCell(requiredColumn)
                    ).trim();
                }
            }

            throw new RuntimeException(
                    "Test case not found: " + testCaseName
            );

        } catch (IOException e) {

            throw new RuntimeException(
                    "Unable to read Excel file: " + FILE_PATH,
                    e
            );
        }
    }
}