package com.lh.helper.jyoti;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.lh.testConfig.TestProperty;

public class DriverFactoryModified {
	
	public static String browser = TestProperty.BROWSER;
	public static WebDriver webDriver = null;
	private static Logger logger 					= LogManager.getLogger(com.lh.helper.DriverFactory.class);
	
	public void getDriverInstance()
	{
		createBrowser(browser);
	}
	
	public void createBrowser(String Browserid)
	
	{
		if("firefox".equalsIgnoreCase(Browserid))
				{
			logger.info("Creating the instance of firefoxDriver...");
			webDriver = new FirefoxDriver();
		         }
		else if("chrome".equalsIgnoreCase(Browserid)) //need to discuss
		{
			logger.info("Creating the instance of webdriver.chrome.driver...");
			final File file = new File("src/test/resources/chromedriver.exe");

			System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());

			DesiredCapabilities capability = DesiredCapabilities.chrome();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("test-type");
			capability.setCapability("chrome.binary", file.getAbsolutePath());
			capability.setCapability(ChromeOptions.CAPABILITY, options);
			webDriver = new ChromeDriver(capability);
		}
		
		else if("iexplore".equalsIgnoreCase(Browserid))//need to discuss
		{
			logger.info("Creating the instance of webdriver.ie.driver...");

			final File file = new File("../resources/IE WebDriver/IEDriverServer.exe");

			System.setProperty("webdriver.ie.driver", file.getAbsolutePath());

			final DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
			ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			webDriver = new InternetExplorerDriver(ieCapabilities);
		}
		else
		{
			System.out.println("Browser is not supported");
		}
	}
       public void getUrl(String URL)
       {
    	   webDriver.get(URL);
    	   webDriver.manage().window().maximize();
    	   int waitTime = TestProperty.WAITING_TIME;
    	   webDriver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);//need to discuss
    	   webDriver.manage().timeouts().pageLoadTimeout(-1, TimeUnit.SECONDS);//need to discuss
       }
}
