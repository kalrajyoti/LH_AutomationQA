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
public class NurturSubstancesHAPage {

    /**
     * Web Element Locators
     */
    By substancesSectionText                    = By.xpath("//*[contains(.,'your alcohol use')][not(.//*[contains(., 'your alcohol use')])]");

    By drinksField                              = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl00_HAQuestionCtrl1_ctl00_TheNumericTextBox");
    By noChangeIsNeededBtn                      = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl01_HAQuestionCtrl1_ctl00_rbtList_0");
    By maintainedChangesForSixPlusMonthsBtn     = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl01_HAQuestionCtrl1_ctl00_rbtList_1");
    By startedMakingChangesAlreadyBtn           = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl01_HAQuestionCtrl1_ctl00_rbtList_2");
    By planToChangeInNextMonthBtn               = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl01_HAQuestionCtrl1_ctl00_rbtList_3");
    By planToChangeInNextSixMonthsBtn           = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl01_HAQuestionCtrl1_ctl00_rbtList_4");
    By noPlansToChangeBtn                       = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl01_HAQuestionCtrl1_ctl00_rbtList_5");

    /**
     * Other Class Initiators
     */
    private static Logger logger = LogManager.getLogger(com.lh.pages.authenticated.ha.NurturSubstancesHAPage.class);

    /**
     * Constructor
     */
    public NurturSubstancesHAPage(){
        super();
        logger.info("...initializing the Substances Nurtur HA Page Object");
        // Check that we're on the right page.
        if (!(driver.findElement(substancesSectionText).isDisplayed())) {
            logger.error("This is not the Substances Nurtur Health Assessment page.");
            throw new IllegalStateException(
                    "This is not the Substances Nurtur Health Assessment page.");
        }
        logger.info("INITIALIZED the Substances Nurtur HA Page Object.");
    }

    //=========================
    //METHODS
    //=========================
    public void setDrinksInTypicalWeek(String value){
        driver.findElement(drinksField).clear();
        driver.findElement(drinksField).sendKeys(value);

        logger.info("Drinks per week -- Set to " +value);
    }
    public void setPlanForModifyingAlcoholUse(String plan){
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
