package com.automationstoretest.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.automationstoretest.base.Base;
import com.automationstoretest.pageobjects.HomePage;
import com.automationstoretest.pageobjects.IndexPage;

public class HomePageTest extends Base {

  @Test(groups = "Sanity")
  public void wishlistTest() {
    logger.info("start of wishlistTest");
    Assert.assertTrue(login().validateMyWishlists());
    logger.info("end of wishlistTest");
  }
  
  @Test(groups = "Sanity")
  public void orderHistoryTest() {
    logger.info("start of orderHistoryTest");
    Assert.assertFalse(login().validateOrderHistory());
    logger.info("end of orderHistoryTest");
  }

  private HomePage login() {
    return new IndexPage()
      .clickOnSignIn()
      .login(properties.getProperty("username"), properties.getProperty("password"));
  }
}
