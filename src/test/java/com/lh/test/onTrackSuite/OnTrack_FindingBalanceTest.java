package com.lh.test.onTrackSuite;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import org.apache.http.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import com.lh.dao.ILifestyleManagerDao;
import com.lh.dao.IOnTrackDao;
import com.lh.dao.LifestyleManagerDaoImpl;
import com.lh.dao.OnTrackDaoImpl;
import com.lh.pages.authenticated.ontrack.OnTrackHomePage;
import com.lh.test.base.AuthBaseTestClass;
import com.lh.utils.SeleniumUtil;

@Listeners(com.lh.helper.LHTestListener.class)
public class OnTrack_FindingBalanceTest  extends AuthBaseTestClass {

	private static Logger logger = LogManager.getLogger(OnTrack_FindingBalanceTest.class);
	/**
	 * Name of the current running test method
	 */
	String testName;
	private static final String VALID_PASSWORD 					= "Password";
	private static final String VALID_USERNAME 					= "Username";
    private final String portalURL								= "portalURL";
	private Assertion findingBal  = new Assertion();
    private OnTrackHomePage  OnTrackHomePageobj;
    private Map<String, String> onTrackData;
    private static final String UPCOMING_DATE = "ThemeStartDate";
    private static final String OVERVIEW_HEADER = "OverviewHeader";
    private static final String OVERVIEW_HEADERName = "Heading";
    private static final String OVERVIEW_BODYHeader    =  "OverviewWhyHeader";
    private static final String OVERVIEW_BODY    =  "OverviewWhyBanner";
    private static final String CHALLENGES_BODYHeader = "OverviewChallengeHeader"; 
    private static final String CHALLENGES_BODY = "OverviewChallenge"; 
    private static final String CHALLENGE_INFO_TXT = "FindingBalanceChallengeText";
    private static final String BACKLOG_ACTIVE_DATE="BacklogActive";
    private static final String BACKLOG_BANNER_TXT="BacklogActiveCaption1";
    private static final String NOT_REGISTERED_DATE = "CampaignNotRegistered";
    private static final String NOT_REGISTERED_BANNER_TXT = "NotRegisteredCaption1";
    private static final String POST_CHALLENGE_SURVEY_DATE ="RegisteredPostChallengeSurveyComplete";
    private static final String REGISTERED_NO_APP_BANNER_TXT1 = "RegisteredNoAppsCaptions2";    
    private static final String CHALLENGE_START_DATE = "challengeStartDate";



    /**
	 * This method runs before the first test from the class runs.
	 * @throws IOException 
	 */
	@BeforeClass
 	public void initClass() throws IOException {
		logger.info("Inside the initClass() method for On OnTrack_FindingBalanceTest class...");
		onTrackData = readexcelsheet("OnTracker");
		logger.info("Exiting the initClass() method for OnTrack_FindingBalanceTest class \n\n");
		IOnTrackDao daoObj = new OnTrackDaoImpl();
		daoObj.resetonTrackForUser(onTrackData.get(VALID_USERNAME));
		doLogin(onTrackData.get(VALID_USERNAME), onTrackData.get(VALID_PASSWORD),"Portal","Portal",onTrackData.get(portalURL));
		OnTrackHomePageobj = new OnTrackHomePage();
	}
	
	@Test(description = "This method verifies whether Overview and Challenge tabs are displayed.",priority=1)
	public void verifyOnTrackFindingBalTabs() throws TimeoutException {
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.setDelorian(onTrackData.get(UPCOMING_DATE));
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.slectYourToolsLink();
		OnTrackHomePageobj.clickOnOnTrackLink();
		SeleniumUtil.sleep(2);
		findingBal.assertTrue(OnTrackHomePageobj.verifyChallengeTab());
		SeleniumUtil.sleep(1);
		findingBal.assertTrue(OnTrackHomePageobj.verifyOverviewTab());	
	}

