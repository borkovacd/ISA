package com.ftn.service;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.dto.PretragaRentDTO;
import com.ftn.dto.RentCarDTO;
import com.ftn.enums.TipVozila;
import com.ftn.model.Korisnik;
import com.ftn.model.hotels.Hotel;
import com.ftn.model.rentacar.CenovnikRentACar;
import com.ftn.model.rentacar.RentACar;
import com.ftn.model.rentacar.RezervacijaVozila;
import com.ftn.model.rentacar.StavkaCenovnikaRent;
import com.ftn.model.rentacar.Vozilo;
import com.ftn.repository.CenovnikRentACarRepository;
import com.ftn.repository.RentCarRepository;
import com.ftn.repository.RezervacijaVozilaRepository;
import com.ftn.repository.StavkaCenovnikaRentRepository;
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
	
	@Autowired
	private CenovnikRentACarRepository cenRentRepository ;
	
	@Autowired
	private StavkaCenovnikaRentRepository stavkaRentRepository ;
	
	private static DecimalFormat df2 = new DecimalFormat("#.##");

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
					// ukoliko se to vozilo nalazi medju rezervacijama
					if(rezervacija.getVozilo().getVoziloId() == v.getVoziloId()) 
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
	
	// pretraga rent-a-car servisa po parametrima
	public ArrayList<RentACar> searchRents(PretragaRentDTO phDTO) {
		
		ArrayList<RentACar> servisi = new ArrayList<RentACar>();
		
		ArrayList<RentACar> servisiPoDatumu = new ArrayList<RentACar>();
		
		String europeanDatePattern = "yyyy-MM-dd";
		DateTimeFormatter europeanDateFormatter = DateTimeFormatter.ofPattern(europeanDatePattern);
		LocalDate d1 = LocalDate.parse(phDTO.getStartDate(), europeanDateFormatter);
		LocalDate d2 = LocalDate.parse(phDTO.getEndDate(), europeanDateFormatter);
		
		//Provera datuma
		if(d1.isAfter(d2)) { //da li je pocetni datum posle krajnjeg datuma
			return null;
		}
		
		for(RentACar rent: rentCarRepository.findAll()) 
		{
			ArrayList<Vozilo> vozilaRent = new ArrayList<Vozilo>();
			ArrayList<Vozilo> svaVozila = (ArrayList<Vozilo>) voziloRepository.findAll();
			
			for(Vozilo voz: svaVozila) 
			{
				if(voz.getRentACar().getRentACarId() == rent.getRentACarId()) 
				{
					vozilaRent.add(voz);
				}
			}
			List<RezervacijaVozila> rezervacije = rezVozRepository.findAll();
			List<CenovnikRentACar> cenovnici = cenRentRepository.findAll();
			
			List<StavkaCenovnikaRent> stavkeCenovnika = stavkaRentRepository.findAll();
			for(Vozilo v: vozilaRent) 
			{
				boolean slobodna = true;
				for(RezervacijaVozila rezervacija : rezervacije) 
				{
					Vozilo rezVozilo = rezervacija.getVozilo();
						if(rezVozilo.getVoziloId() == v.getVoziloId()) 
						{ //Da li se vozilo nalazi medju rezervacijama
							if(d1.isBefore(rezervacija.getDatumPreuzimanja())) {
								if(d2.isAfter(rezervacija.getDatumPreuzimanja())) {
									slobodna = false;
								}
							} else if(d1.isAfter(rezervacija.getDatumPreuzimanja())) {
								if(d2.isBefore(rezervacija.getDatumVracanja())) {
									slobodna = false;
								}
							}
						}
					
				}
				
				if(slobodna == true) 
					for(CenovnikRentACar cenovnik : cenovnici) 
						if(d1.isAfter(cenovnik.getPocetakVazenja()) || d1.isEqual(cenovnik.getPocetakVazenja())) 
							if(d2.isBefore(cenovnik.getPrestanakVazenja()) || d2.isEqual(cenovnik.getPrestanakVazenja())) 
								if(cenovnik.getRentACar().getRentACarId() == rent.getRentACarId())  //ako je cenovnik servisa u kom je slobodno vozilo
									for(StavkaCenovnikaRent stavkaCenovnika : stavkeCenovnika) 
										if(stavkaCenovnika.getCenovnik().getId() == cenovnik.getId()) 
											if(stavkaCenovnika.getTipVozila() == v.getTip()) 
												if(!servisiPoDatumu.contains(rent)) 
													servisiPoDatumu.add(rent);
			}
		}

		ArrayList<RentACar> servisiPoNazivu = new ArrayList<RentACar>();
		String naziv = null;
		if(phDTO.getRentName() != "") {
			naziv = phDTO.getRentName();
			naziv = naziv.toUpperCase();
		}
		
		if(naziv != null) {
			for(RentACar rentic: servisiPoDatumu) {
				if(rentic.getNaziv().toUpperCase().equals(naziv)) {
					servisiPoNazivu.add(rentic);
				}
			}
		}
		
		ArrayList<RentACar> servisiPoLokaciji = new ArrayList<RentACar>();
		String lokacija = null;
		if(phDTO.getRentLocation() != "") {
			lokacija = phDTO.getRentLocation();
			lokacija = lokacija.toUpperCase();
		}
		
		if(lokacija != null) {
			if(naziv != null) {
				for(RentACar rac: servisiPoNazivu) {
					if(rac.getAdresa().toUpperCase().contains(lokacija)) {
						servisi.add(rac);
					}
				}
			} else {
				for(RentACar rc: servisiPoDatumu) {
					if(rc.getAdresa().toUpperCase().contains(lokacija)) {
						servisi.add(rc);
					}
				}
			}
		} else {
			servisi = servisiPoNazivu;
		}
		
		if(naziv==null && lokacija==null) {
			servisi = servisiPoDatumu;
		}
		
		
		return servisi;
		
	}
	
	// vraca listu tipova vozila za jedan rent-a-car servis
	public ArrayList<TipVozila> getTipoviVozilaRent(Long idRent) 
	{
		ArrayList<TipVozila> tipoviVozila = new ArrayList<TipVozila>();
		RentACar rent = rentCarRepository.getOne(idRent);
		
		if(rent == null)
			return tipoviVozila;
		
		ArrayList<Vozilo> svaVozila = (ArrayList<Vozilo>) voziloRepository.findAll();
		ArrayList<Vozilo> vozilaRent = new ArrayList<Vozilo>();
		
		// ukoliko je vozilo iz tog servisa
		for(Vozilo v: svaVozila) 
		{
			if(v.getRentACar().getRentACarId() == rent.getRentACarId())
				vozilaRent.add(v);
		}
		
		
		for(Vozilo v: vozilaRent) 
		{
			if(!tipoviVozila.contains(v.getTip())) // ukoliko taj tip nije vec dodat
				tipoviVozila.add(v.getTip());
		}
		return tipoviVozila;

	}
	
	// GRAFIKONI
	
	// prosledjen id rent-a-car servisa
	public ArrayList<Integer> getWeeklyGraphDataRent(Long id, String yearString, String monthString) {
		
		ArrayList<Integer> values = new ArrayList<Integer>();
		
		LocalDate currentDate = LocalDate.now();
		System.out.println(" ------ Danasnji datum: " + currentDate + " ----- ");
		
		int year = Integer.parseInt(yearString);
		//System.out.println("Godina je: " + year);
		
		int month = Integer.parseInt(monthString);
		//System.out.println("Mesec je:" + month);
		
		RentACar rent = rentCarRepository.getOne(id);
		if(rent == null) 
			return null;
		
		ArrayList<RezervacijaVozila> rezervacije = new ArrayList<RezervacijaVozila>();
		
		ArrayList<RezervacijaVozila> sveRezervacije = (ArrayList<RezervacijaVozila>) rezVozRepository.findAll();
		
		for(RezervacijaVozila rezervacija: sveRezervacije) {
			if(rezervacija.getVozilo().getRentACar().getRentACarId() == id) {
				if(rezervacija.getDatumVracanja().isBefore(currentDate) || rezervacija.getDatumVracanja().isEqual(currentDate)) 
				{
					rezervacije.add(rezervacija);
				}
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
		
		for(RezervacijaVozila r : rezervacije) {
			LocalDate startDate = r.getDatumPreuzimanja();
			LocalDate endDate = r.getDatumVracanja();
			
			
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
					broj1 += 1;
				}
				else if((startDate.isAfter(pocetakDrugeNedelje)) && (startDate.isBefore(pocetakTreceNedelje) || startDate.isEqual(pocetakTreceNedelje))) {
					//System.out.println("2");
					broj2 += 1;
				}
				else if((startDate.isAfter(pocetakTreceNedelje)) && (startDate.isBefore(pocetakCetvrteNedelje) || startDate.isEqual(pocetakCetvrteNedelje))) {
					//System.out.println("3");
					broj3 += 1;
				}
				else if((startDate.isAfter(pocetakCetvrteNedelje)) && (startDate.isBefore(pocetakPeteNedelje) || startDate.isEqual(pocetakPeteNedelje))) {
					//System.out.println("4");
					broj4 += 1;
				}
				else if((startDate.isAfter(pocetakPeteNedelje)) && (startDate.isBefore(krajMeseca) || startDate.isEqual(krajMeseca))) {
					//System.out.println("5");
					broj5 += 1;
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

	// prosledjen id rent-a-car
	public ArrayList<Integer> getDailyGraphDataRent(Long id, String date) {
		
		LocalDate currentDate = LocalDate.now();
		System.out.println(" ------ Danasnji datum: " + currentDate + " ----- ");
		
		ArrayList<Integer> values = new ArrayList<Integer>();
		
		String europeanDatePattern = "yyyy-MM-dd";
		DateTimeFormatter europeanDateFormatter = DateTimeFormatter.ofPattern(europeanDatePattern);
		LocalDate d = LocalDate.parse(date, europeanDateFormatter);
		System.out.println("Datum: " + d);
		
		RentACar rent = rentCarRepository.getOne(id);
		if(rent == null) 
			return null;
		
		ArrayList<RezervacijaVozila> rezervacije = new ArrayList<RezervacijaVozila>();
		
		ArrayList<RezervacijaVozila> sveRezervacije = (ArrayList<RezervacijaVozila>) rezVozRepository.findAll();
		
		for(RezervacijaVozila rezervacija: sveRezervacije) {
			if(rezervacija.getVozilo().getRentACar().getRentACarId() == id) {
				if(rezervacija.getDatumVracanja().isBefore(currentDate) || rezervacija.getDatumVracanja().isEqual(currentDate)) 
				{
					rezervacije.add(rezervacija);
				}
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
		
		for(RezervacijaVozila r: rezervacije) {
			LocalDate startDate = r.getDatumPreuzimanja();
			LocalDate endDate = r.getDatumVracanja();
			
			while(!startDate.isAfter(endDate)) { //dok pocetni datum rezervacije ne dodje do posle krajnjeg datuma rezervacije
				//System.out.println("Trenutni datum: " + startDate);
				if(startDate.isEqual(dan1))
					broj1 += 1;
				else if(startDate.isEqual(dan2))
					broj2 += 1;
				else if(startDate.isEqual(dan3))
					broj3 += 1;
				else if(startDate.isEqual(dan4))
					broj4 += 1;
				else if(startDate.isEqual(dan5))
					broj5 += 1;
				else if(startDate.isEqual(dan6))
					broj6 += 1;
				else if(startDate.isEqual(dan7))
					broj7 += 1;
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
	
	// prosledjen id rent-a-car
	public ArrayList<Integer> getMonthyGraphDataRent(Long id, String yearString) {
		
		
		LocalDate currentDate = LocalDate.now();
		System.out.println(" ------ Danasnji datum: " + currentDate + " ----- ");
		
		ArrayList<Integer> values = new ArrayList<Integer>();
		
		int year = Integer.parseInt(yearString);
		//System.out.println("Godina je: " + year);
		
		RentACar rent = rentCarRepository.getOne(id);
		if(rent == null) 
			return null;
		
		ArrayList<RezervacijaVozila> rezervacije = new ArrayList<RezervacijaVozila>();
		
		ArrayList<RezervacijaVozila> sveRezervacije = (ArrayList<RezervacijaVozila>) rezVozRepository.findAll();
		
		for(RezervacijaVozila rezervacija: sveRezervacije) {
			if(rezervacija.getVozilo().getRentACar().getRentACarId() == id) {
				if(rezervacija.getDatumVracanja().isBefore(currentDate) || rezervacija.getDatumVracanja().isEqual(currentDate)) 
				{
					rezervacije.add(rezervacija);
				}
			}
		}
		
		//JANUAR-NOVEMBAR
		int broj = 0;
		for(int i=1; i<12; i++) {
			LocalDate d1 = LocalDate.of(year, i, 1);
			LocalDate d2 = LocalDate.of(year, i+1, 1);
			broj = 0;
			for(RezervacijaVozila r: rezervacije) {
				LocalDate startDate = r.getDatumPreuzimanja();
				LocalDate endDate = r.getDatumVracanja();
				while(!startDate.isAfter(endDate)) {
					//System.out.println("Trenutni datum: " + startDate);
					if((startDate.isAfter(d1) || startDate.isEqual(d1)) && (startDate.isBefore(d2))) {
						broj += 1; 
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
		for(RezervacijaVozila r: rezervacije) {
			LocalDate startDate = r.getDatumPreuzimanja();
			LocalDate endDate = r.getDatumVracanja();
			while(!startDate.isAfter(endDate)) {
				//System.out.println("Trenutni datum: " + startDate);
				if((startDate.isAfter(d11) || startDate.isEqual(d11)) && (startDate.isBefore(d22))) {
					broj += 1; 
				}
				startDate = startDate.plusDays(1);
			}
		}
		values.add(broj);
		
		return values;
		
	}
	
	// Prihodi rent-a-car servisa u izabranom periodu
	public Double getRevenuesRent(Long idRent, String d1String, String d2String) 
	{
		LocalDate currentDate = LocalDate.now();
		System.out.println(" ------ Danasnji datum: " + currentDate + " ----- ");
		
		
		double revenues = 0;
		
		String europeanDatePattern = "yyyy-MM-dd";
		DateTimeFormatter europeanDateFormatter = DateTimeFormatter.ofPattern(europeanDatePattern);
		LocalDate d1 = LocalDate.parse(d1String, europeanDateFormatter);
		LocalDate d2 = LocalDate.parse(d2String, europeanDateFormatter);
		System.out.println("Datumi: " + d1 + " - " + d2);
		
		RentACar rent = rentCarRepository.getOne(idRent);
		
		if(rent == null) 
			return null;
		
		ArrayList<RezervacijaVozila> rezervacije = new ArrayList<RezervacijaVozila>();
		ArrayList<RezervacijaVozila> sveRezervacije = (ArrayList<RezervacijaVozila>) rezVozRepository.findAll();
		
		for(RezervacijaVozila rezervacija: sveRezervacije) 
		{
			if(rezervacija.getVozilo().getRentACar().getRentACarId() == idRent) 
			{
				if(rezervacija.getDatumVracanja().isBefore(currentDate) || rezervacija.getDatumVracanja().isEqual(currentDate))
					rezervacije.add(rezervacija);
			}
		}
		
		for(RezervacijaVozila r: rezervacije) 
		{
			
			LocalDate startDateTemp = r.getDatumPreuzimanja();
			LocalDate endDateTemp = r.getDatumVracanja();
			int n = 0;
			double dnevnaCena = 0;
			
			while(!startDateTemp.isAfter(endDateTemp)) 
			{
				n++;
				startDateTemp = startDateTemp.plusDays(1);
			}

			dnevnaCena = ((double) r.getCena())/((double) n);
			
			LocalDate startDate = r.getDatumPreuzimanja();
			LocalDate endDate = r.getDatumVracanja();

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
	
	// NA OSNOVU MESTA LETA SE FORMIRA BRZA REZERVACIJA
	public ArrayList<RentACar> getAllRentsByAddress(Long idRezervacijeLeta) 
	{
		
		//TO DO
		//Treba izvuci podatke od rezervaciji leta iz baze
		//Konkretno informaciju o mestu na koje se leti
		
		String mesto = "Beograd"; //privremeno
		
		ArrayList<RentACar> rentsByAddress = new ArrayList<RentACar>();
		
		ArrayList<RentACar> rents = (ArrayList<RentACar>) rentCarRepository.findAll();
		
		for(RentACar rent: rents) {
			if(rent.getAdresa().contains(mesto))
				rentsByAddress.add(rent);
		}
			
		return rentsByAddress;
	}

	
	


	/********************/
}
