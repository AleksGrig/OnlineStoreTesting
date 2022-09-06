package com.automationstoretest.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
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