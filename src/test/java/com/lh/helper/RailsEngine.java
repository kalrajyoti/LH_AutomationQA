package com.lh.helper;

import java.io.IOException;

import com.lh.utils.APIException;

//import org.apache.log4j.Logger;

import com.lh.utils.TestRailUtils;

public class RailsEngine {
	//log	private static Logger log = Logger.getLogger(RailsEngine.class);

	public static void uploadToRails(String classpath, String status,
			String error) throws APIException {
		// Get Test Case ID
		String testCaseId = TestRailUtils.getTestCaseId(classpath);

		// Status Code for rails
		int statusCode = TestRailUtils.TEST_CASE_PASSED_STATUS;
		if (status.equals(Constants.TEST_FAILED)) {
			statusCode = TestRailUtils.TEST_CASE_FAILED_STATUS;
		}

		// Upload the result to rails
		if (!testCaseId.equals("")) {
			try {
				TestRailUtils.addResultForTestCase(testCaseId, statusCode,
						error);
			} catch (IOException e) {
				//log				log.error("Error while upload result to rails" + e);
				//System.out.println("Error while upload result to rails" + e);
				//e.printStackTrace();
			}
		} else {
			//log			log.error("Test case not in rails :: " + classpath);
			//System.out.println("Test case not in rails :: " + classpath);
		}
	}

}
