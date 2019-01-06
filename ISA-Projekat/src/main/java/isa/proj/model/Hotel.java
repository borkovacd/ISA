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
	private Integer id;
	
	@Column(name = "naziv_hotela", nullable = false)
	private String nazivHotela;
	
	@Column(name = "adresa_hotela", nullable = false)
	private String adresaHotela; //+ dodatno prikaz lokacije koriscenjem Google mapa

	@Column(name = "opis_hotela")
	private String opisHotela;
	
	@OneToOne
	private CenovnikHotela cenovnikHotela;
	
	@OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<DodatnaUsluga> dodatneUsluge = new HashSet<DodatnaUsluga> ();
	
	@OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Soba> sobe = new HashSet<Soba>();
	
	@Column(name = "prosecna_ocena")
	private Double  prosecnaOcenaHotela;
	
	private ArrayList<Integer> oceneHotela;
	private ArrayList<Double> prosecneOceneSoba;
	
	@OneToOne
	private PrihodHotela prihodHotela ;
	
	public Hotel() {
		
	}

	public Hotel(Integer id, String nazivHotela, String adresaHotela, String opisHotela, CenovnikHotela cenovnikHotela,
			Set<DodatnaUsluga> dodatneUsluge, Set<Soba> sobe, Double prosecnaOcenaHotela,
			ArrayList<Integer> oceneHotela, ArrayList<Double> prosecneOceneSoba, PrihodHotela prihodHotela) {
		super();
		this.id = id;
		this.nazivHotela = nazivHotela;
		this.adresaHotela = adresaHotela;
		this.opisHotela = opisHotela;
		this.cenovnikHotela = cenovnikHotela;
		this.dodatneUsluge = dodatneUsluge;
		this.sobe = sobe;
		this.prosecnaOcenaHotela = prosecnaOcenaHotela;
		this.oceneHotela = oceneHotela;
		this.prosecneOceneSoba = prosecneOceneSoba;
		this.prihodHotela = prihodHotela;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNazivHotela() {
		return nazivHotela;
	}

	public void setNazivHotela(String nazivHotela) {
		this.nazivHotela = nazivHotela;
	}

	public String getAdresaHotela() {
		return adresaHotela;
	}

	public void setAdresaHotela(String adresaHotela) {
		this.adresaHotela = adresaHotela;
	}

	public String getOpisHotela() {
		return opisHotela;
	}

	public void setOpisHotela(String opisHotela) {
		this.opisHotela = opisHotela;
	}

	public CenovnikHotela getCenovnikHotela() {
		return cenovnikHotela;
	}

	public void setCenovnikHotela(CenovnikHotela cenovnikHotela) {
		this.cenovnikHotela = cenovnikHotela;
	}

	public Set<DodatnaUsluga> getDodatneUsluge() {
		return dodatneUsluge;
	}

	public void setDodatneUsluge(Set<DodatnaUsluga> dodatneUsluge) {
		this.dodatneUsluge = dodatneUsluge;
	}

	public Set<Soba> getSobe() {
		return sobe;
	}

	public void setSobe(Set<Soba> sobe) {
		this.sobe = sobe;
	}

	public Double getProsecnaOcenaHotela() {
		return prosecnaOcenaHotela;
	}

	public void setProsecnaOcenaHotela(Double prosecnaOcenaHotela) {
		this.prosecnaOcenaHotela = prosecnaOcenaHotela;
	}

	public ArrayList<Integer> getOceneHotela() {
		return oceneHotela;
	}

	public void setOceneHotela(ArrayList<Integer> oceneHotela) {
		this.oceneHotela = oceneHotela;
	}

	public ArrayList<Double> getProsecneOceneSoba() {
		return prosecneOceneSoba;
	}

	public void setProsecneOceneSoba(ArrayList<Double> prosecneOceneSoba) {
		this.prosecneOceneSoba = prosecneOceneSoba;
	}

	public PrihodHotela getPrihodHotela() {
		return prihodHotela;
	}

	public void setPrihodHotela(PrihodHotela prihodHotela) {
		this.prihodHotela = prihodHotela;
	}
	
	
}
