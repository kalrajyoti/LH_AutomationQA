package com.lh.pages.authenticated.lifestyleManager;

import static com.lh.helper.DriverFactory.driver;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.lh.pages.authenticated.MainPage;
import com.lh.testConfig.TestProperty;
import com.lh.utils.SeleniumUtil;

public class LifestyleManagerPage extends MainPage {

	private By stepsLogLink						= By.xpath("//li[@id='lm-subnav-logs']//a[text()='Steps']");
	private String typeLink						="//li[@id='lm-subnav-logs']//a[text()='%s']";
	private String verifyText					="//div[@id='lm-logs']//div[text()='Track Your %s']";
	private By stepsEntry						=By.xpath("//input[@placeholder='Steps']");
	private By datePicker						=By.xpath("//input[@id='stepsDatePricker']");
	private By addedStepsList   				=By.xpath("//div[@id='steps']//div[@ng-show='activities.length > 0']/div");
	private By activityTypeColSteps 			= By.xpath("//div[@data-column='steps-steps']");
	private static Logger logger 				= LogManager.getLogger(com.lh.pages.authenticated.lifestyleManager.LifestyleManagerPage.class);
	private By dashboardLink					= By.xpath("//li[@id='lm-subnav-db']/a");
	private By logsLink							= By.xpath("//li[@id='lm-subnav-logs']/a");	
	private By exerciseLink						= By.xpath("//li[@id='lm-subnav-logs']//a[text()='Exercise']");
	private By sleepLink						= By.xpath("//li[@id='lm-subnav-logs']//a[text()='Sleep']");
	private By exerciseText						= By.xpath("//div[@id='lm-logs']//div[text()='Track Your Exercise']");
	private By sleepText						= By.xpath("//div[@id='lm-logs']//div[text()='Track Your Sleep']");
	private By addEntryIcon						= By.xpath("//div[@class='lm-add-entry']/a/img[@alt='add entry/add']");
	private By addEntryIcon_Steps				= By.xpath("(//div[@class='lm-add-entry']/a/img)[1]");
	private By inputDate_Exercise				= By.xpath("(//div[@class='datepicker']/input)[4]");
	private By activityList						= By.xpath("//select[@class='ng-pristine ng-invalid ng-invalid-required']");
	private By activityDuration					= By.xpath("//div[@class='add-data-panel']//div[@class='minutes']/input");
	private By stepsactivitySubmitButton				= By.xpath("//input[@type='button' and @ng-click='addStepsActivity()']");
	
	private String editLink_Steps				="//div[@data-column='steps-date' and text()='%s']/following-sibling::div[@data-column='steps-action']/a[text()='Edit']";
	private String deleteLink_Steps				="//div[@data-column='steps-date' and text()='%s']/following-sibling::div[@data-column='steps-action']/a[text()='Delete']";


	private By activitySubmitButton				= By.xpath("//input[@name='submitfitness']");
	private String editLink_Exericse			= "//div[text()='%s']/following-sibling::div[@data-column='exercise-action']//a[text()='Edit']";
	private String deleteLink					= "//div[text()='%s']/following-sibling::div[@data-column='exercise-action']//a[text()='Delete']";

	private By activitySubmitButton_Steps		= By.xpath("(//input[@value='Submit'])[1]");
	private By yesButton						= By.xpath("//input[@id='YesButton']");
	
	private By addedActivityList				= By.xpath("//div[@id='exercise']//div[@ng-show='activities.length > 0']/div");
	private String exerciseval					= "Exercise";
	private String sleepval						= "Sleep";
	private By activityTypeCol 					= By.xpath("div[@data-column='exercise-activity']");
	private By durationCol						= By.xpath("div[@data-column='exercise-duration']");

	private By editDateText						= By.xpath("(//div[@data-column='steps-date'])[1]");
	private String editStepsText				= "//div[@class='body']//div[text()='%s']/following-sibling::div[@data-column='steps-steps']";
	private By confirmDeleteButton			    =By.xpath("//form[@id='modalForm']//input[@id='YesButton']");
	private By okButton						    =By.xpath("//form[@id='modalForm']//input[@id='OKButton']");

