package com.lh.pages.authenticated.ontarget.jyoti;

import static com.lh.helper.DriverFactory.driver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.lh.pages.authenticated.MainPage;
import com.lh.pages.authenticated.jyoti.MainPageModified;
import com.lh.utils.SeleniumUtil;
import com.lh.utils.jyoti.SeleniumUtilModified;

public class OnTargetPlanmodified extends MainPageModified{
	
	private static Logger logger = LogManager.getLogger(com.lh.pages.authenticated.ontarget.OnTargetPlan.class);

	
	private By yourTargetLink 									= By.xpath("//section[@id='scSubNavigation']/ul[@class='list-inline-block subnav']/li/a[@href='/ontarget/YourTarget/Plan/OnTarget']");
	private By dashboardLink									= By.xpath("//section[@id='scSubNavigation']/ul[@class='list-inline-block subnav']/li/a[@href='/ontarget/Dashboard']");
	private By achievemnentLink 								= By.xpath("//section[@id='scSubNavigation']/ul[@class='list-inline-block subnav']/li/a[@href='/ontarget/Achievement']");
	private By gameplanList										= By.xpath("//div[@class='tb-module tb-hol ng-scope']//ul[@class='list-hol']/li//span[@class='tb-plan-title ng-binding']");
	private String gamePlansName								= "//div[@class='tb-module tb-hol ng-scope']//ul[@class='list-hol']/li//span[@class='tb-plan-title ng-binding' and text()='%s']";
	private By headingVisitDashboardOnYourTarget				= By.xpath("//div[@teaserzonename='onTarget_StartNewGameplanPrompt']/h2	");
	private By getStartedButtonYourTarget						= By.xpath("//div[@class='row padded-grid']//div[@class='button-row']/input");
	private String gamePlanGetStartedBtn						= "(//div[@class='plan-title']/h2[@class='ng-binding' and text()='%s'])[2]/../../following-sibling::div[@class='tb-row-btn']/button";
	private By levelNextBtn										= By.xpath("(//div[@class='row save wide-wrapper']//button[@id='NextButton' and text()='Next'])[1]");
	private By chooseYourGoalsList								= By.xpath("//label[@class='ng-binding']");
	private String chooseYourGoalsRadioBtns						= "(//label[@class='ng-binding'])[%s]";
	private By otherInputBox									= By.xpath("//div[@class='hidetext']/input");
	private By levelSubmitBtn									= By.xpath("(//div[@class='row save wide-wrapper']//button[@id='SubmitButton' and text()='Submit'])[2]");
	private By goalsEditLink									= By.xpath("//div[@class='button-row ng-scope']/a");
	private By chosenGoalsList									= By.xpath("//div[@class='date-copy']");
	private String startAndReviewLinkOfLevelActivity 			= "(//div[@class='tile-header']/h3[text()='%s'])[1]/../following-sibling::div/a";
	private By quizFrame										= By.xpath("//iframe[@id='QuizIframe']");
	private By activityAndPostSurveyQuestion1RadioBtnList		= By.xpath("//div[@class='sg-question-options']/ul");
	private String startAndReviewLnkOfActivity					= "(//div[@class='tile-header']/h3[text()='%s'])[2]/../following-sibling::div/a";
	private By activitySubmitBtn								= By.id("SubmitButton");
	private By continueBtnForProceedingToNextLevel				= By.xpath("//input[@class='btn' and @value='Continue']");
	private By levelProgressHeading								= By.xpath("//span[@class='progress-copy ng-binding']");
	private String addNewRowLnk									="(//div[@class='add-new-row ng-scope']/a)[%s]";
	private By mealPlanningTable								= By.xpath("//div[@class='input-textarea ng-scope']//textarea");
	private String textareaList									= "(//div[@class='input-textarea ng-scope']//textarea)[%s]";
	private By level2StartAndReviewLnkYourWhyActivity			= By.xpath("(//div[@class='tile-header']/h3[text()='Your Why'])[1]/../following-sibling::div/a");
	private By nextArrowBtnForChangingActivity					= By.xpath("//button[@class='slick-next']");
	private By level2StartAndReviewLnkOvercomeVeggieActivity	= By.xpath("(//div[@class='tile-header']/h3[text()='Overcome Veggie Hurdles!'])[2]/../following-sibling::div/a");
	private String nextButtonsInLevelActivity					= "(//div[@class='row save wide-wrapper']//button[@id='NextButton' and text()='Next'])[%s]";
	private By level2OvercomeVeggieSubmitBtn					= By.xpath("(//button[@id='SubmitButton'])[5]");
	private By level3WhatsItWorthActivityRows					= By.xpath("//table[normalize-space(@class='sg-table')]//tr");
	private By tableQuizActivityColumns							= By.xpath("(//table[normalize-space(@class='sg-table')]//tr)[2]/td");
	private String level3WhatsItWorthActivityRadioButtons		= "((//table[normalize-space(@class='sg-table')]//tr)[%s])/td[%s]/label";
	private By nextArrowIcon									= By.id("sg_NextButton");
	private By activityCompleteBtn								= By.id("sg_SubmitButton");	
	private By level3StartAndReviewLnkPictureThisActivity		= By.xpath("(//div[@class='tile-header']/h3[text()='Picture This!'])[1]/../following-sibling::div/a");
	private By level3DocumentUploadInputOfPictureThisActivity	= By.id("documentdraganddropupload");
	private By level3UploadBtnOfPictureThisActivity				= By.id("btnUpload");
	private String level3TitleInputOfPictureThisActivity		= ("(//div[@class='input-textarea']//textarea)[%s]");
	private By level3StartAndReviewLnkPortionDistortionActivity	= By.xpath("(//div[@class='tile-header']/h3[text()='Portion Distortion'])[2]/../following-sibling::div/a");
	private By submitGamePlan									= By.id("SubmitNow1");
	private By postPlanSurveyFrame								= By.xpath("//iframe[@class='ng-scope']");
	private String activityAndPostSurveyQuestion1RadioBtn		= "(//div[@class='sg-question-options']//li/label)[%s]";
	/* private By logoutLnk										= By.xpath("//div[@id='header-old']//ul[@class='list-inline-block header-links']/li/a[@href='/logout.aspx']");
	private By gameplanList										= By.xpath("//div[@class='tb-module tb-hol ng-scope']//ul[@class='list-hol']/li//span[@class='tb-plan-title ng-binding']");
	
	private By eatWellHeading									= By.xpath("(//div[@class='plan-title']/h2[@class='ng-binding' and text()='Eat Well'])[2]");
	private By getMovingHeading 								= By.xpath("(//div[@class='plan-title']/h2[@class='ng-binding' and text()='Get Moving'])[2]");
	private By manageYourWeightHeading							= By.xpath("(//div[@class='plan-title']/h2[@class='ng-binding' and text()='Manage Your Weight'])[2]");
	private By masterStressHeading								= By.xpath("(//div[@class='plan-title']/h2[@class='ng-binding' and text()='Master Stress'])[2]");
	private By quitSmokingHeading								= By.xpath("(//div[@class='plan-title']/h2[@class='ng-binding' and text()='Quit Smoking'])[2]");
	private By collapseGamePlanArrow							= By.className("ng-scope");
	private By spotlightHeading									= By.xpath("//h3[text()='Spotlight']");
	private By spotlightInfoIcon								= By.xpath("//div[@class='web-access-div-tb-tooltip tb-tooltip']/img");
	private By spotlightTooltipMessage 							= By.xpath("//div[@class='web-access-div-tb-tooltip tb-tooltip']//span/p");
	private By eatWellHeadingOnYourTarget						= By.xpath("//h2[@class='ng-binding ng-scope']");
	private By setYourGoalEatWell								= By.xpath("//div[@class='date-copy']/div[@class='ng-scope' and text()='Set your goal below']");
	private By levelDropdownArrow								= By.xpath("//div[@class='web-access-list-dropdown list-dropdown']/span");
	private String enabledLevel									= "//ul[@class='web-access-list-block list-block']//a[@class='ng-binding' and text()='%s']";
	private String lockedLevels									= "//ul[@class='web-access-list-block list-block']/li[@class='ng-scope locked']/span[text()='%s']";
	private By levelProgressTooltipIcon							= By.xpath("(//div[@id='ActionSpace']//span[@class='icon icon-24 afk-ko tooltip'])[1]");
	private By levelProgressTooltipIconText						= By.xpath("(//div[@id='ActionSpace']//span[@class='icon icon-24 afk-ko tooltip'])[1]//p");
	private By activityHowIntenseIsItSubmitBtn					= By.xpath("(//button[@id='SubmitButton'])[3]");
	private By activityPowerUpSubmitBtn							= By.xpath("(//button[@id='SubmitButton'])[9]");
	private By activityWinningTheWarSubmitBtn					= By.xpath("(//button[@id='SubmitButton'])[6]");
	private By activityFuelForFitnessSubmitBtn					= By.xpath("(//button[@id='SubmitButton'])[4]");
	

	private By submitEatWellGamePlan							= By.id("SubmitNow");
	private By inputTextInGetMovingPostSurvey					= By.xpath("//div[@class='sg-control-text sg-control-number']/input");
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
	private By crossLinkOfAlreadyStartedQuitMedsActivity		= By.xpath("(//div[@class='row save wide-wrapper']//div[@class='wide-row']/a[text()='Save and Close'])[2]");
	private By playBtnOfVideoInQuitSmoking						= By.xpath("//div[@id='player']//div/button[@class='ytp-large-play-button ytp-button']");
	private By iframeVideo										= By.id("player");
	private By achievementCardGamePlanHeading					= By.xpath("//div[@id='achievement']/ul/li//div/h2");
	private By reviewLinkOfAchievedGameplan						= By.xpath(".//*[@id='achievement']/ul/li//div[2]/a");
	private By achievedCardCompletionDate						= By.xpath("(//div[@id='achievement']/ul/li//div)[3]");
	private By achievementCardReviewLnk 						= By.xpath(".//*[@id='achievement']/ul/li//div/div/a");
	private By eatWellLoader									= By.xpath("//span[@class='icon icon-105']");*/
	
