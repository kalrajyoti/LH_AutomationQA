package com.lh.pages.admin.schedulerTwo;

import com.lh.testConfig.TestProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.lh.helper.DriverFactory.driver;

/**
 * Created by oleg.andreyev on 7/8/2016 for LiveHealthier-EnvolvePeopleCare
 * <p>
 * Summary -
 */
public class EventListPage {

    private static Logger logger = LogManager.getLogger(NewEventPage.class);
    boolean T = true;
    boolean F = false;

    /**
     * ELEMENT LOCATORS
     */
    By editEventButton = By.partialLinkText("EventEdit.aspx?EventId=14");


    //==============
    /**
     * Constructor
     *
     */
    public EventListPage(){
        super();
        logger.info("...initializing Event List Page Object");
        driver.get(TestProperty.ADMIN_URL.concat(TestProperty.EVENTLIST_URI));
        if(driver.getTitle().contains("LiveHealthier Admin - Screening Event Administration")){
            logger.error("ERROR - Not on EventListPage.");
        }
        logger.info("Success - *INITIALIZED* EventListPage.");
    }

    //================
    /**
     * METHODS
     */
    public void editFirstEvent() {
        driver.findElement(editEventButton).click();
    }

    public void selectEventToEdit(int eventID) {
        driver.get(TestProperty.ADMIN_URL.concat("/ScreeningAdmin/EventEdit.aspx?EventId=") + eventID);

        logger.info("Success - Selected Event by ID and navigating via URL");
    }


    /**
     * VERIFICATIONS
     */
    public void verifyRedirectedToEventInformation() {
        new WebDriverWait(driver,TestProperty.WAITING_TIME).until(ExpectedConditions.titleIs("LiveHealthier Admin - Event Information"));
        logger.info("Success - Properly opened event information.");
    }
}
