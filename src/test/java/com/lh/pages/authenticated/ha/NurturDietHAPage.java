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
public class NurturDietHAPage {

    /**
     * Web Element Locators
     */
    By dietSectionText                              = By.xpath("//*[contains(.,'kinds of foods you eat')][not(.//*[contains(., 'kinds of foods you eat')])]");

    By zeroToOneFruitsPlate                         = By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl00_HAQuestionCtrl1_ctl00_rptChoices_ctl00_ImageRadioList1_DvImgRadio']/table/tbody/tr[2]/td[1]");
    By twoFruitsPlate                               = By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl00_HAQuestionCtrl1_ctl00_rptChoices_ctl00_ImageRadioList1_DvImgRadio']/table/tbody/tr[2]/td[2]");
    By threeFruitsPlate                             = By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl00_HAQuestionCtrl1_ctl00_rptChoices_ctl00_ImageRadioList1_DvImgRadio']/table/tbody/tr[2]/td[3]");
    By fourPlusFruitsPlate                          = By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl00_HAQuestionCtrl1_ctl00_rptChoices_ctl00_ImageRadioList1_DvImgRadio']/table/tbody/tr[2]/td[4]");

    By zeroToOneVegetablesPlate                     = By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl01_HAQuestionCtrl1_ctl00_rptChoices_ctl00_ImageRadioList1_DvImgRadio']/table/tbody/tr[2]/td[1]");
    By twoVegetablesPlate                           = By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl01_HAQuestionCtrl1_ctl00_rptChoices_ctl00_ImageRadioList1_DvImgRadio']/table/tbody/tr[2]/td[2]");
    By threeVegetablesPlate                         = By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl01_HAQuestionCtrl1_ctl00_rptChoices_ctl00_ImageRadioList1_DvImgRadio']/table/tbody/tr[2]/td[3]");
    By fourPlusVegetablesPlate                      = By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl01_HAQuestionCtrl1_ctl00_rptChoices_ctl00_ImageRadioList1_DvImgRadio']/table/tbody/tr[2]/td[4]");

    By zeroToOneWholeGrainsPlate                    = By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl02_HAQuestionCtrl1_ctl00_rptChoices_ctl00_ImageRadioList1_DvImgRadio']/table/tbody/tr[2]/td[1]");
    By twoWholeGrainsPlate                          = By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl02_HAQuestionCtrl1_ctl00_rptChoices_ctl00_ImageRadioList1_DvImgRadio']/table/tbody/tr[2]/td[2]");
    By threeWholeGrainsPlate                        = By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl02_HAQuestionCtrl1_ctl00_rptChoices_ctl00_ImageRadioList1_DvImgRadio']/table/tbody/tr[2]/td[3]");
    By fourPlusWholeGrainsPlate                     = By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl02_HAQuestionCtrl1_ctl00_rptChoices_ctl00_ImageRadioList1_DvImgRadio']/table/tbody/tr[2]/td[4]");

    By lessThanOnceSaturatedFatBtn                  = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl03_HAQuestionCtrl1_ctl00_rbtList_0");
    By onceSaturatedFatBtn                          = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl03_HAQuestionCtrl1_ctl00_rbtList_1");
    By severalTimesSaturatedFatBtn                  = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl03_HAQuestionCtrl1_ctl00_rbtList_2");
    By dailySaturatedFatBtn                         = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl03_HAQuestionCtrl1_ctl00_rbtList_3");
    By dailySeveralTimesSaturatedFatBtn             = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl03_HAQuestionCtrl1_ctl00_rbtList_4");

    By noChangeIsNeededEatingBtn                          = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl04_HAQuestionCtrl1_ctl00_rbtList_0");
    By maintainedChangesForSixPlusMonthsEatingBtn         = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl04_HAQuestionCtrl1_ctl00_rbtList_1");
    By startedMakingChangesAlreadyEatingBtn               = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl04_HAQuestionCtrl1_ctl00_rbtList_2");
    By planToChangeInNextMonthEatingBtn                   = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl04_HAQuestionCtrl1_ctl00_rbtList_3");
    By planToChangeInNextSixMonthsEatingBtn               = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl04_HAQuestionCtrl1_ctl00_rbtList_4");
    By noPlansToChangeEatingBtn                           = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl04_HAQuestionCtrl1_ctl00_rbtList_5");

