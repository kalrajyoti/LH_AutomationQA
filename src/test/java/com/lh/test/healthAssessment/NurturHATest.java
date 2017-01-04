package com.lh.test.healthAssessment;

import com.lh.dao.DaoLayer;
import com.lh.dao.DaoLayerImpl;
import com.lh.pages.authenticated.MainPage;
import com.lh.pages.authenticated.ha.*;
import com.lh.pages.unauthenticated.LogOutPage;
import com.lh.test.base.AuthBaseTestClass;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Map;

import static org.testng.Assert.assertTrue;

/**
 * Created by oleg.andreyev on 9/1/2016 for LiveHealthier-EnvolvePeopleCare
 * <p>
 * Summary - Client: BNSF
 */
@Listeners(com.lh.helper.LHTestListener.class)
public class NurturHATest extends AuthBaseTestClass{

    /**
     * Other Initiators
     */
    private Map<String, String> nurturHAData;
    private static Logger logger = LogManager.getLogger(NurturHATest.class);
    private MainPage mainPage;
    private HealthAssessmentPage hAPage;
    private NurturAboutYouHAPage aboutYouSection;
    private NurturHealthStatusHAPage healthStatusSection;
    private NurturTobaccoUseHAPage tobaccoUseSection;
    private NurturSubstancesHAPage substancesSection;
    private NurturActivityHAPage activitySection;
    private NurturDietHAPage dietSection;
    private NurturWellbeingHAPage wellbeingSection;
    private NurturConditionsHAPage conditionsSection;
    private NurturScreeningsHAPage screeningsSection;


    @BeforeClass
    public void initClass() {
        logger.info("...inside the initClass() method for NurturHATest Class...");
        nurturHAData = readexcelsheet("NurturHA");
        DaoLayer dao = new DaoLayerImpl();
        dao.resetNurturHAForUser(nurturHAData.get("testUser"));

        doLogin(nurturHAData.get("testUser"),nurturHAData.get("userPassword"),"Portal","Portal",nurturHAData.get("clientUrl"));

        mainPage = new MainPage();
        mainPage.clickHealthAssessment();
        hAPage = new HealthAssessmentPage();
        logger.info("Successfully proceeded initClass() method.");
    }

    @AfterClass(alwaysRun = true)
    public void tearDown(){
    	LogOutPage logoutPageObj = new LogOutPage();
    	logoutPageObj.clickLogoutLink("Portal");
    	setcurrentContext(null, null, false);
    	logoutPageObj = null;
        nurturHAData = null;
        mainPage.clickLogoutLink();
        mainPage = null;
        hAPage = null;

        aboutYouSection = null;
        healthStatusSection = null;
        tobaccoUseSection = null;
        substancesSection = null;
        activitySection = null;
        dietSection = null;
        wellbeingSection = null;
        conditionsSection = null;
        screeningsSection = null;
    }

    /**
     * TESTS BELOW
     */
    @Parameters({"gender"})
    @Test(priority = 1)
    public void completeAboutYouNurtur(@Optional("female")String gender){
        hAPage.clickTakeNowBtn();
        hAPage.giveConsent();
        aboutYouSection = new NurturAboutYouHAPage();
        assertTrue(hAPage.verifyOnAboutYou());
        aboutYouSection.selectGender(gender);//(nurturHAData.get("gender"));
        aboutYouSection.setEthnicity(nurturHAData.get("ethnicity"));
        aboutYouSection.setPreferredLanguage(nurturHAData.get("preferredLanguage"));
        aboutYouSection.setSpecialNeeds(nurturHAData.get("specialNeeds"));
        aboutYouSection.setDateOfBirth(nurturHAData.get("dob"));

        hAPage.clickNextBtn();
        assertTrue(hAPage.verifyOnHealthStatus());
        logger.info("....successfully completely About You for Nurtur HA");
    }
    @Test(priority = 2,dependsOnMethods = {"completeAboutYouNurtur"})
    public void completeHealthStatusNurtur(@Optional("female")String gender){
        healthStatusSection = new NurturHealthStatusHAPage();

        healthStatusSection.setHeight(nurturHAData.get("heightFt"),nurturHAData.get("heightIn"));
        healthStatusSection.setWeight(nurturHAData.get("weight"),nurturHAData.get("gender"));
        healthStatusSection.setWaistSize(nurturHAData.get("waistSize"),nurturHAData.get("gender"));
        healthStatusSection.setRestingHeartRate(nurturHAData.get("restingHeartRate"));
        healthStatusSection.setBloodPressure(nurturHAData.get("systolicBP"),nurturHAData.get("diastolicBP"));
        healthStatusSection.setTotalCholesterol(nurturHAData.get("totalCholesterol"));
        healthStatusSection.setHDLCholesterol(nurturHAData.get("hdl"));
        healthStatusSection.setLDLCholesterol(nurturHAData.get("ldl"));
        healthStatusSection.setFastingForTriglyceridesTest(nurturHAData.get("didYouFastTriglycerides"));
        healthStatusSection.setTriglycerides(nurturHAData.get("triglycerides"));
        healthStatusSection.setFastingForGlucoseTest(nurturHAData.get("didYouFastGlucose"));
        healthStatusSection.setGlucose(nurturHAData.get("glucose"));

        hAPage.clickNextBtn();
        assertTrue(hAPage.verifyOnTobaccoUse());
    }
    @Test(priority = 3,dependsOnMethods = {"completeHealthStatusNurtur"})
    public void completeTobaccoUseNurtur(){
        tobaccoUseSection = new NurturTobaccoUseHAPage();

        tobaccoUseSection.selectSmokingStatus(nurturHAData.get("smokingStatus"));
        tobaccoUseSection.setOtherTobaccoProductsUse(nurturHAData.get("otherTobaccoUse"));

        hAPage.clickNextBtn();
        assertTrue(hAPage.verifyOnSubstances());
    }
    @Test(priority = 4,dependsOnMethods = {"completeTobaccoUseNurtur"})
    public void completeSubstancesNurtur(){
        substancesSection = new NurturSubstancesHAPage();

        substancesSection.setDrinksInTypicalWeek(nurturHAData.get("alcoholPerWeek"));
        substancesSection.setPlanForModifyingAlcoholUse(nurturHAData.get("planForAlcohol"));

        hAPage.clickNextBtn();
        assertTrue(hAPage.verifyOnActivity());
    }
    @Test(priority = 5,dependsOnMethods = {"completeSubstancesNurtur"})
    public void completeActivityNurtur(){
        activitySection = new NurturActivityHAPage();

        activitySection.setTotalMinutesExercisePerWeek(nurturHAData.get("minsOfExercisePerWeek"));
        activitySection.setDuringExerciseExersionLevel(nurturHAData.get("exerciseDifficulty"));
        activitySection.setPlanForGettingMoreExercise(nurturHAData.get("exercisePlans"));

        hAPage.clickNextBtn();
        assertTrue(hAPage.verifyOnDiet());
    }
    @Test(priority = 6,dependsOnMethods = {"completeActivityNurtur"})
    public void completeDietNurtur(){
        dietSection = new NurturDietHAPage();

        dietSection.setServingsOfFruitDaily(nurturHAData.get("fruitsDaily"));
        dietSection.setServingsOfVegetablesDaily(nurturHAData.get("vegetablesDaily"));
        dietSection.setServingsOfWholeGrainsDaily(nurturHAData.get("wholeGrainsDaily"));

        dietSection.setHighSaturatedFatFoodsIntake(nurturHAData.get("saturatedFatIntake"));
        dietSection.setPlansForEatingHealthier(nurturHAData.get("planForEatingHealthier"));
        dietSection.setPlansForLosingWeight(nurturHAData.get("planForLosingWeight"));

        hAPage.clickNextBtn();
        assertTrue(hAPage.verifyOnWellbeing());
    }

