package com.automationstoretest.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.automationstoretest.base.Base;
import com.automationstoretest.pageobjects.IndexPage;
import com.automationstoretest.utility.DataProviders;

public class AccountCreationPageTest extends Base {

  @Test(dataProvider = "email", dataProviderClass = DataProviders.class,
        groups = "Sanity")
  public void createAccountTest(String email) {
    logger.info("start of createAccountTest");
    Assert.assertTrue(new IndexPage()
      .clickOnSignIn()
      .createAccount(email)
      .validateAccountCreatePage());
    logger.info("end of createAccountTest");
  }
}
