package com.lh.pages.authenticated.hm;
import static com.lh.helper.DriverFactory.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.lh.pages.authenticated.MainPage;
import com.lh.testConfig.TestProperty;
import com.lh.utils.SeleniumUtil;

public class BloodPressurePage extends MainPage {

	private static Logger logger = LogManager.getLogger(BloodPressurePage.class);
	
	private By BP_Link 				  	= 		By.xpath("//a[text()='Blood Pressure']");
	private By addEntryButton		  	=		By.xpath("//button[@id='AddEntryButton']");		
	private By dateAddress			  	=     	By.xpath("//input[@id='HealthEntryDateTextBox' and @type='text']");
	private By systolicAddress		  	=		By.xpath("//input[@id='ctl00_modalBodyContent_HealthEntryValueRepeater_ctl00_HealthEntryValueTextBox_TheNumericTextBox']");	
	private By diastolicAddress		  	=		By.xpath("//input[@id='ctl00_modalBodyContent_HealthEntryValueRepeater_ctl01_HealthEntryValueTextBox_TheNumericTextBox']");
	private By notesAreaTextBox  	  	=		By.xpath("//textarea[@id='NotesTextBox']");
	private By submitButton			  	=		By.xpath("//input[@id='submitButton']");	
	private By confirmButton		  	= 		By.xpath("//input[@id='OKButton']");
	private By viewHistory			  	=  		By.xpath("//button[@id='ViewHistoryButton']");	
	private String systolicVerifyXpath  =		"//span[text()='%s']//ancestor::tr/following-sibling::tr[2]/td[2]";	
	private String diastolicVerifyXpath =   	"//span[text()='%s']//ancestor::tr/following-sibling::tr[1]/td[2]";
	private String editLink				=		"//span[text()='%s']/parent::td/following-sibling::td//a[text()='Edit']";
	private String deleteLink			=		"//span[text()='%s']/parent::td/following-sibling::td//a[text()='Delete']";
	protected String HALeftColumnText 	= 		"//div[@id='leftNavigationButtons']//a[text()='%s']";
	private String yesButton			= 		"YesButton";	
	
