package com.ftn.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.dto.LokacijaDTO;
import com.ftn.model.rentacar.Lokacija;
import com.ftn.service.LokacijaService;
import com.ftn.service.RentACarService;

@RestController
@RequestMapping(value = "/api/filijala")
public class LokacijaController 
{
	@Autowired
	RentACarService rentService ;
	
	@Autowired
	LokacijaService lokService ;
	
	// Dodavanje filijale
		@PostMapping("/dodajFilijalu/{idRentACar}")
		@CrossOrigin(origins = "http://localhost:4200")
		public void dodajFilijalu(@RequestBody LokacijaDTO lokDTO, @PathVariable Long idRentACar) throws Exception 
		{
			Lokacija l = lokService.findByAdresa(lokDTO.getAdresa());
			
			if (l != null) // vec postoji filijala sa tim imenom
			{
				System.out.println("Vec postoji lokacija na unetoj adresi!");
				//return new ResponseEntity<Lokacija>(HttpStatus.BAD_REQUEST);
			}
			
			else
			{
				lokService.dodajFilijalu(lokDTO, idRentACar);
				//return new ResponseEntity<Lokacija>(v, HttpStatus.OK);
			}
			
		}
	
		// Vraca sve filijale tog rent-a-car servisa
		@GetMapping("/getFilijaleRentACar/{idRentACar}")
		@CrossOrigin(origins = "http://localhost:4200")
		public ResponseEntity<ArrayList<Lokacija>> getFilijaleRentACar(@PathVariable Long idRentACar) throws Exception 
		{

			ArrayList<Lokacija> lokacije = lokService.getFilijaleRentACar(idRentACar);

			return new ResponseEntity<>(lokacije, HttpStatus.OK);
		}
		
		// vraca jednu filijalu
		@GetMapping("/vratiJednuFilijalu/{idRentACar}/{idLok}")
		@CrossOrigin(origins = "http://localhost:4200")
		public ResponseEntity<Lokacija> vratiJednuFilijalu(@PathVariable Long idRentACar,@PathVariable Long idLok) throws Exception 
		{		
			Lokacija l = lokService.findById(idLok);
			if (l == null) // ukoliko ne postoji ta filijala
			{
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			else
			{
				return new ResponseEntity<>(l, HttpStatus.OK);
			}

		}
		
		// izmena filijale
		@PutMapping("/izmeniFilijalu/{idRentACar}/{idLok}")
		@CrossOrigin(origins = "http://localhost:4200")
		public ResponseEntity<Lokacija> izmeniFilijalu(@PathVariable Long idRentACar,@PathVariable Long idLok,
				@RequestBody LokacijaDTO dto) throws Exception {

			Lokacija l = lokService.izmeniFilijalu(idRentACar, idLok, dto);
			
			if (l == null)
			{
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			
			else
			{
				return new ResponseEntity<>(l, HttpStatus.OK);
			}
			
		}

		@DeleteMapping("/obrisiFilijalu/{idRentACar}/{idLok}")
		@CrossOrigin(origins = "http://localhost:4200")
		public boolean obrisiFilijalu(@PathVariable Long idRentACar, @PathVariable Long idLok) throws Exception 
		{
			boolean response = lokService.obrisiFilijalu(idRentACar, idLok);
			return response; // TRUE - uspesno obrisano, FALSE - nije obrisano (nije pronadjeno)
		}
		
		@GetMapping("/checkIfReservedLokacija/{id}")
		@CrossOrigin(origins = "http://localhost:4200")
		public boolean checkIfReservedLokacija(@PathVariable Long id) {
			//Ako lokacija nije rezervisana, taken je FALSE
			//u suprotnom taken ima vrednost TRUE
			boolean taken = lokService.checkIfLokacijaIsReserved(id);
			return taken;
		}

	
}
