package com.ftn.dto;

public class RentCarDTO {

	private String name;
	private String address;
	private String description;
	private String administratorRentCar;
	
	public RentCarDTO() {
		
	}
	
	public RentCarDTO(String name, String address, String description, String administratorRentCar) {
		super();
		this.name = name;
		this.address = address;
		this.description = description;
		this.administratorRentCar = administratorRentCar;
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

	public String getAdministratorRentCar() {
		return administratorRentCar;
	}

	public void setAdministratorRentCar(String administratorRentCar) {
		this.administratorRentCar = administratorRentCar;
	}

}

