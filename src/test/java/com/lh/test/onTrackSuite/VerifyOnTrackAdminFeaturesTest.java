package com.lh.test.onTrackSuite;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import com.lh.pages.admin.AddOnTrackConfigPage;
import com.lh.pages.admin.EditOnTrackConfigPage;
import com.lh.pages.admin.HomePage;
import com.lh.pages.admin.ViewOnTrackConfigPage;
import com.lh.pages.unauthenticated.LogOutPage;
import com.lh.test.base.AuthBaseTestClass;
import com.lh.utils.SeleniumUtil;


@Listeners(com.lh.helper.LHTestListener.class)
public class VerifyOnTrackAdminFeaturesTest extends AuthBaseTestClass{

	private final String VALID_USERNAME 								= "validUsername";
	private final String VALID_PASSWORD 								= "validPassword";
	private final String adminURL										= "adminURL";	
  /** Contains the valid password to login to the client portal */
	
	
	/** Logger for the Login class */
	private static Logger logger = LogManager.getLogger(VerifyOnTrackAdminFeaturesTest.class);
	/**
	 * Name of the current running test method
	 */
	
	private Map<String, String> onTrackData;
	private HomePage homePageobj;
	private Assertion adminOnTrackPageAssert = new Assertion();
	private ViewOnTrackConfigPage ViewOnTrackConfigPageobj;
	private EditOnTrackConfigPage  EditOnTrackConfigPageobj;
	private AddOnTrackConfigPage  AddOnTrackConfigPageobj;
	private LogOutPage logoutObj;

	@BeforeClass
	public void initClass(){
		
		homePageobj = new HomePage();
		ViewOnTrackConfigPageobj = new ViewOnTrackConfigPage();
		EditOnTrackConfigPageobj = new EditOnTrackConfigPage();
		AddOnTrackConfigPageobj = new AddOnTrackConfigPage();
		onTrackData = readexcelsheet("Admin");
		logger.info("Exiting the initClass() method for Admin Login instance class \n\n");
		doLogin(onTrackData.get(VALID_USERNAME), onTrackData.get(VALID_PASSWORD),"Admin","Admin",onTrackData.get(adminURL));
	}
	
	/**
	 * This method runs before each test from the class runs 
	 * @throws InterruptedException 
	 */
	
	/** User login with the valid credentials */
	
	@Test(description = "Verify Product link",priority=1)
	public void verifyProductLink()
	{
		adminOnTrackPageAssert.assertTrue(homePageobj.isLinkDisplayed());	
	}
	
	@Test(description = "User select onTrack link ",priority=2)
	public void navigateToOnTrackConfig()
	{
		homePageobj.clickOnProductLink();
		adminOnTrackPageAssert.assertTrue(homePageobj.verifyOnTrackLink());
		homePageobj.SelectProduct();	
	}
	
	@Test(description = "Verify UI Element Is Present Or Not",priority=3)
	public void VerifyAllElementsForViewResult()
	{
		ViewOnTrackConfigPageobj.clickOnTrackLink();
		adminOnTrackPageAssert.assertTrue(ViewOnTrackConfigPageobj.verifyOnTrackConfigLink());
		adminOnTrackPageAssert.assertTrue(ViewOnTrackConfigPageobj.verifyViewOnTrackConfigLink());
		adminOnTrackPageAssert.assertTrue(ViewOnTrackConfigPageobj.verifyClientDropDown());
		adminOnTrackPageAssert.assertTrue(ViewOnTrackConfigPageobj.verifyTrackDropDown());
		adminOnTrackPageAssert.assertTrue(ViewOnTrackConfigPageobj.verifyDateRangeFrom());
		adminOnTrackPageAssert.assertTrue(ViewOnTrackConfigPageobj.verifyDateRangeTo());
		adminOnTrackPageAssert.assertTrue(ViewOnTrackConfigPageobj.verifySubmitButton());		
	 }
	@Test(description = "Verify All Track Results",priority=4)
	public void searchAllTrackResults()
	{
		SeleniumUtil.sleep(3);
		ViewOnTrackConfigPageobj.selectDateRangeTo();
		SeleniumUtil.sleep(1);
		ViewOnTrackConfigPageobj.selectDateRangeFrom();
		ViewOnTrackConfigPageobj.clickSubmitButton();
		SeleniumUtil.sleep(1);
		adminOnTrackPageAssert.assertTrue(ViewOnTrackConfigPageobj.verifyDisplayResult());	
	}
	/**
	 * This method verifies Track header details
	 */
	@Test(description = "Verify All Track Instance Results Headers", priority=5)
	public void verifyAllSearchResultHeaders()
	{
		
		ViewOnTrackConfigPageobj.verifyClientHeader("Client");
		ViewOnTrackConfigPageobj.verifyTrackHeader("Track");
		ViewOnTrackConfigPageobj.verifyCampaignNameHeader("Campaign Name");
		ViewOnTrackConfigPageobj.verifyCampaignStartDateHeader("Campaign Start Date");
		ViewOnTrackConfigPageobj.verifyStatusHeader("Status");
		ViewOnTrackConfigPageobj.verifyChallengeNameHeader("Challenge 1 Name");
		ViewOnTrackConfigPageobj.verifyChallengeTypeHeader("Challenge 1 Type");
		ViewOnTrackConfigPageobj.verifyThemeStartDateeHeader("Theme 1 Start Date");
		ViewOnTrackConfigPageobj.verifyChallenge2NameHeader("Challenge 2 Name");
		ViewOnTrackConfigPageobj.verifyChallenge2TypeHeader("Challenge 2 Type");
		ViewOnTrackConfigPageobj.verifyTheme2StartDateHeader("Theme 2 Start Date");
		ViewOnTrackConfigPageobj.verifyChallenge3NameHeader("Challenge 3 Name");
		ViewOnTrackConfigPageobj.verifyChallenge3TypeHeader("Challenge 3 Type");
		ViewOnTrackConfigPageobj.verifyTheme3StartDateHeader("Theme 3 Start Date");
		ViewOnTrackConfigPageobj.verifyEditHeader("Edit");
		ViewOnTrackConfigPageobj.verifyDeleteHeader("Delete");
		   			
	}
	/**
	 * This method searches the Confluence track
	 */
	@Test(description = "Verify Confluence on Track Results",priority=6)
	public void searchOnTrackConfluenceClientResults()
	{
		ViewOnTrackConfigPageobj.clickOnTrackLink();
		ViewOnTrackConfigPageobj.SelectClient();
		ViewOnTrackConfigPageobj.clickSubmitButton();
		adminOnTrackPageAssert.assertTrue(ViewOnTrackConfigPageobj.clientWithRecords("confluence"));			
	}

