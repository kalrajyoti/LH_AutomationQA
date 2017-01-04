package com.lh.test;

import static com.lh.helper.DriverFactory.driver;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import com.lh.dao.DaoLayer;
import com.lh.dao.DaoLayerImpl;
import com.lh.pages.authenticated.MainPage;
import com.lh.pages.unauthenticated.LoginPage;
import com.lh.pages.unauthenticated.SelfRegistrationPage;
import com.lh.test.base.AuthBaseTestClass;
import com.lh.utils.DateUtil;

/**
 * <h2>This is the Self Registration test Class for the Unauthenticated user</h2>
 * <p>
 * 
 * @author amita.arya
 * @version 1.0
 * @since 2015-02-24
 */
@Listeners(com.lh.helper.LHTestListener.class)
public class SelfRegistration extends AuthBaseTestClass {

	/** Logger for the Login class */
	private static Logger logger = LogManager.getLogger(SelfRegistration.class);
	private LoginPage loginPageObj;
	private Map<String, String> selfRegistrationData;
	private Assertion selfRegisterationAssert = new Assertion();
	SelfRegistrationPage selfRegistrationPageObj;
	
	/**
	 *  This method runs before the first test from the class runs. <p>
	 */
	@BeforeClass
	public void initClass(){
		selfRegistrationData = readexcelsheet("SelfRegistration");		
		String partialPassword = Long.toString(DateUtil.getTimeInMillisOFCurrentCalender());
		String completePassword = selfRegistrationData.get("password").concat(partialPassword);
		selfRegistrationData.put("userPassword", completePassword);
		selfRegistrationPageObj = new SelfRegistrationPage();
		loginPageObj = new LoginPage("Portal");
		
	}
	
	/**
	 * This method runs before each test from the class runs.
	 */
	@BeforeMethod
	public void init(Method method){
		logger.info("Inside the init() method for SelfRegistration class...");		
		loginPageObj.clickRegisterNow();
		logger.info("Exiting the init() method for SelfRegistration class");
	}

	/**
	 * Complete the registration for a user which is present in the database (isFirstTimeLogin is false). 
	 */

	@Test(description= "Complete the registration for a user which is present in the database (isFirstTimeLogin is false). ", groups = {"smoke"})
	public void completeSelfRegisteration (){
		DaoLayer daoObj = new DaoLayerImpl();
		daoObj.resetSelfRegistration(selfRegistrationData.get("externalID"));
		daoObj = null;		
		selfRegistrationPageObj.completeSelfRegistrationStep1(selfRegistrationData.get("externalID"));
		ArrayList<String> securityQuestions = new ArrayList<String>();
		securityQuestions.add(selfRegistrationData.get("securityQuestion1"));
		securityQuestions.add(selfRegistrationData.get("securityQuestion2"));
		securityQuestions.add(selfRegistrationData.get("securityQuestion3"));
		
		ArrayList<String> securityQuestionAnswers = new ArrayList<String>();
		securityQuestionAnswers.add(selfRegistrationData.get("securityQuestionAnswer1"));
		securityQuestionAnswers.add(selfRegistrationData.get("securityQuestionAnswer2"));
		securityQuestionAnswers.add(selfRegistrationData.get("securityQuestionAnswer3"));	
		
		selfRegistrationPageObj.completeSelfRegistrationStep2(selfRegistrationData.get("email"), selfRegistrationData.get("userPassword"), securityQuestions, securityQuestionAnswers);
		selfRegisterationAssert.assertTrue(selfRegistrationPageObj.verifyAccountCreationMessage(selfRegistrationData.get("createAccountSuccessMessage")), "The User account is successfully created");
		selfRegistrationPageObj.clickOkey();
		MainPage homePageObject = new MainPage();
		homePageObject.clickLogoutLink();
		setcurrentContext(null, null, false);
		homePageObject = null;			
	}
	
	/**
	 * Self Registration for a user which is already registered (isFirstTimeLogin is true).
	 */

	@Test(description= "Self Registration for a user which is already registered (isFirstTimeLogin is true). ", groups = {"smoke"})
	public void verifyErrorMsgInSelfRegisteration (){
		selfRegistrationPageObj.completeSelfRegistrationStep1(selfRegistrationData.get("externalIDRegisteredUser"));
		selfRegisterationAssert.assertTrue(selfRegistrationPageObj.verifyAccountCreationErrorMsg(selfRegistrationData.get("errorMessageRegisteredUser")), "The error message is appearing as expected");
		selfRegistrationPageObj.closeAccountCreationModal();
	}
	
	/** 
	 * This method runs after all test from the class have run 
	 */
	@AfterClass
	public void classTearDown(){
		loginPageObj = null;
		selfRegistrationPageObj = null;
		logger.info("Exiting the classTearDown() method for self registration class \n\n");
		
	}
}
