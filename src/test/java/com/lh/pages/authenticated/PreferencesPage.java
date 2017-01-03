package com.lh.pages.authenticated;

import static com.lh.helper.DriverFactory.driver;

import java.util.ArrayList;

import org.apache.logging.log4j.Logger;import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.lh.testConfig.TestProperty;
import com.lh.utils.UtilityMethods;

/**
 * <h2>This class represents the Preferences page</h2>
 * <p>
 * 
 * @author amita.arya
 * @version 1.0
 * @since 2014-11-17
 */
public class PreferencesPage extends MainPage {

	/**
	 * Logger to log messages for PreferencesPage
	 */
	private static Logger logger = LogManager
			.getLogger(com.lh.pages.unauthenticated.LoginPage.class);
	
	// My information Section
	private By emailLbl 						= By.id("ctl00_ContentPlaceHolder1_LblEmail");
	private By nameLbl 							= By.id("ctl00_ContentPlaceHolder1_LblPrefName");
	private By clientLogoImg 					= By.id("ctl00_ImgLogo");
	private By editNameLnk 						= By.id("EditNameToggle");
	private By cancelEditNameBtn 				= By.id("EditNameCancel");
	private By cancelEditName1Btn 				= By.xpath("//*[@id='EditNameCancel']");
	private By editNameSaveBtn 					= By.id("ctl00_ContentPlaceHolder1_btnPrefName");
	private By userFirstNameTxt 				= By.id("ctl00_ContentPlaceHolder1_PrefFirstName");
	private By userLastNameTxt 					= By.id("ctl00_ContentPlaceHolder1_PrefLastName");
	private By updateEmailLnk 					= By.id("EditEmailToggle");
	private By newEmailTxt 						= By.id("ctl00_ContentPlaceHolder1_PrefEmailAddress");
	private By confirmNewEmailTxt 				= By.id("ctl00_ContentPlaceHolder1_PrefConfirmEmailAddress");
	private By updateEmailSaveBtn 				= By.id("ctl00_ContentPlaceHolder1_btnPrefEmail");
	private By editAliasLnk 					= By.id("EditAliasToggle");
	private By aliasTxt 						= By.id("ctl00_ContentPlaceHolder1_Alias");
	private By cancelEditAliasBtn 				= By.id("EditAliasCance");
	private By saveEditAliasBtn 				= By.id("ctl00_ContentPlaceHolder1_btnPrefAlias");
	private By aliasLbl 						= By.id("ctl00_ContentPlaceHolder1_AliasLabel");
	private By updatePassLnk 					= By.id("EditPassToggle");
	private By updateSecurityQuestionsLnk 		= By.id("EditSecurityQToggle");
	private By updateQues1Sel 					= By.id("ctl00_ContentPlaceHolder1_ddSQ1");
	private By updateQues2Sel 					= By.id("ctl00_ContentPlaceHolder1_ddSQ2");
	private By updateQues3Sel 					= By.id("ctl00_ContentPlaceHolder1_ddSQ3");
	private By updateAns1Txt 					= By.id("ctl00_ContentPlaceHolder1_tbSA1");
	private By updateAns2Txt 					= By.id("ctl00_ContentPlaceHolder1_tbSA2");
	private By updateAns3Txt 					= By.id("ctl00_ContentPlaceHolder1_tbSA3");
	private By updateSecurityQuestionsBtn 		= By.id("ctl00_ContentPlaceHolder1_btnPrefSecurityQ");

