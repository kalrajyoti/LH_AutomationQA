package com.lh.test;

import java.util.Map;

import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.lh.dao.IOnTargetDao;
import com.lh.dao.OnTargetDaoImpl;
import com.lh.pages.authenticated.ontarget.OnTargetPlan;
import com.lh.test.base.AuthBaseTestClass;

public class OnTargetGetMovingGamePlanEvaluation extends AuthBaseTestClass{

	private static final String VALID_PASSWORD 					= "validPassword";
	private static final String USER_STARTING_GET_MOVING_PLAN 	= "userStartingGamePlan";
	private OnTargetPlan onTargetPageObj;
	private Map<String, String> onTargetData;
	String testName;
	IOnTargetDao daoObj;
	private static final String GET_MOVING						= "getMovingGamePlan";
	private static final String LEVEL1_ACTIVITY_1_NAME			= "howIntenseIsItGetMoving";
	private static final String LEVEL1_ACTIVITY_2_NAME			= "powerUpGetMoving";
	private static final String LEVEL2_ACTIVITY_1_NAME			= "sitLessMoveMoreGetMoving";
	private static final String LEVEL3_ACTIVITY_1_NAME			= "winningTheWarGetMoving";
	private static final String LEVEL3_ACTIVITY_2_NAME			= "fuelForFitnessGetMoving";
	private final String partialOnTarget_URL 							= "Ontarget/YourTarget/Plan";
	/** This method runs before the first test from the class runs */
	@BeforeClass
	public void initClass()  throws Exception {
		Reporter.log("Inside the initClass() method for OnTargetGetMovingGamePlanEvaluation class......");
		onTargetData = readexcelsheet("OnTarget");
		daoObj = new OnTargetDaoImpl();
		daoObj.resetUserOnTargetData(onTargetData.get(USER_STARTING_GET_MOVING_PLAN));
		daoObj = null;
		doLogin(onTargetData.get(USER_STARTING_GET_MOVING_PLAN), onTargetData.get(VALID_PASSWORD),"Portal", "OnTarget");
		onTargetPageObj = new OnTargetPlan();
		onTargetPageObj.clickOnTarget();
		onTargetPageObj.verifyURL(partialOnTarget_URL);
		Reporter.log("Exiting the initClass() method for OnTargetGetMovingGamePlanEvaluation class \n\n");
	}
	
	@Test(description = "Start Get Moving game plan by clicking Get Started button")
	public void clickGetMovingGetStartedBtn(){
		onTargetPageObj.clickDashboardLink();
		onTargetPageObj.clickOnGamePlan(onTargetData.get(GET_MOVING));
		onTargetPageObj.clickGamePlanGetStartedBtn(onTargetData.get(GET_MOVING));
	}
	
	@Test(description = "Start and complete 3 activities of level 1", dependsOnMethods = "clickGetMovingGetStartedBtn")
	public void startAndCompleteLevel1(){
		onTargetPageObj.startAndCompleteGoalsActivityOfGetMovingPlan();
		onTargetPageObj.startAndCompleteHowIntenseIsItActivity(onTargetData.get(LEVEL1_ACTIVITY_1_NAME), 2);
		onTargetPageObj.startAndCompletePowerUpActivity(onTargetData.get(LEVEL1_ACTIVITY_2_NAME),8);
	}
	
	@Test(description = "Start and complete 3 activities of level 2", dependsOnMethods = "startAndCompleteLevel1")
	public void startAndCompleteLevel2(){
		onTargetPageObj.startAndCompleteHowActiveAreYouActivity(5);
		onTargetPageObj.startAndCompleteYourWhyActivity();
		onTargetPageObj.startAndCompleteSitLessMoveMoreActivity(onTargetData.get(LEVEL2_ACTIVITY_1_NAME),2);
	}
	
	@Test(description = "Start and complete 3 activities of level 3", dependsOnMethods = "startAndCompleteLevel2")
	public void startAndCompleteLevel3(){
		onTargetPageObj.startAndCompletePlanItActivity(1);
		onTargetPageObj.startAndCompleteWinningTheWarActivity(onTargetData.get(LEVEL3_ACTIVITY_1_NAME));
		onTargetPageObj.startAndCompleteFuelForFitnessActivity(onTargetData.get(LEVEL3_ACTIVITY_2_NAME));
		onTargetPageObj.submitGamePlan();
	}
	
	@Test(description = "Fill the post plan survey", dependsOnMethods = "startAndCompleteLevel3")
	public void fillPostPlanSurvey(){
		onTargetPageObj.completeGetMovingPostPlanSurvey(1);
	}

	/** 
	 * This method runs after all test from the class have run 
	 */
	@AfterClass
	public void classTearDown() {
		Reporter.log("Inside the classTearDown() method for OnTargetGetMovingGamePlanEvaluation class...");
		onTargetPageObj = null;
		onTargetData = null;
		Reporter.log("Exiting the classTearDown() method for OnTargetGetMovingGamePlanEvaluation class \n\n");
	}
}