    By noChangeIsNeededWeightBtn                          = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl05_HAQuestionCtrl1_ctl00_rbtList_0");
    By maintainedChangesForSixPlusMonthsWeightBtn         = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl05_HAQuestionCtrl1_ctl00_rbtList_1");
    By startedMakingChangesAlreadyWeightBtn               = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl05_HAQuestionCtrl1_ctl00_rbtList_2");
    By planToChangeInNextMonthWeightBtn                   = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl05_HAQuestionCtrl1_ctl00_rbtList_3");
    By planToChangeInNextSixMonthsWeightBtn               = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl05_HAQuestionCtrl1_ctl00_rbtList_4");
    By noPlansToChangeWeightBtn                           = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl05_HAQuestionCtrl1_ctl00_rbtList_5");


    /**
     * Other Class Initiators
     */
    private static Logger logger = LogManager.getLogger(com.lh.pages.authenticated.ha.NurturDietHAPage.class);

    /**
     * Constructor
     */
    public NurturDietHAPage(){
        super();
        logger.info("...initializing the Activity Nurtur HA Page Object");
        // Check that we're on the right page.
        if (!(driver.findElement(dietSectionText).isDisplayed())) {
            logger.error("This is not the Activity Nurtur Health Assessment page.");
            throw new IllegalStateException(
                    "This is not the Activity Nurtur Health Assessment page.");
        }
        logger.info("INITIALIZED the Activity Nurtur HA Page Object.");
    }

    //=========================
    //METHODS
    //=========================
    public void setServingsOfFruitDaily(String fruits){
        fruits = fruits.trim().toLowerCase();
        switch(fruits){
            case "0-1 serving" : driver.findElement(zeroToOneFruitsPlate).click();
                logger.info("Daily Fruit Serving intake set to - " +fruits);
                break;
            case "2 servings" : driver.findElement(twoFruitsPlate).click();
                logger.info("Daily Fruit Serving intake set to - " +fruits);
                break;
            case "3 servings" : driver.findElement(threeFruitsPlate).click();
                logger.info("Daily Fruit Serving intake set to - " +fruits);
                break;
            case "4+ servings" : driver.findElement(fourPlusFruitsPlate).click();
                logger.info("Daily Fruit Serving intake set to - " +fruits);
                break;
            default:
                logger.error("INVALID PARAMETER - Set to: '0-1 serving','2 servings','3 servings','4+ servings'.");

        }
    }

    public void setServingsOfVegetablesDaily(String vegetables){
        vegetables = vegetables.trim().toLowerCase();
        switch(vegetables){
            case "0-1 serving" : driver.findElement(zeroToOneVegetablesPlate).click();
                logger.info("Daily Vegetable Serving intake set to - " +vegetables);
                break;
            case "2 servings" : driver.findElement(twoVegetablesPlate).click();
                logger.info("Daily Vegetable Serving intake set to - " +vegetables);
                break;
            case "3 servings" : driver.findElement(threeVegetablesPlate).click();
                logger.info("Daily Vegetable Serving intake set to - " +vegetables);
                break;
            case "4+ servings" : driver.findElement(fourPlusVegetablesPlate).click();
                logger.info("Daily Vegetable Serving intake set to - " +vegetables);
                break;
            default:
                logger.error("INVALID PARAMETER - Set to: '0-1 serving','2 servings','3 servings','4+ servings'.");


        }
    }

    public void setServingsOfWholeGrainsDaily(String wholeGrains){
        wholeGrains = wholeGrains.trim().toLowerCase();
        switch(wholeGrains){
            case "0-1 serving" : driver.findElement(zeroToOneWholeGrainsPlate).click();
                logger.info("Daily Whole Grain Serving intake set to - " +wholeGrains);
                break;
            case "2 servings" : driver.findElement(twoWholeGrainsPlate).click();
                logger.info("Daily Whole Grain Serving intake set to - " +wholeGrains);
                break;
            case "3 servings" : driver.findElement(threeWholeGrainsPlate).click();
                logger.info("Daily Whole Grain Serving intake set to - " +wholeGrains);
                break;
            case "4+ servings" : driver.findElement(fourPlusWholeGrainsPlate).click();
                logger.info("Daily Whole Grain Serving intake set to - " +wholeGrains);
                break;
            default:
                logger.error("INVALID PARAMETER - Set to: '0-1 serving','2 servings','3 servings','4+ servings'.");
        }
    }

