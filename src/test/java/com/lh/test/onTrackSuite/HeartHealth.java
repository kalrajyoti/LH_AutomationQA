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


/**
 * <h2>Verify Hearty Bites,Pump Up Your Heart and
 * Steady your Beat and verify values based on the dates ie from portal!</h2> 
 * <p>
 * @author akash.vansil
 * @version 1.0
 * @since 2016-23-03
 */

@Listeners(com.lh.helper.LHTestListener.class)
public class HeartHealth  extends LHBaseTestClass{

	String testName;
	private LoginPage loginPageObj;
	private static final String VALID_PASSWORD 					= "Hearty_Pass";
	/** Contains the valid username to login to the client portal */
	private static final String VALID_USERNAME 					= "Hearty_User";
	private ExcelFileUtility testDataObj;
    private Assertion heartyHealth  = new Assertion();
    private OnTrackHomePage  OnTrackHomePageobj;
    private Map<String, String> onTrackData;
    private static final String UPCOMING_DATE = "ThemeStartDate";
    private static final String UPCOMING_DATE1 = "ThemeStartDate1";
    private static final String UPCOMING_DATE2 = "ThemeStartDate2";
    private static final String OVERVIEW_HEADER = "HeartyHeader";
    private static final String OVERVIEW_HEADERName = "Header";
    private static final String OVERVIEW_BODYHeader    =  "OverviewHeartyHeader";
    private static final String OVERVIEW_BODY    =  "OverviewHeartyBanner";
    private static final String CHALLENGES_BODYHeader = "OverviewChallengeHeader"; 
    private static final String CHALLENGES_BODY = "OverviewHeartyChallenge"; 
    private static final String CHALLENGES_BODY1 = "OverviewHeartChallenge"; 
    private static final String CHALLENGE_INFO_TXT = "HeartyChallengeText";
    private static final String UPCOMING_BANNER_TXT = "UpcomingHeartyChallengeCaption1";
    private static final String UPCOMING_BANNER_TXT1 = "UpcomingHeartyChallengeCaption2";
    private static final String UPCOMING_BANNER_TXT2 = "UpcomingSteadyChallengeCaption2";
    private static final String BACKLOG_ACTIVE_DATE="BacklogActive";
    private static final String BACKLOG_ACTIVE_DATE1="BacklogActiveChef";
    private static final String BACKLOG_ACTIVE_DATE2 ="ExhaleBacklogActive";
    private static final String BACKLOG_BANNER_TXT="BacklogActiveCaption1";
    private static final String NOT_REGISTERED_DATE = "CampaignNotRegistered";
    private static final String NOT_REGISTERED_DATE1 = "campaignNotRegistered1";
    private static final String NOT_REGISTERED_DATE2 = "campaignNotRegistered2";
	private static final String REGISTERED_NO_APP_BANNER_TXT = "RegistererdSignUPText";
    private static final String NOT_REGISTERED_BANNER_TXT = "NotRegisteredHeartyCaption1";
    private static final String NOT_REGISTERED_BANNER_TXT1 = "signUpHeartyBannerText";
    private static final String NOT_REGISTERED_BANNER_TXT3 = "signUpSteadyBannerText";
    private static final String REGISTERED_NO_APP_BANNER  = "RegisteredExaleNoApps";
    private static final String POST_CHALLENGE_SURVEY_DATE ="RegisteredPostChallengeSurveyComplete";
    private static final String POST_CHALLENGE_SURVEY_DATE2 = "RegisteredPostChallengeSurveyComplete2";
	private static final String POST_CHALLENGE_SURVEY_DATE1 ="RegisteredPostChallengeSurveyComplete1";
    private static final String REGISTERED_NO_APP_BANNER_TXT1 = "RegisteredNoAppsCaptions2";
    private static final String CHALLENGE_START_DATE = "challengeStartDate";
    private static final String CHALLENGE_START_DATE1 = "challengeStartDate1";
	private static final String POST_CHALLENGE_SURVEY_TXT="RegisteredPostChallengeSurvey1";
    private static final String POST_CHALLENGE_SURVEY_TXT1="RegisteredPostChallengeSurvey2";
    private static final String POST_CHALLENGE_SURVEY_TXT2="RegisteredPostChallengeSurvey3";
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
		DriverFactory.openURL(TestProperty.HEARTYURL);
		//DriverFactory.openPortalURL();
		loginPageObj = new LoginPage();

