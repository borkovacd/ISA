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

import com.ftn.dto.StavkaCenovnikaHotelaDTO;
import com.ftn.model.hotels.StavkaCenovnikaHotela;
import com.ftn.service.StavkaCenovnikaHotelaService;

@RestController
@RequestMapping(value = "/api/price")
public class StavkaCenovnikaHotelaController {
	
	@Autowired
	private StavkaCenovnikaHotelaService stavkaCenovnikaHotelaService;
	
	@GetMapping("/getAllPrices/{idPriceList}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<ArrayList<StavkaCenovnikaHotela>> getAllPrices(@PathVariable Long idPriceList) {
		ArrayList<StavkaCenovnikaHotela> stavkeCenovnika = stavkaCenovnikaHotelaService.getAllPrices(idPriceList);
		return new ResponseEntity<ArrayList<StavkaCenovnikaHotela>>(stavkeCenovnika, HttpStatus.OK);
	}
	
	@PostMapping("/createPrice/{idPriceList}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<StavkaCenovnikaHotela> createPrice(@RequestBody StavkaCenovnikaHotelaDTO stavkaDTO, @PathVariable Long idPriceList) {
		StavkaCenovnikaHotela stavkaCenovnika = stavkaCenovnikaHotelaService.createPrice(stavkaDTO, idPriceList);
		return new ResponseEntity<>(stavkaCenovnika, HttpStatus.OK);
	}
	


}
