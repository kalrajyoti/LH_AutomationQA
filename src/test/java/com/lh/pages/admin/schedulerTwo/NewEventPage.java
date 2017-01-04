package com.lh.pages.admin.schedulerTwo;

import com.lh.testConfig.TestProperty;
import com.lh.utils.SeleniumUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;

import static com.lh.helper.DriverFactory.driver;
import static com.lh.utils.DateUtil.someDaysInFuture;

/**
 * Created by oleg.andreyev on 7/5/2016.
 */
public class NewEventPage {

    private static Logger logger = LogManager.getLogger(NewEventPage.class);
    boolean T = true;
    boolean F = false;

    /**
     * Element Locators
     */
    By addNewScreeningEventButton           = By.cssSelector("#ctl00_ContentPlaceHolder1_btnAdd");
    By clientAccountDropDown                = By.cssSelector("#ctl00_ContentPlaceHolder1_ddlClientAccount");
    //By campaignSeasonField                  = By.cssSelector("#ctl00_ContentPlaceHolder1_txtSeason");
    By clientCampaignField                  = By.xpath(".//*[@id='selectCC_chosen']/ul/li/input");
    By eventDateField                       = By.cssSelector("#ctl00_ContentPlaceHolder1_txtEventDate");
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

    //**PREVIEW EVENT**
    By previewButton                        = By.cssSelector("#ctl00_ContentPlaceHolder1_btnPreview");
    By reserveEventCheckbox                 = By.xpath("//*[@id=\"ctl00_ContentPlaceHolder1_tblSchedule\"]/tbody/tr[3]/td[2]/input");
    By closePreviewButton                   = By.cssSelector("#btnclose");


    /**
     * Constructor
     *
     */
    public NewEventPage() {
        super();
        logger.info("...Initializing Admin New Event Page Object");
        goToNewEventPage();
        if(SeleniumUtil.element(addNewScreeningEventButton, driver).isDisplayed()){
            logger.error("ERROR - Not on NewEventPage.");
        }
        logger.info("Success - *INITIALIZED* NewEventPage.");
    }

    /**
     * Methods

     */
    public void goToNewEventPage(){
        driver.get(TestProperty.ADMIN_URL.concat(TestProperty.NEWEVENT_URL));
    }
    public void createNewEvent(String client, String campaign, int daysFromEvent, String startTime, String timeZone, String eventName,
                               String eventLoc, String eventSubLoc, String region, String contact, String apptLengthMins, String apptRows, String apptScreenersColumns){
        SeleniumUtil.waitForPageLoad(driver);
        selectClientAccount(client);
        setTimeZone(timeZone);
        setClientCampaign(campaign);
        setEventRegion(region);
        setEventDateToBeInXDays(daysFromEvent);
        setStartTime(startTime);
        setEventName(eventName);
        setEventLocation(eventLoc);
        setSubLocation(eventSubLoc);
        setContactInfo(contact);
        setAppointmentLength(apptLengthMins);
        setAppointmentRows(apptRows);
        setNumOfScreenerColumns(apptScreenersColumns);
        //checkEventPackageBox();
        checkShowEventOnSiteBox();
        checkAllRelatedOfficeBoxes(client);
        submitAndCreateEvent();

        logger.info("Successfully executed createNewEvent method.");
    }

    public void selectClientAccount(String clientName) {
        Select select = new Select(driver.findElement(clientAccountDropDown));
        select.selectByVisibleText(clientName);
        SeleniumUtil.waitForPageLoad(driver);

        verifyClientAccountEntry(clientName);
        logger.info("Successfully set Client Account as - " + clientName);
        // driver.findElement(clientAccountDropDown).sendKeys(clientName);
    }
    public void setCampaignSeason(String campaignSeason) {
        //driver.findElement(campaignSeasonField).sendKeys(campaignSeason);
    }
    public void setClientCampaign(String clientCampaign) {
        WebElement clientCampaignArea = driver.findElement(clientCampaignField);
        Actions actions = new Actions(driver);
        actions.doubleClick(clientCampaignArea).perform();
        clientCampaignArea.sendKeys(clientCampaign);
        clientCampaignArea.sendKeys(Keys.RETURN);
        SeleniumUtil.waitForPageLoad(driver);

        verifyCampaign(clientCampaign);
        logger.info("Successfully set Client Campaign as - " + clientCampaign);
    }