	private By addEntryIcon_Sleep				= By.xpath("(//div[@class='lm-add-entry']/a/img)[3]");
	private By sleepTime						= By.xpath("//select[@name='sleeptime']");
	private By wakeTime							= By.xpath("//select[@name='waketime']");
	private By sleepSubmitBtn					= By.xpath("//div[@id='sleep']//input[@value='Submit']");
	private By inputeDate_Sleep					= By.xpath("(//div[@class='datepicker']/input)[5]");
	private By totalSleep						= By.xpath("//div[@data-column='sleep-total']");
	private By editLink_Sleep					= By.xpath("(//div[contains(@id,'ContentPlaceHolder1_LifestyleManagerControl_sleepAction2')])[1]/a[text()='Edit']");
	private By deleteLink_Sleep					= By.xpath("(//div[contains(@id,'ContentPlaceHolder1_LifestyleManagerControl_sleepAction2')])[1]/a[text()='Delete']");
	

	public LifestyleManagerPage() {

		super();
		Reporter.log("Initializing the OnTarget plan page Object...");

		if (!(driver.getTitle().equalsIgnoreCase(TestProperty.CLIENT_PORTAL_TITLE))) {
			logger.error("The current page is not the Contact LiveHealthier page");
			throw new IllegalStateException(
					"This is not the Contact LiveHealthier page");
		}
		Reporter.log("Initialized the OnTarget plan page object");

	}
	public boolean dashboardLink(){

		return SeleniumUtil.isElementDisplayed(dashboardLink, driver);
	}

	public boolean logslink(){

		return SeleniumUtil.isElementDisplayed(logsLink, driver);		
	}


	public void clickStepsLog(){
		SeleniumUtil.sleep(2);
		SeleniumUtil.waitForElementToBeVisible(logsLink, driver);
		SeleniumUtil.hover(logsLink, driver);
		SeleniumUtil.click(logsLink, driver);
		SeleniumUtil.sleep(2);
		SeleniumUtil.click(stepsLogLink, driver);
		SeleniumUtil.sleep(2);
	}
	public boolean verifyLinks(String typeText){

		SeleniumUtil.waitForElementToBeVisible(logsLink, driver);
		SeleniumUtil.hover(logsLink, driver);
		SeleniumUtil.click(logsLink, driver);
		SeleniumUtil.sleep(2);
		SeleniumUtil.click(SeleniumUtil.dynamicXpath(typeLink, typeText), driver);
		SeleniumUtil.waitForElementToBeVisible(SeleniumUtil.dynamicXpath(verifyText, typeText), driver);
		return SeleniumUtil.getTextfromWebElement(SeleniumUtil.dynamicXpath(verifyText, typeText), driver).contains(typeText);
		

	}

	public boolean verifyExerciseLink(){
		SeleniumUtil.hover(logsLink, driver);
		SeleniumUtil.click(logsLink, driver);
		SeleniumUtil.sleep(2);
		SeleniumUtil.click(exerciseLink, driver);
		SeleniumUtil.waitForElementToBeVisible(exerciseText, driver);
		return SeleniumUtil.getTextfromWebElement(exerciseText, driver).contains(exerciseval); 	
	}


	public void addEntry_Steps(Map<String, String> lmLogData) throws ParseException{

		clickStepsLog();
		logger.info("Clicked on steps log link");
		ArrayList<String> dateList = splitPipeSepratedString(lmLogData.get("currentData_Steps"));
		ArrayList<String> stepsList = splitPipeSepratedString(lmLogData.get("stepsEntry"));
		for(int i=0;i<dateList.size();i++){
			SeleniumUtil.sleep(3);
			SeleniumUtil.waitForElementToBeVisible(addEntryIcon_Steps, driver);
			SeleniumUtil.element(addEntryIcon_Steps, driver).click();
			SeleniumUtil.click(datePicker, driver);
			SeleniumUtil.selectGivenDate(dateList.get(i), driver);
			driver.findElement(stepsEntry).sendKeys(stepsList.get(i));
			SeleniumUtil.click(activitySubmitButton_Steps, driver);
			SeleniumUtil.sleep(3);
		SeleniumUtil.waitForElementToBeVisible(addEntryIcon_Steps, driver);
		}
	}

