package com.lh.test.jyoti;

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
import com.lh.pages.authenticated.ontarget.jyoti.OnTargetPlanmodified;
import com.lh.test.OnTarget_YourTarget;
import com.lh.test.base.jyoti.AuthBaseTestClassModified;

@Listeners(com.lh.helper.LHTestListener.class)
public class OnTarget_YourTargetModified extends AuthBaseTestClassModified {
	private static final String VALID_PASSWORD 					= "validPassword";
	private static final String USER_WITH_NO_PLAN_STARTED 		= "userWithNoPlanStarted";
	private static Logger logger 								= LogManager.getLogger(OnTarget_YourTarget.class);
	private OnTargetPlanmodified onTargetPageObj;
	private Map<String, String> onTargetData;
	private Assertion onTarget_assert 							= new Assertion();
	IOnTargetDao daoObj;
	private final String partialOnTarget_URL 							= "Ontarget/YourTarget/Plan";
	
	/** This method runs before the first test from the class runs */
	@BeforeClass
	public void initClass() {
		logger.info("Inside the initClass() method for OnTarget class...");
		onTargetData = readexcelsheet("OnTarget");		
		daoObj = new OnTargetDaoImpl();
		daoObj.resetUserOnTargetData(onTargetData.get(USER_WITH_NO_PLAN_STARTED));
		daoObj = null;
		doLogin(onTargetData.get(USER_WITH_NO_PLAN_STARTED), onTargetData.get(VALID_PASSWORD),"Portal", "OnTarget");
		onTargetPageObj = new OnTargetPlanmodified();
		onTargetPageObj.clickOnTarget();
		onTargetPageObj.verifyURL(partialOnTarget_URL);
		logger.info("Exiting the initClass() method for OnTarget class \n\n");
}
	@Test(description = "Verify Your Target tab")
	public void clickAndVerifyYourTarget(){
		onTargetPageObj.clickYourTargetLink();
		onTarget_assert.assertTrue(onTargetPageObj.verifyYourTargetGetStarted());		
	}
	@Test(description = "Click on Get Started button present on Your Target page and verify user is redirected to Dashboard")
	public void clickOnGetStartedBtnAndVerifyDashboard(){
		onTargetPageObj.clickYourTargetLink();
		onTargetPageObj.clickGetStartedYourTarget();
		onTarget_assert.assertTrue(onTargetPageObj.verifyAssociatedPlanIsPresent(onTargetData));
	}
	/** 
	 * This method runs after all test from the class have run 
	 */
	@AfterClass
	public void classTearDown() {
		onTargetPageObj = null;
		onTargetData = null;	
		logger.info("Exiting the classTearDown() method for OnTarget class \n\n");
	}
}
