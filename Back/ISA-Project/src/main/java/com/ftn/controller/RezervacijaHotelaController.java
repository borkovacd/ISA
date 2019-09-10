package com.ftn.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

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
import com.ftn.model.rentacar.RezervacijaVozila;
import com.ftn.repository.RezervacijaHotelaRepository;
import com.ftn.service.RezervacijaHotelaService;
import com.ftn.service.UserService;

@RestController
@Transactional
@RequestMapping(value = "/api/hotelReservation")
public class RezervacijaHotelaController {
	
	@Autowired
	private RezervacijaHotelaService rezervacijaHotelaService;
	
	@Autowired
	private RezervacijaHotelaRepository rezHotelRepository ;
	
	@Autowired 
	private UserService userService ;
	
	@PostMapping("/create/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<RezervacijaHotela> createReservation(@RequestBody RezervacijaSobaDTO rezervacijaDTO, @PathVariable Long id) {
		RezervacijaHotela rezervacija = rezervacijaHotelaService.createReservation(rezervacijaDTO, id);
		return new ResponseEntity<RezervacijaHotela>(rezervacija, HttpStatus.CREATED);
	}
	
	@PostMapping("/create")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<RezervacijaHotela> createReservation(@RequestBody RezervacijaSobaDTO rezervacijaDTO) {
		RezervacijaHotela rezervacija = rezervacijaHotelaService.createReservation(rezervacijaDTO, userService.getCurrentUser().getId());
		return new ResponseEntity<RezervacijaHotela>(rezervacija, HttpStatus.CREATED);
	}
	
	@PostMapping("/createFast/{id}/{idRezervacijeLeta}/{idHotela}/{idRoom}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<RezervacijaHotela> createOrChangeFastHotelReservation(@PathVariable Long id, @PathVariable Long idRezervacijeLeta, @PathVariable Long idHotela, @PathVariable Long idRoom) {
		RezervacijaHotela rezervacija = rezervacijaHotelaService.createOrChangeFastHotelReservation(id, idRezervacijeLeta, idHotela, idRoom);
		return new ResponseEntity<RezervacijaHotela>(rezervacija, HttpStatus.CREATED);
	}
	
	@PostMapping("/createFast/{idRezervacijeLeta}/{idHotela}/{idRoom}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<RezervacijaHotela> createOrChangeFastHotelReservation(@PathVariable Long idRezervacijeLeta, @PathVariable Long idHotela, @PathVariable Long idRoom) {
		RezervacijaHotela rezervacija = rezervacijaHotelaService.createOrChangeFastHotelReservation(userService.getCurrentUser().getId(), idRezervacijeLeta, idHotela, idRoom);
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
	
	@PostMapping("/otkaziRezervacijuHotela/{idRezervacije}")
	@CrossOrigin(origins = "http://localhost:4200")
	public boolean otkaziRezervacijuHotela(@PathVariable Long idRezervacije) 
	{
		RezervacijaHotela rezervacija = rezHotelRepository.getOne(idRezervacije);
		LocalDate date = LocalDate.now().plusDays(3);

		if (date.isBefore(rezervacija.getDatumPocetka()) || date.equals(rezervacija.getDatumPocetka())) 
		{
			System.out.println("Uspesno otkazana rezervacija");
			rezHotelRepository.deleteById(idRezervacije);
			return true;
		}

		else {
			System.out.println("Nije moguce otkazati rezervaciju!");
			return false;

		}
	}
	
	// vraca listu rezervacija korisnika
		@GetMapping("/listaHotelRezervacijaKorisnik")
		@CrossOrigin(origins = "http://localhost:4200")
		public ResponseEntity<List<RezervacijaHotela>> listaHotelRezervacijaKorisnik()
		{
			ArrayList<RezervacijaHotela> reservations = rezervacijaHotelaService.listaHotelRezervacijaKorisnik(userService.getCurrentUser().getId());
			return new ResponseEntity<List<RezervacijaHotela>>(reservations, HttpStatus.OK);
			
		}

}
