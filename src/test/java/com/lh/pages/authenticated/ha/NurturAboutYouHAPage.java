package com.lh.pages.authenticated.ha;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.lh.utils.SeleniumUtil;

import static com.lh.helper.DriverFactory.driver;
import static com.lh.testConfig.TestProperty.WAITING_TIME;

/**
 * Created by oleg.andreyev on 9/14/2016 for LiveHealthier-EnvolvePeopleCare
 * <p>
 * Summary -
 */
public class NurturAboutYouHAPage {

    /**
     * Element Locators
     */
    By aboutYouSpecificHeader           = By.xpath("//*[contains(.,'Tell us a bit about you')][not(.//*[contains(., 'Tell us a bit about you')])]");

    By sexFemaleImg 					= By.cssSelector("input[previous-arialabel*=Female]");
    By sexMaleImg 						= By.cssSelector("input[previous-arialabel*=Male]");
    By dateOfBirthField                 = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl00_HAQuestionCtrl1_ctl00_theTextBox");
    By dateCalendar						= By.xpath("//a[@class='ui-state-default ui-state-active']");
    
    //Ethnicity+Race Section
    By africanAmericanRadioBtn          = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl02_HAQuestionCtrl1_ctl00_rbtList_0");
    By americanIndianRadioBtn           = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl02_HAQuestionCtrl1_ctl00_rbtList_1");
    By asianRadioBtn                    = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl02_HAQuestionCtrl1_ctl00_rbtList_2");
    By caucausianRadioBtn               = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl02_HAQuestionCtrl1_ctl00_rbtList_3");
    By hispanicRadioBtn                 = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl02_HAQuestionCtrl1_ctl00_rbtList_4");
    By pacificRadioBtn                  = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl02_HAQuestionCtrl1_ctl00_rbtList_5");
    By otherRadioBtn                    = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl02_HAQuestionCtrl1_ctl00_rbtList_6");

    //Preferred Language
    By englishLanguageRadioBtn          = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl04_HAQuestionCtrl1_ctl00_rbtList_0");
    By chineseLanguageRadioBtn          = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl04_HAQuestionCtrl1_ctl00_rbtList_1");
    By frenchLanguageRadioBtn          = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl04_HAQuestionCtrl1_ctl00_rbtList_2");
    By germanLanguageRadioBtn          = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl04_HAQuestionCtrl1_ctl00_rbtList_3");
    By hmongLanguageRadioBtn = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl04_HAQuestionCtrl1_ctl00_rbtList_4");
    By indonesianLanguageRadioBtn          = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl04_HAQuestionCtrl1_ctl00_rbtList_5");
    By italianLanguageRadioBtn          = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl04_HAQuestionCtrl1_ctl00_rbtList_6");
    By laotianeLanguageRadioBtn          = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl04_HAQuestionCtrl1_ctl00_rbtList_7");
    By polishLanguageRadioBtn          = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl04_HAQuestionCtrl1_ctl00_rbtList_8");
    By russianLanguageRadioBtn          = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl04_HAQuestionCtrl1_ctl00_rbtList_9");
    By spanishLanguageRadioBtn          = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl04_HAQuestionCtrl1_ctl00_rbtList_10");
    By tagalogLanguageRadioBtn          = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl04_HAQuestionCtrl1_ctl00_rbtList_11");
    By vietnameseLanguageRadioBtn          = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl04_HAQuestionCtrl1_ctl00_rbtList_12");
    By othereLanguageRadioBtn          = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl04_HAQuestionCtrl1_ctl00_rbtList_13");

    By noneOfTheAboveBox                = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl03_HAQuestionCtrl1_ctl00_checkboxList_2");
    By visionProblemsBox                = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl03_HAQuestionCtrl1_ctl00_checkboxList_0");
    By hearingProblemsBox               = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl03_HAQuestionCtrl1_ctl00_checkboxList_1");

    /**
     * Other Initiators
     */
    private static Logger logger = LogManager.getLogger(com.lh.pages.authenticated.ha.NurturAboutYouHAPage.class);

