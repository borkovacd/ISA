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
	private Vozilo vozilo;

	@ManyToOne(fetch = FetchType.EAGER)
	private Lokacija mestoPreuzimanja;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Lokacija mestoVracanja;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Korisnik korisnik;
	
	private double cena ;
	
	private int brojPutnika;
	
	private int tipRezervacije; //0 -> obicna rezervacija 
	 							//1 -> brza rezervacija

	public RezervacijaVozila() {
		super();
		// TODO Auto-generated constructor stub
		vozilo = new Vozilo();
		tipRezervacije = 0;
	}

	public RezervacijaVozila(LocalDate datumPreuzimanja, Lokacija mestoPreuzimanja, LocalDate datumVracanja,
			Lokacija mestoVracanja, String tipVozila, int brPutnika) {
		super();
		this.datumPreuzimanja = datumPreuzimanja;
		this.mestoPreuzimanja = mestoPreuzimanja;
		this.datumVracanja = datumVracanja;
		this.mestoVracanja = mestoVracanja;
	}
	
	
	public RezervacijaVozila(LocalDate datumPreuzimanja, LocalDate datumVracanja, Vozilo vozilo,
			Lokacija mestoPreuzimanja, Lokacija mestoVracanja, Korisnik korisnik, double cena) {
		super();
		this.datumPreuzimanja = datumPreuzimanja;
		this.datumVracanja = datumVracanja;
		this.vozilo = vozilo;
		this.mestoPreuzimanja = mestoPreuzimanja;
		this.mestoVracanja = mestoVracanja;
		this.korisnik = korisnik;
		this.cena = cena;
	}
	
	

	public RezervacijaVozila(LocalDate datumPreuzimanja, LocalDate datumVracanja, Vozilo vozilo,
			Lokacija mestoPreuzimanja, Lokacija mestoVracanja, Korisnik korisnik, double cena, int brojPutnika,
			int tipRezervacije) {
		super();
		this.datumPreuzimanja = datumPreuzimanja;
		this.datumVracanja = datumVracanja;
		this.vozilo = vozilo;
		this.mestoPreuzimanja = mestoPreuzimanja;
		this.mestoVracanja = mestoVracanja;
		this.korisnik = korisnik;
		this.cena = cena;
		this.brojPutnika = brojPutnika;
		this.tipRezervacije = tipRezervacije;
	}

	
	
	public int getBrojPutnika() {
		return brojPutnika;
	}

	public void setBrojPutnika(int brojPutnika) {
		this.brojPutnika = brojPutnika;
	}

	public int getTipRezervacije() {
		return tipRezervacije;
	}

	public void setTipRezervacije(int tipRezervacije) {
		this.tipRezervacije = tipRezervacije;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
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
