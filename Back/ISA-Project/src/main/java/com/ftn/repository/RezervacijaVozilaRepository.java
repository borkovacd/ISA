package com.ftn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.model.rentacar.RezervacijaVozila;

@Repository
public interface RezervacijaVozilaRepository extends JpaRepository<RezervacijaVozila, Long> 
{

}
