package com.ftn.model.hotels;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Soba {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Hotel hotel;

	private int brojKreveta;
	
	private int sprat;
	
	private String tipSobe;

	private boolean rezervisana = false;
	
	private boolean obrisana = false; //obezbediti logicno brisanje
	


}
