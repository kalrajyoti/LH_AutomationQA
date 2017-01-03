package com.lh.test.adminPortal.schedulerVersionTwo;

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
import com.lh.pages.admin.schedulerTwo.ScheduleNewUserPage;
import com.lh.test.base.AuthBaseTestClass;
import com.lh.testConfig.TestProperty;
import com.lh.utils.UtilityMethods;

/**
 * Created by oleg.andreyev on 7/25/2016 for LiveHealthier-EnvolvePeopleCare
 * <p>
 * Summary - Automation in support of [C2925], [C2924]
 */
@Listeners
public class ErrorRegistrationCasesTest extends AuthBaseTestClass{

    /**
     * Test Properties
     */
    String invalidUsername = "invalidUSEROA";
    String differentClientsUser = "lhtestcore_oa";


    /**
     * Other Initiators
     */
    private static Logger logger = LogManager.getLogger(ErrorRegistrationCasesTest.class);
    String testName;
    private AdminLoginPage adminLoginPage;
    private ScheduleNewUserPage scheduleUserPage;

    @BeforeClass
    public void initClass(){
        adminLoginPage = new AdminLoginPage();
        adminLoginPage.loginAs(TestProperty.ADMIN_USERNAME,TestProperty.ADMIN_PASSWORD);
        scheduleUserPage = new ScheduleNewUserPage();

        logger.info("Successfully executed initClass() method of " + getClass());
    }
    @BeforeMethod
    public void initMethod(Method method) throws Exception{
        testName = method.getName();

        logger.info("Successfully executed initMethod() of " + getClass());
    }
    @AfterClass
    public void classTearDown(){
        UtilityMethods.writeOutputResult();

        adminLoginPage = null;
        scheduleUserPage = null;

        logger.info("Successfully executed the classTearDown() method of " + getClass());
    }
    //===
    //=TESTS
    //===
    @Test(description = "Admin error when attempting to register to event invalid user, TCID :- C2925")
    public void invalidUserProperError(){
        scheduleUserPage.goToScheduleUser();
        scheduleUserPage.registerUserForEvent(TestProperty.CLIENTNAME,TestProperty.EVENTNAME,invalidUsername);
        assertTrue(scheduleUserPage.invalidUserError());
    }
    @Test(description = "Admin error when attempting to register user under incorrect client, TCID :- C2924")
    public void userNotWithClientError(){
        scheduleUserPage.goToScheduleUser();
        scheduleUserPage.registerUserForEvent(TestProperty.CLIENTNAME,TestProperty.EVENTNAME,differentClientsUser);
        assertTrue(scheduleUserPage.nonMatchingClientError());
    }
}
