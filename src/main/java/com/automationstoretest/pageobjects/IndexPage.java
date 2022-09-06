package com.automationstoretest.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automationstoretest.base.Action;

public class IndexPage extends Action {
  @FindBy(css = ".login") WebElement signInBtn;
  @FindBy(css = "a > img.logo") WebElement logo;
  @FindBy(css = "#search_query_top") WebElement searchProductBox;
  @FindBy(name = "submit_search") WebElement submitSearchBtn;

  public IndexPage() {
    PageFactory.initElements(getDriver(), this);
  }

  public LoginPage clickOnSignIn() {
    signInBtn.click();
    return new LoginPage();
  }

  public boolean validateLogo() {
    return logo.isDisplayed();
  }

  public String getTitle() {
    return getDriver().getTitle();
  }

  public SearchResultPage searchProduct(String productName) {
    searchProductBox.sendKeys(productName);
    submitSearchBtn.click();
    return new SearchResultPage();
  }
}