	// General Section
	private By changeMessageFormatLnk 			= By.id("EditMessageFormatToggle");
	private By changeMeasurementUnitLnk 		= By.id("EditMeasurementUnitToggle");
	private By changeNotificationsSubscrLnk 	= By.id("EditNotificationsToggle");
	private By notificationsSubscriptionList 	= By
			.id("ctl00_ContentPlaceHolder1_Notifications");
	private By measurementUnitList 				= By
			.id("ctl00_ContentPlaceHolder1_ddMeasurementUnit");
	private By saveNotificationsSubscrBtn 		= By
			.id("ctl00_ContentPlaceHolder1_btnNotifications");
	private By cancelUpdtNotifSubBtn 			= By.id("EditNotificationsCancel");
	private By saveMeasurementUnitsBtn 			= By
			.id("ctl00_ContentPlaceHolder1_btnPrefMeasurementUnit");
	private By cancelUpdtMeasurementUnitsBtn 	= By.id("EditMeasurementUnitCancel");
	private By notificationSubscrLbl 			= By
			.id("ctl00_ContentPlaceHolder1_lblNotifications");
	private By measurementUnitLbl 				= By
			.id("ctl00_ContentPlaceHolder1_lblMeasurementUnit");
	private By changeMsgFormatLnk 				= By.id("EditMessageFormatToggle");
	private By msgFormatList 					= By.id("ctl00_ContentPlaceHolder1_MessageFormat");
	private By saveMsgFormatBtn 				= By.id("ctl00_ContentPlaceHolder1_btnMessagePass");
	private By cancelMsgFormatBtn 				= By.id("EditMessageCancel");
	private By msgFormatLbl 					= By.id("ctl00_ContentPlaceHolder1_lblMsgFormat");
	private By logoutLnk 						= By.id("LogoutLink");
	/**
	 * Webdriver to connect to the current instance of the browser
	 */
	//private WebDriver driver;
	
	/**
	 * One param constructor
	 * 
	 * @param driver
	 *            webdriver instance to connect to the page under test
	 */
	public PreferencesPage() {
		super();
		Reporter.log("Initializing the Preferences Object...");
		
		// Check that we're on the right page.
		if (!(driver.getTitle().equalsIgnoreCase(TestProperty.CLIENT_PORTAL_TITLE))) {
			
			logger.error("This is not the Preferences page");
			throw new IllegalStateException("This is not the Preferences page");
		}
		Reporter.log("Initialized the Preferences page object");
	}
	
	/**
	 * Clicks on the Update button for the Security Questions.
	 */
	private void clickUpdateSecurQuestions() {
		logger.debug("Inside the clickUpdateSecurQuestions() method of the PreferencesPage object...");
		driver.findElement(updateSecurityQuestionsLnk).click();
		logger.debug("Exiting the clickUpdateSecurQuestions() method of the PreferencesPage object...");
	}

	/**
	 * Sets the Security questions
	 * @param question1	The text of the first question.
	 * @param question2 The text of the second question.
	 * @param question3 The text of the third question
	 */
	public void setSecureQuestions(final String question1,
			final String question2, final String question3) {
		logger.debug("Inside the setSecurQuestions() method of the PreferencesPage object...");
		clickUpdateSecurQuestions();

		try {
			driver.findElement(updateQues1Sel).sendKeys(question1);
			Thread.sleep(TestProperty.THREAD_SLEEP);
			driver.findElement(updateQues1Sel).sendKeys(Keys.TAB);
			Thread.sleep(TestProperty.THREAD_SLEEP);
			driver.findElement(updateQues2Sel).sendKeys(question2);
			Thread.sleep(TestProperty.THREAD_SLEEP);
			driver.findElement(updateQues1Sel).sendKeys(Keys.TAB);
			Thread.sleep(TestProperty.THREAD_SLEEP);
			driver.findElement(updateQues3Sel).sendKeys(question3);
			Thread.sleep(TestProperty.THREAD_SLEEP);
			driver.findElement(updateQues1Sel).sendKeys(Keys.TAB);
		} catch (InterruptedException e) {
			logger.error("Interrupted exception is thrown...", e);
		}
		logger.debug("Exiting the setSecurQuestions() method of the PreferencesPage object...");
	}

	/**
	 * Sets the answers to the security questions
	 * @param answer1 Answer for the first security question
	 * @param answer2 Answer for the second security question
	 * @param answer3 Answer for the third security question
	 */
	public void setSecureQuestAns(String answer1, String answer2, String answer3) {
		Reporter.log(("Setting the following -" + answer1 + " and "
				+ answer2 + " and " + answer3));
		logger.debug("Inside the setSecurQuestAns() method of the PreferencesPage object...");
		driver.findElement(updateAns1Txt).clear();
		driver.findElement(updateAns1Txt).sendKeys(answer1);
		driver.findElement(updateAns2Txt).clear();
		driver.findElement(updateAns2Txt).sendKeys(answer2);
		driver.findElement(updateAns3Txt).clear();
		driver.findElement(updateAns3Txt).sendKeys(answer3);
		clickSaveUpdateSecurQues();
		logger.debug("Exiting the setSecurQuestAns() method of the PreferencesPage object...");
	}

