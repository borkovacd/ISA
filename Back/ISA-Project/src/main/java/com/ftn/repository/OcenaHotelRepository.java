package com.ftn.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.model.hotels.OcenaHotel;
import com.ftn.model.hotels.OcenaSoba;
import com.ftn.model.rentacar.OcenaVozilo;

import java.util.List;

import com.ftn.model.hotels.OcenaSoba;

public interface OcenaHotelRepository extends JpaRepository<OcenaHotel, Long>{
	
	List<OcenaHotel> findByHotelId(Long id) ; // vraca listu ocena za jedno vozilo
	List<OcenaHotel> findByUserId(Long id) ; // vraca listu ocena tog korisnika
	OcenaHotel findOneById(Long id); // vraca ocenu vozila na osnovu id-ja
	
}
