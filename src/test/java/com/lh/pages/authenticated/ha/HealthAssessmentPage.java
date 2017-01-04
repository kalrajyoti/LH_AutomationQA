package com.lh.pages.authenticated.ha;

import java.util.List;

import com.lh.pages.authenticated.MainPage;
import com.lh.testConfig.TestProperty;
import com.lh.utils.SeleniumUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static com.lh.helper.DriverFactory.driver;
import static com.lh.testConfig.TestProperty.WAITING_TIME;

/**
 * <h2>This is the base class for all the HA pages.</h2>
 * <p>
 * 
 * @author amita.arya
 * @version 1.0
 * @since 2014-11-17
 */

public class HealthAssessmentPage extends MainPage {

	By reportLink = By.cssSelector("#ctl00_ContentPlaceHolder1_HealthReportDownloadBox_healthReportRepeater_ctl00_healthReportLink");

	By aboutYouSectionText = By.xpath("//*[contains(.,'bit about yourself')][not(.//*[contains(., 'bit about yourself')])]");
	By healthStatusSectionText = By.xpath("//*[contains(.,'about your health status')][not(.//*[contains(., 'about your health status')])]");
	By tobaccoUseSectionText = By.xpath("//*[contains(.,'your tobacco use')][not(.//*[contains(., 'your tobacco use')])]");
	By substancesSectionText = By.xpath("//*[contains(.,'your alcohol use')][not(.//*[contains(., 'your alcohol use')])]");
	By activitySectionText = By.xpath("//*[contains(.,'physical activity habits')][not(.//*[contains(., 'physical activity habits')])]");
	By dietSectionText = By.xpath("//*[contains(.,'kinds of foods you eat')][not(.//*[contains(., 'kinds of foods you eat')])]");
	By wellbeingSectionText = By.xpath("//*[contains(.,'about your wellbeing')][not(.//*[contains(., 'about your wellbeing')])]");
	By conditionsSectionText = By.xpath("//*[contains(.,'your health conditions')][not(.//*[contains(., 'your health conditions')])]");
	By screeningsSectionText = By.xpath("//*[contains(.,'recent preventative screenings')][not(.//*[contains(., 'recent preventative screenings')])]");

	By congratulationsText = By.xpath("//*[contains(.,'Congratulations')][not(.//*[contains(., 'Congratulations')])]");

	protected By takeNowBtn 								= By.id("lnkScheduleNow");
	protected By hABreadCrumb 								= By
			.id("ctl00_TopNavigationBreadcrumb_breadcrumbItemRepeater_ctl04_breadcrumbItemUrl");
	protected By consentFormHeader 							= By.id("ui-id-2");
	protected By giveConsentChk 							= By
			.cssSelector("#ctl00_modalBodyContent_AgreeCheckbox");
	protected By giveConsentContinueBtn 					= By.cssSelector("#AgreementContinueLinkButton");
	protected By saveAndExitBtn 							= By.id("SaveAndExitButton");
	protected By leaveHABtn 								= By.id("leaveButton");
	protected By aboutYouMenuItem 							= By
			.id("ctl00_ContentPlaceHolder1_PSQControl1_leftNavigationButtonRepeater_ctl00_NavigationLinkButton");
	protected By healthStatusMenuItem 						= By
			.id("ctl00_ContentPlaceHolder1_PSQControl1_leftNavigationButtonRepeater_ctl01_NavigationLinkButton");
	protected By tobaccoUseMenuItem 						= By
			.id("ctl00_ContentPlaceHolder1_PSQControl1_leftNavigationButtonRepeater_ctl02_NavigationLinkButton");
	protected By substancesMenuItem 						= By
			.id("ctl00_ContentPlaceHolder1_PSQControl1_leftNavigationButtonRepeater_ctl03_NavigationLinkButton");
	protected By activityMenuItem 							= By
			.id("ctl00_ContentPlaceHolder1_PSQControl1_leftNavigationButtonRepeater_ctl04_NavigationLinkButton");
	protected By dietMenuItem 								= By
			.id("ctl00_ContentPlaceHolder1_PSQControl1_leftNavigationButtonRepeater_ctl05_NavigationLinkButton");
	protected By wellbeingMnuItem 							= By
			.id("ctl00_ContentPlaceHolder1_PSQControl1_leftNavigationButtonRepeater_ctl06_NavigationLinkButton");
	protected By conditionsMenuItem 						= By
			.id("ctl00_ContentPlaceHolder1_PSQControl1_leftNavigationButtonRepeater_ctl07_NavigationLinkButton");
	protected By screeningsMenuItem 						= By
			.id("ctl00_ContentPlaceHolder1_PSQControl1_leftNavigationButtonRepeater_ctl07_NavigationLinkButton");
	protected By nextBtn									= By.id("NextButton");
	protected By healthAssessmentHeaderTitle 				= By.id("ctl00_ContentPlaceHolder1_PSQControl1_litGroupText");
	protected By hAIsCompleteTxt 							= By.xpath("//*[@id='ctl00_ContentPlaceHolder1_healthAssessmentConfirmationContent']/div[2]/span");
	protected final String NO_INTEREST 						= "NO PRESENT INTEREST";
	protected final String CHANGE_IN_6_MONTHS 				= "PLAN TO CHANGE IN 6 MONTHS";
	protected final String CHANGE_THIS_MONTH 				= "PLAN TO CHANGE THIS MONTH";
	protected final String RECENTLY_STARTED 				= "RECENTLY STARTED";
	protected final String ALREADY_DOING 					= "ALREADY DOING THIS";
	protected final String NOT_APPLICABLE 					= "NOT APPLICABLE";
	private static final String CONGRATULATIONS 			= "Congratulations";
	protected String HALeftColumnText							= "//div[@id='leftNavigationButtons']//a[text()='%s']";
	private static Logger logger = LogManager.getLogger(com.lh.pages.authenticated.ha.HealthAssessmentPage.class);
	protected By listOfErrorMsg 								= By.xpath("//div[@class='web-access-skip health_assessment remove-outline']//p[@class='articletext error' and text()='Please provide an answer to this question']");

