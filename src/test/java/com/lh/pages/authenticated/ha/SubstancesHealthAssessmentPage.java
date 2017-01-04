package com.lh.pages.authenticated.ha;

import java.util.Map;
import static com.lh.helper.DriverFactory.driver;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import com.lh.testConfig.TestProperty;

/**
 * <h2>All the methods for Substances Use HA page are defined here</h2>
 * <p>
 * 
 * @author amita.arya
 * @version 1.0
 * @since 2015-01-27
 */
public class SubstancesHealthAssessmentPage extends HealthAssessmentPage {

	By substancesSpecificHeader =
			By.xpath("//*[contains(.,'use of alcohol')][not(.//*[contains(., 'use of alcohol')])]");

	private static final String RARELY_OR_NEVER = "rarely or never";
	private static final String SOMETIMES = "sometimes";
	private static final String ALMOST_EVERY_DAY = "almost every day";
	private static Logger logger = LogManager.getLogger(com.lh.pages.authenticated.ha.SubstancesHealthAssessmentPage.class);
	
	By almostEveryDayUseDrugsOrMedicationsRadio 			= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl00_HAQuestionCtrl1_ctl00_rbtList_0");
	By sometimesUseDrugsOrMedicationsRadio 					= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl00_HAQuestionCtrl1_ctl00_rbtList_1");
	By rarelyOrNeverUseDrugsOrMedicationsRadio 				= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl00_HAQuestionCtrl1_ctl00_rbtList_2");
	By plusOneAlcoholicBeveragesPerWeekBtn 					= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl01_HAQuestionCtrl1_rdS_42_spinnerPlusButton");
	By minusOneAlcoholicBeveragesPerWeekBtn 				= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl01_HAQuestionCtrl1_rdS_42_spinnerMinusButton");
	By plusOneTimesRodeWithDrunkDriverPerWeekBtn 			= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl02_HAQuestionCtrl1_rdS_45_spinnerPlusButton");
	By minusOneTimesRodeWithDrunkDriverPerWeekBtn 			= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl02_HAQuestionCtrl1_rdS_45_spinnerMinusButton");
	By reducingYourAlcoholUseNoPresentInterestBtn 			= By.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl03_HAQuestionCtrl1_rdQ_46_AnswersContainer']/div/div[1]");
	By reducingYourAlcoholUsePlanToChangeIn6MonthsBtn 		= By.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl03_HAQuestionCtrl1_rdQ_46_AnswersContainer']/div/div[2]");
	By reducingYourAlcoholUsePlanToChangeThisMonthBtn 		= By.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl03_HAQuestionCtrl1_rdQ_46_AnswersContainer']/div/div[3]");
	By reducingYourAlcoholUseRecentlyStartedBtn 			= By.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl03_HAQuestionCtrl1_rdQ_46_AnswersContainer']/div/div[4]");
	By reducingYourAlcoholUseAlreadyDoingThisBtn 			= By.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl03_HAQuestionCtrl1_rdQ_46_AnswersContainer']/div/div[5]");
	
	/**
	 * One param class constructor
	 * 
	 * @param driver
	 *            webdriver instance to connect to the page under test
	 */
	public SubstancesHealthAssessmentPage() {
		super();
		logger.info("Initializing the TobaccoUseHealthAssessmentPage Object...");
		if(!(driver.getCurrentUrl().contains("MSEvents"))) {
			logger.info("...not on Health Assessment page.  Navigating there now...");
		}
		new WebDriverWait(driver, 15).until(ExpectedConditions.presenceOfElementLocated(substancesSpecificHeader));
		logger.info("Initialized the TobaccoUseHealthAssessmentPage page object");
	}

	/**
	 * Sets the use of Drugs or Medications for the user
	 * @param drugOrMedicationUse
	 *            values can be rarely or never/sometimes/almost every day
	 */
	private void setDrugOrMedicationUse(String drugOrMedicationUse) {
		drugOrMedicationUse = drugOrMedicationUse.trim().toLowerCase();

		switch (drugOrMedicationUse) {
		case ALMOST_EVERY_DAY:
			driver.findElement(almostEveryDayUseDrugsOrMedicationsRadio).click();
			break;
		case SOMETIMES:
			driver.findElement(sometimesUseDrugsOrMedicationsRadio).click();
			break;
		case RARELY_OR_NEVER:
			driver.findElement(rarelyOrNeverUseDrugsOrMedicationsRadio).click();
		default:
			logger.error("Invalid value for -  use of drugs or medications (including prescription drugs) that affect your mood or help you to relax, was provided.");
			break;
		}
	}
	

