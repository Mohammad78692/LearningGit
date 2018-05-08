package com.osi.log.model;

import java.io.Serializable;

public class LogRead implements Serializable {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	private String location;
	private String debug;
	private String search;
	private String startTime;
	private String endTime;
	private String logType;
	private String cookie;
	
	private String timeFormatOfRegex;
	private String key;

	public LogRead() {
		super();
		// TODO Auto-generated constructor stub
	}


	public LogRead(String location, String debug, String search, String startTime, String endTime, String logType,
			String cookie, String timeFormatOfRegex, String key) {
		super();
		this.location = location;
		this.debug = debug;
		this.search = search;
		this.startTime = startTime;
		this.endTime = endTime;
		this.logType = logType;
		this.cookie = cookie;
		this.timeFormatOfRegex = timeFormatOfRegex;
		this.key = key;
	}

	public String getTimeFormatOfRegex() {
		return timeFormatOfRegex;
	}

	public void setTimeFormatOfRegex(String timeFormatOfRegex) {
		this.timeFormatOfRegex = timeFormatOfRegex;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	public String getCookie() {
		return cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getSearch() {
		return search;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDebug() {
		return debug;
	}

	public void setDebug(String debug) {
		this.debug = debug;
	}

	public String getkey() {
		return key;
	}

	public void setkey(String key) {
		this.key = key;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + ((cookie == null) ? 0 : cookie.hashCode());
		result = prime * result + ((debug == null) ? 0 : debug.hashCode());
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((logType == null) ? 0 : logType.hashCode());
		result = prime * result + ((search == null) ? 0 : search.hashCode());
		result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
		result = prime * result + ((timeFormatOfRegex == null) ? 0 : timeFormatOfRegex.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LogRead other = (LogRead) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (cookie == null) {
			if (other.cookie != null)
				return false;
		} else if (!cookie.equals(other.cookie))
			return false;
		if (debug == null) {
			if (other.debug != null)
				return false;
		} else if (!debug.equals(other.debug))
			return false;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (logType == null) {
			if (other.logType != null)
				return false;
		} else if (!logType.equals(other.logType))
			return false;
		if (search == null) {
			if (other.search != null)
				return false;
		} else if (!search.equals(other.search))
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		if (timeFormatOfRegex == null) {
			if (other.timeFormatOfRegex != null)
				return false;
		} else if (!timeFormatOfRegex.equals(other.timeFormatOfRegex))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "LogRead [location=" + location + ", debug=" + debug + ", search=" + search + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", logType=" + logType + ", cookie=" + cookie + ", timeFormatOfRegex="
				+ timeFormatOfRegex + ", key=" + key + "]";
	}

}
