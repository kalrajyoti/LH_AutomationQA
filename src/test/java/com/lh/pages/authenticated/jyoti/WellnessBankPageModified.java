package com.lh.pages.authenticated.jyoti;

import static com.lh.helper.DriverFactory.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Reporter;

import com.lh.pages.authenticated.BiometricScreeningPage;
import com.lh.pages.authenticated.MainPage;
import com.lh.testConfig.TestProperty;
import com.lh.utils.SeleniumUtil;
import com.lh.utils.jyoti.SeleniumUtilModified;

public class WellnessBankPageModified extends MainPageModified{
	/** Logger for the Login class */
	private static Logger logger 								= LogManager.getLogger(BiometricScreeningPage.class);	//why this is logger for biometric screening
	private By attestationRecordNow11Btn						= By.id("repContainers_ctl00_ctl00_repComponent_ctl00_hrefIncentiveAction");
	private String attestationConfirmationChk					="//form[@id='modalForm']//input[@id='chkConfirmation']";
	private By attestationSubmitBtn								= By.id("lnkSubmit");
	private By attestationCloseBtn								= By.xpath("//*[@id='modalAttestationConfirmation']/div");
	private By progressBarEarnedSpan							= By.id("spanEarned");
	private By earnedPtsLbl										= By.xpath("//*[@id='MasterForm']/div[4]/div/div[1]/div[2]/div[1]/span");
	private By availablePtsLbl									= By.xpath("//*[@id='MasterForm']/div[4]/div/div[1]/div[2]/div[2]/span");
	public WellnessBankPageModified() {
		super();
		Reporter.log("Initializing the WellnessbankPage Object...");
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
		SeleniumUtilModified.sleep(2);
		SeleniumUtilModified.click(attestationRecordNow11Btn, driver);
		SeleniumUtilModified.sleep(2);
		SeleniumUtilModified.clickbyJS(attestationConfirmationChk, driver);
		SeleniumUtilModified.sleep(2);
		SeleniumUtilModified.click(attestationSubmitBtn, driver);
		SeleniumUtilModified.sleep(2);
		SeleniumUtilModified.click(attestationCloseBtn, driver);		
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
}
        
            	 
 
	