		public boolean verifyAddedEntrySteps(Map<String,String> lmLogData,String key1){

			boolean verify = false;
			ArrayList<String> verifyActualActivityList = new ArrayList<String>();	
			List<WebElement> addedSteps 	= driver.findElements(addedStepsList);
			for(int i=0;i<addedSteps.size()-1;i++){
				verifyActualActivityList.add(addedSteps.get(i).findElement(activityTypeColSteps).getText());			
			}
			ArrayList<String> verifyExpectedActivityList = new ArrayList<String>();
			ArrayList<String> expectedDataList = splitPipeSepratedString(lmLogData.get(key1));
			for(int i=0;i<expectedDataList.size();i++){
				verifyExpectedActivityList.add(expectedDataList.get(i));
			}
			if(verifyActualActivityList.size()!=verifyExpectedActivityList.size()){
				return verify = false;
			}
			else{
				Collections.sort(verifyActualActivityList);
				Collections.sort(verifyExpectedActivityList);
				verifyExpectedActivityList.containsAll(verifyActualActivityList);
				verify = true;
			}
			return verify;
		}

		
		public boolean editStepsEntry(String editedSteps){
			
			boolean flag = false;
			clickStepsLog();
			String editdate=SeleniumUtil.getTextfromWebElement(editDateText, driver);
			SeleniumUtil.element(SeleniumUtil.dynamicXpath(editLink_Steps, editdate), driver).click();
		    SeleniumUtil.click(stepsEntry, driver);
		    SeleniumUtil.element(stepsEntry, driver).clear();
		    SeleniumUtil.sendKeysToWebElement(stepsEntry, editedSteps, driver);
		    SeleniumUtil.click(stepsactivitySubmitButton, driver);
		    SeleniumUtil.sleep(3);
		    SeleniumUtil.waitForElementToBeVisible(addEntryIcon_Steps, driver);
		    String editedStepsEntry=SeleniumUtil.getTextfromWebElement(SeleniumUtil.dynamicXpath(editStepsText, editdate), driver);
		    if(editedStepsEntry.equals(editedSteps))
		    		flag= true;
		    
			return flag;
		        
		}
		public boolean deleteStepsEntry(){
			boolean flag = false;
			clickStepsLog();
			String deleteDate=SeleniumUtil.getTextfromWebElement(editDateText, driver);
			SeleniumUtil.sleep(3);
			SeleniumUtil.element(SeleniumUtil.dynamicXpath(deleteLink_Steps, deleteDate), driver).click();
			SeleniumUtil.sleep(3);
			SeleniumUtil.click(confirmDeleteButton, driver);
			SeleniumUtil.sleep(3);
			SeleniumUtil.click(okButton, driver);
			SeleniumUtil.waitForElementToBeVisible(addEntryIcon_Steps, driver);
			if(!SeleniumUtil.isElementPresent(SeleniumUtil.dynamicXpath(deleteLink_Steps, deleteDate), driver))
				
				flag=true;
			
			return flag;
			
		}
		
		
		
	public ArrayList<String> splitPipeSepratedString(String pipeSepratedSting){

			ArrayList<String> alist = new ArrayList<String>();
			StringTokenizer st = new StringTokenizer(pipeSepratedSting, "|");
			while(st.hasMoreTokens()){
				alist.add(st.nextToken());
			}
			return alist;
		}

