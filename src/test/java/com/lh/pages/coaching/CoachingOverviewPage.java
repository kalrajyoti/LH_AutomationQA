package com.lh.pages.coaching;

import static com.lh.helper.DriverFactory.driver;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.testng.Reporter;
import com.lh.utils.SeleniumUtil;

/**
 * <h2>Click health coaching and confirm overview,online onsite and phone tab</h2>
 * <p>
 * 
 * @author akash.vansil
 * @version 1.0
 * @since 2016-08-06
 */

public class CoachingOverviewPage {

	private static Logger logger = LogManager.getLogger(CoachingOverviewPage.class);
	private By resourcesTab = By
			.xpath("//*[@id='navbar']/div/div/div[3]/ul[2]/li[4]/span");
	private By coachingLnk = By.xpath("//*[@id='navbar']/div/div/div[3]/ul[2]/li[4]/ul/li[6]/a");
	private By coachingHeaderTxt = By.xpath("//*[@id='navbar']/div/div/div[2]/p");
	private By overviewTab = By
			.id("ctl00_ContentPlaceHolder1_TheNavigationButtonList_NavigationButtonRepeater_ctl00_NavigationLinkButton");
	private By onlineTab = By
			.id("ctl00_ContentPlaceHolder1_TheNavigationButtonList_NavigationButtonRepeater_ctl01_NavigationLinkButton");
	private By onsiteTab = By
			.id("ctl00_ContentPlaceHolder1_TheNavigationButtonList_NavigationButtonRepeater_ctl02_NavigationLinkButton");
	private By phoneTab = By
			.id("ctl00_ContentPlaceHolder1_TheNavigationButtonList_NavigationButtonRepeater_ctl03_NavigationLinkButton");
	private By overviewHeaderTxt = By.xpath("//*[@id='ctl00_ContentPlaceHolder1_dvMain']/div/div[1]/div/div[2]/h2");
                                           
	public CoachingOverviewPage() {
		logger.info("Initializing the CoachingOverviewPage Object...");
	}

	/**
	 * Select and opens the Health Coaching page
	 */
	public void clickHealthCoaching() {

		SeleniumUtil.sleep(1);
		SeleniumUtil.hover(resourcesTab, driver);
		SeleniumUtil.click(coachingLnk, driver);
		logger.info("Clicked on the Health Coaching sub menu.");

	}

		
	/**
	 *  Verify Header Text are displayed over Overview tab on Health Coaching  page 
	 */
	public boolean verifyCoachingHeaderText(String bannerTxt){

		boolean isPresent = false;

		if(SeleniumUtil.getTextfromWebElement(coachingHeaderTxt,driver).contains(bannerTxt)){
			isPresent = true;
			logger.info("Appropriate Header Text is displayed...");
		}

		return isPresent;
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

		if (SeleniumUtil.getTextfromWebElement(overviewHeaderTxt, driver).contains(headerTxt)) {
			isPresent = true;
			logger.info("Appropriate Header Text is displayed...");
		}

		return isPresent;
	}
	
	
	

}