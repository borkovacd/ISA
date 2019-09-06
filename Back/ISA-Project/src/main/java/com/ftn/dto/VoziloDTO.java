package com.ftn.dto;

public class VoziloDTO 
{
	private String naziv ;
	private String marka;
	private String model;
	private int godinaProizvodnje;
	private int brojSedista;
	
	private String tip;
	
	public VoziloDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public VoziloDTO(String naziv, String marka, String model, int godinaProizvodnje, int brojSedista, String tip) {
		super();
		this.naziv = naziv;
		this.marka = marka;
		this.model = model;
		this.godinaProizvodnje = godinaProizvodnje;
		this.brojSedista = brojSedista;
		this.tip = tip;
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

	
	
	
}
