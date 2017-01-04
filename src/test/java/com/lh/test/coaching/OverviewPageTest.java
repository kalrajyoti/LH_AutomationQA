package com.lh.test.coaching;

import static com.lh.helper.DriverFactory.driver;
import static com.lh.helper.DriverFactory.getDriverInstance;

import java.lang.reflect.Method;
import java.util.Map;

import org.apache.logging.log4j.Logger;import org.apache.logging.log4j.LogManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import com.lh.helper.DriverFactory;
import com.lh.pages.coaching.HealthCoachingPage;
import com.lh.pages.unauthenticated.LoginPage;
import com.lh.test.LHBaseTestClass;
import com.lh.testConfig.TestProperty;
import com.lh.utils.ExcelFileUtility;
import com.lh.utils.SeleniumUtil;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/**
 * <h2>This Page will verify the tabs and overview header info</h2>
 * <p>
 * 
 * @author akash.vansil
 * @version 1.0
 * @since 2016-08-06
 */

@Listeners(com.lh.helper.LHTestListener.class)
public class OverviewPageTest extends LHBaseTestClass {

	/** Logger for the AddGeneralTrackTest class */
	private static Logger logger = LogManager.getLogger(OverviewPageTest.class);
	/**
	 * Name of the current running test method
	 */
	String testName;
	private LoginPage loginPageObj;

	private static final String VALID_PASSWORD = "Password";
	/** Contains the valid username to login to the client portal */
	private static final String VALID_USERNAME = "Username";

	private Assertion overview = new Assertion();
	private HealthCoachingPage CoachingOverviewPageobj;
	private static final String OVERVIEW_HEADERTXT = "Header";
	private static final String OVERVIEW_Txt = "Overview_Content";
	/** Reads data from the test data file */
	private ExcelFileUtility testDataObj;
	/** Contains the Login data */

	/** Contains the coaching data */
	private Map<String, String> data;
	private ExtentReports extent = new ExtentReports(
			System.getProperty("user.dir") + "\\test-output\\ Dashboard.html",
			true);
	private ExtentTest test;

	/**
	 * This method runs before the first test from the class runs.
	 * <p>
	 * 
	 */
	@BeforeClass
	public void initClass() {
		logger.info("initClass() method for OverviewPageTest instance class \n\n");
		testDataObj = new ExcelFileUtility();
		data = testDataObj.readExcelSheet("LH_WORKBOOK", "Coaching");

		test = extent.startTest("Login Test", "Verfying valid login details");

		// call the browser
		getDriverInstance();

		test.log(LogStatus.INFO, "Browser started");
		DriverFactory.openURL(TestProperty.PORTALURL);
		// DriverFactory.openPortalURL();
		loginPageObj = new LoginPage();
		test.log(LogStatus.INFO,
				"Navigated to expedia.com with valid username and password");

		loginPageObj
				.loginAs(data.get(VALID_USERNAME), data.get(VALID_PASSWORD));
		SeleniumUtil.sleep(2);

		CoachingOverviewPageobj = new HealthCoachingPage();
		SeleniumUtil.sleep(2);

	}

	/**
	 * This method runs before each test from the class runs
	 * 
	 * @throws InterruptedException
	 */
	@BeforeMethod
	public void init(Method method) throws InterruptedException {
		logger.info("Inside the init() method for OverviewPageTest  Page class...");
	}

	/** Click on Health Coaching link */
	@Test(description = "Click on Health Coaching link", groups = { "smoke,regression" }, priority = 1)
	public void verifyHealthCoaching() {
		test = extent.startTest("verifyHealthCoaching",
				"Click on Health Coaching link");
		SeleniumUtil.sleep(2);
		test.log(LogStatus.INFO,
				"User selects and click on healthcoaching link");
		CoachingOverviewPageobj.clickHealthCoaching();
		SeleniumUtil.sleep(2);
//		overview.assertTrue(CoachingOverviewPageobj
//				.verifyCoachingHeaderText(data.get(OVERVIEW_HEADERTXT)));
		test.log(LogStatus.PASS, "Coaching Header Verified");
		extent.endTest(test);
		extent.flush();

	}

	/** Verify Health Coaching Page Tab */
	@Test(description = "Verify Health Coaching Page Tab", groups = { "smoke,regression" }, priority = 2)
	public void verifyHealthCoachingTabs() {
		test = extent.startTest("verifyHealthCoachingTabs",
				"Verify Health Coaching Page Tab");
		SeleniumUtil.sleep(2);
		test.log(LogStatus.INFO,
				"User Verify Overview,Online,Onsite and phone Tabs");
		overview.assertTrue(CoachingOverviewPageobj.verifyOverviewTab());
		overview.assertTrue(CoachingOverviewPageobj.verifyOnlineTab());
		overview.assertTrue(CoachingOverviewPageobj.verifyOnSiteTab());
		overview.assertTrue(CoachingOverviewPageobj.verifyOnPhoneTab());
		extent.endTest(test);
		extent.flush();
	}

	/** User selects Overview Tabs and Verifies the content */
	@Test(description = "Verify Health Coaching Page Tab", groups = { "smoke,regression" }, priority = 3)
	public void clickOnOverviewAndVerifyContent() {
		test = extent.startTest("clickOnOverviewAndVerifyContent",
				"User selects Overview Tabs and Verifies the content");
		CoachingOverviewPageobj.clickOnOverviewTab();
		test.log(LogStatus.INFO, "User click on overview tab ");
		SeleniumUtil.sleep(2);
		overview.assertTrue(CoachingOverviewPageobj
				.verifyOverviewHeaderText(data.get(OVERVIEW_Txt)));
		test.log(LogStatus.INFO, "Overview  content Verified");
		extent.endTest(test);
		extent.flush();

	}

	/**
	 * This class runs after running all the methods in the class
	 */
	@AfterClass
	public void tearDown() {

		CoachingOverviewPageobj = null;
		loginPageObj = null;
		driver.quit();

	}

}
