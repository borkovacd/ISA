package com.ftn.model.hotels;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.ftn.model.Korisnik;

@Entity
public class OcenaSoba {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private int ocena;;
	
	@ManyToOne
	private Korisnik user; //korisnik koji je ocenio
	
	@ManyToOne
	private Soba soba;

	public OcenaSoba() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OcenaSoba(int ocena, Korisnik user, Soba soba) {
		super();
		this.ocena = ocena;
		this.user = user;
		this.soba = soba;
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

	public Soba getSoba() {
		return soba;
	}

	public void setSoba(Soba soba) {
		this.soba = soba;
	}
	
	
	
}
