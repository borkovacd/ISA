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

import com.ftn.dto.DodatnaUslugaDTO;
import com.ftn.dto.StavkaCenovnikaHotelaDTO;
import com.ftn.model.hotels.DodatnaUsluga;
import com.ftn.model.hotels.StavkaCenovnikaHotela;
import com.ftn.service.DodatnaUslugaService;

@RestController
@RequestMapping(value = "/api/additionalService")
public class DodatnaUslugaController {
	
	@Autowired
	private DodatnaUslugaService dodatnaUslugaService;
	
	@GetMapping("/checkIfReservedService/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public boolean checkIfReservedService(@PathVariable Long id) {
		//Ako dodatna usluga nije rezervisana, taken je FALSE
		//u suprotnom taken ima vrednost TRUE
		boolean taken = dodatnaUslugaService.checkIfReservedService(id);
		return taken;
	}
	
	@GetMapping("/getAllAdditionalServices/{idHotela}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<ArrayList<DodatnaUsluga>> getAllAdditionalServices(@PathVariable Long idHotela) {
		ArrayList<DodatnaUsluga> dodatneUsluge = dodatnaUslugaService.getAllAdditionalServices(idHotela);
		return new ResponseEntity<ArrayList<DodatnaUsluga>>(dodatneUsluge, HttpStatus.OK);
	}
	
	@PostMapping("/createAdditionalService/{idHotela}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<DodatnaUsluga> createAdditionalService(@RequestBody DodatnaUslugaDTO dodatnaUslugaDTO, @PathVariable Long idHotela) {
		DodatnaUsluga dodatnaUsluga = dodatnaUslugaService.createAdditionalService(dodatnaUslugaDTO, idHotela);
		return new ResponseEntity<>(dodatnaUsluga, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteAdditionalService/{idAdditionalService}")
	@CrossOrigin(origins = "http://localhost:4200")
	public boolean deleteAdditionalService(@PathVariable Long idAdditionalService) {
		boolean response = dodatnaUslugaService.deleteAdditionalService(idAdditionalService);
		return response;
	}
	
	@GetMapping("/getAvailableAdditionalServices/{idHotela}/{idRezervacije}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<ArrayList<DodatnaUsluga>> getAvailableAdditionalServices(@PathVariable Long idHotela, @PathVariable Long idRezervacije) {
		ArrayList<DodatnaUsluga> dodatneUsluge = dodatnaUslugaService.getAvailableAdditionalServices(idHotela, idRezervacije);
		return new ResponseEntity<ArrayList<DodatnaUsluga>>(dodatneUsluge, HttpStatus.OK);
	}
}
