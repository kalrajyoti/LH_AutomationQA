package com.lh.pages.unauthenticated;

import static com.lh.helper.DriverFactory.driver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.testng.Reporter;

import com.lh.pages.staticPages.ContactLiveHealthierBasePage;
import com.lh.testConfig.TestProperty;
import com.lh.utils.SeleniumUtil;


/**
 * <h2>This is the Contact LiveHealthier Class for the Unauthenticated user</h2>
 * <p>
 * 
 * @author amita.arya
 * @version 1.0
 * @since 2015-02-18
 */

public class ContactLiveHealthierUnAuthenticatedPage extends ContactLiveHealthierBasePage {
	
	/**
	 * Logger to log messages
	 */
	private static Logger logger = LogManager.getLogger(com.lh.pages.unauthenticated.ContactLiveHealthierUnAuthenticatedPage.class);
	
	
	/**
	 * One param constructor
	 * 
	 * @param driver
	 *            webdriver instance to connect to the page under test
	 */
	public ContactLiveHealthierUnAuthenticatedPage() {
		super();
		Reporter.log("Initializing the ContactLiveHealthier Object...");
		// Check that we're on the right page.
		if (!(driver.getTitle().equalsIgnoreCase(TestProperty.CLIENT_PORTAL_TITLE))) {
			logger.error("This is not the Contact us page");
			throw new IllegalStateException(
					"This is not the ContactLiveHealthier page");
		}
		Reporter.log("Initialized the ContactLiveHealthier page object");
	}

	/**
	 * Sets the email options in the Contact us page and send the email to LiveHealthier
	 * @param userName
	 * @param userEmail
	 * @param topic
	 * @param message
	 */
	@Override
	public void sendMessageToLiveHealthier(String userName, String userEmail, String topic, String message){
		
		logger.debug("Setting the email options on the unauthenticated ContactUS page");
		
		setUserName(userName);
		setUserEmail(userEmail);
		selectTopicOfMessage(topic);
		setMessage(message);
		clickSubmit();
		SeleniumUtil.sleep(4);
		logger.debug("The message has been sent to LiveHealthier.");
		
	}

	
		
		
	}

	
		
	