	public	BloodPressurePage() {
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
	 * This method verifies the left column links of Health Manager tab
	 * @param text
	 * @return true if verification is successful and false if verification fails
	 */
	public boolean verifyHealthManagerLeftColumnLinks(String text){
		
		boolean flag=false;
		String columnText=SeleniumUtil.getTextfromWebElement(SeleniumUtil.dynamicXpath(HALeftColumnText, text),driver) ;
	    Reporter.log("The columntext is "+columnText);
		if(text.equalsIgnoreCase(columnText)){
			
			Reporter.log("Successfully verified the column text");
			Reporter.log("Successful in verifying the columntext with the text from data sheet");
			flag= true;
		}
		else
			Reporter.log("Failed to verify the column text");	
		return flag;
	}	

	/**
	 * This method verifies whether the data variables for blood pressure are added or not.
	 * @param date
	 * @param sysData
	 * @param dystolData
	 * @param textArea
	 * @return true if blood pressure variables added successfully and returns false if not added.
	 * @throws Throwable
	 */
	public boolean additionOfEntry_BloodPressure(String date,String sysData, String dystolData, String textArea) throws Throwable{
		
	boolean flag = false;	
	SeleniumUtil.click(BP_Link, driver);
	
		WebDriverWait wait = new WebDriverWait(driver, 60);
		SeleniumUtil.click(viewHistory, driver);
		Reporter.log("Clicked on Edit Entry ");
		SeleniumUtil.click(addEntryButton, driver);
		Reporter.log("Clicked on add entry button");
		wait.until(ExpectedConditions.visibilityOfElementLocated(dateAddress));
		SeleniumUtil.click(dateAddress, driver);
		Reporter.log("Clicked on dateAddress");
		SeleniumUtil.sendKeysToWebElement(dateAddress, date, driver);
		Reporter.log("The input value to dateAddress field is "+date);
		SeleniumUtil.sendKeysToWebElement(systolicAddress, sysData, driver);
		Reporter.log("The input value to systolicAddress field is "+sysData);
		SeleniumUtil.sendKeysToWebElement(diastolicAddress, dystolData, driver);
		Reporter.log("The input value to diastolicAddress field is "+dystolData);
		SeleniumUtil.sendKeysToWebElement(notesAreaTextBox, textArea, driver);
		Reporter.log("The input value to notesAreaTextBox field is "+textArea);
		SeleniumUtil.click(submitButton, driver);
		Reporter.log("Clicked on submitButton");
		SeleniumUtil.sleep(3);
		SeleniumUtil.click(confirmButton, driver);
		Reporter.log("Clicked on confirmButton ");
		String systolicValue=SeleniumUtil.getTextfromWebElement(SeleniumUtil.dynamicXpath(systolicVerifyXpath, date), driver).trim();
		String diastolicValue=SeleniumUtil.getTextfromWebElement(SeleniumUtil.dynamicXpath(diastolicVerifyXpath, date), driver).trim();
		String systol[]=systolicValue.split(" ");
		String sysCompValue=systol[0];
		Reporter.log("The systolic value to compare is "+sysCompValue);
		String diastol[]=diastolicValue.split(" ");
		String diasCompValue=diastol[0];
		Reporter.log("The diastolic value to compare is "+diasCompValue);
		Reporter.log("Asserting the systolic & diastolic values with the values from external file");
		if(sysCompValue.equalsIgnoreCase(sysData) && diasCompValue.equalsIgnoreCase(dystolData))
			flag=true;
			
			return flag;	
	}

	/**
	 * Verifies the edited blood pressure variables
	 * @param date
	 * @param newSystolic
	 * @param newDiastolic
	 * @return true if edited data is displayed successfully 
	 * @throws InterruptedException 
	 */
	public boolean edit_BloodPressure(String date, String newSystolic, String newDiastolic) throws InterruptedException{
		
	boolean flag=false;
	
	SeleniumUtil.click(SeleniumUtil.dynamicXpath(editLink, date), driver);
	Reporter.log("Clicked on date input box ");
	SeleniumUtil.click(systolicAddress, driver);
	Reporter.log("Clicked on systolic input box");
	SeleniumUtil.element(systolicAddress, driver).clear();
	SeleniumUtil.sendKeysToWebElement(systolicAddress, newSystolic, driver);
	Reporter.log("The input value to systolicAddress field is "+newSystolic);
	SeleniumUtil.element(diastolicAddress, driver).clear();
	SeleniumUtil.sendKeysToWebElement(diastolicAddress, newDiastolic, driver);
	Reporter.log("The input value to systolicAddress field is "+newDiastolic);
	SeleniumUtil.click(submitButton, driver);
	Reporter.log("Clicked on submitButton");
	SeleniumUtil.sleep(2);
	SeleniumUtil.click(confirmButton, driver);
	Reporter.log("Clicked on confirmButton ");
	String systolicValue=SeleniumUtil.getTextfromWebElement(SeleniumUtil.dynamicXpath(systolicVerifyXpath, date), driver).trim();
	String diastolicValue=SeleniumUtil.getTextfromWebElement(SeleniumUtil.dynamicXpath(diastolicVerifyXpath, date), driver).trim();
	String systol[]=systolicValue.split(" ");
	String sysCompValue=systol[0];
	String diastol[]=diastolicValue.split(" ");
	String diasCompValue=diastol[0];
	Reporter.log("Asserting the edited systolic & diastolic values with the values from external file");
	if(sysCompValue.equalsIgnoreCase(newSystolic) && diasCompValue.equalsIgnoreCase(newDiastolic))
	flag=true;	
	
	return flag;
	}

	/**
	 * This method verifies whether the variables are deleted 
	 * @param date
	 * @return true if variables info deleted successfully
	 * @throws InterruptedException
	 */
public boolean delete_BloodPressureEntry(String date) throws InterruptedException{
	
	boolean flag=false;
	
	SeleniumUtil.click(SeleniumUtil.dynamicXpath(deleteLink, date), driver);
	Reporter.log("Clicked on delete link");
	SeleniumUtil.sleep(4);
	SeleniumUtil.clickbyJSID(yesButton, driver);
	Reporter.log("Clicked on delete button");
	SeleniumUtil.sleep(5);
	SeleniumUtil.click(confirmButton, driver);
	SeleniumUtil.sleep(4);
	SeleniumUtil.click(confirmButton, driver);
	Reporter.log("Clicked on confirmButton Link");
	SeleniumUtil.sleep(3);
	if(!SeleniumUtil.isElementDisplayed(SeleniumUtil.dynamicXpath(deleteLink, date), driver))
		
		flag=true;
	
	return flag;	
}

}