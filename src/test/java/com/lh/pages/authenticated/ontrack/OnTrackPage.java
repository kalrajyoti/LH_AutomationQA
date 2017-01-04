package com.lh.pages.authenticated.ontrack;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.Logger;import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.lh.pages.authenticated.MainPage;
import com.lh.testConfig.TestProperty;
import com.lh.utils.SeleniumUtil;

public class OnTrackPage extends MainPage{
private By challenge_tab = By.linkText("Challenge");
private By overview_tab = By.xpath("//*[@id='ontrack-subnav']/li[1]/a");
private By challenge_frame = By.xpath("//*[@id='ctl00_ContentPlaceHolder1_dvOverview']"); 
private By overview_header = By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_dvUserActionArea']");
private By overview_body= By.xpath(".//*[@id='dvIntroductionArea']/div[1]");
private By banner1 = By.xpath("//div[@id='dvChallengeBanner']/div[2]/div");
private By signUpButton = By.xpath("//button[@class='action-button signup-action']");
private By challengeInfo = By.xpath("//div[@id='ctl00_ContentPlaceHolder1_dvChallengeInfo']/div");
private By nextSurveyPageButton = By.id("sg_NextButton");
private By completeSurveyButton = By.id("sg_SubmitButton");
private By surveyCommonRadioTable = By.xpath("//*[@class='sg-question-options']/table/tbody");
private By surveyCommonRadioList = By.xpath("//*[@class='sg-question-options']/ul/li");
private By survey6HoursOfSleeptxt = By.id("sgE-2241273-7-16-element");
private By surveyIframe = By.xpath("//iframe");
private By addSleepEntry = By.xpath("//*[@id='ctl00_ContentPlaceHolder1_appsTracking']/div/div/div/div[1]/a");
private By sleepDateEntry = By.id("sleepDatePicker");
private By foodDateEntry = By.name("dateentry");
private By entrySubmitBtn = By.xpath("//*[@id='sleep']/div/div[4]/input");
private By sleepTimeDropDown = By.xpath("//*[@id='sleep']/div/div[2]/div/select");
private By wakeTimeDropDown = By.xpath("//*[@id='sleep']/div/div[3]/div/select");
private By connectAppBanner = By.xpath("//*[@id='ctl00_ContentPlaceHolder1_appsTracking']/div/div/div/div[2]");
private By addFoodButton = By.xpath("//*[@class='entry-plus']/a");
private By foodEntryFrame = By.className("entry-section-wrapper");
private By foodEntrySave = By.xpath("//*[@class='entry-action']/input");
private By goalTrackNoOfDays = By.xpath("//*[@id='ctl00_ContentPlaceHolder1_goalProgress']/div/div/div/div[2]/div[2]/span");
/**
	 * Logger to log messages
	 */
	private static Logger logger = LogManager.getLogger(OnTrackPage.class);
	/**
	 * Webdriver to connect to the current instance of the browser
	 */
	private WebDriver driver;
	public OnTrackPage() {
		super();
		logger.info("Initializing the HealthAssessmentPage Object...");
		// Check that we're on the right page.
		if (!(driver.getTitle().equalsIgnoreCase(TestProperty.CLIENT_PORTAL_TITLE))) {
			logger.error("This is not the Health Assessment page");
			throw new IllegalStateException(
					"This is not the HealthAssessmentPage page");
		}
		logger.info("Initialized the HealthAssessmentPage page object");
	}
	/**
	 *  Verify Challenge Tab are displayed on OnTrack page 
	 */
	public boolean verifyChallengeTab(){

		boolean isPresent = false;

		if(SeleniumUtil.isElementDisplayed(challenge_tab, driver)){
			isPresent = true;
			logger.info("Challenge Tab is displayed...");
		}

		return isPresent;
	}
	
