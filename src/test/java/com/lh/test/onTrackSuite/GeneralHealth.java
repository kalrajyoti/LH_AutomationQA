package com.lh.test.onTrackSuite;
import java.io.IOException;
import java.text.ParseException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import com.lh.dao.IOnTrackDao;
import com.lh.dao.OnTrackDaoImpl;
import com.lh.pages.authenticated.ontrack.OnTrackHomePage;
import com.lh.test.base.AuthBaseTestClass;
import com.lh.utils.SeleniumUtil;

/**
 * <h2>Verify Eating For Life Theme,Active 365 and
 *At Ease and verify values based on the dates ie from portal!</h2> 
 * <p>
 * @author akash.vansil
 * @version 1.0
 * @since 2016-23-03
 */

@Listeners(com.lh.helper.LHTestListener.class)
public class GeneralHealth  extends AuthBaseTestClass{

	 String testName;
	private static final String VALID_PASSWORD 					= "validPassword";
	/** Contains the valid username to login to the client portal */
	private static final String VALID_USERNAME 					= "General_User";
    private Assertion generalHealth  = new Assertion();
    private OnTrackHomePage  OnTrackHomePageobj;
    private Map<String, String> onTrackData;
    private final String UPCOMING_DATE = "ThemeStartDate";
    private final String UPCOMING_DATE1 = "ThemeStartDate1";
    private final String UPCOMING_DATE2 = "ThemeStartDate2";
    private final String OVERVIEW_HEADER = "GeneralHeader";
    private final String OVERVIEW_HEADERName = "Heading";
    private final String OVERVIEW_BODYHeader    =  "OverviewBodyHeader";
    private final String OVERVIEW_BODY    =  "OverviewWhyBanner";
    private final String CHALLENGES_BODYHeader = "OverviewChallengeHeader"; 
    private final String CHALLENGES_BODY = "OverviewGeneralChallengeBody"; 
    private final String CHALLENGES_BODY1 = "OverviewActive365Challenge"; 
    private final String UPCOMING_BANNER_TXT = "UpcomingGeneralChallengeCaption1";
    private final String UPCOMING_BANNER_TXT1 = "UpcomingGeneralChallengeCaption2";
    private final String UPCOMING_BANNER_TXT2 = "UpcomingGeneralChallengeCaption3";
    private final String BACKLOG_ACTIVE_DATE="BacklogActive";
    private final String BACKLOG_ACTIVE_DATE1="BacklogActiveChef";
    private final String BACKLOG_ACTIVE_DATE2 ="ExhaleBacklogActive";
    private final String BACKLOG_BANNER_TXT="BacklogActiveCaption1";
    private final String NOT_REGISTERED_DATE = "CampaignNotRegistered";
    private final String NOT_REGISTERED_DATE1 = "campaignNotRegistered1";
    private final String NOT_REGISTERED_DATE2 = "campaignNotRegistered2";
	
    private final String REGISTERED_ConnectApp = "RegistererdSignUPText";
    private final String NOT_REGISTERED_BANNER_TXT = "NotRegisteredGeneralCaption1";
    private final String NOT_REGISTERED_BANNER_TXT1 = "signUpGeneralBannerText";
    private final String NOT_REGISTERED_BANNER_TXT3 = "signUpAtEaseBannerText";
    private final String POST_CHALLENGE_SURVEY_DATE ="RegisteredPostChallengeSurveyComplete";
    private final String POST_CHALLENGE_SURVEY_DATE2 = "RegisteredPostChallengeSurveyComplete2";
	private final String POST_CHALLENGE_SURVEY_DATE1 ="RegisteredPostChallengeSurveyComplete1";
    private final String REGISTERED_NO_APP_BANNER_TXT1 = "RegisteredNoAppsCaptions2";
    private final String CHALLENGE_START_DATE = "challengeStartDate";
    private final String CHALLENGE_START_DATE1 = "challengeStartDate1";
    
	
    private final String POST_CHALLENGE_SURVEY_TXT="RegisteredPostChallengeSurvey1";
    private final String POST_CHALLENGE_SURVEY_TXT1="RegisteredPostChallengeSurvey2";
    private final String POST_CHALLENGE_SURVEY_TXT2="RegisteredPostChallengeSurvey3";
    private final String POST_REGISTRATION_BACKLOG_TXT = "PostRegisterationBackLogCaption1";
    private final String generalHealthURL="generalHealthURL"; 
    	
