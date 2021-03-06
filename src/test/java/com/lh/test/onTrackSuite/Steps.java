package com.lh.test.onTrackSuite;

import static com.lh.helper.DriverFactory.driver;
import static com.lh.helper.DriverFactory.getDriverInstance;
import static com.lh.helper.DriverFactory.takeScreenShot;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import com.lh.helper.DriverFactory;
import com.lh.pages.authenticated.ontrack.OnTrackHomePage;
import com.lh.pages.unauthenticated.LoginPage;
import com.lh.test.LHBaseTestClass;
import com.lh.testConfig.TestProperty;
import com.lh.utils.ExcelFileUtility;
import com.lh.utils.SeleniumUtil;

public class Steps  extends LHBaseTestClass {

	String testName;
	private LoginPage loginPageObj;
	private static final String VALID_PASSWORD 					= "Steps_Pass";
	/** Contains the valid username to login to the client portal */
	private static final String VALID_USERNAME 					= "Steps_User";
	private ExcelFileUtility testDataObj;
    private Assertion nutrition  = new Assertion();
    private OnTrackHomePage  OnTrackHomePageobj;
    private Map<String, String> onTrackData;
    private static final String UPCOMING_DATE = "ThemeStartDate";
    private static final String OVERVIEW_HEADER = "HotSeatHeader";
    private static final String OVERVIEW_HEADERName = "Heading2";
    private static final String OVERVIEW_BODYHeader    =  "OverviewHotSeatHeader";
    private static final String OVERVIEW_BODY    =  "OverviewHotSeatBanner";
    private static final String CHALLENGES_BODYHeader = "OverviewChallengeHeader"; 
    private static final String CHALLENGES_BODY = "OverviewHotSeatChallenge"; 
    private static final String CHALLENGE_INFO_TXT = "HotSeatChallengeText";
    private static final String UPCOMING_BANNER_TXT = "UpcomingHotSeatChallengeCaption1";
    private static final String BACKLOG_ACTIVE_DATE="BacklogActive";
    private static final String BACKLOG_BANNER_TXT="BacklogActiveCaption1";
    private static final String NOT_REGISTERED_DATE = "CampaignNotRegistered";
    private static final String NOT_REGISTERED_BANNER_TXT = "NotRegisteredHotSeatCaption1";
    private static final String REGISTERED_NO_Apps_TXT = "RegisteredNoApps";
    private static final String POST_CHALLENGE_SURVEY_DATE ="RegisteredPostChallengeSurveyComplete";
    private static final String CHALLENGE_START_DATE = "challengeStartDate";
    private static final String CHALLENGE_END_DATE = "challengeEndDate1";
	private static final String POST_CHALLENGE_SURVEY_TXT="RegisteredPostChallengeSurvey1";
    
	/**
	 * This method runs before the first test from the class runs.
	 * @throws IOException 
	 */
	@BeforeClass
 	public void initClass() throws IOException {
		
		
		testDataObj = new ExcelFileUtility();
		onTrackData = testDataObj.readExcelSheet(LH_WORKBOOK, "OnTracker");
		//call the browser
		getDriverInstance();
		DriverFactory.openURL(TestProperty.STEPSURL);
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
		Reporter.log("\n Inside the init() method for OnTrrack Step class...");
        
	}


	/**
	 * This method verifies whether Overview and Challenge tabs are displayed.
	 * @throws TimeoutException 
	 */
	@Test(description = "This method verifies whether Overview and Challenge tabs are displayed.",priority=1,groups={"regression"})
	public void verifyHotSeatTabs() throws TimeoutException {
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.setDelorianforSteps(onTrackData.get(UPCOMING_DATE));
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.slectYourToolsLink();
		OnTrackHomePageobj.clickOnTrackLinks();
		SeleniumUtil.sleep(2);
		nutrition.assertTrue(OnTrackHomePageobj.verifyChallengeTab());
		SeleniumUtil.sleep(1);
		nutrition.assertTrue(OnTrackHomePageobj.verifyOverviewTab());
		takeScreenShot(testName);
		
	}

	/**
	 * This method verifies the content of Overview tab is displayed.
	 * @throws TimeoutException 
	 */ 
	@Test(description = "This method verifies the content of Overview tab is displayed.",priority=2,groups={"regression"})
	public void verifyHotSeatOverviewTabContent() throws TimeoutException{
		
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.clickOverviewTab();
		SeleniumUtil.sleep(3);
		nutrition.assertTrue(OnTrackHomePageobj.verifyOverviewHeaderInfo(onTrackData.get(OVERVIEW_HEADERName)));
		nutrition.assertTrue(OnTrackHomePageobj.verifyOverviewHeaderText(onTrackData.get(OVERVIEW_HEADER)));
		nutrition.assertTrue(OnTrackHomePageobj.verifyOverviewBodyHeader(onTrackData.get(OVERVIEW_BODYHeader)));
		nutrition.assertTrue(OnTrackHomePageobj.verifyOverviewBodyTexts(onTrackData.get(OVERVIEW_BODY)));
		SeleniumUtil.sleep(2);
		nutrition.assertTrue(OnTrackHomePageobj.verifyChallengesBodyHeader(onTrackData.get(CHALLENGES_BODYHeader)));
		nutrition.assertTrue(OnTrackHomePageobj.verifyChallengesBodyText(onTrackData.get(CHALLENGES_BODY)));
		takeScreenShot(testName);
	}
	
	
	/**
	 * This method verifies whether Overview and Challenge tabs are displayed with an upcoming campaign.
	 * @throws TimeoutException 
	 * 
	 */

