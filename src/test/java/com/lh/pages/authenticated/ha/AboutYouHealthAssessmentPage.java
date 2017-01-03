package com.lh.pages.authenticated.ha;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.lh.helper.DriverFactory.driver;
import static com.lh.testConfig.TestProperty.WAITING_TIME;

/**
 * <h2>All the methods for About you HA page are defined here.</h2>
 * <p>
 * 
 * @author amita.arya
 * @version 1.0
 * @since 2014-11-17
 */

public class AboutYouHealthAssessmentPage extends HealthAssessmentPage {

	By aboutYouSpecificHeader = By.xpath("//*[contains(.,'Tell us a bit about you')][not(.//*[contains(., 'Tell us a bit about you')])]");

	private static final String OTHER			 = "other";
	private static final String WIDOWED			 = "widowed";
	private static final String MARRIED			 = "married";
	private static final String DIVORCED		 = "divorced";
	private static final String SEPARATED		 = "separated";
	private static final String SINGLE			 = "single";
	private static final String POOR			 = "poor";
	private static final String FAIR			 = "fair";
	private static final String GOOD			 = "good";
	private static final String VERY_GOOD		 = "very good";
	private static final String EXCELLENT		 = "excellent";
	private static Logger logger = LogManager.getLogger(com.lh.pages.authenticated.ha.AboutYouHealthAssessmentPage.class);
	private By sexFemaleImg 					= By
			.cssSelector("input[previous-arialabel*=Female]");
	private By sexMaleImg 						= By
			.cssSelector("input[previous-arialabel*=Male]");
	private By ageTxt 							= By
			.id("TheNumericTextBox");
	private By singleMSRadio 					= By
			.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl03_HAQuestionCtrl1_rdOthers05_rbtList_0");
	private By separatedMSRadio 				= By
			.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl03_HAQuestionCtrl1_rdOthers05_rbtList_1");
	private By divorcedMSRadio 					= By
			.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl03_HAQuestionCtrl1_rdOthers05_rbtList_2");
	private By marriedMSRadio 					= By
			.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl03_HAQuestionCtrl1_rdOthers05_rbtList_3");
	private By widowedMSRadio 					= By
			.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl03_HAQuestionCtrl1_rdOthers05_rbtList_4");
	private By otherMSRadio 					= By
			.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl03_HAQuestionCtrl1_rdOthers05_rbtList_5");
	private By excellentPHRadio 				= By
			.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl04_HAQuestionCtrl1_ctl00_rbtList_0");
	private By veryGoodPHRadio 					= By
			.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl04_HAQuestionCtrl1_ctl00_rbtList_1");
	private By goodPHRadio 						= By
			.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl04_HAQuestionCtrl1_ctl00_rbtList_2");
	private By fairPHRadio 						= By
			.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl04_HAQuestionCtrl1_ctl00_rbtList_3");
	private By poorPHRadio 						= By
			.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl04_HAQuestionCtrl1_ctl00_rbtList_4");




	/**
	 * Constructor
	 *
	 */
	public AboutYouHealthAssessmentPage() {
		super();
		logger.info("Initializing the AboutYouHAPage Object...");
		// Check that we're on the right page.
		if (!(driver.findElement(aboutYouSpecificHeader).isDisplayed())) {
			logger.error("This is not the About You Health Assessment page");
			throw new IllegalStateException(
					"This is not the AboutYouHAPage page");
		}
		logger.info("On the About You HA Page.  Successfully initialized AboutYou HA Page Object");
	}

	/**
	 * Checks if the About you page can be accessed
	 * 
	 * @return boolean returns true in case the About you left navigation is
	 *         enabled or not
	 */
	public boolean isEnabledAboutYou() {
		boolean isAboutEnabled = driver.findElement(aboutYouMenuItem)
				.isEnabled();
		if (isAboutEnabled) {
			logger.info("About You left navigation Item is ENABLED.");
			return true;
		}
		logger.error("About You left navigation Item is NOT Enabled.");
		return false;
	}

	/**
	 * Checks if the HRA pane is enabled or not
	 * 
	 * @return boolean returns true if the Questionnaire pane is enabled
	 */
	public boolean isEnabledCurrQuestionnaireItem() {
		String aboutYouValue = driver.findElement(aboutYouMenuItem)
				.getAttribute("value");
		logger.info("The value of the element " + aboutYouMenuItem + " is - "
				+ aboutYouValue);
		String aboutYouClass = driver.findElement(aboutYouMenuItem)
				.getAttribute("class");
		logger.info("The class of the element " + aboutYouMenuItem + " is - "
				+ aboutYouClass);
		if (aboutYouValue.equals("About You")
				&& aboutYouClass.equals("side_nav_button active")) {
			logger.info("About You left navigation is appearing as expected.");
			return true;
		}
		logger.error("About You left navigation Item does not appear as expected.");
		return false;
	}

	/**
	 * Checks if the other Questionnaire items are diabled or not
	 * 
	 * @return boolean returns true if the other questionnaire items are
	 *         disabled
	 */
	public boolean isDisabledOthQuestionnaireItems() {
		HashMap<String, Boolean> navItemMap = new HashMap<String, Boolean>();
		navItemMap.put("Health Status",
				isEnabledMenuItem(healthStatusMenuItem, "Health Status"));
		navItemMap.put("Tobacco Use",
				isEnabledMenuItem(tobaccoUseMenuItem, "Tobacco Use"));
		navItemMap.put("Substances",
				isEnabledMenuItem(substancesMenuItem, "Substances"));
		navItemMap.put("Activity",
				isEnabledMenuItem(activityMenuItem, "Activity"));
		navItemMap.put("Diet", isEnabledMenuItem(dietMenuItem, "Diet"));
		navItemMap.put("Wellbeing",
				isEnabledMenuItem(wellbeingMnuItem, "Wellbeing"));
		navItemMap.put("Conditions",
				isEnabledMenuItem(conditionsMenuItem, "Conditions"));
		navItemMap.put("Screenings",
				isEnabledMenuItem(screeningsMenuItem, "Screenings"));

		Set<String> keys = navItemMap.keySet();

		for (String key : keys) {
			if (navItemMap.get(key)) {
				logger.error("The menu item " + key + " returned "
						+ navItemMap.get(key) + " for being disabled.");
				return false;
			}
		}
		logger.error("All the menu items are disabled...");
		return true;
	}

