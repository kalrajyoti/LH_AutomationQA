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
import com.lh.test.base.AuthBaseTestClass;
import com.lh.utils.DateUtil;

public class OnTargetMasterStressGamePlanEvaluation extends AuthBaseTestClass {

	private static final String VALID_PASSWORD 								= "validPassword";
	private static final String USER_STARTING_MASTER_STRESS_PLAN 			= "userStartingGamePlan";
	private OnTargetPlan onTargetPageObj;
	private Map<String, String> onTargetData;
	private Assertion onTarget_assert 										= new Assertion();
	IOnTargetDao daoObj;
	private static final String MASTER_STRESS								= "masterStressGamePlan";
	private static final String LEVEL1_ACTIVITY_1_NAME						= "goodBadAndTheStressfulMasterStress";
	private static final String LEVEL1_ACTIVITY_2_NAME						= "stressAndYourBodyMasterStress";
	private static final String COMPLETE_PERCENT_AFTER_ACTIVITY1_OF_LEVEL2 	= "completePercentAfterCompletingActivity1OfLevel2";
	private static final String LEVEL_2_ACTIVITY_1_NAME						= "stressAndEatingMasterStress";
	private static final String LEVEL_2_ACTIVITY_2_NAME						= "yourRelationshipsMasterStress";
	private static final String COMPLETE_PERCENT_AFTER_LEVEL2				= "completePercentAfterLevel2";
	private static final String LEVEL_3_ACTIVITY_1_NAME						= "focusOnThePositiveMasterStress";
	private static final String LEVEL_3_ACTIVITY_2_NAME						= "findYourHappyPlaceMasterStress";
	private static final String GAME_PLAN_COMPLETE_STATUS					= "gamePlanCompleteStatus";
	private static final String LEVEL_3_HEADING								= "level3Heading";
	private final String partialOnTarget_URL 							= "Ontarget/YourTarget/Plan";
	@BeforeClass
	public void initClass() {
		Reporter.log("Inside the initClass() method for OnTargetMasterStressGamePlanEvaluation class...");
		onTargetData = readexcelsheet("OnTarget");
		daoObj = new OnTargetDaoImpl();
		daoObj.resetUserOnTargetData(onTargetData.get(USER_STARTING_MASTER_STRESS_PLAN));
		daoObj = null;
		doLogin(onTargetData.get(USER_STARTING_MASTER_STRESS_PLAN), onTargetData.get(VALID_PASSWORD),"Portal", "OnTarget");
		onTargetPageObj = new OnTargetPlan();
		onTargetPageObj.clickOnTarget();
		onTargetPageObj.verifyURL(partialOnTarget_URL);
		Reporter.log("Exiting the initClass() method for OnTargetMasterStressGamePlanEvaluation class \n\n");
	}

	@Test(description = "Start Master Stress game plan by clicking Get Started button", groups = {"regression"})
	public void clickMasterStressGetStartedBtn(){
		onTargetPageObj.clickDashboardLink();
		onTargetPageObj.clickOnGamePlan(onTargetData.get(MASTER_STRESS));
		onTargetPageObj.clickGamePlanGetStartedBtn(onTargetData.get(MASTER_STRESS));
	}
	
	@Test(description = "Click on Get Started button to start the level", dependsOnMethods = "clickMasterStressGetStartedBtn", groups = {"regression"})
	public void startAndCompleteLevel1(){
		onTargetPageObj.startAndCompleteGoalsActivityOfMasterStressPlan();
		onTargetPageObj.startAndCompleteGoodBadAndStressfulActivityOfMasterStressPlan(onTargetData.get(LEVEL1_ACTIVITY_1_NAME),2);
		onTargetPageObj.startAndCompleteStressYourBodyActivityOfMasterStressPlan(onTargetData.get(LEVEL1_ACTIVITY_2_NAME),1);
	}
	
