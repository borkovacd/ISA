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

import com.ftn.dto.HotelDTO;
import com.ftn.model.hotels.Hotel;
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
	
	@GetMapping("/getHotelsByAdministrator/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<List<Hotel>> getHotelsByAdministrator(@PathVariable Long id) {
		ArrayList<Hotel> hotels = hotelService.getHotelsByAdministrator(id);
		return new ResponseEntity<List<Hotel>>(hotels, HttpStatus.OK);
	}
	
	@PutMapping("/editHotel/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<Hotel> editHotel( @PathVariable Long id, @RequestBody HotelDTO hotelDTO) {
		Hotel hotel = hotelService.editHotel(id, hotelDTO);
		return new ResponseEntity<>(hotel, HttpStatus.OK);

	}
	
	@GetMapping("/checkIfReservedHotel/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public boolean checkIfReservedHotel(@PathVariable Long id) {
		//Ako hotel ne poseduje rezervisane sobe, taken je FALSE
		//u suprotnom taken ima vrednost TRUE
		boolean taken = hotelService.checkIfHotelIsReserved(id);
		return taken;
	}
	
	

}
