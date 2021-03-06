package com.ftn.controller;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.dto.KorisnikDTO;
import com.ftn.dto.KorisnikProfilDTO;
import com.ftn.model.Korisnik;
import com.ftn.repository.UserRepository;
import com.ftn.service.EmailService;
import com.ftn.service.UserService;

@RestController
@RequestMapping(value = "user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	// Dodato zbog slanja mejla za verifikaciju naloga
	@Autowired
	EmailService emailService ;
	
	@Autowired
	UserRepository userRepository;
	
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	
	/****** Borkovac *******/
	
	
	@GetMapping("/checkIfFreeUser/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public boolean checkIfFreeUser(@PathVariable Long id) {
		//Ako korisnik ne poseduje rezervacije taken je FALSE
		//u suprotnom taken ima vrednost TRUE
		boolean taken = userService.checkIfFreeUser(id);
		return taken;
	}
	
	@GetMapping("/getCurrentUser")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<Korisnik> getCurrentUser() {
		Korisnik korisnik = userService.getCurrentUser();
		return new ResponseEntity<Korisnik>(korisnik, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getRegularUsers", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<List<Korisnik>> getRegularUsers() {	
		List<Korisnik> regularUsers = userService.getAllRegularUsers();
		return new ResponseEntity<>(regularUsers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getOtherAdministrators", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<List<Korisnik>> getOtherAdministrators() {	
		List<Korisnik> administrators = userService.getOtherAdministrators();
		return new ResponseEntity<>(administrators, HttpStatus.OK);
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
	public ResponseEntity<Korisnik> getKorisnikData(@PathVariable Long id,  @Context HttpServletRequest request) {	
		Korisnik korisnik = userService.getKorisnikData(id);
		
		 HttpSession session = request.getSession();
         System.out.println("\nSesija KorisnikData: " + session);
		
		Korisnik k1 = (Korisnik) session.getAttribute("ulogovan");
		String email = (String) session.getAttribute("borkovac.dragan@gmail.com");
		
		if (email == null)
			System.out.println("Email korisnika u metodi je null!");
		else
			System.out.println("Mejl ulogovanog korisnika je: " + email);
		
		return new ResponseEntity<Korisnik>(korisnik, HttpStatus.OK);
	}
	
	@PutMapping("/editUser/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<Korisnik> editUser( @PathVariable Long id, @RequestBody KorisnikProfilDTO korisnikProfilDTO) throws NoSuchAlgorithmException {
		Korisnik korisnik = userService.editUser(id, korisnikProfilDTO);
		return new ResponseEntity<Korisnik>(korisnik, HttpStatus.OK);
	}
	
	@PutMapping("/editCurrentUser")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<Korisnik> editCurrentUser( @RequestBody KorisnikProfilDTO korisnikProfilDTO) {
		Korisnik korisnik = userService.editCurrentUser(korisnikProfilDTO);
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
		
		if(povVrFunkcije == "ok") // ne postoji korisnik sa tim email-om ili username-om
		{
			return new ResponseEntity<>(korisnik, HttpStatus.OK);
		} 
		else // vraca  "greska", jer vec postoji korisnik sa tim email-om ili username-om
		{
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // mozda je bolje vratiti OK
		}
	}
	
	
	
	// DODER 
	@RequestMapping(value="/trenutniKorisnik",method = RequestMethod.GET)
	public Korisnik trenutniKorisnik(@Context HttpServletRequest request){
		
		return userService.getCurrentUser();
	}
	
	@RequestMapping(value="/trenutniKorisnikAutor",method = RequestMethod.GET)
	public ResponseEntity<Korisnik> trenutniKorisnikAutor(@Context HttpServletRequest request){
		
		Korisnik korisnik = userService.getCurrentUser();
		if (korisnik != null)
		{
			return new ResponseEntity<>(korisnik, HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	// vrsi verifikaciju 
	@RequestMapping(value="/verifikujNalog/{email}", method = RequestMethod.GET)
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
			logger.info("Greska prilikom slanja emaila: " + e.getMessage());
		}

		return new ResponseEntity<>(kDTO, HttpStatus.OK);
	}
	
	// vrsi aktivaciju naloga
	@RequestMapping(value="/aktivirajNalog/{email}", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:4200")
	public String aktivirajNalog(@PathVariable String email){

		String s = userService.aktivirajNalog(email);
		return s;
		
	}
	
	// promena lozinke
	// ukoliko je uneo vec postojecu lozinku, vratice BAD_REQUEST
	@RequestMapping(value="/promenaLozinke", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<KorisnikDTO> promenaLozinke(@RequestBody KorisnikDTO kdto)
	{
		Korisnik k = userRepository.findOneByKorisnickoIme(kdto.getKorisnickoIme());
		System.out.println("Korisnicko ime je: " + k.getKorisnickoIme());
		String s = userService.promeniLozinku(kdto);
		
		if (!k.getLozinka().equals(s))
		{
			k.setLozinka(s);
			k.setPrvoLogovanje(false);
			k.setStatusKorisnika("ok");
			userService.save(k);
			
			KorisnikDTO kd = new KorisnikDTO(k);
			return new ResponseEntity<KorisnikDTO>(kd, HttpStatus.OK);
			
		}
		
		else
		{
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}


	

}
