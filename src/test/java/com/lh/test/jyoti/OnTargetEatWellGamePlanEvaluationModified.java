package com.lh.test.jyoti;

import static com.lh.helper.DriverFactory.driver;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import com.lh.dao.IOnTargetDao;
import com.lh.dao.OnTargetDaoImpl;
import com.lh.pages.authenticated.ontarget.OnTargetPlan;
import com.lh.pages.authenticated.ontarget.jyoti.OnTargetPlanmodified;
import com.lh.test.base.jyoti.AuthBaseTestClassModified;
import com.lh.utils.SeleniumUtil;
import com.lh.utils.jyoti.SeleniumUtilModified;

public class OnTargetEatWellGamePlanEvaluationModified extends AuthBaseTestClassModified {
	
	private static final String VALID_PASSWORD 					= "validPassword";
	private static final String USER_STARTING_EAT_WELL_PLAN 	= "userStartingGamePlan";
	private OnTargetPlanmodified onTargetPageObj;
	private Map<String, String> onTargetData;
	private Assertion onTarget_assert 							= new Assertion();
	IOnTargetDao daoObj;
	private static final String EAT_WELL						= "eatWellGamePlan";
	private static final String LEVEL_1							= "level1OfGamePlan";
	private static final String LEVEL_2							= "level2OfGamePlan";
	private static final String LEVEL_3							= "level3OfGamePlan";
	private static final String LEVEL_2_HEADING					= "level2Heading";
	private static final String LEVEL1_ACTIVITY_1_NAME			= "nutritionLabelEatWell";
	private static final String LEVEL1_ACTIVITY_2_NAME			= "shopSmartEatWell";
	private final String partialOnTarget_URL 							= "Ontarget/YourTarget/Plan";
	