	/**
	 * This method checks Edit and delete link
	 */
	@Test(description = "Verify Edit and Delete link is shown", priority=7)
	public void VerifyEditAndDeleteLink()
	{
		adminOnTrackPageAssert.assertTrue(ViewOnTrackConfigPageobj.verifyEditLinkIsPresent("Edit"));
		adminOnTrackPageAssert.assertTrue(ViewOnTrackConfigPageobj.verifyDeleteLinkIsPresent("Delete"));	
	}
	
	/**
	 * This method checks Edit and delete link
	 */
	@Test(description = "click Edit link", priority=8)
	public void clickOnTheEditLink()
	{
		ViewOnTrackConfigPageobj.clickOnEditLink();
			
	}
	@Test(description = "Edit Track details",priority=9)
	public void EditTrackInstanceDetails()
	{
		EditOnTrackConfigPageobj.SelectTrackCampaign();
		EditOnTrackConfigPageobj.submitUpdate();
	}
	
	@Test(description = "Search updated Confluence Track details",priority=10)
	public void navigateToViewOnTrackPage()
	{
		EditOnTrackConfigPageobj.clickOnViewTrackPage();	
		
	}
	
	/**
	 * This method with search updated confluence track name
	 */
	@Test(description = "Search updated Confluence Track details",priority=11)
	public void searchUpdatedTrackInstanceDetails()
	{	
		EditOnTrackConfigPageobj.SelectClient();
		EditOnTrackConfigPageobj.clickSubmitButton();
		adminOnTrackPageAssert.assertTrue(EditOnTrackConfigPageobj.clientWithRecords("Confluence"));					
		}
	    					
	
	/**
	 * This method verify edit Campaign name and supress theme3
	 */
		
	@Test(description = "verify edit Campaign name and supress theme3",priority=12)
	public void verifyEditTrackDetails()
	{
		adminOnTrackPageAssert.assertTrue(EditOnTrackConfigPageobj.updatedRecords("2016"));														
		
	}
	
	@Test(description = "Navigate to Add OnTrack Config Page Test",dependsOnMethods="verifyEditTrackDetails")
	public void navigateToOnTrackAddScreen()
	{
		EditOnTrackConfigPageobj.click_OnTrackAddConfiguration();
		
		
	}
	
	@Test(description = "Verify UI Element Is Present Or Not", priority=13)
	public void VerifyElementsOfConfigPage()
	{
		adminOnTrackPageAssert.assertTrue(AddOnTrackConfigPageobj.verifyClientDropDown());
		adminOnTrackPageAssert.assertTrue(AddOnTrackConfigPageobj.verifyTrackCampaign());
		
	}
	/** Add On Track Configuration for 4 weeks Challenge for all three themes */
	@Test(description = "Add On Track Configuration for 4 weeks Challenge for all three themes",priority=14)
	public void addOnTrackConfiguration()
	{
		AddOnTrackConfigPageobj.SelectClient();
		AddOnTrackConfigPageobj.SelectTrackCampaign();
		AddOnTrackConfigPageobj.selectTrackClient();
		AddOnTrackConfigPageobj.selectChallengeDurationForTheme1();
		AddOnTrackConfigPageobj.selectUpcomingStartDateForTheme1();
		AddOnTrackConfigPageobj.selectChallengeDurationForTheme2();
		AddOnTrackConfigPageobj.selectUpcomingStartDateForTheme2();
		AddOnTrackConfigPageobj.selectChallengeDurationForTheme3();
		AddOnTrackConfigPageobj.selectUpcomingStartDateForTheme3();
		AddOnTrackConfigPageobj.challengeWeeklyCommunication();
		AddOnTrackConfigPageobj.generateOnTrackInstance();
	}
	
	@AfterClass
	public void classTearDown() {
		logoutObj = new LogOutPage();
		logoutObj.clickLogoutLink("Admin");
		setcurrentContext(null, null, false);
		homePageobj = null;
		ViewOnTrackConfigPageobj=null;
		logoutObj=null;
		logger.info("Exiting the classTearDown() method for LifestyleManager_ExerciseLog class \n\n");
		
	}
}