    /**
	 * This method runs before the first test from the class runs.
	 * @throws IOException 
	 */
	@BeforeClass
 	public void initClass() throws IOException {
		
		onTrackData = readexcelsheet("OnTracker");
		IOnTrackDao daoObj = new OnTrackDaoImpl();
		daoObj.resetonTrackForUser(onTrackData.get(VALID_USERNAME));
		doLogin(onTrackData.get(VALID_USERNAME), onTrackData.get(VALID_PASSWORD),"Portal","Portal",onTrackData.get(generalHealthURL));
		OnTrackHomePageobj = new OnTrackHomePage();

	}
	
	/**
	 * This method runs before each test from the class runs
	 */
	

	/**
	 * This method verifies whether Overview and Challenge tabs are displayed.
	 * @throws TimeoutException 
	 */
	@Test(description = "This method verifies whether Overview and Challenge tabs are displayed.",priority=1,groups={"regression"})
	public void verifyOnTrackGeneralTabs() throws TimeoutException {
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.setGeDelorian(onTrackData.get(UPCOMING_DATE));
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.slectYourToolsLink();
		OnTrackHomePageobj.clickOnOnTrackLink();
		SeleniumUtil.sleep(3);
		generalHealth.assertTrue(OnTrackHomePageobj.verifyChallengeTab());
		SeleniumUtil.sleep(1);
		generalHealth.assertTrue(OnTrackHomePageobj.verifyOverviewTab());
		
	}

	/**
	 * This method verifies the content of Overview tab is displayed.
	 * @throws TimeoutException 
	 */ 
	@Test(description = "This method verifies the content of Overview tab is displayed.",priority=2,groups={"regression"})
	public void verifyOverviewGeneralTabContent() throws TimeoutException{
		
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.clickOverviewTab();
		SeleniumUtil.sleep(3);
		generalHealth.assertTrue(OnTrackHomePageobj.verifyOverviewHeaderInfo(onTrackData.get(OVERVIEW_HEADERName)));
		generalHealth.assertTrue(OnTrackHomePageobj.verifyOverviewHeaderText(onTrackData.get(OVERVIEW_HEADER)));
		generalHealth.assertTrue(OnTrackHomePageobj.verifyOverviewBodyHeader(onTrackData.get(OVERVIEW_BODYHeader)));
		generalHealth.assertTrue(OnTrackHomePageobj.verifyOverviewBodyText(onTrackData.get(OVERVIEW_BODY)));
		SeleniumUtil.sleep(1);
		generalHealth.assertTrue(OnTrackHomePageobj.verifyChallengesBodyHeader(onTrackData.get(CHALLENGES_BODYHeader)));
		generalHealth.assertTrue(OnTrackHomePageobj.verifyChallengesBodyText(onTrackData.get(CHALLENGES_BODY)));
	}
	
	
	/**
	 * This method verifies whether Overview and Challenge tabs are displayed with an upcoming campaign.
	 * @throws TimeoutException 
	 * 
	 */

