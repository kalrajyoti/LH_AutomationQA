package com.lh.pages.authenticated.ha;

import com.lh.utils.UtilityMethods;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Map;

import static com.lh.helper.DriverFactory.driver;

/**
 * <h2>All the methods for Health Status HA page are defined here</h2>
 * <p>
 * 
 * @author amita.arya
 * @version 1.0
 * @since 2015-01-08
 */
public class HealthStatusHealthAssessmentPage extends HealthAssessmentPage{

	By healthStatusSpecificHeader =
			By.xpath("//*[contains(.,'your health status')][not(.//*[contains(., 'your health status')])]");


	private static final String UNKNOWN = "unknown";
	
	/**
	 * Logger to log messages
	 */
	private static Logger logger = LogManager
			.getLogger(com.lh.pages.authenticated.ha.AboutYouHealthAssessmentPage.class);
	
	private By heightInFeetTxt 								= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl00_HAQuestionCtrl1_ctl00_FirstTextBox_TheNumericTextBox");
	private By heightInInchesTxt 							= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl00_HAQuestionCtrl1_ctl00_SecondTextBox_TheNumericTextBox");
	private By weightInPoundsTxt 							= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl01_HAQuestionCtrl1_ctl00_TheNumericTextBox");
	private By waistSizeRadio 								= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl02_HAQuestionCtrl1_ctl00_rbtList_0");
	private By waistSizeInInchesTxt 						= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl02_HAQuestionCtrl1_ctl00_txtOther");
	private By waistSizeUnknownRadio 						= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl02_HAQuestionCtrl1_ctl00_rbtList_1");
	private By waistCircumferenceTxt 						= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl02_HAQuestionCtrl1_ctl00_TheNumericTextBox");
	private By bloodPressureRadio 							= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl03_HAQuestionCtrl1_ctl00_rbtList_0");
	private By bloodPressureSystolicTxt 					= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl03_HAQuestionCtrl1_ctl00_BloodPressureMultiBoxes_FirstTextBox_TheNumericTextBox");
	private By bloodPressureDiastolicTxt 					= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl03_HAQuestionCtrl1_ctl00_BloodPressureMultiBoxes_SecondTextBox_TheNumericTextBox");
	private By bloodPressureUnknownRadio 					= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl03_HAQuestionCtrl1_ctl00_rbtList_1");
	private By bloodPressureNoPresentInterestBtn 			= By.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl04_HAQuestionCtrl1_rdQ_23_AnswersContainer']/div/div[1]");
	private By bloodPressurePlanToChangeIn6MonthsBtn 		= By.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl04_HAQuestionCtrl1_rdQ_23_AnswersContainer']/div/div[2]");
	private By bloodPressurePlanToChangeThisMonthBtn 		= By.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl04_HAQuestionCtrl1_rdQ_23_AnswersContainer']/div/div[3]");
	private By bloodPressureRecentlyStartedBtn 				= By.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl04_HAQuestionCtrl1_rdQ_23_AnswersContainer']/div/div[4]");
	private By bloodPressureAlreadyDoingThisBtn 			= By.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl04_HAQuestionCtrl1_rdQ_23_AnswersContainer']/div/div[5]");
	private By totalCholesterolRadio 						= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl05_HAQuestionCtrl1_ctl00_rbtList_0");
	private By totalCholesterolTxt 							= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl05_HAQuestionCtrl1_ctl00_txtOther");
	private By totalCholesterolUnknownRadio 				= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl05_HAQuestionCtrl1_ctl00_rbtList_1");
	private By hDLCholesterolRadio 							= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl06_HAQuestionCtrl1_ctl00_rbtList_0");
	private By hDLCholesterolTxt							= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl06_HAQuestionCtrl1_ctl00_txtOther");
	private By hDLCholesterolUnknownRadio					= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl06_HAQuestionCtrl1_ctl00_rbtList_1");
	private By lDLCholesterolRadio							= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl07_HAQuestionCtrl1_ctl00_rbtList_0");
	private By lDLCholesterolTxt							= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl07_HAQuestionCtrl1_ctl00_txtOther");
	private By lDLCholesterolNotSureGoodRadio 				= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl07_HAQuestionCtrl1_ctl00_rbtList_1");
	private By lDLCholesterolNotSureBadRadio 				= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl07_HAQuestionCtrl1_ctl00_rbtList_2");
	private By lDLCholesterolNotSureUnknownRadio			= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl07_HAQuestionCtrl1_ctl00_rbtList_3");
	private By improveCholesterolNoPresentInterestBtn 		= By.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl08_HAQuestionCtrl1_rdQ_27_AnswersContainer']/div/div[1]");
	private By improveCholesterolPlanToChangeIn6MonthsBtn 	= By.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl08_HAQuestionCtrl1_rdQ_27_AnswersContainer']/div/div[2]");
	private By improveCholesterolPlanToChangeThisMonthBtn 	= By.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl08_HAQuestionCtrl1_rdQ_27_AnswersContainer']/div/div[3]");
	private By improveCholesterolRecentlyStartedBtn 		= By.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl08_HAQuestionCtrl1_rdQ_27_AnswersContainer']/div/div[4]");
	private By improveCholesterolAlreadyDoingThisBtn 		= By.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl08_HAQuestionCtrl1_rdQ_27_AnswersContainer']/div/div[5]");
	private By fastingBloodGlucoseRadio 					= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl09_HAQuestionCtrl1_ctl00_rbtList_0");
	private By fastingBloodGlucoseTxt 						= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl09_HAQuestionCtrl1_ctl00_txtOther");
	private By fastingBloodGlucoseUnknownRadio 				= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl09_HAQuestionCtrl1_ctl00_rbtList_1");
	private By nonFastingBloodGlucoseRadio 					= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl10_HAQuestionCtrl1_ctl00_rbtList_0");
	private By nonFastingBloodGlucoseTxt 					= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl10_HAQuestionCtrl1_ctl00_txtOther");
	private By nonFastingBloodGlucoseUnknownRadio 			= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl10_HAQuestionCtrl1_ctl00_rbtList_1");


