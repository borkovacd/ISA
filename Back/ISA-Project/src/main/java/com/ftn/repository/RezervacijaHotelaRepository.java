package com.ftn.repository;

import java.util.Optional;

import javax.persistence.LockModeType;

import org.hibernate.annotations.OptimisticLocking;
import org.hibernate.validator.constraints.pl.PESEL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import com.ftn.model.hotels.RezervacijaHotela;


public interface RezervacijaHotelaRepository extends JpaRepository<RezervacijaHotela, Long> {


}