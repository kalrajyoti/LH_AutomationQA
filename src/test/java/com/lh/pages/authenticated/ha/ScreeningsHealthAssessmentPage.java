package com.lh.pages.authenticated.ha;

import java.util.Map;
import static com.lh.helper.DriverFactory.driver;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.testng.Reporter;

import com.lh.testConfig.TestProperty;

/**
 * <h2>All the methods for Screenings HA page are defined here</h2>
 * <p>
 * 
 * @author amita.arya
 * @version 1.0
 * @since 2015-02-02
 */
public class ScreeningsHealthAssessmentPage extends HealthAssessmentPage {
	private static final String TWO_TO_THREE_YEARS_AGO 				= "two to three years ago";
	private static final String DON_T_KNOW 							= "don't know";
	private static final String NEVER 								= "never";
	private static final String SEVEN_OR_MORE_YEARS_AGO 			= "seven or more years ago";
	private static final String FIVE_TO_SIX_YEARS_AGO 				= "five to six years ago";
	private static final String THREE_TO_FOUR_YEARS_AGO 			= "three to four years ago";
	private static final String ONE_TO_TWO_YEARS_AGO 				= "one to two years ago";
	private static final String LESS_THAN_A_YEAR_AGO 				= "less than a year ago";
	
	/**
	 * Logger to log messages
	 */
	private static Logger logger 									= LogManager.getLogger(com.lh.pages.authenticated.ha.ScreeningsHealthAssessmentPage.class);
	
	private By lessThanAYearAgoTookFluShotRadio						= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl02_HAQuestionCtrl1_ctl00_rbtList_0"); 
	private By oneToTwoYearsAgoHTookFluShotRadio					= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl02_HAQuestionCtrl1_ctl00_rbtList_1");
	private By twoToThreeYearsAgoTookFluShotRadio					= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl02_HAQuestionCtrl1_ctl00_rbtList_2");
	private By threeToFourYearsAgoTookFluShotRadio					= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl02_HAQuestionCtrl1_ctl00_rbtList_3");
	private By fiveToSixYearsAgoHadTookFluShotRadio					= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl02_HAQuestionCtrl1_ctl00_rbtList_4");
	private By sevenOrMoreYearsAgoTookFluShot						= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl02_HAQuestionCtrl1_ctl00_rbtList_5");
	private By neverTookFluShotRadio								= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl02_HAQuestionCtrl1_ctl00_rbtList_6");
	private By doNotKnowWhenHadFluShotRadio							= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl02_HAQuestionCtrl1_ctl00_rbtList_7");
	private By lessThanAYearAgoTookTetanusShotRadio					= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl03_HAQuestionCtrl1_ctl00_rbtList_0"); 
	private By oneToTwoYearsAgoHTookTetanusShotRadio				= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl03_HAQuestionCtrl1_ctl00_rbtList_1");
	private By twoToThreeYearsAgoTookTetanusShotRadio				= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl03_HAQuestionCtrl1_ctl00_rbtList_2");
	private By threeToFourYearsAgoTookTetanusShotRadio				= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl03_HAQuestionCtrl1_ctl00_rbtList_3");
	private By fiveToSixYearsAgoHadTookTetanusShotRadio				= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl03_HAQuestionCtrl1_ctl00_rbtList_4");
	private By sevenOrMoreYearsAgoTookTetanuShotRadio				= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl03_HAQuestionCtrl1_ctl00_rbtList_5");
	private By neverTookTetanusShotRadio	 						= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl03_HAQuestionCtrl1_ctl00_rbtList_6");
	private By doNotKnowWhenHadTetanusShotRadio						= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl03_HAQuestionCtrl1_ctl00_rbtList_7");
	private By lessThanAYearAgoHadBloodPressureCheckedRadio			= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl04_HAQuestionCtrl1_ctl00_rbtList_0"); 
	private By oneToTwoYearsAgoHadBloodPressureCheckedRadio			= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl04_HAQuestionCtrl1_ctl00_rbtList_1");
	private By twoToThreeYearsAgoHadBloodPressureCheckedRadio		= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl04_HAQuestionCtrl1_ctl00_rbtList_2");
	private By threeToFourYearsAgoHadBloodPressureCheckedRadio		= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl04_HAQuestionCtrl1_ctl00_rbtList_3");
	private By fiveToSixYearsAgoHadBloodPressureCheckedRadio		= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl04_HAQuestionCtrl1_ctl00_rbtList_4");
	private By sevenOrMoreYearsAgoHadBloodPressureCheckedRadio		= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl04_HAQuestionCtrl1_ctl00_rbtList_5");
	private By neverHadBloodPressureCheckedRadio		 			= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl04_HAQuestionCtrl1_ctl00_rbtList_6");
	private By doNotKnowWhenHadBloodPressureCheckedRadio			= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl04_HAQuestionCtrl1_ctl00_rbtList_7");
	private By lessThanAYearAgoHadCholesterolCheckedRadio			= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl05_HAQuestionCtrl1_ctl00_rbtList_0"); 
	private By oneToTwoYearsAgoHadCholesterolCheckedRadio			= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl05_HAQuestionCtrl1_ctl00_rbtList_1");
	private By twoToThreeYearsAgoHadCholesterolCheckedRadio			= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl05_HAQuestionCtrl1_ctl00_rbtList_2");
	private By threeToFourYearsAgoHadCholesterolCheckedRadio		= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl05_HAQuestionCtrl1_ctl00_rbtList_3");
	private By fiveToSixYearsAgoHadCholesterolCheckedRadio			= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl05_HAQuestionCtrl1_ctl00_rbtList_4");
	private By sevenOrMoreYearsAgoHadCholesterolCheckedRadio		= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl05_HAQuestionCtrl1_ctl00_rbtList_5");
	private By neverHadCholesterolCheckedRadio						= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl05_HAQuestionCtrl1_ctl00_rbtList_6");
	private By doNotKnowWhenHadCholesteroleCheckedRadio				= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl05_HAQuestionCtrl1_ctl00_rbtList_7");
	private By plusOneTimeVisitedPhysiciansOfficeIn12MonthsBtn		= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl08_HAQuestionCtrl1_rdS_98_spinnerPlusButton");
	private By minusOneTimeVisitedPhysiciansOfficeIn12MonthsBtn		= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl08_HAQuestionCtrl1_rdS_98_spinnerMinusButton");
	private By plusOneTimeVisitedERIn12MonthsBtn					= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl09_HAQuestionCtrl1_rdS_99_spinnerPlusButton");
	private By minusOneTimeVisitedERIn12MonthsBtn					= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl09_HAQuestionCtrl1_rdS_99_spinnerMinusButton");
	private By plusOneTimeStayedOvernightInHospitalIn12MonthsBtn	= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl10_HAQuestionCtrl1_rdS_100_spinnerPlusButton");
	private By minusOneTimeStayedOvernightInHospitalIn12MonthsBtn= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl10_HAQuestionCtrl1_rdS_100_spinnerMinusButton");
	
