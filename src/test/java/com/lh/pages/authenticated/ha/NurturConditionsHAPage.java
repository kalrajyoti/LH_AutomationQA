package com.lh.pages.authenticated.ha;

/**
 * Created by oleg.andreyev on 9/15/2016 for LiveHealthier-EnvolvePeopleCare
 * <p>
 * Summary -
 */

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import static com.lh.helper.DriverFactory.driver;


public class NurturConditionsHAPage {

    /**
     * Web Element Locators
     */
    By conditionsSectionText                            = By.xpath("//*[contains(.,'your health conditions')][not(.//*[contains(., 'your health conditions')])]");

    By yesHaveDoctorProvider                            = By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl00_HAQuestionCtrl1_dvTwoChoiceyesno67_dvTwo']/table/tbody/tr/td[1]/input");
    By noHaveDoctorProvider                             = By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl00_HAQuestionCtrl1_dvTwoChoiceyesno67_dvTwo']/table/tbody/tr/td[2]/input");

    By yesSeenDoctorProvider                            = By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl01_HAQuestionCtrl1_dvTwoChoiceyesno68_dvTwo']/table/tbody/tr/td[1]/input");
    By noSeenDoctorProvider                             = By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl01_HAQuestionCtrl1_dvTwoChoiceyesno68_dvTwo']/table/tbody/tr/td[2]/input");

    By excellentHealthBtn                               = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl02_HAQuestionCtrl1_ctl00_rbtList_0");
    By veryGoodHealthBtn                                = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl02_HAQuestionCtrl1_ctl00_rbtList_1");
    By goodHealthBtn                                    = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl02_HAQuestionCtrl1_ctl00_rbtList_2");
    By fairHealthBtn                                    = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl02_HAQuestionCtrl1_ctl00_rbtList_3");
    By poorHealthBtn                                    = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl02_HAQuestionCtrl1_ctl00_rbtList_4");

    By yesElectiveSurgeryDiscussion                     = By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl03_HAQuestionCtrl1_dvTwoChoiceyesno70_dvTwo']/table/tbody/tr/td[1]/input");
    By noElectiveSurgeryDiscussion                      = By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl03_HAQuestionCtrl1_dvTwoChoiceyesno70_dvTwo']/table/tbody/tr/td[2]/input");

    By noneOfTheAboveConditions                         = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl06_HAQuestionCtrl1_ctl00_checkboxList_19");

    By yesDailyAspirinBtn                            = By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl09_HAQuestionCtrl1_dvTwoChoiceyesno74_dvTwo']/table/tbody/tr/td[1]/input");
    By noDailyAspirinBtn                              = By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl09_HAQuestionCtrl1_dvTwoChoiceyesno74_dvTwo']/table/tbody/tr/td[2]/input");

    By yesDailyBloodPressureMedsBtn = By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl10_HAQuestionCtrl1_dvTwoChoiceyesno75_dvTwo']/table/tbody/tr/td[1]/input");
    By noDailyBloodPressureMedsBtn = By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl10_HAQuestionCtrl1_dvTwoChoiceyesno75_dvTwo']/table/tbody/tr/td[2]/input");

    By yesDailyCholesterolMedsBtn = By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl11_HAQuestionCtrl1_dvTwoChoiceyesno76_dvTwo']/table/tbody/tr/td[1]/input");
    By noDailyCholesterolMedsBtn = By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl11_HAQuestionCtrl1_dvTwoChoiceyesno76_dvTwo']/table/tbody/tr/td[2]/input");


    /**
     * Other Class Initiators
     */
    private static Logger logger = LogManager.getLogger(com.lh.pages.authenticated.ha.NurturConditionsHAPage.class);

    /**
     * Constructor
     */
    public NurturConditionsHAPage(){
        super();
        logger.info("...initializing the Conditions Nurtur HA Page Object");
        // Check that we're on the right page.
        if (!(driver.findElement(conditionsSectionText).isDisplayed())) {
            logger.error("This is not the Conditions Nurtur Health Assessment page.");
            throw new IllegalStateException(
                    "This is not the Conditions Nurtur Health Assessment page.");
        }
        logger.info("INITIALIZED the Conditions Nurtur HA Page Object.");
    }

    //=========================
    //METHODS
    //=========================
    public void setDoYouHaveDoctorOrProvider(String yesOrNo){
        yesOrNo = yesOrNo.trim().toLowerCase();
        switch(yesOrNo){
            case "yes" : driver.findElement(yesHaveDoctorProvider).click();
                logger.info("Set: Do you have a doctor or health care provider - " +yesOrNo);
                break;
            case "no" : driver.findElement(yesHaveDoctorProvider).click();
                logger.info("Set: Do you have a doctor or health care provider - " +yesOrNo);
                break;
            default:
                logger.error("INVALID PARAMETER - Set to 'yes' or 'no'.");
        }
    }

