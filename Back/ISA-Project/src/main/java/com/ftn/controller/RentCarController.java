package com.ftn.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.dto.RentCarDTO;
import com.ftn.model.rentacar.RentACar;
import com.ftn.service.RentACarService;

@RestController
@RequestMapping(value = "/api/rentCar")
public class RentCarController {
	
	@Autowired
	private RentACarService rentCarService;
	
	/**** Borkovac ******/
	
	@PostMapping("/registerRentCar")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<RentACar> registerRentCar(@RequestBody RentCarDTO rentCarDTO) {
		RentACar rentACar = rentCarService.registerRentCar(rentCarDTO);
		return new ResponseEntity<RentACar>(rentACar, HttpStatus.OK);
	}
	
	/******************/
	
	/***** Olga *******/
	
	// izmeni Rent-a-Car servis
	@PutMapping("/izmeniRent/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<RentACar> izmeniRent( @PathVariable Long id, @RequestBody RentCarDTO dto) {
		RentACar rent = rentCarService.editRent(id, dto);
		return new ResponseEntity<RentACar>(rent, HttpStatus.OK);
	}
	
	// vraca rent-a-car servise administratora
	@GetMapping("/getRentsByAdministrator/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<List<RentACar>> getRentsByAdministrator(@PathVariable Long id)
	{
		ArrayList<RentACar> rents = rentCarService.getRentsByAdministrator(id);
		return new ResponseEntity<List<RentACar>>(rents, HttpStatus.OK);
		
	}
	
	// vraca servis na osnovu id-ja
	@GetMapping("/getRent/{idRent}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<RentACar> getRent(@PathVariable Long idRent) 
	{
		RentACar rent = rentCarService.findByRentACarId(idRent);
		if (rent == null) // ne postoji sa tim id-jem
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<RentACar>(rent, HttpStatus.OK);
	}
	
	
	/******************/

}
