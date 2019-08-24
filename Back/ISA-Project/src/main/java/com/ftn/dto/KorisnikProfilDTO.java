package com.ftn.dto;

public class KorisnikProfilDTO {
	
	private String ime;
	private String prezime;
	private String lozinka;
	private String ponovljenaLozinka;
	private String telefon;
	private String grad;
	
	public KorisnikProfilDTO() {
		
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

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public String getPonovljenaLozinka() {
		return ponovljenaLozinka;
	}

	public void setPonovljenaLozinka(String ponovljenaLozinka) {
		this.ponovljenaLozinka = ponovljenaLozinka;
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

	
}
