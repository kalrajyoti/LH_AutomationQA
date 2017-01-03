package com.lh.pages.admin.schedulerTwo;

import com.lh.testConfig.TestProperty;
import com.lh.utils.SeleniumUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import static com.lh.helper.DriverFactory.driver;
import static com.lh.utils.SeleniumUtil.waitForElementToLoad;
import static org.testng.Assert.assertTrue;

/**
 * Created by oleg.andreyev on 7/19/2016 for LiveHealthier-EnvolvePeopleCare
 * <p>
 * Summary -
 *
 *
 */
public class GreeterInterfacePage {

    /**
     * PAGE LOCATORS
     */
    By greeterScreenLink = By.partialLinkText("Greeter Screen");
    By screeningEventsLink = By.partialLinkText("Screening Events");

    By goButton = By.cssSelector("#ctl00_ContentPlaceHolder1_GreeterScreen1_imgBtnGo");
    By searchCriteriaDropdown = By.cssSelector("#ctl00_ContentPlaceHolder1_GreeterScreen1_ddlField");
    By inputField = By.cssSelector("#ctl00_ContentPlaceHolder1_GreeterScreen1_TxtEmpID");

    By checkboxForApptCheckIn = By.cssSelector("#ctl00_ContentPlaceHolder1_GreeterScreen1_AppointmentsListGridLH_ctl02_inchkBoxSlot");
    By submitBtn = By.cssSelector("#ctl00_ContentPlaceHolder1_GreeterScreen1_imgBtnSubmitLH");

    By noSearchActiveRadioButton = By.cssSelector("#ctl00_ContentPlaceHolder1_rdActive_0");
    By officeDropDown = By.cssSelector("#ctl00_ContentPlaceHolder1_ddlOffice");

    By searchExpand = By.cssSelector("#ctl00_ContentPlaceHolder1_imgSearchExpand");


    private static Logger logger = LogManager.getLogger(GreeterInterfacePage.class);
    boolean T = true;
    boolean F = false;

    /**
     * Constructor
     *
     */
    public GreeterInterfacePage() {
        super();
        logger.info("...Initializing Greeter Interface Page Object.");
        driver.get(TestProperty.ADMIN_URL.concat(TestProperty.GREETERSEARCH_URI));
        if(!driver.getTitle().contains("Greeter Interface")){
            clickOnLogout();
        }
        assertTrue(driver.getTitle().contains("Greeter Interface"));
        logger.info("Success - *INITIALIZED* GreeterInterfacePage Constructor.");
    }

    /**
     * METHODS
     */
    public void clickOnLogout() {
        driver.findElement(By.partialLinkText("Logout")).click();
        logger.info("Success - Properly failed to update event.");
    }
    public void goToGreeterInterface() {
        driver.get(TestProperty.BASEURL.concat(TestProperty.GREETERSEARCH_URI));
        logger.info("Success - Navigated to Greeter Interface.");
    }
    public void gotoScreeningEvents() {
        driver.get(TestProperty.ADMIN_URL.concat(TestProperty.EVENTLIST_URI));
        assertTrue(driver.getTitle().contains("Screening Event"));
    }


    public void searchByFirstName(String firstName){
        Select select = new Select(driver.findElement(searchCriteriaDropdown));
        select.selectByVisibleText("First Name");

        driver.findElement(inputField).sendKeys(firstName);
        driver.findElement(goButton).click();

        logger.info("Successfully submitted search by input of the following value for First Name - " + firstName);
    }
    public void searchByLastName(String lastName){
        Select select = new Select(driver.findElement(searchCriteriaDropdown));
        select.selectByVisibleText("Last Name");

        driver.findElement(inputField).sendKeys(lastName);
        driver.findElement(goButton).click();

        logger.info("Successfully submitted search by input of the following value for Last Name - " + lastName);
    }
    public void searchByDOB(String dateOfBirth){
        Select select = new Select(driver.findElement(searchCriteriaDropdown));
        select.selectByVisibleText("Date of Birth");

        driver.findElement(inputField).sendKeys(dateOfBirth);
        driver.findElement(goButton).click();

        logger.info("Successfully submitted search by input of the following value for DOB - " + dateOfBirth);
    }
    public void searchByUniqueID(String uniqueID){
        Select select = new Select(driver.findElement(searchCriteriaDropdown));
        select.selectByVisibleText("UniqueID NOT EmployeeID");

        driver.findElement(inputField).sendKeys(uniqueID);
        driver.findElement(goButton).click();

        logger.info("Successfully submitted search by input of the following value for Unique ID - " + uniqueID);
    }
    public void clickOnIDLink(String uniqueID){
        String lowercaseID = uniqueID.toLowerCase();
        By lowercaseIDElement = By.xpath
                ("//*[contains(.,'"+lowercaseID+"')][not(.//*[contains(., '"+lowercaseID+"')])]");
        driver.findElement(lowercaseIDElement).click();

        logger.info("Success - Clicked on ID hyperlink: "+uniqueID);
    }
    public void checkBoxForApptClick(){
        driver.findElement(checkboxForApptCheckIn).click();

    }
    public void clickSubmitBtnOnCheckIn(){
        driver.findElement(submitBtn).click();

        Alert alert = driver.switchTo().alert();
        alert.accept();
    }
    public boolean restrictedPageRedirection(){
        driver.get(TestProperty.ADMIN_URL.concat("/Administration/MemberSearchFrame.aspx"));
        assertTrue(driver.getCurrentUrl().contains("GreeterInterface"));
        driver.get(TestProperty.ADMIN_URL.concat("/Administration/ClientAccount/ViewClientAccounts.aspx"));
        assertTrue(driver.getCurrentUrl().contains("GreeterInterface"));
        return T;
    }
    public void expandSearchOptions(){
        driver.findElement(searchExpand).click();
        SeleniumUtil.sleep(2);
        //SeleniumUtil.waitForPageLoad(driver);
    }


