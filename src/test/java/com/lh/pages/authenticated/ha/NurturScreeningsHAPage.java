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
public class NurturScreeningsHAPage {

    /**
     * Web Element Locators
     */
    By screeningsSectionText                            = By.xpath("//*[contains(.,'recent preventative screenings')][not(.//*[contains(., 'recent preventative screenings')])]");
    By womensHealthHeader                               = By.xpath("//*[contains(.,'Women')][not(.//*[contains(., 'Women')])]");

    By noGottenFluShotBtn                               = By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl00_HAQuestionCtrl1_dvTwoChoiceyesno77_dvTwo']/table/tbody/tr/td[2]/input");
    By yesGottenFluShotBtn                              = By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl00_HAQuestionCtrl1_dvTwoChoiceyesno77_dvTwo']/table/tbody/tr/td[1]/input");

    By noHadPapSmearBtn                                 = By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl01_HAQuestionCtrl1_dvTwoChoiceyesno78_dvTwo']/table/tbody/tr/td[2]/input");
    By yesHadPapSmearBtn                                = By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl01_HAQuestionCtrl1_dvTwoChoiceyesno78_dvTwo']/table/tbody/tr/td[1]/input");

    By noColonCancerScreeningBtn                        = By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl02_HAQuestionCtrl1_dvTwoChoiceyesno79_dvTwo']/table/tbody/tr/td[2]/input");
    By yesColonCancerScreeningBtn                       = By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl02_HAQuestionCtrl1_dvTwoChoiceyesno79_dvTwo']/table/tbody/tr/td[1]/input");

    By noMammogramBtn                                   = By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl03_HAQuestionCtrl1_dvTwoChoiceyesno80_dvTwo']/table/tbody/tr/td[2]/input");
    By yesMammogramBtn                                  = By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl03_HAQuestionCtrl1_dvTwoChoiceyesno80_dvTwo']/table/tbody/tr/td[1]/input");

    By noMenopauseBtn                                   = By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl02_HAQuestionCtrl1_dvThreeChoiceyesno83_dvThree']/table/tbody/tr/td[2]/input");

    By noGestationalDiabetesBtn                         = By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl04_HAQuestionCtrl1_dvTwoChoiceyesno85_dvTwo']/table/tbody/tr/td[2]/input");


    /**
     * Other Class Initiators
     */
    private static Logger logger = LogManager.getLogger(com.lh.pages.authenticated.ha.NurturScreeningsHAPage.class);

    /**
     * Constructor
     */
    public NurturScreeningsHAPage(){
        super();
        logger.info("...initializing the Screenings Nurtur HA Page Object");
        // Check that we're on the right page.
        if (!(driver.findElement(screeningsSectionText).isDisplayed())) {
            logger.error("This is not the Screenings Nurtur Health Assessment page.");
            throw new IllegalStateException(
                    "This is not the Screenings Nurtur Health Assessment page.");
        }
        logger.info("INITIALIZED the Screenings Nurtur HA Page Object.");
    }

    //=========================
    //METHODS
    //=========================
    public void setGottenFluShot(String yesOrNo){
        yesOrNo = yesOrNo.trim().toLowerCase();
        switch(yesOrNo){
            case "yes" : driver.findElement(yesGottenFluShotBtn).click();
                logger.info("Set:  - " +yesOrNo);
                break;
            case "no" : driver.findElement(noGottenFluShotBtn).click();
                logger.info("Set:  - " +yesOrNo);
                break;
            default:
                logger.error("INVALID PARAMETER -- Input 'no' or 'yes'.");
        }
    }

    public void setHadPapSmear(String yesOrNo) {
        if (driver.findElement(yesHadPapSmearBtn).isDisplayed()) {
            yesOrNo = yesOrNo.trim().toLowerCase();
            switch (yesOrNo) {
                case "yes":
                    driver.findElement(yesHadPapSmearBtn).click();
                    logger.info("Set:  - " + yesOrNo);
                    break;
                case "no":
                    driver.findElement(noHadPapSmearBtn).click();
                    logger.info("Set:  - " + yesOrNo);
                    break;
                default:
                    logger.error("INVALID PARAMETER -- Input 'no' or 'yes'.");
            }
        }
    }

    public void setColonCancerScreening(String yesOrNo) {
        if (driver.findElement(yesColonCancerScreeningBtn).isDisplayed()) {
            yesOrNo = yesOrNo.trim().toLowerCase();
            switch (yesOrNo) {
                case "yes":
                    driver.findElement(yesColonCancerScreeningBtn).click();
                    logger.info("Set:  - " + yesOrNo);
                    break;
                case "no":
                    driver.findElement(noColonCancerScreeningBtn).click();
                    logger.info("Set:  - " + yesOrNo);
                    break;
                default:
                    logger.error("INVALID PARAMETER -- Input 'no' or 'yes'.");
            }
        }
    }

    public void setMammogramCheck(String yesOrNo){
        if(driver.findElement(yesMammogramBtn).isDisplayed()) {
            yesOrNo = yesOrNo.trim().toLowerCase();
            switch (yesOrNo) {
                case "yes":
                    driver.findElement(yesMammogramBtn).click();
                    logger.info("Set:  - " + yesOrNo);
                    break;
                case "no":
                    driver.findElement(noMammogramBtn).click();
                    logger.info("Set:  - " + yesOrNo);
                    break;
                default:
                    logger.error("INVALID PARAMETER -- Input 'no' or 'yes'.");
            }
        }
    }

    /**
     * Currently answers NO to 'Women's Health' related questions.
     *
     *
     */
    public void answerFemaleQuestionsIfApplicable(String gender) {
        gender = gender.trim().toLowerCase();
        switch(gender){
            case "female" :
                if(driver.findElement(womensHealthHeader).isDisplayed()){
                    driver.findElement(noMenopauseBtn).click();
                    driver.findElement(noGestationalDiabetesBtn).click();

                    logger.info("SET - Have you gone through menopause?  NO");
                    logger.info("SET - Have you ever had gestational diabetes?  NO");
                    logger.info("SUCCESSFULLY answered Women's Health related questions.");

                    driver.findElement(By.cssSelector("#NextButton")).click();
                    logger.info("....clicked NEXT.");
                }
                break;
            case "male" :
                break;
        }
    }
}
