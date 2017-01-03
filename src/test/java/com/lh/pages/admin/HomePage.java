package com.lh.pages.admin;

import static com.lh.helper.DriverFactory.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import com.lh.utils.ExcelFileUtility;
import com.lh.utils.SeleniumUtil;
/**
 * <h2>This is the Admin Base class, it contains all the common methods which can be
 * used by admin pages</h2>
 * <p>
 * 
 * @author Akash Vansil
 * @version 1.0
 * @since 2016-02-04
 */

public class HomePage {
	
	/**
	 * Logger to log messages
	 */
	private static Logger logger 					= LogManager.getLogger(com.lh.pages.admin.HomePage.class);
	protected static ExcelFileUtility testDataObj;
    private By product_Menu_Lnk				  	= 		By.xpath("//*[@id='AdminHome']/div[3]/div/div[2]/ul[1]/li[4]/a");
	private By onTrack_Lnk	  	                =	    By.xpath("//*[@id='AdminHome']/div[3]/div/div[2]/ul[1]/li[4]/ul/li[8]/a");
	

	/**
	 * One param constructor
	 * 
	 * @param driver
	 *            webdriver instance to connect to the page under test
	 */
	public HomePage() {
		logger.info("Initializing the HomePage Object...");
		logger.info("Checking whether we are on the Home Page...");
		}
	
	public boolean isLinkDisplayed()
	{
		boolean isPresent = false;
		if(SeleniumUtil.isElementDisplayed(product_Menu_Lnk, driver)){
			isPresent = true;
			logger.info("Product link is displayed...");
		}
		return isPresent;
	}
	
	public void clickOnProductLink()
	{
		SeleniumUtil.click(product_Menu_Lnk, driver);
		logger.info("Product link is clicked...");
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
	
	
	
    public void SelectProduct()
    {
    	SeleniumUtil.click(onTrack_Lnk, driver);
		logger.info("Clicked OnTrack  Link ");
		
    }
    
	
	
}