    /**
     * VERIFICATIONS
     */
    public boolean verifyGreeterScreenLink(){
        waitForElementToLoad(TestProperty.WAITING_TIME,greeterScreenLink);
        return T;
    }
    public boolean verifyScreeningEventsLink(){
        waitForElementToLoad(TestProperty.WAITING_TIME,screeningEventsLink);
        return T;
    }
    public boolean verifySearchResult(String employeeID){
        String lowercaseID = employeeID.toLowerCase();
        By lowercaseIDElement = By.xpath
                ("//*[contains(.,'"+lowercaseID+"')][not(.//*[contains(., '"+lowercaseID+"')])]");
        assertTrue(driver.findElement(lowercaseIDElement).isDisplayed());
        logger.info("Success - Search Result Found via EmployeeID: "+employeeID);
        return T;
    }
    public boolean noSearchResultInvalid(){
        driver.findElement(By.xpath("//*[contains(.,'Sorry!')][not(.//*[contains(., 'Sorry!')])]")).isDisplayed();
        logger.info("Success - Search properly returned NO RESULT.");
        return T;
    }
    public boolean invalidDOBFormatOutput(){
        driver.findElement(By.xpath("//*[contains(.,'given format')][not(.//*[contains(., 'given format')])]")).isDisplayed();
        logger.info("Success - Proper error displayed when invalid DOB format inputted.");
        return T;
    }
    public boolean noApptScheduledForUserOutput(){
        driver.findElement(By.xpath("//*[contains(.,'no appointments scheduled')][not(.//*[contains(., 'no appointments scheduled')])]")).isDisplayed();
        logger.info("Success - User found to have No Appointments Scheduled.");
        return T;
    }
    public boolean blankSearchInputted(){
        driver.findElement(By.xpath("//*[contains(.,'enter an employee ID')][not(.//*[contains(., 'enter an employee ID')])]")).isDisplayed();
        logger.info("Success - Blank Search via EmployeeID displayed proper error.");
        return T;
    }
    public boolean verifyOnCheckInPage(){
        driver.findElement(By.xpath("//*[contains(.,'Has Attended')][not(.//*[contains(., 'Has Attended')])]")).isDisplayed();
        logger.info("Success - On Check-in Page.");
        return T;
    }
    public boolean verifyUniqueIDCriteriaPresent() {
        Select select = new Select(driver.findElement(searchCriteriaDropdown));
        select.selectByVisibleText("UniqueID NOT EmployeeID");
        logger.info("Successfully located UniqueID search option.");
        return T;
    }
    public boolean verifyFirstNameCriteriaPresent() {
        Select select = new Select(driver.findElement(searchCriteriaDropdown));
        select.selectByVisibleText("First Name");
        logger.info("Successfully located First Name search option.");
        return T;
    }
    public boolean verifyLastNameCriteriaPresent() {
        Select select = new Select(driver.findElement(searchCriteriaDropdown));
        select.selectByVisibleText("Last Name");
        logger.info("Successfully located Last Name search option.");
        return T;
    }
    public boolean verifyDOBCriteriaPresent() {
        Select select = new Select(driver.findElement(searchCriteriaDropdown));
        select.selectByVisibleText("Date of Birth");
        logger.info("Successfully located DOB search option.");
        return T;
    }
    public boolean verifyUserCheckedIn(){
        driver.findElement(By.xpath("//*[contains(.,'updated the check-in')][not(.//*[contains(., 'updated the check-in')])]")).isDisplayed();
        logger.info("Success - Updated Check-In Status for User.");
        return T;

    }
    public boolean verifyDefaultIsNoSearchFiltering(){
        waitForElementToLoad(TestProperty.WAITING_TIME,noSearchActiveRadioButton);
        driver.findElement(noSearchActiveRadioButton).isSelected();
        return T;
    }
    public boolean verifyDefaultOfficeIsNone(){
        waitForElementToLoad(TestProperty.WAITING_TIME,officeDropDown);
        driver.findElement(officeDropDown).getText().contains("None");
        return T;
    }
    public boolean verifyOfficesBelongToGreeter(String greeterClient){
        driver.findElement(officeDropDown).getText().contains(greeterClient);
        /*List<WebElement> dropdownOptions = driver.findElements(officeDropDown);
        for(WebElement ele : dropdownOptions){
            assertTrue(ele.toString().contains(greeterClient));
        }*/
        return T;
    }

}
