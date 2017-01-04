package com.lh.pages.authenticated;

import com.lh.testConfig.TestProperty;
import com.lh.utils.ExcelFileUtility;
import com.lh.utils.SeleniumUtil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.lh.helper.DriverFactory.driver;

/**
 * <h2>This is the Base class, it contains all the common methods which can be
 * used by all pages</h2>
 * <p>
 * 
 * @author amita.arya
 * @version 1.0
 * @since 2014-11-17
 */

public class MainPage {
	/**
	 * Logger to log messages
	 */
	private static Logger logger 					= LogManager.getLogger(com.lh.pages.authenticated.MainPage.class);
	protected static ExcelFileUtility testDataObj;
	private By preferencesLnk 						= By.id("ctl00_PreferencesLink");
	private By helplLnk								= By.id("ctl00_HelpLink");
	private By logoutLnk 							= By.id("LogoutLink");
	private By indexPageBnr 						= By.xpath("//span[@id='ctl00_TopNavigationTextLabel']/.."); ////*[@id='ctl00_headerLeftContentDiv']/div[2]
	private By indexPageHeaderTxtBnr 				= By.xpath("//*[@id='ctl00_headerLeftContentDiv']/div[2]");
	private By theProgramsMenu 						= By.xpath("//*[contains(.,'The Program')][not(.//*[contains(., 'The Program')])]");
	private By yourToolsMenu 						= By.xpath("//div[@id='navbar']/div/div/div[3]/ul[2]/li[3]/span");
	private By resourcesMenu						= By.xpath("//div[@id='navbar']//ul[@class='navbar-global-nav']/li[4]/span");
	private By programOverviewSubMenu				= By.xpath("//ul[@class='navbar-global-subnav']/li/a[@href='/V2/index.aspx ']");
	private String theProgramSubMenu				= "//ul[@class='navbar-global-nav']/li/span[contains(text(),'The Program')]/../ul//li/a[contains(text(),'%s')]";
	private By yourWellnessBankSubMenu				= By.xpath("//div[@id='navbar']//a[@href='/V2/MemberServices/WellnessIncentives/Index.aspx ']");	
	private By onTrackSubMenu						= By.xpath("//*[@id='nav-wrapper']/ul/li[2]/ul/li[10]/a[@href='/V2/MemberServices/OnTrack/index.aspx']");
	private By overviewTab 							= By.xpath(".//*[@id='ontrack-subnav']/li[1]/a");
	private By healthAssessmentSubMenu 				= By.xpath("//*[@href='/V2/MemberServices/MSEvents/HealthAssessmentWelcome.aspx']");
	private By healthCoachingSubMenu				= By.xpath("//*[@href='/V2/MemberServices/Experts/GetHealthCoaching.aspx']");
	private By biometricScreeningSubMenu			= By.xpath("//*[@href='/V2/MemberServices/PSQEvents/BiometricScreeningWelcome.aspx']");
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
	public MainPage(){
		logger.info("Initialized the Main Page Object");
	}

	/**
	 * Checks if the first name updated in the preferences page is the same as
	 * the name appearing on the Home Page banner
	 * 
	 * @param firstName
	 *            The first name of the user
	 * @return boolean returns true if the first name matches with the first
	 *         name provided
	 */
	public boolean verifyUserNameBan(String firstName) {
		Reporter.log("Checking whether the user name is the same as set on the preferences page");
		String headerBanner = driver.findElement(indexPageBnr).getText();
		if (headerBanner.contains(firstName)) {

			Reporter.log("The name of the user- " + firstName + ", MATCHED the name set in the test");
			return true;

		}

		logger.error("The name of the user- " + firstName + ", DID NOT match the name set in the test");
		return false;
	}

	/**
	 * Verify Preferences link is displayed 
	 */
	public boolean verifyPreferencesLink(){
		try{
		if(SeleniumUtil.isElementDisplayed(preferencesLnk, driver)){
			flag = true;
			Reporter.log("Preferences link is displayed...");
		}
		}catch(Exception e){
			e.printStackTrace();
			 flag = false;
		}
		return flag;
	}
	/**
	 * Clicks on the Preferences link present on the top right corner
	 */
	public void clickPreferencesLink() {
		try{	
		driver.findElement(preferencesLnk).click();
	    }
	    catch(Exception e){		
			e.printStackTrace();
	}
	}

