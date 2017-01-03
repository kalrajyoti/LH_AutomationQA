package com.lh.pages.coaching;

import static com.lh.helper.DriverFactory.driver;
import java.util.Map;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.testng.Reporter;

import com.lh.pages.authenticated.MainPage;
import com.lh.utils.SeleniumUtil;

/**
 * <h2>Click health coaching and confirm overview,online onsite and phone tab</h2>
 * <p>
 * 
 * @author pranali.jain
 * @version 1.0
 * @since 2016-08-06
 */

public class HealthCoachingPage extends MainPage{

	private static Logger logger = LogManager.getLogger(HealthCoachingPage.class);
	private By coachingHeaderTxt 					= By.xpath("//*[@id='navbar']/div/div/div[2]/p");
	private By overviewTab 							= By.id("ctl00_ContentPlaceHolder1_TheNavigationButtonList_NavigationButtonRepeater_ctl00_NavigationLinkButton");
	private By onlineTab 							= By.id("ctl00_ContentPlaceHolder1_TheNavigationButtonList_NavigationButtonRepeater_ctl01_NavigationLinkButton");
	private By onsiteTab 							= By.id("ctl00_ContentPlaceHolder1_TheNavigationButtonList_NavigationButtonRepeater_ctl02_NavigationLinkButton");
	private By phoneTab 							= By.id("ctl00_ContentPlaceHolder1_TheNavigationButtonList_NavigationButtonRepeater_ctl03_NavigationLinkButton");
	private By overviewHeaderTxt	 				= By.xpath("//*[@id='ctl00_ContentPlaceHolder1_dvMain']/div/div[1]/div/div[2]/h2");
    private By testContactNowBtn                    = By.id("ctl00_ContentPlaceHolder1_coachingExperts_coachList_ctl00_contactNowButton");
    private By discussionTextInput					= By.id("ctl00_ContentPlaceHolder1_newDiscussionControl_newDiscussiontxt");
    private By sendNowBtn							= By.xpath("//span[@id='ctl00_ContentPlaceHolder1_newDiscussionControl_SubmitButton']/a");
	private By dateOfDiscussionMsg					= By.xpath("(//li[@id='liMessage']//p[@class='message-timestamp'])[1]");
	private By onlineHealthCoach					= By.xpath("//ul[@class='coaches']/li[1]");
    private By contactNowButton						= By.xpath("//ul[@class='coaches']/li[1]//div[@class='coach-contact-button']/a");
	private By messageTextArea						= By.xpath("//div[@class='input-to-text']/following-sibling::textarea");
	private String sendNowButton					= "//a[@id='lnkSubmit']";
	private By okayButton							= By.xpath("//a[text()='Okay']");	
	private By coachReply							= By.xpath("(//li[@id='liMessage']/div[@class='message-info']/p)[2]");
	private By verifySentMessage					= By.xpath("(//li[@id='liMessage']/div[@class='message-info']/p)[1]");
	private By messageAreaAlreadyContact			= By.xpath("//div[@class='coach-message-footer']/textarea[@id='ctl00_ContentPlaceHolder1_newDiscussionControl_newDiscussiontxt']");
	private By sendButtonAreadyContact				= By.xpath("//a[text()='Send Now']");
	private By verifyMessageAlreadyContact			= By.xpath("//ul[@class='coach-conversation']//div[@class='message-info']/p[1]");
	private By verifyPhoneText						= By.xpath("//div[@class='web-access-skip coaching-bottom-right']/h1");
	private By notificationBadge					= By.className("notification");
	private By latestCoachReplyMsg					= By.xpath("(//p[@id='latestmessage'])[1]");
	
	public HealthCoachingPage() {
		super();
		logger.info("Initializing the CoachingOverviewPage Object...");
	}
		
	/**
	 *  Verify Header Text are displayed over Overview tab on Health Coaching  page 
	 */
	public String verifyCoachingHeaderText(){
		String actualText = SeleniumUtil.getTextfromWebElement(coachingHeaderTxt,driver);
		return actualText;
	}


	/** Verify Overview Tab link */
	public boolean verifyOverviewTab() {
		boolean isPresent = false;
		if (SeleniumUtil.isElementDisplayed(overviewTab, driver)) {
			isPresent = true;
			Reporter.log("OverView tab is displayed");
		}
		return isPresent;
	}
	/**
	 * click on overview tab
	 */
	public void clickOnOverviewTab() {
		SeleniumUtil.click(overviewTab, driver);
	}
	

	/** Verify onlineTab Tab link */
	public boolean verifyOnlineTab() {
		boolean isPresent = false;
		if (SeleniumUtil.isElementDisplayed(onlineTab, driver)) {
			isPresent = true;
			Reporter.log("OverView tab is displayed");
		}
		return isPresent;
	}

	/** Verify onSiteTab Tab link */
	public boolean verifyOnSiteTab() {
		boolean isPresent = false;
		if (SeleniumUtil.isElementDisplayed(onsiteTab, driver)) {
			isPresent = true;
			Reporter.log("OverView tab is displayed");
		}
		return isPresent;
	}

	/** Verify Phone tab link */
	public boolean verifyOnPhoneTab() {
		boolean isPresent = false;
		if (SeleniumUtil.isElementDisplayed(phoneTab, driver)) {
			isPresent = true;
			Reporter.log("On Track client dropdown is displayed");
		}
		return isPresent;
	}

