package com.automationstoretest.utility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer{
  private int tryCount = 0;
  private int tryMax = 0;

  @Override
  public boolean retry(ITestResult result) {
    return tryCount++ < tryMax ? true : false;
  }
  
}