	/**Verify Logout link is displayed 
	 * @return true/false based on whether Logout link is present or not
	 */
	public boolean verifyLogoutLink(){
		boolean isPresent = false;

		if (SeleniumUtil.isElementDisplayed(logoutLnk, driver)){
			Reporter.log("Logout link is displayed...");
			isPresent = true;
		}
		return isPresent;
	}

	/**
	 * Clicks on the LogOut link present on the top right corner of the website
	 */
	public void clickLogoutLink() {
		SeleniumUtil.switchToDefaultContent(driver);
		Reporter.log("Clicking on the  the logoutLinkLocator indentified by- " + logoutLnk);
		driver.findElement(logoutLnk).click();

	}

	/**
	 * Fetches the header text of the banner.
	 * @return the header banner text.
	 */
	private String getHeaderBannerTxt(){

		return driver.findElement(indexPageHeaderTxtBnr).getText();

	}

	/**
	 * Clicks and Opens the Program Overview page
	 */
	public void clickProgramOverview() {

		try{
		SeleniumUtil.hover(theProgramsMenu, driver);

		SeleniumUtil.element(programOverviewSubMenu, driver).click();

		Reporter.log("Clicked on the Program Overview sub menu. ");
	}catch(Exception e){
		
		e.printStackTrace();
	}
	}

	public void clickYourWellnessBank(){
		try{
			SeleniumUtil.hover(theProgramsMenu, driver);
			SeleniumUtil.element(yourWellnessBankSubMenu, driver).click();
			Reporter.log("Clicked on the Your WellnessBank sub menu. "); 
}
		catch(Exception e){		
				e.printStackTrace();
				
		}
	
	}	
	
		/**
	 * Clicks and Opens the On Track page.
	 */
	public void clickOnTrack(){

	//	WebElement html = driver.findElement(By.tagName("html"));
	//	html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
	//	html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		SeleniumUtil.hover(yourToolsMenu, driver);

		SeleniumUtil.element(onTrackSubMenu, driver).click();

		Reporter.log("Clicked on the on.Track sub menu. ");

	}
	public void clickOverviewTab() {

		Reporter.log("Clicking on the Overview Tab Locator identified by- "	+ overviewTab);
		driver.findElement(overviewTab).click();

	}
	//
	/**
	 * Clicks and Opens the Health Assessment page
	 */
	public void clickHealthAssessment() {
		SeleniumUtil.hoverAndClick(yourToolsMenu);
		SeleniumUtil.hoverAndClick(healthAssessmentSubMenu);

		try {
			Thread.sleep(TestProperty.THREAD_SLEEP);
		} catch (InterruptedException e) {
			logger.error("Thread sleep applied.", e);
			e.printStackTrace();
		}
		
		if (!driver.getCurrentUrl().contains("HealthAssessmentWelcome.aspx")){
			
			logger.warn("The navigation function did not work as expected for HA, trying by directly hitting the URL");
			//driver.get(TestProperty.CLIENTURL.concat("V2/MemberServices/MSEvents/HealthAssessmentWelcome.aspx"));
			
		} else {
			
			Reporter.log("Clicked on the HA sub menu. ");
			
		}

	}

	public void clickHealthAssessmentFromTheProgram() {
		SeleniumUtil.hoverAndClick(theProgramsMenu);
		SeleniumUtil.element(healthAssessmentSubMenu, driver).click();

		try {
			Thread.sleep(TestProperty.THREAD_SLEEP);
		} catch (InterruptedException e) {
			logger.error("Thread sleep applied.", e);
			e.printStackTrace();
		}

		if (!driver.getCurrentUrl().contains("HealthAssessmentWelcome.aspx")){

			logger.warn("The navigation function did not work as expected for HA, trying by directly hitting the URL");
			//driver.get(TestProperty.CLIENTURL.concat("V2/MemberServices/MSEvents/HealthAssessmentWelcome.aspx"));

		} else {

			Reporter.log("Clicked on the HA sub menu. ");

		}

	}


	/**
	 *  Clicks and opens the Biometric Screening main page
	 */
	public void clickBiometricScreening(){

		driver.get(TestProperty.CLIENT_URL.concat(TestProperty.BIOMETRICSCREENING_URI));
		/*SeleniumUtil.hover(yourToolsMenu, driver);
		SeleniumUtil.element(biometricScreeningSubMenu, driver).click();*/
		Reporter.log("Clicked on the Biometric Screening sub menu.");

	}


