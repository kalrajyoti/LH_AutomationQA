package com.lh.test.base;

import org.apache.logging.log4j.Logger;import org.apache.logging.log4j.LogManager;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeSuite;

import com.lh.helper.SendMailTLS;
import com.lh.helper.ZipResultsFolders;
import com.lh.utils.DateUtil;
import com.lh.utils.RetryAnalyzer;
import com.lh.utils.UtilityMethods;

/**
 * <h2>This class has the common test methods for all the tests</h2>
 * <p>
 * E.g. @AfterSuite
 * 
 * @author amita.arya
 * @version 1.0
 * @since 2015-02-08
 */
public class UnAuthBaseTestClass {

	private static Logger logger 		= LogManager.getLogger(UnAuthBaseTestClass.class);
	
	@BeforeSuite(alwaysRun = true)
	public void initSuite(ITestContext context){
		logger.info("\n\nStarting the Tests run at - " + DateUtil.getFormattedCurrentDateTime());
		for (ITestNGMethod method : context.getAllTestMethods()) {
			method.setRetryAnalyzer(new RetryAnalyzer());
		}
	}

	@AfterSuite
	public void cleanup() {

		logger.info("Writing the output to the Result output text file.");
		UtilityMethods.writeOutputResult();

		logger.debug("Scheduling the Report to be sent via email...");

		ZipResultsFolders.zipResults();
		logger.info("Zipping is successfully done.");

		SendMailTLS.sendMail();
		logger.info("Email with the result has been successfully sent.");

		logger.info("Completed the Tests run at - " + DateUtil.getFormattedCurrentDateTime());

	}


	@BeforeGroups("smoke")
	public void startSmokeTests() {
		logger.info("Starting the SMOKE TEST run...");;
	}

	@AfterGroups("smoke")
	public void teardownSmokeTests() {
		logger.info("Completed the SMOKE TEST run...");;
	}
}
