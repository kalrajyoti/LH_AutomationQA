package com.lh.pages.authenticated.jyoti;

import static com.lh.helper.DriverFactory.driver;

import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.lh.utils.ExcelFileUtility;
import com.lh.utils.SeleniumUtil;
import com.lh.utils.jyoti.SeleniumUtilModified;

public class MainPageModified {

	private static Logger logger 					= LogManager.getLogger(com.lh.pages.authenticated.MainPage.class);
	protected static ExcelFileUtility testDataObj;
	private By preferencesLnk 						= By.id("ctl00_PreferencesLink");
	private By helplLnk								= By.id("ctl00_HelpLink");
	private By logoutLnk 							= By.id("LogoutLink");
	private By indexPageBnr 						= By.xpath("//span[@id='ctl00_TopNavigationTextLabel']/.."); ////*[@id='ctl00_headerLeftContentDiv']/div[2]
	private By indexPageHeaderTxtBnr 				= By.xpath("//*[@id='ctl00_headerLeftContentDiv']/div[2]");
	private By theProgramsMenu 						= By.xpath("//*[@id='navbar']//ul/li[2]/span");
	private By yourToolsMenu 						= By.xpath("//div[@id='navbar']/div/div/div[3]/ul[2]/li[3]/span");
	private By resourcesMenu						= By.xpath("//div[@id='navbar']//ul[@class='navbar-global-nav']/li[4]/span");
	private By programOverviewSubMenu				= By.xpath("//ul[@class='navbar-global-subnav']/li/a[@href='/V2/index.aspx ']");
	private String theProgramSubMenu				= "//ul[@class='navbar-global-nav']/li/span[contains(text(),'The Program')]/../ul//li/a[contains(text(),'%s')]";
	private By yourWellnessBankSubMenu				= By.xpath("//div[@id='navbar']//a[@href='/V2/MemberServices/WellnessIncentives/Index.aspx ']");	
	private By onTrackSubMenu						= By.xpath("//*[@id='nav-wrapper']/ul/li[2]/ul/li[10]/a[@href='/V2/MemberServices/OnTrack/index.aspx']");
	private By overviewTab 							= By.xpath(".//*[@id='ontrack-subnav']/li[1]/a");
	private By healthAssessmentSubMenu 				= By.xpath("//div[@id='navbar']//a[@href='/V2/MemberServices/MSEvents/HealthAssessmentWelcome.aspx ']");
	private By healthCoachingSubMenu				= By.xpath("//div[@id='navbar']//a[@href='/V2/MemberServices/Experts/GetHealthCoaching.aspx ']");
	private By biometricScreeningSubMenu			= By.xpath("//*[@id='navbar']//a[@href='/V2/MemberServices/PSQEvents/BiometricScreeningWelcome.aspx ']");
	private By healthManagerSubMenu					= By.xpath("//div[@id='navbar']//a[@href='/V2/MemberServices/health/ScreeningGraphPage.aspx?GroupID=99 ']");
	private By fitBitSubMenu 						= By.xpath("//nav[@id='nav-wrapper']/ul/li[2]/ul/a[@href='/V2/MemberServices/FitBit/FitBitSetup.aspx']/li");
	private By physicalActivityLogSubMenu 			= By.xpath("//*[@id='nav-wrapper']/ul/li[2]/ul/li[6]/a[@href='/V2/memberservices/ActivityLog/index.aspx']");
	private By lifestyleManagerSubMenu 				= By.xpath("//div[@id='navbar']//li/a[@href='/V2/MemberServices/LifestyleManager.aspx ']");
	private By onTargetSubMenu						= By.xpath("//div[@id='navbar']//li/a[@href='/Ontarget/YourTarget/Plan ']");
	private By byoaSubMenu							= By.xpath("//div[@id='navbar']//a[@href='/V2/MemberServices/BYOA/Store.aspx ']");
	private By healthArticlesSubMenu				= By.xpath("//div[@id='navbar']//ul//ul//a[@href='/V2/MemberServices/Content/ArticleLibrary.aspx ']");
	private By wellnessResourcessSubMenu 			= By.xpath("//div[@id='navbar']//ul/li[4]/ul/li/a[@href='/V2/MemberServices/ThirdPartyLinks.aspx ']");
	private By communitySubMenu						= By.xpath("//nav[@id='nav-wrapper']/ul/li[1]/ul/a[@href='/V2/memberservices/community/CommunityHome.aspx']/li");
	private By wellnessChampionsSubMenu 			= By.xpath("//nav[@id='nav-wrapper']/ul/li[1]/ul/a[@href='/V2/memberservices/WellnessChampions/Overview.aspx']/li");
	private By challangeSubMenu 					= By.xpath("//nav[@id='nav-wrapper']/ul/li[1]/ul/a[@href='/V2/MemberServices/Challenges.aspx']/li");
	protected By breadcrumbFirst					= By.id("ctl00_TopNavigationBreadcrumb_breadcrumbItemRepeater_ctl00_breadcrumbItemUrl");
	protected By breadcrumbSecond					= By.id("ctl00_TopNavigationBreadcrumb_breadcrumbItemRepeater_ctl02_breadcrumbItemUrl");
	protected By actionCenterframe					= By.xpath("//*[@id='ctl00_ActionCenter_actionCenterContainer']/div[4]");		
	protected By bannerPhoto1Img					= By.xpath("//*[@id='slides']/div/div/div[1]/div[1]/img");
	protected By bannerPhoto2Img					= By.xpath("//*[@id='slides']/div/div/div[2]/div[1]/img");
	private By wellnessWebinarsSubMenu				= By.xpath("//ul[@class='main-menu']/li/a[text()='Resources']/../ul/a[@href='/V2/MemberServices/SectionFront.aspx?Section=Webinar']");
	protected By contactLiveHelathierLink			= By.id("ctl00_ContactLink");
	private By termsOfUseLink						= By.id("ctl00_TermsOfUseLink");
	private By privacyPolicyLink					= By.id("ctl00_PrivacyPolicyLink");
	private By img_leftLogo							= By.id("ctl00_ImgLogo");

