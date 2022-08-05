package com.automationstoretest.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automationstoretest.base.Base;

public class OrderConfirmationPage extends Base {
  @FindBy(css = ".cheque-indent > strong.dark") WebElement orderCompletionMessage;

  public OrderConfirmationPage() {
    PageFactory.initElements(getDriver(), this);
  }

  public String validateCompletionMessage() {
    return orderCompletionMessage.getText();
  }
}
