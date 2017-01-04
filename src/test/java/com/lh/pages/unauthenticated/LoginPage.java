package com.lh.pages.unauthenticated;

import static com.lh.helper.DriverFactory.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Reporter;

import com.lh.utils.SeleniumUtil;

/**
 * <h2>All the methods required to work with Login page are defined here</h2>
 * <p>
 * 
 * @author amita.arya
 * @version 1.0
 * @since 2014-11-17
 */
public class LoginPage {

	/**
	 * Logger to log messages
	 */
	private static Logger logger = LogManager
			.getLogger(com.lh.pages.unauthenticated.LoginPage.class);

	// The locators for the HTML elements should only be defined once
	private By usernameTxt 									= By.id("ctl00_ContentPlaceHolder1_UserName");
	private By passwordTxt 									= By.id("ctl00_ContentPlaceHolder1_Password");
	private By loginBtn 									= By.id("ctl00_ContentPlaceHolder1_lnkLogin");
	private By loginAdminBtn								= By.xpath("//input[@value='Sign in']");
	private By contactLivehealthierLnk 						= By.id("ctl00_ContactLink");
	private By preferencesLnk 								= By.id("ctl00_PreferencesLink");
	private By forgotPasswordLnk 							= By.id("forgotPasswordLink");
	private By forgotPassUsrnmTxt 							= By.xpath("//div[@id='forgotDialog']//div[@id='forgotPass']//input[@id='txUserName']");
	private By forgotPassSubmitBtn 							= By.id("nextButton1");
	private By forgotPassCloseBtn 							= By.xpath("//span[@id='ui-id-2']//following-sibling::button[@title='close']");
	private By forgotPassSecAns1Txt 						= By.id("txSecA1");
	private By forgotPassSecAns2Txt 						= By.id("txSecA2");
	private By forgotPassSecAns3Txt 						= By.id("txSecA3");
	private By forgotPassSecAnsSubBtn 						= By.id("nextButton");
	private By forgotPassErrLbl 							= By.id("StatusWarning");
	private By forgotPassNewPassTxt 						= By.id("password");
	private By forgotPassConfirmPassTxt 					= By.id("Password1");
	private By forgotPassNewPassSubBtn 						= By.id("btnNext");
	private By forgotPassCreatePassSubBtn 					= By.id("btnSave");
	private String forgotPassNewPassSvdBtn 					="btnClose";
	private By validationErrSummaryLbl 						= By.id("ctl00_ContentPlaceHolder1_ValidationSummary");
	private By errForgotPassLbl 							= By.xpath("//*[@id='forgotPass']/div/div[1]/label[@class='error']");
	private By forgotPass2TxtHeader 						= By.xpath("//*[@id='modalSubheader']/span");
	private By errMsgUserOfOtherClient 						= By.id("StatusWarning");
	private By registerNowLink								= By.id("ctl00_ContentPlaceHolder1_LoginRegisterButtonTeaser_lkRegister");
	protected By contactLiveHelathierLink					= By.id("ctl00_ContactLink"); 
	protected By clientLogo									= By.id("ctl00_ImgLogo");
	private By pwdUpdated									= By.xpath("//form[@id='modalForm']/div/p[@class='articletext']");
	private By okayBtn										= By.xpath("//form[@id='modalForm']/div/div[@class='modal-right']/input[@id='btnClose']");
	
	/**
	 * Constructor
	 *
	 */
	public LoginPage() {
		Reporter.log("Initializing the LoginPage Object...");
		// Check that we're on the right page.
		if (!(driver.findElement(loginBtn).isDisplayed())) {
			logger.error("This is not the login page based upon no Portal Login button presence.");
			throw new IllegalStateException("This is not the login page based upon no Portal Login button presence.");
		}
		Reporter.log("Success - *Initialized* LoginPage.");
	}
	
	public LoginPage(String portalType) {
		if(portalType.equalsIgnoreCase("Portal")){
//			if(!(driver.findElement(By.cssSelector("#ctl00_ContentPlaceHolder1_lnkLogin")).isDisplayed())){
//			logger.error("This is not the login page");
//			throw new IllegalStateException("This is not the login page");
//			}
	}
	}
	
	public void goToSpecificClientLogin(String clientURLHeader){
		driver.get("http://".concat(clientURLHeader).concat(".qa-web01.livehealthier.com/V2/login.aspx"));
	}
	private void setUsername(final String username) {
		Reporter.log("Entering the username in the Element identified by - " + usernameTxt);
		driver.findElement(usernameTxt).clear();
		driver.findElement(usernameTxt).sendKeys(username);
		Reporter.log("Entered the Username as " + username);
	}

	private void setPassword(final String password) {
		Reporter.log("Entering the Password in the Element identified by - "	+ passwordTxt);
		driver.findElement(passwordTxt).clear();
		driver.findElement(passwordTxt).sendKeys(password);
		Reporter.log("Entered the Password as " + password);
	}

	private void submitLogin() {
		Reporter.log("Clicking the Login button identified by - " + usernameTxt);
		driver.findElement(loginBtn).submit();
		Reporter.log("Logging into the application...");
	}

