package com.lh.pages.authenticated.ha;

import java.util.Map;
import static com.lh.helper.DriverFactory.driver;
import static com.lh.testConfig.TestProperty.WAITING_TIME;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.lh.testConfig.TestProperty;

/**
 * <h2>All the methods for Tobacco Use HA page are defined here</h2>
 * <p>
 * 
 * @author amita.arya
 * @version 1.0
 * @since 2015-01-22
 */
public class TobaccoUseHealthAssessmentPage extends HealthAssessmentPage {

	By tobaccoSectionSpecficHeader =
			By.xpath("//*[contains(.,'your tobacco use')][not(.//*[contains(., 'your tobacco use')])]");

	private static final String NO = "no";
	private static final String YES = "yes";
	private static final String NEVER_SMOKED = "never smoked";
	private static final String USED_TO_SMOKE = "used to smoke";
	private static final String STILL_SMOKE = "still smoke";
	private static Logger logger = LogManager.getLogger(com.lh.pages.authenticated.ha.TobaccoUseHealthAssessmentPage.class);
	By stillSmokeCigaretteSmokingHabitRadio 				= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl00_HAQuestionCtrl1_ctl00_rbtList_0");
	By usedToSmokeCigaretteSmokingHabitRadio 				= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl00_HAQuestionCtrl1_ctl00_rbtList_1");
	By neverSmokedCigaretteSmokingHabitRadio 				= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl00_HAQuestionCtrl1_ctl00_rbtList_2");
	By plusOneCigarettePerDayBtn 							= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl01_HAQuestionCtrl1_rdS_32_spinnerPlusButton");
	By minusOneCigarettePerDayBtn 							= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl01_HAQuestionCtrl1_rdS_32_spinnerMinusButton");
	By plusOneSinceSmokedCigaretteRegularlyBtn 				= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl02_HAQuestionCtrl1_rdS_33_spinnerPlusButton");
	By minusOneSinceSmokedCigaretteRegularlyBtn 			= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl02_HAQuestionCtrl1_rdS_33_spinnerMinusButton");
	By plusOneAverageNumberOfCigarettesPerDayBtn 			= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl03_HAQuestionCtrl1_rdS_34_spinnerPlusButton");
	By minusOneAverageNumberOfCigarettesPerDayBtn 			= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl03_HAQuestionCtrl1_rdS_34_spinnerMinusButton");
	By yesUseSmokePipeBtn 									= By.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl04_HAQuestionCtrl1_dvTwoChoiceyesno35_dvTwo']/table/tbody/tr/td[@class=' first-option']/input");
	By noUseSmokePipeBtn 									= By.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl04_HAQuestionCtrl1_dvTwoChoiceyesno35_dvTwo']/table/tbody/tr/td[@class='second-option']/input");
	By yesUseSmokeCigarsBtn 								= By.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl05_HAQuestionCtrl1_dvTwoChoiceyesno36_dvTwo']/table/tbody/tr/td[@class=' first-option']/input");
	By noUseSmokeCigarsBtn 									= By.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl05_HAQuestionCtrl1_dvTwoChoiceyesno36_dvTwo']/table/tbody/tr/td[@class='second-option']/input");
	By yesUseSmokelessTobacco 								= By.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl06_HAQuestionCtrl1_dvTwoChoiceyesno37_dvTwo']/table/tbody/tr/td[@class=' first-option']/input");
	By noUseSmokelessTobacco 								= By.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl06_HAQuestionCtrl1_dvTwoChoiceyesno37_dvTwo']/table/tbody/tr/td[@class='second-option']/input");
	By cutDownTobaccoNoPresentInterestBtn 					= By.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl07_HAQuestionCtrl1_rdQ_38_AnswersContainer']/div/div[1]/label");
	By cutDownTobaccoPlanToChangeIn6MonthsBtn 				= By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl07_HAQuestionCtrl1_rdQ_38_AnswersContainer']/div/div[2]/label");
	By cutDownTobaccoPlanToChangeThisMonthBtn 				= By.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl07_HAQuestionCtrl1_rdQ_38_AnswersContainer']/div/div[3]/label");
	By cutDownTobaccoRecentlyStartedBtn 					= By.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl07_HAQuestionCtrl1_rdQ_38_AnswersContainer']/div/div[4]/label");
	By cutDownTobaccoAlreadyDoingThisBtn 					= By.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl07_HAQuestionCtrl1_rdQ_38_AnswersContainer']/div/div[5]/label");
	By cutDownTobaccoNotApplicableBtn 						= By.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl07_HAQuestionCtrl1_rdQ_38_AnswersContainer']/div/div[6]/label");

