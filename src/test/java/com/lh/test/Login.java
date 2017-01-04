package com.lh.test;

import java.util.Map;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import com.lh.pages.unauthenticated.LogOutPage;
import com.lh.pages.unauthenticated.LoginPage;
import com.lh.test.base.AuthBaseTestClass;
import com.lh.utils.DateUtil;

/**
 * <h2>This class contains all the test designed for the Login page and Forgot 
 * Password Modal</h2><p>
 * 
 * @author amita.arya
 * @version 2.0
 * @since 2014-11-17
 */
@Listeners(com.lh.helper.LHTestListener.class)
public class Login extends AuthBaseTestClass {

	private static final String MSG_SECURITY_QUESTIONS_NOT_SET 				= "msgSecurityQuestionsNotSet";
	private static final String VALID_USERNAME_SECURITY_QUESTIONS_NOT_SET 	= "validUsernameSecurityQuestionsNotSet";
	private static final String NEW_PASSWORD 								= "newPassword";
	private static final String VALID_SECURITY_ANS1 						= "validSecurityAns1";
	private static final String MSG_INCORRECT_ANSWER 						= "msgIncorrectAnswer";
	private static final String VALID_SECURITY_ANS3 						= "validSecurityAns3";
	private static final String VALID_SECURITY_ANS2 						= "validSecurityAns2";
	private static final String INVALID_SECURITY_ANS 						= "invalidSecurityAns";
	private static final String VALID_EMAIL 								= "validEmail";
	private static final String HEADER_FORGOT_PASS2 						= "headerForgotPass2";
	private static final String VALID_USERNAME_SECURITY_QUESTIONS_SET 		= "validUsernameSecurityQuestionsSet";
	private static final String VALID_USER_ANOTHER_CLIENT					= "validUserAnotherClient";
	private static final String MSG_FORGOT_PASS_USR_NT_FOUND 				= "msgForgotPassUsrNtFound";
	private static final String INVALID_EMAIL 								= "invalidEmail";
	private static final String MSG_INVALID_UNAME_PASS 						= "msgInvalidUnamePass";
	private static final String VALID_PASSWORD 								= "validPassword";
	private static final String MSG_EMPTY_PASSWORD 							= "msgEmptyPassword";
	private static final String VALID_USERNAME 								= "validUsername";
	private static final String INVALID_USERNAME							= "invalidUsername";
	private static final String INVALID_PASSWORD  							= "InvalidPassword";
	private static final String MSG_USER_OF_ANOTHER_CLIENT 					= "msgUserOfAnotherClient";
	private static Logger logger = LogManager.getLogger(Login.class);
	private LoginPage loginPageObj;
	private Map<String, String> loginData;
	private Assertion loginAssert = new Assertion();
	LogOutPage logoutPageobj;
	
	/**
	 *  This method runs before the first test from the class runs. <p>
	 *  This method also updates the new password. 
	 * 
	 */
	@BeforeClass
	public void initClass(){
			loginData = readexcelsheet("Login");
			String partialPassword = Long.toString(DateUtil.getTimeInMillisOFCurrentCalender());
			String completePassword = loginData.get(NEW_PASSWORD).concat(partialPassword);
			loginData.put(NEW_PASSWORD, completePassword);
			logoutPageobj = new LogOutPage();
			loginPageObj = new LoginPage();
			doLogin(loginData.get(VALID_USERNAME), loginData.get(VALID_PASSWORD), "Portal", "Portal");
	}
	
	/**
	 * Test for valid login -User logs in with username and password - correct credentials
	 */
	@Test(description = "User logs in with username and password - correct credentials", groups = {"smoke"},priority = 1)
	public void validLogin(){		
		logoutPageobj.clickLogoutLink("Portal");
		setcurrentContext(null, null, false);
	}

	/**
	 * Test for Error Handling - user attempts to login with an incorrect username
	 */
	@Test(description = "User attempts to login with an incorrect username. Verify incorrect username error handling", groups = {"smoke"},priority=2)
	public void loginErrorIncorrectUsrName() {
		logger.debug("This test enters the username and wrong password");
		doLogin(loginData.get(INVALID_USERNAME), loginData.get(INVALID_PASSWORD), "Portal", "Portal");
		loginAssert.assertTrue(loginPageObj.verifyLoginErr(loginData.get(MSG_INVALID_UNAME_PASS)));
	}

	/**
	 * Test for Error Handling - user attempts to login with an incorrect password
	 */
	@Test(description = "User that attempts to log into the portal with a correct username but an incorrect password.", groups = {"smoke"},priority=3)
	public void loginErrorIncorrectPassword() {
		loginPageObj.loginAs(loginData.get(VALID_USERNAME), loginData.get(INVALID_PASSWORD));
		logger.info("Calling the verifyLoginError(testLoginValidationTxt) method");
		loginAssert.assertTrue(loginPageObj.verifyLoginErr(loginData.get(MSG_INVALID_UNAME_PASS)));
	}

	/**
	 * Test for Error Handling - user attempts to login with a blank password
	 */
	@Test(description = "User attempts to login without password - password field blank", groups = {"smoke"},priority=4)
	public void loginErrorEmptyPassword() {
		loginPageObj.loginAs(loginData.get(VALID_USERNAME), "");
		loginAssert.assertTrue(loginPageObj.verifyLoginErr(loginData.get(MSG_EMPTY_PASSWORD)));
	}
	
