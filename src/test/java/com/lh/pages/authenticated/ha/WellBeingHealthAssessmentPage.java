package com.lh.pages.authenticated.ha;

import java.util.Map;
import static com.lh.helper.DriverFactory.driver;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.testng.Reporter;

import com.lh.testConfig.TestProperty;

/**
 * <h2>All the methods for Well Being HA page are defined here</h2>
 * <p>
 * 
 * @author amita.arya
 * @version 1.0
 * @since 2015-01-30
 */
public class WellBeingHealthAssessmentPage extends HealthAssessmentPage {

	private static final String LESS_THAN_80 				= "Less than 80";
	private static final String EIGHTY 						= "80";
	private static final String NINETY 						= "90";
	private static final String ONE_HUNDRED 				= "100";
	private static final String NO 							= "no";
	private static final String YES_ONE 					= "yes, one";
	private static final String YES_TWO_OR_MORE 			= "yes, two or more";
	private static final String WEAKER_THAN_AVERAGE 		= "weaker than average";
	private static final String ABOUT_AVERAGE 				= "about average";
	private static final String VERY_STRONG 				= "very strong";
	private static final String NOT_SATISFIED 				= "not satisfied";
	private static final String PARTLY_SATISFIED 			= "partly satisfied";
	private static final String MOSTLY_SATISFIED 			= "mostly satisfied";
	private static final String COMPLETELY_SATISFIED 		= "completely satisfied";
	private static final String NONE 						= "none";
	private static final String HARDLY_ANY 					= "hardly any";
	private static final String SOME 						= "some";
	private static final String A_LOT 						= "a lot";
	private static final String RARELY 						= "rarely";
	private static final String SOMETIMES 					= "sometimes";
	private static final String OFTEN 						= "often";
	private static final String DOES_NOT_APPLY 				= "DOES NOT APPLY";
	private static final String DISAGREE_STRONGLY 			= "DISAGREE STRONGLY";
	private static final String DISAGREE 					= "DISAGREE";
	private static final String AGREE 						= "AGREE";
	private static final String AGREE_STRONGLY 				= "AGREE STRONGLY";
	private static Logger logger 							= LogManager.getLogger(com.lh.pages.authenticated.ha.WellBeingHealthAssessmentPage.class);
	private By oftenFeelAnxiousDepressedRadio 				= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl00_HAQuestionCtrl1_ctl00_rbtList_0");
	private By sometimesFeelAnxiousDepressedRadio 			= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl00_HAQuestionCtrl1_ctl00_rbtList_1");
	private By rarelyFeelAnxiousDepressedRadio 				= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl00_HAQuestionCtrl1_ctl00_rbtList_2");
	private By neverFeelAnxiousDepressedRadio 				= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl00_HAQuestionCtrl1_ctl00_rbtList_3");
	private By aLotEffectStessHadOnHealthRadio 				= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl01_HAQuestionCtrl1_ctl00_rbtList_0");
	private By someEffectStessHadOnHealthRadio 				= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl01_HAQuestionCtrl1_ctl00_rbtList_1");
	private By hardlyAnyEffectStessHadOnHealthRadio 		= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl01_HAQuestionCtrl1_ctl00_rbtList_2");
	private By noneEffectStessHadOnHealthRadio 				= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl01_HAQuestionCtrl1_ctl00_rbtList_3");
	private By plusOneHrofSleepBtn							= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl02_HAQuestionCtrl1_rdS_72_spinnerPlusButton");
	private By minusOneHrofSleepBtn 						= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl02_HAQuestionCtrl1_rdS_72_spinnerMinusButton");
	private By compeletelySatisfiedWithPersonalLifeRadio 	= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl03_HAQuestionCtrl1_ctl00_rbtList_0");
	private By mostlySatisfiedWithPersonalLifeRadio 		= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl03_HAQuestionCtrl1_ctl00_rbtList_1");
	private By partlySatisfiedWithPersonalLifeRadio 		= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl03_HAQuestionCtrl1_ctl00_rbtList_2");
	private By notSatisfiedWithPersonalLifeRadio 			= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl03_HAQuestionCtrl1_ctl00_rbtList_3");
	private By veryStrongSocialTiesRadio 					= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl04_HAQuestionCtrl1_ctl00_rbtList_0");
	private By aboutAverageSocialTiesRadio 					= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl04_HAQuestionCtrl1_ctl00_rbtList_1");
	private By weakerThanAverageSocialTiesRadio 			= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl04_HAQuestionCtrl1_ctl00_rbtList_2");
	private By twoOrMoreSeriousLossesInPastYearRadio 		= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl05_HAQuestionCtrl1_ctl00_rbtList_0");
	private By oneSeriousLossinPastYearRadio 				= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl05_HAQuestionCtrl1_ctl00_rbtList_1");
	private By noSeriousLossInPastYearRadio 				= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl05_HAQuestionCtrl1_ctl00_rbtList_2");
	private By oneHunderedPercentTimeBuckleSafetyBeltRadio 	= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl06_HAQuestionCtrl1_ctl00_rbtList_0");
	private By ninetyPercentTimeBuckleSafetyBeltRadio 		= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl06_HAQuestionCtrl1_ctl00_rbtList_1");
	private By eightyPercentTimeBuckleSafetyBeltRadio 		= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl06_HAQuestionCtrl1_ctl00_rbtList_2");
	private By lessThanEightyPercentTimeBuckleSafetyBeltRadio= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl06_HAQuestionCtrl1_ctl00_rbtList_3");
	private By plusOneMissedWorkDueToHealthBtn 				= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl07_HAQuestionCtrl1_rdS_77_spinnerPlusButton");
	private By minusOneMissedWorkDueToHealthBtn 			= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl07_HAQuestionCtrl1_rdS_77_spinnerMinusButton");
	private By agreeStronglyWithJobSatisfactionBtn 			= By.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl08_HAQuestionCtrl1_rdQ_78_AnswersContainer']/div/div[1]");
	private By agreeWithJobSatisfactionBtn 					= By.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl08_HAQuestionCtrl1_rdQ_78_AnswersContainer']/div/div[2]");
	private By disagreeWithJobSatisfactionBtn 				= By.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl08_HAQuestionCtrl1_rdQ_78_AnswersContainer']/div/div[3]");
	private By disagreeStronglyWithJobSatisfactionBtn 		= By.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl08_HAQuestionCtrl1_rdQ_78_AnswersContainer']/div/div[4]");
	private By doesNotApplyWithJobSatisfactionBtn 			= By.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl08_HAQuestionCtrl1_rdQ_78_AnswersContainer']/div/div[5]");
	private By planToManageStressBetterNoPresentInterestBtn = By.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl09_HAQuestionCtrl1_rdQ_79_AnswersContainer']/div/div[1]");
	private By planToManageStressBetterPlanToChangeIn6MonthsBtn = By.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl09_HAQuestionCtrl1_rdQ_79_AnswersContainer']/div/div[2]");
	private By planToManageStressBetterPlanToChangeThisMonthBtn = By.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl09_HAQuestionCtrl1_rdQ_79_AnswersContainer']/div/div[3]");
	private By planToManageStressBetterRecentlyStartedBtn 	= By.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl09_HAQuestionCtrl1_rdQ_79_AnswersContainer']/div/div[4]");
	private By planToManageStressBetterAlreadyDoingThisBtn 	= By.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl09_HAQuestionCtrl1_rdQ_79_AnswersContainer']/div/div[5]");