	/**
	 * One param class constructor
	 * 
	 * @param driver
	 *            webdriver instance to connect to the page under test
	 */
	public TobaccoUseHealthAssessmentPage() {
		super();
		logger.info("Initializing the TobaccoUseHealthAssessmentPage Object...");
		if(!(driver.getCurrentUrl().contains("MSEvents"))) {
			logger.info("...not on Health Assessment page.  Navigating there now...");
		}
		new WebDriverWait(driver,WAITING_TIME).until
				(ExpectedConditions.presenceOfElementLocated(tobaccoSectionSpecficHeader));
		/*if (!(driver.findElement(tobaccoSectionSpecficHeader).isDisplayed())) {
			logger.error("This is not the Tobacco Use Health Assessment page");
			throw new IllegalStateException("This is not the TobaccoUseHealthAssessmentPage page");
		}*/
		logger.info("Initialized the TobaccoUseHealthAssessmentPage page object");
	}

	/**
	 * Sets the Smoking habit option of the user.
	 * 
	 * @param smokingHabit
	 *            values can be Still smoke/Used to smoke/Never smoked.
	 */
	public void setDescribeYourCigaretteSmokingHabits(String smokingHabit) {
		switch (smokingHabit) {
		case STILL_SMOKE:
			driver.findElement(stillSmokeCigaretteSmokingHabitRadio).click();
			break;
		case USED_TO_SMOKE:
			driver.findElement(usedToSmokeCigaretteSmokingHabitRadio).click();
			break;
		case NEVER_SMOKED:
			driver.findElement(neverSmokedCigaretteSmokingHabitRadio).click();
			break;
		default:
			logger.error("Invalid value for - Describe your Cigarrete smoking habits, was provided.");
			break;
		}
	}

	/**
	 * Sets the number of cigarettes the user smokes in a day.
	 * 
	 * @param numberOfCigarettes
	 *            numerical string e.g. 0, 1...
	 */
	private void setHowManyCigarettesDoYouSmokePerDay(String numberOfCigarettes) {
		Integer numberOfCigarettesPerDay = Integer.parseInt(numberOfCigarettes.trim());

		for (int numberOfCigarettesToSet = 0; numberOfCigarettesToSet < numberOfCigarettesPerDay; numberOfCigarettesToSet++) {
			driver.findElement(plusOneCigarettePerDayBtn).click();
		}
	}

	/**
	 * Sets the number of years since the user stopped smoking.
	 * 
	 * @param howManyYearsSinceQuit
	 *            number of years as Numerical string.
	 */
	private void setHowManyYearsSinceYouSmokedCigarettesRegularly(String howManyYearsSinceQuit) {
		Integer yearsSinceQuitSmoking = Integer.parseInt(howManyYearsSinceQuit.trim());

		for (int numberofYearsCounter = 0; numberofYearsCounter < yearsSinceQuitSmoking; numberofYearsCounter++) {
			driver.findElement(plusOneSinceSmokedCigaretteRegularlyBtn).click();
		}
	}

	/**
	 * Sets the number of cigarettes the user smoked on an average before
	 * quitting.
	 * 
	 * @param numberOfCigarettes
	 *            number of cigarettes the user smoked on an average before
	 *            quitting as Numerical sting.
	 */
	private void setHowManyAvgNumberOfCigarettesPerDayBeforeQuitting(String numberOfCigarettesPerDay) {
		Integer numberOfCigarettes = Integer.parseInt(numberOfCigarettesPerDay.trim());

		for (int numberOfCigarettesCounter = 0; numberOfCigarettesCounter < numberOfCigarettes; numberOfCigarettesCounter++) {
			driver.findElement(plusOneSinceSmokedCigaretteRegularlyBtn).click();
		}	
	}
	
	/**
	 * Sets the answers to track the smoking habit of the user
	 * 
	 * @param smokingHabit
	 *            values can be Still smoke/Used to smoke/Never smoked.
	 * @param numberOfCigarettes
	 *            numerical string e.g. 0, 1...
	 * @param howManyYearsSinceQuit
	 *            number of years as Numerical string.
	 * @param numberOfCigarettesPerDay
	 *            number of cigarettes the user smoked on an average before quitting as Numerical sting.
	 */
	private void setSmokingHabits(String smokingHabit, String numberOfCigarettes, String howManyYearsSinceQuit,
			String numberOfCigarettesPerDay) {
		smokingHabit = smokingHabit.trim().toLowerCase();
		if (NEVER_SMOKED.equals(smokingHabit)) {
			setDescribeYourCigaretteSmokingHabits(smokingHabit);
		} else if (USED_TO_SMOKE.equals(smokingHabit)) {
			setDescribeYourCigaretteSmokingHabits(smokingHabit);
			setHowManyYearsSinceYouSmokedCigarettesRegularly(howManyYearsSinceQuit);
			setHowManyAvgNumberOfCigarettesPerDayBeforeQuitting(numberOfCigarettesPerDay);
		} else {
			setDescribeYourCigaretteSmokingHabits(smokingHabit);
			setHowManyCigarettesDoYouSmokePerDay(numberOfCigarettes);
			setHowManyYearsSinceYouSmokedCigarettesRegularly(howManyYearsSinceQuit);
			setHowManyAvgNumberOfCigarettesPerDayBeforeQuitting(numberOfCigarettesPerDay);
		}
	}

