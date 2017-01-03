package com.lh.pages.authenticated.ha;

import com.lh.testConfig.TestProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import java.util.Map;

import static com.lh.helper.DriverFactory.driver;

public class ConditionsHealthAssessmentPage extends HealthAssessmentPage {

	private static final String NO = "no";
	private static final String YES = "yes";
	private static final String CURRENTLY = "currently";
	private static final String IN_THE_PAST = "in the past";
	private static final String NEVER = "never";

	/**
	 * Logger to log messages
	 */
	private static Logger logger = LogManager.getLogger(com.lh.pages.authenticated.ha.ConditionsHealthAssessmentPage.class);

	private By neverHadAsthmaRadio 									= By
			.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl01_HAQuestionCtrl1_ctl00_rbtList_0");
	private By inThePastHadAsthmaRadio 								= By
			.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl01_HAQuestionCtrl1_ctl00_rbtList_1");
	private By currentlyHaveAsthmaRadio 							= By
			.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl01_HAQuestionCtrl1_ctl00_rbtList_2");
	private By yesCurrentlyTakingMedicationForAsthmaBtn				= By
			.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl02_HAQuestionCtrl1_dvTwoChoiceyesno902_dvTwo']/table/tbody/tr/td[@class=' first-option']/input");
	private By noCurrentlyTakingMedicationForAsthmaBtn				= By
			.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl02_HAQuestionCtrl1_dvTwoChoiceyesno902_dvTwo']/table/tbody/tr/td[@class='second-option']/input");
	private By yesCurrentlyUnderMedicalCareForAsthmaBtn				= By
			.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl03_HAQuestionCtrl1_dvTwoChoiceyesno903_dvTwo']/table/tbody/tr/td[@class=' first-option']/input");
	private By noCurrentlyUnderMedicalCareForAsthmaBtn				= By
			.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl03_HAQuestionCtrl1_dvTwoChoiceyesno903_dvTwo']/table/tbody/tr/td[@class='second-option']/input");

	private By neverHadCancerRadio 									= By
			.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl05_HAQuestionCtrl1_ctl00_rbtList_0");
	private By inThePastHadCancerRadio 								= By
			.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl05_HAQuestionCtrl1_ctl00_rbtList_1");
	private By currentlyHaveCancerRadio 							= By
			.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl05_HAQuestionCtrl1_ctl00_rbtList_2");
	private By yesCurrentlyTakingMedicationForCancerBtn				= By
			.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl06_HAQuestionCtrl1_dvTwoChoiceyesno906_dvTwo']/table/tbody/tr/td[@class=' first-option']/input");
	private By noCurrentlyTakingMedicationForCancerBtn				= By
			.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl06_HAQuestionCtrl1_dvTwoChoiceyesno906_dvTwo']/table/tbody/tr/td[@class='second-option']/input");
	private By yesCurrentlyUnderMedicalCareCancerBtn				= By
			.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl07_HAQuestionCtrl1_dvTwoChoiceyesno907_dvTwo']/table/tbody/tr/td[@class=' first-option']/input");
	private By noCurrentlyUnderMedicalCareCancerBtn					= By
			.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl07_HAQuestionCtrl1_dvTwoChoiceyesno907_dvTwo']/table/tbody/tr/td[@class='second-option']/input");
	