	@Test(description = "This method verifies the content of Overview tab is displayed.",priority=2)
	public void verifyOverviewFindingbalTabContent() throws TimeoutException{
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.clickOverviewTab();
		findingBal.assertTrue(OnTrackHomePageobj.verifyOverviewTab());
		SeleniumUtil.sleep(2);
		findingBal.assertTrue(OnTrackHomePageobj.verifyOverviewHeaderInfo(onTrackData.get(OVERVIEW_HEADERName)));
		findingBal.assertTrue(OnTrackHomePageobj.verifyOverviewHeaderText(onTrackData.get(OVERVIEW_HEADER)));
		findingBal.assertTrue(OnTrackHomePageobj.verifyOverviewBodyHeader(onTrackData.get(OVERVIEW_BODYHeader)));
		findingBal.assertTrue(OnTrackHomePageobj.verifyOverviewBodyText(onTrackData.get(OVERVIEW_BODY)));
		findingBal.assertTrue(OnTrackHomePageobj.verifyChallengesBodyHeader(onTrackData.get(CHALLENGES_BODYHeader)));
		findingBal.assertTrue(OnTrackHomePageobj.verifyChallengesBodyText(onTrackData.get(CHALLENGES_BODY)));	
	}
	@Test(description = "This method verifies whether Banner Text is accurate with not registered challenge.",priority=3)
	public void verifyFindingBalNotRegisteredChallenge() throws TimeoutException{
		OnTrackHomePageobj.setDelorian(onTrackData.get(NOT_REGISTERED_DATE));
		SeleniumUtil.sleep(2);
		findingBal.assertTrue(OnTrackHomePageobj.verifyChallengeTab());
		SeleniumUtil.sleep(2);
		findingBal.assertTrue(OnTrackHomePageobj.verifyChallengeInfoText(onTrackData.get(CHALLENGE_INFO_TXT)));
		findingBal.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(NOT_REGISTERED_BANNER_TXT)));
		SeleniumUtil.sleep(2);
		findingBal.assertTrue(OnTrackHomePageobj.verifySignUpButton());
	}
	@Test(description = "This method verifies whether Banner Text is accurate with an challenge which has expired without user sign up.",priority=4)
	public void verifyFindingBalNotRegisteredPostChallenge() throws TimeoutException{
		
		OnTrackHomePageobj.setDelorian(onTrackData.get(POST_CHALLENGE_SURVEY_DATE));
		SeleniumUtil.sleep(2);
		findingBal.assertTrue(OnTrackHomePageobj.verifyChallengeTab());
		SeleniumUtil.sleep(2);
		findingBal.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(BACKLOG_BANNER_TXT)));
		
	}
	@Test(description = "This method verifies whether Banner Text is accurate with an challenge that is over(backlog page).",priority=5)
	public void verifyFindingBalBackLogChallenge() throws TimeoutException{
		
		OnTrackHomePageobj.setDelorian(onTrackData.get(BACKLOG_ACTIVE_DATE));
		SeleniumUtil.sleep(2);
		findingBal.assertTrue(OnTrackHomePageobj.verifyChallengeTab());
		SeleniumUtil.sleep(2);
		findingBal.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(BACKLOG_BANNER_TXT)));	
	}
	@Test(description = "This method verifies whether Banner Text is accurate with an not registered challenge.", priority=6)
	public void verifyRegisteredNoAppsChallengeForFindingBal() throws TimeoutException{
		OnTrackHomePageobj.setDelorian(onTrackData.get(NOT_REGISTERED_DATE));
		SeleniumUtil.sleep(2);
		findingBal.assertTrue(OnTrackHomePageobj.verifyChallengeTab());
		 SeleniumUtil.sleep(1);
		 OnTrackHomePageobj.clickSignUpButton();
		findingBal.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(REGISTERED_NO_APP_BANNER_TXT1)));
	}	
	@Test(description = "This method verifies whether Banner Text is accurate with not registered challenge.",priority=7)
	public void verifyRegisteredNoAppsChallengeAddEntry() throws ParseException, TimeoutException, InterruptedException, java.text.ParseException{
		OnTrackHomePageobj.setDelorian(onTrackData.get(CHALLENGE_START_DATE));
		findingBal.assertTrue(OnTrackHomePageobj.verifyChallengeTab());
		OnTrackHomePageobj.addSleepEntry();		
	}
	@AfterClass
	public void tearDown() {
		OnTrackHomePageobj=null;
		logger.info("Exiting the tearDown() method for Weight Management Test \n\n");
	}
	
	
}
