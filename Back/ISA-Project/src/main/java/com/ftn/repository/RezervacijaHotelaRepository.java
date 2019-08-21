package com.ftn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.model.hotels.RezervacijaHotela;

public interface RezervacijaHotelaRepository extends JpaRepository<RezervacijaHotela, Long> {

}