	/**
	 * One param class constructor
	 * 
	 * @param driver
	 *            webdriver instance to connect to the page under test
	 */
	public WellBeingHealthAssessmentPage() {
		super();
		logger.info("Initializing the WellBeingHealthAssessmentPage Object...");
		// Check that we're on the right page.
		if (!(driver.getTitle().equalsIgnoreCase(TestProperty.CLIENT_PORTAL_TITLE))) {
			logger.error("This is not the Well Being Health Assessment page");
			throw new IllegalStateException("This is not the WellBeingHealthAssessmentPage page");
		}
		logger.info("Initialized the WellBeingHealthAssessmentPage page object");
	}

	/**
	 * Sets the frequency of the the user feeling tense,anxious or depressed
	 * 
	 * @param howOftenFeelAnxiousOrDepressed
	 *            takes values often/sometimes/rarely
	 */
	private void setHowOftenFeelAnxiousOrDepressed(String howOftenFeelAnxiousOrDepressed) {
		howOftenFeelAnxiousOrDepressed = howOftenFeelAnxiousOrDepressed.trim().toLowerCase();

		switch (howOftenFeelAnxiousOrDepressed) {
		case OFTEN:
			driver.findElement(oftenFeelAnxiousDepressedRadio).click();
			break;
		case SOMETIMES:
			driver.findElement(sometimesFeelAnxiousDepressedRadio).click();
			break;
		case RARELY:
			driver.findElement(rarelyFeelAnxiousDepressedRadio).click();
			break;
		default:
			logger.error("Invalid value for - how often do you feel tense, anxious or depressed was provided.");
			break;
		}
	}

