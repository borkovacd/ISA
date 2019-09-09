package com.ftn.model.rentacar;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.persistence.*;

import com.ftn.model.Korisnik;

@Entity
public class RentACar 
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long rentACarId;
	
	private String naziv;
	private String adresa;
	private String opis;
	
	@OneToOne(fetch = FetchType.EAGER)
	private Korisnik administrator; //administrator rent a car

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
	
	// SORTIRANJE
	public static Comparator<RentACar> RentNameComparator = new Comparator<RentACar>() {

		public int compare(RentACar u1, RentACar u2) {
			String name1 = u1.getNaziv().toUpperCase();
			String name2 = u2.getNaziv().toUpperCase();

			// sortiranje od A-Z
			return name1.compareTo(name2);

		}
	};

	public static Comparator<RentACar> RentCityComparator = new Comparator<RentACar>() {

		public int compare(RentACar u1, RentACar u2) {
			String city1 = u1.getAdresa().toUpperCase();
			String city2 = u2.getAdresa().toUpperCase();

			// sortiranje od A-Z
			return city1.compareTo(city2);

		}
	};
	
	
}
