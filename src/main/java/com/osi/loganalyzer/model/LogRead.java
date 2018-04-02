package com.osi.loganalyzer.model;

import java.io.Serializable;

public class LogRead implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private String location;
private String debug;
public LogRead() {
	super();
	// TODO Auto-generated constructor stub
}
public LogRead(String location, String debug, String search) {
	super();
	this.location = location;
	this.debug = debug;
	this.search = search;
}
private String search;

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
@Override
public String toString() {
	return "LogRead [location=" + location + ", debug=" + debug + "]";
}
}
