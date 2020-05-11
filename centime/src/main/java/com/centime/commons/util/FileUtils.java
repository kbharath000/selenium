package com.centime.commons.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtils {

  public static InputStream readFile(String filePath) throws IOException {
    byte[] bytesArray = Files.readAllBytes(Paths.get(filePath));
    InputStream inputStream = new ByteArrayInputStream(bytesArray);
    return inputStream;
  }

  public static <T> T readFile(String fileName, Class<T> elem) throws IOException {
    final String json = new String(Files.readAllBytes(Paths.get(fileName)), Charset.forName("UTF-8"));
    return MapperUtils.MAPPER.readValue(json, elem);
  }


}
