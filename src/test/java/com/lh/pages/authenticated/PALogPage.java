package com.lh.pages.authenticated;

import static com.lh.helper.DriverFactory.driver;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import org.apache.logging.log4j.Logger;import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.lh.testConfig.TestProperty;
import com.lh.utils.SeleniumUtil;

/**
 * @author Sanjeev.Kumar
 *
 */
public class PALogPage extends MainPage{

	private By activityDuration 			= By.id("add-34-inputEl");
	private By activityType					= By.id("add-33-inputEl");
	private By activitySubmit				= By.xpath("//span[@id='btnAddItem']/a[text()='Submit']");
	private By month 						= By.className("ui-datepicker-month");
	private By year 						= By.className("ui-datepicker-year");
	private By startDate 					= By.id("txtStartDate");
	private By endDate						= By.id("txtEndDate");
	private String ans1 					= "//div[@id='dvSelectValues']/div[text()='%s']";
	private String activityLogDate			= "document.getElementById('add-32-inputEl').value='%s'";
	private String days 					= "//table[@class='ui-datepicker-calendar']/tbody/tr/td/a[text()='%s']";
	private By calendarBar					= By.xpath("//div[@class='ui-datepicker-buttonpane ui-widget-content']");
	private By activityFeedDetailTable		= By.xpath("//div[@id='activityFeedDetailContainer']/table/tbody");
	private By activityfeedDetailTableRows	= By.xpath("//tr[(contains(@class,'physicalActivityLog'))]");
	private By editEntryBtn					= By.id("btnEditEntry");
	private String editEntryInput			="//div[@recordtemplate='%s']/following-sibling::div[@recordtemplate='%s']/following-sibling::div//input";
	//div[@recordtemplate='07/13/2015']/following-sibling::div//input[@value='AEROBIC GENERAL']/ancestor::div//input[@value='40']
			//"//div[@recordtemplate='${date}']/following-sibling::div//input[@value='${category}']/ancestor::div//input[@value='${value}']";
	//private By editEntryInput				= By.xpath("//div[@id='trigger-32-14233']/following-sibling::div//input[@id='trigger-34-1423334-inputEl']");
	private By activityTrackerHeader		= By.id("pnlHeader-innerCt");
	
	/**
	 * Logger to log messages
	 */
	private static Logger logger = LogManager.getLogger(PALogPage.class);

	public PALogPage() {
		super();
		Reporter.log("Initializing the HealthAssessmentPage Object...");
		// Check that we're on the right page.
		if (!(driver.getTitle().equalsIgnoreCase(TestProperty.CLIENT_PORTAL_TITLE))) {
			logger.error("This is not the Health Assessment page");
			throw new IllegalStateException(
					"This is not the HealthAssessmentPage page");
		}
		Reporter.log("Initialized the HealthAssessmentPage page object");
	}

	public void selectDateJS(String jsElemetLocation, String date) {
		JavascriptExecutor js;
		js = (JavascriptExecutor) driver;
		SeleniumUtil.sleep(2);
		if (date != null && !date.equals("")) {
			js.executeScript(jsElemetLocation.replaceAll("%s",date));
		}	
	}

	public void addPALog(Map<String, String> paLogData) {				
		ArrayList<String> dateList = splitPipeSepratedString(paLogData.get("date"));
		ArrayList<String> activityTypeList = splitPipeSepratedString(paLogData.get("activityType"));
		ArrayList<String> durationList = splitPipeSepratedString(paLogData.get("duration"));
		driver.switchTo().frame("iframeTracker");
		for(int i=0;dateList.size()>i;i++){
			SeleniumUtil.waitForElementToBePresent(activityDuration, driver);
			
			selectDateJS(activityLogDate, dateList.get(i));
			driver.findElement(activityType).sendKeys(activityTypeList.get(i));
			driver.findElement(SeleniumUtil.dynamicXpath(ans1,activityTypeList.get(i))).click();
			driver.findElement(activityDuration).sendKeys(durationList.get(i));
			driver.findElement(activitySubmit).click();
			SeleniumUtil.sleep(3);
		}	
		driver.switchTo().defaultContent();	
	}

