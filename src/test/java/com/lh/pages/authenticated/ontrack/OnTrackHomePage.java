package com.lh.pages.authenticated.ontrack;

import static com.lh.helper.DriverFactory.driver;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeoutException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.lh.helper.ErrorUtil;
import com.lh.testConfig.TestProperty;
import com.lh.utils.SeleniumUtil;


public class OnTrackHomePage {
	private static Logger logger = LogManager.getLogger(OnTrackHomePage.class);
	
	private By OnTrack_dd_Delorian				    = 		By.id("ctl00_DeloreanBar1_ddlCampaign");
	private By OnTrack_cal_DtpPicker                =       By.xpath("//*[@id='ctl00_dvDeloreanBar']/div/div[5]/div/img");
	private By OnTrack_selectYourToolsLink          =       By.xpath("//*[@id='navbar']/div/div/div[3]/ul[2]/li[3]/span");
	private String OnTrack_link                     =       "//a[@href='/V2/MemberServices/OnTrack/index.aspx ']";

	private By OnTrack_Steps_Link                   =       By.xpath("//*[@id='navbar']/div/div/div[3]/ul[2]/li[3]/ul/li[6]/a");
	private By OnTrack_General_Link                 =       By.xpath("//*[@id='navbar']/div/div/div[3]/ul[2]/li[3]/ul/li[7]/a");
	                                                  
	
	private By delorianDate                         =       By.xpath("//input[@id='datepickerDelorean']");
	private By delorianMonthDropdown                =       By.className("ui-datepicker-month");    
	private By delorianCalendar                     =       By.id("ui-datepicker-div");
	private By delorianYearDropdown                 =       By.className("ui-datepicker-year");
	private By delorianPrevMonthButton              =       By.xpath("//*[@id='ui-datepicker-div']/div/a[1]");
	private By overview_tab                         =       By.xpath("//*[@id='ontrack-subnav']/li[1]/a");
	private By challenge_tab                        =       By.xpath("//*[@id='liChallenge']/a");
	private By overviewTab                          =       By.xpath("//*[@id='ontrack-subnav']/li[1]/a");
	private By overview_heading                     =       By.xpath("//*[@id='ctl00_ContentPlaceHolder1_dvUserActionArea']/h2");
	private By overview_header                      =       By.xpath("//*[@id='ctl00_ContentPlaceHolder1_dvUserActionArea']/p");
	private By overview_bodyHeader                  =       By.xpath("//*[@id='dvIntroductionArea']/div[1]/h2");
	private By overview_body                        =       By.xpath("//*[@id='dvIntroductionArea']/div[1]");
	private By overviews_body                       =       By.xpath("//*[@id='dvIntroductionArea']/div[1]/p");
	private By logoutLnk							=       By.xpath("//*[@id='LogoutLink']");
	
	private By challenge_frameHeader                =       By.xpath("//*[@id='ctl00_ContentPlaceHolder1_dvOverview']/h2"); 
	private By challenge_frame                      =       By.xpath("//*[@id='ctl00_ContentPlaceHolder1_dvOverview']"); 
	private By nutri_Challenge_Frame                =       By.xpath("//*[@id='ctl00_ContentPlaceHolder1_dvChallengeInfo']");
	
	private By challengeInfo                        =       By.xpath("//*[@id='ctl00_ContentPlaceHolder1_dvChallengeInfo']/div/p[1]");
	private By banner1                              =       By.xpath("//div[@zonename='BannerText']/h2");
	private By banner2                              =       By.xpath("//*[@id='dvChallengeBanner']/div[2]/div[3]/p");
	private By banner3                              =       By.xpath("//*[@id='dvChallengeBanner']/div[2]/div[3]/h2");
	private By banner4                              =       By.xpath("//*[@id='dvChallengeBanner']/div[2]/div[3]/p[1]");
	private By banner5                              =       By.xpath("//*[@id='dvChallengeBanner']/div[2]/div[3]/p[2]");
	private By banner6                              =       By.xpath("//*[@id='dvChallengeBanner']/div[2]/div[3]/p[3]");
	private By banner7                              =       By.xpath("//*[@id='ctl00_ContentPlaceHolder1_dvChallengeInfo']/div");
	private By challengeInfoBanner                  =       By.xpath("//*[@id='ctl00_ContentPlaceHolder1_dvChallengeInfo']/div/p[2]");
	
	
	private String signUpButton                      =      "//button[@class='action-button signup-action']";
	private By addSleepEntry                        =       By.xpath("//*[@id='ctl00_ContentPlaceHolder1_appsTracking']/div/div/div/div[1]/a");
	
	private By addNutritionEntry                    =       By.xpath("//*[@id='ctl00_ContentPlaceHolder1_appsTracking']/div/div/div[2]/div[1]/div/img");
	private By sleepDateEntry                       =       By.id("sleepDatePicker");
	
	private By excerciseEntry                       =       By.xpath("//*[@id='exercise']/div[1]/div[1]/div[1]/img");

	private By addDateEntry                         =       By.xpath("//*[@id='ui-datepicker-div']/table/tbody/tr[1]/td[5]/a");
	private By addExcerciseDateEntry                =       By.xpath("//*[@id='ui-datepicker-div']/table/tbody/tr[1]/td[5]/span");

	private By addWeightEntry                       =       By.xpath("//*[@id='ui-datepicker-div']/table/tbody/tr[3]/td[5]");
	private By foodDateEntry                        =       By.name("dateentry");
	private By foodDateEntries                      =       By.xpath("//*[@id='ctl00_ContentPlaceHolder1_appsTracking']/div/div/div[2]/div[1]/div/img");
	private By calendarDatePick                     =       By.xpath("//*[@id='ui-datepicker-div']/table/tbody/tr[2]/td[5]/a");
	private By calendarDatePicker                   =       By.xpath("//*[@id='ui-datepicker-div']/table/tbody/tr[2]/td[2]/a");
	
	private By calendarStepDatePicker               =       By.xpath("//*[@id='stepsDatePicker']");
	private By calendarNutritionDatePicker          =       By.xpath("//*[@id='ui-datepicker-div']/table/tbody/tr[2]/td[3]/a");
	
	private By previousLink                         =       By.xpath("//*[@id='ui-datepicker-div']/div/a[1]/span");
	private By stepsDay1                            =       By.xpath("//*[@id='ui-datepicker-div']/table/tbody/tr[4]/td[4]/a");
	private By stepsEntry                           =       By.xpath("//*[@id='steps']/div[1]/div[2]/span[1]/input");
	
	
	
	private String entrySubmitBtn                       =    "//input[@type='button' and @name='submitfitness']";
	private By sleepTimeDropDown                    =       By.xpath("//*[@id='sleep']/div/div[2]/div/select");
	private By excerciseActivity                    =       By.xpath("//div[@id='exercise']//input[@class='dropdown-input ng-pristine ng-valid']");
	private By excercisetrackMinutes                =       By.xpath("//div[@class='minutes activity-minutes last-field']//input");
	private By wakeTimeDropDown                     =       By.xpath("//*[@id='sleep']/div/div[3]/div/select");
	private By addFoodButton                        =       By.xpath("//*[@class='entry-plus']/a");
	private By foodEntryFrame                       =       By.className("entry-section-wrapper");
	private String foodEntrySave                        =   "//*[@class='entry-action']/input";
	private By stepsEntrySave                       =       By.xpath("//*[@id='steps']/div[1]/div[3]/input");
	
	private By goalTrackNoOfDays                    =       By.xpath("//*[@id='ctl00_ContentPlaceHolder1_goalProgress']/div/div/div/div[2]/div[2]/span");
	private By goalTrackEaseNoOfDays                =       By.xpath("//*[@id='ctl00_ContentPlaceHolder1_goalProgress']/lh-challengecard/div/div/div[2]/div[2]/span");
	private By surveyIframe                        =        By.xpath("//iframe");
	private By surveyCommonRadioList               =        By.xpath("//*[@class='sg-question-options']/ul/li");
	private By surveyCommonchkList1                 =       By.xpath("//input[@type='checkbox']");
	private By surveyTest                          =        By.xpath("//*[@id='sgE-2241273-1-2-10069-other']");
	
