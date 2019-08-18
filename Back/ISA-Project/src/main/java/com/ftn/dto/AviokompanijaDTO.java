package com.ftn.dto;

public class AviokompanijaDTO {

	private String name;
	private String address;
	private String description;
	private String administratorAviokompanije;
	
	public AviokompanijaDTO() {
		// TODO Auto-generated constructor stub
	} 
	
	public AviokompanijaDTO(String name, String address, String description, String administratorAviokompanije) {
		super();
		this.name = name;
		this.address = address;
		this.description = description;
		this.administratorAviokompanije = administratorAviokompanije;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAdministratorAviokompanije() {
		return administratorAviokompanije;
	}

	public void setAdministratorAviokompanije(String administratorAviokompanije) {
		this.administratorAviokompanije = administratorAviokompanije;
	}
	

}

