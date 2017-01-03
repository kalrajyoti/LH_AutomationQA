package com.lh.test;

import java.lang.reflect.Method;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import com.lh.dao.DaoLayer;
import com.lh.dao.DaoLayerImpl;
import com.lh.pages.authenticated.ha.AboutYouHealthAssessmentPage;
import com.lh.pages.authenticated.ha.ActivityHealthAssessmentPage;
import com.lh.pages.authenticated.ha.ConditionsHealthAssessmentPage;
import com.lh.pages.authenticated.ha.DietHealthAssessmentPage;
import com.lh.pages.authenticated.ha.HealthAssessmentPage;
import com.lh.pages.authenticated.ha.HealthStatusHealthAssessmentPage;
import com.lh.pages.authenticated.ha.ScreeningsHealthAssessmentPage;
import com.lh.pages.authenticated.ha.SubstancesHealthAssessmentPage;
import com.lh.pages.authenticated.ha.TobaccoUseHealthAssessmentPage;
import com.lh.pages.authenticated.ha.WellBeingHealthAssessmentPage;
import com.lh.test.base.AuthBaseTestClass;
import com.lh.utils.UtilityMethods;

/**
 * <h2>Define test for completing an HA for a user!</h2> 
 * <p>In the HA the Show Biometrics/IsVerified Biometrics required 
 * 
 * @author amita.arya
 * @version 1.0
 * @since 2014-11-17
 */
@Listeners(com.lh.helper.LHTestListener.class)
public class HealthAssessment extends AuthBaseTestClass{

	/** Contains the valid password to login to the client portal */
	private static final String VALID_PASSWORD 					= "validPassword";
	/** Contains the valid username to login to the client portal */
	private static final String VALID_USERNAME 					= "validUsername";
	/** Logger for the HealthAssessment class */
	private static Logger logger 								= LogManager.getLogger(HealthAssessment.class);
	/** AboutYou page reference for the tests to run */
	private AboutYouHealthAssessmentPage hAPageObj;
	/** Contains the login data */
	private Map<String, String> loginData;
	/** Contains the HA data */
	private Map<String, String> hAData;
	/** To verify the test outcome */
	private Assertion hA_assert = new Assertion();
	DaoLayer daoObj;
	String testName;
	HealthAssessmentPage haBasePageObj;

	/** This method runs before the first test from the class runs */
	@BeforeClass
	public void initClass() {
		logger.info("Inside the initClass() method for HealthAssessment class...");
		loginData = readexcelsheet("Login");
		hAData = readexcelsheet("HA");		
		daoObj = new DaoLayerImpl();
		daoObj.resetHAForUser(loginData.get(VALID_USERNAME));
		daoObj = null;
		doLogin(loginData.get(VALID_USERNAME), loginData.get(VALID_PASSWORD), "Portal", "Portal");
		haBasePageObj = new HealthAssessmentPage();
		logger.info("Exiting the initClass() method for HealthAssessment class \n\n");
	}

	/** This method runs before each test from the class runs */
	@BeforeMethod
	public void init(Method method) throws Exception {
		logger.info("\n Inside the init() method for HealthAssessment class...");
		testName = method.getName();
		haBasePageObj.clickHealthAssessment();
		logger.info("Exiting the init() method for HealthAssessment class \n");
	}

	/** This method tests the enabling of About You page after giving consent */
	@Test(description = "On giving consent, the About You page of the HA is enabled", groups = {"AboutYou", "smoke"})
	public void verifyAboutYouHAPage() {
		haBasePageObj.clickTakeNowBtn();
		hA_assert.assertTrue(haBasePageObj.verifyPresenceOfConsentForm());
		haBasePageObj.giveConsent();
		hAPageObj = new AboutYouHealthAssessmentPage();
		hA_assert.assertTrue(hAPageObj.isEnabledAboutYou());
	}

	/** This method verifies that the HA navigation panels are listed */
	@Test(dependsOnMethods = { "verifyAboutYouHAPage" },groups = {"AboutYou", "smoke"})
	public void verifyHANavPanelsListed() {
		haBasePageObj.clickTakeNowBtn();
		hAPageObj = new AboutYouHealthAssessmentPage();
		hAPageObj.isEnabledAboutYou();
		hAPageObj.isDisabledOthQuestionnaireItems();
	}

