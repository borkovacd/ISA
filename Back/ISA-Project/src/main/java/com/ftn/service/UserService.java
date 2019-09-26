package com.ftn.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ftn.dto.HotelDTO;
import com.ftn.dto.KorisnikDTO;
import com.ftn.dto.KorisnikProfilDTO;
import com.ftn.enums.UlogaKorisnika;
import com.ftn.model.Korisnik;
import com.ftn.model.hotels.Hotel;
import com.ftn.model.hotels.RezervacijaHotela;
import com.ftn.model.hotels.Soba;
import com.ftn.model.rentacar.RezervacijaVozila;
import com.ftn.repository.RezervacijaHotelaRepository;
import com.ftn.repository.RezervacijaVozilaRepository;
import com.ftn.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RezervacijaHotelaRepository rezervacijaHotelaRepository;
	@Autowired
	private RezervacijaVozilaRepository rezervacijaVozilaRepository;
	
	@Autowired
	private UserService userService;
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	


	
	/************ Borkovac *************/
	
	public boolean checkIfFreeUser(Long id) {
		boolean taken = false;
		
		List<RezervacijaHotela> rezervacije = rezervacijaHotelaRepository.findAll();
		for(RezervacijaHotela rezervacija : rezervacije) {
			if(rezervacija.getKorisnik().getId() == id) {
				taken = true;
				return taken;
			}
		}
		 
		List<RezervacijaVozila> rezervacije2 = rezervacijaVozilaRepository.findAll();
		for(RezervacijaVozila rezervacija2 :  rezervacije2) {
			if(rezervacija2.getKorisnik().getId() == id) {
				taken = true;
				return taken;
			}
		}
		return taken;
	}
	
	public List<Korisnik> getAllRegularUsers() {
		List<Korisnik> allUsers = userRepository.findAll();
		List<Korisnik> regularUsers = new ArrayList<Korisnik>();
		for(int i = 0; i < allUsers.size(); i++) {
			if(allUsers.get(i).getUloga() == UlogaKorisnika.OBICAN_KORISNIK) {
				regularUsers.add(allUsers.get(i));
			}
		}
		return regularUsers;
	}
	

	public List<Korisnik> getOtherAdministrators() {
		
		Korisnik administrator = userService.getCurrentUser();
		//System.out.println("Ulogovani admin: " + administrator.getEmail());
		
		List<Korisnik> allUsers = userRepository.findAll();
		List<Korisnik> administrators = new ArrayList<Korisnik>();
		for(int i = 0; i < allUsers.size(); i++) {
			if(allUsers.get(i).getUloga() != UlogaKorisnika.OBICAN_KORISNIK) {
				if(allUsers.get(i).getId() != administrator.getId())
					administrators.add(allUsers.get(i));
			}
		}
		return administrators;
	}


	public List<Korisnik> getAllAdministrators() {
		List<Korisnik> allUsers = userRepository.findAll();
		List<Korisnik> administrators = new ArrayList<Korisnik>();
		for(int i = 0; i < allUsers.size(); i++) {
			if(allUsers.get(i).getUloga() != UlogaKorisnika.OBICAN_KORISNIK) {
				administrators.add(allUsers.get(i));
			}
		}
		return administrators;
	}
	
	public boolean changeRole(Long id, String novaUloga) {
		if(userRepository.getOne(id) != null) {
			Korisnik korisnik = userRepository.getOne(id);
			//System.out.println("Korisnicnko ime: " + korisnik.getKorisnickoIme());
			//System.out.println("Nova uloga: " + novaUloga);
			if(novaUloga.equals("ADMINISTRATOR_SISTEMA")) {
				korisnik.setUloga(UlogaKorisnika.ADMINISTRATOR_SISTEMA);
				korisnik.setPrvoLogovanje(true);
				korisnik.setStatusKorisnika("prvo");
			}
			else if(novaUloga.equals("ADMINISTRATOR_AVIOKOMPANIJE")) {
				korisnik.setUloga(UlogaKorisnika.ADMINISTRATOR_AVIOKOMPANIJE);
				korisnik.setPrvoLogovanje(true);
				korisnik.setStatusKorisnika("prvo");
			}
			else if(novaUloga.equals("ADMINISTRATOR_HOTELA")) {
				korisnik.setUloga(UlogaKorisnika.ADMINISTRATOR_HOTELA);
				korisnik.setPrvoLogovanje(true);
				korisnik.setStatusKorisnika("prvo");
			}
			else if(novaUloga.equals("ADMINISTRATOR_RENT_A_CAR")) {
				korisnik.setUloga(UlogaKorisnika.ADMINISTRATOR_RENT_A_CAR);
				korisnik.setPrvoLogovanje(true);
				korisnik.setStatusKorisnika("prvo");
			}
			userRepository.save(korisnik);
			//System.out.println("Uloga je sada: " + korisnik.getUloga());
			return true;
		} else {
			return false;
		}	
	}
	
	public List<Korisnik> getAllHotelAdministrators() {
		List<Korisnik> allUsers = userRepository.findAll();
		List<Korisnik> hotelAdministrators = new ArrayList<Korisnik>();
		for(int i = 0; i < allUsers.size(); i++) {
			if(allUsers.get(i).getUloga() == UlogaKorisnika.ADMINISTRATOR_HOTELA) {
				hotelAdministrators.add(allUsers.get(i));
			}
		}
		return hotelAdministrators;
	}
	
	public List<Korisnik> getAllRentCarAdministrators() {
		List<Korisnik> allUsers = userRepository.findAll();
		List<Korisnik> rentCarAdministrators = new ArrayList<Korisnik>();
		for(int i = 0; i < allUsers.size(); i++) {
			if(allUsers.get(i).getUloga() == UlogaKorisnika.ADMINISTRATOR_RENT_A_CAR) {
				rentCarAdministrators.add(allUsers.get(i));
			}
		}
		return rentCarAdministrators;
	}


	public List<Korisnik> getAllAviokompanijaAdministrators() {
		List<Korisnik> allUsers = userRepository.findAll();
		List<Korisnik> aviokompanijaAdministrators = new ArrayList<Korisnik>();
		for(int i = 0; i < allUsers.size(); i++) {
			if(allUsers.get(i).getUloga() == UlogaKorisnika.ADMINISTRATOR_AVIOKOMPANIJE) {
				aviokompanijaAdministrators.add(allUsers.get(i));
			}
		}
		return aviokompanijaAdministrators;
	}
	
	public Korisnik getKorisnikData(Long id) {
		Korisnik korisnik = userRepository.getOne(id);
		return korisnik;
	}
	
	public Korisnik editUser(Long id, KorisnikProfilDTO korisnikProfilDTO) throws NoSuchAlgorithmException {
		Korisnik korisnik = userRepository.getOne(id);
		korisnik.setIme(korisnikProfilDTO.getIme());
		korisnik.setPrezime(korisnikProfilDTO.getPrezime());
		korisnik.setGrad(korisnikProfilDTO.getGrad());
		korisnik.setTelefon(korisnikProfilDTO.getTelefon());
		//Provera da li se lozinke poklapaju + hesovanje lozinke
		if(korisnikProfilDTO.getLozinka().equals(korisnikProfilDTO.getPonovljenaLozinka())) {
			String encriptedPass = "";
			encriptedPass = encriptPassword(korisnikProfilDTO.getLozinka());
			korisnik.setLozinka(encriptedPass);
		} else {
			return null;
		}
		userRepository.save(korisnik);
		return korisnik;
		
	}
	
	public Korisnik editCurrentUser(KorisnikProfilDTO korisnikProfilDTO) {
		Korisnik currentUser = getCurrentUser();
		Korisnik korisnik = userRepository.getOne(currentUser.getId());
		System.out.println("Trenutni korisnik: " + korisnik.getIme() + " " + korisnik.getPrezime());
		korisnik.setIme(korisnikProfilDTO.getIme());
		korisnik.setPrezime(korisnikProfilDTO.getPrezime());
		korisnik.setGrad(korisnikProfilDTO.getGrad());
		korisnik.setTelefon(korisnikProfilDTO.getTelefon());
		//Provera da li se lozinke poklapaju + hesovanje lozinke
		if(korisnikProfilDTO.getLozinka().equals(korisnikProfilDTO.getPonovljenaLozinka())) {
			String encriptedPass = "";
			try {
				encriptedPass = encriptPassword(korisnikProfilDTO.getLozinka());
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			encriptedPass = encoder.encode(korisnikProfilDTO.getLozinka());
			korisnik.setLozinka(encriptedPass);
		} else {
			return null;
		}
		userRepository.save(korisnik);
		return korisnik;
		
	}
	
	/************ *********** *************/
	
	
	/****** Olga *********/
	
	// registracija
	public String register(KorisnikDTO korisnik) // zbog enkripcije
	{
		
		Korisnik k1 = userRepository.findOneByEmail(korisnik.getEmail());
		Korisnik k2 = userRepository.findByKorisnickoIme(korisnik.getKorisnickoIme());
		
		if(k1 != null || k2 != null) // ukoliko vec postoji korisnik sa tim mejlom ili korisnickim imenom
			return "greska";
		else 
		{
			String tempPassword = "";
			
			try {
				tempPassword = encriptPassword(korisnik.getLozinka());
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			tempPassword = encoder.encode(korisnik.getLozinka());
			Korisnik k = new Korisnik(korisnik.getIme(), korisnik.getPrezime(), korisnik.getKorisnickoIme(), tempPassword, korisnik.getEmail(), korisnik.getTelefon(), korisnik.getGrad());
			k.setUloga(UlogaKorisnika.OBICAN_KORISNIK); // kad se registruje, postaje OBICAN KORISNIK
			k.setVerifikovan(false); // inicijalno nije verifikovan, mora potvrditi
			k.setPrvoLogovanje(true);
			k.setStatusKorisnika("nijeVerifikovan");
			
			// cuvanje u bazu
			userRepository.save(k);
			return "ok";
		}
	}

	// vrsi sifrovanje lozinke, da se ne bi u bazu cuvala u osnovnom obliku
	private String encriptPassword(String lozinka) throws NoSuchAlgorithmException 
	{
		MessageDigest md = MessageDigest.getInstance("SHA-256"); 
		  
		//pretvori sifru  u bajte
        byte[] messageDigest = md.digest(lozinka.getBytes()); 
         
        StringBuilder sb = new StringBuilder();
        
        for (byte b : messageDigest) 
        {
            sb.append(String.format("%02x", b));
        }
        
        String enkriptovana = sb.toString();
    	
        return enkriptovana;
	}
	
	// ne treba metoda za logovanje ako koristim Spring Security 
	

	
	public Korisnik returnKorisnikByEmail(String email) 
	{
		Korisnik k1 = userRepository.findOneByEmail(email);
		return k1;
	}
	
	public Korisnik returnKorisnikById(Korisnik k) 
	{
		return userRepository.findOneById(k.getId());
	}
	
	public String aktivirajNalog(String email) 
	{
		Korisnik k = userRepository.findOneByEmail(email);
		System.out.println("Mejl korisnika je: " + k.getEmail() + ", a ime je: " + k.getIme());
		// salje se email
		
		k.setVerifikovan(true);	
		k.setStatusKorisnika("prvo");
		k.setUloga(UlogaKorisnika.OBICAN_KORISNIK);
    	
    	userRepository.save(k);
		
		return "Verifikovali ste mail, mozete posetiti sajt.";
	}

	public void save(Korisnik k) {
		userRepository.save(k);
		
	}
	
	public String promeniLozinku(KorisnikDTO dto)
	{
		String pomLozinka = "";
		pomLozinka = encoder.encode(dto.getLozinka());
		dto.setStatusKorisnika("ok");
		return pomLozinka ;
	}
	
	// METODA DODER
	
	public Korisnik getCurrentUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Korisnik k = userRepository.findOneByKorisnickoIme(principal.toString());
		String kIme = k.getKorisnickoIme();
		return k;
	}


	/********* Ende - Olga *********/

}
