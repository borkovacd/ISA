package com.ftn.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.dto.HotelDTO;
import com.ftn.dto.PretragaHotelaDTO;
import com.ftn.dto.VremenskiPeriodDTO;
import com.ftn.model.Korisnik;
import com.ftn.model.hotels.Hotel;
import com.ftn.model.hotels.Soba;
import com.ftn.model.rentacar.RentACar;
import com.ftn.repository.HotelRepository;
import com.ftn.service.HotelService;
import com.ftn.service.UserService;


@RestController
@RequestMapping(value = "/api/hotel")
public class HotelController {
	
	@Autowired
	private HotelService hotelService;
	
	@Autowired
	private HotelRepository hotelRepository;
	
	@Autowired
	private UserService userService ;
	
	
	/************ Borkovac **********/
	
	@PostMapping("/registerHotel")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<Hotel> registerHotel(@RequestBody HotelDTO hotelDTO) {
		Hotel hotel = hotelService.registerHotel(hotelDTO);
		return new ResponseEntity<Hotel>(hotel, HttpStatus.OK);
	}
	
	@GetMapping("/getHotelsByAdministrator/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<List<Hotel>> getHotelsByAdministrator(@PathVariable Long id) {
		ArrayList<Hotel> hotels = hotelService.getHotelsByAdministrator(id);
		return new ResponseEntity<List<Hotel>>(hotels, HttpStatus.OK);
	}
	
	@GetMapping("/getHotelsByAdministrator")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<List<Hotel>> getHotelsByAdministrator() {
		Korisnik administrator = userService.getCurrentUser();
		ArrayList<Hotel> hotels = hotelService.getHotelsByAdministrator(administrator.getId());
		return new ResponseEntity<List<Hotel>>(hotels, HttpStatus.OK);
	}
	
	@PutMapping("/izmeniHotel/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<Hotel> editHotel( @PathVariable Long id, @RequestBody HotelDTO hotelDTO) {
		Hotel hotel = hotelService.editHotel(id, hotelDTO);
		return new ResponseEntity<Hotel>(hotel, HttpStatus.OK);
	}
	
	@GetMapping("/checkIfReservedHotel/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public boolean checkIfReservedHotel(@PathVariable Long id) {
		//Ako hotel ne poseduje rezervisane sobe, taken je FALSE
		//u suprotnom taken ima vrednost TRUE
		boolean taken = hotelService.checkIfHotelIsReserved(id);
		return taken;
	}
	
	@GetMapping("/getHotel/{idHotela}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<Hotel> getHotel(@PathVariable Long idHotela) {
		Hotel hotel = hotelService.getHotel(idHotela);
		if (hotel == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(hotel, HttpStatus.OK);
	}
	
	@GetMapping("/getAllHotels")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<List<Hotel>> getAllHotels() {
		ArrayList<Hotel> hotels = hotelService.getAllHotels();
		return new ResponseEntity<List<Hotel>>(hotels, HttpStatus.OK);
	}
	
	@GetMapping("/getAllHotelsByAddress/{idRezervacijeLeta}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<List<Hotel>> getAllHotelsByAddress(@PathVariable Long idRezervacijeLeta) {
		ArrayList<Hotel> hotels = hotelService.getAllHotelsByAddress(idRezervacijeLeta);
		return new ResponseEntity<List<Hotel>>(hotels, HttpStatus.OK);
	}
	
	@PostMapping("/searchHotels")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<ArrayList<Hotel>> searchHotels(@RequestBody PretragaHotelaDTO phDTO) {
		ArrayList<Hotel> hoteli = hotelService.searchHotels(phDTO);
		return new ResponseEntity<ArrayList<Hotel>>(hoteli, HttpStatus.OK);
	}
	
	@GetMapping("/monthlyGraph/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<List<Integer>> getMonthyGraphData(@PathVariable Long id, @RequestParam String year) {
		ArrayList<Integer> monthsData = hotelService.getMonthyGraphData(id, year);
		return new ResponseEntity<List<Integer>>(monthsData, HttpStatus.OK);
	}
	
	@GetMapping("/weeklyGraph/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<List<Integer>> getWeeklyGraphData(@PathVariable Long id, @RequestParam String year, @RequestParam String month) {
		ArrayList<Integer> weeksData = hotelService.getWeeklyGraphData(id, year, month);
		return new ResponseEntity<List<Integer>>(weeksData, HttpStatus.OK);
	}
	
	@GetMapping("/dailyGraph/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<List<Integer>> getDailyGraphData(@PathVariable Long id, @RequestParam String date) {
		ArrayList<Integer> daysData = hotelService.getDailyGraphData(id, date);
		return new ResponseEntity<List<Integer>>(daysData, HttpStatus.OK);
	}
	
	@GetMapping("/getRevenues/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<Double> getRevenues(@PathVariable Long id, @RequestParam String d1, @RequestParam String d2) {
		Double retVal = hotelService.getRevenues(id, d1, d2);
		return new ResponseEntity<Double>(retVal, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/sort/{uslov}", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:4200")
	public List<Hotel> getSortedHotels(@PathVariable String uslov) {
		System.out.println("Uslov je " + uslov);
		
		List<Hotel> svi = hotelRepository.findAll();
		List<Hotel> sortiranaLista = new ArrayList<Hotel>();

		if (uslov.equals("NameA")) {
			// sortiraj po nazivu od A-Z
			Collections.sort(svi, Hotel.HotelNameComparator);
			for (Hotel R : svi) {
				sortiranaLista.add(R);
			}

		} else if (uslov.equals("NameD")) {
			// sortiraj po nazivu od Z-A
			Collections.sort(svi, Hotel.HotelNameComparator);
			for (int i = svi.size() - 1; i >= 0; i--) {
				sortiranaLista.add(svi.get(i));
			}

		} else if (uslov.equals("CityA")) {
			// sortiraj po gradu od A-Z
			Collections.sort(svi, Hotel.HotelCityComparator);
			for (Hotel R : svi) {
				sortiranaLista.add(R);
			}
		} else {
			// sortiraj po gradu od Z-A
			Collections.sort(svi, Hotel.HotelCityComparator);
			for (int i = svi.size() - 1; i >= 0; i--) {
				sortiranaLista.add(svi.get(i));
			}
		}

		return sortiranaLista;
	}
	
	/**********************/
	/******* Olga *********/
	
	// vraca sve hotele
	@GetMapping("/vratiSveHotele")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<List<Hotel>> vratiSveHotele() throws Exception 
	{
		ArrayList<Hotel> hoteli = hotelService.getSviHoteli();
		return new ResponseEntity<List<Hotel>>(hoteli, HttpStatus.OK);
	}
	
	// 2.7
		@GetMapping("/vratiHoteleKorisnikaStara/{id}")
		@CrossOrigin(origins = "http://localhost:4200")
		public ResponseEntity<List<Hotel>> vratiHoteleKorisnikaStara(@PathVariable Long id) throws Exception 
		{
			ArrayList<Hotel> hoteli = hotelService.getHoteliKorisnik(id);
			return new ResponseEntity<List<Hotel>>(hoteli, HttpStatus.OK);
		}
		
		// 2.7
		@GetMapping("/vratiHoteleKorisnika")
		@CrossOrigin(origins = "http://localhost:4200")
		public ResponseEntity<List<Hotel>> vratiHoteleKorisnika() throws Exception 
		{
			ArrayList<Hotel> hoteli = hotelService.getHoteliKorisnik(userService.getCurrentUser().getId());
			return new ResponseEntity<List<Hotel>>(hoteli, HttpStatus.OK);
		}		
	/**********************/

	
	

}
