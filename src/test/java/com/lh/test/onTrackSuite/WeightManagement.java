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
 * <h2>Verify HealthyWeightChowDown Theme,HealthyWeightShowdown and
 * HealthyWeightLowdown and verify values based on the dates ie from portal!</h2> 
 * <p>
 * @author akash.vansil
 * @version 1.0
 * @since 2016-23-03
 */

@Listeners(com.lh.helper.LHTestListener.class)
public class WeightManagement  extends LHBaseTestClass{

	String testName;
	private LoginPage loginPageObj;
	private static final String VALID_PASSWORD 					= "Weight_Pass";
	/** Contains the valid username to login to the client portal */
	private static final String VALID_USERNAME 					= "Weight_User";
	private ExcelFileUtility testDataObj;
    private Assertion weightManage  = new Assertion();
    private OnTrackHomePage  OnTrackHomePageobj;
    private Map<String, String> onTrackData;
    private static final String UPCOMING_DATE = "ThemeStartDate";
    private static final String UPCOMING_DATE1 = "ThemeStartDate1";
    private static final String UPCOMING_DATE2 = "ThemeStartDate2";
    
    
    private static final String OVERVIEW_HEADER = "WeightHeader";
    private static final String OVERVIEW_HEADERName = "Heading";
    
    private static final String OVERVIEW_BODYHeader    =  "OverviewWeightHeader";
    private static final String OVERVIEW_BODY    =  "OverviewWeightBanner";
    private static final String CHALLENGES_BODYHeader = "OverviewChallengeHeader"; 
    private static final String CHALLENGES_BODY = "OverviewWeightChallenge"; 
    private static final String CHALLENGES_BODY1 = "OverviewWeightChallenge1"; 
    
    private static final String CHALLENGE_INFO_TXT = "WeightChallengeText";
    private static final String UPCOMING_BANNER_TXT = "UpcomingWeightChallengeCaption1";
    private static final String UPCOMING_BANNER_TXT1 = "UpcomingWeightChallengeCaption2";
    
    private static final String UPCOMING_BANNER_TXT2 = "UpcomingWeightChallengeCaption3";
    
    private static final String BACKLOG_ACTIVE_DATE="BacklogActive";
    private static final String BACKLOG_ACTIVE_DATE1="BacklogActiveChef";
    private static final String BACKLOG_ACTIVE_DATE2 ="ExhaleBacklogActive";
    private static final String BACKLOG_BANNER_TXT="BacklogActiveCaption1";
    
    private static final String NOT_REGISTERED_DATE = "CampaignNotRegistered";
    private static final String NOT_REGISTERED_DATE1 = "campaignNotRegistered1";
    private static final String NOT_REGISTERED_DATE2 = "campaignNotRegistered2";
	
    private static final String REGISTERED_NO_APP_BANNER = "RegisteredNoApps";
    private static final String REGISTERED_Connect_App="RegisteredExaleNoApps";
    
    
    private static final String NOT_REGISTERED_BANNER_TXT = "NotRegisteredWeightCaption1";
    private static final String NOT_REGISTERED_BANNER_TXT1 = "signUpHealthyWeightBannerText";
    private static final String NOT_REGISTERED_BANNER_TXT3 = "NotRegisteredWeightCaption2";
    
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
		//DriverFactory.openURL();
		DriverFactory.openURL(TestProperty.WEIGHTURL);
		loginPageObj = new LoginPage();

		//logger.info("Calling the loginPageObj.loginAs(username, password) method");
		loginPageObj.loginAs(onTrackData.get(VALID_USERNAME), onTrackData.get(VALID_PASSWORD));
		SeleniumUtil.sleep(2);

