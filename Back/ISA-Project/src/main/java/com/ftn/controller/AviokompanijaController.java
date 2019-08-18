package com.ftn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.dto.AviokompanijaDTO;
import com.ftn.service.AviokompanijaService;

@RestController
@RequestMapping(value = "/api/aviokompanija")
public class AviokompanijaController {
	
	@Autowired
	private AviokompanijaService aviokompanijaService;
	
	@PostMapping("/registerAviokompanija")
	@CrossOrigin(origins = "http://localhost:4200")
	public void registerAviokompanija(@RequestBody AviokompanijaDTO aviokompanijaDTO) {
		boolean aviokompanijaRegistration = aviokompanijaService.registerAviokompanija(aviokompanijaDTO);
		/*if(aviokompanijaRegistration) 
			return new ResponseEntity<>("Uspesno registrovanje", HttpStatus.OK);
		else 
			return new ResponseEntity<>("Neuspesno registrovanje", HttpStatus.OK);*/
	}

}
