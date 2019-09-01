package com.ftn.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.dto.RentCarDTO;
import com.ftn.model.Korisnik;
import com.ftn.model.rentacar.RentACar;
import com.ftn.repository.RentCarRepository;
import com.ftn.repository.UserRepository;

@Service
public class RentACarService {
	
	@Autowired
	private RentCarRepository rentCarRepository;
	@Autowired
	private UserRepository userRepository;

	/****** Borkovac *******/
	
	public RentACar registerRentCar(RentCarDTO rentCarDTO) {
		RentACar rentCar = new RentACar();
		rentCar.setNaziv(rentCarDTO.getName());
		rentCar.setAdresa(rentCarDTO.getAddress());
		
		//Provera da li vec postoji rent sa istim nazivom i adresom
		ArrayList<RentACar> existingServices = (ArrayList<RentACar>) rentCarRepository.findAll();
		for(RentACar existingService: existingServices) {
			if(existingService.getNaziv().equals(rentCar.getNaziv()) && existingService.getAdresa().equals(rentCar.getAdresa())) {
				return null;
			}
		}
		
		rentCar.setOpis(rentCarDTO.getDescription());
		if(userRepository.findByKorisnickoIme(rentCarDTO.getAdministratorRentCar()) != null) {
			Korisnik administratorRentACar = userRepository.findByKorisnickoIme(rentCarDTO.getAdministratorRentCar());
			rentCar.setAdministrator(administratorRentACar);
		} else {
			return null;
		}
		rentCarRepository.save(rentCar);
		return rentCar;
	}
	
	/********************/
	
	/******* Olga *******/
	
	// vraca sve rent-a-car servise
	public ArrayList<RentACar> vratiSveServise()
	{
		return (ArrayList<RentACar>) rentCarRepository.findAll();
	}
	
	// vraca vozilo na osnovu imena
	public RentACar findByNaziv(String naziv)
	{
		return rentCarRepository.findOneByNaziv(naziv);
	}
	
	// vraca servis na osnovu id-ja
	public RentACar findByRentACarId(Long id)
	{
		return rentCarRepository.findOneByRentACarId(id);
	}
	
	// izmena servisa
	public RentACar editRent(Long id, RentCarDTO dto) 
	{
		RentACar rent = rentCarRepository.findOneByRentACarId(id);
		rent.setNaziv(dto.getName());
		rent.setAdresa(dto.getAddress());
		
		ArrayList<RentACar> allRents = (ArrayList<RentACar>) rentCarRepository.findAll();

		for(RentACar r: allRents) 
		{
			// ukoliko vec postoji sa zadatom adresom i imenom, a razlicit id
			if(r.getRentACarId() != id) 
				if(r.getNaziv().equals(rent.getNaziv()) && r.getAdresa().equals(rent.getAdresa())) {
					return null;
			}
		}
		
		rent.setOpis(dto.getDescription());
		rentCarRepository.save(rent);
		return rent;
	}

	// vraca sve servise od tog administratora
	public ArrayList<RentACar> getRentsByAdministrator(Long id)
	{
		ArrayList<RentACar> newRents = new ArrayList<RentACar>();
		Korisnik administrator = userRepository.findOneById(id);
		
		for (int i = 0; i < rentCarRepository.findAll().size(); i++)
		{
			if (rentCarRepository.findAll().get(i).getAdministrator().getKorisnickoIme().equals(administrator.getKorisnickoIme()))
			{
				newRents.add(rentCarRepository.findAll().get(i));
			}
		}
		
		return newRents ;
	}
	/********************/
}
