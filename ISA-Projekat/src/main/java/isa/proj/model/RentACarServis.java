package isa.proj.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "RentACarServis")
public class RentACarServis 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "rentACarID")
	private Integer id ;
	
	@NotNull
	@Column(name = "rentACarNaziv")
	private String naziv ;
	
	@NotNull
	@Column(name = "rentACarAdresa")
	private String adresa ;
	
	@Column(name = "rentACarOpis")
	private String opis ;

	@OneToMany(mappedBy="rentACarServis")
	private Collection<Vozilo> spisakVozila ;
	
	@OneToOne
	private AdministratorRentACar adminRentACar ;
	
	@OneToOne
	private CenovnikRentACar cenovnikRentACar ;
	
	/*
	@OneToMany(mappedBy="rentACarServis", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Filijala> filijale = new HashSet<Filijala>() ;
	*/
	
	@ElementCollection
	private List<Integer> oceneServisa;
	
	@Column(name = "prosecnaOcena")
	private Double  prosecnaOcenaServisa  ;
	
	@ElementCollection
	private List<Integer> oceneVozila; // pojedinacne ocene Vozila staviti u klasu Vozilo, ne ovde
	
	//private ArrayList<Double>  prosecneOcenaVozila  ;
	
	@OneToOne
	private PrihodRentACar prihodRentACar ;

	
}