	public boolean verifySleepLink(){

		SeleniumUtil.hover(logsLink, driver);
		SeleniumUtil.click(logsLink, driver);
		SeleniumUtil.sleep(2);
		SeleniumUtil.click(sleepLink, driver);
		SeleniumUtil.waitForElementToBeVisible(sleepText, driver);
		return SeleniumUtil.getTextfromWebElement(sleepText, driver).contains(sleepval);

	}
	
	public void clickOnLogsLink(){
		SeleniumUtil.waitForElementToBeVisible(logsLink, driver);
		SeleniumUtil.sleep(2);
		SeleniumUtil.hover(logsLink, driver);
		SeleniumUtil.element(logsLink, driver).click();
	}
	
	public void clickOnExerciseLog(){
		SeleniumUtil.sleep(2);
		SeleniumUtil.waitForElementToBeVisible(exerciseLink, driver);
		SeleniumUtil.element(exerciseLink, driver).click();
	}
	

	public void addEntry_Exercise(Map<String, String> lmLogData) throws ParseException{
		ArrayList<String> dateList = splitPipeSepratedString(lmLogData.get("currentDate"));
		ArrayList<String> activityTypeList = splitPipeSepratedString(lmLogData.get("activityType"));
		ArrayList<String> durationList = splitPipeSepratedString(lmLogData.get("duration"));
		for(int i=0;dateList.size()>i;i++){
			SeleniumUtil.sleep(5);
			SeleniumUtil.waitForElementToBeVisible(addEntryIcon, driver);
			SeleniumUtil.element(addEntryIcon, driver).click();
			SeleniumUtil.waitForElementToBePresent(activityDuration, driver);
			SeleniumUtil.element(inputDate_Exercise, driver).click();
			SeleniumUtil.sleep(2);
			SeleniumUtil.selectGivenDate(dateList.get(i),driver);
			SeleniumUtil.selectByVisibleText(activityList, activityTypeList.get(i), driver);
			SeleniumUtil.element(activityDuration, driver).click();
			SeleniumUtil.element(activityDuration,driver).sendKeys(durationList.get(i));
			SeleniumUtil.element(activitySubmitButton,driver).click();
			SeleniumUtil.sleep(3);
		}
	}
	
	
	public boolean verifyLMData_Exercise(Map<String,String> lmLogData,String key1){
		boolean verify = false;
		ArrayList<String> verifyActualActivityList = new ArrayList<String>();	
		
		List<WebElement> actualActivityList 	= driver.findElements(addedActivityList);
		for(int i=0;i<actualActivityList.size()-1;i++){
			//verifyActualActivityList.add(actualActivityList.get(i).findElement(dateCol).getText());
			verifyActualActivityList.add(actualActivityList.get(i).findElement(activityTypeCol).getText());
			verifyActualActivityList.add(actualActivityList.get(i).findElement(durationCol).getText());
	}
		
		ArrayList<String> verifyExpectedActivityList = new ArrayList<String>();
		ArrayList<String> expectedDataList = splitPipeSepratedString(lmLogData.get(key1));
		
		String[] entry = null ;
		for(String pair : expectedDataList)	{
			entry = pair.split("#");   
			//verifyExpectedActivityList.add(entry[0]);
			verifyExpectedActivityList.add(entry[1]);
			verifyExpectedActivityList.add(entry[2]);
		}
		
		SeleniumUtil.sleep(3);
		if(verifyActualActivityList.size()!=verifyExpectedActivityList.size()){
			return verify = false;
		}
		else{
			Collections.sort(verifyActualActivityList);
			Collections.sort(verifyExpectedActivityList);
			verifyExpectedActivityList.containsAll(verifyActualActivityList);
			verify = true;
		}
		return verify;
	}
	
