package isa.proj.model;

import java.util.ArrayList;

public class RentACarServis {
	
	private String naziv ;
	private String adresa ;
	private String opis ;
	private ArrayList<Vozilo> spisakVozila ;
	private ArrayList<Filijala> listaLokacijaFilijala ;
	
	private ArrayList<Integer> oceneServisa;
	private Double  prosecnaOcenaServisa  ;
	
	private ArrayList<Integer> oceneVozila; // pojedinacne ocene Vozila staviti u klasu Vozilo, ne ovde
	private Double  prosecnaOcenaVozila  ;
	
	private Prihod prihodRentACarServis;

	
}
