package com.lh.pages.authenticated.ontarget;

import static com.lh.helper.DriverFactory.driver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.lh.pages.authenticated.MainPage;
import com.lh.utils.SeleniumUtil;

/**
 * <h2>All the methods to work with onTraget Plan page are defined here</h2>
 * <p>
 * 
 * @author amita.arya
 * @version 1.0
 * @since 2015-05-01
 */
public class OnTargetPlan extends MainPage {

	/**
	 * Logger to log messages
	 */
	private static Logger logger = LogManager.getLogger(com.lh.pages.authenticated.ontarget.OnTargetPlan.class);

	private By getStartedBtn 									= By.xpath("//*[contains(.,'Get Started')][not(.//*[contains(., 'Get Started')])]");
	private By yourTargetLink 									= By.xpath("//*[@href='/ontarget/YourTarget/Plan/OnTarget']");
	private By dashboardLink									= By.xpath("//*[@href='/ontarget/Dashboard']");
	private By achievemnentLink 								= By.xpath("//section[@id='scSubNavigation']/ul[@class='list-inline-block subnav']/li/a[@href='/ontarget/Achievement']");
	private By logoutLnk										= By.xpath("//div[@id='header-old']//ul[@class='list-inline-block header-links']/li/a[@href='/logout.aspx']");
	private By gameplanList										= By.xpath("//div[@class='tb-module tb-hol ng-scope']//ul[@class='list-hol']/li//span[@class='tb-plan-title ng-binding']");
	private String gamePlansName								= "//div[@class='tb-module tb-hol ng-scope']//ul[@class='list-hol']/li//span[@class='tb-plan-title ng-binding' and text()='%s']";
	private By eatWellHeading									= By.xpath("(//div[@class='plan-title']/h2[@class='ng-binding' and text()='Eat Well'])[2]");
	private By getMovingHeading 								= By.xpath("(//div[@class='plan-title']/h2[@class='ng-binding' and text()='Get Moving'])[2]");
	private By manageYourWeightHeading							= By.xpath("(//div[@class='plan-title']/h2[@class='ng-binding' and text()='Manage Your Weight'])[2]");
	private By masterStressHeading								= By.xpath("(//div[@class='plan-title']/h2[@class='ng-binding' and text()='Master Stress'])[2]");
	private By quitSmokingHeading								= By.xpath("(//div[@class='plan-title']/h2[@class='ng-binding' and text()='Quit Smoking'])[2]");
	private By collapseGamePlanArrow							= By.xpath("(//a[@class='arrow-left'])[1]");
	private By spotlightHeading									= By.xpath("//h3[text()='Spotlight']");
	private By spotlightInfoIcon								= By.xpath("//div[@class='web-access-div-tb-tooltip tb-tooltip']/img");
	private By spotlightTooltipMessage 							= By.xpath("//div[@class='web-access-div-tb-tooltip tb-tooltip']//span/p");
	private By headingVisitDashboardOnYourTarget				= By.xpath("//div[@teaserzonename='onTarget_StartNewGameplanPrompt']/h2");
	private By getStartedButtonYourTarget						= By.xpath("//div[@class='row padded-grid']//div[@class='button-row']/input");
	private String gamePlanGetStartedBtn						= "(//div[@class='plan-title']/h2[@class='ng-binding' and text()='%s'])[2]/../../following-sibling::div[@class='tb-row-btn']/button";
	private By eatWellHeadingOnYourTarget						= By.xpath("//h2[@class='ng-binding ng-scope']");
	private By setYourGoalEatWell								= By.xpath("//div[@class='date-copy']/div[@class='ng-scope' and text()='Set your goal below']");
	private By levelDropdownArrow								= By.xpath("//div[@class='web-access-list-dropdown list-dropdown']/span");
	private String enabledLevel									= "//ul[@class='web-access-list-block list-block']//a[@class='ng-binding' and text()='%s']";
	private String lockedLevels									= "//ul[@class='web-access-list-block list-block']/li[@class='ng-scope locked']/span[text()='%s']";
	private By levelProgressTooltipIcon							= By.xpath("(//div[@id='ActionSpace']//span[@class='icon icon-24 afk-ko tooltip'])[1]");
	private By levelProgressTooltipIconText						= By.xpath("(//div[@id='ActionSpace']//span[@class='icon icon-24 afk-ko tooltip'])[1]//p");
	private By levelNextBtn										= By.cssSelector("#NextButton");
	private By chooseYourGoalsList								= By.xpath("//label[@class='ng-binding']");
	private String chooseYourGoalsRadioBtns						= "(//label[@class='ng-binding'])[%s]";
	private By levelSubmitBtn									= By.xpath("(//div[@class='row save wide-wrapper']//button[@id='SubmitButton' and text()='Submit'])[2]");
	private String startAndReviewLinkOfLevelActivity 			= "(//div[@class='tile-header']/h3[text()='%s'])[1]/../following-sibling::div/a";
	private By activityAndPostSurveyQuestion1RadioBtnList		= By.xpath("//div[@class='sg-question-options']/ul");
	private String activityAndPostSurveyQuestion1RadioBtn		= "(//div[@class='sg-question-options']//li/label)[%s]";
	private By nextArrowIcon									= By.id("sg_NextButton");
	private By quizFrame										= By.xpath("//iframe[@id='QuizIframe']");
	private By activityCompleteBtn								= By.id("sg_SubmitButton");
	private String startAndReviewLnkOfActivity					= "(//div[@class='tile-header']/h3[text()='%s'])[2]/../following-sibling::div/a";
	private By activitySubmitBtn								= By.id("SubmitButton");

	private By levelProgressHeading								= By.xpath("//span[@class='progress-copy ng-binding']");
	private By mealPlanningTable								= By.xpath("//div[@class='input-textarea ng-scope']//textarea");
	private String textareaList									= "(//div[@class='input-textarea ng-scope']//textarea)[%s]";
	private String addNewRowLnk									="(//div[@class='add-new-row ng-scope']/a)[%s]";
	private By level2StartAndReviewLnkYourWhyActivity			= By.xpath("//*[contains(.,'Your Why')][not(.//*[contains(., 'Your Why')])]");
	private By nextArrowBtnForChangingActivity					= By.xpath("//button[@class='slick-next']");
	private By level2StartAndReviewLnkOvercomeVeggieActivity	= By.xpath("(//div[@class='tile-header']/h3[text()='Overcome Veggie Hurdles'])[2]/../following-sibling::div/a");
	private String nextButtonsInLevelActivity					= "(//div[@class='row save wide-wrapper']//button[@id='NextButton' and text()='Next'])[%s]";
	private By level2OvercomeVeggieSubmitBtn					= By.xpath("(//button[@id='SubmitButton'])[5]");

	private By level3WhatsItWorthActivityRows					= By.xpath("//table[normalize-space(@class='sg-table')]//tr");
	private String level3WhatsItWorthActivityRadioButtons		= "((//table[normalize-space(@class='sg-table')]//tr)[%s])/td[%s]/label";
	private By tableQuizActivityColumns							= By.xpath("(//table[normalize-space(@class='sg-table')]//tr)[2]/td");
	private By level3StartAndReviewLnkPictureThisActivity		= By.xpath("//*[contains(.,'Picture This')][not(.//*[contains(., 'Picture This')])]");
	private By level3DocumentUploadInputOfPictureThisActivity	= By.id("documentdraganddropupload");
	private By level3UploadBtnOfPictureThisActivity				= By.id("btnUpload");
	private String level3TitleInputOfPictureThisActivity		= ("(//div[@class='input-textarea']//textarea)[%s]");

	private By level3StartAndReviewLnkPortionDistortionActivity	= By.xpath("(//div[@class='tile-header']/h3[text()='Portion Distortion'])[2]/../following-sibling::div/a");

	private By activityHowIntenseIsItSubmitBtn					= By.xpath("(//button[@id='SubmitButton'])[3]");
	private By activityPowerUpSubmitBtn							= By.xpath("(//button[@id='SubmitButton'])[9]");
	private By activityWinningTheWarSubmitBtn					= By.xpath("(//button[@id='SubmitButton'])[6]");
	private By activityFuelForFitnessSubmitBtn					= By.xpath("(//button[@id='SubmitButton'])[4]");
	private By continueBtnForProceedingToNextLevel				= By.xpath("//input[@class='btn' and @value='Continue']");

	private By submitEatWellGamePlan							= By.id("SubmitNow");
	private By submitGamePlan									= By.id("SubmitNow1");
	private By postPlanSurveyFrame								= By.xpath("//iframe[@class='ng-scope']");
	private By inputTextInGetMovingPostSurvey					= By.xpath("//div[@class='sg-control-text sg-control-number']/input");
	private By goalsEditLink									= By.xpath("//div[@class='button-row ng-scope']/a");
	private By chosenGoalsList									= By.xpath("//div[@class='date-copy']");
	private By otherInputBox									= By.xpath("//div[@class='hidetext']/input");

	private By inputTetxCurrentWeightOfManageWeightLevel1		= By.id("txtCurrentWeight");
	private By weightLossPercentageList							= By.xpath("//div[@class='input-weight']/label");
	private String weightLossPercentOption						= "(//div[@class='input-weight']/label)[%s]";
	private By chooseWeightGoalsSubmitBtn						= By.xpath("(//button[@id='SubmitButton'])[2]");
	private By activityMetabolismBasicsSumitBtn					= By.xpath("(//button[@id='SubmitButton'])[7]");
	private String spotlightIconOfYourWhyWeightActivity			= "(//div[@class='tile-header']//h3[text()='%s'])[2]/../preceding-sibling::div";
	private By spotlightactivityNameOnDashboard					= By.xpath("//div[@class='tb-module tb-hol tb-spotlight ng-scope']//ul[@class='list-hol']/li/a");
	private By spotlightActivityNameOnYourTargetPage			= By.xpath("//div[@id='ActionSpace']//div[@class='act-wrap ng-scope']/h2");
	private By emotionalEatingNextButton						= By.xpath("(//div[@class='row save wide-wrapper']//button[@id='NextButton' and text()='Next'])[2]");
	private By sliderFeetOfPostPlanSurveyOfWeightPlan			= By.xpath("(//div[@class='sg-slider']//a)[1]");
	private By sliderInchesOfPostPlanSurveyOfWeightPlan			= By.xpath("(//div[@class='sg-slider-label-center-horizontal'])[2]");
	private By completePercentAfterActivity1OfLevel2			= By.xpath("//span[@class='tb-plan-complete ng-binding ng-scope']");
	private By completeStatusOfGamePlan							= By.xpath("//span[@class='tb-plan-complete ng-scope']");
	private String continueBtnOnRevisitingStartedGamePlan		= "(//div[@class='plan-title']/h2[@class='ng-binding' and text()='%s'])[2]/../../../parent::div//div/a[@class='btn']";

