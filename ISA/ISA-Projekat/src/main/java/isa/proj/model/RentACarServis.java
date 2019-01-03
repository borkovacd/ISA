package isa.proj.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "RentACarServis")
public class RentACarServis 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	
	//private ArrayList<Filijala> listaLokacijaFilijala ;
	
	private ArrayList<Integer> oceneServisa;
	private Double  prosecnaOcenaServisa  ;
	
	private ArrayList<Integer> oceneVozila; // pojedinacne ocene Vozila staviti u klasu Vozilo, ne ovde
	private Double  prosecnaOcenaVozila  ;
	
	//private Prihod prihodRentACarServis;

	
}
