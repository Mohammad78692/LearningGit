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
				+ ", fatal=" + fatal + ", logfilelocation=" + logfilelocation + ", mvnCommand=" + mvnCommand + "]";
	}
	public LogsSpcification(String info, String warn, String error, String debug, String fatal, String logfilelocation,
			String mvnCommand) {
		super();
		this.info = info;
		this.warn = warn;
		this.error = error;
		this.debug = debug;
		this.fatal = fatal;
		this.logfilelocation = logfilelocation;
		this.mvnCommand = mvnCommand;
	}

	
	
}
