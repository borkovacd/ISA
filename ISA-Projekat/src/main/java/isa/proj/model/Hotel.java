package isa.proj.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Hotel {
	
	private String nazivHotela;
	private String adresaHotela; //+ dodatno prikaz lokacije koriscenjem Google mapa
	private String opisHotela;
	private CenovnikHotela cenovnikHotela;
	private HashMap<String, Soba> sobe;
	private Double  prosecnaOcenaHotela;
	private ArrayList<Integer> oceneHotela;
	private ArrayList<Double> prosecneOceneSoba;
	private Prihod prihodHotela ;

}