	private By setYourQuitDateInputArea							= By.xpath("//div[@class='row ng-scope']//input");
	private By calendar 										= By.id("ui-datepicker-div");
	private By calendarNextMonthButton 							= By.xpath("//a[@class='ui-datepicker-next ui-corner-all']");
	private By crossLinkOfAlreadyStartedQuitMedsActivity		= By.xpath("(//*[contains(.,'Save and Close')][not(.//*[contains(., 'Save and Close')])])[2]");
	private By playBtnOfVideoInQuitSmoking						= By.xpath("//div[@id='player']//div/button[@class='ytp-large-play-button ytp-button']");
	private By iframeVideo										= By.id("player");
	private By achievementCardGamePlanHeading					= By.xpath("//div[@id='achievement']/ul/li//div/h2");
	private By reviewLinkOfAchievedGameplan						= By.xpath(".//*[@id='achievement']/ul/li//div[2]/a");
	private By achievedCardCompletionDate						= By.xpath("(//div[@id='achievement']/ul/li//div)[3]");
	private By achievementCardReviewLnk 						= By.xpath(".//*[@id='achievement']/ul/li//div/div/a");
	private By eatWellLoader									= By.xpath("//span[@class='icon icon-105']");

	By yourPrivacyMattersText = By.xpath("//*[contains(.,'Your Privacy Matters')][not(.//*[contains(., 'Your Privacy Matters')])]");

	public OnTargetPlan() {
		super();
		Reporter.log("Initializing the OnTarget plan page Object...");
	}

	/**
	 * verifies that the  "Get Started" button is displayed on the page
	 */
	public boolean verifyGetStartedBtnIsPresent (){

		boolean isPresent;

		isPresent= driver.findElement(getStartedBtn).isDisplayed();
		Reporter.log("Verified if Get Started button is displayed for Level1...");
		return isPresent;
	}


	/**
	 * verifies that the  "Your Target" Link is displayed on the page
	 */
	public boolean verifyYourTargetLinkIsPresent (){

		boolean isPresent;

		isPresent= driver.findElement(yourTargetLink).isDisplayed();
		Reporter.log("Verified if 'Your Target' link is present...");
		return isPresent;
	}

	/**
	 * verifies that the  "Dashboard" Link is displayed on the page
	 */
	public boolean verifyDashboardLinkIsPresent (){

		boolean isPresent;

		isPresent= driver.findElement(dashboardLink).isDisplayed();
		Reporter.log("Verified if Dashboard link is present...");
		return isPresent;
	}	

	/**
	 * verifies that the  "Achievement" Link is displayed on the page
	 */
	public boolean verifyAchievementLinkIsPresent(){

		boolean isPresent;

		isPresent = driver.findElement(achievemnentLink).isDisplayed();
		Reporter.log("Verified if 'achievement link is present...");
		return isPresent;

	}

	/**
	 * Clicks on the Get "Started" Button
	 */
	public void clickGetStartedBtn (){

		driver.findElement(getStartedBtn).click();
		Reporter.log("Clicked on Next button to start the activity/plan...");
	}


	/**
	 * Clicks on the Get "Your Target" Link
	 */
	public void clickYourTargetLink (){
		driver.findElement(yourTargetLink).click();
		Reporter.log("Clicked on 'Your Target' link...");
	}


	/**
	 * Clicks on the Get "Dashboard" Link
	 */
	public void clickDashboardLink (){
		SeleniumUtil.sleep(6);
		driver.findElement(dashboardLink).click();
		Reporter.log("Clicked on Dashboard tab...");
	}

	/**
	 * Verify all the associated plan are appearing on the dashboard
	 */
	public boolean verifyAssociatedPlanIsPresent(Map<String,String> gamePlanData){

		SeleniumUtil.sleep(2);
		boolean isPresent = false;

		List<WebElement> gamePlansList = driver.findElements(gameplanList);

		List<String> gamePlanActualList = new ArrayList<String>();

		for(WebElement el : gamePlansList){
			gamePlanActualList.add(el.getText());
		}

		List<String> gamePlansExpectedList = splitPipeSepratedString(gamePlanData.get("gamePlan"));

		if(gamePlanActualList.size() == gamePlansExpectedList.size()){

			for(String s : gamePlanActualList){
				if(!gamePlansExpectedList.contains(s)){
					isPresent = false;
					return isPresent;
				}
			}
			isPresent=true;

		}

		Reporter.log("Verified if all the associated Game Plans are present...");
		return isPresent;
	}

	public ArrayList<String> splitPipeSepratedString(String pipeSepratedSting){

		ArrayList<String> alist = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(pipeSepratedSting, "|");
		while(st.hasMoreTokens()){
			alist.add(st.nextToken());
		}
		return alist;

	}

	/**
	 * Click on various associated plan
	 * @param gamePlanName- name of the plan
	 */
	public void clickOnGamePlan(String gamePlanName){
		SeleniumUtil.sleep(4);
		SeleniumUtil.waitForElementToBeVisible(SeleniumUtil.dynamicXpath(gamePlansName, gamePlanName),driver);
		driver.findElement(SeleniumUtil.dynamicXpath(gamePlansName, gamePlanName)).click();
		Reporter.log("Clicked on gameplan " + gamePlanName+ "..." );
	}

	/**
	 * Verify the expanded content of Eat Well
	 */
	public boolean verifyEatWellExpandedContent(String gamePlanName){

		boolean isPresent;
		SeleniumUtil.sleep(3);
		isPresent = SeleniumUtil.element(eatWellHeading, driver).getText().contains(gamePlanName);
		Reporter.log("Verified the expanded content of 'Eat Well' game plan...");
		return isPresent;
	}

	/**
	 * Verify the expanded content of Get Moving plan
	 */
	public boolean verifyExpandedGetMovingContent(String gamePlanName){

		boolean isPresent;
		SeleniumUtil.sleep(2);
		isPresent = SeleniumUtil.element(getMovingHeading, driver).getText().equals(gamePlanName);
		Reporter.log("Verified the expanded content of 'Get Moving' game plan...");
		return isPresent;
	}

	/**
	 * Verify the expanded content of Manage Your Weight plan
	 */
	public boolean verifyExpandedManageYourWeightContent(String gamePlanName){

		boolean isPresent;
		SeleniumUtil.sleep(2);
		isPresent = SeleniumUtil.element(manageYourWeightHeading, driver).getText().equals(gamePlanName);
		Reporter.log("Verified the expanded content of 'Manage Your Weight' game plan...");
		return isPresent;
	}

	/**
	 * Verify the expanded content of Master Stress plan
	 */
	public boolean verifyExpandedMasterStressContent(String gamePlanName){

		boolean isPresent;
		SeleniumUtil.sleep(2);
		isPresent = SeleniumUtil.element(masterStressHeading, driver).getText().equals(gamePlanName);
		Reporter.log("Verified the expanded content of 'Master Stress' game plan...");
		return isPresent;
	}

	/**
	 * Verify the expanded content of Quit Smoking plan
	 */
	public boolean verifyExpandedQuitSmokingContent(String gamePlanName){

		boolean isPresent;
		SeleniumUtil.sleep(2);
		isPresent = SeleniumUtil.element(quitSmokingHeading, driver).getText().equals(gamePlanName);
		Reporter.log("Verified the expanded content of 'Quit Smoking' game plan...");
		return isPresent;
	}

	/**
	 * Collapse the expanded gameplan
	 */
	public void collapseGamePlan(){
		SeleniumUtil.sleep(2);
		SeleniumUtil.element(collapseGamePlanArrow, driver).click();
		Reporter.log("Click on arroe icon to collapse the opened gameplan...");
	}

	/**
	 * Verify Spotlight section appears on Dashboard
	 */
	public boolean verifySpotlightSectionIsPresent(){

		boolean isPresent = false;

		if(SeleniumUtil.element(spotlightHeading, driver).isDisplayed() && SeleniumUtil.element(spotlightInfoIcon, driver).isDisplayed()){
			SeleniumUtil.element(spotlightInfoIcon, driver).click();
			isPresent = SeleniumUtil.element(spotlightTooltipMessage, driver).isDisplayed();
		}

		Reporter.log("Verified if Spotlight section is present...");
		return isPresent;
	}

	/**
	 * Verify Your Target page for a new user
	 */
	public boolean verifyYourTargetGetStarted(){

		boolean isPresent = false;

		if(SeleniumUtil.element(headingVisitDashboardOnYourTarget, driver).isDisplayed() && SeleniumUtil.element(getStartedButtonYourTarget, driver).isDisplayed())
		{
			isPresent = true;
		}
		return isPresent;
	}

	/**
	 * Clicks on the LogOut link present on the top right corner of the website
	 */
	public void clickOnOnTargetLogoutLink(){
		Reporter.log("Clicking on the  the logoutLinkLocator indentified by- " + logoutLnk);
		SeleniumUtil.sleep(2);
		SeleniumUtil.waitForElementToBeVisible(logoutLnk, driver);
		driver.findElement(logoutLnk).click();
	}

	/**
	 * Click on Get Started button of Eat Well plan on dashboard
	 */
	public void clickGamePlanGetStartedBtn(String gamePlanName){
		SeleniumUtil.sleep(3);
		SeleniumUtil.waitForElementToBeVisible(SeleniumUtil.dynamicXpath(gamePlanGetStartedBtn, gamePlanName), driver);
		SeleniumUtil.element(SeleniumUtil.dynamicXpath(gamePlanGetStartedBtn, gamePlanName), driver).click();
		Reporter.log("Clicked on 'Get Started' button of " + gamePlanName +" game plan...");
	}

	/**
	 * Verify Eat well heading is displayed on Your Target page after staring the plan
	 */
	public boolean verifyEatWellHeadingOnYourTargetPage(){

		boolean isPresnt;
		isPresnt = SeleniumUtil.element(eatWellHeadingOnYourTarget, driver).isDisplayed();
		Reporter.log("Verify Eat Well game plan page after starting the plan...");
		return isPresnt;
	}

	/**
	 * Verify 'Set your goal below' text is present before user sets any goal
	 */
	public boolean verifyEatWell_SetYourGoalText(){

		boolean isPresent;
		isPresent = SeleniumUtil.element(setYourGoalEatWell, driver).isDisplayed();
		return isPresent;
	}

	/**
	 * Verify Level progress drop down. Check only level 1 is enabled and other 2 levels are locked. 
	 * Also verify Level Progress tooltip icon and it's text on Your Target page
	 */
	public boolean verifyEnabledandLockedLevels(String enabledLevel1, String lockedLevel2, String lockedLevel3)
	{
		SeleniumUtil.waitForElementToDisappear(eatWellLoader, driver);
		SeleniumUtil.waitForElementToBeVisible(levelProgressHeading, driver);
		boolean isPresent = false;
		SeleniumUtil.sleep(2);
		SeleniumUtil.hover(levelDropdownArrow, driver);

		if(SeleniumUtil.element((SeleniumUtil.dynamicXpath(enabledLevel, enabledLevel1)),driver).isDisplayed() &&
				SeleniumUtil.element(SeleniumUtil.dynamicXpath(lockedLevels, lockedLevel2), driver).isDisplayed() &&
				SeleniumUtil.element(SeleniumUtil.dynamicXpath(lockedLevels, lockedLevel3), driver).isDisplayed())
		{
			isPresent = true;
		}

		Reporter.log("Verified Level progress after completing various levels...");
		return isPresent;
	}