	private void submitAdminLogin(){
		
		driver.findElement(loginAdminBtn).submit();
		Reporter.log("Logging into the application...");
	}
	public void loginAs(final String username, final String password) {
		Reporter.log("Logging into the application...");
		setUsername(username);
		setPassword(password);
		String URL=driver.getCurrentUrl();
		if(!URL.contains("admin"))
		submitLogin();
		else
		submitAdminLogin();	
		
		Reporter.log("Logged into the application...");
	}

	public void clickContactLHUnauthContact() {
		Reporter.log("Clicking the Contact LiveHealthier link identified by - "
				+ contactLivehealthierLnk);
		driver.findElement(contactLivehealthierLnk).click();
		Reporter.log("On the ContactUs page...");
	}

	public void clickPreferences() {
		Reporter.log("Clicking the Preferences link identified by - "
				+ preferencesLnk);
		driver.findElement(preferencesLnk).click();
	}

	public void setUsernameInForgotPassword(final String username) {
		Reporter.log("Entering the username in the element identified by - "
				+ forgotPassUsrnmTxt);
		driver.findElement(forgotPassUsrnmTxt).sendKeys(username);
		Reporter.log("Entered the username as " + username);
	}

	public void clickRetrieveForgotPass(final String username) {
		Reporter.log("Inside the clickRetrieveForgotPass() method for the LoginPage object...");
		SeleniumUtil.sleep(2);
		driver.findElement(forgotPassUsrnmTxt).click();
		setUsernameInForgotPassword(username);
		driver.findElement(forgotPassSubmitBtn).click();
		SeleniumUtil.sleep(2);
		Reporter.log("Exiting the clickRetrieveForgotPass() method for the LoginPage object...");

	}

	public boolean verifyForgotPassErrLbl(final String error) {
		Reporter.log("Inside the verifyForgotPassErrorLbl() method for the LoginPage object...");
		String errMsg = driver.findElement(errForgotPassLbl).getText();
		if (errMsg.equals(error)) {
			logger.error("Exiting the verifyForgotPassErrLbl() method for the LoginPage object...");
			return true;
		}
		logger.error("Error msg does not match. Exiting the verifyForgotPassErrLbl() method for the LoginPage object...");
		return false;
	}
	
	public boolean verifyErrorMessageOFUserOFOtherCLient(final String error) {
		Reporter.log("Inside the verifyErrorMessageOFUserOFOtherCLient() method for the LoginPage object...");
	
		String errMsg = driver.findElement(errMsgUserOfOtherClient).getText().trim();
		
		if (error.equals(errMsg)) {
			logger.error("Exiting the verifyErrorMessageOFUserOFOtherCLient() method for the LoginPage object...");
			return true;
		}
		logger.error("Error msg does not match. Exiting the verifyErrorMessageOFUserOFOtherCLient() method for the LoginPage object...");
		return false;
	}

