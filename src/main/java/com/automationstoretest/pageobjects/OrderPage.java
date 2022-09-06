package com.automationstoretest.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automationstoretest.base.Action;

public class OrderPage extends Action {
  @FindBy(css = ".price>.price") WebElement unitPrice;
  @FindBy(css = "#total_price") WebElement totalPrice;
  @FindBy(css = "a.standard-checkout>span") WebElement procedeToChekoutBtn;

  public OrderPage() {
    PageFactory.initElements(getDriver(), this);
  }

  public double getUnitPrice() {
    return Double.parseDouble(unitPrice.getText().substring(1));
  }
  
  public double getTotalPrice() {
    return Double.parseDouble(totalPrice.getText().substring(1));
  }

  public LoginPage procedeToCheckout() {
    procedeToChekoutBtn.click();
    return new LoginPage();
  }
}