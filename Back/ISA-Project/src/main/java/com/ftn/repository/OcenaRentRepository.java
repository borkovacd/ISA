package com.ftn.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.model.hotels.OcenaSoba;
import com.ftn.model.rentacar.OcenaRentACar;
import com.ftn.model.rentacar.OcenaVozilo;

import java.util.List;

import com.ftn.model.hotels.OcenaSoba;

public interface OcenaRentRepository extends JpaRepository <OcenaRentACar, Long> {
	
	List<OcenaRentACar> findByUserId(Long id) ; // vraca listu ocena tog korisnika
	OcenaRentACar findOneById(Long id); // vraca ocenu vozila na osnovu id-ja

}