	/**
	 *  Clicks and opens the health manager page
	 */
	public void clickHealthManager(){
		try{
			SeleniumUtil.hover(yourToolsMenu, driver);
			SeleniumUtil.element(healthManagerSubMenu, driver).click();
			Reporter.log("Clicked on the Health Manager sub menu.");
	}
		catch(Exception e){		
				e.printStackTrace();
				
		}
	}	

	/**
	 * Clicks and opens the Health Coaching main page
	 */

	public void clickHealthCoaching(){

			try{
			SeleniumUtil.hover(resourcesMenu, driver);
			SeleniumUtil.sleep(1);
			SeleniumUtil.element(healthCoachingSubMenu, driver).click();
			Reporter.log("Clicked on the Health Coaching sub menu.");
			
	}
		catch(Exception e){		
				e.printStackTrace();
				
		}
	
	}	
		


	/**
	 * Clicks and opens the FitBit page
	 */
	public void clickFitBit(){

		SeleniumUtil.hover(yourToolsMenu, driver);

		SeleniumUtil.element(fitBitSubMenu, driver).click();

		Reporter.log("Clicked on the FitBit sub menu.");

	}

	/**
	 * Clicks and opens the Physical Activity Log page
	 */
	public void clickPAL(){

		SeleniumUtil.hover(yourToolsMenu, physicalActivityLogSubMenu,driver);
		SeleniumUtil.sleep(1);
		Reporter.log("Clicked on the Physical Activity Log sub menu.");
	}

	/**
	 * Clicks and opens the Lifestyle Manager page
	 */
	public void clickLM(){

		try{
			SeleniumUtil.hover(yourToolsMenu, driver);
			SeleniumUtil.element(lifestyleManagerSubMenu, driver).click();
			Reporter.log("Clicked on the Lifestyle Manager sub menu.");		
	}
		catch(Exception e){		
				e.printStackTrace();	
		}
	}	
		
		

	/**
	 * Clicks and Opens the OnTarget page
	 */
	public void clickOnTarget() {
		SeleniumUtil.switchToDefaultContent(driver);
		try{
			SeleniumUtil.scrollToElement(yourToolsMenu, driver);
			SeleniumUtil.hover(yourToolsMenu, driver);
			SeleniumUtil.element(onTargetSubMenu, driver).click();
		}
		catch(Exception e){
			e.printStackTrace();		
		}
		Reporter.log("Clicked on the onTarget sub menu. ");
	}

	/**
	 * Clicks and opens the Health Articles page
	 */
	public void clickHealthArticles(){

		SeleniumUtil.hover(resourcesMenu, driver);

		SeleniumUtil.element(healthArticlesSubMenu, driver).click();

		Reporter.log("Clicked on the health articles sub menu.");

	}


	/**
	 * Clicks and opens the Wellness Resources page
	 */
	public void clickWellnessResources(){

		SeleniumUtil.hover(resourcesMenu, driver);

		SeleniumUtil.element(wellnessResourcessSubMenu, driver).click();

		Reporter.log("Clicked on the Wellness Resources sub menu.");

	}



	/**
	 * Clicks and opens the Community page
	 */
	public void clickCommunity(){

		SeleniumUtil.hover(resourcesMenu, driver);

		SeleniumUtil.element(communitySubMenu, driver).click();

		Reporter.log("Clicked on the Community sub menu.");

	}


	/**
	 * Clicks and opens the Wellness Champions page
	 */
	public void clickWellnessChampions(){

		SeleniumUtil.hover(resourcesMenu, driver);

		SeleniumUtil.element(wellnessChampionsSubMenu, driver).click();

		Reporter.log("Clicked on the Wellness Champions sub menu.");

	}


	/**
	 * Clicks and opens the Challenge page
	 */
	public void clickChallenge(){

		SeleniumUtil.hover(resourcesMenu, driver);

		SeleniumUtil.element(challangeSubMenu, driver).click();

		Reporter.log("Clicked on the Challange sub menu.");

	}

	/**
	 * Clicks and opens the Challenge page
	 */
	public void clickWellnessWebinars(){

		SeleniumUtil.hover(resourcesMenu, driver);

		SeleniumUtil.element(wellnessWebinarsSubMenu, driver).click();

		Reporter.log("Clicked on the Wellness Webinar sub menu.");

	}
	
	/**
	 * Clicks the ConatctcLiveHealthier link to open the contact us page.
	 */
	public void clickContactLiveHealthier(){

		Reporter.log("Opening the ContactUS page...");

		driver.findElement(contactLiveHelathierLink).click();

	}

