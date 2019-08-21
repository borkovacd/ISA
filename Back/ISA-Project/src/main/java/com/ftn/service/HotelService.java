package com.ftn.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.dto.HotelDTO;
import com.ftn.model.Korisnik;
import com.ftn.model.hotels.Hotel;
import com.ftn.model.hotels.RezervacijaHotela;
import com.ftn.model.hotels.Soba;
import com.ftn.repository.HotelRepository;
import com.ftn.repository.RezervacijaHotelaRepository;
import com.ftn.repository.SobaRepository;
import com.ftn.repository.UserRepository;

@Service
public class HotelService {
	
	@Autowired
	private HotelRepository hotelRepository;
	@Autowired
	private SobaRepository sobaRepository;
	@Autowired
	private RezervacijaHotelaRepository rezervacijaHotelaRepository;
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
			hotel.setAdministrator(administratorHotela);
		} else {
			return false;
		}
		hotelRepository.save(hotel);
		return true;
	}

	public ArrayList<Hotel> getHotelsByAdministrator(Long id) {
		ArrayList<Hotel> hotels = new ArrayList<Hotel>();
		Korisnik administrator = userRepository.getOne(id);
		for(int i=0; i<hotelRepository.findAll().size(); i++) {
			if(hotelRepository.findAll().get(i).getAdministrator().getKorisnickoIme().equals(administrator.getKorisnickoIme()))
				hotels.add(hotelRepository.findAll().get(i));
		}
		return hotels;
	}
	
	
	//MENJACE SE, NECE ADMIN MOCI DA SE PROMENI, MOZDA I POVRATNA VREDNOST
	public Hotel editHotel(Long id, HotelDTO hotelDTO) {
		Hotel hotel = hotelRepository.getOne(id);
		hotel.setNaziv(hotelDTO.getName());
		hotel.setAdresa(hotelDTO.getAddress());
		//DODATI PROVERU DA AKO I NAZIV I ADRESA VEC POSTOJE NE MOZE DA SE REGISTRUJE HOTEL
		hotel.setOpis(hotelDTO.getDescription());
		if(userRepository.findByKorisnickoIme(hotelDTO.getAdministratorHotela()) != null) {
			Korisnik administratorHotela = userRepository.findByKorisnickoIme(hotelDTO.getAdministratorHotela());
			hotel.setAdministrator(administratorHotela);
		} else {
			return null;
		}
		hotelRepository.save(hotel);
		return hotel;
	}

	public boolean checkIfHotelIsReserved(Long id) {
		boolean taken = false;
		List<RezervacijaHotela> rezervacije = rezervacijaHotelaRepository.findAll();
		for(Soba soba: sobaRepository.findAll()) {
			if(soba.getHotel().getId() == id) {
				for(RezervacijaHotela rezervacija : rezervacije) {
					for(Soba rezervisanaSoba: rezervacija.getSobe()) { //izmena u odnosu na xml, moze se rezervisati vise soba
						if(rezervisanaSoba.getId() == soba.getId()) {
							taken = true;
						}
					}
				}
			}
		}
		return taken;
	}
}
