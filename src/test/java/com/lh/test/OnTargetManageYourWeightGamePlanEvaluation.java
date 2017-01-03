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

public class OnTargetManageYourWeightGamePlanEvaluation extends AuthBaseTestClass {	
	
	private static final String VALID_PASSWORD 							= "validPassword";
	private static final String USER_STARTING_MANAGE_YOUR_WEIGHTPLAN 	= "userStartingGamePlan";
	private OnTargetPlan onTargetPageObj;
	private Map<String, String> onTargetData;
	private Assertion onTarget_assert = new Assertion();
	IOnTargetDao daoObj;
	private static final String MANAGE_YOUR_WEIGHT				= "manageYourWeightGamePlan";
	private static final String LEVEL_1							= "level1OfGamePlan";
	private static final String LEVEL_2							= "level2OfGamePlan";
	private static final String LEVEL_3							= "level3OfGamePlan";
	private static final String LEVEL_2_HEADING					= "level2Heading";
	private static final String LEVEL_3_HEADING					= "level3Heading";
	private static final String LEVEL1_ACTIVITY_1_NAME			= "metabolismBasicsManageYourWeight";
	private static final String LEVEL1_ACTIVITY_2_NAME			= "yourWhyWeightManageYourWeight";
	private static final String LEVEL2_ACTIVITY_1_NAME			= "foodIsNotTheEnemyManageYourWeight";
	private static final String LEVEL2_ACTIVITY_2_NAME			= "lookingInTheMirrorManageYourWeight";
	private static final String LEVEL3_ACTIVITY_1_NAME			= "doingMoreThanBodyManageYourWeight";
	private static final String LEVEL3_ACTIVITY_2_NAME			= "findYourHappyPlaceManageYourWeight";
	private final String partialOnTarget_URL 							= "Ontarget/YourTarget/Plan";
	/** This method runs before the first test from the class runs */
	@BeforeClass
	public void initClass() {
		Reporter.log("Inside the initClass() method for OnTargetManageYourWeightGamePlanEvaluation class...");
		onTargetData = readexcelsheet("OnTarget");
		daoObj = new OnTargetDaoImpl();
		daoObj.resetUserOnTargetData(onTargetData.get(USER_STARTING_MANAGE_YOUR_WEIGHTPLAN));
		daoObj = null;
		doLogin(onTargetData.get(USER_STARTING_MANAGE_YOUR_WEIGHTPLAN), onTargetData.get(VALID_PASSWORD),"Portal", "OnTarget");
		onTargetPageObj = new OnTargetPlan();
		onTargetPageObj.clickOnTarget();
		onTargetPageObj.verifyURL(partialOnTarget_URL);
		Reporter.log("Exiting the initClass() method for OnTargetManageYourWeightGamePlanEvaluation class \n\n");
	}
	
	@Test(description = "Start Manage your Weight game plan by clicking Get Started button")
	public void clickManageYourWeightGetStartedBtn(){
		onTargetPageObj.clickDashboardLink();
		onTargetPageObj.clickOnGamePlan(onTargetData.get(MANAGE_YOUR_WEIGHT));
		onTargetPageObj.clickGamePlanGetStartedBtn(onTargetData.get(MANAGE_YOUR_WEIGHT));
	}
	
	@Test(description = "Verify Your Target page after user started Manage Your Weight plan", dependsOnMethods = "clickManageYourWeightGetStartedBtn")
	public void verifyYourTargetPageForManageYourWeightGamePlan(){
		onTarget_assert.assertTrue(onTargetPageObj.verifyEnabledandLockedLevels(onTargetData.get(LEVEL_1),onTargetData.get(LEVEL_2), onTargetData.get(LEVEL_3)));
		onTarget_assert.assertTrue(onTargetPageObj.verifyLevelProgressToolTipIconAndText());
	}
	
	@Test(description = "Start and complete 3 activities of level 1", dependsOnMethods = "verifyYourTargetPageForManageYourWeightGamePlan")
	public void startAndCompleteLevel1(){
		onTargetPageObj.startAndCompleteGoalsActivityOfManageYourWeightplan(1);
		onTargetPageObj.startAndCompleteMetabolismBasicsActivity(onTargetData.get(LEVEL1_ACTIVITY_1_NAME),6);		
		onTargetPageObj.startAndCompleteYourWhyWeightActivity(onTargetData.get(LEVEL1_ACTIVITY_2_NAME));
	}
	
	@Test(description = "Start and complete 3 activities of level 2", dependsOnMethods = "startAndCompleteLevel1")
	public void startAndCompleteLevel2(){
		onTarget_assert.assertTrue(onTargetPageObj.verifyTwoEnabledLevels(onTargetData.get(LEVEL_1),onTargetData.get(LEVEL_2), onTargetData.get(LEVEL_3)));
		onTarget_assert.assertTrue(onTargetPageObj.verifyLevel2Started(onTargetData.get(LEVEL_2_HEADING)));		
		onTargetPageObj.startAndCompleteEmotionalEatingActivity(1,5);
		onTargetPageObj.startAndCompleteFoodIsNotTheEnemyActivity(onTargetData.get(LEVEL2_ACTIVITY_1_NAME),1);
		onTargetPageObj.startAndCompleteLookingInTheMirrorActivity(onTargetData.get(LEVEL2_ACTIVITY_2_NAME),1);
	}
	
	@Test(description = "Start and complete 3 activities of Level 3", dependsOnMethods = "startAndCompleteLevel2")
	public void startAndCompleteLevel3(){
		onTarget_assert.assertTrue(onTargetPageObj.verifyAllLevelsAreEnabled(onTargetData.get(LEVEL_1),onTargetData.get(LEVEL_2), onTargetData.get(LEVEL_3)), "All the 3 levels are not enabled...");
		onTarget_assert.assertTrue(onTargetPageObj.verifyLevel2Started(onTargetData.get(LEVEL_3_HEADING)));
		onTargetPageObj.startAndCompleteExerciseAndCaloriesActivity(1);
		onTargetPageObj.startAndCompleteDoingMoreThanYourBodyGoodActivity(onTargetData.get(LEVEL3_ACTIVITY_1_NAME),6);
		onTargetPageObj.startAndCompleteFindYourHappyPlaceActivity(onTargetData.get(LEVEL3_ACTIVITY_2_NAME),1);
		onTargetPageObj.submitGamePlan();
	}
	
	@Test(description = "Fill the Post plan survey", dependsOnMethods = "startAndCompleteLevel3")
	public void fillPostPlanSurvey(){
		onTargetPageObj.completeManageYourWeightPostPlanSurvey(1);
		onTarget_assert.assertTrue(onTargetPageObj.verifyGoalsEditLinkIsNotPresent(), "Link to edit goal is appearing after submitting post plan survey");
	}
	
	/** 
	 * This method runs after all test from the class have run 
	 */
	@AfterClass
	public void classTearDown() {
		Reporter.log("Inside the classTearDown() method for OnTargetManageYourWeightGamePlanEvaluation class...");
		onTargetPageObj = null;
		onTargetData = null;
		Reporter.log("Exiting the classTearDown() method for OnTargetManageYourWeightGamePlanEvaluation class \n\n");
	}
}