	/**
	 * Verify Level progress drop down. Check only level 1 and level 2 are enabled and level 3 is locked. 
	 * Also verify Level Progress tooltip icon and it's text on Your Target page
	 */
	public boolean verifyTwoEnabledLevels(String enabledLevel1, String enabledLevel2, String lockedLevel3){
		SeleniumUtil.waitForElementToBeVisible(levelProgressHeading, driver);
		boolean isPresent = false;
		SeleniumUtil.sleep(2);
		SeleniumUtil.hover(levelDropdownArrow, driver);

		if(SeleniumUtil.element((SeleniumUtil.dynamicXpath(enabledLevel, enabledLevel1)),driver).isDisplayed() &&
				SeleniumUtil.element(SeleniumUtil.dynamicXpath(enabledLevel, enabledLevel2), driver).isDisplayed() &&
				SeleniumUtil.element(SeleniumUtil.dynamicXpath(lockedLevels, lockedLevel3), driver).isDisplayed())
		{
			isPresent = true;
		}

		Reporter.log("Verified Level progress after completing various levels...");
		return isPresent;
	}

	/**
	 * Verify all the 3 levels are enabled after completing level 2
	 */
	public boolean verifyAllLevelsAreEnabled(String enabledLevel1, String enabledLevel2, String enabledLevel3){
		SeleniumUtil.waitForElementToBeVisible(levelProgressHeading, driver);
		boolean isPresent = false;
		SeleniumUtil.sleep(2);
		SeleniumUtil.hover(levelDropdownArrow, driver);

		if(SeleniumUtil.element((SeleniumUtil.dynamicXpath(enabledLevel, enabledLevel1)),driver).isDisplayed() &&
				SeleniumUtil.element(SeleniumUtil.dynamicXpath(enabledLevel, enabledLevel2), driver).isDisplayed() &&
				SeleniumUtil.element(SeleniumUtil.dynamicXpath(enabledLevel, enabledLevel3), driver).isDisplayed())
		{
			isPresent = true;
		}

		Reporter.log("Verified Level progress after completing various levels...");
		return isPresent;
	}
	/**
	 * Verify Level Progress tooltip icon and it's text on Your Target page
	 */
	public boolean verifyLevelProgressToolTipIconAndText(){

		SeleniumUtil.element(levelProgressHeading, driver).click();

		boolean isPresent = false;
		SeleniumUtil.sleep(3);
		if(SeleniumUtil.element(levelProgressTooltipIcon, driver).isDisplayed())
		{
			SeleniumUtil.element(levelProgressTooltipIcon, driver).click();
			isPresent = SeleniumUtil.element(levelProgressTooltipIconText, driver).isDisplayed();
		}

		return isPresent;
	}

	/**
	 * Click on Get Started button to start the level
	 */
	public void clickGetStartedOrNextLevelBtn(){
		try{
			SeleniumUtil.waitForElementToBePresent(levelNextBtn, driver);
			SeleniumUtil.element(levelNextBtn, driver).click();
			Reporter.log("Click on 'Next' button to start the Level...");
		}
		catch(Exception e){
			SeleniumUtil.element(levelNextBtn, driver).click();
			Reporter.log("An exception occured..");
		}
		SeleniumUtil.sleep(3);
	}

	/**
	 * Choose the goals
	 */
	public void selectRadioBtnForGoalsAndRelatedActivity(){
		SeleniumUtil.sleep(3); 
		SeleniumUtil.waitForElementToBeVisible(chooseYourGoalsList, driver);
		List<WebElement> goalsList	= driver.findElements(chooseYourGoalsList);
		int size = goalsList.size();
		List<Integer> numberList = SeleniumUtil.generateUniqueRandomNumber(size);
		SeleniumUtil.sleep(4);
		for (int i=1; i<=4; i++){
			SeleniumUtil.sleep(4);
			WebElement radioBtn = SeleniumUtil.element(SeleniumUtil.dynamicXpath(chooseYourGoalsRadioBtns, Integer.toString(numberList.get(i))),driver);
			if(numberList.get(i)==size && radioBtn.getText().equalsIgnoreCase("Other")){
				radioBtn.click();
				SeleniumUtil.element(otherInputBox, driver).click();
				SeleniumUtil.sendKeysToWebElement(otherInputBox, "Other data", driver);
			}
			else
			{
				SeleniumUtil.element(SeleniumUtil.dynamicXpath(chooseYourGoalsRadioBtns, Integer.toString(numberList.get(i))),driver).click();
			}
		}
	}

	/**
	 * Verify Edit link for editing goals is appearing on Your Target page after completing goals activity
	 */
	public boolean verifyGoalsEditLinkIsPresent(){
		boolean isPresent = false;
		SeleniumUtil.waitForElementToBeVisible(goalsEditLink, driver);
		if(SeleniumUtil.element(goalsEditLink, driver).isDisplayed() && SeleniumUtil.element(chosenGoalsList, driver).isDisplayed())
		{
			isPresent = true;
		}
		return isPresent;
	}

	/**
	 * Verify goals Edit link is not present after the Game Plan is completed
	 */
	public boolean verifyGoalsEditLinkIsNotPresent(){
		SeleniumUtil.sleep(3);
		boolean isPresent = false;
		new WebDriverWait(driver, 5).until(ExpectedConditions.invisibilityOfElementLocated(goalsEditLink));
		if(SeleniumUtil.isElementDisplayed(goalsEditLink, driver))
		{
			isPresent = false;
		}
		else{
			isPresent = true;
		}
		return isPresent;
	}

	/**
	 * Select checklist for Shop Smart activity
	 */
	public void selectShopSmartChecklist(){
		List<WebElement> goalsList	= driver.findElements(chooseYourGoalsList);
		int size = goalsList.size();
		for(int i = 0;i<size;i++)
		{
			goalsList.get(i).click();
			Reporter.log("Clicked on the radio button to select different goals..");
		}
	}


	/**
	 * Click on Submit button of level
	 */
	public void clickLevelSubmitBtn(){
		SeleniumUtil.sleep(2);
		SeleniumUtil.waitForElementToBeVisible(levelSubmitBtn, driver);	
		SeleniumUtil.element(levelSubmitBtn, driver).click();
		Reporter.log("Clicked on Submit button for submitting the activity...");
	}

	/**
	 * Click on Get Started button appearing on Your target page
	 */
	public void clickGetStartedYourTarget(){
		SeleniumUtil.waitForElementToBeVisible(getStartedButtonYourTarget, driver);
		try{
			SeleniumUtil.element(getStartedButtonYourTarget, driver).click();
		}
		catch(Exception e){
			SeleniumUtil.element(getStartedButtonYourTarget, driver).click();
		}
	}

	/**
	 * Click on Start link of Nutrition Label activity of Level 1
	 */
	public void startActivityOfLevel1(String activityName)
	{
		SeleniumUtil.waitForElementToBeVisible(SeleniumUtil.dynamicXpath(startAndReviewLinkOfLevelActivity,activityName), driver);
		SeleniumUtil.sleep(5);
		SeleniumUtil.element(SeleniumUtil.dynamicXpath(startAndReviewLinkOfLevelActivity,activityName), driver).click();
		Reporter.log("Click on Start link of "+activityName+" label activity...");
	}

	/**
	 * Select an answer for all the questions of an activity having list of radio buttons
	 */
	public void selectAnswerForActivityHavingListOfRadioBtns(int count, By elementToken){

		SeleniumUtil.sleep(2);

		for(int i=0;i<count;i++){

			List<WebElement> answerList = driver.findElements(activityAndPostSurveyQuestion1RadioBtnList);

			int size = answerList.size();

			int random = (int)(Math.random()*size) + 1;
			//				SeleniumUtil.waitForElementToBeVisible(nextArrowIcon, driver);
			SeleniumUtil.sleep(3);

			try{
				switchToDefaultContent();
				driver.switchTo().frame(SeleniumUtil.element(elementToken, driver));
				SeleniumUtil.element(SeleniumUtil.dynamicXpath(activityAndPostSurveyQuestion1RadioBtn, Integer.toString(random)), driver).click();
			}
			catch(Exception e){
				SeleniumUtil.element(SeleniumUtil.dynamicXpath(activityAndPostSurveyQuestion1RadioBtn, Integer.toString(random)), driver).click();
			}
			Reporter.log("Selecting answers of a quiz activity...");
			SeleniumUtil.sleep(2);
			try{
				SeleniumUtil.element(nextArrowIcon, driver).click();
			}
			catch(Exception e){
				System.out.println("This is the last question...");
			}
			//				SeleniumUtil.element(nextArrowIcon, driver).click();
			Reporter.log("Clicked on arrow icon for proceeding to next question...");
		}
	}

	/**
	 * Select an answer for all the questions of an activity having list of radio buttons
	 */
	public void selectAnswerForLevel1ActivityNutritionLabelHavingListOfRadioBtns(int count){
		switchToQuizFrame();
		for(int i=0;i<count;i++){
			SeleniumUtil.sleep(6);
			List<WebElement> answerList = driver.findElements(activityAndPostSurveyQuestion1RadioBtnList);

			int size = answerList.size();
			int random = (int)(Math.random()*size) + 1;
			//	SeleniumUtil.waitForElementToBeVisible(nextArrowIcon, driver);
			SeleniumUtil.sleep(3);
			if(i==0){
				SeleniumUtil.sleep(5);
				SeleniumUtil.element(SeleniumUtil.dynamicXpath(activityAndPostSurveyQuestion1RadioBtn, Integer.toString(random)), driver).click();
				SeleniumUtil.element(SeleniumUtil.dynamicXpath(activityAndPostSurveyQuestion1RadioBtn, Integer.toString(random)), driver).click();
			}
			else{
			
			try
			{
				switchToDefaultContent();
				switchToQuizFrame();
				SeleniumUtil.element(SeleniumUtil.dynamicXpath(activityAndPostSurveyQuestion1RadioBtn, Integer.toString(random)), driver).click();
			}
			catch(Exception e)
			{
				SeleniumUtil.element(SeleniumUtil.dynamicXpath(activityAndPostSurveyQuestion1RadioBtn, Integer.toString(random)), driver).click();
			}
			}
			Reporter.log("Selecting answers of a quiz activity...");
			try{
				SeleniumUtil.element(nextArrowIcon, driver).click();
			}
			catch(Exception e){
				SeleniumUtil.element(nextArrowIcon, driver).click();
			}

			Reporter.log("Clicked on arrow icon for proceeding to next question...");
		}
		switchToDefaultContent();
	}
	