	private By neverHadEmphysemaRadio 								= By
			.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl09_HAQuestionCtrl1_ctl00_rbtList_0");
	private By inThePastHadEmphysemaRadio 							= By
			.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl09_HAQuestionCtrl1_ctl00_rbtList_1");
	private By currentlyHaveEmphysemaRadio 							= By
			.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl09_HAQuestionCtrl1_ctl00_rbtList_2");
	private By yesCurrentlyTakingMedicationForEmphysemaBtn			= By
			.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl10_HAQuestionCtrl1_dvTwoChoiceyesno910_dvTwo']/table/tbody/tr/td[@class=' first-option']/input");
	private By noCurrentlyTakingMedicationForEmphysemaBtn			= By
			.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl10_HAQuestionCtrl1_dvTwoChoiceyesno910_dvTwo']/table/tbody/tr/td[@class='second-option']/input");
	private By yesCurrentlyUnderMedicalCareEmphysemaBtn				= By
			.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl11_HAQuestionCtrl1_dvTwoChoiceyesno911_dvTwo']/table/tbody/tr/td[@class=' first-option']/input");
	private By noCurrentlyUnderMedicalCareEmphysemaBtn				= By
			.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl11_HAQuestionCtrl1_dvTwoChoiceyesno911_dvTwo']/table/tbody/tr/td[@class='second-option']/input");
	
	
	private By neverHadDepressionRadio 								= By
			.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl13_HAQuestionCtrl1_ctl00_rbtList_0");
	private By inThePastHadDepressionRadio 							= By
			.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl13_HAQuestionCtrl1_ctl00_rbtList_1");
	private By currentlyHaveDepressionRadio 						= By
			.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl13_HAQuestionCtrl1_ctl00_rbtList_2");
	private By yesCurrentlyTakingMedicationForDepressionBtn			= By
			.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl14_HAQuestionCtrl1_dvTwoChoiceyesno914_dvTwo']/table/tbody/tr/td[@class=' first-option']/input");
	private By noCurrentlyTakingMedicationForDepressionBtn			= By
			.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl14_HAQuestionCtrl1_dvTwoChoiceyesno914_dvTwo']/table/tbody/tr/td[@class='second-option']/input");
	private By yesCurrentlyUnderMedicalCareDepressionBtn			= By
			.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl15_HAQuestionCtrl1_dvTwoChoiceyesno915_dvTwo']/table/tbody/tr/td[@class=' first-option']/input");
	private By noCurrentlyUnderMedicalCareDepressionBtn				= By
			.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl15_HAQuestionCtrl1_dvTwoChoiceyesno915_dvTwo']/table/tbody/tr/td[@class='second-option']/input");
	

	private By neverHadDiabetesRadio 								= By
			.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl17_HAQuestionCtrl1_ctl00_rbtList_0");
	private By inThePastHadDiabetesRadio 							= By
			.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl17_HAQuestionCtrl1_ctl00_rbtList_1");
	private By currentlyHaveDiabetesRadio 							= By
			.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl17_HAQuestionCtrl1_ctl00_rbtList_2");
	private By yesCurrentlyTakingMedicationForDiabetesBtn			= By
			.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl18_HAQuestionCtrl1_dvTwoChoiceyesno918_dvTwo']/table/tbody/tr/td[@class=' first-option']/input");
	private By noCurrentlyTakingMedicationForDiabetesBtn			= By
			.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl18_HAQuestionCtrl1_dvTwoChoiceyesno918_dvTwo']/table/tbody/tr/td[@class='second-option']/input");
	private By yesCurrentlyUnderMedicalCareDiabetesBtn				= By
			.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl19_HAQuestionCtrl1_dvTwoChoiceyesno919_dvTwo']/table/tbody/tr/td[@class=' first-option']/input");
	private By noCurrentlyUnderMedicalCareDiabetesBtn				= By
			.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl19_HAQuestionCtrl1_dvTwoChoiceyesno919_dvTwo']/table/tbody/tr/td[@class='second-option']/input");
	

	private By neverHadHeartProblemsRadio 							= By
			.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl21_HAQuestionCtrl1_ctl00_rbtList_0");
	private By inThePastHadHeartProblemsRadio						= By
			.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl21_HAQuestionCtrl1_ctl00_rbtList_1");
	private By currentlyHaveHeartProblemsRadio 						= By
			.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl21_HAQuestionCtrl1_ctl00_rbtList_2");
	private By yesCurrentlyTakingMedicationForHeartProblemsBtn		= By
			.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl22_HAQuestionCtrl1_dvTwoChoiceyesno922_dvTwo']/table/tbody/tr/td[@class=' first-option']/input");
	private By noCurrentlyTakingMedicationForHeartProblemsBtn		= By
			.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl22_HAQuestionCtrl1_dvTwoChoiceyesno922_dvTwo']/table/tbody/tr/td[@class='second-option']/input");
	private By yesCurrentlyUnderMedicalCareHeartProblemsBtn			= By
			.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl23_HAQuestionCtrl1_dvTwoChoiceyesno923_dvTwo']/table/tbody/tr/td[@class=' first-option']/input");
	private By noCurrentlyUnderMedicalCareHeartProblemsBtn			= By
			.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl23_HAQuestionCtrl1_dvTwoChoiceyesno923_dvTwo']/table/tbody/tr/td[@class='second-option']/input");
	

