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
public class PrivacyPolicyPage extends MainPage {
	
	/** Logger to log message */
	private static Logger logger = LogManager.getLogger(com.lh.pages.authenticated.ContactPage.class);
	
	private By bannerLabelText						= By.id("ctl00_TopNavigationBarLeftContentHolder_TopNavigationTextLabel");

	/**
	 * One param constructor
	 * @param driver Webdriver instance to connect to the page under test
	 */
	public PrivacyPolicyPage() {
		super();
		Reporter.log("Initializing the PrivacyPolicyPage Object...");
		// Check that we're on the right page.
		if (!(driver.getTitle().equalsIgnoreCase(TestProperty.CLIENT_PORTAL_TITLE))) {
			// Alternatively, we could navigate to the login page, perhaps
			// logging out first
			logger.error("The current page is not the PrivacyPolicy page");
			throw new IllegalStateException(
					"This is not the PrivacyPolicy page");
		}
		Reporter.log("Initialized the PrivacyPolicy page object");
	}

	/**
	 * Verify that link Privacy Policy is displayed and is redirecting to correct page
	 * @param url_privacyPolicy Url of page "Privacy Policy"
	 * @return true/false based on whether link is displayed and redirecting correctly or not
	 */
	public boolean verifyPrivacyPolicyPage(String url_privacyPolicy){
		
		try{
		
		SeleniumUtil.isElementDisplayed(bannerLabelText, driver);
		String bannerText = SeleniumUtil.element(bannerLabelText, driver).getText();
		String urlText = driver.getCurrentUrl();
		
		if(urlText.contains(url_privacyPolicy) && bannerText.contains("Privacy")){
			Reporter.log("Exiting the verifyPrivacyPolicyLink() method for the HomePage object...");
			flag = true;
		}
		}catch(Exception e){
		
			logger.error("Error msg does not match. Exiting the verifyPrivacyPolicyLink() method for the HomePage object...");
			flag = false;
		}
		return flag;
	}
}
