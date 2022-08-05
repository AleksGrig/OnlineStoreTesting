package com.automationstoretest.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automationstoretest.base.Base;

public class ShippingPage extends Base {
  @FindBy(css = "button[name='processCarrier']") WebElement procedeToCheckoutBtn;
  @FindBy(css = "#cgv") WebElement termsOfServiceCheckbox;

  public ShippingPage() {
    PageFactory.initElements(getDriver(), this);
  }

  public ShippingPage checkTheServiceTerms() {
    termsOfServiceCheckbox.click();
    return this;
  }

  public PaymentPage procedeToCheckout() {
    procedeToCheckoutBtn.click();
    return new PaymentPage();
  }
}
