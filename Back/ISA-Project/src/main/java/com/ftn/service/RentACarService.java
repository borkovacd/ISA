package com.ftn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.dto.HotelDTO;
import com.ftn.dto.RentCarDTO;
import com.ftn.model.Korisnik;
import com.ftn.model.hotels.Hotel;
import com.ftn.model.rentacar.RentACar;
import com.ftn.repository.HotelRepository;
import com.ftn.repository.RentCarRepository;
import com.ftn.repository.UserRepository;

@Service
public class RentACarService {
	
	@Autowired
	private RentCarRepository rentCarRepository;
	@Autowired
	private UserRepository userRepository;

	public boolean registerRentCar(RentCarDTO rentCarDTO) {
		RentACar rentCar = new RentACar();
		rentCar.setNaziv(rentCarDTO.getName());
		rentCar.setAdresa(rentCarDTO.getAddress());
		//DODATI PROVERU DA AKO I NAZIV I ADRESA VEC POSTOJE NE MOZE DA SE REGISTRUJE HOTEL
		rentCar.setOpis(rentCarDTO.getDescription());
		if(userRepository.findByKorisnickoIme(rentCarDTO.getAdministratorRentCar()) != null) {
			Korisnik administratorRentACar = userRepository.findByKorisnickoIme(rentCarDTO.getAdministratorRentCar());
			rentCar.setAdministrator(administratorRentACar);
		} else {
			return false;
		}
		rentCarRepository.save(rentCar);
		return true;
	}


}
