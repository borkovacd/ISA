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
import javax.validation.constraints.NotNull;

import com.ftn.model.Korisnik;

@Entity
public class RezervacijaHotela {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDate datumPocetka;

	private LocalDate datumKraja;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<Soba> sobe;

	@ManyToOne(fetch = FetchType.EAGER)
	private Korisnik korisnik;
	
	private double cena;

}
