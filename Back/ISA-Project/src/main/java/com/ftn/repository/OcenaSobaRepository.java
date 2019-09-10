package com.ftn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.model.hotels.OcenaSoba;
import com.ftn.model.rentacar.OcenaVozilo;

public interface OcenaSobaRepository extends JpaRepository<OcenaSoba, Long>{
	
	List<OcenaSoba> findBySobaId(Long id) ; // vraca listu ocena za jedno vozilo
	List<OcenaSoba> findByUserId(Long id) ; // vraca listu ocena tog korisnika
	OcenaSoba findOneById(Long id); // vraca ocenu vozila na osnovu id-ja


}