    @Test(priority = 7,dependsOnMethods = {"completeDietNurtur"})
    public void completeWellbeingNurtur(){
        wellbeingSection = new NurturWellbeingHAPage();

        wellbeingSection.setHoursOfSleepDaily(nurturHAData.get("sleepPerDay"));
        wellbeingSection.setFeltUnableToControlImportantThingsQ(nurturHAData.get("feltUnableToControl"));
        wellbeingSection.setFeltConfidentPersonalProblemsQ(nurturHAData.get("FeltConfidentAboutPersonalProblems"));
        wellbeingSection.setFeltThingsGoingYourWayQ(nurturHAData.get("FeltThingsGoingYourWay"));
        wellbeingSection.setFeltDifficultiesPilingUpHighQ(nurturHAData.get("FeltDifficultiesPilingUp"));
        wellbeingSection.setPlansForManagingStress(nurturHAData.get("PlansForStress"));
        wellbeingSection.setFeelingDownDepressedHopeless(nurturHAData.get("BotheredByDepression"));
        wellbeingSection.setLittleInterestPleasure(nurturHAData.get("BotheredByLittleInterest"));

        hAPage.clickNextBtn();
        assertTrue(hAPage.verifyOnConditions());
    }
    @Test(priority = 8,dependsOnMethods = {"completeWellbeingNurtur"})
    public void completeConditionsNurtur(){
        conditionsSection = new NurturConditionsHAPage();

        conditionsSection.setDoYouHaveDoctorOrProvider(nurturHAData.get("HaveDoctor"));
        conditionsSection.setHaveYouSeenDoctorTwelveMonths(nurturHAData.get("SeenDoctor"));
        conditionsSection.setHowWouldYouRateYourHealth(nurturHAData.get("RateYourHealth"));
        conditionsSection.setElectiveSurgeryDiscussion(nurturHAData.get("ElectiveSurgeryDiscussion"));
        conditionsSection.setAnyAboveConditionsChoices(nurturHAData.get("AnyConditionsAbove"));
        conditionsSection.setDailyAspirinIntake(nurturHAData.get("DailyAspirin"));
        conditionsSection.setDailyCholesterolMedsIntake(nurturHAData.get("DailyCholesterolMeds"));
        conditionsSection.setDailyBloodPressureMedsIntake(nurturHAData.get("DailyBPMeds"));

        hAPage.clickNextBtn();
        assertTrue(hAPage.verifyOnScreenings());
    }
    @Test(priority = 9,dependsOnMethods = {"completeConditionsNurtur"})
    public void completeScreeningsNurtur(){
        screeningsSection = new NurturScreeningsHAPage();

        screeningsSection.setGottenFluShot(nurturHAData.get("GotFluShot"));
        screeningsSection.setHadPapSmear(nurturHAData.get("HadPapSmear"));
        screeningsSection.setColonCancerScreening(nurturHAData.get("ColonCancerScreen"));
        screeningsSection.setMammogramCheck(nurturHAData.get("MammogramScreen"));

        hAPage.clickNextBtn();
        screeningsSection.answerFemaleQuestionsIfApplicable(nurturHAData.get("gender"));
    }
    @Test(description = "Verify Nurtur HA Completed, TCID :- C5881",priority = 10,dependsOnMethods = {"completeScreeningsNurtur"})
    public void verifyNurturHACompleted(){
        assertTrue(hAPage.verifyHealthAssessmentCompleted());
        assertTrue(hAPage.verifyHealthReportGenerated());
    }


}