	/**
	 * Sets the effect of Stress on users health
	 * 
	 * @param effectOfStressOnHealth
	 *            takes values a lot/some/hardly any/none
	 */
	private void setEffectOfStressOnHealth(String effectOfStressOnHealth) {
		effectOfStressOnHealth = effectOfStressOnHealth.trim().toLowerCase();

		switch (effectOfStressOnHealth) {
		case A_LOT:
			driver.findElement(aLotEffectStessHadOnHealthRadio).click();
			break;
		case SOME:
			driver.findElement(someEffectStessHadOnHealthRadio).click();
			break;
		case HARDLY_ANY:
			driver.findElement(hardlyAnyEffectStessHadOnHealthRadio).click();
			break;
		case NONE:
			driver.findElement(noneEffectStessHadOnHealthRadio).click();
			break;
		default:
			logger.error("Invalid value for - how much effect has stress had on your health , anxious or depressed was provided.");
			break;
		}
	}

	/**
	 * Sets the number of hours of sleep the user gets on an average
	 * 
	 * @param howManyHrsOfSleepAtNight
	 *            numerical string e.g. 0, 1...
	 */
	private void setHowManyHrsOfSleepAtNight(String howManyHrsOfSleepAtNight) {
		Integer numberHrsOfSleepAtNight = Integer.parseInt(howManyHrsOfSleepAtNight.trim());

		for (int numberHrsOfSleepAtNightToSet = 0; numberHrsOfSleepAtNightToSet < numberHrsOfSleepAtNight; numberHrsOfSleepAtNightToSet++) {
			driver.findElement(plusOneHrofSleepBtn).click();
		}
	}

	/**
	 * Sets the value for how satisfied is the user with his/her life
	 * 
	 * @param howSatisfiedAreYouWithLife
	 *            takes values completely satisfied/mostly satisfied/partly
	 *            satisfied/not satisfied
	 */
	private void setHowSatisfiedAreYouWithLife(String howSatisfiedAreYouWithLife) {
		howSatisfiedAreYouWithLife = howSatisfiedAreYouWithLife.trim().toLowerCase();

		switch (howSatisfiedAreYouWithLife) {
		case COMPLETELY_SATISFIED:
			driver.findElement(compeletelySatisfiedWithPersonalLifeRadio).click();
			break;
		case MOSTLY_SATISFIED:
			driver.findElement(mostlySatisfiedWithPersonalLifeRadio).click();
			break;
		case PARTLY_SATISFIED:
			driver.findElement(partlySatisfiedWithPersonalLifeRadio).click();
			break;
		case NOT_SATISFIED:
			driver.findElement(notSatisfiedWithPersonalLifeRadio).click();
			break;
		default:
			logger.error("Invalid value for - how satisfied are you with your life, anxious or depressed was provided.");
			break;
		}
	}

