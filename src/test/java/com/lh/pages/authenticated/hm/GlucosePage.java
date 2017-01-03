package com.lh.pages.authenticated.hm;

import static com.lh.helper.DriverFactory.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Reporter;

import com.lh.pages.authenticated.MainPage;
import com.lh.testConfig.TestProperty;
import com.lh.utils.SeleniumUtil;

public class GlucosePage extends MainPage {

	private static Logger logger = LogManager.getLogger(BloodPressurePage.class);
	private By glucoseLink 					= By.xpath("//a[text()='Glucose']");
	private By viewHistory 					= By.xpath("//button[@id='ViewHistoryButton']");
	private By addEntryButton 				= By.xpath("//button[@id='AddEntryButton']");
	private By dateAddress 					= By.xpath("//input[@id='HealthEntryDateTextBox' and @type='text']");
	private By fastingtxtBox 				= By.xpath("//input[@id='ctl00_modalBodyContent_HealthEntryValueRepeater_ctl00_HealthEntryValueTextBox_TheNumericTextBox']");
	private By nonfastingtxtBox				= By.xpath("//input[@id='ctl00_modalBodyContent_HealthEntryValueRepeater_ctl01_HealthEntryValueTextBox_TheNumericTextBox']");
	private By submitBtn 					= By.xpath("//input[@id='submitButton']");
	private String fastingValue 			= "(//span[text()='%s']/ancestor::tr/following-sibling::tr[1]/td[2])[1]";
	private String nonfastingValue 			= "(//span[text()='%s']/ancestor::tr/following-sibling::tr[1]/td[2])[2]";
	private By confirmButton 				= By.id("OKButton");
	private String editFasting 				= "(//span[text()='%s']/parent::td/following-sibling::td//a[text()='Edit'])[1]";
	private String editNonFasting 			= "(//span[text()='%s']/parent::td/following-sibling::td//a[text()='Edit'])[2]";
	private String deleteFasting 			= "(//span[text()='%s']/parent::td/following-sibling::td//a[text()='Delete'])[1]";
	private String deleteNonFasting 		= "(//span[text()='%s']/parent::td/following-sibling::td//a[text()='Delete'])[1]";
	private String deleteLink				= "//span[text()='%s']/parent::td/following-sibling::td//a[text()='Delete']";
	private String yesButton				= "YesButton";
	private By glucoseHeading				= By.id("ctl00_ContentPlaceHolder1_healthHistoryGroup");
	
	public GlucosePage() {
		super();
		Reporter.log("Initializing the HealthAssessmentPage Object...");
		
		// Check that we're on the right page.
		if (!(driver.getTitle()
				.equalsIgnoreCase(TestProperty.CLIENT_PORTAL_TITLE))) {
			logger.error("This is not the Health Assessment page");
			throw new IllegalStateException(
					"This is not the HealthAssessmentPage page");
		}
		Reporter.log("Initialized the HealthAssessmentPage page object");

	}

