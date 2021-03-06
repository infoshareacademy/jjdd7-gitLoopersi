package com.infoshareacademy.gitloopersi.deserialization;

import com.infoshareacademy.gitloopersi.properties.AppConfig;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.Properties;

public class AppConfigLoader {

  private final String FILEPATH = "application.properties";

  public void loadConfig() {
    try (InputStream input = new FileInputStream(FILEPATH)) {
      Properties appProperties = new Properties();
      appProperties.load(input);
      AppConfig.setDateFormat(loadDataFormatChecker(appProperties));
      AppConfig.setSortType(loadSortTypeChecker(appProperties));
    } catch (IOException iOE) {
      System.out.println("Error with loading properties");
      AppConfig.setDateFormat("yyyy.MM.dd");
      AppConfig.setSortType("ASC");
    }
  }

  private String loadDataFormatChecker(Properties properties) {
    String validatedData = null;
    try {
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
          properties.getProperty("date.format"));
      validatedData = Optional.ofNullable(properties.getProperty("date.format"))
          .orElse("yyyy.MM.dd");
    } finally {
      if (validatedData == null) {
        System.out
            .println(
                "Bad date format in properties file, default value \"yyyy.MM.dd\" has been set");
        validatedData = "yyyy.MM.dd";
      }
    }
    return validatedData;
  }

  private String loadSortTypeChecker(Properties properties) {
    String validatedSortType = null;
    if (properties.containsKey("sort.type") && (
        properties.getProperty("sort.type").toUpperCase().equals("ASC") || properties
            .getProperty("sort.type")
            .toUpperCase().equals("DESC"))) {
      validatedSortType = properties.getProperty("sort.type");
    } else {
      System.out.println(
          "Bad sort or no type format in properties file, default value \"ASC\" has been set");
      validatedSortType = "ASC";
    }

    return validatedSortType;
  }
}
