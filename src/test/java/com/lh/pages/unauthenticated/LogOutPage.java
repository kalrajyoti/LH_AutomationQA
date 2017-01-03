package com.lh.pages.unauthenticated;

import com.lh.utils.SeleniumUtil;
import org.openqa.selenium.By;
import org.testng.Reporter;

import static com.lh.helper.DriverFactory.driver;

public class LogOutPage {
	private By logoutLnk 		= By.id("LogoutLink");
	private By logoutLnkOntarget		= By.xpath("//div[@id='header-old']//ul[@class='list-inline-block header-links']/li/a[@href='/logout.aspx']");
	private By logoutLnkAdmin	= By.xpath("//a[@href='/Administration/AdminLogout.aspx']");
	
	/**
	 * Clicks on the LogOut link present on the top right corner of the website
	 */
	public void clickLogoutLink(String portalType) {
		if(portalType.equalsIgnoreCase("OnTarget")){
			SeleniumUtil.switchToDefaultContent(driver);
			SeleniumUtil.sleep(2);
			SeleniumUtil.waitForElementToBeVisible(logoutLnk, driver);
			driver.findElement(logoutLnkOntarget).click();
		}
		else if(portalType.equalsIgnoreCase("Admin")){
			
			SeleniumUtil.switchToDefaultContent(driver);
			SeleniumUtil.sleep(2);
			SeleniumUtil.waitForElementToBeVisible(logoutLnkAdmin, driver);
			driver.findElement(logoutLnkAdmin).click();
		}
		
		else{
			SeleniumUtil.sleep(3);
			SeleniumUtil.switchToDefaultContent(driver);
			Reporter.log("Clicking on the  the logoutLinkLocator identified by- " + logoutLnk);
			driver.findElement(logoutLnk).click();
		}
	}

}