	/**
	 * Constructor
	 *
	 */
	public HealthStatusHealthAssessmentPage() {
		super();
		logger.info("Initializing the HealthStatusHealthAssessmentPage Object...");
		if(!(driver.getCurrentUrl().contains("MSEvents"))) {
			logger.info("...not on Health Assessment page.  Navigating there now...");
		}
		//new WebDriverWait(driver, 15).until(ExpectedConditions.presenceOfElementLocated(healthStatusSpecificHeader));
		// Check that we're on the right page.
		/*if (!(driver.findElement(healthStatusSpecificHeader).isDisplayed())){
			logger.error("This is not the Health Status Health Assessment page");
			throw new IllegalStateException("This is not the HealthStatusHealthAssessmentPage page");
		}*/
		logger.info("Initialized the HealthStatusHealthAssessmentPage page object");
	}

	/**
	 * Sets the height for the user on the Health Status HA page
	 * 
	 * @param heightFeet
	 *            height in feet
	 * @param heightInches
	 *            height in inches, if height is >12 , feet+1 and inches-12 is
	 *            set in the DB
	 */
	public void setHeight(String heightFeet, String heightInches) {
		heightFeet = heightFeet.trim();
		heightInches = heightInches.trim();

		if (UtilityMethods.isInteger(heightFeet)) {
			logger.debug("Setting the Height in feet as - " + heightFeet);
			driver.findElement(heightInFeetTxt).clear();
			driver.findElement(heightInFeetTxt).sendKeys(heightFeet);
		} else {
			logger.error("Setting the Height - " + heightFeet + "failed as the value provided is invalid");
		}

		if (UtilityMethods.isInteger(heightInches)) {
			logger.debug("Setting the Height in feet as - " + heightInches);
			driver.findElement(heightInInchesTxt).clear();
			driver.findElement(heightInInchesTxt).sendKeys(heightInches);
		} else {
			logger.error("Setting the Height - " + heightFeet + "failed as the value provided is invalid");
		}
	}