	public ArrayList<String> splitPipeSepratedString(String pipeSepratedSting){

		ArrayList<String> alist = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(pipeSepratedSting, "|");
		while(st.hasMoreTokens()){
			alist.add(st.nextToken());
		}
		return alist;

	}

	public boolean verifyPALog(Map<String, String> paLogData, String key1, String key2) throws ParseException {
		boolean verify = true;
		ArrayList<String> verifyAddDateList = splitPipeSepratedString(paLogData.get(key1));
		ArrayList<String> verifyAddedDataList = splitPipeSepratedString(paLogData.get(key2));

		Map<String,String> palActivityMap = new HashMap<>();               

		String[] entry = null ;
		for(String pair : verifyAddedDataList)	{
			entry = pair.split("=");  
			palActivityMap.put(entry[0].trim(), entry[1].trim()); 
		}

		SeleniumUtil.waitForElementToBePresent(startDate, driver);
		//click on start date
		SeleniumUtil.element(startDate, driver).click();
		selectDate(verifyAddDateList.get(0),month,year,days);

		SeleniumUtil.waitForElementToBePresent(endDate, driver);
		
		if(verifyAddDateList.size()>1){
			SeleniumUtil.element(endDate, driver).click();
			selectDate(verifyAddDateList.get(1),month,year,days);
		}
		
		SeleniumUtil.sleep(3);
		WebElement activityFeedDetailTableEl = SeleniumUtil.element(activityFeedDetailTable, driver);
		List<WebElement> rowsList =  activityFeedDetailTableEl.findElements(activityfeedDetailTableRows);
		
		
		if(rowsList.size()!=verifyAddedDataList.size())
		{
			return verify=false;
		}
		for(WebElement row : rowsList){
			List<WebElement> coloumnList = row.findElements(By.xpath("td"));
			String actualResult = palActivityMap.get(coloumnList.get(0).getText());
			String expectedResult = coloumnList.get(1).getText();
			if(!actualResult.equals(expectedResult))
			{
				Reporter.log(" Actual and expected result is not matched\n"+"Actual result :"+actualResult+"\nExpected Result : "+expectedResult);
				return verify=false;

			}
		}
		return verify;

	}

	public void selectDate(String date, By month, By year, String days)
			throws ParseException {

		// Convert the date in date format
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		Date dateformate = formatter.parse(date);

		// Select year from years drop down
		driver.findElement(year).sendKeys(String.valueOf(dateformate.getYear() + 1900));

		// Select month from months drop down

		driver.findElement(month).sendKeys(String.valueOf(new DateFormatSymbols().getShortMonths()[dateformate.getMonth()]));
		driver.findElement(month).click();
		driver.findElement(month).click();

		SeleniumUtil.element(calendarBar, driver).click();
		// select date from calendar
		driver.findElement(SeleniumUtil.dynamicXpath(days,String.valueOf(dateformate.getDate()))).click();
		SeleniumUtil.sleep(3);
	}
	
	public void editPALogEntry(Map<String, String> paLogData){
				
		driver.switchTo().frame("iframeTracker");
		
		SeleniumUtil.element(editEntryBtn, driver).click();
		
		ArrayList<String> editDateList = splitPipeSepratedString(paLogData.get("editDate"));
		ArrayList<String> editDurationList = splitPipeSepratedString(paLogData.get("editDuration"));
		ArrayList<String> editActivityTypeList = splitPipeSepratedString(paLogData.get("editActivityType"));
		
		//String endDate = verifyAddDateList.get(1);
		By editElement = SeleniumUtil.dynamicXpath(editEntryInput, editDateList.get(0), editActivityTypeList.get(0));
		
		SeleniumUtil.element(editElement,driver).click();
		SeleniumUtil.element(editElement,driver).clear();
		SeleniumUtil.element(editElement,driver).sendKeys(editDurationList.get(0));
	/*	SeleniumUtil.element(SeleniumUtil.dynamicXpath(editEntryInput, endDate, "Aerobic general", "50"),driver).clear();
		SeleniumUtil.element(SeleniumUtil.dynamicXpath(editEntryInput, endDate, "Aerobic general", "50"),driver).sendKeys("40");*/
		SeleniumUtil.element(activityTrackerHeader, driver).click();		
		
		driver.switchTo().defaultContent();		
	}
}
