package com.lh.pages.authenticated;
import static com.lh.helper.DriverFactory.driver;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.testng.Reporter;
import com.lh.testConfig.TestProperty;
import com.lh.utils.SeleniumUtil;

/**
 * <h2>This is the Self Registration page Class for the Unauthenticated user</h2>
 * <p>
 * 
 * @author amita.arya
 * @version 1.0
 * @since 2015-03-02
 */
public class WellnessBankPage extends MainPage {

	/** Logger for the Login class */
	private static Logger logger 								= LogManager.getLogger(BiometricScreeningPage.class);	
	private By progressBarTxt									= By.xpath("//*[@id='progressBar']/div[@class='wb-progress-bar-text']");
	private By availablePoints									= By.xpath("//*[@id='MasterForm']/div[3]/div/div[1]/div[2]/div[@class='wellnessbank-legend available']/span");
	private By earnedPoints										= By.xpath("//*[@id='MasterForm']/div[3]/div/div[1]/div[2]/div[@class='wellnessbank-legend earned']/span");
	private By incentive1Gp1									= By.id("repContainers_ctl00_ctl00_repComponent_ctl00_ltrComponentName");	
	private By incentive2Gp1									= By.id("repContainers_ctl00_ctl00_repComponent_ctl01_ltrComponentName");	
	private By incentive1Gp2									= By.id("repContainers_ctl01_ctl00_repComponent_ctl00_ltrComponentName");	
	private By attestationRecordNow11Btn						= By.id("repContainers_ctl00_ctl00_repComponent_ctl00_hrefIncentiveAction");
	private String attestationConfirmationChk					="//form[@id='modalForm']//input[@id='chkConfirmation']";
	private By attestationSubmitBtn								= By.id("lnkSubmit");
	private By attestationCloseBtn								= By.xpath("//*[@id='modalAttestationConfirmation']/div");
	private By incentiveStatusLbl								= By.id("repContainers_ctl00_ctl00_repComponent_ctl00_lblIncentiveAction");
	private By earnedPtsLbl										= By.xpath("//*[@id='MasterForm']/div[4]/div/div[1]/div[2]/div[1]/span");//@class='wellnessbank-legend earned'
	private By availablePtsLbl									= By.xpath("//*[@id='MasterForm']/div[4]/div/div[1]/div[2]/div[2]/span");
	private By progressBarEarnedSpan							= By.id("spanEarned");
	private By progressBarAvailableSpan							= By.id("spanAvailable");
	//private String partialURL									= "MemberServices/WellnessIncentives/Index.aspx";	
	/**
	 * One param constructor
	 * 
	 * @param driver
	 *            webdriver instance to connect to the page under test
	 */
	public WellnessBankPage () {
		super();
		Reporter.log("Initializing the WellnessbankPage Object...");

		 //Check that we're on the right page.
		Reporter.log("Initialized the Wellnessbank object");
	
			if (!(driver.getTitle().equalsIgnoreCase(TestProperty.CLIENT_PORTAL_TITLE))) {
			logger.error("This is not the Wellnessbank page");
			throw new IllegalStateException(
					"This is not the Wellnessbank page");
		}
		Reporter.log("Initialized the Wellnessbank object");
	}
		
	public void completeSelfAttestation(){
		
		Reporter.log ("Starting the Self attestation ");
		SeleniumUtil.sleep(2);
		SeleniumUtil.click(attestationRecordNow11Btn, driver);
		SeleniumUtil.sleep(2);
		SeleniumUtil.clickbyJS(attestationConfirmationChk, driver);
		SeleniumUtil.sleep(2);
		SeleniumUtil.click(attestationSubmitBtn, driver);
		SeleniumUtil.sleep(2);
		SeleniumUtil.click(attestationCloseBtn, driver);		
		Reporter.log("Completed the self attestation for the user.");
		
	}
	
	public boolean verifySelfAttestationCompletion(String result) {

		boolean isSelfAttestationCompleted = false;

		if (!result.trim().isEmpty()) {

			if ("completed".equalsIgnoreCase((result).trim())) {

				isSelfAttestationCompleted = true;
				Reporter.log("The expected status of the attestation matches with the actual result: " + result.trim());

			} else {

				Reporter.log("The expected status of the attestation does not match with the actual result: " + result.trim());

			}
		}

		return isSelfAttestationCompleted;

	}
	
