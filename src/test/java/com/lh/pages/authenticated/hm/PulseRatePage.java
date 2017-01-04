package com.lh.pages.authenticated.hm;

import static com.lh.helper.DriverFactory.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Reporter;

import com.lh.pages.authenticated.MainPage;
import com.lh.testConfig.TestProperty;
import com.lh.utils.SeleniumUtil;

public class PulseRatePage extends MainPage {

	private static Logger logger = LogManager.getLogger(PulseRatePage.class);
	private By pulseRatelnk = By.xpath("//a[text()='Pulse Rate']");
	private By viewHistory = By.xpath("//button[@id='ViewHistoryButton']");
	private By addEntryButton = By.xpath("//button[@id='AddEntryButton']");
	private By dateAddress = By
			.xpath("//input[@id='HealthEntryDateTextBox' and @type='text']");
	private By pulseRatetxtbox = By.xpath("//input[@id='ctl00_modalBodyContent_HealthEntryValueRepeater_ctl00_HealthEntryValueTextBox_TheNumericTextBox']");
	private By submitBtn = By.xpath("//input[@id='submitButton']");
	private By confirmButton = By.xpath("//input[@id='OKButton']");
	private String pulseRateValue ="//span[text()='%s']/ancestor::tr/following-sibling::tr[1]/td[2]";
	private String editPulseRate = "//span[text()='%s']/parent::td/following-sibling::td//a[text()='Edit']";
	private String deletePulseRateLnk= "//span[text()='%s']/parent::td/following-sibling::td//a[text()='Delete']";
	private String deleteLink			=		"//span[text()='%s']/parent::td/following-sibling::td//a[text()='Delete']";
	private String yesButton			= 		"YesButton";
	public PulseRatePage() {
	
		super();
	
		Reporter.log("Initializing the HealthAssessmentPage Object...");
		
		// Check that we're on the right page.
		if (!(driver.getTitle().equalsIgnoreCase(TestProperty.CLIENT_PORTAL_TITLE))) {
			logger.error("This is not the Health Assessment page");
			throw new IllegalStateException(
					"This is not the HealthAssessmentPage page");
		}
		Reporter.log("Initialized the HealthAssessmentPage page object");
	}

	/**
	 * This method verifies whether the data variables for blood pressure are added or not.
	 * @param date
	 * @param pulseRateTxt
	 * @return true if blood pressure variables added successfully and returns false if not added.
	 * @throws Throwable
	 */
	public boolean addEntry_PulseRate(String date, String pulseRateTxt){
		
		boolean flag = false;
		SeleniumUtil.click(pulseRatelnk, driver);
		Reporter.log("Clicked on the pulse rate link");
		Reporter.log("Click on the view history button");
		SeleniumUtil.click(viewHistory, driver);
		SeleniumUtil.click(addEntryButton, driver);
		Reporter.log("Clicked on the Add Entry button");
		SeleniumUtil.click(dateAddress, driver);
		Reporter.log("Click on the calendar date text box");
		SeleniumUtil.sendKeysToWebElement(dateAddress, date, driver);
		Reporter.log("The Entered date in calendar text box is" + date);
		SeleniumUtil.sendKeysToWebElement(pulseRatetxtbox, pulseRateTxt, driver);
		Reporter.log("The Entered date in fasting text box is" + pulseRateTxt);
		SeleniumUtil.click(submitBtn, driver);
		Reporter.log("Clicked on the submit button ");
		SeleniumUtil.sleep(2);
		SeleniumUtil.click(confirmButton, driver);
		Reporter.log("Clicked on the Okay button ");
		String pulseValue = SeleniumUtil.getTextfromWebElement(SeleniumUtil.dynamicXpath(pulseRateValue,date),driver).trim();
		String pulse[] = pulseValue.split(" ");
		String pulseVal = pulse[0];
		Reporter.log("Asserting the pulse rate value with the value from external file");
		if(pulseVal.equalsIgnoreCase(pulseRateTxt))
			flag=true;
		
		return flag;
		}
	/**
	 * Verifies the edited blood pressure variables
	 * @param date
	 * @param newPulseTxt
	 * @return true if edited data is displayed successfully 
	 */
	public boolean editEntry_PulseRate(String date,String newPulseTxt){
		
		boolean flag = false;
		SeleniumUtil.click(SeleniumUtil.dynamicXpath(editPulseRate, date), driver);
		Reporter.log("Clicked on edit pulseRate");
		SeleniumUtil.click(pulseRatetxtbox, driver);
		Reporter.log("Clicked on pulseRate textBox");
		SeleniumUtil.element(pulseRatetxtbox, driver).clear();
		SeleniumUtil.sendKeysToWebElement(pulseRatetxtbox, newPulseTxt, driver);
		Reporter.log("The new pulse rate value is "+newPulseTxt);
		SeleniumUtil.click(submitBtn, driver);
		Reporter.log("Clicked on the submit button ");
		SeleniumUtil.sleep(2);
		SeleniumUtil.click(confirmButton, driver);
		Reporter.log("Clicked on the Okay button ");
		String editPulseRate = SeleniumUtil.getTextfromWebElement(SeleniumUtil.dynamicXpath(pulseRateValue,date),driver).trim();
		String pulseRt[] = editPulseRate.split(" ");
		String editedPulseVal = pulseRt[0];
		Reporter.log("Asserting the edited pulse rate value with the value from external file");
		if(editedPulseVal.equalsIgnoreCase(newPulseTxt))
			flag = true;
		return flag;
	}

	/**
	 * This method verifies whether the variables are deleted 
	 * @param date
	 * @return true if variables info deleted successfully
	 * @throws InterruptedException
	 */
public boolean deleteEntry_PulseRate(String date) throws InterruptedException{
	
	boolean flag = false;
	SeleniumUtil.click(SeleniumUtil.dynamicXpath(deletePulseRateLnk, date), driver);
	Reporter.log("Clicked on pulse rate delete link");
	SeleniumUtil.sleep(3);
	SeleniumUtil.clickbyJSID(yesButton, driver);
	SeleniumUtil.sleep(2);
	SeleniumUtil.click(confirmButton, driver);
	Reporter.log("Clicked on confirm button");
	if(!SeleniumUtil.isElementDisplayed(SeleniumUtil.dynamicXpath(deleteLink, date), driver))
	flag =true;
	
	return flag;
}


}