	public void editAddedEntry_Exercise(String editDuration,String activityName){
		SeleniumUtil.element(SeleniumUtil.dynamicXpath(editLink_Exericse, activityName), driver).click();
		SeleniumUtil.element(activityDuration, driver).click();
		SeleniumUtil.sleep(2);
		SeleniumUtil.element(activityDuration, driver).clear();
		SeleniumUtil.element(activityDuration, driver).sendKeys(editDuration);
		SeleniumUtil.element(activitySubmitButton, driver).click();
		SeleniumUtil.sleep(2);
		SeleniumUtil.waitForElementToBeVisible(SeleniumUtil.dynamicXpath(editLink_Exericse, activityName), driver);
	}
	
	public void deleteEntry_Exercise(String activityName){
		SeleniumUtil.waitForElementToBeVisible(SeleniumUtil.dynamicXpath(deleteLink,activityName), driver);
		SeleniumUtil.element(SeleniumUtil.dynamicXpath(deleteLink, activityName), driver).click();
		SeleniumUtil.sleep(2);
		SeleniumUtil.element(yesButton, driver).click();
		SeleniumUtil.sleep(3);
		SeleniumUtil.element(okButton, driver).click();
	}
	
	public void clickOnSleepLog(){
		SeleniumUtil.sleep(2);
		SeleniumUtil.element(sleepLink, driver).click();
	}
	
	public void addEntry_Sleep(Map<String, String> lmLogData) throws ParseException{
		ArrayList<String> dateList = splitPipeSepratedString(lmLogData.get("currentDate"));
		for(int i=1;i<=3; i++){
			SeleniumUtil.sleep(6);
			SeleniumUtil.waitForElementToBeVisible(addEntryIcon_Sleep, driver);
			SeleniumUtil.element(addEntryIcon_Sleep, driver).click();
			SeleniumUtil.element(inputeDate_Sleep, driver).click();
			SeleniumUtil.sleep(2);
			SeleniumUtil.selectGivenDate(dateList.get(i-1),driver);
			SeleniumUtil.selectByVisibleText(sleepTime, lmLogData.get("sleepTimeEntry"+i), driver);
			SeleniumUtil.selectByVisibleText(wakeTime, lmLogData.get("wakeTimeEntry"+i), driver);
			driver.findElement(sleepSubmitBtn).click();
			SeleniumUtil.sleep(3);
		}
	}
	
	public boolean veifyAdded_DeletedSleepData(Map<String,String> lmLogData,String key){
		SeleniumUtil.sleep(3);
		boolean verify = false;
		List<WebElement> totalSleepList	= driver.findElements(totalSleep);
		for(int i=0;i<totalSleepList.size()-2;i++){
			if(!totalSleepList.get(i+1).getText().equals(lmLogData.get(key))){
				return verify = false;
			}
			else{
				 verify = true;
			}
		}
		return verify;
	}
	
	public void editEntryInSleepLog(Map<String,String> lmLogData){
		SeleniumUtil.element(editLink_Sleep, driver).click();
		SeleniumUtil.selectByVisibleText(sleepTime, lmLogData.get("editSleepTimeEntry1"), driver);
		driver.findElement(sleepSubmitBtn).click();
		SeleniumUtil.sleep(3);
	}
	
	public boolean veifyEditSleepData(Map<String, String> lmLogData, String key){
		SeleniumUtil.sleep(3);
		boolean verify = false;
		ArrayList<String> sleepList	= splitPipeSepratedString(lmLogData.get(key));
		List<WebElement> totalSleepList	= driver.findElements(totalSleep);
		for(int i=0;i<totalSleepList.size()-2;i++){
			if(!totalSleepList.get(i+1).getText().equals(sleepList.get(i))){
				return verify = false;
			}
			else{
				 verify = true;
			}
		}
		return verify;
	}
	
	public void deleteEntryInSleepLog(){
		SeleniumUtil.element(deleteLink_Sleep, driver).click();
		SeleniumUtil.sleep(2);
		SeleniumUtil.element(yesButton, driver).click();
		SeleniumUtil.sleep(3);
		SeleniumUtil.element(okButton, driver).click();
		SeleniumUtil.sleep(3);
	}
}

