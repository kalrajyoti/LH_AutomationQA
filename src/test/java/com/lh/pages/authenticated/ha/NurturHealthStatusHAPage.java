package com.lh.pages.authenticated.ha;

import java.util.List;
import com.lh.utils.UtilityMethods;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.lh.helper.DriverFactory.driver;

/**
 * Created by oleg.andreyev on 9/14/2016 for LiveHealthier-EnvolvePeopleCare
 * <p>
 * Summary -
 */
public class NurturHealthStatusHAPage {

    /**
     * Element Locators
     */
    By healthStatusSpecificHeader =
            By.xpath("//*[contains(.,'your health status')][not(.//*[contains(., 'your health status')])]");

    By heightInFeetTxt 								= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl00_HAQuestionCtrl1_ctl00_FirstTextBox_TheNumericTextBox");
    By heightInInchesTxt 							= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl00_HAQuestionCtrl1_ctl00_SecondTextBox_TheNumericTextBox");

    By weightFieldFEMALE = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl02_HAQuestionCtrl1_ctl00_TheNumericTextBox");
    By weightFieldMALE = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl01_HAQuestionCtrl1_ctl00_TheNumericTextBox");

    By waistSizeFieldFEMALE = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl04_HAQuestionCtrl1_ctl00_TheNumericTextBox");
    By waistSizeFieldMALE = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl03_HAQuestionCtrl1_ctl00_TheNumericTextBox");

    By restingHeartRateField						= By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl05_HAQuestionCtrl1_ctl00_txtOther");
    By restingHeartRateRadioButton 					= By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl05_HAQuestionCtrl1_ctl00_rbtList_0");
    By systolicBPField = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl08_HAQuestionCtrl1_ctl00_TheNumericTextBox");
    By diastolicBPField = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl09_HAQuestionCtrl1_ctl00_TheNumericTextBox");

    By triglyceridesField                           = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl19_HAQuestionCtrl1_ctl00_txtOther");
    By triglycerdiesRadioBtn                        = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl19_HAQuestionCtrl1_ctl00_rbtList_0");

    private By lDLCholesterolRadio					= By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl16_HAQuestionCtrl1_ctl00_rbtList_0");
    private By lDLCholesterolTxt					= By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl16_HAQuestionCtrl1_ctl00_txtOther");
    private By hDLCholesterolRadio 					= By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl13_HAQuestionCtrl1_ctl00_rbtList_0");
    private By hDLCholesterolTxt					= By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl13_HAQuestionCtrl1_ctl00_txtOther");
    private By totalCholesterolRadio 				= By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl11_HAQuestionCtrl1_ctl00_rbtList_0");
    private By totalCholesterolTxt 					= By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl11_HAQuestionCtrl1_ctl00_txtOther");

    By glucoseField                                 = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl23_HAQuestionCtrl1_ctl00_txtOther");
    By glucoseRadioBtn                              = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl23_HAQuestionCtrl1_ctl00_rbtList_0");

    By doYouKnowYourBloodPressureButtonYES          = By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl07_HAQuestionCtrl1_dvTwoChoiceyesno13_dvTwo']/table/tbody/tr/td[1]/input");

    By fastForTriglyceridesYesButton                = By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl18_HAQuestionCtrl1_dvTwoChoiceyesno22_dvTwo']/table/tbody/tr/td[1]/input");
    By fastForTriglyceridesNoButton                 = By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl18_HAQuestionCtrl1_dvTwoChoiceyesno22_dvTwo']/table/tbody/tr/td[2]/input");

    By fastForGlucoseYesButton                      = By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl22_HAQuestionCtrl1_dvTwoChoiceyesno26_dvTwo']/table/tbody/tr/td[1]/input");
    By fastForGlucoseNoButton                       = By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl22_HAQuestionCtrl1_dvTwoChoiceyesno26_dvTwo']/table/tbody/tr/td[2]/input");