	/**
	 * One param class constructor
	 * 
	 * @param driver
	 *            webdriver instance to connect to the page under test
	 */
	public ScreeningsHealthAssessmentPage () {
		super();
		logger.info("Initializing the ScreeningsHealthAssessmentPage Object...");
			// Check that we're on the right page.
		if (!(driver.getTitle().equalsIgnoreCase(TestProperty.CLIENT_PORTAL_TITLE))) {
			logger.error("This is not the Screenings Health Assessment page");
			throw new IllegalStateException("This is not the ScreeningsHealthAssessmentPage page");
		}
		logger.info("Initialized the ScreeningsHealthAssessmentPage page object");
	}

	/**
	 * Sets the users history of flu preventive shots
	 * 
	 * @param lastTimeHadFlueShot
	 *            the choices are less than a year ago/one to two years ago/two
	 *            to three years ago/three to four years ago/ five to six years
	 *            ago/seven or more years ago/never/don't know
	 */
	private void setLastTimeHadFluShot(String lastTimeHadFlueShot) {
		lastTimeHadFlueShot = lastTimeHadFlueShot.trim().toLowerCase();

		switch (lastTimeHadFlueShot) {
		case LESS_THAN_A_YEAR_AGO:
			driver.findElement(lessThanAYearAgoTookFluShotRadio).click();
			break;
		case ONE_TO_TWO_YEARS_AGO:
			driver.findElement(oneToTwoYearsAgoHTookFluShotRadio).click();
			break;
		case TWO_TO_THREE_YEARS_AGO:
			driver.findElement(twoToThreeYearsAgoTookFluShotRadio).click();
			break;
		case THREE_TO_FOUR_YEARS_AGO:
			driver.findElement(threeToFourYearsAgoTookFluShotRadio).click();
			break;
		case FIVE_TO_SIX_YEARS_AGO:
			driver.findElement(fiveToSixYearsAgoHadTookFluShotRadio).click();
			break;
		case SEVEN_OR_MORE_YEARS_AGO:
			driver.findElement(sevenOrMoreYearsAgoTookFluShot).click();
			break;
		case NEVER:
			driver.findElement(neverTookFluShotRadio).click();
			break;
		case DON_T_KNOW:
			driver.findElement(doNotKnowWhenHadFluShotRadio).click();
			break;
		default:
			logger.error("Invalid value for- when did you have the flue shots last was provided.");
			break;
		}
			
	}

