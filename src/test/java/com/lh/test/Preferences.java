package com.lh.test;

import java.lang.reflect.Method;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import com.lh.pages.authenticated.PreferencesPage;
import com.lh.test.base.AuthBaseTestClass;

/**
 * <h2>Define test for Preferences page here!
 * </h2> All the tests for the Preferences page are defined here
 * <p> 
 * @author amita.arya
 * @version 2.0
 * @since 2014-11-17
 */
@Listeners(com.lh.helper.LHTestListener.class)
public class Preferences extends AuthBaseTestClass {

	private static final String VALID_PASSWORD = "validPassword";
	private static final String VALID_USERNAME = "validUsername";
	private static final String SECURITY_QUESTION3_ANS = "securityQuestion3Ans";
	private static final String SECURITY_QUESTION2_ANS = "securityQuestion2Ans";
	private static final String SECURITY_QUESTION1_ANS = "securityQuestion1Ans";
	private static final String SECURITY_QUESTION3 = "securityQuestion3";
	private static final String SECURITY_QUESTION2 = "securityQuestion2";
	private static final String SECURITY_QUESTION1 = "securityQuestion1";
	private static final String MSG_FORMAT_LIST = "msgFormatList";
	private static final String MEASUREMENT_UNIT_LIST = "measurementUnitList";
	private static final String NOTIFICATION_SUB_LIST = "notificationSubList";
	private static final String ALIAS = "alias";
	private static final String CURR_ALIAS = "currAlias";
	private static final String MEASUREMENT_UNIT = "measurementUnit";
	private static final String NOTIFICATION_SUBSCRIPTION = "notificationSubscription";
	private static final String EMAIL = "email";
	private static final String CUR_EMAIL = "curEmail";
	private static final String CUR_LAST_NAME = "curLastName";
	private static final String CUR_FIRST_NAME = "curFirstName";
	private static final String LAST_NAME = "lastName";
	private static final String FIRST_NAME = "firstName";
	/** Logger for the Preferences class */
	private static Logger logger = LogManager.getLogger(Preferences.class);
	/** PreferencesPage reference used run the tests */
	private PreferencesPage preferencesPageObj;
	/** Contains the login data */
	private Map<String, String> loginData;
	/** Contains the preferences data */
	private Map<String, String> preferencesData;
	/** To verify the test outcome */
	private Assertion preferencesAssert = new Assertion();
	private String partialPreferences_URL = "MemberServices/Preferences.aspx";
	/** This method runs before the first test from the class runs  */
	@BeforeClass
	public void initClass() {
		logger.info("Inside the initClass() method for Preferences class...");
		loginData = readexcelsheet("Login");
		preferencesData = readexcelsheet("Preferences");
		logger.info("Exiting the initClass() method for Preferences class \n\n");
		preferencesPageObj = new PreferencesPage();
	}
	
 	@BeforeMethod
 	public void init(Method method) {
 		logger.info("Inside the init() method Call the loginPageObj.loginAs(username, password) method");
 		doLogin(loginData.get(VALID_USERNAME), loginData.get(VALID_PASSWORD),"Portal","Portal");
 		preferencesPageObj.clickPreferencesLink();
 		preferencesPageObj.verifyURL(partialPreferences_URL);
 		logger.info("Exiting the init() method for Preferences class \n");

 	}

	/*/**
	 * Test to verify that the user's username or email address is displayed to
	 * the left of the Username field.
	 */
	/*@Test(description = "Verify that the user's username or email address is displayed to the left "
			+ "of the Username field.", groups = {"smoke"})*/
	public void verifyUsername() {	
		preferencesAssert.assertTrue(preferencesPageObj.verifyUsername(preferencesData.get(VALID_USERNAME)));
	}

	/**
	 * Verify that Edit Name works by editing the username and viewing the new 
	 * name on the preferences page and on the banner of the index page
	 */
	@Test(description = "Verify that Edit Name works by editing the username and viewing the new"
			+ "name on the preferences page and on the banner of the index page", groups = {"smoke"})
	public void updateName(){
		String[] curName = preferencesPageObj.getName();
		preferencesData.put(CUR_FIRST_NAME, curName[0]);
		preferencesData.put(CUR_LAST_NAME, curName[1]);

		preferencesPageObj.setName(preferencesData.get(FIRST_NAME), preferencesData.get(LAST_NAME));
		preferencesPageObj.verifyName(preferencesData.get(FIRST_NAME) + " " + preferencesData.get(LAST_NAME));
		preferencesPageObj.clickClientLogo();
		preferencesAssert.assertTrue(preferencesPageObj.verifyUserNameBan(preferencesData.get(FIRST_NAME)));	
		preferencesPageObj.clickPreferencesLink();
		preferencesPageObj.setName(preferencesData.get(CUR_FIRST_NAME), preferencesData.get(CUR_LAST_NAME));
	}

