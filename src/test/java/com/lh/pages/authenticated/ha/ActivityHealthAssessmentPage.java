package com.lh.pages.authenticated.ha;
import java.util.Map;
import static com.lh.helper.DriverFactory.driver;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.testng.Reporter;
import com.lh.testConfig.TestProperty;

/**
 * <h2>All the methods for Activity HA page are defined here</h2>
 * <p>
 * 
 * @author amita.arya
 * @version 1.0
 * @since 2015-01-27
 */
public class ActivityHealthAssessmentPage extends HealthAssessmentPage{

	/**
	 * Logger to log messages
	 */
	private static Logger logger = LogManager.getLogger(com.lh.pages.authenticated.ha.ActivityHealthAssessmentPage.class);
	
	//Page element definition
	private By physicalActivityPerWeekUISlider 						= By
			.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl00_HAQuestionCtrl1_rdSS_51_SliderCtrl1_jQuerySliderContainer']/a[@class='ui-slider-handle ui-state-default ui-corner-all ui-state-hover']");
	private By marker1VigorousPhysicalActivityPerWeek 				= By
			.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl00_HAQuestionCtrl1_rdSS_51_SliderCtrl1_jQuerySliderContainer']/ul/li[1]");
	private By marker2VigorousPhysicalActivityPerWeek 				= By
			.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl00_HAQuestionCtrl1_rdSS_51_SliderCtrl1_jQuerySliderContainer']/ul/li[2]");
	private By marker3VigorousPhysicalActivityPerWeek 				= By
			.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl00_HAQuestionCtrl1_rdSS_51_SliderCtrl1_jQuerySliderContainer']/ul/li[3]");
	private By marker4VigorousPhysicalActivityPerWeek 				= By
			.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl00_HAQuestionCtrl1_rdSS_51_SliderCtrl1_jQuerySliderContainer']/ul/li[4]");
	private By marker5VigorousPhysicalActivityPerWeek 				= By
			.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl00_HAQuestionCtrl1_rdSS_51_SliderCtrl1_jQuerySliderContainer']/ul/li[5]");
	private By marker6VigorousPhysicalActivityPerWeek 				= By
			.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl00_HAQuestionCtrl1_rdSS_51_SliderCtrl1_jQuerySliderContainer']/ul/li[6]");
	private By marker1ModeratePhysicalActivityPerWeek 				= By
			.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl01_HAQuestionCtrl1_rdSS_52_SliderCtrl1_jQuerySliderContainer']/ul/li[1]");
	private By marker2ModeratePhysicalActivityPerWeek 				= By
			.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl01_HAQuestionCtrl1_rdSS_52_SliderCtrl1_jQuerySliderContainer']/ul/li[1]");
	private By marker3ModeratePhysicalActivityPerWeek 				= By
			.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl01_HAQuestionCtrl1_rdSS_52_SliderCtrl1_jQuerySliderContainer']/ul/li[1]");
	private By marker4ModeratePhysicalActivityPerWeek 				= By
			.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl01_HAQuestionCtrl1_rdSS_52_SliderCtrl1_jQuerySliderContainer']/ul/li[1]");
	private By marker5ModeratePhysicalActivityPerWeek 				= By
			.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl01_HAQuestionCtrl1_rdSS_52_SliderCtrl1_jQuerySliderContainer']/ul/li[1]");
	private By marker6ModeratePhysicalActivityPerWeek 				= By
			.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl01_HAQuestionCtrl1_rdSS_52_SliderCtrl1_jQuerySliderContainer']/ul/li[1]");
	private By beingMoreActiveNoPresentInterestBtn 					= By
			.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl02_HAQuestionCtrl1_rdQ_53_AnswersContainer']/div/div[1]");
	private By beingMoreActivePlanToChangeIn6MonthsBtn 				= By
			.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl02_HAQuestionCtrl1_rdQ_53_AnswersContainer']/div/div[2]");
	private By beingMoreActivePlanToChangeThisMonthBtn 				= By
			.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl02_HAQuestionCtrl1_rdQ_53_AnswersContainer']/div/div[3]");
	private By beingMoreActiveRecentlyStartedBtn 						= By
			.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl02_HAQuestionCtrl1_rdQ_53_AnswersContainer']/div/div[4]");
	private By beingMoreActiveAlreadyDoingThisBtn 					= By
			.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl02_HAQuestionCtrl1_rdQ_53_AnswersContainer']/div/div[5]");
	
	/**
	 * One param class constructor
	 * 
	 * @param driver
	 *            webdriver instance to connect to the page under test
	 */
	public ActivityHealthAssessmentPage() {
		super();
		logger.info("Initializing the ActivityHealthAssessmentPage Object...");

		if (!(driver.getTitle().equalsIgnoreCase(TestProperty.CLIENT_PORTAL_TITLE))) {
			logger.error("This is not the Activity Health Assessment page");
			throw new IllegalStateException("This is not the ActivityHealthAssessmentPage page");
		}
		logger.info("Initialized the ActivityHealthAssessmentPage page object");
	}

