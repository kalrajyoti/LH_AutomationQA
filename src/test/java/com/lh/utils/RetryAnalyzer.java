package com.lh.utils;


import org.apache.logging.log4j.Logger;import org.apache.logging.log4j.LogManager;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.lh.testConfig.TestProperty;

public class RetryAnalyzer implements IRetryAnalyzer  { 

public static int retryCount = TestProperty.RETRY_COUNT;
private static Logger logger = LogManager.getLogger(com.lh.utils.RetryAnalyzer.class);

@Override 
public boolean retry(ITestResult result) { 
	logger.info("Re running failed method '" 
            + result.getName() 
           + "' of class " + this.getClass().getName() );
        if(retryCount < retryCount) {                     
        	retryCount++;                                    
                return true; 
        } 
        return false; 
}
}