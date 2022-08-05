package com.automationstoretest.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.automationstoretest.base.Base;
import com.automationstoretest.pageobjects.IndexPage;
import com.automationstoretest.utility.DataProviders;

public class SearchResultPageTest extends Base {

  @Test(dataProvider = "search", dataProviderClass = DataProviders.class,
        groups = "Sanity")
  public void productAvailabilityTest(String productName) {
    logger.info("start of productAvailabilityTest");
    Assert.assertTrue(new IndexPage()
      .searchProduct(productName)
      .isProductAvailable());
    logger.info("end of productAvailabilityTest");
  }
}
