package com.lh.test.healthAssessment;

import com.lh.dao.DaoLayer;
import com.lh.dao.DaoLayerImpl;
import com.lh.pages.authenticated.MainPage;
import com.lh.pages.authenticated.ha.*;
import com.lh.test.base.AuthBaseTestClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.*;

import java.util.Map;

import static org.testng.Assert.assertTrue;

/**
 * Created by oleg.andreyev on 11/2/2016 for LiveHealthier-EnvolvePeopleCare
 * <p>
 * Summary - Ambetter Client.  MHP HA Configuration
 */
@Listeners(com.lh.helper.LHTestListener.class)
public class MHPTest extends AuthBaseTestClass {

    private static Logger logger = LogManager.getLogger(NurturHATest.class);

    /**
     * Initiation of Class
     */
    private Map<String, String> MHPData;
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
    private MHPDependentsPage dependentsPage;



    @BeforeClass
    public void initClass() {
        logger.info("...inside the initClass() method.");
        MHPData = readexcelsheet("MHPHA");
        DaoLayer dao = new DaoLayerImpl();
        dao.resetNurturHAForUser(MHPData.get("testUser"));

        doLogin(MHPData.get("testUser"), MHPData.get("userPassword"), "Portal", "Portal", MHPData.get("clientUrl"));

        mainPage = new MainPage();
        //mainPage.checkIfEmailVerificationRequested(MHPData.get("emailIfRequiredToVerify"));
        //mainPage.checkIfWelcomeDialogAppears();
        mainPage.clickHealthAssessmentFromTheProgram();
        hAPage = new HealthAssessmentPage();
        logger.info("Successfully proceeded initClass() method.");
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        MHPData = null;
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
        dependentsPage = null;
    }

    /**
     * TESTS BELOW
     */
    @Test(priority = 1)
    public void completeAboutYouMHP() {
        hAPage.clickTakeNowBtn();
        hAPage.giveConsent();
        aboutYouSection = new NurturAboutYouHAPage();
        assertTrue(hAPage.verifyOnAboutYou());

        aboutYouSection.selectGender(MHPData.get("gender"));
        aboutYouSection.setEthnicity(MHPData.get("ethnicity"));
        aboutYouSection.setPreferredLanguage(MHPData.get("preferredLanguage"));
        aboutYouSection.setSpecialNeeds(MHPData.get("specialNeeds"));
        aboutYouSection.setDateOfBirth(MHPData.get("dob"));

        hAPage.clickNextBtn();
        assertTrue(hAPage.verifyOnHealthStatus());

    }

    @Test(priority = 2,dependsOnMethods = {"completeAboutYouMHP"})
    public void completeHealthStatusMHP(){
        healthStatusSection = new NurturHealthStatusHAPage();

        healthStatusSection.setHeight(MHPData.get("heightFt"),MHPData.get("heightIn"));
        healthStatusSection.setWeight(MHPData.get("weight"),MHPData.get("gender"));
        healthStatusSection.setWaistSize(MHPData.get("waistSize"),MHPData.get("gender"));
        healthStatusSection.setRestingHeartRate(MHPData.get("restingHeartRate"));
        healthStatusSection.setBloodPressure(MHPData.get("systolicBP"),MHPData.get("diastolicBP"));
        healthStatusSection.setTotalCholesterol(MHPData.get("totalCholesterol"));
        healthStatusSection.setHDLCholesterol(MHPData.get("hdl"));
        healthStatusSection.setLDLCholesterol(MHPData.get("ldl"));
        healthStatusSection.setFastingForTriglyceridesTest(MHPData.get("didYouFastTriglycerides"));
        healthStatusSection.setTriglycerides(MHPData.get("triglycerides"));
        healthStatusSection.setFastingForGlucoseTest(MHPData.get("didYouFastGlucose"));
        healthStatusSection.setGlucose(MHPData.get("glucose"));

        hAPage.clickNextBtn();
        assertTrue(hAPage.verifyOnTobaccoUse());
    }

    @Test(priority = 3,dependsOnMethods = {"completeHealthStatusMHP"})
    public void completeTobaccoUseMHP(){
        tobaccoUseSection = new NurturTobaccoUseHAPage();

        tobaccoUseSection.selectSmokingStatus(MHPData.get("smokingStatus"));
        tobaccoUseSection.setOtherTobaccoProductsUse(MHPData.get("otherTobaccoUse"));

        hAPage.clickNextBtn();
        assertTrue(hAPage.verifyOnSubstances());
    }
    @Test(priority = 4,dependsOnMethods = {"completeTobaccoUseMHP"})
    public void completeSubstancesMHP(){
        substancesSection = new NurturSubstancesHAPage();

        substancesSection.setDrinksInTypicalWeek(MHPData.get("alcoholPerWeek"));
        substancesSection.setPlanForModifyingAlcoholUse(MHPData.get("planForAlcohol"));

        hAPage.clickNextBtn();
        assertTrue(hAPage.verifyOnActivity());
    }
    @Test(priority = 5,dependsOnMethods = {"completeSubstancesMHP"})
    public void completeActivityMHP(){
        activitySection = new NurturActivityHAPage();

        activitySection.setTotalMinutesExercisePerWeek(MHPData.get("minsOfExercisePerWeek"));
        activitySection.setDuringExerciseExersionLevel(MHPData.get("exerciseDifficulty"));
        activitySection.setPlanForGettingMoreExercise(MHPData.get("exercisePlans"));

        hAPage.clickNextBtn();
        assertTrue(hAPage.verifyOnDiet());
    }
    @Test(priority = 6,dependsOnMethods = {"completeActivityMHP"})
    public void completeDietMHP(){
        dietSection = new NurturDietHAPage();

        dietSection.setServingsOfFruitDaily(MHPData.get("fruitsDaily"));
        dietSection.setServingsOfVegetablesDaily(MHPData.get("vegetablesDaily"));
        dietSection.setServingsOfWholeGrainsDaily(MHPData.get("wholeGrainsDaily"));

        dietSection.setHighSaturatedFatFoodsIntake(MHPData.get("saturatedFatIntake"));
        dietSection.setPlansForEatingHealthier(MHPData.get("planForEatingHealthier"));
        dietSection.setPlansForLosingWeight(MHPData.get("planForLosingWeight"));

        hAPage.clickNextBtn();
        assertTrue(hAPage.verifyOnWellbeing());
    }

