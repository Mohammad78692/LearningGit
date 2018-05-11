package com.osi.loganalyzer.model;

import java.io.Serializable;

public class Configuration implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String hostname;
	private String username;
	private String password;
	private String location;
	
	private String timeZoneOfLinux;
	private String formatOfTime;
	
	private String formatOfInfo;
	private String formatOfDebug;
	private String formatOfWarn;
	private String formatOfError;
	private String formatOfFatal;
	
	private String yearAndMonth;

	public Configuration() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getTimeZoneOfLinux() {
		return timeZoneOfLinux;
	}

	public void setTimeZoneOfLinux(String timeZoneOfLinux) {
		this.timeZoneOfLinux = timeZoneOfLinux;
	}

	public String getFormatOfTime() {
		return formatOfTime;
	}

	public void setFormatOfTime(String formatOfTime) {
		this.formatOfTime = formatOfTime;
	}

	public String getFormatOfInfo() {
		return formatOfInfo;
	}

	public void setFormatOfInfo(String formatOfInfo) {
		this.formatOfInfo = formatOfInfo;
	}

	public String getFormatOfDebug() {
		return formatOfDebug;
	}

	public void setFormatOfDebug(String formatOfDebug) {
		this.formatOfDebug = formatOfDebug;
	}

	public String getFormatOfWarn() {
		return formatOfWarn;
	}

	public void setFormatOfWarn(String formatOfWarn) {
		this.formatOfWarn = formatOfWarn;
	}

	public String getFormatOfError() {
		return formatOfError;
	}

	public void setFormatOfError(String formatOfError) {
		this.formatOfError = formatOfError;
	}

	public String getFormatOfFatal() {
		return formatOfFatal;
	}

	public void setFormatOfFatal(String formatOfFatal) {
		this.formatOfFatal = formatOfFatal;
	}

	public String getYearAndMonth() {
		return yearAndMonth;
	}

	public void setYearAndMonth(String yearAndMonth) {
		this.yearAndMonth = yearAndMonth;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Configuration [hostname=" + hostname + ", username=" + username + ", password=" + password
				+ ", location=" + location + ", timeZoneOfLinux=" + timeZoneOfLinux + ", formatOfTime=" + formatOfTime
				+ ", formatOfInfo=" + formatOfInfo + ", formatOfDebug=" + formatOfDebug + ", formatOfWarn="
				+ formatOfWarn + ", formatOfError=" + formatOfError + ", formatOfFatal=" + formatOfFatal
				+ ", yearAndMonth=" + yearAndMonth + "]";
	}

	public Configuration(String hostname, String username, String password, String location, String timeZoneOfLinux,
			String formatOfTime, String formatOfInfo, String formatOfDebug, String formatOfWarn, String formatOfError,
			String formatOfFatal, String yearAndMonth) {
		super();
		this.hostname = hostname;
		this.username = username;
		this.password = password;
		this.location = location;
		this.timeZoneOfLinux = timeZoneOfLinux;
		this.formatOfTime = formatOfTime;
		this.formatOfInfo = formatOfInfo;
		this.formatOfDebug = formatOfDebug;
		this.formatOfWarn = formatOfWarn;
		this.formatOfError = formatOfError;
		this.formatOfFatal = formatOfFatal;
		this.yearAndMonth = yearAndMonth;
	}
	
}
