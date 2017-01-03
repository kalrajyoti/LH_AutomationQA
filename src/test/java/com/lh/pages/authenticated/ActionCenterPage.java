package com.lh.pages.authenticated;

import static com.lh.helper.DriverFactory.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Reporter;

import com.lh.testConfig.TestProperty;
import com.lh.utils.SeleniumUtil;

/**
 * @author arpit.tandon
 * @version 1.0
 * @since 2015-06-29
 */

	public class ActionCenterPage extends MainPage {

	private static Logger logger = LogManager.getLogger(PALogPage.class);
	private By healthRisk=By.xpath("//a[@id='ctl00_ActionCenter_ActionCenterBlockRepeater_ctl00_MultipleViews_ctl00_blockLink']/div/div/h3");
	private By healthManager =By.xpath("//a[@id='ctl00_ActionCenter_ActionCenterBlockRepeater_ctl01_MultipleViews_ctl00_blockLink']/div/div/h3");
	private By healthCoaching  =By.xpath("//a[@id='ctl00_ActionCenter_ActionCenterBlockRepeater_ctl03_MultipleViews_ctl00_blockLink']/div/div/h3");
	private By community =By.xpath("//a[@id='ctl00_ActionCenter_ActionCenterBlockRepeater_ctl03_MultipleViews_ctl00_blockLink']/div/div/h3");
	private By healthChallenge =By.xpath("//a[@id='ctl00_ActionCenter_ActionCenterBlockRepeater_ctl04_MultipleViews_ctl00_blockLink']/div/div/h3");
	private By lifestyleManager = By.xpath("//a[@id='ctl00_ActionCenter_ActionCenterBlockRepeater_ctl06_MultipleViews_ctl00_blockLink']/div/div/h3");
	private By biometric =By.xpath("//a[@id='ctl00_ActionCenter_ActionCenterBlockRepeater_ctl05_MultipleViews_ctl00_blockLink']/div/div/h3");
	boolean flag;
	
	public ActionCenterPage() {
		super();
		Reporter.log("Initializing the HealthAssessmentPage Object...");
		// Check that we're on the right page.
		if (!(driver.getTitle().equalsIgnoreCase(TestProperty.CLIENT_PORTAL_TITLE))) {
			logger.error("This is not the Health Assessment page");
			throw new IllegalStateException(
					"This is not the HealthAssessmentPage page");
		}
		Reporter.log("Initialized the HealthAssessmentPage page object");
	}

	/**
	 *  method to get health risk text 
	 *  @return health risk text
	 */
	public String gethealthRiskText(){
		String healthRiskText=SeleniumUtil.getTextfromWebElement(healthRisk, driver).trim();
		Reporter.log("Returning the text found out of the health risk block in the Action Center"+healthRiskText);
		return healthRiskText;
	}


	/**
	 *  method to get biometric text 
	 *  @return biometric text
	 */
	public String getBiometricText(){
		String biometricText=SeleniumUtil.getTextfromWebElement(biometric, driver).trim();
		Reporter.log("Returning the text found out of the biometric screening block in the Action Center"+biometricText);
		return biometricText;
	}

	/**
	 *  method to get health manager text 
	 *  @return health manager text
	 */

	public String gethealthManagerText(){
		String healthManagerText=SeleniumUtil.getTextfromWebElement(healthManager, driver).trim();
		Reporter.log("Returning the text found out of the health manager block in the Action Center"+healthManagerText);
		return healthManagerText;
	}

	/**
	 *  method to get health coaching text 
	 *  @return health coaching text
	 */

	public String getHealthCoachingText(){
		String healthCoachingText=SeleniumUtil.getTextfromWebElement(healthCoaching, driver).trim();
		Reporter.log("Returning the text found out of the health manager block in the Action Center"+healthCoachingText);
		return healthCoachingText;
	}
	/**
	 * method to get Community
	 * @return communityText
	 */
	public String getCommunity(){
		String communityText=SeleniumUtil.getTextfromWebElement(community, driver).trim();
		Reporter.log("Returning the text found out of the community block in the Action Center"+communityText);
		return communityText;
	}

//	/**
//	 *  method to get wellness text 
//	 *  @return wellness text
//	 */
//	public String getWellnessText(){
//
//		String wellnessText=SeleniumUtil.getTextfromWebElement(wellness, driver);
//
//		Reporter.log("Returning the text found out of the wellness block in the Action Center"+wellnessText);
//
//		return wellnessText;
//
//	}
	
	/**
	 *  method to get health challenges text 
	 *  @return health challenges text
	 */
	public String getHealthChallengesText(){
		String healthChallengesText=SeleniumUtil.getTextfromWebElement(healthChallenge, driver).trim();
		Reporter.log("Returning the text found out of the health challenges block in the Action Center"+healthChallengesText);
		return healthChallengesText;

	}

	public String getLifestyleManagerText(){
		String lifestyleManagerText=SeleniumUtil.getTextfromWebElement(lifestyleManager, driver).trim();
		Reporter.log("Returning the text found out of the health challenges block in the Action Center"+lifestyleManagerText);
		return lifestyleManagerText;
	}

	/**
	 * 
	 * @param expectedText
	 * @return flag
	 */

	public boolean verifyhealthRiskText(String expectedText){
		try{
		if(expectedText.equalsIgnoreCase(gethealthRiskText()))

			flag = true;
		Reporter.log("Return boolean value if Health Risk Text is present");
        }catch(Exception e){
	
	e.printStackTrace();
	flag = false;
}
	return flag;

	}

	/**
	 * 
	 * @param expectedText
	 * @return flag
	 */
	public boolean verifybiometricText(String expectedText){
		try{
		if(getBiometricText().contains(expectedText))
			flag=true;
		
		Reporter.log("Return boolean value if Biometric Text is present");
		}catch(Exception e){
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}


	/**
	 * 
	 * @param expectedText
	 * @return flag
	 */
	public boolean verifyhealthManagerText(String expectedText){
		try{
		if(expectedText.equalsIgnoreCase(gethealthManagerText())){
			flag=true;
		}
		Reporter.log("Return boolean value if Health Manager Text is present");
		}catch(Exception e){	
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	/**
	 * 
	 * @param expectedText
	 * @return flag
	 */

	public boolean verifyCommunityText(String expectedText){
		try{
		if(expectedText.equalsIgnoreCase(getCommunity()))
			flag=true;
		Reporter.log("Return boolean value if Community Text is present");
		}catch(Exception e){
			e.printStackTrace();
			flag = false;
		}
		return flag;

	}

//	/**
//	 * 
//	 * @param expectedText
//	 * @return iswellnessTextPresent
//	 */
//
//	public boolean verifyWellnessText(String expectedText){
//
//		boolean iswellnessTextPresent = false;
//		testDataObj = new ExcelFileUtility();
//
//		if(expectedText.equalsIgnoreCase(getWellnessText())){
//
//			iswellnessTextPresent=true;
//		}
//		Reporter.log("Return boolean value if Wellness Text is present");
//		return iswellnessTextPresent;
//
//	}

	/**
	 * 
	 * @param expectedText
	 * @return flag
	 */

	public boolean verifyHealthChallenges(String expectedText){
		try{
		if(expectedText.equalsIgnoreCase(getHealthChallengesText())){
		flag=true;
		}
		Reporter.log("Return boolean value if Health Challenges Text is present");
		}catch(Exception e){
			e.printStackTrace();
			flag=false;
}
		return flag;
	}

	/**
	 * 
	 * @param expectedText
	 * @return flag
	 */
	public boolean verifyHealthCoachingText(String expectedText){
		try{
		if(expectedText.equalsIgnoreCase(getHealthCoachingText())){

			flag=true;
		}
		Reporter.log("Return boolean value if Health Coaching Text is present");
		}catch(Exception e){
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
	
	/**
	 * 
	 * @param expectedText
	 * @return flag
	 */
	public boolean verifyLifestyleManagerText(String expectedText){
	try{
	if(expectedText.equalsIgnoreCase(getLifestyleManagerText()))
		flag=true;
	}catch(Exception e){
		flag=false;
	}
	Reporter.log("Return boolean value if Lifestyle Manager Text is present");
	return flag;
}
}

