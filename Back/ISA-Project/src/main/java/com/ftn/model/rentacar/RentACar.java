package com.ftn.model.rentacar;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.ftn.model.Korisnik;

@Entity
public class RentACar 
{

	@Id
	@GeneratedValue
	private Long rentACarId;
	
	private String naziv;
	private String adresa;
	private String opis;
	
	@OneToOne(fetch = FetchType.EAGER)
	private Korisnik administrator; //administrator rent a car

	
	/*
	// u servisu se nalazi lista vozila
	@OneToMany(targetEntity=Vozilo.class, mappedBy="rentACar", cascade = CascadeType.ALL)
	private List<Vozilo> spisakVozila = new ArrayList<>();
	
	// filijale na kojima se rentACar nalazi
	@OneToMany(targetEntity=Lokacija.class, mappedBy="rentACar", cascade = CascadeType.ALL)
	private List<Lokacija> lokacije = new ArrayList<>();
	*/
	
	//private List<Vozilo> cenovnik = new ArrayList<>();
	
	// FALI
	// CenovnikUsluga
	
	/*
	private ArrayList<Integer> ocene;
	
	private double prihod;
	private ArrayList<RezervacijaVozila> listaRezervacija;
	*/
	/*@OneToMany(targetEntity=Korisnik.class, mappedBy="rentACar", cascade = CascadeType.ALL)
	private List<Korisnik> administratori = new ArrayList<>();*/


	
	// Inspo
	
	/*
	private double prosecnaOcena ;
	private double brojOcena ;
	private double sumaOcena ;
	*/

	public RentACar() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RentACar(String naziv, String adresa, String opis, Korisnik administrator) {
		super();
		this.naziv = naziv;
		this.adresa = adresa;
		this.opis = opis;
		this.administrator = administrator;
	}

	public Korisnik getAdministrator() {
		return administrator;
	}

	public void setAdministrator(Korisnik administrator) {
		this.administrator = administrator;
	}

	// sa spiskom vozila
	public RentACar(String naziv, String adresa, String opis, Korisnik administrator, List<Vozilo> spisakVozila) {
		super();
		this.naziv = naziv;
		this.adresa = adresa;
		this.opis = opis;
		this.administrator = administrator;
	}

	public Long getRentACarId() {
		return rentACarId;
	}

	public void setRentACarId(Long rentACarId) {
		this.rentACarId = rentACarId;
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
	
	
}