	/**
	 *  Verify Banner Text are displayed on OnTrack page 
	 */
	public boolean verifyChallengeInfoText(String bannerTxt){

		boolean isPresent = false;

		if(SeleniumUtil.getTextfromWebElement(challengeInfo,driver).contains(bannerTxt)){
			isPresent = true;
			logger.info("Appropriate Banner Text is displayed...");
		}

		return isPresent;
	}
	/**
	 *  Verify connectAppBanner Text are displayed under challenge tab on OnTrack page post sign up.
	 */
	public boolean verifyConnectAppText(String bannerTxt){

		boolean isPresent = false;

		if(SeleniumUtil.getTextfromWebElement(connectAppBanner,driver).contains(bannerTxt)){
			isPresent = true;
			logger.info("Appropriate Banner Text is displayed...");
		}

		return isPresent;
	}
	/**
	 *  Verify Header Text are displayed under overview tab on OnTrack page 
	 */
	public boolean verifyOverviewHeaderText(String bannerTxt){

		boolean isPresent = false;

		if(SeleniumUtil.getTextfromWebElement(overview_header,driver).contains(bannerTxt)){
			isPresent = true;
			logger.info("Appropriate Banner Text is displayed...");
		}

		return isPresent;
	}
	
	/**
	 *  Verify Body Text are displayed under overview tab on OnTrack page 
	 */
	public boolean verifyOverviewBodyText(String bannerTxt){

		boolean isPresent = false;

		if(SeleniumUtil.getTextfromWebElement(overview_body,driver).contains(bannerTxt)){
			isPresent = true;
			logger.info("Appropriate Banner Text is displayed...");
		}

		return isPresent;
	}
	
	/**
	 *  Verify Challenges Frame Text are displayed under overview tab on OnTrack page 
	 */
	public boolean verifyChallengesBodyText(String bannerTxt){

		boolean isPresent = false;

		if(SeleniumUtil.getTextfromWebElement(challenge_frame,driver).contains(bannerTxt)){
			isPresent = true;
			logger.info("Appropriate Banner Text is displayed...");
		}

		return isPresent;
	}
	/**
	 *  Verify Banner Text are displayed under challange tab on OnTrack page 
	 */
	public boolean verifyBannerText(String bannerTxt){

		boolean isPresent = false;

		if(SeleniumUtil.getTextfromWebElement(banner1,driver).contains(bannerTxt)){
			isPresent = true;
			logger.info("Appropriate Banner Text is displayed...");
		}

		return isPresent;
	}
	/**
	 *  Verify Banner Text are displayed on OnTrack page 
	 */
	public boolean verifySignUpButton(){

		boolean isPresent = false;

		if(SeleniumUtil.isElementDisplayed(signUpButton, driver)){
			isPresent = true;
			logger.info("Appropriate Banner Text is displayed...");
		}
		return isPresent;
	}
	/**
	 *  Verify Overview Tab are displayed on OnTrack page 
	 */
	public boolean verifyOverviewTab(){

		boolean isPresent = false;

		if(SeleniumUtil.isElementDisplayed(overview_tab, driver)){
			isPresent = true;
			logger.info("Overview tab is displayed...");
		}

		return isPresent;
	}
	/**
	 *  Click Sign Up button on OnTrack page 
	 */
	public void clickSignUpButton(){

		SeleniumUtil.click(signUpButton, driver);
		SeleniumUtil.waitForElementToBePresent(banner1, driver);
	}
	public void fillSurvey_FindingBalance(){
		int count = 0;
		
		SeleniumUtil.waitForElementToBePresent(surveyIframe, driver);
		SeleniumUtil.switchToFrame(surveyIframe, driver);
		
		clickSurveyOption(surveyCommonRadioList);
		SeleniumUtil.click(nextSurveyPageButton, driver);
		while(count<4){
		clickSurveyOption(surveyCommonRadioTable);
		SeleniumUtil.click(nextSurveyPageButton, driver);
		count++;
		}
		clickSurveyOption(survey6HoursOfSleeptxt);
		SeleniumUtil.click(nextSurveyPageButton, driver);
		clickSurveyOption(surveyCommonRadioTable);
		SeleniumUtil.click(nextSurveyPageButton, driver);
		clickSurveyOption(surveyCommonRadioList);
		SeleniumUtil.isElementDisplayed(completeSurveyButton, driver);
		logger.info("Finding Balance post challenge survey has been completed...");
		driver.switchTo().defaultContent();
		
	}
	
