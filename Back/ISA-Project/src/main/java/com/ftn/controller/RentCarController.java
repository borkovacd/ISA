package com.ftn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.dto.RentCarDTO;
import com.ftn.service.RentACarService;

@RestController
@RequestMapping(value = "/api/rentCar")
public class RentCarController {
	
	@Autowired
	private RentACarService rentCarService;
	
	@PostMapping("/registerRentCar")
	@CrossOrigin(origins = "http://localhost:4200")
	public void registerRentCar(@RequestBody RentCarDTO rentCarDTO) {
		boolean rentCarRegistration = rentCarService.registerRentCar(rentCarDTO);
		/*if(rentCarRegistration) 
			return new ResponseEntity<>("Uspesno registrovanje", HttpStatus.OK);
		else 
			return new ResponseEntity<>("Neuspesno registrovanje", HttpStatus.OK);*/
	}


}
