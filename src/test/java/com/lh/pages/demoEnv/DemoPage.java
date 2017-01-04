package com.lh.pages.demoEnv;

import com.lh.testConfig.TestProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.util.concurrent.TimeUnit;

import static com.lh.helper.DriverFactory.driver;
import static com.lh.utils.SeleniumUtil.waitForElementToLoad;
import static com.lh.utils.UtilityMethods.getResponseCode;

/**
 * ----------------------------------------------
 * Created by Oleg Andreyev (EnvolvePeopleCare) on 6/15/2016.
 * <p>
 * ----------------------------------------------
 * ----------------------------------------------
 */
public class DemoPage {
    /**
     * Logger for messages.
     */
    private static Logger logger = LogManager
            .getLogger(DemoPage.class);

    /**
     * Expectations for Assertions
     */
    boolean T = true;
    boolean F = false;

    /**
     * Test Properties.
     */
    protected static final String username = TestProperty.DEMO_USERNAME;
    protected static final String password = TestProperty.DEMO_PASSWORD;

    protected static final String wellnessBankURL = TestProperty.DEMO_URL.concat(TestProperty.DEMO_wellnessBankURI);
    protected static final String onTrackURL = TestProperty.DEMO_URL.concat(TestProperty.DEMO_onTrackURI);
    protected static final String onTrackTeamURL = TestProperty.DEMO_URL.concat(TestProperty.DEMO_onTrackTeamURI);

    protected static final String onCallURL = TestProperty.DEMO_URL.concat(TestProperty.DEMO_onCallURI);
    protected static final String onCallOnlineURL = TestProperty.DEMO_URL.concat(TestProperty.DEMO_onCallOnlineURI);
    protected static final String onCallOnSiteURL = TestProperty.DEMO_URL.concat(TestProperty.DEMO_onCallOnSiteURI);
    protected static final String onCallByPhoneURL = TestProperty.DEMO_URL.concat(TestProperty.DEMO_onCallByPhoneURI);

    protected static final String yourHAURL = TestProperty.DEMO_URL.concat(TestProperty.DEMO_yourHAURI);

    protected static final String bioScreeningURL = TestProperty.DEMO_URL.concat(TestProperty.DEMO_bioScreeningURI);
    protected static final String healthManagerURL = TestProperty.DEMO_URL.concat(TestProperty.DEMO_healthManagerURI);
    protected static final String appManagerURL = TestProperty.DEMO_URL.concat(TestProperty.DEMO_appManagerURI);
    protected static final String lifestyleManagerURL = TestProperty.DEMO_URL.concat(TestProperty.DEMO_lifestyleManagerURI);
    protected static final String goMobileURL = TestProperty.DEMO_URL.concat(TestProperty.DEMO_goMobileURI);

    protected static final String contactUsURL = TestProperty.DEMO_URL.concat(TestProperty.DEMO_contactUsURI);

    protected static final String onTargetURL = TestProperty.DEMO_URL.concat(TestProperty.DEMO_onTargetURI);
    protected static final String onTargetYourTargetURL = TestProperty.DEMO_URL.concat(TestProperty.DEMO_onTargetYourTargetURI);
    protected static final String onTargetAchievementsURL = TestProperty.DEMO_URL.concat(TestProperty.DEMO_onTargetAchievementsURI);

    protected static final String healthArticlesURL = TestProperty.DEMO_URL.concat(TestProperty.DEMO_healthArticlesURI);
    protected static final String financialWellnessURL = TestProperty.DEMO_URL.concat(TestProperty.DEMO_financialWellnessURI);
    protected static final String wellnessResourcesURL = TestProperty.DEMO_URL.concat(TestProperty.DEMO_wellnessResourcesURI);
    protected static final String communityURL = TestProperty.DEMO_URL.concat(TestProperty.DEMO_communityURI);
    protected static final String wellnessChampionsURL = TestProperty.DEMO_URL.concat(TestProperty.DEMO_wellnessChampionsURI);
    protected static final String webinarsURL = TestProperty.DEMO_URL.concat(TestProperty.DEMO_webinarsURI);

