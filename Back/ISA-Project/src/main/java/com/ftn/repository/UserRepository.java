package com.ftn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.model.Korisnik;

public interface UserRepository extends JpaRepository<Korisnik, Long> {
	
}
