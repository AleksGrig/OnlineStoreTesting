package com.automationstoretest.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automationstoretest.base.Action;

public class AddressPage extends Action {
  @FindBy(css = "button[name='processAddress']") WebElement procedeToCheckoutBtn;

  public AddressPage() {
    PageFactory.initElements(getDriver(), this);
  }

  public ShippingPage procedeToCheckout() {
    procedeToCheckoutBtn.click();
    return new ShippingPage();
  }
}
