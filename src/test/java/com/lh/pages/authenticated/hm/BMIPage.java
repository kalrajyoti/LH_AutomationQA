package com.lh.pages.authenticated.hm;

import static com.lh.helper.DriverFactory.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Reporter;

import com.lh.pages.authenticated.MainPage;
import com.lh.testConfig.TestProperty;
import com.lh.utils.SeleniumUtil;

public class BMIPage extends MainPage {

	private static Logger logger = LogManager.getLogger(BloodPressurePage.class);
	
	private By bMIlink					=		By.xpath("//div[@id='leftNavigationButtons']//a[text()='BMI']");
	private By addEntryButton		  	=		By.xpath("//button[@id='AddEntryButton']");		
	private By dateAddress			  	=     	By.xpath("//input[@id='HealthEntryDateTextBox' and @type='text']");
	private By BMIAddress				=		By.id("ctl00_modalBodyContent_HealthEntryValueRepeater_ctl00_HealthEntryValueTextBox_TheNumericTextBox");
	private By notesAreaTextBox  	  	=		By.xpath("//textarea[@id='NotesTextBox']");
	private By submitButton			  	=		By.xpath("//input[@id='submitButton']");
//	private By confirmButton		  	= 		By.xpath("//form[@id='modalForm']/div[@class='modal-right']/input[@id='OKButton']");
	private By confirmButton		  	= 		By.id("OKButton");
	private By viewHistory			  	=  		By.xpath("//button[@id='ViewHistoryButton']");
	private String bmiVerifyText			=	"//span[text()='%s']//ancestor::tr/following-sibling::tr[1]/td[2]";
	private String editLink				=		"//span[text()='%s']/parent::td/following-sibling::td//a[text()='Edit']";
	private String deleteLink			=		"//span[text()='%s']/parent::td/following-sibling::td//a[text()='Delete']";
	private By dateEntry				=  		By.id("HealthEntryDateTextBox");
	private String yesButton			= 		"YesButton";
	
	public BMIPage() {
		
		super();
		Reporter.log("Initializing the HealthAssessmentPage Object...");
		
		if (!(driver.getTitle().equalsIgnoreCase(TestProperty.CLIENT_PORTAL_TITLE))) {
			logger.error("This is not the Health Assessment page");
			throw new IllegalStateException(
					"This is not the HealthAssessmentPage page");
		}
		Reporter.log("Initialized the HealthAssessmentPage page object");
	}

  
	/**
	 * This method verifies whether the data variables for BMI is added or not.
	 * @param date
	 * @param BMI
	 * @param notes
	 * @return true if BMI variable is added successfully and returns false if not added.
	 * @throws Throwable
	 */
	public boolean addEntry_BMI(String date, String BMI, String notes){
	
		boolean flag = false;
		SeleniumUtil.click(bMIlink, driver);
			SeleniumUtil.click(viewHistory, driver);
			Reporter.log("Clicked on viewHistory");
			SeleniumUtil.click(addEntryButton, driver);	
			Reporter.log("Clicked on addEntryButton");
			SeleniumUtil.click(dateAddress, driver);
			Reporter.log("Clicked on dateAddress");
			SeleniumUtil.sendkeys_JS(dateEntry, date, driver);
			Reporter.log("The input value to date input field is "+date);
			SeleniumUtil.sendkeys_JS(BMIAddress, BMI, driver);
			Reporter.log("The input value to BMI address field is "+BMI);
			SeleniumUtil.sendKeysToWebElement(notesAreaTextBox, notes, driver);
			Reporter.log("The input value to notes field is "+notes);
			SeleniumUtil.click(submitButton, driver);
			Reporter.log("Clicked on submitButton");
			SeleniumUtil.sleep(4);
			SeleniumUtil.click(confirmButton, driver);
			Reporter.log("Clicked on confirmButton");
			String BMIValue=SeleniumUtil.getTextfromWebElement(SeleniumUtil.dynamicXpath(bmiVerifyText, date), driver).trim();
			Reporter.log("The value from the text box of BMI is "+BMIValue);
			String bmi[] =BMIValue.split(" ");
			String bmiComp=bmi[0];
			Reporter.log("Asserting the BMI value with the value from external file");
			if(bmiComp.equalsIgnoreCase(BMI))
				flag=true;
		
		return flag;
	}
	
	/**
	 * Verifies the edited BMI variable
	 * @param date
	 * @param newBMI
	 * @return true if edited data of BMI is displayed successfully 
	 */
	public boolean editEntry_BMI(String date, String newBMI){
		
		boolean flag = false;
		
		SeleniumUtil.click(SeleniumUtil.dynamicXpath(editLink, date), driver);
		Reporter.log("Clicked on editLink");
		SeleniumUtil.click(BMIAddress, driver);
		Reporter.log("Clicked on BMIAddress");
		SeleniumUtil.element(BMIAddress, driver).clear();
		SeleniumUtil.sendKeysToWebElement(BMIAddress, newBMI, driver);
		Reporter.log("The edited input value of BMI is "+newBMI);
		SeleniumUtil.click(submitButton, driver);
		Reporter.log("Clicked on submitButton");
		SeleniumUtil.sleep(2);
		SeleniumUtil.click(confirmButton, driver);	
		Reporter.log("Clicked on confirmButton");
		String BMIValue=SeleniumUtil.getTextfromWebElement(SeleniumUtil.dynamicXpath(bmiVerifyText, date), driver).trim();
		Reporter.log("The value from the input textbox of BMI is "+BMIValue);
		String bmi[] =BMIValue.split(" ");
		String bmiComp=bmi[0];
		Reporter.log("Asserting the edited BMI value with the value from external file");
		if(bmiComp.equalsIgnoreCase(newBMI))
			flag=true;
		
		return flag;
	}
		
	/**
	 * This method verifies whether the BMI variable is deleted 
	 * @param date
	 * @return true if BMI variable info deleted successfully
	 * @throws InterruptedException
	 */
	public boolean deleteEntry_BMI(String date) throws InterruptedException{
		
		boolean flag = false;
		SeleniumUtil.click(SeleniumUtil.dynamicXpath(deleteLink, date), driver);
		Reporter.log("Clicked on delete link");
		SeleniumUtil.sleep(4);
		SeleniumUtil.clickbyJSID(yesButton, driver);
		SeleniumUtil.sleep(5);
		SeleniumUtil.click(confirmButton, driver);
		Reporter.log("Clicked on confirm button");
		if(!SeleniumUtil.isElementDisplayed(SeleniumUtil.dynamicXpath(deleteLink, date), driver))
			flag=true;
		
		return flag;
	}

}