package com.ftn.model.rentacar;

public class CenovnikRentACar 
{
	Vozilo vozilo ;
	private int brojDana ;
	
	public CenovnikRentACar() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CenovnikRentACar(Vozilo vozilo, int brojDana) {
		super();
		this.vozilo = vozilo;
		this.brojDana = brojDana;
	}

	public Vozilo getVozilo() {
		return vozilo;
	}

	public void setVozilo(Vozilo vozilo) {
		this.vozilo = vozilo;
	}

	public int getBrojDana() {
		return brojDana;
	}

	public void setBrojDana(int brojDana) {
		this.brojDana = brojDana;
	}
	
	
}
