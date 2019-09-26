package com.ftn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.model.rentacar.Lokacija;

@Repository
public interface LokacijaRepository extends JpaRepository<Lokacija, Long>
{
	public Lokacija save(Lokacija lok);
	public List<Lokacija> findAll() ; // sve filijale
	public List<Lokacija> findByRentACar(Long id); // sve filijale tog servisa
	
	public void deleteById(Long id); 
	
	public void deleteByAdresa(String adresa);
	
	// vraca jednu lokaciju na osnovu id-ja / adrese
	Lokacija findOneById(Long id);
	Lokacija findOneByAdresa(String adresa) ;
	
	List<Lokacija> findByRentACarRentACarId(Long id); // sve filijale tog servisa

	
	
}
