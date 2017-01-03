package com.lh.pages.authenticated;

import com.lh.testConfig.TestProperty;
import com.lh.utils.DateUtil;
import com.lh.utils.SeleniumUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.util.ArrayList;
import java.util.List;

import static com.lh.helper.DriverFactory.driver;


/**
 * <h2>This is the Self Registration page Class for the Unauthenticated user</h2>
 * <p>
 * 
 * @author amita.arya
 * @version 1.0
 * @since 2015-03-02
 */
public class BiometricScreeningPage{

	boolean T = true;
	boolean F = false;

	/** Logger for the Login class */
	private static Logger logger 								= LogManager.getLogger(BiometricScreeningPage.class);
	private static String dayEventStatus_XpathPart1 			= "//*[@id='dayPicker']/div[2]/ul/li[";
	private static String dayEventStatus_XpathPart2 			= "]/div[2]/p[2]";
	private static String dayEventStat							= "//*[@id='dayPicker']/div[2]";
	private static String date_XpathPart1						= "//*[@id='dayPicker']/div[2]/ul/li["; 
	private static String date_XpathPart2						= "]/div[2]/p[1]";
	private static String time_Xpath							= "//*[@id='timePicker']/div[1]/div[2]/ul/li[";
	
	By scheduleNowBtn											= By.cssSelector("#scheduleButton2_0");
	By chooseALocationDropdown									 = By.id("locationDropdownList");
	By cancelBtn												= By.id("cancelButton");
	By submitBtn												= By.id("submitButton");
	By monthTxt													= By.xpath("//*[@id='dayPicker']/div[1]/div[2]");
	By availableDateTxt											= By.xpath(".//*[@id='dayPicker']/div[2]/ul/li[6]/div[2]/p[2]");
	By TimeAMOfDayBtn											= By.xpath("//*[@id='timePicker']/div[1]/div[1]");
	By TimePMOfDayBtn											= By.xpath("//*[@id='timePicker']/div[2]/div[1]");
	By confirmBtn												= By.id("confirmButton");
	By okayBtn													= By.id("okayButton");
	By okayBtnEventCancelPrompt									= By.cssSelector(".button");
	By appointmentConfirmationDateTxt 							= By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ScreeningLandingControl_divScreeningAppointment']/p[1]");
	By appointmentConfirmationTimeTxt							= By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ScreeningLandingControl_divScreeningAppointment']/p[2]");
	By addToCalendarLink 										= By.id("ctl00_ContentPlaceHolder1_ScreeningLandingControl_addToCalendarLink");
	By rescheduleLink											= By.id("lnkReschedule");
	By cancelLink												= By.cssSelector("#ctl00_ContentPlaceHolder1_ScreeningLandingControl_lnkCancel");//modalScreeningCancellationYesButton
	By timeOfDay												= By.xpath("//*[@id='timePicker']/div[1]/div[2]/ul/li[@class='available']");
	By timeOfDayAlt												= By.xpath("//*[@id='timePicker']/div[1]/div[2]/ul/li[@class='available altRow']");
	By yesAppointmentCancelModalBtn								= By.id("modalScreeningCancellationYesButton");
	By noAppointmentCancelModalBtn								= By.xpath("//*[@id='modalScreeningCancellation']/button[1]");
	By subTitleScheduleYourAppointmentLbl						= By.xpath("//*[@id='schedulerModalContent']/p");
	By okeyAppointmentCancelledBtn								= By.xpath("//*[@id='modalScreeningCanceled']/div");
	By subLocationDropdown 										= By.cssSelector("#sublocationDropdownList");
	By confirmTimeInReview 										= By.cssSelector("#timeBlockText");
	By confirmLocationInReview 									= By.cssSelector("#locationText");
	By confirmSubLocationInReview								= By.cssSelector("#sublocationText");
	By availableDateBox                                         = By.cssSelector(".available");
	By downloadNowBtn                                           = By.cssSelector("#ctl00_ContentPlaceHolder1_ScreeningLandingControl_OffsiteFormRepeater_ctl00_lnkDownload");