		OnTrackHomePageobj = new OnTrackHomePage();
		SeleniumUtil.sleep(2);
		//testName = method.getName();

	}
	
	/**
	 * This method runs before each test from the class runs
	 */
	@BeforeMethod
	public void init(Method method) throws Exception {
		Reporter.log("\n Inside the init() method for OnTrack_WeightManagement class...");
        //logger.info("Exiting the init() method for OnTrack_FindingBalanceTest class \n");
	}


	/**
	 * This method verifies whether Overview and Challenge tabs are displayed.
	 * @throws TimeoutException 
	 */
	@Test(description = "This method verifies whether Overview and Challenge tabs are displayed.",priority=1,groups={"regression"})
	public void verifyOnTrackWeightTabs() throws TimeoutException {
		SeleniumUtil.sleep(6);
		OnTrackHomePageobj.setDelorianforNutrition(onTrackData.get(UPCOMING_DATE));
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.slectYourToolsLink();
		OnTrackHomePageobj.clickOnTrackLinks();
		SeleniumUtil.sleep(2);
    	weightManage.assertTrue(OnTrackHomePageobj.verifyChallengeTab());
		SeleniumUtil.sleep(1);
		weightManage.assertTrue(OnTrackHomePageobj.verifyOverviewTab());
		takeScreenShot(testName);
		
	}

	/**
	 * This method verifies the content of Overview tab is displayed.
	 * @throws TimeoutException 
	 */ 
	@Test(description = "This method verifies the content of Overview tab is displayed.",priority=2,groups={"regression"})
	public void verifyOverviewWeightTabContent() throws TimeoutException{
		
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.clickOverviewTab();
		SeleniumUtil.sleep(3);
		weightManage.assertTrue(OnTrackHomePageobj.verifyOverviewHeaderInfo(onTrackData.get(OVERVIEW_HEADERName)));
		weightManage.assertTrue(OnTrackHomePageobj.verifyOverviewHeaderText(onTrackData.get(OVERVIEW_HEADER)));
		weightManage.assertTrue(OnTrackHomePageobj.verifyOverviewBodyHeader(onTrackData.get(OVERVIEW_BODYHeader)));
		weightManage.assertTrue(OnTrackHomePageobj.verifyOverviewBodyText(onTrackData.get(OVERVIEW_BODY)));
		SeleniumUtil.sleep(1);
		weightManage.assertTrue(OnTrackHomePageobj.verifyChallengesBodyHeader(onTrackData.get(CHALLENGES_BODYHeader)));
		weightManage.assertTrue(OnTrackHomePageobj.verifyChallengesBodyText(onTrackData.get(CHALLENGES_BODY)));
		takeScreenShot(testName);
	}
	
	
	/**
	 * This method verifies whether Overview and Challenge tabs are displayed with an upcoming campaign.
	 * @throws TimeoutException 
	 * 
	 */

	@Test(description = "This method verifies whether Overview and Challenge tabs are displayed with an upcoming campaign.",priority=3,groups={"regression"})
	public void verifyUpcomingWeightChowDownChallenge() throws TimeoutException{
	
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.clickOnChallengeTab();
		SeleniumUtil.sleep(3);
		weightManage.assertTrue(OnTrackHomePageobj.verifyChallengeBannerText(onTrackData.get(CHALLENGE_INFO_TXT)));
		weightManage.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(UPCOMING_BANNER_TXT)));
		SeleniumUtil.sleep(2);
		takeScreenShot(testName);
	}
	
	/**
	 * This method verifies whether Banner Text is accurate with an challenge that is over(backlog page).
	 * @throws TimeoutException 
	 */
	@Test(description = "This method verifies whether Banner Text is accurate with an challenge that is over(backlog page).",priority=4,groups={"regression"})
	public void verifyWeightChowDownbBackLogChallenge() throws TimeoutException{
		
	
		OnTrackHomePageobj.setDelorian(onTrackData.get(BACKLOG_ACTIVE_DATE));
		SeleniumUtil.sleep(2);
		weightManage.assertTrue(OnTrackHomePageobj.verifyChallengeTab());
		SeleniumUtil.sleep(2);
		weightManage.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(BACKLOG_BANNER_TXT)));
		takeScreenShot(testName);
	}

	/**
	 * This method verifies whether Banner Text is accurate with an not registered challenge.
	 * @throws TimeoutException 
	 */

	@Test(description = "This method verifies whether Banner Text is accurate with an not registered challenge.",priority=5,groups={"regression"})
	public void verifyWeightChowDownNotRegisteredAndRegiterChallenge() throws TimeoutException{
		
	
		OnTrackHomePageobj.setDelorian(onTrackData.get(NOT_REGISTERED_DATE));
		SeleniumUtil.sleep(2);
		weightManage.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(NOT_REGISTERED_BANNER_TXT)));
		SeleniumUtil.sleep(2);
		 weightManage.assertTrue(OnTrackHomePageobj.verifySignUpButton());
		 OnTrackHomePageobj.clickSignUpButton();
		 SeleniumUtil.sleep(5);
		 weightManage.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(REGISTERED_NO_APP_BANNER_TXT1)));
		 //weightManage.assertTrue(OnTrackHomePageobj.REGISTERED_NO_APP2_BANNER_Text(onTrackData.get(REGISTERED_NO_APP_BANNER_TXT2)));
		takeScreenShot(testName);
	}

	/**
	 * This method verifies whether Banner Text is accurate with an not registered challenge.
	 * @throws ParseException 
	 * @throws TimeoutException 
	 * @throws InterruptedException 
	 */
	@Test(description = "This method verifies whether Banner Text is accurate with an not registered challenge.",priority=6,groups={"regression"})
	public void verifyWeightChowDownRegisteredNoAppsChallengeAddEntry() throws ParseException, TimeoutException, InterruptedException{
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.setDelorian(onTrackData.get(CHALLENGE_START_DATE));
		SeleniumUtil.sleep(2);
		weightManage.assertTrue(OnTrackHomePageobj.verifyChallengeTab());
		SeleniumUtil.sleep(5);
		//OnTrackHomePageobj.addFoodEntry(onTrackData.get(CHALLENGE_START_DATE), onTrackData.get(CHALLENGE_END_DATE));
		SeleniumUtil.sleep(2);
		takeScreenShot(testName);
	}
	
	/**
	 * This method verifies whether Banner Text is accurate with an challenge which has expired where user has signed up.
	 * @throws TimeoutException 
	 */
	@Test(description = "This method verifies whether Banner Text is accurate with an challenge which has expired where user has signed up.",priority=7,groups={"regression"})
	public void verifyRegisteredPostChallengeForWeightChowDown() throws TimeoutException{
		
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.setDelorian(onTrackData.get(POST_CHALLENGE_SURVEY_DATE));
		SeleniumUtil.sleep(2);
		weightManage.assertTrue(OnTrackHomePageobj.verifyChallengeTab());
		SeleniumUtil.sleep(2);
		weightManage.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(POST_CHALLENGE_SURVEY_TXT)));
		takeScreenShot(testName);
	}
	
	/**
	 * This method verifies whether Banner Text is accurate with an challenge that is over(backlog page).
	 * @throws TimeoutException 
	 */
	@Test(description = "This method verifies whether Banner Text is accurate with an challenge that is over(backlog page).",priority=8,groups={"regression"})
	public void verifyRegisteredBackLogChallengeForWeightChowDown() throws TimeoutException{
		
	
		OnTrackHomePageobj.setDelorian(onTrackData.get(BACKLOG_ACTIVE_DATE));
		SeleniumUtil.sleep(3);
		weightManage.assertTrue(OnTrackHomePageobj.verifyChallengeTab());
		weightManage.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(POST_REGISTRATION_BACKLOG_TXT)));
		//weightManage.assertTrue(OnTrackHomePageobj.verifyRegisteredPostChallengeSurvey1Text(onTrackData.get(POST_REGISTRATION_BACKLOG_TXT1)));
		takeScreenShot(testName);
	}
	

	@Test(description = "This method verifies whether Overview and Challenge tabs are displayed.", priority=9,groups={"regression"})
	public void verifyOnTrackWeightShowDownTabs() throws TimeoutException {
		SeleniumUtil.sleep(4);
		OnTrackHomePageobj.setDelorian(onTrackData.get(UPCOMING_DATE1));
		SeleniumUtil.sleep(3);
		weightManage.assertTrue(OnTrackHomePageobj.verifyChallengeTab());
		SeleniumUtil.sleep(1);
		weightManage.assertTrue(OnTrackHomePageobj.verifyOverviewTab());
		takeScreenShot(testName);	
	}
	
	/**
	 * This method verifies the content of Overview tab is displayed.
	 * @throws TimeoutException 
	 */ 
	
	@Test(description = "This method verifies the content of Overview tab is displayed.",priority=10,groups={"regression"})
	public void verifyOverviewWeightShowDownTabContent() throws TimeoutException{
		
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.clickOverviewTab();
		weightManage.assertTrue(OnTrackHomePageobj.verifyOverviewTab());
		SeleniumUtil.sleep(2);
		weightManage.assertTrue(OnTrackHomePageobj.verifyChallengesBodyHeader(onTrackData.get(CHALLENGES_BODYHeader)));
		weightManage.assertTrue(OnTrackHomePageobj.verifyChallengesBodyText(onTrackData.get(CHALLENGES_BODY1)));
		takeScreenShot(testName);
	}
	
	/**
	 * This method verifies whether Overview and Challenge tabs are displayed with an upcoming campaign.
	 * @throws TimeoutException 
	 * 
	 */

	@Test(description = "This method verifies whether Overview and Challenge tabs are displayed with an upcoming campaign.",priority=11,groups={"regression"})
	public void verifyUpcomingChallenge() throws TimeoutException{
	
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.clickOnChallengeTab();
		SeleniumUtil.sleep(3);
		weightManage.assertTrue(OnTrackHomePageobj.verifyChallengeTab());
		SeleniumUtil.sleep(2);
		weightManage.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(UPCOMING_BANNER_TXT1)));
		takeScreenShot(testName);
	}
	
	/**
	 * This method verifies whether Banner Text is accurate with an challenge that is over(backlog page).
	 * @throws TimeoutException 
	 */
	@Test(description = "This method verifies whether Banner Text is accurate with an challenge that is over(backlog page).",priority=12,groups={"regression"})
	public void verifyWeightShowDownBackLogChallenge() throws TimeoutException{
		
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.setDelorian(onTrackData.get(BACKLOG_ACTIVE_DATE1));
		SeleniumUtil.sleep(2);
		weightManage.assertTrue(OnTrackHomePageobj.verifyChallengeTab());
		weightManage.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(BACKLOG_BANNER_TXT)));	
		takeScreenShot(testName);
	}
	

	/**
	 * This method verifies whether Banner Text is accurate with an not registered challenge.
	 * @throws TimeoutException 
	 */

	@Test(description = "This method verifies whether Banner Text is accurate with an not registered challenge.",priority=13,groups={"regression"})
	public void verifyNotRegisteredChallengeForWeightShowDown() throws TimeoutException{
		
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.setDelorian(onTrackData.get(NOT_REGISTERED_DATE1));
		SeleniumUtil.sleep(3);
		weightManage.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(NOT_REGISTERED_BANNER_TXT1)));
		weightManage.assertTrue(OnTrackHomePageobj.verifySignUpButton());
		OnTrackHomePageobj.clickSignUpButton();
		 SeleniumUtil.sleep(5);
		 weightManage.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(REGISTERED_NO_APP_BANNER)));
	     takeScreenShot(testName);
	}
	
	/**
	 * This method verifies whether Banner Text is accurate with an not registered challenge.
	 * @throws ParseException 
	 * @throws TimeoutException 
	 * @throws InterruptedException 
	 */
	@Test(description = "This method verifies whether Banner Text is accurate with an not registered challenge.",priority=14,groups={"regression"})
	public void verifyRegisteredNoAppsChallengeAddEntry() throws ParseException, TimeoutException, InterruptedException{
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.setDelorian(onTrackData.get(CHALLENGE_START_DATE1));
		SeleniumUtil.sleep(2);
		weightManage.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(REGISTERED_NO_APP_BANNER)));
		SeleniumUtil.sleep(4);
		//OnTrackHomePageobj.addWeightEntry();	
		SeleniumUtil.sleep(2);
		takeScreenShot(testName);
	}

	
	/**
	 * This method verifies whether Banner Text is accurate with an challenge which has expired where user has signed up.
	 * @throws TimeoutException 
	 */
	@Test(description = "This method verifies whether Banner Text is accurate with an challenge which has expired where user has signed up.",priority=15,groups={"regression"})
	public void verifyRegisteredPostChallenge() throws TimeoutException{
		SeleniumUtil.sleep(1);
		OnTrackHomePageobj.setDelorian(onTrackData.get(POST_CHALLENGE_SURVEY_DATE1));
		SeleniumUtil.sleep(3);
		weightManage.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(POST_CHALLENGE_SURVEY_TXT1)));
		takeScreenShot(testName);
	}
	
	@Test(description = "This method verifies whether Overview and Challenge tabs are displayed.",priority=16,groups={"regression"})
	public void verifyOnTrackWeightLowDownTabs() throws TimeoutException{
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.setDelorian(onTrackData.get(UPCOMING_DATE2));
		SeleniumUtil.sleep(2);
		SeleniumUtil.sleep(2);
		weightManage.assertTrue(OnTrackHomePageobj.verifyChallengeTab());
		SeleniumUtil.sleep(1);
		weightManage.assertTrue(OnTrackHomePageobj.verifyOverviewTab());
		takeScreenShot(testName);
		
	}
	
	/**
	 * This method verifies whether Overview and Challenge tabs are displayed with an upcoming campaign.
	 * 
	 */
	@Test(description = "This method verifies whether Overview and Challenge tabs are displayed with an upcoming campaign.",priority=17,groups={"regression"})
	public void verifyWeightLowDownUpcomingChallenge(){
		
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.clickOnChallengeTab();
		SeleniumUtil.sleep(2);
		weightManage.assertTrue(OnTrackHomePageobj.verifyChallengeTab());
		SeleniumUtil.sleep(2);
		weightManage.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(UPCOMING_BANNER_TXT2)));
		takeScreenShot(testName);
	}

	
	/**
	 * This method verifies whether Banner Text is accurate with an challenge that is over(backlog page).
	 * @throws TimeoutException 
	 */
	@Test(description = "This method verifies whether Banner Text is accurate with an challenge that is over(backlog page).",priority=18, groups={"regression"})
	public void verifyWeightLowDownBackLogChallenge() throws TimeoutException{
		
		SeleniumUtil.sleep(3);
		OnTrackHomePageobj.setDelorian(onTrackData.get(BACKLOG_ACTIVE_DATE2));
		SeleniumUtil.sleep(4);
		weightManage.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(BACKLOG_BANNER_TXT)));	
		takeScreenShot(testName);

	}
	
	/**
	 * This method verifies whether Banner Text is accurate with an not registered challenge.
	 * @throws TimeoutException 
	 * @throws InterruptedException 
	 * @throws ParseException 
	 */
	@Test(description = "This method verifies whether Banner Text is accurate with an challenge which has expired without user sign up.",priority=19,groups={"regression"})
	public void verifyNotRegisteredChallengeForWeightLowDown() throws TimeoutException, ParseException, InterruptedException{

		SeleniumUtil.sleep(3);
		OnTrackHomePageobj.setDelorian(onTrackData.get(NOT_REGISTERED_DATE2));
		SeleniumUtil.sleep(4);
		weightManage.assertTrue(OnTrackHomePageobj.verifyChallengeTab());
		weightManage.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(NOT_REGISTERED_BANNER_TXT3)));
		weightManage.assertTrue(OnTrackHomePageobj.verifySignUpButton());
		OnTrackHomePageobj.clickSignUpButton();
		 SeleniumUtil.sleep(5);
		 weightManage.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(REGISTERED_Connect_App)));
		  SeleniumUtil.sleep(3);
		 //OnTrackHomePageobj.addExaleEntry();
		 takeScreenShot(testName);
			
		}
	
	/**
	 * This method verifies whether Banner Text is accurate with an challenge which has expired where user has signed up.
	 * @throws TimeoutException 
	 */
	@Test(description = "This method verifies whether Banner Text is accurate with an challenge which has expired where user has signed up.",priority=20, groups={"regression"})
	public void verifyWeightLowDownRegisteredPostChallenge() throws TimeoutException{

		SeleniumUtil.sleep(1);
		OnTrackHomePageobj.setDelorian(onTrackData.get(POST_CHALLENGE_SURVEY_DATE2));
		SeleniumUtil.sleep(2);
		weightManage.assertTrue(OnTrackHomePageobj.verifyChallengeTab());
		weightManage.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(POST_CHALLENGE_SURVEY_TXT2)));
		takeScreenShot(testName);
	}

	/**
	 * This method verifies whether Banner Text is accurate with an challenge that requires to fill the survey.
	 * @throws TimeoutException 
	 */
	
	@Test(description = "This method verifies whether Banner Text is accurate with an challenge that requires to fill the survey.",priority=21,groups={"regression"})
	public void verifyWeightLowDownPostChallengeSurvey() throws TimeoutException{
		
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.setDelorian(onTrackData.get(POST_CHALLENGE_SURVEY_DATE2));
		SeleniumUtil.sleep(2);
		SeleniumUtil.sleep(2);
		weightManage.assertTrue(OnTrackHomePageobj.verifyChallengeTab());
		weightManage.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(POST_CHALLENGE_SURVEY_TXT2)));
		SeleniumUtil.sleep(1);
		OnTrackHomePageobj.fillSurvey_weight();
		takeScreenShot(testName);	
		SeleniumUtil.sleep(2);
		}
	
	/**
	 * This method runs after each test from the class runs 
	 */
	@AfterClass
	public void tearDown() {
		OnTrackHomePageobj = null;
		loginPageObj= null;
		driver.quit();
		
	}

}
