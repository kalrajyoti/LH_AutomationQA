package com.lh.test;

import static com.lh.helper.DriverFactory.driver;
import static com.lh.helper.DriverFactory.getDriverInstance;
import static com.lh.helper.DriverFactory.takeScreenShot;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.Map;

import org.apache.logging.log4j.Logger;import org.apache.logging.log4j.LogManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import com.lh.helper.DriverFactory;
import com.lh.pages.authenticated.PALogPage;
import com.lh.pages.unauthenticated.LoginPage;
import com.lh.utils.ExcelFileUtility;
import com.lh.utils.SeleniumUtil;
import com.lh.utils.UtilityMethods;

/**
 * This test class is used for verification of Physical activity Log
 * @author Sanjeev.Kumar
 *
 */
public class PALogTest extends LHBaseTestClass {
	private static final String PA_Log_sheet = "PALog";
	/** Contains the valid password to login to the client portal */
	private static final String VALID_PASSWORD 					= "validPassword";
	/** Contains the valid username to login to the client portal */
	private static final String VALID_USERNAME 					= "validUsername";

	/** Logger for the Login class */
	private static Logger logger = LogManager.getLogger(PALogTest.class);

	private String testName;
	private LoginPage loginPageObj;
	/** AboutYou page reference for the tests to run */
	private PALogPage paLogPage;
	private ExcelFileUtility testDataObj;
	/** Contains the coaching data */
	private Map<String, String> paLogData;
	private Assertion palog_assert 								= new Assertion();

	/**
	 * This method runs before the first test from the class runs.
	 */
	@BeforeClass
	public void initClass() {
		logger.info("Inside the initClass() method for PALogTest class...");
		testDataObj = new ExcelFileUtility();
		paLogData = testDataObj.readExcelSheet(LH_WORKBOOK, PA_Log_sheet);
		logger.info("Exiting the initClass() method for PALogTest class \n\n");
	}

	/**
	 * This method runs before each test from the class runs
	 */
	@BeforeMethod
	public void init(Method method) throws Exception {
		logger.info("\n Inside the init() method for PALogTest class...");

		getDriverInstance();
		DriverFactory.openURL(baseUrl);
		loginPageObj = new LoginPage();

		logger.info("Calling the loginPageObj.loginAs(username, password) method");
		loginPageObj.loginAs(paLogData.get(VALID_USERNAME), paLogData.get(VALID_PASSWORD));
		SeleniumUtil.sleep(3);

		paLogPage = new PALogPage();

		paLogPage.clickPAL();
		SeleniumUtil.sleep(1);
		testName = method.getName();
		logger.info("Exiting the init() method for PALogTest class \n");
	}

	/**
	 * This method verify the addition of Physical Activity Log
	 * @throws ParseException 
	 */

	@Test(description = "This method verify the addition of Physical Activity Log", groups={"regression"})
	public void verifyAdditionOfPALog() throws ParseException{
		paLogPage.addPALog(paLogData);
		palog_assert.assertTrue(paLogPage.verifyPALog(paLogData,"verifyAddDate","verifyAddedData"));
		takeScreenShot(testName);
	}

	/**
	 * This method verify the Edit Physical Activity Log
	 * @throws ParseException 
	 */

	@Test(description = "This method verify the Edit Physical Activity Log", groups={"regression"}, dependsOnMethods={"verifyAdditionOfPALog"})
	public void verifyEditPALog() throws ParseException{	
		paLogPage.editPALogEntry(paLogData);
		palog_assert.assertTrue(paLogPage.verifyPALog(paLogData,"editDate","verifyEditData"));
		takeScreenShot(testName);
	}


	/** This method runs after each test method from the class runs */
	@AfterMethod
	public void tearDown() {

		logger.info("Inside the tearDown() method for PALogTest class...");

		logger.info("Loging out of the application...");
		paLogPage.clickLogoutLink();

		paLogPage = null;
		loginPageObj = null;

		driver.quit();

		logger.info("Exiting the tearDown() method for PALog class \n\n");
	}

	@AfterClass
	public void afterClass() {
		logger.info("Inside the classTearDown() method for PALog class...");

		logger.info("Writing the output to the Result output text file.");

		UtilityMethods.writeOutputResult();

		logger.info("Exiting the classTearDown() method for PALog class \n\n");

	}

}