	/**
	 * Constructor
	 * 
	 *
	 */
	public HealthAssessmentPage() {
		super();
		logger.info("Initializing the HealthAssessmentPage Object...");
		// Check that we're on the right page.
		if(!(driver.getCurrentUrl().contains("MSEvents"))) {
			logger.info("...not on Health Assessment page.  Navigating there now...");
            clickHealthAssessment();
		}
		logger.info("Initialized the HealthAssessmentPage page object");
	}

	/**
	 * Clicks on the Take now button to start the HRA
	 */
	public void clickTakeNowBtn() {
		try {
			Thread.sleep(TestProperty.THREAD_SLEEP);
		} catch (InterruptedException e) {
			logger.error("InterruptedException is found and the test is halting...", e);
		}
		driver.findElement(takeNowBtn).click();
		logger.info("Clicked on the Take Now button.");
	}
	
	public boolean verifyIsPresentTakeNowButton(){
		return driver.findElement(takeNowBtn).isDisplayed();
	}

	/**
	 * Verifies the presence of the consent form
	 * @return boolean returns true if the consent form is present 
	 */
	public boolean verifyPresenceOfConsentForm() {
		if (driver.findElement(consentFormHeader).isDisplayed()) {
			logger.info("The consent form is displayed");
			return true;
		}
		logger.error("The consent form did not appear.");
		return false;
	}

	/**
	 * Accept the consent by checking the I agree check box on the consent form
	 */
	public void giveConsent() {
		SeleniumUtil.sleep(5);
		SeleniumUtil.waitForElementToBeVisible(giveConsentChk, driver);
		driver.findElement(giveConsentChk).click();
		driver.findElement(giveConsentContinueBtn).click();
		logger.info("Successfully proceeded past Consent Form.");
	}

	/**
	 * Exit the HRA by clicking on the save and exit button
	 */
	public void clickSaveAndExitBtn() {
		driver.findElement(saveAndExitBtn).click();
		logger.info("Clicked on Save and Exit button in the HRA.");
	}

	/**
	 * To click on the leave button to exit the HRA
	 */
	public void clickLeaveBtn() {
		driver.findElement(leaveHABtn).click();
		logger.info("Clicked on the Leave button of the warning dialog.");
	}
	
	/**
	 * Check if the menu item  is enabled
	 * @param menuItem HA left Navigation menu item
	 * @param itemLabel Label of the HA menu item
	 * @return
	 */
	public boolean isEnabledMenuItem(By menuItem, String itemLabel){
		if (!driver.findElement(menuItem).isEnabled()){
			logger.info(itemLabel + " left navigation Item appears as expected");
			return true;
		}
		logger.error(itemLabel + " left navigation Item does not appear as expected.");
		return false;
	}
	
	/**
	 * Check if the menu item  is visible
	 * @param menuItem HA left Navigation menu item
	 * @param itemLabel Label of the HA menu item
	 * @return
	 */
	public boolean isVisibleMenuItem(By menuItem, String itemLabel){
		if (!driver.findElement(menuItem).isDisplayed()){
			logger.info(itemLabel + " left navigation Item is visible in the HRA.");
			return true;
		}
		
		logger.error(itemLabel + " left navigation Item does not visible.");
		return false;
	}
	
	/**
	 * Clicks on the next button on the HA page to navigate to the next page
	 */
	public void clickNextBtn(){
		SeleniumUtil.hover(nextBtn,driver);

		//Actions action = new Actions(driver);
        //action.moveToElement(driver.findElement(nextBtn)).click(driver.findElement(nextBtn)).build().perform();

		logger.info("Clicked on the Next button on the HA page.");
	}
	
