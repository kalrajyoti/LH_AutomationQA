package com.lh.test.healthAssessment;

import com.lh.dao.DaoLayer;
import com.lh.dao.DaoLayerImpl;
import com.lh.pages.authenticated.MainPage;
import com.lh.pages.authenticated.ha.*;
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
 * Created by pranali.jain on 10/17/2016 for LiveHealthier-EnvolvePeopleCare
 * <p>
 * Summary - Client: Exelis
 */
@Listeners(com.lh.helper.LHTestListener.class)
public class NurturEPCFinancialTest extends AuthBaseTestClass{

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
    private NurturWellbeingEPCFinancialPage wellbeingSection;
    private NurturConditionsHAPage conditionsSection;
    private NurturScreeningsHAPage screeningsSection;
    
    @BeforeClass
    public void initClass() {
        logger.info("...inside the initClass() method for NurturHATest Class...");
        nurturHAData = readexcelsheet("NurturHA");
        DaoLayer dao = new DaoLayerImpl();
        dao.resetNurturHAForUser(nurturHAData.get("testUserEPC"));

        doLogin(nurturHAData.get("testUserEPC"),nurturHAData.get("userPassword"),"Portal","Portal",nurturHAData.get("clientUrlEPC"));

        mainPage = new MainPage();
        mainPage.clickHealthAssessment();
        hAPage = new HealthAssessmentPage();
        logger.info("Successfully proceeded initClass() method.");
    }

