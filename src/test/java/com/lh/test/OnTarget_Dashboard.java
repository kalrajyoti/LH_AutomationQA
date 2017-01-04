package com.lh.test;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import com.lh.dao.IOnTargetDao;
import com.lh.dao.OnTargetDaoImpl;
import com.lh.pages.authenticated.ontarget.OnTargetPlan;
import com.lh.test.base.AuthBaseTestClass;
import com.lh.utils.SeleniumUtil;

/**
 * <h2>Define test for OnTarget here!</h2> 
 * <p>
 * 
 * @author pranali.jain
 * @version 1.0
 * @since 2015-05-02
 */
@Listeners(com.lh.helper.LHTestListener.class)
public class OnTarget_Dashboard extends AuthBaseTestClass {

	private  final String VALID_PASSWORD 					= "validPassword";
	private  final String VALID_USERNAME 					= "validUsername";
	private  Logger logger 								= LogManager.getLogger(OnTarget_Dashboard.class);
	private OnTargetPlan onTargetPageObj;
	private Map<String, String> onTargetData;
	private Assertion onTarget_assert 							= new Assertion();
	IOnTargetDao daoObj;
	private final String EAT_WELL						= "Eat Well";
	private final String GET_MOVING						= "Get Moving";
	private final String MANAGE_YOUR_WEIGHT				= "Manage Your Weight";
	private final String MASTER_STRESS					= "Master Stress";
	private final String QUIT_SMOKING					= "Quit Smoking";
	private final String partialOnTarget_URL 							= "Ontarget/YourTarget/Plan";
	/** This method runs before the first test from the class runs */
	@BeforeClass
	public void initClass() {
		logger.info("Inside the initClass() method for OnTarget class...");
		onTargetData = readexcelsheet("OnTarget");
		daoObj = new OnTargetDaoImpl();
		daoObj.resetUserOnTargetData(onTargetData.get(VALID_USERNAME));
		doLogin(onTargetData.get(VALID_USERNAME), onTargetData.get(VALID_PASSWORD),"Portal","OnTarget");
		onTargetPageObj = new OnTargetPlan();
		onTargetPageObj.clickOnTarget();
		onTargetPageObj.verifyURL(partialOnTarget_URL);
		logger.info("Exiting the initClass() method for OnTarget class \n\n");
	}
	
	/** Verify that the OnTarget Plan page opens */
	@Test(description = " Verify that the OnTarget Plan page opens", groups = {"OnTarget","smoke", "sanity"})
	public void verifyOntargetPlanPage() {
		onTarget_assert.assertTrue(onTargetPageObj.verifyDashboardLinkIsPresent(),"The \"Dashboard\" Link is present");
		onTarget_assert.assertTrue(onTargetPageObj.verifyYourTargetLinkIsPresent(),"The \"Your Target\" Link is present");
		onTarget_assert.assertTrue(onTargetPageObj.verifyAchievementLinkIsPresent(),"The \"Achievement\" Link is present");	
	}
	
	/** Verify all the associated plans appear on On.Target dashboard */
	@Test(description = "Verify all the associated plans appear on On.Target dashboard", groups = {"OnTarget","smoke"})
	public void verifyAssociatedPlansOnDashboard(){
		onTargetPageObj.clickDashboardLink();
		onTarget_assert.assertTrue(onTargetPageObj.verifyAssociatedPlanIsPresent(onTargetData));
	}
	
	@Test(description = "Expand/Collapse Eat Well plan and verify the expanded content", groups = {"OnTarget","smoke"})
	public void clickAndVerifyEatWellPlan(){
		onTargetPageObj.clickDashboardLink();
		SeleniumUtil.sleep(4);
		onTargetPageObj.clickOnGamePlan(EAT_WELL);
		onTarget_assert.assertTrue(onTargetPageObj.verifyEatWellExpandedContent(EAT_WELL));
		onTargetPageObj.collapseGamePlan();
	}
	
	@Test(description = "Expand/Collapse Get Moving plan and verify the expanded content", groups = {"OnTarget","smoke"})
	public void clickAndVerifyGetMovingPlan(){
		onTargetPageObj.clickDashboardLink();
		onTargetPageObj.clickOnGamePlan(GET_MOVING);
		onTarget_assert.assertTrue(onTargetPageObj.verifyExpandedGetMovingContent(GET_MOVING));
		onTargetPageObj.collapseGamePlan();
	}
	
	@Test(description = "Expand/Collapse Manage your Weight plan and verify the expanded content", groups = {"OnTarget","smoke"})
	public void clickAndVerifyManageYourWeightPlan(){
		onTargetPageObj.clickDashboardLink();
		onTargetPageObj.clickOnGamePlan(MANAGE_YOUR_WEIGHT);
		onTarget_assert.assertTrue(onTargetPageObj.verifyExpandedManageYourWeightContent(MANAGE_YOUR_WEIGHT));
		onTargetPageObj.collapseGamePlan();
	}
	
	@Test(description = "Expand/Collapse Master Stress plan and verify the expanded content", groups = {"OnTarget","smoke"})
	public void clickAndVerifyMasterStressPlan(){
		onTargetPageObj.clickDashboardLink();
		onTargetPageObj.clickOnGamePlan(MASTER_STRESS);
		onTarget_assert.assertTrue(onTargetPageObj.verifyExpandedMasterStressContent(MASTER_STRESS));
		onTargetPageObj.collapseGamePlan();
	}
	
	@Test(description = "Expand/Collapse Quit Smoking plan and verify the expanded content", groups = {"OnTarget","smoke"})
	public void clickAndVerifyQuitSmokingPlan(){
		onTargetPageObj.clickDashboardLink();
		onTargetPageObj.clickOnGamePlan(QUIT_SMOKING);
		onTarget_assert.assertTrue(onTargetPageObj.verifyExpandedQuitSmokingContent(QUIT_SMOKING));
		onTargetPageObj.collapseGamePlan();
	}

	/** 
	 * This method runs after all test from the class have run 
	 */
	@AfterClass
	public void classTearDown() {
		onTargetPageObj = null;
		onTargetData = null;
		daoObj = null;
		logger.info("Exiting the classTearDown() method for OnTarget class \n\n");	
	}
}
