package com.ftn.model.rentacar;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CenovnikRentACar 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDate pocetakVazenja;
	
	private LocalDate prestanakVazenja;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private RentACar rentACar ;

	public CenovnikRentACar() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CenovnikRentACar(LocalDate pocetakVazenja, LocalDate prestanakVazenja) {
		super();
		this.pocetakVazenja = pocetakVazenja;
		this.prestanakVazenja = prestanakVazenja;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getPocetakVazenja() {
		return pocetakVazenja;
	}

	public void setPocetakVazenja(LocalDate pocetakVazenja) {
		this.pocetakVazenja = pocetakVazenja;
	}

	public LocalDate getPrestanakVazenja() {
		return prestanakVazenja;
	}

	public void setPrestanakVazenja(LocalDate prestanakVazenja) {
		this.prestanakVazenja = prestanakVazenja;
	}

	public RentACar getRentACar() {
		return rentACar;
	}

	public void setRentACar(RentACar rentACar) {
		this.rentACar = rentACar;
	}
	
	
}
