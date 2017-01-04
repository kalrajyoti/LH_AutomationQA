package com.lh.test.schedulerVersionTwo;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.testng.Assert.assertTrue;

import java.lang.reflect.Method;

import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.lh.pages.admin.AdminLoginPage;
import com.lh.pages.admin.schedulerTwo.GreeterInterfacePage;
import com.lh.pages.admin.schedulerTwo.NewEventPage;
import com.lh.pages.admin.schedulerTwo.ScheduleNewUserPage;
import com.lh.test.base.AuthBaseTestClass;
import com.lh.testConfig.TestProperty;
import com.lh.utils.UtilityMethods;

/**
 * Created by oleg.andreyev on 7/19/2016 for LiveHealthier-EnvolvePeopleCare
 * <p>
 * Summary - In support of [C81160]
 */
@Listeners(com.lh.helper.LHTestListener.class)
public class greeterTest extends AuthBaseTestClass{

    /**
     * Test Properties
     */
    boolean T = true;
    int daysFromEvent = 0;
    String eventName = UtilityMethods.randomString(8) + " - " + TestProperty.EVENTNAME_GREETER;

    /**
     * Other Initiators
     */
    String testName;

    AdminLoginPage adminLoginPageObj;
    GreeterInterfacePage greeterPageObj;
    NewEventPage newEventPageObj;
    ScheduleNewUserPage scheduleUserPageObj;

    @BeforeClass
    public void initClass() {
        adminLoginPageObj = new AdminLoginPage();
        adminLoginPageObj.loginAs(TestProperty.GREETER_USERNAME,TestProperty.GREETER_PASSWORD);
        Reporter.log("Successfully logged into the Admin Portal as a greeter at - " + TestProperty.BASEURL);

        greeterPageObj = new GreeterInterfacePage();
        Reporter.log("Successfully navigated to Greeter Home and initialized Page Object.");
    }
    @BeforeMethod
    public void initMethod(Method method) throws Exception {
        testName = method.getName();

        Reporter.log("Successfully initiated initMethod() of " + getClass());
    }

    //=====
    //TESTS
    //=====
    /**
     * TODO
     * Overview of Test Below
     * 1) Sign into Admin Portal
     * 2) Create Screening for Lubrizol
     * 3) Schedule user for event via Admin
     * 4) Navigate to Greeter Screen
     * 5) Search for registered user
     * 6) Click on ID-link, check the box, and Submit.
     *       Assert that output is 'Successfully updated the check-in info for the user.'
     */
    @Test(dependsOnGroups = "a")
    public void checkInUser(){
        greeterPageObj.clickOnLogout();
        adminLoginPageObj.loginAs(TestProperty.ADMIN_USERNAME, TestProperty.ADMIN_PASSWORD);
        newEventPageObj = new NewEventPage();
        newEventPageObj.createNewEvent(TestProperty.CLIENTACCT_GREETER,TestProperty.CAMPAIGN_GREETER,daysFromEvent,
                TestProperty.STARTTIME_GREETER,TestProperty.TIMEZONE_GREETER,eventName,
                TestProperty.EVENTLOC_GREETER,TestProperty.EVENTSUBLOC_GREETER,TestProperty.REGION_GREETER,
                TestProperty.CONTACTINFO_GREETER,TestProperty.APPTLENGTH_GREETER,TestProperty.APPTROWS_GREETER,
                TestProperty.APPTCOLUMNS_GREETER);
        assertTrue(newEventPageObj.verifyEventCreated());

        scheduleUserPageObj = new ScheduleNewUserPage();
        scheduleUserPageObj.registerUserForEvent(TestProperty.CLIENTACCT_GREETER,eventName,
                TestProperty.USER_TO_SCHEDULE_GREETER);
        assertTrue(scheduleUserPageObj.verifySuccessfulRegistration());
        greeterPageObj.clickOnLogout();
        adminLoginPageObj.loginAs(TestProperty.GREETER_USERNAME,TestProperty.GREETER_PASSWORD);
        greeterPageObj.goToGreeterInterface();
        greeterPageObj.searchByUniqueID(TestProperty.USER_TO_SCHEDULE_GREETER);
        greeterPageObj.clickOnIDLink(TestProperty.USER_TO_SCHEDULE_GREETER);
        assertTrue(greeterPageObj.verifyOnCheckInPage());
        greeterPageObj.checkBoxForApptClick();
        greeterPageObj.clickSubmitBtnOnCheckIn();
        assertTrue(greeterPageObj.verifyUserCheckedIn());

    }