	private void setSecurityAnswer1(final String answer1) {
		Reporter.log("Entering text in Security Question Answer 1...");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			logger.error("Thread wait stmt failed ", e);;
		}
		driver.findElement(forgotPassSecAns1Txt).sendKeys(answer1);
		Reporter.log("Exiting the setSecurityAnswer1() method for the LoginPage object...");
	}

	private void setSecurityAnswer2(final String answer2) {
		Reporter.log("Entering text in Security Question Answer 2...");
		driver.findElement(forgotPassSecAns2Txt).sendKeys(answer2);
		Reporter.log("Exiting the setSecurityAnswer2() method for the LoginPage object...");
	}

	private void setSecurityAnswer3(final String answer3) {
		Reporter.log("Entering text in Security Question Answer 3...");
		driver.findElement(forgotPassSecAns3Txt).sendKeys(answer3);
		Reporter.log("Exiting the setSecurityAnswer3() method for the LoginPage object...");
	}

	private void submitSecurityAnswers() {
		Reporter.log("Submitting the Answers to security questions...");
		driver.findElement(forgotPassSecAnsSubBtn).click();
		Reporter.log("Exiting the submitSecurityAnswers() method for the LoginPage object...");
	}

	public void submitSecurityAnswers(final String answer1,
			final String answer2, final String answer3) {
		Reporter.log("Inside the submitSecurityAnswers() method of the LoginPage object...");
		setSecurityAnswer1(answer1);
		setSecurityAnswer2(answer2);
		setSecurityAnswer3(answer3);
		submitSecurityAnswers();
		Reporter.log("Exiting the submitSecurityAnswers() method for the LoginPage object...");
	}

	public boolean verifyCreateNewPasswordError(final String errorMsg) {
		Reporter.log("Inside the verifyCreateNewPasswordError() method of the LoginPage object...");
		String currErrorMsg = driver.findElement(forgotPassErrLbl).getText();
		Reporter.log("Verifying the Login Error - " + currErrorMsg);
		if (currErrorMsg.equals(errorMsg)) {
			Reporter.log("Exiting the verifyCreateNewPasswordError() method for the LoginPage object...");
			return true;
		}
		logger.error("Error msg does not match. Exiting the verifyCreateNewPasswordError() method for the LoginPage object...");
		return false;
	}

	public void setNewPassword(final String newPassword) {
		Reporter.log("Inside the setNewPassword() method of the LoginPage object...");
		driver.findElement(forgotPassNewPassTxt).sendKeys(newPassword);
		Reporter.log("Exiting the setNewPassword() method for the LoginPage object...");
	}

	public void setConfirmPassword(final String confirmPassword) {
		Reporter.log("Inside the setConfirmPassword() method of the LoginPage object...");
		driver.findElement(forgotPassConfirmPassTxt).sendKeys(confirmPassword);
		Reporter.log("Exiting the setConfirmPassword() method for the LoginPage object...");
	}

	public void clickNewPasswordSubmit() {
		Reporter.log("Inside the clickNewPasswordSubmit() method of the LoginPage object...");
		driver.findElement(forgotPassCreatePassSubBtn).click();
		Reporter.log("Exiting the clickNewPasswordSubmit() method for the LoginPage object...");
	}

	public void setPassword(final String newPassword,
			final String confirmPassword) {
		Reporter.log("Inside the setPassword() method of the LoginPage object...");
		setNewPassword(newPassword);
		setConfirmPassword(confirmPassword);
		clickNewPasswordSubmit();
		Reporter.log("Exiting the setPassword() method for the LoginPage object...");
	}

	public void clickClosePassUpdatdModal() {
		Reporter.log("Inside the clickClosePassUpdatdModal() method of the LoginPage object...");
		SeleniumUtil.sleep(2);
		SeleniumUtil.clickbyJS(forgotPassNewPassSvdBtn, driver);
		Reporter.log("Exiting the clickClosePassUpdatdModal() method for the LoginPage object...");	
	}

	public void clickForgotPass() {
		Reporter.log("Inside the clickForgotPass() method of the LoginPage object...");
		SeleniumUtil.sleep(3);
		SeleniumUtil.waitForElementToBePresent(forgotPasswordLnk, driver);
		driver.findElement(forgotPasswordLnk).click();
		Reporter.log("Exiting the clickForgotPass() method for the LoginPage object...");
	}

	public void clickCloseForgotPassModal() {
		Reporter.log("Inside the clickCloseForgotPassModal() method of the LoginPage object...");
		driver.findElement(forgotPassCloseBtn).click();
		Reporter.log("Exiting the clickCloseForgotPassModal() method for the LoginPage object...");
	}

	public boolean verifyLoginErr(final String testLoginValidationTxt) {
		Reporter.log("Inside the verifyLoginErr() method of the LoginPage object...");
		String currLoginValidationTxt = driver.findElement(validationErrSummaryLbl).getText();
		Reporter.log("Verifying the Login Error- " + currLoginValidationTxt);
		if (currLoginValidationTxt.equals(testLoginValidationTxt)) {
			Reporter.log("Exiting the verifyLoginErr() method for the LoginPage object...");
			return true;
		}
		logger.error("Error msg does not match. Exiting the verifyLoginErr() method for the LoginPage object...");
		return false;
	}

	public boolean verifyForgotPassModal2Header(
			
			final String forgotPasswordStep2Txt) {
		
		Reporter.log("Inside the verifyForgotPassModal2Header() method of the LoginPage object...");
		
		String currorgotPasswordStep2Txt = driver.findElement(forgotPass2TxtHeader).getText();
		
		Reporter.log("Verifying the Modal dialog for 2nd step of ForgotPassword-  "	+ forgotPass2TxtHeader);
		
		if (currorgotPasswordStep2Txt.equals(forgotPasswordStep2Txt)) {
			
			Reporter.log("Exiting the verifyForgotPassModal2Header() method for the LoginPage object...");
			
			return true;
		}
		
		logger.error("Error msg does not match. Exiting the verifyForgotPassModal2Header() method for the LoginPage object...");
		
		return false;
		
	}
	
	/**
	 * Clicks the ContactLiveHelathier link to open the contact us page.
	 */
	public void clickContactLiveHealthier() {
		
		try{
		
		Reporter.log("Opening the ContactUS page...");
		
		driver.findElement(contactLiveHelathierLink).click();
		}catch(Exception e){
			
			e.printStackTrace();
		}
	}
	
	/**
	 * Clicks the ContactLiveHelathier link to open the contact us page.
	 */
	public void clickRegisterNow () {
		
		Reporter.log("Opening the ContactUS page...");
		
		driver.findElement(registerNowLink).click();
		
	}
	
	public void clickClientLogo(){
		SeleniumUtil.element(clientLogo, driver).click();
	}
	
	public boolean verifyPasswordUpdateModal(){
		SeleniumUtil.sleep(2);
		return SeleniumUtil.element(pwdUpdated, driver).isDisplayed();
	}
	
	public void clickOkayBtn(){
		SeleniumUtil.sleep(1);
		SeleniumUtil.element(okayBtn, driver).click();
	}
	
}
