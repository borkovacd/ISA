package com.ftn.dto;

import com.ftn.enums.UlogaKorisnika;
import com.ftn.model.Korisnik;

public class KorisnikDTO 
{
	
	private String ime;
	private String prezime;
	private String korisnickoIme;
	private String lozinka;
	private String email;
	private String telefon;
	private String grad;
	
	private String statusKorisnika; // na osnovu ovoga redirekcija
	
	private String uloga ;

	public KorisnikDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public KorisnikDTO(String ime, String prezime, String korisnickoIme, String lozinka, String email,
			String telefon, String grad, String uloga) {
		super();
		
		this.ime = ime;
		this.prezime = prezime;
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.email = email;
		this.telefon = telefon;
		this.grad = grad;
		this.uloga = uloga;
	}
	
	public KorisnikDTO(Korisnik korisnik) {
		
		this.ime = korisnik.getIme();
		this.prezime = korisnik.getPrezime();
		this.korisnickoIme = korisnik.getKorisnickoIme();
		this.lozinka = korisnik.getLozinka();
		this.email = korisnik.getEmail();
		this.telefon = korisnik.getTelefon();
		this.grad = korisnik.getGrad();
		this.uloga = korisnik.getUloga().toString();
		
	}

	
	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

	public String getUloga() {
		return uloga;
	}

	public void setUloga(String uloga) {
		this.uloga = uloga;
	}

	public String getStatusKorisnika() {
		return statusKorisnika;
	}

	public void setStatusKorisnika(String statusKorisnika) {
		this.statusKorisnika = statusKorisnika;
	}
	
	
}
