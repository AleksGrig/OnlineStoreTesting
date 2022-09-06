package com.automationstoretest.base;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;

public class Action extends Base {

  public static void fluentWait(WebDriver driver, int timeOut, ExpectedCondition<WebElement> condition) {
    new FluentWait<WebDriver>(driver)
      .withTimeout(Duration.ofSeconds(20))
      .pollingEvery(Duration.ofSeconds(2))
      .ignoring(Exception.class)
      .until(condition);
  }

  public static String screenshot(WebDriver driver, String filename) {
		String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File source = screenshot.getScreenshotAs(OutputType.FILE);
    logger.info(() -> "launch: " + System.getProperty("launch")); // Testing jenkins parameter
    boolean jenkins = System.getProperty("launch") == null ? false : 
        System.getProperty("launch").equals("jenkins") ? true : false;
		logger.info(() -> "jenkins: " + jenkins);
    String destination = jenkins ? """
        http://localhost:8080/job/OnlineStoreTesting/ws/screenshots/%s_%s.png
        """.formatted(filename, date) : """
        %s/screenshots/%s_%s.png""".formatted(userDir, filename, date);
    logger.info(() -> "destination: " + destination);

		try {
			FileUtils.copyFile(source, new File(destination));
		} catch(Exception e) { e.getMessage(); }

		return destination;
	}

  public static void selectByValue(WebElement element, String value) {
    new Select(element).selectByValue(value);
  }

  public static void selectByVisibleText(WebElement element, String text) {
    new Select(element).selectByVisibleText(text);
  }

  public static String getRandomEmail(String email) {
    int random = (int) (Math.random() * 1000000000);
    String[] emailSplit = email.split("@");
    email = emailSplit[0] + random + "@" + emailSplit[1];
    return email;
  }
}