	/**
	 * Test for Error Handling - user attempts to login with an blank username
	 */
	@Test(description = "User logs in with user name and password - leave username blank - verify error handling", groups = {"smoke"},priority=5)
	public void loginErrorEmptyUserName() {
		loginPageObj.loginAs("", loginData.get(VALID_PASSWORD));
		loginAssert.assertTrue(loginPageObj.verifyLoginErr(loginData.get(MSG_INVALID_UNAME_PASS)));
	}

	/**
	 * Test for password link 
	 */
	@Test(description = "User Clicking on the forgot password link and closing the modal dialog", groups = {"smoke"},priority=6)
	public void forgotPasswordIncorrectEmail() {

		loginPageObj.clickForgotPass();

		loginPageObj.clickRetrieveForgotPass(loginData.get(INVALID_EMAIL));
		loginAssert.assertTrue(loginPageObj.verifyForgotPassErrLbl(loginData.get(MSG_FORGOT_PASS_USR_NT_FOUND)));
		loginPageObj.clickCloseForgotPassModal();
	}

	/**
	 * Test for Forgot Password in which user enters email address for a different client
	 */
	@Test(description = "clicking on the forgot password link and user enters email address", groups = {"smoke"},priority=7)
	public void forgotPasswordOthrClientEmail() {

		loginPageObj.clickForgotPass();
		loginPageObj.clickRetrieveForgotPass(loginData.get(VALID_USER_ANOTHER_CLIENT));
		loginAssert.assertTrue(loginPageObj.verifyErrorMessageOFUserOFOtherCLient(loginData.get(MSG_USER_OF_ANOTHER_CLIENT)));
		loginPageObj.clickCloseForgotPassModal();
	}

	/**
	 * Test for Forgot Password in which user enters correct username
	 */
	@Test(description = "Verify forgot password functionality - user enters username", groups = {"smoke"},priority=8)
	public void forgotPasswordEnterUserName() {

		loginPageObj.clickForgotPass();
		loginPageObj.clickRetrieveForgotPass(loginData.get(VALID_USERNAME_SECURITY_QUESTIONS_SET));
		loginAssert.assertTrue(loginPageObj.verifyForgotPassModal2Header(loginData.get(HEADER_FORGOT_PASS2)));
		loginPageObj.clickCloseForgotPassModal();
	}

	/**
	 * Test for Forgot Password in which user enters user enters correct email address
	 */
	@Test(description = "Verify forgot password functionality - user enters correct email address", groups = {"smoke"},priority=9)
	public void forgotPasswordEnterEmail() {

		loginPageObj.clickForgotPass();
		loginPageObj.clickRetrieveForgotPass(loginData.get(VALID_EMAIL));
		loginAssert.assertTrue(loginPageObj.verifyForgotPassErrLbl(loginData.get(MSG_FORGOT_PASS_USR_NT_FOUND)));
		loginPageObj.clickCloseForgotPassModal();
	}

	/**
	 * Test for Forgot Password in which user User has set security questions and enter 
	 * one incorrect answer
	 */
	@Test(description = "Forgot password - User has entered one incorrect response", groups = {"smoke"},priority=10)
	public void forgotPasswordOneIncorrectResponse() {

		loginPageObj.clickForgotPass();
		loginPageObj.clickRetrieveForgotPass(loginData.get(VALID_USERNAME_SECURITY_QUESTIONS_SET));
		loginPageObj.submitSecurityAnswers(loginData.get(INVALID_SECURITY_ANS), loginData.get(VALID_SECURITY_ANS2),
				loginData.get(VALID_SECURITY_ANS3));
		loginPageObj.verifyCreateNewPasswordError(loginData.get(MSG_INCORRECT_ANSWER));
		loginPageObj.clickCloseForgotPassModal();
	}
	
	/**
	 * Test for Forgot Password in which user has set security questions and enter 
	 * correct passwords and changes the password successfully
	 */
	@Test(description = "Forgot password - User has set security questions", groups = {"smoke"},priority=11)
	public void forgotPasswordResetPassword() {
		loginPageObj.clickForgotPass();
		loginPageObj.clickRetrieveForgotPass(loginData.get(VALID_USERNAME_SECURITY_QUESTIONS_SET));
		loginPageObj.submitSecurityAnswers(loginData.get(VALID_SECURITY_ANS1), loginData.get(VALID_SECURITY_ANS2),loginData.get(VALID_SECURITY_ANS3));
		loginPageObj.setPassword(loginData.get(NEW_PASSWORD),loginData.get(NEW_PASSWORD));
		loginAssert.assertTrue(loginPageObj.verifyPasswordUpdateModal());
		loginPageObj.clickOkayBtn();
		loginPageObj.loginAs(loginData.get(VALID_USERNAME_SECURITY_QUESTIONS_SET), loginData.get(NEW_PASSWORD));		
		logoutPageobj.clickLogoutLink("Portal");
		setcurrentContext(null, null, false);		
	}
	
	/**
	 * Test for Forgot password when the User has not set security questions
	 */
	@Test(description= "Forgot password - User has not set security questions", groups = {"smoke"},priority=12)
	public void forgotPasswordSecurityQuesNotSet() {
		loginPageObj.clickForgotPass();		
		loginPageObj.clickRetrieveForgotPass(loginData.get(VALID_USERNAME_SECURITY_QUESTIONS_NOT_SET));
		loginAssert.assertTrue(loginPageObj.verifyCreateNewPasswordError(loginData.get(MSG_SECURITY_QUESTIONS_NOT_SET)));
		loginPageObj.clickCloseForgotPassModal();
	}

	/** 
	 * This method runs after all test from the class have run 
	 */
	@AfterClass
	public void classTearDown(){
		loginPageObj = null;
		logger.info("Exiting the classTearDown() method for Login class \n\n");
	}

}