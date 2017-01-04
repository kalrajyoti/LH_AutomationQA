package com.lh.pages.admin.schedulerTwo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.lh.helper.DriverFactory.driver;
import static com.lh.utils.DateUtil.someDaysInFuture;

/**
 * Created by oleg.andreyev on 7/8/2016 for LiveHealthier-EnvolvePeopleCare
 * <p>
 * Summary -
 */
public class EditEventPage {

    private static Logger logger = LogManager.getLogger(NewEventPage.class);
    boolean T = true;
    boolean F = false;

    /**
     * Element Locators
     */
    By eventDateField                       = By.cssSelector("#ctl00_ContentPlaceHolder1_txtEventDate");
    By updateButton                         = By.cssSelector("#ctl00_ContentPlaceHolder1_btnAdd");

    By notificationMsg                      = By.cssSelector("#ctl00_ContentPlaceHolder1_lblMsg");


    By clientAccountDropDown                = By.cssSelector("#ctl00_ContentPlaceHolder1_ddlClientAccount");
    //By campaignSeasonField                  = By.cssSelector("#ctl00_ContentPlaceHolder1_txtSeason");
    By clientCampaignField                  = By.xpath(".//*[@id='selectCC_chosen']/ul/li/input");

    By startTimeField                       = By.cssSelector("#ctl00_ContentPlaceHolder1_txtStartTime");
    By timeZoneDropDown                     = By.cssSelector("#ctl00_ContentPlaceHolder1_ddlOffice");
    By eventNameField                       = By.cssSelector("#ctl00_ContentPlaceHolder1_txtEventName");
    By eventLocationField                   = By.cssSelector("#ctl00_ContentPlaceHolder1_txtEventLocation");
    By subLocationField                     = By.cssSelector("#ctl00_ContentPlaceHolder1_txtSubLocation");
    By regionDropDown                       = By.cssSelector("#ctl00_ContentPlaceHolder1_regionDropdownList");
    By contactNameField                     = By.cssSelector("#ctl00_ContentPlaceHolder1_txtContactName");
    By appointmentTimeField                 = By.cssSelector("#ctl00_ContentPlaceHolder1_txtSlotLength");
    By numOfAppointmentRowsSlots            = By.cssSelector("#ctl00_ContentPlaceHolder1_txtNumSlots");
    By numOfColumnsScreeners                = By.cssSelector("#ctl00_ContentPlaceHolder1_txtNumScreeners");

    By selectedOfficeCheckboxes             = By.cssSelector("#ctl00_ContentPlaceHolder1_chkListSelectedOffices");

    By showEventCheckbox                    = By.cssSelector("#ctl00_ContentPlaceHolder1_chkShowEvent");
    By linkedToPSQCheckbox                  = By.cssSelector("#ctl00_ContentPlaceHolder1_chkIsPSQ");
    By eventPackageCheckbox                 = By.xpath("//input[(@id='ctl00_ContentPlaceHolder1_chkListQEventPackages_0') and (@type='checkbox')]");

    /**
     * Constructor
     *
     */
    public EditEventPage() {
        super();
        logger.info("...initializing Admin New Event Page Object");
        if(!driver.getTitle().contains("LiveHealthier Admin - Event Information")){
            logger.error("ERROR - NOT on Edit Event Page.");
        }
        logger.info("Success - *INITIALIZED* EditEventPage.");
    }

    /**
     * Methods
     */
    public void setEventDateToBeInXDays(int numberOfDays) {
        driver.findElement(eventDateField).clear();
        driver.findElement(eventDateField).sendKeys(someDaysInFuture(numberOfDays));

        logger.info("Success - Set Event to be in * "+numberOfDays+" days. Event Date - " + someDaysInFuture(numberOfDays));
    }
    public void clickUpdateButton(){
        driver.findElement(updateButton).click();

        logger.info("Success - Clicked Update Button");
    }

    public void logout() {
        driver.findElement(By.linkText("Logout")).click();
    }
    /**
     * VERIFICATIONS
     */
    public boolean verifyFailedUpdatedAttempt(){
        WebElement failedEventChangeMsg = driver.findElement(notificationMsg);
        if(failedEventChangeMsg.getText().contains
                ("Event Date cannot be changed once appointments are booked.")){
            logger.info("Success - Properly failed to update event.");
            return T;
        }
        else{
            logger.error("FAIL - Update was supposed to be UNSUCCESSFUL.");
            return F;
        }

    }


}