    By logoutLink                                               = By.linkText("Logout");


	/**
	 * Constructor
	 * 
	 *
	 */
	public BiometricScreeningPage() {
	    super();
		Reporter.log("...initializing the BiometricScreeningPage Object...");
		if (!(driver.getCurrentUrl().contains(TestProperty.BIOMETRICSCREENING_URI))) {
			Reporter.log("Had to navigate to the BiometricScreening Page.");
			//logger.error("This is not the Biometric Screening page");
			//throw new IllegalStateException("This is not the BiometricScreeningPage page");
		}
		Reporter.log("Success - *INITIALIZED* BiometricScreeningPage.");
	}
	
	/**
	 * Clicks on the "Schedule Now" button on the Biometric Screening page.
	 */
	public void clickScheduleNow() {

		driver.findElement(scheduleNowBtn).click();

		Reporter.log("Clicked on Schedule Now button to Schedule the event for the user.");

	}
	
	/**
	 * Clicks on the "Schedule Now" button on the Biometric Screening page.
	 */
	public boolean verifyScheduleNowBtnDisplayed() {

		if (driver.findElement(scheduleNowBtn).isDisplayed()){
			
			Reporter.log("The Schedule Now button is displayed for scheduling the event.");	
			
			return true;
			
		}
		
		Reporter.log("The Schedule Now button is NOT displayed for scheduling the event..");

		return false;

	}

	/**
	 * Selects a location for scheduling the event for the user.
	 * 
	 * @param location
	 *            location for the event
	 */
	public void setLocationOfEvent(String location) {

		Select select = new Select(driver.findElement(chooseALocationDropdown));
		select.selectByVisibleText(location);

		//driver.findElement(chooseALocationDropdown).sendKeys(location);
		//driver.findElement(subTitleScheduleYourAppointmentLbl).click();
		Reporter.log("Selected the location - " + location + " from the list available for scheduling the event");
	}

	public void setEventDate(String desiredDate) {
		Reporter.log("Desired: " + desiredDate);
		if(driver.findElement(By.cssSelector(".date")).getAttribute("value").contains(desiredDate)){
			driver.findElement(availableDateBox).click();
		}else {
			logger.error("Client App Biometric Screening event registration for date selection failing.");
		}
	}

	public void setEventDate() {
		driver.findElement(availableDateTxt).click();
	}


	public void setTimeOfEvent(String timeOfEvent) {

		Reporter.log("The expected time to be selected is: " + timeOfEvent);

		if (timeOfEvent != null) {

			List<WebElement> timesAvailable = driver.findElements(timeOfDay);
			System.out.println(timesAvailable.size());

			for (int count = 0; count < timesAvailable.size(); count++) {

				WebElement element = timesAvailable.get(count);

				Reporter.log("The time text for the element is: " + element.getText().trim());

				if (timeOfEvent.trim().equalsIgnoreCase(element.getText().trim())) {

					element.click();

					Reporter.log("Selected the expected time for scheduling the event - " + element.getText().trim());

				}

			}
			List<WebElement> timesAvailableAlt = driver.findElements(timeOfDayAlt);
			System.out.println(timesAvailableAlt.size());

			for (int countAlt = 0; countAlt < timesAvailableAlt.size(); countAlt++) {

				WebElement elementAlt = timesAvailableAlt.get(countAlt);

				Reporter.log("The time text for the element is: " + elementAlt.getText().trim());

				if (timeOfEvent.trim().equalsIgnoreCase(elementAlt.getText().trim())) {

					elementAlt.click();

					Reporter.log("Selected the expected time for scheduling the event - " + elementAlt.getText().trim());

				}

			}

		}

	}
	
