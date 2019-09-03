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

import com.ftn.dto.RezervacijaVozilaDTO;
import com.ftn.model.rentacar.RezervacijaVozila;
import com.ftn.service.RezervacijaVozilaService;

@RestController
@RequestMapping(value = "/api/voziloReservation")
public class RezervacijaVozilaController 
{
	@Autowired
	private RezervacijaVozilaService rezVozService ;
	
	@PostMapping("/voziloReservation/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<RezervacijaVozila> voziloReservation(@RequestBody RezervacijaVozilaDTO rezervacijaDTO, @PathVariable Long id) 
	{
		RezervacijaVozila rezervacija = rezVozService.createReservationRent(rezervacijaDTO, id);
		return new ResponseEntity<RezervacijaVozila>(rezervacija, HttpStatus.CREATED);
	}
}