	private By nextSurveyPageButton                =        By.id("sg_NextButton");
	private By surveyCommonRadioTable              =        By.xpath("//*[@class='sg-question-options']/table/tbody");
	private By survey6HoursOfSleeptxt              =        By.id("sgE-2241273-7-16-element");
	private By completeSurveyButton                =        By.id("sg_SubmitButton");
    private By activity1                           =       By.xpath("//*[@id='sgE-2241270-12-43-box']/div[2]/table/tbody/tr[1]/td[1]/label");
	private By activity2                           =       By.xpath("//*[@id='sgE-2241270-12-43-box']/div[2]/table/tbody/tr[2]/td[1]/label");
	private By activity3                           =       By.xpath("//*[@id='sgE-2241270-12-43-box']/div[2]/table/tbody/tr[3]/td[1]/label");
	private By activity4                           =       By.xpath("//*[@id='sgE-2241270-12-43-box']/div[2]/table/tbody/tr[4]/td[1]/label");	
	private By activity5                           =       By.xpath("//*[@id='sgE-2241270-13-48-box']/div[2]/table/tbody/tr[1]/td[1]/label");
	private By activity6                           =       By.xpath("//*[@id='sgE-2241270-13-48-box']/div[2]/table/tbody/tr[2]/td[1]/label");
	private By activity7                           =       By.xpath("//*[@id='sgE-2241270-13-48-box']/div[2]/table/tbody/tr[3]/td[1]/label");
	private By activity8                           =       By.xpath("//*[@id='sgE-2241270-13-48-box']/div[2]/table/tbody/tr[4]/td[1]/label");
	private By activity9                           =       By.xpath("//*[@id='sgE-2241270-13-48-box']/div[2]/table/tbody/tr[5]/td[1]/label");
	private By activity10                          =       By.xpath("//*[@id='sgE-2241270-13-48-box']/div[2]/table/tbody/tr[6]/td[1]/label");
	private By activity11                          =       By.xpath("//*[@id='sgE-2241270-13-48-box']/div[2]/table/tbody/tr[7]/td[1]/label");
	private By activity12                          =       By.xpath("//*[@id='sgE-2241270-15-80-box']/div[2]/table/tbody/tr[1]/td[1]/label");
	private By activity13                          =       By.xpath("//*[@id='sgE-2241270-15-80-box']/div[2]/table/tbody/tr[2]/td[1]/label");
	private By activity14                          =       By.xpath("//*[@id='sgE-2241270-15-80-box']/div[2]/table/tbody/tr[3]/td[1]/label");
	private By activity15                          =       By.xpath("//*[@id='sgE-2241270-15-80-box']/div[2]/table/tbody/tr[4]/td[1]/label");
	private By noOfHoursAdded                      =       By.xpath("//*[@id='sgE-2241270-19-105-element']");
	
	private By weightActivity                      =       By.xpath("//*[@id='sgE-2241273-3-30-box']/div[2]/table/tbody/tr[1]/td[1]/label");
	private By weightActivity1                     =       By.xpath("//*[@id='sgE-2241273-3-30-box']/div[2]/table/tbody/tr[2]/td[1]/label");
	private By weightActivity2                     =       By.xpath("//*[@id='sgE-2241273-3-30-box']/div[2]/table/tbody/tr[3]/td[1]/label");
	private By weightActivity3                     =       By.xpath("//*[@id='sgE-2241273-3-30-box']/div[2]/table/tbody/tr[4]/td[1]/label");
	
	private By trackActivity                       =       By.xpath("//*[@id='sgE-2241267-14-41-box']/div[2]/table/tbody/tr[1]/td[1]/label");
	private By trackActivity1                      =       By.xpath("//*[@id='sgE-2241267-14-41-box']/div[2]/table/tbody/tr[2]/td[1]/label");
	private By trackActivity2                      =       By.xpath("//*[@id='sgE-2241267-14-41-box']/div[2]/table/tbody/tr[3]/td[1]/label");
	private By trackActivity3                      =       By.xpath("//*[@id='sgE-2241267-14-41-box']/div[2]/table/tbody/tr[4]/td[1]/label");
	
	private By eatingActivity                      =       By.xpath("//*[@id='sgE-2241264-11-43-box']/div[2]/table/tbody/tr[1]/td[1]/label");
	private By eatingActivity1                     =       By.xpath("//*[@id='sgE-2241264-11-43-box']/div[2]/table/tbody/tr[2]/td[1]/label");
	private By eatingActivity2                     =       By.xpath("//*[@id='sgE-2241264-11-43-box']/div[2]/table/tbody/tr[3]/td[1]/label");
	private By eatingActivity3                     =       By.xpath("//*[@id='sgE-2241264-11-43-box']/div[2]/table/tbody/tr[4]/td[1]/label");
	
	
	private By weight_RadioSatisfactionOption       =      By.xpath("//*[@id='sgE-2241273-4-4-box']/div[2]/table/tbody/tr[1]/td[1]/label");
	private By weight_RadioSatisfactionOption1      =      By.xpath("//*[@id='sgE-2241273-4-4-box']/div[2]/table/tbody/tr[2]/td[1]/label");
	private By weight_RadioSatisfactionOption2      =      By.xpath("//*[@id='sgE-2241273-4-4-box']/div[2]/table/tbody/tr[3]/td[1]/label");
	private By weight_RadioSatisfactionOption3      =      By.xpath("//*[@id='sgE-2241273-4-4-box']/div[2]/table/tbody/tr[4]/td[1]/label");
	private By weight_RadioSatisfactionOption4      =      By.xpath("//*[@id='sgE-2241273-4-4-box']/div[2]/table/tbody/tr[5]/td[1]/label");
	private By weight_RadioSatisfactionOption5      =      By.xpath("//*[@id='sgE-2241273-4-4-box']/div[2]/table/tbody/tr[6]/td[1]/label");
	private By weight_RadioSatisfactionOption6      =      By.xpath("//*[@id='sgE-2241273-4-4-box']/div[2]/table/tbody/tr[7]/td[1]/label");
	private By track_RadioSatisfactionOption        =      By.xpath("//*[@id='sgE-2241267-15-47-box']/div[2]/table/tbody/tr[1]/td[1]/label");
	private By track_RadioSatisfactionOption1       =      By.xpath("//*[@id='sgE-2241267-15-47-box']/div[2]/table/tbody/tr[2]/td[1]/label");
	private By track_RadioSatisfactionOption2       =      By.xpath("//*[@id='sgE-2241267-15-47-box']/div[2]/table/tbody/tr[3]/td[1]/label");
	private By track_RadioSatisfactionOption3       =      By.xpath("//*[@id='sgE-2241267-15-47-box']/div[2]/table/tbody/tr[4]/td[1]/label");
	private By track_RadioSatisfactionOption4       =      By.xpath("//*[@id='sgE-2241267-15-47-box']/div[2]/table/tbody/tr[5]/td[1]/label");
	private By track_RadioSatisfactionOption5       =      By.xpath("//*[@id='sgE-2241267-15-47-box']/div[2]/table/tbody/tr[6]/td[1]/label");
	private By track_RadioSatisfactionOption6       =      By.xpath("//*[@id='sgE-2241267-15-47-box']/div[2]/table/tbody/tr[7]/td[1]/label");
	
	private By track_RadionNutritioOption           =      By.xpath("//*[@id='sgE-2241264-12-50-box']/div[2]/table/tbody/tr[1]/td[1]/label");
	private By track_RadionNutritioOption1          =      By.xpath("//*[@id='sgE-2241264-12-50-box']/div[2]/table/tbody/tr[2]/td[1]/label");
	private By track_RadionNutritioOption2          =      By.xpath("//*[@id='sgE-2241264-12-50-box']/div[2]/table/tbody/tr[3]/td[1]/label");
	private By track_RadionNutritioOption3          =      By.xpath("//*[@id='sgE-2241264-12-50-box']/div[2]/table/tbody/tr[4]/td[1]/label");
	private By track_RadionNutritioOption4          =      By.xpath("//*[@id='sgE-2241264-12-50-box']/div[2]/table/tbody/tr[5]/td[1]/label");
	private By track_RadionNutritioOption5          =      By.xpath("//*[@id='sgE-2241264-12-50-box']/div[2]/table/tbody/tr[6]/td[1]/label");
	private By track_RadionNutritioOption6          =      By.xpath("//*[@id='sgE-2241264-12-50-box']/div[2]/table/tbody/tr[7]/td[1]/label");
	
	private By participate_RadionNutritioOption1     =      By.xpath("//*[@id='sgE-2241264-14-93-box']/div[2]/table/tbody/tr[1]/td[1]/label");
	private By participate_RadionNutritioOption2     =      By.xpath("//*[@id='sgE-2241264-14-93-box']/div[2]/table/tbody/tr[2]/td[1]/label");
	private By participate_RadionNutritioOption3     =      By.xpath("//*[@id='sgE-2241264-14-93-box']/div[2]/table/tbody/tr[3]/td[1]/label");
	private By participate_RadionNutritioOption4     =      By.xpath(".//*[@id='sgE-2241264-14-93-box']/div[2]/table/tbody/tr[4]/td[1]/label");
	private By track_steps1                          =      By.xpath("//*[@id='sgE-2241267-17-71-box']/div[2]/table/tbody/tr[1]/td[1]/label");
	private By track_steps2                          =      By.xpath("//*[@id='sgE-2241267-17-71-box']/div[2]/table/tbody/tr[2]/td[1]/label");
	private By track_steps3                          =      By.xpath("//*[@id='sgE-2241267-17-71-box']/div[2]/table/tbody/tr[3]/td[1]/label");
	private By track_steps4                          =      By.xpath("//*[@id='sgE-2241267-17-71-box']/div[2]/table/tbody/tr[4]/td[1]/label");
	private By participate_Radiosurvey               =      By.xpath("//*[@id='sgE-2241273-5-43-box']/div[2]/table/tbody/tr[1]/td[1]/label");
	private By participate_Radiosurvey1              =      By.xpath("//*[@id='sgE-2241273-5-43-box']/div[2]/table/tbody/tr[2]/td[1]/label");
	private By participate_Radiosurvey2              =      By.xpath("//*[@id='sgE-2241267-16-55-box']/div[2]/ul/li[1]/label");
	private By addStepsEntry                         =      By.xpath("//*[@id='sgE-2241273-7-16-element']");
	private By addStepsEntryHrs                      =      By.xpath("//*[@id='sgE-2241267-20-63-element']");
	private By monthly_RadioSurvey                   =      By.xpath("//*[@id='sgE-2241273-8-17-box']/div[2]/table/tbody/tr[1]/td[1]/label");
	private By monthly_RadioSurvey1                  =      By.xpath("//*[@id='sgE-2241273-8-17-box']/div[2]/table/tbody/tr[2]/td[1]/label");
	private By monthly_RadioSurvey2                  =      By.xpath("//*[@id='sgE-2241273-8-17-box']/div[2]/table/tbody/tr[3]/td[1]/label");
	private By monthly_RadioSurvey3                  =      By.xpath("//*[@id='sgE-2241273-8-17-box']/div[2]/table/tbody/tr[4]/td[1]/label");
	private By addDays                               =      By.xpath("//*[@id='sgE-2241267-20-63-element']");	
	private By logout                                =      By.xpath("//*[@id='LogoutLink']");

