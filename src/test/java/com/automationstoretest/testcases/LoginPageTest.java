package com.automationstoretest.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.automationstoretest.base.Base;
import com.automationstoretest.pageobjects.IndexPage;
import com.automationstoretest.utility.DataProviders;

public class LoginPageTest extends Base {

  @Test(dataProvider = "credentials", dataProviderClass = DataProviders.class,
        groups = "Smoke")
  public void loginTest(String username, String password) {
    logger.info("start of loginTest");
    String expectedURL = "http://automationpractice.com/index.php?controller=my-account";
    Assert.assertEquals(new IndexPage()
      .clickOnSignIn() 
      .login(username, password) 
      .getCurrentURL(), expectedURL);
    logger.info("end of loginTest");
  }
}
