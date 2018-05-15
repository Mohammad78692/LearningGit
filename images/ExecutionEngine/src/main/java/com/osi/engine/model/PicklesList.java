package com.osi.engine.model;

import java.io.Serializable;
import java.util.List;

import gherkin.pickles.Pickle;

public class PicklesList implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Pickle> pickles;
	
	private List<String> fileLocation;

	public PicklesList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PicklesList(List<Pickle> pickles, List<String> fileLocation) {
		super();
		this.pickles = pickles;
		this.fileLocation = fileLocation;
	}

	public List<String> getFileLocation() {
		return fileLocation;
	}

	public void setFileLocation(List<String> fileLocation) {
		this.fileLocation = fileLocation;
	}

	public List<Pickle> getPickles() {
		return pickles;
	}

	public void setPickles(List<Pickle> pickles) {
		this.pickles = pickles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fileLocation == null) ? 0 : fileLocation.hashCode());
		result = prime * result + ((pickles == null) ? 0 : pickles.hashCode());
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
		PicklesList other = (PicklesList) obj;
		if (fileLocation == null) {
			if (other.fileLocation != null)
				return false;
		} else if (!fileLocation.equals(other.fileLocation))
			return false;
		if (pickles == null) {
			if (other.pickles != null)
				return false;
		} else if (!pickles.equals(other.pickles))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PicklesList [pickles=" + pickles + ", fileLocation=" + fileLocation + "]";
	}

	

}
