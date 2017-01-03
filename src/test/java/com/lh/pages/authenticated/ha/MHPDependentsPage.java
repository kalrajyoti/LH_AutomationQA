package com.lh.pages.authenticated.ha;

import com.lh.utils.SeleniumUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import static com.lh.helper.DriverFactory.driver;

/**
 * Created by oleg.andreyev on 11/2/2016 for LiveHealthier-EnvolvePeopleCare
 * <p>
 * Summary -
 */
public class MHPDependentsPage {

    /**
     * Web Element Locators
     */
    By dependentsSectionText                                    = By.xpath("//*[contains(.,'Dependents')][not(.//*[contains(., 'Dependents')])]");

    By yesToChild                                               = By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl00_HAQuestionCtrl1_dvTwoChoiceyesno91_dvTwo']/table/tbody/tr/td[1]/input");
    By noToChild                                                = By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl00_HAQuestionCtrl1_dvTwoChoiceyesno91_dvTwo']/table/tbody/tr/td[2]/input");

    By howManyChildrenField                                     = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl01_HAQuestionCtrl1_ctl00_TheNumericTextBox");
    By dependentFirstNameField                                  = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl03_HAQuestionCtrl1_ctl00_txtTextEntry");
    By dependentLastNameField                                   = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl04_HAQuestionCtrl1_ctl00_txtTextEntry");
    By dependentDOBField                                        = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl05_HAQuestionCtrl1_ctl00_theTextBox");

    By parentRadioButton                                        = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl06_HAQuestionCtrl1_ctl00_rbtList_0");
    By guardianRadioButton                                      = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl06_HAQuestionCtrl1_ctl00_rbtList_1");
    By consentorRadioButton                                     = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl06_HAQuestionCtrl1_ctl00_rbtList_2");

    By heightFeetField                                          = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl07_HAQuestionCtrl1_ctl00_FirstTextBox_TheNumericTextBox");
    By heightInchesField                                        = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl07_HAQuestionCtrl1_ctl00_SecondTextBox_TheNumericTextBox");
    By weightPoundsField                                        = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl08_HAQuestionCtrl1_ctl00_FirstTextBox_TheNumericTextBox");
    By weightOuncesField                                        = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl08_HAQuestionCtrl1_ctl00_SecondTextBox_TheNumericTextBox");

    By noneOfTheAboveCheckbox                                   = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl09_HAQuestionCtrl1_ctl00_checkboxList_11");

    By yesToImmunizationsBox                                    = By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl10_HAQuestionCtrl1_dvTwoChoiceyesno1107_dvTwo']/table/tbody/tr/td[1]/input");
    By noToImmunizationsBox                                     = By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl10_HAQuestionCtrl1_dvTwoChoiceyesno1107_dvTwo']/table/tbody/tr/td[2]/input");


    /**
     * Other Class Initiators
     */
    private static Logger logger = LogManager.getLogger(com.lh.pages.authenticated.ha.MHPDependentsPage.class);

    /**
     * Constructor
     */
    public MHPDependentsPage(){
        super();
        logger.info("...initializing the Dependents MHP HA Page Object");
        // Check that we're on the right page.
        if (!(driver.findElement(dependentsSectionText).isDisplayed())) {
            logger.error("This is not the Dependents MHP Health Assessment page.");
            throw new IllegalStateException(
                    "This is not the Wellbeing Nurtur Health Assessment page.");
        }
        logger.info("INITIALIZED the Dependents MHP Health Assessment Page.");
    }


    //=========================
    //METHODS
    //=========================
    public void areYouAParentOrGuardian(String ans,String firstName,String lastName,String dateOfBirth,String relationship,String heightFt, String heightIn, String weightPounds, String weightOunces, String immunizations){
        ans = ans.toLowerCase().trim();
        switch (ans){
            case "yes" :
                driver.findElement(yesToChild).click();
                driver.findElement(howManyChildrenField).sendKeys("1");
                SeleniumUtil.hoverAndClick(yesToChild);
                driver.findElement(dependentFirstNameField).sendKeys(firstName);
                driver.findElement(dependentLastNameField).sendKeys(lastName);
                driver.findElement(dependentDOBField).sendKeys(dateOfBirth);
                setRelationship(relationship);
                driver.findElement(heightFeetField).sendKeys(heightFt);
                driver.findElement(heightInchesField).sendKeys(heightIn);
                driver.findElement(weightPoundsField).sendKeys(weightPounds);
                driver.findElement(weightOuncesField).sendKeys(weightOunces);
                driver.findElement(noneOfTheAboveCheckbox).click();
                setImmunizations(immunizations);
                break;
            case "no" :
                driver.findElement(noToChild).click();
                break;
            default :
                logger.error("INVALID PARAMETER! Set either 'No' or 'Yes' (and related answers)");
        }
    }
    public void setImmunizations(String ans){
        ans = ans.toLowerCase().trim();
        switch (ans){
            case "yes" :
                driver.findElement(yesToImmunizationsBox).click();
                break;
            case "no" :
                driver.findElement(noToImmunizationsBox).click();
                break;
            default :
                logger.error("INVALID PARAMETER! Set either 'No' or 'Yes'.");
        }
    }
    public void setRelationship(String ans){
        ans = ans.toLowerCase().trim();
        switch (ans){
            case "parent" :
                driver.findElement(parentRadioButton).click();
                break;
            case "medical consentor" :
                driver.findElement(consentorRadioButton).click();
                break;
            case "legal guardian" :
                driver.findElement(guardianRadioButton).click();
                break;
            default :
                logger.error("INVALID PARAMETER! Set to 'Parent','Legal Guardian','Medical Consentor'.");
        }
    }






}