    /**
     * Constructor
     */
    public NurturAboutYouHAPage() {
        super();
        logger.info("...initializing the AboutYouHAPage Object");
        // Check that we're on the right page.
        SeleniumUtil.sleep(3);
        SeleniumUtil.waitForElementToBeVisible(aboutYouSpecificHeader, driver);
        if (!(driver.findElement(aboutYouSpecificHeader).isDisplayed())) {
            logger.error("This is not the About You Health Assessment page.");
            throw new IllegalStateException(
                    "This is not the AboutYouHAPage page.");
        }
        logger.info("INITIALIZED the About You HA Page Object.");
    }

    /**
     * Methods
     */
    public void setDateOfBirth(String mmDdYyyy){
        driver.findElement(dateOfBirthField).clear();
        driver.findElement(dateOfBirthField).sendKeys(mmDdYyyy);
        driver.findElement(By.cssSelector(".ui-datepicker-trigger")).click();
        logger.info("Successfully set the DOB to - " + mmDdYyyy);
    }

    /**
     * Clicks on the gender image
     * @param gender male/female
     */
    public void selectGender(String gender){
        new WebDriverWait(driver,WAITING_TIME).until(ExpectedConditions.elementToBeClickable(sexMaleImg));
        gender = gender.toLowerCase().trim();
        switch(gender){
            case "female" : driver.findElement(sexFemaleImg).click();
                logger.info("Setting the gender as female.");
                break;
            case "male" : driver.findElement(sexMaleImg).click();
                logger.info("Setting the gender as male.");
                break;
            default : logger.error("Invalid gender! Cannot be set...");
                break;
        }
    }

    public void setEthnicity(String ethnicity){
        ethnicity = ethnicity.toLowerCase().trim();
        switch(ethnicity){
            case "african american" : driver.findElement(africanAmericanRadioBtn).click();
                break;
            case "american indian or alaskan native" : driver.findElement(americanIndianRadioBtn).click();
                break;
            case "native hawaiian or other pacific islander" : driver.findElement(pacificRadioBtn).click();
                break;
            case "hispanic or latino" : driver.findElement(hispanicRadioBtn).click();
                break;
            case "caucasian" : driver.findElement(caucausianRadioBtn).click();
                break;
            case "asian" : driver.findElement(asianRadioBtn).click();
                break;
            case "other" : driver.findElement(otherRadioBtn).click();
            default : logger.error("Method setEthnicity FAILED - Ensure Full Value Inputted");
                break;
        }
    }

    public void setPreferredLanguage(String language){
        language = language.toLowerCase().trim();
        switch(language){
            case "english" : driver.findElement(englishLanguageRadioBtn).click();
                break;
            case "chinese" : driver.findElement(chineseLanguageRadioBtn).click();
                break;
            case "french" : driver.findElement(frenchLanguageRadioBtn).click();
                break;
            case "german" : driver.findElement(germanLanguageRadioBtn).click();
                break;
            case "hmong" : driver.findElement(hmongLanguageRadioBtn).click();
                break;
            case "indonesian" : driver.findElement(indonesianLanguageRadioBtn).click();
                break;
            case "italian" : driver.findElement(italianLanguageRadioBtn).click();
                break;
            case "laotian" : driver.findElement(laotianeLanguageRadioBtn).click();
                break;
            case "polish" : driver.findElement(polishLanguageRadioBtn).click();
                break;
            case "russian" : driver.findElement(russianLanguageRadioBtn).click();
                break;
            case "spanish" : driver.findElement(spanishLanguageRadioBtn).click();
                break;
            case "tagalog" : driver.findElement(tagalogLanguageRadioBtn).click();
                break;
            case "vietnamese" : driver.findElement(vietnameseLanguageRadioBtn).click();
                break;
            case "other" : driver.findElement(othereLanguageRadioBtn).click();
        }
    }

    public void setSpecialNeeds(String selection) {
        selection = selection.toLowerCase().trim();
        switch(selection){
            case "none of the above" : driver.findElement(noneOfTheAboveBox).click();
                break;
            case "vision problems" : driver.findElement(visionProblemsBox).click();
                break;
            case "hearing problems" : driver.findElement(hearingProblemsBox).click();
                break;
            default : logger.error("INVALID CHOICE - Select either 'none of the above', 'vision problems', or 'hearing problems'");
        }
    }
}
