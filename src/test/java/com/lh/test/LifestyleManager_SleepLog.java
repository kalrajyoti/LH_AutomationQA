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
public class LifestyleManager_SleepLog extends AuthBaseTestClass{

	private static Logger logger 								= LogManager.getLogger(LifestyleManager_SleepLog.class);
	private Map<String, String> lifestyleManagerData;
	private static final String password 						= "password";
	private static final String username 						= "username";
	private LifestyleManagerPage lifestylePageObj;
	private Assertion LM_Assert = new Assertion();
	String testName;
	private String partiallifestyleManager_URL = "MemberServices/health/ScreeningGraphPage.aspx";
	@BeforeClass
	public void initClass() {
		logger.info("\n Inside the init() method for LifestyleManager_SleepLog class...");
		lifestyleManagerData = readexcelsheet("LifestyleManager");
		ILifestyleManagerDao daoObj = new LifestyleManagerDaoImpl();
		daoObj.resetLifestyleManagerForUser(lifestyleManagerData.get(username));
		lifestylePageObj = new LifestyleManagerPage();
		doLogin(lifestyleManagerData.get(username), lifestyleManagerData.get(password),"Portal", "Portal");
		lifestylePageObj.clickLM();
		lifestylePageObj.verifyURL(partiallifestyleManager_URL);
	}

	/** This method runs before each test from the class runs */
	@BeforeMethod
	public void init(Method method) throws Exception {
		logger.info("Inside the init() method Call the loginPageObj.loginAs(username, password) method");
		testName = method.getName();
		logger.info("Exiting the init() method for LifestyleManager_SleepLog class \n");
	}
	
	@Test(description = "Click on Sleep from Logs menu list")
	public void clickOnSleepSubmenu(){
		lifestylePageObj.clickOnLogsLink();
		lifestylePageObj.clickOnSleepLog();
	}
	
	@Test(description = "Add entry for current date", dependsOnMethods = "clickOnSleepSubmenu")
	public void addEntryInSleepLog() throws ParseException{
		lifestylePageObj.addEntry_Sleep(lifestyleManagerData);
		LM_Assert.assertTrue(lifestylePageObj.veifyAdded_DeletedSleepData(lifestyleManagerData,"verifyAddedSleepDataEntry"));
	}
	
	@Test(description = "Edit entry for current date", dependsOnMethods = "addEntryInSleepLog")
	public void editEntryInSleepLog(){
		lifestylePageObj.editEntryInSleepLog(lifestyleManagerData);
		LM_Assert.assertTrue(lifestylePageObj.veifyEditSleepData(lifestyleManagerData,"verifyEditSleepEntry"));
	}
	
	@Test(description = "Delete entry in Sleep log", dependsOnMethods = "editEntryInSleepLog")
	public void deleteEntryInSleepLog(){
		lifestylePageObj.deleteEntryInSleepLog();
		lifestylePageObj.veifyAdded_DeletedSleepData(lifestyleManagerData, "verifyAddedSleepDataEntry");
	}
	
	@AfterClass
	public void classTearDown() {
		lifestylePageObj = null;
		lifestyleManagerData = null;
		logger.info("Exiting the classTearDown() method for LifestyleManager_SleepLog class \n\n");
		
	}
}
