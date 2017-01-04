package com.lh.test.onTrackSuite;

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
//import com.lh.pages.unauthenticated.LoginPage;
//import com.lh.pages.unauthenticated.LoginPage;

/**
 * <h2>Verify the Stress Less chef values from the Portal!</h2> 
 * <p>
 * 
 * @author akash.vansil
 * @version 1.0
 * @since 2016-22-03
 */

@Listeners(com.lh.helper.LHTestListener.class)
public class StressLessChefTest  extends AuthBaseTestClass{

	private static Logger logger = LogManager.getLogger(StressLessChefTest.class);
	/**
	 * Name of the current running test method
	 */
	String testName;
	
    private Assertion stressLess  = new Assertion();
    private OnTrackHomePage  OnTrackHomePageobj;
    private Map<String, String> onTrackData;
    private static final String UPCOMING_DATE = "ThemeStartDate1";
    private static final String UPCOMING_BANNER_TXT = "UpcomingChallengeCaption2";
    private static final String NOT_REGISTERED_DATE = "campaignNotRegistered1";
    private static final String NOT_REGISTERED_BANNER_TXT1 = "NotRegisteredCaption3";
    private static final String Registered_Banner_Txt        ="RegisteredNoAppsCaption3";
    private static final String REGISTERED_NO_APP_BANNER_TXT2 = "RegisteredNoAppsCaption3";
    private static final String BACKLOG_ACTIVE_DATE="BacklogActiveChef";
    private static final String BACKLOG_BANNER_TXT="BacklogActiveCaption2";
    private static final String POST_CHALLENGE_SURVEY_DATE ="RegisteredPostChallengeSurveyComplete1";
    private static final String POST_CHALLENGE_SURVEY_TXT="RegisteredPostChallengeSurvey2";
    private static final String CHALLENGES_BODY = "OverviewStressChallenge";	
    private static final String CHALLENGE_START_DATE = "challengeStartDate1";
    private static final String OVERVIEW_HEADER = "OverviewHeader";
    private static final String OVERVIEW_HEADERName = "Heading";
    private static final String VALID_PASSWORD 					= "validPassword";
	private static final String VALID_USERNAME 					= "validUsername";
    private static final String OVERVIEW_BODYHeader  =  "OverviewWhyHeader";
    private static final String OVERVIEW_BODY    =  "OverviewWhyBanner";
    private static final String CHALLENGES_BODYHeader = "OverviewChallengeHeader"; 
	private final String portalURL = "portalURL";
    
    /**
	 * This method runs before the first test from the class runs.
	 * @throws IOException 
	 */
	@BeforeClass
 	public void initClass() throws IOException {
		
		logger.info("Inside the initClass() method for On OnTrack_stressLessanceTest class...");
		onTrackData = readexcelsheet("OnTracker");
		SeleniumUtil.sleep(1);
		IOnTrackDao daoObj = new OnTrackDaoImpl();
		daoObj.resetonTrackForUser(onTrackData.get(VALID_USERNAME));
		doLogin(onTrackData.get(VALID_USERNAME), onTrackData.get(VALID_PASSWORD),"Portal","Portal",onTrackData.get(portalURL));
		OnTrackHomePageobj = new OnTrackHomePage();	
	}
	
	
	@Test(description = "This method verifies whether Overview and Challenge tabs are displayed.", priority=1,groups={"regression"})
	public void verifyOnTrackTabs() throws TimeoutException {
		SeleniumUtil.sleep(4);
		OnTrackHomePageobj.setDelorian(onTrackData.get(UPCOMING_DATE));
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.slectYourToolsLink();
		OnTrackHomePageobj.clickOnOnTrackLink();
		SeleniumUtil.sleep(3);
		stressLess.assertTrue(OnTrackHomePageobj.verifyChallengeTab());
		SeleniumUtil.sleep(1);
		stressLess.assertTrue(OnTrackHomePageobj.verifyOverviewTab());

	}

	/**
	 * This method verifies the content of Overview tab is displayed.
	 * @throws TimeoutException 
	 */ 
	
	@Test(description = "This method verifies the content of Overview tab is displayed.",priority=2,groups={"regression"})
	public void verifyOverviewTabContent() throws TimeoutException{
		
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.clickOverviewTab();
		stressLess.assertTrue(OnTrackHomePageobj.verifyOverviewTab());
		SeleniumUtil.sleep(2);
		stressLess.assertTrue(OnTrackHomePageobj.verifyOverviewHeaderInfo(onTrackData.get(OVERVIEW_HEADERName)));
		stressLess.assertTrue(OnTrackHomePageobj.verifyOverviewHeaderText(onTrackData.get(OVERVIEW_HEADER)));
		stressLess.assertTrue(OnTrackHomePageobj.verifyOverviewBodyHeader(onTrackData.get(OVERVIEW_BODYHeader)));
		stressLess.assertTrue(OnTrackHomePageobj.verifyOverviewBodyText(onTrackData.get(OVERVIEW_BODY)));
		stressLess.assertTrue(OnTrackHomePageobj.verifyChallengesBodyHeader(onTrackData.get(CHALLENGES_BODYHeader)));
		stressLess.assertTrue(OnTrackHomePageobj.verifyChallengesBodyText(onTrackData.get(CHALLENGES_BODY)));

	}
	