	/**
	 * Checks if the a questionnaire menu item is disabled
	 * 
	 * @return boolean returns true if a questionnaire menu item is displayed or
	 *         not.
	 */
	public boolean isDisplayedMenuItem() {
		HashMap<String, Boolean> navItemMap = new HashMap<String, Boolean>();
		navItemMap.put("About You",
				isVisibleMenuItem(aboutYouMenuItem, "About You"));
		navItemMap.put("Health Status",
				isVisibleMenuItem(healthStatusMenuItem, "Health Status"));
		navItemMap.put("Tobacco Use",
				isVisibleMenuItem(tobaccoUseMenuItem, "Tobacco Use"));
		navItemMap.put("Substances",
				isVisibleMenuItem(substancesMenuItem, "Substances"));
		navItemMap.put("Activity",
				isVisibleMenuItem(activityMenuItem, "Activity"));
		navItemMap.put("Diet", isVisibleMenuItem(dietMenuItem, "Diet"));
		navItemMap.put("Wellbeing",
				isVisibleMenuItem(wellbeingMnuItem, "Wellbeing"));
		navItemMap.put("Conditions",
				isVisibleMenuItem(conditionsMenuItem, "Conditions"));
		navItemMap.put("Screenings",
				isVisibleMenuItem(screeningsMenuItem, "Screenings"));

		Set<String> keys = navItemMap.keySet();

		for (String key : keys) {
			if (!navItemMap.get(key)) {
				logger.error("The menu item " + key + " returned "
						+ navItemMap.get(key) + " for being disabled.");
				return false;
			}
		}
		logger.info("All the menu items are disabled...");
		return true;
	}
	
	/**
	 * Clicks on the gender image
	 * @param gender male/female
	 */
	public void selectGender(String gender){
		new WebDriverWait(driver,WAITING_TIME).until(ExpectedConditions.elementToBeClickable(sexMaleImg));
		if ("female".equalsIgnoreCase((gender).trim())){
			driver.findElement(sexFemaleImg).click();
			logger.info("Setting the gender as female.");
		} else if("male".equalsIgnoreCase((gender).trim())){
			driver.findElement(sexMaleImg).click();
			logger.info("Setting the gender as male.");
		} else {
			logger.error("Invalid gender cannot be set ...");
		}	
	}
	
	/**
	 * Sets the Age
	 * @param age age in years
	 */
	public void setAge (String age){
		driver.findElement(ageTxt).clear();
		logger.info("Setting the age to - " + age);
		driver.findElement(ageTxt).sendKeys(age);
	}
	
	/**
	 * Selects the appropriate marital Status
	 * @param maritalStatus Single/Separated/Divorced/Married/Widowed/Other
	 */
	public void selectMaritalStatus(String maritalStatus){
		maritalStatus = maritalStatus.toLowerCase().trim();
		logger.info("Selecting the radio button for Marital Status- " + maritalStatus + "...");
		switch (maritalStatus) {
		case SINGLE			: driver.findElement(singleMSRadio).click();
			break;
		case SEPARATED		: driver.findElement(separatedMSRadio).click();
			break;
		case DIVORCED		: driver.findElement(divorcedMSRadio).click();
			break;
		case MARRIED		: driver.findElement(marriedMSRadio).click();
			break;
		case WIDOWED		: driver.findElement(widowedMSRadio).click();
			break;
		case OTHER			: driver.findElement(otherMSRadio).click();
			break;
		default				: logger.error("The invalid option- " + maritalStatus + " is not available for selection");
			break;

		}	
	}
	
	/**
	 * Selects the appropriate overall health for the test
	 * @param overallHealth Excellent/Very Good/Good/Fair/Poor
	 */
	public void selectOverallHealth(String overallHealth){
		overallHealth = overallHealth.toLowerCase().trim();
		logger.info("Selecting the radio button for overall health- " + overallHealth + "...");
		switch(overallHealth){
		case EXCELLENT		: driver.findElement(excellentPHRadio).click();
			break;
		case VERY_GOOD		: driver.findElement(veryGoodPHRadio).click();
			break;
		case GOOD			: driver.findElement(goodPHRadio).click();
			break;
		case FAIR			: driver.findElement(fairPHRadio).click();
			break;
		case POOR			: driver.findElement(poorPHRadio).click();
			break;
		default				: logger.error("The invalid option- " + overallHealth + " is not available for selection");
			break;	
		}
	}
	
	/**
	 * Completes the information on the About you HA page
	 * 
	 * @param aboutYouHAData
	 *            contains data values read form the data file
	 */
	public void completeAboutYouInformation(Map<String, String> aboutYouHAData) {
		selectGender(aboutYouHAData.get("sex"));
		setAge(aboutYouHAData.get("age"));
		selectMaritalStatus(aboutYouHAData.get("maritalStatus"));
		selectOverallHealth(aboutYouHAData.get("overallHealth"));
	}



}
