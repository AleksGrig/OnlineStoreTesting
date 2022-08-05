package com.automationstoretest.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automationstoretest.base.Base;

public class AccountCreationPage extends Base{
  @FindBy(css = "h1.page-heading") WebElement formTitle;

  public AccountCreationPage() {
    PageFactory.initElements(getDriver(), this);
  }

  public boolean validateAccountCreatePage() {
    return formTitle.isDisplayed();
  }
}
