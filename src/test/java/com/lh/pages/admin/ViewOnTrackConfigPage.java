package com.lh.pages.admin;

import static com.lh.helper.DriverFactory.driver;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.lh.utils.SeleniumUtil;

public class ViewOnTrackConfigPage {

	private static Logger logger = LogManager.getLogger(ViewOnTrackConfigPage.class);

    private By onTrack_Add                      =       By.xpath("//*[@id='ManageApps']/div[6]/div/ul/li[2]/a");
    private By onTrack_View                     =       By.xpath("//*[@id='ManageApps']/div[6]/div/ul/li[3]/a");
    private By OnTrack_Client				  	= 		By.id("ddlClients");
	private By OnTrack_Challenge	  	        =	    By.id("ctl00_ContentPlaceHolder1_ViewAppConfig1_ddlChallengeType");
	private By OnTrack_Track                    =       By.id("ddlTrack");
	private By OnTrack_DateRange_To             =       By.xpath("//*[@id='ManageApps']/div[7]/table/tbody/tr[2]/td[4]/img[1]");
	private By OnTrack_DateRange_From             =     By.xpath("//*[@id='ManageApps']/div[7]/table/tbody/tr[2]/td[4]/img[2]");
	private By OnTrack_Submit                   =       By.id("ctl00_ContentPlaceHolder1_ViewAppConfig1_btnSubmit");
	public  By OnTrack_ClientResult             =       By.id("ctl00_ContentPlaceHolder1_ViewAppConfig1_GrdConfiguration_ctl02_lblClientName");
	public  By OnTrack_CheckRecords             =       By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ViewAppConfig1_GrdConfiguration']/tbody/tr[2]/td/span");
	
	public  By onTrack_tableRsultHeader_1       =       By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ViewAppConfig1_GrdConfiguration']/tbody/tr[1]/th[1]");
	public  By onTrack_tableRsultHeader_2       =       By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ViewAppConfig1_GrdConfiguration']/tbody/tr[1]/th[2]");
	public  By onTrack_tableRsultHeader_3       =       By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ViewAppConfig1_GrdConfiguration']/tbody/tr[1]/th[3]");
	public  By onTrack_tableRsultHeader_4       =       By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ViewAppConfig1_GrdConfiguration']/tbody/tr[1]/th[4]");
	public  By onTrack_tableRsultHeader_5       =       By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ViewAppConfig1_GrdConfiguration']/tbody/tr[1]/th[5]");
	public  By onTrack_tableRsultHeader_6       =       By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ViewAppConfig1_GrdConfiguration']/tbody/tr[1]/th[6]");
	public  By onTrack_tableRsultHeader_7       =       By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ViewAppConfig1_GrdConfiguration']/tbody/tr[1]/th[7]");
	public  By onTrack_tableRsultHeader_8       =       By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ViewAppConfig1_GrdConfiguration']/tbody/tr[1]/th[8]");
	
	public  By onTrack_tableRsultHeader_9       =       By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ViewAppConfig1_GrdConfiguration']/tbody/tr[1]/th[9]");
	public  By onTrack_tableRsultHeader_10       =       By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ViewAppConfig1_GrdConfiguration']/tbody/tr[1]/th[10]");
	public  By onTrack_tableRsultHeader_11       =       By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ViewAppConfig1_GrdConfiguration']/tbody/tr[1]/th[11]");
	public  By onTrack_tableRsultHeader_12       =       By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ViewAppConfig1_GrdConfiguration']/tbody/tr[1]/th[12]");
	public  By onTrack_tableRsultHeader_13       =       By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ViewAppConfig1_GrdConfiguration']/tbody/tr[1]/th[13]");
	public  By onTrack_tableRsultHeader_14       =       By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ViewAppConfig1_GrdConfiguration']/tbody/tr[1]/th[14]");
	public  By onTrack_tableRsultHeader_15       =       By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ViewAppConfig1_GrdConfiguration']/tbody/tr[1]/th[15]");
	public  By onTrack_tableRsultHeader_16       =       By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ViewAppConfig1_GrdConfiguration']/tbody/tr[1]/th[16]");
	private  By onTrack_edit                      =       By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ViewAppConfig1_GrdConfiguration_ctl02_linkEdit']");
	private static WebElement datePicker; 
	private static List<WebElement> noOfColumns; 
	private static List<String> monthList = Arrays.asList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"); 
	private static String calMonth = null; 
	private static  String calYear = null; 
	private static boolean dateNotFound; 
	
	
	
    