	/**
	 * Sets the number of times the user engages in Vigorous activity per week
	 * 
	 * @param vigorousActivityTimes
	 *            numerical string e.g. 0, 1...
	 */
	public void setNumberOfTimesEngageInVigorousActivity(String vigorousActivityTimes) {
		Integer numberOfVigorousActivityTimes = Integer.parseInt(vigorousActivityTimes.trim());

		switch (numberOfVigorousActivityTimes) {
		case 0:
			logger.debug("No vigrous activity was done.");
			break;
		case 1:
			driver.findElement(marker1VigorousPhysicalActivityPerWeek).click();
			break;
		case 2:
			driver.findElement(marker2VigorousPhysicalActivityPerWeek).click();
			break;
		case 3:
			driver.findElement(marker3VigorousPhysicalActivityPerWeek).click();
			break;
		case 4:
			driver.findElement(marker4VigorousPhysicalActivityPerWeek).click();
			break;
		case 5:
			driver.findElement(marker5VigorousPhysicalActivityPerWeek).click();
			break;
		case 6:
			driver.findElement(marker6VigorousPhysicalActivityPerWeek).click();
			break;
		default:
			logger.error("Invalid value for the number of times the vigorous activity is done, was provided. ");
			break;
		}
		/*
		 * new Actions(driver).clickAndHold(dragElementFrom).moveByOffset(100,
		 * 0).release().perform(); new
		 * Actions(driver).dragAndDropBy(dragElementFrom, pixelToMove,
		 * 0).build().perform();
		 */
	}

	/**
	 * Sets the number of times the user engages in light to moderate activity
	 * per week
	 * 
	 * @param moderateActivity
	 *            numerical string e.g. 0, 1...
	 */
	public void setNumberOfTimesEngageInModerateActivity(String moderateActivity) {
		Integer numberOfModerateActivityTimes = Integer.parseInt(moderateActivity.trim());

		switch (numberOfModerateActivityTimes) {
		case 0:
			logger.debug("No vigrous activity was done.");
			break;
		case 1:
			driver.findElement(marker1ModeratePhysicalActivityPerWeek).click();
			break;
		case 2:
			driver.findElement(marker2ModeratePhysicalActivityPerWeek).click();
			break;
		case 3:
			driver.findElement(marker3ModeratePhysicalActivityPerWeek).click();
			break;
		case 4:
			driver.findElement(marker4ModeratePhysicalActivityPerWeek).click();
			break;
		case 5:
			driver.findElement(marker5ModeratePhysicalActivityPerWeek).click();
			break;
		case 6:
			driver.findElement(marker6ModeratePhysicalActivityPerWeek).click();
			break;
		default:
			logger.error("Invalid value for the number of times the light to moderate activity is done, was provided. ");
			break;
		}
	}

	/**
	 * Sets the plan of the user to be more physically active
	 * 
	 * @param planToBeMoreActive
	 *            defines the users plan to be more physically active-no present
	 *            interest/Plan to change in 6 months/Plan to change this
	 *            month/Already Doing This.
	 */
	public void setPlanOnBeingMoreActive(String planToBeMoreActive) {
		planToBeMoreActive = planToBeMoreActive.trim().toUpperCase();

		switch (planToBeMoreActive) {
		case NO_INTEREST:
			driver.findElement(beingMoreActiveNoPresentInterestBtn).click();
			break;
		case CHANGE_IN_6_MONTHS:
			driver.findElement(beingMoreActivePlanToChangeIn6MonthsBtn).click();
			break;
		case CHANGE_THIS_MONTH:
			driver.findElement(beingMoreActivePlanToChangeThisMonthBtn).click();
			break;
		case RECENTLY_STARTED:
			driver.findElement(beingMoreActiveRecentlyStartedBtn).click();
			break;
		case ALREADY_DOING:
			driver.findElement(beingMoreActiveAlreadyDoingThisBtn).click();
			break;
		default:
			logger.error("Invalid value for - Plan on being more physically active was provided.");
			break;
		}
	}

	/**
	 * Sets answers for all the Activity page questions
	 * 
	 * @param vigorousActivityTimes
	 * @param moderateActivity
	 * @param planToBeMoreActive
	 */
	public void completeActivityHA(Map<String, String> activityHAData) {
		logger.info("Starting to fill data in the Activity Page...");
		setNumberOfTimesEngageInVigorousActivity(activityHAData.get("vigorousActivityTimes"));
		setNumberOfTimesEngageInModerateActivity(activityHAData.get("moderateActivity"));
		setPlanOnBeingMoreActive(activityHAData.get("planToBeMoreActive"));
	}
}