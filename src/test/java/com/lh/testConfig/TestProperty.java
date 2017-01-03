package com.lh.testConfig;

import com.lh.utils.PropertyFileReader;
import com.lh.utils.UtilityMethods;
import org.apache.logging.log4j.Logger;import org.apache.logging.log4j.LogManager;

import java.util.Properties;

/**
 * <h2>Test Properties are defined in this class!</h2>
 * This class reads the configuration properties
 * of the automation run.
 * <p>
 * <b>Note:</b> All the test properties should be defined in the 
 * PROPERTY files in the project
 * 
 * @author amita.arya,akash vansil
 * @version 2.0
 * @since  2016-05-17
 */
public class TestProperty {
	/** propObj is an instance of PropertyFileReader class */
	final static PropertyFileReader prop= new PropertyFileReader();
	/** Instance of the properties */
	final static Properties propObj = prop.returnProperties("automation");
	/** BASEURL contains the URL which is used for test Automation */
	public static final String ADMIN_URL = propObj.getProperty("ADMIN_URL");
	public static final String BASEURL = propObj.getProperty("BASEURL");
	public static final String PORTALURL = propObj.getProperty("PORTALURL");
	public static final String BASE_URL = propObj.getProperty("BASE_URL");
	public static final String STEPSURL = propObj.getProperty("STEPSURL");
	public static final String WEIGHTURL = propObj.getProperty("WEIGHTURL");
	public static final String PHYSICALURL = propObj.getProperty("PHYSICALURL");
	public static final String NUTRIURL = propObj.getProperty("NUTRIURL");
	public static final String GENERALURL = propObj.getProperty("GENERALURL");
	public static final String HEARTYURL = propObj.getProperty("HEARTYURL");
	public static final String FITBITURL = propObj.getProperty("FITBITURL");
	/** Admin Portal Related */
	public static final String ADMIN_TESTUSER_EVENTS = propObj.getProperty("ADMIN_TESTUSER_EVENTS");
	public static final String BASEADMINURL = propObj.getProperty("BASEADMINURL");
	public static final String ADMIN_USERNAME = propObj.getProperty("ADMIN_USERNAME");
	public static final String ADMIN_PASSWORD = propObj.getProperty("ADMIN_PASSWORD");
	public static final String NEWEVENT_URL = propObj.getProperty("NEWEVENT_URI");
	public static final String USERREGISTRATION_URI = propObj.getProperty("SCHEDULENEWUSER_URI");
	public static final String EVENTLIST_URI = propObj.getProperty("EVENTLIST_URI");
	public static final String EDITCLIENTACCT_URI = propObj.getProperty("EDITCLIENTACCT_URI");
	public static final String ADDOFFICE_URI = propObj.getProperty("ADDOFFICE_URI");
	public static final String CLIENTSOFFICELIST_URI = propObj.getProperty("CLIENTSOFFICELIST_URI");
	public static final String MEMBERSEARCH_URI = propObj.getProperty("MEMBERSEARCH_URI");
	public static final String CHANGEMEMBERSTATUS_URI = propObj.getProperty("CHANGEMEMBERSTATUS_URI");
	public static final String USERINCENTIVE_URI = propObj.getProperty("USERINCENTIVE_URI");
	public static final String VIEWBIOMETRICS_URI = propObj.getProperty("VIEWBIOMETRICS_URI");
	public static final String INCENTIVESTATUS_URI = propObj.getProperty("INCENTIVESTATUS_URI");

	public static final String COACH_EXPERT_TITLE			= propObj.getProperty("COACH_EXPERT_TITLE");
	
	public static final String BROWSER = propObj.getProperty("BROWSER");
	
	/** ZIPFILE_REPORT_NAME contains the name of the zipped report */
	public static final String ZIPFILE_REPORT_NAME =  propObj.getProperty("ZIPFILE_REPORT_NAME");
	/** CONFIG_AUTOMATION contains relative path of the configuration file 	 */
	public static final String CONFIG_AUTOMATION = "/test/resources/automation.properties";
	/** WAITING_TIME contains the webDriverWait time */
	
	public static final int WAITING_TIME =  Integer.parseInt(propObj.getProperty("WAITING_TIME"));
	/** ELEMENT_WAIT_TIME is the time in milli seconds the driver waits for to proceed in HelperWebDriverEventListener */
	public static final int ELEMENT_WAIT_TIME =  Integer.parseInt(propObj.getProperty("ELEMENT_WAITTIME"));
	/** ELEMENT_WAIT_TIME is the time in seconds the driver waits for to proceed in HelperWebDriverEventListener */
	public static final int ELEMENT_POLL_TIME =  Integer.parseInt(propObj.getProperty("ELEMENT_POLLTIME"));
	
