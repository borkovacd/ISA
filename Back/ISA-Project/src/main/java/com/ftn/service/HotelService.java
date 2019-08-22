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

	
	/******** Borkovac *********/
	
	public Hotel registerHotel(HotelDTO hotelDTO) {
		Hotel hotel = new Hotel();
		hotel.setNaziv(hotelDTO.getName());
		hotel.setAdresa(hotelDTO.getAddress());
		
		//Provera da li vec postoji hotel sa istim nazivom i adresom
		ArrayList<Hotel> existingHotels = (ArrayList<Hotel>) hotelRepository.findAll();
		for(Hotel existingHotel: existingHotels) {
			if(existingHotel.getNaziv().equals(hotel.getNaziv()) && existingHotel.getAdresa().equals(hotel.getAdresa())) {
				return null;
			}
		}
		
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

	public Hotel getHotel(Long id) {
		Hotel hotel = hotelRepository.getOne(id);
		return hotel;
	}
	
	/***********************/
	/******* Olga **********/
	
	// vraca sve hotele
	public ArrayList<Hotel> getSviHoteli()
	{
		return (ArrayList<Hotel>) hotelRepository.findAll();
	}
	
	// vraca hotele izabranog korisnika
		public ArrayList<Hotel> getHoteliKorisnik(Long idKorisnik) throws Exception 
		{
			ArrayList<Hotel> hoteli = new ArrayList<Hotel>();
			Korisnik korisnik = userRepository.getOne(idKorisnik);
			
			List<RezervacijaHotela> rezervacijeHotela = rezervacijaHotelaRepository.findAll();
			List<Hotel> sviHoteli = hotelRepository.findAll();
			
			
			// ukoliko ne postoji nijedan hotel
			if (sviHoteli.size() == 0) // sviHoteli == null
			{
				return hoteli ;
			}
			
			// ukoliko ne postoji nijedna rezervacija
			if (rezervacijeHotela.size() == 0) // rezervacijeHotela == null
			{
				return hoteli ;
			}
			
			for (int i = 0; i < rezervacijeHotela.size(); i++) // prolazak kroz sve rezervacije
			{
				if (rezervacijaHotelaRepository.findAll().get(i).getKorisnik().getId() == idKorisnik) // Da li je to rezervacija ovog korisnika?
				{
					for (Soba s : rezervacijaHotelaRepository.findAll().get(i).getSobe())
					{
						Hotel h = s.getHotel();
						hoteli.add(h);
						break ;
					}
				}
			}
			
			return hoteli;
			
		}

	/***********************/
}