	public ViewOnTrackConfigPage() {
		super();
		logger.info("Initializing the View On Track Configuration Object...");
		logger.info("Initialized the ViewOnTrackConfig page object");
	
	}
	
	  public boolean verifyOnTrackConfigLink()
      {
   	   boolean isPresent = false;
  		if(SeleniumUtil.isElementDisplayed(onTrack_Add, driver)){
  			isPresent = true;
  			logger.info("On Track config link is displayed");
  		}
  		return isPresent;
      }
	  
	  public boolean verifyViewOnTrackConfigLink()
      {
   	   boolean isPresent = false;
  		if(SeleniumUtil.isElementDisplayed(onTrack_View, driver)){
  			isPresent = true;
  			logger.info("On Track view config link is displayed");
  		}
  		return isPresent;
      }
	  
      
      public boolean verifyClientDropDown()
      {
    	  boolean isPresent = false;	  
   	   if(SeleniumUtil.isElementDisplayed(OnTrack_Client,driver)){
   		isPresent = true;
   	   logger.info("On Track client dropdown is displayed");
   	   }
	       return isPresent;
   	   }
      
      
      public void clickOnTrackLink()
      {
    	  SeleniumUtil.click(onTrack_View, driver);
    }
      
      public void SelectClient()
      {
    	  SeleniumUtil.sendKeysToWebElement(OnTrack_Client,"confluence",driver);
    }
      
      
      
      
      public boolean verifyTrackDropDown()
      {
    	  boolean isPresent = false;
   	   if(SeleniumUtil.isElementDisplayed(OnTrack_Track,driver)){
   		   isPresent = true;
   		logger.info("On Track Track dropdown is displayed");
   	   }
	return isPresent;
   	   
      }
      
      public void SelectTrack()
      {
    	  SeleniumUtil.sendKeysToWebElement(OnTrack_Track,"Weight Management", driver);
    	  
      }
      
      public boolean verifyChallengeTypeDropDown()
      {
    	  boolean isPresent = false;
   	   if(SeleniumUtil.isElementDisplayed(OnTrack_Challenge,driver)){
   		   isPresent = true;
   		logger.info("On Track Challenge dropdown is displayed");
   	   }
	return isPresent;
   	   
      }
      
      public void selectChallengeType()
      {
    	  SeleniumUtil.sendKeysToWebElement(OnTrack_Challenge,"nutrition", driver);
    	  
    	  
      
      }
      public boolean verifyDateRangeFrom()
      {
    	  boolean isPresent = false;
   	   if(SeleniumUtil.isElementDisplayed(OnTrack_DateRange_From,driver)){
   		   isPresent = true;
   		logger.info("On Track DateRangeFrom is displayed");
   	   }
	return isPresent;
   	   
      }
      
      
      public void verifyDateRangeFrom(String FromDate)
      {
   	   SeleniumUtil.isElementDisplayed(OnTrack_DateRange_From,driver);
   	   logger.info("On Track DateRangeFrom is displayed");
   	   
      }
      
      public boolean verifyDateRangeTo()
      {
    	  boolean isPresent = false;
   	   if(SeleniumUtil.isElementDisplayed(OnTrack_DateRange_To,driver)){
   		   isPresent = true;
   		logger.info("On Track DateRangeTo is displayed");
   	   }
	return isPresent;
   	   
      }
      
      public void verifyDateRangeTo(String toDate)
      {
   	  SeleniumUtil.isElementDisplayed(OnTrack_DateRange_To,driver);
   	  logger.info("On Track DateRangeTo is displayed");
 	   
      }
      
      public boolean verifySubmitButton()
      {
    	  boolean isPresent = false;
   	   if(SeleniumUtil.isElementDisplayed(OnTrack_Submit,driver)){
   		   isPresent = true;
   		logger.info("On Track Submit is displayed");
   	   }
	return isPresent;
   	   
      }
      
      
      public void clickSubmitButton()
      {
      SeleniumUtil.click(OnTrack_Submit,driver);
   	  }
      