	public void selectAnswerForLevel1ActivityNutritionLabelHavingListOfRadioBtns1(){
		SeleniumUtil.waitForElementToBePresent(quizFrame, driver);
        SeleniumUtil.switchToFrame(quizFrame, driver);
        do{
        clickSurveyOption(activityAndPostSurveyQuestion1RadioBtnList);
        SeleniumUtil.click(nextArrowIcon, driver);
        SeleniumUtil.switchToDefaultContent(driver);
        SeleniumUtil.switchToFrame(quizFrame, driver);
        if(!SeleniumUtil.isElementDisplayed(nextArrowIcon, driver)){
              break;
        }
        }while(SeleniumUtil.isElementDisplayed(nextArrowIcon, driver));
        SeleniumUtil.click(activityCompleteBtn, driver);
        driver.switchTo().defaultContent();
	}
	

	/**
	 * Switch to Quiz Frame
	 */
	public void switchToQuizFrame(){
		SeleniumUtil.switchToFrame(quizFrame, driver);
	}

	/**
	 * Switch to default content
	 */
	public void switchToDefaultContent(){
		SeleniumUtil.switchToDefaultContent(driver);
	}

	/**
	 * Click on Complete button of an activity
	 */
	public void clickActivityCompleteBtn(){
		SeleniumUtil.sleep(4);
		SeleniumUtil.element(activityCompleteBtn, driver).click();
		Reporter.log("Clicked on 'Complete' button of an activity or Post plan survey...");
	}

	/**
	 * Verify Review link is present after completing Nutrition activity
	 */
	public boolean verifyReviewLinkOnActivity(String activityName){
		SeleniumUtil.waitForElementToBeVisible(SeleniumUtil.dynamicXpath(startAndReviewLinkOfLevelActivity,activityName), driver);
		SeleniumUtil.sleep(4);
		boolean isPresent;
		isPresent = SeleniumUtil.element(SeleniumUtil.dynamicXpath(startAndReviewLinkOfLevelActivity,activityName), driver).isDisplayed();
		Reporter.log("Verified if Nutrition activity is marked as complete or not...");
		return isPresent;
	}

	/**
	 * Click on Start link of Shop Smart(Eat Well), Power Up!(Get moving) activity of Level 1
	 */
	public void startOtherActivitiesOfGPLevel(String activityName){
		SeleniumUtil.sleep(6);
		SeleniumUtil.waitForElementToBeVisible(SeleniumUtil.dynamicXpath(startAndReviewLnkOfActivity,activityName), driver);
		SeleniumUtil.element(SeleniumUtil.dynamicXpath(startAndReviewLnkOfActivity, activityName), driver).click();
		Reporter.log("Clicked on Start button of " + activityName+ " activity");
	}

	/**
	 * Click on Submit button of Shop Smart Activity
	 */
	public void clickOnActivitySubmitBtn(){
		SeleniumUtil.waitForElementToBeVisible(activitySubmitBtn, driver);
		SeleniumUtil.element(activitySubmitBtn, driver).click();
		Reporter.log("Clicked on Sublit button of an activity");
	}

	/**
	 * Verify level 2 is started after level1 completion 
	 */
	public boolean verifyLevel2Started(String levelHeading){
		SeleniumUtil.sleep(3);
		boolean isPresent = false;
		if(SeleniumUtil.element(levelProgressHeading, driver).isDisplayed()){
			isPresent = SeleniumUtil.element(levelProgressHeading, driver).getText().equalsIgnoreCase(levelHeading);
		}		

		logger.debug(levelHeading +" heading is displayed after subsequent levels are completed...");
		return isPresent;
	}

	/**
	 * Click on Add New Row link to add a new row in meal plan
	 */
	public void clickOnAddNewRow(int count){
		String value = Integer.toString(count);
		SeleniumUtil.waitForElementToBeVisible(SeleniumUtil.dynamicXpath(addNewRowLnk, value), driver);
		try{
			SeleniumUtil.element(SeleniumUtil.dynamicXpath(addNewRowLnk, value), driver).click();
		}
		catch(Exception e){
			SeleniumUtil.element(SeleniumUtil.dynamicXpath(addNewRowLnk, value), driver).click();
		}
		Reporter.log("Clicked on the Add new row link identified by "+ addNewRowLnk);
	}

	/**
	 * Filling the data in columnn format activity(1 and 3 column)[Meal Planning/Looking in the Mirror activity]
	 */
	public void fillingMealPlanningDetailsInLevel2(){

		List<WebElement> mealPlansList = driver.findElements(mealPlanningTable);
		int size = mealPlansList.size();

		for(int i=0;i<size;i++){
			String j = Integer.toString(i+1);

			if(!(SeleniumUtil.element(SeleniumUtil.dynamicXpath(textareaList, j), driver).getAttribute("value").equalsIgnoreCase(""))){
				continue;
			}

			else{
				SeleniumUtil.waitForElementToBeVisible(SeleniumUtil.dynamicXpath(textareaList, j), driver);
				SeleniumUtil.element(SeleniumUtil.dynamicXpath(textareaList, j), driver).click();
				SeleniumUtil.element(SeleniumUtil.dynamicXpath(textareaList, j), driver).clear();
				SeleniumUtil.element(SeleniumUtil.dynamicXpath(textareaList, j), driver).sendKeys("testData" + j);
				Reporter.log("Filled the data in column input fields..");
			}
		}
	}

	/**
	 * Click on Start link of Your Why activity of Level 2
	 */
	public void startYourWhyActivityOfLevel2(){
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		SeleniumUtil.sleep(4);
		SeleniumUtil.waitForElementToBeVisible(level2StartAndReviewLnkYourWhyActivity, driver);
		SeleniumUtil.element(level2StartAndReviewLnkYourWhyActivity, driver).click();
		Reporter.log("Clicked on Start link of Your Why activity");
	}	

	/**
	 * Click on Next arrow for loadiTng more activities
	 */
	public void clickOnNextArrowToLoadMoreActivities(){
		SeleniumUtil.sleep(5);
		SeleniumUtil.waitForElementToBeVisible(nextArrowBtnForChangingActivity, driver);
		SeleniumUtil.element(nextArrowBtnForChangingActivity, driver).click();
		Reporter.log("Clicked on Next arror button to load more activities..");
	}

	/**
	 * Click on Start link of Overcome Veggie Hurdles activity of Level 2
	 */
	public void startOvercomeVeggieHurdleActivityOfLevel2(){
		SeleniumUtil.waitForElementToBeVisible(level2StartAndReviewLnkOvercomeVeggieActivity, driver);
		SeleniumUtil.element(level2StartAndReviewLnkOvercomeVeggieActivity, driver).click();
		Reporter.log("Clicked on Start link of Overcome Veggie Hurdle activity");
	}	

	/**
	 * Click on Next buttons in Overcome Veggie Hurdle activity
	 */
	public void clickOnNextAndSubmitBtnsOfLevelActivity(int count){

		for(int i=0;i<count;i++){
			String j = Integer.toString(i+2);
			SeleniumUtil.waitForElementToBeVisible(SeleniumUtil.dynamicXpath(nextButtonsInLevelActivity, j), driver);
			SeleniumUtil.element(SeleniumUtil.dynamicXpath(nextButtonsInLevelActivity, j), driver).click();
			Reporter.log("Clicked on Next button of an activity...");
		}
	}

	/**
	 * Click on Submit button of Overcome Veggie Hurdle Activity
	 */
	public void clickSubmitOfOvercomeVeggieHurdleActivity(){
		SeleniumUtil.sleep(2);
		SeleniumUtil.waitForElementToBeVisible(level2OvercomeVeggieSubmitBtn, driver);
		SeleniumUtil.element(level2OvercomeVeggieSubmitBtn, driver).click();
		Reporter.log("Clicked on Next and Submit button of Overcome Veggie Hurdle activity");
	}

	/**
	 * Complete the activity 'What's it worth' of Level 3 by selecting radio buttons for different questions
	 */
	public void completeActivityHavingQuizInTabularForm(){
		List<WebElement> itemsList = driver.findElements(level3WhatsItWorthActivityRows);
		int size = itemsList.size()-1;
		List<WebElement> colList = driver.findElements(tableQuizActivityColumns);
		int colSize = colList.size();
		for(int i=0;i<size;i++){
			SeleniumUtil.sleep(4);
			String j = Integer.toString(i+2);
			int random = (int)(Math.random()*colSize) + 1;
			if(i==0){
				SeleniumUtil.element(SeleniumUtil.dynamicXpath(level3WhatsItWorthActivityRadioButtons, j, Integer.toString(random)), driver).click();
				SeleniumUtil.element(SeleniumUtil.dynamicXpath(level3WhatsItWorthActivityRadioButtons, j, Integer.toString(random)), driver).click();
			}
			else{
				SeleniumUtil.element(SeleniumUtil.dynamicXpath(level3WhatsItWorthActivityRadioButtons, j, Integer.toString(random)), driver).click();
			}
			Reporter.log("Selecting the radio button for various questions appearing in tabular form...");
		}
	}


	/**
	 * Click on 'Next arrow icon' of an activity for proceeding to next question
	 */
	public void clickOnNextArrowIcon(){

		SeleniumUtil.switchToFrame(quizFrame, driver);
		SeleniumUtil.element(nextArrowIcon, driver).click();
		Reporter.log("Clicked on Next arrow icon after answering questions related to food items...");
		SeleniumUtil.switchToDefaultContent(driver);
	}
	/**
	 * Click on Complete button of the level activity
	 */
	public void clickOnCompleteButton(){

		SeleniumUtil.switchToFrame(quizFrame, driver);
		SeleniumUtil.element(activityCompleteBtn, driver).click();
		Reporter.log("Clicked on Complete button of Whats It Worth activity...");
		SeleniumUtil.switchToDefaultContent(driver);
	}
	/**
	 * Click on Start/Review button of Picture This! activity
	 */
	public void startPictureThisActivityOfLevel3(){
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		SeleniumUtil.sleep(8);
		SeleniumUtil.element(level3StartAndReviewLnkPictureThisActivity, driver).click();
		Reporter.log("Clicked on Start link of Picture This! activity...");
	}

	/**
	 * Complete Picture This! activity
	 */
	public void completePictureThisActivityOfLevel3(int count){

		for(int i=0;i<count;i++){
			String j = Integer.toString(i+1);
			SeleniumUtil.sleep(3);
			SeleniumUtil.element(level3DocumentUploadInputOfPictureThisActivity, driver).sendKeys(System.getProperty("user.dir")+ "\\src\\test\\resources\\Image"+j+".jpg");
			Reporter.log("Uploaded the image for Picture This! activity of level3...");
			SeleniumUtil.sleep(8);
//			SeleniumUtil.waitForElementToBeVisible(level3UploadBtnOfPictureThisActivity, driver);
			SeleniumUtil.element(level3UploadBtnOfPictureThisActivity, driver).click();
			Reporter.log("Clicked on Upload button for uploading the files...");
			SeleniumUtil.sleep(3);
			SeleniumUtil.waitForElementToBeVisible(SeleniumUtil.dynamicXpath(level3TitleInputOfPictureThisActivity, j), driver);
			SeleniumUtil.waitForElementToBeVisible(SeleniumUtil.dynamicXpath(level3TitleInputOfPictureThisActivity, j), driver);
			SeleniumUtil.element(SeleniumUtil.dynamicXpath(level3TitleInputOfPictureThisActivity, j), driver).click();
			SeleniumUtil.element(SeleniumUtil.dynamicXpath(level3TitleInputOfPictureThisActivity, j), driver).sendKeys("UploadImage"+j);
			Reporter.log("Filled the comments/title for uploaded image...");
		}
	}

