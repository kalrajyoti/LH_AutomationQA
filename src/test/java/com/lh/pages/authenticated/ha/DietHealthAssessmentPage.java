package com.lh.pages.authenticated.ha;

import java.util.Map;

import static com.lh.helper.DriverFactory.driver;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.testng.Reporter;

import com.lh.testConfig.TestProperty;
import com.lh.utils.SeleniumUtil;

/**
 * <h2>All the methods for Diet HA page are defined here</h2>
 * <p>
 * 
 * @author amita.arya
 * @version 1.0
 * @since 2015-01-29
 */
public class DietHealthAssessmentPage extends HealthAssessmentPage {
	
	private static final String FIVE_TO_SIX 				= "5 to 6";
	private static final String THREE_TO_FOUR 				= "3 to 4";
	private static final String ONE_TO_TWO 					= "1 to 2";
	private static final String RARELY 						= "rarely";
	/**
	 * Logger to log messages
	 */
	private static Logger logger = LogManager.getLogger(com.lh.pages.authenticated.ha.DietHealthAssessmentPage.class);
	
	private By rareIntakeOfHighFiberFoods				 					= By
			.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl00_HAQuestionCtrl1_ctl00_rptChoices_ctl00_ImageRadioList1_DvImgRadio']/table/tbody/tr[2]/td[@value='0']//img[@src='/v2/images/psq/0cups_icon.png']");
	private By oneToTwoServingsIntakeOfHighFiberFoods 						= By
			.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl00_HAQuestionCtrl1_ctl00_rptChoices_ctl00_ImageRadioList1_DvImgRadio']/table/tbody/tr[2]/td[@value='1']//img[@src='/v2/images/psq/1-2servingsFiber_icon.png']");
	private By threeToFourServingsIntakeOfHighFiberFoods 					= By
			.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl00_HAQuestionCtrl1_ctl00_rptChoices_ctl00_ImageRadioList1_DvImgRadio']/table/tbody/tr[2]/td[@value='2']//img[@src='/v2/images/psq/3-4servingsFiber_icon.png']");
	private By fiveToSixServingsIntakeOfHighFiberFoods 						= By
			.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl00_HAQuestionCtrl1_ctl00_rptChoices_ctl00_ImageRadioList1_DvImgRadio']/table/tbody/tr[2]/td[@value='4']//img[@src='/v2/images/psq/5-6servingsFiber_icon.png']");
	private By rareIntakeOfFruits 											= By
			.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl00_HAQuestionCtrl1_ctl00_rptChoices_ctl01_ImageRadioList1_DvImgRadio']/table/tbody/tr[2]/td[@value='0']//img[@src='/v2/images/psq/0cups_icon.png']");
	private By oneCupIntakeOfFruits 										= By
			.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl00_HAQuestionCtrl1_ctl00_rptChoices_ctl01_ImageRadioList1_DvImgRadio']/table/tbody/tr[2]/td[@value='1']//img[@src='/v2/images/psq/1cupFruit_icon.png']");
	private By twoCupsIntakeOfFruits 										= By
			.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl00_HAQuestionCtrl1_ctl00_rptChoices_ctl01_ImageRadioList1_DvImgRadio']/table/tbody/tr[2]/td[@value='2']//img[@src='/v2/images/psq/2cupsFruit_icon.png']");
	private By threeCupsIntakeOfFruits 										= By
			.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl00_HAQuestionCtrl1_ctl00_rptChoices_ctl01_ImageRadioList1_DvImgRadio']/table/tbody/tr[2]/td[@value='4']//img[@src='/v2/images/psq/3cupsFruit_icon.png']");
	private By fourCupsIntakeOfFruits 										= By
			.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl00_HAQuestionCtrl1_ctl00_rptChoices_ctl01_ImageRadioList1_DvImgRadio']/table/tbody/tr[2]/td[@value='8']//img[@src='/v2/images/psq/4cupsFruit_icon.png']");
	private By rareIntakeOfVegetables 										= By
			.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl00_HAQuestionCtrl1_ctl00_rptChoices_ctl02_ImageRadioList1_DvImgRadio']/table/tbody/tr[2]/td[1]//img[@src='/v2/images/psq/0cups_icon.png']");
	private By oneCupIntakeOfVegetables 									= By
			.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl00_HAQuestionCtrl1_ctl00_rptChoices_ctl02_ImageRadioList1_DvImgRadio']/table/tbody/tr[2]/td[2]//img[@src='/v2/images/psq/1cupVeg_icon.png']");
	private By twoCupsIntakeOfVegetables 									= By
			.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl00_HAQuestionCtrl1_ctl00_rptChoices_ctl02_ImageRadioList1_DvImgRadio']/table/tbody/tr[2]/td[3]//img[@src='/v2/images/psq/2cupsVeg_icon.png']");
	private By threeCupsIntakeOfVegetables 									= By
			.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl00_HAQuestionCtrl1_ctl00_rptChoices_ctl02_ImageRadioList1_DvImgRadio']/table/tbody/tr[2]/td[4]//img[@src='/v2/images/psq/3cupsVeg_icon.png']");
	private By fourCupsIntakeOfVegetables 									= By
			.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl00_HAQuestionCtrl1_ctl00_rptChoices_ctl02_ImageRadioList1_DvImgRadio']/table/tbody/tr[2]/td[5]//img[@src='/v2/images/psq/4cupsVeg_icon.png']");
	private By rareIntakeOfHighFatFood 										= By
			.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl00_HAQuestionCtrl1_ctl00_rptChoices_ctl03_ImageRadioList1_DvImgRadio']/table/tbody/tr[2]/td[@value='0']//img[@src='/v2/images/psq/0cups_icon.png']");
	private By oneToTwoServingsIntakeOfHighFatFood 							= By
			.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl00_HAQuestionCtrl1_ctl00_rptChoices_ctl03_ImageRadioList1_DvImgRadio']/table/tbody/tr[2]/td[@value='1']//img[@src='/v2/images/psq/1-2servingsFat_icon.png']");
	private By threeToFourServingsIntakeOfHighFatFood 						= By
			.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl00_HAQuestionCtrl1_ctl00_rptChoices_ctl03_ImageRadioList1_DvImgRadio']/table/tbody/tr[2]/td[@value='2']//img[@src='/v2/images/psq/3-4servingsFat_icon.png']");
	private By fiveToSixServingsIntakeOfHighFatFood 						= By
			.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl00_HAQuestionCtrl1_ctl00_rptChoices_ctl03_ImageRadioList1_DvImgRadio']/table/tbody/tr[2]/td[@value='4']//img[@src='/v2/images/psq/5-6servingsFat_icon.png']");
	private By planOnEatingHealthierDietNoPresentInterestBtn 				= By.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl01_HAQuestionCtrl1_rdQ_63_AnswersContainer']/div/div[1]");
	private By planOnEatingHealthierDietPlanToChangeIn6MonthsBtn 			= By.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl01_HAQuestionCtrl1_rdQ_63_AnswersContainer']/div/div[2]");
	private By planOnEatingHealthierDietPlanToChangeThisMonthBtn 			= By.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl01_HAQuestionCtrl1_rdQ_63_AnswersContainer']/div/div[3]");
	private By planOnEatingHealthierDietRecentlyStartedBtn 					= By.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl01_HAQuestionCtrl1_rdQ_63_AnswersContainer']/div/div[4]");
	private By planOnEatingHealthierDietAlreadyDoingThisBtn 				= By.xpath("//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl01_HAQuestionCtrl1_rdQ_63_AnswersContainer']/div/div[5]");

