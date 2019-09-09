package com.ftn.model.rentacar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.ftn.model.Korisnik;
import com.ftn.model.rentacar.Vozilo;

@Entity
public class OcenaVozilo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private int ocena;
	
	@NotNull
	@ManyToOne
	private Korisnik user;
	
	@ManyToOne
	private Vozilo vozilo ;

	public OcenaVozilo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OcenaVozilo(@NotNull int ocena, @NotNull Korisnik user, Vozilo vozilo) {
		super();
		this.ocena = ocena;
		this.user = user;
		this.vozilo = vozilo;
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

	public Vozilo getVozilo() {
		return vozilo;
	}

	public void setVozilo(Vozilo vozilo) {
		this.vozilo = vozilo;
	}
	
	
	
	

	

}