    public void setHighSaturatedFatFoodsIntake(String oftenHighInFat){
        oftenHighInFat = oftenHighInFat.trim().toLowerCase();
        switch (oftenHighInFat){
            case "less than once a week":driver.findElement(lessThanOnceSaturatedFatBtn).click();
                logger.info("Eat foods high in Saturated Fat set to - " +oftenHighInFat);
                break;
            case "once a week":driver.findElement(onceSaturatedFatBtn).click();
                logger.info("Eat foods high in Saturated Fat set to - " +oftenHighInFat);
                break;
            case "several times a week":driver.findElement(severalTimesSaturatedFatBtn).click();
                logger.info("Eat foods high in Saturated Fat set to - " +oftenHighInFat);
                break;
            case "once a day":driver.findElement(dailySaturatedFatBtn).click();
                logger.info("Eat foods high in Saturated Fat set to - " +oftenHighInFat);
                break;
            case "several times a day":driver.findElement(dailySeveralTimesSaturatedFatBtn).click();
                logger.info("Eat foods high in Saturated Fat set to - " +oftenHighInFat);
                break;
            default:
                logger.error("INVALID PARAMETER - Set to: 'less than once a week','once a week','several times a week','once a day','several times a day'.");
        }
    }

    public void setPlansForEatingHealthier(String plan){
        plan = plan.trim().toLowerCase();
        switch(plan){
            case "no change is needed" : driver.findElement(noChangeIsNeededEatingBtn).click();
                logger.info("Plans for modifying Eating set to - " +plan);
                break;
            case "maintained changes for 6+ months" : driver.findElement(maintainedChangesForSixPlusMonthsEatingBtn).click();
                logger.info("Plans for modifying Eating set to - " +plan);
                break;
            case "started making changes already" : driver.findElement(startedMakingChangesAlreadyEatingBtn).click();
                logger.info("Plans for modifying Eating set to - " +plan);
                break;
            case "plan to change in next month" : driver.findElement(planToChangeInNextMonthEatingBtn).click();
                logger.info("Plans for modifying Eating set to - " +plan);
                break;
            case "plan to change in next 6 months" : driver.findElement(planToChangeInNextSixMonthsEatingBtn).click();
                logger.info("Plans for modifying Eating set to - " +plan);
                break;
            case "no plans to change" : driver.findElement(noPlansToChangeEatingBtn).click();
                logger.info("Plans for modifying Eating set to - " +plan);
                break;
            default :
                logger.error("INVALID PARAMETER -- Set to: 'No change is needed','Maintained changes for 6+ months','Started making changes already','Plan to change in next month','Plan to change in next 6 months','No plans to change'.");
        }
    }

    public void setPlansForLosingWeight(String plan){
        plan = plan.trim().toLowerCase();
        switch(plan){
            case "no change is needed" : driver.findElement(noChangeIsNeededWeightBtn).click();
                logger.info("Plans for modifying Weight set to - " +plan);
                break;
            case "maintained changes for 6+ months" : driver.findElement(maintainedChangesForSixPlusMonthsWeightBtn).click();
                logger.info("Plans for modifying Weight set to - " +plan);
                break;
            case "started making changes already" : driver.findElement(startedMakingChangesAlreadyWeightBtn).click();
                logger.info("Plans for modifying Weight set to - " +plan);
                break;
            case "plan to change in next month" : driver.findElement(planToChangeInNextMonthWeightBtn).click();
                logger.info("Plans for modifying Weight set to - " +plan);
                break;
            case "plan to change in next 6 months" : driver.findElement(planToChangeInNextSixMonthsWeightBtn).click();
                logger.info("Plans for modifying Weight set to - " +plan);
                break;
            case "no plans to change" : driver.findElement(noPlansToChangeWeightBtn).click();
                logger.info("Plans for modifying Weight set to - " +plan);
                break;
            default :
                logger.error("INVALID PARAMETER -- Set to: 'No change is needed','Maintained changes for 6+ months','Started making changes already','Plan to change in next month','Plan to change in next 6 months','No plans to change'.");
        }
    }






}