	/**
	 * Verify Header Text are displayed under overview tab on Health Coaching
	 * Page
	 */
	public boolean verifyOverviewHeaderText(String headerTxt) {

		boolean isPresent = false;
		if (SeleniumUtil.getTextfromWebElement(overviewHeaderTxt, driver).trim().equalsIgnoreCase(headerTxt.trim())) {
			isPresent = true;
			logger.info("Appropriate Header Text is displayed...");
		}

		return isPresent;
	}
	
	/**
	 * Click on Online tab
	 */
	public void clickOnlineTab(){
		SeleniumUtil.waitForElementToBeVisible(onlineTab, driver);
		SeleniumUtil.element(onlineTab, driver).click();
	}
	
	/**
	 * Click on Contact Now and type the message
	 */

	public void clickContactNowBtn(){
		SeleniumUtil.waitForElementToBeVisible(testContactNowBtn, driver);
		SeleniumUtil.sleep(2);
		SeleniumUtil.element(testContactNowBtn, driver).click();
		SeleniumUtil.waitForElementToBeVisible(discussionTextInput, driver);
		SeleniumUtil.element(discussionTextInput, driver).click();
		SeleniumUtil.element(discussionTextInput, driver).sendKeys("Test Message");
	}
	public void clickContactNowBtn(String discussionMsg){
		SeleniumUtil.waitForElementToBeVisible(testContactNowBtn, driver);
		SeleniumUtil.sleep(2);
		SeleniumUtil.element(testContactNowBtn, driver).click();
		SeleniumUtil.waitForElementToBeVisible(discussionTextInput, driver);
		SeleniumUtil.element(discussionTextInput, driver).click();
		SeleniumUtil.element(discussionTextInput, driver).sendKeys(discussionMsg);

		SeleniumUtil.element(sendNowBtn, driver).click();
	}
	
	/**
	 * Verify the message in published 
	 */

	

	public boolean verifyDiscussionMsg(Map<String,String> discussionMsg,String messageSender){

		SeleniumUtil.sleep(8);
		SeleniumUtil.waitForElementToBeVisible(dateOfDiscussionMsg, driver);
		boolean isCorrect = false;
		String actualDate = SeleniumUtil.element(dateOfDiscussionMsg, driver).getText();
		String expectedDate = SeleniumUtil.getUSTime();

		if(actualDate.contains(expectedDate)){

		By message = null;
		String messageContent = "";
		if(messageSender.equalsIgnoreCase("User")){
			message = verifySentMessage;
			messageContent = discussionMsg.get("discussionMessage");
		}
		else if(messageSender.equalsIgnoreCase("Coach")){
			message = coachReply;
			messageContent = discussionMsg.get("coachReply");
		}
		if(actualDate.contains(expectedDate) && (SeleniumUtil.element(message, driver).getText().equalsIgnoreCase(messageContent)))

			isCorrect = true;
		}
		return isCorrect;
	}
	

	
	

	public boolean verifyOnlineTabContactNow(String textMessage, String textMessageContact){
		
		boolean isPresent=false;
		verifyOnlineTab();
		SeleniumUtil.click(onlineTab, driver);
		if(SeleniumUtil.isElementPresent(onlineHealthCoach, driver)){

				SeleniumUtil.click(contactNowButton, driver);
				if(SeleniumUtil.isElementPresent(messageTextArea, driver)){
				SeleniumUtil.click(messageTextArea, driver);
				SeleniumUtil.sendKeysToWebElement(messageTextArea, textMessage, driver);
				SeleniumUtil.clickbyJS(sendNowButton, driver);
				SeleniumUtil.click(okayButton, driver);
				SeleniumUtil.click(contactNowButton, driver);

				SeleniumUtil.sleep(5);
				if(SeleniumUtil.getTextfromWebElement(verifySentMessage, driver).equalsIgnoreCase(textMessage))
					isPresent=true;
				}
				else if(SeleniumUtil.isElementPresent(messageAreaAlreadyContact, driver)){
					
					SeleniumUtil.click(messageAreaAlreadyContact, driver);
					SeleniumUtil.sendKeysToWebElement(messageAreaAlreadyContact, textMessageContact, driver);

					SeleniumUtil.click(sendButtonAreadyContact, driver);
					driver.navigate().refresh();
					SeleniumUtil.sleep(2);
					

					
					if(SeleniumUtil.getTextfromWebElement(verifyMessageAlreadyContact, driver).contains((textMessageContact)))
						isPresent=true;
				}
		}

			
			return isPresent;
	}
	
	
	public boolean phoneTabVerification(String phoneText){
			
			boolean isPresent = false;
			verifyOnPhoneTab();
			SeleniumUtil.click(phoneTab, driver);
			if(SeleniumUtil.getTextfromWebElement(verifyPhoneText, driver).contains(phoneText))
				
				isPresent = true;
		return isPresent;
	}
	public boolean verifyNotificationBadge(){
		
				boolean isPresent = false;
				isPresent = SeleniumUtil.element(notificationBadge, driver).isDisplayed();
				return isPresent;
			}
	public void clickLatestCoachReply(){
				SeleniumUtil.element(latestCoachReplyMsg, driver).click();
			}
}
	