package com.lh.pages.authenticated.hm;

import static com.lh.helper.DriverFactory.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Reporter;

import com.lh.pages.authenticated.MainPage;
import com.lh.testConfig.TestProperty;
import com.lh.utils.SeleniumUtil;

public class CholesterolPage extends MainPage {

	private static Logger logger = LogManager.getLogger(CholesterolPage.class);
	
	private By cholesterolLnk = By
			.xpath("//a[@id='ctl00_ContentPlaceHolder1_TheNavigationButtonList_NavigationButtonRepeater_ctl03_NavigationLinkButton']");
	private By addEntryButton = By.xpath("//button[@id='AddEntryButton']");
	private By dateAddress = By
			.xpath("//input[@id='HealthEntryDateTextBox' and @type='text']");
	private By cholesteroltxtBox = By
			.xpath("//input[@id='ctl00_modalBodyContent_HealthEntryValueRepeater_ctl00_HealthEntryValueTextBox_TheNumericTextBox']");
	private By ldltxtBox = By
			.xpath("//input[@id='ctl00_modalBodyContent_HealthEntryValueRepeater_ctl01_HealthEntryValueTextBox_TheNumericTextBox']");
	private By editldltxtBox = By
			.xpath("//input[@id='ctl00_modalBodyContent_HealthEntryValueRepeater_ctl00_HealthEntryValueTextBox_TheNumericTextBox']");
	private By hdltxtBox = By
			.xpath("//input[@id='ctl00_modalBodyContent_HealthEntryValueRepeater_ctl02_HealthEntryValueTextBox_TheNumericTextBox']");
	private By edithdltxtBox = By
			.xpath("//input[@id='ctl00_modalBodyContent_HealthEntryValueRepeater_ctl00_HealthEntryValueTextBox_TheNumericTextBox']");
	private By tryglyceridestxtBox = By
			.xpath("//input[@id='ctl00_modalBodyContent_HealthEntryValueRepeater_ctl03_HealthEntryValueTextBox_TheNumericTextBox']");
	private By edittryglyceridestxtBox = By
			.xpath("//input[@id='ctl00_modalBodyContent_HealthEntryValueRepeater_ctl00_HealthEntryValueTextBox_TheNumericTextBox']");

	private By notesAreaTextBox = By.xpath("//textarea[@id='NotesTextBox']");
	private By submitBtn = By.xpath("//input[@id='submitButton']");
	private By confirmButton = By.xpath("//input[@id='OKButton']");
	private By viewHistory = By.xpath("//button[@id='ViewHistoryButton']");
	private String getcholValue = "(//span[text()='%s']//ancestor::tr/following-sibling::tr[1]/td[2])[1]";
	private String getLDLValue = "(//span[text()='%s']//ancestor::tr/following-sibling::tr[1]/td[2])[2]";
	private String getHDLValue = "(//span[text()='%s']//ancestor::tr/following-sibling::tr[1]/td[2])[3]";
	private String gettriglycerValue = "(//span[text()='%s']//ancestor::tr/following-sibling::tr[1]/td[2])[4]";

