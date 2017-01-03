package com.lh.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;



public class DateUtil {
	public static enum Mode {
	    ALPHA, ALPHANUMERIC, NUMERIC 
	}
	private static int randomInt;
	public static Date getCurrentDate() {
		Calendar calendar = new GregorianCalendar();
		return calendar.getTime();
	}

	/**
	 * Return the system date in GregorianCalendar Format
	 * @return Calendar
	 */
	private static Calendar getCurrentCalendar() {
		return new GregorianCalendar();
	}
	
	
	
	/**
	 * Returns the system time in milliseconds
	 * @return timeInMilliseconds
	 */
	public static long getTimeInMillisOFCurrentCalender(){
		long timeInMillis = getCurrentCalendar().getTimeInMillis();
		return timeInMillis;
	}

	/**
	 * Format curretn date to the "yyyy-MM-dd" format
	 * @return date in "yyyy-MM-dd" format
	 */
	public static String getFormattedCurrentDate() {
		
		SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd");
		timeFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		String time = timeFormat.format(new Date());
		
		return time;
	}
	
	public static String getCurrentDayOfMonth(){
		String currentDate = getFormattedCurrentDate();
		System.out.println(currentDate);
		
		String dayOfMonth = currentDate.substring(8, 10);
		System.out.println(dayOfMonth);
		return dayOfMonth;
		
		
	}
	
	public static String getFormattedCurrentDateFull(){
		
		SimpleDateFormat timeFormat = new SimpleDateFormat("EEEE, MMMM d, yyyy");
		timeFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		String time = timeFormat.format(new Date());

		return time;
		
	}
	
	public static String getFormattedCurrentDateTime (){
		
		SimpleDateFormat timeFormat = new SimpleDateFormat("EEEE, MMMM d, yyyy h:mm a ");
		timeFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		String time = timeFormat.format(new Date());

		return time;
		
	}
	
	public static String getCurrentDateFormatted(){
		
		SimpleDateFormat timeFormat = new SimpleDateFormat("MMMM dd, yyyy");
		timeFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		String time = timeFormat.format(new Date());
		System.out.println(time);
		return time;
		
	}

	public static void main(String args[]) {

//		getFormattedCurrentDateFull();
		getCurrentDateFormatted();
	}

	public static String calendarDate() throws Throwable{
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY");
		Calendar cal = Calendar.getInstance();
		cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -randomStringGenerator());
		return dateFormat.format(cal.getTime());
	}

	public static int randomStringGenerator() throws Throwable{
	randomInt=Integer.parseInt(generateRandomString(1,DateUtil.Mode.NUMERIC));	
	return randomInt;
	}
	
	public static String generateRandomString(int length, Mode mode) throws Exception {
		StringBuffer buffer = new StringBuffer();
		String characters = "";
		switch(mode){
		case ALPHA:
			characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
			break;
		case ALPHANUMERIC:
			characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
			break;
		case NUMERIC:
			characters = "1234567890";
		    break;
		}
		int charactersLength = characters.length();
		for (int i = 0; i < length; i++) {
			double index = Math.random() * charactersLength;
			buffer.append(characters.charAt((int) index));
		}
		return buffer.toString();
	}

	/**
	 * Description - Method which returns a string date value of 'MM/DD/YYYY' based on an
	 * input parameter of how many days from current date user desires.
	 *
	 * Author - Oleg Andreyev
	 *
	 * @param numberOfDays
	 * @return
     */
	public static String someDaysInFuture(int numberOfDays) {
		SimpleDateFormat sdf = new SimpleDateFormat("M/d/yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); // Now use today date.
		c.add(Calendar.DATE, numberOfDays); // Adding some number of days specified in parameter
		String output = sdf.format(c.getTime());
		return output;
	}
	
}
