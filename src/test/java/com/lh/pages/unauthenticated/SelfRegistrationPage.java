package com.lh.pages.unauthenticated;

import java.util.ArrayList;
import static com.lh.helper.DriverFactory.driver;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.testng.Reporter;
import com.lh.testConfig.TestProperty;
import com.lh.utils.SeleniumUtil;

/**
 * <h2>This is the Self Registration page Class for the Unauthenticated user</h2>
 * <p>
 * 
 * @author amita.arya
 * @version 1.0
 * @since 2015-02-20
 */

public class SelfRegistrationPage extends LoginPage {
	
	/** Logger for the Login class */
	private static Logger logger 							= LogManager.getLogger(SelfRegistrationPage.class);
	private By userExternalID 								= By.id("ctl00_modalBodyContent_rptFields_ctl00_txtFieldValue");
	private By nextBtn 										= By.id("nextButton");
	private By emailTxt 									= By.id("SelfRegistrationEmail");
	private By passwordTxt 									= By.id("password");
	private By confirmPasswordTxt 							= By.id("passwordConfirm");
	private By securityQuestion1Sel							= By.id("ddlistSecurityQuestionlist1");
	private By securityQuestion2Sel							= By.id("ddlistSecurityQuestionlist2");
	private By securityQuestion3Sel							= By.id("ddlistSecurityQuestionlist3");
	private By securityQuestionAnswer1txt					= By.id("SecurityAnswer1");
	private By securityQuestionAnswer2txt					= By.id("SecurityAnswer2");
	private By securityQuestionAnswer3txt					= By.id("SecurityAnswer3");
	private By registerBtn 									= By.id("submitButton");	
	private By createAccountMessage							= By.xpath("//*[@id='modalForm']/p[@class='articletext']");
	private By okayBtn										= By.id("OkButton");
	private By errorMsg										= By.xpath("//*[@id='errorContainer']/p");
	private By closeAccountCreationModal					= By.xpath("//*[@id='ctl00_bodyMaster']/div[3]/div[1]/button[@title='close']");
	private By modalbox										= By.xpath("//div[@class='modal-right']");
	
	/**
	 * One param constructor
	 * 
	 * @param driver
	 *            webdriver instance to connect to the page under test
	 */
	public SelfRegistrationPage() {
		super();
		Reporter.log("Initializing the SelfRegistrationPage Object...");
		// Check that we're on the right page.
		if (!(driver.getTitle().equalsIgnoreCase(TestProperty.CLIENT_PORTAL_TITLE))) {
			logger.error("This is not the Self Registration page");
			throw new IllegalStateException(
					"This is not the SelfRegistrationPage page");
		}
		Reporter.log("Initialized the SelfRegistrationPage page object");
	}
	
	
	/**
	 * Sets the externalID of the userr in the Setp 1 of the Create Account Modal.
	 * @param externalID the external ID of the user.
	 */
	private void setExternalID(String externalID) {
		SeleniumUtil.sleep(3);
		externalID = externalID.trim();
		
		if (!externalID.isEmpty()) {
			
			driver.findElement(userExternalID).sendKeys(externalID);
			
		}

	}
	