	public boolean verifyDashboardLinkIsPresent (){

		boolean isPresent;

		isPresent= driver.findElement(dashboardLink).isDisplayed();
		Reporter.log("Verified if Dashboard link is present...");
		return isPresent;
	}
	
	public boolean verifyYourTargetLinkIsPresent (){

		boolean isPresent;

		isPresent= driver.findElement(yourTargetLink).isDisplayed();
		Reporter.log("Verified if 'Your Target' link is present...");
		return isPresent;
	}

	public boolean verifyAchievementLinkIsPresent() {
		boolean isPresent;
		isPresent = driver.findElement(achievemnentLink).isDisplayed();
		return isPresent;
	}
	/**
	 * Clicks on the Get "Dashboard" Link
	 */
	public void clickDashboardLink (){
		SeleniumUtilModified.sleep(6);
		driver.findElement(dashboardLink).click();
		Reporter.log("Clicked on Dashboard tab...");
	}
	/**
	 * Clicks on the Get "Your Target" Link
	 */
	public void clickYourTargetLink(){
		driver.findElement(yourTargetLink).click();
		Reporter.log("Clicked on 'Your Target' link...");
	}
	public boolean verifyAssociatedPlanIsPresent(Map<String,String> gamePlanData){

		SeleniumUtilModified.sleep(2);
		boolean isPresent = false;

		List<WebElement> gamePlansList = driver.findElements(gameplanList);
		List<String> gamePlanActualList = new ArrayList<String>();
		for(WebElement e1:gamePlansList)
		{
			System.out.println(e1);
			gamePlanActualList.add(e1.getText());
		}
		List<String> gamePlansExpectedList = splitPipeSepratedString(gamePlanData.get("Eat Well"));
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
	
	public void clickOnGamePlan(String gamePlanName){
		SeleniumUtilModified.sleep(4);
		SeleniumUtilModified.waitForElementToBeVisible(SeleniumUtilModified.dynamicXpath(gamePlansName, gamePlanName),driver);
		driver.findElement(SeleniumUtilModified.dynamicXpath(gamePlansName, gamePlanName)).click();
		Reporter.log("Clicked on gameplan " + gamePlanName+ "..." );
	}
	/**
	 * Verify Your Target page for a new user
	 */
	public boolean verifyYourTargetGetStarted(){

		boolean isPresent = false;

	if(SeleniumUtilModified.element(headingVisitDashboardOnYourTarget, driver).isDisplayed() && SeleniumUtil.element(getStartedButtonYourTarget, driver).isDisplayed())
		{
			isPresent = true;
		}
		return isPresent;
	}
	/**
	 * Click on Get Started button appearing on Your target page
	 */
	public void clickGetStartedYourTarget(){
		SeleniumUtilModified.waitForElementToBeVisible(getStartedButtonYourTarget, driver);
		try{
			SeleniumUtilModified.element(getStartedButtonYourTarget, driver).click();
		}
		catch(Exception e){
			SeleniumUtilModified.element(getStartedButtonYourTarget, driver).click();
		}
	}
	/**
	 * Click on Get Started button of Eat Well plan on dashboard
	 */
	public void clickGamePlanGetStartedBtn(String gamePlanName){
		SeleniumUtilModified.sleep(3);
		SeleniumUtilModified.waitForElementToBeVisible(SeleniumUtilModified.dynamicXpath(gamePlanGetStartedBtn, gamePlanName), driver);
		SeleniumUtilModified.element(SeleniumUtilModified.dynamicXpath(gamePlanGetStartedBtn, gamePlanName), driver).click();
		Reporter.log("Clicked on 'Get Started' button of " + gamePlanName +" game plan...");
	}
	/**
	 * Click on Get Started button to start the level
	 */
	public void clickGetStartedOrNextLevelBtn(){
		try{
			SeleniumUtilModified.waitForElementToBePresent(levelNextBtn, driver);//need to ask
			SeleniumUtilModified.element(levelNextBtn, driver).click();
			Reporter.log("Click on 'Next' button to start the Level...");
		}
		catch(Exception e){
			SeleniumUtilModified.element(levelNextBtn, driver).click();
			Reporter.log("An exception occured..");
		}
		SeleniumUtilModified.sleep(3);
	}
	/**
	 * Choose the goals
	 */
	public void selectRadioBtnForGoalsAndRelatedActivity(){
		SeleniumUtilModified.sleep(3);
		SeleniumUtilModified.waitForElementToBeVisible(chooseYourGoalsList, driver);
		List<WebElement> goalsList	= driver.findElements(chooseYourGoalsList);
		int size = goalsList.size();
		List<Integer> numberList = SeleniumUtilModified.generateUniqueRandomNumber(size);
		SeleniumUtilModified.sleep(4);
		for (int i=1; i<=4; i++){
			SeleniumUtilModified.sleep(4);
			WebElement radioBtn = SeleniumUtilModified.element(SeleniumUtilModified.dynamicXpath(chooseYourGoalsRadioBtns, Integer.toString(numberList.get(i))),driver);
			if(numberList.get(i)==size && radioBtn.getText().equalsIgnoreCase("Other")){
				radioBtn.click();
				SeleniumUtilModified.element(otherInputBox, driver).click();
				SeleniumUtilModified.sendKeysToWebElement(otherInputBox, "Other data", driver);
			}
			else
			{
				SeleniumUtilModified.element(SeleniumUtil.dynamicXpath(chooseYourGoalsRadioBtns, Integer.toString(numberList.get(i))),driver).click();
			}
		}
	}
	
	/**
	 * Click on Submit button of level
	 */
	public void clickLevelSubmitBtn(){
		SeleniumUtilModified.sleep(2);
		SeleniumUtilModified.waitForElementToBeVisible(levelSubmitBtn, driver);	
		SeleniumUtilModified.element(levelSubmitBtn, driver).click();
		Reporter.log("Clicked on Submit button for submitting the activity...");
	}
	/**
	 * Verify Edit link for editing goals is appearing on Your Target page after completing goals activity
	 */
		boolean isPresent = false;
		public boolean verifyGoalsEditLinkIsPresent(){
			SeleniumUtilModified.waitForElementToBeVisible(goalsEditLink, driver);
		if(SeleniumUtilModified.element(goalsEditLink, driver).isDisplayed() && SeleniumUtilModified.element(chosenGoalsList, driver).isDisplayed())
		{
			isPresent = true;
		}
		return isPresent;
	}

		/**
		 * Click on Start link of Nutrition Label activity of Level 1
		 */
		public void startActivityOfLevel1(String activityName)
		{
			SeleniumUtilModified.waitForElementToBeVisible(SeleniumUtilModified.dynamicXpath(startAndReviewLinkOfLevelActivity,activityName), driver);
			SeleniumUtilModified.sleep(5);
			SeleniumUtilModified.element(SeleniumUtilModified.dynamicXpath(startAndReviewLinkOfLevelActivity,activityName), driver).click();
			Reporter.log("Click on Start link of "+activityName+" label activity...");
		}
		public void selectAnswerForLevel1ActivityNutritionLabelHavingListOfRadioBtns1(){
			SeleniumUtilModified.waitForElementToBePresent(quizFrame, driver);
			SeleniumUtilModified.switchToFrame(quizFrame, driver);
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
			 * Verify Review link is present after completing Nutrition activity
			 */
			public boolean verifyReviewLinkOnActivity(String activityName){
				SeleniumUtilModified.waitForElementToBeVisible(SeleniumUtilModified.dynamicXpath(startAndReviewLinkOfLevelActivity,activityName), driver);
				SeleniumUtilModified.sleep(4);
				boolean isPresent;
				isPresent = SeleniumUtilModified.element(SeleniumUtilModified.dynamicXpath(startAndReviewLinkOfLevelActivity,activityName), driver).isDisplayed();
				Reporter.log("Verified if Nutrition activity is marked as complete or not...");
				return isPresent;
			}

			/**
			 * Click on Start link of Shop Smart(Eat Well), Power Up!(Get moving) activity of Level 1
			 */
			public void startOtherActivitiesOfGPLevel(String activityName){
				SeleniumUtilModified.sleep(6);
				SeleniumUtilModified.waitForElementToBeVisible(SeleniumUtilModified.dynamicXpath(startAndReviewLnkOfActivity,activityName), driver);
				SeleniumUtil.element(SeleniumUtilModified.dynamicXpath(startAndReviewLnkOfActivity, activityName), driver).click();
				Reporter.log("Clicked on Start button of " + activityName+ " activity");
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
			 * Click on Submit button of Shop Smart Activity and What's It Worth? activity
			 */
			public void clickOnActivitySubmitBtn(){
				SeleniumUtilModified.waitForElementToBeVisible(activitySubmitBtn, driver);
				SeleniumUtilModified.element(activitySubmitBtn, driver).click();
				Reporter.log("Clicked on Sublit button of an activity");
			}
			/**
			 * Click on Continue button for proceeding to next level
			 */
			public void clickOnContinueBtn(){
				SeleniumUtilModified.waitForElementToBeVisible(continueBtnForProceedingToNextLevel, driver);
				SeleniumUtilModified.element(continueBtnForProceedingToNextLevel, driver).click();
				Reporter.log("Clicked on Continue button for proceeding to next level...");
			}
			
			/**
			 * Verify level 2 is started after level1 completion 
			 */
			public boolean verifyLevel2Started(String levelHeading){
				SeleniumUtilModified.sleep(3);
				boolean isPresent = false;
				if(SeleniumUtilModified.element(levelProgressHeading, driver).isDisplayed()){
					isPresent = SeleniumUtilModified.element(levelProgressHeading, driver).getText().equalsIgnoreCase(levelHeading);
				}		

				logger.debug(levelHeading +" heading is displayed after subsequent levels are completed...");
				return isPresent;
			}

			/**
			 * Click on Add New Row link to add a new row in meal plan
			 */
			public void clickOnAddNewRow(int count){
				String value = Integer.toString(count);
				SeleniumUtilModified.waitForElementToBeVisible(SeleniumUtilModified.dynamicXpath(addNewRowLnk, value), driver);
				try{
					SeleniumUtilModified.element(SeleniumUtilModified.dynamicXpath(addNewRowLnk, value), driver).click();
				}
				catch(Exception e){
					SeleniumUtilModified.element(SeleniumUtilModified.dynamicXpath(addNewRowLnk, value), driver).click();
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
					if(!(SeleniumUtilModified.element(SeleniumUtilModified.dynamicXpath(textareaList, j), driver).getAttribute("value").equalsIgnoreCase(""))){
						continue;
					}

					else{
						SeleniumUtilModified.waitForElementToBeVisible(SeleniumUtilModified.dynamicXpath(textareaList, j), driver);
						SeleniumUtilModified.element(SeleniumUtilModified.dynamicXpath(textareaList, j), driver).click();
						SeleniumUtilModified.element(SeleniumUtilModified.dynamicXpath(textareaList, j), driver).clear();
						SeleniumUtilModified.element(SeleniumUtilModified.dynamicXpath(textareaList, j), driver).sendKeys("testData" + j);
						Reporter.log("Filled the data in column input fields..");
					}
				}
			}
			/**
			 * Start and complete 'Your Why' activity of Eat Well and Get Moving game plan.
			 */
			public void startAndCompleteYourWhyActivity(){
				SeleniumUtilModified.sleep(3);
				startYourWhyActivityOfLevel2();
			}
			
			/**
			 * Click on Start link of Your Why activity of Level 2
			 */
			public void startYourWhyActivityOfLevel2(){
				driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
				SeleniumUtilModified.sleep(4);
				SeleniumUtilModified.waitForElementToBeVisible(level2StartAndReviewLnkYourWhyActivity, driver);
				SeleniumUtilModified.element(level2StartAndReviewLnkYourWhyActivity, driver).click();
				Reporter.log("Clicked on Start link of Your Why activity");
			}	
			
			/**
			 * Click on Next arrow for loadiTng more activities
			 */
			public void clickOnNextArrowToLoadMoreActivities(){
				SeleniumUtilModified.sleep(5);
				SeleniumUtilModified.waitForElementToBeVisible(nextArrowBtnForChangingActivity, driver);
				SeleniumUtilModified.element(nextArrowBtnForChangingActivity, driver).click();
				Reporter.log("Clicked on Next arror button to load more activities..");
			}
			
			/**
			 * Click on Start link of Overcome Veggie Hurdles activity of Level 2
			 */
			public void startOvercomeVeggieHurdleActivityOfLevel2(){
				SeleniumUtilModified.waitForElementToBeVisible(level2StartAndReviewLnkOvercomeVeggieActivity, driver);
				SeleniumUtilModified.element(level2StartAndReviewLnkOvercomeVeggieActivity, driver).click();
				Reporter.log("Clicked on Start link of Overcome Veggie Hurdle activity");
			}	
			
			/**
			 * Click on Next buttons in Overcome Veggie Hurdle activity
			 */
			public void clickOnNextAndSubmitBtnsOfLevelActivity(int count){
				for(int i=0;i<count;i++){
					String j = Integer.toString(i+2);
					SeleniumUtilModified.waitForElementToBeVisible(SeleniumUtilModified.dynamicXpath(nextButtonsInLevelActivity, j), driver);
					SeleniumUtilModified.element(SeleniumUtilModified.dynamicXpath(nextButtonsInLevelActivity, j), driver).click();
					Reporter.log("Clicked on Next button of an activity...");
				}
			}
			
			/**
			 * Click on Submit button of Overcome Veggie Hurdle Activity
			 */
			public void clickSubmitOfOvercomeVeggieHurdleActivity(){
				SeleniumUtilModified.sleep(2);
				SeleniumUtilModified.waitForElementToBeVisible(level2OvercomeVeggieSubmitBtn, driver);
				SeleniumUtilModified.element(level2OvercomeVeggieSubmitBtn, driver).click();
				Reporter.log("Clicked on Next and Submit button of Overcome Veggie Hurdle activity");
			}
			
			/**
			 * Switch to Quiz Frame
			 */
			public void switchToQuizFrame(){
				SeleniumUtilModified.switchToFrame(quizFrame, driver);
			}
			/**
			 * Complete the activity 'What's it worth' of Level 3 by selecting radio buttons for different questions
			 */
			public void completeActivityHavingQuizInTabularForm(){
				List<WebElement> itemsList = driver.findElements(level3WhatsItWorthActivityRows);
				int size = itemsList.size()-1;
				System.out.println("size of item list is:" +size);
				List<WebElement> colList = driver.findElements(tableQuizActivityColumns);
				int colSize = colList.size();
				System.out.println("size of colum list is:" +colSize);
				for(int i=0;i<size;i++){
					SeleniumUtilModified.sleep(4);
					String j = Integer.toString(i+2);
					int random = (int)(Math.random()*colSize) + 1;
					System.out.println("the value of random no is:" +random);
					if(i==0){
						SeleniumUtilModified.element(SeleniumUtilModified.dynamicXpath(level3WhatsItWorthActivityRadioButtons, j, Integer.toString(random)), driver).click();
						SeleniumUtilModified.element(SeleniumUtilModified.dynamicXpath(level3WhatsItWorthActivityRadioButtons, j, Integer.toString(random)), driver).click();
					}
					else{
						SeleniumUtilModified.element(SeleniumUtil.dynamicXpath(level3WhatsItWorthActivityRadioButtons, j, Integer.toString(random)), driver).click();
					}
					Reporter.log("Selecting the radio button for various questions appearing in tabular form...");
				}
			}
			
			/**
			 * Switch to default content
			 */
			public void switchToDefaultContent(){
				SeleniumUtilModified.switchToDefaultContent(driver);
			}
			
			/**
			 * Click on 'Next arrow icon' of an activity for proceeding to next question
			 */
			public void clickOnNextArrowIcon(){

				SeleniumUtilModified.switchToFrame(quizFrame, driver);
				SeleniumUtilModified.element(nextArrowIcon, driver).click();
				Reporter.log("Clicked on Next arrow icon after answering questions related to food items...");
				SeleniumUtilModified.switchToDefaultContent(driver);
			}
			
			/**
			 * Click on Complete button of the level activity
			 */
			public void clickOnCompleteButton(){

				SeleniumUtilModified.switchToFrame(quizFrame, driver);
				SeleniumUtilModified.element(activityCompleteBtn, driver).click();
				Reporter.log("Clicked on Complete button of Whats It Worth activity...");
				SeleniumUtilModified.switchToDefaultContent(driver);
			}
			/**
			 * Click on Start/Review button of Picture This! activity
			 */
			public void startPictureThisActivityOfLevel3(){
				driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
				SeleniumUtilModified.sleep(8);
				SeleniumUtilModified.element(level3StartAndReviewLnkPictureThisActivity, driver).click();
				Reporter.log("Clicked on Start link of Picture This! activity...");
			}
			
			/**
			 * Complete Picture This! activity
			 */
			public void completePictureThisActivityOfLevel3(int count){

				for(int i=0;i<count;i++){
					String j = Integer.toString(i+1);
					SeleniumUtilModified.sleep(3);
					SeleniumUtilModified.element(level3DocumentUploadInputOfPictureThisActivity, driver).sendKeys(System.getProperty("user.dir")+ "\\src\\test\\resources\\Image"+j+".jpg");
					Reporter.log("Uploaded the image for Picture This! activity of level3...");
					SeleniumUtilModified.sleep(8);
					SeleniumUtilModified.element(level3UploadBtnOfPictureThisActivity, driver).click();
					Reporter.log("Clicked on Upload button for uploading the files...");
					SeleniumUtilModified.sleep(3);
					SeleniumUtilModified.sleep(3);
					SeleniumUtilModified.waitForElementToBeVisible(SeleniumUtilModified.dynamicXpath(level3TitleInputOfPictureThisActivity, j), driver);
					SeleniumUtilModified.waitForElementToBeVisible(SeleniumUtilModified.dynamicXpath(level3TitleInputOfPictureThisActivity, j), driver);
					SeleniumUtilModified.element(SeleniumUtilModified.dynamicXpath(level3TitleInputOfPictureThisActivity, j), driver).click();
					SeleniumUtilModified.element(SeleniumUtilModified.dynamicXpath(level3TitleInputOfPictureThisActivity, j), driver).sendKeys("UploadImage"+j);
					Reporter.log("Filled the comments/title for uploaded image...");
				}
			}
			
			/**
			 * Click on Start/Review button of Portion Distortion activity of level 3
			 */
			public void startPortionDistortionActivityOfLevel3(){
				SeleniumUtilModified.sleep(8);
				SeleniumUtilModified.element(level3StartAndReviewLnkPortionDistortionActivity, driver).click();
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
			 * Submit the Get Moving game plan
			 */
			public void submitGamePlan(){
				SeleniumUtilModified.sleep(3);
				SeleniumUtilModified.waitForElementToBeVisible(submitGamePlan, driver);
				SeleniumUtilModified.element(submitGamePlan, driver).click();
				//		SeleniumUtil.element(submitEatWellGamePlan, driver).click();
				Reporter.log("Clicked on Submit button to submit the game plan...");
			}

			/**
			 * Complete the post survey form of Eat Well game plan 
			 */
			public void completeEaTwellPostPlanSurvey(){
				submitGamePlan();
				fillPostSurveyForm(2,2);
			}
			/**
			 * Filling the post survey form
			 */
			public void fillPostSurveyForm(int count,int radioQuestions){
				SeleniumUtilModified.switchToFrame(postPlanSurveyFrame, driver);
				//Selecting answer for first 2 questions
				for(int i=0;i<radioQuestions;i++)
				{
					switchToDefaultContent();
					SeleniumUtilModified.switchToFrame(postPlanSurveyFrame, driver);
					List<WebElement> answerList = driver.findElements(activityAndPostSurveyQuestion1RadioBtnList);
					int size = answerList.size()-1;
					int random = (int)(Math.random()*size) + 1;
					try{
						SeleniumUtilModified.element(SeleniumUtilModified.dynamicXpath(activityAndPostSurveyQuestion1RadioBtn, Integer.toString(random)), driver).click();
					}
					catch(Exception e){
						SeleniumUtilModified.element(SeleniumUtilModified.dynamicXpath(activityAndPostSurveyQuestion1RadioBtn, Integer.toString(random)), driver).click();
					}
					Reporter.log("Selecting answers for Post survey question...");
					SeleniumUtilModified.element(nextArrowIcon, driver).click();
				}
//				Filling the answers for a tabular question	
				List<WebElement> colList1 = driver.findElements(tableQuizActivityColumns);
				int colSize1 = colList1.size();
				System.out.println("the value of colSize1:"+colSize1);
				for(int i=0;i<count;i++)
				{
					List<WebElement> itemList = driver.findElements(level3WhatsItWorthActivityRows);
					int size2 = itemList.size()-1;
					System.out.println("the value of size2:"+size2);
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
			}
			
		

