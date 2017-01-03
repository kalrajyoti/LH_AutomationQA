package com.lh.sanitySuite;

import java.util.Map;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import com.lh.pages.authenticated.ActionCenterPage;
import com.lh.pages.authenticated.ContactPage;
import com.lh.pages.authenticated.HelpPage;
import com.lh.pages.authenticated.MainPage;
import com.lh.pages.authenticated.PrivacyPolicyPage;
import com.lh.pages.authenticated.TermsOfUsePage;
import com.lh.test.base.AuthBaseTestClass;
import com.lh.utils.SeleniumUtil;

/**
 * <h2>Define test for the Index page here!</h2> 
 * <p>
 * 
 * @author pranali.jain, arpit.tandon
 * @version 1.0
 * @since 2015-06-26
 */
@Listeners(com.lh.helper.LHTestListener.class)
public class HomePageTest extends AuthBaseTestClass {

	private static final String HELP_URL						= "Help";
	private static final String TERMS_OF_USE					= "Terms";
	private static final String PRIVACY_POLICY					= "PrivacyPolicy";
	private static final String CONTACT_US						= "ContactUs";
	private static final String VALID_PASSWORD 					= "validPassword";
	private static final String VALID_USERNAME 					= "validUsername";
	private static final String INCENTIVE_TRACKER				= "Incentive Tracker";
	private static final String HEALTH_CHALLENGES	 			= "HEALTH_CHALLENGES";
	private static final String HEALTH_ARTICLES 				= "Health Articles";
	private static final String WELLNESS_RESOURCES 				= "Wellness Resources";
	private static final String WELLNESS_WEBINARS 				= "Wellness Webinars";
	private static final String HEALTH_RISK						= "healthriskText";
	public static final String  HEALTH_MANAGER					="healthManagerText";
	private static final String HEALTH_COACHING					="healthCoachingText";
	private static final String BIOMETRIC_SCREENING				="biometricText";
	private static final String LIFESTYLE_MANAGER				="lifeStyleManagerText";
	private static final String GO_MOBILE						="goMobileText";
	private static final String APP_MANAGER						="appManagerText";
	private static final String COMMUNITY 				        ="communityText";
	private static final String WELLNESS_TEXT					="wellnessText";
	/** LoginPage reference used to login for logging in for tests */
	private MainPage mainPageObj;
	/** HelpPage reference used for Help link functionality */
	private HelpPage helpPageObj;
	/** TermsOfUse page reference used for verifying static elements on it */
	private TermsOfUsePage termsOfUsePageObj;
	/** PrivacyPolicy page reference for verifying static elements on it */
	private PrivacyPolicyPage privacyPolicyPageObj;
	/** Contact Us Page reference for verifying static elements on it */
	private ContactPage contactPageObj;
	/** Reads data from the test data file */
	/** Contains the login data */
	private Map<String, String> loginData;
	/** LoginPage reference used to login for logging in for tests */
	
	/** Assertion to verify different elements of the page. */
	private Assertion home_assert 									= new Assertion();
	private ActionCenterPage actionCenterPageObject;
	/** Assertion to verify different elements of the page. */
	
	@BeforeClass
	public void initClass() throws Exception {

		Reporter.log("Inside the initClass() method for HomePage class...");
		loginData = readexcelsheet("Login");
		Reporter.log("Exiting the initClass() method for HomePage class \n\n");
		
		/**********/
		Reporter.log("\n Inside the init() method for HomePage class...");
		Reporter.log("Calling the loginPageObj.loginAs(username, password) method");
		SeleniumUtil.sleep(3);
		doLogin(loginData.get(VALID_USERNAME), loginData.get(VALID_PASSWORD),"Portal","Portal");
		mainPageObj = new MainPage();
		helpPageObj = new HelpPage();
		termsOfUsePageObj = new TermsOfUsePage();
		privacyPolicyPageObj = new PrivacyPolicyPage();
		contactPageObj = new ContactPage();
		actionCenterPageObject = new ActionCenterPage();
		Reporter.log("Exiting the init() method for HomePage class \n");
	}

	/** This method tests that both the logos are displayed on Index Page" */
	@Test(description = "Verify Client logos are displayed", groups = {"sanity"})
	public void verifyClientLogos(){
		home_assert.assertTrue(helpPageObj.verifyClientLogo());
	}

	/** This method tests the functionality of Help link */
	@Test(description = "Verify Help link is displayed and functional", groups = {"sanity"})
	public void verifyHelpLink() {
		home_assert.assertTrue(mainPageObj.verifyHelpLinkIsDisplayed());
		mainPageObj.clickHelpLink();
		home_assert.assertTrue(helpPageObj.verifyHelpPage(HELP_URL));
	}

	/** This method tests the presence of Preference link */
	@Test(description = "Verify Preferences link is displayed", groups = {"sanity"})
	public void clickOnPreferencesLink(){
		home_assert.assertTrue(mainPageObj.verifyPreferencesLink());
		mainPageObj.clickPreferencesLink();
	}

