package com.ftn.controller;

import java.util.ArrayList;
import java.util.List;

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

import com.ftn.dto.ProveriDostupnostRentDTO;
import com.ftn.dto.RentCarDTO;
import com.ftn.dto.VoziloDTO;
import com.ftn.dto.VremenskiPeriodDTO;
import com.ftn.model.rentacar.RentACar;
import com.ftn.model.rentacar.Vozilo;
import com.ftn.service.RentACarService;
import com.ftn.service.UserService;
import com.ftn.service.VoziloService;

@RestController
@RequestMapping(value = "/api/vozilo")
public class VoziloController 
{
	@Autowired
	VoziloService voziloService ;
	
	@Autowired
	RentACarService rentService ;
	
	@Autowired
	UserService userService ;

	// Dodavanje vozila
	@PostMapping("/dodajVozilo/{idRentACar}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<Vozilo> dodajVozilo(@RequestBody VoziloDTO voziloDTO, @PathVariable Long idRentACar) 
	{
		Vozilo vozilo = voziloService.dodajVozilo(voziloDTO, idRentACar);
		return new ResponseEntity<Vozilo>(vozilo, HttpStatus.OK);
		
	}
	
	// Brisanje vozila
	
	// Vraca sva vozila tog rent-a-car servisa
	@GetMapping("/getVozilaRentACar/{idRentACar}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<ArrayList<Vozilo>> getVozilaRentACar(@PathVariable Long idRentACar) throws Exception 
	{

		ArrayList<Vozilo> vozila = voziloService.getVozilaRentACar(idRentACar);

		return new ResponseEntity<>(vozila, HttpStatus.OK);
	}
	
	// vraca jedno vozilo
	@GetMapping("/vratiJednoVozilo/{idRentACar}/{idVozilo}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<Vozilo> vratiJednoVozilo(@PathVariable Long idRentACar,@PathVariable Long idVozilo) throws Exception 
	{		
		Vozilo v = voziloService.findByVoziloId(idVozilo);
		if (v == null) // ukoliko ne postoji to vozilo
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		else
		{
			return new ResponseEntity<>(v, HttpStatus.OK);
		}

	}
	
	// izmena vozila
	@PutMapping("/izmeniVozilo/{idRentACar}/{idVozila}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<Vozilo> izmeniVozilo(@PathVariable Long idRentACar,@PathVariable Long idVozila,
			@RequestBody VoziloDTO dto) throws Exception {

		Vozilo v = voziloService.izmeniVozilo(idRentACar, idVozila, dto);
		
		if (v == null)
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		else
		{
			return new ResponseEntity<>(v, HttpStatus.OK);
		}
		
	}

	@DeleteMapping("/obrisiVozilo/{idRentACar}/{idVozila}")
	@CrossOrigin(origins = "http://localhost:4200")
	public boolean obrisiVozilo(@PathVariable Long idRentACar, @PathVariable Long idVozila) throws Exception 
	{
		boolean response = voziloService.obrisiVozilo(idRentACar, idVozila);
		return response; // TRUE - uspesno obrisano, FALSE - nije obrisano (nije pronadjeno)
	}
	
	// provera da li je vozilo rezervisano
	@GetMapping("/checkIfReservedVozilo/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public boolean checkIfReservedVozilo(@PathVariable Long id) 
	{
		//Ako vozilo nije rezervisano, taken je FALSE
		//u suprotnom taken ima vrednost TRUE
		
		boolean taken = voziloService.checkIfVoziloIsReserved(id);
		return taken;
	}
	
	// vraca slobodna vozila jednog servisa
	@PostMapping("/getAvailableVozila/{idRent}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<ArrayList<Vozilo>> getAvailableVozila(@RequestBody VremenskiPeriodDTO vpDTO, @PathVariable Long idRent) 
	{
		ArrayList<Vozilo> vozila = voziloService.getSlobodnaVozilaPeriod(vpDTO, idRent);
		return new ResponseEntity<ArrayList<Vozilo>>(vozila, HttpStatus.OK);
	}
	
	
	
	
	
	// vraca sva vozila - 2.7
	// 2.7
		@GetMapping("/vratiSvaVozila")
		@CrossOrigin(origins = "http://localhost:4200")
		public ResponseEntity<List<Vozilo>> vratiSvaVozila() throws Exception 
		{
			List<Vozilo> vozila = voziloService.getAll();
			return new ResponseEntity<List<Vozilo>>(vozila, HttpStatus.OK);
		}

	
	// 2.7
	@GetMapping("/vratiVozilaKorisnika")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<List<Vozilo>> vratiVozilaKorisnika() throws Exception 
	{
		ArrayList<Vozilo> vozila = voziloService.getVozilaKorisnik(userService.getCurrentUser().getId());
		return new ResponseEntity<List<Vozilo>>(vozila, HttpStatus.OK);
	}
	
	// provera da li je vozilo dostupno
	@PostMapping("/checkAvailabilityVozilo/{idRent}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<ArrayList<Vozilo>> checkAvailabilityVozilo(@RequestBody ProveriDostupnostRentDTO pdDTO, @PathVariable Long idRent) 
	{
		ArrayList<Vozilo> vozila = voziloService.checkAvailability(pdDTO, idRent);
		return new ResponseEntity<ArrayList<Vozilo>>(vozila, HttpStatus.OK);
	}
}
