package com.centime.page.objects;

import com.centime.selenium.utils.SeleniumUtils;

import org.openqa.selenium.By;
import org.testng.Assert;

public class IndexPage {

  SeleniumUtils seleniumUtils;

  public static By signIn = By.linkText("Sign in");

  public IndexPage(SeleniumUtils seleniumUtils) {
    this.seleniumUtils = seleniumUtils;
  }

  public void navigateToPage(String url, String title) {
    seleniumUtils.navigateToUrl(url);
    String getTitle = seleniumUtils.getTitle();
    Assert.assertEquals(getTitle, title);
  }

  public void clickSigin() {
    seleniumUtils.click(IndexPage.signIn);
  }

}