	/** This method tests the functionality of TermsOfUse link */
	@Test(description = "Verify TermsOfUse link is displayed and functional", groups = {"sanity"})
	public void clickAndVerifyTermsOfUse() {
		home_assert.assertTrue(mainPageObj.verifyTermsOfUseIsDispalyed());
		mainPageObj.clickTermsOfUseLink();
		home_assert.assertTrue(termsOfUsePageObj.verifyTermsOfUsePage(TERMS_OF_USE));
	}

	/** This method tests the functionality of PrivacyPolicy link */
	@Test(description = "Verify PrivacyPolicy link is displayed and functional", groups = {"sanity"})
	public void clickAndVerifyPrivacyPolicy() {
		home_assert.assertTrue(mainPageObj.verifyPrivacyPolicyIsDispalyed());
		mainPageObj.clickPrivacyPolicyLink();
		home_assert.assertTrue(privacyPolicyPageObj.verifyPrivacyPolicyPage(PRIVACY_POLICY));
	}

	/** This method tests the functionality of Contact LiveHealthier link */
	@Test(description = "Verify ContactUs link is displayed and functional", groups = {"sanity"})
	public void clickAndVerifyContactUs() {
		home_assert.assertTrue(mainPageObj.verifyContactLiveHealthierLinkisDisplayed());
		mainPageObj.clickContactLiveHealthier();
		home_assert.assertTrue(contactPageObj.verifyContactUsPage(CONTACT_US));
	}

	/** This method clicks on the Program Overview sub menu of The Program menu item */
	@Test(description = "Verify ContactUs link is displayed and functional", groups = {"sanity"})
	public void clickProgramOverviewSubMenu() {
		mainPageObj.clickProgramOverview();
		home_assert.assertTrue(mainPageObj.verifyClientLogo());
	}

	/** This method clicks on the Program Overview sub menu of The Program menu item */
	//@Test(description = "Verify ContactUs link is displayed and functional", groups = {"sanity"})
	public void clickIncentiveTrackerSubMenu() {
		mainPageObj.clickTheProgramSubMenu(INCENTIVE_TRACKER);
		home_assert.assertTrue(mainPageObj.verifyBannerLabelAndText(INCENTIVE_TRACKER));
		home_assert.assertTrue(mainPageObj.verifyHeader());
		home_assert.assertTrue(mainPageObj.verifyFooter());
	}
	
	/** This method clicks and validates the text on the Health Risk Assessment Page*/
	@Test(description="Verify the content in the Health Risk Assessment Page", groups = {"sanity"})
	public void verifyToolsSubLinks_HealthAsssessment(){
		mainPageObj.clickOnToolsSubLinks(loginData.get(HEALTH_RISK));
		home_assert.assertTrue(mainPageObj.verifyToolSubText(loginData.get(HEALTH_RISK)));
		home_assert.assertTrue(mainPageObj.verifyHeader());
		home_assert.assertTrue(mainPageObj.verifyFooter());
	}
	
	/** This method clicks and validates the text on the Health Manager Page*/
	@Test(description="Verify the content in the Health Manager Page", groups = {"sanity"})
	public void verifyToolsSubLinks_HealthManager(){
		mainPageObj.clickOnToolsSubLinks(loginData.get(HEALTH_MANAGER));
		home_assert.assertTrue(mainPageObj.verifyToolSubText(loginData.get(HEALTH_MANAGER)));
		home_assert.assertTrue(mainPageObj.verifyHeader());
		home_assert.assertTrue(mainPageObj.verifyFooter());
		
	}
	
	/** This method clicks and validates the text on the Health Coaching Page*/
	@Test(description="Verify the content in the Health Coaching Page", groups = {"sanity"})
	public void verifyToolsSubLinks_HealthCoaching(){
		mainPageObj.clickOnToolsSubLinks(loginData.get(HEALTH_COACHING));
		SeleniumUtil.sleep(5);
		home_assert.assertTrue(mainPageObj.verifyToolSubText(loginData.get(HEALTH_COACHING)));
		home_assert.assertTrue(mainPageObj.verifyHeader());
		home_assert.assertTrue(mainPageObj.verifyFooter());
	}
//	
//	/** This method clicks and validates the text on the Biometric Screening Page*/
//	@Test(description="Verify the content in the Biometric Page", groups = {"sanity"})
//	public void verifyToolsSubLinks_BiometricScreening(){
//		mainPageObj.clickOnToolsSubLinks(loginData.get(BIOMETRIC_SCREENING));
//		home_assert.assertTrue(mainPageObj.verifyToolSubText(loginData.get(BIOMETRIC_SCREENING)));
//		home_assert.assertTrue(mainPageObj.verifyHeader());
//		home_assert.assertTrue(mainPageObj.verifyFooter());
//	}
	
	/** This method clicks and validates the text on the Lifestyle Manager Page*/
	@Test(description="Verify the content in the Lifestyle Manager Page", groups = {"sanity"})
	public void verifyToolsSubLinks_LifeStyleManager(){
		mainPageObj.clickOnToolsSubLinks(loginData.get(LIFESTYLE_MANAGER));
		home_assert.assertTrue(mainPageObj.verifyToolSubText(loginData.get(LIFESTYLE_MANAGER)));
		home_assert.assertTrue(mainPageObj.verifyHeader());
		home_assert.assertTrue(mainPageObj.verifyFooter());
	}
	
