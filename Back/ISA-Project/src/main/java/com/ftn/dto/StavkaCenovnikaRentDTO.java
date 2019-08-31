package com.ftn.dto;

public class StavkaCenovnikaRentDTO 
{
	private String tipStavke;
	private String cena;
	
	public StavkaCenovnikaRentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StavkaCenovnikaRentDTO(String tipStavke, String cena) {
		super();
		this.tipStavke = tipStavke;
		this.cena = cena;
	}

	public String getTipStavke() {
		return tipStavke;
	}

	public void setTipStavke(String tipStavke) {
		this.tipStavke = tipStavke;
	}

	public String getCena() {
		return cena;
	}

	public void setCena(String cena) {
		this.cena = cena;
	}
	
	
	
	
}
