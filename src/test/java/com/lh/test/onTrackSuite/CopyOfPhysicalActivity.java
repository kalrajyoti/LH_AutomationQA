package com.lh.test.onTrackSuite;

import static com.lh.helper.DriverFactory.driver;
import static com.lh.helper.DriverFactory.getDriverInstance;
import static com.lh.helper.DriverFactory.takeScreenShot;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import com.lh.helper.DriverFactory;
import com.lh.pages.authenticated.ontrack.OnTrackHomePage;
import com.lh.pages.unauthenticated.LoginPage;
import com.lh.test.LHBaseTestClass;
import com.lh.testConfig.TestProperty;
import com.lh.utils.ExcelFileUtility;
import com.lh.utils.SeleniumUtil;
import com.lh.utils.TestDataProvider;

/**
 * <h2>Verify  Make Tracks and verify values based on the dates ie from portal!</h2> 
 * <p>
 * @author akash.vansil
 * @version 1.0
 * @since 2016-23-03
 */

@Listeners(com.lh.helper.LHTestListener.class)
public class CopyOfPhysicalActivity  extends LHBaseTestClass {

	 String testName;
	private LoginPage loginPageObj;
	private static final String VALID_PASSWORD 					= "Physical_Pass";
	/** Contains the valid username to login to the client portal */
	private static final String VALID_USERNAME 					= "Physical_User";
	private ExcelFileUtility testDataObj;
    private Assertion physicalActivity  = new Assertion();
    private OnTrackHomePage  OnTrackHomePageobj;
    private Map<String, String> onTrackData;
    private static final String UPCOMING_DATE = "ThemeStartDate";
    private static final String OVERVIEW_HEADER = "OverviewMakeHeader";
    private static final String OVERVIEW_HEADERName = "Heading";
    
    private static final String OVERVIEW_BODYHeader    =  "OverviewTrackHeader";
    private static final String OVERVIEW_BODY    =  "OverviewTrackBanner";
    private static final String CHALLENGES_BODYHeader = "OverviewChallengeHeader"; 
    private static final String CHALLENGES_BODY = "OverviewChallengeText"; 
    
    private static final String CHALLENGE_INFO = "UpcomingChallengeCaption";
    private static final String UPCOMING_BANNER_TXT = "UpcomingChallengeBanner";
    
    private static final String BACKLOG_ACTIVE_DATE="BacklogActive1";
    private static final String BACKLOG_BANNER_TXT="BacklogActiveCaption1";
    
    private static final String NOT_REGISTERED_DATE = "CampaignNotRegistered";
    private static final String POST_CHALLENGE_SURVEY_DATE ="RegisteredPostChallengeSurveyComplete";
    private static final String REGISTERED_NO_APP_BANNER_TXT1 = "RegisteredNoAppsCaptions2";
    private static final String NOT_REGISTERED_BANNER_TXT  = "NotRegisteredCaption1";
    private static final String CHALLENGE_START_DATE = "challengeStartDate";
    private static final String CHALLENGE_END_DATE = "challengeEndDate1";
    private static final String POST_CHALLENGE_SURVEY_TXT="RegisteredPostChallengeSurvey";
    private static final String POST_REGISTRATION_BACKLOG_TXT = "PostRegisterationBackLogCaption1";
     
    	
    /**
	 * This method runs before the first test from the class runs.
	 * @throws IOException 
	 */
	@BeforeClass
 	public void initClass() throws IOException {
		
		
		//logger.info("Inside the initClass() method for On OnTrack_FindingBalanceTest class...");
		testDataObj = new ExcelFileUtility();
		onTrackData = testDataObj.readExcelSheet(LH_WORKBOOK, "OnTracker");
		//logger.info("Exiting the initClass() method for OnTrack_FindingBalanceTest class \n\n");
		
		//call the browser
		getDriverInstance();
		DriverFactory.openURL(TestProperty.PHYSICALURL);
		loginPageObj = new LoginPage();

		loginPageObj.loginAs(onTrackData.get(VALID_USERNAME), onTrackData.get(VALID_PASSWORD));
		SeleniumUtil.sleep(2);

		OnTrackHomePageobj = new OnTrackHomePage();
		SeleniumUtil.sleep(2);

	}
	
