package com.ftn.dto;

public class PretragaRentDTO {

	private String rentName ;
	private String rentLocation ;
	private String startDate;
	private String endDate;
	
	public PretragaRentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PretragaRentDTO(String rentName, String rentLocation, String startDate, String endDate) {
		super();
		this.rentName = rentName;
		this.rentLocation = rentLocation;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public String getRentName() {
		return rentName;
	}

	public void setRentName(String rentName) {
		this.rentName = rentName;
	}

	public String getRentLocation() {
		return rentLocation;
	}

	public void setRentLocation(String rentLocation) {
		this.rentLocation = rentLocation;
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
	
	
	
	
}
