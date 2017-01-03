package com.lh.pages.admin;

import java.util.List;
import static com.lh.helper.DriverFactory.driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.lh.utils.SeleniumUtil;

public class EditOnTrackConfigPage {

	private static Logger logger = LogManager.getLogger(EditOnTrackConfigPage.class);
	
	private By OnTrack_selectCampaign               =       By.xpath("//*[@id='selectCC_chosen']/a/div/b");
	private By OnTrack_inputCampaign                =       By.xpath("//*[@id='selectCC_chosen']/div/div/input");
 	private By onTrack_btnUpdate                    =       By.xpath("//*[@id='ctl00_ContentPlaceHolder1_AddOntrackAppConfig_UpdateButton']");
 	private By OnTrack_Client				  	    = 		By.id("ddlClients");
 	private By OnTrack_Submit                       =       By.id("ctl00_ContentPlaceHolder1_ViewAppConfig1_btnSubmit");
 	private By OnTrack_CheckRecords                 =       By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ViewAppConfig1_GrdConfiguration_ctl02_lblClientName']");
 	private By OnTrack_UpdatedRecords               =       By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ViewAppConfig1_GrdConfiguration_ctl02_lblCampaignKey']");
	private By OnTrack_challengeName                =       By.id("ctl00_ContentPlaceHolder1_ViewAppConfig1_GrdConfiguration_ctl02_lblChal3Name");
	public String campaignName = "CAM2016 -";
	private By onTrack_Add                           =       By.xpath("//*[@id='ManageApps']/div[6]/div/ul/li[2]/a");
	private By onTrack_ViewLink                      =       By.xpath("//*[@id='ManageApps']/div[6]/div/ul/li[3]/a");
    
	public EditOnTrackConfigPage() {
		super();
		logger.info("Initializing the Edit On Track Configuration Object...");
		
			}
		
	
	public void SelectClient()
    {
  	  SeleniumUtil.sendKeysToWebElement(OnTrack_Client,"confluence",driver);
  	  
    }
	
	public void clickOnViewTrackPage()
    {
  	  SeleniumUtil.click(onTrack_ViewLink, driver);
  	  
    }
	
	
	 public void clickSubmitButton()
     {
     SeleniumUtil.click(OnTrack_Submit,driver);
  	  
     }
	
	 public boolean clientWithRecords(String client)
     {
   	  boolean isPresent;
   	  isPresent = SeleniumUtil.element(OnTrack_CheckRecords, driver).getText().equalsIgnoreCase(client);
   	  return isPresent;
     }
	 
	 public boolean updatedRecords(String campaignName)
     {
   	  boolean isPresent;
   	  isPresent = SeleniumUtil.element(OnTrack_UpdatedRecords, driver).getText().equalsIgnoreCase(campaignName);
   	  return isPresent;
     }
	 
	 public boolean noRecords(String name)
     {
   	  boolean isPresent;
   	  isPresent = SeleniumUtil.element(OnTrack_challengeName, driver).getText().equalsIgnoreCase(name);
   	  return isPresent;
     }
	
	public void SelectTrackCampaign()
    {
	  driver.findElement(OnTrack_selectCampaign).click();
	  driver.findElement(OnTrack_inputCampaign).sendKeys(campaignName);
	  driver.findElement(OnTrack_inputCampaign).sendKeys(Keys.ENTER);
	  SeleniumUtil.sleep(1);
    }
	
	
	 public void selectChallengeDurationForTheme3()
     {
		 SeleniumUtil.selRadioOption("ctl00$ContentPlaceHolder1$AddOntrackAppConfig$chkSuppressManualEntryTheme3",2,driver);
		 
     }
	
	 public void submitUpdate()
	 {
		 SeleniumUtil.click(onTrack_btnUpdate, driver);
		 
	 }
	 
	
	 public void clickonBack()
	 {
		 driver.navigate().back();
		 
	 }
	 
	 public void clickOnViewConfig()
	 {
		 SeleniumUtil.click(onTrack_ViewLink, driver);
	 }
	
	 
	 public void suppressChallengeTheme3()
     {
		selRadioOption("ctl00$ContentPlaceHolder1$AddOntrackAppConfig$Theme3$Challenge",2);
		                  
     }
	
	 public void click_OnTrackAddConfiguration()
     {
   	  SeleniumUtil.click(onTrack_Add, driver);
   	  logger.info("On Track click on add configuration is clicked");
   	 
     }
    
	 public void selRadioOption(String name,int i)
	 {
		 try{
			 List<WebElement> RadButtonList = driver.findElements(By.name(name));
		      RadButtonList.get(i).click();
			 
		 }
		 catch(Exception e)
		 {
				
				 
		 }
		 
	 }

}
