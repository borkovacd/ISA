package com.ftn.model.rentacar;

import javax.persistence.*;

@Entity
public class Lokacija 
{
	@Id
	@GeneratedValue
	private long lokacijaId;
	
	private String adresa;
	
	// rentACar se nalazi na toj lokaciji
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="IdRentACar", referencedColumnName="rentACarId")
	private RentACar rentACar;

	public Lokacija() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Lokacija(String adresa, RentACar rentACar) {
		super();
		
		this.adresa = adresa;
		this.rentACar = rentACar;
	}

	public long getLokacijaId() {
		return lokacijaId;
	}

	public void setLokacijaId(long lokacijaId) {
		this.lokacijaId = lokacijaId;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public RentACar getRentACar() {
		return rentACar;
	}

	public void setRentACar(RentACar rentACar) {
		this.rentACar = rentACar;
	}
	
	
}
