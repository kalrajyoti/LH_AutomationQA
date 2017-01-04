package com.lh.test;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import com.lh.dao.DaoLayer;
import com.lh.dao.DaoLayerImpl;
import com.lh.dao.HealthCoachingDaoImpl;
import com.lh.dao.IHealthCoachingDao;
import com.lh.helper.DriverFactory;
import com.lh.pages.admin.AdminLoginPage;
import com.lh.pages.coaching.HealthCoachingPage;
import com.lh.pages.unauthenticated.LogOutPage;
import com.lh.pages.unauthenticated.LoginPage;
import com.lh.test.base.AuthBaseTestClass;
import com.lh.testConfig.TestProperty;

@Listeners(com.lh.helper.LHTestListener.class)
public class HealthCoachingTest extends AuthBaseTestClass {

	private Logger logger = LogManager.getLogger(HealthCoachingTest.class);
	private Map<String, String> healthCoachingData;
	private  final String password = "password";
	private  final String username = "username";
	private  final String header = "Header";
	private  final String overviewContent = "overviewContent";
	private HealthCoachingPage healthCoachingPageObj;
	private  final String phonetext = "PhoneText";
	private Assertion HC_Assert = new Assertion();
	private AdminLoginPage adminPageObj;
	private Map<String,String> adminData;
	private final String COACH_USERNAME					= "coachUsername";
	private final String COACH_PASSWORD					= "coachPassword";	
	private final String COACH_URL						= "coachURL";
	private final String DISCUSSION_MSG					= "discussionMessage";
	private LoginPage loginPageObj;
	private final String Message							= "Message";
	private final String MessageAlready					= "MessageAlreadyContacted";
	private final String partialUrl_HealthCoaching										= "MemberServices/Experts/GetHealthCoaching.aspx";
	LogOutPage logoutPageObj;

	@BeforeClass
	public void initClass() {
		logger.info("\n Inside the init() method for HealthCoachingTest class...");
		healthCoachingData = readexcelsheet("Coaching");
		DaoLayer dao = new DaoLayerImpl();
		String userid=dao.getUserId(username);
		IHealthCoachingDao daoObj = new HealthCoachingDaoImpl();
		daoObj.resetHealthCoachingForUser((healthCoachingData.get(userid)));
		adminData 	= readexcelsheet("Admin");
		healthCoachingPageObj = new HealthCoachingPage();
		doLogin(healthCoachingData.get(username),healthCoachingData.get(password), "Portal","Portal");
		healthCoachingPageObj.clickHealthCoaching();
		healthCoachingPageObj.verifyURL(partialUrl_HealthCoaching);
		logger.info("Exiting the initClass() method for HealthCoachingTest class \n\n");
	}
	
	@Test(description = "Verify the Overview tab")
	public void verifyOverviewTabContent(){
		HC_Assert.assertEquals(healthCoachingPageObj.verifyCoachingHeaderText(), healthCoachingData.get(header));
		healthCoachingPageObj.clickOnOverviewTab();
		HC_Assert.assertTrue(healthCoachingPageObj.verifyOverviewHeaderText(healthCoachingData.get(overviewContent)));
	}
	
	@Test(description = "Verify the Online tab", dependsOnMethods = "verifyOverviewTabContent")
	public void verifyOnlineTab(){
		HC_Assert.assertTrue(healthCoachingPageObj.verifyOnlineTab());
		healthCoachingPageObj.clickOnlineTab();
		healthCoachingPageObj.clickContactNowBtn(healthCoachingData.get(DISCUSSION_MSG));
		HC_Assert.assertTrue(healthCoachingPageObj.verifyDiscussionMsg(healthCoachingData, "User"));
	}
	
	@Test(description = "Add and verify the Coach reply", dependsOnMethods = "verifyOnlineTab")
	public void addAndVerifyCoachReply(){
		DriverFactory.openURL(adminData.get(COACH_URL));
		adminPageObj = new AdminLoginPage(TestProperty.COACH_EXPERT_TITLE);
		adminPageObj.loginAs(adminData.get(COACH_USERNAME), adminData.get(COACH_PASSWORD));
		adminPageObj.clickOnCoachMsgViewLink();
		adminPageObj.clickOnPostNewMsgLink();
		adminPageObj.enterMsg();
		adminPageObj.clickLogout();
	}
	
	@Test(description = "Verify coach reply on portal", dependsOnMethods = "addAndVerifyCoachReply")
	public void verifyCoachReply(){
		DriverFactory.openURL(TestProperty.BASEURL);
		loginPageObj = new LoginPage("Portal");
		loginPageObj.loginAs(healthCoachingData.get(username), healthCoachingData.get(password));
		healthCoachingPageObj.clickHealthCoaching();
		healthCoachingPageObj.verifyURL(partialUrl_HealthCoaching);
		healthCoachingPageObj.clickOnlineTab();
		HC_Assert.assertTrue(healthCoachingPageObj.verifyNotificationBadge());
		healthCoachingPageObj.clickLatestCoachReply();
		HC_Assert.assertTrue(healthCoachingPageObj.verifyDiscussionMsg(healthCoachingData, "Coach"));
	}
	
	@Test(description = "Verify the Online Contact Now", dependsOnMethods = "verifyCoachReply")
	public void verifyTextMessage() {
		HC_Assert.assertTrue(healthCoachingPageObj.verifyOnlineTab());
		healthCoachingPageObj.clickOnlineTab();
		HC_Assert.assertTrue(healthCoachingPageObj.verifyOnlineTabContactNow(healthCoachingData.get(Message),
				healthCoachingData.get(MessageAlready)));
	}

	@Test(description = "Verify the phone number text in Phone Tab of Health Coaching", dependsOnMethods = "verifyTextMessage")
	public void verifyPhoneNumberText() {
		HC_Assert.assertTrue(healthCoachingPageObj
				.phoneTabVerification(healthCoachingData.get(phonetext)));
	}
	
	@AfterClass
	public void classTearDown() {
		logoutPageObj = new LogOutPage();
		logoutPageObj.clickLogoutLink("Portal");
		setcurrentContext(null, null, false);
		healthCoachingPageObj = null;
		healthCoachingData = null;
		logoutPageObj = null;
		logger.info("Exiting the classTearDown() method for HealthCoachingTest class \n\n");
	}
}