    public void submitAndCreateEvent(){
        driver.findElement(addNewScreeningEventButton).click();

        logger.info("Success - submitAndCreateEvent");
    }

    /**
     * Sets the event date to be a given numbers of days in the future, set by the parameter.
     *
     * @param numberOfDays
     */
    public void setEventDateToBeInXDays(int numberOfDays) {
        driver.findElement(eventDateField).sendKeys(someDaysInFuture(numberOfDays));

        verifyEventDate(someDaysInFuture(numberOfDays));
        logger.info("Success - Event Date set to be in " + numberOfDays + " days.");
        logger.info("Success - Event Date is - " + someDaysInFuture(numberOfDays));
    }
    public void setStartTime(String time) {
        driver.findElement(startTimeField).sendKeys(time);

        verifyStartTime(time);
        logger.info("Success - Event Start Time set to: " + time);
    }
    public void setTimeZone(String timeZone) {
        driver.findElement(timeZoneDropDown).sendKeys(timeZone);
        SeleniumUtil.waitForPageLoad(driver);

        verifyTimeZone(timeZone);
        logger.info("Success - Timezone set to: " + timeZone);
    }
    public void setEventName(String eventName) {
        driver.findElement(eventNameField).sendKeys(eventName);

        verifyEventName(eventName);
        logger.info("Success - Event Name set to: " + eventName);
    }
    public void setEventLocation(String location) {
        driver.findElement(eventLocationField).sendKeys(location);

        verifyLocation(location);
        logger.info("Success - Event Location set to: " + location);
    }
    public void setSubLocation(String subLocation) {
        driver.findElement(subLocationField).sendKeys(subLocation);

        verifySubLocation(subLocation);
        logger.info("Success - Event SubLocation set to: " + subLocation);
    }
    public void setEventRegion(String region) {
        driver.findElement(regionDropDown).sendKeys(region);
        SeleniumUtil.waitForPageLoad(driver);

        verifyRegion(region);
        logger.info("Success - Event Region set to: " + region);
    }
    public void setContactInfo(String contactName) {
        driver.findElement(contactNameField).sendKeys(contactName);

        verifyContactInfo(contactName);
        logger.info("Success - Event Contact Info set to: " + contactName);
    }
    public void setAppointmentLength(String timeInMins) {
        WebElement apptTime = driver.findElement(appointmentTimeField);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(false);", apptTime);
        driver.findElement(appointmentTimeField).sendKeys(timeInMins);

        verifyAppointmentLength(timeInMins);
        logger.info("Success - Event Appt Time(mins) set to: " + timeInMins);

    }
    public void setAppointmentRows(String numberOfRows) {
        driver.findElement(numOfAppointmentRowsSlots).sendKeys(numberOfRows);

        verifyApptRows(numberOfRows);
        logger.info("Success - Event Appt Rows set to: " + numberOfRows);
    }
    public void setNumOfScreenerColumns(String numberOfColumns) {
        driver.findElement(numOfColumnsScreeners).sendKeys(numberOfColumns);

        verifyApptScreenerColumns(numberOfColumns);
        logger.info("Success - Event Number of Screeners/Columns set to: " + numberOfColumns);

    }
    public void checkEventPackageBox() {
        /*new WebDriverWait(driver,TestProperty.WAITING_TIME).until(ExpectedConditions.elementToBeClickable(eventPackageCheckbox));
        driver.findElement(eventPackageCheckbox).click();*/


        WebElement checkboxEventPackage = driver.findElement(eventPackageCheckbox);
        //checkboxEventPackage.getLocation();

        /*Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(eventPackageCheckbox)).click().perform();
        logger.info("Success - Checkbox 'Event Package' checked.");*/


        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(false);", checkboxEventPackage);
        if(!driver.findElement(eventPackageCheckbox).isSelected()){
        driver.findElement(eventPackageCheckbox).click();
            logger.info("Clicked on 'Event Package' checkbox successfully.");
        }

    }
    public void checkShowEventOnSiteBox() {
        WebElement showEventBox = driver.findElement(showEventCheckbox);

        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(false);", showEventBox);
        if(!driver.findElement(showEventCheckbox).isSelected()){
            driver.findElement(showEventCheckbox).click();
            logger.info("Clicked on 'Event Package' checkbox successfully.");
        }
        //new WebDriverWait(driver,TestProperty.WAITING_TIME).until(ExpectedConditions.elementToBeClickable(showEventCheckbox));
        //driver.findElement(showEventCheckbox).click();

        /*Actions actions = new Actions(driver);
        actions.moveToElement(showEventBox).click().perform();*/

        logger.info("Success - Checkbox 'Show Event on Site' checked.");
        /*if(!driver.findElement(showEventCheckbox).isSelected()){
            driver.findElement(showEventCheckbox).click();
            logger.info("Clicked on 'Show Event' checkbox successfully.");
        }*/
    }

