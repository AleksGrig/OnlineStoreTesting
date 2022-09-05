package com.automationstoretest.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.automationstoretest.utility.ExtentManager;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
  public static final String userDir = System.getProperty("user.dir");
  protected static final Properties properties = new Properties();
  protected static Logger logger;
  private static final ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();

  static {
    try {
      String configPath = """
        %s/configuration/config.properties""".formatted(userDir);
      properties.load(new FileInputStream(configPath));
    } catch(FileNotFoundException e) { e.printStackTrace(); }
      catch(IOException e) { e.printStackTrace(); } 

    System.setProperty("log4j.configurationFile", "configuration\\log4j2.xml");
    logger = LogManager.getLogger();
  }

  public static WebDriver getDriver() {
    return driver.get();
  }
  
  @BeforeSuite(groups = {"Smoke", "Sanity", "Regression"})
  public void beforeSuite() throws IOException {
    ExtentManager.setExtent("Samsung-laptop", "OnlineStoreTest", "Wi9htWalker");
  }
  
  @AfterSuite(groups = {"Smoke", "Sanity", "Regression"})
  public void afterSuite() {
    ExtentManager.endReport();
  }

  @Parameters("browser")
  @BeforeMethod(groups = {"Smoke", "Sanity", "Regression"})
  public void bethoreMethod(String browser) {
    launchApp(browser);
  }
  
  @AfterMethod(groups = {"Smoke", "Sanity", "Regression"})
  public void afterMethod() {
    driver.get().quit();
  }

  public static void fluentWait(WebDriver driver, int timeOut, ExpectedCondition<WebElement> condition) {
    Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
      .withTimeout(Duration.ofSeconds(20))
      .pollingEvery(Duration.ofSeconds(2))
      .ignoring(Exception.class);
    wait.until(condition);
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

  public void selectByValue(WebElement element, String value) {
    Select select = new Select(element);
    select.selectByValue(value);
  }

  public void selectByVisibleText(WebElement element, String text) {
    Select select = new Select(element);
    select.selectByVisibleText(text);
  }

  public String getRandomEmail(String email) {
    int random = (int) (Math.random() * 1000000000);
    String[] emailSplit = email.split("@");
    email = emailSplit[0] + random + "@" + emailSplit[1];
    return email;
  }
  
  private void launchApp(String browser) {
    switch (browser.toLowerCase()) {
      case "firefox": 
        WebDriverManager.firefoxdriver().setup();
        driver.set(new FirefoxDriver());
        break;
      case "edge":
        WebDriverManager.edgedriver().setup();
        driver.set(new EdgeDriver());
        break;
      default:
        WebDriverManager.chromedriver().setup();
        driver.set(new ChromeDriver());
        break;
    };
    
    driver.get().manage().window().maximize();
    driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    driver.get().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
    driver.get().get(properties.getProperty("url"));
  }
}