package com.ftn.model;

import javax.persistence.*;

import com.ftn.enums.UlogaKorisnika;

@Entity
public class Korisnik {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String ime;
	private String prezime;
	private String korisnickoIme;
	private String lozinka;
	private String email;
	private String telefon;
	private String grad;
	
	@Enumerated(EnumType.STRING)
	private UlogaKorisnika uloga; 
	
	private boolean prvoLogovanje = true;
	private boolean verifikovan;
	
	/*@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="IdRentACar", referencedColumnName="rentACarId")
	private RentACar rentACar = null;*/

	public Korisnik() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Korisnik(String ime, String prezime, String korisnickoIme, String lozinka, String email, String telefon,
			String grad) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.email = email;
		this.telefon = telefon;
		this.grad = grad;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public UlogaKorisnika getUloga() {
		return uloga;
	}

	public void setUloga(UlogaKorisnika uloga) {
		this.uloga = uloga;
	}

	public boolean isPrvoLogovanje() {
		return prvoLogovanje;
	}

	public void setPrvoLogovanje(boolean prvoLogovanje) {
		this.prvoLogovanje = prvoLogovanje;
	}

	public boolean isVerifikovan() {
		return verifikovan;
	}

	public void setVerifikovan(boolean verifikovan) {
		this.verifikovan = verifikovan;
	}

	/*public RentACar getRentACar() {
		return rentACar;
	}

	public void setRentACar(RentACar rentACar) {
		this.rentACar = rentACar;
	}*/
	
	

}
