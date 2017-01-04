package com.lh.pages.admin;

import com.lh.testConfig.TestProperty;
import com.lh.utils.SeleniumUtil;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Reporter;

import static com.lh.helper.DriverFactory.driver;
import static com.lh.utils.SeleniumUtil.waitForPageLoad;

/**
 * <h2>All the methods required to work with Login page are defined here</h2>
 * <p>
 * 
 * @author Akash vansil
 * @version 1.0
 * @since 2016-02-03
 */

public class AdminLoginPage {
	/**
	 * Logger to log messages
	 */
	private static Logger logger = LogManager
			.getLogger(com.lh.pages.unauthenticated.LoginPage.class);

	By errorMessage 										= By.xpath("//*[contains(.,'do not have the required permissions to view')][not(.//*[contains(., 'do not have the required permissions to view')])]");
	private By usernameTxt 									= By.cssSelector("#ctl00_ContentPlaceHolder1_UserName");
	private By passwordTxt 									= By.cssSelector("#ctl00_ContentPlaceHolder1_Password");
	private By loginBtn 									= By.cssSelector("#ctl00_ContentPlaceHolder1_btnSignIn");
	private By adminMsgViewLink								= By.xpath("//td[text()[normalize-space() = 'ExpediaEST']]/following-sibling::td[@class='ConversationTitlesLink']/a");
	private By postNewMsgLink								= By.id("ctl00_ContentPlaceHolder1_linkNewMessage");
	private By submitBtn 									= By.xpath("//a[@id='SubmitButton']/img");
	private By logoutLnk									= By.xpath("//div[@id='ctl00_ContentPlaceHolder1_ExpertTopNav1_divLoginLogout']/a[text()=' Logout']");


	/**
	 * One param constructor
	 * 
	 * @param driver
	 *            webdriver instance to connect to the page under test
	 */
	public AdminLoginPage() {
		Reporter.log("...initializing the Admin LoginPage Object...");
		// Check that we're on the right page.
		driver.get(TestProperty.BASEADMINURL);
		if (driver.getCurrentUrl().contains("ErrorPage")){
			// HTTP 403 Error.  Logout and Retry
			logoutFromAdmin();
			loginAs(TestProperty.ADMIN_USERNAME,TestProperty.ADMIN_PASSWORD);
			//logger.error("This is not the admin login page");
			//throw new IllegalStateException("This is not the admin login page");
		}
		Reporter.log("Success - *INITIALIZED* AdminLoginPage.");
	}

	public AdminLoginPage(String pageTitle){
		if(!(driver.getTitle().equals(""))){
			logger.error("This is not the admin login page");
			throw new IllegalStateException("This is not the admin login page");
		}
	}

	public void logoutFromAdmin() {
		driver.findElement(By.linkText("Logout"));
		waitForPageLoad(driver);
		Reporter.log("**Successfully logged out from Admin Portal and Closed Broser.");
	}

	/**
	 * 
	 * @param username
	 * Set username in the text fields
	 */

	private void setUsername(final String username) {
		Reporter.log("Entering the username in the Element identified by - "  + usernameTxt);
		driver.findElement(usernameTxt).sendKeys(username);
		Reporter.log("Entered the Username as "  + username);
	}
	/**
	 * 
	 * @param password
	 * Set password in the text fields
	 */
	private void setPassword(final String password) {
		Reporter.log("Entering the Password in the Element identified by - " + passwordTxt);
		driver.findElement(passwordTxt).sendKeys(password);
		Reporter.log("Entered the Password as "  + password);
	}
	/**
	 * 
	 * click on submit button
	 */

	private void submitLogin() {
		Reporter.log("Clicking the Login button identified by - " +  usernameTxt);
		//logger.info("Clicking the Login button identified by - "   usernameTxt);
		driver.findElement(loginBtn).click();
		Reporter.log("Logging into the application...");
		//logger.info("Logging into the application...");
	}

	/**
	 * 
	 * @param username,password
	 * return new home page on successful login
	 */
	public HomePage loginAs(final String username, final String password) {
		Reporter.log("Logging into Admin Portal...");
		//logger.info("Logging into the application...");
		setUsername(username);
		setPassword(password);
		submitLogin();
		Reporter.log("Success - Logged into Admin Portal.");
		return new HomePage();
		//logger.info("Logged into the application...");
	}

	public void clickOnCoachMsgViewLink(){
		SeleniumUtil.waitForElementToBeVisible(adminMsgViewLink, driver);
		SeleniumUtil.element(adminMsgViewLink, driver).click();
	}		 

	public void clickOnPostNewMsgLink(){
		SeleniumUtil.waitForElementToBeVisible(postNewMsgLink, driver);
		SeleniumUtil.element(postNewMsgLink, driver).click();
	}

	public void enterMsg(){
		JavascriptExecutor javascript = ((JavascriptExecutor)driver);
		javascript.executeScript("document.getElementById('TxtMessage_ifr').contentDocument.getElementById('tinymce').childNodes[0].innerHTML='Reply to Test Message'");
		SeleniumUtil.switchToDefaultContent(driver);
		SeleniumUtil.sleep(3);
		SeleniumUtil.element(submitBtn, driver).click();
	}

	public void clickLogout(){
		SeleniumUtil.sleep(3);
		SeleniumUtil.element(logoutLnk, driver).click();
	}
}