	/**
	 * This method runs before each test from the class runs
	 */
	@BeforeMethod
	public void init(Method method) throws Exception {
		Reporter.log("\n Inside the init() method for OnTrack_physicalActivity class...");
	}


	/**
	 * This method verifies whether Overview and Challenge tabs are displayed and verifies the content of Overview tab is displayed.
	 * @throws TimeoutException 
	 */
	@Test(dataProviderClass=TestDataProvider.class,dataProvider="onTrack",description = "This method verifies whether Overview and Challenge tabs are displayed.",priority=1,groups={"regression"})
	public void verifyOverviewContentsAndTabs(Hashtable<String,String> table1) throws TimeoutException {
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.setPhysicalDelorian(table1.get(UPCOMING_DATE));
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.slectYourToolsLink();
		OnTrackHomePageobj.clickOnTrackLinks();
		SeleniumUtil.sleep(2);
		physicalActivity.assertTrue(OnTrackHomePageobj.verifyChallengeTab());
		SeleniumUtil.sleep(1);
		OnTrackHomePageobj.clickOverviewTab();
		SeleniumUtil.sleep(3);
		physicalActivity.assertTrue(OnTrackHomePageobj.verifyOverviewHeaderInfo(table1.get(OVERVIEW_HEADERName)));
		physicalActivity.assertTrue(OnTrackHomePageobj.verifyOverviewHeaderText(table1.get(OVERVIEW_HEADER)));
		physicalActivity.assertTrue(OnTrackHomePageobj.verifyOverviewBodyHeader(table1.get(OVERVIEW_BODYHeader)));
		physicalActivity.assertTrue(OnTrackHomePageobj.verifyOverviewBodyCaption(table1.get(OVERVIEW_BODY)));
		SeleniumUtil.sleep(2);
		physicalActivity.assertTrue(OnTrackHomePageobj.verifyChallengesBodyHeader(table1.get(CHALLENGES_BODYHeader)));
		physicalActivity.assertTrue(OnTrackHomePageobj.verifyChallengesBodyText(table1.get(CHALLENGES_BODY)));
		takeScreenShot(testName);
		
	}

	
	/**
	 * This method verifies whether Overview and Challenge tabs are displayed with an upcoming campaign.
	 * @throws TimeoutException 
	 * 
	 */
   @Test(dataProviderClass=TestDataProvider.class,dataProvider="onTrack",description = "This method verifies whether Overview and Challenge tabs are displayed with an upcoming campaign.",priority=2,groups={"regression"})
	public void verifyUpcomingChallenge(Hashtable<String,String> table1) throws TimeoutException{
	
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.clickOnChallengeTab();
		SeleniumUtil.sleep(3);
		//physicalActivity.assertTrue(OnTrackHomePageobj.verifyChallengeBannerText(onTrackData.get(CHALLENGE_INFO_TXT)));
		physicalActivity.assertTrue(OnTrackHomePageobj.verifyChallengeBanner(table1.get(CHALLENGE_INFO)));
		physicalActivity.assertTrue(OnTrackHomePageobj.verifyBannerText(table1.get(UPCOMING_BANNER_TXT)));
		SeleniumUtil.sleep(2);
		takeScreenShot(testName);
	}
	
	/**
	 * This method verifies whether Banner Text is accurate with an challenge that is over(backlog page).
	 * @throws TimeoutException 
	 */
	@Test(dataProviderClass=TestDataProvider.class,dataProvider="onTrack",description = "This method verifies whether Banner Text is accurate with an challenge that is over(backlog page).",priority=3,groups={"regression"})
	public void verifyBackLogChallenge(Hashtable<String,String> table2) throws TimeoutException{
		
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.setPhysicalDelorian(table2.get(BACKLOG_ACTIVE_DATE));
		SeleniumUtil.sleep(2);
		physicalActivity.assertTrue(OnTrackHomePageobj.verifyChallengeTab());
		SeleniumUtil.sleep(2);
		physicalActivity.assertTrue(OnTrackHomePageobj.verifyBannerText(table2.get(BACKLOG_BANNER_TXT)));
		takeScreenShot(testName);
	}

	/**
	 * This method verifies whether Banner Text is accurate with an not registered challenge.
	 * @throws TimeoutException 
	 */