	/**
	 * Verifies that the breadcrumb has the expected text.
	 * @param breadcrumbSecondChild The visible text expected in the second breadcrumb.
	 * @return true/false depending on the match between the text appearing and expected in the second breadcrumb.
	 */
	public boolean verifyBreadcrumbFirstChild(String breadcrumbSecondChild){

		String breadcrumbSecondChildExpectedTxt = breadcrumbSecondChild.trim();

		boolean isExpectedtextVisible = false;

		String breadcrumbVisibleText = driver.findElement(breadcrumbSecond).getText().trim();
		
		Reporter.log("The expected text visible for the breadcrumb is - " + breadcrumbSecondChildExpectedTxt );
		Reporter.log("The actual text visible for the breadcrumb is - " + breadcrumbVisibleText );

		if(!breadcrumbSecondChildExpectedTxt.isEmpty()) {

			if (breadcrumbSecondChildExpectedTxt.equals(breadcrumbVisibleText)) {

				isExpectedtextVisible = true;

				Reporter.log("The text visible for the breadcrumb is as expected.");

			} else {
				
				Reporter.log("The expected text for the breadcrumb does not match the actual text.");
			}

		} else {


			Reporter.log("Empty string is passed as the expected text for the breadcrumb.");
		}

		return isExpectedtextVisible;

	}


	public boolean verifyTheProgramSubMenuItem (String breadcrumb){		

		boolean isPresent = false;

		clickProgramOverview();

		if (verifyBreadcrumbFirstChild(breadcrumb)){

			isPresent = true;

			Reporter.log("The bread crumb is found as expected.");

		}

		return isPresent;

	}


	public boolean verifyYourWellnessBankMenuItem (String breadcrumb){

		boolean isPresent = false;

		clickYourWellnessBank();

		if (verifyBreadcrumbFirstChild(breadcrumb)){

			isPresent = true;

			Reporter.log("The bread crumb is found as expected.");

		}

		return isPresent;

	}


	public boolean verifyHealthAssessmentMenuItem (String breadcrumb){

		boolean isPresent = false;

		clickHealthAssessment();

		if (verifyBreadcrumbFirstChild(breadcrumb)){
			
			isPresent = true;

			Reporter.log("The bread crumb is found as expected.");
			
		}

		return isPresent;


	}
	public boolean verifyBiometricScreeningMenuItem (String breadcrumb){
		boolean isPresent = false;

		clickBiometricScreening();

		if (verifyBreadcrumbFirstChild(breadcrumb)){

			isPresent = true;

			Reporter.log("The bread crumb is found as expected.");

		}

		return isPresent;

	}

	public boolean verifyHealthManagerMenuItem (String breadcrumb){
		boolean isPresent = false;

		clickHealthManager();

		if (verifyBreadcrumbFirstChild(breadcrumb)){

			isPresent = true;

			Reporter.log("The bread crumb is found as expected.");

		}

		return isPresent;


	}
	public boolean verifyHealthCoachingMenuItem (String breadcrumb){

		boolean isPresent = false;

		clickHealthCoaching();

		if (verifyBreadcrumbFirstChild(breadcrumb)){

			isPresent = true;

			Reporter.log("The bread crumb is found as expected.");

		}

		return isPresent;

	}
	public boolean verifyFitBitMenuItem (String breadcrumb){

		boolean isPresent = false;

		clickFitBit();

		if (verifyBreadcrumbFirstChild(breadcrumb)){

			isPresent = true;

			Reporter.log("The bread crumb is found as expected.");

		}

		return isPresent;

	}


	public boolean verifyPhysicalActivityogMenuItem (String breadcrumb){

		boolean isPresent = false;

		clickPAL();

		if (verifyBreadcrumbFirstChild(breadcrumb)){

			isPresent = true;

			Reporter.log("The bread crumb is found as expected.");

		}

		return isPresent;

	}

	public boolean verifyLifestyleManagerMenuItem (String breadcrumb){


		boolean isPresent = false;

		clickLM();

		if (verifyBreadcrumbFirstChild(breadcrumb)){

			isPresent = true;

			Reporter.log("The bread crumb is found as expected.");

		}

		return isPresent;

	}
	public boolean verifyHealthArticlesMenuItem (String breadcrumb){

		boolean isPresent = false;

		clickHealthArticles();

		if (verifyBreadcrumbFirstChild(breadcrumb)){

			isPresent = true;

			Reporter.log("The bread crumb is found as expected.");

		}

		return isPresent;

	}
	public boolean verifyWellnessResourcesMenuItem (String breadcrumb){


		boolean isPresent = false;

		clickWellnessResources();

		if (verifyBreadcrumbFirstChild(breadcrumb)){

			isPresent = true;

			Reporter.log("The bread crumb is found as expected.");

		}

		return isPresent;

	}
	public boolean verifyCommunintyMenuItem (String breadcrumb){


		boolean isPresent = false;

		clickCommunity();

		if (verifyBreadcrumbFirstChild(breadcrumb)){

			isPresent = true;

			Reporter.log("The bread crumb is found as expected.");

		}

		return isPresent;
	}