	/**
	 * Sets the users history of tetanus preventive shots
	 * 
	 * @param lastTimeHadTetanusShot
	 *            the choices are less than a year ago/one to two years ago/two
	 *            to three years ago/three to four years ago/ five to six years
	 *            ago/seven or more years ago/never/don't know
	 */
	private void setLastTimeHadTetanusShot(String lastTimeHadTetanusShot) {
		lastTimeHadTetanusShot = lastTimeHadTetanusShot.trim().toLowerCase();

		switch (lastTimeHadTetanusShot) {
		case LESS_THAN_A_YEAR_AGO:
			driver.findElement(lessThanAYearAgoTookTetanusShotRadio).click();
			break;
		case ONE_TO_TWO_YEARS_AGO:
			driver.findElement(oneToTwoYearsAgoHTookTetanusShotRadio).click();
			break;
		case TWO_TO_THREE_YEARS_AGO:
			driver.findElement(twoToThreeYearsAgoTookTetanusShotRadio).click();
			break;
		case THREE_TO_FOUR_YEARS_AGO:
			driver.findElement(threeToFourYearsAgoTookTetanusShotRadio).click();
			break;
		case FIVE_TO_SIX_YEARS_AGO:
			driver.findElement(fiveToSixYearsAgoHadTookTetanusShotRadio).click();
			break;
		case SEVEN_OR_MORE_YEARS_AGO:
			driver.findElement(sevenOrMoreYearsAgoTookTetanuShotRadio).click();
			break;
		case NEVER:
			driver.findElement(neverTookTetanusShotRadio).click();
			break;
		case DON_T_KNOW:
			driver.findElement(doNotKnowWhenHadTetanusShotRadio).click();
			break;
		default:
			logger.error("Invalid value for- when did you have the Tetanus shots last was provided.");
			break;
		}

	}

	/**
	 * Sets the users history of blood pressure checkups
	 * 
	 * @param lastTimeHadBloodPressureChecked
	 *            the choices are less than a year ago/one to two years ago/two
	 *            to three years ago/three to four years ago/ five to six years
	 *            ago/seven or more years ago/never/don't know
	 */
	private void setLastTimeHadBloodPressureChecked(String lastTimeHadBloodPressureChecked) {
		lastTimeHadBloodPressureChecked = lastTimeHadBloodPressureChecked.trim().toLowerCase();

		switch (lastTimeHadBloodPressureChecked) {
		case LESS_THAN_A_YEAR_AGO:
			driver.findElement(lessThanAYearAgoHadBloodPressureCheckedRadio).click();
			break;
		case ONE_TO_TWO_YEARS_AGO:
			driver.findElement(oneToTwoYearsAgoHadBloodPressureCheckedRadio).click();
			break;
		case TWO_TO_THREE_YEARS_AGO:
			driver.findElement(twoToThreeYearsAgoHadBloodPressureCheckedRadio).click();
			break;
		case THREE_TO_FOUR_YEARS_AGO:
			driver.findElement(threeToFourYearsAgoHadBloodPressureCheckedRadio).click();
			break;
		case FIVE_TO_SIX_YEARS_AGO:
			driver.findElement(fiveToSixYearsAgoHadBloodPressureCheckedRadio).click();
			break;
		case SEVEN_OR_MORE_YEARS_AGO:
			driver.findElement(sevenOrMoreYearsAgoHadBloodPressureCheckedRadio).click();
			break;
		case NEVER:
			driver.findElement(neverHadBloodPressureCheckedRadio).click();
			break;
		case DON_T_KNOW:
			driver.findElement(doNotKnowWhenHadBloodPressureCheckedRadio).click();
			break;
		default:
			logger.error("Invalid value for- when did you last have blood pressure checked, was provided.");
			break;
		}
	}
	
