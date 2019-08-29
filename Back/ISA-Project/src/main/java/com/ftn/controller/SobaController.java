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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.dto.SobaDTO;
import com.ftn.dto.VremenskiPeriodDTO;
import com.ftn.model.hotels.Soba;
import com.ftn.service.SobaService;

@RestController
@RequestMapping(value = "/api/room")
public class SobaController {
	
	@Autowired
	private SobaService sobaService;
	
	@PostMapping("/createRoom/{idHotela}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<Soba> createRoom(@RequestBody SobaDTO sobaDTO, @PathVariable Long idHotela) {
		Soba soba = sobaService.createRoom(sobaDTO, idHotela);
		return new ResponseEntity<Soba>(soba, HttpStatus.OK);
	}

	@GetMapping("/getAllRooms/{idHotela}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<ArrayList<Soba>> getAllRooms(@PathVariable Long idHotela) {
		ArrayList<Soba> sobe = sobaService.getAllRooms(idHotela);
		return new ResponseEntity<ArrayList<Soba>>(sobe, HttpStatus.OK);
	}

	@GetMapping("/checkIfReservedRoom/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public boolean checkIfReservedRoom(@PathVariable Long id) {
		//Ako soba nije rezervisana, taken je FALSE
		//u suprotnom taken ima vrednost TRUE
		boolean taken = sobaService.checkIfRoomIsReserved(id);
		return taken;
	}
	
	@GetMapping("/getRoom/{idRoom}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<Soba> getRoom(@PathVariable Long idRoom) {
		Soba soba = sobaService.getRoom(idRoom);
		if (soba == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(soba, HttpStatus.OK);
	}

	
	@PutMapping("/editRoom/{idRoom}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<Soba> editRoom(@PathVariable Long idRoom, @RequestBody SobaDTO sobaDTO) {
		Soba soba = sobaService.editRoom(idRoom, sobaDTO);
		return new ResponseEntity<>(soba, HttpStatus.OK);

	}

	@DeleteMapping("/deleteRoom/{idRoom}")
	@CrossOrigin(origins = "http://localhost:4200")
	public boolean deleteRoom(@PathVariable Long idRoom) {
		boolean response = sobaService.deleteRoom(idRoom);
		return response;
	}
	
	@PostMapping("/getAvailableRooms/{idHotela}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<ArrayList<Soba>> getAvailableRooms(@RequestBody VremenskiPeriodDTO vpDTO, @PathVariable Long idHotela) {
		ArrayList<Soba> sobe = sobaService.getAvailableRooms(vpDTO, idHotela);
		return new ResponseEntity<ArrayList<Soba>>(sobe, HttpStatus.OK);
	}

	
}
