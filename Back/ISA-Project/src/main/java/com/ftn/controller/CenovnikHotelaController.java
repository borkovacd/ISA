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

import com.ftn.dto.CenovnikHotelaDTO;
import com.ftn.dto.SobaDTO;
import com.ftn.model.hotels.CenovnikHotela;
import com.ftn.model.hotels.Soba;
import com.ftn.service.CenovnikHotelaService;

@RestController
@RequestMapping(value = "/api/pricelist")
public class CenovnikHotelaController {
	
	@Autowired
	private CenovnikHotelaService cenovnikHotelaService;
	
	@GetMapping("/getAllPricelists/{idHotela}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<ArrayList<CenovnikHotela>> getAllPricelists(@PathVariable Long idHotela) {
		ArrayList<CenovnikHotela> cenovnici = cenovnikHotelaService.getAllPricelists(idHotela);
		return new ResponseEntity<ArrayList<CenovnikHotela>>(cenovnici, HttpStatus.OK);
	}
	
	@PostMapping("/createPricelist/{idHotela}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<CenovnikHotela> createPricelist(@RequestBody CenovnikHotelaDTO cenvnikHotelaDTO, @PathVariable Long idHotela) {
		CenovnikHotela cenovnikHotela = cenovnikHotelaService.createPricelist(cenvnikHotelaDTO, idHotela);
		return new ResponseEntity<CenovnikHotela>(cenovnikHotela, HttpStatus.OK);
	}

	/*@GetMapping("/getRoom/{idRoom}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<Soba> getRoom(@PathVariable Long idRoom) {
		Soba soba = sobaService.getRoom(idRoom);
		if (soba == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(soba, HttpStatus.OK);
	}*/
	

}
