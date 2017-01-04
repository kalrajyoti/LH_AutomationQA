package com.lh.test;

import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
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
import com.lh.pages.authenticated.ha.ScreeningsHealthAssessmentPage;
import com.lh.pages.authenticated.ha.SubstancesHealthAssessmentPage;
import com.lh.pages.authenticated.ha.TobaccoUseHealthAssessmentPage;
import com.lh.pages.authenticated.ha.WellBeingHealthAssessmentPage;
import com.lh.test.base.AuthBaseTestClass;

/**
 * <h2>Define test for completing an HA (showBiometrics=false) for a user!</h2> 
 * <p>
 * 
 * @author amita.arya
 * @version 1.0
 * @since 2014-02-09
 */
@Listeners(com.lh.helper.LHTestListener.class)
public class HealthAssessmentNoBiometrics extends AuthBaseTestClass {
	
	private static final String LOGIN_PASSWORD 		= "loginPassword";
	private static final String LOGIN_AS_USER 		= "loginAsUser";
	/** Contains the HA data */
	private Map<String, String> hAData;
	private AboutYouHealthAssessmentPage hAPageObj;
	private TobaccoUseHealthAssessmentPage tobaccoUseHAPageObj;
	private ActivityHealthAssessmentPage activityHealthAssessmentPageObj ;
	private SubstancesHealthAssessmentPage substancesHealthAssessmentPageObj;
	private DietHealthAssessmentPage dietHealthAssessmentPageObj;
	private WellBeingHealthAssessmentPage wellBeingHealthAssessmentPageObj;
	private ConditionsHealthAssessmentPage conditionsHealthAssessmentPageObj ;
	private ScreeningsHealthAssessmentPage screeningsHealthAssessmentPageObj ;
	/** Logger for the HealthAssessmentNoBiometrics class */
	private static Logger logger 								= LogManager.getLogger(HealthAssessmentNoBiometrics.class);
	/** Assertion to verify different elements of the page. */
	private Assertion hA_assert 								= new Assertion();
	DaoLayer daoObj;
	HealthAssessmentPage haBasePageObj;
	
	/** This method runs before the first test from the class runs */
	@BeforeClass
	public void initClass() throws Exception {
		
		logger.info("Inside the initClass() method for HealthAssessmentNoBiometrics class...");
		hAData = readexcelsheet("HA_NoHealthStatus");		
		daoObj = new DaoLayerImpl();
		daoObj.resetHAForUser(hAData.get(LOGIN_AS_USER));
		daoObj = null;
		doLogin(hAData.get(LOGIN_AS_USER), hAData.get(LOGIN_PASSWORD), "Portal", "Portal");
		haBasePageObj = new HealthAssessmentPage();
		haBasePageObj.clickHealthAssessment();
//		hAPageObj = new AboutYouHealthAssessmentPage();
//		hAPageObj.clickHealthAssessment();
		logger.info("Exiting the initClass() method for HealthAssessmentNoBiometrics class \n\n");
	}

	@Test(description = "Verify that the Health Status page does not appear in the HA.", groups = {"HA_ShowBiometrics_False", "smoke"})
	public void verifyHealthStatusDoesNotAppear() {
		haBasePageObj.clickTakeNowBtn();
		hA_assert.assertTrue(haBasePageObj.verifyPresenceOfConsentForm());
		haBasePageObj.giveConsent();
		hAPageObj = new AboutYouHealthAssessmentPage();
		hA_assert.assertTrue(hAPageObj.isEnabledAboutYou());
		hAPageObj = new AboutYouHealthAssessmentPage();
		hAPageObj.completeAboutYouInformation(hAData);
		haBasePageObj.clickNextBtn();
		hA_assert.assertFalse(haBasePageObj.verifyCurrentHAPage(hAData.get("verifyPresenceofHealthStatusPage")));	
	}
	
	@Test(description = "Verify that the Health Status page does not appear in the HA.", groups = {"HA_ShowBiometrics_False", "smoke"}, dependsOnMethods = { "verifyHealthStatusDoesNotAppear" })
	public void completeHA(){
		tobaccoUseHAPageObj = new TobaccoUseHealthAssessmentPage();
		tobaccoUseHAPageObj.completeTobaccoUseHA(hAData);
		haBasePageObj.clickNextBtn();
		hA_assert.assertFalse(tobaccoUseHAPageObj.verifyCurrentHAPage(hAData.get("verifyPresenceofSubstancesPage")));
		
		substancesHealthAssessmentPageObj = new SubstancesHealthAssessmentPage();
		substancesHealthAssessmentPageObj.completeSubstancesUseHA(hAData);
		haBasePageObj.clickNextBtn();
		hA_assert.assertTrue(substancesHealthAssessmentPageObj.verifyCurrentHAPage(hAData.get("verifyPresenceofActivityPage")));

		activityHealthAssessmentPageObj = new ActivityHealthAssessmentPage();
		activityHealthAssessmentPageObj.completeActivityHA(hAData);
		haBasePageObj.clickNextBtn();
		hA_assert.assertTrue(activityHealthAssessmentPageObj.verifyCurrentHAPage(hAData.get("verifyPresenceOfDietPage")));
		
		dietHealthAssessmentPageObj = new DietHealthAssessmentPage();
		dietHealthAssessmentPageObj.completeDietHAPage(hAData);
		haBasePageObj.clickNextBtn();
		hA_assert.assertTrue(dietHealthAssessmentPageObj.verifyCurrentHAPage(hAData.get("verifyPresenceOfWellBeingPage")));

		wellBeingHealthAssessmentPageObj = new WellBeingHealthAssessmentPage();
		wellBeingHealthAssessmentPageObj.completeWellBeingHA(hAData);	
		haBasePageObj.clickNextBtn();
		hA_assert.assertTrue(wellBeingHealthAssessmentPageObj.verifyCurrentHAPage(hAData.get("verifyPresenceOfConditionsPage")));
		
		
		conditionsHealthAssessmentPageObj = new ConditionsHealthAssessmentPage();
		conditionsHealthAssessmentPageObj.completeConditionHA(hAData);
		haBasePageObj.clickNextBtn();
		hA_assert.assertTrue(conditionsHealthAssessmentPageObj.verifyCurrentHAPage(hAData.get("verifyPresenceOfScreeningsPage")));
		
		screeningsHealthAssessmentPageObj = new ScreeningsHealthAssessmentPage();
		screeningsHealthAssessmentPageObj.setCompleteScreeningsHA(hAData);
		haBasePageObj.clickNextBtn();
		hA_assert.assertTrue(haBasePageObj.isHAComplete());
		
		logger.info("Successfully logged out of the application.");
	}
	
	/** This method runs after all test from the class have run */
	@AfterClass
	public void classTearDown() {
		logger.info("Inside the classTearDown() method for HealthAssessmentNoBiometrics class...");
		hAPageObj = null;
		haBasePageObj = null;
		tobaccoUseHAPageObj = null;
		substancesHealthAssessmentPageObj = null;
		activityHealthAssessmentPageObj = null;
		dietHealthAssessmentPageObj = null;
		wellBeingHealthAssessmentPageObj = null;
		conditionsHealthAssessmentPageObj = null;
		screeningsHealthAssessmentPageObj = null;
		hAData = null;
		logger.info("Exiting the classTearDown() method for HealthAssessmentNoBiometrics class \n\n");
	}

}
