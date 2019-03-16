package isa.proj.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Grad 
{
	@Id
	@GeneratedValue
	private Integer id ;
	
	@Column(nullable = false, unique = true)
	private String naziv;

	
	
	public Grad() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Grad(String naziv) {
		super();
		this.naziv = naziv;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