	private By neverHadBloodPressureRadio 							= By
			.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl25_HAQuestionCtrl1_ctl00_rbtList_0");
	private By inThePastHadBloodPressureRadio 						= By
			.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl25_HAQuestionCtrl1_ctl00_rbtList_1");
	private By currentlyHaveBloodPressureRadio 						= By
			.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl25_HAQuestionCtrl1_ctl00_rbtList_2");
	private By yesCurrentlyTakingMedicationForBloodPressureBtn		= By
			.xpath(".//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl26_HAQuestionCtrl1_dvTwoChoiceyesno926_dvTwo']/table/tbody/tr/td[@class=' first-option']/input");
	private By noCurrentlyTakingMedicationForBloodPressureBtn		= By
			.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl26_HAQuestionCtrl1_dvTwoChoiceyesno926_dvTwo']/table/tbody/tr/td[@class='second-option']/input");
	private By yesCurrentlyUnderMedicalCareBloodPressureBtn			= By
			.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl27_HAQuestionCtrl1_dvTwoChoiceyesno927_dvTwo']/table/tbody/tr/td[@class=' first-option']/input");
	private By noCurrentlyUnderMedicalCareBloodPressureBtn			= By
			.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl27_HAQuestionCtrl1_dvTwoChoiceyesno927_dvTwo']/table/tbody/tr/td[@class='second-option']/input");
	

	private By neverHadHighCholesterolRadio 						= By
			.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl29_HAQuestionCtrl1_ctl00_rbtList_0");
	private By inThePastHadHighCholesterolRadio 					= By
			.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl29_HAQuestionCtrl1_ctl00_rbtList_1");
	private By currentlyHaveHighCholesterolRadio 					= By
			.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl29_HAQuestionCtrl1_ctl00_rbtList_2");
	private By yesCurrentlyTakingMedicationForHighCholesterolBtn	= By
			.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl30_HAQuestionCtrl1_dvTwoChoiceyesno930_dvTwo']/table/tbody/tr/td[@class=' first-option']/input");
	private By noCurrentlyTakingMedicationForHighCholesterolBtn		= By
			.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl30_HAQuestionCtrl1_dvTwoChoiceyesno930_dvTwo']/table/tbody/tr/td[@class='second-option']/input");
	private By yesCurrentlyUnderMedicalCareHighCholesterolBtn		= By
			.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl31_HAQuestionCtrl1_dvTwoChoiceyesno931_dvTwo']/table/tbody/tr/td[@class=' first-option']/input");
	private By noCurrentlyUnderMedicalCareHighCholesterolBtn		= By
			.xpath(".//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl31_HAQuestionCtrl1_dvTwoChoiceyesno931_dvTwo']/table/tbody/tr/td[@class='second-option']/input");
	
	private By neverHadStrokeRadio 									= By
			.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl33_HAQuestionCtrl1_ctl00_rbtList_0");
	private By inThePastHadStrokeRadio 								= By
			.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl33_HAQuestionCtrl1_ctl00_rbtList_1");
	private By currentlyHaveStrokeRadio 							= By
			.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl33_HAQuestionCtrl1_ctl00_rbtList_2");
	private By yesCurrentlyTakingMedicationForStrokeBtn				= By
			.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl34_HAQuestionCtrl1_dvTwoChoiceyesno934_dvTwo']/table/tbody/tr/td[@class=' first-option']/input");
	private By noCurrentlyTakingMedicationForStrokeBtn				= By
			.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl34_HAQuestionCtrl1_dvTwoChoiceyesno934_dvTwo']/table/tbody/tr/td[@class='second-option']/input");
	private By yesCurrentlyUnderMedicalCareStrokeBtn				= By
			.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl35_HAQuestionCtrl1_dvTwoChoiceyesno935_dvTwo']/table/tbody/tr/td[@class=' first-option']/input");
	private By noCurrentlyUnderMedicalCareStrokeBtn					= By
			.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl35_HAQuestionCtrl1_dvTwoChoiceyesno935_dvTwo']/table/tbody/tr/td[@class='second-option']/input");

