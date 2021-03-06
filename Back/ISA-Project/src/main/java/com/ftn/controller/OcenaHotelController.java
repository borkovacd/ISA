package com.ftn.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.repository.OcenaVoziloRepository;
import com.ftn.repository.RezervacijaVozilaRepository;
import com.ftn.repository.VoziloRepository;
import com.ftn.service.OcenaHotelService;
import com.ftn.service.OcenaVoziloService;
import com.ftn.service.UserService;
import com.ftn.repository.UserRepository;
import com.ftn.model.Korisnik;
import com.ftn.dto.OcenaHotelDTO;
import com.ftn.dto.OcenaVoziloDTO;
import com.ftn.model.rentacar.Vozilo;
import com.ftn.model.rentacar.OcenaVozilo;
import com.ftn.model.rentacar.RezervacijaVozila;

@RestController
@RequestMapping(value = "ocenaHotel")
public class OcenaHotelController {
	
	@Autowired
	private RezervacijaVozilaRepository rezVozRepository ;
	
	@Autowired
	private VoziloRepository voziloRepository ;
	
	@Autowired
	private OcenaVoziloRepository ocenaRepository ;
	
	@Autowired
	private OcenaHotelService ocenaService ;
	
	@Autowired
	private UserService userService ;
	
	// KLIK OCENI SMESTAJ - dobija idRezervacije koju je kliknuo, idSobe
		// `${this.BASE_URL}/checkIfComment/${idRoom}`
		@GetMapping("/dozvoljenoOcenjivanje/{idHotel}")
		public boolean dozvoljenoOcenjivanje(@PathVariable Long idHotel) throws Exception 
		{	
			//metoda treba da vraca true kada korisnik ima neku prethodno kreiranu rezervaciju
			boolean canComment = ocenaService.dozvoljenoOcenjivanje(idHotel, userService.getCurrentUser().getId());
			return canComment;
		}
		
		// CONFIRM CLICK - DTO i idRoom
		// oceniAcc(id: any, idRoom: any)
		// `${this.BASE_URL}/createRating/${token}/${idRoom}`
		@PostMapping("/oceniHotel/{idHotel}")
		public void oceniHotel(@RequestBody OcenaHotelDTO ratingDTO, @PathVariable Long idHotel) throws Exception 
		{
		
			ocenaService.oceniHotel(ratingDTO, userService.getCurrentUser(), idHotel);		
		}
		
		// OCENE SMESTAJA - RoomRating (VRACA - prosecna ocena, lista ocena i komentara)
		// getAverageRating(idRoom)
		// `${this.BASE_URL}/getAverageRating/${idRoom}`
		@GetMapping("/getProsecnaOcenaHotel/{idHotel}")
		public double getProsecnaOcenaHotel(@PathVariable Long idHotel)
		{
			double rezultat = ocenaService.getProsecnaOcenaHotel(idHotel);
			return rezultat ;
		}
		

}
