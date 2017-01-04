package com.lh.pages.authenticated;

import static com.lh.helper.DriverFactory.driver;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.testng.Reporter;
import com.lh.testConfig.TestProperty;
import com.lh.utils.SeleniumUtil;

/**
 * <h2>All the methods to work with Help page are defined here</h2>
 * <p>
 * 
 * @author pranali.jain
 * @version 1.0
 * @since 2015-06-29
 */

public class HelpPage extends MainPage{

	/** Logger to log message */
	private static Logger logger = LogManager.getLogger(com.lh.pages.authenticated.ContactPage.class);
	private By bannerLabelText						= By.id("ctl00_TopNavigationBarLeftContentHolder_TopNavigationTextLabel");
	
	/**
	 * One param constructor
	 * @param driver Webdriver instance to connect to the page under test
	 */
	public HelpPage() {
		super();
		Reporter.log("Initializing the HelpPage Object...");
		// Check that we're on the right page.
		if (!(driver.getTitle().equalsIgnoreCase(TestProperty.CLIENT_PORTAL_TITLE))) {
			// Alternatively, we could navigate to the login page, perhaps
			// logging out first
			logger.error("The current page is not the Help page");
			throw new IllegalStateException(
					"This is not the Help page");
		}
		Reporter.log("Initialized the Help page object");
	}

	/**
	 * Verify the Help page
	 * @param urlHelp The url of Help page
	 * @return true/false based on whether the static links are displayed on Help Page or not
	 */
	public boolean verifyHelpPage(String urlHelp){
		try{
		String urlText = driver.getCurrentUrl();
		SeleniumUtil.isElementDisplayed(bannerLabelText, driver);
		String helpText = SeleniumUtil.element(bannerLabelText, driver).getText();
		if(helpText.contains(urlHelp) && urlText.contains(urlHelp)){
			Reporter.log("Exiting the VerifyHelpLnk() method for the HomePage object...");
			flag = true;
		}
		}catch(Exception e){
			e.printStackTrace();
			logger.error("Error msg does not match. Exiting the VerifyHelpLnk() method for the HomePage object...");
			flag =  false;
		}
		return flag;
	}
}