	private int getAvailablePoints(String ptsDenomination) {

		int availablePoints = 0;
		String availablePtsString = driver.findElement(availablePtsLbl).getText().trim();

		Reporter.log("The available points lable on the UI is - " + availablePtsString);

		String availablePointsSubStr = availablePtsString.substring(11);

		if (!ptsDenomination.trim().isEmpty()) {

			if ("points".equals(ptsDenomination.trim().toLowerCase())) {

				availablePointsSubStr = availablePointsSubStr.substring(0, availablePointsSubStr.length() - 4);

			} else {

				Reporter.log("The denomination - " + ptsDenomination + ", is invalid");

			}

		}

		availablePoints = Integer.parseInt(availablePointsSubStr);

		return availablePoints;
	}
	
	
	private int getEarnedPoints(String ptsDenomination) {
		
		int earnedPoints = 0;
		String earnedPtsString = driver.findElement(earnedPtsLbl).getText().trim();

		Reporter.log("The available points lable on the UI is - " + earnedPtsString);

		String earnedPointsSubStr = earnedPtsString.substring(11);

		if (!ptsDenomination.trim().isEmpty()) {

			if ("points".equals(ptsDenomination.trim().toLowerCase())) {

				earnedPointsSubStr = earnedPointsSubStr.substring(0, earnedPointsSubStr.length() - 4);

			} else {

				Reporter.log("The denomination - " + ptsDenomination + ", is invalid");

			}

		}

		earnedPoints = Integer.parseInt(earnedPointsSubStr);

		return earnedPoints;

	}
	
	public boolean verifyEarnedPoints(String expEarnedPts, String ptsDenomination) {

		boolean isAvailablePointsVerified = false;
		int expectedEarnedPoints = Integer.parseInt(expEarnedPts.trim());

		int actEarnedPts = getEarnedPoints(ptsDenomination);

		if (!expEarnedPts.isEmpty()) {

			if (expectedEarnedPoints == actEarnedPts) {

				isAvailablePointsVerified = true;
			}
		}

		return isAvailablePointsVerified;
	}
	
	public boolean verifyAvailablePoints(String expAvailablePts, String ptsDenomination) {

		boolean isAvailablePointsVerified = false;

		int expectedAvailablePoints = Integer.parseInt(expAvailablePts.trim());

		int actAvailablePts = getAvailablePoints(ptsDenomination);

		if (!expAvailablePts.isEmpty()) {

			if (expectedAvailablePoints == actAvailablePts) {

				isAvailablePointsVerified = true;
			}
		}

		return isAvailablePointsVerified;
	}

	public boolean verifyWellnessBankProgressBar (String expEarnedPoints, String totalPoints){
		
		boolean isProgressBarStatus = false;
		
		float earnedProgressBarFloat =  ((float) Integer.parseInt(expEarnedPoints.trim()) / (float) Integer.parseInt(totalPoints.trim())) * 100;
		logger.info(earnedProgressBarFloat);
		int earnedProgressBarInt	= (int) Math.round(earnedProgressBarFloat);
		Reporter.log("The expected earned points are: " + earnedProgressBarInt);
		
		String earnedProgressBarPercentage = driver.findElement(progressBarEarnedSpan).getAttribute("style");
		Reporter.log("The progress bar style attribute value is : " + earnedProgressBarPercentage);
		
		String earnedProgressBarPercentageSubStr = earnedProgressBarPercentage.substring(7);
		earnedProgressBarPercentageSubStr = earnedProgressBarPercentageSubStr.substring(0, earnedProgressBarPercentageSubStr.length()-2);
		Reporter.log("The progress bar percentage is : " + earnedProgressBarPercentageSubStr);
		
		int actEarnedProgressBarInt = Integer.parseInt(earnedProgressBarPercentageSubStr);
		Reporter.log("The progress bar percentage as an integer is : " + actEarnedProgressBarInt);
		
		if(earnedProgressBarInt==actEarnedProgressBarInt){
			
			Reporter.log("The expected progress bar percentage for earned points - " + earnedProgressBarInt + " is equal to the actual - " + actEarnedProgressBarInt);
			
			isProgressBarStatus = true;
		}
		
		return isProgressBarStatus;
	}
}