	/**
	 * One param class constructor
	 * 
	 * @param driver
	 *            webdriver instance to connect to the page under test
	 */
	public ConditionsHealthAssessmentPage() {
		super();
		logger.info("Initializing the ConditionsHealthAssessmentPage Object...");
		// Check that we're on the right page.
		if (!(driver.getTitle().equalsIgnoreCase(TestProperty.CLIENT_PORTAL_TITLE))) {
			logger.error("This is not the Conditions Health Assessment page");
			throw new IllegalStateException("This is not the ConditionsHealthAssessmentPage page");
		}
		logger.info("Initialized the ConditionsHealthAssessmentPage page object");
	}
	
	/**
	 * Selects the Yes/No button for taking medication for the current condition
	 * 
	 * @param answerForTakingMedication
	 *            users input yes/no
	 * @param yesTakingmedication
	 *            for selecting option yes
	 * @param noTakingMedication
	 *            for selection option no
	 */
	private void setTakingMedication(String answerForTakingMedication, By yesTakingmedication, By noTakingMedication) {
		if (YES.equals(answerForTakingMedication.trim().toLowerCase())) {
			driver.findElement(yesTakingmedication).click();
		} else if (NO.equals(answerForTakingMedication.trim().toLowerCase())) {
			driver.findElement(noTakingMedication).click();
		} else {
			logger.error("Invalid value for - are you currently taking medication, was provided.");
		}
	}

	/**
	 * Selects the Yes/No button for being under medical care for the current
	 * condition
	 * 
	 * @param answerForUnderMedicalCare
	 *            users input yes/no
	 * @param yesUnderMedicalCare
	 *            for selecting option yes
	 * @param noUnderMedicalCare
	 *            for selection option no
	 */
	private void setUnderMedicalCare(String answerForUnderMedicalCare, By yesUnderMedicalCare, By noUnderMedicalCare) {
		if (YES.equals(answerForUnderMedicalCare.trim().toLowerCase())) {
			driver.findElement(yesUnderMedicalCare).click();
		} else if (NO.equals(answerForUnderMedicalCare.trim().toLowerCase())) {
			driver.findElement(noUnderMedicalCare).click();
		} else {
			logger.error("Invalid value for - are you currently under medical care, was provided.");
		}
	}

	/**
	 * Sets the users history of Asthma
	 * 
	 * @param haveYouEverHadAsthma
	 *            defines the users Asthma history as - never/in the
	 *            past/currently
	 *
	 * @param takingMedication
	 *            yes/no
	 * @param underMedicalCare
	 *            yes/no
	 */
	private void setHistoryOfAsthama(String haveYouEverHadAsthma, String takingMedication, String underMedicalCare) {
		haveYouEverHadAsthma = haveYouEverHadAsthma.trim().toLowerCase();

		switch (haveYouEverHadAsthma) {
		case NEVER:
			driver.findElement(neverHadAsthmaRadio).click();
			break;
		case IN_THE_PAST:
			driver.findElement(inThePastHadAsthmaRadio).click();
			break;
		case CURRENTLY:
			driver.findElement(currentlyHaveAsthmaRadio).click();
			setTakingMedication(takingMedication, yesCurrentlyTakingMedicationForAsthmaBtn,
					noCurrentlyTakingMedicationForAsthmaBtn);
			setUnderMedicalCare(underMedicalCare, yesCurrentlyUnderMedicalCareForAsthmaBtn,
					noCurrentlyUnderMedicalCareForAsthmaBtn);
			break;
		default:
			logger.error("Invalid value for - Have you ever had asthma, was provided.");
			break;
		}
	}

	/**
	 * Sets the users history of Cancer
	 * 
	 * @param haveYouEverHadCancer
	 *            defines the users cancer history as - never/in the
	 *            past/currently
	 * @param takingMedication
	 *            yes/no
	 * @param underMedicalCare
	 *            yes/no
	 */
	private void setHistoryOfCancer(String haveYouEverHadCancer, String takingMedication, String underMedicalCare) {
		haveYouEverHadCancer = haveYouEverHadCancer.trim().toLowerCase();

		switch (haveYouEverHadCancer) {
		case NEVER:
			driver.findElement(neverHadCancerRadio).click();
			break;
		case IN_THE_PAST:
			driver.findElement(inThePastHadCancerRadio).click();
			break;
		case CURRENTLY:
			driver.findElement(currentlyHaveCancerRadio).click();
			setTakingMedication(takingMedication, yesCurrentlyTakingMedicationForCancerBtn,
					noCurrentlyTakingMedicationForCancerBtn);
			setUnderMedicalCare(underMedicalCare, yesCurrentlyUnderMedicalCareCancerBtn,
					noCurrentlyUnderMedicalCareCancerBtn);
			break;
		default:
			logger.error("Invalid value for - Have you ever had cancer, was provided.");
			break;
		}
	}

