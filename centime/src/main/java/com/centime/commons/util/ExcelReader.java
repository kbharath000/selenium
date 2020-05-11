package com.centime.commons.util;

import com.centime.commons.service.ExcelService;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExcelReader implements ExcelService {

  public Map<String, String> readExcel(String filePath, String sheetName, String testName)
      throws IOException {
    Map<String, String> map = new HashMap<String, String>();

    Sheet sheet = getSheet(filePath, sheetName);
    int lastCellNum = 0;
    if (sheet.getRow(0).getCell(0).getStringCellValue() != null) {
      lastCellNum = sheet.getRow(0).getLastCellNum();
    }
    int rowValueOfTestName = getRowValueOfTestName(sheet, testName);
    for (int j = 1; j < lastCellNum; j++) {
      map.put(sheet.getRow(0).getCell(j).toString(),
          sheet.getRow(rowValueOfTestName).getCell(j) != null ? sheet.getRow(rowValueOfTestName).getCell(j).toString()
              : null);
    }
    return map;
  }


  private Sheet getSheet(String filePath, String sheetName) throws IOException {
    Workbook workbook = null;
    Sheet sheet = null;

    if (filePath.toLowerCase().endsWith("xlsx")) {
      workbook = new XSSFWorkbook(FileUtils.readFile(filePath));
      sheet = workbook.getSheet(sheetName);
      workbook.close();
    } else if (filePath.toLowerCase().endsWith("xls")) {
      workbook = new HSSFWorkbook(FileUtils.readFile(filePath));
      sheet = workbook.getSheet(sheetName);
      workbook.close();
    }
    return sheet;
  }

  private int getRowValueOfTestName(Sheet sheet, String testName) {
    Map<String, Integer> name = readValues(sheet);
    if (name.containsKey(testName)) {
      return name.get(testName);
    }
    return 0;
  }

  private Map<String, Integer> readValues(Sheet sheet) {
    int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
    Map<String, Integer> name = new HashMap<String, Integer>();
    for (int i = 1; i <= rowCount; i++) {
      name.put(sheet.getRow(i).getCell(0).toString(), i);
    }
    return name;
  }
}
