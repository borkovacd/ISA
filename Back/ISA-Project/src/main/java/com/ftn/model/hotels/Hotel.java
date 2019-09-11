package com.ftn.model.hotels;

import java.util.Comparator;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.ftn.model.Korisnik;
import com.ftn.model.rentacar.RentACar;

@Entity
public class Hotel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String naziv;

	private String adresa;

	private String opis;
	
	@OneToOne(fetch = FetchType.EAGER)
	private Korisnik administrator; //administrator hotela

	public Hotel() {
		
	}
	
	public Hotel(String naziv, String adresa, String opis, Korisnik administrator) {
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
	
	public static Comparator<Hotel> HotelNameComparator = new Comparator<Hotel>() {

		public int compare(Hotel u1, Hotel u2) {
			String name1 = u1.getNaziv().toUpperCase();
			String name2 = u2.getNaziv().toUpperCase();

			// sortiranje od A-Z
			return name1.compareTo(name2);

		}
	};

	public static Comparator<Hotel> HotelCityComparator = new Comparator<Hotel>() {

		public int compare(Hotel u1, Hotel u2) {
			String city1 = u1.getAdresa().toUpperCase();
			String city2 = u2.getAdresa().toUpperCase();

			// sortiranje od A-Z
			return city1.compareTo(city2);

		}
	};
	
	
	

}
