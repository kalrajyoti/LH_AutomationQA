package com.lh.utils;

import com.lh.dao.Constants;
import com.lh.helper.ErrorUtil;
import com.lh.testConfig.TestProperty;
import org.apache.logging.log4j.Logger;import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.lh.helper.DriverFactory.driver;
import static com.lh.testConfig.TestProperty.WAITING_TIME;

/**
 * @author Sanjeev.Kumar,Akash Vansil
 * Update by : Akash Vansil
 * Version : 3.0
 * Date Modified: 6/01/2016
 */
public class SeleniumUtil {

	private static Logger logger = LogManager.getLogger(SeleniumUtil.class);

	public static String s;

	private static WebElement datePicker; 
	private static List<String> monthList = Arrays.asList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"); 
	private static String calMonth = null; 
	private static  String calYear = null; 
	private static boolean dateNotFound; 
	private static Hashtable<String,String> table = null;;


	public static void matchDate(String expDate,int expMonth,int expYear,WebDriver driver)
	{
		dateNotFound = true; 
		while(dateNotFound) {
			calMonth = driver.findElement(By.className("ui-datepicker-month")).getText();
			calYear = driver.findElement(By.className("ui-datepicker-year")).getText(); 
			if(monthList.indexOf(calMonth)+1 == expMonth && (expYear == Integer.parseInt(calYear)))
			{
				selectDate(expDate,driver); 
				dateNotFound = false;
			}
			else if(monthList.indexOf(calMonth)+1 < expMonth && (expYear == Integer.parseInt(calYear)) || expYear > Integer.parseInt(calYear))
			{
				driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[2]/span")).click(); 	
			}	
			else if(monthList.indexOf(calMonth)+1 > expMonth && (expYear == Integer.parseInt(calYear)) || expYear < Integer.parseInt(calYear))
			{
				driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[1]/span")).click();
			}
		}

	}

	public static void checkPageIsReady(WebDriver driver)
	{
		JavascriptExecutor js = (JavascriptExecutor)driver; 
		if (js.executeScript("return document.readyState").toString().equals("complete"))
		{ 
			System.out.println("Page Is loaded."); 
			return;
		}


		for (int i=0; i<25; i++)
		{ 
			try {
				Thread.sleep(1000); 
			}
			catch (InterruptedException e) 
			{System.out.println("caught");
			} 
			if (js.executeScript("return document.readyState").toString().equals("complete"))
			{ break; 
			}
		}
	}

	public static void selectDate(String date,WebDriver driver) 
	{ 
		datePicker = driver.findElement(By.id("ui-datepicker-div")); 
		List<WebElement> columns=datePicker.findElements(By.tagName("td"));  
		for (WebElement cell: columns){ 
			//Select the date from date picker when condition match.
			if (cell.getText().equals(date))
			{
				cell.findElement(By.linkText(date)).click();
				break; 
			}
		}     
	}

	public static boolean isElementEnabled(By elementLocator,WebDriver driver){
		try{

			logger.debug("�lement :"+ elementLocator);
			driver.findElement(elementLocator).isEnabled();
		}
		catch (org.openqa.selenium.NoSuchElementException Ex) {
			logger.debug("Unable to locate Element: " + elementLocator);
			Reporter.log("Unable to locate Element: "+elementLocator);
			ErrorUtil.addVerificationFailure(new Throwable("Unable to locate Element: " + elementLocator));
			return false;
		}
		return true;
	}	

	public static Object[][]  getData(String testName, Xls_Reader xls){
		int rows = xls.getRowCount(Constants.DATA_SHEET);

		// row number for testCase
		int testCaseRowNum=1;
		for(testCaseRowNum=1;testCaseRowNum<=rows;testCaseRowNum++){
			String testNameXls = xls.getCellData(Constants.DATA_SHEET, 0, testCaseRowNum);
			if(testNameXls.equalsIgnoreCase(testName))
				break;
		}
		int dataStartRowNum=testCaseRowNum+2;
		int colStartRowNum=testCaseRowNum+1;

		// rows of data
		int testRows=1;
		while(!xls.getCellData(Constants.DATA_SHEET, 0, dataStartRowNum+testRows).equals("")){
			testRows++;
		}

		// cols of data
		int testCols=0;
		while(!xls.getCellData(Constants.DATA_SHEET,testCols,colStartRowNum).equals("")){
			testCols++;
		}
		Object[][] data = new Object[testRows][1];
		int r=0;
		for(int rNum=dataStartRowNum;rNum<(dataStartRowNum+testRows);rNum++){
			table = new Hashtable<String,String>();
			for(int cNum=0;cNum<testCols;cNum++){
				//System.out.println(xls.getCellData(Constants.DATA_SHEET, cNum, rNum));
				//data[r][cNum]=xls.getCellData(Constants.DATA_SHEET, cNum, rNum);
				table.put(xls.getCellData(Constants.DATA_SHEET, cNum, colStartRowNum), xls.getCellData(Constants.DATA_SHEET, cNum, rNum));
			}
			data[r][0]=table;
			r++;
		}
		return data;
	}