	/** CLIENT_PORTAL_TITLE contains the title of the client portal under test */
	public static final String CLIENT_PORTAL_TITLE 			= propObj.getProperty("CLIENT_PORTAL_TITLE");
	/** ADMIN_LOGIN_TITLE  contains the title of the admin portal under test */
	public static final String ADMIN_LOGIN_TITLE 			= propObj.getProperty("ADMIN_LOGIN_TITLE");
	public static final String ADMIN_HOME_TITLE 			= propObj.getProperty("ADMIN_HOME_TITLE");
	public static final String ADMIN_VIEW_RESULT 			= propObj.getProperty("ADMIN_VIEW_RESULT");
	public static final String ADMIN_ADD_ONTRACK            = propObj.getProperty("ADMIN_ADD_ONTRACK");
	
	/** EMAIL_REPORT_TO contains the email addresses to which the email is sent on test completion */
	public static final String EMAIL_REPORT_TO 				= propObj.getProperty("EMAIL_REPORT_TO");
	/** THREAD_SLEEP contains the value in long for the Thread.sleep(THREAD_SLEEP) stmt */
	private static String THREAD_SLEEP_STRING = propObj.getProperty("THREAD_SLEEP_STRING").trim();
	/** RETRY_COUNT indicates the number of times a failed test case re executed  */
	public static final int RETRY_COUNT = Integer.parseInt(propObj.getProperty("RETRY_COUNT"));

	public static long THREAD_SLEEP = Long.valueOf(THREAD_SLEEP_STRING).longValue();
	public static final String TESTDATA_WORKBOOK =  propObj.getProperty("XLS_DATA").trim();
	public static final String TAKE_SCREENSHOTS = propObj.getProperty("TAKE_SCREENSHOTS").trim().toLowerCase();
	
	public static final String CLIENTURL = UtilityMethods.extractPartialClientURL(propObj.getProperty("BASEURL"));


	/** Logger for the HealthAssessment class */
	private static Logger logger 							= LogManager.getLogger(TestProperty.class);
	
	/**
	 * Overriding the default constructor
	 */
	protected TestProperty (){
		logger.debug("Creating instance of the TestProperty class");
	}

	/**
 	*  DEMO Env
 	*/
	public static final String DEMO_CLIENT = propObj.getProperty("DEMO_CLIENT");
	public static final String DEMO_URL = propObj.getProperty("DEMO_URL");
	public static final String DEMO_USERNAME = propObj.getProperty("DEMO_USERNAME");
	public static final String DEMO_PASSWORD = propObj.getProperty("DEMO_PASSWORD");
	public static final String DEMO_wellnessBankURI = propObj.getProperty("DEMO_wellnessBankURI");
	public static final String DEMO_onTrackURI = propObj.getProperty("DEMO_onTrackURI");
	public static final String DEMO_onTrackTeamURI = propObj.getProperty("DEMO_onTrackTeamURI");
	public static final String DEMO_onCallURI = propObj.getProperty("DEMO_onCallURI");
	public static final String DEMO_onCallOnlineURI = propObj.getProperty("DEMO_onCallOnlineURI");
	public static final String DEMO_onCallOnSiteURI = propObj.getProperty("DEMO_onCallOnSiteURI");
	public static final String DEMO_onCallByPhoneURI = propObj.getProperty("DEMO_onCallByPhoneURI");
	public static final String DEMO_yourHAURI = propObj.getProperty("DEMO_yourHAURI");
	public static final String DEMO_bioScreeningURI = propObj.getProperty("DEMO_bioScreeningURI");
	public static final String DEMO_healthManagerURI = propObj.getProperty("DEMO_healthManagerURI");
	public static final String DEMO_appManagerURI = propObj.getProperty("DEMO_appManagerURI");
	public static final String DEMO_lifestyleManagerURI = propObj.getProperty("DEMO_lifestyleManagerURI");
	public static final String DEMO_goMobileURI = propObj.getProperty("DEMO_goMobileURI");
	public static final String DEMO_contactUsURI = propObj.getProperty("DEMO_contactUsURI");
	public static final String DEMO_onTargetURI = propObj.getProperty("DEMO_onTargetURI");
	public static final String DEMO_onTargetYourTargetURI = propObj.getProperty("DEMO_onTargetYourTargetURI");
	public static final String DEMO_onTargetAchievementsURI = propObj.getProperty("DEMO_onTargetAchievementsURI");
	public static final String DEMO_healthArticlesURI = propObj.getProperty("DEMO_healthArticlesURI");
	public static final String DEMO_financialWellnessURI = propObj.getProperty("DEMO_financialWellnessURI");
	public static final String DEMO_wellnessResourcesURI = propObj.getProperty("DEMO_wellnessResourcesURI");
	public static final String DEMO_communityURI = propObj.getProperty("DEMO_communityURI");
	public static final String DEMO_wellnessChampionsURI = propObj.getProperty("DEMO_wellnessChampionsURI");
	public static final String DEMO_webinarsURI = propObj.getProperty("DEMO_webinarsURI");

