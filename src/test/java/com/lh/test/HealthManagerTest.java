package com.lh.test;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import com.lh.pages.authenticated.MainPage;
import com.lh.pages.authenticated.hm.BMIPage;
import com.lh.pages.authenticated.hm.BloodPressurePage;
import com.lh.pages.authenticated.hm.BodyCompositionPage;
import com.lh.pages.authenticated.hm.CholesterolPage;
import com.lh.pages.authenticated.hm.GlucosePage;
import com.lh.pages.authenticated.hm.PulseRatePage;
import com.lh.pages.authenticated.hm.WeightPage;
import com.lh.test.base.AuthBaseTestClass;
import com.lh.utils.UtilityMethods;

@Listeners(com.lh.helper.LHTestListener.class)
public class HealthManagerTest extends AuthBaseTestClass {

	private static Logger logger = LogManager.getLogger(IndexTest.class);
	/** Assertion to verify different elements of the page. */

	
	private MainPage mainPageObj;
	private BloodPressurePage bpPageObject;
	private BMIPage bmiPageObject;
	private BodyCompositionPage bodyCompobject;
	private CholesterolPage cholesterolObject;
	private GlucosePage glucoseObject;
	private PulseRatePage pulsePageObject;
	private WeightPage weightPageObject;
	private static final String VALID_PASSWORD = "validPassword";
	private static final String VALID_USERNAME = "validUsername";
	private Assertion columnAssert = new Assertion();
	private String BLOOD_PRESSURE = "bloodPressureTxt";
	private String BMI = "BMITxt";
	private String Body_Comp = "bodyCompositionTxt";
	private String Cholesterol = "cholesterolTxt";
	private String Glucose = "glucoseTxt";
	private String Pulse_Rate = "pulseRateTxt";
	private String Weight = "weightTxt";
	private String Wellness = "wellnessTxt";
	private String date = "date";
	private String systolic = "systolicData";
	private String diastolic = "diastolicData";
	private String textBoxBP = "textBoxAreaBP";
	private String newsystolic = "newSystolicData";
	private String newdiastolic = "newDiastolicData";
	private String bMIValue = "BMI";
	private String newBMIValue = "newBMI";
	private String bmiNotes = "BMINotes";
	private String waistSize = "waistSize";
	private String bodyFat = "bodyFat";
	private String bodyCompNotes = "bodyCompNotes";
	private String newWaistSize = "newWaistSize";
	private String newbodyFat = "newbodyFat";
	private String chlolesterol = "chlolesterol";
	private String ldl = "ldl";
	private String hdl = "hdl";
	private String triglycerides = "triglycerides";
	private String cholesterolNotes = "cholesterolNotes";
	private String newCholest = "newCholest";
	private String newldl = "newldl";
	private String newhdl = "newhdl";
	private String newGlyc = "newGlyc";
	private String fastingTxt = "fastingVal";
	private String nonFastingTxt = "nonfastingVal";
	private String newfastingVal = "newfastingVal";
	private String newnonfastingVal = "newnonfastingVal";
	private String pulseRate = "pulseRate";
	private String newPulseRate = "newPulseRate";
	private String weightVal = "weightVal";
	private String newWeightVal = "newWeightVal";
	private Map<String, String> healthManagerData;
	public static final String HEALTH_MANAGER = "healthManagerText";
	private String partialHealthManager_URL = "MemberServices/health/ScreeningGraphPage.aspx";	
	
	@BeforeClass
	public void initClass() throws Exception {

		logger.info("Inside the initClass() method for HealthManagerTest class...");
		healthManagerData = readexcelsheet("HealthManager");
		doLogin(healthManagerData.get(VALID_USERNAME), healthManagerData.get(VALID_PASSWORD),"Portal","Portal");
		mainPageObj = new MainPage();
		bpPageObject = new BloodPressurePage();
		bmiPageObject = new BMIPage();
		bodyCompobject = new BodyCompositionPage();
		glucoseObject = new GlucosePage();
		cholesterolObject = new CholesterolPage();
		pulsePageObject = new PulseRatePage();
		weightPageObject = new WeightPage();
		mainPageObj.clickHealthManager();
		mainPageObj.verifyURL(partialHealthManager_URL);
		logger.info("Exiting the initClass() method for HealthManagerTest class \n\n");
	}

	
	
