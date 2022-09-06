package com.automationstoretest.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automationstoretest.base.Action;

public class SearchResultPage extends Action {
  @FindBy(css = "img[title='Faded Short Sleeve T-shirts']") WebElement tshirt;

  public SearchResultPage() {
    PageFactory.initElements(getDriver(), this);
  }

  public boolean isProductAvailable() {
    return tshirt.isDisplayed();
  }

  public AddToCartPage clickOnProduct() {
    tshirt.click();
    return new AddToCartPage();
  }
}
