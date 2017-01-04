package com.lh.test.onTrackSuite;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import com.lh.dao.IOnTrackDao;
import com.lh.dao.OnTrackDaoImpl;
import com.lh.pages.authenticated.ontrack.OnTrackHomePage;
import com.lh.test.base.AuthBaseTestClass;
import com.lh.utils.SeleniumUtil;
//import com.lh.pages.unauthenticated.LoginPage;

/**
 * <h2>Verify the portal values for the Exhale test!</h2> 
 * <p>
 * 
 * @author akash.vansil
 * @version 1.0
 * @since 2016-10-03
 */

public class ExhaleTest extends AuthBaseTestClass {
	/** Following variables are stored in OnTrack_Data Sheet */
	private static final String VALID_PASSWORD 					= "Password";
	private static final String VALID_USERNAME 					= "Username";
	private final String portalURL								= "portalURL";
	private static final String UPCOMING_DATE                   = "ThemeStartDate2";
	private static final String NOT_REGISTERED_DATE             = "campaignNotRegistered2";
	private static final String BACKLOG_ACTIVE_DATE ="ExhaleBacklogActive";
	private static final String POST_CHALLENGE_SURVEY_DATE      = "RegisteredPostChallengeSurveyComplete2";
	
	/** Following variables are stored in OnTrack_Content Sheet */
	private static final String UPCOMING_BANNER_TXT             = "UpcomingExaleChallengeCaption";
    private static final String NOT_REGISTERED_BANNER_TXT1      = "NotRegisteredExhaleCaption1";
    private static final String REGISTERED_NO_APP_BANNER_TXT    = "RegisteredExaleNoApps";
    private static final String BACKLOG_BANNER_TXT              = "BacklogActiveCaption1";
    private static final String POST_CHALLENGE_SURVEY_TXT       = "RegisteredPostChallengeSurvey3";
    private static final String OVERVIEW_HEADERName             = "Heading";
    private static final String OVERVIEW_HEADER                 = "OverviewHeader";
    private static final String OVERVIEW_BODYHeader             =  "OverviewWhyHeader";
    private static final String OVERVIEW_BODY                   = "stressManagementBanner";	
    private static final String CHALLENGES_BODYHeader           = "OverviewChallengeHeader"; 
    private static final String CHALLENGES_BODY                 = "OverviewChallengeExhale";
	/** Logger for the Login class */
	private static Logger logger = LogManager.getLogger(ExhaleTest.class);

	 String testName;
	/** AboutYou page reference for the tests to run */
	/** Contains the coaching data */
	private Map<String, String> onTrackData;
	private OnTrackHomePage  OnTrackHomePageobj;
	private Assertion exhaleAssert = new Assertion();
	   

	/**
	 * This method runs before the first test from the class runs.
	 * @throws IOException 
	 */
	@BeforeClass
	public void initClass() throws IOException {

		logger.info("Inside the initClass() method for On OnTrack_exaleTest class...");
		onTrackData = readexcelsheet("OnTracker");
		logger.info("Exiting the initClass() method for OnTrack_exaleTest class \n\n");
		IOnTrackDao daoObj = new OnTrackDaoImpl();
		daoObj.resetonTrackForUser(onTrackData.get(VALID_USERNAME));
		doLogin(onTrackData.get(VALID_USERNAME), onTrackData.get(VALID_PASSWORD),"Portal","Portal",onTrackData.get(portalURL));
		OnTrackHomePageobj = new OnTrackHomePage();
		
	}

	/**
	 * This method runs before each test from the class runs
	 */
	@BeforeMethod
	public void init(Method method) throws Exception {
		logger.info("\n Inside the init() method for OnTrack_OnTrack_ExhaleTest class...");
	}

	/**
	 * This method verifies whether Overview and Challenge tabs are displayed.
	 * @throws TimeoutException 
	 */
	 

