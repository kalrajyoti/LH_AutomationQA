package com.lh.pages.authenticated.hm;

import static com.lh.helper.DriverFactory.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Reporter;

import com.lh.pages.authenticated.MainPage;
import com.lh.testConfig.TestProperty;
import com.lh.utils.SeleniumUtil;

public class BodyCompositionPage extends MainPage{

	
	private static Logger logger = LogManager.getLogger(BloodPressurePage.class);
	
	private By bodyCompLink      		= By.xpath("//a[text()='Body Composition']"); 
	private By addbodyCompButon			= By.xpath("//button[text()='Add Entry']");
	private By dateAddress			  	= By.xpath("//input[@id='HealthEntryDateTextBox' and @type='text']");
	private By waisttxtbox				= By.xpath("//input[@id='ctl00_modalBodyContent_HealthEntryValueRepeater_ctl00_HealthEntryValueTextBox_TheNumericTextBox']");	
	private By bodyfattxtbox			= By.xpath("//input[@id='ctl00_modalBodyContent_HealthEntryValueRepeater_ctl01_HealthEntryValueTextBox_TheNumericTextBox']");
	private By notestxtbox				= By.xpath("//textarea[@id='NotesTextBox']");
	private By submitBtn				= By.xpath("//input[@id='submitButton']");		
	private By confirmButton			= By.xpath("//input[@id='OKButton']");
	private By viewHistory			  	= By.xpath("//button[@id='ViewHistoryButton']");
	private String bodyFatVerifyPath	= "//span[text()='%s']//ancestor::tr/following-sibling::tr[1]/td[2]";
	private String waistVerifyPath		= "//span[text()='%s']//ancestor::tr/following-sibling::tr[2]/td[2]";
	private String editLink				= "//span[text()='%s']/parent::td/following-sibling::td//a[text()='Edit']";
	private String deleteLink			= "//span[text()='%s']/parent::td/following-sibling::td//a[text()='Delete']";
	private String yesButton			= 		"YesButton";
	
	public BodyCompositionPage() {
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
	 * This method verifies whether the data variable for Body Composition are added or not.
	 * @param date
	 * @param waistTxt
	 * @param bodyFatTxt
	 * @param notes
	 * @return true if Body Composition variables added successfully and returns false if not added.
	 * @throws Throwable
	 */
	public boolean addEntry_BodyComp(String date, String waistTxt, String bodyFatTxt, String notes){
		
		boolean flag = false;
		SeleniumUtil.click(bodyCompLink, driver);
		Reporter.log("Clicked on body composition link");
		SeleniumUtil.click(viewHistory, driver);
		Reporter.log("Clicked on body composition link");
		SeleniumUtil.click(addbodyCompButon, driver);
		Reporter.log("Clicked on add entry for body composition ");
		SeleniumUtil.click(dateAddress, driver);
		Reporter.log("Clicked on dateAddress textbox");
		SeleniumUtil.sendKeysToWebElement(dateAddress, date, driver);
		Reporter.log("The input value to date field is"+date);
		SeleniumUtil.sendKeysToWebElement(waisttxtbox, waistTxt, driver);
		Reporter.log("The input value to waisttxtbox is"+waistTxt);
		SeleniumUtil.sendKeysToWebElement(bodyfattxtbox, bodyFatTxt, driver);
		Reporter.log("The input value to bodyfattxtbox is"+bodyFatTxt);
		SeleniumUtil.sendKeysToWebElement(notestxtbox, notes, driver);
		Reporter.log("The input value to notestxtbox is"+notes);
		SeleniumUtil.click(submitBtn, driver);
		Reporter.log("Clicked on body composition link");
		SeleniumUtil.sleep(2);
		SeleniumUtil.click(confirmButton, driver);
		Reporter.log("Clicked on body composition link");
		String bodyFatValue=SeleniumUtil.getTextfromWebElement(SeleniumUtil.dynamicXpath(bodyFatVerifyPath, date), driver).trim();
		Reporter.log("The  value from body fat field is"+bodyFatValue);
		String waistValue=SeleniumUtil.getTextfromWebElement(SeleniumUtil.dynamicXpath(waistVerifyPath, date), driver).trim();
		Reporter.log("The value from waist field is"+waistValue);
		String bodyFat[]=bodyFatValue.split(" ");
		String bodyFatCompValue=bodyFat[0];
		String waist[]=waistValue.split(" ");
		String waistCompValue=waist[0];
		Reporter.log("Asserting the Body Fat and Waist value with the value from external file");
		if(bodyFatCompValue.equalsIgnoreCase(bodyFatTxt) && waistCompValue.equalsIgnoreCase(waistTxt))
			flag=true;
		
		return flag;
	}
	/**
	 * Verifies the edited Body Composition variables
	 * @param date
	 * @param newWaistSize
	 * @param newBodyFat
	 * @return true if Body Composition edited data is displayed successfully 
	 */
	public boolean editEntry_BodyComp(String date, String newWaistSize, String newBodyFat){
		
		boolean flag = false;
		
		SeleniumUtil.click(SeleniumUtil.dynamicXpath(editLink, date), driver);
		Reporter.log("Clicked on edit link");
		SeleniumUtil.click(waisttxtbox, driver);
		Reporter.log("Clicked on waist text box ");
		SeleniumUtil.element(waisttxtbox, driver).clear();
		SeleniumUtil.sendKeysToWebElement(waisttxtbox, newWaistSize, driver);
		Reporter.log("The input value to notestxtbox is"+newWaistSize);
		SeleniumUtil.element(bodyfattxtbox, driver).clear();
		SeleniumUtil.sendKeysToWebElement(bodyfattxtbox, newBodyFat, driver);
		Reporter.log("The input value to notestxtbox is"+newBodyFat);
		SeleniumUtil.click(submitBtn, driver);
		Reporter.log("Clicked on body composition link");
		SeleniumUtil.sleep(2);
		SeleniumUtil.click(confirmButton, driver);
		Reporter.log("Clicked on body composition link");
		String newWaistValue=SeleniumUtil.getTextfromWebElement(SeleniumUtil.dynamicXpath(waistVerifyPath, date), driver).trim();
		String newBodyFatValue=SeleniumUtil.getTextfromWebElement(SeleniumUtil.dynamicXpath(bodyFatVerifyPath, date), driver).trim();
		String bodyFat[]=newBodyFatValue.split(" ");
		String bodyFatCompValue=bodyFat[0];
		String waist[]=newWaistValue.split(" ");
		String waistCompValue=waist[0];
		Reporter.log("Asserting the edited Body Fat and Waist value with the value from external file");
		if(bodyFatCompValue.equalsIgnoreCase(newBodyFat) && waistCompValue.equalsIgnoreCase(newWaistSize))
			flag=true;
		
		return flag;
	}

	/**
	 * This method verifies whether the Body Composition variables are deleted 
	 * @param date
	 * @return true if Body Composition variables info deleted successfully
	 * @throws InterruptedException
	 */
	public boolean deleteEntry_bodyComp(String date) throws InterruptedException{
		
		boolean flag=false;
		
		SeleniumUtil.click(SeleniumUtil.dynamicXpath(deleteLink, date), driver);
		Reporter.log("Clicked on delete link");
		SeleniumUtil.sleep(5);
		SeleniumUtil.clickbyJSID(yesButton, driver);
		SeleniumUtil.sleep(5);
		SeleniumUtil.click(confirmButton, driver);
		Reporter.log("Clicked on confirm button");
		if(!SeleniumUtil.isElementDisplayed(SeleniumUtil.dynamicXpath(deleteLink, date), driver))
			flag=true;
		
		return flag;
		
		
	}
}