	private By dateSelect							 = 		By.xpath("//*[@id='ui-datepicker-div']/table/tbody/tr[5]/td[4]/a");
	private By activitySelect						 = 		By.xpath("//*[@id='exercise']/div[1]/div[1]/div[2]/div/input");
	private By entryField_Exhale					 =		By.xpath("//*[@id='exercise']/div[1]/div[1]/div[3]/div/span[1]/input");
	private String minuteEntry						 =      "60";	
	private By selectCategory						= By.xpath("//*[@id='exercise']/div[1]/div[1]/div[2]/div/ul/li[2]/a/span");
	private String exerciseSubmitBtn				="//*[@id='exercise']/div[1]/div[2]/input"; 
	boolean flag;

	private By activityDrop							 =		By.xpath("//ul[@class='dropdown-ul open']/li[2]");	
	private String activityMinutes					 =       "60";	
	

	
	public OnTrackHomePage() {
		
		super();
		Reporter.log("Initializing the OnTarget plan page Object...");

		if (!(driver.getTitle().equalsIgnoreCase(TestProperty.CLIENT_PORTAL_TITLE))) {
			logger.error("The current page is not the Contact LiveHealthier page");
			throw new IllegalStateException(
					"This is not the Contact LiveHealthier page");
		}
		Reporter.log("Initialized the OnTarget plan page object");
		logger.info("Initializing the OnTrack Home Page Object...");
		

		}
		
	
	/**
	 * Verify Campaign DropDown  is displayed 
	 */
	
	
	public boolean verifyCampaignDropDown()
    {
  	  boolean isPresent = false;	  
 	   if(SeleniumUtil.isElementDisplayed(OnTrack_dd_Delorian,driver)){
 		isPresent = true;
 	   }
	       return isPresent;
 	   }
    
	
	public boolean verifyDelorianDtpPicker()
    {
  	  boolean isPresent = false;
 	   if(SeleniumUtil.isElementDisplayed(OnTrack_cal_DtpPicker,driver)){
 		   isPresent = true;
 		logger.info("On Track Delorian Dtp Picker is displayed");
 	   }
	    return isPresent;
 	   }
	
	public boolean verifyYourToolsMenuLink()
    {
  	  boolean isPresent = false;
 	   if(SeleniumUtil.isElementDisplayed(OnTrack_selectYourToolsLink,driver)){
 		   isPresent = true;
 		logger.info("On Track Your Tools Menu Link is displayed");
 	   }
	    return isPresent;
 	   }
	
	public void selectcampaignDropDown()
	{    Select delorianList = new Select(SeleniumUtil.element(OnTrack_dd_Delorian,driver));
		delorianList.selectByIndex(1);
	}
	
	public void selectGeneralcampaignDropDown()
	{
	    Select delorianList = new Select(SeleniumUtil.element(OnTrack_dd_Delorian,driver));
		delorianList.selectByIndex(4);
	}
	
	public void selectHeartyHealthCampaignDropDown()
	{
		Select delorianList = new Select(SeleniumUtil.element(OnTrack_dd_Delorian,driver));
		delorianList.selectByIndex(1);
	}
	
	public void selectGeneralcampaign()
	{
	    Select delorianList = new Select(SeleniumUtil.element(OnTrack_dd_Delorian,driver));
		delorianList.selectByIndex(1);
		
	}
	
	public void selectNutritioncampaignDropDown()
	{
		Select delorianList = new Select(SeleniumUtil.element(OnTrack_dd_Delorian,driver));
		delorianList.selectByIndex(1);
	}
	
	public void selectPhysicalDropDown()
	{
	    Select delorianList = new Select(SeleniumUtil.element(OnTrack_dd_Delorian,driver));
		delorianList.selectByIndex(1);
	}
	
     public void setDelorian(String date) throws TimeoutException{
		
	    selectcampaignDropDown();
		// wait for page refresh
		SeleniumUtil.waitForElementToBePresent(OnTrack_dd_Delorian, driver);
		driver.findElement(delorianDate).clear();
		SeleniumUtil.sendKeysToWebElement(delorianDate, date, driver);
		SeleniumUtil.sleep(3);
		datePickerClick(date);	
	}
     
     public void setGeneralDelorian(String date) throws TimeoutException{
 		
    	 selectGeneralcampaignDropDown();
 		// wait for page refresh
 		SeleniumUtil.waitForElementToBePresent(OnTrack_dd_Delorian, driver);
 		driver.findElement(delorianDate).clear();
 		SeleniumUtil.sendKeysToWebElement(delorianDate, date, driver);
 		SeleniumUtil.sleep(3);
 		datePickerClick(date);	
 	}
     
     public void setPhysicalDelorian(String date) throws TimeoutException{
 		
    	 selectPhysicalDropDown();
 		// wait for page refresh
 		SeleniumUtil.waitForElementToBePresent(OnTrack_dd_Delorian, driver);
 		driver.findElement(delorianDate).clear();
 		SeleniumUtil.sendKeysToWebElement(delorianDate, date, driver);
 		SeleniumUtil.sleep(3);
 		datePickerClick(date);
 		
 	}
     
     public void setDelorianforGE(String date) throws TimeoutException{
 		
    	 selectGeneralcampaignDropDown();
 	    SeleniumUtil.sleep(3);
 		// wait for page refresh
 		SeleniumUtil.waitForElementToBePresent(OnTrack_dd_Delorian, driver);
 		driver.findElement(delorianDate).clear();
 		SeleniumUtil.sendKeysToWebElement(delorianDate, date, driver);
 		SeleniumUtil.sleep(3);
 		datePickerClick(date);
 		
 	}
     
     public void setDelorianforHeartyHealth(String date) throws TimeoutException{
  		
    	 selectHeartyHealthCampaignDropDown();
 	    SeleniumUtil.sleep(3);
 		// wait for page refresh
 		SeleniumUtil.waitForElementToBePresent(OnTrack_dd_Delorian, driver);
 		driver.findElement(delorianDate).clear();
 		SeleniumUtil.sendKeysToWebElement(delorianDate, date, driver);
 		SeleniumUtil.sleep(3);
 		datePickerClick(date);
 		
 	}
     
     public void setGeDelorian(String date) throws TimeoutException{
  		
    	 selectGeneralcampaign();
 	    SeleniumUtil.sleep(3);
 		// wait for page refresh
 		SeleniumUtil.waitForElementToBePresent(OnTrack_dd_Delorian, driver);
 		driver.findElement(delorianDate).clear();
 		SeleniumUtil.sendKeysToWebElement(delorianDate, date, driver);
 		SeleniumUtil.sleep(3);
 		datePickerClick(date);
 		
 	}
     
     public void setDelorianforNutrition(String date) throws TimeoutException{
  		
    	 selectNutritioncampaignDropDown();
 	    SeleniumUtil.sleep(3);
 		// wait for page refresh
 		SeleniumUtil.waitForElementToBePresent(OnTrack_dd_Delorian, driver);
 		driver.findElement(delorianDate).clear();
 		SeleniumUtil.sendKeysToWebElement(delorianDate, date, driver);
 		SeleniumUtil.sleep(3);
 		datePickerClick(date);
 		
 	}
     
     public void setDelorianforSteps(String date) throws TimeoutException{
   		
    	 selectNutritioncampaignDropDown();
 	    SeleniumUtil.sleep(3);
 		// wait for page refresh
 		SeleniumUtil.waitForElementToBePresent(OnTrack_dd_Delorian, driver);
 		driver.findElement(delorianDate).clear();
 		SeleniumUtil.sendKeysToWebElement(delorianDate, date, driver);
 		SeleniumUtil.sleep(3);
 		datePickerClick(date);
 		
 	}
     
