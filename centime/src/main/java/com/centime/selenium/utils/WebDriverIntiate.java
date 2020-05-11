package com.centime.selenium.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class WebDriverIntiate {

  public WebDriver getWebDriver(String browserName) {
    WebDriver driver;
    if ("chrome".equalsIgnoreCase(browserName)) {
      // Uncomment below line current chrome browser is above or equal to 83 version and comment Systemset property
      // WebDriverManager.chromedriver().setup();
      
      System.setProperty("webdriver.chrome.driver",
          System.getProperty("user.dir") + File.separator + "drivers" + File.separator + "chromedriver.exe");
      driver = new ChromeDriver();
      driver.manage().window().maximize();
      driver.manage().deleteAllCookies();
      driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
      driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

      return driver;
    } else if (browserName.equalsIgnoreCase("firefox")) {
      WebDriverManager.firefoxdriver().setup();
      driver = new FirefoxDriver();
      driver.manage().window().maximize();
      driver.manage().deleteAllCookies();
      driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
      driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
      return driver;
    } else {
      WebDriverManager.edgedriver().setup();
      driver = new EdgeDriver();
      driver.manage().window().maximize();
      driver.manage().deleteAllCookies();
      driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
      driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
      return driver;
    }
  }

}
