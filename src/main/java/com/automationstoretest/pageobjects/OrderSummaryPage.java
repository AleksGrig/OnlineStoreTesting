package com.automationstoretest.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automationstoretest.base.Action;

public class OrderSummaryPage extends Action {
  @FindBy(css = "#cart_navigation > button") WebElement iConfirmMyOrderBtn;

  public OrderSummaryPage() {
    PageFactory.initElements(getDriver(), this);
  }

  public OrderConfirmationPage clickOnConfirmOrder() {
    iConfirmMyOrderBtn.click();
    return new OrderConfirmationPage();
  }
}
