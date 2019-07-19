package com.ftn.model.hotels;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.ftn.model.Korisnik;

@Entity
public class Hotel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String naziv;

	private String adresa;

	private String opis;
	
	@OneToOne(fetch = FetchType.EAGER)
	private Korisnik korisnik; //administrator hotela

}
