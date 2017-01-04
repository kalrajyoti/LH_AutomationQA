package com.lh.test;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.lh.helper.DriverFactory;
import com.lh.pages.authenticated.lifestyleManager.LifestyleManagerPage;
import com.lh.pages.authenticated.validic.ValidicImplementation;
import com.lh.test.base.AuthBaseTestClass;
import com.lh.testConfig.TestProperty;


@Listeners(com.lh.helper.LHTestListener.class)
public class ValidicTest extends AuthBaseTestClass {

	private static Logger logger = LogManager.getLogger(ValidicTest.class);
	private Map<String, String> validicData;
	private final String VALID_USERNAME = "validUsername";
	private final String VALID_PASSWORD = "validPassword";
	private final String appName = "appName";
	private final String fitbitUserName = "fitbitUserName";
	private final String fitbitPassword = "fitbitPassword";
	private final String startHour = "starttimehours";
	private final String startMinute = "starttimeminutes";
	private final String meridiemType = "meridiemType";
	private final String durationHour = "durationhours";
	private final String durationMinute = "durationminutes";
	private final String durationSecond = "durationseconds";			
	private LifestyleManagerPage lifestylePageObj;
	private final String Range	="Range";
	private final String teaserZoneTxt = "TeaserZoneText";
	private final String LMTxt = "LM_Txt";			
	private ValidicImplementation validicobj;
	private String partialBYOA_URL = "MemberServices/BYOA/Store.aspx";
	private String partiallifestyleManager_URL = "MemberServices/health/ScreeningGraphPage.aspx";
	@BeforeClass
	public void initClass() {
		logger.info("Inside the initClass() method for BYOA class...");
		validicData = readexcelsheet("ValidicData");


		doLogin(validicData.get(VALID_USERNAME),
				validicData.get(VALID_PASSWORD), "Portal", "Portal");
		validicobj = new ValidicImplementation();
		validicobj.clickBYOA();
		validicobj.verifyURL(partialBYOA_URL);
		lifestylePageObj = new LifestyleManagerPage();
		logger.info("Exiting the initClass() method for BYOA class \n\n");
	}

	@Test(description = "Connect Validic App, TCID :- C104111")
	public void connectValidicApp() {

		Assert.assertTrue(validicobj.connectFitBit(validicData.get(appName),
				validicData.get(fitbitUserName),
				validicData.get(fitbitPassword)));
	}

	@Test(description = "Make Entry in Validic Portal :- C104112",dependsOnMethods="connectValidicApp")
	public void addEntryInValidicPortal() {

		DriverFactory.openURL(TestProperty.FITBITURL);
		Assert.assertTrue(validicobj.createValidicEntry(validicData.get(fitbitUserName),
				validicData.get(fitbitPassword), validicData.get(startHour),
				validicData.get(startMinute), validicData.get(meridiemType),
				validicData.get(durationHour), validicData.get(durationMinute),
				validicData.get(durationSecond)));
	}
	@Test(description = "Click on Exercise from Logs menu list",dependsOnMethods="addEntryInValidicPortal")
	public void valdiateFitbitEntry_LM(){
		DriverFactory.openURL(TestProperty.BASEURL);
		lifestylePageObj.clickLM();
		lifestylePageObj.verifyURL(partiallifestyleManager_URL);
		Assert.assertTrue(validicobj.verifyLifestyleManagerTitleAndTeaserZone(validicData.get(LMTxt), validicData.get(teaserZoneTxt)));
		lifestylePageObj.clickOnLogsLink();
		lifestylePageObj.clickOnExerciseLog();
		Assert.assertTrue(validicobj.validateValidicEntryInLifestyleManager(validicData.get(Range)));
	}
	
	@AfterClass
	public void classTearDown() {
		validicData = null;
		validicobj = null;
		logger.info("Exiting the classTearDown() method for Validic class \n\n");

	}

}
