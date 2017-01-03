package com.lh.test.base;

import com.lh.helper.SendMailTLS;
import com.lh.helper.ZipResultsFolders;
import com.lh.testConfig.TestProperty;
import com.lh.utils.DateUtil;
import com.lh.utils.ExcelFileUtility;
import com.lh.utils.RetryAnalyzer;
import com.lh.utils.UtilityMethods;
import com.thoughtworks.selenium.webdriven.commands.SelectWindow;

import org.apache.logging.log4j.Logger;import org.apache.logging.log4j.LogManager;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.annotations.*;

import java.util.Map;

import static com.lh.helper.DriverFactory.*;

public class BaseTestClass {

	private static Logger logger             = LogManager.getLogger(BaseTestClass.class);

	@BeforeSuite(alwaysRun = true)
	public void initSuite(ITestContext context){
		logger.info("\n\nStarting the Tests run at - " + DateUtil.getFormattedCurrentDateTime());
		for (ITestNGMethod method : context.getAllTestMethods()) {
			method.setRetryAnalyzer(new RetryAnalyzer());
		}
		getDriverInstance();
		openURL(TestProperty.BASEURL);
	}

	@AfterSuite
	public void cleanupSuite() {

		driver.quit();
		logger.info("Writing the output to the Result output text file.");
		UtilityMethods.writeOutputResult();

		logger.debug("Scheduling the Report to be sent via email...");

		ZipResultsFolders.zipResults();
		logger.info("Zipping is successfully done.");

		SendMailTLS.sendMail();
		logger.info("Email with the result has been successfully sent.");

		logger.info("Completed the Tests run at - " + DateUtil.getFormattedCurrentDateTime());
	
	}

	@AfterTest
	public void cleanupTest(){
		driver.quit();
	}

	@BeforeGroups("smoke")
	public void startSmokeTests() {
		logger.info("Starting the SMOKE TEST run...");;
	}

	@AfterGroups("smoke")
	public void teardownSmokeTests() {
		logger.info("Completed the SMOKE TEST run...");;
	}

	protected Map<String, String> readexcelsheet(String sheetName) {
		ExcelFileUtility testDataObj = new ExcelFileUtility();
		return testDataObj.readExcelSheet(TestProperty.TESTDATA_WORKBOOK, sheetName);
	}
}