	public boolean verifyWellnessChampionsMenuItem (String breadcrumb){


		boolean isPresent = false;

		clickWellnessChampions();

		if (verifyBreadcrumbFirstChild(breadcrumb)){

			isPresent = true;

			Reporter.log("The bread crumb is found as expected.");

		}

		return isPresent;

	}
	public boolean verifyChallangeMenuItem (String breadcrumb){


		boolean isPresent = false;

		clickChallenge();

		if (verifyBreadcrumbFirstChild(breadcrumb)){

			isPresent = true;

			Reporter.log("The bread crumb is found as expected.");

		}

		return isPresent;

	}


	public boolean verifyActionCenter() {

		boolean isActionCenterPresent = false;

		if (driver.findElement(actionCenterframe).isDisplayed()) {

			isActionCenterPresent = true;

		}

		return isActionCenterPresent;
	}





	/**
	 * 
	 * @param photo1Src
	 * @param photo2Src
	 * @return
	 */
	public boolean verifyPhotoFlow(String photo1Src, String photo2Src) {

		boolean isPhotoFlowCorrect = false;

		if (driver.findElement(bannerPhoto1Img).isDisplayed()) {

			String currentPhoto1Src = driver.findElement(bannerPhoto1Img).getAttribute("src");

			if (photo1Src.trim().equalsIgnoreCase(currentPhoto1Src)) {

				try {

					Thread.sleep(TestProperty.THREAD_SLEEP);
					
					isPhotoFlowCorrect = true;

				} catch (InterruptedException e) {

					e.printStackTrace();

				}

				if (driver.findElement(bannerPhoto2Img).isDisplayed()) {

					String currentPhoto2Src = driver.findElement(bannerPhoto2Img).getAttribute("src");
					
					if (photo2Src.trim().equalsIgnoreCase(currentPhoto2Src)) {

						isPhotoFlowCorrect = true;

					}

				} else {

					Reporter.log("The second photo does not appear as expected.");

				}
			}

		} else {

			Reporter.log("The first photo does not appear as expected.");

		}

		return isPhotoFlowCorrect;
	}

	/**
	 * 
	 * @param indexPageData
	 * @return
	 */
	public boolean verifyMenuItemsPageLoad(Map<String, String> indexPageData) {

		boolean isPageLoadSuccessful = true;

		if (!verifyTheProgramSubMenuItem(indexPageData.get("programOverviewBreadCrumb"))) {

			isPageLoadSuccessful = false;

		} else if (!verifyYourWellnessBankMenuItem(indexPageData.get("wellnessBankBreadCrumb"))) {

			isPageLoadSuccessful = false;

		} else if (!verifyHealthAssessmentMenuItem(indexPageData.get("yourHealthAssessmsentBreadCrumb"))) {

			isPageLoadSuccessful = false;

		} else if (!verifyBiometricScreeningMenuItem(indexPageData.get("biometricScreeningBreadCrumb"))) {

			isPageLoadSuccessful = false;

		} else if (!verifyHealthManagerMenuItem(indexPageData.get("healthManagerBreadCrumb"))) {

			isPageLoadSuccessful = false;

		} else if (!verifyHealthCoachingMenuItem(indexPageData.get("healthCoachingBreadCrumb"))) {

			isPageLoadSuccessful = false;

//		} else if (!verifyFitBitMenuItem(indexPageData.get("fitBitBreadCrumb"))) {
//
//			isPageLoadSuccessful = false;

		} else if (!verifyPhysicalActivityogMenuItem(indexPageData.get("physicalActivityLogBreadCrumb"))) {

			isPageLoadSuccessful = false;

		} else if (!verifyLifestyleManagerMenuItem(indexPageData.get("lifestyleManagerBreadCrumb"))) {

			isPageLoadSuccessful = false;

		} else if (!verifyWellnessResourcesMenuItem(indexPageData.get("wellnessResourcesBreadCrumb"))) {

			isPageLoadSuccessful = false;

		} else if (!verifyLifestyleManagerMenuItem(indexPageData.get("lifestyleManagerBreadCrumb"))) {

			isPageLoadSuccessful = false;

		} else if (!verifyWellnessResourcesMenuItem(indexPageData.get("wellnessResourcesBreadCrumb"))) {

			isPageLoadSuccessful = false;

//		} else if (!verifyCommunintyMenuItem(indexPageData.get("communityBreadCrumb"))) {
//
//			isPageLoadSuccessful = false;

//		} else if (!verifyWellnessChampionsMenuItem(indexPageData.get("wellnessChampionsBreadCrumb"))) {
//
//			isPageLoadSuccessful = false;

		} else if (!verifyChallangeMenuItem(indexPageData.get("challengeBreadCrumb"))) {

			isPageLoadSuccessful = false;

		}

		return isPageLoadSuccessful;
	}

