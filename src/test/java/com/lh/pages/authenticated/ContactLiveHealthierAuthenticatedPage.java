package com.lh.pages.authenticated;

import static com.lh.helper.DriverFactory.driver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import com.lh.pages.staticPages.ContactLiveHealthierBasePage;
import com.lh.testConfig.TestProperty;
import com.lh.utils.SeleniumUtil;

/**
 * <h2>This is the Contact LiveHealthier Class for the Unauthenticated user</h2>
 * <p>
 * 
 * @author amita.arya
 * @version 1.0
 * @since 2015-02-18
 */

public class ContactLiveHealthierAuthenticatedPage extends ContactLiveHealthierBasePage {
	
	/**
	 * Logger to log messages
	 */
	private static Logger logger = LogManager
			.getLogger(com.lh.pages.authenticated.ContactLiveHealthierAuthenticatedPage.class);
	Map<String, String> contactUsData;
	/**
	 * One param constructor
	 * 
	 * @param driver
	 *            webdriver instance to connect to the page under test
	 */
	public ContactLiveHealthierAuthenticatedPage(WebDriver driver) {
		super();
		Reporter.log("Initializing the ContactLiveHealthierAuthenticatedPage Object...");
		// Check that we're on the right page.
		if (!(driver.getTitle().equalsIgnoreCase(TestProperty.CLIENT_PORTAL_TITLE))) {
			logger.error("This is not the Contact us page");
			throw new IllegalStateException(
					"This is not the ContactLiveHealthierAuthenticatedPage page");
		}
		Reporter.log("Initialized the ContactLiveHealthierAuthenticatedPage page object");
	}
	
	
	/**
	 * Verifies the name of the user appearing in the username textbox
	 * @param userName the contacting user's name
	 */
	protected boolean verifyUserName(String userName) {

		if (userName.isEmpty()) {
			
			logger.error("The user First name to verify is not provided");	

		} else  {
			
			userName = userName.trim();

			String userNameCurrTxt = driver.findElement(userNameTxt).getAttribute("value").trim();
			Reporter.log("user name is - " + userNameCurrTxt);
			
			if (userNameCurrTxt.equals(userName)) {
				Reporter.log("The user email is set to - " + userName);
				
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * 
	 * Verifies the email of the user appearing in the Email textbox
	 * @param userEmail The contacting users email.
	 */
	protected boolean verifyUserEmail(String userEmail) {

		if (userEmail.isEmpty()) {
			
			logger.error("The user email to verify is not provided");

		} else {
			userEmail = userEmail.trim();

			String userEmailCurrTxt = driver.findElement(userEmailTxt).getAttribute("value").trim();
			Reporter.log("user email is - " + userEmailCurrTxt);

			if (userEmailCurrTxt.equals(userEmail)) {
				Reporter.log("The user email is set to - " + userEmail);
				
				return true;
			}
		}

		return false;
	}
	public  boolean verifyalldropdown(Map<String,String> dropdowndata){
	
		boolean ispresent;
		WebElement dropdownValues = driver.findElement(contactingAboutTopicSel);
		Select sel = new Select(dropdownValues);
		List <WebElement> dropdownvalueList = sel.getOptions();
		System.out.println("The value of dropdown is:" +dropdownvalueList);
		int Actualdropdownsize = dropdownvalueList.size();
		List<String> expecteddropvalue = splitPipeSepratedString(dropdowndata.get("dropdown"));
		int expectedDropdownsize = expecteddropvalue.size();
		if(expectedDropdownsize==Actualdropdownsize)
		{
		for(int i=0;i<Actualdropdownsize-1;i++)
		{
			String dropdownselvalues = dropdownvalueList.get(i).getText();
			if(!(expecteddropvalue.contains(dropdownselvalues)))
			/*if(!(expectedDropdownsize==Actualdropdownsize && expectdropselvalues.equals(dropdownselvalues)))*/
			{
			
				System.out.println("This is not dropdown for contactus page:");
				ispresent =false;
				return ispresent;
			}
			else
			{
				continue;
			}
		
		}}
		return true;
		}	
		public ArrayList<String> splitPipeSepratedString(String pipeSepratedSting){

		ArrayList<String> alist = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(pipeSepratedSting, "|");
		while(st.hasMoreTokens()){
			alist.add(st.nextToken());
		}
		return alist;

	}



	/*	if("Login".equals(sValue))
			{
				System.out.println("element is present");
				continue;
			}
			else if("What are you contacting us about?".equals(sValue))
			{
				System.out.println("elememt not present");
				continue;
			}
			else if("Biometric Screening".equals(sValue))
			{
				continue;
			}
			else if(" Incentives - general questions".equals(sValue))
			{
				continue;
			}
			else if(" Incentives - report completing an alternative option".equals(sValue))
					{
				continue;
					}
			else if(" Challenges".equals(sValue))
			{
				continue;
			}
			else if("Spouse/Dependent".equals(sValue))
			{
				continue;
			}
			else if("Other".equals(sValue))
			{
				return true;
			}
		
			List<String> expecteddropvalue = splitPipeSepratedString(contactUsData.get("dropdown"));
			for(String s1:expecteddropvalue)
			{
			String j1 =String s1:expecteddropvalue;
			System.out.println(expecteddropvalue);
			//int size=expecteddropvalue.size();
			//for(int j=0;j<size;j++){
			//	String expecteddropvalue1 = expecteddropvalue.get(j);
			//	System.out.println(expecteddropvalue1);
				//String expecteddropvalue1=expecteddropvalue.get(j).getText();
			//if(sValue.equals(expecteddropvalue1))
				//return true;
			//}
			
			//System.out.println(expecteddropvalue);
			//for(WebElement s:elementCount)
			//{
			//if(expecteddropvalue.contains(s))
			
			//{
				//return true;
			//}
			
			
			}
			
		}	
	
	return false;
	}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//List<String> contactUsData = driver.findElements(arg0)
		/*SeleniumUtil.hover(SeleniumUtil.dynamicXpath(dropdownofContactuspage, dropdownvalue), driver);
		WebElement element =SeleniumUtil.element(SeleniumUtil.dynamicXpath(dropdownofContactuspage, dropdownvalue), driver);
		element.click();
		System.out.println(element);
		
		
		if("Login".equals(dropdownvalue))
		{
			System.out.println("element is present");
			return true;
		}
		else if("What are you contacting us about?".equals(dropdownvalue))
		{
			System.out.println("elememt not present");
			return true;
		}
		else if("Biometric Screening".equals(dropdownvalue))
		{
			return true;
		}
		else if(" Incentives - general questions".equals(dropdownvalue))
		{
			return true;
		}
		else if(" Incentives - report completing an alternative option".equals(dropdownvalue))
				{
			return true;
				}
		else if(" Challenges".equals(dropdownvalue))
		{
			return true;
		}
		else if("Spouse/Dependent".equals(dropdownvalue))
		{
			return true;
		}
		else if("Other".equals(dropdownvalue))
		{
			return true;
		}
		
	
	return false;
	}}
		//List<String> Dropdownlist = new ArrayList<String>();
		//List<String> Dropdownlist = splitPipeSepratedString(contactUsData.get("messageSubject");
				//List<String> gamePlansExpectedList = splitPipeSepratedString(gamePlanData.get("gamePlan")); /*


	/**
	 * Sets the email options in the Contact us page and send the email to LiveHealthier
	 * @param userName
	 * @param userEmail
	 * @param topic
	 * @param message
	 */
	@Override
	public void sendMessageToLiveHealthier(String userName, String userEmail, String topic, String message){
		
		logger.debug("Setting the email options on the unauthenticated ContactUS page");

		if (verifyUserName(userName)) {

			if (verifyUserEmail(userEmail)) {

				selectTopicOfMessage(topic);
				setMessage(message);
				clickSubmit();
				SeleniumUtil.sleep(3);
			} else {
				
				logger.error("The user Email is not appearing as expected");
				
			}
		} else {
			
			logger.error("The username is not appearing as expected");
		}

		logger.debug("The message has been sent to LiveHealthier.");

	}
}
