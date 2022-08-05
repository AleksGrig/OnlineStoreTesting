package com.automationstoretest.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.automationstoretest.base.Base;
import com.automationstoretest.pageobjects.IndexPage;

public class IndexPageTest extends Base {
  
  @Test(groups = "Smoke")
  public void verifyLogo() {
    logger.info("start of logoTest");
    Assert.assertTrue(new IndexPage().validateLogo());
    logger.info("end of logoTest");
  }

  @Test(groups = "Sanity")
  public void verifyTitle() {
    logger.info("start of titleTest");
    Assert.assertEquals(new IndexPage().getTitle(), "My Store");
    logger.info("end of titleTest");
  }
}
