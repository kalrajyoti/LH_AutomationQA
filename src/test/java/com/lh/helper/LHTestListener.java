package com.lh.helper;

import java.util.List;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;
import org.testng.internal.Utils;

/**
 * <h2>A listener that gets invoked before and after a method is invoked by TestNG.</h2>
 * This listener will only be invoked for configuration and test methods.
 * <p>
 * 
 * @author amita.arya
 * @version 1.0
 * @since 2014-11-17
 */
public class LHTestListener extends TestListenerAdapter implements IInvokedMethodListener {
	/**
	 * Logger to log the testNg Listener log messages
	 */
	private static Logger logger = LogManager.getLogger(LHTestListener.class);

	/**
	 * Called after a test method is run
	 */
	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult result) {
	
		Reporter.setCurrentTestResult(result);
		if (method.isTestMethod()) {
			List<Throwable> verificationFailures = ErrorUtil.getVerificationFailures();
			//if there are verification failures...
			if (verificationFailures.size() != 0) {
				//set the test to failed
				result.setStatus(ITestResult.FAILURE);				
				//if there is an assertion failure add it to verificationFailures
				if (result.getThrowable() != null) {
					verificationFailures.add(result.getThrowable());
				}
				int size = verificationFailures.size();
				//if there's only one failure just set that
				if (size == 1) {
					result.setThrowable(verificationFailures.get(0));
				} else {
					//create a failure message with all failures and stack traces (except last failure)
					StringBuffer failureMessage = new StringBuffer("Multiple failures (").append(size).append("):nn");
					for (int i = 0; i < size-1; i++) {
						failureMessage.append("Failure ").append(i+1).append(" of ").append(size).append(":n");
						Throwable t = verificationFailures.get(i);
						String fullStackTrace = Utils.stackTrace(t, false)[1];
						failureMessage.append(fullStackTrace).append("nn");
					}
                   	//final failure
					Throwable last = verificationFailures.get(size-1);
					failureMessage.append("Failure ").append(size).append(" of ").append(size).append(":n");
					failureMessage.append(last.toString());
 
					//set merged throwable
					Throwable merged = new Throwable(failureMessage.toString());
					merged.setStackTrace(last.getStackTrace());
					result.setThrowable(merged);
					
				}
			}
		
		}
		logger.info("Completed execution of- " + method + " test");
		logger.info("The result of test " + method + " is- " + result + "\n\n");

	}
	
	/**
	 * @return Returns the passedTests.
	 */
	public int getNumberOfFailedTests() {
		return super.getFailedTests().size();
	}

	/**
	 * @return Returns the failedTests.
	 */
	public int getNumberOfPassedTests() {
		logger.info("the number of test passed are- " + super.getPassedTests().size());
		return super.getPassedTests().size();
	}

	/**
	 * @return Returns the skippedTests count.
	 */
	public int getNumberOfSkippedTest(){
		logger.info("the number of test passed are- " + super.getSkippedTests().size());
		return super.getSkippedTests().size();
	}
	/**
	 * Called before a test method is run
	 */
	@Override
	public void beforeInvocation(IInvokedMethod arg0, ITestResult arg1) {
		logger.info("Starting execution of the test - " + arg0 + "...");
		
	}

	@Override
	public void onTestFailure(ITestResult tr) {
		super.onTestFailure(tr);
		
		String screenshotPath = DriverFactory.takeScreenShot(tr);
		
		Reporter.log("<a href=\"" + screenshotPath + "\">Failed Screenshot</a>");
		
	}
	
	@Override
	public void onTestSuccess(ITestResult tr) {
		
	}
	
	@Override
	public void onConfigurationFailure(ITestResult itr) {
		
	}
	
	
}
