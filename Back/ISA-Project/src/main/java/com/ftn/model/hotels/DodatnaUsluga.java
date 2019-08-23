package com.ftn.model.hotels;

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
	private TipDodatneUsluge tipDodateneUsluge;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Hotel hotel;
	
	public DodatnaUsluga() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipDodatneUsluge getTipDodateneUsluge() {
		return tipDodateneUsluge;
	}

	public void setTipDodateneUsluge(TipDodatneUsluge tipDodateneUsluge) {
		this.tipDodateneUsluge = tipDodateneUsluge;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	
	

	
	
	
	

}