	/** This method clicks and validates the text on the Go Mobile Page*/
	@Test(description="Verify the content in the GO Mobile Page", groups = {"sanity"})
	public void verifyToolsSubLinks_goMobile(){
		mainPageObj.clickOnToolsSubLinks(loginData.get(GO_MOBILE));
		home_assert.assertTrue(mainPageObj.verifyToolSubText(loginData.get(GO_MOBILE)));
		home_assert.assertTrue(mainPageObj.verifyHeader());
		home_assert.assertTrue(mainPageObj.verifyFooter());
	}
	
	/** This method clicks and validates the text on the App Manager Page*/
	@Test(description="Verify the content in the App Manager Page", groups = {"sanity"})
	public void verifyToolsSubLinks_appManager(){
		mainPageObj.clickOnToolsSubLinks(loginData.get(APP_MANAGER));
		home_assert.assertTrue(mainPageObj.verifyToolSubText(loginData.get(APP_MANAGER)));
		home_assert.assertTrue(mainPageObj.verifyHeader());
		home_assert.assertTrue(mainPageObj.verifyFooter());
	}

	/** This method clicks on the Health Challenges sub menu of The Program menu item */
	@Test(description = "Verify Health Challenges sub menu", groups = {"sanity"})
	public void clickHealthChallengesSubMenu() {
		mainPageObj.clickTheProgramSubMenu(loginData.get(HEALTH_CHALLENGES));
		home_assert.assertTrue(mainPageObj.verifyBannerLabelAndText("Challenges"));
		home_assert.assertTrue(mainPageObj.verifyHeader());
		home_assert.assertTrue(mainPageObj.verifyFooter());
	}

	/** This method clicks on the Health Articles sub menu of Resources menu item */
	@Test(description = "Verify Health Articles sub menu", groups = {"sanity"})
	public void clickHealthArticlesSubMenu() {
		mainPageObj.clickHealthArticles();
		home_assert.assertTrue(mainPageObj.verifyBannerLabelAndText(HEALTH_ARTICLES));
		home_assert.assertTrue(mainPageObj.verifyHeader());
		home_assert.assertTrue(mainPageObj.verifyFooter());
	}
	
	/** This method clicks on the Wellness Resources sub menu of Resources menu item */
	@Test(description = "Verify Wellness Resources sub menu", groups = {"sanity"})
	public void clickWellnessResourcesSubMenu() {
		mainPageObj.clickWellnessResources();
		home_assert.assertTrue(mainPageObj.verifyBannerLabelAndText(WELLNESS_RESOURCES));
		home_assert.assertTrue(mainPageObj.verifyHeader());
		home_assert.assertTrue(mainPageObj.verifyFooter());
	}
	
	/** This method clicks on the Wellness Webinars sub menu of Resources menu item*//*
	@Test(description = "Verify Wellness Webinars sub menu", groups = {"sanity"})
	public void clickWellnessWebinars() {
		mainPageObj.clickWellnessWebinars();
		home_assert.assertTrue(mainPageObj.verifyBannerLabelAndText(WELLNESS_WEBINARS));
		home_assert.assertTrue(mainPageObj.verifyHeader());
		home_assert.assertTrue(mainPageObj.verifyFooter());
	}*/
	
	@Test(description="Verify that all the action center links are displayed inside the Action Center", groups = {"sanity"} )
	public void verifyActionCenterItems(){
		home_assert.assertTrue(actionCenterPageObject.verifyhealthRiskText(loginData.get(HEALTH_RISK)));
        //home_assert.assertTrue(actionCenterPageObject.verifybiometricText(indexPageData.get(BIOMETRIC_SCREENING)));
		home_assert.assertTrue(actionCenterPageObject.verifyhealthManagerText(loginData.get(HEALTH_MANAGER)));
		home_assert.assertTrue(actionCenterPageObject.verifyCommunityText(loginData.get(COMMUNITY)));
		//home_assert.assertTrue(actionCenterPageObject.verifyWellnessText(indexPageData.get(WELLNESS_TEXT)));
		home_assert.assertTrue(actionCenterPageObject.verifyHealthChallenges(loginData.get(HEALTH_CHALLENGES)));
		//home_assert.assertTrue(actionCenterPageObject.verifyHealthCoachingText(loginData.get(HEALTH_COACHING)));
		home_assert.assertTrue(actionCenterPageObject.verifyLifestyleManagerText(loginData.get(LIFESTYLE_MANAGER)));
		
	}
	
	/** This method runs after all test from the class have run */
	@AfterClass
	public void classTearDown() {
		Reporter.log("Inside the classTearDown() method for HomePage class...");
		loginData=null;
		mainPageObj = null;
		Reporter.log("Exiting the classTearDown() method for HomePage class \n\n");
	}
}
