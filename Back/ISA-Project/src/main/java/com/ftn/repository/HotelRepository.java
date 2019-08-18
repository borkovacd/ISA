package com.ftn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.model.hotels.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long>{

}
