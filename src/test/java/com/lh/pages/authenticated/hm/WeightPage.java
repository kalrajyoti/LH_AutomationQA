package com.lh.pages.authenticated.hm;

import static com.lh.helper.DriverFactory.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Reporter;

import com.lh.pages.authenticated.MainPage;
import com.lh.testConfig.TestProperty;
import com.lh.utils.SeleniumUtil;

public class WeightPage extends MainPage {

	private static Logger logger = LogManager.getLogger(WeightPage.class);
	
	private By weightlnk=By.xpath("//a[text()='Weight']");
	private By viewHistory = By.xpath("//button[@id='ViewHistoryButton']");
	private By addEntryButton = By.xpath("//button[@id='AddEntryButton']");
	private By dateAddress = By
			.xpath("//input[@id='HealthEntryDateTextBox' and @type='text']");
	private By weightTextBox = By.xpath("//input[@id='ctl00_modalBodyContent_HealthEntryValueRepeater_ctl00_HealthEntryValueTextBox_TheNumericTextBox']");
	private By submitBtn = By.xpath("//input[@id='submitButton']");
	private By confirmButton = By.xpath("//input[@id='OKButton']");
	private String weightValuexpath ="//span[text()='%s']/ancestor::tr/following-sibling::tr[1]/td[2]";
	private String editWeight = "//span[text()='%s']/parent::td/following-sibling::td//a[text()='Edit']";
	private String deleteWeight= "//span[text()='%s']/parent::td/following-sibling::td//a[text()='Delete']";
	private String deleteLink			=		"//span[text()='%s']/parent::td/following-sibling::td//a[text()='Delete']";
	private String yesButton			= 		"YesButton";
	public WeightPage() {
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
	 * This method verifies whether the data variable for Weight Page is added or not.
	 * @param date
	 * @param weightTxt
	 * @return true if Weight Page variables added successfully and returns false if not added.
	 * @throws Throwable
	 */
	public boolean addEntryWeight(String date, String weightTxt){
		
		boolean flag = false;
		SeleniumUtil.click(weightlnk, driver);
		Reporter.log("Clicked on the weight Link");
		SeleniumUtil.click(viewHistory, driver);
		Reporter.log("Clicked on the view history button");
		SeleniumUtil.click(addEntryButton, driver);
		Reporter.log("Clicked on the Add Entry button");
		SeleniumUtil.click(dateAddress, driver);
		Reporter.log("Click on the calendar date text box");
		SeleniumUtil.sendKeysToWebElement(dateAddress, date, driver);
		Reporter.log("The Entered date in calendar text box is" + date);
		SeleniumUtil.sendKeysToWebElement(weightTextBox, weightTxt, driver);
		Reporter.log("The Entered date in weight text box is" + weightTxt);
		SeleniumUtil.click(submitBtn, driver);
		Reporter.log("Clicked on the submit button ");
		SeleniumUtil.sleep(2);
		SeleniumUtil.click(confirmButton, driver);
		Reporter.log("Clicked on the Okay button ");
		String weightValue = SeleniumUtil.getTextfromWebElement(SeleniumUtil.dynamicXpath(weightValuexpath,date),driver).trim();
		String weight[] = weightValue.split(" ");
		String weightVal = weight[0];
		Reporter.log("Asserting the weight value with the value from external file");
		if(weightVal.equalsIgnoreCase(weightTxt))
			flag=true;
		return flag;
		
	}
	
	/**
	 * Verifies the edited Weight Page variables
	 * @param date
	 * @param newWeightTxt
	 * @return true if edited Weight Page data is displayed successfully 
	 */
	public boolean editEntryWeight(String date,String newWeightTxt){
		
		boolean flag = false;
		SeleniumUtil.click(SeleniumUtil.dynamicXpath(editWeight, date), driver);
		Reporter.log("Clicked on edit weight");
		SeleniumUtil.click(weightTextBox, driver);
		Reporter.log("Clicked on weight textBox");
		SeleniumUtil.element(weightTextBox, driver).clear();
		SeleniumUtil.sendKeysToWebElement(weightTextBox, newWeightTxt, driver);
		Reporter.log("The new weight value is "+newWeightTxt);
		SeleniumUtil.click(submitBtn, driver);
		Reporter.log("Clicked on the submit button ");
		SeleniumUtil.sleep(2);
		SeleniumUtil.click(confirmButton, driver);
		Reporter.log("Clicked on the Okay button ");
		String editWeight = SeleniumUtil.getTextfromWebElement(SeleniumUtil.dynamicXpath(weightValuexpath,date),driver).trim();
		String WeightVal[] = editWeight.split(" ");
		String editedWeight = WeightVal[0];
		Reporter.log("Asserting the edited weight value with the value from external file");
		if(editedWeight.equalsIgnoreCase(newWeightTxt))
			flag = true;
		
		return flag;
	}
	
	/**
	 * This method verifies whether the Weight Page variables are deleted 
	 * @param date
	 * @return true if Weight Page variables info deleted successfully
	 * @throws InterruptedException
	 */
	public boolean deleteEntryWeight(String date) throws InterruptedException{
		
		boolean flag = false;
		SeleniumUtil.click(SeleniumUtil.dynamicXpath(deleteWeight, date), driver);
		Reporter.log("Clicked on weight delete link");
		SeleniumUtil.sleep(3);
		SeleniumUtil.clickbyJSID(yesButton, driver);
		SeleniumUtil.sleep(2);
		SeleniumUtil.click(confirmButton, driver);
		Reporter.log("Clicked on confirm button");
		SeleniumUtil.sleep(2);
		System.out.println(SeleniumUtil.isElementDisplayed(SeleniumUtil.dynamicXpath(deleteLink, date), driver));
		
		if(!SeleniumUtil.isElementDisplayed(SeleniumUtil.dynamicXpath(deleteLink, date), driver))
		
		flag =true;
		
		return flag;
		
		
	}
}
