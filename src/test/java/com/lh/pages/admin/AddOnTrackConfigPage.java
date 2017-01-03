package com.lh.pages.admin;

import static com.lh.helper.DriverFactory.driver;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import com.lh.utils.SeleniumUtil;
/**
 * <h2>Configure OnTrack instance!</h2> 
 * <p>
 * 
 * @author akash.vansil
 * @version 1.0
 * @since 2016-23-02
 */
public class AddOnTrackConfigPage {
	private static Logger logger = LogManager.getLogger(AddOnTrackConfigPage.class);
	private By addTrackConfig						=		By.xpath("//a[@href='/Administration/Ontrack/AddConfiguration.aspx']");	
	private By OnTrack_Client				  	    = 		By.id("ddlClients");
	private By OnTrack_TrackCampaign                =       By.xpath("//*[@id='selectCC_chosen']/a");
	private By OnTrack_selectCampaign               =       By.xpath("//*[@id='selectCC_chosen']/a/div/b");
	private By OnTrack_inputCampaign                =       By.xpath("//*[@id='selectCC_chosen']/div/div/input");
    private By OnTrack_TrackClient	                =       By.id("ddlTrack");
    private By OnTrack_upcomingChallengeStartDate1  =       By.xpath("//*[@id='ManageApps']/div[7]/table[2]/tbody/tr[3]/td[2]/img[1]");
    private By OnTrack_upcomingChallengeStartDate2  =       By.xpath("//*[@id='ManageApps']/div[7]/table[2]/tbody/tr[3]/td[4]/img[1]");
    private By OnTrack_upcomingChallengeStartDate3  =       By.xpath("//*[@id='ManageApps']/div[7]/table[2]/tbody/tr[3]/td[6]/img[1]");
    private By onTrack_ddWeeklyCommunication1       =       By.id("ddlCommunication1");
    private By onTrack_ddWeeklyCommunication2       =       By.id("ddlCommunication2");
    private By onTrack_ddWeeklyCommunication3       =       By.id("ddlCommunication3");
    private By onTrack_btnGenerate                  =       By.id("ctl00_ContentPlaceHolder1_AddOntrackAppConfig_CreateButton");
    private By OnTrack_logOut                       =       By.xpath("//a[@href='/Administration/AdminLogout.aspx']"); 
    
	
	public AddOnTrackConfigPage() {
		super();
		logger.info("Initializing the Add On Track Configuration Object...");
	
	}
	/** Verify Client Drop Down Element */
	
	public void clickAddConfig(){
		
		SeleniumUtil.click(addTrackConfig, driver);
		
	}
	public boolean verifyClientDropDown()
    {
  	  boolean isPresent = false;	  
 	   if(SeleniumUtil.isElementDisplayed(OnTrack_Client,driver)){
 		isPresent = true;
 	   }
	       return isPresent;
 	   }
    
	/** Select Client dropdown */
	   public void SelectClient()
     {
		 Select sel = new Select(driver.findElement(OnTrack_Client));
		 sel.selectByIndex(4);
	     
     }
	   
	   /** Select Air Client dropdown */
	   public void SelectAirClient()
     {
		 Select sel = new Select(driver.findElement(OnTrack_Client));
		 sel.selectByIndex(2);
	     
     } 
	   
	   /** Select General Client dropdown */
	   public void SelectGeneralClient()
     {
		 Select sel = new Select(driver.findElement(OnTrack_Client));
		 sel.selectByIndex(1);
	     
     }
	   
	   /** Select Apple Client dropdown */
	   public void SelectAppleClient()
     {
		 Select sel = new Select(driver.findElement(OnTrack_Client));
		 sel.selectByIndex(9);
	     
     }
		/** Select Client dropdown */
		   public void SelectPhysicalClient()
	     {
			 Select sel = new Select(driver.findElement(OnTrack_Client));
			 sel.selectByIndex(55);
		    
	     } 
	   
	   /** Select Client dropdown */
	   public void SelectWeightClient()
     {
		 Select sel = new Select(driver.findElement(OnTrack_Client));
		 sel.selectByIndex(8);
	    
     }
	   
	   
	   /** Select Client dropdown */
	   public void SelectAstraClient()
     {
		 Select sel = new Select(driver.findElement(OnTrack_Client));
		 sel.selectByIndex(12);
	    
     }
	
	 /** Verify track dropdown is present or not*/
	 public boolean verifyTrackCampaign()
	    {
	  	  boolean isPresent = false;	  
	 	   if(SeleniumUtil.isElementDisplayed(OnTrack_TrackCampaign,driver)){
	 		isPresent = true;
	 		
	 	   }
		       return isPresent;
	 	   }
	    