	/**
	 * 
	 * @param
	 *            the text expected on the HA page
	 * @return boolean true/false depending on the result
	 */
	public boolean verifyCurrentHAPage(String hAtHeaderTxt) {
		String healthAssessmentHeaderTxt = hAtHeaderTxt.trim();
		String currentHealthAssessmentHeaderTxt = driver.findElement(healthAssessmentHeaderTitle).getText().trim();
		logger.info("HA header Text = "+currentHealthAssessmentHeaderTxt);
		
		logger.info("HA sheeet Text = "+healthAssessmentHeaderTxt);
		logger.error(currentHealthAssessmentHeaderTxt.length());
		logger.error(healthAssessmentHeaderTxt.length());
		
		if(currentHealthAssessmentHeaderTxt.equalsIgnoreCase(healthAssessmentHeaderTxt)) {
			logger.info("The expected text matched with the actual text.");
			return true;
		}
		logger.info("The expected text did not match with the actual text.");
		return false;
	}
	
	/**
	 * Retrieves the HA completion text and matches it with "Congratulations"
	 * 
	 * @return true in case the text retrieved matches "Congratulations" else
	 *         returns false
	 */
	public boolean isHAComplete() {
		String hAString = driver.findElement(hAIsCompleteTxt).getText();
		logger.info("The text that is retrieved form the HA is - " + hAString);

		if (CONGRATULATIONS.equals(hAString)) {
			return true;
		}
		return false;
	}

	public boolean verifyHealthAssessLeftColumnLinks(String text){

		String columnText=SeleniumUtil.getTextfromWebElement(SeleniumUtil.dynamicXpath(HALeftColumnText, text),driver) ;
    
		logger.info("The columntext is "+columnText);
	
		if(text.equalsIgnoreCase(columnText)){
		
			logger.info("Successfully verified the column text");
				return true;
		}
		logger.info("Failed to verify the column text");
		return false;
	}

	public boolean verifyOnAboutYou() {
		new WebDriverWait(driver, WAITING_TIME).until(ExpectedConditions.presenceOfElementLocated(aboutYouSectionText));
		logger.info("....successfully on About You section of the HA");
		return true;
	}

	public boolean verifyOnHealthStatus() {
		new WebDriverWait(driver, WAITING_TIME).until(ExpectedConditions.presenceOfElementLocated(healthStatusSectionText));
		logger.info("....successfully completed About You section of the HA. On Health Status section...");
		return true;
	}

	public boolean verifyOnTobaccoUse() {
		new WebDriverWait(driver, WAITING_TIME).until(ExpectedConditions.presenceOfElementLocated(tobaccoUseSectionText));
		logger.info("....successfully completed Health Status section of the HA. On Tobacco Use section...");
		return true;
	}

	public boolean verifyOnSubstances() {
		new WebDriverWait(driver, WAITING_TIME).until(ExpectedConditions.presenceOfElementLocated(substancesSectionText));
		logger.info("....successfully completed Tobacco Use section of the HA. On Substances section...");
		return true;
	}

	public boolean verifyOnActivity() {
		new WebDriverWait(driver, WAITING_TIME).until(ExpectedConditions.presenceOfElementLocated(activitySectionText));
		logger.info("....successfully completed Substances section of the HA. On Activity section...");
		return true;
	}

	public boolean verifyOnDiet() {
		new WebDriverWait(driver, WAITING_TIME).until(ExpectedConditions.presenceOfElementLocated(dietSectionText));
		logger.info("....successfully completed Activity section of the HA. On Diet section...");
		return true;
	}

	public boolean verifyOnWellbeing() {
		new WebDriverWait(driver, WAITING_TIME).until(ExpectedConditions.presenceOfElementLocated(wellbeingSectionText));
		logger.info("....successfully completed Diet section of the HA. On Wellbeing section...");
		return true;
	}

	public boolean verifyOnConditions() {
		new WebDriverWait(driver, WAITING_TIME).until(ExpectedConditions.presenceOfElementLocated(conditionsSectionText));
		logger.info("....successfully completed Wellbeing section of the HA. On Conditions section...");
		return true;
	}

	public boolean verifyOnScreenings() {
		new WebDriverWait(driver, WAITING_TIME).until(ExpectedConditions.presenceOfElementLocated(screeningsSectionText));
		logger.info("....successfully completed Conditions section of the HA. On Screening section...");
		return true;
	}

	public boolean verifyHealthAssessmentCompleted() {
		new WebDriverWait(driver, 45).until(ExpectedConditions.presenceOfAllElementsLocatedBy(congratulationsText));
		logger.info("**SUCCESSFULLY COMPLETED THE HEALTH ASSESSMENT.");
		return true;
	}

	public boolean verifyHealthReportGenerated(){
		/*String currentDate = DateUtil.getFormattedCurrentDate();
		By currentDateLink = By.linkText(currentDate);*/
		new WebDriverWait(driver, 45).until(ExpectedConditions.presenceOfAllElementsLocatedBy(reportLink));
		return true;
	}

	public boolean verifyListOfErrorMessage(String num){
    	boolean flag = false;
    	List<WebElement> errorMsgList = driver.findElements(listOfErrorMsg);
    	String size = Integer.toString(errorMsgList.size());
    	if(num.equals(size)){
    		flag = true;
    	}
    	return flag;
    }
}
