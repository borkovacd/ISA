package isa.proj.model;

//Dodatna usluga koju hotel nudi
public class DodatnaUsluga {
	
	private Integer idDodatneUsluge;
	private String nazivDodatneUsluge;
	private Double cenaDodatneUsluge;
	private Hotel hotel;
	
	public DodatnaUsluga() {
		
	}
	
	public DodatnaUsluga(Integer idDodatneUsluge, String nazivDodatneUsluge, Double cenaDodatneUsluge, Hotel hotel) {
		super();
		this.idDodatneUsluge = idDodatneUsluge;
		this.nazivDodatneUsluge = nazivDodatneUsluge;
		this.cenaDodatneUsluge = cenaDodatneUsluge;
		this.hotel = hotel;
	}

	public Integer getIdDodatneUsluge() {
		return idDodatneUsluge;
	}

	public void setIdDodatneUsluge(Integer idDodatneUsluge) {
		this.idDodatneUsluge = idDodatneUsluge;
	}

	public String getNazivDodatneUsluge() {
		return nazivDodatneUsluge;
	}

	public void setNazivDodatneUsluge(String nazivDodatneUsluge) {
		this.nazivDodatneUsluge = nazivDodatneUsluge;
	}

	public Double getCenaDodatneUsluge() {
		return cenaDodatneUsluge;
	}

	public void setCenaDodatneUsluge(Double cenaDodatneUsluge) {
		this.cenaDodatneUsluge = cenaDodatneUsluge;
	}
	
	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	
	
	
	

}