    @AfterClass(alwaysRun = true)
    public void tearDown(){
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
    @Test(priority = 1, alwaysRun = true)
    public void completeAboutYouNurtur(@Optional("female")String gender){
        hAPage.clickTakeNowBtn();
        hAPage.giveConsent();
        aboutYouSection = new NurturAboutYouHAPage();
        assertTrue(hAPage.verifyOnAboutYou());
        aboutYouSection.selectGender(gender);
        aboutYouSection.setEthnicity(nurturHAData.get("ethnicity"));
        aboutYouSection.setPreferredLanguage(nurturHAData.get("preferredLanguage"));
        aboutYouSection.setSpecialNeeds(nurturHAData.get("specialNeeds"));
        aboutYouSection.setDateOfBirth(nurturHAData.get("dob"));

        hAPage.clickNextBtn();
        assertTrue(hAPage.verifyOnHealthStatus());
        logger.info("....successfully completely About You for Nurtur HA");
    }
    
    @Parameters({"gender"})
    @Test(priority = 2,dependsOnMethods = {"completeAboutYouNurtur"}, alwaysRun = true)
    public void completeHealthStatusNurtur(@Optional("female")String gender){
        healthStatusSection = new NurturHealthStatusHAPage();

        healthStatusSection.setHeight(nurturHAData.get("heightFt"),nurturHAData.get("heightIn"));
        healthStatusSection.setWeight(nurturHAData.get("weight"),gender);
        healthStatusSection.setWaistSize(nurturHAData.get("waistSize"),gender);
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
    
    @Test(priority = 3,dependsOnMethods = {"completeHealthStatusNurtur"}, alwaysRun = true)
    public void completeTobaccoUseNurtur(){
        tobaccoUseSection = new NurturTobaccoUseHAPage();

        tobaccoUseSection.selectSmokingStatus(nurturHAData.get("smokingStatus"));
        tobaccoUseSection.setOtherTobaccoProductsUse(nurturHAData.get("otherTobaccoUse"));

        hAPage.clickNextBtn();
        assertTrue(hAPage.verifyOnSubstances());
    }
    
    @Test(priority = 4,dependsOnMethods = {"completeTobaccoUseNurtur"}, alwaysRun = true)
    public void completeSubstancesNurtur(){
        substancesSection = new NurturSubstancesHAPage();

        substancesSection.setDrinksInTypicalWeek(nurturHAData.get("alcoholPerWeek"));
        substancesSection.setPlanForModifyingAlcoholUse(nurturHAData.get("planForAlcohol"));

        hAPage.clickNextBtn();
        assertTrue(hAPage.verifyOnActivity());
    }
    
    @Test(priority = 5,dependsOnMethods = {"completeSubstancesNurtur"}, alwaysRun = true)
    public void completeActivityNurtur(){
        activitySection = new NurturActivityHAPage();

        activitySection.setTotalMinutesExercisePerWeek(nurturHAData.get("minsOfExercisePerWeek"));
        activitySection.setDuringExerciseExersionLevel(nurturHAData.get("exerciseDifficulty"));
        activitySection.setPlanForGettingMoreExercise(nurturHAData.get("exercisePlans"));

        hAPage.clickNextBtn();
        assertTrue(hAPage.verifyOnDiet());
    }
    
    @Test(priority = 6,dependsOnMethods = {"completeActivityNurtur"}, alwaysRun = true)
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

    @Test(priority = 7,dependsOnMethods = {"completeDietNurtur"}, alwaysRun = true)
    public void completeWellbeingNurtur(){
        wellbeingSection = new NurturWellbeingEPCFinancialPage();
        
        wellbeingSection.setPersonalFinances(nurturHAData.get("personalFinance"));
        wellbeingSection.setPlansForAddressingFinance(nurturHAData.get("planToAddressFinance"));
        wellbeingSection.setHoursOfSleepDaily(nurturHAData.get("sleepPerDay"));
        wellbeingSection.setFeltUnableToControlImportantThingsQ(nurturHAData.get("feltUnableToControl"));
        wellbeingSection.setFeltConfidentPersonalProblemsQ(nurturHAData.get("FeltConfidentAboutPersonalProblems"));
        wellbeingSection.setFeltThingsGoingYourWayQ(nurturHAData.get("FeltThingsGoingYourWay"));
        wellbeingSection.setFeltDifficultiesPilingUpHighQ(nurturHAData.get("FeltDifficultiesPilingUp"));
        wellbeingSection.setPlansForManagingStress(nurturHAData.get("PlansForStress"));
        wellbeingSection.setFeelingDownDepressedHopeless(nurturHAData.get("BotheredByDepression"));
        wellbeingSection.setLittleInterestPleasure(nurturHAData.get("BotheredByLittleInterest"));
        wellbeingSection.setCurrentlyEmployedStatus(nurturHAData.get("currentEmploymentStatus"));
        wellbeingSection.setHealthProblemAffectingRegularActivity(nurturHAData.get("healthaffectingRegularActivity"));

        hAPage.clickNextBtn();
        assertTrue(hAPage.verifyOnConditions());
    }
    
    @Test(priority = 8,dependsOnMethods = {"completeWellbeingNurtur"}, alwaysRun = true)
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
    
    @Parameters({"gender"})
    @Test(priority = 9,dependsOnMethods = {"completeConditionsNurtur"}, alwaysRun = true)
    public void completeScreeningsNurtur(@Optional("female")String gender){
        screeningsSection = new NurturScreeningsHAPage();

        screeningsSection.setGottenFluShot(nurturHAData.get("GotFluShot"));
        screeningsSection.setHadPapSmear(nurturHAData.get("HadPapSmear"));
        screeningsSection.setColonCancerScreening(nurturHAData.get("ColonCancerScreen"));
        screeningsSection.setMammogramCheck(nurturHAData.get("MammogramScreen"));

        hAPage.clickNextBtn();
        screeningsSection.answerFemaleQuestionsIfApplicable(gender);
    }
    
    @Test(description = "Verify Nurtur HA Completed, TCID :- C111417",priority = 10,dependsOnMethods = {"completeScreeningsNurtur"}, alwaysRun = true)
    public void verifyNurturHACompleted(){
        assertTrue(hAPage.verifyHealthAssessmentCompleted());
        assertTrue(hAPage.verifyHealthReportGenerated());
    }

}
