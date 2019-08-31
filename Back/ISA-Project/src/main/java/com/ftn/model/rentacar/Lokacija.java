package com.ftn.model.rentacar;

import javax.persistence.*;

@Entity
public class Lokacija 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String adresa;
	// inspo
	private String drzava ;
	private String grad ;
	private double longitude ;
	private double latitude ;
	
	
	public RentACar getRentACar() {
		return rentACar;
	}

	public void setRentACar(RentACar rentACar) {
		this.rentACar = rentACar;
	}

	// rentACar se nalazi na toj lokaciji
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="rentACarId", referencedColumnName="rentACarId")
	private RentACar rentACar;
	

	public Lokacija() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Lokacija(String adresa, RentACar rentACar) {
		super();
		
		this.adresa = adresa;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}


	public String getDrzava() {
		return drzava;
	}

	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}

	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	
	
}