	/**
	 * Sets the users history of cholesterol checkups
	 * 
	 * @param lastTimeHadCholesterolChecked
	 *            the choices are less than a year ago/one to two years ago/two
	 *            to three years ago/three to four years ago/ five to six years
	 *            ago/seven or more years ago/never/don't know
	 */
	private void setLastTimeHadCholesterolChecked(String lastTimeHadCholesterolChecked) {
		lastTimeHadCholesterolChecked = lastTimeHadCholesterolChecked.trim().toLowerCase();

		switch (lastTimeHadCholesterolChecked) {
		case LESS_THAN_A_YEAR_AGO:
			driver.findElement(lessThanAYearAgoHadCholesterolCheckedRadio).click();
			break;
		case ONE_TO_TWO_YEARS_AGO:
			driver.findElement(oneToTwoYearsAgoHadCholesterolCheckedRadio).click();
			break;
		case TWO_TO_THREE_YEARS_AGO:
			driver.findElement(twoToThreeYearsAgoHadCholesterolCheckedRadio).click();
			break;
		case THREE_TO_FOUR_YEARS_AGO:
			driver.findElement(threeToFourYearsAgoHadCholesterolCheckedRadio).click();
			break;
		case FIVE_TO_SIX_YEARS_AGO:
			driver.findElement(fiveToSixYearsAgoHadCholesterolCheckedRadio).click();
			break;
		case SEVEN_OR_MORE_YEARS_AGO:
			driver.findElement(sevenOrMoreYearsAgoHadCholesterolCheckedRadio).click();
			break;
		case NEVER:
			driver.findElement(neverHadCholesterolCheckedRadio).click();
			break;
		case DON_T_KNOW:
			driver.findElement(doNotKnowWhenHadCholesteroleCheckedRadio).click();
			break;
		default:
			logger.error("Invalid value for- when did you last have Cholesterol checked, was provided.");
			break;
		}
	}

	/**
	 * Sets the number of times the user visited a physicians office/clinic in
	 * the last one year.
	 * 
	 * @param numberOfVisitsToPhysiciansOffice
	 *            number of visits to the Physicians office/clinic in last year
	 *            as a numerical string.
	 */
	private void setNumberOfVisitsToPhysiciansOfficeIn12Months(String numberOfVisitsToPhysiciansOffice) {
		Integer numberOfVisitsToPhysiciansOfficeIn12Months = Integer.parseInt(numberOfVisitsToPhysiciansOffice.trim());

		for (int numberOfAlcoholicBeverageToSet = 0; numberOfAlcoholicBeverageToSet < numberOfVisitsToPhysiciansOfficeIn12Months; numberOfAlcoholicBeverageToSet++) {
			driver.findElement(plusOneTimeVisitedPhysiciansOfficeIn12MonthsBtn).click();
		}
	}

	/**
	 * Sets the number of times the user went to ER in the last one year.
	 * 
	 * @param numberOfVisitsToEROffice
	 *            number of visits to the ER in last year as a numerical string.
	 */
	private void setNumberOfVisitsToERIn12Months(String numberOfVisitsToEROffice) {
		Integer numberOfVisitsToERIn12Months = Integer.parseInt(numberOfVisitsToEROffice.trim());

		for (int numberOfAlcoholicBeverageToSet = 0; numberOfAlcoholicBeverageToSet < numberOfVisitsToERIn12Months; numberOfAlcoholicBeverageToSet++) {
			driver.findElement(plusOneTimeVisitedERIn12MonthsBtn).click();
		}
	}

	/**
	 * Sets the number of times the user stayed overnight in a hospital in the
	 * last one year.
	 * 
	 * @param numberOfTimesStayedOvernightInHospital
	 *            number of times stayed in the ER in last one year as a
	 *            numerical string.
	 */
	private void setNumberOfTimesStayedOvernightInHospital(String numberOfTimesStayedOvernightInHospital) {
		Integer numberOfTimesStayedOvernightInHospitalIn12Months = Integer
				.parseInt(numberOfTimesStayedOvernightInHospital.trim());

		for (int numberOfAlcoholicBeverageToSet = 0; numberOfAlcoholicBeverageToSet < numberOfTimesStayedOvernightInHospitalIn12Months; numberOfAlcoholicBeverageToSet++) {
			driver.findElement(plusOneTimeStayedOvernightInHospitalIn12MonthsBtn).click();
		}
	}

	/**
	 * Completes the answers for all the HA screenings questions.
	 * @param screenignsHAData contains data values read from the data file
	 */
	public void setCompleteScreeningsHA(Map<String, String> screenignsHAData) {

		logger.info("Started to Fill the Screenigns HA page...");
		
		setLastTimeHadFluShot(screenignsHAData.get("lastTimeHadFlueShot"));
		setLastTimeHadTetanusShot(screenignsHAData.get("lastTimehadTetanusShot"));
		setLastTimeHadBloodPressureChecked(screenignsHAData.get("lastTimeHadBloodPressureChecked"));
		setLastTimeHadCholesterolChecked(screenignsHAData.get("lastTimeHadCholesterolChecked"));
		setNumberOfVisitsToPhysiciansOfficeIn12Months(screenignsHAData.get("numberOfTimesVisitedPhysiciansOffice"));
		setNumberOfVisitsToERIn12Months(screenignsHAData.get("numberOfTimesVisitedER"));
		setNumberOfTimesStayedOvernightInHospital(screenignsHAData.get("numberOfTimesStayedOvernightInHospital"));
		
		logger.info("Completed filling the Screenings HA page.");

	}

}
