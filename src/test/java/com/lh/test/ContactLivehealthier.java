package com.lh.test;

import static com.lh.helper.DriverFactory.driver;

import java.util.Map;

import org.apache.logging.log4j.Logger;import org.apache.logging.log4j.LogManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import com.lh.pages.authenticated.ContactLiveHealthierAuthenticatedPage;
import com.lh.pages.unauthenticated.ContactLiveHealthierUnAuthenticatedPage;
import com.lh.pages.unauthenticated.LogOutPage;
import com.lh.pages.unauthenticated.LoginPage;
import com.lh.test.base.AuthBaseTestClass;

@Listeners(com.lh.helper.LHTestListener.class)
public class ContactLivehealthier extends AuthBaseTestClass {
	
	private static final String CONTACT_LIVE_HEALTHIER = "ContactLiveHealthier";
	private static Logger logger 							= LogManager.getLogger(Login.class);
	private LoginPage loginPageObj;
	private ContactLiveHealthierUnAuthenticatedPage contactLiveHealthierUnAuthenticatedPageObj;
	private ContactLiveHealthierAuthenticatedPage contactLiveHealthierAuthenticatedPageObj;
	private Map<String, String> contactUsData;
	private Assertion contactUsAssert						= new Assertion();
	private static final String Login					= "Login";
	private static final String What_are_you_contacting	= "What are you contacting us about?";
	private static final String Biometric_Screening		= "Biometric Screening";
	private static final String Incentivesgeneralquestions	= " Incentives - general questions";
	private static final String Incentive_report			= " Incentives - report completing an alternative option";
	private static final String Challenge					= " Challenges";
	private static final String Spouse_Dependent			= "Spouse/Dependent";
	private static final String Other			            = "Other";
	private static final String contactus_sel           ="messageSubject";
	private static final String contactus_dropdown          ="dropdown";
	
	LogOutPage logoutPageObj;
	/**
	 * This method runs before the first test from the class runs.
	 * <p>
	 * This method also updates the new password.
	 * 
	 */
	@BeforeClass
	public void initClass() {
		contactUsData = readexcelsheet(CONTACT_LIVE_HEALTHIER);
		loginPageObj = new LoginPage();
		contactLiveHealthierUnAuthenticatedPageObj = new ContactLiveHealthierUnAuthenticatedPage();
		contactLiveHealthierAuthenticatedPageObj = new ContactLiveHealthierAuthenticatedPage(driver);
		logoutPageObj = new LogOutPage();
	}

	@Test(description="Sends an email to LiveHealthier as an Unauthenticated User", groups = {"smoke"},priority=2)
	public void sendMessageContactUsUnauthenticated() {
		loginPageObj.clickContactLiveHealthier();
		contactUsAssert.assertTrue(contactLiveHealthierAuthenticatedPageObj.verifyalldropdown(contactUsData));
		contactLiveHealthierUnAuthenticatedPageObj.sendMessageToLiveHealthier(contactUsData.get("userName"),
				contactUsData.get("userEmail"), contactUsData.get("messageSubject"),
				contactUsData.get("messageBodyUnauthenticated"));
	// clickAllDropdown() ;
		contactUsAssert.assertTrue(contactLiveHealthierUnAuthenticatedPageObj.verifyThanksModal(), "The verification has passed");
		contactLiveHealthierUnAuthenticatedPageObj.closeThanksModal();
		contactLiveHealthierUnAuthenticatedPageObj = null;
		loginPageObj.clickClientLogo();
	}
	//@Test(description="check all the drop down in contact us page  as an Unauthenticated User", groups = {"smoke"},priority=2)
	//public void clickAllDropdown() 
	//{
		//loginPageObj.clickContactLiveHealthier();
		//contactUsAssert.assertTrue(contactLiveHealthierUnAuthenticatedPageObj.verifyalldropdown(contactUsData,Login));
		
	//}
	
	/** Verify all the associated plans appear on On.Target dashboard */
//	@Test(description = "Verify all the drop downs appear on contact us page as an Unauthenticated User", groups = {"OnTarget","smoke"})
	//public void verifyAssociatedPlansOnDashboard(){
		//loginPageObj.clickContactLiveHealthier();
		//ContactLiveHealthierUnAuthenticatedPage.verifyalldropdown(contactUsData,contactUsData.get("Login"));
	//}
		
	
	@Test(description="Verify sending an email to LiveHealthier as an Authenticated User", groups = {"smoke"},priority=1)
	public void sendMessageContactUsAuthenticated() {
		doLogin(contactUsData.get("loginAsUser"), contactUsData.get("loginPassword"), "Portal", "Potal");
		loginPageObj.clickContactLiveHealthier();
		contactUsAssert.assertTrue(contactLiveHealthierAuthenticatedPageObj.verifyalldropdown(contactUsData));
		contactLiveHealthierAuthenticatedPageObj.sendMessageToLiveHealthier(contactUsData.get("userNameAuth"),
		contactUsData.get("userEmailAuth"), contactUsData.get("messageSubject"),
		contactUsData.get("messageBodyUnauthenticated"));
		contactUsAssert.assertTrue(contactLiveHealthierAuthenticatedPageObj.verifyThanksModal(), "The verification has passed");
		contactLiveHealthierAuthenticatedPageObj.closeThanksModal();
		contactLiveHealthierAuthenticatedPageObj = null;
		logoutPageObj.clickLogoutLink("Portal");
		setcurrentContext(null,null,false);
	}
	
	/** This method runs after all test from the class have run */
	@AfterClass
	public void classTearDown() {
		logger.info("Inside the classTearDown() method for ContactLivehealthier class...");
		loginPageObj = null;
		logger.info("Exiting the classTearDown() method for ContactLivehealthier class \n\n");
	}
}