	 /** Select Track Campaign */
	 public void SelectTrackCampaign()
     {
	  driver.findElement(OnTrack_selectCampaign).click();
	  driver.findElement(OnTrack_inputCampaign).sendKeys("Automation -");
	  driver.findElement(OnTrack_inputCampaign).sendKeys(Keys.ENTER);
	   	  
     }
	 
	 /** Select Air Track Campaign */
	 public void SelectAirTrackCampaign()
     {
	  driver.findElement(OnTrack_selectCampaign).click();
	  driver.findElement(OnTrack_inputCampaign).sendKeys("NewPY2425 - 2425");
	  driver.findElement(OnTrack_inputCampaign).sendKeys(Keys.ENTER);
	   	  
     }
	 
	 
	 /** Select Track Campaign */
	 public void SelectTrackAppleCampaign()
     {
	  driver.findElement(OnTrack_selectCampaign).click();
	  driver.findElement(OnTrack_inputCampaign).sendKeys("nutrition -");
	  driver.findElement(OnTrack_inputCampaign).sendKeys(Keys.ENTER);
	 	  
     }
	 
	 
	 /** Select Track Campaign */
	 public void SelectTrackWeightCampaign()
     {
	  driver.findElement(OnTrack_selectCampaign).click();
	  driver.findElement(OnTrack_inputCampaign).sendKeys("001 -");
	  driver.findElement(OnTrack_inputCampaign).sendKeys(Keys.ENTER);
	   	  
     }
	 
	 
	 
	 /** Select Astra Track Campaign */
	 public void SelectAstraTrackCampaign()
     {
	  driver.findElement(OnTrack_selectCampaign).click();
	  driver.findElement(OnTrack_inputCampaign).sendKeys("Steps -");
	  driver.findElement(OnTrack_inputCampaign).sendKeys(Keys.ENTER);
	     	  
     }
	 /** Select Track Campaign */
	 public void SelectGeneralTrackCampaign()
     {
	  driver.findElement(OnTrack_selectCampaign).click();
	  driver.findElement(OnTrack_inputCampaign).sendKeys("General -");
	  driver.findElement(OnTrack_inputCampaign).sendKeys(Keys.ENTER);
	   
     }
	 
	 /** Select Phsical Track Campaign */
	 public void SelectPhysicalTrackCampaign()
     {
	  driver.findElement(OnTrack_selectCampaign).click();
	  driver.findElement(OnTrack_inputCampaign).sendKeys("Physical -");
	  driver.findElement(OnTrack_inputCampaign).sendKeys(Keys.ENTER);
	     	  
     }
	 
	 /** Select Track Client*/
	 public void selectTrackClient()
	 {
		 Select sel1 = new Select(driver.findElement(OnTrack_TrackClient));
		 sel1.selectByIndex(4);
		 
		 
	 }
	 
	
	 /** Select Challenge Weekly Communication1*/
	 public void selectChallengeWeeklyCommunication()
	 {
		 Select sel1 = new Select(driver.findElement(onTrack_ddWeeklyCommunication1));
		 sel1.selectByIndex(1);
		 
		 
	 }
	 
	 /** Select Challenge Weekly Communication2*/
	 public void selectChallengeWeeklyCommunication1()
	 {
		 Select sel1 = new Select(driver.findElement(onTrack_ddWeeklyCommunication2));
		 sel1.selectByIndex(1);
		 
		 
	 }
	
	 /** Select Challenge Weekly Communication3*/
	 public void selectChallengeWeeklyCommunication2()
	 {
		 Select sel1 = new Select(driver.findElement(onTrack_ddWeeklyCommunication3));
		 sel1.selectByIndex(1);
		 
		 
	 }
	
	 /** Select Track HeartHealthTrack*/
	 public void selectHeartHealthTrack()
	 {
		 Select sel1 = new Select(driver.findElement(OnTrack_TrackClient));
		 sel1.selectByIndex(1);
		
		 
	 }
	
	 /** Select Track WeightManagement*/
	 public void selectWeightManagementTrack()
	 {
		 Select sel1 = new Select(driver.findElement(OnTrack_TrackClient));
		 sel1.selectByIndex(2);
		
		 
	 }
	
	 /** Select Track PhysicalActivity*/
	 public void selectPhysicalActivityTrack()
	 {
		 Select sel1 = new Select(driver.findElement(OnTrack_TrackClient));
		 sel1.selectByIndex(3);
		 
	 }
	
