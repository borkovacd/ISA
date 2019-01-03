package isa.proj.model;

import java.util.HashMap;

public class CenovnikHotela {

	// ovde bi trebala da ide cena nocenja (uz ubacen datum za vazenje cene, spa, bazen, room service..)
	
	private HashMap<String, Double> ceneNocenja = new HashMap<String, Double> ();
	private HashMap<Integer, Double> ceneDodatnihUsluga = new HashMap<Integer,Double> ();
	private Double popust;
	private Hotel hotel;
	
	public CenovnikHotela () {
		
	}

	public CenovnikHotela(HashMap<String, Double> ceneNocenja, HashMap<Integer, Double> ceneDodatnihUsluga,
			Double popust, Hotel hotel) {
		super();
		this.ceneNocenja = ceneNocenja;
		this.ceneDodatnihUsluga = ceneDodatnihUsluga;
		this.popust = popust;
		this.hotel = hotel;
	}

	public HashMap<String, Double> getCeneNocenja() {
		return ceneNocenja;
	}

	public void setCeneNocenja(HashMap<String, Double> ceneNocenja) {
		this.ceneNocenja = ceneNocenja;
	}

	public HashMap<Integer, Double> getCeneDodatnihUsluga() {
		return ceneDodatnihUsluga;
	}

	public void setCeneDodatnihUsluga(HashMap<Integer, Double> ceneDodatnihUsluga) {
		this.ceneDodatnihUsluga = ceneDodatnihUsluga;
	}

	public Double getPopust() {
		return popust;
	}

	public void setPopust(Double popust) {
		this.popust = popust;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	
	
}
