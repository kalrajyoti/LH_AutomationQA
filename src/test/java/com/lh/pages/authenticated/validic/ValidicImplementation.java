package com.lh.pages.authenticated.validic;

import static com.lh.helper.DriverFactory.driver;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Reporter;

import com.lh.pages.authenticated.MainPage;
import com.lh.testConfig.TestProperty;
import com.lh.utils.SeleniumUtil;

public class ValidicImplementation extends MainPage {

	private static Logger logger = LogManager
			.getLogger(ValidicImplementation.class);
	private By availableApps = By
			.xpath("//div[@id='menuSection']/a[text()='Available Apps']");
	private String fibitConnectButton = "//h1[text()='%s']/ancestor::div[@class='content']/following-sibling::div//button[text()='Connect']";
	private By byoaTC = By.xpath("//div[@id='byoaTermsAndConditions']");
	private By checkbox = By.xpath("//input[@id='AgreeCheckbox']");
	private By continueButton = By
			.xpath("//div[@id='AgreementContinueLinkButton']");
	private By fitBitUserName = By.xpath("(//input[@name='email'])[2]");
	private By fitBitPassword = By.xpath("(//input[@name='password'])[2]");
	private By loginButton = By.xpath("//form[@id='loginForm']/div[1]/button");
	private By allowbutton = By.xpath("//button[@id='allow-button']");
	private By yourAppsLink = By
			.xpath("//div[@id='menuSection']//a[@class='menu yourapps']");
	private By appsDisplayed = By
			.xpath("//div[@class='byoa-bottom-container byoa-your-apps active']");
	private String fitbitDisconnectButton = "//div[@class='byoa-bottom-container byoa-your-apps active']//h1[text()='%s']/ancestor::div[@class='content']/following-sibling::div//a[text()='Disconnect']";
	private By confirmContinueButton = By
			.xpath("//div[@id='byoaConfirmContinue']");
	private By fitbitloginLink = By.xpath("//a[@href='/login']");
	private By fitbitLogLink = By
			.xpath("//nav[@class='wrapper-nav clearfix']//a[@href='//www.fitbit.com/foods/log']");
	private By activitieslink = By.xpath("//a[@href='/activities']/span");
	private By exerciseButton = By.xpath("//button[@title='Run']");
	private By startTimeHours = By
			.xpath("//label[@for='start-time-hours-c38']/following-sibling::input[@placeholder='hh']");
	private By startTimeMinutes = By
			.xpath("//label[@for='start-time-hours-c38']/following-sibling::input[@placeholder='mm']");
	private By durationHours = By
			.xpath("//label[@for='duration-hours-c38']/following-sibling::input[@placeholder='hh']");
	private By durationMinutes = By
			.xpath("//label[@for='duration-hours-c38']/following-sibling::input[@placeholder='mm']");
	private By durationSeconds = By
			.xpath("//label[@for='duration-hours-c38']/following-sibling::input[@placeholder='ss']");
	private By submitButton	=By.xpath("//span[@class='submit-button']/button[@type='submit']");	

	private By loginHeadline = By.xpath("//div[@class='panel panel-login']/h1");
	private By distanceExercise = By.xpath("//span[@class='entry']/input[@name='distance']");
	private By tableLogs = By.xpath("//table[@class='logs']");
	private By loginFitbitHomePage = By.xpath("//a[@href='/login']");
	private By dropDownList =By.xpath("//select[@ng-model='selectedDateName']");
	private By distanceEntryInLM = By.xpath("(//div[@id='ctl00_ContentPlaceHolder1_LifestyleManagerControl_exerciseSource2']//following-sibling::div[@data-column='exercise-distance'])[1]");
	private By logsLink							= By.xpath("//li[@id='lm-subnav-logs']/a");	
	private By exerciseLink						= By.xpath("//li[@id='lm-subnav-logs']//a[text()='Exercise']");
	private By teaserzone						= By.xpath("//p[@class='articlesubtitle']");	
	private By LifestyleManagerText				= By.xpath("//div[@class='articletitle_subpage']/p");
	
	private String distanceInput; 
	boolean flag;
	
	public ValidicImplementation() {
		super();
		if (!(driver.getTitle().equalsIgnoreCase(TestProperty.CLIENT_PORTAL_TITLE))) {
			logger.error("This is not the Login page");
			throw new IllegalStateException(
					"This is not the Login page");
		}
		Reporter.log("Initialized the Login page object");
	}	