	/**
	 * Verify that update email works by editing the email and viewing the new
	 * email on the preferences page
	 */
	@Test(description = "Verify that update email works by editing the email and viewing the new"
			+ "email on the preferences page", groups = {"smoke"})
	public void updateEmail() {
		preferencesData.put(CUR_EMAIL, preferencesPageObj.getEmail());
		preferencesPageObj.setEmail(preferencesData.get(EMAIL));
		preferencesAssert.assertTrue(preferencesPageObj.verifyEmail(preferencesData.get(EMAIL)));
		preferencesPageObj.setEmail(preferencesData.get(CUR_EMAIL));
	}

	/**
	 * Test to set Notification subscription and verify the the new value on the page
	 */
	@Test(description = "Test to set Notification subscription and verify the the new value on the page", groups = {"smoke"})
	public void updateNotificationsSubscription() {
		
		preferencesPageObj.setNotificationsSubscription(preferencesData.get(NOTIFICATION_SUBSCRIPTION));

		// Verify that the updated Notification Subscription is visible
		preferencesAssert.assertEquals(true,
				preferencesPageObj.verifyNotificationSub(preferencesData.get(NOTIFICATION_SUBSCRIPTION)));
		
	}

	/**
	 * Test to set Measurement unit and verify the the new value on the page
	 */
	@Test(description = "Test to set Measurement unit and verify the the new value on the page", groups = {"smoke"})
	public void updateMeasurementUnit() {
		
		preferencesPageObj.setMeasurementUnits(preferencesData.get(MEASUREMENT_UNIT));

		// Verify that the updated Measurement unit is visible in the Page
		preferencesAssert.assertEquals(true,
				preferencesPageObj.verifyMeasurementUnits(preferencesData.get(MEASUREMENT_UNIT)));
		
	}

	/**
	 * Test to verify that the Alias can be updated in the preferences page
	 */
	@Test(description = "Verify that the Alias can be updated in the preferences page", groups = {"smoke"})
	public void editAlias() {
		preferencesData.put(CURR_ALIAS, preferencesPageObj.getAlias());

		preferencesPageObj.setAlias(preferencesData.get(ALIAS));
		
		preferencesAssert.assertTrue(preferencesPageObj.verifyAlias(preferencesData.get(ALIAS)));
		

		preferencesPageObj.setAlias(preferencesData.get(CURR_ALIAS));
	}

	/**
	 * Test to verify the options present in the Notification Subscription dropdown
	 */
	@Test(description = "Verify the options present in the Notification Subscription dropdown", groups = {"smoke"})
	public void verifyNotificationSubsOptions() {
		Boolean isVerify = preferencesPageObj.verifyNotificationSubOptions(preferencesData.get(NOTIFICATION_SUB_LIST));
		preferencesAssert.assertTrue(isVerify);
		
	}

	/**
	 * Test to verify the options present in the Measurement Units dropdown
	 */
	@Test(description = "Verify the options present in the Measurement Units dropdown", groups = {"smoke"})
	public void verifyMeasureUnitOptions(){
		boolean isVerify = preferencesPageObj.verifyMeasureUnitOptions(preferencesData.get(MEASUREMENT_UNIT_LIST));
		preferencesAssert.assertTrue(isVerify);
		
	}
 
	/**
	 * Verify that the Change message format dropdown menu has Text and HTML options
	 */
	@Test(description="Verify that the Change message format dropdown menu has Text and HTML options", groups = {"smoke"})
	public void verifyMsgOptions() {
		boolean isVerify = preferencesPageObj.verifyMsgFormatOptions(preferencesData.get(MSG_FORMAT_LIST));
		preferencesAssert.assertTrue(isVerify);
		
	}

	/**
	 * Test to verify that the updated security questions are saved after the update
	 */
	@Test(description = "Verify that the updated security questions are saved after the update", groups = {"smoke"})
	public void updateSecurityQuestions() {
		String[] securityQuestionsArr = preferencesPageObj.getSecurQuestions();
		String[] securityAnswersArr = preferencesPageObj.getSecurQuestAns();
		preferencesPageObj.setSecureQuestions(preferencesData.get(SECURITY_QUESTION1),
				preferencesData.get(SECURITY_QUESTION2), preferencesData.get(SECURITY_QUESTION3));
		preferencesPageObj.setSecureQuestAns(preferencesData.get(SECURITY_QUESTION1_ANS),
				preferencesData.get(SECURITY_QUESTION2_ANS), preferencesData.get(SECURITY_QUESTION3_ANS));
		boolean isQuestionsSet = preferencesPageObj.verifySecurityQuestions(preferencesData.get(SECURITY_QUESTION1),
				preferencesData.get(SECURITY_QUESTION2), preferencesData.get(SECURITY_QUESTION3));
		preferencesAssert.assertTrue(isQuestionsSet);
		preferencesPageObj.setSecureQuestions(securityQuestionsArr[0], securityQuestionsArr[1], securityQuestionsArr[2]);
		preferencesPageObj.setSecureQuestAns(securityAnswersArr[0], securityAnswersArr[1], securityAnswersArr[2]);
	}

	
	/** 
	 * This method runs after all test from the class have run 
	 */
	@AfterClass
	public void classTearDown() {	
		loginData = null;
		preferencesData = null;
		logger.info("Exiting the classTearDown() method for Preferences class \n\n");
	}
}