	public static By dynamicXpath(String xpathValue, String...substitutionValue) {
		return By.xpath(String.format(xpathValue,substitutionValue));
	}

	/** This method is used for element is present on page or not
	 * @param fieldLocator
	 * @param driver
	 * @return true if element is present, other wise it return Fail
	 */
	public static boolean isElementPresent(By fieldLocator, WebDriver driver) {
		try {
			logger.debug("Element: " + fieldLocator);
			driver.findElement(fieldLocator);

		} catch (org.openqa.selenium.NoSuchElementException Ex) {
			logger.debug("Unable to locate Element: " + fieldLocator);
			return false;
		}
		return true;
	}

	/**
	 * This method is used for wait for element for ELEMENT_POLL_TIME defined in
	 * TestProperty file
	 * 
	 * @param elementLocator
	 * @param driver
	 */
	public static void waitForElementToBePresent(By elementLocator, WebDriver driver) {
		WebElement myDynamicElement = (new WebDriverWait(driver, TestProperty.ELEMENT_POLL_TIME))
				.until(ExpectedConditions.presenceOfElementLocated(elementLocator));
		if (myDynamicElement.isDisplayed()) {
			logger.info("Element Found: " + elementLocator.toString());
		} else {
			logger.error("\nElement NOT found: " + elementLocator.toString());
		}
	}

	public static void selectKeys(By locator,String text,WebDriver driver){
		try{
			Select sel = new Select(driver.findElement(locator));
			sel.selectByValue(text);
			logger.debug("The text to send is "+text);
		}
		catch(Exception e){
			logger.debug("Failed to type in the textbox");
			e.getStackTrace();
		}
	}

	public static void sendKeysToWebElement(By locator,String text,WebDriver driver){
		try{

			//driver.findElement(locator).clear();
			driver.findElement(locator).sendKeys(text);
			logger.debug("The text to send is test "+text);
		}catch(Exception e){
			logger.debug("Failed to type in the textbox");
			e.getStackTrace();
		}
	}