    /**===========================
    * HTML LOCATORS
    */
    By liveSupportSendBtn                           = By.xpath(".//*[@id='frmCallInvoerAnoniem']/div[3]/div/div[1]/div[2]/a");
    //=== Login Page
    By loginButton                                  = By.cssSelector("#ctl00_ContentPlaceHolder1_lnkLogin");
    By usernameField                                = By.cssSelector("#ctl00_ContentPlaceHolder1_UserName");
    By passwordField                                = By.cssSelector("#ctl00_ContentPlaceHolder1_Password");
    By contactUsLink                                = By.linkText("Contact Us");
    By contactUsSubmitButton                        = By.cssSelector("#ContactFormSubmit");
    //=== Portal Home
    By logoutLink                                   = By.cssSelector("#LogoutLink");
    //HA
    By takeNowHAButton                              = By.cssSelector("#lnkScheduleNow");
    //=== Wellness Bank
    By ProgressSnapshotWB                           = By.xpath("//*[contains(.,'Progress Snapshot')][not(.//*[contains(., 'Progress Snapshot')])]");
    //=== On.Track Team
    By trackNowButton                               = By.cssSelector("#track>a>img");
    //=== On.Target
    By achivementsSubLink                           = By.xpath("//*[contains(.,'Achievements')][not(.//*[contains(., 'Achievements')])]");
    By continueButton                               = By.xpath(".//*[@id='OnTargetBanner']/div/div/div[4]/a");
    By editLinkOnTargetYourTarget                   = By.xpath("//*[contains(.,'Edit')][not(.//*[contains(., 'Edit')])]");
    //=== On.Call
    By contactNowOnCallOnline                       = By.cssSelector("#ctl00_ContentPlaceHolder1_coachingExperts_coachList_ctl00_contactNowButton");
    By scheduleNowOnCallOnsite                      = By.cssSelector(".button>a");
    //=== Biometric Screening
    By downloadNowPCPForm                           = By.cssSelector("#ctl00_ContentPlaceHolder1_ScreeningLandingControl_OffsiteFormRepeater_ctl00_lnkDownload");
    //=== Health Manager
    By viewHistoryBtnHealthManager                  = By.cssSelector("#ViewHistoryButton");
    //=== App Manager
    By fitnessBlockAppManager                       = By.cssSelector(".category-block.fitness");
    //=== Lifestyle Manager
    By stepsBoxLifestyleManager                     = By.cssSelector("#steps");
    //=== Go Mobile
    By androidBtnGoMobile                           = By.cssSelector(".button.leftButton");
    //=== Health Articles
    By articleBlocksHealthArticles                  = By.cssSelector(".article-block");


