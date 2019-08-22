package com.ftn.model.rentacar;

import java.sql.Date;
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
	
	private Date datumPreuzimanja;
	private Date datumVracanja;

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
	
	// lista rezervisanih vozila
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Vozilo> rezervisanaVozila;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Vozilo vozilo;

	@ManyToOne(fetch = FetchType.EAGER)
	private Korisnik korisnik;

	public RezervacijaVozila() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RezervacijaVozila(Date datumPreuzimanja, Lokacija mestoPreuzimanja, Date datumVracanja,
			Lokacija mestoVracanja, String tipVozila, int brPutnika) {
		super();
		this.datumPreuzimanja = datumPreuzimanja;
		this.mestoPreuzimanja = mestoPreuzimanja;
		this.datumVracanja = datumVracanja;
		this.mestoVracanja = mestoVracanja;
		this.tipVozila = tipVozila;
		this.brPutnika = brPutnika;
	}

	public Date getDatumPreuzimanja() {
		return datumPreuzimanja;
	}

	public void setDatumPreuzimanja(Date datumPreuzimanja) {
		this.datumPreuzimanja = datumPreuzimanja;
	}

	public Lokacija getMestoPreuzimanja() {
		return mestoPreuzimanja;
	}

	public void setMestoPreuzimanja(Lokacija mestoPreuzimanja) {
		this.mestoPreuzimanja = mestoPreuzimanja;
	}

	public Date getDatumVracanja() {
		return datumVracanja;
	}

	public void setDatumVracanja(Date datumVracanja) {
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

	public List<Vozilo> getRezervisanaVozila() {
		return rezervisanaVozila;
	}

	public void setRezervisanaVozila(ArrayList<Vozilo> rezervisanaVozila) {
		this.rezervisanaVozila = rezervisanaVozila;
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

	public void setRezervisanaVozila(List<Vozilo> rezervisanaVozila) {
		this.rezervisanaVozila = rezervisanaVozila;
	}
	
	
	
	
	
	
}
