package com.centime.selenium.test;

import com.centime.commons.service.ExcelService;
import com.centime.commons.util.ExcelReader;
import com.centime.page.objects.AuthenticationPage;
import com.centime.page.objects.CreateAccountPage;
import com.centime.page.objects.IndexPage;
import com.centime.selenium.utils.SeleniumUtils;
import com.centime.selenium.utils.WebDriverIntiate;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AuthorizationNegativeTest {

  WebDriverIntiate initiate = new WebDriverIntiate();
  WebDriver driver;

  SeleniumUtils selenium;
  IndexPage indexPage;
  AuthenticationPage authenticationPage;
  CreateAccountPage createAccountPage;
  Map<String, String> map;
  String url = "http://automationpractice.com/index.php";


  @BeforeMethod
  @Parameters(value = {"browser"})
  public void testSetUp(String browser) {
    driver = initiate.getWebDriver(browser);
    selenium = new SeleniumUtils(driver);
    indexPage = new IndexPage(selenium);
    authenticationPage = new AuthenticationPage(selenium);
    createAccountPage = new CreateAccountPage(selenium);
  }

  @Test
  public void authorizationCheckEmailAlreadyExistTest() {
    Reporter.log("Authorization Check Email Already Exist Test Started");
    indexPage.navigateToPage(url, "My Store");
    indexPage.clickSigin();
    authenticationPage.verifyHeaderOfCreationAccount();
    authenticationPage.verifyGivenEmail("test123@gmail.com");
    authenticationPage.verifyAccountAlreadyPresentError();
    Reporter.log("Authorization Check Email Already Exist Test Finished");
  }

  @Test
  public void authorizationValidationErrorsTest() throws IOException {
    Reporter.log("Authorization Validation Errors Test Started");
    indexPage.navigateToPage(url, "My Store");
    indexPage.clickSigin();
    authenticationPage.verifyHeaderOfCreationAccount();
    map = new HashMap<String, String>();
    map = readData("Registration", "authorizationValidationErrorsTest");
    String fName = map.get("firstName");
    String lName = map.get("lastName");
    String password = map.get("password");
    String email = map.get("email");
    authenticationPage.verifyGivenEmail(email);
    createAccountPage.validatieErrors(fName, lName, password);
    Reporter.log("Authorization Validation Errors Test Finished");
  }

  @AfterMethod
  public void tearDown() {
    driver.quit();
  }

  private Map<String, String> readData(String sheetName, String testName) throws IOException {
    ExcelService service = new ExcelReader();
    String filePath = "src/main/resources/TestData.xlsx";
    return service.readExcel(filePath, sheetName, testName);

  }

}