	/**
	 * Sets the user email in the second step of the create account modal.
	 * @param userEmail email of the user.
	 */
	private void setEmail(String userEmail) {

		userEmail = userEmail.trim();

		if (!userEmail.isEmpty()) {
			driver.findElement(emailTxt).click();
			driver.findElement(emailTxt).sendKeys(userEmail);

		}

		Reporter.log("Setting the useremail in the self registeration page");
		
	}
	
	
	/**
	 * Sets the password for the user on the second step of the Create Account Modal.
	 * @param password password to be set.
	 */
	private void setPassword(String password) {

		password = password.trim();

		if (!password.isEmpty()) {

			driver.findElement(passwordTxt).sendKeys(password);

			driver.findElement(confirmPasswordTxt).sendKeys(password);

		}

		Reporter.log("Setting the password in the self registration page");
	}

	
	/**
	 * Sets the three security questions for the user.
	 * @param securityQuestions Three security questions to be set for the user.
	 */
	private void setSecurityQuestions(ArrayList<String> securityQuestions) {

		if (securityQuestions.size()==3) {
			String securityQuestion1 = securityQuestions.get(0);
			String securityQuestion2 = securityQuestions.get(1);
			String securityQuestion3 = securityQuestions.get(2);

			if (!(securityQuestion1.isEmpty() || securityQuestion2.isEmpty() || securityQuestion3.isEmpty())) {

				driver.findElement(securityQuestion1Sel).sendKeys(securityQuestion1.trim());

				driver.findElement(securityQuestion2Sel).sendKeys(securityQuestion2.trim());

				driver.findElement(securityQuestion3Sel).sendKeys(securityQuestion3.trim());

				Reporter.log("Setting the userEmail in the self registeration page");


			}
		} else {
			
			logger.error("All the three security questions are not provided!");
		}

	}
	
	
	/**
	 * Sets the three answer to the security questions for the user.
	 * @param securityQuestionAnswers Answers to the security questions to be set for the user .
	 */
	private void setSecurityQuestionsAnswer(ArrayList<String> securityQuestionAnswers){

		if (securityQuestionAnswers.size() == 3) {
			
			String securityQuestionAnswer1 = securityQuestionAnswers.get(0);
			String securityQuestionAnswer2 = securityQuestionAnswers.get(1);
			String securityQuestionAnswer3 = securityQuestionAnswers.get(2);

			if (!(securityQuestionAnswer1.isEmpty() || securityQuestionAnswer2.isEmpty() || securityQuestionAnswer3.isEmpty())) {

				driver.findElement(securityQuestionAnswer1txt).clear();
				driver.findElement(securityQuestionAnswer1txt).sendKeys(securityQuestionAnswer1.trim());

				driver.findElement(securityQuestionAnswer2txt).clear();
				driver.findElement(securityQuestionAnswer2txt).sendKeys(securityQuestionAnswer2.trim());

				driver.findElement(securityQuestionAnswer3txt).clear();
				driver.findElement(securityQuestionAnswer3txt).sendKeys(securityQuestionAnswer3.trim());
				
				Reporter.log("Setting the answers to the security questions in the self registeration page");
			}
		
		} else {
			
			logger.error("All the required three answers to the security questions were not provided!");
			
		}

	}
	
	
	/**
	 * Clicks on the Next button to move to the second step of the Create Account Modal.
	 */
	private void clickNext(){
		
		driver.findElement(nextBtn).click();
		
		Reporter.log("Setting the useremail in the self registeration page");
		
	}
	
	
	/**
	 * Click on the Register button to complete the account creation.
	 */
	private void clickRegister(){
		
		driver.findElement(registerBtn).click();
		
		Reporter.log("Setting the useremail in the self registeration page");
		
	}
	
	
	/**
	 * Complete the self registration step 1 for the user.
	 * @param externalID external id of the user.
	 */
	public void completeSelfRegistrationStep1(String externalID){
		
		setExternalID(externalID);
		clickNext();
		
		Reporter.log("Completed the first step of self registeration.");
		
	}
	
	
	/**
	 * Complete the self registration Step 2 for the user.
	 * @param userEmail
	 * @param password 
	 * @param securityQuestions
	 * @param securityQuestionAnswers
	 */
	public void completeSelfRegistrationStep2 (String userEmail, String password, ArrayList<String> securityQuestions, ArrayList<String> securityQuestionAnswers ){
		
		
		setEmail(userEmail);
		setPassword(password);
		setSecurityQuestions(securityQuestions);
		setSecurityQuestionsAnswer(securityQuestionAnswers);
		clickRegister();
				
		Reporter.log("Completed the first step of self registeration.");
		
	}
	
	
	/**
	 * Verifies that the message appearing on the account creation modal matches with the expected error message.
	 * @param errorMessage
	 * @return true/false depending on whether the message match
	 */
	public boolean verifyAccountCreationErrorMsg (String errorMessage){
		SeleniumUtil.sleep(3);
		errorMessage = errorMessage.trim();

		String currentMessage = driver.findElement(errorMsg).getText().trim();
		
		Reporter.log("The current error message appearing on the account creation page is :" + currentMessage + ".");
		Reporter.log("The error message expected on the account creation page is :" + errorMessage + ".");

		if (!errorMessage.trim().isEmpty()) {

			if (errorMessage.equals(currentMessage)) {

				Reporter.log("The self registeration account creation error message matches with the expected message");

				return true;
			} else {

				logger.error("The success message does not match the expected error message for self registration account creation.");
			}

		} else {

			logger.error("The provided error message is empty.");

		}

		return false;

	}
	
	
	/**
	 * Verifies that the message appearing on the account creation modal matches with the expected error message.
	 * @param message
	 * @return true/false depending on whether the message match
	 */
	public boolean verifyAccountCreationMessage(String message) {
		
		message = message.trim();

		String currentMessage = driver.findElement(createAccountMessage).getText().trim();
		
		Reporter.log("The current message appearing on the account creation page is :" + currentMessage + ".");
		Reporter.log("The message expected on the account creation page is :" + message + ".");

		if (!message.trim().isEmpty()) {

			if (message.equals(currentMessage)) {

				Reporter.log("The self registeration account creation message matches with the expected message");

				return true;
				
			} else {

				logger.error("The success message does not match the expected message for self registeration account creation.");
			}

		} else {

			logger.error("The provided message is empty.");

		}

		return false;

	}
	
	/**
	 * Clicks on Ok button.
	 */
	public void clickOkey () {
		
		SeleniumUtil.click(modalbox, driver);
		SeleniumUtil.click(okayBtn, driver);
		
		Reporter.log("Clicked on the OK button to close the self registeration modal");
		
	}
	
	/**
	 * Closes the Account creation modal.
	 */
	public void closeAccountCreationModal(){
		
		SeleniumUtil.isElementDisplayed(closeAccountCreationModal, driver);
		SeleniumUtil.click(closeAccountCreationModal,driver);
	}

}