	/**
	 * Click on Start/Review button of Portion Distortion activity of level 3
	 */
	public void startPortionDistortionActivityOfLevel3(){
		SeleniumUtil.sleep(8);
		SeleniumUtil.element(level3StartAndReviewLnkPortionDistortionActivity, driver).click();
		Reporter.log("Clicked on Start link of Portion Distortion activity...");
	}

	/**
	 * Complete Portion Distortion activity
	 */
	public void completePortionDistortionActivity(){
		selectRadioBtnForGoalsAndRelatedActivity();
		clickOnActivitySubmitBtn();
	}

	/**
	 * Submit the Eat Well game plan
	 */
	public void submitEatWellGamePlan(){
		SeleniumUtil.element(submitEatWellGamePlan, driver).click();
		Reporter.log("Clicked on Submit button to submit the game plan...");
	}

	/**
	 * Filling the post survey form
	 */
	public void fillPostSurveyForm(int count,int radioQuestions){

		SeleniumUtil.switchToFrame(postPlanSurveyFrame, driver);

		//Selecting answer for first 2 questions
		for(int i=0;i<radioQuestions;i++)
		{
			switchToDefaultContent();
			SeleniumUtil.switchToFrame(postPlanSurveyFrame, driver);
			List<WebElement> answerList = driver.findElements(activityAndPostSurveyQuestion1RadioBtnList);
			int size = answerList.size()-1;
			int random = (int)(Math.random()*size) + 1;
			try{
				SeleniumUtil.element(SeleniumUtil.dynamicXpath(activityAndPostSurveyQuestion1RadioBtn, Integer.toString(random)), driver).click();
			}
			catch(Exception e){
				SeleniumUtil.element(SeleniumUtil.dynamicXpath(activityAndPostSurveyQuestion1RadioBtn, Integer.toString(random)), driver).click();
			}
			Reporter.log("Selecting answers for Post survey question...");

			SeleniumUtil.element(nextArrowIcon, driver).click();
		}

		//Filling the answers for a tabular question
		List<WebElement> foodItemList = driver.findElements(level3WhatsItWorthActivityRows);
		int size1 = foodItemList.size()-1;
		switchToDefaultContent();
		SeleniumUtil.switchToFrame(postPlanSurveyFrame, driver);
		List<WebElement> colList = driver.findElements(tableQuizActivityColumns);
		int colSize = colList.size();

		for(int k=0;k<size1;k++){
			String j = Integer.toString(k+2);
			int random1 = (int)(Math.random()*colSize) + 1;
			SeleniumUtil.element(SeleniumUtil.dynamicXpath(level3WhatsItWorthActivityRadioButtons, j, Integer.toString(random1)), driver).click();
		}

		SeleniumUtil.element(nextArrowIcon, driver).click();

		//Selecting the answer from a list of radio buttons for 4th question

		List<WebElement> answerList = driver.findElements(activityAndPostSurveyQuestion1RadioBtnList);
		int size = answerList.size();

		int random = (int)(Math.random()*size) + 1;
		switchToDefaultContent();
		SeleniumUtil.switchToFrame(postPlanSurveyFrame, driver);
		SeleniumUtil.element(SeleniumUtil.dynamicXpath(activityAndPostSurveyQuestion1RadioBtn, Integer.toString(random)), driver).click();

		Reporter.log("Selecting answers for Post survey question...");

		SeleniumUtil.element(nextArrowIcon, driver).click();

		//	Filling the answers for a tabular question	
		List<WebElement> colList1 = driver.findElements(tableQuizActivityColumns);
		int colSize1 = colList.size();
		for(int i=0;i<count;i++)
		{
			List<WebElement> itemList = driver.findElements(level3WhatsItWorthActivityRows);
			int size2 = itemList.size()-1;
			for(int k=0;k<size2;k++){
				String j = Integer.toString(k+2);
				int random1 = (int)(Math.random()*colSize1) + 1;
				switchToDefaultContent();
				SeleniumUtil.switchToFrame(postPlanSurveyFrame, driver);
				SeleniumUtil.element(SeleniumUtil.dynamicXpath(level3WhatsItWorthActivityRadioButtons, j, Integer.toString(random1)), driver).click();
			}
			switchToDefaultContent();
			SeleniumUtil.switchToFrame(postPlanSurveyFrame, driver);
			SeleniumUtil.element(nextArrowIcon, driver).click();
		}

		//		//Selecting the answer from a list of radio buttons for 6th question
		//		try{
		//			selectAnswerForActivityHavingListOfRadioBtns(count);
		//		}
		//		catch(Exception e){
		//			clickActivityCompleteBtn();
		//		}
		SeleniumUtil.switchToDefaultContent(driver);
	}

	/**
	 * Switch to post plan survey frame
	 */
	public void switchToPostPlanSurveyFrame(){

		SeleniumUtil.switchToFrame(postPlanSurveyFrame, driver);
	}

	/**
	 * Select answers for the question appearing on last panel of Get Moving post plan survey
	 */
	public void fillTheLastPanelOfGetMovingPostSurvey(){

		SeleniumUtil.switchToFrame(postPlanSurveyFrame, driver);
		int random = (int)(Math.random() * 8) + 1;

		Random num = new Random();
		int value = num.nextInt(21-9+1)+9;

		SeleniumUtil.element(SeleniumUtil.dynamicXpath(activityAndPostSurveyQuestion1RadioBtn, Integer.toString(random)), driver).click();

		SeleniumUtil.element(SeleniumUtil.dynamicXpath(activityAndPostSurveyQuestion1RadioBtn, Integer.toString(value)), driver).click();

		Reporter.log("Selecting answers of a quiz activity...");

		clickActivityCompleteBtn();

		switchToDefaultContent();
	}

	/**
	 * Click on Submit button of 'How Intense Is It?' activity of Get Moving game plan
	 */
	public void clickOnSubmitButtonOfHowIntenseActivity(){
		SeleniumUtil.sleep(3);
		SeleniumUtil.waitForElementToBeVisible(activityHowIntenseIsItSubmitBtn, driver);
		SeleniumUtil.element(activityHowIntenseIsItSubmitBtn, driver).click();
		Reporter.log("Clicked on Submit button of 'How Intense Is it?' activity of Get Moving game plan...");
	}

	/**
	 * Click on Next buttons in How Intense Is It?(Get Moving), Power Up!(Get Moving) activity
	 */
	public void clickOnNextBtnOfActivity(int count){

		for(int i=0;i<count;i++){
			String j = Integer.toString(i+1);
			try{
				SeleniumUtil.waitForElementToBeVisible(SeleniumUtil.dynamicXpath(nextButtonsInLevelActivity, j), driver);
				SeleniumUtil.element(SeleniumUtil.dynamicXpath(nextButtonsInLevelActivity, j), driver).click();
				Reporter.log("Clicked on Next button of an activity...");
			}
			catch(StaleElementReferenceException e){
				SeleniumUtil.element(SeleniumUtil.dynamicXpath(nextButtonsInLevelActivity, j), driver).click();
				Reporter.log("Clicked on Next button of an activity...");
			}
		}
	}

	/**
	 * Click on Submit button of 'Power Up!' activity of Get Moving game plan
	 */
	public void clickOnSubmitButtonOfPowerUpActivity(){
		driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
		SeleniumUtil.waitForElementToBeVisible(activityPowerUpSubmitBtn, driver);
		SeleniumUtil.element(activityPowerUpSubmitBtn, driver).click();
		Reporter.log("Clicked on Submit button of 'Power Up!' activity of Get Moving game plan...");
	}

	/**
	 * Click on Submit button of 'Winning the War' activity of Get Moving game plan
	 */
	public void clickOnSubmitButtonOfWinningTheWarActivity(){

		SeleniumUtil.element(activityWinningTheWarSubmitBtn, driver).click();
		Reporter.log("Clicked on Submit button of 'Winning the War' activity of Get Moving game plan...");
	}

	/**
	 * Click on Submit button of 'Winning the War' activity of Get Moving game plan
	 */
	public void clickOnSubmitButtonOfFuelForFitnessActivity(){
		SeleniumUtil.sleep(3);
		SeleniumUtil.waitForElementToBeVisible(activityFuelForFitnessSubmitBtn, driver);
		SeleniumUtil.element(activityFuelForFitnessSubmitBtn, driver).click();
		Reporter.log("Clicked on Submit button of 'Fuel For Fitness' activity of Get Moving game plan...");
	}

	/**
	 * Click on Continue button for proceeding to next level
	 */
	public void clickOnContinueBtn(){
		SeleniumUtil.waitForElementToBeVisible(continueBtnForProceedingToNextLevel, driver);
		SeleniumUtil.element(continueBtnForProceedingToNextLevel, driver).click();
		Reporter.log("Clicked on Continue button for proceeding to next level...");
	}

	/**
	 * Complete panel 2 of 'Plan It!' activity of Get Moving game plan
	 */
	public void enterDetailsInPlanItPanel(int size){
		for(int i=0;i<size;i++){
			String j = Integer.toString(i+1);

			if(!(SeleniumUtil.element(SeleniumUtil.dynamicXpath(textareaList, j), driver).getAttribute("value").equalsIgnoreCase(""))){
				continue;
			}

			else{
				SeleniumUtil.sleep(3);
				SeleniumUtil.waitForElementToBePresent(SeleniumUtil.dynamicXpath(textareaList, j), driver);
				SeleniumUtil.element(SeleniumUtil.dynamicXpath(textareaList, j), driver).click();
				SeleniumUtil.element(SeleniumUtil.dynamicXpath(textareaList, j), driver).clear();
				SeleniumUtil.element(SeleniumUtil.dynamicXpath(textareaList, j), driver).sendKeys("testData" + j);
				Reporter.log("Filled the data for Plan It! activity...");
			}
		}
	}

	/**
	 * Complete panel 3 of 'Plan It!' activity of Get Moving game plan
	 */
	public void enterDetailsInActivityHavingInputColumns(){
		List<WebElement> mealPlansList = driver.findElements(mealPlanningTable);
		int size = mealPlansList.size()-1;

		for(int i=0;i<size;i++){
			String j = Integer.toString(i+2);

			if(!(SeleniumUtil.element(SeleniumUtil.dynamicXpath(textareaList, j), driver).getAttribute("value").equalsIgnoreCase(""))){
				continue;
			}

			else{
				SeleniumUtil.element(SeleniumUtil.dynamicXpath(textareaList, j), driver).click();
				SeleniumUtil.element(SeleniumUtil.dynamicXpath(textareaList, j), driver).clear();
				SeleniumUtil.element(SeleniumUtil.dynamicXpath(textareaList, j), driver).sendKeys("testData" + j);
				Reporter.log("Filled the data for Plan It! activity...");
			}
		}
	}

