package isa.proj.model;

import java.util.ArrayList;

public class Aviokompanija {
	
	private String nazivAK;
	private String adresaAK;
	private String opisAK;
	private ArrayList<Integer> oceneAK;
	private Double  prosecnaOcenaAK ;
	
	private ArrayList<Let> letovi;
	private ArrayList<Destinacija> destinacije ;
	private ArrayList<Aviokarta> aviokarte ;
	private ArrayList<SedisteAvion> sedistaAviona ;
	private CenovnikAviokompanija cenovnikAviokompanija ;
	private Prtljag prtljag ;
	
	private Prihod prihodAK ;

}
