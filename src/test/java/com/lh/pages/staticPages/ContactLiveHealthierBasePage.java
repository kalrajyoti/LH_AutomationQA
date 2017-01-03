package com.lh.pages.staticPages;

import static com.lh.helper.DriverFactory.driver;

import java.util.ArrayList;
import java.util.StringTokenizer;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.testng.Reporter;

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

public abstract class ContactLiveHealthierBasePage {
	
	protected By contactUsPageLbl 							= By.id("ctl00_TopNavigationBarLeftContentHolder_TopNavigationTextLabel");
	protected By emailPanelLbl 								= By.id("ctl00_ContentPlaceHolder1_panelContactPage");
	protected By userNameTxt 								= By.className("input-contact");
	protected By userEmailTxt 								= By.id("ctl00_ContentPlaceHolder1_Email");
	protected By contactingAboutTopicSel 					= By.name("ctl00$ContentPlaceHolder1$Reason");
	public static String dropdownofContactuspage =           "//div[@class='styled-select-subpage']//select[@id='ctl00_ContentPlaceHolder1_Reason']//option[text()='%s']";
	protected By messageTxt 								= By.id("ctl00_ContentPlaceHolder1_Message");
	protected By submitMsgBtn								= By.id("ContactFormSubmit");
	protected By liveSupportHeaderLbl 						= By.xpath("//*[@id='ctl00_ContentPlaceHolder1_LiveSupportCallout']/div[1]");
	protected By chatNowBtn 								= By.id("_lpChatBtn1");
	protected By contactUsThanksModalMsg 					= By.xpath("//*[@id='modalForm']/div[3]/p");
	protected By closeThanksModalBtn 						= By.id("ThanksClose");
		
	/**
	 * Logger to log messages
	 */
	protected static Logger logger = LogManager.getLogger(com.lh.pages.staticPages.ContactLiveHealthierBasePage.class);
	/**
	 * One param constructor
	 * 
	 * @param driver
	 *            webdriver instance to connect to the page under test
	 */
	public ContactLiveHealthierBasePage() {

		Reporter.log("Initializing the ContactLiveHealthierBasePage Object...");
		// Check that we're on the right page.
		if (!(driver.getTitle().equalsIgnoreCase(TestProperty.CLIENT_PORTAL_TITLE))) {
			logger.error("This is not the Contact us page");
			throw new IllegalStateException(
					"This is not the ContactLiveHealthierBasePage page");
		}
		Reporter.log("Initialized the ContactLiveHealthierBasePage page object");
	}

	
	/**
	 * Sets the name of the user
	 * @param userName the contacting user's name
	 */
	protected void setUserName(String userName) {

		if (userName.isEmpty()) {
			logger.error("The user First name is not provided");
			

		} else {
			//userName = userName.trim();
			driver.findElement(userNameTxt).sendKeys("testUser");
			Reporter.log("The user name is set to - " + userName);
		}

	}
	
	/**
	 *  Sets the email of the user
	 * @param userEmail The contacting users email.
	 */
	protected void setUserEmail(String userEmail) {

		if (userEmail.isEmpty()) {
			logger.error("The user email is not provided");

		} else {
			userEmail = userEmail.trim();
			driver.findElement(userEmailTxt).sendKeys(userEmail);
			Reporter.log("The user email is set to - " + userEmail);
		}

	}

	/**
	 * Sets the message topic/subject
	 * 
	 * @param topic
	 *            The topic to be set or the message
	 */
	protected void selectTopicOfMessage(String topic) {

		if (topic.isEmpty()) {

			logger.error("The user topic of the email is not provided");

		} else {

			topic = topic.trim();

			driver.findElement(contactingAboutTopicSel).sendKeys(topic);

			Reporter.log("The user email tipic is set to - " + topic);
		}

	}
	
	/**
	 * Sets the text of the email message.
	 * @param message The email message text.
	 */
	protected void setMessage(String message){
		
		if(message.isEmpty()){
						
			logger.error("The email message text is not provided");
			
		} else{
			
			message = message.trim();
			driver.findElement(messageTxt).sendKeys(message);
			
			Reporter.log("The email message  is set to - " + message);
		}
		
	}
	
	/**
	 * Clicks on the Submit button to Send the message.
	 */
	protected void clickSubmit(){
		
		Reporter.log("Submitting the message to livehealthier.");
		
		driver.findElement(submitMsgBtn).click();
	}

	/**
	 * Abstract method to set the email options in the Contact us page and send the email to LiveHealthier
	 * @param userName
	 * @param userEmail
	 * @param topic
	 * @param message
	 */
	public abstract void sendMessageToLiveHealthier(String userName, String userEmail, String topic, String message);

	/**
	 * Verifies that the Thanks modal is appearing on the screen
	 * @return true in case the thank you modal appears otherwise return false
	 */
	public boolean verifyThanksModal() {
		SeleniumUtil.sleep(3);
		if (driver.findElement(contactUsThanksModalMsg).isDisplayed()) {
			return true;
		}
		return false;
	}
	
	/**
	 * Clicks on the close button of the Thanks modal to close the modal dialog 
	 */
	public void closeThanksModal(){
		Reporter.log("Clicking on the close button of the Thank you modal dialog.");
		driver.findElement(closeThanksModalBtn).click();
		SeleniumUtil.sleep(2);
	}
	
}
