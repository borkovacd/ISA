package com.ftn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.model.rentacar.RentACar;

public interface RentCarRepository extends JpaRepository<RentACar, Long>{

}

