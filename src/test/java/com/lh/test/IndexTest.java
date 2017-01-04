package com.lh.test;

import static com.lh.helper.DriverFactory.driver;
import static com.lh.helper.DriverFactory.getDriverInstance;

import java.lang.reflect.Method;
import java.util.Map;

import org.apache.logging.log4j.Logger;import org.apache.logging.log4j.LogManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import com.lh.helper.DriverFactory;
import com.lh.pages.authenticated.MainPage;
import com.lh.pages.unauthenticated.LoginPage;
import com.lh.testConfig.TestProperty;
import com.lh.utils.ExcelFileUtility;

/**
 * <h2>Define test for the Index page here!</h2> 
 * <p>
 * 
 * @author amita.arya
 * @version 1.0
 * @since 2014-03-20
 */
@Listeners(com.lh.helper.LHTestListener.class)
@Test(singleThreaded = true)
public class IndexTest extends LHBaseTestClass {
	
	

	private static final String PHOTO2_SRC_BANNER					= "photo2SrcBanner";
	private static final String PHOTO1_SRC_BANNER 					= "photo1SrcBanner";
	private static final String PASSWORD 							= "password";
	private static final String USER_NAME 							= "userName";
	
	/** Reads data from the test data file */
	private ExcelFileUtility testDataObj;
	/** Contains the index page data */
	private Map<String, String> indexPageData;
	
	/** Index page reference for the initiating the HA filling */
	private MainPage mainPageObj;
	/** LoginPage reference used to login for logging in for tests */
	private LoginPage loginPageObj;
	
	/** Logger for the IndexTest class */
	private static Logger logger 									= LogManager.getLogger(IndexTest.class);
	/** Assertion to verify different elements of the page. */
	private Assertion index_assert 									= new Assertion();
	private String testName;
	
	@BeforeClass
	public void initClass(){
		
		logger.info("Inside the initClass() method for IndexTest class...");
		testDataObj = new ExcelFileUtility();
		
		indexPageData = testDataObj.readExcelSheet("LH", "IndexPage");
		
		testDataObj = null;
				
		logger.info("Exiting the initClass() method for IndexTest class \n\n");
	}

	/** This method runs before each test from the class runs */
	@BeforeMethod
	public void init(Method method) throws Exception {
	
		logger.info("\n Inside the init() method for IndexTest class...");
		
		getDriverInstance();
		DriverFactory.openURL(baseUrl);
		testName = method.getName();
		
		loginPageObj = new LoginPage();
		
		logger.info("Calling the loginPageObj.loginAs(username, password) method");
		
		loginPageObj.loginAs(indexPageData.get(USER_NAME), indexPageData.get(PASSWORD));
		
		Thread.sleep(TestProperty.THREAD_SLEEP);
		
		mainPageObj = new MainPage();
		
		logger.info("Exiting the init() method for IndexTest class \n");
	}

	@Test(description = "Verify that the appropriate tabs/menu items are displayed.", groups = {"smoke"})
	public void verifyPagesLoad() {
		
		index_assert.assertTrue(mainPageObj.verifyMenuItemsPageLoad(indexPageData), "The Menu items do not appear as expected.");

	}

	@Test(description = "Verify that the action center is displayed.", groups = {"smoke"})
	public void verifyProgramsOverviewAppears() {

		index_assert.assertTrue(mainPageObj.verifyActionCenter(), "The action center is displayed on the Home page.");

	}

	@Test(description = "Verify that the photo flow loads correctly on the Client portal home page.", groups = {"smoke"})
	public void verifyCorrectPhotoFlow() {

		index_assert.assertTrue(mainPageObj.verifyPhotoFlow(indexPageData.get(PHOTO1_SRC_BANNER),indexPageData.get(PHOTO2_SRC_BANNER)), 
				"The expected photo flow appears on the Banner");

	}
	
	/** This method runs after each test from the class runs */
	@AfterMethod
	public void tearDown() {

		logger.info("Inside the tearDown() method for Index/Home page class...");

		logger.info("Logging out of the application...");
		mainPageObj.clickLogoutLink();

		mainPageObj = null;
		loginPageObj = null;

		driver.quit();
		
		logger.info("Exiting the tearDown() method for Index/Home page class \n\n");
	}
	
	/** This method runs after all test from the class have run */
	@AfterClass
	public void classTearDown() {
		logger.info("Inside the classTearDown() method for HealthAssessment class...");
		indexPageData = null;
	}

}
