package com.osi.loganalyzer.model;

import java.io.Serializable;

public class Report implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String Tests_run;
	private String Failures;
	
	private String Errors;
	private String Skipped;
	private String Time_elapsed;
	public Report() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getTests_run() {
		return Tests_run;
	}
	public void setTests_run(String tests_run) {
		Tests_run = tests_run;
	}
	public String getFailures() {
		return Failures;
	}
	public void setFailures(String failures) {
		Failures = failures;
	}
	public String getErrors() {
		return Errors;
	}
	public void setErrors(String errors) {
		Errors = errors;
	}
	public String getSkipped() {
		return Skipped;
	}
	public void setSkipped(String skipped) {
		Skipped = skipped;
	}
	public String getTime_elapsed() {
		return Time_elapsed;
	}
	public void setTime_elapsed(String time_elapsed) {
		Time_elapsed = time_elapsed;
	}
	public Report(String tests_run, String failures, String errors, String skipped, String time_elapsed) {
		super();
		Tests_run = tests_run;
		Failures = failures;
		Errors = errors;
		Skipped = skipped;
		Time_elapsed = time_elapsed;
	}
	@Override
	public String toString() {
		return "Report [Tests_run=" + Tests_run + ", Failures=" + Failures + ", Errors=" + Errors + ", Skipped="
				+ Skipped + ", Time_elapsed=" + Time_elapsed + "]";
	}
}