    public void previewEvent() {
        //Handling of New Tab Opening
        String oldTab = driver.getWindowHandle();
        driver.findElement(previewButton).click();

        ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
        newTab.remove(oldTab);
        // change focus to new tab
        driver.switchTo().window(newTab.get(0));

        logger.info("Success - Event Previewed successfully.");
    }

    /**
     * This method goes through the table of Client Offices and checks the ones that contain some part of the parameter.
     *
     * @param matchingCharacteristic
     */
    public void checkAllRelatedOfficeBoxes(String matchingCharacteristic){
        int checkboxNumber = driver.findElements(By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_chkListSelectedOffices']//*[contains(text(),'"+matchingCharacteristic+"')]")).size();
        for (int i = 0; i < checkboxNumber; i++) {
            WebElement ele = driver.findElements(By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_chkListSelectedOffices']//*[contains(text(),'"+matchingCharacteristic+"')]")).get(i);
            if (!ele.isSelected())
                ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(false);", ele);
                ele.click();
                logger.info("Selected 'Client Office' checkbox - " +i+ ".  Total number matching offices is - "+checkboxNumber);
        }
                //ele.click();
    }


        //logger.info("Select following Office - " + driver.find)
        /*WebElement box = driver.findElement(By.xpath("/*//*[contains(text(), '" + stringToSelect + "')])"));
        List<WebElement> lc = box.findElements(By.tagName("input"));
        for (int i = 0; i <= lc.size(); i++) {
            lc.get(i).click();
        }*/
        /*//CoreLogic - MHEDU HSA
        WebElement boxCoreLogicMHEDUHSA = driver.findElement(By.cssSelector("#ctl00_ContentPlaceHolder1_chkListSelectedOffices_49"));
        boxCoreLogicMHEDUHSA.click();
        assertThat(boxCoreLogicMHEDUHSA.isSelected(), is(equalTo(T)));

        //CoreLogic - Regular Hires
        WebElement boxCoreLogicRegularHires = driver.findElement(By.cssSelector("#ctl00_ContentPlaceHolder1_chkListSelectedOffices_177"));
        boxCoreLogicRegularHires.click();
        assertThat(boxCoreLogicRegularHires.isSelected(), is(equalTo(T)));*/


    /**
     * Verifications of Actions Performed
     *
     */
    public boolean verifyClientAccountEntry(String desiredClientAccount) {
        WebElement selectElement = driver.findElement(clientAccountDropDown);
        Select selectedValue = new Select(selectElement);

        logger.info("Desired Client Account: " + desiredClientAccount);
        logger.info("Selected Client Account: " + selectedValue.getFirstSelectedOption().getText());

        if(selectedValue.getFirstSelectedOption().getText().equalsIgnoreCase(desiredClientAccount)){
            logger.info("Successfully verified Client Account entry.");
            return T;
        }
        else{
            logger.error("FAIL - verifyClientAccountEntry");
            return F;
        }
        //assertThat(selectedValue.getFirstSelectedOption().getText(), is(equalTo(desiredClientAccount)));
    }
    public boolean verifyCampaign(String desiredCampaign){
        WebElement selectedCampaign = driver.findElement(By.cssSelector("#selectCC_chosen > ul > li.search-choice > span"));

        logger.info("Desired Campaign: " + desiredCampaign);
        logger.info("Selected Campaign: " + selectedCampaign.getAttribute("innerHTML"));

        if(selectedCampaign.getAttribute("innerHTML").equalsIgnoreCase(desiredCampaign)){
            logger.info("Success - verifyCampaign");
            return T;
        }
        else{
            logger.error("FAIL - verifyCampaign");
            return F;
        }
        //assertThat(driver.findElement(By.cssSelector("#selectCC_chosen > ul > li.search-choice > span")).getAttribute("innerHTML").));
    }
    public boolean verifyEventDate(String desiredDate){
        logger.info("Desired Date: " + desiredDate);
        logger.info("Selected Date: " + driver.findElement(eventDateField).getAttribute("value"));

        if(driver.findElement(eventDateField).getAttribute("value").equalsIgnoreCase(desiredDate)){
            logger.info("Success - verifyEventDate");
            return T;
        }
        else{
            logger.error("FAIL - verifyEventDate");
            return F;
        }
        //assertThat(driver.findElement(eventDateField).getAttribute("value"), is(equalTo(desiredDate)));
    }
    public boolean verifyStartTime(String desiredStartTime){
        logger.info("Desired Start Time: " + desiredStartTime);
        logger.info("Selected Start: " + driver.findElement(startTimeField).getAttribute("value"));

        if(driver.findElement(startTimeField).getAttribute("value").equalsIgnoreCase(desiredStartTime)){
            logger.info("Success - verifyStartTime");
            return T;
        }
        else{
            logger.error("FAIL - verifyStartTime");
            return F;
        }
        //assertThat(driver.findElement(startTimeField).getAttribute("value"), is(equalTo(desiredStartTime)));
    }
    public boolean verifyTimeZone(String desiredTimeZone){
        WebElement selectElement = driver.findElement(timeZoneDropDown);
        Select selectedValue = new Select(selectElement);

        logger.info("Desired Timezone: " + desiredTimeZone);
        logger.info("Selected Timezone: " + selectedValue.getFirstSelectedOption().getText());

        if(selectedValue.getFirstSelectedOption().getText().equalsIgnoreCase(desiredTimeZone)){
            logger.info("Success - verifyTimeZone");
            return T;
        }
        else{
            logger.error("FAIL - verifyTimeZone");
            return F;
        }
        //assertThat(selectedValue.getFirstSelectedOption().getText(), is(equalTo(desiredTimeZone)));
    }
    public boolean verifyEventName(String desiredName){
        logger.info("Desired Event Name: " + desiredName);
        logger.info("Selected Event Name: " + driver.findElement(eventNameField).getAttribute("value"));

        if(driver.findElement(eventNameField).getAttribute("value").equalsIgnoreCase(desiredName)){
            logger.info("Success - verifyEventName");
            return T;
        }
        else{
            logger.error("FAIL - verifyEventName");
            return F;
        }
        //assertThat(driver.findElement(eventNameField).getAttribute("value"), is(equalTo(desiredName)));
    }
    public boolean verifyLocation(String desiredLocation){
        logger.info("Desired Location: " + desiredLocation);
        logger.info("Selected Location: " + driver.findElement(eventLocationField).getAttribute("value"));

        if(driver.findElement(eventLocationField).getAttribute("value").equalsIgnoreCase(desiredLocation)){
            logger.info("Success - verifyLocation");
            return T;
        }
        else{
            logger.error("FAIL - verifyLocation");
            return F;
        }
        //assertThat(driver.findElement(eventLocationField).getAttribute("value"), is(equalTo(desiredLocation)));
    }
    public boolean verifySubLocation(String desiredSubLocation){
        logger.info("Desired Sub-Location: " + desiredSubLocation);
        logger.info("Selected Sub-Location: " + driver.findElement(subLocationField).getAttribute("value"));

        if(driver.findElement(subLocationField).getAttribute("value").equalsIgnoreCase(desiredSubLocation)){
            logger.info("Success - verifySubLocation");
            return T;
        }
        else{
            logger.error("FAIL - verifySubLocation");
            return F;
        }
        //assertThat(driver.findElement(subLocationField).getAttribute("value"), is(equalTo(desiredSubLocation)));
    }
    public boolean verifyRegion(String desiredRegion){
        WebElement selectElement = driver.findElement(regionDropDown);
        Select selectedValue = new Select(selectElement);

        logger.info("Desired Region: " + desiredRegion);
        logger.info("Selected Region: " + selectedValue.getFirstSelectedOption().getText());

        if(selectedValue.getFirstSelectedOption().getText().equalsIgnoreCase(desiredRegion)){
            logger.info("Success - verifyRegion");
            return T;
        }
        else{
            logger.error("FAIL - verifyRegion");
            return F;
        }
        //assertThat(selectedValue.getFirstSelectedOption().getText(), is(equalTo(desiredRegion)));
    }
    public boolean verifyContactInfo(String desiredContactInfo){
        logger.info("Desired Contact Info: " + desiredContactInfo);
        logger.info("Selected Contact Info: " + driver.findElement(contactNameField).getAttribute("value"));

        if(driver.findElement(contactNameField).getAttribute("value").equalsIgnoreCase(desiredContactInfo)){
            logger.info("Success - verifyContactInfo");
            return T;
        }
        else{
            logger.error("FAIL - verifyContactInfo");
            return F;
        }
        //assertThat(driver.findElement(contactNameField).getAttribute("value"), is(equalTo(desiredContactInfo)));
    }
    public boolean verifyAppointmentLength(String desiredApptTime){
        logger.info("Desired Appt Length: " + desiredApptTime);
        logger.info("Selected Appt Length: " + driver.findElement(appointmentTimeField).getAttribute("value"));

        if(driver.findElement(appointmentTimeField).getAttribute("value").equalsIgnoreCase(desiredApptTime)){
            logger.info("Success - verifyAppointmentLength");
            return T;
        }
        else{
            logger.error("FAIL - verifyAppointmentLength");
            return F;
        }
        //assertThat(driver.findElement(appointmentTimeField).getAttribute("value"), is(equalTo(desiredApptTime)));
    }
    public boolean verifyApptRows(String desiredApptRows){
        logger.info("Desired Appt Rows: " + desiredApptRows);
        logger.info("Selected Appt Rows: " + driver.findElement(numOfAppointmentRowsSlots).getAttribute("value"));

        if(driver.findElement(numOfAppointmentRowsSlots).getAttribute("value").equalsIgnoreCase(desiredApptRows)){
            logger.info("Success - verifyApptRows");
            return T;
        }
        else{
            logger.error("FAIL - verifyApptRows");
            return F;
        }
        //assertThat(driver.findElement(numOfAppointmentRowsSlots).getAttribute("value"), is(equalTo(desiredApptRows)));
    }
    public boolean verifyApptScreenerColumns(String desiredScreeners){
        logger.info("Desired Screeners/Columns: " + desiredScreeners);
        logger.info("Selected Screeners/Columns: " + driver.findElement(numOfColumnsScreeners).getAttribute("value"));

        if(driver.findElement(numOfColumnsScreeners).getAttribute("value").equalsIgnoreCase(desiredScreeners)){
            logger.info("Success - verifyApptScreenerColumns");
            return T;
        }
        else{
            logger.error("FAIL - verifyApptScreenerColumns");
            return F;
        }
        //assertThat(driver.findElement(numOfColumnsScreeners).getAttribute("value"), is(equalTo(desiredScreeners)));
    }
    public boolean verifyEventCreated(){
        WebElement successEventAddMessage = driver.findElement(By.cssSelector("#ctl00_ContentPlaceHolder1_lblMsg"));
        if(successEventAddMessage.getText().equalsIgnoreCase("SCREENING Event Added Successfully.")){
            logger.info("Success - verifyEventCreated");
            return T;
        }
        else{
            logger.info("FAIL - Could NOT verify Event Created");
            return F;
        }
    }



    /**
     * PREVIEW EVENT
     */
    public boolean verifyPreviewEventDisplayed() {
        if(driver.findElement(closePreviewButton).isDisplayed()){
            logger.info("Success - Executed verifyPreviewEventDisplayed");
            return T;
        }
        else{
            logger.error("FAIL - Could NOT verifyPreviewEventDisplayed");
            return F;
        }
    }
    public boolean verifyReserveCheckboxes() {
        if(driver.findElement(reserveEventCheckbox).isDisplayed()){
            driver.findElement(reserveEventCheckbox).click();
            logger.info("Success - Executed verifyReserveCheckboxes");
            return T;
        }
        else{
            logger.error("FAIL - Could NOT verify Reserve Checkboxes");
            return F;
        }

    }

    public void logoutFromAdmin() {
        driver.findElement(By.linkText("Logout")).click();
    }
}
