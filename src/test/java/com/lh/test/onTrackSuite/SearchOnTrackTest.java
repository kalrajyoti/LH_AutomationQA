package com.lh.test.onTrackSuite;

import static com.lh.helper.DriverFactory.driver;

import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import com.lh.pages.admin.SearchTrackPage;
import com.lh.test.LHBaseTestClass;
import com.lh.testConfig.TestProperty;
import com.lh.utils.SeleniumUtil;
//import com.lh.pages.admin.ontrack.AddOnTrackConfigPage;

@Listeners(com.lh.helper.LHTestListener.class)
public class SearchOnTrackTest extends LHBaseTestClass {
	
	/** Logger for the Login class */
	private static Logger logger = LogManager.getLogger(SearchOnTrackTest.class);
	/**
	 * Name of the current running test method
	 */
	private Assertion searchTrack  = new Assertion();
    private SearchTrackPage  SearchTrackPageobj;
    
    /**
	 *  This method runs before the first test from the class runs. <p>
	 *  
	 */
	@BeforeClass
	public void initClass(){
		logger.info("Exiting the initClass() method for Search OnTrack Config Test instance class \n\n");
			
	}
	
	/**
	 * This method runs before each test from the class runs 
	 * @throws InterruptedException 
	 */
	@BeforeMethod
	public void init(Method method) throws InterruptedException{
		logger.info("Inside the init() method for View OnTrack Config Test Page class...");
		SearchTrackPageobj = new SearchTrackPage(driver);	
		Thread.sleep(TestProperty.THREAD_SLEEP);
		logger.info("Exiting the init() method for Search OnTrack Config Test class");
	}


	/** Search Track instance and delete */
	@Test(description = "Search Track instance and delete", groups = {"smoke,regression"},priority=1)
	public void searchAndDeleteTrack()
	{
		SeleniumUtil.sleep(7);
		SearchTrackPageobj.clickOnTrackLink();
		SeleniumUtil.sleep(3);
		SearchTrackPageobj.SelectClient();
		SearchTrackPageobj.clickSubmitButton();
		SeleniumUtil.sleep(2);
		searchTrack.assertTrue(SearchTrackPageobj.verifyDeleteLink());
		SearchTrackPageobj.deleteTrack();
	}
	

	@Test(description = "Navigate to Add OnTrack Config Page Test", groups = {"smoke,regression"},priority=2)
	public void navigateToOnTrackAddScreen()
	{
		SeleniumUtil.sleep(2);
		SearchTrackPageobj.click_OnTrackAddConfiguration();
		SeleniumUtil.sleep(1);
		
	}

}
