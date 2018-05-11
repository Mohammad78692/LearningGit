package com.osi.loganalyzer.model;

import java.io.Serializable;

public class Command implements Serializable {
	/**
	 * To send over the network
	 */
	private static final long serialVersionUID = 1L;

	
	private String location;
	private String command;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	
	@Override
	public String toString() {
		return "Command [location=" + location + ", command=" + command + "]";
	}

}
