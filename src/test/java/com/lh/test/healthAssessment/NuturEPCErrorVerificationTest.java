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

public class NuturEPCErrorVerificationTest extends AuthBaseTestClass {

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

	@AfterClass
	public void tearDown(){
		nurturHAData = null;
		mainPage = null;
		hAPage = null;

		aboutYouSection = null;
		healthStatusSection = null;
		tobaccoUseSection = null;
		substancesSection = null;
		activitySection = null;
		dietSection = null;
		wellbeingSection = null;
	}

	/**
	 * TESTS BELOW
	 */
	@Parameters({"gender"})
	@Test(description = "TCID: C111420",priority = 1)
	public void completeAboutYouNurtur(@Optional("female")String gender){
		hAPage.clickTakeNowBtn();
		hAPage.giveConsent();
		aboutYouSection = new NurturAboutYouHAPage();
		assertTrue(hAPage.verifyOnAboutYou());
		hAPage.clickNextBtn();
		assertTrue(hAPage.verifyListOfErrorMessage(nurturHAData.get("noOfErrorForUnfilledFieldsinEPC_AboutYou")));
		aboutYouSection.selectGender(gender);
		aboutYouSection.setEthnicity(nurturHAData.get("ethnicity"));
		aboutYouSection.setPreferredLanguage(nurturHAData.get("preferredLanguage"));
		//        aboutYouSection.setDateOfBirth(nurturHAData.get("dob"));
		aboutYouSection.setSpecialNeeds(nurturHAData.get("specialNeeds"));
		aboutYouSection.setDateOfBirth(nurturHAData.get("dob"));

		hAPage.clickNextBtn();
		assertTrue(hAPage.verifyOnHealthStatus());
		logger.info("....successfully completely About You for Nurtur HA");
	}

	@Parameters({"gender"})
	@Test(description = "TCID: C111427", priority = 2,dependsOnMethods = {"completeAboutYouNurtur"})
	public void verifyIncompleteHealthStatusPanel(@Optional("female")String gender){    	 
		healthStatusSection = new NurturHealthStatusHAPage();
		hAPage.clickNextBtn();
		assertTrue(hAPage.verifyListOfErrorMessage(nurturHAData.get("noOfErrorForUnfilledFieldsinEPC_HealthStatus")));
	}
	
