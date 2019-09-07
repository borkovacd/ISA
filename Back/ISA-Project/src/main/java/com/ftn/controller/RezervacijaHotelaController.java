package com.ftn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.dto.RezervacijaDodatnihUslugaDTO;
import com.ftn.dto.RezervacijaSobaDTO;
import com.ftn.model.hotels.RezervacijaHotela;
import com.ftn.model.hotels.Soba;
import com.ftn.service.RezervacijaHotelaService;

@RestController
@RequestMapping(value = "/api/hotelReservation")
public class RezervacijaHotelaController {
	
	@Autowired
	private RezervacijaHotelaService rezervacijaHotelaService;
	
	@PostMapping("/create/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<RezervacijaHotela> createReservation(@RequestBody RezervacijaSobaDTO rezervacijaDTO, @PathVariable Long id) {
		RezervacijaHotela rezervacija = rezervacijaHotelaService.createReservation(rezervacijaDTO, id);
		return new ResponseEntity<RezervacijaHotela>(rezervacija, HttpStatus.CREATED);
	}
	
	@PostMapping("/createFast/{id}/{idRezervacijeLeta}/{idHotela}/{idRoom}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<RezervacijaHotela> createOrChangeFastHotelReservation(@PathVariable Long id, @PathVariable Long idRezervacijeLeta, @PathVariable Long idHotela, @PathVariable Long idRoom) {
		RezervacijaHotela rezervacija = rezervacijaHotelaService.createOrChangeFastHotelReservation(id, idRezervacijeLeta, idHotela, idRoom);
		return new ResponseEntity<RezervacijaHotela>(rezervacija, HttpStatus.CREATED);
	}
	
	@PostMapping("/addToRes/additional/{idRezervacije}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<RezervacijaHotela> addToReservation(@RequestBody RezervacijaDodatnihUslugaDTO dodatnaRezervacijaDTO, @PathVariable Long idRezervacije) {
		RezervacijaHotela rezervacija = rezervacijaHotelaService.addToReservation(dodatnaRezervacijaDTO, idRezervacije);
		return new ResponseEntity<RezervacijaHotela>(rezervacija, HttpStatus.CREATED);
	}
	

	@GetMapping("/getReservation/{idRezervacije}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<RezervacijaHotela> getReservation(@PathVariable Long idRezervacije) {
		RezervacijaHotela rez = rezervacijaHotelaService.getReservation(idRezervacije);
		if (rez == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(rez, HttpStatus.OK);
	}

}
