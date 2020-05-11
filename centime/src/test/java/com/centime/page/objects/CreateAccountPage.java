package com.centime.page.objects;

import com.centime.selenium.utils.SeleniumUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.AssertJUnit;
import org.testng.Reporter;

public class CreateAccountPage {

  SeleniumUtils seleniumUtils;


  static By maleGender = By.id("id_gender1");
  static By femaleGender = By.id("id_gender2");
  static By customerFirstName = By.id("customer_firstname");
  static By customerLastName = By.id("customer_lastname");
  static By customerPassword = By.id("passwd");
  static By emailId = By.id("email");
  static By days = By.id("days");
  static By months = By.id("months");
  static By years = By.id("years");
  static By firstName = By.id("firstname");
  static By laststName = By.id("lastname");
  static By company = By.id("company");
  static By address = By.id("address1");
  static By city = By.id("city");
  static By state = By.id("id_state");
  static By postalCode = By.id("postcode");
  static By country = By.id("id_country");
  static By phoneMobile = By.id("phone_mobile");
  static By aliasAddress = By.xpath("//input[@value='My address']");
  static By registerButton = By.xpath("//span[contains(text(),'Register')]");

  static By passwordError =
      By.xpath("//*[@class='required password form-group form-error']/label[contains(text(),'Password ')]/../input");

  By validateErrorGroup(String name) {
    By errorGroup =
        By.xpath("//*[@class='required form-group form-error']/label[contains(text(),'" + name + "')]/../input");
    return errorGroup;
  }

  public CreateAccountPage(SeleniumUtils seleniumUtils) {
    this.seleniumUtils = seleniumUtils;
  }

  public void validatieErrors(String fName, String lName, String password) {
    seleniumUtils.sendInput(customerFirstName, fName);
    seleniumUtils.sendKeys(customerFirstName, Keys.TAB);
    seleniumUtils.visibilityOfElementLocated(validateErrorGroup("First name"));
    AssertJUnit.assertTrue(seleniumUtils.isElementPresent(validateErrorGroup("First name")));
    seleniumUtils.sendInput(customerLastName, lName);
    seleniumUtils.sendKeys(customerLastName, Keys.TAB);
    seleniumUtils.visibilityOfElementLocated(validateErrorGroup("Last name"));
    AssertJUnit.assertTrue(seleniumUtils.isElementPresent(validateErrorGroup("Last name")));
    seleniumUtils.sendInput(customerPassword, password);
    seleniumUtils.sendKeys(customerPassword, Keys.TAB);
    seleniumUtils.visibilityOfElementLocated(passwordError);
    AssertJUnit.assertTrue(seleniumUtils.isElementPresent(passwordError));
  }

  public void selectGender(String gender) {
    if (gender.equalsIgnoreCase("Mr"))
      seleniumUtils.click(maleGender);
    else
      seleniumUtils.click(femaleGender);
  }

  public void verifyEmailAddress(String email) {
    String emailValue = seleniumUtils.getText(emailId);
    AssertJUnit.assertEquals(email, emailValue);
    Reporter.log("Asserted the email address" + email);
  }

  public void sendvalidNameAndPassword(String fName, String lName, String password) {
    seleniumUtils.sendInput(customerFirstName, fName);
    seleniumUtils.sendKeys(customerFirstName, Keys.TAB);
    Reporter.log("Enter First Name as Input" + fName);
    seleniumUtils.sendInput(customerLastName, lName);
    seleniumUtils.sendKeys(customerLastName, Keys.TAB);
    Reporter.log("Enter Last Name as Input" + lName);
    seleniumUtils.sendInput(customerPassword, password);
    seleniumUtils.sendKeys(customerPassword, Keys.TAB);
    Reporter.log("Enter Password as Input" + password);
  }

  public void sendValidFirstNameLastName(String fName, String lName, String addres) {
    seleniumUtils.sendInput(firstName, fName);
    seleniumUtils.sendKeys(firstName, Keys.TAB);
    Reporter.log("Enter First Name as Input" + fName);
    seleniumUtils.sendInput(laststName, lName);
    seleniumUtils.sendKeys(laststName, Keys.TAB);
    Reporter.log("Enter Last Name as Input" + lName);
    seleniumUtils.sendInput(address, addres);
    seleniumUtils.sendKeys(address, Keys.TAB);
    Reporter.log("Enter address as Input" + addres);
  }

  public void selectDateOfBirth(String day, String month, String year) {
    seleniumUtils.selectByValue(days, day.substring(0, 2));
    Reporter.log("Selected given days" + day.substring(0, 2) + " as Input");
    seleniumUtils.selectByValue(months, month.substring(0, 1));
    Reporter.log("Selected given months" + month.substring(0, 1) + " as Input");
    seleniumUtils.selectByValue(years, year.substring(0, 4));
    Reporter.log("Selected given Year" + year.substring(0, 4) + " as Input");
  }

  public void selectStateAndCountry(String inputState, String inputCountry) {
    seleniumUtils.selectByVisibleText(state, inputState);
    Reporter.log("Selected given state: "+inputState+" as Input");
    seleniumUtils.selectByVisibleText(country, inputCountry);
    Reporter.log("Selected given Country" + inputCountry + " as Input");
  }

  public void sendCityAndPostalCode(String cityName, String zipCode) {
    seleniumUtils.sendInput(city, cityName);
    seleniumUtils.sendKeys(city, Keys.TAB);
    Reporter.log("Entered City as Input");
    seleniumUtils.sendInput(postalCode, zipCode.substring(0, 5));
    seleniumUtils.sendKeys(postalCode, Keys.TAB);
    Reporter.log("Entered Postal Code Input");
  }

  public void sendPhoneAndAddress(String phoneNumber, String adress) {
    seleniumUtils.sendInput(phoneMobile, phoneNumber);
    Reporter.log("Enter Phone Number as Input");
    seleniumUtils.sendKeys(phoneMobile, Keys.TAB);
    seleniumUtils.sendInput(aliasAddress, adress);
    Reporter.log("Entered Address Input");
    seleniumUtils.sendKeys(aliasAddress, Keys.TAB);
  }

  public void selectRegisterButton() {
    seleniumUtils.click(registerButton);
    Reporter.log("Click On Register Button");
  }
}
