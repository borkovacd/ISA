package com.ftn.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.dto.RentCarDTO;
import com.ftn.model.Korisnik;
import com.ftn.model.hotels.RezervacijaHotela;
import com.ftn.model.hotels.Soba;
import com.ftn.model.rentacar.RentACar;
import com.ftn.model.rentacar.RezervacijaVozila;
import com.ftn.model.rentacar.Vozilo;
import com.ftn.repository.RentCarRepository;
import com.ftn.repository.RezervacijaVozilaRepository;
import com.ftn.repository.UserRepository;
import com.ftn.repository.VoziloRepository;

@Service
public class RentACarService {
	
	@Autowired
	private RentCarRepository rentCarRepository;
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RezervacijaVozilaRepository rezVozRepository ;
	
	@Autowired 
	private VoziloRepository voziloRepository ;

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
	
	// provera da li u rent servisu ima rezervisanih vozila, prosledjen id servisa
	public boolean checkIfRentIsReserved(Long id) 
	{
		boolean taken = false;
		List<RezervacijaVozila> rezervacije = rezVozRepository.findAll();
		
		for(Vozilo v: voziloRepository.findAll()) // prolazak kroz sva vozila
		{
			if(v.getRentACar().getRentACarId() == id) // ogranicim se samo na vozila iz tog rent-a-car servisa
			{
				for(RezervacijaVozila rezervacija : rezervacije) // prolazak kroz sve rezervacije
				{
					// ukoliko se to vozilo nalazi medju rezervacijama i ukoliko je u tom momentu rezervisano
					if(rezervacija.getVozilo().getVoziloId() == v.getVoziloId() && v.isRezervisano() == true) 
					{
						taken = true;
					}
				
				}
			}
		}
		
		return taken;
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
