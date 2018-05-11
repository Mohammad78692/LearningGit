package com.osi.loganalyzer.model;


import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource("classpath:application.properties")
@Component
public class LogsSpcification implements Serializable{
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public LogsSpcification() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	 @Value("${log.INFO}")
	 private String info;
	 
	 @Value("${log.WARN}")
	 private String warn;
	 
	 @Value("${log.ERROR}")
	 private String error;
	 
	 @Value("${log.DEBUG}")
	 private String debug;

	 @Value("${log.FATAL}")
	 private String fatal;
	 
	 @Value("${log.filelocation}")
	 private String logfilelocation;
	 
	 @Value("${maven.command}")
	 private String mvnCommand;
	 
	 @Value("${log.defaultHtmlFileLocation}")
	 private String htmlLocation;
	 
	 @Value("${log.hostname}")
	 private String hostname;
	 
	 @Value("${log.username}")
	 private String username;
	 
	 @Value("${log.password}")
	 private String password;
	 
	 @Value("${log.linuxlog}")
	 private String linuxLog;
	 
	 
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
	public String getLinuxLog() {
		return linuxLog;
	}
	public void setLinuxLog(String linuxLog) {
		this.linuxLog = linuxLog;
	}
	public void setHtmlLocation(String htmlLocation) {
		this.htmlLocation = htmlLocation;
	}
	 public String getHtmlLocation() {
		return htmlLocation;
	}
	 
	 public String getMvnCommand() {
		return mvnCommand;
	}
	 public void setMvnCommand(String mvnCommand) {
		this.mvnCommand = mvnCommand;
	}
	 
	 public void setLogfilelocation(String logfilelocation) {
		this.logfilelocation = logfilelocation;
	}
	 
	 public String getLogfilelocation() {
		return logfilelocation;
	}
	 
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getWarn() {
		return warn;
	}
	public void setWarn(String warn) {
		this.warn = warn;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getDebug() {
		return debug;
	}
	public void setDebug(String debug) {
		this.debug = debug;
	}
	public String getFatal() {
		return fatal;
	}
	public void setFatal(String fatal) {
		this.fatal = fatal;
	}
	@Override
	public String toString() {
		return "LogsSpcification [info=" + info + ", warn=" + warn + ", error=" + error + ", debug=" + debug
				+ ", fatal=" + fatal + ", logfilelocation=" + logfilelocation + ", mvnCommand=" + mvnCommand
				+ ", htmlLocation=" + htmlLocation + ", hostname=" + hostname + ", username=" + username + ", password="
				+ password + ", linuxLog=" + linuxLog + "]";
	}
	public LogsSpcification(String info, String warn, String error, String debug, String fatal, String logfilelocation,
			String mvnCommand, String htmlLocation, String hostname, String username, String password,
			String linuxLog) {
		super();
		this.info = info;
		this.warn = warn;
		this.error = error;
		this.debug = debug;
		this.fatal = fatal;
		this.logfilelocation = logfilelocation;
		this.mvnCommand = mvnCommand;
		this.htmlLocation = htmlLocation;
		this.hostname = hostname;
		this.username = username;
		this.password = password;
		this.linuxLog = linuxLog;
	}
	
	
	

	
	
}
