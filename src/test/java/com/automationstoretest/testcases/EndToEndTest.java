package com.automationstoretest.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.automationstoretest.base.Base;
import com.automationstoretest.pageobjects.IndexPage;
import com.automationstoretest.utility.DataProviders;

public class EndToEndTest extends Base {

  @Test(dataProvider = "product", dataProviderClass = DataProviders.class,
        groups = "Regression")
  public void endToEndTest(String productName, String quantity, String size) {
    logger.info("start of endToEndTest");
    Assert.assertEquals(new IndexPage()
      .searchProduct(productName)
      .clickOnProduct()
      .enterQuantity(quantity)
      .selectSize(size)
      .clickOnAddToCart()
      .clickOnProcedeToCheckout()
      .procedeToCheckout()
      .loginWhenCheckout(properties.getProperty("username"), properties.getProperty("password"))
      .procedeToCheckout()
      .checkTheServiceTerms()
      .procedeToCheckout()
      .clickOnPayByBankWire()
      .clickOnConfirmOrder()
      .validateCompletionMessage(), "Your order on My Store is complete."); 
    logger.info("end of endToEndTest");
  } 
}
