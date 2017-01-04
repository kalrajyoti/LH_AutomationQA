package com.lh.pages.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import com.lh.utils.SeleniumUtil;

public class UpdateOnTrackPage {

	private static Logger logger = LogManager.getLogger(UpdateOnTrackPage.class);
	public WebDriver driver;
	private By OnTrack_Client				  	    = 		By.id("ddlClients");
	private By OnTrack_Submit                       =       By.id("ctl00_ContentPlaceHolder1_ViewAppConfig1_btnSubmit");
	private By OnTrack_CheckRecords                 =       By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ViewAppConfig1_GrdConfiguration_ctl02_lblClientName']");
 	private By OnTrack_UpdatedRecords               =       By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ViewAppConfig1_GrdConfiguration_ctl02_lblCampaignKey']");
	private By OnTrack_challengeName                =       By.id("ctl00_ContentPlaceHolder1_ViewAppConfig1_GrdConfiguration_ctl02_lblChal3Name");
	private By onTrack_Add                           =      By.xpath("//*[@id='ManageApps']/div[6]/div/ul/li[2]/a");
	private By onTrack_ViewLink                      =      By.xpath("//*[@id='ManageApps']/div[6]/div/ul/li[3]/a");
    
	
	public UpdateOnTrackPage(final WebDriver driver) {
		//super(driver);
		logger.info("Initializing the updated On Track Configuration Object...");
		this.driver = driver;
		logger.info("Initialized the UpdatedOnTrackConfig page object");
	
	}
    
	public void SelectClient()
    {
  	  SeleniumUtil.sendKeysToWebElement(OnTrack_Client,"confluence",driver);
  	  Reporter.log("User has selected grc client");  
    }
	
	public void clickOnViewTrackPage()
    {
  	  SeleniumUtil.click(onTrack_ViewLink, driver);
  	  Reporter.log("User has clicked on  viewTrack link");  
    }
	
	public void clickSubmitButton()
    {
    SeleniumUtil.click(OnTrack_Submit,driver);
 	  Reporter.log("User click on submit button");  
    }
	
	public boolean clientWithRecords(String client)
    {
  	  boolean isPresent;
  	  isPresent = SeleniumUtil.element(OnTrack_CheckRecords, driver).getText().equalsIgnoreCase(client);
  	  Reporter.log("GRC client is not present");
  	  return isPresent;
    }
	
	public boolean updatedRecords(String campaignName)
    {
  	  boolean isPresent;
  	  isPresent = SeleniumUtil.element(OnTrack_UpdatedRecords, driver).getText().equalsIgnoreCase(campaignName);
  	  Reporter.log("Confluence Campaign is not present");
  	  return isPresent;
    }
	
	public boolean noRecords(String name)
    {
  	  boolean isPresent;
  	  isPresent = SeleniumUtil.element(OnTrack_challengeName, driver).getText().equalsIgnoreCase(name);
  	  return isPresent;
    }
	
	public void click_OnTrackAddConfiguration()
    {
  	  SeleniumUtil.click(onTrack_Add, driver);
  	  logger.info("On Track click on add configuration is clicked");
  	
    }
}
