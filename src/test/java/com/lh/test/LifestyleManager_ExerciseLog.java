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
import com.lh.test.base.AuthBaseTestClass;

@Listeners(com.lh.helper.LHTestListener.class)
public class LifestyleManager_ExerciseLog extends AuthBaseTestClass{

	private static Logger logger 								= LogManager.getLogger(LifestyleManager_ExerciseLog.class);
	private Map<String, String> lifestyleManagerData;
	private static final String password 						= "password";
	private static final String username 						= "username";
	private LifestyleManagerPage lifestylePageObj;
	private Assertion LM_Assert = new Assertion();
	String testName;
	private static final String EDIT_DURATION					= "editDuration";
	private static final String ACTIVITY_NAME					= "activityName";
	private String partiallifestyleManager_URL = "MemberServices/health/ScreeningGraphPage.aspx";
	
	@BeforeClass
	public void initClass() {
		logger.info("\n Inside the init() method for LifestyleManager_ExerciseLog class...");
		lifestyleManagerData = readexcelsheet("LifestyleManager");
		ILifestyleManagerDao daoObj = new LifestyleManagerDaoImpl();
		daoObj.resetLifestyleManagerForUser(lifestyleManagerData.get(username));
		doLogin(lifestyleManagerData.get(username), lifestyleManagerData.get(password),"Portal","Portal");
		lifestylePageObj = new LifestyleManagerPage();
		lifestylePageObj.clickLM();
		lifestylePageObj.verifyURL(partiallifestyleManager_URL);
		logger.info("Exiting the initClass() method for Lifestyle Manager class \n\n");
	}

	/** This method runs before each test from the class runs */
	@BeforeMethod
	public void init(Method method) throws Exception {
		testName = method.getName();
		logger.info("Exiting the init() method for LifestyleManager_ExerciseLog class \n");
	}
	
	@Test(description = "Click on Exercise from Logs menu list")
	public void clickOnExerciseSubmenu(){
		lifestylePageObj.clickOnLogsLink();
		lifestylePageObj.clickOnExerciseLog();
	}
	
	@Test(description = "Add entry for current date", dependsOnMethods = "clickOnExerciseSubmenu")
	public void addEntryInExerciseLog() throws ParseException{
		lifestylePageObj.addEntry_Exercise(lifestyleManagerData);
		LM_Assert.assertTrue(lifestylePageObj.verifyLMData_Exercise(lifestyleManagerData,"verifyAddedData"));
	}
	
	@Test(description = "Edit the added entry", dependsOnMethods = "addEntryInExerciseLog")
	public void editAddedEntry(){
		lifestylePageObj.editAddedEntry_Exercise(lifestyleManagerData.get(EDIT_DURATION),lifestyleManagerData.get(ACTIVITY_NAME));
		LM_Assert.assertTrue(lifestylePageObj.verifyLMData_Exercise(lifestyleManagerData,"verifyEditedData"));
	}
	
	@Test(description = "Delete the added entry", dependsOnMethods = "editAddedEntry")
	public void deleteEntry(){
		lifestylePageObj.deleteEntry_Exercise(lifestyleManagerData.get(ACTIVITY_NAME));
		LM_Assert.assertTrue(lifestylePageObj.verifyLMData_Exercise(lifestyleManagerData,"verifyDeletedData"));
	}
	
	@AfterClass
	public void classTearDown() {
		lifestylePageObj = null;
		lifestyleManagerData = null;
		logger.info("Exiting the classTearDown() method for LifestyleManager_ExerciseLog class \n\n");
		
	}
}
