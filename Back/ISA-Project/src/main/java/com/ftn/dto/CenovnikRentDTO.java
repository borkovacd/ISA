package com.ftn.dto;

public class CenovnikRentDTO 
{
	private String startDate;
	
	private String endDate;

	public CenovnikRentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CenovnikRentDTO(String startDate, String endDate) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
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