	/**
	 * Sets the average number of alcoholic beverages consumed per week.
	 * 
	 * @param numberOfAlcoholicBeverage
	 *            numerical string e.g. 0, 1...
	 */
	private void setNumberOfAlcoholicBeveragesPerWeek(String numberOfAlcoholicBeverage){
		Integer numberOfAlcoholicBeveragePerWeek = Integer.parseInt(numberOfAlcoholicBeverage.trim());

		for (int numberOfAlcoholicBeverageToSet = 0; numberOfAlcoholicBeverageToSet < numberOfAlcoholicBeveragePerWeek; numberOfAlcoholicBeverageToSet++) {
			driver.findElement(plusOneAlcoholicBeveragesPerWeekBtn).click();
		}
	}
	
	/**
	 * Sets the number of times rode with a drunk driver last week.
	 * @param numberOfTimesRodeWithDrunkDriver
	 * 			 numerical string e.g. 0, 1...
	 */
	private void setNumberOfTimesDroveWithDrunkDriverLastWeek(String numberOfTimesRodeWithDrunkDriver){
		Integer numberOfTimesRodeWithDrunkDriverLastWeek = Integer.parseInt(numberOfTimesRodeWithDrunkDriver.trim());

		for (int numberOfTimesRodeWithDrunkDriverToSet = 0; numberOfTimesRodeWithDrunkDriverToSet < numberOfTimesRodeWithDrunkDriverLastWeek; numberOfTimesRodeWithDrunkDriverToSet++) {
			driver.findElement(plusOneTimesRodeWithDrunkDriverPerWeekBtn).click();
		}
	}
	
	/**
	 * Sets the users plan to reduce alcohol use
	 * 
	 * @param planForAlcoholUse
	 *            defines the users plan to reduce alcohol use as no present
	 *            interest/Plan to change in 6 months/Plan to change this
	 *            month/Already Doing This/Not Applicable
	 */
	private void setPlanOnReducingAlcoholUse(String planForAlcoholUse) {
		planForAlcoholUse = planForAlcoholUse.trim().toUpperCase();

		switch (planForAlcoholUse) {
		case NO_INTEREST:
			driver.findElement(reducingYourAlcoholUseNoPresentInterestBtn).click();
			break;
		case CHANGE_IN_6_MONTHS:
			driver.findElement(reducingYourAlcoholUsePlanToChangeIn6MonthsBtn).click();
			break;
		case CHANGE_THIS_MONTH:
			driver.findElement(reducingYourAlcoholUsePlanToChangeThisMonthBtn).click();
			break;
		case RECENTLY_STARTED:
			driver.findElement(reducingYourAlcoholUseRecentlyStartedBtn).click();
			break;
		case ALREADY_DOING:
			driver.findElement(reducingYourAlcoholUseAlreadyDoingThisBtn).click();
			break;
		default:
			logger.error("Invalid value for - Plan on reducing alcohol use, was provided.");
			break;
		}
	}
	

	/**
	 * Sets answers for all the Substances use page questions
	 * @param substancesHAData contains values read from the data file 
	 */
	public void completeSubstancesUseHA(Map<String, String> substancesHAData){
		logger.info("Starting to fill data in the Substances Use Page...");
		setDrugOrMedicationUse(substancesHAData.get("drugOrMedicationUse"));
		setNumberOfAlcoholicBeveragesPerWeek(substancesHAData.get("numberOfAlcoholicBeverage"));
		setNumberOfTimesDroveWithDrunkDriverLastWeek(substancesHAData.get("numberOfTimesRodeWithDrunkDriver"));
		setPlanOnReducingAlcoholUse(substancesHAData.get("planForAlcoholUse"));
	}
}