	/**
	 * This method verifies whether the data variables for Glucose are added or not.
	 * @param date
	 * @param fastingTxt
	 * @param nonFastingTxt
	 * @return true if blood pressure variables added successfully and returns false if not added.
	 * @throws Throwable
	 */
	public boolean addEntry_Glucose(String date, String fastingTxt,
			String nonFastingTxt) {

		boolean flag = false;
		Reporter.log("Click on the glucose Link");
		SeleniumUtil.click(glucoseLink, driver);
		Reporter.log("Click on the view history button");
		SeleniumUtil.click(viewHistory, driver);
		SeleniumUtil.click(addEntryButton, driver);
		Reporter.log("Clicked on the Add Entry button");
		SeleniumUtil.click(dateAddress, driver);
		Reporter.log("Click on the calendar date text box");
		SeleniumUtil.sendKeysToWebElement(dateAddress, date, driver);
		Reporter.log("The Entered date in calendar text box is" + date);

		SeleniumUtil.sendKeysToWebElement(fastingtxtBox, fastingTxt, driver);
		Reporter.log("The Entered date in fasting text box is" + fastingTxt);

		SeleniumUtil.sendKeysToWebElement(nonfastingtxtBox, nonFastingTxt,
				driver);
		Reporter.log("The Entered date in nonfasting text box is"
				+ nonFastingTxt);
		SeleniumUtil.click(submitBtn, driver);
		Reporter.log("Clicked on the submit button ");
		SeleniumUtil.sleep(2);
		SeleniumUtil.click(confirmButton, driver);
		Reporter.log("Clicked on the Okay button ");
		String fastVal = SeleniumUtil.getTextfromWebElement(SeleniumUtil.dynamicXpath(fastingValue,date),
				driver).trim();
		String nonFastVal = SeleniumUtil.getTextfromWebElement(SeleniumUtil.dynamicXpath(nonfastingValue,date),
				driver).trim();
		String fast[] = fastVal.split(" ");
		String fastValue = fast[0];
		String nonfast[] = nonFastVal.split(" ");
		String nonfastValue = nonfast[0];
		if (fastValue.equalsIgnoreCase(fastingTxt)
				&& nonfastValue.equalsIgnoreCase(nonFastingTxt))
			flag = true;

		return flag;
	}
	/**
	 * Verifies the edited Glucose variables
	 * @param date
	 * @param newfastVal
	 * @param newnonFastVal
	 * @return true if edited data is displayed successfully 
	 */
	public boolean editEntry_Glucose(String date,String newfastVal, String newnonFastVal) {
		boolean flag = false;
		SeleniumUtil.click(glucoseHeading, driver);
		SeleniumUtil.click(SeleniumUtil.dynamicXpath(editFasting, date), driver);
		Reporter.log("Clicked on edit Fasting");
		SeleniumUtil.click(fastingtxtBox, driver);
		Reporter.log("Clicked on Fasting textBox");
		SeleniumUtil.element(fastingtxtBox, driver).clear();
		SeleniumUtil.sendKeysToWebElement(fastingtxtBox, newfastVal, driver);
		Reporter.log("The new fasting value is "+newfastVal);
		SeleniumUtil.click(submitBtn, driver);
		Reporter.log("Clicked on the submit button ");
		SeleniumUtil.sleep(2);
		SeleniumUtil.click(confirmButton, driver);
		Reporter.log("Clicked on the Okay button ");
		String editfastVal = SeleniumUtil.getTextfromWebElement(SeleniumUtil.dynamicXpath(fastingValue,date),
				driver).trim();
		String fast[] = editfastVal.split(" ");
		String editfastValue = fast[0];
		SeleniumUtil.click(SeleniumUtil.dynamicXpath(editNonFasting, date), driver);
		SeleniumUtil.click(fastingtxtBox, driver);
		SeleniumUtil.element(fastingtxtBox, driver).clear();
		SeleniumUtil.sendKeysToWebElement(fastingtxtBox, newnonFastVal, driver);
		Reporter.log("The new fasting value is "+newnonFastVal);
		SeleniumUtil.click(submitBtn, driver);
		Reporter.log("Clicked on the submit button ");
		SeleniumUtil.sleep(2);
		SeleniumUtil.click(confirmButton, driver);
		Reporter.log("Clicked on the Okay button ");
		String editnonfastVal = SeleniumUtil.getTextfromWebElement(SeleniumUtil.dynamicXpath(nonfastingValue,date),
				driver).trim();
		String nonfast[] = editnonfastVal.split(" ");
		String editnonfastValue = nonfast[0];
		
		if(editfastValue.equalsIgnoreCase(newfastVal) && editnonfastValue.equalsIgnoreCase(newnonFastVal))
			flag = true;
		
		return flag;
	}

	/**
	 * This method verifies whether the Glucose variables are deleted 
	 * @param date
	 * @return true if variables info deleted successfully
	 * @throws InterruptedException
	 */
	public boolean deleteEntry_Glucose(String date) throws InterruptedException{
		boolean flag = false;
		SeleniumUtil.click(SeleniumUtil.dynamicXpath(deleteFasting, date), driver);
		Reporter.log("Clicked on delete link-fasting");
		SeleniumUtil.sleep(3);
		SeleniumUtil.clickbyJSID(yesButton, driver);
		SeleniumUtil.sleep(3);
		SeleniumUtil.click(confirmButton, driver);
		SeleniumUtil.sleep(2);
		SeleniumUtil.click(SeleniumUtil.dynamicXpath(deleteNonFasting, date), driver);
		Reporter.log("Clicked on delete link-nonfasting");
		SeleniumUtil.sleep(6);
		SeleniumUtil.clickbyJSID(yesButton, driver);
		SeleniumUtil.sleep(2);
		SeleniumUtil.click(confirmButton, driver);
		if(!SeleniumUtil.isElementDisplayed(SeleniumUtil.dynamicXpath(deleteLink, date), driver))
		flag =true;
		
		return flag;
	}



}