	private By img_secondaryLogo					= By.id("ctl00_imgSecondaryLogo");

	protected String toolsPath						= "//ul[@class='navbar-global-nav']/li/span[contains(text(),'Tools')]/../ul//li/a[contains(text(),'%s')]";

	protected By imageText  						= By.xpath("//span[@id='ctl00_TopNavigationTextLabel']/..");								

	private By delorianDropdown = By.id("ctl00_DeloreanBar1_ddlCampaign");
	private By delorianCalendarImg = By.xpath("//img[@class='ui-datepicker-trigger']");
	private By delorianCalendar = By.id("ui-datepicker-div");
	private By delorianDate = By.xpath("//input[@id='datepickerDelorean']");
	private By delorianPrevMonthButton = By.xpath("//*[@id='ui-datepicker-div']/div/a[1]");
	protected String actionPath = "//*[@id='ctl00_ActionCenter_ActionCenterBlockRepeater_ctl0%s_blockTitleLable']";
	private By nextSurveyPageButton = By.id("sg_NextButton");
	private By backSurveyPageButton = By.id("sg_BackButton");
	private By completeSurveyButton = By.id("sg_SubmitButton");
	private By surveyCommonRadioTable = By.xpath("//div[@class='sg-question-options']/table/tbody");
	private By surveyCommonRadioList = By.xpath("//div[@class='sg-question-options']/ul/li");
	private By survey6HoursOfSleeptxt = By.id("sgE-2241273-7-16-element");
	private By errorHomePageLink = By.xpath(".//*[@id='MasterForm']/div[3]/div[5]/h4/a[2]");
	private By delorianMonthDropdown = By.className("ui-datepicker-month");
	private By delorianYearDropdown = By.className("ui-datepicker-year");
	private By delorianSelectedYear = By.xpath("//*[@class='ui-datepicker-year' ]/option[@selected='selected']");
	boolean flag;
	String url;								
	
	
	/**
	 * Constructor
	 * 
	 *
	 */
	public MainPageModified(){
		logger.info("Initialized the Main Page Object");
	}
	
	
	public void clickOnTarget() {
		SeleniumUtilModified.switchToDefaultContent(driver);
		try{
			SeleniumUtilModified.scrollToElement(yourToolsMenu, driver);
			SeleniumUtilModified.hover(yourToolsMenu, driver);
			SeleniumUtilModified.element(onTargetSubMenu, driver).click();
		}
		catch(Exception e){
			e.printStackTrace();		
		}
		Reporter.log("Clicked on the onTarget sub menu. ");
	}
	
	public void clickYourWellnessBank(){
		try{
			
			SeleniumUtilModified.hover(theProgramsMenu, driver);
			SeleniumUtilModified.element(yourWellnessBankSubMenu, driver).click();
			Reporter.log("Clicked on the Your WellnessBank sub menu. "); 
}
		catch(Exception e){		
				e.printStackTrace();
				
		}
			
			
			
		}
	public boolean verifyURL(String pageUrl){
		try{
		url=driver.getCurrentUrl();
	    if(url.contains(pageUrl))
	    flag = true;
		}catch(Exception e){
				e.printStackTrace();
				flag = false;
		}
	return flag;
	}	
	public void clickSurveyOption(By element){
		List<WebElement> surveyOptions = driver.findElements(element);
		System.out.println("the value in serveyoptions is:" +surveyOptions);
		
		String surveyop = surveyOptions.get(0).getAttribute("tagName").toLowerCase();
		Random randomRadio = new Random();
		int count = 0;
		if(surveyop.contains("ul")){
			
			List<WebElement> radioBtnList = driver.findElements(surveyCommonRadioList);
			System.out.println("the radiobtn list is:" +radioBtnList);
			count = radioBtnList.size()-1;
			radioBtnList.get(randomRadio.nextInt(count)).findElement(By.tagName("label")).click();
			radioBtnList.get(randomRadio.nextInt(count)).findElement(By.tagName("label")).click();
			Reporter.log("Clicked on a list option...");
		} else if(surveyop.contains("table")){
			WebElement radioTable = driver.findElement(surveyCommonRadioTable);  
			
			  List<WebElement> rows=radioTable.findElements(By.tagName("tr"));  
			  
				  for(count=0;count<rows.size();count++){
					  List<WebElement> columns=rows.get(count).findElements(By.tagName("td"));  
				 columns.get(randomRadio.nextInt(columns.size()-1)).findElement(By.tagName("label")).click();
				 Reporter.log("Clicked on a table radio option...");
					}
		} else if(surveyop.contains("DIV")){
			SeleniumUtil.sendKeysToWebElement(By.xpath("//*[@id='sgE-2241273-7-16-element']"), "8", driver);
			Reporter.log("Entered no of hours slept...");
		} else {
			Reporter.log("Failed to enter no of hours slept...");
		}
	}
}