	/**
	 * Sets the weight for the user on the Health Status HA page
	 * 
	 * @param weight
	 *            weight in pounds
	 */
	public void setWeight(String weight) {
		weight = weight.trim();

		if (UtilityMethods.isInteger(weight)) {
			logger.debug("Setting the weight in lbs as - " + weight);
			driver.findElement(weightInPoundsTxt).clear();
			driver.findElement(weightInPoundsTxt).sendKeys(weight);
		} else {
			logger.error("Setting the weight - " + weight + "failed as the value provided is invalid");
		}
	}

	/**
	 * Sets the waist size in inches or selects "I am not sure" option
	 * 
	 * @param waistCircumference
	 *            waist size in inches or "unknown"
	 */
	public void setWaistCircumference(String waistCircumference) {
		waistCircumference = waistCircumference.toLowerCase().trim();
		if (UNKNOWN.equals(waistCircumference)) {
			driver.findElement(waistSizeUnknownRadio).click();
			logger.debug("The user selected- I am not sure for Waist Circumference.");
		} else if (UtilityMethods.isInteger(waistCircumference)) {
			driver.findElement(waistSizeInInchesTxt).clear();
			driver.findElement(waistSizeRadio).click();
			driver.findElement(waistSizeInInchesTxt).sendKeys(waistCircumference);
			logger.debug("The Waist Circumference is set to - " + waistCircumference);
		} else {
			logger.error("Invalid value for waist circumference is provided.");
		}
	}
	
	
	/**
	 * Sets the waist circumference for an all state configuration Client
	 * @param waistCircumference
	 */
	public void setWaistCircumferenceTxtOnly(String waistCircumference) {
		waistCircumference = waistCircumference.toLowerCase().trim();

		driver.findElement(waistCircumferenceTxt).clear();
		driver.findElement(waistCircumferenceTxt).sendKeys(waistCircumference);
		logger.debug("The Waist Circumference is set to - " + waistCircumference);

	}

	/**
	 * Sets the blood pressure reading for the user
	 * 
	 * @param bloodPressureSystolic
	 *            blood pressure in integer values or "unknown"
	 * @param bloodPressureDiastolic
	 *            diastolic blood pressure in integer values or "unknown"
	 */
	public void setBloodPressure(String bloodPressureSystolic, String bloodPressureDiastolic) {
		bloodPressureSystolic = bloodPressureSystolic.toLowerCase().trim();
		bloodPressureDiastolic = bloodPressureDiastolic.toLowerCase().trim();
		if (UNKNOWN.equals(bloodPressureSystolic) && UNKNOWN.equals(bloodPressureDiastolic)) {
			driver.findElement(bloodPressureUnknownRadio).click();
			logger.debug("The user selected- I am not sure for blood pressure.");
		} else if (UtilityMethods.isInteger(bloodPressureSystolic) && UtilityMethods.isInteger(bloodPressureDiastolic)) {
			driver.findElement(bloodPressureSystolicTxt).clear();
			driver.findElement(bloodPressureDiastolicTxt).clear();
			driver.findElement(bloodPressureRadio).click();
			driver.findElement(bloodPressureSystolicTxt).sendKeys(bloodPressureSystolic);
			driver.findElement(bloodPressureDiastolicTxt).sendKeys(bloodPressureDiastolic);
			logger.debug("The blood pressure is set to - " + bloodPressureSystolic + "/" + bloodPressureDiastolic);
		} else {
			logger.error("Invalid value for blood pressure systolic/diastolic is provided.");
		}
	}

	/**
	 * Sets the plan to lower blood pressure of the user
	 * 
	 * @param lowerYourBloodPressure
	 *            can be set to the following values: "no present interest",
	 *            "plan to change in 6 months", "plan to change this month",
	 *            "recently started", "already doing this"
	 */
	public void setPlanToLowerBloodPressure(String lowerYourBloodPressure) {
		lowerYourBloodPressure = lowerYourBloodPressure.toUpperCase().trim();
		switch (lowerYourBloodPressure) {
		case NO_INTEREST:
			driver.findElement(bloodPressureNoPresentInterestBtn).click();
			break;
		case CHANGE_IN_6_MONTHS:
			driver.findElement(bloodPressurePlanToChangeIn6MonthsBtn).click();
			break;
		case CHANGE_THIS_MONTH:
			driver.findElement(bloodPressurePlanToChangeThisMonthBtn).click();
			break;
		case RECENTLY_STARTED:
			driver.findElement(bloodPressureRecentlyStartedBtn).click();
			break;
		case ALREADY_DOING:
			driver.findElement(bloodPressureAlreadyDoingThisBtn).click();
			break;
		default:
			logger.error("Invalid value for - plan to lower your blood pressure was provided.");
			break;
		}
	}

