package com.ftn.controller;

import java.util.ArrayList;

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

import com.ftn.dto.CenovnikRentDTO;
import com.ftn.enums.TipVozila;
import com.ftn.model.rentacar.CenovnikRentACar;
import com.ftn.service.CenovnikRentACarService;

@RestController
@RequestMapping(value = "/api/pricelistRent")
public class CenovnikRentACarController 
{
	@Autowired
	private CenovnikRentACarService cenovnikRentService ;
	
	// vraca sve cenovnike jednog rent-a-car servisa
	@GetMapping("/getAllPricelistsRent/{idRent}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<ArrayList<CenovnikRentACar>> getAllPricelistsRent(@PathVariable Long idRent) 
	{
		ArrayList<CenovnikRentACar> cenovnici = cenovnikRentService.getAllPricelists(idRent);
		return new ResponseEntity<ArrayList<CenovnikRentACar>>(cenovnici, HttpStatus.OK);
	}
	
	// kreira novi cenovnik za rent-a-car
	@PostMapping("/createPricelistRent/{idRent}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<CenovnikRentACar> createPricelistRent(@RequestBody CenovnikRentDTO cenvnikRentDTO, @PathVariable Long idRent) 
	{
		CenovnikRentACar cenovnikRent = cenovnikRentService.createPricelist(cenvnikRentDTO, idRent);
		return new ResponseEntity<CenovnikRentACar>(cenovnikRent, HttpStatus.OK);
	}
	
	// vraca cenovnik na osnovu id-ja
	@GetMapping("/getPricelistRent/{idPriceList}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<CenovnikRentACar> getPricelistRent(@PathVariable Long idPriceList) 
	{
		CenovnikRentACar cenovnik = cenovnikRentService.getPricelist(idPriceList);
		
		if (cenovnik == null) 
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<CenovnikRentACar>(cenovnik, HttpStatus.OK);
	}
	
	// vraca aktivan cenovnik
	@GetMapping("/getActivePricelistRent/{idRent}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<CenovnikRentACar> getActivePricelistRent(@PathVariable Long idRent) 
	{
		CenovnikRentACar cenovnik = cenovnikRentService.getActivePricelist(idRent);
		
		if (cenovnik == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<CenovnikRentACar>(cenovnik, HttpStatus.OK);
	}
	
	// vraca tipove vozila tog servisa
	@GetMapping("/getRoomTypesInRent/{idPriceList}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<ArrayList<TipVozila>> getRoomTypesInRent(@PathVariable Long idPriceList) 
	{
		ArrayList<TipVozila> tipoviVozila = cenovnikRentService.getTipoviVozilaRent(idPriceList);
		return new ResponseEntity<ArrayList<TipVozila>>(tipoviVozila, HttpStatus.OK);
	}
	
	
	
	
	
	
	
}
