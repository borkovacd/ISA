package isa.proj.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import isa.proj.model.Hotel;
import isa.proj.model.Korisnik;
import isa.proj.service.KorisnikService;

@RestController
@RequestMapping(value = "/korisnik")
public class KorisnikController {
	@Autowired
	private KorisnikService korisnikService;
	
	@RequestMapping(
			value = "/getById/{idKor}",
			method = RequestMethod.GET
	)
	public Korisnik getById(@PathVariable Integer idKor) {
		Korisnik kor = korisnikService.getById(idKor);
		return kor;
		
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/preuzmiSve", produces="application/json", consumes="application/json")
	public List<Korisnik> getAllUsers() {
		return korisnikService.getAllUsers();
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/promeniTipKorisnika")
	public @ResponseBody ResponseEntity<Hotel> promeniTipKorisnika(@RequestBody Korisnik korisnik, HttpServletRequest request) {
		
		korisnikService.promeniTipKorisnika(korisnik);
		return new ResponseEntity<>(HttpStatus.OK);
		//return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		}
}
