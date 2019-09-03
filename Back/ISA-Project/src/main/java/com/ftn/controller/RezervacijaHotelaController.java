package com.ftn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ftn.dto.RezervacijaSobaDTO;
import com.ftn.model.hotels.RezervacijaHotela;
import com.ftn.service.RezervacijaHotelaService;

@RestController
@RequestMapping(value = "/api/hotelReservation")
public class RezervacijaHotelaController {
	
	@Autowired
	private RezervacijaHotelaService rezervacijaHotelaService;
	
	@PostMapping("/hotelReservation/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<RezervacijaHotela> createReservation(@RequestBody RezervacijaSobaDTO rezervacijaDTO, @PathVariable Long id) {
		RezervacijaHotela rezervacija = rezervacijaHotelaService.createReservation(rezervacijaDTO, id);
		return new ResponseEntity<RezervacijaHotela>(rezervacija, HttpStatus.CREATED);
	}
}