	/**
	 * Clicks on the save button for updation the security questions and answers
	 */
	private void clickSaveUpdateSecurQues() {
		logger.debug("Inside the clickSaveUpdateSecQues() method of the PreferencesPage object...");
		driver.findElement(updateSecurityQuestionsBtn).click();
		logger.debug("Exiting the clickSaveUpdateSecQues() method of the PreferencesPage object...");
	}

	/**
	 * Reads the security questions set for the user
	 * @return Array of String with the security questions set in the application
	 */
	public String[] getSecurQuestions() {
		clickUpdateSecurQuestions();
		logger.debug("Inside the getSecurQuestions() method of the PreferencesPage object...");
		String answerArray[] = {
				UtilityMethods
						.getSelectText(driver.findElement(updateQues1Sel)),
				UtilityMethods
						.getSelectText(driver.findElement(updateQues2Sel)),
				UtilityMethods
						.getSelectText(driver.findElement(updateQues3Sel)) };
		logger.debug("Exiting the getSecurQuestions() method for the PreferencesPage object...");
		return answerArray;
	}

	/**
	 * Reads the answers to security questions set for the user
	 * @return Array of String with the answers set in the application
	 */
	public String[] getSecurQuestAns() {
		logger.debug("Inside the getSecurQuestAns() method of the PreferencesPage object...");
		String[] questionsAnsArray = {
				driver.findElement(updateAns1Txt).getAttribute("value"),
				driver.findElement(updateAns2Txt).getAttribute("value"),
				driver.findElement(updateAns3Txt).getAttribute("value") };
		clickSaveUpdateSecurQues();
		logger.debug("Exiting the getSecurQuestAns() method for the PreferencesPage object...");
		return questionsAnsArray;
	}

	/**
	 * Verifies the Security questions set in the application
	 * @param question1 Expected security question 1
	 * @param question2 Expected security question 1
	 * @param question3 Expected security question 1
	 * @return
	 */
	public boolean verifySecurityQuestions(final String question1,
			final String question2, final String question3) {
		logger.debug("Inside the verifySecurityQuestions() method of the PreferencesPage object...");
		try {
			Thread.sleep(TestProperty.THREAD_SLEEP);
		} catch (InterruptedException e) {
			logger.error("InterruptedException is found and the test is halting...", e);
		}
		clickUpdateSecurQuestions();
		String currentQuestion1 = UtilityMethods.getSelectText(driver
				.findElement(updateQues1Sel));
		String currentQuestion2 = UtilityMethods.getSelectText(driver
				.findElement(updateQues2Sel));
		String currentQuestion3 = UtilityMethods.getSelectText(driver
				.findElement(updateQues3Sel));
		clickSaveUpdateSecurQues();

		if (currentQuestion1.equals(question1)
				&& currentQuestion2.equals(question2)
				&& currentQuestion3.equals(question3)) {
			logger.debug("Exiting the verifySecurityQuestions() method for the PreferencesPage object...");
			return true;
		}
		
		Reporter.log("Error msg does not match. Exiting the verifySecurityQuestions() method for " + "the PreferencesPage object...");
		
		return false;
	}

	/**
	 * Clicks on the Client portal logo to navigate to the home page
	 */
	public void clickClientLogo() {
		
		Reporter.log("Clicking on the Client logo image identified by -" + clientLogoImg);
		
		driver.findElement(clientLogoImg).click();
		
		Reporter.log("Clicked on the Client logo image to navigate to index page");
	}

	/**
	 * Clicks on the Edit Name Link
	 */
	private void clickEditName() {
		
		Reporter.log("Clicking on the Edit Name link identified by - " + editNameLnk);
		
		driver.findElement(editNameLnk).click();
		
		Reporter.log("Name fields are available for editing now...");
	}

	/**
	 * Updates the first name of the user to be set
	 * @param firstname first name of the User.
	 */
	private void setFirstName(String firstname) {
		Reporter.log("Entering the First Name in the element identified by - "
				+ userFirstNameTxt);
		driver.findElement(userFirstNameTxt).sendKeys(firstname);
		Reporter.log("Entered the First Name as " + firstname);
	}

	/**
	 * Updates the first name of the user to be set
	 * @param lastname Last name of the User.
	 */
	private void setLastName(String lastname) {
		Reporter.log("Entering the Last Name in the element identified by - "
				+ userFirstNameTxt);
		driver.findElement(userLastNameTxt).sendKeys(lastname);
		Reporter.log("Entered the Last Name as " + lastname);

	}

