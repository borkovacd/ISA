package isa.proj.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class CenovnikHotela {

	@Id
	@GeneratedValue
	private Long id;
	
	private HashMap<String, Double> ceneNocenja = new HashMap<String, Double> ();
	
	private HashMap<Integer, Double> ceneDodatnihUsluga = new HashMap<Integer,Double> ();
	
	private Double popust;
	
	@OneToOne(fetch = FetchType.LAZY)
    @MapsId
	private Hotel hotel;
	
	public CenovnikHotela () {
		
	}

	public CenovnikHotela(Long id, HashMap<String, Double> ceneNocenja, HashMap<Integer, Double> ceneDodatnihUsluga,
			Double popust, Hotel hotel) {
		super();
		this.id = id;
		this.ceneNocenja = ceneNocenja;
		this.ceneDodatnihUsluga = ceneDodatnihUsluga;
		this.popust = popust;
		this.hotel = hotel;
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