	@Test(description = "This method verifies whether Overview and Challenge tabs are displayed with an upcoming campaign.",priority=3,groups={"regression"})
	public void verifyUpcomingChallengeForSteps() throws TimeoutException{
	
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.clickOnChallengeTab();
		SeleniumUtil.sleep(3);
		nutrition.assertTrue(OnTrackHomePageobj.verifyChallengeBannerText(onTrackData.get(CHALLENGE_INFO_TXT)));
		nutrition.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(UPCOMING_BANNER_TXT)));
		SeleniumUtil.sleep(2);
		takeScreenShot(testName);
	}
	
	/**
	 * This method verifies whether Banner Text is accurate with an challenge that is over(backlog page).
	 * @throws TimeoutException 
	 */
	@Test(description = "This method verifies whether Banner Text is accurate with an challenge that is over(backlog page).",priority=4,groups={"regression"})
	public void verifyStepsBackLogChallenge() throws TimeoutException{
		
	
		OnTrackHomePageobj.setDelorian(onTrackData.get(BACKLOG_ACTIVE_DATE));
		SeleniumUtil.sleep(2);
		nutrition.assertTrue(OnTrackHomePageobj.verifyChallengeTab());
		SeleniumUtil.sleep(2);
		nutrition.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(BACKLOG_BANNER_TXT)));
		takeScreenShot(testName);
	}

	/**
	 * This method verifies whether Banner Text is accurate with an not registered challenge.
	 * @throws TimeoutException 
	 */

	@Test(description = "This method verifies whether Banner Text is accurate with an not registered challenge.",priority=5,groups={"regression"})
	public void verifyStepsNotRegisteredAndRegiterChallenge() throws TimeoutException{
		
	
		OnTrackHomePageobj.setDelorian(onTrackData.get(NOT_REGISTERED_DATE));
		SeleniumUtil.sleep(2);
		nutrition.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(NOT_REGISTERED_BANNER_TXT)));
		SeleniumUtil.sleep(2);
		nutrition.assertTrue(OnTrackHomePageobj.verifySignUpButton());
		 OnTrackHomePageobj.clickSignUpButton();
		 SeleniumUtil.sleep(5);
		 nutrition.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(REGISTERED_NO_Apps_TXT)));		
		takeScreenShot(testName);
	}

	/**
	 * This method verifies whether Banner Text is accurate with an not registered challenge.
	 * @throws ParseException 
	 * @throws TimeoutException 
	 * @throws InterruptedException 
	 */
	@Test(description = "This method verifies whether Banner Text is accurate with an not registered challenge.",priority=6,groups={"regression"})
	public void verifyStepsRegisteredNoAppsChallengeAddEntry() throws ParseException, TimeoutException, InterruptedException{
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.setDelorian(onTrackData.get(CHALLENGE_START_DATE));
		SeleniumUtil.sleep(2);
		nutrition.assertTrue(OnTrackHomePageobj.verifyChallengeTab());
		SeleniumUtil.sleep(7);
		OnTrackHomePageobj.addStepsEntry(onTrackData.get(CHALLENGE_START_DATE), onTrackData.get(CHALLENGE_END_DATE));
		SeleniumUtil.sleep(3);
		takeScreenShot(testName);
	}
	
	/**
	 * This method verifies whether Banner Text is accurate with an challenge which has expired where user has signed up.
	 * @throws TimeoutException 
	 */
	@Test(description = "This method verifies whether Banner Text is accurate with an challenge which has expired where user has signed up.",priority=7,groups={"regression"})
	public void verifyRegisteredPostChallengeForSteps() throws TimeoutException{
		
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.setDelorian(onTrackData.get(POST_CHALLENGE_SURVEY_DATE));
		SeleniumUtil.sleep(2);
		nutrition.assertTrue(OnTrackHomePageobj.verifyChallengeTab());
		SeleniumUtil.sleep(2);
		nutrition.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(POST_CHALLENGE_SURVEY_TXT)));
		SeleniumUtil.sleep(4);
		OnTrackHomePageobj.completeSteps_Survey();
		takeScreenShot(testName);
	}
	
	/**
	 * This method verifies whether Banner Text is accurate with an challenge that is over(backlog page).
	 * @throws TimeoutException 
	 */
	@Test(description = "This method verifies whether Banner Text is accurate with an challenge that is over(backlog page).",priority=8,groups={"regression"})
	public void verifyRegisteredBackLogChallengeForSteps() throws TimeoutException{
		
	
		OnTrackHomePageobj.setDelorian(onTrackData.get(BACKLOG_ACTIVE_DATE));
		SeleniumUtil.sleep(4);
		nutrition.assertTrue(OnTrackHomePageobj.verifyChallengeTab());
		nutrition.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(BACKLOG_BANNER_TXT)));
		takeScreenShot(testName);
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
