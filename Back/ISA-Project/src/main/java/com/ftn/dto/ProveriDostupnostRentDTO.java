package com.ftn.dto;

public class ProveriDostupnostRentDTO 
{
	
	// Korak 2 - 2.10
	private String startDate;
	private String endDate;
	
	private String mestoPreuzimanja ;
	private String mestoVracanja ;
	
	private String numberOfGuests; // broj putnika
	private String tipVozila ;
	
	private String priceRange;

	public ProveriDostupnostRentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProveriDostupnostRentDTO(String startDate, String endDate, String mestoPreuzimanja, String mestoVracanja,
			String numberOfGuests, String tipVozila, String priceRange) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.mestoPreuzimanja = mestoPreuzimanja;
		this.mestoVracanja = mestoVracanja;
		this.numberOfGuests = numberOfGuests;
		this.tipVozila = tipVozila;
		this.priceRange = priceRange;
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

	public String getMestoPreuzimanja() {
		return mestoPreuzimanja;
	}

	public void setMestoPreuzimanja(String mestoPreuzimanja) {
		this.mestoPreuzimanja = mestoPreuzimanja;
	}

	public String getMestoVracanja() {
		return mestoVracanja;
	}

	public void setMestoVracanja(String mestoVracanja) {
		this.mestoVracanja = mestoVracanja;
	}

	public String getNumberOfGuests() {
		return numberOfGuests;
	}

	public void setNumberOfGuests(String numberOfGuests) {
		this.numberOfGuests = numberOfGuests;
	}

	public String getTipVozila() {
		return tipVozila;
	}

	public void setTipVozila(String tipVozila) {
		this.tipVozila = tipVozila;
	}

	public String getPriceRange() {
		return priceRange;
	}

	public void setPriceRange(String priceRange) {
		this.priceRange = priceRange;
	}
	
	
	
	
}
