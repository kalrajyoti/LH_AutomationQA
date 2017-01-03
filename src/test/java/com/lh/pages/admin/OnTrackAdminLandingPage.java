package com.lh.pages.admin;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import com.lh.pages.authenticated.MainPage;
import com.lh.testConfig.TestProperty;
import com.lh.utils.SeleniumUtil;

public class OnTrackAdminLandingPage extends MainPage{

	public OnTrackAdminLandingPage(WebDriver driver) {
		super();
		logger.info("Initializing the OnTrackAdminPage Object...");
		this.driver = driver;
		// Check that we're on the right page.
		if (!(driver.getTitle().equalsIgnoreCase(TestProperty.CLIENT_PORTAL_TITLE))) {
			logger.error("This is not the Health Assessment page");
			throw new IllegalStateException(
					"This is not the HealthAssessmentPage page");
		}
		logger.info("Initialized the HealthAssessmentPage page object");
	
	}
	
	private static Logger logger = LogManager.getLogger(OnTrackAdminLandingPage.class);
	private WebDriver driver;
    private By product_Menu_Lnk				  	= 		By.xpath("//*[@id='AdminHome']/div[3]/div/div[2]/ul[1]/li[4]/a");
	private By onTrack_Lnk	  	                =	    By.xpath(".//*[@id='AdminHome']/div[3]/div/div[2]/ul[1]/li[4]/ul/li[8]/a");
	


	public boolean verifyProductLink()
	{
		boolean isPresent = false;
		if(SeleniumUtil.isElementDisplayed(product_Menu_Lnk, driver)){
			isPresent = true;
			logger.info("Product link is displayed...");
		}
		return isPresent;
	}
	
	public boolean clickOnProductLink()
	{
		
		SeleniumUtil.click(product_Menu_Lnk, driver);
		logger.info("Product link is clicked...");
			return clickOnProductLink();
		}
	
	public boolean verifyOnTrackLink()
	{
		boolean isPresent = false;
		if(SeleniumUtil.isElementDisplayed(onTrack_Lnk, driver)){
			isPresent = true;
			logger.info("On Track link is displayed...");
		}
		return isPresent;
	}
	
	
	
    public boolean SelectProduct()
    {
    	SeleniumUtil.click(onTrack_Lnk, driver);
    	Reporter.log("User select On Track product");
		logger.info("Clicked OnTrack  Link ");
		return false;
    	
    }
    
     public void getTitle()
     {
    		if (!(driver.getTitle().equalsIgnoreCase(TestProperty.CLIENT_PORTAL_TITLE))) {
    			logger.error("This is not the On Track Configuration page");
    			throw new IllegalStateException(
    					"This is not the On Track Configuration page");
    		}
    		logger.info("Initialized the On Track Configuration page object");
    	 
     }
     
            

}