	/**
	 * Clicks on the Save button for saving the updated name of the user
	 */
	private void clickSaveEditName() {
		Reporter.log("Clicking on the Save button for Edit Name identified by - "
				+ editNameSaveBtn);
		driver.findElement(editNameSaveBtn).click();
		Reporter.log("Updated name is saved");
	}

	/**
	 * Sets the name of the user
	 * @param firstName First name of the User.
	 * @param lastName Last name of the User.
	 */
	public void setName(String firstName, String lastName) {
		clickEditName();
		setFirstName(firstName);
		setLastName(lastName);
		clickSaveEditName();
	}

	/**
	 * Gets the full name of the user
	 * @return full name of the user appearing on the preferences page.
	 */
	public String[] getName() {
		return driver.findElement(nameLbl).getText().split(" ");
	}

	/**
	 * Clicks on the Cancel button to cancel the Edit name.
	 */
	public void clickCancelEditName(){
		Reporter.log("Clicking on the Cancel button for Edit Name identified by - "
				+ cancelEditName1Btn);
		driver.findElement(cancelEditName1Btn);
		Reporter.log("Edit Name is cancelled");
	}

	/**
	 * Clicks on the Update button for updating the email
	 */
	private void clickUpdateEmail() {
		Reporter.log("Clicking on the Edit Email link identified by - "
				+ updateEmailLnk);
		driver.findElement(updateEmailLnk).click();
		Reporter.log("New Email field is available for editing now...");
	}

	/**
	 * Sets new email fort he user ont he preference page.
	 * @param newEmail email address of the user to be set
	 */
	private void setNewEmail(String newEmail) {
		Reporter.log("Entering the New Email in the element identified by - "
				+ newEmailTxt);
		driver.findElement(newEmailTxt).sendKeys(newEmail);
		Reporter.log("Entered the New Email as " + newEmail);
	}

	/**
	 * Confirms the new email address of the user.
	 * @param confirmNewEmail email address of the user to be set.
	 */
	private void setConfirmNewEmail(String confirmNewEmail) {
		Reporter.log("Entering the New Email in the element identified by - "
				+ confirmNewEmailTxt);
		driver.findElement(confirmNewEmailTxt).sendKeys(confirmNewEmail);
		Reporter.log("Entered the Confirmed New Email as " + confirmNewEmail);
	}

	/**
	 * Clicks on the Save button for the email updation.
	 */
	private void clickSaveUpdateEmail() {
		Reporter.log("Clicking on the Save button for Edit Email identified by - "
				+ updateEmailSaveBtn);
		driver.findElement(updateEmailSaveBtn).click();
		Reporter.log("Updated email is saved");
	}

	/**
	 * Sets the email for the user.
	 * @param email email to be set for the user.
	 */
	public void setEmail(String email) {
		logger.debug("Inside the setEmail() method of the PreferencesPage object...");
		clickUpdateEmail();
		setNewEmail(email);
		setConfirmNewEmail(email);
		clickSaveUpdateEmail();
		logger.debug("Exiting the setEmail() method for the PreferencesPage object...");
	}

	/**
	 * returns the email set for the user.
	 * @return the current email set for the user.
	 */
	public String getEmail() {
		logger.debug("Inside the getEmail() method of the PreferencesPage object...");
		return driver.findElement(emailLbl).getText();
	}

	/**
	 * Verifies the email set for the user
	 * @param email the user email expected. 
	 * @return  boolean.
	 */
	public boolean verifyEmail(String email) {
		logger.debug("Inside the verifyEmail() method of the PreferencesPage object...");
		String currentEmail = driver.findElement(emailLbl).getText();
		if (currentEmail.equals(email)) {
			logger.debug("Exiting the verifyEmail() method for the PreferencesPage object...");
			return true;
		}
		logger.error("Error msg does not match. Exiting the verifyEmail() method for the PreferencesPage object...");
		return false;
	}

	/**
	 * Clicks on the update password button.
	 */
	public void clickPassword() {
		logger.debug("Inside the clickPassword() method of the PreferencesPage object...");
		driver.findElement(updatePassLnk).click();
		logger.debug("Exiting the clickPassword() method for the PreferencesPage object...");
	}

