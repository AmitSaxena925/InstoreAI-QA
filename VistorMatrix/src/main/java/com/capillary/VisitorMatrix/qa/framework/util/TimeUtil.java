package com.capillary.VisitorMatrix.qa.framework.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

public class TimeUtil {
	static Calendar rightnow = Calendar.getInstance();

	public static Time getTime() {
		int hour = rightnow.get(Calendar.HOUR_OF_DAY);
		int minute = rightnow.get(Calendar.MINUTE);
		Time t = new Time();
		if (minute == 59) {
			if (t.hour == String.format("%2d", 23)) {
				t.hour = String.format("%02d", 00);
				t.minute = String.format("%02d", 01);
			} else {
				t.hour = String.format("%2d", hour + 1);
				t.minute = String.format("%02d", 01);
			}
		} else if (hour == 23 && minute == 59) {
			t.hour = String.format("%2d", 00);
			t.minute = String.format("%02d", 01);
		} else {

			t.hour = String.format("%2d", hour);
			t.minute = String.format("%02d", minute + 1);
		}
		return t;
	}

	public static String getCurrentTime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));
		String time = dtf.format(now);
		return time;
	}
	public static String getRTCCurrentTime() {
		Calendar c = Calendar.getInstance();
		Date date = new Date(c.getTimeInMillis());
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String formatted = format.format(date);
		return formatted;
	}

	public static String getFormatTime(String timestamp) throws ParseException {
		SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = dformat.parse(timestamp);
		String day = dformat.format(date);
		return day;
	}

	public static String getmodifiedTime(String type, int unit, String day) throws ParseException {
		try {
			if (type.equals("SECONDS")) {
				Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(day);
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				cal.add(Calendar.SECOND, unit);
				Date date1 = new Date();
				date1 = cal.getTime();
				SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				return dformat.format(date1);
			} else {
				return null;
			}
		} catch (Exception e) {
			Logger.getLogger("wrong date format " + e.getMessage());
			return null;
		}

	}

	public static Boolean verifyTriggeredTime(String date1, String date2) throws ParseException 
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date first = sdf.parse(date1);
		Date second = sdf.parse(date2);
		boolean before = (first.after(second));
		return before;
	}
	
	public static String convertEpoctoUTC(long epoc)
	{
		Date d = new Date(epoc);
		SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy hh:mm:ss zzz");
		return df.format(d).toString();	
	}
	
	public static String convertISOtoUTC(String iso)
	{
		System.out.println(Instant.parse(iso).toEpochMilli());
		long epoc1 = Instant.parse(iso).toEpochMilli();
		SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy hh:mm:ss zzz");
		return df.format(new Date(epoc1)).toString();
	}

}
