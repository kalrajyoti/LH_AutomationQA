package com.lh.pages.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import com.lh.utils.SeleniumUtil;

public class SearchTrackPage {

	private static Logger logger = LogManager.getLogger(SearchTrackPage.class);
	private  WebDriver driver;
    
	private By onTrack_View                     =       By.xpath("//*[@id='ManageApps']/div[6]/div/ul/li[3]/a");
    private By OnTrack_Client				  	= 		By.id("ddlClients");
    private By OnTrack_Track                    =       By.id("ddlTrack");
    private By OnTrack_Submit                   =       By.id("ctl00_ContentPlaceHolder1_ViewAppConfig1_btnSubmit");
    private By OnTrack_delete                   =       By.id("ctl00_ContentPlaceHolder1_ViewAppConfig1_GrdConfiguration_ctl02_linkDelete");
    private By onTrack_Add                      =       By.xpath("//*[@id='ManageApps']/div[6]/div/ul/li[2]/a");
	
	
    public SearchTrackPage(final WebDriver driver) {
    	this.driver=driver;
		logger.info("Initializing the search On Track Configuration Object...");
		logger.info("Initialized the searchTrackConfig page object");
	
	}
    
    
    public void clickOnTrackLink()
    {
  	  SeleniumUtil.click(onTrack_View, driver);
    }
    
    public void SelectClient()
    {
  	  SeleniumUtil.sendKeysToWebElement(OnTrack_Client,"aks1",driver); 
    }
  
    public void SelectTrack(String track)
    {
  	  SeleniumUtil.sendKeysToWebElement(OnTrack_Track,track, driver);
    }
  
    public void selectAksClient()
    {
     Select sel = new Select(driver.findElement(OnTrack_Client));
       sel.selectByIndex(4);
    }
    
    public void clickSubmitButton()
    {
      SeleniumUtil.click(OnTrack_Submit,driver);  
    }
   
    public boolean verifyDeleteLink()
    {
    	  boolean isPresent = false;
    	   if(SeleniumUtil.isElementDisplayed(OnTrack_delete,driver)){
    		   isPresent = true;
    		logger.info("Delete link is displayed");
    	   }
 	return isPresent;
    	
    }
    
    public void deleteTrack()
    {
    	SeleniumUtil.click(OnTrack_delete, driver);
    	SeleniumUtil.sleep(4);
    	Alert alert=driver.switchTo().alert();
		alert.accept();
    
   }
    
    public void click_OnTrackAddConfiguration()
    {
  	  SeleniumUtil.click(onTrack_Add, driver);
  	  logger.info("On Track click on add configuration is clicked");
    }
    
    	
}
