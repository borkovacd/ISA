package com.ftn.controller;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.dto.KorisnikDTO;
import com.ftn.model.Korisnik;
import com.ftn.service.EmailService;
import com.ftn.service.UserService;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	// Dodato zbog slanja mejla za verifikaciju naloga
	@Autowired
	private EmailService emailService ;
	
	/****** Borkovac *******/
	
	@RequestMapping(value = "/getRegularUsers", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<List<Korisnik>> getRegularUsers() {	
		List<Korisnik> regularUsers = userService.getAllRegularUsers();
		return new ResponseEntity<>(regularUsers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getAdministrators", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<List<Korisnik>> getAdministrators() {	
		List<Korisnik> administrators = userService.getAllAdministrators();
		return new ResponseEntity<>(administrators, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/changeRole/{id}", method = RequestMethod.PUT)
	@CrossOrigin(origins = "http://localhost:4200")
	public void changeRole(@PathVariable Long id,@RequestBody String novaUloga) {	
		boolean uspesnaPromena = userService.changeRole(id, novaUloga);
		/*if (uspesnaPromena) {
			return new ResponseEntity<>("Uspesna promena", HttpStatus.OK);
		} else 
			return new ResponseEntity<>("Neuspesna promena", HttpStatus.OK);*/
	}
	
	@RequestMapping(value = "/getHotelAdministrators", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<List<Korisnik>> getHotelAdministrators() {	
		List<Korisnik> hotelAdministrators = userService.getAllHotelAdministrators();
		return new ResponseEntity<>(hotelAdministrators, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getRentCarAdministrators", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<List<Korisnik>> getRentCarAdministrators() {	
		List<Korisnik> rentCarAdministrators = userService.getAllRentCarAdministrators();
		return new ResponseEntity<>(rentCarAdministrators, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getAviokompanijaAdministrators", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<List<Korisnik>> getAviokompanijaAdministrators() {	
		List<Korisnik> aviokompanijaAdministrators = userService.getAllAviokompanijaAdministrators();
		return new ResponseEntity<>(aviokompanijaAdministrators, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getKorisnikData/{id}", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<Korisnik> getKorisnikData(@PathVariable Long id) {	
		Korisnik korisnik = userService.getKorisnikData(id);
		return new ResponseEntity<Korisnik>(korisnik, HttpStatus.OK);
	}
	
	/***** Olga 
	 * @throws NoSuchAlgorithmException ******/
	
	// vrsi registraciju
	@RequestMapping(value="/register", method = RequestMethod.POST, consumes="application/json")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<KorisnikDTO> register(@RequestBody KorisnikDTO korisnik) throws NoSuchAlgorithmException 
	{
		
		String povVrFunkcije = userService.register(korisnik);
		
		if(povVrFunkcije == "ok") // ne postoji korisnik sa tim email-om
		{
			return new ResponseEntity<>(korisnik, HttpStatus.OK);
		} 
		else // vraca  "greska", jer vec postoji korisnik sa tim email-om
		{
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // mozda je bolje vratiti OK
		}
	}
	
	// vrsi logovanje
	@RequestMapping(value="/logIn", method = RequestMethod.POST, consumes="application/json")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<KorisnikDTO> logIn(@RequestBody KorisnikDTO korisnik, @Context HttpServletRequest request) 
	{
		
		String povVrFunkc = userService.logIn(korisnik);
		Korisnik k1 = userService.returnKorisnikById(korisnik);
		
		if(k1 != null) // ukoliko postoji registrovan korisnik, sa aktiviranim nalogom
		{
			request.getSession().setAttribute("ulogovan", k1);
			KorisnikDTO kDTO = new KorisnikDTO(k1);
			kDTO.setStatusKorisnika(povVrFunkc);
			return new ResponseEntity<>(kDTO, HttpStatus.OK);
		} else 
		{
			KorisnikDTO kDTO = new KorisnikDTO();
			kDTO.setStatusKorisnika(povVrFunkc);
			return new ResponseEntity<>(kDTO, HttpStatus.OK);
		}
		
	}
	
	// vrsi sifrovanje unete lozinke, za prvo logovanje
	@RequestMapping(value="/changePassword", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<KorisnikDTO> changePassword(@RequestBody KorisnikDTO kdto)
	{
		Korisnik k = userService.returnKorisnikById(kdto);
		String s = userService.changePassword(kdto);
		
		if(!k.getLozinka().equals(s)) // ne sme sifrovana biti ista kao i pocetna
		{
			k.setLozinka(s);
			k.setPrvoLogovanje(true);
			userService.save(k);
			KorisnikDTO kd = new KorisnikDTO(k);
			kd.setStatusKorisnika("ok");
			return new ResponseEntity<>(kd, HttpStatus.OK);
		} else 
		{
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	// vrsi verifikaciju 
	@RequestMapping(value="/verifikujNalog/{mail}", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<KorisnikDTO> verifikujNalog(@PathVariable String email)
	{
		
		Korisnik k = userService.returnKorisnikByEmail(email);
		KorisnikDTO kDTO = new KorisnikDTO(k);
		
		try 
		{
			emailService.sendNotificaitionAsync(k);
		}catch( Exception e )
		{
			
		}

		return new ResponseEntity<>(kDTO, HttpStatus.OK);
	}
	
	// vrsi aktivaciju naloga
	@RequestMapping(value="/aktivirajNalog/{mail}", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:4200")
	public String aktivirajNalog(@PathVariable String email){

		String s = userService.aktivirajNalog(email);
		return s;
		
	}
	
	// odjava korisnika
	@RequestMapping(value="/logOut", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<KorisnikDTO> logOut(@Context HttpServletRequest request) 
	{
		Korisnik k = (Korisnik) request.getSession().getAttribute("ulogovan");
		request.getSession().invalidate();
		KorisnikDTO kDTO = new KorisnikDTO(k);
		return new ResponseEntity<>(kDTO, HttpStatus.OK);
		
	}
	
	// vraca trenutno ulogovanog korisnika
	@RequestMapping(value="/currentUser", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<KorisnikDTO> currentUser(@Context HttpServletRequest request)
	{
		Korisnik k = (Korisnik) request.getSession().getAttribute("ulogovan");
		KorisnikDTO kd = new KorisnikDTO(k);
		return new ResponseEntity<>(kd, HttpStatus.OK);
	}

	

}
