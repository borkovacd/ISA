package com.ftn.model.hotels;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class StavkaCenovnikaHotela {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDate pocetakVazenja;
	
	private LocalDate prestanakVazenja;
	
	@ManyToOne
	private Soba soba;
	
	private Double cena;

}