	/** 
	 * Verify Help link is present
	 */
	public boolean verifyHelpLinkIsDisplayed(){

		boolean isPresent = false;

		if(SeleniumUtil.isElementDisplayed(helplLnk, driver)){
			isPresent = true;
			Reporter.log("Help link is displayed...");
		}

		return isPresent;
	}

	/**
	 * Click on Help link
	 */
	public void clickHelpLink(){
		Reporter.log("Clicking on the  the HelpLinkLocator indentified by- " + helplLnk);
		driver.findElement(helplLnk).click();
	}

	/**
	 * Verify link Terms of Use is displayed
	 * @return true/false based on whether link is present or not
	 */
	public boolean verifyTermsOfUseIsDispalyed(){
		try{
		
		if(SeleniumUtil.isElementDisplayed(termsOfUseLink, driver)){
			Reporter.log("TermsOfUse link is displayed...");
			flag = true; 
		}
		}catch(Exception e){
			
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
	
	/**
	 *Click on link Terms of Use
	 */
	public void clickTermsOfUseLink(){

		Reporter.log("Clicking on the  the termsOfUseLink Locator indentified by- " + termsOfUseLink);
		driver.findElement(termsOfUseLink).click();
	}

	/**
	 * Verify link PrivacyPolicy is displayed
	 * @return true/false based on whether link is present or not
	 */
	public boolean verifyPrivacyPolicyIsDispalyed(){
		
		try{
		if(SeleniumUtil.isElementDisplayed(privacyPolicyLink, driver)){
			Reporter.log("Privacy Policy link is displayed...");
			flag = true; 
		}
		}catch(Exception e){
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	/**
	 * Verify that link Privacy Policy is displayed and click on it
	 */
	public void clickPrivacyPolicyLink(){
		try{
		Reporter.log("Clicking on the  the privacyPolicyLink Locator indentified by- " + privacyPolicyLink);
		driver.findElement(privacyPolicyLink).click();
	}catch(Exception e){
		
		e.printStackTrace();
	}
	}
	/**
	 * Verify link Contact LiveHealthier is displayed and click on it
	 */
	public boolean verifyContactLiveHealthierLinkisDisplayed(){

		try{
		
		if(SeleniumUtil.isElementDisplayed(contactLiveHelathierLink, driver)){
			flag = true;
			Reporter.log("Contact Us link is displayed...");
		}
		
		Reporter.log("Clicking on the  the contactUsLink Locator indentified by- " + contactLiveHelathierLink);
		}catch(Exception e){
		e.printStackTrace();
		flag = false;
		}
			return flag;
	}

	/**
	 *  Verify Client logo's are displayed on Index page 
	 */
	public boolean verifyClientLogo(){
		try{
		
		if(SeleniumUtil.isElementDisplayed(img_leftLogo, driver)){
			flag = true;
			Reporter.log("Both primary and secondary client logos are displayed...");
		}
		}catch(Exception e){
			
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
	
/**
 * This method mouse hovers and clicks on tools-menu sub links
 * @param sublinkText
 *  
 */
	public void clickOnToolsSubLinks(String sublinkText){
		try{
		SeleniumUtil.hover(yourToolsMenu, driver);	
		driver.findElement(SeleniumUtil.dynamicXpath(toolsPath, sublinkText)).click();
		Reporter.log("Click on the tools sub link item");
		}catch(Exception e){
			e.printStackTrace();
		}
		}

/**
 * This method verifies the text present on the banner with the actual text
 * @param text
 * @return
 */
	public boolean verifyToolSubText(String text){
		
		try{
		
		String imgText=SeleniumUtil.getTextfromWebElement(imageText, driver);
		Reporter.log("The image text is "+imgText);
		if(imgText.contains(text)){
			flag=true;
			Reporter.log("Successfully verified the tools sub menu"+imgText);	
		}
		}catch(Exception e){
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	/**
	 * Verify banner label is displayed and verifying its text
	 * @param bannerText: Text appearing on the banner corresponding to the link clicked
	 * @return true/false based on whether banner is present and its text is correct or not
	 */
	public boolean verifyBannerLabelAndText(String bannerText){
		
		try{
	
		SeleniumUtil.isElementDisplayed(indexPageBnr, driver);
		String bnrText = SeleniumUtil.element(indexPageBnr, driver).getText();
		if(bnrText.contains(bannerText)){
			Reporter.log("Banner text displayed is correct");
			flag = true;
		}
		}catch(Exception e){
			e.printStackTrace();
			flag = false;
		}
		
		return flag;
	}

	/**
	 * Click the various sub menu of The Program menu item
	 */
	public void clickTheProgramSubMenu(String subMenu){

		try{
		SeleniumUtil.hover(theProgramsMenu, driver);
		SeleniumUtil.element(SeleniumUtil.dynamicXpath(theProgramSubMenu,subMenu), driver).click();
		Reporter.log("Clicked on the" + subMenu+" sub menu. ");
	}catch(Exception e){
		
		e.printStackTrace();
	}
	}
	/**
	 * Verify all the Header links are displayed
	 * @return true/false based on whether all the Header elements are present or not
	 */
	public boolean verifyHeader(){

		try{
		
		if(verifyHelpLinkIsDisplayed() && verifyPreferencesLink() && verifyLogoutLink()){
			Reporter.log("All the header links are displayed...");
			flag = true;
		}
		}catch(Exception e){
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	/**
	 * Verify all the Footer links are displayed
	 * @return true/false based on whether all the Header elements are present or not
	 */
	public boolean verifyFooter(){
		
		try{
		if(verifyTermsOfUseIsDispalyed() && verifyPrivacyPolicyIsDispalyed() && verifyContactLiveHealthierLinkisDisplayed()){
			Reporter.log("All the Footer links are displayed...");
			flag = true;
		}
		}catch(Exception e){
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
	
	/**
	 * Select campaign from delorean
	 */
	
	public void setDelorian(String date,String campaign){
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		
		int count = 0;
		selectDelorianOption(campaign);
		
		SeleniumUtil.waitForElementToBePresent(delorianDropdown, driver);
		if(date.length()==10){
		driver.findElement(delorianDate).click();
		} else {
			SeleniumUtil.sendKeysToWebElement(delorianDate, date, driver);
		}
		datePickerClick(date);

		SeleniumUtil.switchToDefaultContent(driver);
	}
	
	public void delorianErrorPageCheck(String date){
		if(!SeleniumUtil.isElementPresent(delorianDropdown, driver)){
			SeleniumUtil.click(errorHomePageLink, driver);
			datePickerClick(date);
			clickOnTrack();
		}
	}
	/**
	 * Select campaign from delorean
	 */
	
	public void selectDelorianOption(String campaign){
		
		Select delorianList = new Select(SeleniumUtil.element(delorianDropdown,driver));
		delorianList.selectByValue(campaign);
		
	}
	
	public void clickSurveyOption(By element){
		List<WebElement> surveyOptions = driver.findElements(element);
		
		String surveyop = surveyOptions.get(0).getAttribute("tagName").toLowerCase();
		Random randomRadio = new Random();
		int count = 0;
		if(surveyop.contains("ul")){
			
			List<WebElement> radioBtnList = driver.findElements(surveyCommonRadioList);
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
	
	public void clickSurveyOption2(){
		Random randomRadio = new Random();
		int count = 0;
		int surveyPageNo=0;
		for(surveyPageNo=1;surveyPageNo<9;surveyPageNo++){
		if(SeleniumUtil.isElementPresent(By.tagName("li"), driver)){
			
			List<WebElement> radioBtnList = driver.findElements(surveyCommonRadioList);
			count = radioBtnList.size()-2;
			radioBtnList.get(randomRadio.nextInt(count)).findElement(By.tagName("label")).click();
		} else if(SeleniumUtil.isElementPresent(By.tagName("table"), driver)){
			
			WebElement radioTable = driver.findElement(surveyCommonRadioTable);  
			  List<WebElement> rows=radioTable.findElements(By.tagName("tr"));  
			
				  for(count=0;count<rows.size();count++){
					  List<WebElement> columns=rows.get(count).findElements(By.tagName("td"));  
				 columns.get(randomRadio.nextInt(columns.size()-1)).findElement(By.tagName("label")).click();
			  }
		} else if(SeleniumUtil.isElementPresent(survey6HoursOfSleeptxt, driver)){
			SeleniumUtil.sendKeysToWebElement(survey6HoursOfSleeptxt, "8", driver);
		} else {
			//error
		}
		if(SeleniumUtil.isElementPresent(completeSurveyButton,driver)){
			SeleniumUtil.isElementDisplayed(completeSurveyButton, driver);
			driver.switchTo().defaultContent();
		}else {
			SeleniumUtil.click(nextSurveyPageButton, driver);
			
		}
		}
	}
	/*
	 * A random date is selected between two passed date.
	 */
	
	public String clickRandomInBetweenDate(String startDate,String endDate) throws ParseException{
		Random randomDd = new Random();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/dd/yyyy");
		LocalDate start = LocalDate.parse(startDate,formatter);
		LocalDate end = LocalDate.parse(endDate,formatter);
		List<LocalDate> totalDates = new ArrayList<>();
		while (!start.isAfter(end)) {
		    totalDates.add(start);  
		    start = start.plusDays(1);	   
		}
		LocalDate lDate = totalDates.get(randomDd.nextInt(totalDates.size()-1));
        String dateStr = lDate.format(formatter);
        Reporter.log("Subtracted Date = "+dateStr);
        datePickerClick(dateStr);
        return dateStr;
	}
	
	public String formatDate(Date date,String format){
		String sDate = " ";
		
	    SimpleDateFormat format1 = new SimpleDateFormat(format);
	    try {
	      Date date1 = format1.parse(date.toString());
	      sDate = date1.toString();
	    } catch (ParseException e) {
	      e.printStackTrace();
	    }
	    // formatting
	    return sDate;
	  }
		
	
	public void datePickerClick(String date){
		SeleniumUtil.waitForElementToBePresent(By.className("ui-datepicker-title"), driver);
		//String calDate = date.substring(date.indexOf("/") + 1, date.indexOf("/201"));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyy");
		
		LocalDate delorianD //= LocalDate.parse(date,formatter);
		= LocalDate.parse(date, formatter.withLocale(Locale.ENGLISH));
		String year = String.format("%s", delorianD.getYear());
		Reporter.log("Year ="+year);
	    String month=	String.format("%s", delorianD.getMonthValue()-1);
	    Reporter.log("month ="+month);
	    String calDate = String.format("%s", delorianD.getDayOfMonth());
	    Reporter.log("Local date day= "+calDate);

		SeleniumUtil.waitForElementToBePresent(By.className("ui-datepicker-title"), driver);
		
			
		Select dyear = new Select(SeleniumUtil.element(delorianYearDropdown, driver));
		dyear.selectByValue(year);
		try{
	//	SeleniumUtil.waitForElementTextToBeVisible(delorianSelectedYear, year,driver);
			Thread.sleep(2000);
		} catch (TimeoutException e){
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SeleniumUtil.waitForElementToBePresent(By.xpath("//*[@class='ui-datepicker-year' ]/option[@selected='selected' and @value='"+year+"']"), driver);
				
		//Reporter.log("current year = "+SeleniumUtil.getTextfromWebElement(delorianSelectedYear,driver));

	    Select dmonth = new Select(SeleniumUtil.element(delorianMonthDropdown, driver));
	    dmonth.selectByValue(month);
	    SeleniumUtil.waitForElementToBePresent(By.xpath("//*[@class='ui-datepicker-month']/option[@selected='selected' and @value='"+month+"']"), driver);
	    
		SeleniumUtil.waitForElementToBePresent(delorianCalendar, driver);
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
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
				 //  SeleniumUtil.click(By.linkText(calDate), driver);
				   try{
				   cell.click(); 
				   }catch (TimeoutException e){
					   e.printStackTrace();
				   }
			   }
				   Reporter.log("Delorian Date Clicked....");
		   		   break;  
		   }  		
		  }
	}


public void clickBYOA(){
	try{
		SeleniumUtil.hover(yourToolsMenu, driver);
		SeleniumUtil.element(byoaSubMenu, driver).click();
		Reporter.log("Clicked on the BYOA sub menu sub menu. ");
	}catch(Exception e){
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
}
