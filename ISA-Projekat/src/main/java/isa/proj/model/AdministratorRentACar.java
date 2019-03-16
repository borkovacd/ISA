package isa.proj.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Entity
public class AdministratorRentACar extends Korisnik 
{
	
	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	private RentACarServis rentACar ;

	// konstruktori 

	
	public AdministratorRentACar(RentACarServis rentACar) {
		super();
		this.rentACar = rentACar;
	}

	public AdministratorRentACar() {
		super();
		this.rentACar = null ;
		// TODO Auto-generated constructor stub
	}

	public AdministratorRentACar(Integer idKorisnika, String korisnickoIme, String lozinka, String ime, String prezime,
			String telefon, String email, String uloga, RentACarServis rentACar) 
	{
		super(idKorisnika, korisnickoIme, lozinka, ime, prezime, telefon, email, uloga);
		this.rentACar = rentACar ;
		// TODO Auto-generated constructor stub
	}

	// Geteri i seteri 

	
	public RentACarServis getRentACar() {
		return rentACar;
	}

	public void setRentACar(RentACarServis rentACar) {
		this.rentACar = rentACar;
	}

	
	
	

	
	
	
	
}