     public void updateDelorian(String date) throws TimeoutException{
 		
 	    // wait for page refresh
 		SeleniumUtil.waitForElementToBePresent(OnTrack_dd_Delorian, driver);
 		driver.findElement(delorianDate).clear();
 		SeleniumUtil.sendKeysToWebElement(delorianDate, date, driver);
 		SeleniumUtil.sleep(4);
 		
 		datePickerClick(date);
 		
 	}
     
     
     public  void datePickerClick(String date) throws TimeoutException{
         SeleniumUtil.waitForElementToBePresent(By.className("ui-datepicker-title"), driver);
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyy");
         LocalDate delorianD = LocalDate.parse(date, formatter.withLocale(Locale.ENGLISH));
         String year = String.format("%s", delorianD.getYear());
         String month= String.format("%s", delorianD.getMonthValue()-1);
         String calDate = String.format("%s", delorianD.getDayOfMonth());
         SeleniumUtil.waitForElementToBePresent(By.className("ui-datepicker-title"), driver);
         Select dyear = new Select(SeleniumUtil.element(delorianYearDropdown, driver));
         dyear.selectByValue(year);
         try{
               Thread.sleep(2000);
         } catch (InterruptedException e) {
               e.printStackTrace();
         }
         SeleniumUtil.waitForElementToBePresent(By.xpath("//*[@class='ui-datepicker-year' ]/option[@selected='selected' and @value='"+year+"']"), driver);
   
       Select dmonth = new Select(SeleniumUtil.element(delorianMonthDropdown, driver));
       dmonth.selectByValue(month);
       SeleniumUtil.waitForElementToBePresent(By.xpath("//*[@class='ui-datepicker-month']/option[@selected='selected' and @value='"+month+"']"), driver);
      
         SeleniumUtil.waitForElementToBePresent(delorianCalendar, driver);
         try {
               Thread.sleep(4000);
         } catch (InterruptedException e) {
               e.printStackTrace();
         }
         WebElement dateWidget = driver.findElement(delorianCalendar); 
       List<WebElement> rows=dateWidget.findElements(By.tagName("tr")); 
           List<WebElement> columns=dateWidget.findElements(By.tagName("td")); 
             
           for (WebElement cell: columns){ 
           // select random date
            if (cell.getText().equals(calDate)){
                  SeleniumUtil.waitForElementToBePresent(delorianCalendar, driver);
   
                        if(cell.getAttribute("class").endsWith("disabled ")){
                        SeleniumUtil.click(delorianPrevMonthButton, driver);
                        SeleniumUtil.waitForElementToBePresent(delorianCalendar, driver);
                    
                        SeleniumUtil.click(By.linkText(calDate), driver);
                    
                  }else {
                     cell.click();
                  }
                        break; 
            }             
           }
   }

     
     public void slectYourToolsLink()
     {
    	 SeleniumUtil.hover(OnTrack_selectYourToolsLink, driver);
    	 

     }
     public void clickOnOnTrackLink()
     {

    	 SeleniumUtil.clickbyJS(OnTrack_link, driver);
    	 // return new OnTrack_FindingBalanceTest();

     }
     
     public void clickOnTrackLinks()
     {
    	 Actions act = new Actions(driver);
    	 WebElement subMenu = driver.findElement(OnTrack_Steps_Link);
    	 act.moveToElement(subMenu).click().build().perform();
     }
     
     public void clickOnGeneralTrackLink()
     {
    	 Actions act = new Actions(driver);
    	 
    	 //SeleniumUtil.waitForElementToBePresent(OnTrack_Steps_Link, driver);
    	 WebElement subMenu = driver.findElement(OnTrack_General_Link);
    	 act.moveToElement(subMenu).click().build().perform();
     }
     
     public boolean verifyChallengeTab(){

 		boolean isPresent = false;

 		if(SeleniumUtil.isElementDisplayed(challenge_tab, driver)){
 			isPresent = true;
 			logger.info("Challenge Tab is displayed...");
 		}

 		return isPresent;
 	}

