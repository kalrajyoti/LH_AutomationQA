/**
 * 
 */
package com.lh.pages.authenticated.ha;

import java.util.Map;

import org.apache.logging.log4j.Logger;import org.apache.logging.log4j.LogManager;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import com.lh.pages.authenticated.MainPage;
import com.lh.testConfig.TestProperty;
import com.lh.utils.SeleniumUtil;

/**
 * @author Sanjeev.Kumar
 *
 */
public class HealthCoachingPage_Old extends MainPage{
	
	private By phoneNumber 			= By.xpath("//ol[@class='small-circle']/li[1]/strong");
	private By phoneButton 			= By.xpath("//div[@id='leftNavigationButtons']/div/a[contains(text(),'Phone')]");
	private By onlineButton 		= By.xpath("//div[@id='leftNavigationButtons']/div/a[contains(text(),'Online')]");
	private By contactNowButton 	= By.xpath("//div[@class='coachingContactButtonRow']/a[contains(text(),'Contact Now')]");
	private By continueButton 		= By.xpath("//div[@class='health-history-form']/div/input[@value='Continue']");
	private By conversationSubject 	= By.xpath("//div[@class='styled-select-subpage']/input[@placeholder='Subject']");
	private By conversationMessage 	= By.xpath("//div[@class='styled-select-subpage']/textarea[@placeholder='Message']");
	private By conversationSend 	= By.xpath("//div[@class='styled-select-subpage']/div/a[@id='lnkSubmit']");
	private By okayButton 			= By.xpath("//div[@id='thanksdialog']/div[@class='modal-container']/div/a[@class='button']");
	private String  coachingconversations= "//div[@class='coaching-conversations-inner']/ext.net.direct.update/div/div/a[text()='%s']";
	private String ans1 = "//label[text()='%s']/../input";
	private String ans5 = "//div[@class='health-history-form'][5]/div/div/ol/li/label[contains(text(),'%s')]/../input";
	private String ans6 = "//div[@class='health-history-form'][6]/div/div/ol/li/label[contains(text(),'%s')]/../input";
	private String ans7 = "//div[@class='health-history-form'][7]/div/div/ol/li/label[contains(text(),'%s')]/../input";
	private String ans8 = "//div[@class='health-history-form'][8]/div/div/ol/li/label[contains(text(),'%s')]/../input";
	private String ans9 = "//div[@class='health-history-form'][9]/div/div/ol/li/label[contains(text(),'%s')]/../input";
	private String ans10 = "//div[@class='health-history-form'][10]/div/div/span/input[%s]";
	/**
	 * Logger to log messages
	 */
	private static Logger logger = LogManager
			.getLogger(HealthCoachingPage_Old.class);
	/**
	 * Webdriver to connect to the current instance of the browser
	 */
	private WebDriver driver;

	public HealthCoachingPage_Old(WebDriver driver) {
		super();
		Reporter.log("Initializing the HealthAssessmentPage Object...");
		this.driver = driver;
		// Check that we're on the right page.
		if (!(driver.getTitle().equalsIgnoreCase(TestProperty.CLIENT_PORTAL_TITLE))) {
			logger.error("This is not the Health Assessment page");
			throw new IllegalStateException(
					"This is not the HealthAssessmentPage page");
		}
		Reporter.log("Initialized the HealthAssessmentPage page object");
	}

	/**
	 * This method is used for getting the phone number for verification on phone choching
	 * @return phone number
	 */
	public String getPhoneNumber() {
		driver.findElement(phoneButton).click();
		return driver.findElement(phoneNumber).getText();
		
	}
	
	public String getonlineCoachingr() {
		
		return driver.findElement(phoneNumber).getText();
		
	}

	/**
	 * This method is verify the online coaching functionality
	 * @param coachingData
	 * @return true if mail subject is display on coaching conversations area, otherwise it return fail
	 * @throws JSONException
	 * @throws InterruptedException
	 */
	public boolean verifySendConversation(Map<String, String> coachingData) throws JSONException, InterruptedException {
		Reporter.log("Satrt online coaching verification...");
		
		driver.findElement(onlineButton).click();
		driver.findElement(contactNowButton).click();
		Reporter.log("Online coaching Page is open ...");
		
		if(!SeleniumUtil.isElementPresent(conversationSubject,driver)){
			Reporter.log("Answering the mandootry question  ...");
			JSONArray json = new JSONArray(coachingData.get("coachingQuestionsAnswer"));
			JSONObject resionObj = json.getJSONObject(0);
			
			driver.findElement(SeleniumUtil.dynamicXpath(ans1, resionObj.getString("question1"))).click();
			
			driver.findElement(SeleniumUtil.dynamicXpath(ans1, resionObj.getString("question2"))).click();
			
			driver.findElement(SeleniumUtil.dynamicXpath(ans1, resionObj.getString("question3"))).click();
			
			driver.findElement(SeleniumUtil.dynamicXpath(ans1, resionObj.getString("question4"))).click();
			
			driver.findElement(SeleniumUtil.dynamicXpath(ans5, resionObj.getString("question5"))).click();
			
			driver.findElement(SeleniumUtil.dynamicXpath(ans6, resionObj.getString("question6"))).click();
			
			driver.findElement(SeleniumUtil.dynamicXpath(ans7, resionObj.getString("question7"))).click();
			
			driver.findElement(SeleniumUtil.dynamicXpath(ans8, resionObj.getString("question8"))).click();
			
			driver.findElement(SeleniumUtil.dynamicXpath(ans1, resionObj.getString("question9"))).click();
			
			driver.findElement(SeleniumUtil.dynamicXpath(ans10, resionObj.getString("question10"))).click();	
			
			driver.findElement(continueButton).click();
			Thread.sleep(30000);
			driver.findElement(contactNowButton).click();
			return sendConversation(coachingData.get("conversationSubject"),coachingData.get("conversationMessage"));
		}
		else{
			return sendConversation(coachingData.get("conversationSubject"),coachingData.get("conversationMessage"));
		}
	}
	
	/**
	 * This method is used for sending the mail to online coaching expert
	 * 
	 * @param conversationSubjectText
	 * @param conversationMessageText
	 * @return true, if mail subject is display on coaching conversations area
	 * @throws InterruptedException
	 */
	public boolean sendConversation (String conversationSubjectText, String conversationMessageText) throws InterruptedException{
		Reporter.log("wrting mail to export for OnlLIne coaching  ...");
		driver.findElement(conversationSubject).clear();
		driver.findElement(conversationSubject).sendKeys(conversationSubjectText);
		driver.findElement(conversationMessage).clear();
		driver.findElement(conversationMessage).sendKeys(conversationMessageText);
		driver.findElement(conversationSend).click();
		Thread.sleep(10000);
		driver.findElement(okayButton).click();
		SeleniumUtil.waitForElementToBePresent(SeleniumUtil.dynamicXpath(coachingconversations, conversationSubjectText), driver);
		return SeleniumUtil.isElementPresent(SeleniumUtil.dynamicXpath(coachingconversations, conversationSubjectText),driver);
		
	}
}
