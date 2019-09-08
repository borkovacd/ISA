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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.dto.PretragaHotelaDTO;
import com.ftn.dto.PretragaRentDTO;
import com.ftn.dto.RentCarDTO;
import com.ftn.enums.TipVozila;
import com.ftn.model.hotels.Hotel;
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
	@GetMapping("/getAllRents")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<List<RentACar>> getAllRents()
	{
		ArrayList<RentACar> rents = rentCarService.vratiSveServise();
		return new ResponseEntity<List<RentACar>>(rents, HttpStatus.OK);
		
	}
	
	// provera da li u rent-a-car servisu ima rezervisanih vozila
	@GetMapping("/checkIfReservedRent/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public boolean checkIfReservedRent(@PathVariable Long id) 
	{
		//Ako rent ne poseduje rezervisana vozila, taken je FALSE
		//u suprotnom taken ima vrednost TRUE
		boolean taken = rentCarService.checkIfRentIsReserved(id);
		
		return taken;
	}
	
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
	
	@PostMapping("/searchRents")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<ArrayList<RentACar>> searchRents(@RequestBody PretragaRentDTO phDTO) 
	{
		ArrayList<RentACar> servisi = rentCarService.searchRents(phDTO);
		return new ResponseEntity<ArrayList<RentACar>>(servisi, HttpStatus.OK);
	}
	
	// vraca tipove vozila tog servisa
		@GetMapping("/getVoziloTypesInRent/{idRent}")
		@CrossOrigin(origins = "http://localhost:4200")
		public ResponseEntity<ArrayList<TipVozila>> getRoomTypesInRent(@PathVariable Long idRent) 
		{
			ArrayList<TipVozila> tipoviVozila = rentCarService.getTipoviVozilaRent(idRent);
			return new ResponseEntity<ArrayList<TipVozila>>(tipoviVozila, HttpStatus.OK);
		}
		
		@GetMapping("/monthlyGraphRent/{id}")
		@CrossOrigin(origins = "http://localhost:4200")
		public ResponseEntity<List<Integer>> monthlyGraphRent(@PathVariable Long id, @RequestParam String year) {
			ArrayList<Integer> monthsData = rentCarService.getMonthyGraphDataRent(id, year);
			return new ResponseEntity<List<Integer>>(monthsData, HttpStatus.OK);
		}
		
		@GetMapping("/weeklyGraphRent/{id}")
		@CrossOrigin(origins = "http://localhost:4200")
		public ResponseEntity<List<Integer>> weeklyGraphRent(@PathVariable Long id, @RequestParam String year, @RequestParam String month) {
			ArrayList<Integer> weeksData = rentCarService.getWeeklyGraphDataRent(id, year, month);
			return new ResponseEntity<List<Integer>>(weeksData, HttpStatus.OK);
		}
		
		@GetMapping("/dailyGraphRent/{id}")
		@CrossOrigin(origins = "http://localhost:4200")
		public ResponseEntity<List<Integer>> dailyGraphRent(@PathVariable Long id, @RequestParam String date) {
			ArrayList<Integer> daysData = rentCarService.getDailyGraphDataRent(id, date);
			return new ResponseEntity<List<Integer>>(daysData, HttpStatus.OK);
		}
		
		@GetMapping("/getRevenuesRent/{id}")
		@CrossOrigin(origins = "http://localhost:4200")
		public ResponseEntity<Double> getRevenuesRent(@PathVariable Long id, @RequestParam String d1, @RequestParam String d2) {
			Double retVal = rentCarService.getRevenuesRent(id, d1, d2);
			return new ResponseEntity<Double>(retVal, HttpStatus.OK);
		}
	
	
	/******************/

}
