package com.example.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class PasspodOrder {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "https://passpod.com/id/wifi-rental";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testPasspodOrder() throws Exception {
    driver.get(baseUrl + "/id/wifi-rental");
    assertEquals("Wifi Rental - Passpod", driver.getTitle());
    new Select(driver.findElement(By.id("country"))).selectByVisibleText("Wifi South East Asia 1 GB/day");
    new Select(driver.findElement(By.id("qty"))).selectByVisibleText("2");
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if ("07/28/2018".equals(driver.findElement(By.cssSelector("div.react-datepicker__input-container > input.form-control")).getText())) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }

    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if ("07/29/2018".equals(driver.findElement(By.cssSelector("div.form-group.end-date-picker > div > div.react-datepicker-wrapper > div.react-datepicker__input-container > input.form-control")).getText())) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }

    new Select(driver.findElement(By.xpath("(//select[@id='country'])[2]"))).selectByVisibleText("BCA");
    driver.findElement(By.id("acnNumber")).clear();
    driver.findElement(By.id("acnNumber")).sendKeys("2348 9231");
    driver.findElement(By.id("self")).click();
    driver.findElement(By.id("selfTwo")).click();
    driver.findElement(By.cssSelector("div.payment-wrap > button.btn.btn-orange")).click();
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