	/**
	 * Complete the post survey form of Eat Well game plan 
	 */
	public void completeEaTwellPostPlanSurvey(){
		submitGamePlan();
		fillPostSurveyForm(2,2);
		switchToPostPlanSurveyFrame();
		selectAnswerForActivityHavingListOfRadioBtns(2,postPlanSurveyFrame);
		clickActivityCompleteBtn();
		switchToDefaultContent();
	}

	/**
	 * Complete How Intense Is It activity of Get Moving game plan
	 */
	public void startAndCompleteHowIntenseIsItActivity(String activityName,int count){
		startActivityOfLevel1(activityName);
		switchToQuizFrame();
		completeActivityHavingQuizInTabularForm();
		switchToDefaultContent();
		clickOnCompleteButton();
		clickOnNextBtnOfActivity(count);
		clickOnSubmitButtonOfHowIntenseActivity();
	}

	/**
	 * Complete Goals selection activity of Level 1 of Get Moving game plan
	 */
	public void startAndCompleteGoalsActivityOfGetMovingPlan(){
		clickGetStartedOrNextLevelBtn();
		selectRadioBtnForGoalsAndRelatedActivity();
		clickLevelSubmitBtn();
	}

	/**
	 * Complete Power Up! activity of Level 1 of Get Moving game plan
	 */
	public void startAndCompletePowerUpActivity(String activityName,int count){
		SeleniumUtil.sleep(5);
		startOtherActivitiesOfGPLevel(activityName);
		clickOnNextBtnOfActivity(count);
		clickOnSubmitButtonOfPowerUpActivity();
		clickOnContinueBtn();
	}

	/**
	 * Complete the 'How active are you' activity of level 2 of Get Moving game plan
	 */
	public void startAndCompleteHowActiveAreYouActivity(int count){
		SeleniumUtil.sleep(10);
		SeleniumUtil.waitForElementToBeVisible(quizFrame, driver);
		switchToQuizFrame();
		selectAnswerForActivityHavingListOfRadioBtns(count,quizFrame);
		clickActivityCompleteBtn();
		switchToDefaultContent();
		clickOnActivitySubmitBtn();
	}

	/**
	 * Start and complete 'Your Why' activity of Eat Well and Get Moving game plan.
	 */
	public void startAndCompleteYourWhyActivity(){
		SeleniumUtil.sleep(3);
		startYourWhyActivityOfLevel2();
		selectRadioBtnForGoalsAndRelatedActivity();
		clickOnActivitySubmitBtn();
	}

	/**
	 * Start and complete 'Sit Less, Move More' activity of Get Moving game plan
	 */
	public void startAndCompleteSitLessMoveMoreActivity(String activityName, int count){
		clickOnNextArrowToLoadMoreActivities();
		SeleniumUtil.sleep(3);
		startOtherActivitiesOfGPLevel(activityName);
		clickOnNextBtnOfActivity(8);
		selectRadioBtnForGoalsAndRelatedActivity();
		clickOnSubmitButtonOfPowerUpActivity();
		clickOnContinueBtn();
	}

	/**
	 * Complete the 'Plan It!' activity of level 3
	 */
	public void startAndCompletePlanItActivity(int count){
		SeleniumUtil.sleep(6);
		clickOnNextBtnOfActivity(count);
		enterDetailsInPlanItPanel(1);
		clickOnNextAndSubmitBtnsOfLevelActivity(1);
		clickOnAddNewRow(2);
		enterDetailsInActivityHavingInputColumns();
		clickOnSubmitButtonOfHowIntenseActivity();
	}

	/**
	 * Start and complete the 'Winning the War' activity of Get Moving game plan
	 */
	public void startAndCompleteWinningTheWarActivity(String activityName){
		SeleniumUtil.waitForElementToBePresent(SeleniumUtil.dynamicXpath(startAndReviewLinkOfLevelActivity,activityName), driver);
		startActivityOfLevel1(activityName);
		clickOnNextBtnOfActivity(5);
		enterDetailsInPlanItPanel(2);
		clickOnSubmitButtonOfWinningTheWarActivity();
	}

	/**
	 * Start and complete 'Fuel for Fitness' activity of Get Moving game plan
	 */
	public void startAndCompleteFuelForFitnessActivity(String activityName){
		SeleniumUtil.waitForElementToBePresent(nextArrowBtnForChangingActivity, driver);
		clickOnNextArrowToLoadMoreActivities();
		startOtherActivitiesOfGPLevel(activityName);
		clickOnNextBtnOfActivity(3);
		clickOnSubmitButtonOfFuelForFitnessActivity();
	}

	/**
	 * Submit the Get Moving game plan
	 */
	public void submitGamePlan(){
		SeleniumUtil.sleep(3);
		SeleniumUtil.waitForElementToBeVisible(submitGamePlan, driver);
		SeleniumUtil.element(submitGamePlan, driver).click();
		//		SeleniumUtil.element(submitEatWellGamePlan, driver).click();
		Reporter.log("Clicked on Submit button to submit the game plan...");
	}

	/**
	 * Enter the value in Post-Plan survey of Get moving game plan
	 */
	public void enterValueInTextareaOfGetMovingPostPlanSurvey(String value){
		SeleniumUtil.switchToFrame(postPlanSurveyFrame, driver);
		SeleniumUtil.element(inputTextInGetMovingPostSurvey, driver).click();
		SeleniumUtil.element(inputTextInGetMovingPostSurvey, driver).sendKeys(value);
		SeleniumUtil.element(nextArrowIcon, driver).click();
		SeleniumUtil.switchToDefaultContent(driver);
	}

	/**
	 * Complete the post survey form of Get Moving game plan
	 */
	public void completeGetMovingPostPlanSurvey(int count){
		fillPostSurveyForm(1,2);
		selectAnswerForActivityHavingListOfRadioBtns(count, postPlanSurveyFrame);
		switchToDefaultContent();
		switchToPostPlanSurveyFrame();
		//		clickActivityCompleteBtn();
		switchToDefaultContent();
		enterValueInTextareaOfGetMovingPostPlanSurvey("30");
		SeleniumUtil.switchToFrame(postPlanSurveyFrame, driver);
		selectAnswerForActivityHavingListOfRadioBtns(count,postPlanSurveyFrame);
		switchToDefaultContent();
		fillTheLastPanelOfGetMovingPostSurvey();
	}	

	/**
	 * Enter current weight in Level 1 goal activity of Manage Your Weight Game plan
	 */
	private void enterCurrentWeight() {
		SeleniumUtil.waitForElementToBeVisible(inputTetxCurrentWeightOfManageWeightLevel1, driver);
		SeleniumUtil.element(inputTetxCurrentWeightOfManageWeightLevel1, driver).sendKeys("150");
	}

	/**
	 * 
	 */
	private void selectWeightLossPercentage(){
		List<WebElement> weightLossList = driver.findElements(weightLossPercentageList);
		int size = weightLossList.size();
		int random = (int)(Math.random()*size) + 1;
		SeleniumUtil.element(SeleniumUtil.dynamicXpath(weightLossPercentOption, Integer.toString(random)), driver).click();
	}

	/**
	 * Click on the Submit button of 'Your weight Goals' activity
	 */
	public void clickOnChooseWeightGoalsActivitySubmitBtn(){
		SeleniumUtil.waitForElementToBeVisible(chooseWeightGoalsSubmitBtn, driver);
		SeleniumUtil.element(chooseWeightGoalsSubmitBtn, driver).click();
	}

	/**
	 * Click on Submit button of 'Metabolism Basics' activity of Manage Your Weight game plan
	 */
	public void clickOnSubmitButtonOfMetabolismBasicsActivity(){
		SeleniumUtil.waitForElementToBeVisible(activityMetabolismBasicsSumitBtn, driver);
		SeleniumUtil.element(activityMetabolismBasicsSumitBtn, driver).click();
		Reporter.log("Clicked on Submit button of 'Metabolism Basics' activity of Manage Your Weight game plan...");
	}

	/**
	 * Verify spotlight icon for an activity is displayed
	 */
	public boolean verifySpotlighticonForAnActivity(String activityName){
		SeleniumUtil.sleep(3);
		boolean isPresent = false;
		SeleniumUtil.waitForElementToBeVisible(SeleniumUtil.dynamicXpath(startAndReviewLnkOfActivity,activityName), driver);
		isPresent = SeleniumUtil.element(SeleniumUtil.dynamicXpath(spotlightIconOfYourWhyWeightActivity, activityName), driver).isDisplayed();
		return isPresent;
	}

	/**
	 * Fetch the name of spotlight activity
	 */
	public String fetchSpotlightActivityName(){
		String activityName = SeleniumUtil.element(spotlightactivityNameOnDashboard, driver).getText();
		return activityName;
	}

	/**
	 * Click on Spotlight activity appearing on Dashboard page
	 */
	public void clickOnSpotlightActivity(){
		SeleniumUtil.element(spotlightactivityNameOnDashboard, driver).click();
	}

	/**
	 * Verify user is redirected to Spotlight activity on 'Your Target' page
	 */
	public boolean verifyUserRedirectsToSpotlightActivity(String expected){
		boolean isCorrect;
		String actual = SeleniumUtil.element(spotlightActivityNameOnYourTargetPage, driver).getText();
		if(actual.equalsIgnoreCase(expected)){
			isCorrect = true;
		}
		else{
			isCorrect = false;
		}
		return isCorrect;
	}

	/**
	 * Click on Next button appearing after completing quiz of Emotional eating activity 
	 */
	public void clickOnNextBtnOfEmotionalEatingActivity(){
	
		SeleniumUtil.waitForElementToBeVisible(emotionalEatingNextButton, driver);
		SeleniumUtil.element(emotionalEatingNextButton, driver).click();
		Reporter.log("Clicked on Next button of an activity...");
		
	}

	/**
	 * Start and complete 'Your Weight Goal' activity of Manage Your weight plan
	 */
	public void startAndCompleteGoalsActivityOfManageYourWeightplan(int count){
		clickOnNextBtnOfActivity(count);
		enterCurrentWeight();
		selectWeightLossPercentage();
		clickOnChooseWeightGoalsActivitySubmitBtn();
	}

	/**
	 * Start and complete Metabolism Basics activity of Level 1 of Manage Your Weight plan
	 */
	public void startAndCompleteMetabolismBasicsActivity(String activityName, int count){
		startActivityOfLevel1(activityName);
		clickOnNextBtnOfActivity(count);
		clickOnSubmitButtonOfMetabolismBasicsActivity();
	}