	@Parameters({"gender"})
	@Test(description = "TCID: C111428", priority = 3,dependsOnMethods = {"verifyIncompleteHealthStatusPanel"})
	public void completeHealthStatusNurtur(@Optional("female")String gender){    	 
		healthStatusSection = new NurturHealthStatusHAPage();
		healthStatusSection.setHeight(nurturHAData.get("incorrectHeightFt"),nurturHAData.get("incorrectHeightIn"));
		healthStatusSection.setWeight(nurturHAData.get("incorrectWeight"),gender);
		healthStatusSection.setWaistSize(nurturHAData.get("incorrectWaistSize"),gender);
		healthStatusSection.setRestingHeartRate(nurturHAData.get("restingHeartRate"));
		healthStatusSection.setBloodPressure(nurturHAData.get("incorrectSystolicBP"),nurturHAData.get("incorrectDiastolicBP"));
		healthStatusSection.setTotalCholesterol(nurturHAData.get("totalCholesterol"));
		healthStatusSection.setHDLCholesterol(nurturHAData.get("hdl"));
		healthStatusSection.setLDLCholesterol(nurturHAData.get("ldl"));
		healthStatusSection.setFastingForTriglyceridesTest(nurturHAData.get("didYouFastTriglycerides"));
		healthStatusSection.setTriglycerides(nurturHAData.get("triglycerides"));
		healthStatusSection.setFastingForGlucoseTest(nurturHAData.get("didYouFastGlucose"));
		healthStatusSection.setGlucose(nurturHAData.get("glucose"));
		hAPage.clickNextBtn();
		healthStatusSection.verifyListOfDecimalError(nurturHAData.get("noOfDecimalErrorInEPC"));
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

	@Test(description = "TCID: C111429", priority = 4,dependsOnMethods = {"completeHealthStatusNurtur"})
	public void completeTobaccoUseNurtur(){
		tobaccoUseSection = new NurturTobaccoUseHAPage();
		hAPage.clickNextBtn();
		assertTrue(hAPage.verifyListOfErrorMessage(nurturHAData.get("noOfErrorForUnfilledFieldsinEPC_Tobacco")));
		tobaccoUseSection.selectSmokingStatus(nurturHAData.get("smokingStatus"));
		tobaccoUseSection.setOtherTobaccoProductsUse(nurturHAData.get("otherTobaccoUse"));

		hAPage.clickNextBtn();
		assertTrue(hAPage.verifyOnSubstances());
	}

	@Test(priority = 5,dependsOnMethods = {"completeTobaccoUseNurtur"})
	public void completeSubstancesNurtur(){
		substancesSection = new NurturSubstancesHAPage();
		hAPage.clickNextBtn();
		assertTrue(hAPage.verifyListOfErrorMessage(nurturHAData.get("noOfErrorForUnfilledFieldsinEPC_Substances")));
		substancesSection.setDrinksInTypicalWeek(nurturHAData.get("alcoholPerWeek"));
		substancesSection.setPlanForModifyingAlcoholUse(nurturHAData.get("planForAlcohol"));

		hAPage.clickNextBtn();
		assertTrue(hAPage.verifyOnActivity());
	}

	@Test(priority = 6,dependsOnMethods = {"completeSubstancesNurtur"})
	public void completeActivityNurtur(){
		activitySection = new NurturActivityHAPage();
		hAPage.clickNextBtn();
		assertTrue(hAPage.verifyListOfErrorMessage(nurturHAData.get("noOfErrorForUnfilledFieldsinEPC_Activity")));
		activitySection.setTotalMinutesExercisePerWeek(nurturHAData.get("minsOfExercisePerWeek"));
		activitySection.setDuringExerciseExersionLevel(nurturHAData.get("exerciseDifficulty"));
		activitySection.setPlanForGettingMoreExercise(nurturHAData.get("exercisePlans"));

		hAPage.clickNextBtn();
		assertTrue(hAPage.verifyOnDiet());
	}

	@Test(priority = 7,dependsOnMethods = {"completeActivityNurtur"})
	public void completeDietNurtur(){
		dietSection = new NurturDietHAPage();
		hAPage.clickNextBtn();
		assertTrue(hAPage.verifyListOfErrorMessage(nurturHAData.get("noOfErrorForUnfilledFieldsinEPC_Diet")));
		dietSection.setServingsOfFruitDaily(nurturHAData.get("fruitsDaily"));
		dietSection.setServingsOfVegetablesDaily(nurturHAData.get("vegetablesDaily"));
		dietSection.setServingsOfWholeGrainsDaily(nurturHAData.get("wholeGrainsDaily"));

		dietSection.setHighSaturatedFatFoodsIntake(nurturHAData.get("saturatedFatIntake"));
		dietSection.setPlansForEatingHealthier(nurturHAData.get("planForEatingHealthier"));
		dietSection.setPlansForLosingWeight(nurturHAData.get("planForLosingWeight"));

		hAPage.clickNextBtn();
		assertTrue(hAPage.verifyOnWellbeing());
	}

	@Test(priority = 8,dependsOnMethods = {"completeDietNurtur"})
	public void completeWellbeingNurtur(){
		wellbeingSection = new NurturWellbeingEPCFinancialPage();
		hAPage.clickNextBtn();
		assertTrue(hAPage.verifyListOfErrorMessage(nurturHAData.get("noOfErrorForUnfilledFieldsinEPC_Wellbeing")));
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

	@Test(priority = 9,dependsOnMethods = {"completeWellbeingNurtur"})
	public void completeConditionsNurtur(){
		conditionsSection = new NurturConditionsHAPage();
		hAPage.clickNextBtn();
		assertTrue(hAPage.verifyListOfErrorMessage(nurturHAData.get("noOfErrorForUnfilledFieldsinEPC_Conditions")));
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
	@Test(priority = 10,dependsOnMethods = {"completeConditionsNurtur"})
	public void completeScreeningsNurtur(@Optional("female")String gender){
		screeningsSection = new NurturScreeningsHAPage();

		screeningsSection.setGottenFluShot(nurturHAData.get("GotFluShot"));
		screeningsSection.setHadPapSmear(nurturHAData.get("HadPapSmear"));
		screeningsSection.setColonCancerScreening(nurturHAData.get("ColonCancerScreen"));
		screeningsSection.setMammogramCheck(nurturHAData.get("MammogramScreen"));

		hAPage.clickNextBtn();
		screeningsSection.answerFemaleQuestionsIfApplicable(gender);
	}

	@Test(description = "Verify Nurtur HA Completed, TCID :- C111417",priority = 11,dependsOnMethods = {"completeScreeningsNurtur"})
	public void verifyNurturHACompleted(){
		assertTrue(hAPage.verifyHealthAssessmentCompleted());
		assertTrue(hAPage.verifyHealthReportGenerated());
	}
}