		//logger.info("Calling the loginPageObj.loginAs(username, password) method");
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
		Reporter.log("\n Inside the init() method for OnTrack_HeartyHealth class...");
        
	}


	/**
	 * This method verifies whether Overview and Challenge tabs are displayed.
	 * @throws TimeoutException 
	 */
	@Test(description = "This method verifies whether Overview and Challenge tabs are displayed.",priority=1,groups={"regression"})
	public void verifyOnTrackHeartyBitesTabs() throws TimeoutException {
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.setDelorianforHeartyHealth(onTrackData.get(UPCOMING_DATE));
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.slectYourToolsLink();
		OnTrackHomePageobj.clickOnTrackLinks();
		SeleniumUtil.sleep(2);
		heartyHealth.assertTrue(OnTrackHomePageobj.verifyChallengeTab());
		SeleniumUtil.sleep(1);
		heartyHealth.assertTrue(OnTrackHomePageobj.verifyOverviewTab());
		takeScreenShot(testName);
		
	}

	/**
	 * This method verifies the content of Overview tab is displayed.
	 * @throws TimeoutException 
	 */ 
	@Test(description = "This method verifies the content of Overview tab is displayed.",priority=2,groups={"regression"})
	public void verifyOverviewHeartyBitesTabContent() throws TimeoutException{
		
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.clickOverviewTab();
		SeleniumUtil.sleep(3);
		heartyHealth.assertTrue(OnTrackHomePageobj.verifyOverviewHeaderInfo(onTrackData.get(OVERVIEW_HEADERName)));
		heartyHealth.assertTrue(OnTrackHomePageobj.verifyOverviewHeaderText(onTrackData.get(OVERVIEW_HEADER)));
		heartyHealth.assertTrue(OnTrackHomePageobj.verifyOverviewBodyHeader(onTrackData.get(OVERVIEW_BODYHeader)));
		heartyHealth.assertTrue(OnTrackHomePageobj.verifyOverviewBodyText(onTrackData.get(OVERVIEW_BODY)));
		SeleniumUtil.sleep(1);
		heartyHealth.assertTrue(OnTrackHomePageobj.verifyChallengesBodyHeader(onTrackData.get(CHALLENGES_BODYHeader)));
		heartyHealth.assertTrue(OnTrackHomePageobj.verifyChallengesBodyText(onTrackData.get(CHALLENGES_BODY)));
		takeScreenShot(testName);
	}
	
	
	/**
	 * This method verifies whether Overview and Challenge tabs are displayed with an upcoming campaign.
	 * @throws TimeoutException 
	 * 
	 */

	@Test(description = "This method verifies whether Overview and Challenge tabs are displayed with an upcoming campaign.",priority=3,groups={"regression"})
	public void verifyUpcomingHeartyChallenge() throws TimeoutException{
	
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.clickOnChallengeTab();
		SeleniumUtil.sleep(3);
		heartyHealth.assertTrue(OnTrackHomePageobj.verifyChallengeBannerText(onTrackData.get(CHALLENGE_INFO_TXT)));
		heartyHealth.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(UPCOMING_BANNER_TXT)));
		SeleniumUtil.sleep(2);
		takeScreenShot(testName);
	}
	
	/**
	 * This method verifies whether Banner Text is accurate with an challenge that is over(backlog page).
	 * @throws TimeoutException 
	 */
	@Test(description = "This method verifies whether Banner Text is accurate with an challenge that is over(backlog page).",priority=4,groups={"regression"})
	public void verifyHeartyBackLogChallenge() throws TimeoutException{
		
	
		OnTrackHomePageobj.setDelorianforHeartyHealth(onTrackData.get(BACKLOG_ACTIVE_DATE));
		SeleniumUtil.sleep(2);
		heartyHealth.assertTrue(OnTrackHomePageobj.verifyChallengeTab());
		SeleniumUtil.sleep(2);
		heartyHealth.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(BACKLOG_BANNER_TXT)));
		takeScreenShot(testName);
	}

	/**
	 * This method verifies whether Banner Text is accurate with an not registered challenge.
	 * @throws TimeoutException 
	 */

	@Test(description = "This method verifies whether Banner Text is accurate with an not registered challenge.",priority=5,groups={"regression"})
	public void verifyHeartyNotRegisteredAndRegiterChallenge() throws TimeoutException{
		
	
		OnTrackHomePageobj.setDelorianforHeartyHealth(onTrackData.get(NOT_REGISTERED_DATE));
		SeleniumUtil.sleep(2);
		heartyHealth.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(NOT_REGISTERED_BANNER_TXT)));
		SeleniumUtil.sleep(2);
		heartyHealth.assertTrue(OnTrackHomePageobj.verifySignUpButton());
		 OnTrackHomePageobj.clickSignUpButton();
		 SeleniumUtil.sleep(5);
		 heartyHealth.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(REGISTERED_NO_APP_BANNER_TXT1)));
		 takeScreenShot(testName);
	}

	/**
	 * This method verifies whether Banner Text is accurate with an not registered challenge.
	 * @throws ParseException 
	 * @throws TimeoutException 
	 * @throws InterruptedException 
	 */
	@Test(description = "This method verifies whether Banner Text is accurate with an not registered challenge.",priority=6,groups={"regression"})
	public void verifyHeartyRegisteredNoAppsChallengeAddEntry() throws ParseException, TimeoutException, InterruptedException{
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.setDelorianforHeartyHealth(onTrackData.get(CHALLENGE_START_DATE));
		SeleniumUtil.sleep(2);
		heartyHealth.assertTrue(OnTrackHomePageobj.verifyChallengeTab());
		SeleniumUtil.sleep(7);
		//OnTrackHomePageobj.addFoodEntry(onTrackData.get(CHALLENGE_START_DATE), onTrackData.get(CHALLENGE_END_DATE));
		SeleniumUtil.sleep(2);
		takeScreenShot(testName);
	}
	
	/**
	 * This method verifies whether Banner Text is accurate with an challenge which has expired where user has signed up.
	 * @throws TimeoutException 
	 */
	@Test(description = "This method verifies whether Banner Text is accurate with an challenge which has expired where user has signed up.",priority=7,groups={"regression"})
	public void verifyRegisteredPostChallengeForHeartyBites() throws TimeoutException{
		
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.setDelorianforHeartyHealth(onTrackData.get(POST_CHALLENGE_SURVEY_DATE));
		SeleniumUtil.sleep(2);
		heartyHealth.assertTrue(OnTrackHomePageobj.verifyChallengeTab());
		SeleniumUtil.sleep(2);
		heartyHealth.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(POST_CHALLENGE_SURVEY_TXT)));
		takeScreenShot(testName);
	}
	
	/**
	 * This method verifies whether Banner Text is accurate with an challenge that is over(backlog page).
	 * @throws TimeoutException 
	 */
	@Test(description = "This method verifies whether Banner Text is accurate with an challenge that is over(backlog page).",priority=8,groups={"regression"})
	public void verifyRegisteredBackLogChallengeForHeartyBites() throws TimeoutException{
		
	
		OnTrackHomePageobj.setDelorianforHeartyHealth(onTrackData.get(BACKLOG_ACTIVE_DATE));
		SeleniumUtil.sleep(3);
		heartyHealth.assertTrue(OnTrackHomePageobj.verifyChallengeTab());
		heartyHealth.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(POST_REGISTRATION_BACKLOG_TXT)));
		takeScreenShot(testName);
	}
	

	@Test(description = "This method verifies whether Overview and Challenge tabs are displayed.", priority=9,groups={"regression"})
	public void verifyOnTrackPumpUpYourHeartTabs() throws TimeoutException {
		SeleniumUtil.sleep(4);
		OnTrackHomePageobj.setDelorianforHeartyHealth(onTrackData.get(UPCOMING_DATE1));
		SeleniumUtil.sleep(3);
		heartyHealth.assertTrue(OnTrackHomePageobj.verifyChallengeTab());
		SeleniumUtil.sleep(1);
		heartyHealth.assertTrue(OnTrackHomePageobj.verifyOverviewTab());
		takeScreenShot(testName);	
	}
	
	/**
	 * This method verifies the content of Overview tab is displayed.
	 * @throws TimeoutException 
	 */ 
	
	@Test(description = "This method verifies the content of Overview tab is displayed.",priority=10,groups={"regression"})
	public void verifyOverviewPumpUpYourHeartTabContent() throws TimeoutException{
		
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.clickOverviewTab();
		SeleniumUtil.sleep(1);
		heartyHealth.assertTrue(OnTrackHomePageobj.verifyOverviewTab());
		SeleniumUtil.sleep(2);
		heartyHealth.assertTrue(OnTrackHomePageobj.verifyChallengesBodyHeader(onTrackData.get(CHALLENGES_BODYHeader)));
		heartyHealth.assertTrue(OnTrackHomePageobj.verifyChallengesBodyText(onTrackData.get(CHALLENGES_BODY1)));
		takeScreenShot(testName);
	}
	
	/**
	 * This method verifies whether Overview and Challenge tabs are displayed with an upcoming campaign.
	 * @throws TimeoutException 
	 * 
	 */

	@Test(description = "This method verifies whether Overview and Challenge tabs are displayed with an upcoming campaign.",priority=11,groups={"regression"})
	public void verifyUpcomingChallengeForPumpUpYourHeart() throws TimeoutException{
	
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.clickOnChallengeTab();
		SeleniumUtil.sleep(3);
		heartyHealth.assertTrue(OnTrackHomePageobj.verifyChallengeTab());
		SeleniumUtil.sleep(2);
		heartyHealth.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(UPCOMING_BANNER_TXT1)));
		takeScreenShot(testName);
	}
	
	/**
	 * This method verifies whether Banner Text is accurate with an challenge that is over(backlog page).
	 * @throws TimeoutException 
	 */
	@Test(description = "This method verifies whether Banner Text is accurate with an challenge that is over(backlog page).",priority=12,groups={"regression"})
	public void verifyHeartBackLogChallenge() throws TimeoutException{
		
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.setDelorianforHeartyHealth(onTrackData.get(BACKLOG_ACTIVE_DATE1));
		SeleniumUtil.sleep(2);
		heartyHealth.assertTrue(OnTrackHomePageobj.verifyChallengeTab());
		heartyHealth.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(BACKLOG_BANNER_TXT)));	
		takeScreenShot(testName);
	}
	

	/**
	 * This method verifies whether Banner Text is accurate with an not registered challenge.
	 * @throws TimeoutException 
	 */

	@Test(description = "This method verifies whether Banner Text is accurate with an not registered challenge.",priority=13,groups={"regression"})
	public void verifyNotRegisteredChallengeForPumpUpYourHeart() throws TimeoutException{
		
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.setDelorianforHeartyHealth(onTrackData.get(NOT_REGISTERED_DATE1));
		SeleniumUtil.sleep(4);
		heartyHealth.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(NOT_REGISTERED_BANNER_TXT1)));
		heartyHealth.assertTrue(OnTrackHomePageobj.verifySignUpButton());
		OnTrackHomePageobj.clickSignUpButton();
		 SeleniumUtil.sleep(5);
		 heartyHealth.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(REGISTERED_NO_APP_BANNER)));
	     takeScreenShot(testName);
	}
	
	/**
	 * This method verifies whether Banner Text is accurate with an not registered challenge.
	 * @throws ParseException 
	 * @throws TimeoutException 
	 * @throws InterruptedException 
	 */
	@Test(description = "This method verifies whether Banner Text is accurate with an not registered challenge.",priority=14,groups={"regression"})
	public void verifyRegisteredNoAppsChallengeAddEntryForPumpUpYourHeart() throws ParseException, TimeoutException, InterruptedException{
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.setDelorianforHeartyHealth(onTrackData.get(CHALLENGE_START_DATE1));
		SeleniumUtil.sleep(2);
		heartyHealth.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(REGISTERED_NO_APP_BANNER)));
		SeleniumUtil.sleep(6);
		//OnTrackHomePageobj.addWeightEntry();
		//OnTrackHomePageobj.addPumpUpEntry();
		SeleniumUtil.sleep(2);
		takeScreenShot(testName);
	}

	
	/**
	 * This method verifies whether Banner Text is accurate with an challenge which has expired where user has signed up.
	 * @throws TimeoutException 
	 */
	@Test(description = "This method verifies whether Banner Text is accurate with an challenge which has expired where user has signed up.",priority=15,groups={"regression"})
	public void verifyRegisteredPostChallengeForPumpUpYourHeart() throws TimeoutException{
		SeleniumUtil.sleep(1);
		OnTrackHomePageobj.setDelorianforHeartyHealth(onTrackData.get(POST_CHALLENGE_SURVEY_DATE1));
		SeleniumUtil.sleep(3);
		heartyHealth.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(POST_CHALLENGE_SURVEY_TXT1)));
		takeScreenShot(testName);
	}
	
	@Test(description = "This method verifies whether Overview and Challenge tabs are displayed.",priority=16,groups={"regression"})
	public void verifyOnTrackSteadyYourBeatsTabs() throws TimeoutException{
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.setDelorianforHeartyHealth(onTrackData.get(UPCOMING_DATE2));
		SeleniumUtil.sleep(2);
		SeleniumUtil.sleep(2);
		heartyHealth.assertTrue(OnTrackHomePageobj.verifyChallengeTab());
		SeleniumUtil.sleep(1);
		heartyHealth.assertTrue(OnTrackHomePageobj.verifyOverviewTab());
		takeScreenShot(testName);
		
	}
	
	/**
	 * This method verifies whether Overview and Challenge tabs are displayed with an upcoming campaign.
	 * 
	 */
	@Test(description = "This method verifies whether Overview and Challenge tabs are displayed with an upcoming campaign.",priority=17,groups={"regression"})
	public void verifytSteadyYourBeatsTabsUpcomingChallenge(){
		
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.clickOnChallengeTab();
		SeleniumUtil.sleep(2);
		heartyHealth.assertTrue(OnTrackHomePageobj.verifyChallengeTab());
		SeleniumUtil.sleep(2);
		heartyHealth.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(UPCOMING_BANNER_TXT2)));
		takeScreenShot(testName);
	}

	
	/**
	 * This method verifies whether Banner Text is accurate with an challenge that is over(backlog page).
	 * @throws TimeoutException 
	 */
	@Test(description = "This method verifies whether Banner Text is accurate with an challenge that is over(backlog page).",priority=18, groups={"regression"})
	public void verifyAtEaseChallenge() throws TimeoutException{
		
		SeleniumUtil.sleep(3);
		OnTrackHomePageobj.setDelorianforHeartyHealth(onTrackData.get(BACKLOG_ACTIVE_DATE2));
		SeleniumUtil.sleep(4);
		heartyHealth.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(BACKLOG_BANNER_TXT)));	
		takeScreenShot(testName);

	}
	
	/**
	 * This method verifies whether Banner Text is accurate with an not registered challenge.
	 * @throws TimeoutException 
	 * @throws InterruptedException 
	 * @throws ParseException 
	 */
	@Test(description = "This method verifies whether Banner Text is accurate with an challenge which has expired without user sign up.",priority=19,groups={"regression"})
	public void verifyNotRegisteredChallengeForAtEaaseChallenge() throws TimeoutException, ParseException, InterruptedException{

		SeleniumUtil.sleep(3);
		OnTrackHomePageobj.setDelorianforHeartyHealth(onTrackData.get(NOT_REGISTERED_DATE2));
		SeleniumUtil.sleep(3);
		heartyHealth.assertTrue(OnTrackHomePageobj.verifyChallengeTab());
		heartyHealth.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(NOT_REGISTERED_BANNER_TXT3)));
		heartyHealth.assertTrue(OnTrackHomePageobj.verifySignUpButton());
		SeleniumUtil.sleep(1);
		OnTrackHomePageobj.clickSignUpButton();
		 SeleniumUtil.sleep(5);
		 heartyHealth.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(REGISTERED_NO_APP_BANNER_TXT)));
		  SeleniumUtil.sleep(6);
		// OnTrackHomePageobj.addSleepEntryForAtEaseChallenge();
		// OnTrackHomePageobj.addHeartyChallengeEntry();
		 
			
		 takeScreenShot(testName);
			
		}
	
	/**
	 * This method verifies whether Banner Text is accurate with an challenge which has expired where user has signed up.
	 * @throws TimeoutException 
	 */
	@Test(description = "This method verifies whether Banner Text is accurate with an challenge which has expired where user has signed up.",priority=20, groups={"regression"})
	public void verifyAtEaseRegisteredPostChallenge() throws TimeoutException{

		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.setDelorianforHeartyHealth(onTrackData.get(POST_CHALLENGE_SURVEY_DATE2));
		SeleniumUtil.sleep(2);
		heartyHealth.assertTrue(OnTrackHomePageobj.verifyChallengeTab());
		heartyHealth.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(POST_CHALLENGE_SURVEY_TXT2)));
		takeScreenShot(testName);
	}

	/**
	 * This method verifies whether Banner Text is accurate with an challenge that requires to fill the survey.
	 * @throws TimeoutException 
	 */
	
	@Test(description = "This method verifies whether Banner Text is accurate with an challenge that requires to fill the survey.",priority=21,groups={"regression"})
	public void verifyAtEasePostChallengeSurvey() throws TimeoutException{
		
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.setDelorianforHeartyHealth(onTrackData.get(POST_CHALLENGE_SURVEY_DATE2));
		SeleniumUtil.sleep(2);
		SeleniumUtil.sleep(2);
		heartyHealth.assertTrue(OnTrackHomePageobj.verifyChallengeTab());
		heartyHealth.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(POST_CHALLENGE_SURVEY_TXT2)));
		SeleniumUtil.sleep(4);
		OnTrackHomePageobj.fillSurvey_weight();
		takeScreenShot(testName);	
		SeleniumUtil.sleep(3);
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