	/**
	 * Start and complete 'Your Why Weight' activity of Level 1 of Manage Your Weight plan 
	 */
	public void startAndCompleteYourWhyWeightActivity(String activityName){
		startOtherActivitiesOfGPLevel(activityName);
		selectRadioBtnForGoalsAndRelatedActivity();
		clickOnActivitySubmitBtn();
		clickOnContinueBtn();
	}

	/**
	 * Start and complete 'Emotional Eating' activity of Level 2 of 'Manage Your Weight' plan
	 */
	public void startAndCompleteEmotionalEatingActivity(int count,int num){
		clickOnNextBtnOfActivity(count);
		selectAnswerForLevel1ActivityNutritionLabelHavingListOfRadioBtns(num);
		switchToQuizFrame();
		clickActivityCompleteBtn();
		switchToDefaultContent();
		clickOnNextBtnOfEmotionalEatingActivity();
		clickOnSubmitButtonOfHowIntenseActivity();
	}

	/**
	 * Start and complete 'Looking in the Mirror' activity of Level 2 of 'Manage Your Weight' plan
	 */
	public void startAndCompleteLookingInTheMirrorActivity(String activityName, int count){
		clickOnNextArrowToLoadMoreActivities();
		startOtherActivitiesOfGPLevel(activityName);
		clickOnNextBtnOfActivity(count);
		completePictureThisActivityOfLevel3(count);
		clickOnNextBtnOfEmotionalEatingActivity();
		fillingMealPlanningDetailsInLevel2();
		clickOnSubmitButtonOfHowIntenseActivity();
		clickOnContinueBtn();
	}

	/**
	 * Complete the quiz of 'Exercise and Calories' activity of level 3 of 'Manage Yoyur Weight' game plan
	 */
	public void completePanel2OfExerciseAndCaloriesActivity(){
		switchToQuizFrame();
		int answer1 = (int)(Math.random() * 4) + 1;

		Random random2 = new Random();
		int answer2 = random2.nextInt(8-5+1)+5;

		Random random3 = new Random();
		int answer3 = random3.nextInt(12-9+1)+9;

		Random random4 = new Random();
		int answer4 = random4.nextInt(16-13+1)+13;
		SeleniumUtil.waitForElementToBeVisible(SeleniumUtil.dynamicXpath(activityAndPostSurveyQuestion1RadioBtn, Integer.toString(answer1)), driver);
		SeleniumUtil.element(SeleniumUtil.dynamicXpath(activityAndPostSurveyQuestion1RadioBtn, Integer.toString(answer1)), driver).click();
		SeleniumUtil.element(SeleniumUtil.dynamicXpath(activityAndPostSurveyQuestion1RadioBtn, Integer.toString(answer2)), driver).click();
		SeleniumUtil.element(SeleniumUtil.dynamicXpath(activityAndPostSurveyQuestion1RadioBtn, Integer.toString(answer3)), driver).click();
		SeleniumUtil.element(SeleniumUtil.dynamicXpath(activityAndPostSurveyQuestion1RadioBtn, Integer.toString(answer4)), driver).click();
		switchToDefaultContent();

		Reporter.log("Selecting answers of a quiz activity...");
	}

	/**
	 * Start and complete the 'Food Is Not the Enemy' activity of Level 2 of 'Manage Yoyur Weight' game plan
	 */
	public void startAndCompleteFoodIsNotTheEnemyActivity(String activityName, int count){
		startOtherActivitiesOfGPLevel(activityName);
		clickOnNextBtnOfActivity(count);
		clickOnChooseWeightGoalsActivitySubmitBtn();
	}

	/**
	 * Start and complete the 'Exercise and Calories: The Basics' activity of Level 3 of 'Manage Your Weight' game plan
	 */
	public void startAndCompleteExerciseAndCaloriesActivity(int count){
		clickOnNextBtnOfActivity(count);
		completePanel2OfExerciseAndCaloriesActivity();
		clickOnNextArrowIcon();
		clickOnCompleteButton();
		clickOnChooseWeightGoalsActivitySubmitBtn();
	}

	/**
	 * Start and complete 'Doing More Than Your Body Good' activity of level 3 of 'Manage Your weight' game plan
	 */
	public void startAndCompleteDoingMoreThanYourBodyGoodActivity(String activityName, int count){
		startActivityOfLevel1(activityName);
		clickOnNextBtnOfActivity(count);
		clickOnSubmitButtonOfMetabolismBasicsActivity();
	}

	/**
	 * Start and complete 'Find Your Happy Place' activity of level 3 of 'Manage Your weight' game plan
	 */
	public void startAndCompleteFindYourHappyPlaceActivity(String activityName,int count){
		clickOnNextArrowToLoadMoreActivities();
		clickOnNextArrowToLoadMoreActivities();
		startOtherActivitiesOfGPLevel(activityName);
		clickOnNextBtnOfActivity(count);
		selectRadioBtnForGoalsAndRelatedActivity();
		clickOnChooseWeightGoalsActivitySubmitBtn();
	}

	/**
	 * Select an answer for all the questions of an activity having list of radio buttons
	 */
	public void selectAnswerForActivityHavingListOfRadioBtns1InManageWightPostPlanSurvey(int count, By elementToken){

		SeleniumUtil.sleep(2);

		for(int i=0;i<count;i++){

			List<WebElement> answerList = driver.findElements(activityAndPostSurveyQuestion1RadioBtnList);

			int size = answerList.size();

			int random = (int)(Math.random()*size) + 1;

			SeleniumUtil.sleep(3);
			try{
				switchToDefaultContent();
				driver.switchTo().frame(SeleniumUtil.element(elementToken, driver));
				SeleniumUtil.element(SeleniumUtil.dynamicXpath(activityAndPostSurveyQuestion1RadioBtn, Integer.toString(random)), driver).click();
			}
			catch(Exception e){
				SeleniumUtil.element(SeleniumUtil.dynamicXpath(activityAndPostSurveyQuestion1RadioBtn, Integer.toString(random)), driver).click();
			}
			Reporter.log("Selecting answers of a quiz activity...");
			SeleniumUtil.sleep(2);
			try{
				SeleniumUtil.element(nextArrowIcon, driver).click();
			}
			catch(Exception e){
				System.out.println("This is the last question...");
			}
			Reporter.log("Clicked on arrow icon for proceeding to next question...");
		}
	}
	/**
	 * Complete the post survey form of Manage Your Weight game plan
	 */
	public void completeManageYourWeightPostPlanSurvey(int count){
		fillPostSurveyForm(count,2);
		enterValueInTextareaOfGetMovingPostPlanSurvey("200");
		switchToPostPlanSurveyFrame();
		new Actions(driver).clickAndHold(SeleniumUtil.element(sliderFeetOfPostPlanSurveyOfWeightPlan, driver)).moveByOffset(100,0).release().perform(); 
		new Actions(driver).clickAndHold(SeleniumUtil.element(sliderInchesOfPostPlanSurveyOfWeightPlan, driver)).moveByOffset(100,0).release().perform();
		SeleniumUtil.element(nextArrowIcon, driver).click();
		switchToDefaultContent();
		switchToPostPlanSurveyFrame();
		completeActivityHavingQuizInTabularForm();
		SeleniumUtil.element(nextArrowIcon, driver).click();
		selectAnswerForActivityHavingListOfRadioBtns(2,postPlanSurveyFrame);
		switchToDefaultContent();
		enterValueInTextareaOfGetMovingPostPlanSurvey("20");
		SeleniumUtil.switchToFrame(postPlanSurveyFrame, driver);
		SeleniumUtil.element(nextArrowIcon, driver).click();
		selectAnswerForActivityHavingListOfRadioBtns1InManageWightPostPlanSurvey(3,postPlanSurveyFrame);
		clickActivityCompleteBtn();
		switchToDefaultContent();
	}	

	/**
	 * Start and complete goals activity of Level 1 of Master Stress game plan
	 */
	public void startAndCompleteGoalsActivityOfMasterStressPlan(){
		selectRadioBtnForGoalsAndRelatedActivity();
		clickOnActivitySubmitBtn();
	}

	/**
	 * Start and complete 'Stress and Your Body' activity of level 1 of Master Stress game plan
	 */
	public void startAndCompleteStressYourBodyActivityOfMasterStressPlan(String activityName, int count){
		startOtherActivitiesOfGPLevel(activityName);
		clickOnNextBtnOfActivity(count);
		selectRadioBtnForGoalsAndRelatedActivity();
		clickLevelSubmitBtn();
		clickOnContinueBtn();
	}

	/**
	 * Start and complete 'The Good, the Bad, and the Stressful' activity of level 1 of Master Stress Plan
	 */
	public void startAndCompleteGoodBadAndStressfulActivityOfMasterStressPlan(String activityName, int count){
		startActivityOfLevel1(activityName);
		clickOnNextBtnOfActivity(count);
		clickOnSubmitButtonOfHowIntenseActivity();
	}

	/**
	 * Start and complete 'Your response' activity of level 1 of Master Stress game plan
	 */
	public void startAndCompleteYourResponseActivityOfMAsterStresGP(int count){
		clickOnNextBtnOfActivity(count);
		fillingMealPlanningDetailsInLevel2();
		clickOnChooseWeightGoalsActivitySubmitBtn();
	}

	/**
	 * Verify the completion percentage of game plan after completing the level activity
	 */
	public boolean verifyCompletePercentOfGP(String expectedPercent){
		boolean isCorrect = false;

		String actualPercent = SeleniumUtil.element(completePercentAfterActivity1OfLevel2, driver).getText().trim();
		if(actualPercent.equals(expectedPercent)){
			isCorrect = true;
		}
		else{
			isCorrect = false;
		}
		return isCorrect;
	}

	/**
	 * Verify that completion status changes to 'Complete' after  game plan is submitted
	 */
	public boolean verifyGamePlanCompletetatus(String expectedPercent){
		boolean isCorrect = false;

		String actualPercent = SeleniumUtil.element(completeStatusOfGamePlan, driver).getText().trim();
		if(actualPercent.equals(expectedPercent)){
			isCorrect = true;
		}
		else{
			isCorrect = false;
		}
		return isCorrect;
	}

	/**
	 * Verify 'Continue' button is present on revisiting the already started game plan
	 */
	public boolean verifyContinueBtnOnRevistingStartedGP(String gamePlanName){
		boolean isPresent = false;
		SeleniumUtil.sleep(4);
		isPresent = SeleniumUtil.element(SeleniumUtil.dynamicXpath(continueBtnOnRevisitingStartedGamePlan, gamePlanName), driver).isDisplayed();
		return isPresent;
	}

	/**
	 * Start and complete activity 'Stress and Eating' of level 2 of Master Stress
	 */
	public void startAndCompleteStressAndEatingactivityOfMasterStress(String activityName,int count){
		startActivityOfLevel1(activityName);
		clickOnNextBtnOfActivity(count);
		clickOnSubmitButtonOfFuelForFitnessActivity();
	}