	/**
	 * Sets the total cholesterol
	 * 
	 * @param totalCholesterol
	 *            total cholesterol value as integer or "Unknown"
	 */
	public void setTotalCholesterol(String totalCholesterol) {
		totalCholesterol = totalCholesterol.toLowerCase().trim();
		if (UNKNOWN.equals(totalCholesterol)) {
			driver.findElement(totalCholesterolUnknownRadio).click();
			logger.debug("The user selected- I am not sure for total cholesterol value.");
		} else if (UtilityMethods.isInteger(totalCholesterol)) {
			driver.findElement(totalCholesterolRadio).click();
			driver.findElement(totalCholesterolTxt).clear();
			driver.findElement(totalCholesterolTxt).sendKeys(totalCholesterol);
			logger.debug("The total cholesterol is set to - " + totalCholesterol);
		} else {
			logger.error("Invalid value for total cholesterol is provided.");
		}
	}

	/**
	 * 
	 * @param hDLCholesterol
	 *            HDL cholesterol value as integer or "Unknown"
	 */
	public void setHDLCholesterol(String hDLCholesterol) {
		hDLCholesterol = hDLCholesterol.toLowerCase().trim();
		if (UNKNOWN.equals(hDLCholesterol)) {
			driver.findElement(hDLCholesterolUnknownRadio).click();
			logger.debug("The user selected- I am not sure for hdl cholesterol value.");
		} else if (UtilityMethods.isInteger(hDLCholesterol)) {
			driver.findElement(hDLCholesterolRadio).click();
			driver.findElement(hDLCholesterolTxt).sendKeys(hDLCholesterol);
			logger.debug("The hdl cholesterol is set to - " + hDLCholesterol);
		} else {
			logger.error("Invalid value for hdl cholesterol is provided.");
		}
	}

	/**
	 * Sets the LDL cholesterol for the user on Health status page
	 * 
	 * @param lDLCholesterol
	 *            LDL cholesterol can be an integer or
	 *            "unknown"/"not sure good"/"not sure bad"
	 */
	public void setLDLCholesterol(String lDLCholesterol) {
		lDLCholesterol = lDLCholesterol.toLowerCase().trim();
		if (UNKNOWN.equals(lDLCholesterol)) {
			driver.findElement(lDLCholesterolNotSureUnknownRadio).click();
			logger.debug("The user selected- I am not sure for ldl cholesterol value.");
		} else if ("not sure good".equals(lDLCholesterol)) {
			driver.findElement(lDLCholesterolNotSureGoodRadio).click();
		} else if ("not sure bad".equals(lDLCholesterol)) {
			driver.findElement(lDLCholesterolNotSureBadRadio).click();
		} else if (UtilityMethods.isInteger(lDLCholesterol)) {
			driver.findElement(lDLCholesterolRadio).click();
			driver.findElement(lDLCholesterolTxt).sendKeys(lDLCholesterol);
			logger.debug("The ldl cholesterol is set to - " + lDLCholesterol);
		} else {
			logger.error("Invalid value for ldl cholesterol is provided.");
		}
	}