	/**
	 * This method asserts the left column links
	 * @throws Throwable
	 */

	@Test(description="Validate the left column items",groups = {"regression"}) 
	public void validateLeftColumnText() throws Throwable{
		mainPageObj.clickHealthManager();
		mainPageObj.verifyURL(partialHealthManager_URL);
		columnAssert.assertTrue(bpPageObject.verifyHealthManagerLeftColumnLinks(healthManagerData.get(BLOOD_PRESSURE))); 
		columnAssert.assertTrue(bpPageObject.verifyHealthManagerLeftColumnLinks(healthManagerData.get(BMI)));
		columnAssert.assertTrue(bpPageObject.verifyHealthManagerLeftColumnLinks(healthManagerData.get(Body_Comp)));
		columnAssert.assertTrue(bpPageObject.verifyHealthManagerLeftColumnLinks(healthManagerData.get(Cholesterol))); 
		columnAssert.assertTrue(bpPageObject.verifyHealthManagerLeftColumnLinks(healthManagerData.get(Glucose))); 
		columnAssert.assertTrue(bpPageObject.verifyHealthManagerLeftColumnLinks(healthManagerData.get(Pulse_Rate)));
		columnAssert.assertTrue(bpPageObject.verifyHealthManagerLeftColumnLinks(healthManagerData.get(Weight)));
		columnAssert.assertTrue(bpPageObject.verifyHealthManagerLeftColumnLinks(healthManagerData.get(Wellness)));
		
	}

	/**
	 * This methiod asserts the addition, edit and deletion of Blood Pressure Page
	 * @throws Throwable
	 */
	@Test(description="Add,Edit & Delete an Entry in Blood Pressure Page",groups = {"regression"}) 
	public void addEditDeleteBloodPressureEntry() throws Throwable
	{ 
		logger.info("Navigate to the health manager page");
		columnAssert.assertTrue(bpPageObject.additionOfEntry_BloodPressure(healthManagerData.get(date),healthManagerData.get(systolic),healthManagerData.get(diastolic), healthManagerData.get(textBoxBP)));
		columnAssert.assertTrue(bpPageObject.edit_BloodPressure(healthManagerData.get(date),healthManagerData.get(newsystolic),healthManagerData.get(newdiastolic)));
		columnAssert.assertTrue(bpPageObject.delete_BloodPressureEntry(healthManagerData.get(date)));
		
	}

	/**
	 * This method asserts the addition, edit and deletion of BMI Page
	 * @throws Throwable
	 */
	@Test(description = "Add, Edit & Delete Entry in BMI Page", groups = {"regression"}) 
	public void addEditDeleteBMIEntry() throws InterruptedException {
		logger.info("Navigate to the health manager page");
		columnAssert.assertTrue(bmiPageObject.addEntry_BMI(healthManagerData.get(date), healthManagerData.get(bMIValue),healthManagerData.get(bmiNotes)));
		columnAssert.assertTrue(bmiPageObject.editEntry_BMI(healthManagerData.get(date),healthManagerData.get(newBMIValue)));
		columnAssert.assertTrue(bmiPageObject.deleteEntry_BMI(healthManagerData.get(date)));
		 
	}

	/**
	 * This method asserts the addition, edit and deletion of Body Composition Page
	 * @throws Throwable
	 */
	@Test(description = "Add, Edit & Delete Entry in Body Composition Page",groups = {"regression"}) 
	public void addEditDeleteEntry_BodyComposition() throws InterruptedException {

		columnAssert.assertTrue(bodyCompobject.addEntry_BodyComp(healthManagerData.get(date), healthManagerData.get(waistSize),healthManagerData.get(bodyFat),healthManagerData.get(bodyCompNotes)));
		columnAssert.assertTrue(bodyCompobject.editEntry_BodyComp(healthManagerData.get(date), healthManagerData.get(newWaistSize),healthManagerData.get(newbodyFat)));
		columnAssert.assertTrue(bodyCompobject.deleteEntry_bodyComp(healthManagerData.get(date))); 
		
	}