	@Test(dataProviderClass=TestDataProvider.class,dataProvider="onTrack",description = "This method verifies whether Banner Text is accurate with an not registered challenge.",priority=4,groups={"regression"})
	public void verifyNotRegisteredAndRegisterChallenge(Hashtable<String,String> table1) throws TimeoutException{
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.setPhysicalDelorian(table1.get(NOT_REGISTERED_DATE));
		SeleniumUtil.sleep(3);
		physicalActivity.assertTrue(OnTrackHomePageobj.verifyBannerText(table1.get(NOT_REGISTERED_BANNER_TXT)));
		SeleniumUtil.sleep(2);
		physicalActivity.assertTrue(OnTrackHomePageobj.verifySignUpButton());
		 OnTrackHomePageobj.clickSignUpButton();
		 SeleniumUtil.sleep(5);
		
		physicalActivity.assertTrue(OnTrackHomePageobj.verifyBannerText(table1.get(REGISTERED_NO_APP_BANNER_TXT1)));
		takeScreenShot(testName);
	}

	/**
	 * This method verifies whether Banner Text is accurate with an not registered challenge.
	 * @throws ParseException 
	 * @throws TimeoutException 
	 * @throws InterruptedException 
	 */
	@Test(dataProviderClass=TestDataProvider.class,dataProvider="onTrack",description = "This method verifies whether Banner Text is accurate with an not registered challenge.",priority=5,groups={"regression"})
	public void verifyRegisteredNoAppsChallengeAddEntry(Hashtable<String,String> table1) throws ParseException, TimeoutException, InterruptedException{
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.setPhysicalDelorian(table1.get(CHALLENGE_START_DATE));
		SeleniumUtil.sleep(2);
		physicalActivity.assertTrue(OnTrackHomePageobj.verifyChallengeTab());
		SeleniumUtil.sleep(2);
		physicalActivity.assertTrue(OnTrackHomePageobj.verifyBannerText(table1.get(REGISTERED_NO_APP_BANNER_TXT1)));
		SeleniumUtil.sleep(4);
		OnTrackHomePageobj.addStepsEntry(onTrackData.get(CHALLENGE_START_DATE), onTrackData.get(CHALLENGE_END_DATE));
		SeleniumUtil.sleep(3);
		takeScreenShot(testName);
	}
	
	/**
	 * This method verifies whether Banner Text is accurate with an challenge that is over(backlog page).
	 * @throws TimeoutException 
	 */
	@Test(dataProviderClass=TestDataProvider.class,dataProvider="onTrack",description = "This method verifies whether Banner Text is accurate with an challenge that is over(backlog page).",priority=6,groups={"regression"})
	public void verifyRegisteredBackLogChallenge(Hashtable<String,String> table1) throws TimeoutException{
		
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.setPhysicalDelorian(table1.get(BACKLOG_ACTIVE_DATE));
		SeleniumUtil.sleep(3);
		physicalActivity.assertTrue(OnTrackHomePageobj.verifyChallengeTab());
		physicalActivity.assertTrue(OnTrackHomePageobj.verifyBannerText(table1.get(POST_REGISTRATION_BACKLOG_TXT)));
		takeScreenShot(testName);
	}
	

	
	/**
	 * This method verifies whether Banner Text is accurate with an challenge which has expired where user has signed up.
	 * @throws TimeoutException 
	 */
	@Test(dataProviderClass=TestDataProvider.class,dataProvider="onTrack",description = "This method verifies whether Banner Text is accurate with an challenge which has expired where user has signed up.",priority=7,groups={"regression"})
	public void verifyRegisteredPostChallengeSurvey(Hashtable<String,String> table1) throws TimeoutException{
		
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.setPhysicalDelorian(table1.get(POST_CHALLENGE_SURVEY_DATE));
		SeleniumUtil.sleep(2);
		physicalActivity.assertTrue(OnTrackHomePageobj.verifyChallengeTab());
		SeleniumUtil.sleep(2);
		physicalActivity.assertTrue(OnTrackHomePageobj.verifyBannerText(table1.get(POST_CHALLENGE_SURVEY_TXT)));		
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.makeTrackSurvey();	
		SeleniumUtil.sleep(2);
		takeScreenShot(testName);
		SeleniumUtil.sleep(4);
	}
	
	
	/**
	 * This class runs after running all the methods in the class 
	 */
	@AfterClass
	public void tearDown() {
		OnTrackHomePageobj = null;
		loginPageObj= null;
		driver.quit();
	}
	

}
