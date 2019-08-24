package com.ftn.model.hotels;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.ftn.enums.TipDodatneUsluge;
import com.ftn.enums.TipSobe;

@Entity
public class StavkaCenovnikaHotela {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Double cena;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private CenovnikHotela cenovnik;
	
	@Enumerated(EnumType.STRING)
	private TipSobe tipSobe;
	
	@Enumerated(EnumType.STRING)
	private TipDodatneUsluge tipDodatneUsluge;

	
	public StavkaCenovnikaHotela() {
		
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


	public CenovnikHotela getCenovnik() {
		return cenovnik;
	}


	public void setCenovnik(CenovnikHotela cenovnik) {
		this.cenovnik = cenovnik;
	}


	public TipSobe getTipSobe() {
		return tipSobe;
	}


	public void setTipSobe(TipSobe tipSobe) {
		this.tipSobe = tipSobe;
	}


	public TipDodatneUsluge getTipDodatneUsluge() {
		return tipDodatneUsluge;
	}


	public void setTipDodatneUsluge(TipDodatneUsluge tipDodatneUsluge) {
		this.tipDodatneUsluge = tipDodatneUsluge;
	}
	
	
	
	

}
