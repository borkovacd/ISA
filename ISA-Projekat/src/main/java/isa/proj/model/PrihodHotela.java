package isa.proj.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Entity
public class PrihodHotela {

	@Id
	@GeneratedValue
	private Long id;
	
	private String pocetak;
	private String kraj;
	
	private Double kolicinaPrihod;
	
	@OneToOne(fetch = FetchType.LAZY)
    @MapsId
	private Hotel hotel;
	
	public PrihodHotela() {
		
	}

	public PrihodHotela(Long id, String pocetak, String kraj, Double kolicinaPrihod, Hotel hotel) {
		super();
		this.id = id;
		this.pocetak = pocetak;
		this.kraj = kraj;
		this.kolicinaPrihod = kolicinaPrihod;
		this.hotel = hotel;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPocetak() {
		return pocetak;
	}

	public void setPocetak(String pocetak) {
		this.pocetak = pocetak;
	}

	public String getKraj() {
		return kraj;
	}

	public void setKraj(String kraj) {
		this.kraj = kraj;
	}

	public Double getKolicinaPrihod() {
		return kolicinaPrihod;
	}

	public void setKolicinaPrihod(Double kolicinaPrihod) {
		this.kolicinaPrihod = kolicinaPrihod;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	
	
	
	
}