    @Test(priority = 7,dependsOnMethods = {"completeDietMHP"})
    public void completeWellbeingMHP(){
        wellbeingSection = new NurturWellbeingHAPage();

        wellbeingSection.setHoursOfSleepDaily(MHPData.get("sleepPerDay"));
        wellbeingSection.setFeltUnableToControlImportantThingsQ(MHPData.get("feltUnableToControl"));
        wellbeingSection.setFeltConfidentPersonalProblemsQ(MHPData.get("FeltConfidentAboutPersonalProblems"));
        wellbeingSection.setFeltThingsGoingYourWayQ(MHPData.get("FeltThingsGoingYourWay"));
        wellbeingSection.setFeltDifficultiesPilingUpHighQ(MHPData.get("FeltDifficultiesPilingUp"));
        wellbeingSection.setPlansForManagingStress(MHPData.get("PlansForStress"));
        wellbeingSection.setFeelingDownDepressedHopeless(MHPData.get("BotheredByDepression"));
        wellbeingSection.setLittleInterestPleasure(MHPData.get("BotheredByLittleInterest"));

        wellbeingSection.setEmploymentStateAndRelatedQuestions(MHPData.get("currentEmploymentStatus"),MHPData.get("hoursMissedDueToHealthProblemsField"),
                MHPData.get("hoursMissedForOtherReasonField"),MHPData.get("hoursActuallyWorkedField"),MHPData.get("healthaffectingWorkProductivity"));
        wellbeingSection.setHealthProblemsImpactingRegularActivities(MHPData.get("healthaffectingRegularActivity"));

        hAPage.clickNextBtn();
        assertTrue(hAPage.verifyOnConditions());
    }
    @Test(priority = 8,dependsOnMethods = {"completeWellbeingMHP"})
    public void completeConditionsMHP(){
        conditionsSection = new NurturConditionsHAPage();

        conditionsSection.setDoYouHaveDoctorOrProvider(MHPData.get("HaveDoctor"));
        conditionsSection.setHaveYouSeenDoctorTwelveMonths(MHPData.get("SeenDoctor"));
        conditionsSection.setHowWouldYouRateYourHealth(MHPData.get("RateYourHealth"));
        conditionsSection.setElectiveSurgeryDiscussion(MHPData.get("ElectiveSurgeryDiscussion"));
        conditionsSection.setAnyAboveConditionsChoices(MHPData.get("AnyConditionsAbove"));
        conditionsSection.setDailyAspirinIntake(MHPData.get("DailyAspirin"));
        conditionsSection.setDailyCholesterolMedsIntake(MHPData.get("DailyCholesterolMeds"));
        conditionsSection.setDailyBloodPressureMedsIntake(MHPData.get("DailyBPMeds"));

        hAPage.clickNextBtn();
        assertTrue(hAPage.verifyOnScreenings());
    }
    @Test(priority = 9,dependsOnMethods = {"completeConditionsMHP"})
    public void completeScreeningsMHP(){
        screeningsSection = new NurturScreeningsHAPage();

        screeningsSection.setGottenFluShot(MHPData.get("GotFluShot"));
        screeningsSection.setHadPapSmear(MHPData.get("HadPapSmear"));
        screeningsSection.setColonCancerScreening(MHPData.get("ColonCancerScreen"));
        screeningsSection.setMammogramCheck(MHPData.get("MammogramScreen"));

        hAPage.clickNextBtn();
        screeningsSection.answerFemaleQuestionsIfApplicable(MHPData.get("gender"));
    }

    @Test(priority = 10, dependsOnMethods = {"completeScreeningsMHP"})
    public void completeDependentsSection(){
        dependentsPage = new MHPDependentsPage();

        dependentsPage.areYouAParentOrGuardian(MHPData.get("areYouAParentOrLegalGuardian"),MHPData.get("dependentFirstName"),MHPData.get("dependentLastName"),MHPData.get("dependentDateOfBirth")
                ,MHPData.get("relationshipToChild"),MHPData.get("dependentHeightFeet"),MHPData.get("dependentHeightInches"),MHPData.get("dependentWeightPounds"),MHPData.get("dependentWeightOunces")
                ,MHPData.get("dependentUpToDateOnImmunizations"));

        hAPage.clickNextBtn();
    }
    @Test(description = "Verify MHP HA Completed",priority = 11,dependsOnMethods = {"completeDependentsSection"})
    public void verifyMHPHACompleted(){
        assertTrue(hAPage.verifyHealthAssessmentCompleted());
        assertTrue(hAPage.verifyHealthReportGenerated());
    }




}
