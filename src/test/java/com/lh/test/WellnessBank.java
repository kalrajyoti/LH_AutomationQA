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
import com.lh.pages.authenticated.WellnessBankPage;
import com.lh.test.base.AuthBaseTestClass;
import com.lh.utils.UtilityMethods;

/**
 * <h2>Define test for Wellness Bank page here!
 * </h2> All the tests for the Wellness bank on client portal are defined here
 * <p> 
 * @author amita.arya
 * @version 2.0
 * @since 2015-05-19
 */
@Listeners(com.lh.helper.LHTestListener.class)
public class WellnessBank extends AuthBaseTestClass {

	private static final String WS_WELLNESSBANK 					= "WellnessBank";
	private static final String USER_NAME 							= "userName";
	private static final String PASSWORD 							= "password";
	private static final String BIOMETRIC_EVENT_INCENTIVE_GROUP_NAME = "BiometricEventIncentiveGroupName";
	private static final String BIOMETRIC_EVENT_INCENTIVE_NAME = "BiometricEventIncentiveName";
	private static final String ATTESTATION_INCENTIVE_GROUP_NAME = "attestationIncentiveGroupName";
	private static final String ATTESTATION_INCENTIVE_NAME = "attestationIncentiveName";
	private static final String ATTESTATION_COMPLETION_STATUS 		= "attestationCompletionStatus";
	private static Logger logger = LogManager.getLogger(WellnessBank.class);
	private WellnessBankPage wellnessBankPageObj;
	private Map<String, String> wellnessBankData;
	private Assertion wellnessBankAssert = new Assertion();
	private String partialURL									= "MemberServices/WellnessIncentives/Index.aspx";
	/** This method runs before the first test from the class runs 
	 *  Resets the Incentive achieved and the group incentive achieved.
	 */
	@BeforeClass
	public void initClass() {
		logger.info("Inside the initClass() method for WellnessBank class...");
		wellnessBankData = readexcelsheet(WS_WELLNESSBANK);		
		DaoLayer daoObj = new DaoLayerImpl();
		daoObj.deleteIncentiveAchievement(wellnessBankData.get(USER_NAME), wellnessBankData.get(ATTESTATION_INCENTIVE_NAME));
		daoObj.deleteAttestationRecord(wellnessBankData.get(USER_NAME));
		daoObj.deleteIncentiveGroupAchievement(wellnessBankData.get(USER_NAME), wellnessBankData.get(ATTESTATION_INCENTIVE_GROUP_NAME));
		daoObj.deleteIncentiveAchievement(wellnessBankData.get(USER_NAME), wellnessBankData.get(BIOMETRIC_EVENT_INCENTIVE_NAME));
		daoObj.deleteIncentiveGroupAchievement(wellnessBankData.get(USER_NAME), wellnessBankData.get(BIOMETRIC_EVENT_INCENTIVE_GROUP_NAME));
		daoObj = null;
		doLogin(wellnessBankData.get(USER_NAME),wellnessBankData.get(PASSWORD), "Portal", "Portal");	
		wellnessBankPageObj = new WellnessBankPage();
		wellnessBankPageObj.clickYourWellnessBank();
		wellnessBankPageObj.verifyURL(partialURL);
		logger.info("Exiting the initClass() method for WellnessBank class \n\n");
	}

	@Test(description = "Complete The self attestaion of the user and verify that it is completed", dependsOnMethods = { }, groups = {"smoke"})
	public void completeSelfAttestation(){
		
		wellnessBankPageObj.completeSelfAttestation();		
		wellnessBankAssert.assertTrue(wellnessBankPageObj.verifySelfAttestationCompletion(wellnessBankData.get(ATTESTATION_COMPLETION_STATUS)), "The self attestation is not completed!");		
	}
	
	@Test(description = "Verify the progress bar earned progress", dependsOnMethods = {"completeSelfAttestation"}, groups = {"smoke"})
	public void verifyProgressBar(){
		wellnessBankAssert.assertTrue(wellnessBankPageObj.verifyWellnessBankProgressBar(wellnessBankData.get("earnedPoints"), wellnessBankData.get("totalWellnessBankPoints")), "The progressbar appears as expected");		
	}
	
	@Test(description = "Verify the WellnessBank Earned and Available Points", dependsOnMethods = {"completeSelfAttestation"}, groups = {"smoke"})
	public void verifyWellnessBankPoints(){
		wellnessBankPageObj.verifyEarnedPoints(wellnessBankData.get("earnedPoints"), wellnessBankData.get("pointDenomination"));
		wellnessBankPageObj.verifyAvailablePoints(wellnessBankData.get("totalWellnessBankPoints"), wellnessBankData.get("pointDenomination"));		
		
	}

	@Test(description = "Verify the WellnessBank Incentives are updated", dependsOnMethods = {"completeSelfAttestation"}, groups = {"smoke"})
	public void verifyWellnessBankIncentiveUpdate(){
		wellnessBankAssert.assertTrue(wellnessBankPageObj.verifySelfAttestationCompletion(wellnessBankData.get(ATTESTATION_COMPLETION_STATUS)), "The self attestation is not completed!");
	}

	/** This method runs after all test from the class have run */
	@AfterClass
	public void classTearDown() {
		logger.info("Inside the classTearDown() method for WellnessBank class...");
		wellnessBankPageObj = null;		
		logger.info("Writing the output to the Result output text file.");
		UtilityMethods.writeOutputResult();
		logger.info("Exiting the classTearDown() method for WellnessBank class \n\n");
	}
}
