package com.automationstoretest.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.automationstoretest.base.Base;
import com.automationstoretest.pageobjects.IndexPage;
import com.automationstoretest.pageobjects.OrderPage;
import com.automationstoretest.utility.DataProviders;

public class OrderPageTest extends Base {

  @Test(dataProvider = "product", dataProviderClass = DataProviders.class,
        groups = "Regression")
  public void totalPriceTest(String product, String quantity, String size) {
    logger.info("start of totalPriceTest");
    Assert.assertEquals(new IndexPage()
      .searchProduct(product)
      .clickOnProduct()
      .enterQuantity(quantity)
      .selectSize(size)
      .clickOnAddToCart()
      .clickOnProcedeToCheckout()
      .getTotalPrice(), new OrderPage().getUnitPrice() * 2 + 2);
    logger.info("end of totalPriceTest");
  }
}
