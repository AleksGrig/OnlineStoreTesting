package com.automationstoretest.pageobjects;

import java.util.HashMap;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automationstoretest.base.Action;

public class AccountCreationPage extends Action {
  @FindBy(css = "h1.page-heading") WebElement formTitle;
  @FindBy(id = "id_gender1") WebElement titleMr;
  @FindBy(id = "id_gender2") WebElement titleMrs;
  @FindBy(id = "customer_firstname") WebElement firstNamePI;
  @FindBy(id = "customer_lastname") WebElement lastNamePI;
  @FindBy(id = "email") WebElement email;
  @FindBy(id = "passwd") WebElement password;
  @FindBy(name = "days") WebElement days;
  @FindBy(name = "months") WebElement months;
  @FindBy(name = "years") WebElement years;
  @FindBy(id = "newsletter") WebElement newsletter;
  @FindBy(id = "optin") WebElement specialOffers;
  @FindBy(id = "firstname") WebElement firstNameAdress;
  @FindBy(id = "lastname") WebElement lastNameAdress;
  @FindBy(id = "company") WebElement company;
  @FindBy(id = "address1") WebElement address1;
  @FindBy(id = "address2") WebElement address2;
  @FindBy(id = "city") WebElement city;
  @FindBy(name = "id_state") WebElement state;
  @FindBy(id = "postcode") WebElement postcode;
  @FindBy(name = "id_country") WebElement country;
  @FindBy(id = "other") WebElement otherInfo;
  @FindBy(id = "phone") WebElement homePhone;
  @FindBy(id = "phone_mobile") WebElement mobilePhone;
  @FindBy(id = "alias") WebElement adressAlias;
  @FindBy(id = "submitAccount") WebElement registerBtn;

  public AccountCreationPage() {
    PageFactory.initElements(getDriver(), this);
  }

  public boolean validateAccountCreatePage() {
    return formTitle.isDisplayed();
  }

  public HomePage createAccount(HashMap<String, String> personalData) {
    switch(personalData.get("Gender").toLowerCase()) {
      case "mrs": titleMrs.click(); break;
      default:   titleMr.click() ; break;
    }
    firstNamePI.sendKeys(personalData.get("FirstName"));
    lastNamePI.sendKeys(personalData.get("LastName"));
    password.sendKeys(personalData.get("SetPassword"));
    selectByValue(days, personalData.get("Day"));
    selectByValue(months, personalData.get("Month"));
    selectByValue(years, personalData.get("Year"));
    company.sendKeys(personalData.get("Company"));
    address1.sendKeys(personalData.get("Address"));
    city.sendKeys(personalData.get("City"));
    selectByVisibleText(state, personalData.get("State"));
    postcode.sendKeys(personalData.get("Zipcode"));
    selectByVisibleText(country, personalData.get("Country"));
    mobilePhone.sendKeys(personalData.get("MobilePhone"));

    registerBtn.click();
    return new HomePage();
  }
}