	private String editLink_Cholesterol = "(//span[text()='%s']/parent::td/following-sibling::td//a[text()='Edit'])[1]";
	private String editLink_ldl = "(//span[text()='%s']/parent::td/following-sibling::td//a[text()='Edit'])[2]";
	private String editLink_hdl = "(//span[text()='%s']/parent::td/following-sibling::td//a[text()='Edit'])[3]";
	private String editLink_triglyc = "(//span[text()='%s']/parent::td/following-sibling::td//a[text()='Edit'])[4]";
	private String deleteLink = "(//span[text()='%s']/parent::td/following-sibling::td//a[text()='Delete'])[1]";
	private String yesButton			= 		"YesButton";
	public CholesterolPage() {
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
	 * This method verifies whether the data variables for Cholesterol are added or not.
	 * @param date
	 * @param cholesterol
	 * @param ldl
	 * @param hdl
	 * @param tryglycer
	 * @param notes
	 * @return true if blood pressure variables added successfully and returns false if not added.
	 * @throws Throwable
	 */
	public boolean addEntry_Cholesterol(String date, String cholesterol,
			String ldl, String hdl, String tryglycer, String notes) {

		boolean flag = false;

		SeleniumUtil.click(cholesterolLnk, driver);
		Reporter.log("Clicked on cholesterol link");
		SeleniumUtil.click(viewHistory, driver);
		Reporter.log("Clicked on view history link");
		SeleniumUtil.click(addEntryButton, driver);
		Reporter.log("Clicked on add entry button");
		SeleniumUtil.click(dateAddress, driver);
		Reporter.log("Clicked on date check box");
		SeleniumUtil.sendKeysToWebElement(dateAddress, date, driver);
		Reporter.log("The input value to date address is"+date);
		SeleniumUtil.sendKeysToWebElement(cholesteroltxtBox, cholesterol,driver);
		Reporter.log("The input value to cholesterol text box is"+cholesterol);
		SeleniumUtil.sendKeysToWebElement(ldltxtBox, ldl, driver);
		Reporter.log("The input value to ldl text box is"+ldl);
		SeleniumUtil.sendKeysToWebElement(hdltxtBox, hdl, driver);
		Reporter.log("The input value to hdl text box is"+hdl);
		SeleniumUtil.sendKeysToWebElement(tryglyceridestxtBox, tryglycer,driver);
		Reporter.log("The input value to tryglyceride text box is"+tryglycer);
		SeleniumUtil.sendKeysToWebElement(notesAreaTextBox, notes, driver);
		Reporter.log("The input value to tryglyceride text box is"+notes);
		SeleniumUtil.click(submitBtn, driver);
		SeleniumUtil.sleep(2);
		SeleniumUtil.click(confirmButton, driver);
		String cholesterolValue = SeleniumUtil.getTextfromWebElement(
				SeleniumUtil.dynamicXpath(getcholValue, date), driver).trim();
		String cholest[] = cholesterolValue.split(" ");
		String cholesCompValue = cholest[0];
		String lDLValue = SeleniumUtil.getTextfromWebElement(
				SeleniumUtil.dynamicXpath(getLDLValue, date), driver).trim();
		String ldlval[] = lDLValue.split(" ");
		String ldlCompValue = ldlval[0];
		SeleniumUtil.sleep(2);
		String hDLValue = SeleniumUtil.getTextfromWebElement(
				SeleniumUtil.dynamicXpath(getHDLValue, date), driver).trim();
		String hdlval[] = hDLValue.split(" ");
		String hdlCompValue = hdlval[0];
		String triGlycerValue = SeleniumUtil.getTextfromWebElement(
				SeleniumUtil.dynamicXpath(gettriglycerValue, date), driver)
				.trim();
		String triGlycer[] = triGlycerValue.split(" ");
		String triCompValue = triGlycer[0];
		SeleniumUtil.sleep(2);
		Reporter.log("Asserting the values with the value from data sheet");
		
		if (cholesCompValue.equalsIgnoreCase(cholesterol)
				&& ldlCompValue.equalsIgnoreCase(ldl)
				&& hdlCompValue.equalsIgnoreCase(hdl)
				&& triCompValue.equalsIgnoreCase(tryglycer))
			flag = true;

		return flag;

	}
	/**
	 * Verifies the edited Cholesterol variables
	 * @param date
	 * @param newChol
	 * @param newldl
	 * @param newhdl
	 * @param newtriGly
	 * @return true if edited data for Cholesterol is displayed successfully 
	 */
	public boolean editEntry_Chloesterol(String date, String newChol,
			String newldl, String newhdl, String newtriGly) {

		boolean flag = false;

		SeleniumUtil.click(
				SeleniumUtil.dynamicXpath(editLink_Cholesterol, date), driver);
		Reporter.log("Clicked on edit link");
		SeleniumUtil.click(cholesteroltxtBox, driver);
		Reporter.log("Clicked on cholesterol text box");
		SeleniumUtil.element(cholesteroltxtBox, driver).clear();
		SeleniumUtil.sendKeysToWebElement(cholesteroltxtBox, newChol, driver);
		Reporter.log("The edited input value to cholesterol text box is"+newChol);
		SeleniumUtil.click(submitBtn, driver);
		Reporter.log("Clicked on submit button");
        SeleniumUtil.sleep(2);
		SeleniumUtil.click(confirmButton, driver);
		Reporter.log("Clicked on confirm button");
		String newcholesterolValue = SeleniumUtil.getTextfromWebElement(
				SeleniumUtil.dynamicXpath(getcholValue, date), driver).trim();
		String cholest[] = newcholesterolValue.split(" ");
		String cholesCompValue = cholest[0];

		SeleniumUtil.click(SeleniumUtil.dynamicXpath(editLink_ldl, date),
				driver);
		Reporter.log("Clicked on edit link");
		SeleniumUtil.click(editldltxtBox, driver);
		SeleniumUtil.element(editldltxtBox, driver).clear();
		SeleniumUtil.sendKeysToWebElement(editldltxtBox, newldl, driver);
		SeleniumUtil.click(submitBtn, driver);
		Reporter.log("Clicked on submit button");
		SeleniumUtil.sleep(2);
		SeleniumUtil.click(confirmButton, driver);
		Reporter.log("Clicked on confirm button");
		String newlDLValue = SeleniumUtil.getTextfromWebElement(
				SeleniumUtil.dynamicXpath(getLDLValue, date), driver).trim();
		String ldlval[] = newlDLValue.split(" ");
		String ldlCompValue = ldlval[0];

		SeleniumUtil.click(SeleniumUtil.dynamicXpath(editLink_hdl, date),
				driver);
		Reporter.log("Clicked on edit link");
		SeleniumUtil.click(edithdltxtBox, driver);
		Reporter.log("Clicked on hdl text box");
		SeleniumUtil.element(edithdltxtBox, driver).clear();
		SeleniumUtil.sendKeysToWebElement(edithdltxtBox, newhdl, driver);
		
		SeleniumUtil.click(submitBtn, driver);
		Reporter.log("Clicked on submit button");
		SeleniumUtil.sleep(2);
		SeleniumUtil.click(confirmButton, driver);
		Reporter.log("Clicked on confirm button");
		String newhDLValue = SeleniumUtil.getTextfromWebElement(SeleniumUtil.dynamicXpath(getHDLValue, date), driver).trim();
		String hdlval[] = newhDLValue.split(" ");
		String hdlCompValue = hdlval[0];
		
		SeleniumUtil.click(SeleniumUtil.dynamicXpath(editLink_triglyc, date),
				driver);
		Reporter.log("Clicked on edit link");
		SeleniumUtil.click(edittryglyceridestxtBox, driver);
		Reporter.log("Clicked on tryglyceride button");
		SeleniumUtil.element(edittryglyceridestxtBox, driver).clear();
		SeleniumUtil.sendKeysToWebElement(edittryglyceridestxtBox, newtriGly,
				driver);
		
		SeleniumUtil.click(submitBtn, driver);
		Reporter.log("Clicked on submit button");
		SeleniumUtil.sleep(4);
		SeleniumUtil.click(confirmButton, driver);
		Reporter.log("Clicked on confirm button");
		String newtriGlycerValue = SeleniumUtil.getTextfromWebElement(SeleniumUtil.dynamicXpath(gettriglycerValue, date), driver).trim();
		String triGlycer[] = newtriGlycerValue.split(" ");
		String triCompValue = triGlycer[0];
		Reporter.log("Asserting the edited values with the value from data sheet");
		if (cholesCompValue.equalsIgnoreCase(newChol)
				&& ldlCompValue.equalsIgnoreCase(newldl)
				&& hdlCompValue.equalsIgnoreCase(newhdl)
				&& triCompValue.equalsIgnoreCase(newtriGly))

			flag = true;

		return flag;

	}

	/**
	 * This method verifies whether the Cholesterol variables are deleted 
	 * @param date
	 * @return true if Cholesterol variables info deleted successfully
	 * @throws InterruptedException
	 */
	public boolean deleteEntry_Cholesterol(String date) throws InterruptedException {

		boolean flag = false;
		SeleniumUtil.click(SeleniumUtil.dynamicXpath(deleteLink, date),driver);
		Reporter.log("Clicked on delete link");
		SeleniumUtil.sleep(3);
		SeleniumUtil.clickbyJSID(yesButton, driver);
		SeleniumUtil.sleep(2);
		SeleniumUtil.click(confirmButton, driver);
		Reporter.log("Clicked on confirm button");
		SeleniumUtil.click(SeleniumUtil.dynamicXpath(deleteLink, date),driver);
		Reporter.log("Clicked on delete link");
		SeleniumUtil.sleep(4);
		SeleniumUtil.clickbyJSID(yesButton, driver);
		SeleniumUtil.sleep(4);
		SeleniumUtil.click(confirmButton, driver);
		Reporter.log("Clicked on confirm button");
		SeleniumUtil.click(SeleniumUtil.dynamicXpath(deleteLink, date),driver);
		Reporter.log("Clicked on delete link");
		SeleniumUtil.sleep(4);
		SeleniumUtil.clickbyJSID(yesButton, driver);
		SeleniumUtil.sleep(4);
		SeleniumUtil.click(confirmButton, driver);
		Reporter.log("Clicked on confirm button");
		SeleniumUtil.click(SeleniumUtil.dynamicXpath(deleteLink, date), driver);
		Reporter.log("Clicked on delete link");
		SeleniumUtil.sleep(3);
		SeleniumUtil.clickbyJSID(yesButton, driver);
		SeleniumUtil.sleep(3);
		SeleniumUtil.click(confirmButton, driver);
		Reporter.log("Clicked on confirm button");
		if (!SeleniumUtil.isElementDisplayed(SeleniumUtil.dynamicXpath(deleteLink, date),driver)
				&& !SeleniumUtil
						.isElementDisplayed(
								SeleniumUtil.dynamicXpath(deleteLink, date),
								driver)
				&& !SeleniumUtil
						.isElementDisplayed(
								SeleniumUtil.dynamicXpath(deleteLink, date),
								driver)
				&& !SeleniumUtil.isElementDisplayed(
						SeleniumUtil.dynamicXpath(deleteLink, date),
						driver))

			flag = true;

		return flag;

	}
}