	/**
	 * One param class constructor.
	 * 
	 * @param driver
	 *            webdriver instance to connect to the page under test.
	 */
	public DietHealthAssessmentPage() {
		super();
		logger.info("Initializing the DietHealthAssessmentPage Object...");
			if (!(driver.getTitle().equalsIgnoreCase(TestProperty.CLIENT_PORTAL_TITLE))) {
			logger.error("This is not the Diet Health Assessment page");
			throw new IllegalStateException("This is not the DietHealthAssessmentPage page.");
		}
		logger.info("Initialized the DietHealthAssessmentPage page object.");
	}
	
	/**
	 * Sets the high Fiber food intake by measure of servings.
	 * 
	 * @param amountOfHighFiberFoodIntake
	 *            amount of high fiber food intake rarely/1-2/3-4/5-6.
	 */
	private void sethowMuchOfHighFiberFoodIntakeDaily(String amountOfHighFiberFoodIntake) {
		amountOfHighFiberFoodIntake = amountOfHighFiberFoodIntake.trim().toLowerCase();

		switch (amountOfHighFiberFoodIntake) {
		case RARELY:
			driver.findElement(rareIntakeOfHighFiberFoods).click();
			break;
		case ONE_TO_TWO:
			driver.findElement(oneToTwoServingsIntakeOfHighFiberFoods).click();
			break;
		case THREE_TO_FOUR:
			driver.findElement(threeToFourServingsIntakeOfHighFiberFoods).click();
			break;
		case FIVE_TO_SIX:
			driver.findElement(fiveToSixServingsIntakeOfHighFiberFoods).click();
			break;
		default:
			logger.error("Invalid value for the amount of high fiber foods intake daily, was provided. ");
			break;
		}
	}
	
	/**
	 * Sets the Fruit intake by measure of cups.
	 * 
	 * @param amountOfFruitIntake
	 *            amount of fruit intake in integer, range 0-4.
	 */
	private void sethowMuchOfFruitIntakeDaily(String amountOfFruitIntake) {
		Integer amountOfFruitIntakeDaily = Integer.parseInt(amountOfFruitIntake.trim());

		switch (amountOfFruitIntakeDaily) {
		case 0:
			driver.findElement(rareIntakeOfFruits).click();
			break;
		case 1:
			driver.findElement(oneCupIntakeOfFruits).click();
			break;
		case 2:
			driver.findElement(twoCupsIntakeOfFruits).click();
			break;
		case 3:
			driver.findElement(threeCupsIntakeOfFruits).click();
			break;
		case 4:
			driver.findElement(fourCupsIntakeOfFruits).click();
			break;
		default:
			logger.error("Invalid value for the amount of fruit intake daily, was provided. ");
			break;
		}
	}

