package com.lh.test;

import java.util.Map;

import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import com.lh.dao.IOnTargetDao;
import com.lh.dao.OnTargetDaoImpl;
import com.lh.pages.authenticated.ontarget.OnTargetPlan;
import com.lh.pages.unauthenticated.LogOutPage;
import com.lh.test.base.AuthBaseTestClass;

public class OnTargetQuitSmokingGamePlanEvaluation extends AuthBaseTestClass{

	private static final String VALID_PASSWORD 							= "validPassword";
	private static final String USER_STARTING_QUIT_SMOKING_PLAN 		= "userStartingGamePlan";
	private OnTargetPlan onTargetPageObj;
	private Map<String, String> onTargetData;
	private Assertion onTarget_assert 									= new Assertion();
	IOnTargetDao daoObj;
	private static final String QUIT_SMOKING							= "quitSmokingGamePlan";
	private static final String LEVEL_1									= "Level 1";
	private static final String LEVEL_2									= "Level 2";
	private static final String LEVEL_3									= "Level 3";
	private static final String LEVEL1_ACTIVITY_1_NAME					= "cleanHouseQuitSmoking";
	private static final String LEVEL2_ACTIVITY_1_NAME					= "quitMedsQuitSmoking";
	private static final String LEVEL2_ACTIVITY_2_NAME					= "smokingAndYouQuitSmoking";
	private static final String LEVEL2_ACTIVITY_3_NAME					= "focusOnThePositiveQuitSmoking";
	private static final String continueTextOfAlreadyStartedActivity	= "continueTextOfAlreadyStartedActivity";
	private static final String LEVEL3_ACTIVITY_1_NAME					= "stressAndSmokingQuitSmoking";
	private static final String LEVEL3_ACTIVITY_2_NAME					= "dealingWithEmotionsQuitSmoking";
	LogOutPage logoutPageObj;
	private final String partialOnTarget_URL 							= "Ontarget/YourTarget/Plan";
	@BeforeClass
	public void initClass() {
		Reporter.log("Inside the initClass() method for OnTargetQuitSmokingGamePlanEvaluation class...");
		onTargetData = readexcelsheet("OnTarget");
		daoObj = new OnTargetDaoImpl();
		daoObj.resetUserOnTargetData(onTargetData.get(USER_STARTING_QUIT_SMOKING_PLAN));
		daoObj = null;
		doLogin(onTargetData.get(USER_STARTING_QUIT_SMOKING_PLAN), onTargetData.get(VALID_PASSWORD),"Portal", "OnTarget");
		onTargetPageObj = new OnTargetPlan();
		onTargetPageObj.clickOnTarget();
		onTargetPageObj.verifyURL(partialOnTarget_URL);
		Reporter.log("Exiting the initClass() method for OnTargetQuitSmokingGamePlanEvaluation class \n\n");
	}

	@Test(description = "Start Eat Well game plan by clicking Get Started button", groups = {"regression"})
	public void clickQuitSmokingGetStartedBtn(){
		onTargetPageObj.clickDashboardLink();
		onTargetPageObj.clickOnGamePlan(onTargetData.get(QUIT_SMOKING));
		onTargetPageObj.clickGamePlanGetStartedBtn(onTargetData.get(QUIT_SMOKING));
		onTarget_assert.assertTrue(onTargetPageObj.verifyEnabledandLockedLevels(LEVEL_1, LEVEL_2, LEVEL_3));
		onTargetPageObj.clickDashboardLink();
		onTargetPageObj.clickOnGamePlan(onTargetData.get(QUIT_SMOKING));
		onTarget_assert.assertTrue(onTargetPageObj.verifyContinueBtnOnRevistingStartedGP(onTargetData.get(QUIT_SMOKING)), "Continue button is not present on for already started game plan..");
	}
	
	@Test(description = "Start and complete level 1", dependsOnMethods = "clickQuitSmokingGetStartedBtn", groups = {"regression"})
	public void startAndCompleteLevel1(){
		onTargetPageObj.clickYourTargetLink();
		onTargetPageObj.startAndCompleteSetYourQuitDateOfQuitSmoking();
		onTargetPageObj.startAndCompleteYourWhyActivity();
		onTargetPageObj.startAndCompleteCleanHouseOfQuitSmoking(onTargetData.get(LEVEL1_ACTIVITY_1_NAME));
	}
	
	@Test(description = "Start and complete level 2", dependsOnMethods = "startAndCompleteLevel1", groups = {"regression"})
	public void startAndCompleteLevel2(){
		onTargetPageObj.startAndCompleteLearnHowNicotineAffectsOfQuitSmoking(1);
		onTargetPageObj.startAndCloseQuitMedsActivityOfQuitSmoking(onTargetData.get(LEVEL2_ACTIVITY_1_NAME));
		onTarget_assert.assertTrue(onTargetPageObj.verifyContinueLnkOnRevistingAlreadyStartedActivity(onTargetData.get(LEVEL2_ACTIVITY_1_NAME), onTargetData.get(continueTextOfAlreadyStartedActivity)), "Continue link is not present...");
		onTargetPageObj.startAndCompleteSmokingandYouOfQuitSmoking(onTargetData.get(LEVEL2_ACTIVITY_2_NAME));
		onTargetPageObj.startAndCompleteFocusOnPositiveOfQuitSmoking(onTargetData.get(LEVEL2_ACTIVITY_3_NAME));
	}
	
	@Test(description = "Start and complete level 2", dependsOnMethods = "startAndCompleteLevel2", groups = {"regression"})
	public void startAndCompleteLevel3(){
		onTargetPageObj.clickDashboardLink();
		onTargetPageObj.clickYourTargetLink();
		onTargetPageObj.startAndCompleteEverybodySlipsOfQuitSmoking();
		onTargetPageObj.startAndCompleteStressAndSmokingOfQuitSmoking(onTargetData.get(LEVEL3_ACTIVITY_1_NAME));
		onTargetPageObj.startAndCompleteDealingWithEmotionsOfQuitSmoking(onTargetData.get(LEVEL3_ACTIVITY_2_NAME));
		onTargetPageObj.submitGamePlan();
	}
	
	@Test(description = "Fill the post plan survey", dependsOnMethods = "startAndCompleteLevel3", groups = {"regression"})
	public void fillPostPlanSurvey(){
		onTargetPageObj.completeQuitSmokingPostPlanSurvey();
		logoutPageObj = new LogOutPage();
		logoutPageObj.clickLogoutLink("OnTarget");
		setcurrentContext(null, null, false);
	}
	
	/** 
	 * This method runs after all test from the class have run 
	 */
	@AfterClass
	public void classTearDown() {
		Reporter.log("Inside the classTearDown() method for OnTargetQuitSmokingGamePlanEvaluation class...");	
		onTargetPageObj = null;
		onTargetData = null;
		Reporter.log("Exiting the classTearDown() method for OnTargetQuitSmokingGamePlanEvaluation class \n\n");
	}
}
