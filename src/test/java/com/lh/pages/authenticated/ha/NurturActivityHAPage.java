package com.lh.pages.authenticated.ha;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import static com.lh.helper.DriverFactory.driver;

/**
 * Created by oleg.andreyev on 9/15/2016 for LiveHealthier-EnvolvePeopleCare
 * <p>
 * Summary -
 */
public class NurturActivityHAPage {

    /**
     * Web Element Locators
     */
    By activitySectionText                          = By.xpath("//*[contains(.,'physical activity habits')][not(.//*[contains(., 'physical activity habits')])]");

    By zeroMinsBtn                                  = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl00_HAQuestionCtrl1_ctl00_rbtList_0");
    By thirtyMinsBtn                                = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl00_HAQuestionCtrl1_ctl00_rbtList_1");
    By sixtyMinsBtn                                 = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl00_HAQuestionCtrl1_ctl00_rbtList_2");
    By ninetyMinsBtn                                = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl00_HAQuestionCtrl1_ctl00_rbtList_3");
    By oneHundredTwentyMinsBtn                      = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl00_HAQuestionCtrl1_ctl00_rbtList_4");
    By oneHundredFiftyPlusMinsBtn                   = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl00_HAQuestionCtrl1_ctl00_rbtList_5");

    By normalBtn                                    = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl01_HAQuestionCtrl1_ctl00_rbtList_0");
    By moderateBtn                                  = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl01_HAQuestionCtrl1_ctl00_rbtList_1");
    By hardBtn                                      = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl01_HAQuestionCtrl1_ctl00_rbtList_2");

    By noChangeIsNeededBtn                          = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl02_HAQuestionCtrl1_ctl00_rbtList_0");
    By maintainedChangesForSixPlusMonthsBtn         = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl02_HAQuestionCtrl1_ctl00_rbtList_1");
    By startedMakingChangesAlreadyBtn               = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl02_HAQuestionCtrl1_ctl00_rbtList_2");
    By planToChangeInNextMonthBtn                   = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl02_HAQuestionCtrl1_ctl00_rbtList_3");
    By planToChangeInNextSixMonthsBtn               = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl02_HAQuestionCtrl1_ctl00_rbtList_4");
    By noPlansToChangeBtn                           = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl02_HAQuestionCtrl1_ctl00_rbtList_5");


    /**
     * Other Class Initiators
     */
    private static Logger logger = LogManager.getLogger(com.lh.pages.authenticated.ha.NurturActivityHAPage.class);

    /**
     * Constructor
     */
    public NurturActivityHAPage(){
        super();
        logger.info("...initializing the Activity Nurtur HA Page Object");
        // Check that we're on the right page.
        if (!(driver.findElement(activitySectionText).isDisplayed())) {
            logger.error("This is not the Activity Nurtur Health Assessment page.");
            throw new IllegalStateException(
                    "This is not the Activity Nurtur Health Assessment page.");
        }
        logger.info("INITIALIZED the Activity Nurtur HA Page Object.");
    }

    //=========================
    //METHODS
    //=========================
    public void setTotalMinutesExercisePerWeek(String minutes){
        minutes = minutes.trim().toLowerCase();
        switch(minutes){
            case "0" : driver.findElement(zeroMinsBtn).click();
                logger.info("Mins of Exercise set to - " +minutes);
                break;
            case "30" : driver.findElement(thirtyMinsBtn).click();
                logger.info("Mins of Exercise set to - " +minutes);
                break;
            case "60" : driver.findElement(sixtyMinsBtn).click();
                logger.info("Mins of Exercise set to - " +minutes);
                break;
            case "90" : driver.findElement(ninetyMinsBtn).click();
                logger.info("Mins of Exercise set to - " +minutes);
                break;
            case "120" : driver.findElement(oneHundredTwentyMinsBtn).click();
                logger.info("Mins of Exercise set to - " +minutes);
                break;
            case "150+" : driver.findElement(oneHundredFiftyPlusMinsBtn).click();
                logger.info("Mins of Exercise set to - " +minutes);
                break;
            default :
                logger.error("INVALID PARAMETER -- Set to: '0','30','60','90','120','150+'.");
        }
    }

    public void setDuringExerciseExersionLevel(String level){
        level = level.trim().toLowerCase();
        switch(level){
            case "normal" : driver.findElement(normalBtn).click();
                logger.info("Level of Exercise set to - " +level);
                break;
            case "moderate" : driver.findElement(moderateBtn).click();
                logger.info("Level of Exercise set to - " +level);
                break;
            case "hard" : driver.findElement(hardBtn).click();
                logger.info("Level of Exercise set to - " +level);
                break;
            default :
                logger.error("INVALID PARAMETER -- Set to: 'normal','moderate','hard'.");
        }
    }

    public void setPlanForGettingMoreExercise(String plan){
        plan = plan.trim().toLowerCase();
        switch(plan){
            case "no change is needed" : driver.findElement(noChangeIsNeededBtn).click();
                logger.info("Plans for modifying set to - " +plan);
                break;
            case "maintained changes for 6+ months" : driver.findElement(maintainedChangesForSixPlusMonthsBtn).click();
                logger.info("Plans for modifying set to - " +plan);
                break;
            case "started making changes already" : driver.findElement(startedMakingChangesAlreadyBtn).click();
                logger.info("Plans for modifying set to - " +plan);
                break;
            case "plan to change in next month" : driver.findElement(planToChangeInNextMonthBtn).click();
                logger.info("Plans for modifying set to - " +plan);
                break;
            case "plan to change in next 6 months" : driver.findElement(planToChangeInNextSixMonthsBtn).click();
                logger.info("Plans for modifying set to - " +plan);
                break;
            case "no plans to change" : driver.findElement(noPlansToChangeBtn).click();
                logger.info("Plans for modifying set to - " +plan);
                break;
            default :
                logger.error("INVALID PARAMETER -- Set to: 'No change is needed','Maintained changes for 6+ months','Started making changes already','Plan to change in next month','Plan to change in next 6 months','No plans to change'.");
        }
    }



}