	/**
	 * Sets the users history of Chronic Bronchitis/Emphysema
	 * 
	 * @param haveYouEverHadEmphysema
	 *            defines the users Chronic Bronchitis/Emphysema history as -
	 *            never/in the past/currently
	 * @param takingMedication
	 *            yes/no
	 * @param underMedicalCare
	 *            yes/no
	 */
	private void setHistoryOfEmphysema(String haveYouEverHadEmphysema, String takingMedication, String underMedicalCare) {
		haveYouEverHadEmphysema = haveYouEverHadEmphysema.trim().toLowerCase();

		switch (haveYouEverHadEmphysema) {
		case NEVER:
			driver.findElement(neverHadEmphysemaRadio).click();
			break;
		case IN_THE_PAST:
			driver.findElement(inThePastHadEmphysemaRadio).click();
			break;
		case CURRENTLY:
			driver.findElement(currentlyHaveEmphysemaRadio).click();
			setTakingMedication(takingMedication, yesCurrentlyTakingMedicationForEmphysemaBtn,
					noCurrentlyTakingMedicationForEmphysemaBtn);
			setUnderMedicalCare(underMedicalCare, yesCurrentlyUnderMedicalCareEmphysemaBtn,
					noCurrentlyUnderMedicalCareEmphysemaBtn);
			break;
		default:
			logger.error("Invalid value for - Have you ever had Chronic Bronchitis/Emphysema, was provided.");
			break;
		}
	}

	/**
	 * Sets the users history of depression
	 * 
	 * @param haveYouEverHadDepression
	 *            defines the user's depression history as - never/in the
	 *            past/currently
	 * @param takingMedication
	 *            yes/no
	 * @param underMedicalCare
	 *            yes/no
	 */
	private void setHistoryOfDepression(String haveYouEverHadDepression, String takingMedication, String underMedicalCare) {
		haveYouEverHadDepression = haveYouEverHadDepression.trim().toLowerCase();

		switch (haveYouEverHadDepression) {
		case NEVER:
			driver.findElement(neverHadDepressionRadio).click();
			break;
		case IN_THE_PAST:
			driver.findElement(inThePastHadDepressionRadio).click();
			break;
		case CURRENTLY:
			driver.findElement(currentlyHaveDepressionRadio).click();
			setTakingMedication(takingMedication, yesCurrentlyTakingMedicationForDepressionBtn,
					noCurrentlyTakingMedicationForDepressionBtn);
			setUnderMedicalCare(underMedicalCare, yesCurrentlyUnderMedicalCareDepressionBtn,
					noCurrentlyUnderMedicalCareDepressionBtn);
			break;
		default:
			logger.error("Invalid value for - Have you ever had depression, was provided.");
			break;
		}
	}

	/**
	 * Sets the users history of Diabetes
	 * 
	 * @param haveYouEverHadDiabetes
	 *            defines the user's diabetes history as - never/in the
	 *            past/currently
	 * @param takingMedication
	 *            yes/no
	 * @param underMedicalCare
	 *            yes/no
	 */
	private void setHistoryOfDiabetes(String haveYouEverHadDiabetes, String takingMedication, String underMedicalCare) {
		haveYouEverHadDiabetes = haveYouEverHadDiabetes.trim().toLowerCase();

		switch (haveYouEverHadDiabetes) {
		case NEVER:
			driver.findElement(neverHadDiabetesRadio).click();
			break;
		case IN_THE_PAST:
			driver.findElement(inThePastHadDiabetesRadio).click();
			break;
		case CURRENTLY:
			driver.findElement(currentlyHaveDiabetesRadio).click();
			setTakingMedication(takingMedication, yesCurrentlyTakingMedicationForDiabetesBtn,
					noCurrentlyTakingMedicationForDiabetesBtn);
			setUnderMedicalCare(underMedicalCare, yesCurrentlyUnderMedicalCareDiabetesBtn,
					noCurrentlyUnderMedicalCareDiabetesBtn);
			break;
		default:
			logger.error("Invalid value for - Have you ever had diabetes, was provided.");
			break;
		}
	}

