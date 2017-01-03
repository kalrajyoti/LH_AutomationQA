package com.lh.test.adminPortal.schedulerVersionTwo;

import static com.lh.utils.DateUtil.someDaysInFuture;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;

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
import com.lh.test.base.AuthBaseTestClass;
import com.lh.testConfig.TestProperty;
import com.lh.utils.UtilityMethods;

/**
 * Created by oleg.andreyev on 7/18/2016 for LiveHealthier-EnvolvePeopleCare
 * <p>
 * Summary - Automation in support of [C7461].
 *
 * -*- Greeter is for Lubrizol
 */
@Listeners(com.lh.helper.LHTestListener.class)
public class PreviewEventTest extends AuthBaseTestClass{

    /**
     * TEST PROPERTIES
     */
    //=====EVENT CREATION
    int numOfDaysFromEvent = 2;
    String eventName = TestProperty.EVENTNAME;
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



    /**
     * Other Initiators
     */
    private static Logger logger = LogManager.getLogger(PreviewEventTest.class);
    String testName;
    AdminLoginPage adminLoginPage;
    NewEventPage newEventPage;

    @BeforeClass
    public void initClass() {
        adminLoginPage = new AdminLoginPage();
        adminLoginPage.loginAs(TestProperty.ADMIN_USERNAME, TestProperty.ADMIN_PASSWORD);
        logger.info("Successfully logged into the Admin Portal at - " + TestProperty.BASEURL);

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

        newEventPage.logoutFromAdmin();

        adminLoginPage = null;
        newEventPage = null;

        logger.info("Successfully executed the classTearDown() method of " + getClass());
    }
    //---
    //====== TESTS BELOW
    //---
    @Test(description = "[C461]",groups = "mustSetFirst")
    public void setClientAccount() {
        newEventPage.selectClientAccount(clientName);
        assertThat(newEventPage.verifyClientAccountEntry(clientName), is(equalTo(true)));
    }
    @Test(description = "[C461]",groups = "mustSetFirst")
    public void setTimeZone() {
        newEventPage.setTimeZone(timeZone);
        assertThat(newEventPage.verifyTimeZone(timeZone), is(equalTo(true)));
    }
    @Test(description = "[C461]",groups = "mustSetFirst")
    public void setClientCampaign() {
        newEventPage.setClientCampaign(clientCampaign);
        assertThat(newEventPage.verifyCampaign(clientCampaign), is(equalTo(true)));
    }
    @Test(description = "[C461]",dependsOnGroups = "mustSetFirst")
    public void setRegion() {
        newEventPage.setEventRegion(region);
        assertThat(newEventPage.verifyRegion(region), is(equalTo(true)));
    }
    @Test(description = "[C461]",groups = "creatingEvent", dependsOnGroups = "mustSetFirst")
    public void setEventDateFromCurrent() {
        newEventPage.setEventDateToBeInXDays(numOfDaysFromEvent);
        assertThat(newEventPage.verifyEventDate(someDaysInFuture(numOfDaysFromEvent)), is(equalTo(true)));
    }
    @Test(description = "[C461]",groups = "creatingEvent", dependsOnGroups = "mustSetFirst")
    public void setEventStartTime() {
        newEventPage.setStartTime(startTime);
        assertThat(newEventPage.verifyStartTime(startTime), is(equalTo(true)));
    }
    @Test(description = "[C461]",groups = "creatingEvent", dependsOnGroups = "mustSetFirst")
    public void setNameOfEvent() {
        newEventPage.setEventName(eventName);
        assertThat(newEventPage.verifyEventName(eventName), is(equalTo(true)));
    }
    @Test(description = "[C461]",groups = "creatingEvent", dependsOnGroups = "mustSetFirst")
    public void setLocation() {
        newEventPage.setEventLocation(eventLocation);
        assertThat(newEventPage.verifyLocation(eventLocation), is(equalTo(true)));
    }
    @Test(description = "[C461]",groups = "creatingEvent", dependsOnGroups = "mustSetFirst")
    public void setSubLocation() {
        newEventPage.setSubLocation(eventSubLocation);
        assertThat(newEventPage.verifySubLocation(eventSubLocation), is(equalTo(true)));
    }
    @Test(description = "[C461]",groups = "creatingEvent", dependsOnGroups = "mustSetFirst")
    public void setContactName() {
        newEventPage.setContactInfo(contactInfo);
        assertThat(newEventPage.verifyContactInfo(contactInfo), is(equalTo(true)));
    }
    @Test(description = "[C461]",groups = "creatingEvent", dependsOnGroups = "mustSetFirst")
    public void setAppointmentLength() {
        newEventPage.setAppointmentLength(appointmentLength);
        assertThat(newEventPage.verifyAppointmentLength(appointmentLength), is(equalTo(true)));
    }
    @Test(description = "[C461]",groups = "creatingEvent", dependsOnGroups = "mustSetFirst")
    public void setAppointmentRows() {
        newEventPage.setAppointmentRows(appointmentRows);
        assertThat(newEventPage.verifyApptRows(appointmentRows), is(equalTo(true)));
    }
    @Test(description = "[C461]",groups = "creatingEvent", dependsOnGroups = "mustSetFirst")
    public void setAppointmentScreenerColumns() {
        newEventPage.setNumOfScreenerColumns(appointmentColumns);
        assertThat(newEventPage.verifyApptScreenerColumns(appointmentColumns), is(equalTo(true)));
    }
    @Test(description = "[C461]",groups = "creatingEvent", dependsOnGroups = "mustSetFirst")
    public void verifyEventPackageChecked() {
        newEventPage.checkEventPackageBox();
    }
    @Test(description = "[C461]",groups = "creatingEvent", dependsOnGroups = "mustSetFirst")
    public void verifyShowEventChecked() {
        newEventPage.checkShowEventOnSiteBox();
    }
    @Test(description = "[C461]",groups = "creatingEvent", dependsOnGroups = "mustSetFirst")
    public void verifyRelatedOfficesChecked() {
        newEventPage.checkAllRelatedOfficeBoxes(clientName);
    }

    @Test(description = "Successfully Preview Event, TCID :- C461",dependsOnGroups = "creatingEvent", alwaysRun = true)
    public void previewEventAndVerify() {
        newEventPage.previewEvent();
        assertThat(newEventPage.verifyPreviewEventDisplayed(), is(equalTo(true)));
        assertThat(newEventPage.verifyReserveCheckboxes(), is(equalTo(true)));

    }


}
