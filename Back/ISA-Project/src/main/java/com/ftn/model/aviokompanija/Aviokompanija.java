package com.ftn.model.aviokompanija;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.ftn.model.Korisnik;

@Entity
public class Aviokompanija {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String naziv;

	private String adresa;

	private String opis;
	
	@OneToOne(fetch = FetchType.EAGER)
	private Korisnik administrator; //administrator aviokompanije

	public Aviokompanija() {
		
	}
	
	public Aviokompanija(String naziv, String adresa, String opis, Korisnik administrator) {
		super();
		this.naziv = naziv;
		this.adresa = adresa;
		this.opis = opis;
		this.administrator = administrator;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public Korisnik getAdministrator() {
		return administrator;
	}

	public void setAdministrator(Korisnik administrator) {
		this.administrator = administrator;
	}
}
