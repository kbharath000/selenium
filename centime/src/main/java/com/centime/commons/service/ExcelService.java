package com.centime.commons.service;

import java.io.IOException;
import java.util.Map;

public interface ExcelService {

  public Map<String, String> readExcel(String filePath, String sheetName, String testName)
      throws IOException;

}
