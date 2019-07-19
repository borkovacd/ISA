package com.ftn.model.hotels;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.ftn.model.Korisnik;

@Entity
public class Ocena {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private int ocena;;
	
	@ManyToOne
	private Korisnik korisnik; //korisnik koji je ocenio
	
	@ManyToOne
	private Soba soba;
	
}
