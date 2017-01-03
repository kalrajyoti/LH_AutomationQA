package com.lh.pages.authenticated;

import static com.lh.helper.DriverFactory.driver;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.testng.Reporter;
import com.lh.testConfig.TestProperty;
import com.lh.utils.SeleniumUtil;

/**
 * <h2>All the methods to work with Contact US page are defined here</h2>
 * <p>
 * 
 * @author amita.arya
 * @version 1.0
 * @since 2014-11-17
 */
public class ContactPage extends MainPage {
	/**
	 * Logger to log messages
	 */
	private static Logger logger = LogManager.getLogger(com.lh.pages.authenticated.ContactPage.class);

	// The Contact Us page contains several HTML elements that will be
	// represented as WebElements.
	private By firstnameTxt 						= By.id("ctl00_ContentPlaceHolder1_FirstLast");
	private By emailTxt 							= By.id("ctl00_ContentPlaceHolder1_Email");
	private By reasonTxt 							= By.id("ctl00_ContentPlaceHolder1_Reason");
	private By messageTxt 							= By.id("ctl00_ContentPlaceHolder1_Message");
	private By submitBtn							= By.id("ContactFormSubmit");
	private By mainLogoImg 							= By.id("ctl00_ImgLogo");
	private By popupOkayBtn 						= By.id("ThanksClose");
	private By insideHeaderBanner					= By.id("insideHeader");
	private By bannerLabelText						= By.id("ctl00_TopNavigationBarLeftContentHolder_TopNavigationTextLabel");
	
	/**
	 * One param constructor
	 * 
	 * @param driver
	 *            webdriver instance to connect to the page under test
	 */
	public ContactPage() {
		super();
		Reporter.log("Initializing the ContactPage Object...");
		// Check that we're on the right page.
		if (!(driver.getTitle().equalsIgnoreCase(TestProperty.CLIENT_PORTAL_TITLE))) {
			// Alternatively, we could navigate to the login page, perhaps
			// logging out first
			logger.error("The current page is not the Contact LiveHealthier page");
			throw new IllegalStateException(
					"This is not the Contact LiveHealthier page");

		}
		Reporter.log("Initialized the Contact page object");
	}

	/**
	 * Set the firstname of the user
	 * @param name
	 */
	public void setName(final String name) {
		driver.findElement(firstnameTxt).sendKeys(name);
		Reporter.log("Entered the FirstName as " + name);
	}

	/**
	 * Close the pop up by clicking on OK button
	 */
	public void clickPopupOkayBtn() {
		driver.findElement(popupOkayBtn).click();
		Reporter.log("Clicked the OK button in the Popup identified by - "
				+ popupOkayBtn);
	}

	/**
	 * Set the email id of the user
	 * @param email email id of the user
	 */
	public void setEmail(final String email) {
		driver.findElement(emailTxt).sendKeys(email);
		Reporter.log("Entered the Email as -" + email);
	}

	/**
	 * Set the reason for using the page
	 * @param reason the reason to contact
	 */
	public void setReason(final String reason) {
		driver.findElement(reasonTxt).sendKeys(reason);
		Reporter.log("Entered the reason as -" + reason);
	}

	/**
	 * Set the message text of the contact us page
	 * @param message message text
	 */
	public void setMessage(final String message) {
		Reporter.log("Entering the Message in the Element identified by - "
				+ messageTxt);
		driver.findElement(messageTxt).sendKeys(message);
		Reporter.log("Entered the message as -" + message);
	}

	/**
	 * Click on the submit button on the page to send the message
	 */
	public void clickSubmitMessage() {
		Reporter.log("Clicking the Submit button identified by - " + submitBtn);
		driver.findElement(submitBtn).click();
		Reporter.log("Clicked the Submit button");
	}

	/**
	 * Sends the message to LH
	 * @param name name of the user
	 * @param email email id  of the user
	 * @param reason reason for contacting
	 * @param message the message to send
	 */
	public void completeMessage(final String name, final String email,
			final String reason, final String message) {
		setName(name);
		setEmail(email);
		setReason(reason);
		setMessage(message);
		clickSubmitMessage();
		Reporter.log("Exiting the completeMessage method of the contactPage...");
	}

	public void completeMessage(final String reason, final String message) {
		setReason(reason);
		setMessage(message);
		clickSubmitMessage();
		Reporter.log("Exiting the completeMessage method of the contactPage...");
	}

	/**
	 * Click the icon at top left to go to home page
	 */
	public void gotoHome() {
		driver.findElement(mainLogoImg).click();
		Reporter.log("Clicked on the Main logo to return to home page of the application");
	}
	
	/**
	 * VerifyContact LiveHealthier page
	 * @param url_contactUs Url of page "ContactUs"
	 * @return true/false based on whether link is displayed and redirecting correctly or not
	 */
	public boolean verifyContactUsPage(String url_contactUs){
		try{
		
		
		//SeleniumUtil.isElementDisplayed(insideHeaderBanner, driver);
		SeleniumUtil.isElementDisplayed(bannerLabelText, driver);
		String bannerText = SeleniumUtil.element(bannerLabelText, driver).getText();
		String urlText = driver.getCurrentUrl();
		
		if(urlText.contains(url_contactUs) && bannerText.contains("Contact")){
			Reporter.log("Exiting the verifyContactUsPage() method for the HomePage object...");
			flag = true;
		}
		}catch(Exception e){
		
			logger.error("Error msg does not match. Exiting the verifyContactUsPage() method for the HomePage object...");
			flag = false;
		}
		return flag;
	}
}