	@Test(description = "This method verifies whether Overview and Challenge tabs are displayed.",priority=1,groups={"regression"})
	public void verifyOnTrackExhaleTabs() throws TimeoutException{
		SeleniumUtil.sleep(3);
		OnTrackHomePageobj.setDelorian(onTrackData.get(UPCOMING_DATE));
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.slectYourToolsLink();
		OnTrackHomePageobj.clickOnOnTrackLink();
		SeleniumUtil.sleep(2);
		exhaleAssert.assertTrue(OnTrackHomePageobj.verifyChallengeTab());
		SeleniumUtil.sleep(1);
		exhaleAssert.assertTrue(OnTrackHomePageobj.verifyOverviewTab());
		
	}
	/**
	 * This method verifies the content of Overview tab is displayed.
	 * @throws TimeoutException 
	 */ 
	@Test(description = "This method verifies the content of Overview tab is displayed.",priority=2,groups={"regression"})
	public void verifyOverviewExhaleTabContent() throws TimeoutException{
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.clickOverviewTab();
		SeleniumUtil.sleep(1);
		exhaleAssert.assertTrue(OnTrackHomePageobj.verifyOverviewHeaderInfo(onTrackData.get(OVERVIEW_HEADERName)));
		exhaleAssert.assertTrue(OnTrackHomePageobj.verifyOverviewHeaderText(onTrackData.get(OVERVIEW_HEADER)));
		exhaleAssert.assertTrue(OnTrackHomePageobj.verifyOverviewBodyHeader(onTrackData.get(OVERVIEW_BODYHeader)));
		exhaleAssert.assertTrue(OnTrackHomePageobj.verifyOverviewBodyText(onTrackData.get(OVERVIEW_BODY)));
		exhaleAssert.assertTrue(OnTrackHomePageobj.verifyChallengesBodyHeader(onTrackData.get(CHALLENGES_BODYHeader)));
		exhaleAssert.assertTrue(OnTrackHomePageobj.verifyChallengesBodyText(onTrackData.get(CHALLENGES_BODY)));
	}
	/**
	 * This method verifies whether Overview and Challenge tabs are displayed with an upcoming campaign.
	 * 
	 */

	@Test(description = "This method verifies whether Overview and Challenge tabs are displayed with an upcoming campaign.",priority=3,groups={"regression"})
	public void verifyExhaleUpcomingChallenge(){
		
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.clickOnChallengeTab();
		SeleniumUtil.sleep(2);
		exhaleAssert.assertTrue(OnTrackHomePageobj.verifyChallengeTab());
		SeleniumUtil.sleep(2);
		exhaleAssert.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(UPCOMING_BANNER_TXT)));
	}
	/**
	 * This method verifies whether Banner Text is accurate with an challenge that is over(backlog page).
	 * @throws TimeoutException 
	 */
	@Test(description = "This method verifies whether Banner Text is accurate with an challenge that is over(backlog page).",priority=4, groups={"regression"})
	public void verifyExhaleBackLogChallenge() throws TimeoutException{
		
		SeleniumUtil.sleep(3);
		OnTrackHomePageobj.setDelorian(onTrackData.get(BACKLOG_ACTIVE_DATE));
		SeleniumUtil.sleep(4);
		exhaleAssert.assertTrue(OnTrackHomePageobj.verifyChallengeTab());
		exhaleAssert.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(BACKLOG_BANNER_TXT)));	
		
	}

	/**
	 * This method verifies whether Banner Text is accurate with an challenge which has expired without user sign up.
	 */

	@Test(description = "This method verifies whether Banner Text is accurate with an not registered challenge.",priority=5, groups={"regression"})
	public void verifyExhaleNotRegisteredPostChallenge() throws TimeoutException{

		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.setDelorian(onTrackData.get(POST_CHALLENGE_SURVEY_DATE));
		SeleniumUtil.sleep(2);
		exhaleAssert.assertTrue(OnTrackHomePageobj.verifyChallengeTab());
		exhaleAssert.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(BACKLOG_BANNER_TXT)));
		
	}
	/**
	 * This method verifies whether Banner Text is accurate with an not registered challenge.
	 * @throws TimeoutException 
	 */
	@Test(description = "This method verifies whether Banner Text is accurate with an challenge which has expired without user sign up.",priority=6,groups={"regression"})
	public void verifyExhaleNotRegisteredChallenge() throws TimeoutException{

		SeleniumUtil.sleep(3);
		OnTrackHomePageobj.setDelorian(onTrackData.get(NOT_REGISTERED_DATE));
		SeleniumUtil.sleep(4);
		exhaleAssert.assertTrue(OnTrackHomePageobj.verifyChallengeTab());
		exhaleAssert.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(NOT_REGISTERED_BANNER_TXT1)));
		exhaleAssert.assertTrue(OnTrackHomePageobj.verifySignUpButton());
			
		}
	
	/**
	 * This method verifies whether Banner Text is accurate with an not registered challenge.
	 * @throws TimeoutException 
	 * @throws InterruptedException 
	 * @throws ParseException 
	 */
	@Test(description = "This method verifies whether Banner Text is accurate with an not registered challenge.", priority=7,groups={"regression"})
	public void verifyExhaleRegisteredNoAppsChallenge() throws TimeoutException, ParseException, InterruptedException{
		
		OnTrackHomePageobj.setDelorian(onTrackData.get(NOT_REGISTERED_DATE));
		SeleniumUtil.sleep(2);
		exhaleAssert.assertTrue(OnTrackHomePageobj.verifyChallengeTab());
		OnTrackHomePageobj.clickSignUpButton();
		exhaleAssert.assertTrue(OnTrackHomePageobj.addExaleEntry());
		
	}
	
	/**
	 * This method verifies whether Banner Text is accurate with an challenge which has expired where user has signed up.
	 * @throws TimeoutException 
	 */
	@Test(description = "This method verifies whether Banner Text is accurate with an challenge which has expired where user has signed up.",priority=8, groups={"regression"})
	public void verifyExhaleRegisteredPostChallenge() throws TimeoutException{

		SeleniumUtil.sleep(1);
		OnTrackHomePageobj.setDelorian(onTrackData.get(POST_CHALLENGE_SURVEY_DATE));
		SeleniumUtil.sleep(2);
		exhaleAssert.assertTrue(OnTrackHomePageobj.verifyChallengeTab());
		exhaleAssert.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(POST_CHALLENGE_SURVEY_TXT)));
		
	}