	/**
	 * Sets the plan to lower cholesterol for the user in the Health Status page
	 * 
	 * @param improveYourCholesterol
	 *            can be set to the following values: "no present interest",
	 *            "plan to change in 6 months", "plan to change this month",
	 *            "recently started", "already doing this"
	 */
	public void setPlanToImproveChlosterol(String improveYourCholesterol) {
		improveYourCholesterol = improveYourCholesterol.toUpperCase().trim();
		switch (improveYourCholesterol) {
		case NO_INTEREST:
			driver.findElement(improveCholesterolNoPresentInterestBtn).click();
			break;
		case CHANGE_IN_6_MONTHS:
			driver.findElement(improveCholesterolPlanToChangeIn6MonthsBtn).click();
			break;
		case CHANGE_THIS_MONTH:
			driver.findElement(improveCholesterolPlanToChangeThisMonthBtn).click();
			break;
		case RECENTLY_STARTED:
			driver.findElement(improveCholesterolRecentlyStartedBtn).click();
			break;
		case ALREADY_DOING:
			driver.findElement(improveCholesterolAlreadyDoingThisBtn).click();
			break;
		default:
			logger.error("Invalid value for - plan to lower cholesterol was provided.");
			break;
		}
	}

	/**
	 * Sets the fasting blood glucose value for the user on the Health Status
	 * page
	 * 
	 * @param fastingBloodGlucose
	 *            fasting blood glucose as Integer value/"unknown"
	 */
	public void setFastingBloodGlucose(String fastingBloodGlucose) {
		fastingBloodGlucose = fastingBloodGlucose.toLowerCase().trim();
		if (UNKNOWN.equals(fastingBloodGlucose)) {
			driver.findElement(fastingBloodGlucoseUnknownRadio).click();
			logger.debug("The user selected- I am not sure for fasting blood glucose value.");
		} else if (UtilityMethods.isInteger(fastingBloodGlucose)) {
			driver.findElement(fastingBloodGlucoseRadio).click();
			driver.findElement(fastingBloodGlucoseTxt).sendKeys(fastingBloodGlucose);
			logger.debug("The fasting blood glucose is set to - " + fastingBloodGlucose);
		} else {
			logger.error("Invalid value for fasting blood glucose is provided.");
		}
	}

	/**
	 * Sets the non fasting blood glucose value for the user on the Health
	 * Status page
	 * 
	 * @param nonFastingBloodGlucose
	 *            non fasting blood glucose as Integer value/"unknown"
	 */
	public void setNonFastingBloodGlucose(String nonFastingBloodGlucose) {
		nonFastingBloodGlucose = nonFastingBloodGlucose.toLowerCase().trim();
		if (UNKNOWN.equals(nonFastingBloodGlucose)) {
			driver.findElement(nonFastingBloodGlucoseUnknownRadio).click();
			logger.debug("The user selected- I am not sure for non fasting blood glucose value.");
		} else if (UtilityMethods.isInteger(nonFastingBloodGlucose)) {
			driver.findElement(nonFastingBloodGlucoseRadio).click();
			driver.findElement(nonFastingBloodGlucoseTxt).sendKeys(nonFastingBloodGlucose);
			logger.debug("The non fasting blood glucose is set to - " + nonFastingBloodGlucose);
		} else {
			logger.error("Invalid value for non fasting blood glucose is provided.");
		}
	}

	/**
	 * Sets the value for all the Health Status fields for the user
	 * 
	 * @param healthStatusHAData
	 *            contains data read from the excel file
	 */
	public void completeHealthStatusHA(Map<String, String> healthStatusHAData) {

		logger.info("Starting to fill data in the Health Status Page...");

		setHeight(healthStatusHAData.get("heightFeet"), healthStatusHAData.get("heightInches"));
		setWeight(healthStatusHAData.get("weight"));
		setWaistCircumference(healthStatusHAData.get("waistCircumference"));
		setBloodPressure(healthStatusHAData.get("bloodPressureSystolic"),
				healthStatusHAData.get("bloodPressureDiastolic"));
		setPlanToLowerBloodPressure(healthStatusHAData.get("lowerYourBloodPressure"));
		setTotalCholesterol(healthStatusHAData.get("totalCholesterol"));
		setHDLCholesterol(healthStatusHAData.get("hDLCholesterol"));
		setLDLCholesterol(healthStatusHAData.get("lDLCholesterol"));
		setPlanToImproveChlosterol(healthStatusHAData.get("improveYourCholesterol"));
		setFastingBloodGlucose(healthStatusHAData.get("fastingBloodGlucose"));
		setNonFastingBloodGlucose(healthStatusHAData.get("nonFastingBloodGlucose"));

		logger.info("Completed filling data in the Health Status Page.");
	}




}