	/** This method verifies that the HA navigation panels are enabled */
	@Test(description = "Verify that All the navigation links are disabled with the exception of About You page", 
			dependsOnMethods = { "verifyAboutYouHAPage" },groups = {"AboutYou", "smoke"})
	public void verifyHANavPanelsDiabled() {
		haBasePageObj.clickTakeNowBtn();
		hAPageObj.isEnabledAboutYou();
		hAPageObj.isDisabledOthQuestionnaireItems();
	}

	/** This method verifies that the HA navigation panels are visible */
	@Test(description = "Verify that All the navigation links are visible", dependsOnMethods = { "verifyAboutYouHAPage" }, groups = {"AboutYou", "smoke"})
	public void verifyHANavPanelsVisible() {
		haBasePageObj.clickTakeNowBtn();
		hAPageObj = new AboutYouHealthAssessmentPage();
		hAPageObj.isDisplayedMenuItem();
	}

	/**
	 * This method fills the About You data and saves the information in the
	 * database
	 */
	@Test(description = "Complete the About You HA page", dependsOnMethods = { "verifyHANavPanelsVisible" }, groups = {"AboutYou", "smoke"})
	public void completeAboutYouHA() {
		haBasePageObj.clickTakeNowBtn();
		hAPageObj = new AboutYouHealthAssessmentPage();
		hAPageObj.completeAboutYouInformation(hAData);
		haBasePageObj.clickNextBtn();
		hA_assert.assertTrue(hAPageObj.verifyCurrentHAPage(hAData.get("verifyPresenceofHealthStatusPage")));	
	}

	/**
	 * This method fills the Health Status data and saves the information in the database
	 */
	@Test(description = "Complete the Health Status HA page", dependsOnMethods = { "completeAboutYouHA" }, groups = {"smoke"})
	public void completeHealthStatus() {
		haBasePageObj.clickTakeNowBtn();
		HealthStatusHealthAssessmentPage healthStatusHAPageObj = new HealthStatusHealthAssessmentPage();
		healthStatusHAPageObj.completeHealthStatusHA(hAData);
		haBasePageObj.clickNextBtn();
		hA_assert.assertTrue(healthStatusHAPageObj.verifyCurrentHAPage(hAData.get("verifyPresenceofTobaccoUsePage")));
		healthStatusHAPageObj = null;
	}

	/**
	 * This method fills the Tobacco use data and saves the information in the database
	 */
	@Test(description = "Complete the Tobacco use HA page", dependsOnMethods = { "completeHealthStatus" }, groups = {"smoke"})
	public void completeTobaccoUse() {
		haBasePageObj.clickTakeNowBtn();
		TobaccoUseHealthAssessmentPage tobaccoUseHAPageObj = new TobaccoUseHealthAssessmentPage();
		tobaccoUseHAPageObj.completeTobaccoUseHA(hAData);
		haBasePageObj.clickNextBtn();
		hA_assert.assertTrue(tobaccoUseHAPageObj.verifyCurrentHAPage(hAData.get("verifyPresenceofSubstancesPage")));
		tobaccoUseHAPageObj = null;
	}
	
	/**
	 * This method fills the Substances use data and saves the information in the database
	 */
	@Test(description = "Complete the Substances use HA page", dependsOnMethods = { "completeTobaccoUse" }, groups = {"smoke"})
	public void completeSubstanceUse() {
		haBasePageObj.clickTakeNowBtn();
		SubstancesHealthAssessmentPage substancesHealthAssessmentPageObj = new SubstancesHealthAssessmentPage();
		
		substancesHealthAssessmentPageObj.completeSubstancesUseHA(hAData);
		
		haBasePageObj.clickNextBtn();
		hA_assert.assertTrue(substancesHealthAssessmentPageObj.verifyCurrentHAPage(hAData.get("verifyPresenceofActivityPage")));
		substancesHealthAssessmentPageObj = null;
	}

	/**
	 * This method fills the Activity data and saves the information in the database
	 */
	@Test(description = "Complete the Activity HA page", dependsOnMethods = { "completeSubstanceUse" }, groups = {"smoke"})
	public void completeActivity(){
		haBasePageObj.clickTakeNowBtn();
		ActivityHealthAssessmentPage activityHealthAssessmentPageObj = new ActivityHealthAssessmentPage();
		
		activityHealthAssessmentPageObj.completeActivityHA(hAData);
		
		haBasePageObj.clickNextBtn();
		hA_assert.assertTrue(activityHealthAssessmentPageObj.verifyCurrentHAPage(hAData.get("verifyPresenceOfDietPage")));
		activityHealthAssessmentPageObj = null;
	}
	