	public boolean connectFitBit(String fitbitText, String userName, String passWord){

		try{

			String appsText;
			if (SeleniumUtil.isElementDisplayed(yourAppsLink, driver)){
				SeleniumUtil.click(yourAppsLink, driver);
				appsText = SeleniumUtil.getTextfromWebElement(appsDisplayed, driver);
				if(appsText.contains(fitbitText)){

					SeleniumUtil.click(SeleniumUtil.dynamicXpath(fitbitDisconnectButton	, fitbitText),driver);
					SeleniumUtil.click(confirmContinueButton, driver);
				}
				else {
					SeleniumUtil.click(availableApps, driver);
				}
			}
			SeleniumUtil.click(availableApps, driver);
			connectFitBitApp(fitbitText, userName, passWord);
			SeleniumUtil.click(yourAppsLink, driver);

			appsText = SeleniumUtil.getTextfromWebElement(appsDisplayed, driver);

			if (appsText.contains(fitbitText))

				flag = true;
			
		}
		catch(Exception e){
			e.printStackTrace();
			flag =false;
			
		}
		return flag;
	}

	
	public void connectFitBitApp(String fitbitText, String userName, String passWord){
		
		try{
		SeleniumUtil.click(SeleniumUtil.dynamicXpath(fibitConnectButton, fitbitText), driver);
		if (SeleniumUtil.isElementDisplayed(byoaTC, driver)){	
			SeleniumUtil.click(checkbox, driver);
			SeleniumUtil.click(continueButton, driver);

			SeleniumUtil.sendKeysToWebElement(fitBitUserName, userName, driver);
			SeleniumUtil.sendKeysToWebElement(fitBitPassword, passWord, driver);
			SeleniumUtil.click(loginButton, driver);
			SeleniumUtil.click(allowbutton, driver);
		}
		else {
			SeleniumUtil.sendKeysToWebElement(fitBitUserName, userName, driver);
			SeleniumUtil.sendKeysToWebElement(fitBitPassword, passWord, driver);
			SeleniumUtil.click(loginButton, driver);
			SeleniumUtil.click(allowbutton, driver);
		}
		}catch(Exception e){
		  e.printStackTrace();
		}
	}

	public String randomDistanceGenerator(){
		
		Random rand = new Random(); 
		int distance = rand.nextInt(100);
		return Integer.toString(distance);
		 	
	}
	public boolean createValidicEntry(String userName, String passWord,
			String starttimehours, String starttimeMinutes,
			String index, String durationhours, String durationminutes, String durationseconds) {

		try{
			
	if(SeleniumUtil.isElementDisplayed(loginFitbitHomePage, driver))
		SeleniumUtil.click(loginFitbitHomePage, driver);
			
		
	if (SeleniumUtil.isElementDisplayed(loginHeadline, driver)){
		SeleniumUtil.click(fitbitloginLink, driver);
		SeleniumUtil.sendKeysToWebElement(fitBitUserName, userName, driver);
		SeleniumUtil.sendKeysToWebElement(fitBitPassword, passWord, driver);
		SeleniumUtil.click(loginButton, driver);
	}
	else{		
		
		SeleniumUtil.sendKeysToWebElement(fitBitUserName, userName, driver);
		SeleniumUtil.sendKeysToWebElement(fitBitPassword, passWord, driver);
		SeleniumUtil.click(loginButton, driver);
		SeleniumUtil.click(allowbutton, driver);
	}
	
			SeleniumUtil.click(fitbitLogLink, driver);
			SeleniumUtil.sleep(2);
			SeleniumUtil.click(activitieslink, driver);
			SeleniumUtil.sleep(2);
			SeleniumUtil.click(exerciseButton, driver);
			SeleniumUtil.click(startTimeHours, driver);
            SeleniumUtil.sendkeys_JS(startTimeHours, starttimehours,driver);
			SeleniumUtil.sendkeys_JS(startTimeMinutes, starttimeMinutes,driver);
			SeleniumUtil.click(durationHours, driver);
			SeleniumUtil.sendkeys_JS(durationHours, durationhours, driver);
			SeleniumUtil.sendkeys_JS(durationMinutes, durationminutes,driver);
			SeleniumUtil.sendkeys_JS(durationSeconds, durationseconds, driver);
			distanceInput=randomDistanceGenerator().trim();
			SeleniumUtil.sendkeys_JS(distanceExercise, distanceInput, driver);
			SeleniumUtil.click(submitButton, driver);
			SeleniumUtil.waitForElementToBeVisible(tableLogs, driver);
			String table = SeleniumUtil.getTextfromWebElement(tableLogs, driver);
			if(table.contains(distanceInput))
			     flag = true;	
					
		}catch(Exception e){
			flag = false;
			e.printStackTrace();	
		}
	
		return flag;
	}

public boolean validateValidicEntryInLifestyleManager(String range){
		
	try{
		SeleniumUtil.sleep(5);
		SeleniumUtil.waitForElementToBePresent(dropDownList, driver);
		SeleniumUtil.selectByVisibleText(dropDownList, range, driver);
		SeleniumUtil.waitForElementToBeVisible(dropDownList,driver);
		while(!SeleniumUtil.isElementDisplayed(distanceEntryInLM,driver)){
		
			driver.navigate().refresh();
			SeleniumUtil.sleep(2);
			SeleniumUtil.hover(logsLink, driver);
			SeleniumUtil.sleep(2);
			SeleniumUtil.element(exerciseLink, driver).click();
			SeleniumUtil.sleep(2);
			SeleniumUtil.selectByVisibleText(dropDownList, range, driver);
		}
		String textToVerify=SeleniumUtil.getTextfromWebElement(distanceEntryInLM, driver).trim();
		String[] textSplit=textToVerify.split("\\.");
		String textaftersplit = textSplit[0].trim();
		if(textaftersplit.equals(distanceInput))
			
			flag=true;
			
		
		}catch(Exception e){
			
			flag = false;
			e.printStackTrace();
					
		
	}	
	return flag;
}

public boolean verifyLifestyleManagerTitleAndTeaserZone(String LM_Txt, String teaserZone_Txt){

	try{
	String LM_Title = SeleniumUtil.getTextfromWebElement(LifestyleManagerText, driver).trim();
	String teaserZone = SeleniumUtil.getTextfromWebElement(teaserzone, driver).trim();
	if(LM_Txt.contains(LM_Title) && teaserZone_Txt.contains(teaserZone) )
		flag=true;
}catch(Exception e){
	flag=false;
	e.printStackTrace();
	
}
	return flag;
}
}


