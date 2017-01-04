package com.lh.utils;

import com.lh.helper.LHTestListener;
import org.apache.logging.log4j.Logger;import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/**
 * <h1>This class has all the utility methods which are used by the Page Classes
 * </h1>
 * <p>
 * 
 * @author amita.arya
 * @version 1.0
 * @since 2014-11-17
 */
public class UtilityMethods {
	
	private static final String TASKLIST = "tasklist";
	private static final String KILL = "taskkill /F /IM ";
	
	/**
	 * To log the log messages
	 */
	private static Logger logger = LogManager
			.getLogger(com.lh.utils.UtilityMethods.class);

	/**
	 * Random String Generator
	 * - Oleg Andreyev (7.20.16)
     */
	static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	static SecureRandom rnd = new SecureRandom();
	public static String randomString( int len ){
		StringBuilder sb = new StringBuilder( len );
		for( int i = 0; i < len; i++ )
			sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
		return sb.toString();
	}

	/**
	 * This method returns the label text of the Selected option
	 * 
	 * @param element
	 *            accessed WebElement
	 * @return Selected Element Identifier
	 */
	public static String getSelectText(WebElement element) {
		String selTxt = null;

		List<WebElement> optionsSelect = element.findElements(By
				.tagName("option"));
		for (int count = 0; count <= (optionsSelect.size() - 1); count++) {
			WebElement option = optionsSelect.get(count);
			if (option.isSelected()) {
				selTxt = option.getText();
			}
		}
		return selTxt;
	}

	/**
	 * This method returns the list of options available in the dropdown
	 * 
	 * @param element
	 *            accessed WebElement
	 * @return Array with list of options available in the dropdown
	 */
	public static ArrayList<String> getSelectOptions(WebElement element) {
		List<String> selOptValue = new ArrayList<String>();

		List<WebElement> optionsSelect = element.findElements(By
				.tagName("option"));
		logger.debug("The size is - " + optionsSelect.size());
		for (int count = 0; count <= (optionsSelect.size() - 1); count++) {
			WebElement option = optionsSelect.get(count);
			selOptValue.add(option.getText());
			logger.debug("The option no is--" + count
					+ ". The option Text is ****" + option.getText());
		}
		return (ArrayList<String>) selOptValue;
	}

	/**
	 * This method compares the elements of two ArrayLists
	 * 
	 * @param listString1
	 *            List 1
	 * @param listString2
	 *            List 2
	 * @return Boolean with the result of comparison of two lists
	 */
	public static boolean compareTwoList(ArrayList<String> listString1,
			ArrayList<String> listString2) {
		
		logger.debug("Comparing the two Arraylist " + listString1 + " and "
				+ listString2 + " for equality...");
		boolean isVerify = false;
		if (listString1.size() == listString2.size()) {

			verifyPresenceOfItems: for (int countList1 = 0; countList1 <= (listString1
					.size() - 1); countList1++) {
				isVerify = false;
				for (int countList2 = 0; countList2 <= (listString2.size() - 1); countList2++) {
					if (listString1.get(countList1).equals(
							listString2.get(countList2))) {
						isVerify = true;
						logger.debug("The item is found in the both Lists! The current item on the page is--"
										+ listString1.get(countList1)
										+ " and the item from the list is  --"
										+ listString2.get(countList2));
					}
				}
				if (!isVerify) {
					logger.debug("One of the Items is not found in the other list...");
					break verifyPresenceOfItems;
				}

			}
		}
		return isVerify;
	}

	/**
	 * This method converts an ArrayList of Strings to Array of Strings
	 * 
	 * @param strList
	 *            ArrayList of Strings
	 * @return Array of Strings
	 */
	public static String[] convertArrListToString(
		ArrayList<String> strList) {
		logger.info("Converting ArrayList to array");
		return (String[]) strList.toArray();
	}

	/**
	 * This method converts an Array of Strings to ArrayList of Strings
	 * 
	 * @param strArr
	 *            Array of Strings
	 * @return ArrayList of Strings
	 */
	public static ArrayList<String> convertStringArrToArrList(String[] strArr) {
		ArrayList<String> strArrList = new ArrayList<String>();
		for (int count = 0; count <= (strArr.length - 1); count++) {
			strArrList.add(strArr[count]);
		}
		return strArrList;
	}

	
	public static boolean isInteger(String testString) {
	    try { 
	        Integer.parseInt(testString); 
	    } catch(NumberFormatException e) { 
	    	logger.info("The String-- " + testString + " cannot be converted into an integer", e);
	        return false; 
	    }
	    return true;
	}

	
	public static void writeOutputResult() {
		LHTestListener listenerObj = new LHTestListener();

		ResultOutputWriter outputWriterObj = new ResultOutputWriter();
		
		int passedTest = listenerObj.getNumberOfPassedTests();
		int failedTest = listenerObj.getNumberOfFailedTests();
		int skippedTest = listenerObj.getNumberOfSkippedTest();
		outputWriterObj.writeResultOutputToDisk(passedTest, failedTest, skippedTest, null, null);	
	}
	
	public static String extractPartialClientURL (String baseURL) {
		
		String clientURL = baseURL.substring(0, baseURL.length()-13);
		return clientURL;
	}
	
	public static boolean isProcessRunning(String serviceName) throws Exception {

		 Process p = Runtime.getRuntime().exec(TASKLIST);
		 BufferedReader reader = new BufferedReader(new InputStreamReader(
		   p.getInputStream()));
		 String line;
		 while ((line = reader.readLine()) != null) {

		  System.out.println(line);
		  if (line.contains(serviceName)) {
		   return true;
		  }
		 }

		 return false;

		}

		public static void killProcess(String serviceName) throws Exception {

		  Runtime.getRuntime().exec(KILL + serviceName);

		 }

	/**
	 +	 * Summary - This method verifies the HTTP Status Code of the provided URL parameter.
	 +	 * 				Will return error if 404 or 500 errors returned.
	 +	 * Author - Oleg Andreyev
	 +	 *
	 +	 * @param urlString
	 +     * @return
	 +     */
	public static boolean getResponseCode(String urlString) {
			boolean isValid = false;
				try {
						URL u = new URL(urlString);
						HttpURLConnection h = (HttpURLConnection) u.openConnection();
						h.setRequestMethod("GET");
						h.connect();
						System.out.println("HTTP Response Code - " + h.getResponseCode() + " ::: " + urlString);
						if (h.getResponseCode() != 404) {
							isValid = true;
						}
						if (h.getResponseCode() != 500) {
								isValid = true;
							}
					} catch (Exception e) {
						Assert.fail("HTTP Error detected on the following URL: " + urlString);
					}
				return isValid;
			}



	
}
