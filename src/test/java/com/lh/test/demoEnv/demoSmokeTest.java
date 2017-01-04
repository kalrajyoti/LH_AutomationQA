package com.lh.test.demoEnv;

import static com.lh.testConfig.TestProperty.DEMO_PASSWORD;
import static com.lh.testConfig.TestProperty.DEMO_URL;
import static com.lh.testConfig.TestProperty.DEMO_USERNAME;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.lh.pages.demoEnv.DemoPage;
import com.lh.test.base.AuthBaseTestClass;

/**
 * ----------------------------------------------
 * Created by Oleg Andreyev (EnvolvePeopleCare) on 6/15/2016.
 * <p>
 * Versions:
 * * Deploy Number - 1514, 1515, 1521, 1525, 1526, 1527
 * * Build Number - Not Specified
 * <p>
 * Notes/Comments
 * 6.16.16 - OnTarget Pages were problematic and showing HTTP 404 errors.
 * ----------------------------------------------
 * ----------------------------------------------
 */
@Listeners(com.lh.helper.LHTestListener.class)
public class demoSmokeTest extends AuthBaseTestClass {

    boolean T = true;

    private static Logger logger = LogManager.getLogger(demoSmokeTest.class);
    private DemoPage demoPage;

    private String testName;
    /*private ExcelFileUtility testDataObj;
    private Map<String, String> loginData;
    private AdminLoginPage adminPageobj;*/