	@Test(description = "This method verifies whether Overview and Challenge tabs are displayed with an upcoming campaign.",priority=3,groups={"regression"})
	public void verifyUpcomingGeneralChallenge() throws TimeoutException{
	
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.clickOnChallengeTab();
		SeleniumUtil.sleep(3);
		generalHealth.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(UPCOMING_BANNER_TXT)));
		SeleniumUtil.sleep(2);
	}
	
	/**
	 * This method verifies whether Banner Text is accurate with an challenge that is over(backlog page).
	 * @throws TimeoutException 
	 */
	@Test(description = "This method verifies whether Banner Text is accurate with an challenge that is over(backlog page).",priority=4,groups={"regression"})
	public void verifyWeightGeneralBackLogChallenge() throws TimeoutException{
		
	
		OnTrackHomePageobj.setGeDelorian(onTrackData.get(BACKLOG_ACTIVE_DATE));
		SeleniumUtil.sleep(2);
		generalHealth.assertTrue(OnTrackHomePageobj.verifyChallengeTab());
		SeleniumUtil.sleep(2);
		generalHealth.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(BACKLOG_BANNER_TXT)));
	}

	/**
	 * This method verifies whether Banner Text is accurate with an not registered challenge.
	 * @throws TimeoutException 
	 */

	@Test(description = "This method verifies whether Banner Text is accurate with an not registered challenge.",priority=5,groups={"regression"})
	public void verifyGeneralNotRegisteredAndRegiterChallenge() throws TimeoutException{
		
	
		OnTrackHomePageobj.setGeDelorian(onTrackData.get(NOT_REGISTERED_DATE));
		SeleniumUtil.sleep(2);
		generalHealth.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(NOT_REGISTERED_BANNER_TXT)));
		SeleniumUtil.sleep(2);
		generalHealth.assertTrue(OnTrackHomePageobj.verifySignUpButton());
		 OnTrackHomePageobj.clickSignUpButton();
		 SeleniumUtil.sleep(5);
		 generalHealth.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(REGISTERED_NO_APP_BANNER_TXT1)));
	}

	/**
	 * This method verifies whether Banner Text is accurate with an not registered challenge.
	 * @throws ParseException 
	 * @throws TimeoutException 
	 * @throws InterruptedException 
	 */
	@Test(description = "This method verifies whether Banner Text is accurate with an not registered challenge.",priority=6,groups={"regression"})
	public void verifyGeneralRegisteredNoAppsChallengeAddEntry() throws ParseException, TimeoutException, InterruptedException{
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.setGeDelorian(onTrackData.get(CHALLENGE_START_DATE));
		SeleniumUtil.sleep(2);
		generalHealth.assertTrue(OnTrackHomePageobj.verifyChallengeTab());
		SeleniumUtil.sleep(5);
		OnTrackHomePageobj.addFoodEntryForGeneralHealth();
		SeleniumUtil.sleep(2);
	}
	
	/**
	 * This method verifies whether Banner Text is accurate with an challenge which has expired where user has signed up.
	 * @throws TimeoutException 
	 */
	@Test(description = "This method verifies whether Banner Text is accurate with an challenge which has expired where user has signed up.",priority=7,groups={"regression"})
	public void verifyRegisteredPostChallengeForGeneral() throws TimeoutException{
		
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.setGeDelorian(onTrackData.get(POST_CHALLENGE_SURVEY_DATE));
		SeleniumUtil.sleep(2);
		generalHealth.assertTrue(OnTrackHomePageobj.verifyChallengeTab());
		SeleniumUtil.sleep(2);
		generalHealth.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(POST_CHALLENGE_SURVEY_TXT)));
	}
	
	/**
	 * This method verifies whether Banner Text is accurate with an challenge that is over(backlog page).
	 * @throws TimeoutException 
	 */
	@Test(description = "This method verifies whether Banner Text is accurate with an challenge that is over(backlog page).",priority=8,groups={"regression"})
	public void verifyRegisteredBackLogChallengeForGeneral() throws TimeoutException{
		
	
		OnTrackHomePageobj.setGeDelorian(onTrackData.get(BACKLOG_ACTIVE_DATE));
		SeleniumUtil.sleep(3);
		generalHealth.assertTrue(OnTrackHomePageobj.verifyChallengeTab());
		generalHealth.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(POST_REGISTRATION_BACKLOG_TXT)));
	}
	

	@Test(description = "This method verifies whether Overview and Challenge tabs are displayed.", priority=9,groups={"regression"})
	public void verifyOnTrackActive365Tabs() throws TimeoutException {
		SeleniumUtil.sleep(4);
		OnTrackHomePageobj.setGeDelorian(onTrackData.get(UPCOMING_DATE1));
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.slectYourToolsLink();
		OnTrackHomePageobj.clickOnOnTrackLink();
		SeleniumUtil.sleep(3);
		generalHealth.assertTrue(OnTrackHomePageobj.verifyChallengeTab());
		SeleniumUtil.sleep(1);
		generalHealth.assertTrue(OnTrackHomePageobj.verifyOverviewTab());	
	}
	
	/**
	 * This method verifies the content of Overview tab is displayed.
	 * @throws TimeoutException 
	 */ 
	
	@Test(description = "This method verifies the content of Overview tab is displayed.",priority=10,groups={"regression"})
	public void verifyOverviewActive365TabContent() throws TimeoutException{
		
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.clickOverviewTab();
		generalHealth.assertTrue(OnTrackHomePageobj.verifyOverviewTab());
		SeleniumUtil.sleep(2);
		generalHealth.assertTrue(OnTrackHomePageobj.verifyChallengesBodyHeader(onTrackData.get(CHALLENGES_BODYHeader)));
		generalHealth.assertTrue(OnTrackHomePageobj.verifyChallengesBodyText(onTrackData.get(CHALLENGES_BODY1)));
	}
	
	/**
	 * This method verifies whether Overview and Challenge tabs are displayed with an upcoming campaign.
	 * @throws TimeoutException 
	 * 
	 */

	@Test(description = "This method verifies whether Overview and Challenge tabs are displayed with an upcoming campaign.",priority=11,groups={"regression"})
	public void verifyUpcomingChallengeForActive365() throws TimeoutException{
	
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.clickOnChallengeTab();
		SeleniumUtil.sleep(3);
		generalHealth.assertTrue(OnTrackHomePageobj.verifyChallengeTab());
		SeleniumUtil.sleep(2);
		generalHealth.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(UPCOMING_BANNER_TXT1)));
	}
	
	/**
	 * This method verifies whether Banner Text is accurate with an challenge that is over(backlog page).
	 * @throws TimeoutException 
	 */
	@Test(description = "This method verifies whether Banner Text is accurate with an challenge that is over(backlog page).",priority=12,groups={"regression"})
	public void verifyActive365BackLogChallenge() throws TimeoutException{
		
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.setGeDelorian(onTrackData.get(BACKLOG_ACTIVE_DATE1));
		SeleniumUtil.sleep(2);
		generalHealth.assertTrue(OnTrackHomePageobj.verifyChallengeTab());
		generalHealth.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(BACKLOG_BANNER_TXT)));	
	}
	

	/**
	 * This method verifies whether Banner Text is accurate with an not registered challenge.
	 * @throws TimeoutException 
	 */

	@Test(description = "This method verifies whether Banner Text is accurate with an not registered challenge.",priority=13,groups={"regression"})
	public void verifyNotRegisteredChallengeForActive365() throws TimeoutException{
		
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.setGeDelorian(onTrackData.get(NOT_REGISTERED_DATE1));
		SeleniumUtil.sleep(4);
		generalHealth.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(NOT_REGISTERED_BANNER_TXT1)));
		generalHealth.assertTrue(OnTrackHomePageobj.verifySignUpButton());
		OnTrackHomePageobj.clickSignUpButton();
		SeleniumUtil.sleep(10);
		generalHealth.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(REGISTERED_ConnectApp)));
	}
	
	/**
	 * This method verifies whether Banner Text is accurate with an not registered challenge.
	 * @throws ParseException 
	 * @throws TimeoutException 
	 * @throws InterruptedException 
	 */
	@Test(description = "This method verifies whether Banner Text is accurate with an not registered challenge.",priority=14,groups={"regression"})
	public void verifyRegisteredNoAppsChallengeAddEntryForActive365() throws ParseException, TimeoutException, InterruptedException{
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.setGeDelorian(onTrackData.get(CHALLENGE_START_DATE1));
		SeleniumUtil.sleep(2);
		generalHealth.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(REGISTERED_ConnectApp)));
		SeleniumUtil.sleep(5);
		OnTrackHomePageobj.addWeightEntry();	
		SeleniumUtil.sleep(2);
	}

	
	/**
	 * This method verifies whether Banner Text is accurate with an challenge which has expired where user has signed up.
	 * @throws TimeoutException 
	 */
	@Test(description = "This method verifies whether Banner Text is accurate with an challenge which has expired where user has signed up.",priority=15,groups={"regression"})
	public void verifyRegisteredPostChallengeForActive365() throws TimeoutException{
		SeleniumUtil.sleep(1);
		OnTrackHomePageobj.setGeDelorian(onTrackData.get(POST_CHALLENGE_SURVEY_DATE1));
		SeleniumUtil.sleep(3);
		generalHealth.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(POST_CHALLENGE_SURVEY_TXT1)));
	}
	
	@Test(description = "This method verifies whether Overview and Challenge tabs are displayed.",priority=16,groups={"regression"})
	public void verifyOnTrackAtEaseTabs() throws TimeoutException{
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.setGeDelorian(onTrackData.get(UPCOMING_DATE2));
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.slectYourToolsLink();
		OnTrackHomePageobj.clickOnOnTrackLink();
		SeleniumUtil.sleep(2);
		generalHealth.assertTrue(OnTrackHomePageobj.verifyChallengeTab());
		SeleniumUtil.sleep(1);
		generalHealth.assertTrue(OnTrackHomePageobj.verifyOverviewTab());
	}
	
	/**
	 * This method verifies whether Overview and Challenge tabs are displayed with an upcoming campaign.
	 * 
	 */
	@Test(description = "This method verifies whether Overview and Challenge tabs are displayed with an upcoming campaign.",priority=17,groups={"regression"})
	public void verifyAtEaseUpcomingChallenge(){
		
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.clickOnChallengeTab();
		SeleniumUtil.sleep(2);
		generalHealth.assertTrue(OnTrackHomePageobj.verifyChallengeTab());
		SeleniumUtil.sleep(2);
		generalHealth.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(UPCOMING_BANNER_TXT2)));	
	}

	
	/**
	 * This method verifies whether Banner Text is accurate with an challenge that is over(backlog page).
	 * @throws TimeoutException 
	 */
	@Test(description = "This method verifies whether Banner Text is accurate with an challenge that is over(backlog page).",priority=18, groups={"regression"})
	public void verifyAtEaseChallenge() throws TimeoutException{
		
		SeleniumUtil.sleep(3);
		OnTrackHomePageobj.setGeDelorian(onTrackData.get(BACKLOG_ACTIVE_DATE2));
		SeleniumUtil.sleep(4);
		generalHealth.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(BACKLOG_BANNER_TXT)));	
		}
	
	/**
	 * This method verifies whether Banner Text is accurate with an not registered challenge.
	 * @throws TimeoutException 
	 * @throws InterruptedException 
	 * @throws ParseException 
	 */
	@Test(description = "This method verifies whether Banner Text is accurate with an challenge which has expired without user sign up.",priority=19,groups={"regression"})
	public void verifyNotRegisteredChallengeForAtEaaseChallenge() throws TimeoutException, ParseException, InterruptedException{

		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.setGeDelorian(onTrackData.get(NOT_REGISTERED_DATE2));
		SeleniumUtil.sleep(3);
		generalHealth.assertTrue(OnTrackHomePageobj.verifyChallengeTab());
		generalHealth.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(NOT_REGISTERED_BANNER_TXT3)));
		generalHealth.assertTrue(OnTrackHomePageobj.verifySignUpButton());
		SeleniumUtil.sleep(1);
		OnTrackHomePageobj.clickSignUpButton();
		SeleniumUtil.sleep(7);
		generalHealth.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(REGISTERED_ConnectApp)));
		SeleniumUtil.sleep(5);
		 OnTrackHomePageobj.addSleepEntryForAtEaseChallenge();
		 }
	
	/**
	 * This method verifies whether Banner Text is accurate with an challenge which has expired where user has signed up.
	 * @throws TimeoutException 
	 */
	@Test(description = "This method verifies whether Banner Text is accurate with an challenge which has expired where user has signed up.",priority=20, groups={"regression"})
	public void verifyAtEaseRegisteredPostChallenge() throws TimeoutException{

		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.setGeDelorian(onTrackData.get(POST_CHALLENGE_SURVEY_DATE2));
		SeleniumUtil.sleep(2);
		generalHealth.assertTrue(OnTrackHomePageobj.verifyChallengeTab());
		generalHealth.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(POST_CHALLENGE_SURVEY_TXT2)));
	}

	/**
	 * This method verifies whether Banner Text is accurate with an challenge that requires to fill the survey.
	 * @throws TimeoutException 
	 */
	
	@Test(description = "This method verifies whether Banner Text is accurate with an challenge that requires to fill the survey.",priority=21,groups={"regression"})
	public void verifyAtEasePostChallengeSurvey() throws TimeoutException{
		
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.setGeDelorian(onTrackData.get(POST_CHALLENGE_SURVEY_DATE2));
		SeleniumUtil.sleep(2);
		SeleniumUtil.sleep(2);
		generalHealth.assertTrue(OnTrackHomePageobj.verifyChallengeTab());
		generalHealth.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(POST_CHALLENGE_SURVEY_TXT2)));
	
		}
	
	/**
	 * This class runs after running all the methods in the class 
	 */
	@AfterClass
	public void tearDown() {
		
		OnTrackHomePageobj = null;
	
			}
	

}
