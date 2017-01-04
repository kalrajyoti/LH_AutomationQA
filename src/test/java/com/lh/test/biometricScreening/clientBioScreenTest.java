package com.lh.test.biometricScreening;

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
import com.lh.pages.authenticated.MainPage;
import com.lh.pages.unauthenticated.LoginPage;
import com.lh.test.base.AuthBaseTestClass;
import com.lh.testConfig.TestProperty;

/**
 * Created by oleg.andreyev on 7/13/2016 for LiveHealthier-EnvolvePeopleCare
 * <p>
 * Summary - Supporting [C2095], [C2919]
 *      Objective is to create specified Biometric Screening appointment and navigate into Client Portal
 *      for user to successfully register for it.
 *
 * Completed
 *      - Successfully registering for an already event.
 *
 * Limitations
 *      - 7.15.16: Event must be already be created (working to fix that).  If test executed on the same day as an event,
 *      it is time dependent.
 *
 */
@Listeners(com.lh.helper.LHTestListener.class)
public class clientBioScreenTest extends AuthBaseTestClass{

    /**
     * TEST PROPERTIES
     */
    //==EVENT PROPERTIES
    int daysFromEvent = 2;
    //==REGISTRATION PROPERTIES
    String registrationTime = "10:00 AM";
    //==RESCHEDULED PROPERTIES
    String rescheduledTime = "11:30 AM";


    boolean T = true;
    String testName;
    private AdminLoginPage adminPageObj;
    private NewEventPage newEventPageObj;

    private LoginPage loginPageObj;
    private MainPage mainPageObj;
    private BiometricScreeningPage bioScreen;


    @BeforeClass
    public void initClass() {
        //adminPageObj = new AdminLoginPage(driver);
        //adminPageObj.loginAs(TestProperty.ADMIN_USERNAME, TestProperty.ADMIN_PASSWORD);
        //Reporter.log("Successfully Logged into the Admin Portal at - " + TestProperty.BASEURL);
        //newEventPageObj = new NewEventPage(driver);

        loginPageObj = new LoginPage();
        loginPageObj.loginAs(TestProperty.CLIENT_USERNAME,TestProperty.CLIENT_PASSWORD);
        bioScreen = new BiometricScreeningPage();

        Reporter.log("Successfully executed the initClass() method of " + getClass());
    }
    @BeforeMethod
    public void initMethod(Method method) throws Exception {
        testName = method.getName();

        Reporter.log("Successfully executed the initMethod() method of " + getClass());
    }

    //---
    //====== TESTS BELOW
    //---
    /**
     * Supports [C2095]
     */
    @Test()
    public void registerForExistingEvent() {
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

    /**
     * Supports [C2919]
     */
    @Test(dependsOnMethods = {"registerForExistingEvent"})
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

    /**
     * Supports [C4172]
     */
    @Test(dependsOnMethods = {"rescheduleEvent"})
    public void cancelEvent() {
        bioScreen.clickCancelLink();
        bioScreen.clickYesAppointmentCancellationConfirmation();
        assertThat(bioScreen.confirmAppointmentCancelledBox(), is(equalTo(T)));
        bioScreen.closeCancellationBox();
        assertTrue(bioScreen.confirmScheduleNowButtonDisplayed());
    }

    /**
     * Test for TC [C2458] - Ensure that when calendar carousal first opens after selecting Sub-location
     *//*
    @Test(dependsOnMethods = {"cancelEvent"})
    public void verifyAvailableDateShownImmediately() {
        bioScreen.clickScheduleNow();
        bioScreen.setLocationOfEvent(eventLocation);
        bioScreen.setSubLocationOfEvent(eventsubLocation);
        assertThat(bioScreen.verifyCalendarShowsWeekWithAvailable(), is(equalTo(T)));
    }*/


}