	/**
	 * Clicks on Update link for setting Security Questions.
	 */
	public void clickUpdateSecurityQuestions() {
		logger.debug("Inside the clickSecurityQuestions() method of the PreferencesPage object...");
		driver.findElement(updateSecurityQuestionsLnk).click();
		logger.debug("Exiting the clickSecurityQuestions() method for the PreferencesPage object...");
	}

	/**
	 * Clicks on change Message Format link.
	 */
	public void clickMessageFormat(){
		logger.debug("Inside the clickMessageFormat() method of the PreferencesPage object...");
		driver.findElement(changeMessageFormatLnk).click();
		logger.debug("Exiting the clickMessageFormat() method for the PreferencesPage object...");
	}

	/**
	 * Clicks on change Measurement Unit link.
	 */
	private void clickMeasurementUnit(){
		logger.debug("Inside the clickMeasurementUnit() method of the PreferencesPage object...");
		driver.findElement(changeMeasurementUnitLnk).click();
		logger.debug("Exiting the clickMeasurementUnit() method for the PreferencesPage object...");
	}

	/**
	 * Clicks on change Notifications Subscription link.
	 */
	private void clickSubscriptionsNotification() {
		logger.debug("Inside the clickSubscriptionsNotification() method of the PreferencesPage object...");
		try {
			Thread.sleep(TestProperty.THREAD_SLEEP);
		} catch (InterruptedException e) {
			logger.error("InterruptedException is found and the test is halting...", e);
		}
		driver.findElement(changeNotificationsSubscrLnk).click();
		logger.debug("Exiting the clickSubscriptionsNotification() method for the PreferencesPage object...");
	}

	/**
	 * Sets the desired Notification Subscription option
	 * @param notificationsSubscription To set Notification Subscription 
	 */
	public void setNotificationsSubscription(String notificationsSubscription)
			{
		logger.debug("Inside the setNotificationsSubscription() method of the PreferencesPage object...");
		clickSubscriptionsNotification();
		driver.findElement(notificationsSubscriptionList).sendKeys(
				notificationsSubscription);
		try {
			Thread.sleep(TestProperty.THREAD_SLEEP);
		} catch (InterruptedException e) {
			logger.error("InterruptedException is found and the test is halting...", e);
		}
		driver.findElement(saveNotificationsSubscrBtn).click();
		logger.debug("Exiting the setNotificationsSubscription() method for the PreferencesPage object...");
	}

	/**
	 * Reads the measurement unit set for the user.
	 * @return Measurement unit.
	 */
	public String getMeasurementUnits() {
		logger.debug("Inside the getMeasurementUnits() method of the PreferencesPage object...");
		String currentMUnit = driver.findElement(measurementUnitLbl).getText();
		logger.debug("Exiting the getMeasurementUnits() method for the PreferencesPage object...");
		return currentMUnit;
	}

	/**
	 * Reads the value of the Notification Subscription set.
	 * @return Notification subscription.
	 */
	public String getNotificationsSubscription() {
		logger.debug("Inside the getNotificationsSubscription() method of the PreferencesPage object...");
		return driver.findElement(notificationSubscrLbl).getText();
	}

	/**
	 * Sets the Measurement Unit.
	 * @param measurementUnit desired Measurement unit to be set.
	 */
	public void setMeasurementUnits(String measurementUnit){
		logger.debug("Inside the setMeasurementUnits() method of the PreferencesPage object...");
		clickMeasurementUnit();
		try {
			Thread.sleep(TestProperty.THREAD_SLEEP);
		} catch (InterruptedException e) {
			logger.error("InterruptedException is found and the test is halting...", e);
		}
		driver.findElement(measurementUnitList).sendKeys(measurementUnit);
		driver.findElement(saveMeasurementUnitsBtn).click();
		logger.debug("Exiting the setMeasurementUnits() method for the PreferencesPage object...");
	}

	/**
	 * Verifies the Measurement unit set for the user.
	 * @param measurementUnit expected Measurement unit.
	 * @return boolean
	 */
	public boolean verifyMeasurementUnits(String measurementUnit) {
		logger.debug("Inside the verifyMeasurementUnits() method of the PreferencesPage object...");
		String currentMUnit = driver.findElement(measurementUnitLbl).getText();
		if (currentMUnit.equals(measurementUnit)) {
			logger.debug("Exiting the verifyMeasurementUnits() method for the PreferencesPage object...");
			return true;
		}
		Reporter.log("Error msg does not match. Exiting the verifyMeasurementUnits() method for the PreferencesPage object...");
		return false;
	}

