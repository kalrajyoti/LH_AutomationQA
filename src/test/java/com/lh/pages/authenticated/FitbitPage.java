package com.lh.pages.authenticated;

import java.util.List;

import org.apache.logging.log4j.Logger;import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.lh.testConfig.TestProperty;
import com.lh.utils.SeleniumUtil;

public class FitbitPage extends MainPage {
private  By connectAppLink = By.xpath("//*[@id='ctl00_ContentPlaceHolder1_appsTracking']/div/div/div/div[2]/a");
private By clickFitBitIcon = By.xpath("//*[@src='https://app.validic.com//assets/appicons/fitbit-icon.png']");
private By fitBitUsername = By.xpath("(//input[@class='field email'])[2]");
private By fitBitPassword = By.xpath("(//input[@class='field password'])[2]");
private By fitBitRemeberMeCheck = By.id("rememberMe");
private By allowAccess = By.xpath("(//div[@class='submit'])[2]/button");
private By manageApps = By.xpath("//*[@id='ctl00_ContentPlaceHolder1_appsTracking']/div/div/div/div[3]/div[2]/a");
private By selfEntry = By.xpath("//*[@id='ctl00_ContentPlaceHolder1_appsTracking']/div/div/div/div[1]/a");
private String viewEntries = "(//a[@class='view-entries'])[%s]";
private String fitBitSourceCell = "//div[@id='%sSourceValue']";//By.xpath("//div[@id='sleepSourceValue']"); // edit the second last div num
private String fitBitactionCell = "//div[@id='%sActionValue']";//By.xpath("//div[@id='sleepActionValue']");// edit the second last div num
private By numberOfRows = By.xpath("//*[@id='exercise']/div[2]/div[2]");
private By disconnectApp = By.xpath("//*[@id='ctl00_ContentPlaceHolder1_appsTracking']/div/div/div/div[4]/div[2]/div[3]/a");
private By disconnectContinue = By.xpath("//div[@id='byoaConfirmContinue']");
/**
	 * Logger to log messages
	 */
	private static Logger logger = LogManager.getLogger(FitbitPage.class);
	/**
	 * Webdriver to connect to the current instance of the browser
	 */
	private WebDriver driver;
	public FitbitPage() {
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
	
	public void setManageApps(String username,String password){
		//if(!SeleniumUtil.isElementDisplayed(manageApps, driver)){
			clickConnectApp();
			clickFitBit();
			enterFitBitCredentials(username,password);
		//}
	}
	public void clickConnectApp(){
		SeleniumUtil.waitForElementToBePresent(connectAppLink, driver);
	
		SeleniumUtil.click(connectAppLink, driver);
	}
	public void disconnectFitBit(){
		SeleniumUtil.waitForElementToBePresent(manageApps, driver);
		SeleniumUtil.click(manageApps, driver);
		SeleniumUtil.click(disconnectApp, driver);
		SeleniumUtil.click(disconnectContinue, driver);
	}
	public void clickFitBit(){
		SeleniumUtil.click(clickFitBitIcon, driver);
	}
	
	public void enterFitBitCredentials(String username,String password){
		
		SeleniumUtil.sendKeysToWebElement(fitBitUsername, username, driver);
		SeleniumUtil.sendKeysToWebElement(fitBitPassword, password, driver);
		SeleniumUtil.click(fitBitRemeberMeCheck, driver);
		SeleniumUtil.click(allowAccess, driver);
	
		
	}
	
	public void clickViewEntries(){
		
		// (//a[@class='view-entries'])[2] - sleep
		// (//a[@class='view-entries'])[1] - exercise
	}
	public Boolean verifyAppEntries(String testName){
		Boolean result = false;
		
		SeleniumUtil.waitForElementToBePresent(manageApps, driver);
		//SeleniumUtil.waitForElementToBePresent(selfEntry, driver);
		SeleniumUtil.click(selfEntry, driver);
		By clickViewEntries = null ;
		if(testName.contains("sleep")){
		 clickViewEntries =	SeleniumUtil.dynamicXpath(viewEntries, "2");
		} else if (testName.contains("exercise")){
			 clickViewEntries =	SeleniumUtil.dynamicXpath(viewEntries, "1");
		} else {
			Reporter.log("View Entries Xpath could not be dynamically generated.");	
		}
		
		SeleniumUtil.click(clickViewEntries, driver);
		int count = 1;
		//List<WebElement> viewEntries = driver.findElements(numberOfRows);
		By source = SeleniumUtil.dynamicXpath(fitBitSourceCell,testName);
		System.out.println("fitbitsourcecellxpath= "+source.toString());
		List<WebElement> sourceCell = driver.findElements(source);
		
		By action = SeleniumUtil.dynamicXpath(fitBitactionCell,testName);
		System.out.println("fitbitactioncellxpath= "+action.toString());
		List<WebElement> actionCell = driver.findElements(action);
		while(count<sourceCell.size()){
				
//			int rownumber = count +2;
//			List<WebElement> sourceCell = driver.findElements(By.xpath(fitBitSourceCell));
//			By row =SeleniumUtil.dynamicXpath(fitBitSourceCell, rownumber);
//			System.out.println("xpath = "+row.toString());
			if(sourceCell.get(count).getText().contains("Fitbit")){
			//	By action = SeleniumUtil.dynamicXpath(actionCell,rownumber);
				System.out.println("fitbit action field = "+actionCell.get(count).getText());
				if(actionCell.get(count).getText().contains(" ")){		
					result = true;
					Reporter.log("FitBit entries CAN'T be edited - Correct Response");
				} else {
					Reporter.log("FitBit entries can be edited - Incorrect Response");
				}
			} else if(sourceCell.get(count).getText().toLowerCase().contains("self-entered")){
				System.out.println("Self-Entered action field = "+actionCell.get(count).getText());
			//	By action = SeleniumUtil.dynamicXpath(actionCell+"/a",rownumber);
				if(actionCell.get(count).getText().contains("Edit | Delete")){
					result = true;
					Reporter.log("Self entries CAN be edited - Correct Response");
				} else {
					Reporter.log("Self entries can't be edited - Incorrect Response");
				}
			}
			count ++;
			}
			
		
		
		return result;
	}
}
