
package com.lh.helper;

import com.lh.testConfig.TestProperty;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.TestNG;
import org.testng.annotations.AfterSuite;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static com.lh.testConfig.TestProperty.BASE_URL;
import static com.lh.testConfig.TestProperty.WAITING_TIME;

/**
 * <h2>This class creates the required driver for the test, to run the test in a
 * selected browser. Supported Browsers are:</h2>
 * <p>
 * Firefox<br>
 * Chrome<br>
 * IE<br>
 *
 * @author ravi.middha, amita.arya,akash vansil
 * @version 2.0
 * @modified 2016-05-17
 */

public class DriverFactory {

	/**
	 * This String contains the value of the browser needed to create the
	 * required driver instance
	 */
	public static String browser 				= TestProperty.BROWSER;
	/** Webdriver instance to create a webdriver */
	public static WebDriver webDriver 				= null;
	/** Event firing webdriver with a registered listener */
	public static EventFiringWebDriver driver 		= null;	
	/** Logger to log the Driver Factory log messages */
	private static Logger logger 					= LogManager.getLogger(com.lh.helper.DriverFactory.class);

	/**
	 * This method is called by the test classes to create a driver
	 */
	public static void getDriverInstance() {
		createDriver();
		logger.info("Initialized the webdriver");
	}

	/**
	 * This is the Factory Method used for creating appropriate web driver
	 *
	 * @param browserId - Browser to run the test on
	 */
	public static EventFiringWebDriver createDriver() {
		logger.info("The browser to open is: " + TestProperty.BROWSER);


		if ("firefox".equalsIgnoreCase(TestProperty.BROWSER.trim())) {


			logger.info("Creating the instance of firefoxDriver...");
			webDriver = new FirefoxDriver();

		} else if ("ie".equalsIgnoreCase(TestProperty.BROWSER.trim())) {

			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, BASE_URL);

			logger.info("Creating the instance of webdriver.ie.driver...");

			File file = new File("src/test/resources/IEDriverServer64.exe");
			System.setProperty("webdriver.ie.driver", file.getAbsolutePath());

			webDriver = new InternetExplorerDriver(cap);


		
		} else if ("chrome".equalsIgnoreCase(TestProperty.BROWSER.trim())) {

			logger.info("Creating the instance of webdriver.chrome.driver...");
			final File file = new File("src/test/resources/chromedriver.exe");

			System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());

			DesiredCapabilities capability = DesiredCapabilities.chrome();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("test-type");
			capability.setCapability("chrome.binary", file.getAbsolutePath());
			capability.setCapability(ChromeOptions.CAPABILITY, options);
			webDriver = new ChromeDriver(capability);

		} else {
			try {
				throw new Exception(TestProperty.BROWSER +" NOT supported.  Choose either firefox, ie, or chrome.");
			} catch (Exception e) {
				logger.error("The requested browser is NOT supported! Choose either firefox, ie, or chrome.", e);
			}
		}

		return driver = new EventFiringWebDriver(webDriver);

	}

	/**
	 * This method opens the test URL and sets the driver properties
	 */
	public static void openURL(String url) {
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(WAITING_TIME, TimeUnit.SECONDS);

		driver.manage().window().maximize();
		int waitTime = TestProperty.WAITING_TIME;
		//WebDriverWait waitVar = new WebDriverWait(driver, waitTime);
		driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(-1, TimeUnit.SECONDS);
	}

	/**
	 * This method takes the screenshot of the current browser instance
	 * 
	 * @param testName
	 */
	public static void takeScreenShot(String currTestName) {
		
		if ("true".equals(TestProperty.TAKE_SCREENSHOTS)) {
			String testName = addDate(currTestName);

			@SuppressWarnings("deprecation")
			String outputDIR = TestNG.getDefault().getOutputDirectory();
			final String newFileNamePath = outputDIR + "\\ScreenShot\\" + currTestName + "\\" + testName + ".jpg";

			final File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			
			try {
				
				FileUtils.copyFile(scrFile, new File(newFileNamePath));
				logger.info("The Screenshot is saved for- " + testName);
				
			} catch (IOException e) {
				logger.error("The File is not found, in the takeScreenShot method of the driverFactory...");
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * This method takes the screenshot of the current browser instance
	 * @param currTestName The name of the test method being executed
	 * @param tr the test result of the test method
	 * @return
	 */
	public static String takeScreenShot( ITestResult tr ) {
		
		
		String tm= tr.getMethod().getMethodName();
		
		String currTestName = addDate(tm);
		
		@SuppressWarnings("deprecation")
		String outputDIR = TestNG.getDefault().getOutputDirectory();
		final String newFileNamePath = outputDIR + "\\ScreenShot\\" + tm + "\\"	+ currTestName + ".jpg";

		final File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(scrFile, new File(newFileNamePath));
			logger.info("The Screenshot is saved for- " + currTestName);
		} catch (IOException e) {
			logger.error("The File is not found, in the takeScreenShot method of the driverFactory...");
			e.printStackTrace();
		}
		
		return newFileNamePath;
	}

	/**
	 * This method appends current data parameter to the current test name for
	 * naming the screenshot
	 * 
	 * @param currTestName
	 * @return String with the testname and date-time appended
	 */
	public static String addDate(final String currTestName) {
		Date myDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd-HHmmss");
		return currTestName + "-" + sdf.format(myDate);
	}



	/**
	 * This innerclass is used for exception handling within the Driver Factory
	 * 
	 * @author ravi.middha
	 *
	 */
	public static class UnsupportedBrowserError extends RuntimeException {
		private static final long serialVersionUID = 1L;

		/**
		 * This method handles the Unsupported browser request
		 * 
		 * @param message
		 */
		public UnsupportedBrowserError(final String message) {
			super(message);
		}
	}
	
	
	/**
	 * This method is run at the end of the test suite
	 */
	@AfterSuite (alwaysRun = true)
	public void suiteCleanup() {
		logger.debug("Scheduling the Report to be sent via email");
		SendMailTLS.sendMail();
		ZipResultsFolders.zipResults();
		SendMailTLS.sendMail();
		logger.info("Scheduled the Report to be sent via email and exiting the suite...");
	}



}
