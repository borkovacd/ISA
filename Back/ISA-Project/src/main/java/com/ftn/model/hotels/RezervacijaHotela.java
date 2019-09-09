package com.ftn.model.hotels;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import com.ftn.model.Korisnik;

@Entity
public class RezervacijaHotela {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Version
    private Long version;

	private LocalDate datumPocetka;

	private LocalDate datumKraja;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<Soba> sobe;
	
	@ManyToMany(fetch = FetchType.LAZY)
	private List<DodatnaUsluga> dodatneUsluge;

	@ManyToOne(fetch = FetchType.EAGER)
	private Korisnik korisnik;
	
	private double cena;
	
	private int brojGostiju;
	
	private int tipRezervacije; //0 -> obicna rezervacija 
	 							//1 -> brza rezervacija
	
	public RezervacijaHotela() {
		
		sobe = new ArrayList<Soba>();
		dodatneUsluge = new ArrayList<DodatnaUsluga>();
		tipRezervacije = 0;
	}

	public RezervacijaHotela(LocalDate datumPocetka, LocalDate datumKraja, List<Soba> sobe, List<DodatnaUsluga> dodatneUsluge, Korisnik korisnik,
			double cena, int tipRezervacije, int brojGostiju) {
		super();
		this.datumPocetka = datumPocetka;
		this.datumKraja = datumKraja;
		this.sobe = sobe;
		this.dodatneUsluge = dodatneUsluge;
		this.korisnik = korisnik;
		this.cena = cena;
		this.tipRezervacije = tipRezervacije;
		this.brojGostiju = brojGostiju;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDatumPocetka() {
		return datumPocetka;
	}

	public void setDatumPocetka(LocalDate datumPocetka) {
		this.datumPocetka = datumPocetka;
	}

	public LocalDate getDatumKraja() {
		return datumKraja;
	}

	public void setDatumKraja(LocalDate datumKraja) {
		this.datumKraja = datumKraja;
	}

	public List<Soba> getSobe() {
		return sobe;
	}

	public void setSobe(List<Soba> sobe) {
		this.sobe = sobe;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public List<DodatnaUsluga> getDodatneUsluge() {
		return dodatneUsluge;
	}

	public void setDodatneUsluge(List<DodatnaUsluga> dodatneUsluge) {
		this.dodatneUsluge = dodatneUsluge;
	}

	public int getTipRezervacije() {
		return tipRezervacije;
	}

	public void setTipRezervacije(int tipRezervacije) {
		this.tipRezervacije = tipRezervacije;
	}

	public int getBrojGostiju() {
		return brojGostiju;
	}

	public void setBrojGostiju(int brojGostiju) {
		this.brojGostiju = brojGostiju;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
	
	
	
	
	
	

}
