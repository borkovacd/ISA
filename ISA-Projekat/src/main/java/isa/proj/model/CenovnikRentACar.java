package isa.proj.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Entity
public class CenovnikRentACar 
{
	@Id
	@GeneratedValue
	private Long id ;
	
	@OneToOne(fetch = FetchType.LAZY)
    @MapsId
	private RentACarServis rentACar;

	public CenovnikRentACar() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