	/**
	 * Sets how strong are users social ties with family and/or friends
	 * 
	 * @param howStrongSocialTies
	 *            takes values very strong/about average/weaker than average
	 */
	private void setHowStrongSocialTies(String howStrongSocialTies) {
		howStrongSocialTies = howStrongSocialTies.trim().toLowerCase();

		switch (howStrongSocialTies) {
		case VERY_STRONG:
			driver.findElement(veryStrongSocialTiesRadio).click();
			break;
		case ABOUT_AVERAGE:
			driver.findElement(aboutAverageSocialTiesRadio).click();
			break;
		case WEAKER_THAN_AVERAGE:
			driver.findElement(weakerThanAverageSocialTiesRadio).click();
			break;
		default:
			logger.error("Invalid value for - how strong are your social ties with your family and/or friends, anxious or depressed was provided.");
			break;
		}
	}

	/**
	 * 
	 * @param sufferedPersonalLossOrMisfortune
	 *            takes values Yes, two or more/Yes, one/No
	 */
	private void setSufferedPersonalLossOrMisfortune(String sufferedPersonalLossOrMisfortune) {
		sufferedPersonalLossOrMisfortune = sufferedPersonalLossOrMisfortune.trim().toLowerCase();

		switch (sufferedPersonalLossOrMisfortune) {
		case YES_TWO_OR_MORE:
			driver.findElement(twoOrMoreSeriousLossesInPastYearRadio).click();
			break;
		case YES_ONE:
			driver.findElement(oneSeriousLossinPastYearRadio).click();
			break;
		case NO:
			driver.findElement(noSeriousLossInPastYearRadio).click();
			break;
		default:
			logger.error("Invalid value for - Have you suffered a personal loss or misfortune in the past year, anxious or depressed was provided.");
			break;
		}
	}

	/**
	 * Sets percent of the time the user usually buckles safety belt while
	 * driving
	 * 
	 * @param percentOfTimeUsuallyBuckleSafetyBelt
	 *            takes sting values 100/90/80/Less than 80
	 */
	private void setPercentOfTimeUsuallyBuckleSafetyBelt(String percentOfTimeUsuallyBuckleSafetyBelt) {
		percentOfTimeUsuallyBuckleSafetyBelt = percentOfTimeUsuallyBuckleSafetyBelt.trim().toLowerCase();

		switch (percentOfTimeUsuallyBuckleSafetyBelt) {
		case ONE_HUNDRED:
			driver.findElement(oneHunderedPercentTimeBuckleSafetyBeltRadio).click();
			break;
		case NINETY:
			driver.findElement(ninetyPercentTimeBuckleSafetyBeltRadio).click();
			break;
		case EIGHTY:
			driver.findElement(eightyPercentTimeBuckleSafetyBeltRadio).click();
			break;
		case LESS_THAN_80:
			driver.findElement(lessThanEightyPercentTimeBuckleSafetyBeltRadio).click();
			break;
		default:
			logger.error("Invalid value for -  percent of the time do you usually buckle your safety belt while driving or riding in a motorized vehicle was provided.");
			break;
		}
	}

	/**
	 * Sets number of days the user missed work due to personal illness
	 * 
	 * @param daysOfWorkMissedDueToIllness
	 *            numerical string e.g. 0, 1...
	 */
	private void setDaysOfWorkMissedDueToIllness(String daysOfWorkMissedDueToIllness) {
		Integer numberOfDaysOfWorkMissedDueToIllness = Integer.parseInt(daysOfWorkMissedDueToIllness.trim());

		for (int numberOfDaysOfWorkMissedDueToIllnessToSet = 0; numberOfDaysOfWorkMissedDueToIllnessToSet < numberOfDaysOfWorkMissedDueToIllness; numberOfDaysOfWorkMissedDueToIllnessToSet++) {
			driver.findElement(plusOneMissedWorkDueToHealthBtn).click();
		}
	}