    public void setHaveYouSeenDoctorTwelveMonths(String yesOrNo){
        yesOrNo = yesOrNo.trim().toLowerCase();
        switch(yesOrNo){
            case "yes" : driver.findElement(yesSeenDoctorProvider).click();
                logger.info("Set: Have you seen a doctor or health care provider past 12 months - " +yesOrNo);
                break;
            case "no" : driver.findElement(noSeenDoctorProvider).click();
                logger.info("Set: Have you seen a doctor or health care provider past 12 months - " +yesOrNo);
                break;
            default:
                logger.error("INVALID PARAMETER - Set to 'yes' or 'no'.");
        }
    }

    public void setHowWouldYouRateYourHealth(String answer){
        answer = answer.trim().toLowerCase();
        switch(answer){
            case "excellent" : driver.findElement(excellentHealthBtn).click();
                logger.info("Set: How would you rate your health -- " +answer);
                break;
            case "very good" : driver.findElement(excellentHealthBtn).click();
                logger.info("Set: How would you rate your health -- " +answer);
                break;
            case "good" : driver.findElement(excellentHealthBtn).click();
                logger.info("Set: How would you rate your health -- " +answer);
                break;
            case "fair" : driver.findElement(excellentHealthBtn).click();
                logger.info("Set: How would you rate your health -- " +answer);
                break;
            case "poor" : driver.findElement(excellentHealthBtn).click();
                logger.info("Set: How would you rate your health -- " +answer);
                break;
            default:
                logger.error("INVALID PARAMETER: Set to 'excellent', 'very good', 'good', 'fair', 'poor'.");
        }
    }

    public void setElectiveSurgeryDiscussion(String ans){
        ans = ans.trim().toLowerCase();
        switch(ans){
            case "no" : driver.findElement(noElectiveSurgeryDiscussion).click();
                logger.info("Set NO -- Discussed elective surgery as an option...?");
                break;
            case "yes" : driver.findElement(yesElectiveSurgeryDiscussion).click();
                logger.info("Set YES -- Discussed elective surgery as an option...?");
                break;
            default:
                logger.error("INVALID PARAMETER: Set to 'yes' or 'no'.");
        }


    }

    public void setDailyAspirinIntake(String yesOrNo){
        yesOrNo = yesOrNo.trim().toLowerCase();
        switch(yesOrNo){
            case "yes" : driver.findElement(yesDailyAspirinBtn).click();
                logger.info("Set: Do you take Aspirin daily? - " +yesOrNo);
                break;
            case "no" : driver.findElement(noDailyAspirinBtn).click();
                logger.info("Set: Do you take Aspirin daily? - " +yesOrNo);
                break;
            default:
                logger.error("INVALID PARAMETER - Set to 'yes' or 'no'.");
        }
    }

    public void setDailyBloodPressureMedsIntake(String yesOrNo){
        yesOrNo = yesOrNo.trim().toLowerCase();
        switch(yesOrNo){
            case "yes" : driver.findElement(yesDailyBloodPressureMedsBtn).click();
                logger.info("Set: Do you take BP medication daily? - " +yesOrNo);
                break;
            case "no" : driver.findElement(noDailyBloodPressureMedsBtn).click();
                logger.info("Set: Do you take BP medication daily? - " +yesOrNo);
                break;
            default:
                logger.error("INVALID PARAMETER - Set to 'yes' or 'no'.");
        }
    }

    public void setDailyCholesterolMedsIntake(String yesOrNo){
        yesOrNo = yesOrNo.trim().toLowerCase();
        switch(yesOrNo){
            case "yes" : driver.findElement(yesDailyCholesterolMedsBtn).click();
                logger.info("Set: Do you take Cholesterol medication daily? - " +yesOrNo);
                break;
            case "no" : driver.findElement(noDailyCholesterolMedsBtn).click();
                logger.info("Set: Do you take Cholesterol medication daily? - " +yesOrNo);
                break;
            default:
                logger.error("INVALID PARAMETER - Set to 'yes' or 'no'.");
        }
    }

    public void setAnyAboveConditionsChoices(String ans){
        ans = ans.trim().toLowerCase();
        switch(ans) {
            case "none of the above" :
                if (!driver.findElement(noneOfTheAboveConditions).isSelected()) {
                    driver.findElement(noneOfTheAboveConditions).click();
                }
                logger.info("Set NONE OF THE ABOVE to - Do you have any of the above conditions...?");
                break;
            default:
                logger.error("Set to 'none of the above'.");
        }


    }






}