	public static void sendkeys_JS(By locator, String text, WebDriver driver){
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].value='"+text+"'", driver.findElement(locator));
			logger.debug("The text to send is "+text);
		}catch(Exception e){
			logger.debug("Unable to send text");
			e.getStackTrace();
		}
	}

	public static boolean isElementDisplayed(By elementLocator, WebDriver driver) {
		try {
			logger.debug("Element: " + elementLocator);
			driver.findElement(elementLocator).isDisplayed();

		} catch (org.openqa.selenium.NoSuchElementException Ex) {
			logger.debug("Unable to locate Element: " + elementLocator);
			Reporter.log("Unable to locate Element: "+elementLocator);
//			ErrorUtil.addVerificationFailure(new Throwable("Unable to locate Element: " + elementLocator));
			return false;
		}
		return true;
	}

	public static boolean isElementDisplayedString(String elementLocator, WebDriver driver) {
		try {
			logger.debug("Element: " + elementLocator);
			driver.findElement(By.xpath(elementLocator)).isDisplayed();

		} catch (org.openqa.selenium.NoSuchElementException Ex) {
			logger.debug("Unable to locate Element: " + elementLocator);
			Reporter.log("Unable to locate Element: "+elementLocator);
//			ErrorUtil.addVerificationFailure(new Throwable("Unable to locate Element: " + elementLocator));
			return false;
		}
		return true;
	}
	
	public static WebElement element(By elementToken, WebDriver driver){

		return driver.findElement(elementToken);
	}

	public static void hover(By elementToken, WebDriver driver){
		WebElement element = driver.findElement(elementToken);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Actions hoverClick = new Actions(driver);
		hoverClick.moveToElement(driver.findElement(elementToken)).click().build().perform();
	}

	public static void hover(By sourceElementToken, By targetElementToken,	WebDriver driver) {
		Actions hoverClick = new Actions(driver);
		hoverClick.moveToElement(driver.findElement(sourceElementToken))
		.moveToElement(driver.findElement(targetElementToken)).click().build().perform();
	}

	public static void hoverAndClick(By elementIdentifier){
		new WebDriverWait(driver,WAITING_TIME).until(ExpectedConditions.
				presenceOfElementLocated(elementIdentifier));
		Actions builder = new Actions(driver);
		builder.moveToElement(driver.findElement(elementIdentifier)).perform();
		driver.findElement(elementIdentifier).click();
	}

	public static String getTextfromWebElement(By xpathLocator, WebDriver driver){
		String text =null;
		try{
			text=element(xpathLocator, driver).getText();
		}catch(Exception e){
			logger.debug("Unable to get text from Webelement");
			e.getStackTrace();
		}
		return text;
	}


	/**
	 * This method is used for wait for element for ELEMENT_POLL_TIME defined in
	 * TestProperty file
	 *
	 * @param elementLocator
	 * @param driver
	 */
	public static void waitForElementToBeVisible(By elementLocator, WebDriver driver) {
		try{
			WebElement myDynamicElement = (new WebDriverWait(driver, TestProperty.ELEMENT_WAIT_TIME))
					.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
		}
		catch(Exception e){
			logger.info("Exception occured.. "+ e);
		}
	}

	public static void waitForPageLoad(WebDriver driver) {
		ExpectedCondition<Boolean> pageLoadCondition = new
				ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(pageLoadCondition);
	}

	/**
	 * This method is used for wait for element for ELEMENT_POLL_TIME defined in
	 * TestProperty file
	 * 
	 * @param elementLocator
	 * @param driver
	 */
	public static void waitForElementTextToBeVisible(By elementLocator,String text, WebDriver driver) {
		Boolean myDynamicElement = (new WebDriverWait(driver, TestProperty.ELEMENT_POLL_TIME))
				.until(ExpectedConditions.textToBePresentInElementLocated(elementLocator, text));
		if (myDynamicElement==true) {
			logger.info("Element Found: " + elementLocator.toString());
		} else {
			logger.error("\nElement NOT found: " + elementLocator.toString());
		}
	}


	public static boolean waitForElementToDisappear(By elementLocator, WebDriver driver){
		boolean isElementVisible = (new WebDriverWait(driver, TestProperty.ELEMENT_POLL_TIME))
				.until(ExpectedConditions.invisibilityOfElementLocated(elementLocator));

		if(isElementVisible){
			System.out.println("Element disappeared...");
		}
		else{
			System.out.println("Element is still visible..");
		}
		return isElementVisible;
	}

	public static ArrayList<Integer> generateUniqueRandomNumber(int endPoint){
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i=1; i<=endPoint; i++) {
			list.add(new Integer(i));
		}
		Collections.shuffle(list);

		return list;

	}

	public static Boolean checkTextPresentInPage(String text,WebDriver driver){
		Boolean textState = false;
		if(driver.findElement(By.tagName("body")).getText().contains(text)){
			textState = true;
		}
		return textState;
	}

	public static void selectByVisibleText(By xpath,String text,WebDriver driver){
		Select dropdown = new Select(driver.findElement(xpath));
		dropdown.selectByVisibleText(text);
	}

	
	public static void selectByValue(By xpath,String index,WebDriver driver){
		Select dropdown = new Select(driver.findElement(xpath));
		dropdown.selectByValue(index);
	}
	
	public static void scrollToElement(By elementToken, WebDriver driver){
		WebElement element = driver.findElement(elementToken);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void sleep(int seconds){
		try{
			Thread.sleep(seconds*1000);
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
	}

	public static void click(By xpath,WebDriver driver){
		try{
			driver.findElement(xpath).click();
			logger.debug("Sucessfully clicked on element");
		}catch(Exception e){	
			logger.debug("Failed to click on element");
			Reporter.log("Failed to click on element");

			e.getStackTrace();	
		}	
	}

	public static void switchToFrame(By elementToken, WebDriver driver){
//		sleep(4);
		driver.switchTo().frame(element(elementToken, driver));
		sleep(1);
	}

	public static void switchToDefaultContent(WebDriver driver){
		driver.switchTo().defaultContent();
	}

	public static void selectRandomDropDown(By Dropdown,WebDriver driver) throws InterruptedException
	{
		WebElement drpDwnList = driver.findElement(Dropdown);
		Select objSel = new Select(drpDwnList);
		List <WebElement> weblist = objSel.getOptions();
		int iCnt = weblist.size();
		Random num = new Random();
		int iSelect = num.nextInt(iCnt);
		objSel.selectByIndex(iSelect);
		System.out.println(drpDwnList.getAttribute("value"));	  
	}

	public static void selectRandomCheckBox(By chkbox,WebDriver driver) throws InterruptedException
	{
		List<WebElement> oCheckBox  = driver.findElements(chkbox);
		Random num = new Random();
		int iCnt = num.nextInt(oCheckBox.size());
		oCheckBox.get(iCnt).click(); 
	}

	public static void clickRandomDropdown(By dropDown,String type,WebDriver driver){
		String dropDownText = "no";
		Random randomOption = new Random();
		if(type.contains("Select")){
			Select randomDrop = new Select(driver.findElement(dropDown));
			int option = randomOption.nextInt(randomDrop.getOptions().size()-1);
			randomDrop.selectByIndex(option);
			dropDownText = driver.findElement(dropDown).getText();
		} else if (type.contains("List")){
			List<WebElement> randomDrop = driver.findElements(dropDown);
			int option = randomOption.nextInt(randomDrop.size()-1);
			randomDrop.get(option).click();
			dropDownText = randomDrop.get(option).getText();
		} else {
			logger.info("Please check the dropdown parameters entered");
		}
		logger.info("Clicked on the "+dropDownText +" dropdown option.");
	}

	public static void selRadioOption(String name,int i,WebDriver driver)
	{
		try{
			List<WebElement> RadButtonList = driver.findElements(By.name(name));
			RadButtonList.get(i).click();
			Reporter.log("User select the radio option");
		}
		catch(Exception e)
		{
			Reporter.log("Failed to click on element");

		}
	}

	public static void selectRadioOption(By name,int i,WebDriver driver)
	{
		try{
			List<WebElement> RadButtonList = driver.findElements(name);
			RadButtonList.get(i).click();
			Reporter.log("User select the radio option");
		}
		catch(Exception e)
		{
			ErrorUtil.addVerificationFailure(new Throwable("Failed to click on element"));
			Reporter.log("Failed to click on element");
		}
	}

	public static void clickbyJS(String xpath,WebDriver driver){
		WebElement element = driver.findElement(By.xpath(xpath));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
	}

	public static void clickbyJSID(String ID, WebDriver driver){
		((JavascriptExecutor) driver).executeScript(String.format("document.getElementById('%s').click()",ID));
	}

	public static void selectDateJS(String jsElemetLocation, String date, WebDriver driver) {
		JavascriptExecutor js;
		js = (JavascriptExecutor) driver;
		SeleniumUtil.sleep(2);
		if (date != null && !date.equals("")) {
			js.executeScript(jsElemetLocation.replaceAll("%s",date));
		}	
	}

	public static void selectCurrentDate(WebDriver driver){
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		String date1=dateFormat.format(date);
		String[] parts = date1.split("/");
		String currdate=parts[1];
		selectDate(currdate, driver);
	}

	public static void selectGivenDate(String date,WebDriver driver) throws ParseException{
		SimpleDateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
		Date date1 = dateFormat.parse(date);
		String date2=dateFormat.format(date1);
		String[] parts = date2.split("/");
		String currdate=parts[1];
		selectDate(currdate, driver);
	}

	public static void waitForElementToLoad(int maxWaitTime, By elementIdentifier){
			new WebDriverWait(driver,maxWaitTime).until(ExpectedConditions.presenceOfElementLocated(elementIdentifier));
	}

		public static String getUSTime(){
		Date d1 = new Date();
		DateFormat df = new SimpleDateFormat("EEEE, MMMM dd, yyyy h");
		df.setTimeZone(TimeZone.getTimeZone("America/New_York"));
		return df.format(d1);
	}
		public static void waitForPageLoaded(WebDriver driver) {
	        ExpectedCondition<Boolean> expectation = new
	                ExpectedCondition<Boolean>() {
	                    public Boolean apply(WebDriver driver) {
	                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
	                    }
	                };
	        try {
	            Thread.sleep(1000);
	            WebDriverWait wait = new WebDriverWait(driver, 30);
	            wait.until(expectation);
	        } catch (Throwable error) {
	            Assert.fail("Timeout waiting for Page Load Request to complete.");
	        }
	    }
}

