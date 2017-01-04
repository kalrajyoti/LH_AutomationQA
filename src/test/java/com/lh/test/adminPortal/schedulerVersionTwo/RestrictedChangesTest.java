package com.lh.test.adminPortal.schedulerVersionTwo;

import static org.testng.Assert.assertTrue;

import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.lh.pages.admin.AdminLoginPage;
import com.lh.pages.admin.schedulerTwo.EditEventPage;
import com.lh.pages.admin.schedulerTwo.EventListPage;
import com.lh.test.base.AuthBaseTestClass;
import com.lh.testConfig.TestProperty;
import com.lh.utils.UtilityMethods;

/**
 * Created by oleg.andreyev on 7/8/2016 for LiveHealthier-EnvolvePeopleCare
 * <p>
 * Summary - Automation in support of [C2080]
 */
@Listeners(com.lh.helper.LHTestListener.class)
public class RestrictedChangesTest extends AuthBaseTestClass {

    /**
     * TEST PROPERTIES
     */
    //=====EVENT DETAILS
    int eventID = 73;
    int numOfDaysFromEvent = 1;



    /**
     * Other Initiators
     */
    private static Logger logger = LogManager.getLogger(RestrictedChangesTest.class);
    boolean T = true;
    String testName;
    private AdminLoginPage adminLoginPage;
    private EventListPage eventListPage;
    private EditEventPage editEventPage;

    @BeforeClass
    public void initClass() {
        adminLoginPage = new AdminLoginPage();
        adminLoginPage.loginAs(TestProperty.ADMIN_USERNAME, TestProperty.ADMIN_PASSWORD);
        logger.info("Successfully Logged into the Admin Portal at - " + TestProperty.ADMIN_URL);

        eventListPage = new EventListPage();
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
        eventListPage = null;
        editEventPage = null;

        logger.info("Successfully executed the classTearDown() method of " + getClass());
    }
    @AfterMethod
    public void logout(){
        editEventPage.logout();
    }
    //---
    //====== TESTS BELOW
    //---
    @Test(description = "Verify expected failed update when reserved slot exists, TCID :- C2080")
    public void selectEventToEdit() {
        eventListPage.selectEventToEdit(eventID);
        eventListPage.verifyRedirectedToEventInformation();


        editEventPage = new EditEventPage();
        editEventPage.setEventDateToBeInXDays(numOfDaysFromEvent);

        editEventPage.clickUpdateButton();
        assertTrue(editEventPage.verifyFailedUpdatedAttempt());
    }

}
