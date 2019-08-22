package com.ftn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	
	/******************/

}
