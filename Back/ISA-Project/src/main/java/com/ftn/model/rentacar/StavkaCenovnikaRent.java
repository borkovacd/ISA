package com.ftn.model.rentacar;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.ftn.enums.TipVozila;

@Entity
public class StavkaCenovnikaRent {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Double cena ;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private CenovnikRentACar cenovnik ;
	
	@Enumerated(EnumType.STRING)
	private TipVozila tipVozila ;

	public StavkaCenovnikaRent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StavkaCenovnikaRent(Double cena, CenovnikRentACar cenovnik, TipVozila tipVozila) {
		super();
		this.cena = cena;
		this.cenovnik = cenovnik;
		this.tipVozila = tipVozila;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getCena() {
		return cena;
	}

	public void setCena(Double cena) {
		this.cena = cena;
	}

	public CenovnikRentACar getCenovnik() {
		return cenovnik;
	}

	public void setCenovnik(CenovnikRentACar cenovnik) {
		this.cenovnik = cenovnik;
	}

	public TipVozila getTipVozila() {
		return tipVozila;
	}

	public void setTipVozila(TipVozila tipVozila) {
		this.tipVozila = tipVozila;
	}
	
	

}
