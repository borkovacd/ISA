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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.dto.KorisnikDTO;
import com.ftn.dto.KorisnikProfilDTO;
import com.ftn.model.Korisnik;
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
	
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	
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
	
	
	// NE TREBA METODA ZA LOGOVANJE, VEC PREKO SPRING SECURITY-JA
	/*
	// vrsi logovanje
	@RequestMapping(value="/logIn", method = RequestMethod.POST, consumes="application/json")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<KorisnikDTO> logIn(@RequestBody KorisnikDTO korisnik, @Context HttpServletRequest request) 
	{
		
		String povVrFunkc = userService.logIn(korisnik);
		Korisnik k1 = userService.returnKorisnikByEmail(korisnik.getEmail());
		
		if(k1 != null) // ukoliko postoji registrovan korisnik, sa aktiviranim nalogom
		{
			HttpSession session = request.getSession();
			System.out.println("Sesija LOGIN: " + session);
			
			session.setAttribute("ulogovan", k1);
			session.setAttribute("borkovac.dragan@gmail.com", k1.getEmail());
			KorisnikDTO kDTO = new KorisnikDTO(k1);
			System.out.println("Log korisnik email: " + k1.getEmail());
			System.out.println("Log korisnikDTO email: " + kDTO.getEmail());
			
			
			
			Korisnik mica = (Korisnik) session.getAttribute("ulogovan");
			String email = (String) session.getAttribute("borkovac.dragan@gmail.com");
			System.out.println("Email ulogovanog je: " + email); 
			kDTO.setStatusKorisnika(povVrFunkc);
			return new ResponseEntity<>(kDTO, HttpStatus.OK);
		} else // ne postoji registrovan takav korisnik
		{
			System.out.println("Javlja da ne postoji ulogovan, a postoji!");
			KorisnikDTO kDTO = new KorisnikDTO();
			kDTO.setStatusKorisnika(povVrFunkc);
			return new ResponseEntity<>(kDTO, HttpStatus.OK);
		}
		
	}
	*/
	
	// DODER 
	@RequestMapping(value="/trenutniKorisnik",method = RequestMethod.GET)
	public Korisnik trenutniKorisnik(@Context HttpServletRequest request){
		return userService.getCurrentUser();
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
	
	// odjava korisnika
	@RequestMapping(value="/logOut", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<KorisnikDTO> logOut(@Context HttpServletRequest request) 
	{

		Korisnik k = (Korisnik) request.getSession().getAttribute("ulogovan");
		System.out.println("LogOUT korisnik email: " + k.getEmail());
		//request.getSession().invalidate();
		KorisnikDTO kDTO = new KorisnikDTO();
		return new ResponseEntity<>(kDTO, HttpStatus.OK);
	
		
	}
	
	// promena lozinke
	// ukoliko je uneo vec postojecu lozinku, vratice BAD_REQUEST
	@RequestMapping(value="/promenaLozinke", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<KorisnikDTO> promenaLozinke(@RequestBody KorisnikDTO kdto)
	{
		Korisnik k = userService.returnKorisnikByEmail(kdto.getEmail());
		String s = userService.promeniLozinku(kdto);
		
		if (!k.getLozinka().equals(s))
		{
			k.setLozinka(s);
			k.setPrvoLogovanje(true);
			userService.save(k);
			
			KorisnikDTO kd = new KorisnikDTO(k);
			return new ResponseEntity<KorisnikDTO>(kd, HttpStatus.OK);
			
		}
		
		else
		{
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	/*
	// vraca trenutno ulogovanog korisnika
	@RequestMapping(value="/currentUser", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<KorisnikDTO> currentUser(@Context HttpServletRequest request)
	{
		Korisnik k = (Korisnik) request.getSession().getAttribute("ulogovan");
		KorisnikDTO kd = new KorisnikDTO(k);
		return new ResponseEntity<>(kd, HttpStatus.OK);
	}
	*/

	

}