	/**
	 * Sets whether the user smokes pipe or not.
	 * 
	 * @param doYouSmoke
	 *            yes or no value
	 */
	public void setDoYouSmokePipe(String doYouSmoke) {
		doYouSmoke = doYouSmoke.trim().toLowerCase();
		
		if (YES.equals(doYouSmoke)){
			driver.findElement(yesUseSmokePipeBtn).click();
		} else if (NO.equals(doYouSmoke)){
			driver.findElement(noUseSmokePipeBtn).click();
		} else {
			logger.error("A value other than Yes or No was provided for Do you smoke pipe.");
		}
	}

	/**
	 * Sets whether the user smokes cigars or not.
	 * 
	 * @param doYouSmoke
	 *            yes or no value
	 */
	public void setDoYouSmokeCigars(String doYouSmoke) {
		doYouSmoke = doYouSmoke.trim().toLowerCase();
		
		if (YES.equals(doYouSmoke)){
			driver.findElement(yesUseSmokeCigarsBtn).click();
		} else if (NO.equals(doYouSmoke)){
			driver.findElement(noUseSmokeCigarsBtn).click();
		} else {
			logger.error("A value other than Yes or No was provided for Do you smoke cigars.");
		}
	}

	/**
	 * Sets whether the user uses smokeless tobacco or not
	 * 
	 * @param doYouSmoke
	 *            yes or no value
	 */
	public void setDoYouUseSmokeLessTobacco(String doYouSmoke) {
		doYouSmoke = doYouSmoke.trim().toLowerCase();
		
		if (YES.equals(doYouSmoke)){
			driver.findElement(yesUseSmokelessTobacco).click();
		} else if (NO.equals(doYouSmoke)){
			driver.findElement(noUseSmokelessTobacco).click();
		} else {
			logger.error("A value other than Yes or No was provided for Do you use smokeless tobacco.");
		}
	}

	/**
	 * Sets the users plan for quitting or cutting down tobacco use
	 * 
	 * @param planForTobaccoUse
	 *            defines the users plan for tobacco use no present
	 *            interest/Plan to change in 6 months/Plan to change this
	 *            month/Already Doing This/Not Applicable
	 */
	public void setPlanToQuitOrCutDownTobaccoUse(String planForTobaccoUse) {
		planForTobaccoUse = planForTobaccoUse.trim().toUpperCase();
		
		switch (planForTobaccoUse){
		case NO_INTEREST:
			driver.findElement(cutDownTobaccoNoPresentInterestBtn).click();
			break;
		case CHANGE_IN_6_MONTHS:
			driver.findElement(cutDownTobaccoPlanToChangeIn6MonthsBtn).click();
			break;
		case CHANGE_THIS_MONTH:
			driver.findElement(cutDownTobaccoPlanToChangeThisMonthBtn).click();
			break;
		case RECENTLY_STARTED:
			driver.findElement(cutDownTobaccoRecentlyStartedBtn).click();
			break;
		case ALREADY_DOING:
			driver.findElement(cutDownTobaccoAlreadyDoingThisBtn).click();
			break;
		case NOT_APPLICABLE:
			driver.findElement(cutDownTobaccoNotApplicableBtn).click();
			break;
		default:
			logger.error("Invalid value for - Plan to quit or reduce tobacco use was provided.");
			break;
		}
	}

	/**
	 * Sets the answers for all the Tobacco use questions
	 * 
	 * @param tobaccoUseHAData
	 *            contains data read form the data file
	 */
	public void completeTobaccoUseHA(Map<String, String> tobaccoUseHAData) {
		logger.info("Starting to fill data in the Tobacco Use Page...");
		
		setSmokingHabits(tobaccoUseHAData.get("cigaretteSmokeHabit"), tobaccoUseHAData.get("howManyCigarettesPerDay"),
				tobaccoUseHAData.get("howManyYearsSinceQuit"),
				tobaccoUseHAData.get("howManyAvgCigarettesPerDayBeforeQuit"));
		setDoYouSmokePipe(tobaccoUseHAData.get("smokePipe"));
		setDoYouSmokeCigars(tobaccoUseHAData.get("smokeCigars"));
		setDoYouUseSmokeLessTobacco(tobaccoUseHAData.get("useSmokelessTobacco"));
		setPlanToQuitOrCutDownTobaccoUse(tobaccoUseHAData.get("planForReducingTobaccoUse"));
		
		logger.info("Completed filling data in the Tobacco Use Page...");
	}
}