//	/**
//	 * This method verifies whether Banner Text is accurate with an challenge that is over(backlog page).
//	 * @throws TimeoutException 
//	 */
	@Test(description = "This method verifies whether Banner Text is accurate with an challenge that is over(backlog page).",priority=9,groups={"regression"})
	public void verifyExhaleRegisteredBackLogChallenge() throws TimeoutException{
		
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.setDelorian(onTrackData.get(BACKLOG_ACTIVE_DATE));
		SeleniumUtil.sleep(2);
		exhaleAssert.assertTrue(OnTrackHomePageobj.verifyChallengeTab());
		exhaleAssert.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(POST_CHALLENGE_SURVEY_TXT)));
		
	}
	/**
	 * This method verifies whether Banner Text is accurate with an challenge that requires to fill the survey.
	 * @throws TimeoutException 
	 */
	
	@Test(description = "This method verifies whether Banner Text is accurate with an challenge that requires to fill the survey.",priority=10,groups={"regression"})
	public void verifyExhalePostChallengeSurvey() throws TimeoutException{
		
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.setDelorian(onTrackData.get(POST_CHALLENGE_SURVEY_DATE));
		SeleniumUtil.sleep(2);
		OnTrackHomePageobj.slectYourToolsLink();
		OnTrackHomePageobj.clickOnOnTrackLink();
		SeleniumUtil.sleep(2);
		exhaleAssert.assertTrue(OnTrackHomePageobj.verifyChallengeTab());
		exhaleAssert.assertTrue(OnTrackHomePageobj.verifyBannerText(onTrackData.get(POST_CHALLENGE_SURVEY_TXT)));
		OnTrackHomePageobj.fillSurvey_Chef();	
	}
	
 
	
	/**
	 * This method runs after each test from the class runs 
	 */
	@AfterClass
	public void tearDown() {

		onTrackData=null;
		OnTrackHomePageobj = null;
	   logger.info("Exiting the tearDown() method for Exhale Test \n\n");
	}

	
}