	/**
	 * This method verifies whether Overview and Challenge tabs are displayed with an upcoming campaign.
	 * @throws TimeoutException 
	 * 
	 */

	@Test(description = "This method verifies whether Overview and Challenge tabs are displayed with an upcoming campaign.",priority=3,groups={"regression"})
	public void verifyUpcomingChallenge() throws TimeoutException{
	
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.clickOnChallengeTab();
		SeleniumUtil.sleep(2);
		stressLess.assertTrue(OnTrackHomePageobj.verifyChallengeTab());
		SeleniumUtil.sleep(2);
		stressLess.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(UPCOMING_BANNER_TXT).trim()));
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.clickOverviewTab();			
	}
	
	/**
	 * This method verifies whether Banner Text is accurate with an challenge that is over(backlog page).
	 * @throws TimeoutException 
	 */
	@Test(description = "This method verifies whether Banner Text is accurate with an challenge that is over(backlog page).",priority=4,groups={"regression"})
	public void verifyBackLogChallenge() throws TimeoutException{
		
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.setDelorian(onTrackData.get(BACKLOG_ACTIVE_DATE));
		SeleniumUtil.sleep(2);
		stressLess.assertTrue(OnTrackHomePageobj.verifyChallengeTab());
		stressLess.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(BACKLOG_BANNER_TXT)));		
	}
	
	
	/**
	 * This method verifies whether Banner Text is accurate with an challenge which has expired without user sign up.
	 * @throws TimeoutException 
	 */
	@Test(description = "This method verifies whether Banner Text is accurate with an challenge which has expired without user sign up.",priority=5,groups={"regression"})
	public void verifyNotRegisteredPostChallenge() throws TimeoutException{
		
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.setDelorian(onTrackData.get(POST_CHALLENGE_SURVEY_DATE));
		SeleniumUtil.sleep(2);
		stressLess.assertTrue(OnTrackHomePageobj.verifyChallengeTab());
		stressLess.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(BACKLOG_BANNER_TXT)));	
	}
	
	
	/**
	 * This method verifies whether Banner Text is accurate with an not registered challenge.
	 * @throws TimeoutException 
	 */

	@Test(description = "This method verifies whether Banner Text is accurate with an not registered challenge.",priority=6,groups={"regression"})
	public void verifyNotRegisteredChallenge() throws TimeoutException{
		
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.setDelorian(onTrackData.get(NOT_REGISTERED_DATE));
		SeleniumUtil.sleep(3);
		stressLess.assertTrue(OnTrackHomePageobj.verifyChallengeTab());
		stressLess.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(NOT_REGISTERED_BANNER_TXT1)));
		stressLess.assertTrue(OnTrackHomePageobj.verifySignUpButton());
	}

	
	/**
	 * This method verifies whether Banner Text is accurate with an not registered challenge.
	 * @throws TimeoutException 
	 */
	@Test(description = "This method verifies whether Banner Text is accurate with an not registered challenge.", priority=7,groups={"regression"})
	public void verifyRegisteredNoAppsChallenge() throws TimeoutException{
		OnTrackHomePageobj.setDelorian(onTrackData.get(NOT_REGISTERED_DATE));
		SeleniumUtil.sleep(2);
		stressLess.assertTrue(OnTrackHomePageobj.verifyChallengeTab());
		OnTrackHomePageobj.clickSignUpButton();
		SeleniumUtil.sleep(5);
		stressLess.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(Registered_Banner_Txt)));	
	}
	
	/**
	 * This method verifies whether Banner Text is accurate with an not registered challenge.
	 * @throws ParseException 
	 * @throws TimeoutException 
	 */
	@Test(description = "This method verifies whether Banner Text is accurate with an not registered challenge.",priority=8,groups={"regression"})
	public void verifyRegisteredNoAppsChallengeAddEntry() throws ParseException, TimeoutException{
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.setDelorian(onTrackData.get(CHALLENGE_START_DATE));
		SeleniumUtil.sleep(2);
		stressLess.assertTrue(OnTrackHomePageobj.verifyChallengeTab());
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.addFoodEntry();
	}
	
	/**
	 * This method verifies whether Banner Text is accurate with an challenge which has expired where user has signed up.
	 * @throws TimeoutException 
	 */
	@Test(description = "This method verifies whether Banner Text is accurate with an challenge which has expired where user has signed up.",priority=9,groups={"regression"})
	public void verifyRegisteredPostChallenge() throws TimeoutException{
		SeleniumUtil.sleep(1);
		OnTrackHomePageobj.setDelorian(onTrackData.get(POST_CHALLENGE_SURVEY_DATE));
		SeleniumUtil.sleep(3);
		stressLess.assertTrue(OnTrackHomePageobj.verifyChallengeTab());
		stressLess.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(POST_CHALLENGE_SURVEY_TXT)));	
	}			
	
	@AfterClass
	public void tearDown(){
		
		OnTrackHomePageobj=null;
	}
}
