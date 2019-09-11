package com.ftn.service;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.events.EndDocument;

import org.hibernate.dialect.pagination.FirstLimitHandler;
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
import com.ftn.model.rentacar.RezervacijaVozila;
import com.ftn.model.rentacar.Vozilo;
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
	
	private static DecimalFormat df2 = new DecimalFormat("#.##");

	
	/******** Borkovac *********/
	
	public ArrayList<Integer> getWeeklyGraphData(Long id, String yearString, String monthString) {
		
		ArrayList<Integer> values = new ArrayList<Integer>();
		
		LocalDate currentDate = LocalDate.now();
		System.out.println(" ------ Danasnji datum: " + currentDate + " ----- ");
		
		int year = Integer.parseInt(yearString);
		//System.out.println("Godina je: " + year);
		
		int month = Integer.parseInt(monthString);
		//System.out.println("Mesec je:" + month);
		
		Hotel hotel = hotelRepository.getOne(id);
		if(hotel == null) 
			return null;
		
		ArrayList<RezervacijaHotela> rezervacije = new ArrayList<RezervacijaHotela>();
		
		ArrayList<RezervacijaHotela> sveRezervacije = (ArrayList<RezervacijaHotela>) rezervacijaHotelaRepository.findAll();
		
		for(RezervacijaHotela rezervacija: sveRezervacije) {
			if(rezervacija.getSobe().get(0).getHotel().getId() == id) {
				if(rezervacija.getDatumKraja().isBefore(currentDate) || rezervacija.getDatumKraja().isEqual(currentDate))
					rezervacije.add(rezervacija);
			}
		}
		
		int broj1 = 0;
		int broj2 = 0;
		int broj3 = 0;
		int broj4 = 0;
		int broj5 = 0;
		
		LocalDate d1;
		LocalDate d2;
		if(month != 12) {
			d1 = LocalDate.of(year, month, 1);
			d2 = LocalDate.of(year, month+1, 1);
		} else {
			d1 = LocalDate.of(year, month, 1);
			d2 = LocalDate.of(year+1, 1, 1);
		}
		System.out.println("Pocetni datum:" + d1);
		System.out.println("Krajnji datum:" + d2);		
		for(RezervacijaHotela r: rezervacije) {
			LocalDate startDate = r.getDatumPocetka();
			LocalDate endDate = r.getDatumKraja();
			
			
			//System.out.println("*********************");
			LocalDate pocetakPrveNedelje = d1;
			//System.out.println("Pocetak prve nedelje: " + pocetakPrveNedelje);
			LocalDate pocetakDrugeNedelje = d1.plusDays(6);
			//System.out.println("Pocetak druge nedelje: " + pocetakDrugeNedelje);
			LocalDate pocetakTreceNedelje = pocetakDrugeNedelje.plusDays(6);
			//System.out.println("Pocetak trece nedelje: " + pocetakTreceNedelje);
			LocalDate pocetakCetvrteNedelje = pocetakTreceNedelje.plusDays(6);
			//System.out.println("Pocetak cetvrte nedelje: " + pocetakCetvrteNedelje);
			LocalDate pocetakPeteNedelje = pocetakCetvrteNedelje.plusDays(6);
			//System.out.println("Pocetak pete nedelje: " + pocetakPeteNedelje);
			LocalDate krajMeseca = d2.minusDays(1);
			//System.out.println("Kraj meseca: " + krajMeseca);
			
			
			while(!startDate.isAfter(endDate)) { //dok pocetni datum rezervacije ne dodje do posle krajnjeg datuma rezervacije
				//System.out.println("Trenutni datum: " + startDate);
				if((startDate.isAfter(pocetakPrveNedelje) || startDate.isEqual(pocetakPrveNedelje)) && (startDate.isBefore(pocetakDrugeNedelje) || startDate.isEqual(pocetakDrugeNedelje))) {
					//System.out.println("1");
					broj1 += r.getBrojGostiju();
				}
				else if((startDate.isAfter(pocetakDrugeNedelje)) && (startDate.isBefore(pocetakTreceNedelje) || startDate.isEqual(pocetakTreceNedelje))) {
					//System.out.println("2");
					broj2 += r.getBrojGostiju();
				}
				else if((startDate.isAfter(pocetakTreceNedelje)) && (startDate.isBefore(pocetakCetvrteNedelje) || startDate.isEqual(pocetakCetvrteNedelje))) {
					//System.out.println("3");
					broj3 += r.getBrojGostiju();
				}
				else if((startDate.isAfter(pocetakCetvrteNedelje)) && (startDate.isBefore(pocetakPeteNedelje) || startDate.isEqual(pocetakPeteNedelje))) {
					//System.out.println("4");
					broj4 += r.getBrojGostiju();
				}
				else if((startDate.isAfter(pocetakPeteNedelje)) && (startDate.isBefore(krajMeseca) || startDate.isEqual(krajMeseca))) {
					//System.out.println("5");
					broj5 += r.getBrojGostiju();
				}
				startDate = startDate.plusDays(1);
			}
		}
		
		values.add(broj1);
		values.add(broj2);
		values.add(broj3);
		values.add(broj4);
		values.add(broj5);
		return values;
	}

	public ArrayList<Integer> getDailyGraphData(Long id, String date) {
		
		LocalDate currentDate = LocalDate.now();
		System.out.println(" ------ Danasnji datum: " + currentDate + " ----- ");
		
		ArrayList<Integer> values = new ArrayList<Integer>();
		
		String europeanDatePattern = "yyyy-MM-dd";
		DateTimeFormatter europeanDateFormatter = DateTimeFormatter.ofPattern(europeanDatePattern);
		LocalDate d = LocalDate.parse(date, europeanDateFormatter);
		System.out.println("Datum: " + d);
		
		Hotel hotel = hotelRepository.getOne(id);
		if(hotel == null) 
			return null;
		
		ArrayList<RezervacijaHotela> rezervacije = new ArrayList<RezervacijaHotela>();
		
		ArrayList<RezervacijaHotela> sveRezervacije = (ArrayList<RezervacijaHotela>) rezervacijaHotelaRepository.findAll();
		
		for(RezervacijaHotela rezervacija: sveRezervacije) {
			if(rezervacija.getSobe().get(0).getHotel().getId() == id) {
				if(rezervacija.getDatumKraja().isBefore(currentDate) || rezervacija.getDatumKraja().isEqual(currentDate))
					rezervacije.add(rezervacija);
			}
		}
		
		int broj1 = 0;
		int broj2 = 0;
		int broj3 = 0;
		int broj4 = 0;
		int broj5 = 0;
		int broj6 = 0;
		int broj7 = 0;
		
		LocalDate dan1 = d;
		LocalDate dan2 = d.plusDays(1);
		LocalDate dan3 = dan2.plusDays(1);
		LocalDate dan4 = dan3.plusDays(1);
		LocalDate dan5 = dan4.plusDays(1);
		LocalDate dan6 = dan5.plusDays(1);
		LocalDate dan7 = dan6.plusDays(1);
		System.out.println("Pocetni datum: " + dan1);
		System.out.println("Krajnji datum: " + dan7);
		
		for(RezervacijaHotela r: rezervacije) {
			LocalDate startDate = r.getDatumPocetka();
			LocalDate endDate = r.getDatumKraja();
			
			while(!startDate.isAfter(endDate)) { //dok pocetni datum rezervacije ne dodje do posle krajnjeg datuma rezervacije
				//System.out.println("Trenutni datum: " + startDate);
				if(startDate.isEqual(dan1))
					broj1 += r.getBrojGostiju();
				else if(startDate.isEqual(dan2))
					broj2 += r.getBrojGostiju();
				else if(startDate.isEqual(dan3))
					broj3 += r.getBrojGostiju();
				else if(startDate.isEqual(dan4))
					broj4 += r.getBrojGostiju();
				else if(startDate.isEqual(dan5))
					broj5 += r.getBrojGostiju();
				else if(startDate.isEqual(dan6))
					broj6 += r.getBrojGostiju();
				else if(startDate.isEqual(dan7))
					broj7 += r.getBrojGostiju();
				startDate = startDate.plusDays(1);
			}
		}
		
		values.add(broj1);
		values.add(broj2);
		values.add(broj3);
		values.add(broj4);
		values.add(broj5);
		values.add(broj6);
		values.add(broj7);
		return values;
		
	}
	
	public ArrayList<Integer> getMonthyGraphData(Long id, String yearString) {
		
		LocalDate currentDate = LocalDate.now();
		System.out.println(" ------ Danasnji datum: " + currentDate + " ----- ");
		
		ArrayList<Integer> values = new ArrayList<Integer>();
		
		int year = Integer.parseInt(yearString);
		//System.out.println("Godina je: " + year);
		
		Hotel hotel = hotelRepository.getOne(id);
		if(hotel == null) 
			return null;
		
		ArrayList<RezervacijaHotela> rezervacije = new ArrayList<RezervacijaHotela>();
		
		ArrayList<RezervacijaHotela> sveRezervacije = (ArrayList<RezervacijaHotela>) rezervacijaHotelaRepository.findAll();
		
		for(RezervacijaHotela rezervacija: sveRezervacije) {
			if(rezervacija.getSobe().get(0).getHotel().getId() == id) {
				if(rezervacija.getDatumKraja().isBefore(currentDate) || rezervacija.getDatumKraja().isEqual(currentDate))
					rezervacije.add(rezervacija);
			}
		}
		
		//JANUAR-NOVEMBAR
		int broj = 0;
		for(int i=1; i<12; i++) {
			LocalDate d1 = LocalDate.of(year, i, 1);
			LocalDate d2 = LocalDate.of(year, i+1, 1);
			broj = 0;
			for(RezervacijaHotela r: rezervacije) {
				LocalDate startDate = r.getDatumPocetka();
				LocalDate endDate = r.getDatumKraja();
				while(!startDate.isAfter(endDate)) {
					//System.out.println("Trenutni datum: " + startDate);
					if((startDate.isAfter(d1) || startDate.isEqual(d1)) && (startDate.isBefore(d2))) {
						broj += r.getBrojGostiju();
					}
					startDate = startDate.plusDays(1);
				}
			}
			values.add(broj);
		}
		
		
		//DECEMBAR-JANUAR
		LocalDate d11 = LocalDate.of(year, 12, 1);
		LocalDate d22 = LocalDate.of(year+1, 1, 1);
		broj = 0;
		for(RezervacijaHotela r: rezervacije) {
			LocalDate startDate = r.getDatumPocetka();
			LocalDate endDate = r.getDatumKraja();
			while(!startDate.isAfter(endDate)) {
				//System.out.println("Trenutni datum: " + startDate);
				if((startDate.isAfter(d11) || startDate.isEqual(d11)) && (startDate.isBefore(d22))) {
					broj += r.getBrojGostiju();
				}
				startDate = startDate.plusDays(1);
			}
		}
		values.add(broj);
		
		return values;
		
	}
	
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
	
	public ArrayList<Hotel> getAllHotelsByAddress(Long idRezervacijeLeta) {
		
		//TO DO
		//Treba izvuci podatke od rezervaciji leta iz baze
		//Konkretno informaciju o mestu na koje se leti
		
		String mesto = "Beograd"; //privremeno
		
		ArrayList<Hotel> hotelsByAddress = new ArrayList<Hotel>();
		
		ArrayList<Hotel> hotels = (ArrayList<Hotel>) hotelRepository.findAll();
		for(Hotel hotel: hotels) {
			if(hotel.getAdresa().contains(mesto))
				hotelsByAddress.add(hotel);
		}
			
		return hotelsByAddress;
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
								if(d1.isBefore(rezervacija.getDatumKraja())) {
									slobodna = false;
								}
							} else if(d1.isEqual(rezervacija.getDatumPocetka())) {
								if(d2.isEqual(rezervacija.getDatumKraja())) {
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
	
	public Double getRevenues(Long id, String d1String, String d2String) {
		
		double revenues = 0;
		
		String europeanDatePattern = "yyyy-MM-dd";
		DateTimeFormatter europeanDateFormatter = DateTimeFormatter.ofPattern(europeanDatePattern);
		LocalDate d1 = LocalDate.parse(d1String, europeanDateFormatter);
		LocalDate d2 = LocalDate.parse(d2String, europeanDateFormatter);
		System.out.println("Datumi: " + d1 + " - " + d2);
		
		Hotel hotel = hotelRepository.getOne(id);
		if(hotel == null) 
			return null;
		
		ArrayList<RezervacijaHotela> rezervacije = new ArrayList<RezervacijaHotela>();
		ArrayList<RezervacijaHotela> sveRezervacije = (ArrayList<RezervacijaHotela>) rezervacijaHotelaRepository.findAll();
		
		for(RezervacijaHotela rezervacija: sveRezervacije) {
			if(rezervacija.getSobe().get(0).getHotel().getId() == id) {
				rezervacije.add(rezervacija);
			}
		}
		
		for(RezervacijaHotela r: rezervacije) {
			
			LocalDate startDateTemp = r.getDatumPocetka();
			LocalDate endDateTemp = r.getDatumKraja();
			int n = 0;
			double dnevnaCena = 0;
			while(!startDateTemp.isAfter(endDateTemp)) {
				n++;
				startDateTemp = startDateTemp.plusDays(1);
			}

			dnevnaCena = ((double) r.getCena())/((double) n);
			
			LocalDate startDate = r.getDatumPocetka();
			LocalDate endDate = r.getDatumKraja();

			while(!startDate.isAfter(endDate)) {
				//System.out.println("Trenutni datum: " + startDate);
				if((startDate.isAfter(d1) || startDate.isEqual(d1)) && (startDate.isBefore(d2)) || (startDate.isEqual(d2))) {
					revenues += dnevnaCena;
				}
				startDate = startDate.plusDays(1);
			}
		}
		
		df2.setRoundingMode(RoundingMode.DOWN);
		String val = df2.format(revenues);
		double retVal = Double.parseDouble(val);
		
		return retVal;
	}
	
	/***********************/
	/******* Olga **********/
	
	// vraca sve hotele
	public ArrayList<Hotel> getSviHoteli()
	{
		return (ArrayList<Hotel>) hotelRepository.findAll();
	}
	
	// vraca hotele izabranog korisnika
		public ArrayList<Hotel> getHoteliKorisnikStara(Long idKorisnik) throws Exception 
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
		
		// vraca rezervisane hotele korisnika
		public ArrayList<Hotel> getHoteliKorisnik(Long idKorisnik) throws Exception 
		{
			ArrayList<Hotel> hoteli = new ArrayList<Hotel>();
			Korisnik korisnik = userRepository.getOne(idKorisnik);
			
			List<RezervacijaHotela> rezervacijeHotela = rezervacijaHotelaRepository.findAll();
			List<Hotel> sviHoteli = hotelRepository.findAll();
			
			
			// ukoliko ne postoji nijedan hotel
			if (sviHoteli.size() == 0) // svaVozila == null
			{
				return hoteli ;
			}
			
			// ukoliko ne postoji nijedna rezervacija
			if (rezervacijeHotela.size() == 0) // rezervacijeVozila == null
			{
				return hoteli ;
			}
			
			for (int i = 0; i < rezervacijeHotela.size(); i++) // prolazak kroz sve rezervacije
			{
				if (rezervacijeHotela.get(i).getKorisnik().getId() == idKorisnik) // Da li je to rezervacija ovog korisnika?
				{
					for (int a = 0; a < rezervacijeHotela.get(i).getSobe().size(); a++)
					{
						if (!hoteli.contains(rezervacijeHotela.get(i).getSobe().get(a).getHotel())) {
							hoteli.add(rezervacijeHotela.get(i).getSobe().get(a).getHotel());
						}
					}
				}
			}
			
			return hoteli ;
			
		}

		public Hotel getHotelByNaziv(String naziv) {
			return hotelRepository.findByNaziv(naziv);
		}

		


		

		

	/***********************/
}
