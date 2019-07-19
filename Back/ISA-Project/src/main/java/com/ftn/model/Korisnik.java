package com.ftn.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Korisnik {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String ime;
	
	private String prezime;
	
	private String korisnickoIme;
	
	private String lozinka;

	private String email;
	
	private String telefon;

	private String uloga; 
	
	private boolean prvoLogovanje = true;

}