    @Test(groups = "a")
    public void confirmSearchCriteriaOptions() {
        greeterPageObj.goToGreeterInterface();
        assertThat(greeterPageObj.verifyUniqueIDCriteriaPresent(), is(equalTo(T)));
        assertThat(greeterPageObj.verifyFirstNameCriteriaPresent(), is(equalTo(T)));
        assertThat(greeterPageObj.verifyLastNameCriteriaPresent(), is(equalTo(T)));
        assertThat(greeterPageObj.verifyDOBCriteriaPresent(), is(equalTo(T)));
    }
    @Test
    public void searchByFirstName(){
        greeterPageObj.goToGreeterInterface();
        greeterPageObj.searchByFirstName(TestProperty.USER_FIRSTNAME);
        assertTrue(greeterPageObj.verifySearchResult(TestProperty.USER_ID));
    }
    @Test
    public void searchByLastName(){
        greeterPageObj.goToGreeterInterface();
        greeterPageObj.searchByLastName(TestProperty.USER_LASTNAME);
        assertTrue(greeterPageObj.verifySearchResult(TestProperty.USER_ID));
    }
    @Test
    public void searchByDOB(){
        greeterPageObj.goToGreeterInterface();
        greeterPageObj.searchByDOB(TestProperty.USER_DOB);
        assertTrue(greeterPageObj.verifySearchResult(TestProperty.USER_ID));
    }
    @Test
    public void searchByUniqueID(){
        greeterPageObj.goToGreeterInterface();
        greeterPageObj.searchByUniqueID(TestProperty.USER_ID);
        assertTrue(greeterPageObj.verifySearchResult(TestProperty.USER_ID));
    }
    @Test
    public void nonExistingFirstNameSearch(){
        greeterPageObj.goToGreeterInterface();
        greeterPageObj.searchByFirstName("INVALID-FIRST");
        assertTrue(greeterPageObj.noSearchResultInvalid());
    }
    @Test
    public void nonExistingLastNameSearch(){
        greeterPageObj.goToGreeterInterface();
        greeterPageObj.searchByLastName("INVALID-LAST");
        assertTrue(greeterPageObj.noSearchResultInvalid());
    }
    @Test
    public void nonExistingDOBSearch(){
        greeterPageObj.goToGreeterInterface();
        greeterPageObj.searchByDOB("01/01/2001");
        assertTrue(greeterPageObj.noSearchResultInvalid());
    }
    @Test
    public void invalidDOBEntry(){
        greeterPageObj.goToGreeterInterface();
        greeterPageObj.searchByDOB("*INVALID*");
        assertTrue(greeterPageObj.invalidDOBFormatOutput());
    }
    @Test
    public void nonExistingUniqueIDSearch(){
        greeterPageObj.goToGreeterInterface();
        greeterPageObj.searchByUniqueID("INVALID-ID");
        assertTrue(greeterPageObj.noSearchResultInvalid());
    }
    @Test
    public void validSearchBUTNoApptScheduled(){
        greeterPageObj.goToGreeterInterface();
        greeterPageObj.searchByUniqueID(TestProperty.USER_ID);
        greeterPageObj.clickOnIDLink(TestProperty.USER_ID);
        assertTrue(greeterPageObj.noApptScheduledForUserOutput());
    }
    @Test
    public void emptyFieldSearch(){
        greeterPageObj.goToGreeterInterface();
        greeterPageObj.searchByUniqueID("");
        assertTrue(greeterPageObj.blankSearchInputted());
    }

}
