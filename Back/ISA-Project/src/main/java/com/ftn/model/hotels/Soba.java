package com.ftn.model.hotels;

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

	private int kapacitet;
	
	private int sprat;
	
	private String tipSobe;

	private boolean imaBalkon = false;

	private boolean rezervisana = false;
	
	//private double cena; //Mozda ce mi trebati
	
	private boolean obrisana = false; //obezbediti logicno brisanje
	
	//private boolean aktivna = false; //Ideja da soba bude aktivna tek kada se doda njena cena
	
	public Soba() {
		
	}
	
	public Soba(Hotel hotel, int kapacitet, int sprat, String tipSobe, boolean imaBalkon, boolean rezervisana,
			boolean obrisana) {
		super();
		this.hotel = hotel;
		this.kapacitet = kapacitet;
		this.sprat = sprat;
		this.tipSobe = tipSobe;
		this.imaBalkon = imaBalkon;
		this.rezervisana = rezervisana;
		this.obrisana = obrisana;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public int getKapacitet() {
		return kapacitet;
	}

	public void setKapacitet(int kapacitet) {
		this.kapacitet = kapacitet;
	}

	public int getSprat() {
		return sprat;
	}

	public void setSprat(int sprat) {
		this.sprat = sprat;
	}

	public String getTipSobe() {
		return tipSobe;
	}

	public void setTipSobe(String tipSobe) {
		this.tipSobe = tipSobe;
	}

	public boolean isImaBalkon() {
		return imaBalkon;
	}

	public void setImaBalkon(boolean imaBalkon) {
		this.imaBalkon = imaBalkon;
	}

	public boolean isRezervisana() {
		return rezervisana;
	}

	public void setRezervisana(boolean rezervisana) {
		this.rezervisana = rezervisana;
	}

	public boolean isObrisana() {
		return obrisana;
	}

	public void setObrisana(boolean obrisana) {
		this.obrisana = obrisana;
	}


}