	/**
	 * Sets the vegetable intake by measure of cups.
	 * 
	 * @param amountOfVegetablesIntake
	 *            amount of vegetable intake in integer, range 0-4.
	 */
	private void sethowMuchOfVegetablesIntakeDaily(String amountOfVegetablesIntake) {
		Integer amountOfVegetablesIntakeDaily = Integer.parseInt(amountOfVegetablesIntake.trim());

		switch (amountOfVegetablesIntakeDaily) {
		case 0:
			driver.findElement(rareIntakeOfVegetables).click();
			break;
		case 1:
			driver.findElement(oneCupIntakeOfVegetables).click();
			break;
		case 2:
			driver.findElement(twoCupsIntakeOfVegetables).click();
			break;
		case 3:
			driver.findElement(threeCupsIntakeOfVegetables).click();
			break;
		case 4:
			driver.findElement(fourCupsIntakeOfVegetables).click();
			break;
		default:
			logger.error("Invalid value for the amount of vegetable intake daily, was provided. ");
			break;
		}
	}
	
	/**
	 * Sets the high fat food intake by measure of servings.
	 * 
	 * @param amountOfHighFatFoodIntake
	 *            amount of high fat food intake rarely/1-2/3-4/5-6.
	 */
	private void sethowMuchOfHighFatFoodIntakeDaily(String amountOfHighFatFoodIntake) {
		amountOfHighFatFoodIntake = amountOfHighFatFoodIntake.trim().toLowerCase();

		switch (amountOfHighFatFoodIntake) {
		case RARELY:
			driver.findElement(rareIntakeOfHighFatFood).click();
			break;
		case ONE_TO_TWO:
			driver.findElement(oneToTwoServingsIntakeOfHighFatFood).click();
			break;
		case THREE_TO_FOUR:
			driver.findElement(threeToFourServingsIntakeOfHighFatFood).click();
			break;
		case FIVE_TO_SIX:
			driver.findElement(rareIntakeOfHighFatFood).click();
			break;
		default:
			logger.error("Invalid value for the amount of high fat foods intake daily, was provided. ");
			break;
		}
	}

	/**
	 * Sets the users plan for eating a healthier diet
	 * 
	 * @param planOnEatingHealthierDiet
	 *            takes values from no present interest/Plan to change in 6
	 *            months/Plan to change this month/Already Doing This
	 */
	private void setPlanOnEatingHealthierDiet(String planOnEatingHealthierDiet){
		planOnEatingHealthierDiet = planOnEatingHealthierDiet.trim().toUpperCase();
		
		switch (planOnEatingHealthierDiet) {
		case NO_INTEREST:
			driver.findElement(planOnEatingHealthierDietNoPresentInterestBtn).click();
			break;
		case CHANGE_IN_6_MONTHS:
			driver.findElement(planOnEatingHealthierDietPlanToChangeIn6MonthsBtn).click();
			break;
		case CHANGE_THIS_MONTH:
			driver.findElement(planOnEatingHealthierDietPlanToChangeThisMonthBtn).click();
			break;
		case RECENTLY_STARTED:
			driver.findElement(planOnEatingHealthierDietRecentlyStartedBtn).click();
			break;
		case ALREADY_DOING:
			driver.findElement(planOnEatingHealthierDietAlreadyDoingThisBtn).click();
			break;
		default:
			logger.error("Invalid value for - Plan on start eating a healthier diet, was provided.");
			break;
		}
		
	}
	
	/**
	 * Sets answers for all the Diet page questions
	 * 
	 * @param dietHAData Contains the data read from the excel file
	 */
	public void completeDietHAPage(Map<String, String> dietHAData) {
		logger.info("Starting to fill data in the Diet Page...");
		SeleniumUtil.sleep(3);
		sethowMuchOfHighFiberFoodIntakeDaily(dietHAData.get("amountOfHighFiberFoodIntake"));
		SeleniumUtil.sleep(2);
		sethowMuchOfFruitIntakeDaily(dietHAData.get("amountOfFruitIntake"));
		SeleniumUtil.sleep(2);
		sethowMuchOfVegetablesIntakeDaily(dietHAData.get("amountOfVegetablesIntake"));
		SeleniumUtil.sleep(2);
		sethowMuchOfHighFatFoodIntakeDaily(dietHAData.get("amountOfHighFatFoodIntake"));
		SeleniumUtil.sleep(2);
		setPlanOnEatingHealthierDiet(dietHAData.get("planOnEatingHealthierDiet"));
	}
}
