package com.lh.utils;

//import org.apache.log4j.Logger;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.lh.helper.Constants;
import com.lh.helper.RailsEngine;

/*
 * This class overrides the failure, success function 
 * to get snapshot 
 */
public class TestNGCustom extends TestListenerAdapter {


	@Override
	public void onTestFailure(ITestResult tr) {
		String testCase = tr.getTestClass().getName() + "." + tr.getName();
		String error = tr.getThrowable().getMessage();
		try {
			RailsEngine.uploadToRails(testCase, Constants.TEST_FAILED, error);
		} catch (APIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
	}

	@Override
	public void onTestSuccess(ITestResult tr) {
		String testCase = tr.getTestClass().getName() + "." + tr.getName();
		try {
			RailsEngine.uploadToRails(testCase, Constants.TEST_PASSED, "");
		} catch (APIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
