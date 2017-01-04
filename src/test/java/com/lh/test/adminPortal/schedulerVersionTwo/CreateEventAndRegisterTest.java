package com.lh.test.adminPortal.schedulerVersionTwo;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.testng.Assert.assertTrue;

import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.lh.pages.admin.AdminLoginPage;
import com.lh.pages.admin.schedulerTwo.NewEventPage;
import com.lh.pages.admin.schedulerTwo.ScheduleNewUserPage;
import com.lh.pages.authenticated.BiometricScreeningPage;
import com.lh.pages.authenticated.MainPage;
import com.lh.pages.unauthenticated.LoginPage;
import com.lh.test.base.AuthBaseTestClass;
import com.lh.testConfig.TestProperty;
import com.lh.utils.UtilityMethods;

/**
 * Created by Oleg Andreyev on 7/5/2016 for LiveHealthier-EnvolvePeopleCare
 *
 * Summary - Automation in support of [C2923] and [C2457]
 *
 */
@Listeners(com.lh.helper.LHTestListener.class)
public class CreateEventAndRegisterTest extends AuthBaseTestClass {

    /**
     *  TEST PROPERTIES
     */
    boolean T = true;
    //=====EVENT CREATION
    int numOfDaysFromEvent = 1;
    int daysFromEvent = 2;
    //==REGISTRATION PROPERTIES
    String registrationTime = "10:00 AM";
    //==RESCHEDULED PROPERTIES
    String rescheduledTime = "11:30 AM";
    String eventName = UtilityMethods.randomString(8) + TestProperty.EVENTNAME;
    String eventLocation = TestProperty.EVENTLOCATION;
    String eventSubLocation = TestProperty.EVENTSUBLOC;

    String clientName = TestProperty.CLIENTNAME;
    String clientCampaign = TestProperty.CLIENTCAMPAIGN;
    String startTime = TestProperty.STARTTIME;
    String timeZone = TestProperty.TIMEZONE;
    String region = TestProperty.REGION;

    String contactInfo = TestProperty.CONTACTINFO;
    String appointmentLength = TestProperty.APPTLENGTH;
    String appointmentRows = TestProperty.APPTROWS;
    String appointmentColumns = TestProperty.APPTCOLUMNS;

    //=====REGISTRATION
    String desiredUser = TestProperty.ADMIN_TESTUSER_EVENTS;




    /**
     * Other Initiators
     */
    private static Logger logger = LogManager.getLogger(CreateEventAndRegisterTest.class);
    String testName;
    private AdminLoginPage adminLoginPage;
    private NewEventPage newEventPage;
    private ScheduleNewUserPage scheduleUserPage;


    private LoginPage loginPage;
    private MainPage mainPage;
    private BiometricScreeningPage bioScreen;

    @BeforeClass
    public void initClass() {
        adminLoginPage = new AdminLoginPage();
        adminLoginPage.loginAs(TestProperty.ADMIN_USERNAME, TestProperty.ADMIN_PASSWORD);
        logger.info("Successfully Logged into the Admin Portal at - " + TestProperty.BASEURL);

        newEventPage = new NewEventPage();

        logger.info("Successfully executed the initClass() method of " + getClass());
    }
    @BeforeMethod
    public void initMethod(Method method) throws Exception {
        testName = method.getName();

        logger.info("Successfully executed the initMethod() method of " + getClass());
    }
    @AfterClass
    public void classTearDown(){
        UtilityMethods.writeOutputResult();

        adminLoginPage = null;
        newEventPage = null;
        scheduleUserPage = null;

        logger.info("Successfully executed the classTearDown() method of " + getClass());
    }
    //---
    //====== TESTS BELOW
    //---
    @Test(description = "Admin can create event, TCID :- C2457 C2923", priority = 1)
    public void adminCanCreateAndRegisterUserToEvent(){
        newEventPage.createNewEvent(clientName,clientCampaign,numOfDaysFromEvent,startTime,timeZone,eventName,
                eventLocation,eventSubLocation,region,contactInfo,appointmentLength,appointmentRows,appointmentColumns);
        assertTrue(newEventPage.verifyEventCreated());

        scheduleUserPage = new ScheduleNewUserPage();
        scheduleUserPage.registerUserForEvent(clientName,eventName,desiredUser,TestProperty.SCHEDULEDAPPTTIME);
        assertTrue(scheduleUserPage.verifySuccessfulRegistration());

        newEventPage.logoutFromAdmin();
    }
    @Test(description = "User can register user for existing event, TCID :- C2095", dependsOnMethods = {"adminCanCreateAndRegisterUserToEvent"}, priority = 3)
    public void registerForExistingEvent() {
        loginPage = new LoginPage();
        loginPage.loginAs(TestProperty.CLIENT_USERNAME,TestProperty.CLIENT_PASSWORD);
        mainPage = new MainPage();
        mainPage.clickBiometricScreening();
        bioScreen = new BiometricScreeningPage();

        bioScreen.clickScheduleNow();
        //May have to handle pop up dialog if HA never taken
        bioScreen.setLocationOfEvent(TestProperty.EVENTLOCATION);
        bioScreen.setSubLocationOfEvent(TestProperty.EVENTSUBLOC);
        bioScreen.setFirstAvailableDate();
        bioScreen.setTimeOfEvent(registrationTime);
        bioScreen.clickSubmitEventAppointment();
        assertThat(bioScreen.confirmEventTime(registrationTime), is(equalTo(T)));
        assertThat(bioScreen.confirmLocation(TestProperty.EVENTLOCATION), is(equalTo(T)));
        assertThat(bioScreen.confirmSubLocation(TestProperty.EVENTSUBLOC), is(equalTo(T)));
        bioScreen.clickConfirmEventAppointment();
        bioScreen.clickOkayEventAppointment();
        bioScreen.verifyAddToCalendarLinkPresent();
    }
    @Test(description = "User can reschedule event, TCID :- C2919",dependsOnMethods = {"registerForExistingEvent"})
    public void rescheduleEvent(){
        bioScreen.clickRescheduleLink();
        bioScreen.setLocationOfEvent(TestProperty.EVENTLOCATION);
        bioScreen.setSubLocationOfEvent(TestProperty.EVENTSUBLOC);
        bioScreen.setFirstAvailableDate();
        bioScreen.setTimeOfEvent(rescheduledTime);
        bioScreen.clickSubmitEventAppointment();
        assertThat(bioScreen.confirmEventTime(rescheduledTime), is(equalTo(T)));
        assertThat(bioScreen.confirmLocation(TestProperty.EVENTLOCATION), is(equalTo(T)));
        assertThat(bioScreen.confirmSubLocation(TestProperty.EVENTSUBLOC), is(equalTo(T)));
        bioScreen.clickConfirmEventAppointment();
        bioScreen.clickOkayEventAppointment();
        bioScreen.verifyAddToCalendarLinkPresent();
    }
    @Test(description = "User can cancel event, TCID :- C4172",dependsOnMethods = {"rescheduleEvent"})
    public void cancelEvent() {
        bioScreen.clickCancelLink();
        bioScreen.clickYesAppointmentCancellationConfirmation();
        assertThat(bioScreen.confirmAppointmentCancelledBox(), is(equalTo(T)));
        bioScreen.closeCancellationBox();
        assertTrue(bioScreen.confirmScheduleNowButtonDisplayed());
    }


}
