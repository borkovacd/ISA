package com.ftn.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.dto.KorisnikDTO;
import com.ftn.enums.UlogaKorisnika;
import com.ftn.model.Korisnik;
import com.ftn.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	/************ Borkovac *************/
	
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
			if(novaUloga.equals("ADMINISTRATOR_SISTEMA")) 
				korisnik.setUloga(UlogaKorisnika.ADMINISTRATOR_SISTEMA);
			else if(novaUloga.equals("ADMINISTRATOR_AVIOKOMPANIJE")) 
				korisnik.setUloga(UlogaKorisnika.ADMINISTRATOR_AVIOKOMPANIJE);
			else if(novaUloga.equals("ADMINISTRATOR_HOTELA")) 
				korisnik.setUloga(UlogaKorisnika.ADMINISTRATOR_HOTELA);
			else if(novaUloga.equals("ADMINISTRATOR_RENT_A_CAR")) 
				korisnik.setUloga(UlogaKorisnika.ADMINISTRATOR_RENT_A_CAR);
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

	
	
	/************ *********** *************/
	
	
	/****** Olga *********/
	
	// registracija
	public String register(KorisnikDTO korisnik) throws NoSuchAlgorithmException // zbog enkripcije
	{
		
		Korisnik k1 = userRepository.findOneByEmail(korisnik.getEmail());
		
		if(k1 != null) // ukoliko vec postoji korisnik sa tim mejlom
			return "greska";
		else 
		{
			String tempPassword = "";
			tempPassword = encriptPassword(korisnik.getLozinka());
			Korisnik k = new Korisnik(korisnik.getIme(), korisnik.getPrezime(), korisnik.getKorisnickoIme(), tempPassword, korisnik.getEmail(), korisnik.getTelefon(), korisnik.getGrad());
			k.setUloga(UlogaKorisnika.OBICAN_KORISNIK); // kad se registruje, postaje OBICAN KORISNIK
			k.setVerifikovan(false); // inicijalno nije verifikovan, mora potvrditi
			
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
	
	// logovanje
	public String logIn(KorisnikDTO korisnik) 
	{
		Korisnik k1 = userRepository.findOneByEmail(korisnik.getEmail());
		
		if(k1 != null) // znaci registrovan postoji sa tim mejlom
		{
			String tempPassword = "";
			try 
			{
				tempPassword = encriptPassword(korisnik.getLozinka());
				
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// ukoliko uneta lozinka nije ispravna, odnosno nije ista kao i pocetna
			if(!tempPassword.equals(k1.getLozinka())) 
			{
				return "greska";
			} else 
			{
				if(!k1.isVerifikovan()) // ukoliko je registrovan, ali nije potvrdio registraciju
				{
					return "greska1";
				} 
				
				else 
				{
					if(k1.getUloga().equals(UlogaKorisnika.OBICAN_KORISNIK))
						return "obicanKorisnik";
					
					else if(k1.getUloga().equals(UlogaKorisnika.ADMINISTRATOR_AVIOKOMPANIJE) && !k1.isPrvoLogovanje())
						return "prvoLogovanje"; // mora promeniti pass pre prvog logovanja
					else if(k1.getUloga().equals(UlogaKorisnika.ADMINISTRATOR_AVIOKOMPANIJE))
						return "adminAvio";
					else if(k1.getUloga().equals(UlogaKorisnika.ADMINISTRATOR_HOTELA))
						return "adminHotel";
					else if(k1.getUloga().equals(UlogaKorisnika.ADMINISTRATOR_RENT_A_CAR))
						return "adminRentACar";
					else
						return "adminSistem";
				}
			}
		} 
		else 
		{
			return "greska";
		}
		
	}
	
	// vraca korisnika na osnovu email-a
	/*
	public Korisnik returnKorisnikByEmail(KorisnikDTO k) 
	{
		Korisnik k1 = userRepository.findKorisnikByEmail(k.getEmail());
		return k1;
	}
	*/
	
	public Korisnik returnKorisnikByEmail(String email) 
	{
		Korisnik k1 = userRepository.findOneByEmail(email);
		return k1;
	}
	
	public Korisnik returnKorisnikById(KorisnikDTO k) 
	{
		return userRepository.findOneById(k.getId());
	}
	
	public String aktivirajNalog(String email) 
	{
		Korisnik k = userRepository.findOneByEmail(email);
		
		// FALI SLANJE EMAIL-A KORISNIKU I PRAVA POTVRDA
		
		k.setVerifikovan(true);	
		k.setUloga(UlogaKorisnika.OBICAN_KORISNIK);
    	
    	userRepository.save(k);
		
		return "verifikovan";
	}


	public String changePassword(KorisnikDTO kdto) {
		String enkriptovanaLozinka = "";
		try 
		{
			enkriptovanaLozinka = encriptPassword(kdto.getLozinka());
			
		} 
		catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return enkriptovanaLozinka;
	}


	public void save(Korisnik k) {
		userRepository.save(k);
		
	}


	/********* Ende - Olga *********/

}