    By listOfDecimalError							= By.xpath("//div[contains(@id,'ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_') and not(contains(@style,'display: none'))]//span[contains(.,'Please enter a number, no decimals')]");

    /**
     * Other Initiators
     */
    private static Logger logger = LogManager.getLogger(com.lh.pages.authenticated.ha.NurturHealthStatusHAPage.class);

    /**
     * Constructor
     */
    public NurturHealthStatusHAPage() {
        super();
        logger.info("...initializing the Health Status Nurtur HA Page Object");
        if (!(driver.findElement(healthStatusSpecificHeader).isDisplayed())) {
            logger.error("This is not the Health Status Nurtur HA page.");
            throw new IllegalStateException(
                    "This is not the Health Status Nurtur HA page.");
        }
        logger.info("INITIALIZED the Health Status Nurtur HA Page Object.");
    }


    //=================
    //METHODS
    //=================
    /**
     * Sets the height for the user on the Health Status HA page
     *
     * @param heightFeet
     *            height in feet
     * @param heightInches
     *            height in inches, if height is >12 , feet+1 and inches-12 is
     *            set in the DB
     */
    public void setHeight(String heightFeet, String heightInches) {
        heightFeet = heightFeet.trim();
        heightInches = heightInches.trim();

        	driver.findElement(heightInFeetTxt).click();
            driver.findElement(heightInFeetTxt).clear();
            driver.findElement(heightInFeetTxt).sendKeys(heightFeet);
            logger.info("Successfully set HEIGHT (FEET) to - " + heightFeet);
        	driver.findElement(heightInInchesTxt).click();
            driver.findElement(heightInInchesTxt).clear();
            driver.findElement(heightInInchesTxt).sendKeys(heightInches);
            logger.info("Successfully set HEIGHT (INCHES) to - " + heightInches);
    }

    public void setWeight(String weight, String gender){
        gender = gender.trim().toLowerCase();
        
        switch(gender){
            case "female" :
            	driver.findElement(weightFieldFEMALE).click();
                driver.findElement(weightFieldFEMALE).clear();
                driver.findElement(weightFieldFEMALE).sendKeys(weight);
                logger.info("Successfully set WEIGHT to - " +weight);
                break;
            case "male" :
                driver.findElement(weightFieldMALE).click();
                driver.findElement(weightFieldMALE).clear();
                driver.findElement(weightFieldMALE).sendKeys(weight);
                logger.info("Successfully set WEIGHT to - " +weight);
                break;
        }
    }

    public void setWaistSize(String waist, String gender) {
        gender = gender.trim().toLowerCase();
      
        switch(gender){
            case "female" :
            	driver.findElement(waistSizeFieldFEMALE).click();
                driver.findElement(waistSizeFieldFEMALE).clear();
                driver.findElement(waistSizeFieldFEMALE).sendKeys(waist);
                logger.info("Successfully set WAIST SIZE to - " +waist);
                break;
            case "male" :
                driver.findElement(waistSizeFieldMALE).click();
                driver.findElement(waistSizeFieldMALE).clear();
                driver.findElement(waistSizeFieldMALE).sendKeys(waist);
                logger.info("Successfully set WAIST SIZE to - " +waist);
                break;
        }


    }

    public void setRestingHeartRate(String heartRate) {
        driver.findElement(restingHeartRateField).clear();
        driver.findElement(restingHeartRateField).sendKeys(heartRate);
        if(!driver.findElement(restingHeartRateRadioButton).isSelected()){
            driver.findElement(restingHeartRateRadioButton).click();
            logger.info("...clicked on Resting Heart Rate radio button");
        }
        logger.info("Successfully set RESTING HEART RATE to - " +heartRate);
    }

    public void setBloodPressure(String systolicBP, String diastolicBP){
        driver.findElement(doYouKnowYourBloodPressureButtonYES).click();
      
        driver.findElement(systolicBPField).clear();
        driver.findElement(diastolicBPField).clear();

        driver.findElement(systolicBPField).sendKeys(systolicBP);
        driver.findElement(diastolicBPField).sendKeys(diastolicBP);

        logger.info("Successfully set SYSTOLIC and DIASTOLIC BP to - " +systolicBP+" and " +diastolicBP);
    }

