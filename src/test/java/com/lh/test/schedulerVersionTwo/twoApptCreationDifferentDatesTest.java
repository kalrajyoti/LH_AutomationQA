package com.lh.test.schedulerVersionTwo;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.testng.Assert.assertTrue;

import java.lang.reflect.Method;

import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.lh.pages.admin.AdminLoginPage;
import com.lh.pages.admin.schedulerTwo.NewEventPage;
import com.lh.pages.authenticated.BiometricScreeningPage;
import com.lh.pages.unauthenticated.LoginPage;
import com.lh.test.base.AuthBaseTestClass;
import com.lh.testConfig.TestProperty;
import com.lh.utils.UtilityMethods;

/**
 * Created by oleg.andreyev on 7/13/2016 for LiveHealthier-EnvolvePeopleCare
 * <p>
 * Summary - Automation in support of [C2457], [C2456] & [C2458].
 *     Objective is to verify that Client Portal displays available events that is closest to the current date
 *     in the date carousal.
 */
@Listeners(com.lh.helper.LHTestListener.class)
public class twoApptCreationDifferentDatesTest extends AuthBaseTestClass{

    boolean T = true;
    /**
     * TEST PROPERTIES
     */
    //=====EVENT ONE
    int soonerEventDaysFromEvent = 1;
    String soonerEventName = "SOONER-"+TestProperty.EVENTNAME+" - "+UtilityMethods.randomString(8);
    //=====EVENT TWO
    int laterEventDaysFromEvent = 4;
    String laterEventName = "LATER-"+TestProperty.EVENTNAME+" - "+UtilityMethods.randomString(8);


    String testName;
    AdminLoginPage adminPageObj;
    NewEventPage newEventPageObj;
    LoginPage loginPage;
    BiometricScreeningPage bioScreen;


    @BeforeClass
    public void initClass() {
        adminPageObj = new AdminLoginPage();
        adminPageObj.loginAs(TestProperty.ADMIN_USERNAME, TestProperty.ADMIN_PASSWORD);
        Reporter.log("Successfully logged into the Admin Portal at - " + TestProperty.BASEURL);

        newEventPageObj = new NewEventPage();

        Reporter.log("Successfully executed the initClass() method of " + getClass());
    }
    @BeforeMethod
    public void initMethod(Method method) throws Exception {
        testName = method.getName();

        Reporter.log("Successfully executed the initMethod() method of " + getClass());
    }

    /**
     * TESTS
     */
    @Test(description = "[C2456]",priority = 1)
    public void createFirstEvent(){
        newEventPageObj.createNewEvent(TestProperty.CLIENTACCT_GREETER,TestProperty.CAMPAIGN_GREETER,
                soonerEventDaysFromEvent,
                TestProperty.STARTTIME_GREETER,TestProperty.TIMEZONE_GREETER,
                soonerEventName,
                TestProperty.EVENTLOC_GREETER,TestProperty.EVENTSUBLOC_GREETER,TestProperty.REGION_GREETER,
                TestProperty.CONTACTINFO_GREETER,TestProperty.APPTLENGTH_GREETER,TestProperty.APPTROWS_GREETER,
                TestProperty.APPTCOLUMNS_GREETER);
        assertTrue(newEventPageObj.verifyEventCreated());

    }
    @Test(description = "[C2456]",priority = 2)
    public void createSecondEvent(){
        newEventPageObj.goToNewEventPage();
        newEventPageObj.createNewEvent(TestProperty.CLIENTACCT_GREETER,TestProperty.CAMPAIGN_GREETER,
                laterEventDaysFromEvent,
                TestProperty.STARTTIME_GREETER,TestProperty.TIMEZONE_GREETER,
                laterEventName,
                TestProperty.EVENTLOC_GREETER,TestProperty.EVENTSUBLOC_GREETER,TestProperty.REGION_GREETER,
                TestProperty.CONTACTINFO_GREETER,TestProperty.APPTLENGTH_GREETER,TestProperty.APPTROWS_GREETER,
                TestProperty.APPTCOLUMNS_GREETER);
        assertTrue(newEventPageObj.verifyEventCreated());
    }
    @Test(description = "[C2458]",priority = 3)
    public void confirmEventCalendarDisplay(){
        newEventPageObj.logoutFromAdmin();
        loginPage = new LoginPage();
        loginPage.loginAs(TestProperty.CLIENT_USERNAME,TestProperty.CLIENT_PASSWORD);

        bioScreen = new BiometricScreeningPage();
        bioScreen.clickScheduleNow();
        bioScreen.setLocationOfEvent(TestProperty.EVENTLOCATION);
        bioScreen.setSubLocationOfEvent(TestProperty.EVENTSUBLOC);
        assertThat(bioScreen.verifyCalendarShowsWeekWithAvailable(), is(equalTo(T)));
    }
}
