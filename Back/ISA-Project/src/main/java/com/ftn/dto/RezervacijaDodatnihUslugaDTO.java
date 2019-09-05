package com.ftn.dto;

import java.util.ArrayList;

public class RezervacijaDodatnihUslugaDTO {
	
	private String startDate;
	private String endDate;
	private ArrayList<String> additionalServicesList;
	
	public RezervacijaDodatnihUslugaDTO() {
		
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public ArrayList<String> getAdditionalServicesList() {
		return additionalServicesList;
	}

	public void setAdditionalServicesList(ArrayList<String> additionalServicesList) {
		this.additionalServicesList = additionalServicesList;
	}
	
	
	

}