	/**
	 * Sets the users history of heart problems
	 * 
	 * @param haveYouEverHadHeartProblems
	 *            defines the user's heart problems history as - never/in the
	 *            past/currently
	 * @param takingMedication
	 *            yes/no
	 * @param underMedicalCare
	 *            yes/no
	 */

	private void setHistoryOfHeartProblems(String haveYouEverHadHeartProblems, String takingMedication,
			String underMedicalCare) {
		haveYouEverHadHeartProblems = haveYouEverHadHeartProblems.trim().toLowerCase();

		switch (haveYouEverHadHeartProblems) {
		case NEVER:
			driver.findElement(neverHadHeartProblemsRadio).click();
			break;
		case IN_THE_PAST:
			driver.findElement(inThePastHadHeartProblemsRadio).click();
			break;
		case CURRENTLY:
			driver.findElement(currentlyHaveHeartProblemsRadio).click();
			setTakingMedication(takingMedication, yesCurrentlyTakingMedicationForHeartProblemsBtn,
					noCurrentlyTakingMedicationForHeartProblemsBtn);
			setUnderMedicalCare(underMedicalCare, yesCurrentlyUnderMedicalCareHeartProblemsBtn,
					noCurrentlyUnderMedicalCareHeartProblemsBtn);
			break;
		default:
			logger.error("Invalid value for - Have you ever had Heart Problems, was provided.");
			break;
		}
	}

	/**
	 * Sets the users high blood pressure history
	 * 
	 * @param haveYouEverHadHighBloodPressure
	 *            defines the user's high blood pressure history as - never/in
	 *            the past/currently
	 * @param takingMedication
	 *            yes/no
	 * @param underMedicalCare
	 *            yes/no
	 */
	private void setHistoryOfHighBloodPressure(String haveYouEverHadHighBloodPressure, String takingMedication,
			String underMedicalCare) {
		haveYouEverHadHighBloodPressure = haveYouEverHadHighBloodPressure.trim().toLowerCase();

		switch (haveYouEverHadHighBloodPressure) {
		case NEVER:
			driver.findElement(neverHadBloodPressureRadio).click();
			break;
		case IN_THE_PAST:
			driver.findElement(inThePastHadBloodPressureRadio).click();
			break;
		case CURRENTLY:
			driver.findElement(currentlyHaveBloodPressureRadio).click();
			setTakingMedication(takingMedication, yesCurrentlyTakingMedicationForBloodPressureBtn,
					noCurrentlyTakingMedicationForBloodPressureBtn);
			setUnderMedicalCare(underMedicalCare, yesCurrentlyUnderMedicalCareBloodPressureBtn,
					noCurrentlyUnderMedicalCareBloodPressureBtn);
			break;
		default:
			logger.error("Invalid value for - Have you ever had high blood pressure, was provided.");
			break;
		}
	}

	/**
	 * Sets the users high Cholesterol history.
	 * 
	 * @param haveYouEverHadHighCholesterol
	 *            defines the user's high cholesterol history as - never/in the
	 *            past/currently
	 * @param takingMedication
	 *            yes/no
	 * @param underMedicalCare
	 *            yes/no
	 */
	private void setHistoryOfHighholesterolCholesterol(String haveYouEverHadHighCholesterol, String takingMedication,
			String underMedicalCare) {
		haveYouEverHadHighCholesterol = haveYouEverHadHighCholesterol.trim().toLowerCase();

		switch (haveYouEverHadHighCholesterol) {
		case NEVER:
			driver.findElement(neverHadHighCholesterolRadio).click();
			break;
		case IN_THE_PAST:
			driver.findElement(inThePastHadHighCholesterolRadio).click();
			break;
		case CURRENTLY:
			driver.findElement(currentlyHaveHighCholesterolRadio).click();
			setTakingMedication(takingMedication, yesCurrentlyTakingMedicationForHighCholesterolBtn,
					noCurrentlyTakingMedicationForHighCholesterolBtn);
			setUnderMedicalCare(underMedicalCare, yesCurrentlyUnderMedicalCareHighCholesterolBtn,
					noCurrentlyUnderMedicalCareHighCholesterolBtn);
			break;
		default:
			logger.error("Invalid value for - Have you ever had high Cholesterol, was provided.");
			break;
		}
	}

