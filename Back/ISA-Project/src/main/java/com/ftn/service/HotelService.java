package com.ftn.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.dto.HotelDTO;
import com.ftn.dto.PretragaHotelaDTO;
import com.ftn.model.Korisnik;
import com.ftn.model.hotels.CenovnikHotela;
import com.ftn.model.hotels.Hotel;
import com.ftn.model.hotels.RezervacijaHotela;
import com.ftn.model.hotels.Soba;
import com.ftn.model.hotels.StavkaCenovnikaHotela;
import com.ftn.repository.CenovnikHotelaRepository;
import com.ftn.repository.HotelRepository;
import com.ftn.repository.RezervacijaHotelaRepository;
import com.ftn.repository.SobaRepository;
import com.ftn.repository.StavkaCenovnikaHotelaRepository;
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
	@Autowired
	private CenovnikHotelaRepository cenovnikHotelaRepository;
	@Autowired
	private StavkaCenovnikaHotelaRepository stavkaCenovnikaHotelaRepository;

	
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
	
	public Hotel editHotel(Long id, HotelDTO hotelDTO) {
		Hotel hotel = hotelRepository.getOne(id);
		System.out.println(hotel.getNaziv());
		hotel.setNaziv(hotelDTO.getName());
		hotel.setAdresa(hotelDTO.getAddress());
		//Provera da li vec postoji hotel sa istim nazivom i adresom
		ArrayList<Hotel> existingHotels = (ArrayList<Hotel>) hotelRepository.findAll();
		for(Hotel existingHotel: existingHotels) {
			if(existingHotel.getId() != id) 
				if(existingHotel.getNaziv().equals(hotel.getNaziv()) && existingHotel.getAdresa().equals(hotel.getAdresa())) {
					return null;
			}
		}
		hotel.setOpis(hotelDTO.getDescription());
		System.out.println("novi: "  + hotel.getNaziv());
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
	
	public ArrayList<Hotel> getAllHotels() {
		ArrayList<Hotel> hotels = (ArrayList<Hotel>) hotelRepository.findAll();
		return hotels;
	}
	
	public ArrayList<Hotel> searchHotels(PretragaHotelaDTO phDTO) {
		
		ArrayList<Hotel> hoteli = new ArrayList<Hotel>();
		
		ArrayList<Hotel> hoteliPoDatumu = new ArrayList<Hotel>();
		
		String europeanDatePattern = "yyyy-MM-dd";
		DateTimeFormatter europeanDateFormatter = DateTimeFormatter.ofPattern(europeanDatePattern);
		LocalDate d1 = LocalDate.parse(phDTO.getStartDate(), europeanDateFormatter);
		LocalDate d2 = LocalDate.parse(phDTO.getEndDate(), europeanDateFormatter);
		
		//Provera datuma
		if(d1.isAfter(d2)) { //da li je pocetni datum posle krajnjeg datuma
			return null;
		}
		
		for(Hotel hotel: hotelRepository.findAll()) {
			ArrayList<Soba> sobeHotela = new ArrayList<Soba>();
			ArrayList<Soba> sveSobe = (ArrayList<Soba>) sobaRepository.findAll();
			for(Soba soba : sveSobe) {
				if(soba.getHotel().getId() == hotel.getId()) {
					sobeHotela.add(soba);
				}
			}
			List<RezervacijaHotela> rezervacije = rezervacijaHotelaRepository.findAll();
			List<CenovnikHotela> cenovnici = cenovnikHotelaRepository.findAll();
			List<StavkaCenovnikaHotela> stavkeCenovnika = stavkaCenovnikaHotelaRepository.findAll();
			for(Soba soba: sobeHotela) {
				boolean slobodna = true;
				for(RezervacijaHotela rezervacija : rezervacije) {
					for(Soba rezervisanaSoba: rezervacija.getSobe()) { 
						if(rezervisanaSoba.getId() == soba.getId()) { //Da li se soba nalazi medju rezervacijama
							if(d1.isBefore(rezervacija.getDatumPocetka())) {
								if(d2.isAfter(rezervacija.getDatumPocetka())) {
									slobodna = false;
								}
							} else if(d1.isAfter(rezervacija.getDatumPocetka())) {
								if(d2.isBefore(rezervacija.getDatumKraja())) {
									slobodna = false;
								}
							}
						}
					}
				}
				if(slobodna == true) 
					for(CenovnikHotela cenovnik : cenovnici) 
						if(d1.isAfter(cenovnik.getPocetakVazenja()) || d1.isEqual(cenovnik.getPocetakVazenja())) 
							if(d2.isBefore(cenovnik.getPrestanakVazenja()) || d2.isEqual(cenovnik.getPrestanakVazenja())) 
								if(cenovnik.getHotel().getId() == hotel.getId())  //ako je cenovnik hotela u kojem je slobodna soba
									for(StavkaCenovnikaHotela stavkaCenovnika : stavkeCenovnika) 
										if(stavkaCenovnika.getCenovnik().getId() == cenovnik.getId()) 
											if(stavkaCenovnika.getTipSobe() == soba.getTipSobe()) 
												if(!hoteliPoDatumu.contains(hotel)) 
													hoteliPoDatumu.add(hotel);
			}
		}

		ArrayList<Hotel> hoteliPoNazivu = new ArrayList<Hotel>();
		String naziv = null;
		if(phDTO.getHotelName() != "") {
			naziv = phDTO.getHotelName();
			naziv = naziv.toUpperCase();
		}
		
		if(naziv != null) {
			for(Hotel hotel: hoteliPoDatumu) {
				if(hotel.getNaziv().toUpperCase().equals(naziv)) {
					hoteliPoNazivu.add(hotel);
				}
			}
		}
		
		ArrayList<Hotel> hoteliPoLokaciji = new ArrayList<Hotel>();
		String lokacija = null;
		if(phDTO.getHotelLocation() != "") {
			lokacija = phDTO.getHotelLocation();
			lokacija = lokacija.toUpperCase();
		}
		
		if(lokacija != null) {
			if(naziv != null) {
				for(Hotel hotel: hoteliPoNazivu) {
					if(hotel.getAdresa().toUpperCase().contains(lokacija)) {
						hoteli.add(hotel);
					}
				}
			} else {
				for(Hotel hotel: hoteliPoDatumu) {
					if(hotel.getAdresa().toUpperCase().contains(lokacija)) {
						hoteli.add(hotel);
					}
				}
			}
		} else {
			hoteli = hoteliPoNazivu;
		}
		
		if(naziv==null && lokacija==null) {
			hoteli = hoteliPoDatumu;
		}
		
		
		return hoteli;
		
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
