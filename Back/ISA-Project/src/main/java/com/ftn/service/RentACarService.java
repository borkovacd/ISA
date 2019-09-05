package com.ftn.service;

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

	/********************/
}