	/**
	 * Verify the Notification subscription set for the user.
	 * @param notificationsSubscription expected Notification Subscription.
	 * @return boolean
	 */
	public boolean verifyNotificationSub(String notificationsSubscription) {
		logger.debug("Inside the verifyNotificationSub() method of the PreferencesPage object...");
		String currentNotSub = driver.findElement(notificationSubscrLbl)
				.getText();
		if (currentNotSub.equals(notificationsSubscription)) {
			logger.debug("Exiting the verifyNotificationSub() method for the PreferencesPage object...");
			return true;
		}
		logger.error("Error msg does not match. Exiting the verifyNotificationSub() method for the PreferencesPage object...");
		return false;
	}

/**
 * Verify the Name of the user set on the preferences page.
 * @param name expected name of the user.
 * @return boolean
 */
	public boolean verifyName(String name) {
		logger.debug("Inside the verifyName() method of the PreferencesPage object...");
		String currentName = driver.findElement(nameLbl).getText();
		if (currentName.equals(name)) {
			logger.debug("Exiting the verifyName() method for the PreferencesPage object...");
			return true;
		}
		logger.debug("Exiting the submitSecurityAnswers() method for the PreferencesPage object...");
		return false;
	}

	/**
	 * Clicks on the logout link.
	 */
	public void clickLogout() {
		logger.debug("Inside the clickLogout() method of the PreferencesPage object...");
		driver.findElement(logoutLnk).click();
		logger.debug("Exiting the clickLogout() method for the PreferencesPage object...");
	}

	/**
	 * Verifies the user name of the user appearing on the preference page.
	 * @param userName expected user name of the user.
	 * @return boolean
	 */
	public boolean verifyUsername(String userName) {
		logger.debug("Inside the verifyCreateNewPasswordError() method of the PreferencesPage object...");

		return false;
	}

	/**
	 * Clicks on the Edit Alias link.
	 */
	public void clickEditAlias() {
		logger.debug("Inside the clickEditAlias() method of the PreferencesPage object...");
		driver.findElement(editAliasLnk).click();
		logger.debug("Exiting the clickEditAlias() method for the PreferencesPage object...");
	}

	/**
	 * Updates the Alias.
	 * @param alias Alias to be set for the user.
	 */
	public void setNewAlias(final String alias) {
		logger.debug("Inside the setNewAlias() method of the PreferencesPage object...");
		driver.findElement(aliasTxt).sendKeys(alias);
		logger.debug("Exiting the setNewAlias() method for the PreferencesPage object...");
	}

	/**
	 * Clicks on the Save button to save the updated Alias.
	 */
	public void saveNewAlias() {
		logger.debug("Inside the saveNewAlias() method of the PreferencesPage object...");

		driver.findElement(saveEditAliasBtn).click();
		try {
			Thread.sleep(TestProperty.THREAD_SLEEP);
		} catch (InterruptedException e) {
			logger.error("InterruptedException is found and the test is halting...", e);
		}
		logger.debug("Exiting the saveNewAlias() method for the PreferencesPage object...");
	}

	/**
	 * Updates the alias of the user on the preferences page.
	 * @param alias alias to be set.
	 */
	public void setAlias(final String alias) {
		logger.debug("Inside the setAlias() method of the PreferencesPage object...");
		clickEditAlias();
		setNewAlias(alias);
		saveNewAlias();
		logger.debug("Exiting the setAlias() method for the PreferencesPage object...");
	}

	/**
	 * Verify the alias of the user.
	 * @param alias expected alias.
	 * @return boolean
	 */
	public boolean verifyAlias(final String alias) {
		logger.debug("Inside the verifyAlias() method of the PreferencesPage object...");
		String currentAlias = driver.findElement(aliasLbl).getText();
		Reporter.log(currentAlias + "----" + alias);
		if (currentAlias.equals(alias)) {
			logger.debug("Exiting the verifyAlias() method for the PreferencesPage object...");
			return true;
		}
		Reporter.log("Error msg does not match. Exiting the verifyAlias() method for the PreferencesPage object...");
		return false;
	}

	/**
	 * Reads the alias set for the user on the preferences page.
	 * @return Alias appearing on the preferences page.
	 */
	public String getAlias() {
		logger.debug("Inside the getAlias() method of the PreferencesPage object...");
		String currentAlias = driver.findElement(aliasLbl).getText();
		logger.debug("Exiting the getAlias() method for the PreferencesPage object and returning text as- "
				+ currentAlias);
		return currentAlias;
	}