    /**
     * Sets the total cholesterol
     *
     * @param totalCholesterol
     *            total cholesterol value as integer or "Unknown"
     */
    public void setTotalCholesterol(String totalCholesterol) {
            driver.findElement(totalCholesterolRadio).click();
            driver.findElement(totalCholesterolTxt).clear();
            driver.findElement(totalCholesterolTxt).sendKeys(totalCholesterol);
            logger.info("Successfully set TOTAL CHOLESTEROL to - " + totalCholesterol);

    }

    /**
     *
     * @param hDLCholesterol
     *            HDL cholesterol value as integer or "Unknown"
     */
    public void setHDLCholesterol(String hDLCholesterol) {
        hDLCholesterol = hDLCholesterol.toLowerCase().trim();
        if (UtilityMethods.isInteger(hDLCholesterol)) {
            driver.findElement(hDLCholesterolTxt).clear();
            driver.findElement(hDLCholesterolRadio).click();
            driver.findElement(hDLCholesterolTxt).sendKeys(hDLCholesterol);
            logger.info("Successfully set HDL Cholesterol to - " + hDLCholesterol);
        } else {
            logger.error("Invalid value for hdl cholesterol is provided.");
        }
    }

    /**
     * Sets the LDL cholesterol for the user on Health status page
     *
     * @param lDLCholesterol
     *            LDL cholesterol can be an integer or
     *            "unknown"/"not sure good"/"not sure bad"
     */
    public void setLDLCholesterol(String lDLCholesterol) {
        lDLCholesterol = lDLCholesterol.toLowerCase().trim();
        if (UtilityMethods.isInteger(lDLCholesterol)) {
            driver.findElement(lDLCholesterolRadio).click();
            driver.findElement(lDLCholesterolTxt).clear();
            driver.findElement(lDLCholesterolTxt).sendKeys(lDLCholesterol);
            logger.info("Successfully set LDL CHOLESTEROL to - " + lDLCholesterol);
        } else {
            logger.error("Invalid value for ldl cholesterol is provided.");
        }
    }

    public void setFastingForTriglyceridesTest(String ans){
        ans = ans.toLowerCase().trim();
        switch(ans){
            case "yes" : driver.findElement(fastForTriglyceridesYesButton).click();
                break;
            case "no" : driver.findElement(fastForTriglyceridesNoButton).click();
                break;
        }
    }

    public void setFastingForGlucoseTest(String ans){
        ans = ans.toLowerCase().trim();
        switch(ans){
            case "yes" : driver.findElement(fastForGlucoseYesButton).click();
                break;
            case "no" : driver.findElement(fastForGlucoseNoButton).click();
                break;
        }
    }

    public void setTriglycerides(String triglycerides) {
        driver.findElement(triglyceridesField).clear();
        driver.findElement(triglyceridesField).sendKeys(triglycerides);

        if(!driver.findElement(triglycerdiesRadioBtn).isSelected()){
            driver.findElement(triglycerdiesRadioBtn).click();
        }
        logger.info("Successfully set TRIGLYCERIDES to - " + triglycerides);
    }


    public void setGlucose(String glucose) {
        driver.findElement(glucoseField).clear();
        driver.findElement(glucoseField).sendKeys(glucose);

        if(!driver.findElement(glucoseRadioBtn).isSelected()){
            driver.findElement(glucoseRadioBtn).click();
        }
        logger.info("Successfully set GLUCOSE to - " + glucose);
    }
    
    public boolean verifyListOfDecimalError(String num){
    	boolean flag = false;
    	List<WebElement> errorMsgList = driver.findElements(listOfDecimalError);
    	String size = Integer.toString(errorMsgList.size());
    	if(num.equals(size)){
    		flag = true;
    	}
    	return flag;
    }
}
