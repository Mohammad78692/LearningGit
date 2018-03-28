package com.osi.loganalyzer.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;



public class TimeZoneConvertion {
  
	public static String formate1;
	public String getCurrentTime(){
		Date today = new Date();
		 SimpleDateFormat formatter = new SimpleDateFormat("dd MMM HH:mm");
	     formatter.setTimeZone(TimeZone.getTimeZone("America/New_York"));
	     formate1 = formatter.format(today);
	   // System.out.println("final : "+formatter.format(today));
		return formate1;
	}
}
