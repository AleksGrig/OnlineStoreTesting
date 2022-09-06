package com.automationstoretest.testcases;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.automationstoretest.base.Action;
import com.automationstoretest.base.Base;
import com.automationstoretest.pageobjects.IndexPage;
import com.automationstoretest.utility.DataProviders;

public class AccountCreationPageTest extends Base {

  @Test(dataProvider = "email", dataProviderClass = DataProviders.class,
        groups = "Sanity")
  public void createAccountTest(String email) {
    logger.info(() -> "start of createAccountTest");
    Assert.assertTrue(new IndexPage()
      .clickOnSignIn()
      .createAccount(email)
      .validateAccountCreatePage());
    logger.info(() -> "end of createAccountTest");
  }

  @Test(dataProvider = "userdata", dataProviderClass = DataProviders.class,
        groups = "Regression")
  public void createAccountWithPersonalData(HashMap<String, String> personalData) {
    logger.info(() -> "start of createAccountWithPersonalData");
    String expectedURL = "http://automationpractice.com/index.php?controller=my-account";
    Assert.assertEquals(new IndexPage()
      .clickOnSignIn()
      .createAccount(Action.getRandomEmail(personalData.get("Email")))
      .createAccount(personalData)
      .getCurrentURL(), expectedURL);
    logger.info(() -> "end of createAccountWithPersonalData");
  }
}
