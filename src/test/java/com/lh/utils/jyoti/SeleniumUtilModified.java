package com.lh.utils.jyoti;

import java.util.ArrayList;
import java.util.Collections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.lh.testConfig.TestProperty;
import com.lh.utils.SeleniumUtil;
import com.thoughtworks.selenium.Selenium;

public class SeleniumUtilModified {
	private static Logger logger = LogManager.getLogger(SeleniumUtil.class);
	
	public static void switchToDefaultContent(WebDriver driver){
		driver.switchTo().defaultContent();
	}
	public static void scrollToElement(By elementToken, WebDriver driver){
		WebElement element = driver.findElement(elementToken);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static void click(By xpath,WebDriver driver){
		try{
			driver.findElement(xpath).click();
			logger.debug("Sucessfully clicked on element");
		}catch(Exception e){	
			logger.debug("Failed to click on element");
			Reporter.log("Failed to click on element");

			e.getStackTrace();	
			
		}	
	}
	
	public static void hover(By elementToken, WebDriver driver){
		WebElement element = driver.findElement(elementToken);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Actions hoverClick = new Actions(driver);
		hoverClick.moveToElement(driver.findElement(elementToken)).click().build().perform();
	}
	public static WebElement element(By elementToken, WebDriver driver){
		return driver.findElement(elementToken);
	}
	
	public static void sleep(int seconds){
		try{
			Thread.sleep(seconds*1000);
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * This method is used for wait for element for ELEMENT_POLL_TIME defined in
	 * TestProperty file
	 * 
	 * @param elementLocator
	 * @param driver
	 */
	public static void waitForElementToBePresent(By elementLocator, WebDriver driver) {
		WebElement myDynamicElement = (new WebDriverWait(driver, TestProperty.ELEMENT_POLL_TIME))
				.until(ExpectedConditions.presenceOfElementLocated(elementLocator));
		if (myDynamicElement.isDisplayed()) {
			logger.info(": " + elementLocator.toString());
		} else {
			logger.error("\nElement NOT found: " + elementLocator.toString());
		}
	}
	public static By dynamicXpath(String xpathValue, String...substitutionValue) {
		return By.xpath(String.format(xpathValue,substitutionValue));
	}
	public static void waitForElementToBeVisible(By elementLocator, WebDriver driver) {
		try{
			WebElement myDynamicElement = (new WebDriverWait(driver, TestProperty.ELEMENT_WAIT_TIME))
					.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
			//System.out.println("exception is " +myDynamicElement);
		}
		catch(Exception e){
			logger.info("Exception occured.. "+ e.getMessage());
		}
	}
	
	public static ArrayList<Integer> generateUniqueRandomNumber(int endPoint){
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i=1; i<=endPoint; i++) {
			list.add(new Integer(i));   ///why new Integer is delcalred??
		}
	      Collections.shuffle(list);
	      return list;
	}
	public static void sendKeysToWebElement(By locator,String text,WebDriver driver){
		try{

			//driver.findElement(locator).clear();
			driver.findElement(locator).sendKeys(text);
			logger.debug("The text to send is test "+text);
		}
		catch(Exception e){
			logger.debug("Failed to type in the textbox");
			e.getStackTrace();
		}
}
	public static void switchToFrame(By elementToken, WebDriver driver){
//		sleep(4);
		driver.switchTo().frame(element(elementToken, driver));
		sleep(1);
	}
	public static void clickbyJS(String xpath,WebDriver driver){
		WebElement element = driver.findElement(By.xpath(xpath));
		element.click();
		//JavascriptExecutor executor = (JavascriptExecutor)driver;
		//executor.executeScript("arguments[0].click();", element);
	}

}