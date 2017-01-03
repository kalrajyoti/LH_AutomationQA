package com.lh.test;

import com.lh.dao.DaoLayer;
import com.lh.dao.DaoLayerImpl;
import com.lh.pages.authenticated.BiometricScreeningPage;
import com.lh.pages.unauthenticated.LoginPage;
import com.lh.test.base.AuthBaseTestClass;
import com.lh.testConfig.TestProperty;
import com.lh.utils.ExcelFileUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.*;
import org.testng.asserts.Assertion;

import java.lang.reflect.Method;
import java.util.Map;

import static com.lh.helper.DriverFactory.driver;
import static com.lh.helper.DriverFactory.takeScreenShot;
import static com.lh.testConfig.TestProperty.TESTDATA_WORKBOOK;


/**
 * <h2> Define test for Biometric Event Scheduling page here!
 * </h2> All the tests for the Preferences page are defined here
 * <p> 
 * @author amita.arya
 * @version 2.0
 * @since 2014-11-17
 */
@Listeners(com.lh.helper.LHTestListener.class)
public class BiometricEventScheduler extends AuthBaseTestClass {

	
	private static final String RESCHEDULE_EVENT_TIME 	= "rescheduleEventTime";
	private static final String EVENT_TIME 				= "eventTime";
	private static final String EVENT_LOCATION 			= "eventLocation";
	private static final String PASSWORD 				= "password";
	private static final String USER_NAME				= "userName";
	private static final String BIOMETRIC_SCHEDULER 	= "BiometricScheduler";
	
	/** Logger for the Preferences class */
	private static Logger logger = LogManager.getLogger(BiometricEventScheduler.class);
	/** BiometricScreeningPage reference used for tests */
	private static BiometricScreeningPage biometricScreeningPageObj;
	/** LoginPage reference used to login for logging in for tests */
	private LoginPage loginPageObj;
	/** Contains the preferences data */
	private Map<String, String> schedulerData;
	/** To verify the test outcome */
	private Assertion schedulerAssert = new Assertion();
	/** Name of the current running test method */
	private String testName;
	
	
	/** This method runs before the first test from the class runs  */
	@BeforeClass
	public void initClass() {
		
		logger.info("Inside the initClass() method for BiometricEventScheduler class...");
		
		ExcelFileUtility testDataObj = new ExcelFileUtility();
		
		schedulerData = testDataObj.readExcelSheet(TESTDATA_WORKBOOK, BIOMETRIC_SCHEDULER);
		testDataObj = null;
		
		DaoLayer daoObj = new DaoLayerImpl();
		
		logger.info("updating the event");
		daoObj.updateHAEventToCurrentDate();
		daoObj.resetUserHAEvent(schedulerData.get(USER_NAME));
		
		daoObj = null;
		
		
		logger.info("Exiting the initClass() method for BiometricEventScheduler class \n\n");
	}

	/**This method runs before each test from the class runs */
	@BeforeMethod
	public void init(Method method) {
		
		logger.info("\n Inside the init() method for BiometricEventScheduler class...");
		/*getDriverInstance();
		DriverFactory.openURL(baseUrl);*/
		loginPageObj = new LoginPage();
		biometricScreeningPageObj = new BiometricScreeningPage();
		
		logger.info("Calling the loginPageObj.loginAs(username, password) method");
		
		loginPageObj.loginAs(schedulerData.get(USER_NAME),	schedulerData.get(PASSWORD));
		
		try {
			
			Thread.sleep(TestProperty.THREAD_SLEEP);
			
		} catch (InterruptedException e) {
			
			e.printStackTrace();
			
		}

		testName = method.getName();
		
		logger.info("Exiting the init() method for BiometricEventScheduler class \n");
	}

	@Test (description= "Verify that the user is successfully able to schedule the event for the current date.", groups = {"smoke"})
	public void scheduleEvent(){

		biometricScreeningPageObj.clickScheduleNow();
		
		biometricScreeningPageObj.scheduleBiometricEvent(schedulerData.get(EVENT_LOCATION), schedulerData.get(EVENT_TIME));
		
		takeScreenShot(testName);
		
		schedulerAssert.assertTrue(biometricScreeningPageObj.verifyAppointmentDateSchedule(), "The appointment is scheduled as expected for the user for today.");
			
	}
	

	@Test (description= "Verify that the user is successfully able to rescheduled the event for the current date.", dependsOnMethods = { "scheduleEvent" }, groups = {"smoke"})
	public void verifyScheduledEvent() throws InterruptedException{
		
//		biometricScreeningPageObj.clickBiometricScreening();
		
		biometricScreeningPageObj.rescheduleBiometricEvent(schedulerData.get(EVENT_LOCATION), schedulerData.get(RESCHEDULE_EVENT_TIME));
		
		takeScreenShot(testName);
		
		schedulerAssert.assertTrue(biometricScreeningPageObj.verifyAppointmentTimeSchedule(schedulerData.get(RESCHEDULE_EVENT_TIME)), "The appointment is scheduled as expected for the user for today at - " + schedulerData.get(RESCHEDULE_EVENT_TIME));
			
	}

	@Test (description= "Verify that the user is successfully able to cancel the event for the current date.", dependsOnMethods = { "verifyScheduledEvent" }, groups = {"smoke"})
	public void VerifyCancelEvent(){
		
//		biometricScreeningPageObj.clickBiometricScreening();
		
		biometricScreeningPageObj.clickCancelLink();
		
		takeScreenShot(testName);
		
		biometricScreeningPageObj.clickYesAppointmentCancellationConfirmation();
		
		takeScreenShot(testName);
		
		schedulerAssert.assertTrue(biometricScreeningPageObj.verifyScheduleNowBtnDisplayed());
			
	}

	/**
	 * This method runs after each test from the class runs 
	 */
	@AfterMethod
	public void tearDown() {
		
		logger.info("Inside the tearDown() method for Preferences class...");
		logger.info("Loging out of the application...");
		
//		biometricScreeningPageObj.clickLogoutLink();
		
		loginPageObj = null;
		biometricScreeningPageObj = null;
		
		driver.quit();
		
		logger.info("Exiting the tearDown() method for Preferences class \n\n");
		
	}

	/** 
	 * This method runs after all test from the class have run 
	 */
	@AfterClass
	public void classTearDown() {
		
		schedulerData = null;
		
		logger.info("Exiting the classTearDown() method for Preferences class \n\n");
		
	}
	
}
