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
public class TermsOfUsePage extends MainPage {

	/** Logger to log message */
	private static Logger logger = LogManager.getLogger(com.lh.pages.authenticated.ContactPage.class);
	private By bannerLabelText						= By.id("ctl00_TopNavigationBarLeftContentHolder_TopNavigationTextLabel");

	
	public TermsOfUsePage() {
		super();
		Reporter.log("Initializing the TermsOfUsePage Object...");
		// Check that we're on the right page.
		if (!(driver.getTitle().equalsIgnoreCase(TestProperty.CLIENT_PORTAL_TITLE))) {
			// Alternatively, we could navigate to the login page, perhaps
			// logging out first
			logger.error("The current page is not the TermsOfUse page");
			throw new IllegalStateException(
					"This is not the TermsOfUse page");
		}
		Reporter.log("Initialized the TermsOfUse  page object");
	}

	/**
	 * Verify Terms of Use page 
	 * @param url_termsOfUse url of "Terms of Use" page
	 * @return true/false based on whether link is displayed and redirecting correctly or not
	 */
	public boolean verifyTermsOfUsePage(String url_termsOfUse){
		try{
		String urlText = driver.getCurrentUrl();
		SeleniumUtil.isElementDisplayed(bannerLabelText, driver);
		String bannerText = SeleniumUtil.element(bannerLabelText, driver).getText();
		if(urlText.contains(url_termsOfUse) && bannerText.contains(url_termsOfUse)){
			Reporter.log("Exiting the VerifyTermsOfUseLink() method for the HomePage object...");
			flag = true;
		}
		}catch(Exception e){
		
			logger.error("Error msg does not match. Exiting the VerifyTermsOfUseLink() method for the HomePage object...");
			flag = false;
		}
		return flag;
}
	
}