package isa.proj.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Adresa 
{
	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToOne(optional = false)
	private Grad grad;
	
	@Column(nullable = false)
	private String ulica;
	
	@Column(nullable = false)
	private String broj;

	public Adresa() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Adresa(Grad grad, String ulica, String broj) {
		super();
		this.grad = grad;
		this.ulica = ulica;
		this.broj = broj;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Grad getGrad() {
		return grad;
	}

	public void setGrad(Grad grad) {
		this.grad = grad;
	}

	public String getUlica() {
		return ulica;
	}

	public void setUlica(String ulica) {
		this.ulica = ulica;
	}

	public String getBroj() {
		return broj;
	}

	public void setBroj(String broj) {
		this.broj = broj;
	}
	
	
	
}
