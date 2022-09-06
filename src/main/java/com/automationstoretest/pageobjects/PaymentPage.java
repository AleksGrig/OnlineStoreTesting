package com.automationstoretest.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automationstoretest.base.Action;

public class PaymentPage extends Action {
  @FindBy(css = "a[title='Pay by bank wire']") WebElement payByBankWireLink;
  @FindBy(css = "a[title='Pay by check.']") WebElement payByCheckLink;

  public PaymentPage() {
    PageFactory.initElements(getDriver(), this);
  }

  public OrderSummaryPage clickOnPayByBankWire() {
    payByBankWireLink.click();
    return new OrderSummaryPage();
  }
}