	/**
	 * Sets the users high stroke history.
	 * 
	 * @param haveYouEverHadStroke
	 *            defines the user's stroke history as - never/in the
	 *            past/currently
	 * @param takingMedication
	 *            yes/no
	 * @param underMedicalCare
	 *            yes/no
	 */
	public void setHistoryOfStroke(String haveYouEverHadStroke, String takingMedication, String underMedicalCare) {
		haveYouEverHadStroke = haveYouEverHadStroke.trim().toLowerCase();

		switch (haveYouEverHadStroke) {
		case NEVER:
			driver.findElement(neverHadStrokeRadio).click();
			break;
		case IN_THE_PAST:
			driver.findElement(inThePastHadStrokeRadio).click();
			break;
		case CURRENTLY:
			driver.findElement(currentlyHaveStrokeRadio).click();
			setTakingMedication(takingMedication, yesCurrentlyTakingMedicationForStrokeBtn,
					noCurrentlyTakingMedicationForStrokeBtn);
			setUnderMedicalCare(underMedicalCare, yesCurrentlyUnderMedicalCareStrokeBtn,
					noCurrentlyUnderMedicalCareStrokeBtn);
			break;
		default:
			logger.error("Invalid value for - Have you ever had a stroke, was provided.");
			break;
		}
	}
	
	public void completeConditionHA (Map<String, String> conditionsHAData){
		logger.info("Starting to fill data in the Conditions Page...");
		setHistoryOfAsthama(conditionsHAData.get("haveYouEverHadAsthma"),
				conditionsHAData.get("areYouTakingMedicationForAsthma"), conditionsHAData.get("areYouUnderMedicalCareForAsthma"));
		
		setHistoryOfCancer(conditionsHAData.get("haveYouEverHadCancer"),
				conditionsHAData.get("areYouTakingMedicationForCancer"), conditionsHAData.get("areYouUnderMedicalCareForCancer"));
		
		setHistoryOfEmphysema(conditionsHAData.get("haveYouEverHadEmphysema"),
				conditionsHAData.get("areYouTakingMedicationForEmphysema"), conditionsHAData.get("areYouUnderMedicalCareForEmphysema"));
		
		setHistoryOfDepression(conditionsHAData.get("haveYouEverHadDepression"),
				conditionsHAData.get("areYouTakingMedicationForDepression"), conditionsHAData.get("areYouUnderMedicalCareForDepression"));
		
		setHistoryOfDiabetes(conditionsHAData.get("haveYouEverHadDiabetes"),
				conditionsHAData.get("areYouTakingMedicationForDiabetes"), conditionsHAData.get("areYouUnderMedicalCareForDiabetes"));
		
		setHistoryOfHeartProblems(conditionsHAData.get("haveYouEverHadHeartProblems"),
				conditionsHAData.get("areYouTakingMedicationForHeartProblems"),
				conditionsHAData.get("areYouUnderMedicalCareForHeartProblems"));
		
		setHistoryOfHighBloodPressure(conditionsHAData.get("haveYouEverHadHighBloodPressure"),
				conditionsHAData.get("areYouTakingMedicationForHighBloodPressure"),
				conditionsHAData.get("areYouUnderMedicalCareForHighBloodPressure"));
		
		setHistoryOfHighholesterolCholesterol(
				conditionsHAData.get("haveYouEverHadHighCholesterol"), conditionsHAData.get("areYouTakingMedicationForHighCholesterol"),
				conditionsHAData.get("areYouUnderMedicalCareForHighCholesterol"));
		
		setHistoryOfStroke(conditionsHAData.get("haveYouEverHadStroke"),
				conditionsHAData.get("areYouTakingMedicationForStroke"), conditionsHAData.get("areYouUnderMedicalCareForStroke"));
		
		logger.info("Completed filling data in the Conditions Page.");
	}
}
