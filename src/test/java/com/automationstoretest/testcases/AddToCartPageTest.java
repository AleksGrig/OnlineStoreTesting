package com.automationstoretest.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.automationstoretest.base.Base;
import com.automationstoretest.pageobjects.IndexPage;
import com.automationstoretest.utility.DataProviders;

public class AddToCartPageTest extends Base {

  @Test(dataProvider = "product", dataProviderClass = DataProviders.class,
        groups = {"Regression", "Sanity"})
  public void addToCartTest(String productName, String quantity, String size) {
    logger.info("start of addToCartTest");
    Assert.assertTrue(new IndexPage()
      .searchProduct(productName)
      .clickOnProduct()
      .enterQuantity(quantity)
      .selectSize(size)
      .clickOnAddToCart()
      .validateAddToCart());
    logger.info("end of addToCartTest");
  }
}
