package com.ftn.repository;

import java.util.List;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ftn.model.rentacar.RentACar;
import com.ftn.model.rentacar.Vozilo;

@Repository
public interface VoziloRepository extends JpaRepository<Vozilo, Long> 
{
	public Vozilo save(Vozilo vozilo);
	public List<Vozilo> findAll() ; // sva vozila
	public List<Vozilo> findByRentACar(Long id); // sva vozila tog servisa
	
	public void deleteById(Long id); // Moze li ova jer nemam polje id??
	
	public void deleteByVoziloId(Long id);
	public void deleteByNaziv(String naziv);
	
	// vraca jedno vozilo na osnovu id-ja / naziva
	Vozilo findOneByVoziloId(Long id);
	Vozilo findOneByNaziv(String naziv) ;
	
	@Query("select vozilo from Vozilo vozilo where vozilo.voziloId = ?1")
	@Lock(LockModeType.PESSIMISTIC_READ)
	public Vozilo vratiVoziloPoId(long id);
	 
		
	// test
	public List<Vozilo> findByRentACarRentACarId(Long id); // sva vozila tog servisa

}
