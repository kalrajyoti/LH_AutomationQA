package com.lh.test.adminPortal;

import com.lh.pages.admin.AdminLoginPage;
import com.lh.pages.admin.MemberSearchPage;
import com.lh.test.base.AuthBaseTestClass;
import com.lh.testConfig.TestProperty;
import com.lh.utils.UtilityMethods;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.*;

import java.lang.reflect.Method;

import static org.testng.Assert.assertTrue;

/**
 * Created by oleg.andreyev on 8/8/2016 for LiveHealthier-EnvolvePeopleCare
 * <p>
 * Summary -
 */
@Listeners(com.lh.helper.LHTestListener.class)
public class MemberSearchTest extends AuthBaseTestClass{

    /**
     * TEST PROPERTIES
     */
    String userID = "499383"; //For verifications
    String testClient = "CoreLogic";

    String username = "lhtestcore_oa";
    String externalID = "xlhtestcore_oa";
    String userEmail = "oleg.andreyev@envolvehealth.com";
    String preferredEmail = "oleg.andreyev@envolvehealth.com";
    String firstName = "Oleg";
    String lastName = "Andreyev";
    String ssn = "111-11-1111";

    String userIDForStatusChange = "500501";

    /**
     * Other Initiators
     */
    private static Logger logger = LogManager.getLogger(MemberSearchTest.class);
    String testName;
    AdminLoginPage adminLoginPage;
    MemberSearchPage memberSearchPage;

    @BeforeClass
    public void initClass() {
        adminLoginPage = new AdminLoginPage();
        adminLoginPage.loginAs(TestProperty.ADMIN_USERNAME, TestProperty.ADMIN_PASSWORD);
        logger.info("Successfully logged into the Admin Portal at - " + TestProperty.BASEURL);

        memberSearchPage = new MemberSearchPage();

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
        memberSearchPage = null;

        logger.info("Successfully executed the classTearDown() method of " + getClass());
    }
    //---
    //====== TESTS BELOW
    //---
    @Test(description = "Member search by Username, TCID :- C2121")
    public void searchByUsername(){
        memberSearchPage.navigateToMemberSearch();
        memberSearchPage.searchByUsername(username);

        assertTrue(memberSearchPage.verifySearchResult(userID));
    }
    @Test(description = "Member search by ExternalID, TCID :- C2124")
    public void searchByExternalID(){
        memberSearchPage.navigateToMemberSearch();
        memberSearchPage.searchByExternalID(externalID);

        assertTrue(memberSearchPage.verifySearchResult(userID));
    }
    @Test(description = "Member search by First Name")
    public void searchByFirstName(){
        memberSearchPage.navigateToMemberSearch();
        memberSearchPage.searchByFirstName(firstName);

        assertTrue(memberSearchPage.verifySearchResult(userID));
    }
    @Test(description = "Member search by Last Name, TCID :- C2122")
    public void searchByLastName(){
        memberSearchPage.navigateToMemberSearch();
        memberSearchPage.searchByLastName(lastName);

        assertTrue(memberSearchPage.verifySearchResult(userID));
    }
    @Test(description = "Member search by SSN, TCID :- C2125")
    public void searchBySSN(){
        memberSearchPage.navigateToMemberSearch();
        memberSearchPage.searchBySSN(ssn);

        assertTrue(memberSearchPage.verifySSNSearchResult());
    }
    @Test(description = "Member search by Email, TCID :- C2123")
    public void searchByUserEmail(){
        memberSearchPage.navigateToMemberSearch();
        memberSearchPage.searchByEmail(userEmail);

        assertTrue(memberSearchPage.verifySearchResult(userID));
    }
    @Test(description = "Member search by Preferred Email, TCID :- C6734")
    public void searchByPreferredEmail(){
        memberSearchPage.navigateToMemberSearch();
        memberSearchPage.searchByPreferredEmail(preferredEmail);

        assertTrue(memberSearchPage.verifySearchResult(userID));
    }
    @Test(description = "Admin able to change user status, TC=C2127")
    public void changeUserStatus(){
        memberSearchPage.navigateToChangeMemberStatus(userIDForStatusChange);
        assertTrue(memberSearchPage.verifyUserStatusChanged());
    }
    @Test(description = "Admin able to view user's incentives, TCID :- C2128")
    public void canViewUsersIncentives(){
        memberSearchPage.navigateToUserIncentivePage(userID);
        memberSearchPage.verifyOnUsersIncentiveAdminPage();
    }
    @Test(description = "Admin Edit of User's Biometric Reading, TCID :- C2129")
    public void editUsersBiometricReading(){
        memberSearchPage.navigateToViewBiometrics(userID);
        memberSearchPage.updateBiometricTestDateToday();
        assertTrue(memberSearchPage.verifyBiometricTestDateUpdated());
    }
    @Test(description = "Admin able to login as user, TCID :- C2130", priority = 12)
    public void adminAbleToLoginAsUser(){
        memberSearchPage.navigateToMemberSearch();
        memberSearchPage.searchByExternalID(externalID);
        memberSearchPage.logInFromAdmin();
        memberSearchPage.verifyOnUserPortal(testClient);
    }
    @Test(description = "Admin able to view user's incentives, TCID :- C2133")
    public void adminAbleToViewUsersIncentives(){
        memberSearchPage.navigateToViewIncentiveStatus(userID);
        assertTrue(memberSearchPage.verifyOnIncentiveStatus());
    }


}