	@Test(description = "Start and complete 3 activities of Level 2", dependsOnMethods = "startAndCompleteLevel1", groups = {"regression"})
	public void startAndCompleteLevel2(){
		onTargetPageObj.startAndCompleteYourResponseActivityOfMAsterStresGP(1);
		onTargetPageObj.clickDashboardLink();
		onTarget_assert.assertTrue(onTargetPageObj.verifyCompletePercentOfGP(onTargetData.get(COMPLETE_PERCENT_AFTER_ACTIVITY1_OF_LEVEL2)), "Complete percent of game plan is incorrect...");
		
		onTargetPageObj.clickYourTargetLink();
		onTargetPageObj.startAndCompleteStressAndEatingactivityOfMasterStress(onTargetData.get(LEVEL_2_ACTIVITY_1_NAME),3);
		onTargetPageObj.startAndCompleteYourRelationshipsActivityOfMasterStress(onTargetData.get(LEVEL_2_ACTIVITY_2_NAME),1);
		
		onTargetPageObj.clickDashboardLink();
		onTarget_assert.assertTrue(onTargetPageObj.verifyCompletePercentOfGP(onTargetData.get(COMPLETE_PERCENT_AFTER_LEVEL2)), "Complete percent of game plan is incorrect...");
	}
	
	@Test(description = "Start and complete 3 activities of level 3", dependsOnMethods = "startAndCompleteLevel2", groups = {"regression"})
	public void startAndCompleteLevel3(){
		onTargetPageObj.clickYourTargetLink();
		onTargetPageObj.startAndCompleteSTOPActivityOfMasterStress(1);
		onTargetPageObj.startAndCompleteFocusOnPositivetActivityOfMasterStress(onTargetData.get(LEVEL_3_ACTIVITY_1_NAME),1);
		onTargetPageObj.startAndCompleteFindYourHappyPlaceActivityOfMasterStress(onTargetData.get(LEVEL_3_ACTIVITY_2_NAME),1);
		onTargetPageObj.submitGamePlan();
	}
	
	@Test(description = "Fill the Post plan survey", dependsOnMethods = "startAndCompleteLevel3", groups = {"regression"})
	public void fillPostPlanSurvey(){
		onTargetPageObj.completeMasterStressPostPlanSurvey(1);
		onTargetPageObj.clickDashboardLink();
		onTarget_assert.assertTrue(onTargetPageObj.verifyGamePlanCompletetatus(onTargetData.get(GAME_PLAN_COMPLETE_STATUS)), "Complete status of game plan is not updated...");
	}
	
	@Test(description = "Verify completed activity in Achievements tab", dependsOnMethods = "fillPostPlanSurvey", groups = {"regression"})
	public void verifyCompletedActivityInAchievementsTab(){
		onTargetPageObj.clickOnAchievementsTab();
		onTarget_assert.assertTrue(onTargetPageObj.verifyAchievementCardGamePlanHeading(onTargetData.get(MASTER_STRESS)), "Achieved game plan heading is incorrect");
		onTarget_assert.assertTrue(onTargetPageObj.verifyAchievementCardCompletionDate(DateUtil.getCurrentDateFormatted()), "Completion date doesn't match the curent date");
		onTargetPageObj.clickOnReviewLnkOfAchievedActivity();
		onTarget_assert.assertTrue(onTargetPageObj.verifyAchievedGPRedirectsToLastLevel(onTargetData.get(LEVEL_3_HEADING)), "Achievement card redirected incorrectly");
	}
	
	/** 
	 * This method runs after all test from the class have run 
	 */
	@AfterClass
	public void classTearDown() {
		Reporter.log("Inside the classTearDown() method for OnTargetMasterStressGamePlanEvaluation class...");		
		onTargetPageObj = null;
		onTargetData = null;
		Reporter.log("Exiting the classTearDown() method for OnTargetMasterStressGamePlanEvaluation class \n\n");
	}
	
}
