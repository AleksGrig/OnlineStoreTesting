package com.automationstoretest.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automationstoretest.base.Base;

public class HomePage extends Base {
  @FindBy(css = "a[title='My wishlists'] > span") WebElement myWishlists;
  @FindBy(css = "a[title='Orders'] > span") WebElement myOrderHistory;

  public HomePage() {
    PageFactory.initElements(getDriver(), this);
  }

  public boolean validateMyWishlists() {
    return myWishlists.isDisplayed();
  }

  public boolean validateOrderHistory() {
    return myOrderHistory.isDisplayed();
  }

  public String getCurrentURL() {
    return getDriver().getCurrentUrl();
  }

  public boolean validateHomePage(String currentURL) {
    return currentURL.equals("http://automationpractice.com/index.php?controller=my-account");
  }
}
