package isa.proj.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
public class Hotel {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "naziv_hotela", nullable = false)
	private String nazivHotela;
	
	@Column(name = "adresa_hotela", nullable = false)
	private String adresaHotela; //+ dodatno prikaz lokacije koriscenjem Google mapa

	@Column(name = "opis_hotela")
	private String opisHotela;
	
	//private CenovnikHotela cenovnikHotela;
	
	@OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Soba> sobe = new HashSet<Soba>();
	
	@Column(name = "prosecna_ocena")
	private Double  prosecnaOcenaHotela;
	
	//private ArrayList<Integer> oceneHotela;
	
	//private ArrayList<Double> prosecneOceneSoba;
	
	//private Prihod prihodHotela ;
}
