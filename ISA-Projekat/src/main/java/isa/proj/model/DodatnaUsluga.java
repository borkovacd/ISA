package isa.proj.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

//Dodatna usluga koju hotel nudi
@Entity
public class DodatnaUsluga {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "naziv_du")
	private String nazivDodatneUsluge;
	
	@Column(name = "cena_du")
	private Double cenaDodatneUsluge;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Hotel hotel;
	
	public DodatnaUsluga() {
		
	}
	
	public DodatnaUsluga(Long id, String nazivDodatneUsluge, Double cenaDodatneUsluge, Hotel hotel) {
		super();
		this.id = id;
		this.nazivDodatneUsluge = nazivDodatneUsluge;
		this.cenaDodatneUsluge = cenaDodatneUsluge;
		this.hotel = hotel;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNazivDodatneUsluge() {
		return nazivDodatneUsluge;
	}

	public void setNazivDodatneUsluge(String nazivDodatneUsluge) {
		this.nazivDodatneUsluge = nazivDodatneUsluge;
	}

	public Double getCenaDodatneUsluge() {
		return cenaDodatneUsluge;
	}

	public void setCenaDodatneUsluge(Double cenaDodatneUsluge) {
		this.cenaDodatneUsluge = cenaDodatneUsluge;
	}
	
	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	
	
	
	

}
