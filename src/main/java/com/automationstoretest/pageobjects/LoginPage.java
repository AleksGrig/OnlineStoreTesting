package com.automationstoretest.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automationstoretest.base.Base;

public class LoginPage extends Base {
  @FindBy(id = "email") WebElement userName;
  @FindBy(id = "passwd") WebElement userPassword;
  @FindBy(id = "SubmitLogin") WebElement submitLoginnBtn;
  @FindBy(id = "email_create") WebElement createAccountEmail;
  @FindBy(id = "SubmitCreate") WebElement createAccountSubmitBtn;

  public LoginPage() {
    PageFactory.initElements(getDriver(), this);
  }

  public HomePage login(String email, String password) {
    fillLoginPasswordForm(email, password);
    return new HomePage();
  }

  public AddressPage loginWhenCheckout(String email, String password) {
    fillLoginPasswordForm(email, password);
    return new AddressPage();
  }
  
  private void fillLoginPasswordForm(String email, String password) {
    userName.sendKeys(email);
    userPassword.sendKeys(password);
    submitLoginnBtn.click();
  }

  public AccountCreationPage createAccount(String email) {
    createAccountEmail.sendKeys(email);
    createAccountSubmitBtn.click();
    return new AccountCreationPage();
  }
}