    /** -----------------     */
    /** -- Tests Below --     */
    /** -----------------
     */
    @BeforeClass
    public void initClass() {
        doLogin(DEMO_USERNAME,DEMO_PASSWORD,"Portal","Portal",DEMO_URL);
        demoPage = new DemoPage();
        Reporter.log("Successfully Logged into the Demo Portal");

        logger.info("Successfully exiting the initClass() for DEMO SMOKE test class.");
    }
    @BeforeMethod
    public void initMethod(Method method) throws Exception {
        testName = method.getName();

        logger.info("Successfully exiting the initMethod() for DEMO SMOKE test class.");
    }
    @AfterClass
    public void logOutAndClassTearDown(){
        demoPage.logout();
        demoPage.verifyLoggedOut();

        demoPage = null;
    }
    //=========
    //=========
    @Test(groups = {"smoke"},description = "Verify user can navigate to 'Your Health Assessment' page and Take Now button is present.")
    public void navigateToYourHA() {
        demoPage.goToYourHA();
        assertThat(demoPage.verifyHATakeNowButton(), is(equalTo(T)));
    }
    @Test(groups = {"smoke"}, description = "Verify user is able to navigate to Contact Us Page.")
    public void contactUsLoading() {
        demoPage.goToContactUs();
        assertThat(demoPage.verifyContactUsButton(), is(equalTo(T)));
    }
    @Test(groups = {"smoke"},description = "Navigate to Wellness Bank and confirm first incentive block has loaded.")
    public void navigateToWellnessBank() {
        demoPage.goToWellnessBank();
        assertThat(demoPage.verifyWBLoaded(), is(equalTo(T)));
    }
    @Test(groups = {"smoke"},description = "Verify user can navigate to 'On.Track' and Get Started button is present.")
    public void navigateToOnTrack() {
        demoPage.checkOnTrack();
        assertThat(demoPage.verifyOnTrack(), is(equalTo(T)));
    }
    @Test(groups = {"smoke"},description = "Verify user can navigate to 'On.Track Team' and no HTTP errors exist")
    public void navigateToOnTrackTeam() {
        demoPage.goToOnTrackTeam();
        assertThat(demoPage.verifyOnTrackTeamButton(), is(equalTo(T)));
    }
    @Test(groups = {"smoke"},description = "Verify user can navigate to On.Target Dashboard and Continue button navigates properly")
    public void navigateToOnTargetDashboard() {
        demoPage.goToOnTargetDashboard();
        assertThat(demoPage.verifyOnTargetDashboardButton(), is(equalTo(T)));
    }
    @Test(groups = {"smoke"},description = "Verify user can navigate to On.Target Your Target and Continue button navigates properly")
    public void navigateToOnTargetYourTarget() {
        demoPage.goToOnTargetYourTarget();
        assertThat(demoPage.verifyYourTargetEditButton(), is(equalTo(T)));
    }
    @Test(groups = {"smoke"},description = "Verify user can navigate to On.Target Achievements and Continue button navigates properly")
    public void navigateToOnTargetAchievement() {
        demoPage.goToOnTargetAchievements();
        assertThat(demoPage.verifyOnTargetAchievements(), is(equalTo(T)));
    }
    @Test(groups = {"smoke"},description = "Verify User can navigate to On.Call Coaching Overview Tab")
    public void navigateToOnCallOverview() {
        demoPage.goToOnCallOverview();
        assertThat(demoPage.verifyOnCallOverview(), is(equalTo(T)));
    }
    @Test(groups = {"smoke"},description = "Verify User can navigate to On.Call Online Tab")
    public void navigateToOnCallOnline() {
        demoPage.goToOnCallOnline();
        assertThat(demoPage.verifyOnCallContactNowButton(), is(equalTo(T)));
    }
    @Test(groups = {"smoke"},description = "Verify User can navigate to On.Call Onsite Tab")
    public void navigateToOnCallOnsite(){
        demoPage.goToOnCallOnsite();
        assertThat(demoPage.verifyScheduleNowOnCallOnSite(), is(equalTo(T)));
    }
    @Test(groups = {"smoke"},description = "Verify User can navigate to On.Call By Phone Tab.")
    public void navigateToOnCallPhone() {
        demoPage.goToOnCallPhone();
        assertThat(demoPage.verifyOnCallPhone(), is(equalTo(T)));
    }
    @Test(groups = {"smoke"},description = "Verify User can navigate to Biometric Page and Get Started is present.")
    public void navigateToBiometricScreening() {
        demoPage.goToBiometricScreening();
        assertThat(demoPage.verifyDownloadNowBioScreening(), is(equalTo(T)));
    }
    @Test(groups = {"smoke"},description = "Verify User can navigate to Health Manager Page and View History button is present.")
    public void navigateToHealthManager() {
        demoPage.goToHealthManager();
        assertThat(demoPage.verifyHistoryButtonHealthManager(), is(equalTo(T)));
    }
    @Test(groups = {"smoke"},description = "Navigate to App Manager and verify that Fitness block is visible.")
    public void navigateToAppManager() {
        demoPage.goToAppManager();
        assertThat(demoPage.verifyBlockInAppManager(), is(equalTo(T)));
    }
    @Test(groups = {"smoke"},description = "Navigate to LifeStyle Manager and verify that Steps box is visible.")
    public void navigateToLifestyleManger() {
        demoPage.goToLifestyleManger();
        assertThat(demoPage.verifyStepsLifestyleManager(), is(equalTo(T)));
    }
    @Test(groups = {"smoke"},description = "Navigate to Go Mobile page and verify that Android button is visible.")
    public void navigateToGoMobile() {
        demoPage.goToGoMobile();
        assertThat(demoPage.verifyMobilePage(), is(equalTo(T)));
    }
    @Test(groups = {"smoke"},description = "Navigate to Health Articles and verify Article Block is visible.")
    public void navigateToHealthArticles() {
        demoPage.goToHealthArticles();
        assertThat(demoPage.verifyHealthArticles(), is(equalTo(T)));
    }
    @Test(groups = {"smoke"},description = "Navigate to Financial Wellness section.")
    public void navigateToFinancialWellness() {
        demoPage.goToFinancialWellness();
        assertThat(demoPage.verifyFinancialWellness(), is(equalTo(T)));
    }
    @Test(groups = {"smoke"},description = "Navigate to Community section.")
    public void navigateToCommunity() {
        demoPage.goToCommunity();
        assertThat(demoPage.verifyCommunity(), is(equalTo(T)));
    }
    @Test(groups = {"smoke"},description = "Navigate to Wellness Resources section.")
    public void navigateToWellnessResources() {
        demoPage.goToWellnessResources();
        assertThat(demoPage.verifyWellnessResources(), is(equalTo(T)));
    }
    @Test(groups = {"smoke"},description = "Navigate to Wellness Champions section.")
    public void navigateToWellnessChampions() {
        demoPage.goToWellnessChampions();
        assertThat(demoPage.verifyWellnessChampions(), is(equalTo(T)));
    }
    @Test(groups = {"smoke"},description = "Navigate to Webinars section.")
    public void navigateToWebinars() {
        demoPage.goToWebinars();
        assertThat(demoPage.verifyWebinars(), is(equalTo(T)));
    }


}
