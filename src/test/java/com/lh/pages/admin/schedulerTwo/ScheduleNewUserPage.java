package com.lh.pages.admin.schedulerTwo;

import com.lh.testConfig.TestProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static com.lh.helper.DriverFactory.driver;
import static com.lh.utils.SeleniumUtil.waitForPageLoad;

/**
 * Created by oleg.andreyev on 7/6/2016.
 */
public class ScheduleNewUserPage {

    private static Logger logger = LogManager.getLogger(ScheduleNewUserPage.class);
    boolean T = true;
    boolean F = false;

    /**
     * Element Locators
     */
    By clientAccountDropDown = By.cssSelector("#ctl00_ContentPlaceHolder1_ddlClientAccount");
    By usernameEmailField = By.cssSelector("#ctl00_ContentPlaceHolder1_txtUserName");
    By phoneNumberField = By.cssSelector("#ctl00_ContentPlaceHolder1_txtPhoneNum");
    By okayButton = By.cssSelector("#ctl00_ContentPlaceHolder1_btnOK");
    By appointmentTimeDropdown = By.cssSelector("#ctl00_ContentPlaceHolder1_ddlApointmentTime");


    /**
     * Constructor
     *
     */
    public ScheduleNewUserPage() {
        super();
        logger.info("...initializing Admin Schedule New User Page Object.");
        driver.get(TestProperty.ADMIN_URL.concat(TestProperty.USERREGISTRATION_URI));
        if(driver.getTitle().contains("LiveHealthier Admin - Schedule New User")){
            logger.equals("ERROR - Not on Schedule New User Page.");
        }
        logger.info("Success - *INITIALIZED* ScheduleNewUserPage.");
    }
    //=====================================
    /**
     * Methods Below
     */
    public void goToScheduleUser(){
        driver.get(TestProperty.ADMIN_URL.concat(TestProperty.USERREGISTRATION_URI));
    }

    public void selectClientAccount(String clientName) {
        Select select = new Select(driver.findElement(clientAccountDropDown));
        select.selectByVisibleText(clientName);

        waitForPageLoad(driver);
        // driver.findElement(clientAccountDropDown).sendKeys(clientName);

        logger.info("Success - Client set to: "+clientName);
    }
    public void setUserToRegister(String desiredUser){
        driver.findElement(usernameEmailField).sendKeys(desiredUser);

        logger.info("Success - User to be scheduled set: "+desiredUser);
    }

    /**
     * Will choose last created which is the most bottom one.
     * @param eventNameToMatch
     */
    public void selectDesiredEvent(String eventNameToMatch){
        driver.findElement(By.xpath
                ("(//*[contains(.,'"+eventNameToMatch+"')][not(.//*[contains(., '"+eventNameToMatch+"')])])[last()]")).click();

        logger.info("Success - Selected Event");
    }
    public void selectAppointmentTime(String apptTime){
        Select select = new Select(driver.findElement(appointmentTimeDropdown));
        select.selectByVisibleText(apptTime);

        logger.info("Success - Appointment time set to: " +apptTime);
    }
    public void clickOKandRegister(){
        driver.findElement(okayButton).click();

        logger.info("Success - Clicked OK and Registered.");
    }

    public void registerUserForEvent(String clientName, String eventName, String userToRegister, String appointmentTime) {
        selectClientAccount(clientName);
        waitForPageLoad(driver);
        selectDesiredEvent(eventName);
        waitForPageLoad(driver);
        selectAppointmentTime(appointmentTime);
        setUserToRegister(userToRegister);
        waitForPageLoad(driver);
        clickOKandRegister();

        logger.info("Success - Completed flow for registering user.");
    }
    public void registerUserForEvent(String clientName, String eventName, String userToRegister) {
        selectClientAccount(clientName);
        waitForPageLoad(driver);
        selectDesiredEvent(eventName);
        waitForPageLoad(driver);
        setUserToRegister(userToRegister);
        waitForPageLoad(driver);
        clickOKandRegister();

        logger.info("Success - Completed flow for registering user.");
    }



    /**
     * VERIFICATIONS
     */
    public boolean verifyClientAccountEntry(String desiredClientAccount) {
        WebElement selectElement = driver.findElement(clientAccountDropDown);
        Select selectedValue = new Select(selectElement);

        if(selectedValue.getFirstSelectedOption().getText().equalsIgnoreCase(desiredClientAccount)){
            return T;
        }
        else{
            logger.error("FAIL - Error in verifyClientAccountEntry");
            return F;
        }
        //assertThat(selectedValue.getFirstSelectedOption().getText(), is(equalTo(desiredClientAccount)));
    }
    public boolean verifySuccessfulRegistration() {
        driver.findElement(By.xpath("//*[contains(.,'Appointment Successfully Booked.')][not(.//*[contains(., 'Appointment Successfully Booked.')])]")).isDisplayed();
        logger.info("Success - User successfully booked the appointment.");
        return T;
    }

    public boolean invalidUserError() {
        driver.findElement(By.xpath("//*[contains(.,'no user with the username/email currently exists')][not(.//*[contains(., 'no user with the username/email currently exists')])]"));
        logger.info("Success - Proper Error when invalid user inputted. Appointment scheduled regardless.");
        return T;
    }

    public boolean nonMatchingClientError() {
        driver.findElement(By.xpath("//*[contains(.,'User does not belong to this client')][not(.//*[contains(., 'User does not belong to this client')])]"));
        logger.info("Success - Proper Error Handling when Admin tries to Schedule User for incorrect client.");
        return T;
    }

    public void logoutFromAdmin() {
        waitForPageLoad(driver);
        driver.findElement(By.linkText("Logout"));
    }
}
