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

import com.ftn.dto.HotelDTO;
import com.ftn.service.HotelService;


@RestController
@RequestMapping(value = "/api/hotel")
public class HotelController {
	
	@Autowired
	private HotelService hotelService;
	
	@PostMapping("/registerHotel")
	@CrossOrigin(origins = "http://localhost:4200")
	public void registerHotel(@RequestBody HotelDTO hotelDTO) throws Exception {
		boolean hotelRegistration = hotelService.registerHotel(hotelDTO);
		/*if(hotelRegistration) 
			return new ResponseEntity<>("Uspesno registrovanje", HttpStatus.OK);
		else 
			return new ResponseEntity<>("Neuspesno registrovanje", HttpStatus.OK);*/
	}

}
