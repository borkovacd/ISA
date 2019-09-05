package com.ftn.model.hotels;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.ftn.enums.TipDodatneUsluge;

@Entity
public class DodatnaUsluga {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private TipDodatneUsluge tipDodatneUsluge;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Hotel hotel;
	
	@Column(nullable =  true)
	private double cena;
	
	public DodatnaUsluga() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipDodatneUsluge getTipDodatneUsluge() {
		return tipDodatneUsluge;
	}

	public void setTipDodateneUsluge(TipDodatneUsluge tipDodatneUsluge) {
		this.tipDodatneUsluge = tipDodatneUsluge;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public void setTipDodatneUsluge(TipDodatneUsluge tipDodatneUsluge) {
		this.tipDodatneUsluge = tipDodatneUsluge;
	}
	
	
	
	

	
	
	
	

}
