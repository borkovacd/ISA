package com.ftn.model.rentacar;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.ftn.model.Korisnik;

// KORAK 2 tacka 2.10 
@Entity
public class RezervacijaVozila 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDate datumPreuzimanja;
	private LocalDate datumVracanja;

	@ManyToOne(fetch = FetchType.EAGER)
	private Lokacija mestoPreuzimanja;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Lokacija mestoVracanja;
	
	private String tipVozila;
	private int brPutnika;
	
	// FALI
	// opciono cenovni rang
	
	// private int minCena ;
	// private int maxCena ;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Vozilo vozilo;

	@ManyToOne(fetch = FetchType.EAGER)
	private Korisnik korisnik;

	public RezervacijaVozila() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RezervacijaVozila(LocalDate datumPreuzimanja, Lokacija mestoPreuzimanja, LocalDate datumVracanja,
			Lokacija mestoVracanja, String tipVozila, int brPutnika) {
		super();
		this.datumPreuzimanja = datumPreuzimanja;
		this.mestoPreuzimanja = mestoPreuzimanja;
		this.datumVracanja = datumVracanja;
		this.mestoVracanja = mestoVracanja;
		this.tipVozila = tipVozila;
		this.brPutnika = brPutnika;
	}

	

	public Lokacija getMestoPreuzimanja() {
		return mestoPreuzimanja;
	}

	public void setMestoPreuzimanja(Lokacija mestoPreuzimanja) {
		this.mestoPreuzimanja = mestoPreuzimanja;
	}

	
	public LocalDate getDatumPreuzimanja() {
		return datumPreuzimanja;
	}

	public void setDatumPreuzimanja(LocalDate datumPreuzimanja) {
		this.datumPreuzimanja = datumPreuzimanja;
	}

	public LocalDate getDatumVracanja() {
		return datumVracanja;
	}

	public void setDatumVracanja(LocalDate datumVracanja) {
		this.datumVracanja = datumVracanja;
	}

	public Lokacija getMestoVracanja() {
		return mestoVracanja;
	}

	public void setMestoVracanja(Lokacija mestoVracanja) {
		this.mestoVracanja = mestoVracanja;
	}

	public String getTipVozila() {
		return tipVozila;
	}

	public void setTipVozila(String tipVozila) {
		this.tipVozila = tipVozila;
	}

	public int getBrPutnika() {
		return brPutnika;
	}

	public void setBrPutnika(int brPutnika) {
		this.brPutnika = brPutnika;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public Vozilo getVozilo() {
		return vozilo;
	}

	public void setVozilo(Vozilo vozilo) {
		this.vozilo = vozilo;
	}

	
	
	
	
	
}