	/**
	 * Sets the users Job Satisfaction level
	 * 
	 * @param jobSatisfactionLevel
	 *            defines the users job satisfaction level - AGREE
	 *            STRONGLY/AGREE/DISAGREE/DISAGREE STRONGLY/DOES NOT APPLY
	 */
	private void setJobSatisfactionLevel(String jobSatisfactionLevel) {
		jobSatisfactionLevel = jobSatisfactionLevel.trim().toUpperCase();

		switch (jobSatisfactionLevel) {
		case AGREE_STRONGLY:
			driver.findElement(agreeStronglyWithJobSatisfactionBtn).click();
			break;
		case AGREE:
			driver.findElement(agreeWithJobSatisfactionBtn).click();
			break;
		case DISAGREE:
			driver.findElement(disagreeWithJobSatisfactionBtn).click();
			break;
		case DISAGREE_STRONGLY:
			driver.findElement(disagreeStronglyWithJobSatisfactionBtn).click();
			break;
		case DOES_NOT_APPLY:
			driver.findElement(doesNotApplyWithJobSatisfactionBtn).click();
			break;
		default:
			logger.error("Invalid value for - planning on better managing your stress, was provided.");
			break;
		}

	}

	/**
	 * Sets the users plan on managing stress better
	 * 
	 * @param planOnBetterManagingStress
	 *            defines the users plan to better manage stress - no present
	 *            interest/Plan to change in 6 months/Plan to change this
	 *            month/Already Doing This.
	 */
	private void setPlanOnBetterManagingStress(String planOnBetterManagingStress) {
		planOnBetterManagingStress = planOnBetterManagingStress.trim().toUpperCase();

		switch (planOnBetterManagingStress) {
		case NO_INTEREST:
			driver.findElement(planToManageStressBetterNoPresentInterestBtn).click();
			break;
		case CHANGE_IN_6_MONTHS:
			driver.findElement(planToManageStressBetterPlanToChangeIn6MonthsBtn).click();
			break;
		case CHANGE_THIS_MONTH:
			driver.findElement(planToManageStressBetterPlanToChangeThisMonthBtn).click();
			break;
		case RECENTLY_STARTED:
			driver.findElement(planToManageStressBetterRecentlyStartedBtn).click();
			break;
		case ALREADY_DOING:
			driver.findElement(planToManageStressBetterAlreadyDoingThisBtn).click();
			break;
		default:
			logger.error("Invalid value for - planning on better managing your stress, was provided.");
			break;
		}
	}
	
	/**
	 * Sets all the answers for the Well being HA page
	 * @param wellBeingHAData contains data read from the data file
	 */
	public void completeWellBeingHA(Map<String, String> wellBeingHAData) {
		
		logger.info("Starting to fill data in the Well Being Page...");
		setHowOftenFeelAnxiousOrDepressed(wellBeingHAData.get("howOftenFeelAnxiousOrDepressed"));
		setEffectOfStressOnHealth(wellBeingHAData.get("effectOfStressOnHealth"));
		setHowManyHrsOfSleepAtNight(wellBeingHAData.get("howManyHrsOfSleepAtNight"));
		setHowSatisfiedAreYouWithLife(wellBeingHAData.get("howSatisfiedAreYouWithLife"));
		setHowStrongSocialTies(wellBeingHAData.get("howStrongSocialTies"));
		setSufferedPersonalLossOrMisfortune(wellBeingHAData.get("sufferedPersonalLossOrMisfortune"));
		setPercentOfTimeUsuallyBuckleSafetyBelt(wellBeingHAData.get("percentOfTimeUsuallyBuckleSafetyBelt"));
		setDaysOfWorkMissedDueToIllness(wellBeingHAData.get("daysOfWorkMissedDueToIllness"));
		setJobSatisfactionLevel(wellBeingHAData.get("jobSatisfactionLevel"));
		setPlanOnBetterManagingStress(wellBeingHAData.get("planOnBetterManagingStress"));
	}

}