	/**
	 * Client Portal Navigation
	 */
	public static final String BIOMETRICSCREENING_URI = propObj.getProperty("BIOMETRICSCREENING_URI");


	/**
	 * Event Creation Related
     */
	public static final String EVENTNAME = propObj.getProperty("EVENTNAME");
	public static final String EVENTLOCATION = propObj.getProperty("EVENTLOCATION");
	public static final String EVENTSUBLOC = propObj.getProperty("EVENTSUBLOC");
	public static final String CLIENTNAME = propObj.getProperty("CLIENTNAME");
	public static final String CLIENTCAMPAIGN = propObj.getProperty("CLIENTCAMPAIGN");
	public static final String STARTTIME = propObj.getProperty("STARTTIME");
	public static final String TIMEZONE = propObj.getProperty("TIMEZONE");
	public static final String REGION = propObj.getProperty("REGION");
	public static final String CONTACTINFO = propObj.getProperty("CONTACTINFO");
	public static final String APPTLENGTH = propObj.getProperty("APPTLENGTH");
	public static final String APPTROWS = propObj.getProperty("APPTROWS");
	public static final String APPTCOLUMNS = propObj.getProperty("APPTCOLUMNS");
	public static final String USERTOREGISTER = propObj.getProperty("USERTOREGISTER");

	/**
	 * Admin - Greeters
     */
	public static final String GREETERSEARCH_URI = propObj.getProperty("GREETERSEARCH_URI");
	public static final String GREETER_USERNAME	= propObj.getProperty("GREETER_USERNAME");
	public static final String GREETER_PASSWORD	= propObj.getProperty("GREETER_PASSWORD");
	public static final String USER_ID = propObj.getProperty("USER_ID");
	public static final String USER_FIRSTNAME = propObj.getProperty("USER_FIRSTNAME");
	public static final String USER_LASTNAME = propObj.getProperty("USER_LASTNAME");
	public static final String USER_DOB = propObj.getProperty("USER_DOB");

	/**
	 * Event Related for Scheduler V2 GREETER
     */
	public static final String USER_TO_SCHEDULE_GREETER = propObj.getProperty("USER_TO_SCHEDULE_GREETER");
	public static final String SCHEDULEDAPPTTIME = propObj.getProperty("SCHEDULEDTIME_GREETER");
	public static final String CLIENTACCT_GREETER = propObj.getProperty("CLIENTACCT_GREETER");
	public static final String CAMPAIGN_GREETER = propObj.getProperty("CAMPAIGN_GREETER");
	public static final String STARTTIME_GREETER = propObj.getProperty("STARTTIME_GREETER");
	public static final String TIMEZONE_GREETER = propObj.getProperty("TIMEZONE_GREETER");
	public static final String EVENTNAME_GREETER = propObj.getProperty("EVENTNAME_GREETER");
	public static final String EVENTLOC_GREETER = propObj.getProperty("EVENTLOC_GREETER");
	public static final String EVENTSUBLOC_GREETER = propObj.getProperty("EVENTSUBLOC_GREETER");
	public static final String REGION_GREETER = propObj.getProperty("REGION_GREETER");
	public static final String CONTACTINFO_GREETER = propObj.getProperty("CONTACTINFO_GREETER");
	public static final String APPTLENGTH_GREETER = propObj.getProperty("APPTLENGTH_GREETER");
	public static final String APPTROWS_GREETER = propObj.getProperty("APPTROWS_GREETER");
	public static final String APPTCOLUMNS_GREETER = propObj.getProperty("APPTCOLUMNS_GREETER");

	/**
	 * Client Portal
     */
	public static final String CLIENT_URL = propObj.getProperty("CLIENT_URL");
	public static final String CLIENT_USERNAME = propObj.getProperty("CLIENT_USERNAME");
	public static final String CLIENT_PASSWORD = propObj.getProperty("CLIENT_PASSWORD");
	
	
	/**
	 * TestRail
     */
	public static final String TEST_RUN_ID = propObj.getProperty("TEST_RUN_ID");
	public static final String TESTRAIL_USERNAME = propObj.getProperty("RAILS_USERNAME");
	public static final String TESTRAIL_PASSWORD = propObj.getProperty("RAILS_PASSWORD");
	
	
}
