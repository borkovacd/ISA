package com.ftn.dto;

public class HotelDTO {
	
	private String name;
	private String address;
	private String description;
	private String administratorHotela;
	
	public HotelDTO() {
		
	}
	
	public HotelDTO(String name, String address, String description, String administratorHotela) {
		super();
		this.name = name;
		this.address = address;
		this.description = description;
		this.administratorHotela = administratorHotela;
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

	public String getAdministratorHotela() {
		return administratorHotela;
	}

	public void setAdministratorHotela(String administratorHotela) {
		this.administratorHotela = administratorHotela;
	}
	
	
	
	
	

}
