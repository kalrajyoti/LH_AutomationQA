package com.lh.helper;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.testng.Reporter;

import com.lh.testConfig.TestProperty;

/**
 * <h2>This class Listens for the WebDriver Events</h2>
 * <p>
 * 
 * @author amita.arya
 * @version 1.0
 * @since 2014-11-17
 */
public class HelperWebDriverEventListener extends
		AbstractWebDriverEventListener {
	/**
	 * Logger to log the web driver event listener log messages
	 */
	private static Logger logger = LogManager
			.getLogger(HelperWebDriverEventListener.class);

	/**
	 * Called after WebDriver.findElement(...), or WebDriver.findElements(...),
	 * or WebElement.findElement(...), or WebElement.findElements(...).
	 * @param element - will be null, if a find method of WebDriver is called.
	 */
	public void afterFindBy(By by, WebElement element, WebDriver driver) {
		logger.debug("Successfully found the element: " + by.toString());
	}

	// Static wait
	/*
	 * public void beforeFindBy(By by, WebElement element, WebDriver driver){
	 * try { Thread.sleep(TestProperty.ELEMENT_WAIT_TIME); } catch
	 * (InterruptedException e) { e.printStackTrace(); }
	 * Reporter.log("Looking for the element: " + by.toString()); }
	 */

	// Dynamic wait
	/**
	 * Called before WebDriver.findElement(...), or WebDriver.findElements(...),
	 * or WebElement.findElement(...), or WebElement.findElements(...).
	 * 
	 * @param element
	 *            - will be null, if a find method of WebDriver is called.
	 */
	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
		Reporter.log("Looking for the element: " + by.toString());
		/*WebElement myDynamicElement = (new WebDriverWait(driver,TestProperty.ELEMENT_POLL_TIME)).until(ExpectedConditions
				.presenceOfElementLocated(by));
		if (myDynamicElement.isDisplayed()){
			Reporter.log("Element Found: " + by.toString());
		} else {
			logger.error("\nElement NOT found: " + by.toString());
			// throw new ElementNotFoundException(by.toString(),"Element Not found", "Element Not found");
		}*/

	}

	/**
	 * Called after WebDriver.findElement(...), or WebDriver.findElements(...),
	 * or WebElement.findElement(...), or WebElement.findElements(...).
	 * 
	 * @param element
	 *            - will be null, if a find method of WebDriver is called.
	 */
	public void beforeChangeValueOf(WebElement element, WebDriver driver) {
		Reporter.log("Changing value for the element: " + element.toString());
		try {
			Thread.sleep(TestProperty.ELEMENT_WAIT_TIME);
		} catch (InterruptedException e) {
			logger.error("Unable to change value of the element - " + element.toString(), e);;
		}
	}

	/**
	 * Called after WebElement.clear(), WebElement.sendKeys(...), or
	 * WebElement.toggle(). Not called, if an exception is thrown.
	 */
	public void afterChangeValueOf(WebElement element, WebDriver driver) {
		Reporter.log("Changed value for the element: " + element.toString());
	}

	/**
	 * Called before WebElement.click().
	 */
	public void beforeClickOn(WebElement element, WebDriver driver) {
		Reporter.log("Clicking on element: " + element.toString());
		try {
			Thread.sleep(TestProperty.ELEMENT_WAIT_TIME);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Called before WebElement.click().
	 */
	public void afterClickOn(WebElement element, WebDriver driver) {
		Reporter.log("Clicked on element: " + element.toString());
	}
}