package isa.proj.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Entity
public class AdministratorHotela extends Korisnik {
	
	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	private Hotel hotel;

	public AdministratorHotela() {
		super();
		this.hotel = null;
	}
	
	public AdministratorHotela(Integer idKorisnika, String korisnickoIme, String lozinka, String ime, String prezime, String telefon, String email,
			String uloga, Hotel hotel) {
		super();
		this.hotel = hotel;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	
	
	
	

}