	/**
	 * Verifies the list of Measurement Unit options appearing on the preferences page.
	 * @param measurementUnitOptList Options which should appear in the Measurement unit options.
	 * @return boolean
	 */
	public boolean verifyMeasureUnitOptions(String measurementUnitOptList) {
		logger.debug("Inside the verifyMeasureUnitOptions() method of the PreferencesPage object to verify the measurement units - "
				+ measurementUnitOptList);
		clickMeasurementUnit();
		
		String[] measurementUnitsStr = measurementUnitOptList.split(",");
		WebElement selectElement = driver.findElement(measurementUnitList);
		ArrayList<String> currMeasurementUnitsList = UtilityMethods
				.getSelectOptions(selectElement);
		
		ArrayList<String> measurementUnitsList = UtilityMethods.convertStringArrToArrList(measurementUnitsStr);
		boolean isArrEqual =UtilityMethods.compareTwoList(currMeasurementUnitsList, measurementUnitsList) ;
		
		Reporter.log("The the comparison of the two list has returned- "+ isArrEqual);
		driver.findElement(cancelUpdtMeasurementUnitsBtn).click();
		logger.debug("Exiting the verifyMeasureUnitOptions() method of the PreferencesPage object...");
		return isArrEqual;
	}
	
	/**
	 * Verifies the list of Notification Subscription options appearing on the preferences page.
	 * @param notificationSubOptList Options which should appear in the Notification Subscription options.
	 * @return boolean
	 */
	public boolean verifyNotificationSubOptions(String notificationSubOptList) {
		logger.debug("Inside the verifyMeasureUnitOptions() method of the PreferencesPage object to verify the measurement units - "
				+ notificationSubOptList);
		clickSubscriptionsNotification();
		String[] notificationSubStr = notificationSubOptList.split(",");
		WebElement selectElement = driver.findElement(notificationsSubscriptionList);
		ArrayList<String> currNotificationSubList = UtilityMethods
				.getSelectOptions(selectElement);
		ArrayList<String> notificationSubList = UtilityMethods.convertStringArrToArrList(notificationSubStr);
		boolean isArrEqual =UtilityMethods.compareTwoList(currNotificationSubList, notificationSubList) ;
		Reporter.log("The the comparison of the two list for notification subscription options has returned- "+ isArrEqual);
		driver.findElement(cancelUpdtNotifSubBtn).click();
		logger.debug("Exiting the verifyMeasureUnitOptions() method of the PreferencesPage object...");
		return isArrEqual;
	}
	
	/**
	 * Verifies the list of Msg format options appearing on the preferences page.
	 * @param msgFormatOptList Msg format Options which should appear in the Notification Subscription options.
	 * @return
	 */
	public boolean verifyMsgFormatOptions(String msgFormatOptList){
		logger.debug("Inside the verifyMsgFormatOptions() method of the PreferencesPage object to verify the measurement units - "
				+ msgFormatOptList);
		clickUpdateMsgFormat();
		String[] msgFormatStr = msgFormatOptList.split(",");
		WebElement selectElement = driver.findElement(msgFormatList);
		ArrayList<String> currMsgFormatList = UtilityMethods
				.getSelectOptions(selectElement);
		ArrayList<String> notificationSubList = UtilityMethods.convertStringArrToArrList(msgFormatStr);
		boolean isArrEqual =UtilityMethods.compareTwoList(currMsgFormatList, notificationSubList) ;
		
		Reporter.log("The the comparison of the two list for message format options has returned- "+ isArrEqual);
		driver.findElement(cancelMsgFormatBtn).click();
		logger.debug("Exiting the verifyMeasureUnitOptions() method of the PreferencesPage object...");
		return isArrEqual;
	}
	
	/**
	 * Clicks on update button to update the message format option.
	 */
	public void clickUpdateMsgFormat() {
		logger.debug("Inside the clickUpdateMsgFormat() method of the PreferencesPage object...");
		try {
			Thread.sleep(TestProperty.THREAD_SLEEP);
		} catch (InterruptedException e) {
			logger.error("InterruptedException is found and the test is halting...", e);
		}
		driver.findElement(changeMsgFormatLnk).click();
		logger.debug("Exiting the clickUpdateMsgFormat() method for the PreferencesPage object...");
	}

}