      public boolean recordDisplay(String client)
      {
    	
    	   boolean isPresent;
    	  isPresent = SeleniumUtil.element(OnTrack_ClientResult, driver).getText().equalsIgnoreCase(client);	  
  	      return isPresent;
    	  
      }
     /* public boolean verifyTableResult()
      {
    	 boolean checkRecords;
    	  if(checkRecords=true)
    	  {
    	noRecords("No records found");
    	click_OnTrackAddConfiguration();
    		  }
    	  else
    	  {
    		  clientWithRecords("GRCP");
    	  }
		return checkRecords;
    		  	  
    	}*/
      
      public boolean noRecords(String records)
      {
    	
    	  boolean notFound;
    	  notFound = SeleniumUtil.element(OnTrack_CheckRecords, driver).getText().equalsIgnoreCase(records);
    	  return notFound;
    	}
      
      
      public boolean clientWithRecords(String client)
      {
    	  boolean isPresent;
    	  isPresent = SeleniumUtil.element(OnTrack_CheckRecords, driver).getText().equalsIgnoreCase(client);
    	  return isPresent;
      }
      
      
      public boolean verifyClientHeader(String header1)
      {
    	
    	  boolean isPresent;
    	  isPresent = SeleniumUtil.element(onTrack_tableRsultHeader_1, driver).getText().equalsIgnoreCase(header1);
    	  return  isPresent;
        
      }
      
      public boolean verifyTrackHeader(String header2)
      {
    	
    	  boolean isPresent;
    	  isPresent = SeleniumUtil.element(onTrack_tableRsultHeader_2, driver).getText().equalsIgnoreCase(header2);
    	  return  isPresent;
        
      }
      
      public boolean verifyCampaignNameHeader(String header3)
      {
          boolean isPresent;
    	  isPresent = SeleniumUtil.element(onTrack_tableRsultHeader_3, driver).getText().equalsIgnoreCase(header3);
    	  return  isPresent; 
      }
      
      public boolean verifyCampaignStartDateHeader(String header4)
      {
    
    	  boolean isPresent;
    	  isPresent = SeleniumUtil.element(onTrack_tableRsultHeader_4, driver).getText().equalsIgnoreCase(header4);
    	  return  isPresent;    
      }
      
  
      public boolean verifyStatusHeader(String header5)
      {
    	  boolean isPresent;
    	  isPresent = SeleniumUtil.element(onTrack_tableRsultHeader_5, driver).getText().equalsIgnoreCase(header5);
    	  return  isPresent;
      }
      
      public boolean verifyChallengeNameHeader(String header6)
      {
    	  boolean isPresent;
    	  isPresent = SeleniumUtil.element(onTrack_tableRsultHeader_6, driver).getText().equalsIgnoreCase(header6);
    	  return  isPresent;
      }
      
      public boolean verifyChallengeTypeHeader(String header7)
      {
    	  boolean isPresent;
    	  isPresent = SeleniumUtil.element(onTrack_tableRsultHeader_7, driver).getText().equalsIgnoreCase(header7);
    	  return  isPresent;
      }
      public boolean verifyThemeStartDateeHeader(String header8)
      {
    	  boolean isPresent;
    	  isPresent = SeleniumUtil.element(onTrack_tableRsultHeader_8, driver).getText().equalsIgnoreCase(header8);
    	  return  isPresent;
      }
      
      public boolean verifyChallenge2NameHeader(String header9)
      {
    	  boolean isPresent;
    	  isPresent = SeleniumUtil.element(onTrack_tableRsultHeader_9, driver).getText().equalsIgnoreCase(header9);
    	  return  isPresent;
      }
      
      public boolean verifyChallenge2TypeHeader(String header10)
      {
    	  boolean isPresent;
    	  isPresent = SeleniumUtil.element(onTrack_tableRsultHeader_10, driver).getText().equalsIgnoreCase(header10);
    	  return  isPresent;
      }
      
      public boolean verifyTheme2StartDateHeader(String header11)
      {
    	  boolean isPresent;
    	  isPresent = SeleniumUtil.element(onTrack_tableRsultHeader_11, driver).getText().equalsIgnoreCase(header11);
    	  return  isPresent;
      }
      
      public boolean verifyChallenge3NameHeader(String header12)
      {
    	  boolean isPresent;
    	  isPresent = SeleniumUtil.element(onTrack_tableRsultHeader_12, driver).getText().equalsIgnoreCase(header12);
    	  return  isPresent;
      }
      
