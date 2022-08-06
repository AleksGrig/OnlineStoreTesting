package com.automationstoretest.utility;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.automationstoretest.base.Base;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class ExtentListener extends ExtentManager implements ITestListener {
  private ThreadLocal<ExtentTest> test = new ThreadLocal<>();

  public void onTestStart(ITestResult result) {
		test.set(extent.createTest(result.getName()));
	}

	public void onTestSuccess(ITestResult result) {
		test.get().log(Status.PASS, "Pass Test case is: " + result.getName());
	}

	public void onTestFailure(ITestResult result) {
		test.get().log(Status.FAIL,
				MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
		test.get().log(Status.FAIL,
				MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
		String imgPath = Base.screenshot(Base.getDriver(), result.getName());
		test.get().fail("ScreenShot is Attached", MediaEntityBuilder.createScreenCaptureFromPath(imgPath).build());
	}

	public void onTestSkipped(ITestResult result) {
		test.get().log(Status.SKIP, "Skipped Test case is: " + result.getName());
	}
}
