package com.lh.test.onTrackSuite;

import static com.lh.helper.DriverFactory.driver;

import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import com.lh.pages.admin.AddOnTrackConfigPage;
import com.lh.test.LHBaseTestClass;
import com.lh.testConfig.TestProperty;
import com.lh.utils.SeleniumUtil;

/**
 * <h2>Configure OnTrack Physical Activity Track instance!</h2> 
 * <p>
 * 
 * @author akash.vansil
 * @version 1.0
 * @since 2016-23-02
 */



@Listeners(com.lh.helper.LHTestListener.class)
public class AddPhysicalActivityTrackTest extends LHBaseTestClass{
	
	/** Logger for the AddPhysicalActivityTrackTest class */
	private static Logger logger = LogManager.getLogger(AddPhysicalActivityTrackTest.class);
	/**
	 * Name of the current running test method
	 */
	String testName;
    private Assertion AddOnTrackConfig  = new Assertion();
    private AddOnTrackConfigPage  AddOnTrackConfigPageobj;
    
    
    /**
	 *  This method runs before the first test from the class runs. <p> 
	 * 
	 */
	@BeforeClass
	public void initClass(){
		logger.info("Exiting the initClass() method for Add OnTrack Physical Activity Config Test instance class \n\n");
			
	}
	
	/**
	 * This method runs before each test from the class runs 
	 * @throws InterruptedException 
	 */
	@BeforeMethod
	public void init(Method method) throws InterruptedException{
		logger.info("Inside the init() method for OnTrack Physical Activity Test Page class...");
		//getDriverInstance();
		
		AddOnTrackConfigPageobj = new AddOnTrackConfigPage();
		
		
		Thread.sleep(TestProperty.THREAD_SLEEP);
		
		testName = method.getName();
		logger.info("Exiting the init() method for OnTrack Physical Activity Test class");
	}

	/** Verify UI Element Is Present Or Not */
	@Test(description = "Verify UI Element Is Present Or Not", groups = {"smoke,regression"},priority=1)
	public void VerifyElementsOfConfigPage()
	{
		AddOnTrackConfig.assertTrue(AddOnTrackConfigPageobj.verifyClientDropDown());
		AddOnTrackConfig.assertTrue(AddOnTrackConfigPageobj.verifyTrackCampaign());
		
	}
	/** Add On Track Configuration for 4 weeks Challenge for all three themes */
	@Test(description = "Add On Track Configuration for 4 weeks Challenge for all three themes", groups = {"regression"},priority=2)
	public void addOnTrackConfiguration()
	{
		AddOnTrackConfigPageobj.SelectPhysicalClient();
		SeleniumUtil.sleep(2);
		AddOnTrackConfigPageobj.SelectPhysicalTrackCampaign();
		AddOnTrackConfigPageobj.selectPhysicalActivityTrack();
		SeleniumUtil.sleep(3);
		AddOnTrackConfigPageobj.selectChallengeDurationForTheme1();
		AddOnTrackConfigPageobj.selectUpcomingStartDateForPhysicalActivityTheme1();
		SeleniumUtil.sleep(2);
		AddOnTrackConfigPageobj.selectPhysicalChallengeDurationForTheme2();
		SeleniumUtil.sleep(2);
		AddOnTrackConfigPageobj.selectPhysicalChallengeDurationForTheme3();	
		SeleniumUtil.sleep(4);
		AddOnTrackConfigPageobj.challengeWeeklyCommunication();
		SeleniumUtil.sleep(2);
		AddOnTrackConfigPageobj.generateOnTrackInstance();
		SeleniumUtil.sleep(5);
		
	}
	
	/**
	 * This class runs after running all the methods in the class 
	 */
	@AfterClass
	public void tearDown() {
		AddOnTrackConfigPageobj = null;
		driver.quit();
		logger.info("Exiting the tearDown() method for OnTrack Physical Activity Test \n\n");
	}

	

}