	public void fillSurvey_Chef(){
	
		SeleniumUtil.waitForElementToBePresent(surveyIframe, driver);
		SeleniumUtil.switchToFrame(surveyIframe, driver);
		
		clickSurveyOption(surveyCommonRadioList);
		SeleniumUtil.click(nextSurveyPageButton, driver);
		clickSurveyOption(surveyCommonRadioList);
		SeleniumUtil.click(nextSurveyPageButton, driver);
		clickSurveyOption(surveyCommonRadioTable);
		SeleniumUtil.click(nextSurveyPageButton, driver);
		clickSurveyOption(surveyCommonRadioList);
		SeleniumUtil.click(nextSurveyPageButton, driver);
		clickSurveyOption(surveyCommonRadioTable);
		SeleniumUtil.click(nextSurveyPageButton, driver);
		clickSurveyOption(surveyCommonRadioTable);
		SeleniumUtil.click(nextSurveyPageButton, driver);
		clickSurveyOption(surveyCommonRadioList);
		SeleniumUtil.click(nextSurveyPageButton, driver);
		clickSurveyOption(surveyCommonRadioList);
		SeleniumUtil.isElementDisplayed(completeSurveyButton, driver);
		logger.info("Stress Less Chef post challenge survey has been completed...");
		driver.switchTo().defaultContent();
		
	}
	
	public Integer noOfDaysGoal(){
		String[] noOfDays =SeleniumUtil.getTextfromWebElement(goalTrackNoOfDays, driver).replaceAll("[\\-\\+\\.\\^:,]","").split("Days");
		int noOfDaysCount = Integer.parseInt(noOfDays[0]);
		return noOfDaysCount;
	}
	
	public Boolean addSleepEntry(String startDate,String endDate) throws ParseException{
		int beforeCount = noOfDaysGoal();
		SeleniumUtil.click(addSleepEntry, driver);
		SeleniumUtil.click(sleepDateEntry, driver);
	//	clickRandomInBetweenDate(startDate,endDate);
		SeleniumUtil.clickRandomDropdown(sleepTimeDropDown,"Select",driver);
		SeleniumUtil.clickRandomDropdown(wakeTimeDropDown,"Select",driver);
		
		SeleniumUtil.click(entrySubmitBtn, driver);
		SeleniumUtil.waitForElementToBePresent(goalTrackNoOfDays, driver);
		int afterCount = noOfDaysGoal();
		Boolean count = false;
		if(afterCount==beforeCount+1){
			count=true;
			logger.info("No. of days tracked has been recorded...");
		} else {
			logger.info("No. of days tracked has NOT been recorded...");
		}
		return count;
	}
	
	public void addFoodEntry(String startDate,String endDate) throws ParseException{
		
		SeleniumUtil.click(foodDateEntry, driver);
	//	clickRandomInBetweenDate(startDate,endDate);
		WebDriverWait wait = new WebDriverWait(driver, 5);

		// while the following loop runs, the DOM changes - 
		// the below is a wait for the frame
		wait.until(ExpectedConditions.presenceOfElementLocated(addFoodButton)); 
		SeleniumUtil.click(foodEntryFrame, driver);
		Random randomOption = new Random();
		int count = 0;
		int randomNum = 1;
		int times = 0;
		List<WebElement> add = driver.findElements(addFoodButton);
		System.out.println("No. of add buttons="+add.size());
		
		while(count<6){
			randomNum = randomOption.nextInt(add.size()-1);
			for (times=0;times<=randomNum;times++){
				add.get(count).click();
			}
			count++;
		}
		SeleniumUtil.click(foodEntrySave, driver);
		logger.info("Food Entry has been recorded...");
	}
	
	public void checkCharts(){
		 List<String> labels = new ArrayList<String>();
		List<WebElement> xAxisLabels = driver.findElements(By.cssSelector("text>tspan"));
        for (WebElement xAxisLabel : xAxisLabels) {
            labels.add(xAxisLabel.getText());
        }
		System.out.println(labels);
	}
	
}
