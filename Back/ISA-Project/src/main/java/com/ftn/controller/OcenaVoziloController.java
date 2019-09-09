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
import com.ftn.service.OcenaVoziloService;
import com.ftn.service.UserService;
import com.ftn.repository.UserRepository;
import com.ftn.model.Korisnik;
import com.ftn.dto.OcenaVoziloDTO;
import com.ftn.model.rentacar.Vozilo;
import com.ftn.model.rentacar.OcenaVozilo;
import com.ftn.model.rentacar.RezervacijaVozila;

@RestController
@RequestMapping(value = "ocenaVozilo")
public class OcenaVoziloController {
	
	@Autowired
	private RezervacijaVozilaRepository rezVozRepository ;
	
	@Autowired
	private VoziloRepository voziloRepository ;
	
	@Autowired
	private OcenaVoziloRepository ocenaRepository ;
	
	@Autowired
	private OcenaVoziloService ocenaService ;
	
	@Autowired
	private UserService userService ;
	
	// KLIK OCENI SMESTAJ - dobija idRezervacije koju je kliknuo, idSobe
		// `${this.BASE_URL}/checkIfComment/${idRoom}`
		@GetMapping("/dozvoljenoOcenjivanje/{idVozilo}")
		public boolean dozvoljenoOcenjivanje(@PathVariable Long idVozilo) throws Exception 
		{	
			//metoda treba da vraca true kada korisnik ima neku prethodno kreiranu rezervaciju
			boolean canComment = ocenaService.dozvoljenoOcenjivanje(idVozilo, userService.getCurrentUser().getId());
			return canComment;
		}
		
		// CONFIRM CLICK - DTO i idRoom
		// oceniAcc(id: any, idRoom: any)
		// `${this.BASE_URL}/createRating/${token}/${idRoom}`
		@PostMapping("/oceniVozilo/{idVozilo}")
		public void oceniVozilo(@RequestBody OcenaVoziloDTO ratingDTO, @PathVariable Long idVozilo) throws Exception 
		{
		
			ocenaService.oceniVozilo(ratingDTO, userService.getCurrentUser(), idVozilo);		
		}
		
		// OCENE SMESTAJA - RoomRating (VRACA - prosecna ocena, lista ocena i komentara)
		// getAverageRating(idRoom)
		// `${this.BASE_URL}/getAverageRating/${idRoom}`
		@GetMapping("/getProsecnaOcenaVozila/{idVozilo}")
		public double getProsecnaOcenaVozila(@PathVariable Long idVozilo)
		{
			double rezultat = ocenaService.getProsecnaOcenaVozila(idVozilo);
			return rezultat ;
		}
		
		// getListOfRating(Lista svih rejtinga, komentara i ocena)
		// getListOfRating(idRoom)
		// (`${this.BASE_URL}/getListOfRating/${idRoom}`
		@GetMapping("/vratiListuOcenaVozila/{idVozilo}")
		public ResponseEntity<List<OcenaVozilo>> vratiListuOcenaVozila (@PathVariable Long idVozilo)
		{
			List<OcenaVozilo> ratings = new ArrayList<OcenaVozilo>();
			ratings = ocenaService.vratiListuOcenaVozila(idVozilo);
			
			if (ratings == null)
			{
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			else
			{
				return new ResponseEntity<List<OcenaVozilo>>(ratings, HttpStatus.OK);
			}
			
		}
		// ********* ADMIN ************
		
		@GetMapping("/vratiListuSvihOcena")
		public ResponseEntity<List<OcenaVozilo>> vratiListuSvihOcena()
		{
			List<OcenaVozilo> ratings = new ArrayList<OcenaVozilo>();
			ratings = ocenaService.vratiListuSvihOcena();
				
			if (ratings.size() == 0)
			{
				return new ResponseEntity<>(HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity<List<OcenaVozilo>>(ratings, HttpStatus.OK);
			}
		}
		
		

}
