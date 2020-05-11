package com.centime.selenium.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class SeleniumUtils {

  private WebDriver driver;

  public SeleniumUtils(WebDriver webDriver) {
    this.driver = webDriver;
  }

  public void navigateToUrl(String url) {
    driver.navigate().to(url);
  }

  public void refreshPage() {
    driver.navigate().refresh();
  }

  public String getTitle() {
    return driver.getTitle();
  }

  public boolean isElementPresent(By by) {
    boolean value = false;
    if (driver.findElement(by) != null) {
      WebElement ele = driver.findElement(by);
      highLighterMethod(driver, ele);
      return true;
    }
    return value;
  }

  public boolean sendInput(By by, String input) {
    boolean value = false;
    if (isElementPresent(by)) {
      driver.findElement(by).sendKeys(input);
      return true;
    }
    return value;
  }

  public void sendKeys(By by, Keys keys) {
    if (isElementPresent(by)) {
      driver.findElement(by).sendKeys(keys);
    }
  }

  public String getText(By by) {
    if (isElementPresent(by)) {
      return driver.findElement(by).getText();
    }
    return null;
  }

  public void visibilityOfElementLocated(By by) {
    WebDriverWait wait = new WebDriverWait(driver, 50);
    wait.until(ExpectedConditions.visibilityOfElementLocated(by));
  }

  public boolean click(By by) {
    boolean value = false;
    if (isElementPresent(by)) {
      driver.findElement(by).click();
      return true;
    }
    return value;
  }

  public boolean selectByValue(By by, String selectValue) {
    boolean value = false;
    if (isElementPresent(by)) {
      WebElement element = driver.findElement(by);
      highLighterMethod(driver, element);
      Select select = new Select(element);
      select.selectByValue(selectValue);
      return true;
    }
    return value;
  }

  public boolean selectByIndex(By by, int selectIndex) {
    boolean value = false;
    if (isElementPresent(by)) {
      WebElement element = driver.findElement(by);
      highLighterMethod(driver, element);
      Select select = new Select(element);
      select.selectByIndex(selectIndex);
      return true;
    }
    return value;
  }

  public boolean selectByVisibleText(By by, String visibleText) {
    boolean value = false;
    if (isElementPresent(by)) {
      WebElement element = driver.findElement(by);
      highLighterMethod(driver, element);
      Select select = new Select(element);
      select.selectByVisibleText(visibleText);
      return true;
    }
    return value;
  }

  public void switchToFrameByName(String frameName) {
    driver.switchTo().frame(frameName);
  }

  public void switchToFrameByIndex(int index) {
    driver.switchTo().frame(index);
  }

  public void switchToFrameByWebElement(WebElement frameElement) {
    driver.switchTo().frame(frameElement);
  }

  public boolean selectAllByValue(By by, List<String> list) {
    boolean value = false;
    if (isElementPresent(by)) {
      WebElement element = driver.findElement(by);
      highLighterMethod(driver, element);
      Select select = new Select(element);
      for (String input : list) {
        select.selectByValue(input);
      }
      return true;
    }
    return value;
  }

  public boolean selectAllByIndex(By by, List<Integer> list) {
    boolean value = false;
    if (isElementPresent(by)) {
      WebElement element = driver.findElement(by);
      highLighterMethod(driver, element);
      Select select = new Select(element);
      for (int input : list) {
        select.selectByIndex(input);
      }
      return true;
    }
    return value;
  }

  public boolean deSelectByDropDownValue(By by, String inputValue) {
    boolean value = false;
    if (isElementPresent(by)) {
      WebElement element = driver.findElement(by);
      highLighterMethod(driver, element);
      Select select = new Select(element);
      select.deselectByValue(inputValue);
      return true;
    }
    return value;
  }

  public List<WebElement> getAllSelectedValues(By by) {
    if (isElementPresent(by)) {
      WebElement element = driver.findElement(by);
      highLighterMethod(driver, element);
      Select select = new Select(element);
      return select.getAllSelectedOptions();
    }
    return Collections.emptyList();
  }

  public void takeScreenshotAtEndOfTest() throws IOException {
    File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    String currentDir = System.getProperty("user.dir");
    FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
  }

  public void highLighterMethod(WebDriver driver, WebElement element) {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("arguments[0].setAttribute('style', 'border: 4px solid green;');", element);
  }
}
