package isa.proj.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Vozila")
public class Vozilo 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "voziloID")
	private Integer voziloID ;
	
	@ManyToOne
	@JoinColumn(name="rentACarID")
	private RentACarServis rentACarServis ;
	
	@NotNull
	@Column(name = "voziloRegistracija")
	private String registracija ;
	
	@NotNull
	@Column(name = "voziloMarka")
	private String marka ;
	
	@NotNull
	@Column(name = "voziloModel")
	private String model ;
	
	@Column(name = "voziloGodiste")
	private String godiste ;
	
	@Column(name = "voziloTip")
	private String tip ;
	
	@Column(name = "voziloBrojSedista")
	private Integer brojSedista;
	
	@Column(name = "voziloProsecnaOcena")
	private Double prosecnaOcena;
	
	@Column(name = "voziloCena")
	private Double cena ;
	
	

}
