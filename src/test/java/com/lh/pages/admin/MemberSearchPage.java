package com.lh.pages.admin;

import com.lh.pages.authenticated.MainPage;
import com.lh.testConfig.TestProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static com.lh.helper.DriverFactory.driver;
import static com.lh.utils.DateUtil.someDaysInFuture;
import static com.lh.utils.SeleniumUtil.waitForElementToLoad;
import static com.lh.utils.SeleniumUtil.waitForPageLoad;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * Created by oleg.andreyev on 8/5/2016 for LiveHealthier-EnvolvePeopleCare
 * <p>
 * Summary -
 */
public class MemberSearchPage extends MainPage {

    /**
     * HTML LOCATORS
     */
    By usernameField = By.cssSelector("#ctl00_ContentPlaceHolder1_txtUserName");
    By externalIDField = By.cssSelector("#ctl00_ContentPlaceHolder1_txtExtid");
    By firstNameField = By.cssSelector("#ctl00_ContentPlaceHolder1_txtFirstName");
    By lastNameField = By.cssSelector("#ctl00_ContentPlaceHolder1_txtLastName");
    By ssnField = By.cssSelector("#ctl00_ContentPlaceHolder1_txtSSN");
    By memberSSNField = By.cssSelector("#ctl00_ContentPlaceHolder1_txtMemberSSN");
    By emailField = By.cssSelector("#ctl00_ContentPlaceHolder1_txtEmail");
    By preferredEmailField = By.cssSelector("#ctl00_ContentPlaceHolder1_txtPreferredEmail");
    By searchButton = By.cssSelector("#ctl00_ContentPlaceHolder1_btnSearch");
    By newStatusDropdown = By.cssSelector("#ctl00_ContentPlaceHolder1_NewStatus");

    By biometricEditLinkFirst = By.cssSelector("#ctl00_ContentPlaceHolder1_viewResults_rptrTestResults_ctl01_aEdit");
    By biometricTestDateField = By.cssSelector("#edit_txtDate");
    By biometricUpdateButton = By.cssSelector("#edit_btnUpdate");

    By changeStatusBtn = By.cssSelector("#ctl00_ContentPlaceHolder1_SubmitButton");

    /**
     * PROPERTIES
     */
    private static Logger logger = LogManager.getLogger(MemberSearchPage.class);
    boolean T = true;
    Boolean F = false;


    /**
     * Constructer
     */
    public MemberSearchPage(){
        super();
        logger.info("...initializing Member Search Page Object");
        if(!driver.getCurrentUrl().contains("MemberSearchFrame")){
            logger.info("--Had to manually navigate via URL injection.");
            navigateToMemberSearch();
        }
        logger.info("Success - *INITIALIZED* MemberSearchPage.");
    }

    /**
     * METHODS
     */
    public void navigateToMemberSearch(){
        driver.get(TestProperty.ADMIN_URL.concat(TestProperty.MEMBERSEARCH_URI));
        waitForPageLoad(driver);
        logger.info("Success - User navigated to Admin Member Search page.");
    }
    public void searchByUsername(java.lang.String username){
        WebElement userField = driver.findElement(usernameField);

        userField.clear();
        userField.sendKeys(username);

        clickSearch();
        logger.info("Success - Executed Search via Username.");
    }
    public void searchByExternalID(java.lang.String externalID){
        WebElement eIDField = driver.findElement(externalIDField);

        eIDField.clear();
        eIDField.sendKeys(externalID);

        clickSearch();
        logger.info("Success - Executed Search via External ID.");
    }
    public void searchByFirstName(java.lang.String firstName){
        WebElement firstField = driver.findElement(firstNameField);

        firstField.clear();
        firstField.sendKeys(firstName);
        clickSearch();
        logger.info("Success - Executed Search via First Name.");;
    }
    public void searchByLastName(java.lang.String lastName){
        driver.findElement(lastNameField).clear();
        driver.findElement(lastNameField).sendKeys(lastName);

        clickSearch();
        logger.info("Success - Executed Search via Last Name.");
    }
    public void searchBySSN(java.lang.String ssn){
        driver.findElement(ssnField).clear();
        driver.findElement(ssnField).sendKeys(ssn);

        clickSearch();
        logger.info("Success - Executed Search via SSN.");
    }
    public void searchByMemberSSN(java.lang.String memberSSN){
        driver.findElement(memberSSNField).clear();
        driver.findElement(memberSSNField).sendKeys(memberSSN);

        clickSearch();
        logger.info("Success - Executed Search via Member SSN.");
    }
    public void searchByEmail(java.lang.String email){
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);

