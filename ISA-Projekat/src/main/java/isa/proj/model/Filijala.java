package isa.proj.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Filijala 
{
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "oznakaFilijala")
	private String oznakaFilijala ;
	
	@Column(name = "nazivFilijala")
	private String nazivFilijala ;
	
	@OneToOne
	private Adresa adresa;
	
	/*
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private RentACarServis rentACar;
	*/
	
}
