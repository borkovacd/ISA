package com.ftn.model.hotels;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.ftn.model.Korisnik;
import com.ftn.model.rentacar.RentACar;

@Entity
public class OcenaHotel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private int ocena;
	
	@NotNull
	@ManyToOne
	private Korisnik user;
	
	@ManyToOne
	private Hotel hotel ;

	public OcenaHotel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OcenaHotel(@NotNull int ocena, @NotNull Korisnik user, Hotel hotel) {
		super();
		this.ocena = ocena;
		this.user = user;
		this.hotel = hotel;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getOcena() {
		return ocena;
	}

	public void setOcena(int ocena) {
		this.ocena = ocena;
	}

	public Korisnik getUser() {
		return user;
	}

	public void setUser(Korisnik user) {
		this.user = user;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	
	

}