	/**
	 * This method fills the Diet related information and saves the information in the database
	 */
	@Test(description = "Complete the Diet HA page", dependsOnMethods = { "completeActivity" }, groups = {"smoke"})
	public void completeDiet() {
		haBasePageObj.clickTakeNowBtn();
		DietHealthAssessmentPage dietHealthAssessmentPageObj = new DietHealthAssessmentPage();
		dietHealthAssessmentPageObj.completeDietHAPage(hAData);
		haBasePageObj.clickNextBtn();
		hA_assert.assertTrue(dietHealthAssessmentPageObj.verifyCurrentHAPage(hAData.get("verifyPresenceOfWellBeingPage")));
		dietHealthAssessmentPageObj = null;
	}
	
	/**
	 * This method fills the Well being information and saves the information in the database
	 */
	@Test(description = "Complete the Well Being HA page", dependsOnMethods = { "completeDiet" }, groups = {"smoke"})
	public void completeWellbeing(){
		haBasePageObj.clickTakeNowBtn();
		WellBeingHealthAssessmentPage wellBeingHealthAssessmentPageObj = new WellBeingHealthAssessmentPage();
		
		wellBeingHealthAssessmentPageObj.completeWellBeingHA(hAData);
		haBasePageObj.clickNextBtn();
		hA_assert.assertTrue(wellBeingHealthAssessmentPageObj.verifyCurrentHAPage(hAData.get("verifyPresenceOfConditionsPage")));
		wellBeingHealthAssessmentPageObj = null;
	}
	/**
	 * This method fills the existing/past conditions information and saves the information in the database
	 */
	@Test(description = "Complete the Conditions HA page", dependsOnMethods = { "completeWellbeing" }, groups = {"smoke"})
	public void completeConditions() {
		
		haBasePageObj.clickTakeNowBtn();
		
		ConditionsHealthAssessmentPage conditionsHealthAssessmentPageObj = new ConditionsHealthAssessmentPage();
		conditionsHealthAssessmentPageObj.completeConditionHA(hAData);
		haBasePageObj.clickNextBtn();
		hA_assert.assertTrue(conditionsHealthAssessmentPageObj.verifyCurrentHAPage(hAData.get("verifyPresenceOfScreeningsPage")));
		conditionsHealthAssessmentPageObj = null;
	}
	
	/**
	 * This method fills the screenings information and saves the information in the database
	 */
	@Test(description = "Complete the Screenigns HA questionnaire by answering the Screenings Questions", dependsOnMethods = { "completeConditions" }, groups = {"smoke"})
	public void completeScreenigns(){
		
		haBasePageObj.clickTakeNowBtn();
		
		ScreeningsHealthAssessmentPage screeningsHealthAssessmentPageObj = new ScreeningsHealthAssessmentPage();
		screeningsHealthAssessmentPageObj.setCompleteScreeningsHA(hAData);
		haBasePageObj.clickNextBtn();
		
		hA_assert.assertTrue(hAPageObj.isHAComplete());
		screeningsHealthAssessmentPageObj = null;
	}

	/** This method runs after each test from the class runs */
	@AfterMethod
	public void tearDown() {
		logger.info("Inside the tearDown() method for HealthAssessment class...");
		if (!testName.equalsIgnoreCase("completeScreenigns")) {
			haBasePageObj.clickSaveAndExitBtn();
			haBasePageObj.clickLeaveBtn();
		}
		logger.info("Loging out of the application...");
		logger.info("Exiting the tearDown() method for HealthAssessment class \n\n");
	}

	/** This method runs after all test from the class have run */
	@AfterClass
	public void classTearDown() {
		logger.info("Inside the classTearDown() method for HealthAssessment class...");
		loginData = null;
		hAData = null;
		hAPageObj = null;	
		haBasePageObj = null;
		logger.info("Writing the output to the Result output text file.");
		UtilityMethods.writeOutputResult();
		logger.info("Exiting the classTearDown() method for HealthAssessment class \n\n");
		
	}
}