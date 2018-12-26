package isa.proj.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Hotel {
	
	private String nazivH;
	private String adresaH;
	private String opisH;
	private String ocenaH;
	private CenovnikHotel cenovnikHotel ;
	
	private HashMap<String, Soba> sobe ;
	private ArrayList<Integer> oceneHotela;
	private Double  prosecnaOcenaHotela  ;
	
	private ArrayList<Integer> oceneSoba;
	private Double  prosecnaOcenaSoba  ;
	
	private Prihod prihodHotela ;

}
