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
public class NurturTobaccoUseHAPage extends HealthAssessmentPage {

    /**
     * Element Locators
     */
    By tobaccoUseSectionText                = By.xpath("//*[contains(.,'your tobacco use')][not(.//*[contains(., 'your tobacco use')])]");
    //Smoking Status
    By currentSmokerRadioBtn                = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl00_HAQuestionCtrl1_ctl00_rbtList_0");
    By neverSmokedRadioBtn                  = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl00_HAQuestionCtrl1_ctl00_rbtList_2");
    By quitSmokingRadioBtn                  = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl00_HAQuestionCtrl1_ctl00_rbtList_1");

    By yearsSmoked                          = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl01_HAQuestionCtrl1_ctl00_TheNumericTextBox");

    //Cigarettes Smoked Per Day
    By lessThanHalfPackBtn                  = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl02_HAQuestionCtrl1_ctl00_rbtList_0");
    By halfToOnePackBtn                     = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl02_HAQuestionCtrl1_ctl00_rbtList_1");
    By oneToTwoPacksBtn                     = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl02_HAQuestionCtrl1_ctl00_rbtList_2");
    By twoPlusPacksBtn                      = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl02_HAQuestionCtrl1_ctl00_rbtList_3");


    By yesOtherTobaccoProducts              = By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl04_HAQuestionCtrl1_dvTwoChoiceyesno34_dvTwo']/table/tbody/tr/td[1]/input");
    By noOtherTobaccoProducts               = By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl04_HAQuestionCtrl1_dvTwoChoiceyesno34_dvTwo']/table/tbody/tr/td[2]/input");

    By chewingTobaccoRadioBtn               = By.cssSelector(".//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl05_HAQuestionCtrl1_ctl00_checkboxList_0']");
    By cigarsTobaccoRadioBtn                = By.cssSelector(".//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl05_HAQuestionCtrl1_ctl00_checkboxList_1']");

    By planToChangeWithinTheMonthRadioBtn   = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl06_HAQuestionCtrl1_ctl00_rbtList_1");
    By startedMakingChangesAlreadyBtn       = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl06_HAQuestionCtrl1_ctl00_rbtList_0");
    By planToChangeInNextSixMonthsBtn       = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl06_HAQuestionCtrl1_ctl00_rbtList_2");
    By noPlansToChangeBtn                   = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl06_HAQuestionCtrl1_ctl00_rbtList_3");



    /**
     * Other Initiators
     */
    private static Logger logger = LogManager.getLogger(com.lh.pages.authenticated.ha.NurturTobaccoUseHAPage.class);

    /**
     * Constructor
     */
    public NurturTobaccoUseHAPage(){
        super();
        logger.info("...initializing the Tobacco Use Nurtur HA Page Object");
        // Check that we're on the right page.
        if (!(driver.findElement(tobaccoUseSectionText).isDisplayed())) {
            logger.error("This is not the Tobacco Use Nurtur HA page.");
            throw new IllegalStateException(
                    "This is not the AboutYouHAPage page.");
        }
        logger.info("INITIALIZED the Tobacco Use Nurtur HA Page Object.");
    }

    //========================
    //METHODS
    //========================
    public void selectSmokingStatus(String status){
        status = status.trim().toLowerCase();
        switch(status){
            case "current smoker" : driver.findElement(currentSmokerRadioBtn).click();
                break;
            case "never smoked" : driver.findElement(neverSmokedRadioBtn).click();
                break;
            case "quit smoking" : driver.findElement(neverSmokedRadioBtn).click();
                break;
            default:
                logger.error("INVALID PARAMETER - Provide 'current smoker', 'never smoked', or 'quit smoking'.");
        }
        logger.info("Smoking Status --- Set to " + status);
    }

    public void setYearsSmoked(String years){
        driver.findElement(yearsSmoked).clear();
        driver.findElement(yearsSmoked).sendKeys(years);

        logger.info("Years Smoked set to - " +years);
    }

    public void setCigarettesSmokedPerDay(String selection){
        selection = selection.trim().toLowerCase();
        switch (selection){
            case "less than 1/2 pack" : driver.findElement(lessThanHalfPackBtn).click();
                break;
            case "1/2 - 1 pack" : driver.findElement(halfToOnePackBtn).click();
                break;
            case "1- 2 packs" : driver.findElement(oneToTwoPacksBtn).click();
                break;
            case "2+ packs" : driver.findElement(twoPlusPacksBtn).click();
                break;
            default:
                logger.error("INVALID PARAMETER - Provide: 'less than 1/2 pack','1/2 - 1 pack','1- 2 packs','2+ packs'.");
        }
    }

    public void setOtherTobaccoProductsUse(String ans){
        ans = ans.trim().toLowerCase();
        switch(ans){
            case "no" : driver.findElement(noOtherTobaccoProducts).click();
                logger.info("Set NO -- Use of other tobacco products?");
                break;
            case "yes" : driver.findElement(yesOtherTobaccoProducts).click();
                logger.info("Set NO -- Use of other tobacco products?");
                break;
            default :
                logger.error("INVALID PARAMETER.  Set 'yes' or 'no'.");
        }
    }

    public void setPlansForQuittingTobacco(String plans){
        plans = plans.trim().toLowerCase();
        switch(plans){
            case "started making changes already" : driver.findElement(startedMakingChangesAlreadyBtn).click();
                break;
            case "plan to change within the month" : driver.findElement(planToChangeWithinTheMonthRadioBtn).click();
                break;
            case "plan to change in next 6 months" : driver.findElement(planToChangeInNextSixMonthsBtn).click();
                break;
            case "no plans to change" : driver.findElement(noPlansToChangeBtn).click();
                break;
            default:
                logger.error("INVALID PARAMETER - Provide: 'started making changes already','plan to change within the month','plan to change in next 6 months','no plans to change'.");
        }
    }

}