    /**
     * One param constructor
     *
     */
    public DemoPage() {
        super();
        if (!(driver.getTitle()
                .contains(TestProperty.DEMO_CLIENT))) {
            // Alternatively, we could navigate to the login page, perhaps
            // logging out first
            driver.get(TestProperty.DEMO_URL);
            //logger.error("This is not the Demo Login page");
            //throw new IllegalStateException("This is not the Demo Login page");
        }
        logger.info("Successfully *initialized* the Demo Login page object");
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    //===============
    /**
     * Sets defined Username into the Field
     * @param username
     */
    private void setUsername(final String username) {
        Reporter.log("Entering the username in the Element identified by - " + usernameField);
        driver.findElement(usernameField).sendKeys(username);
        Reporter.log("Entered the Username as " + username);
    }
    /**
     *Sets defined Password into the Field
     * @param password
     */
    private void setPassword(final String password) {
        Reporter.log("Entering the Password in the Element identified by - " + passwordField);
        driver.findElement(passwordField).sendKeys(password);
        Reporter.log("Entered the Password as " + password);
    }
    private void submitLogin() {
        Reporter.log("Clicking the Login button identified by - " + loginButton);
        //logger.info("Clicking the Login button identified by - " + usernameTxt);
        driver.findElement(loginButton).submit();
        Reporter.log("...Logging into the application...");
        //logger.info("Logging into the application...");
    }
    public void loginAs(final String username, final String password) {
        Reporter.log("Logging into the application...");
        //logger.info("Logging into the application...");
        setUsername(username);
        setPassword(password);
        submitLogin();
        Reporter.log("Success - Logged into the...");

        //logger.info("Logged into the application...");
    }
    //====
    public void goToContactUs() {
        driver.findElement(contactUsLink).click();
        getResponseCode(contactUsURL);
        Reporter.log("Navigated successfully to the Contact Us page.");
    }
    public void goToYourHA() {
        driver.get(yourHAURL);
        getResponseCode(yourHAURL);
        Reporter.log("Navigated successfully to 'Your Health Assessment' page.");
    }

    public void goToWellnessBank() {
        driver.get(wellnessBankURL);
        getResponseCode(wellnessBankURL);
        Reporter.log("Navigated successfully to 'Wellness Bank' page.");
    }

    public void checkOnTrack() {
        driver.get(onTrackURL);
        getResponseCode(onTrackURL);
        Reporter.log("Navigated successfully to 'On.Track' page.");
    }

    public void goToOnTrackTeam() {
        driver.get(onTrackTeamURL);
        getResponseCode(onTrackTeamURL);
        Reporter.log("Navigated successfully to 'On.Track Team' page.");
    }
    public void goToOnTargetDashboard() {
        driver.get(onTargetURL);
        getResponseCode(onTargetURL);
        Reporter.log("Navigated successfully to 'On.Target Dashboard' page.");
    }
    public void goToOnTargetYourTarget() {
        driver.get(onTargetYourTargetURL);
        getResponseCode(onTargetYourTargetURL);
        Reporter.log("Navigated successfully to 'On.Target - Your Target' page.");
    }
    public void goToOnTargetAchievements() {
        driver.get(onTargetAchievementsURL);
        getResponseCode(onTargetAchievementsURL);
        Reporter.log("Navigated successfully to 'On.Target - Achievements' page.");
    }
    public void goToOnCallOverview() {
        driver.get(onCallURL);
        getResponseCode(onCallURL);
        Reporter.log("Navigated successfully to 'On.Call - Coaching Overview' page.");
    }
    public void goToOnCallOnline() {
        driver.get(onCallOnlineURL);
        getResponseCode(onCallOnlineURL);
        Reporter.log("Navigated successfully to 'On.Call - Online' page.");
    }
    public void goToOnCallOnsite() {
        driver.get(onCallOnSiteURL);
        getResponseCode(onCallOnSiteURL);
        Reporter.log("Navigated successfully to 'On.Call - On Site' page.");
    }

    public void goToOnCallPhone() {
        driver.get(onCallByPhoneURL);
        getResponseCode(onCallOnSiteURL);
        Reporter.log("Navigated successfully to 'On.Call - By Phone' page.");
    }
    public void goToBiometricScreening() {
        driver.get(bioScreeningURL);
        getResponseCode(bioScreeningURL);
        Reporter.log("Navigated successfully to 'Biometric Screening' page.");
    }
    public void goToHealthManager() {
        driver.get(healthManagerURL);
        getResponseCode(healthManagerURL);
        Reporter.log("Navigated successfully to 'Health Manager' page.");
    }
    public void goToAppManager() {
        driver.get(appManagerURL);
        getResponseCode(appManagerURL);
        Reporter.log("Navigated successfully to 'App Manager' page.");
    }
    public void goToLifestyleManger() {
        driver.get(lifestyleManagerURL);
        getResponseCode(lifestyleManagerURL);
        Reporter.log("Navigated successfully to 'Lifestyle Manager' page.");
    }
    public void goToGoMobile() {
        driver.get(goMobileURL);
        getResponseCode(goMobileURL);
        Reporter.log("Navigated successfully to 'Go Mobile' page.");
    }
    public void goToHealthArticles() {
        driver.get(healthArticlesURL);
        getResponseCode(healthArticlesURL);
        Reporter.log("Navigated successfully to 'Health Articles' page.");
    }
    public void goToFinancialWellness() {
        driver.get(financialWellnessURL);
        getResponseCode(financialWellnessURL);
        Reporter.log("Navigated successfully to 'Financial Wellness' page.");
    }
    public void goToCommunity() {
        driver.get(communityURL);
        getResponseCode(communityURL);
        Reporter.log("Navigated successfully to 'Community' page.");
    }
    public void goToWellnessResources() {
        driver.get(wellnessResourcesURL);
        getResponseCode(wellnessResourcesURL);
        Reporter.log("Navigated successfully to 'Wellness Resources' page.");
    }
    public void goToWellnessChampions() {
        driver.get(wellnessChampionsURL);
        getResponseCode(wellnessChampionsURL);
        Reporter.log("Navigated successfully to 'Wellness Champions' page.");
    }
    public void goToWebinars() {
        driver.get(webinarsURL);
        getResponseCode(webinarsURL);
        Reporter.log("Navigated successfully to 'Webinars' page.");
    }


    public void liveSupportWindowOpen() {
        driver.findElement(By.linkText("Live Support")).click();
        getResponseCode(contactUsURL);

        //driver.switchTo().window

        new WebDriverWait(driver,TestProperty.WAITING_TIME).until
                (ExpectedConditions.presenceOfElementLocated(liveSupportSendBtn));
    }

    public void logout() {
        driver.findElement(logoutLink).click();

        Reporter.log("Successfully logged out from Demo Portal.");
    }

    /**
     * VERIFICATIONS
     *
     */
    public boolean verifyContactUsButton() {
        if(driver.findElement(contactUsSubmitButton).isDisplayed()){
            return T;
        }
        else{
            return F;
        }
    }
    public boolean verifyHATakeNowButton() {
        if(driver.findElement(takeNowHAButton).isDisplayed()){
            return T;
        }
        else{
            return F;
        }
    }
    public boolean verifyWBLoaded() {
        if(driver.findElement(ProgressSnapshotWB).isDisplayed()){
            return T;
        }
        else{
            return F;
        }
        /*new WebDriverWait(driver,waitTime).until
                (ExpectedConditions.presenceOfElementLocated(ProgressSnapshotWB));
        assertThat(driver.findElement(ProgressSnapshotWB).isDisplayed(), is(equalTo(T)));*/
    }
    public boolean verifyOnTrackTeamButton() {
        if(driver.findElement(trackNowButton).isDisplayed()){
            return T;
        }
        else{
            return F;
        }
        //assertThat(driver.findElement(trackNowButton).isDisplayed(), is(equalTo(T)));
    }
    public boolean verifyOnTargetDashboardButton() {
        waitForElementToLoad(TestProperty.WAITING_TIME,continueButton);
        if(driver.findElement(continueButton).isDisplayed()){
            return T;
        }
        else{
            return F;
        }

    }
    public boolean verifyYourTargetEditButton() {
        if(driver.findElement(editLinkOnTargetYourTarget).isDisplayed()){
            return T;
        }
        else{
            return F;
        }
        /*new WebDriverWait(driver,waitTime).until
                (ExpectedConditions.presenceOfElementLocated(editLinkOnTargetYourTarget));
        assertThat(driver.findElement(editLinkOnTargetYourTarget).isDisplayed(), is(equalTo(T)));*/
    }
    public boolean verifyOnCallContactNowButton() {
        if(driver.findElement(contactNowOnCallOnline).isDisplayed()){
            return T;
        }
        else{
            return F;
        }
        //assertThat(driver.findElement(contactNowOnCallOnline).isDisplayed(), is(equalTo(T)));
    }
    public boolean verifyScheduleNowOnCallOnSite() {
        if(driver.findElement(scheduleNowOnCallOnsite).isDisplayed()){
            return T;
        }
        else{
            return F;
        }
        //assertThat(driver.findElement(scheduleNowOnCallOnsite).isDisplayed(), is(equalTo(T)));
    }
    public boolean verifyDownloadNowBioScreening() {
        if(driver.findElement(downloadNowPCPForm).isDisplayed()){
            return T;
        }
        else{
            return F;
        }
        /*new WebDriverWait(driver,waitTime).until
                (ExpectedConditions.presenceOfElementLocated(downloadNowPCPForm));
        assertThat(driver.findElement(downloadNowPCPForm).isDisplayed(), is(equalTo(T)));*/
    }
    public boolean verifyHealthArticles() {
        if(driver.findElement(articleBlocksHealthArticles).isDisplayed()){
            return T;
        }
        else{
            return F;
        }
        //assertThat(driver.findElement(articleBlocksHealthArticles).isDisplayed(), is(equalTo(T)));
    }
    public boolean verifyMobilePage() {
        if(driver.findElement(androidBtnGoMobile).isDisplayed()){
            return T;
        }
        else{
            return F;
        }
        //assertThat(driver.findElement(androidBtnGoMobile).isDisplayed(), is(equalTo(T)));
    }
    public boolean verifyStepsLifestyleManager() {
        if(driver.findElement(stepsBoxLifestyleManager).isDisplayed()){
            return T;
        }
        else{
            return F;
        }
        //assertThat(driver.findElement(stepsBoxLifestyleManager).isDisplayed(), is(equalTo(T)));
    }
    public boolean verifyBlockInAppManager() {
        new WebDriverWait(driver,TestProperty.WAITING_TIME).until(ExpectedConditions.presenceOfElementLocated(fitnessBlockAppManager));
        return T;

    }
    public boolean verifyHistoryButtonHealthManager() {
        if(driver.findElement(viewHistoryBtnHealthManager).isDisplayed()){
            return T;
        }
        else{
            return F;
        }
        //assertThat(driver.findElement(viewHistoryBtnHealthManager).isDisplayed(), is(equalTo(T)));
    }
    public boolean verifyOnTrack(){
        WebElement onTrackOverview = driver.findElement(By.cssSelector("#box-one-link"));

        if(onTrackOverview.getText().equalsIgnoreCase("Overview")){
            return T;
        }
        else{
            return F;
        }
    }
    public boolean verifyWebinars(){
        WebElement onTrackOverview = driver.findElement(By.cssSelector(".col-md-6.navbar-title>p"));

        if(onTrackOverview.getText().equalsIgnoreCase("Webinars")){
            return T;
        }
        else{
            return F;
        }
    }


    public boolean verifyWellnessChampions() {
        WebElement whoTheyAreLinkWC = driver.findElement(By.cssSelector(
                "#ctl00_ctl00_ContentPlaceHolder1_TheNavigationButtonList_NavigationButtonRepeater_ctl01_NavigationLinkButton"));

        if(whoTheyAreLinkWC.isDisplayed()){
            return T;
        }
        else{
            return F;
        }
    }

    public boolean verifyWellnessResources() {
        WebElement titleWR = driver.findElement(By.cssSelector(".col-md-6.navbar-title>p"));

        if(titleWR.getText().equalsIgnoreCase("Wellness Resources")){
            return T;
        }
        else{
            return F;
        }
    }

    public boolean verifyCommunity() {
        WebElement titleCommunity = driver.findElement(By.cssSelector(".headerTextContainer"));

        if(titleCommunity.getText().equalsIgnoreCase("Community")){
            return T;
        }
        else{
            return F;
        }
    }

    public boolean verifyFinancialWellness() {
        WebElement titleFinWellnessArticles = driver.findElement(By.cssSelector(".col-md-6.navbar-title"));

        if(titleFinWellnessArticles.getText().equalsIgnoreCase("Financial Wellness Articles")){
            return T;
        }
        else{
            return F;
        }
    }

    public boolean verifyOnCallOverview() {
        WebElement onCallOverviewText = driver.findElement(By.cssSelector(".web-access-skip.coaching-bottom-right>h1"));

        if(onCallOverviewText.getText().equalsIgnoreCase("Get On.Call Health Coaching")){
            return T;
        }
        else{
            return F;
        }
    }

    public boolean verifyOnTargetAchievements() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(achivementsSubLink)).isDisplayed();
        return T;
    }

    public boolean verifyOnCallPhone() {
        WebElement onCallPhoneText = driver.findElement(By.cssSelector(".web-access-skip.coaching-bottom-right>h1"));

        if(onCallPhoneText.getText().equalsIgnoreCase("On.Call Health Coaching by Phone")){
            return T;
        }
        else{
            return F;
        }
    }
    public boolean verifyLoggedOut(){
        waitForElementToLoad(TestProperty.WAITING_TIME,loginButton);
        return T;
    }


}