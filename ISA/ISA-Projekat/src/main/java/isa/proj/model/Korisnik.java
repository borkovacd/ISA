package isa.proj.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity //Na osnovu anotacije 'Entity' Hibernate zna da treba da napravi tabelu od ove klase
public class Korisnik {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer idKorisnika;
	private String korisnickoIme;
	private String lozinka;
	private String ime;
	private String prezime;
	private String telefon;
	private String email;
	private String uloga;
	
	public Korisnik() {
		
	}

	public Korisnik(Integer idKorisnika, String korisnickoIme, String lozinka, String ime, String prezime, String telefon, String email,
			String uloga) {
		super();
		this.idKorisnika = idKorisnika;
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.ime = ime;
		this.prezime = prezime;
		this.telefon = telefon;
		this.email = email;
		this.uloga = uloga;
	}

	public Integer getIdKorisnika() {
		return idKorisnika;
	}

	public void setIdKorisnika(Integer idKorisnika) {
		this.idKorisnika = idKorisnika;
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

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUloga() {
		return uloga;
	}

	public void setUloga(String uloga) {
		this.uloga = uloga;
	}

}