     public void clickOnChallengeTab()
     {
    	 SeleniumUtil.click(challenge_tab, driver);
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

     
     public boolean verifyOverviewTab(){

 		boolean isPresent = false;

 		if(SeleniumUtil.isElementDisplayed(overview_tab, driver)){
 			isPresent = true;
 			logger.info("Overview tab is displayed...");
 		}

 		return isPresent;
 	}

     public void clickOverviewTab() {

 		logger.info("Clicking on the Overview Tab Locator identified by- "	+ overviewTab);
 		driver.findElement(overviewTab).click();
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
 	 *  Verify Header Text are displayed under overview tab on OnTrack page 
 	 */
 	public boolean verifyOverviewHeaderInfo(String bannerTxt){

 		boolean isPresent = false;

 		if(SeleniumUtil.getTextfromWebElement(overview_heading,driver).contains(bannerTxt)){
 			isPresent = true;
 			logger.info("Appropriate Banner Text is displayed...");
 		}

 		return isPresent;
 	}


 	/**
	 * Clicks on the LogOut link present on the top right corner of the website
	 */
	public void clickOnLogoutLink(){

		logger.info("Clicking on the  the logoutLinkLocator indentified by- " + logoutLnk);
		driver.findElement(logoutLnk).click();
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
	 *  Verify Body Text are displayed under overview tab on OnTrack page 
	 */
	public boolean verifyOverviewBodyTexts(String bannerTxt){

		boolean isPresent = false;

		if(SeleniumUtil.getTextfromWebElement(overviews_body,driver).contains(bannerTxt)){
			isPresent = true;
			logger.info("Appropriate Banner Text is displayed...");
		}

		return isPresent;
	}

	
	/**
	 *  Verify Body Text are displayed under overview tab on OnTrack page 
	 */
	public boolean verifyOverviewBodyCaption(String bannerTxt){

		boolean isPresent = false;

		if(SeleniumUtil.getTextfromWebElement(overviews_body,driver).contains(bannerTxt)){
			isPresent = true;
			logger.info("Appropriate Banner Text is displayed...");
		}

		return isPresent;
	}
	

	
	/**
	 *  Verify Body Text are displayed under overview tab on OnTrack page 
	 */
	public boolean verifyOverviewBodyHeader(String bannerTxt){

		boolean isPresent = false;
		if(SeleniumUtil.getTextfromWebElement(overview_bodyHeader,driver).contains(bannerTxt)){
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
		if(SeleniumUtil.getTextfromWebElement(challenge_frame,driver).contains(bannerTxt.trim())){
			isPresent = true;
			logger.info("Appropriate Banner Text is displayed...");
		}

		return isPresent;
	}
	
	/**
	 *  Verify Challenges Frame Text are displayed under overview tab on OnTrack page 
	 */
	public boolean verifyChallengesBodyHeader(String bannerTxt){

		boolean isPresent = false;

		if(SeleniumUtil.getTextfromWebElement(challenge_frameHeader,driver).contains(bannerTxt)){
			isPresent = true;
			logger.info("Appropriate Banner Text is displayed...");
		}

		return isPresent;
	}
	

	
	/**
	 *  Verify Banner Text are displayed under challenge tab on OnTrack page 
	 */
	public boolean verifyBannerText(String bannerTxt){


		boolean isPresent = false;

		if(SeleniumUtil.getTextfromWebElement(banner1,driver).contains(bannerTxt.trim())){

			isPresent = true;
			logger.info("Appropriate Banner Text is displayed...");
		}
		return isPresent;
	}
	
	
	/**
	 *  Verify Banner Text are displayed under Nutrition challenge tab on OnTrack page 
	 */
	public boolean verifyNutriChallengeBannerText(String bannerTxt){

		boolean isPresent = false;

		if(SeleniumUtil.getTextfromWebElement(nutri_Challenge_Frame,driver).contains(bannerTxt)){
			isPresent = true;
			logger.info("Appropriate Banner Text is displayed...");
		}

		return isPresent;
	}
	

	
	/**
	 *  Verify Banner Text are displayed under challange tab on OnTrack page 
	 */
	public boolean verifyChallengeBannerText(String bannerTxt){

		boolean isPresent = false;

		if(SeleniumUtil.getTextfromWebElement(challengeInfoBanner,driver).contains(bannerTxt)){
			isPresent = true;
			logger.info("Appropriate Banner Text is displayed...");
		}

		return isPresent;
	}

  /**
	 *  Verify Banner Text are displayed under challange tab on OnTrack page 
	 */
	public boolean verifyChallengeBanner(String bannerTxt){

		boolean isPresent = false;

		if(SeleniumUtil.getTextfromWebElement(banner7,driver).contains(bannerTxt)){
			isPresent = true;
			logger.info("Appropriate Banner Text is displayed...");
		}

		return isPresent;
	}

	

	
	
	/**
	 *  Verify Banner Text are displayed under challange tab on OnTrack page 
	 */
	public boolean REGISTERED_NO_APP_BANNER_Text(String bannerTxt){

		boolean isPresent = false;

		if(SeleniumUtil.getTextfromWebElement(banner3,driver).contains(bannerTxt)){
			isPresent = true;
			logger.info("Appropriate Banner Text is displayed...");
		}

		return isPresent;
	}


	/**
	 *  Verify Banner Text are displayed under challange tab on OnTrack page 
	 */
	public boolean REGISTERED_NO_APP1_BANNER_Text(String bannerTxt){

		boolean isPresent = false;

		if(SeleniumUtil.getTextfromWebElement(banner4,driver).contains(bannerTxt)){
			isPresent = true;
			logger.info("Appropriate Banner Text is displayed...");
		}

		return isPresent;
	}

	

	/**
	 *  Verify Banner Text are displayed under challange tab on OnTrack page 
	 */
	public boolean REGISTERED_NO_APP2_BANNER_Text(String bannerTxt){

		boolean isPresent = false;

		if(SeleniumUtil.getTextfromWebElement(banner5,driver).contains(bannerTxt)){
			isPresent = true;
			logger.info("Appropriate Banner Text is displayed...");
		}

		return isPresent;
	}
	
	/**
	 *  Verify Banner Text are displayed under challange tab on OnTrack page 
	 */
	public boolean REGISTERED_NO_APP3_BANNER_Text(String bannerTxt){

		boolean isPresent = false;

		if(SeleniumUtil.getTextfromWebElement(banner6,driver).contains(bannerTxt)){
			isPresent = true;
			logger.info("Appropriate Banner Text is displayed...");
		}

		return isPresent;
	}
	

	
	
	
	
	/**
	 *  Verify Banner Text are displayed under challange tab on OnTrack page 
	 */
	public boolean verifyRegisteredPostChallengeSurvey1Text(String bannerTxt){

		boolean isPresent = false;

		if(SeleniumUtil.getTextfromWebElement(banner2,driver).contains(bannerTxt)){
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

		if(SeleniumUtil.isElementDisplayedString(signUpButton, driver)){
			isPresent = true;
			logger.info("Appropriate Banner Text is displayed...");
		}
//.
		return isPresent;
	}


	/**
	 *  Click Sign Up button on OnTrack page 
	 */
	public void clickSignUpButton(){

		SeleniumUtil.clickbyJS(signUpButton, driver);
		SeleniumUtil.sleep(10);
		SeleniumUtil.waitForElementToBePresent(banner1, driver);
	}
	public int noOfDaysGoal(){
		
		String noOfDays =SeleniumUtil.getTextfromWebElement(goalTrackNoOfDays, driver);
		String st[] = noOfDays.split(" ");
		int days = Integer.valueOf(st[0]);
		int  noOfDaysTrack = days;
		return noOfDaysTrack;
	}
	
      public int noOfDaysEaseGoal(){
		
		String noOfDays =SeleniumUtil.getTextfromWebElement(goalTrackNoOfDays, driver);
		String st[] = noOfDays.split(" ");
		int days = Integer.valueOf(st[0]);
		int  noOfDaysTrack = days;
		return noOfDaysTrack;
	}
	
      public int noOfDaysHeartyGoal(){
  		
  		String noOfDays =SeleniumUtil.getTextfromWebElement(goalTrackEaseNoOfDays, driver);
  		String st[] = noOfDays.split(" ");
  		int days = Integer.valueOf(st[0]);
  		int  noOfDaysTrack = days;
  		return noOfDaysTrack;
  	}
      
      public int noOfDaysPumpOfGoal(){
  		
		String noOfDays =SeleniumUtil.getTextfromWebElement(goalTrackEaseNoOfDays, driver);
		String st[] = noOfDays.split(" ");
		int days = Integer.valueOf(st[0]);
		int  noOfDaysTrack = days;
		return noOfDaysTrack;
	}

      public void physicalActivity()
	{
		SeleniumUtil.click(activity1, driver);
		SeleniumUtil.click(activity2, driver);
		SeleniumUtil.click(activity3, driver);
		SeleniumUtil.click(activity4, driver);		
	}
	
	public void weightActivity()
	{
		SeleniumUtil.click(weightActivity, driver);
		SeleniumUtil.click(weightActivity1, driver);
		SeleniumUtil.click(weightActivity2, driver);
		SeleniumUtil.click(weightActivity3, driver);		
	}
	
	public void trackActivity()
	{
		SeleniumUtil.click(trackActivity, driver);
		SeleniumUtil.click(trackActivity1, driver);
		SeleniumUtil.click(trackActivity2, driver);
		SeleniumUtil.click(trackActivity3, driver);		
	}
	
	public void eatingActivity()
	{
		SeleniumUtil.click(eatingActivity, driver);
		SeleniumUtil.click(eatingActivity1, driver);
		SeleniumUtil.click(eatingActivity2, driver);
		SeleniumUtil.click(eatingActivity3, driver);		
	}
	
	public void levelOfSatisfactionSurvey()
	{
		SeleniumUtil.click(activity5, driver);
		SeleniumUtil.click(activity6, driver);
		SeleniumUtil.click(activity7, driver);
		SeleniumUtil.click(activity8,driver);
		SeleniumUtil.click(activity9, driver);
		SeleniumUtil.click(activity10, driver);
		SeleniumUtil.click(activity11, driver);	
	}
	
	public void levelOfSatisfactionSurvey1()
	{
		SeleniumUtil.click(activity12, driver);
		SeleniumUtil.click(activity13, driver);
		SeleniumUtil.click(activity14, driver);
		SeleniumUtil.click(activity15,driver);
		
	}
	
	public void addsleepEntry()
	{
		SeleniumUtil.sendKeysToWebElement(addStepsEntry,"9", driver);
	}
	
	public void addStepsEntry()
	{
		SeleniumUtil.sendKeysToWebElement(addStepsEntryHrs,"9", driver);
	}
	public void clickMonthlySurvey()
	{
	  SeleniumUtil.click(monthly_RadioSurvey, driver);
	  SeleniumUtil.click(monthly_RadioSurvey1, driver);
	  SeleniumUtil.click(monthly_RadioSurvey2, driver);
	  SeleniumUtil.click(monthly_RadioSurvey3, driver);
	  	
	}
	
	
	
	public void levelOfSatisfactionWeightSurvey()
	{	SeleniumUtil.click(weight_RadioSatisfactionOption, driver);
		SeleniumUtil.click(weight_RadioSatisfactionOption1, driver);
		SeleniumUtil.click(weight_RadioSatisfactionOption2, driver);
		SeleniumUtil.click(weight_RadioSatisfactionOption3,driver);
		SeleniumUtil.click(weight_RadioSatisfactionOption4, driver);
		SeleniumUtil.click(weight_RadioSatisfactionOption5, driver);
		SeleniumUtil.click(weight_RadioSatisfactionOption6, driver);
			
	}
	
	public void levelOfSatisfactionMakeTrackSurvey()
	{	SeleniumUtil.click(track_RadioSatisfactionOption, driver);
		SeleniumUtil.click(track_RadioSatisfactionOption1, driver);
		SeleniumUtil.click(track_RadioSatisfactionOption2, driver);
		SeleniumUtil.click(track_RadioSatisfactionOption3,driver);
		SeleniumUtil.click(track_RadioSatisfactionOption4, driver);
		SeleniumUtil.click(track_RadioSatisfactionOption5, driver);
		SeleniumUtil.click(track_RadioSatisfactionOption6, driver);
			
	}
	public void levelOfSatisfactionNutritionSurvey()
	{	SeleniumUtil.click(track_RadionNutritioOption, driver);
		SeleniumUtil.click(track_RadionNutritioOption1, driver);
		SeleniumUtil.click(track_RadionNutritioOption2, driver);
		SeleniumUtil.click(track_RadionNutritioOption3,driver);
		SeleniumUtil.click(track_RadionNutritioOption4, driver);
		SeleniumUtil.click(track_RadionNutritioOption5, driver);
		SeleniumUtil.click(track_RadionNutritioOption6, driver);
			
	}
	
	public void fuelUpChallengeSurvey()
	{
		
		SeleniumUtil.click(participate_RadionNutritioOption1, driver);
		SeleniumUtil.click(participate_RadionNutritioOption2, driver);
		SeleniumUtil.click(participate_RadionNutritioOption3, driver);
		SeleniumUtil.click(participate_RadionNutritioOption4,driver);
		
	}
	
	public void participationSurvey()
	{
		SeleniumUtil.click(participate_Radiosurvey, driver);
		SeleniumUtil.click(participate_Radiosurvey1, driver);
		
	}
	
	private void stepsChallenge()
	{
		SeleniumUtil.click(track_steps1, driver);
		SeleniumUtil.click(track_steps2, driver);
		SeleniumUtil.click(track_steps3, driver);
		SeleniumUtil.click(track_steps4, driver);
		
	}
	
	public void participationSurvey1()
	{
		SeleniumUtil.click(participate_Radiosurvey2, driver);
		
	}
	
	public void addDaysActivity()
	{
		SeleniumUtil.sendKeysToWebElement(addDays, "15", driver);
		
	}
	
	
	public void clickSurveyOption(By element){
		Random randomRadio = new Random();
		int count = 0;
		if(element.toString().endsWith("li")){
			
			List<WebElement> radioBtnList = driver.findElements(element);
			count = radioBtnList.size()-2;
			radioBtnList.get(randomRadio.nextInt(count)).findElement(By.tagName("label")).click();
			logger.info("Clicked on a list option...");
		} else if(element.toString().endsWith("tbody")){
			//.//*[@class='sg-question-options']/table
			WebElement radioTable = driver.findElement(element);  
			  List<WebElement> rows=radioTable.findElements(By.tagName("tr"));  
				  for(count=0;count<rows.size();count++){
					  List<WebElement> columns=rows.get(count).findElements(By.tagName("td"));  
				 columns.get(randomRadio.nextInt(columns.size()-1)).findElement(By.tagName("label")).click();
				 logger.info("Clicked on a table radio option...");
			  }
		} else if(element.toString().endsWith("element")){
			SeleniumUtil.sendKeysToWebElement(element, "8", driver);
			logger.info("Entered no of hours slept...");
		} else {
		}
	}

	public boolean addExaleEntry() throws ParseException, InterruptedException{
		
		try{
		SeleniumUtil.sleep(2);
		//The logic written below is not working correctly,need to be reimplemented
		int beforeCount = noOfDaysGoal();
		SeleniumUtil.sleep(2);
		SeleniumUtil.click(addSleepEntry, driver);
		SeleniumUtil.sleep(1);
		SeleniumUtil.click(excerciseEntry, driver);
		SeleniumUtil.sleep(1);

		SeleniumUtil.click(addExcerciseDateEntry, driver);
		SeleniumUtil.click(dateSelect, driver);	
		SeleniumUtil.click(activitySelect, driver);
		SeleniumUtil.click(selectCategory, driver);
		SeleniumUtil.sendKeysToWebElement(entryField_Exhale, minuteEntry, driver);
		SeleniumUtil.clickbyJS(exerciseSubmitBtn, driver);
		SeleniumUtil.sleep(5);

		//SeleniumUtil.click(addExcerciseDateEntry, driver);
		boolean isDisplay = SeleniumUtil.isElementEnabled(addExcerciseDateEntry, driver);
			 if (isDisplay ==false){
				 return isDisplay;
			 }
			 else {
		
		   SeleniumUtil.click(addExcerciseDateEntry, driver);
			SeleniumUtil.selectRandomDropDown(sleepTimeDropDown,driver);
		  SeleniumUtil.selectRandomDropDown(wakeTimeDropDown,driver);
			SeleniumUtil.clickbyJS(entrySubmitBtn, driver);
		

		SeleniumUtil.waitForElementToBePresent(goalTrackNoOfDays, driver);
		int afterCount = noOfDaysGoal();
		if(afterCount==beforeCount+1){
			flag=true;
			logger.info("No. of days tracked has been recorded...");
		} 
		}}catch(Exception e){
			e.printStackTrace();
			flag=false;
		}	 
		return flag;
		
		}
	
public Boolean addPumpUpEntry() throws ParseException, InterruptedException{
		
		SeleniumUtil.sleep(2);
		int beforeCount = noOfDaysPumpOfGoal();
		SeleniumUtil.sleep(2);
	
		SeleniumUtil.click(addSleepEntry, driver);
		SeleniumUtil.click(excerciseEntry, driver);
		
		SeleniumUtil.click(addWeightEntry, driver);
		SeleniumUtil.sleep(2);
		
		SeleniumUtil.selectRandomDropDown(excerciseActivity,driver);
		SeleniumUtil.selectRandomDropDown(excercisetrackMinutes,driver);
		SeleniumUtil.sleep(2);
		SeleniumUtil.clickbyJS(entrySubmitBtn, driver);
		
		SeleniumUtil.waitForElementToBePresent(goalTrackEaseNoOfDays, driver);
		int afterCount = noOfDaysPumpOfGoal();
		Boolean count = false;
		if(afterCount==beforeCount+1){
			count=true;
			logger.info("No. of days tracked has been recorded...");
		} else {
			logger.info("No. of days tracked has NOT been recorded...");
		}
		return count;
	}
	
	
	public boolean addWeightEntry() throws ParseException, InterruptedException{
		
		SeleniumUtil.sleep(2);
		int beforeCount = noOfDaysGoal();
		SeleniumUtil.sleep(2);
		SeleniumUtil.click(addSleepEntry, driver);
		SeleniumUtil.click(excerciseEntry, driver);
		SeleniumUtil.click(addWeightEntry, driver);
		SeleniumUtil.sleep(2);
		SeleniumUtil.click(excerciseActivity,driver);
		SeleniumUtil.click(activityDrop, driver);
		SeleniumUtil.sendKeysToWebElement(excercisetrackMinutes,activityMinutes,driver);
		SeleniumUtil.clickbyJS(entrySubmitBtn, driver);
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
	
	public boolean addSleepEntry() throws ParseException, InterruptedException{
		SeleniumUtil.sleep(3);
		boolean isDisplay=true;
		int beforeCount = noOfDaysGoal();
		SeleniumUtil.sleep(3);
		SeleniumUtil.click(addSleepEntry, driver);
		SeleniumUtil.sleep(1);
		SeleniumUtil.click(sleepDateEntry, driver);
		SeleniumUtil.click(addDateEntry, driver);
		SeleniumUtil.selectRandomDropDown(sleepTimeDropDown,driver);
		SeleniumUtil.selectRandomDropDown(wakeTimeDropDown,driver);
		SeleniumUtil.clickbyJS(entrySubmitBtn, driver);
		
		SeleniumUtil.waitForElementToBePresent(goalTrackNoOfDays, driver);
		int afterCount = noOfDaysGoal();
		 flag = false;
		if(afterCount==beforeCount+1){
			flag=true;
			logger.info("No. of days tracked has been recorded...");
		} else {
			logger.info("No. of days tracked has NOT been recorded...");
		}
		return flag;
	}
	
	public Boolean addHeartyChallengeEntry() throws InterruptedException
	{
		SeleniumUtil.sleep(2);
		
		int beforeCount = noOfDaysHeartyGoal();
		SeleniumUtil.sleep(2);
		SeleniumUtil.click(addSleepEntry, driver);
		SeleniumUtil.click(sleepDateEntry, driver);
		
		if(SeleniumUtil.isElementDisplayed(addDateEntry, driver))
		{
			 return false ;
	
		}
		
	
		SeleniumUtil.click(addDateEntry, driver);
		SeleniumUtil.selectRandomDropDown(sleepTimeDropDown,driver);
		SeleniumUtil.selectRandomDropDown(wakeTimeDropDown,driver);
		SeleniumUtil.clickbyJS(entrySubmitBtn, driver);
		SeleniumUtil.waitForElementToBePresent(goalTrackEaseNoOfDays, driver);
		
		int afterCount = noOfDaysHeartyGoal();
		 flag = false;
		if(afterCount==beforeCount+1){
			flag=true;
			logger.info("No. of days tracked has been recorded...");
		} else {
			logger.info("No. of days tracked has NOT been recorded...");
		}
		
		return flag;
	}

		

	public boolean addSleepEntryForAtEaseChallenge() throws ParseException, InterruptedException{
		SeleniumUtil.sleep(2);
		int beforeCount = noOfDaysEaseGoal();
		SeleniumUtil.sleep(2);
		SeleniumUtil.click(addSleepEntry, driver);
		SeleniumUtil.click(sleepDateEntry, driver);
		SeleniumUtil.click(addDateEntry, driver);
		SeleniumUtil.selectRandomDropDown(sleepTimeDropDown,driver);
		SeleniumUtil.selectRandomDropDown(wakeTimeDropDown,driver);
		SeleniumUtil.clickbyJS(entrySubmitBtn, driver);
		SeleniumUtil.waitForElementToBePresent(goalTrackNoOfDays, driver);
		
		int afterCount = noOfDaysEaseGoal();
		Boolean count = false;
		if(afterCount==beforeCount+1){
			count=true;
			logger.info("No. of days tracked has been recorded...");
		} else {
			logger.info("No. of days tracked has NOT been recorded...");
		}
		
		return count;
	}
	
	public void fillSurvey_FindingBalance() throws InterruptedException{
		int count = 0;
		
		SeleniumUtil.waitForElementToBePresent(surveyIframe, driver);
		SeleniumUtil.switchToFrame(surveyIframe, driver);
		SeleniumUtil.sleep(4);
		driver.findElement(surveyCommonchkList1).sendKeys(Keys.ENTER);
		driver.findElement(surveyCommonchkList1).sendKeys(Keys.values());
		
		driver.findElement(surveyTest).sendKeys(Keys.ENTER);
		driver.findElement(surveyTest).sendKeys(Keys.values());
		
		
		driver.findElement(surveyTest).sendKeys("Test");
		List<WebElement> ele1 = driver.findElements(surveyCommonchkList1);
		  System.out.println("Check boxes are "+ele1.size());
		  for(int i=0;i<ele1.size();i++)
		  {
			   driver.findElement(surveyTest).sendKeys(Keys.ENTER);
				driver.findElement(surveyTest).sendKeys(Keys.values());		
			    ele1.get(3).click();
            	
		  }
		SeleniumUtil.sleep(2);
		SeleniumUtil.click(nextSurveyPageButton, driver);
		while(count<5){
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

    public void addStepsEntry(String startDate,String endDate) throws ParseException{
		
		SeleniumUtil.click(addSleepEntry, driver);
		SeleniumUtil.sleep(2);
		SeleniumUtil.click(calendarStepDatePicker, driver);
		SeleniumUtil.click(previousLink, driver);
		SeleniumUtil.click(stepsDay1, driver);
		SeleniumUtil.sendKeysToWebElement(stepsEntry, "1000", driver);
		SeleniumUtil.sleep(1);
		SeleniumUtil.click(stepsEntrySave, driver);
		logger.info("Steps Entry has been recorded...");
	}
    
public void addNutritionEntry() throws ParseException{
		
		SeleniumUtil.click(addNutritionEntry, driver);
		SeleniumUtil.sleep(2);
		SeleniumUtil.click(calendarNutritionDatePicker, driver);
		SeleniumUtil.sleep(2);
		WebDriverWait wait = new WebDriverWait(driver, 10);

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
				SeleniumUtil.sleep(4);
				SeleniumUtil.clickbyJS(foodEntrySave, driver);
				logger.info("Nutrition Food Entry has been recorded...");
			
		
		logger.info("Steps Entry has been recorded...");
	}

public void addFoodEntryForGeneralHealth() throws ParseException{
	
	SeleniumUtil.sleep(1);
	SeleniumUtil.click(foodDateEntries, driver);
	SeleniumUtil.sleep(1);
	SeleniumUtil.click(calendarDatePicker, driver);
	WebDriverWait wait = new WebDriverWait(driver, 10);

	wait.until(ExpectedConditions.presenceOfElementLocated(addFoodButton)); 
	SeleniumUtil.sleep(5);
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
	SeleniumUtil.sleep(3);
	SeleniumUtil.clickbyJS(foodEntrySave, driver);
	logger.info("Food Entry has been recorded...");
}

  public void addFoodEntry() throws ParseException{
		
		SeleniumUtil.click(foodDateEntry, driver);
		SeleniumUtil.sleep(1);
		SeleniumUtil.click(calendarDatePick, driver);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(addFoodButton)); 
		SeleniumUtil.sleep(5);
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
				SeleniumUtil.sleep(1);
				add.get(count).click();
			}
			count++;
		}
		SeleniumUtil.sleep(5);
		SeleniumUtil.clickbyJS(foodEntrySave, driver);
		logger.info("Food Entry has been recorded...");
	}
  
  public void fillEating_survey()
  {
	  SeleniumUtil.selRadioOption("sgE-2241264-11-46", 0, driver);
	  SeleniumUtil.selRadioOption("sgE-2241264-11-47", 1, driver);
	  SeleniumUtil.selRadioOption("sgE-2241264-11-48", 2, driver);
	  SeleniumUtil.selRadioOption("sgE-2241264-11-49", 3, driver);
	  
  }
  
  public void fillSurvey_Chef(){
		
		SeleniumUtil.waitForElementToBePresent(surveyIframe, driver);
		SeleniumUtil.switchToFrame(surveyIframe, driver);
		
		clickSurveyOption(surveyCommonRadioList);
		SeleniumUtil.click(nextSurveyPageButton, driver);
		driver.switchTo().defaultContent();
		
		
		SeleniumUtil.sleep(2);
		SeleniumUtil.waitForElementToBePresent(surveyIframe, driver);
		SeleniumUtil.switchToFrame(surveyIframe, driver);
		physicalActivity();
		SeleniumUtil.click(nextSurveyPageButton, driver);
		driver.switchTo().defaultContent();
		
		SeleniumUtil.sleep(2);
		SeleniumUtil.waitForElementToBePresent(surveyIframe, driver);
		SeleniumUtil.switchToFrame(surveyIframe, driver);
		levelOfSatisfactionSurvey();
		SeleniumUtil.click(nextSurveyPageButton, driver);
		driver.switchTo().defaultContent();
		
		SeleniumUtil.sleep(2);
		SeleniumUtil.waitForElementToBePresent(surveyIframe, driver);
		SeleniumUtil.switchToFrame(surveyIframe, driver);
		clickSurveyOption(surveyCommonRadioList);
		SeleniumUtil.click(nextSurveyPageButton, driver);
		driver.switchTo().defaultContent();
		
		SeleniumUtil.sleep(2);
		SeleniumUtil.waitForElementToBePresent(surveyIframe, driver);
		SeleniumUtil.switchToFrame(surveyIframe, driver);
		levelOfSatisfactionSurvey1();
		SeleniumUtil.click(nextSurveyPageButton, driver);
		driver.switchTo().defaultContent();
		
		SeleniumUtil.sleep(2);
		SeleniumUtil.waitForElementToBePresent(surveyIframe, driver);
		SeleniumUtil.switchToFrame(surveyIframe, driver);
		clickSurveyOption(surveyCommonRadioList);
		SeleniumUtil.click(nextSurveyPageButton, driver);
		driver.switchTo().defaultContent();
		
		SeleniumUtil.sleep(2);
		SeleniumUtil.waitForElementToBePresent(surveyIframe, driver);
		SeleniumUtil.switchToFrame(surveyIframe, driver);
		SeleniumUtil.sendKeysToWebElement(noOfHoursAdded,"120", driver);
		SeleniumUtil.click(nextSurveyPageButton, driver);
		driver.switchTo().defaultContent();
	
		SeleniumUtil.sleep(2);
		SeleniumUtil.waitForElementToBePresent(surveyIframe, driver);
		SeleniumUtil.switchToFrame(surveyIframe, driver);
		clickSurveyOption(surveyCommonRadioList);
		SeleniumUtil.click(nextSurveyPageButton, driver);
		driver.switchTo().defaultContent();
		
		SeleniumUtil.sleep(2);
		SeleniumUtil.waitForElementToBePresent(surveyIframe, driver);
		SeleniumUtil.switchToFrame(surveyIframe, driver);
		clickSurveyOption(surveyCommonRadioList);
		SeleniumUtil.click(nextSurveyPageButton, driver);
		driver.switchTo().defaultContent();
		
		SeleniumUtil.sleep(2);
		SeleniumUtil.waitForElementToBePresent(surveyIframe, driver);
		SeleniumUtil.switchToFrame(surveyIframe, driver);
		clickSurveyOption(surveyCommonRadioList);
		SeleniumUtil.click(completeSurveyButton, driver);
		driver.switchTo().defaultContent();
		logger.info("Stress Less Chef post challenge survey has been completed...");		
	}


  public void makeTrackSurvey(){
		
		SeleniumUtil.waitForElementToBePresent(surveyIframe, driver);
		SeleniumUtil.switchToFrame(surveyIframe, driver);
		
		clickSurveyOption(surveyCommonRadioList);
		SeleniumUtil.click(nextSurveyPageButton, driver);
		driver.switchTo().defaultContent();
		
		
		SeleniumUtil.sleep(2);
		SeleniumUtil.waitForElementToBePresent(surveyIframe, driver);
		SeleniumUtil.switchToFrame(surveyIframe, driver);
		trackActivity();
		SeleniumUtil.click(nextSurveyPageButton, driver);
		driver.switchTo().defaultContent();
		
		SeleniumUtil.sleep(2);
		SeleniumUtil.waitForElementToBePresent(surveyIframe, driver);
		SeleniumUtil.switchToFrame(surveyIframe, driver);
		levelOfSatisfactionMakeTrackSurvey();
		SeleniumUtil.click(nextSurveyPageButton, driver);
		driver.switchTo().defaultContent();
		
		SeleniumUtil.sleep(2);
		SeleniumUtil.waitForElementToBePresent(surveyIframe, driver);
		SeleniumUtil.switchToFrame(surveyIframe, driver);
		participationSurvey1();
		SeleniumUtil.click(nextSurveyPageButton, driver);
		driver.switchTo().defaultContent();
		
		SeleniumUtil.sleep(2);
		SeleniumUtil.waitForElementToBePresent(surveyIframe, driver);
		SeleniumUtil.switchToFrame(surveyIframe, driver);
		clickSurveyOption(surveyCommonRadioList);
		SeleniumUtil.click(nextSurveyPageButton, driver);
		driver.switchTo().defaultContent();
		
		SeleniumUtil.sleep(2);
		SeleniumUtil.waitForElementToBePresent(surveyIframe, driver);
		SeleniumUtil.switchToFrame(surveyIframe, driver);
		clickSurveyOption(surveyCommonRadioList);
		SeleniumUtil.click(nextSurveyPageButton, driver);
		driver.switchTo().defaultContent();
		
		SeleniumUtil.sleep(2);
		SeleniumUtil.waitForElementToBePresent(surveyIframe, driver);
		SeleniumUtil.switchToFrame(surveyIframe, driver);
		addDaysActivity();
		SeleniumUtil.click(nextSurveyPageButton, driver);
		driver.switchTo().defaultContent();
		
		
		SeleniumUtil.sleep(2);
		SeleniumUtil.waitForElementToBePresent(surveyIframe, driver);
		SeleniumUtil.switchToFrame(surveyIframe, driver);
		clickSurveyOption(surveyCommonRadioList);
		SeleniumUtil.click(completeSurveyButton, driver);
		driver.switchTo().defaultContent();
		logger.info("Stress Less Chef post challenge survey has been completed...");		
	}

  
  
  public void fillSurvey_weight(){
		
		SeleniumUtil.waitForElementToBePresent(surveyIframe, driver);
		SeleniumUtil.switchToFrame(surveyIframe, driver);
		
		clickSurveyOption(surveyCommonRadioList);
		SeleniumUtil.click(nextSurveyPageButton, driver);
		driver.switchTo().defaultContent();
		
		
		SeleniumUtil.sleep(2);
		SeleniumUtil.waitForElementToBePresent(surveyIframe, driver);
		SeleniumUtil.switchToFrame(surveyIframe, driver);
		weightActivity();
		SeleniumUtil.click(nextSurveyPageButton, driver);
		driver.switchTo().defaultContent();
		
		SeleniumUtil.sleep(2);
		SeleniumUtil.waitForElementToBePresent(surveyIframe, driver);
		SeleniumUtil.switchToFrame(surveyIframe, driver);
		levelOfSatisfactionWeightSurvey();
		SeleniumUtil.click(nextSurveyPageButton, driver);
		driver.switchTo().defaultContent();
		
		SeleniumUtil.sleep(2);
		SeleniumUtil.waitForElementToBePresent(surveyIframe, driver);
		SeleniumUtil.switchToFrame(surveyIframe, driver);
		participationSurvey();
		SeleniumUtil.click(nextSurveyPageButton, driver);
		driver.switchTo().defaultContent();
		
		SeleniumUtil.sleep(2);
		SeleniumUtil.waitForElementToBePresent(surveyIframe, driver);
		SeleniumUtil.switchToFrame(surveyIframe, driver);
		addsleepEntry();
		SeleniumUtil.click(nextSurveyPageButton, driver);
		driver.switchTo().defaultContent();
		
		SeleniumUtil.sleep(2);
		SeleniumUtil.waitForElementToBePresent(surveyIframe, driver);
		SeleniumUtil.switchToFrame(surveyIframe, driver);
		clickMonthlySurvey();
		SeleniumUtil.click(nextSurveyPageButton, driver);
		driver.switchTo().defaultContent();
		
	
		SeleniumUtil.sleep(2);
		SeleniumUtil.waitForElementToBePresent(surveyIframe, driver);
		SeleniumUtil.switchToFrame(surveyIframe, driver);
		clickSurveyOption(surveyCommonRadioList);
		SeleniumUtil.click(completeSurveyButton, driver);
		driver.switchTo().defaultContent();
		logger.info("Stress Less Chef post challenge survey has been completed...");		
	}



  public void fillSteps_Survey(){
		
		SeleniumUtil.waitForElementToBePresent(surveyIframe, driver);
		SeleniumUtil.switchToFrame(surveyIframe, driver);
		
		clickSurveyOption(surveyCommonRadioList);
		SeleniumUtil.click(nextSurveyPageButton, driver);
		driver.switchTo().defaultContent();
		
		
		SeleniumUtil.sleep(2);
		SeleniumUtil.waitForElementToBePresent(surveyIframe, driver);
		SeleniumUtil.switchToFrame(surveyIframe, driver);
		trackActivity();
		SeleniumUtil.click(nextSurveyPageButton, driver);
		driver.switchTo().defaultContent();
		
		SeleniumUtil.sleep(2);
		SeleniumUtil.waitForElementToBePresent(surveyIframe, driver);
		SeleniumUtil.switchToFrame(surveyIframe, driver);
		levelOfSatisfactionMakeTrackSurvey();
		SeleniumUtil.click(nextSurveyPageButton, driver);
		driver.switchTo().defaultContent();
		
		SeleniumUtil.sleep(2);
		SeleniumUtil.waitForElementToBePresent(surveyIframe, driver);
		SeleniumUtil.switchToFrame(surveyIframe, driver);
		clickSurveyOption(surveyCommonRadioList);
		SeleniumUtil.click(nextSurveyPageButton, driver);
		driver.switchTo().defaultContent();
		
		SeleniumUtil.sleep(2);
		SeleniumUtil.waitForElementToBePresent(surveyIframe, driver);
		SeleniumUtil.switchToFrame(surveyIframe, driver);
		stepsChallenge();
		SeleniumUtil.click(nextSurveyPageButton, driver);
		driver.switchTo().defaultContent();
		
		
		SeleniumUtil.sleep(2);
		SeleniumUtil.waitForElementToBePresent(surveyIframe, driver);
		SeleniumUtil.switchToFrame(surveyIframe, driver);
		clickSurveyOption(surveyCommonRadioList);
		SeleniumUtil.click(nextSurveyPageButton, driver);
		driver.switchTo().defaultContent();
		
		SeleniumUtil.sleep(2);
		SeleniumUtil.waitForElementToBePresent(surveyIframe, driver);
		SeleniumUtil.switchToFrame(surveyIframe, driver);
		clickSurveyOption(surveyCommonRadioList);
		SeleniumUtil.click(nextSurveyPageButton, driver);
		driver.switchTo().defaultContent();
		
		
		
		SeleniumUtil.sleep(2);
		SeleniumUtil.waitForElementToBePresent(surveyIframe, driver);
		SeleniumUtil.switchToFrame(surveyIframe, driver);
		addsleepEntry();
		SeleniumUtil.click(nextSurveyPageButton, driver);
		driver.switchTo().defaultContent();
	
		SeleniumUtil.sleep(2);
		SeleniumUtil.waitForElementToBePresent(surveyIframe, driver);
		SeleniumUtil.switchToFrame(surveyIframe, driver);
		clickSurveyOption(surveyCommonRadioList);
		SeleniumUtil.click(completeSurveyButton, driver);
		driver.switchTo().defaultContent();
		logger.info("Stress Less Chef post challenge survey has been completed...");		
	}
  
  public void completeSteps_Survey(){
		
		SeleniumUtil.waitForElementToBePresent(surveyIframe, driver);
		SeleniumUtil.switchToFrame(surveyIframe, driver);
		
		clickSurveyOption(surveyCommonRadioList);
		SeleniumUtil.click(nextSurveyPageButton, driver);
		driver.switchTo().defaultContent();
		
		
		SeleniumUtil.sleep(2);
		SeleniumUtil.waitForElementToBePresent(surveyIframe, driver);
		SeleniumUtil.switchToFrame(surveyIframe, driver);
		trackActivity();
		SeleniumUtil.click(nextSurveyPageButton, driver);
		driver.switchTo().defaultContent();
		
		SeleniumUtil.sleep(2);
		SeleniumUtil.waitForElementToBePresent(surveyIframe, driver);
		SeleniumUtil.switchToFrame(surveyIframe, driver);
		levelOfSatisfactionMakeTrackSurvey();
		SeleniumUtil.click(nextSurveyPageButton, driver);
		driver.switchTo().defaultContent();
		
		SeleniumUtil.sleep(2);
		SeleniumUtil.waitForElementToBePresent(surveyIframe, driver);
		SeleniumUtil.switchToFrame(surveyIframe, driver);
		clickSurveyOption(surveyCommonRadioList);
		SeleniumUtil.click(nextSurveyPageButton, driver);
		driver.switchTo().defaultContent();
		
		SeleniumUtil.sleep(2);
		SeleniumUtil.waitForElementToBePresent(surveyIframe, driver);
		SeleniumUtil.switchToFrame(surveyIframe, driver);
		stepsChallenge();
		SeleniumUtil.click(nextSurveyPageButton, driver);
		driver.switchTo().defaultContent();
		
		
		SeleniumUtil.sleep(2);
		SeleniumUtil.waitForElementToBePresent(surveyIframe, driver);
		SeleniumUtil.switchToFrame(surveyIframe, driver);
		clickSurveyOption(surveyCommonRadioList);
		SeleniumUtil.click(nextSurveyPageButton, driver);
		driver.switchTo().defaultContent();
		
		SeleniumUtil.sleep(2);
		SeleniumUtil.waitForElementToBePresent(surveyIframe, driver);
		SeleniumUtil.switchToFrame(surveyIframe, driver);
		clickSurveyOption(surveyCommonRadioList);
		SeleniumUtil.click(nextSurveyPageButton, driver);
		driver.switchTo().defaultContent();
		
		
		
		SeleniumUtil.sleep(2);
		SeleniumUtil.waitForElementToBePresent(surveyIframe, driver);
		SeleniumUtil.switchToFrame(surveyIframe, driver);
		addStepsEntry();
		SeleniumUtil.click(nextSurveyPageButton, driver);
		driver.switchTo().defaultContent();
	
		SeleniumUtil.sleep(2);
		SeleniumUtil.waitForElementToBePresent(surveyIframe, driver);
		SeleniumUtil.switchToFrame(surveyIframe, driver);
		clickSurveyOption(surveyCommonRadioList);
		SeleniumUtil.click(completeSurveyButton, driver);
		driver.switchTo().defaultContent();
		logger.info("Stress Less Chef post challenge survey has been completed...");		
	}


  public void fillNutrition_Survey(){
		
		SeleniumUtil.waitForElementToBePresent(surveyIframe, driver);
		SeleniumUtil.switchToFrame(surveyIframe, driver);
		
		clickSurveyOption(surveyCommonRadioList);
		SeleniumUtil.click(nextSurveyPageButton, driver);
		driver.switchTo().defaultContent();
		
		
		SeleniumUtil.sleep(2);
		SeleniumUtil.waitForElementToBePresent(surveyIframe, driver);
		SeleniumUtil.switchToFrame(surveyIframe, driver);
		eatingActivity();
		SeleniumUtil.click(nextSurveyPageButton, driver);
		driver.switchTo().defaultContent();
		
		SeleniumUtil.sleep(2);
		SeleniumUtil.waitForElementToBePresent(surveyIframe, driver);
		SeleniumUtil.switchToFrame(surveyIframe, driver);
		levelOfSatisfactionNutritionSurvey();
		SeleniumUtil.click(nextSurveyPageButton, driver);
		driver.switchTo().defaultContent();
		
		SeleniumUtil.sleep(2);
		SeleniumUtil.waitForElementToBePresent(surveyIframe, driver);
		SeleniumUtil.switchToFrame(surveyIframe, driver);
		clickSurveyOption(surveyCommonRadioList);
		SeleniumUtil.click(nextSurveyPageButton, driver);
		driver.switchTo().defaultContent();
		
		SeleniumUtil.sleep(2);
		SeleniumUtil.waitForElementToBePresent(surveyIframe, driver);
		SeleniumUtil.switchToFrame(surveyIframe, driver);
		fuelUpChallengeSurvey();
		SeleniumUtil.click(nextSurveyPageButton, driver);
		driver.switchTo().defaultContent();
		
		SeleniumUtil.sleep(2);
		SeleniumUtil.waitForElementToBePresent(surveyIframe, driver);
		SeleniumUtil.switchToFrame(surveyIframe, driver);
		clickSurveyOption(surveyCommonRadioList);
		SeleniumUtil.click(nextSurveyPageButton, driver);
		driver.switchTo().defaultContent();
		
		SeleniumUtil.sleep(2);
		SeleniumUtil.waitForElementToBePresent(surveyIframe, driver);
		SeleniumUtil.switchToFrame(surveyIframe, driver);
		clickSurveyOption(surveyCommonRadioList);
		SeleniumUtil.click(completeSurveyButton, driver);
		driver.switchTo().defaultContent();
		logger.info("Stress Less Chef post challenge survey has been completed...");		
	}

  
  
  
       public void logOut()
       {
    	   
    	   SeleniumUtil.click(logout, driver);
       }
  
  
}