	 /** Select Track Steps*/
	 public void selectStepsTrack()
	 {
		 Select sel1 = new Select(driver.findElement(OnTrack_TrackClient));
		 sel1.selectByIndex(5);
		 
	 }
	
	 /** Select Nutrition Steps*/
	 public void selectNutritionTrack()
	 {
		 Select sel1 = new Select(driver.findElement(OnTrack_TrackClient));
		 sel1.selectByIndex(6);
		 	 
	 }
	

	 /** Select GeneralHealth Steps*/
	 public void selectGeneralHealthTrack()
	 {
		 Select sel1 = new Select(driver.findElement(OnTrack_TrackClient));
		 sel1.selectByIndex(7);
		 
	 }
	 
	
	 
	 /** Select challenge Duration theme1 radio option*/
	 public void selectChallengeDurationForTheme1()
     {
		selRadioOption("ctl00$ContentPlaceHolder1$AddOntrackAppConfig$Theme1$Challenge",1);
		               
     }
	 /** Select challenge Duration theme2 radio option*/
	 public void selectChallengeDurationForTheme2()
     {
		 SeleniumUtil.selRadioOption("ctl00$ContentPlaceHolder1$AddOntrackAppConfig$Theme2$Challenge",1,driver);
		  
     }
	 
	 /** Select Physical challenge Duration theme2 radio option*/
	 public void selectPhysicalChallengeDurationForTheme2()
     {
		 SeleniumUtil.selRadioOption("ctl00$ContentPlaceHolder1$AddOntrackAppConfig$Theme2$Challenge",2,driver);
		  
     }
	 /** Select challenge Duration theme3 radio option*/
	 public void selectChallengeDurationForTheme3()
     {
		 SeleniumUtil.selRadioOption("ctl00$ContentPlaceHolder1$AddOntrackAppConfig$Theme3$Challenge",1,driver);
		  
     }
	 
	 /** Select challenge Duration theme3 radio option*/
	 public void selectPhysicalChallengeDurationForTheme3()
     {
		 SeleniumUtil.selRadioOption("ctl00$ContentPlaceHolder1$AddOntrackAppConfig$Theme3$Challenge",2,driver);
		  
     }
	
	 
	
	 /** Select upcoming challenege date for theme1*/
	 public void selectUpcomingStartDateForTheme1()
	 {
		 SeleniumUtil.click(OnTrack_upcomingChallengeStartDate1, driver);
		 SeleniumUtil.sleep(2);
		 SeleniumUtil.matchDate("16",01,2016, driver);
		
	 }
	 
	 /** Select upcoming challenege date for theme1*/
	 public void selectUpcomingStartDateForPhysicalActivityTheme1()
	 {
		 SeleniumUtil.click(OnTrack_upcomingChallengeStartDate1, driver);
		 SeleniumUtil.matchDate("7",9,2015, driver);
		
	 }
	 
	 
	 /** Select upcoming challenege date for theme2*/
		  public void selectUpcomingStartDateForTheme2()
	 {
		 SeleniumUtil.click(OnTrack_upcomingChallengeStartDate2, driver);
		 SeleniumUtil.matchDate("30",03,2016, driver);
		 	 
	 }
      /** Select upcoming challenege date for theme3*/			
	 public void selectUpcomingStartDateForTheme3()
	 {
		 SeleniumUtil.click(OnTrack_upcomingChallengeStartDate3, driver);
		 SeleniumUtil.matchDate("11",7,2016,driver);
		 
	 }
	 /** Select challenge weekly communication dropdown*/		
	 public void challengeWeeklyCommunication()
	 {
		 selectChallengeWeeklyCommunication();
		 selectChallengeWeeklyCommunication1();
		 selectChallengeWeeklyCommunication2();
		 		 
	}
	 /**
		 * 
		 * @param name,i
		 * @Select radio option
		 */
		
	 public void selRadioOption(String name,int i)
	 {
		 try{
			 List<WebElement> RadButtonList = driver.findElements(By.name(name));
		      RadButtonList.get(i).click();
			  Reporter.log("User select the radio option");
			
		 }
		 catch(Exception e)
		 {
				Reporter.log("Failed to click on element");
				 
		 }
		 
	 }
	 /**
		 * 
		 *User click on generate button 
		 */
		
	  public void generateOnTrackInstance()
	  {
		  SeleniumUtil.click(onTrack_btnGenerate, driver);
		  Reporter.log("On Track instance is created");
		  SeleniumUtil.sleep(1);
	  }
	
	 
}