	/** This method runs before the first test from the class runs */
	@BeforeClass
	public void initClass() {
		Reporter.log("Inside the initClass() method for OnTargetEatWellGamePlanEvaluation class...");
		onTargetData = readexcelsheet("OnTarget");
		daoObj = new OnTargetDaoImpl();
		daoObj.resetUserOnTargetData(onTargetData.get(USER_STARTING_EAT_WELL_PLAN));
		daoObj = null;
		doLogin(onTargetData.get(USER_STARTING_EAT_WELL_PLAN), onTargetData.get(VALID_PASSWORD),"Portal", "OnTarget");
		onTargetPageObj = new OnTargetPlanmodified();
		onTargetPageObj.clickOnTarget();
		onTargetPageObj.verifyURL(partialOnTarget_URL);
		Reporter.log("Exiting the initClass() method for OnTargetEatWellGamePlanEvaluation class \n\n");
	}
	@Test(description = "Start Eat Well game plan by clicking Get Started button")
	public void clickEatWellGetStartedBtn(){
		onTargetPageObj.clickDashboardLink();
		onTargetPageObj.clickOnGamePlan(onTargetData.get(EAT_WELL));
		SeleniumUtilModified.sleep(5);
		onTargetPageObj.clickGamePlanGetStartedBtn(onTargetData.get(EAT_WELL));
	}
	@Test(description = "Verify Your Target page after user started Eat Well plan", dependsOnMethods = "clickEatWellGetStartedBtn")
	public void verifyYourTargetPageForEatWellPlan(){
		//onTarget_assert.assertTrue(onTargetPageObj.verifyEnabledandLockedLevels(onTargetData.get(LEVEL_1),onTargetData.get(LEVEL_2),onTargetData.get(LEVEL_3)));
		//onTarget_assert.assertTrue(onTargetPageObj.verifyLevelProgressToolTipIconAndText());
	}
	@Test(description = "Click on Get Started button to start the level", dependsOnMethods = "verifyYourTargetPageForEatWellPlan")
	public void startAndCompleteLevel1(){
		onTargetPageObj.clickGetStartedOrNextLevelBtn();
		onTargetPageObj.selectRadioBtnForGoalsAndRelatedActivity();
		onTargetPageObj.clickLevelSubmitBtn();
		onTarget_assert.assertTrue(onTargetPageObj.verifyGoalsEditLinkIsPresent(), "Edit link for editing goals is not present on Your Target page..");
		onTargetPageObj.startActivityOfLevel1(onTargetData.get(LEVEL1_ACTIVITY_1_NAME));
		onTargetPageObj.clickGetStartedOrNextLevelBtn();
		onTargetPageObj.selectAnswerForLevel1ActivityNutritionLabelHavingListOfRadioBtns1();//need to ask
		onTargetPageObj.clickLevelSubmitBtn();
		onTarget_assert.assertTrue(onTargetPageObj.verifyReviewLinkOnActivity(onTargetData.get(LEVEL1_ACTIVITY_1_NAME)),"Review link is not visible for "+onTargetData.get(LEVEL1_ACTIVITY_1_NAME) + " .");
		onTargetPageObj.startOtherActivitiesOfGPLevel(onTargetData.get(LEVEL1_ACTIVITY_2_NAME));
		onTargetPageObj.selectShopSmartChecklist();
		onTargetPageObj.clickOnActivitySubmitBtn();
		onTargetPageObj.clickOnContinueBtn();
	}	
   @Test(description = "Verify Level 2 is started after completion of Level 1", dependsOnMethods = "startAndCompleteLevel1")
   public void verifyLevel2Started()
   {
			onTarget_assert.assertTrue(onTargetPageObj.verifyLevel2Started(onTargetData.get(LEVEL_2_HEADING)));
   }
   @Test(description = "Start and complete the level 2", dependsOnMethods = "verifyLevel2Started")
	   public void startAndCompleteLevel2(){
	   driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);//how to get page load timeout
	   onTargetPageObj.clickGetStartedOrNextLevelBtn();
	   onTargetPageObj.clickOnAddNewRow(1);
	   onTargetPageObj.clickLevelSubmitBtn();
	   onTargetPageObj.startAndCompleteYourWhyActivity();
	   onTargetPageObj.clickOnNextArrowToLoadMoreActivities();
	   onTargetPageObj.startOvercomeVeggieHurdleActivityOfLevel2();
	   onTargetPageObj.clickGetStartedOrNextLevelBtn();
	   onTargetPageObj.clickOnNextAndSubmitBtnsOfLevelActivity(3);
	   onTargetPageObj.clickSubmitOfOvercomeVeggieHurdleActivity();
	   onTargetPageObj.clickOnContinueBtn();
	}
   @Test(description = "Start and complete the level 3", dependsOnMethods = "startAndCompleteLevel2")
	public void startAndCompleteLevel3(){
	   driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		SeleniumUtil.sleep(14);
		onTargetPageObj.switchToQuizFrame();
		onTargetPageObj.completeActivityHavingQuizInTabularForm();
		onTargetPageObj.switchToDefaultContent();	
		onTargetPageObj.clickOnNextArrowIcon();
		onTargetPageObj.clickOnCompleteButton();
		onTargetPageObj.clickOnActivitySubmitBtn();
		onTargetPageObj.startPictureThisActivityOfLevel3();
		onTargetPageObj.clickGetStartedOrNextLevelBtn();
		onTargetPageObj.completePictureThisActivityOfLevel3(2);
		onTargetPageObj.clickLevelSubmitBtn();
		onTargetPageObj.startPortionDistortionActivityOfLevel3();
		onTargetPageObj.completePortionDistortionActivity();
	}
   @Test(description = "Submit the game plan after completing all the 3 levels", dependsOnMethods = "startAndCompleteLevel3")
	public void submitGamePlan(){
	   onTargetPageObj.completeEaTwellPostPlanSurvey();
		onTarget_assert.assertTrue(onTargetPageObj.verifyGoalsEditLinkIsNotPresent(), "Edit link for editing goals is present after completing game plan...");
	}
   }
   
   
   
   