	/**
	 * Start and complete activity 'Your Relationships' of level 2 of Master Stress
	 */
	public void startAndCompleteYourRelationshipsActivityOfMasterStress(String activityName,int count){
		startOtherActivitiesOfGPLevel(activityName);
		clickOnNextBtnOfActivity(count);
		fillingMealPlanningDetailsInLevel2();
		clickOnChooseWeightGoalsActivitySubmitBtn();
		clickOnContinueBtn();
	}

	/**
	 * Start and complete activity 'S.T.O.P' of level 3 of Master Stress game plan
	 */
	public void startAndCompleteSTOPActivityOfMasterStress(int count){
		clickOnNextBtnOfActivity(count);
		clickOnChooseWeightGoalsActivitySubmitBtn();
	}

	/**
	 * Start and complete 'Empty Your Bucket' activity of Master Stress game plan
	 */
	public void startAndCompleteFocusOnPositivetActivityOfMasterStress(String activityName, int count){
		clickOnNextArrowToLoadMoreActivities();
		startOtherActivitiesOfGPLevel(activityName);
		clickOnNextBtnOfActivity(count);
		fillingMealPlanningDetailsInLevel2();
		clickOnChooseWeightGoalsActivitySubmitBtn();
	}

	/**
	 * Start and complete 'Find Your Happy Place' activity of Master Stress game plan
	 */
	public void startAndCompleteFindYourHappyPlaceActivityOfMasterStress(String activityName, int count){
		clickOnNextArrowToLoadMoreActivities();
		clickOnNextArrowToLoadMoreActivities();
		startOtherActivitiesOfGPLevel(activityName);
		clickOnNextBtnOfActivity(count);
		completePictureThisActivityOfLevel3(2);
		clickOnChooseWeightGoalsActivitySubmitBtn();
	}

	/**
	 * Complete the post survey form of 'Master Stress' game plan
	 */
	public void completeMasterStressPostPlanSurvey(int count){
		fillPostSurveyForm(count,2);
		enterValueInTextareaOfGetMovingPostPlanSurvey("8");
		switchToPostPlanSurveyFrame();
		completeActivityHavingQuizInTabularForm();
		SeleniumUtil.element(nextArrowIcon, driver).click();
		selectAnswerForActivityHavingListOfRadioBtns1InManageWightPostPlanSurvey(count, postPlanSurveyFrame);
		clickActivityCompleteBtn();
		switchToDefaultContent();
	}

	/**
	 * Select date for 'Set your Quit Date' activity og f Quit Smoking Game plan
	 */
	public void selectCalendarDate(){
		SeleniumUtil.sleep(4);
		SeleniumUtil.waitForElementToBePresent(setYourQuitDateInputArea, driver);
		SeleniumUtil.element(setYourQuitDateInputArea, driver).click();
		SeleniumUtil.waitForElementToBePresent(calendar, driver);
		SeleniumUtil.element(calendarNextMonthButton, driver).click();
		SeleniumUtil.sleep(2);
		SeleniumUtil.click(By.linkText("16"), driver);
	}

	/**
	 * Start and complete activity 'Set your Quit date' of Quit Smoking game plan
	 */
	public void startAndCompleteSetYourQuitDateOfQuitSmoking(){
		selectCalendarDate();		
		SeleniumUtil.sleep(5);
		clickOnActivitySubmitBtn();
	}

	/**
	 * Start and complete activity 'Clean House' of level 1 of Quit Smoking game plan
	 * @param activityName
	 */
	public void startAndCompleteCleanHouseOfQuitSmoking(String activityName){
		clickOnNextArrowToLoadMoreActivities();
		clickOnNextArrowToLoadMoreActivities();
		startOtherActivitiesOfGPLevel(activityName);
		selectRadioBtnForGoalsAndRelatedActivity();
		SeleniumUtil.sleep(2);
		clickOnActivitySubmitBtn();
		clickOnContinueBtn();
	}

	/**
	 * Start and complete activity 'Learn How Nicotine Affects You' of level 2 of Quit Smoking
	 */
	public void startAndCompleteLearnHowNicotineAffectsOfQuitSmoking(int count){
		clickOnNextBtnOfActivity(count);
		switchToQuizFrame();
		selectAnswerForActivityHavingListOfRadioBtns1InManageWightPostPlanSurvey(6, quizFrame);
		clickActivityCompleteBtn();
		switchToDefaultContent();
		clickOnChooseWeightGoalsActivitySubmitBtn();
	}

	/**
	 * Start and complete activity 'Smoking and You' of level 2 of Quit Smoking game plan
	 */
	public void startAndCompleteSmokingandYouOfQuitSmoking(String activityName){
		clickOnNextArrowToLoadMoreActivities();
		startOtherActivitiesOfGPLevel(activityName);
		fillingMealPlanningDetailsInLevel2();
		clickOnActivitySubmitBtn();
	}

	/**
	 * Start and complete 'Focus on the Positive!' activity of level 2 of Quit Smoking game plan
	 */
	public void startAndCompleteFocusOnPositiveOfQuitSmoking(String activityName){
		clickOnNextArrowToLoadMoreActivities();
		clickOnNextArrowToLoadMoreActivities();
		startOtherActivitiesOfGPLevel(activityName);
		clickOnActivitySubmitBtn();
		clickOnContinueBtn();
	}

	/**
	 * Click on cross icon of an activity once activity has been started
	 */
	public void clickOnCrossIconOfActivity(){
		SeleniumUtil.sleep(2);
		SeleniumUtil.element(crossLinkOfAlreadyStartedQuitMedsActivity, driver).click();
	}

	/**
	 * Click on start button of activity 'Quit Meds 101' of level 2 of Quit Smoking game plan
	 */
	public void startAndCloseQuitMedsActivityOfQuitSmoking(String activityName){
		startActivityOfLevel1(activityName);
		clickOnNextBtnOfActivity(1);
		clickOnCrossIconOfActivity();
	}

	/**
	 * Verify continue link is visible on revisiting the already started activity
	 */
	public boolean verifyContinueLnkOnRevistingAlreadyStartedActivity(String activityName, String expectedText){
		SeleniumUtil.sleep(6);
		boolean isPresent = false;
		String actualText = SeleniumUtil.element(SeleniumUtil.dynamicXpath(startAndReviewLinkOfLevelActivity, activityName), driver).getText();
		if(actualText.equals(expectedText)){
			isPresent = true;
		}

		return isPresent;
	}

	/**
	 * Start and complete activity 'Everybody Slips' of level 3 of 'Quit Smoking' game plan
	 */
	public void startAndCompleteEverybodySlipsOfQuitSmoking(){
		SeleniumUtil.sleep(8);
		switchToDefaultContent();
		SeleniumUtil.switchToFrame(iframeVideo, driver);
		SeleniumUtil.sleep(4);
		SeleniumUtil.element(playBtnOfVideoInQuitSmoking, driver).click();
		switchToDefaultContent();
		SeleniumUtil.sleep(160);
		SeleniumUtil.element(activitySubmitBtn, driver).click();
	}

	/**
	 * Start and complete activity 'Stress and Smoking' of level 3 of Quit Smoking
	 */
	public void startAndCompleteStressAndSmokingOfQuitSmoking(String activityName){
		startActivityOfLevel1(activityName);
		selectRadioBtnForGoalsAndRelatedActivity();
		clickOnActivitySubmitBtn();
	}

	/**
	 * Start and complete 'Be Active Today' activity of level 3 of Quit Smoking game plan
	 */
	public void startAndCompleteDealingWithEmotionsOfQuitSmoking(String activityName){
		clickOnNextArrowToLoadMoreActivities();
		startOtherActivitiesOfGPLevel(activityName);
		clickOnActivitySubmitBtn();
	}

	/**
	 * Fill post plan survey of Quit Smoking
	 */
	public void fillPostPlanSurveyOfQuitSmoking(){

		SeleniumUtil.switchToFrame(postPlanSurveyFrame, driver);

		//Selecting answer for first 2 questions
		for(int i=0;i<4;i++)
		{
			switchToDefaultContent();
			SeleniumUtil.switchToFrame(postPlanSurveyFrame, driver);
			List<WebElement> answerList = driver.findElements(activityAndPostSurveyQuestion1RadioBtnList);
			int size = answerList.size()-1;
			int random = (int)(Math.random()*size) + 1;
			try{
				SeleniumUtil.element(SeleniumUtil.dynamicXpath(activityAndPostSurveyQuestion1RadioBtn, Integer.toString(random)), driver).click();
			}
			catch(Exception e){
				SeleniumUtil.element(SeleniumUtil.dynamicXpath(activityAndPostSurveyQuestion1RadioBtn, Integer.toString(random)), driver).click();
			}
			Reporter.log("Selecting answers for Post survey question...");

			SeleniumUtil.element(nextArrowIcon, driver).click();
		}

	}
	/**
	 * Complete post plan survey of Quit Smoking game plan
	 */
	public void completeQuitSmokingPostPlanSurvey(){
		//		fillPostPlanSurveyOfQuitSmoking();
		fillPostSurveyForm(1,4);
		selectAnswerForActivityHavingListOfRadioBtns1InManageWightPostPlanSurvey(1, postPlanSurveyFrame);
		clickActivityCompleteBtn();
		switchToDefaultContent();
	}

	/**
	 * Click on Achievements tab
	 */
	public void clickOnAchievementsTab(){
		SeleniumUtil.waitForElementToBeVisible(achievemnentLink, driver);
		SeleniumUtil.element(achievemnentLink, driver).click();
	}

	/**
	 * Verify achievement card game plan heading
	 */
	public boolean verifyAchievementCardGamePlanHeading(String expected){
		boolean isCorrect;
		String actual = SeleniumUtil.element(achievementCardGamePlanHeading, driver).getText().trim();
		if(actual.equalsIgnoreCase(expected))
		{
			isCorrect = true;
		}
		else{
			isCorrect = false;
		}
		return isCorrect;
	}

	/**
	 * Verify achievement card completion date
	 */
	public boolean verifyAchievementCardCompletionDate(String expected){
		boolean isCorrect;
		String actual = SeleniumUtil.element(achievedCardCompletionDate,driver).getText();
		if(actual.trim().contains(expected)){
			isCorrect = true;
		}
		else{
			isCorrect = false;
		}
		return isCorrect;
	}

	/**
	 * Click on achieved game plan Review link
	 */
	public void clickOnReviewLnkOfAchievedActivity(){
		SeleniumUtil.waitForElementToBeVisible(achievementCardReviewLnk, driver);
		SeleniumUtil.element(achievementCardReviewLnk, driver).click();
	}

	/**
	 * Verify achieved game plan redirects to it's last level on clicking 'Review' link
	 */
	public boolean verifyAchievedGPRedirectsToLastLevel(String expected){
		boolean isPresent;
		SeleniumUtil.waitForElementToBeVisible(levelProgressHeading, driver);
		if(SeleniumUtil.element(levelProgressHeading, driver).isDisplayed() && SeleniumUtil.element(levelProgressHeading, driver).getText().equalsIgnoreCase(expected))
		{
			isPresent = true;
		}
		else{
			isPresent = false;
		}
		return isPresent;
	}
}


