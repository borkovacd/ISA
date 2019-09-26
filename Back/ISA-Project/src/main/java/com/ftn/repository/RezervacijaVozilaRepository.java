package com.ftn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.model.rentacar.RezervacijaVozila;

@Repository
public interface RezervacijaVozilaRepository extends JpaRepository<RezervacijaVozila, Long> 
{
	List<RezervacijaVozila> findByKorisnikId(Long idKorisnik);
}