	public void clickSubmitEventAppointment(){
		
		driver.findElement(submitBtn).click();
		
		Reporter.log("Clicked on the Submit button for the appointment to be scheduled.");
		
	}

	
	public void clickConfirmEventAppointment(){
		
		driver.findElement(confirmBtn).click();
		
		
		try {
			
			Thread.sleep(TestProperty.THREAD_SLEEP);
			
		} catch (InterruptedException e) {
			
			e.printStackTrace();
			
		}
		
		Reporter.log("Confirmed the appointment for the user...");
		
	}
	
	public void clickOkayEventAppointment(){
		
		driver.findElement(okayBtn).click();
		
		Reporter.log("Closed the event appointment schedulerTwo modal after confirming the appointment.");
		
	}
	
	public void clickRescheduleLink(){
		
		
		driver.findElement(rescheduleLink).click();
		
		Reporter.log("Clicked on the reschedule link to reschedule the appointment");
	}
	
	
	public void clickCancelLink(){

		//SeleniumUtil.waitForPageLoad(driver);

		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(cancelLink)).click().build().perform();

		//driver.findElement(cancelLink).click();

		Reporter.log("Clicked on the cancel link to cancel the appointment");
	}
	
	public void clickYesAppointmentCancellationConfirmation(){
		
		driver.findElement(yesAppointmentCancelModalBtn).click();
		//driver.findElement(okeyAppointmentCancelledBtn).click();
		Reporter.log("Clicked on the yes button in the Appointment Cancellation Modal link to cancel the appointment");
		
	}

	public void scheduleBiometricEvent (String locationOfEvent, String eventTime) {
		
		Reporter.log("Selecting the date for the event.");
		
		clickScheduleNow();
		setLocationOfEvent(locationOfEvent);
		setEventDate();
		setTimeOfEvent(eventTime);
		clickSubmitEventAppointment();
		clickConfirmEventAppointment();
		clickOkayEventAppointment();
		
		Reporter.log("Selected the date for the event.");
		
	}
	
	public boolean verifyAppointmentDateSchedule(){
		
		if(driver.findElement(rescheduleLink).isDisplayed()){
			
			String appointmentScheduleText = driver.findElement(appointmentConfirmationDateTxt).getText().trim();
			
			String expectedAppointmentScheduleText = DateUtil.getFormattedCurrentDateFull();
			
			Reporter.log("The appoint is scheduled for - " + appointmentScheduleText);
			
			if(expectedAppointmentScheduleText.equalsIgnoreCase(appointmentScheduleText)){
					
				Reporter.log("The appointment is scheduled successfully.");
				
				return true;
			}
		}
		
		logger.error("The appointment is NOT scheduled.");
		return false;
		
	}
	
	public boolean verifyAppointmentTimeSchedule(String eventTime)
			throws InterruptedException {

		Thread.sleep(TestProperty.THREAD_SLEEP);
		if (driver.findElement(rescheduleLink).isDisplayed()) {

			String appointmentScheduleTimeText = driver.findElement(appointmentConfirmationTimeTxt)
					.getText().trim();

			Reporter.log("The appoint is scheduled for time - "
					+ appointmentScheduleTimeText);

			if (eventTime.equalsIgnoreCase(appointmentScheduleTimeText)) {

				Reporter.log("The appointment is scheduled successfully for - "
						+ eventTime);

				return true;
			}
		}

		logger.error("The appointment is NOT scheduled for the expected time.");

		return false;

	}
	
	public void rescheduleBiometricEvent(String locationOfEvent, String eventTime){
		
		Reporter.log("Selecting the date for the event.");
		
		clickRescheduleLink();
		setLocationOfEvent(locationOfEvent);
		setEventDate();
		setTimeOfEvent(eventTime);
		clickSubmitEventAppointment();
		clickConfirmEventAppointment();
		clickOkayEventAppointment();
		
		Reporter.log("Selected the date for the event.");
		
	}

	public void setFirstAvailableDate() {
		driver.findElement(availableDateBox).click();
	}

	public void setSubLocationOfEvent(String eventSubLocation) {
		Select select = new Select(driver.findElement(subLocationDropdown));
		select.selectByVisibleText(eventSubLocation);
	}

	public boolean verifySubLocationValue(String desiredSubLocation){
		driver.findElement(subLocationDropdown).getText().contains(desiredSubLocation);
		return T;
	}

	public boolean confirmEventTime(String desiredRegistrationTime) {
		Reporter.log("Desired: " + desiredRegistrationTime);
		Reporter.log("Selected: " + driver.findElement(confirmTimeInReview).getText());

		if(driver.findElement(confirmTimeInReview).getText().equalsIgnoreCase(desiredRegistrationTime)){
			return T;
		}
		else{
			return F;
		}

	}

	public boolean verifyAddToCalendarLinkPresent() {
		if (driver.findElement(addToCalendarLink).isDisplayed()) {
			return T;
		} else {
			return F;
		}
	}

	public boolean confirmLocation(String desiredLocation) {
		Reporter.log("Desired: " + desiredLocation);
		Reporter.log("Selected: " + driver.findElement(confirmLocationInReview).getText());

		if(driver.findElement(confirmLocationInReview).getText().equalsIgnoreCase(desiredLocation)){
			return T;
		}
		else{
			return F;
		}
	}

	public boolean confirmSubLocation(String desiredSubLocation) {
		Reporter.log("Desired: " + desiredSubLocation);
		Reporter.log("Selected: " + driver.findElement(confirmSubLocationInReview).getText());

		if(driver.findElement(confirmSubLocationInReview).getText().equalsIgnoreCase(desiredSubLocation)){
			return T;
		}
		else{
			return F;
		}
	}

	public boolean confirmAppointmentCancelledBox() {
		if(driver.findElement(By.cssSelector("#ui-id-4")).getText().equalsIgnoreCase("Appointment Cancelled")){
			return T;
		}
		else{
			return F;
		}

	}


	public boolean confirmScheduleNowButtonDisplayed() {
		driver.navigate().refresh();
		SeleniumUtil.waitForPageLoad(driver);
		new WebDriverWait(driver, TestProperty.WAITING_TIME).until(ExpectedConditions.presenceOfElementLocated(scheduleNowBtn));
		return T;
	}

	public void closeCancellationBox() {
		driver.findElement(By.xpath(".//*[@id='ctl00_bodyMaster']/div[11]/div[1]/button")).click();

		//new WebDriverWait(driver,TestProperty.WAITING_TIME).until(ExpectedConditions.elementToBeClickable(okayBtnEventCancelPrompt));
		//driver.findElement(okayBtnEventCancelPrompt).click();
	}

	/**
	 * This method is to check that the first week shown in the Calendar Carousal / Date Picker
	 * when the 'Schedule Your Appointment' box is opened contains an 'Available' date.
	 *
	 * @return - T if
     */
	public boolean verifyCalendarShowsWeekWithAvailable() {
		if(driver.findElement(availableDateBox).isDisplayed()){
			return T;
		}
		else{
			return F;
		}
	}

	public void closeScheduleAppointmentBox() {
		driver.findElement(By.xpath(".//*[@id='ctl00_bodyMaster']/div[10]/div[1]/button"));
	}

	public void selectLastAvailable(){
	    List <WebElement> options = driver.findElements(By.className("DayContent"));
        for (WebElement option : options){
            options.get(options.size()-1);
            option.click();
        }

    }

    public boolean verifyDownloadNowPDF() {
        String oldTab = driver.getWindowHandle();
        driver.findElement(downloadNowBtn).click();
        ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
        newTab.remove(oldTab);
        //Switch to New Tab (the PDF) and verify pdf contained in URL
        driver.switchTo().window(newTab.get(0));
        //New Tab Assertion
        driver.getCurrentUrl().contains("pdf");
        return T;
    }


    public void logout() {
        driver.findElement(logoutLink).click();
    }

    public void exitFromSchedulerPopup() {
    	Actions action = new Actions(driver);
		action.sendKeys(Keys.ESCAPE);
    }
}