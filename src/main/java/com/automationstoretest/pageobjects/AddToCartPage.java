package com.automationstoretest.pageobjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.automationstoretest.base.Base;

public class AddToCartPage extends Base {
  @FindBy(css = "#quantity_wanted") WebElement quantityField;
  @FindBy(css = "#group_1") WebElement sizeField;
  @FindBy(css = "button[name='Submit'] > span") WebElement addToCartBtn;
  @FindBy(css = ".layer_cart_product > h2") WebElement successMessage;
  @FindBy(css = "a[title='Proceed to checkout']") WebElement procedeToCheckoutBtn;

  public AddToCartPage() {
    PageFactory.initElements(getDriver(), this);
  }

  public AddToCartPage enterQuantity(String quantity) {
    quantityField.sendKeys(quantity);
    return this;
  }

  public AddToCartPage selectSize(String size) {
    Select select = new Select(sizeField);
		select.selectByVisibleText(size);
    return this;
  }

  public AddToCartPage clickOnAddToCart() {
    addToCartBtn.click();
    return this;
  }

  public boolean validateAddToCart() {
    fluentWait(getDriver(), successMessage, 10);
    return successMessage.isDisplayed();
  }

  public OrderPage clickOnProcedeToCheckout() {
    fluentWait(getDriver(), successMessage, 10);
    JavascriptExecutor executor = (JavascriptExecutor) getDriver();
		executor.executeScript("arguments[0].click();", procedeToCheckoutBtn);
    return new OrderPage();
  }
  
}