	/**
	 * This method asserts the addition, edit and deletion of Cholesterol Page
	 * @throws Throwable
	 */

	@Test(description = "Add, Edit & Delete Entry in Cholesterol Page", groups = {"regression"}) 
	public void addEditDeleteEntry_Cholesterol() throws InterruptedException {

		columnAssert.assertTrue(cholesterolObject.addEntry_Cholesterol(healthManagerData.get(date), healthManagerData.get(chlolesterol),healthManagerData.get(ldl), healthManagerData.get(hdl),healthManagerData.get(triglycerides),healthManagerData.get(cholesterolNotes)));
		columnAssert.assertTrue(cholesterolObject.editEntry_Chloesterol( healthManagerData.get(date), healthManagerData.get(newCholest),healthManagerData.get(newldl), healthManagerData.get(newhdl),healthManagerData.get(newGlyc)));
		columnAssert.assertTrue(cholesterolObject.deleteEntry_Cholesterol(healthManagerData.get(date)));
		
	}

	/**
	 * This method asserts the addition, edit and deletion of Glucose Page
	 * @throws Throwable
	 */
	@Test(description = "Add, Edit & Delete Entry in Glucose Page", groups = {"regression"}) 
	public void addEditDeleteEntry_Glucose() throws InterruptedException {
		
		columnAssert.assertTrue(glucoseObject.addEntry_Glucose(healthManagerData.get(date), healthManagerData.get(fastingTxt),healthManagerData.get(nonFastingTxt))); 
		columnAssert.assertTrue(glucoseObject.editEntry_Glucose(healthManagerData.get(date), healthManagerData.get(newfastingVal),healthManagerData.get(newnonfastingVal)));
		columnAssert.assertTrue(glucoseObject.deleteEntry_Glucose(healthManagerData.get(date)));
		 
	}

	/**
	 * This method asserts the addition, edit and deletion of Pulse Rate Page
	 * @throws Throwable
	 */

	@Test(description = "Add, Edit & Delete Entry in Pulse Rate Page",groups = {"regression"})
	public void addEditDeleteEntry_PulseRate() throws InterruptedException {

		
		columnAssert.assertTrue(pulsePageObject.addEntry_PulseRate(healthManagerData.get(date),healthManagerData.get(pulseRate)));
		columnAssert.assertTrue(pulsePageObject.editEntry_PulseRate(healthManagerData.get(date),healthManagerData.get(newPulseRate)));
		columnAssert.assertTrue(pulsePageObject.deleteEntry_PulseRate(healthManagerData.get(date)));
		
	}

	/**
	 * This methiod asserts the addition, edit and deletion of Weight Page
	 * @throws Throwable
	 */
	@Test(description = "Add,Edit & Delete Entry in Weight Page",groups = {"regression"})
	public void addEditDeleteEntry_Weight() throws InterruptedException {

	
		columnAssert.assertTrue(weightPageObject.addEntryWeight(healthManagerData.get(date),healthManagerData.get(weightVal)));
		columnAssert.assertTrue(weightPageObject.editEntryWeight(healthManagerData.get(date),healthManagerData.get(newWeightVal)));
		columnAssert.assertTrue(weightPageObject.deleteEntryWeight(healthManagerData.get(date)));
		 
	}

	/** This method runs after all test from the class have run */
	@AfterClass
	public void classTearDown() {
		logger.info("Logging out of the application...");
		mainPageObj = null;
		bpPageObject = null;
		bmiPageObject = null;
		bodyCompobject = null;
		cholesterolObject = null;
		glucoseObject = null;
		pulsePageObject = null;
		weightPageObject = null;
		logger.info("Writing the output to the Result output text file.");
		UtilityMethods.writeOutputResult();
		logger.info("Exiting the classTearDown() method for HomePageActionCenter class \n\n");

	}

}
