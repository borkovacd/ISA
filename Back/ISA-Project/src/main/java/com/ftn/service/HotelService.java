package com.ftn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.dto.HotelDTO;
import com.ftn.model.Korisnik;
import com.ftn.model.hotels.Hotel;
import com.ftn.repository.HotelRepository;
import com.ftn.repository.UserRepository;

@Service
public class HotelService {
	
	@Autowired
	private HotelRepository hotelRepository;
	@Autowired
	private UserRepository userRepository;

	public boolean registerHotel(HotelDTO hotelDTO) {
		Hotel hotel = new Hotel();
		hotel.setNaziv(hotelDTO.getName());
		hotel.setAdresa(hotelDTO.getAddress());
		//DODATI PROVERU DA AKO I NAZIV I ADRESA VEC POSTOJE NE MOZE DA SE REGISTRUJE HOTEL
		hotel.setOpis(hotelDTO.getDescription());
		if(userRepository.findByKorisnickoIme(hotelDTO.getAdministratorHotela()) != null) {
			Korisnik administratorHotela = userRepository.findByKorisnickoIme(hotelDTO.getAdministratorHotela());
			hotel.setKorisnik(administratorHotela);
		} else {
			return false;
		}
		hotelRepository.save(hotel);
		return true;

	}
	

}