      public boolean verifyChallenge3TypeHeader(String header13)
      {
    	  boolean isPresent;
    	  isPresent = SeleniumUtil.element(onTrack_tableRsultHeader_13, driver).getText().equalsIgnoreCase(header13);
    	  return  isPresent;
      }
      
      public boolean verifyTheme3StartDateHeader(String header14)
      {
    	  boolean isPresent;
    	  isPresent = SeleniumUtil.element(onTrack_tableRsultHeader_14, driver).getText().equalsIgnoreCase(header14);
    	  return  isPresent;
      }
      
      public boolean verifyEditHeader(String header15)
      {
    	  boolean isPresent;
    	  isPresent = SeleniumUtil.element(onTrack_tableRsultHeader_15, driver).getText().equalsIgnoreCase(header15);
    	  return  isPresent;
      }
      
      public boolean verifyDeleteHeader(String header16)
      {
    	  boolean isPresent;
    	  isPresent = SeleniumUtil.element(onTrack_tableRsultHeader_16, driver).getText().equalsIgnoreCase(header16);
    	  return  isPresent;
      }
      
      public void selectDateRangeTo()
      {
    	  SeleniumUtil.click(OnTrack_DateRange_To, driver);
    	  SeleniumUtil.matchDate("4",1, 2016, driver);	  
      }
      
      public void selectDateRangeFrom()
      {
    	  SeleniumUtil.click(OnTrack_DateRange_From, driver);
    	   matchDateFrom("7",2, 2016);
      }
      
      public void displayAllResults()
      {
    	   clickSubmitButton();  
      }
      
      public boolean verifyDisplayResult()
      {
    	  boolean  isPresent;
    	  isPresent = SeleniumUtil.element(onTrack_tableRsultHeader_1, driver) != null;
    	  return isPresent;	  
    	  }
    	
      public boolean verifyEditLinkIsPresent (String isPresent){
  		boolean isElementPresent=false;
 		
  		if(driver.findElement(By.linkText(isPresent)) != null)	
  			isElementPresent=true;
  
    	  return isElementPresent;	
  	}	

      public void clickOnEditLink()
      {
    	  SeleniumUtil.click(onTrack_edit, driver);
    	  logger.info("On Track click on add configuration is clicked");
    	  
      }
      
      public boolean verifyDeleteLinkIsPresent (String isPresent){
    	  boolean isElementPresent=false;
    	  
    	  if(driver.findElement(By.linkText(isPresent)) != null)
    		  isElementPresent=true;
		
    	  return isElementPresent;
    	}
      

      
      public void delete_OnTrackConfiguration()
      {
    	  
      }  
      public AddOnTrackConfigPage click_OnTrackAddConfiguration()
      {
    	  SeleniumUtil.click(onTrack_Add, driver);
    	  logger.info("On Track click on add configuration is clicked");
    	  return new AddOnTrackConfigPage();
      }
      
      public  void matchDateFrom(String expDate,int expMonth,int expYear)
      {
    	  dateNotFound = true; 
 		 while(dateNotFound) {
 			calMonth = driver.findElement(By.className("ui-datepicker-month")).getText();
 			calYear = driver.findElement(By.className("ui-datepicker-year")).getText(); 
 			if(monthList.indexOf(calMonth)+1 == expMonth && (expYear == Integer.parseInt(calYear)))
 			    {
 				selectDateFrom(expDate); 
 					dateNotFound = false;
 					}
 				else if(monthList.indexOf(calMonth)+1 < expMonth && (expYear == Integer.parseInt(calYear)) || expYear > Integer.parseInt(calYear))
 				{
 					driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[2]/span")).click(); 	
 				}	
 				else if(monthList.indexOf(calMonth)+1 > expMonth && (expYear == Integer.parseInt(calYear)) || expYear < Integer.parseInt(calYear))
 				{
 					driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[1]/span")).click();
 				}
 			}}

      public void selectDateFrom(String date) 
      { 
      	datePicker = driver.findElement(By.id("ui-datepicker-div")); 
      	 List<WebElement> rows=datePicker.findElements(By.tagName("tr"));  
      	  List<WebElement> columns=datePicker.findElements(By.tagName("td"));  
             for (WebElement cell: columns){ 
      	   if (cell.getText().equals(date))
      	   {
      		   cell.findElement(By.linkText(date)).click();
      		   break; 
      	   }
         }
       
      }
  	
}
