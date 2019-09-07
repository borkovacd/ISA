package com.ftn.dto;

public class RezervacijaVozilaDTO 
{
	private String startDate;
	private String endDate;
	private String vozilo ;
	
	private String mestoPreuzimanja; 
	private String mestoVracanja ;
	
	private String numberOfGuests;
	
	public RezervacijaVozilaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}



	public RezervacijaVozilaDTO(String startDate, String endDate, String vozilo, String mestoPreuzimanja,
			String mestoVracanja) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.vozilo = vozilo;
		this.mestoPreuzimanja = mestoPreuzimanja;
		this.mestoVracanja = mestoVracanja;
	}


	
	

	public RezervacijaVozilaDTO(String startDate, String endDate, String vozilo, String mestoPreuzimanja,
			String mestoVracanja, String numberOfGuests) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.vozilo = vozilo;
		this.mestoPreuzimanja = mestoPreuzimanja;
		this.mestoVracanja = mestoVracanja;
		this.numberOfGuests = numberOfGuests;
	}



	public String getNumberOfGuests() {
		return numberOfGuests;
	}



	public void setNumberOfGuests(String numberOfGuests) {
		this.numberOfGuests = numberOfGuests;
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

	public String getVozilo() {
		return vozilo;
	}

	public void setVozilo(String vozilo) {
		this.vozilo = vozilo;
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
	
	
	
	
	
}
