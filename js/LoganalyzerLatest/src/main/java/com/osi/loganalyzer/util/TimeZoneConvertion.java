package com.osi.loganalyzer.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;



public class TimeZoneConvertion {
  
	public static String formate1;
	public static String formate2;
	public String getCurrentTime(){
		Date today = new Date();
		//yyyy-MM-dd HH:mm
		//dd MMM HH:mm
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	     formatter.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
	     formate1 = formatter.format(today);
	     String[] split = formate1.split(" ");
	     formate2=split[0];
		return formate1;
	}
}
