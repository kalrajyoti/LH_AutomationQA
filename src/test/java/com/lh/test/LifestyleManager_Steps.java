package com.lh.test;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import com.lh.dao.ILifestyleManagerDao;
import com.lh.dao.LifestyleManagerDaoImpl;
import com.lh.pages.authenticated.lifestyleManager.LifestyleManagerPage;
import com.lh.pages.unauthenticated.LogOutPage;
import com.lh.test.base.AuthBaseTestClass;
import com.lh.testConfig.TestProperty;

@Listeners(com.lh.helper.LHTestListener.class)
public class LifestyleManager_Steps extends AuthBaseTestClass {

	private static Logger logger = LogManager
			.getLogger(LifestyleManager_Steps.class);
	
	private Map<String, String> LifestyleManagerData;
	
	private static final String password = "password";
	private static final String username = "username";
	private LifestyleManagerPage lifestylePageObj;
	private Assertion LM_Assert = new Assertion();
	String testName;
	private String stepsval = "Steps";
	private String exerciseval = "Exercise";
	private String nutritionval = "Nutrition";
	private String sleepval = "Sleep";
	private String editedStepsVal = "editedSteps";
	private String partiallifestyleManager_URL = "MemberServices/health/ScreeningGraphPage.aspx";
	@BeforeClass
	public void initClass() {
		logger.info("\n Inside the init() method for Lifestyle Manager test class...");
		lifestylePageObj = new LifestyleManagerPage();
		logger.info("Inside the initClass() method for Lifestyle Manager class...");
		LifestyleManagerData = readexcelsheet("LifestyleManager");
		ILifestyleManagerDao daoObj = new LifestyleManagerDaoImpl();
		daoObj.resetLifestyleManagerForUser(LifestyleManagerData.get(username));
		logger.info("Exiting the initClass() method for Lifestyle Manager class \n\n");
		doLogin(LifestyleManagerData.get(username), LifestyleManagerData.get(password),"Portal","Portal");
		lifestylePageObj.clickLM();
		logger.info("Calling the loginPageObj.loginAs(username, password) method");
		lifestylePageObj.verifyURL(partiallifestyleManager_URL);
	}

	/** This method runs before each test from the class runs */
	@BeforeMethod
	public void init(Method method) throws Exception {
		Thread.sleep(TestProperty.THREAD_SLEEP);
		testName = method.getName();
		logger.info("Exiting the init() method for Lifestyle Manager test class \n");
	}

	@Test(description = "Verify the Dashboard and Logs link")
	public void verifyDashboardLogsLink() {
		LM_Assert.assertTrue(lifestylePageObj.dashboardLink());
		LM_Assert.assertTrue(lifestylePageObj.logslink());
	}

	@Test(description = "Verify Steps page", dependsOnMethods = "verifyDashboardLogsLink")
	public void stepsTypePage() {
		LM_Assert.assertTrue(lifestylePageObj.verifyLinks(stepsval));
	}

	@Test(description = "Verify exercise page",dependsOnMethods = "stepsTypePage")
	public void exerciseTypePage() {
		LM_Assert.assertTrue(lifestylePageObj.verifyLinks(exerciseval));
	}

	@Test(description = "Verify nutrition page",dependsOnMethods = "exerciseTypePage")
	public void nutritionTypePage() {
		LM_Assert.assertTrue(lifestylePageObj.verifyLinks(nutritionval));
	}

	@Test(description = "Verify sleep page",dependsOnMethods = "nutritionTypePage")
	public void sleepTypePage() {
		LM_Assert.assertTrue(lifestylePageObj.verifyLinks(sleepval));
	}

	@Test(description = "Verify Added Steps Entry")
	public void verifyAddedEntry_Steps() throws ParseException {
		lifestylePageObj.addEntry_Steps(LifestyleManagerData);
		LM_Assert.assertTrue(lifestylePageObj.verifyAddedEntrySteps(
				LifestyleManagerData, "verifyStepsData"));

	}

	@Test(description = "Verify edited steps entry", dependsOnMethods = "verifyAddedEntry_Steps")
	public void verifyEditSteps() {
		LM_Assert.assertTrue(lifestylePageObj
				.editStepsEntry(LifestyleManagerData.get(editedStepsVal)));
	}

	@Test(description = "Delete and verify deleted entry", dependsOnMethods = "verifyEditSteps")
	public void verifydeleteStepsEntry() {
		LM_Assert.assertTrue(lifestylePageObj.deleteStepsEntry());
	}

	/**
	 * This method runs after all test from the class have run
	 */
	@AfterClass
	public void classTearDown() {
		
		lifestylePageObj = null;
		LifestyleManagerData = null;
		LogOutPage logoutPageObj = new LogOutPage();
		logoutPageObj.clickLogoutLink("Portal");
		setcurrentContext(null, null, false);
		logger.info("Exiting the classTearDown() method for LifestyleManager_Steps class \n\n");

	}

}
