package com.centime.page.objects;

import com.centime.selenium.utils.SeleniumUtils;

import org.openqa.selenium.By;
import org.testng.Assert;

public class AuthenticationPage {

  SeleniumUtils seleniumUtils;

  private static By emailCreate = By.id("email_create");
  private static By createAccount = By.id("SubmitCreate");
  private static By createAccountHeader = By.xpath("//*[text()='Create an account']");
  private static By accountAlreadyRegisterError =
      By.xpath("//li[contains(text(),'An account using this email address has already be')]");

  public AuthenticationPage(SeleniumUtils seleniumUtils) {
    this.seleniumUtils = seleniumUtils;
  }

  public void verifyGivenEmail(String emailAddress) {
    seleniumUtils.sendInput(emailCreate, emailAddress);
    seleniumUtils.click(createAccount);
  }

  public void verifyHeaderOfCreationAccount() {
    Assert.assertTrue(seleniumUtils.isElementPresent(createAccountHeader));
  }

  public void verifyAccountAlreadyPresentError() {
    Assert.assertTrue(seleniumUtils.isElementPresent(accountAlreadyRegisterError));
  }

}
