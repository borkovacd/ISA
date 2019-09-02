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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.dto.CenovnikRentDTO;
import com.ftn.dto.StavkaCenovnikaHotelaDTO;
import com.ftn.dto.StavkaCenovnikaRentDTO;
import com.ftn.enums.TipVozila;
import com.ftn.model.hotels.StavkaCenovnikaHotela;
import com.ftn.model.rentacar.CenovnikRentACar;
import com.ftn.model.rentacar.StavkaCenovnikaRent;
import com.ftn.service.CenovnikRentACarService;
import com.ftn.service.StavkaCenovnikaRentService;

@RestController
@RequestMapping(value = "/api/priceRent")
public class StavkaCenovnikaRentController 
{
	@Autowired
	private StavkaCenovnikaRentService stavkaRentService ;
	
	// vraca sve cene iz jednog cenovnika
	@GetMapping("/getAllPricesRent/{idPriceList}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<ArrayList<StavkaCenovnikaRent>> getAllPricesRent(@PathVariable Long idPriceList) 
	{
		ArrayList<StavkaCenovnikaRent> stavkeCenovnika = stavkaRentService.getAllPrices(idPriceList);
		return new ResponseEntity<ArrayList<StavkaCenovnikaRent>>(stavkeCenovnika, HttpStatus.OK);
	}
	
	// kreira novu cenu u cenovniku
	@PostMapping("/createPriceRent/{idPriceList}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<StavkaCenovnikaRent> createPriceRent(@RequestBody StavkaCenovnikaRentDTO stavkaDTO, @PathVariable Long idPriceList) 
	{
		StavkaCenovnikaRent stavkaCenovnika = stavkaRentService.createPrice(stavkaDTO, idPriceList);
		return new ResponseEntity<StavkaCenovnikaRent>(stavkaCenovnika, HttpStatus.OK);
	}
	
	// brise cenovnik
	@DeleteMapping("/obrisiStavkuCenovnik/{idRentACar}/{idCenovnik}/{idStavka}")
	@CrossOrigin(origins = "http://localhost:4200")
	public boolean obrisiStavkuCenovnik(@PathVariable Long idRentACar, @PathVariable Long idCenovnik, @PathVariable Long idStavka) throws Exception 
	{
		boolean response = stavkaRentService.obrisiStavkuCenovnik(idRentACar, idCenovnik, idStavka);
		return response; // TRUE - uspesno obrisano, FALSE - nije obrisano (nije pronadjeno)
	}
	
	
	
}
