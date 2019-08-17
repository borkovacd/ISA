package com.ftn.model.rentacar;

import java.util.ArrayList;

import javax.persistence.*;

// KORAK 3 tacka 2.10 - vozilo 
@Entity
public class Vozilo 
{
	@Id
	@GeneratedValue
	private long voziloId ;
	
	private double cena ;
	private String naziv ;
	private String marka;
	private String model;
	private int godinaProizvodnje;
	private int brojSedista;
	
	private String tip;
	
	// za svako vozilo vezana je lista ocena
	private ArrayList<Integer> ocena;
	
	// rezervisano - DA / NE
	private boolean rezervisano;
	
	// rentACar kom vozilo pripada
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="rentACarId", referencedColumnName="rentACarId")
	private RentACar rentACar;

	// Inspo
	
	private double prosecnaOcena;
	private double brojOcena ;
	private double sumaOcena ;
	

	public Vozilo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Vozilo(long voziloId, double cena, String naziv, String marka, String model, int godinaProizvodnje,
			int brojSedista, String tip) {
		super();
		this.voziloId = voziloId;
		this.cena = cena;
		this.naziv = naziv;
		this.marka = marka;
		this.model = model;
		this.godinaProizvodnje = godinaProizvodnje;
		this.brojSedista = brojSedista;
		this.tip = tip;
	}

	public long getVoziloId() {
		return voziloId;
	}

	public void setVoziloId(long voziloId) {
		this.voziloId = voziloId;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getMarka() {
		return marka;
	}

	public void setMarka(String marka) {
		this.marka = marka;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getGodinaProizvodnje() {
		return godinaProizvodnje;
	}

	public void setGodinaProizvodnje(int godinaProizvodnje) {
		this.godinaProizvodnje = godinaProizvodnje;
	}

	public int getBrojSedista() {
		return brojSedista;
	}

	public void setBrojSedista(int brojSedista) {
		this.brojSedista = brojSedista;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public ArrayList<Integer> getOcena() {
		return ocena;
	}

	public void setOcena(ArrayList<Integer> ocena) {
		this.ocena = ocena;
	}

	public boolean isRezervisano() {
		return rezervisano;
	}

	public void setRezervisano(boolean rezervisano) {
		this.rezervisano = rezervisano;
	}

	public RentACar getRentACar() {
		return rentACar;
	}

	public void setRentACar(RentACar rentACar) {
		this.rentACar = rentACar;
	}
	
	
	public double getProsecnaOcena() {
		return prosecnaOcena;
	}

	public void setProsecnaOcena(double prosecnaOcena) {
		this.prosecnaOcena = prosecnaOcena;
	}

	public double getBrojOcena() {
		return brojOcena;
	}

	public void setBrojOcena(double brojOcena) {
		this.brojOcena = brojOcena;
	}

	public double getSumaOcena() {
		return sumaOcena;
	}

	public void setSumaOcena(double sumaOcena) {
		this.sumaOcena = sumaOcena;
	}

	
	
	
}