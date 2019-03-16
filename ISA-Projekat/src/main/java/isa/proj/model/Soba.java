package isa.proj.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Soba {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="oznaka_sobe")
	private String oznakaSobe;
	
	@Column(name="rezervisana_soba")
	private Boolean rezervisana;
	
	@Column(name="cena_nocenja")
	private Double cenaNocenja;
	
	@ElementCollection
	private List<Integer> oceneSobe;
	
	private Double prosecnaOcenaSobe;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Hotel hotel;

	public Soba () {
		
	}
	
	public Soba(Long id, String oznakaSobe, Boolean rezervisana, Double cenaNocenja, List<Integer> oceneSobe,
			Double prosecnaOcenaSobe, Hotel hotel) {
		super();
		this.id = id;
		this.oznakaSobe = oznakaSobe;
		this.rezervisana = rezervisana;
		this.cenaNocenja = cenaNocenja;
		this.oceneSobe = oceneSobe;
		this.prosecnaOcenaSobe = prosecnaOcenaSobe;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOznakaSobe() {
		return oznakaSobe;
	}

	public void setOznakaSobe(String oznakaSobe) {
		this.oznakaSobe = oznakaSobe;
	}

	public Boolean getRezervisana() {
		return rezervisana;
	}

	public void setRezervisana(Boolean rezervisana) {
		this.rezervisana = rezervisana;
	}

	public Double getCenaNocenja() {
		return cenaNocenja;
	}

	public void setCenaNocenja(Double cenaNocenja) {
		this.cenaNocenja = cenaNocenja;
	}

	public List<Integer> getOceneSobe() {
		return oceneSobe;
	}

	public void setOceneSobe(List<Integer> oceneSobe) {
		this.oceneSobe = oceneSobe;
	}

	public Double getProsecnaOcenaSobe() {
		return prosecnaOcenaSobe;
	}

	public void setProsecnaOcenaSobe(Double prosecnaOcenaSobe) {
		this.prosecnaOcenaSobe = prosecnaOcenaSobe;
	}
	
	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	
	
	
	
	
	

}