        clickSearch();
        logger.info("Success - Executed Search via Email.");
    }
    public void searchByPreferredEmail(java.lang.String preferredEmail){
        driver.findElement(preferredEmailField).clear();
        driver.findElement(preferredEmailField).sendKeys(preferredEmail);

        clickSearch();
        logger.info("Success - Executed Search via Preferred Email.");
    }
    public void clickSearch(){
        driver.findElement(searchButton).click();
        logger.info("...clicked the Search Button.");
    }
    public void updateBiometricTestDateToday(){
        driver.findElement(biometricEditLinkFirst).click();
        driver.switchTo().frame("myiframe");

        //Insert Current Date into 'Test Date' for first row.
        driver.findElement(biometricTestDateField).clear();
        driver.findElement(biometricTestDateField).sendKeys(someDaysInFuture(0));
        driver.findElement(biometricUpdateButton).click();
    }
    public void logInFromAdmin(){
        By firstResultManageButton = By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_MemberSearchResults1_DGrid1_ctl03_pnlNoActiveMember']/div/button");
        driver.findElement(firstResultManageButton).click();
        By loginAsUserLink = By.xpath("//*[contains(.,'Login As User')][not(.//*[contains(., 'Login As User')])]");
        driver.findElement(loginAsUserLink).click();

    }

    public void navigateToChangeMemberStatus(java.lang.String userID){
        driver.get(TestProperty.ADMIN_URL.concat(TestProperty.CHANGEMEMBERSTATUS_URI.concat(userID)));
    }
    public void navigateToUserIncentivePage(java.lang.String userID){
        driver.get(TestProperty.ADMIN_URL.concat(TestProperty.USERINCENTIVE_URI.concat(userID)));
    }
    public void navigateToViewBiometrics(java.lang.String userID){
        driver.get(TestProperty.ADMIN_URL.concat(TestProperty.VIEWBIOMETRICS_URI.concat(userID)));
        assertThat(driver.getTitle().contains("View Biometric Values"), is(equalTo(T)));
        logger.info("...successfully navigated to View Biometric Values page within Admin Portal.");
    }
    public void navigateToViewIncentiveStatus(String userID){
        driver.get(TestProperty.ADMIN_URL.concat(TestProperty.INCENTIVESTATUS_URI.concat(userID)));
        assertThat(driver.getTitle().contains("View Incentive Status"), is(equalTo(T)));
        logger.info("...successfully navigated to View Incentive Status page within Admin Portal.");
    }




    /**
     * VERIFICATIONS
     */
    public boolean verifySearchResult(java.lang.String userID){
        waitForElementToLoad(TestProperty.WAITING_TIME,
                By.xpath("//*[contains(.,'"+userID+"')][not(.//*[contains(., '"+userID+"')])]"));
        return T;
    }
    public boolean verifySSNSearchResult(){
        waitForElementToLoad(TestProperty.WAITING_TIME,
                By.xpath("//*[contains(.,'SSN')][not(.//*[contains(., 'SSN')])]"));
        return T;
    }
    public boolean verifyUserStatusChanged(){
        if(driver.findElement(By.xpath("//*[contains(.,'Active')][not(.//*[contains(., 'Active')])]")).isDisplayed()){
            WebElement statusDropdown = driver.findElement(newStatusDropdown);
            Select dropdown = new Select(statusDropdown);
            dropdown.selectByVisibleText("Disabled");
            driver.findElement(changeStatusBtn).click();
        }else if(driver.findElement(By.xpath("//*[contains(.,'Active')][not(.//*[contains(., 'Active')])]")).isDisplayed()){
            WebElement statusDropdown = driver.findElement(newStatusDropdown);
            Select dropdown = new Select(statusDropdown);
            dropdown.selectByVisibleText("Active");
            driver.findElement(changeStatusBtn).click();
        }
        return T;
    }
    public boolean verifyOnUsersIncentiveAdminPage(){
        if(driver.getTitle().contains("View Incentive Status")){
            return T;
        }else{
            return F;
        }
    }
    public boolean verifyBiometricTestDateUpdated(){
        By currentDate = By.xpath("//*[contains(.,'"
                +someDaysInFuture(0)+"')][not(.//*[contains(., '"+someDaysInFuture(0)+"')])]");
        waitForElementToLoad(TestProperty.WAITING_TIME,currentDate);
        return T;
    }
    public boolean verifyOnUserPortal(String client){
        waitForPageLoad(driver);
        if(driver.getCurrentUrl().contains(client)){
            logger.info("Success - Page URL contains Client within it.");
            return T;
        }else{
            return F;
        }
    }
    public boolean verifyOnIncentiveStatus(){
        By incentivePage = By.xpath
                ("//*[contains(.,'View Incentive Status')][not(.//*[contains(., 'View Incentive Status')])]");
        waitForElementToLoad(TestProperty.WAITING_TIME,incentivePage);
        return T;
    }


}
