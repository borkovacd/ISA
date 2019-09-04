package com.ftn.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.dto.RezervacijaVozilaDTO;
import com.ftn.model.Korisnik;

import com.ftn.model.rentacar.CenovnikRentACar;
import com.ftn.model.rentacar.Lokacija;
import com.ftn.model.rentacar.RezervacijaVozila;
import com.ftn.model.rentacar.StavkaCenovnikaRent;
import com.ftn.model.rentacar.Vozilo;
import com.ftn.repository.CenovnikRentACarRepository;
import com.ftn.repository.LokacijaRepository;
import com.ftn.repository.RezervacijaVozilaRepository;

import com.ftn.repository.StavkaCenovnikaRentRepository;
import com.ftn.repository.UserRepository;
import com.ftn.repository.VoziloRepository;

@Service
public class RezervacijaVozilaService 
{
	@Autowired
	private RezervacijaVozilaRepository rezVozRepository ;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private VoziloRepository voziloRepository ;
	
	@Autowired
	private StavkaCenovnikaRentRepository stavkaRentRepository ;
	
	@Autowired
	private CenovnikRentACarRepository cenRentRepository ;
	
	@Autowired
	private LokacijaRepository lokRepository ;
	
	// formira se rezervacija za korisnika sa prosledjenim id-jem
	public RezervacijaVozila createReservationRent(RezervacijaVozilaDTO rezervacijaDTO, Long id) 
	{
		RezervacijaVozila rezervacija = new RezervacijaVozila();
		
		String europeanDatePattern = "yyyy-MM-dd";
		DateTimeFormatter europeanDateFormatter = DateTimeFormatter.ofPattern(europeanDatePattern);
		LocalDate d1 = LocalDate.parse(rezervacijaDTO.getStartDate(), europeanDateFormatter);
		LocalDate d2 = LocalDate.parse(rezervacijaDTO.getEndDate(), europeanDateFormatter);
		
		//Provera datuma
		if(d1.isAfter(d2)) { //da li je pocetni datum posle krajnjeg datuma
			return null;
		}
		
		int brojDana =  (int) d1.until(d2, ChronoUnit.DAYS);
		
		
		rezervacija.setDatumPreuzimanja(d1);
		rezervacija.setDatumVracanja(d2);
		
		Korisnik korisnik = userRepository.getOne(id);
		rezervacija.setKorisnik(korisnik);
		
		Long idVozila = Long.parseLong(rezervacijaDTO.getVozilo()) ; // u bazu je u toj koloni upisan id vozila
		Vozilo v = voziloRepository.getOne(idVozila);
		rezervacija.setVozilo(v);
		
		String mestoP = rezervacijaDTO.getMestoPreuzimanja();
		Lokacija mestoPreuzimanja = lokRepository.findOneByAdresa(mestoP);
		rezervacija.setMestoPreuzimanja(mestoPreuzimanja);
		
		String mestoV = rezervacijaDTO.getMestoVracanja();
		Lokacija mestoVracanja = lokRepository.findOneByAdresa(mestoV);
		rezervacija.setMestoVracanja(mestoVracanja);

		List<CenovnikRentACar> cenovnici = cenRentRepository.findAll();
		List<StavkaCenovnikaRent> stavkeCenovnika = stavkaRentRepository.findAll();
		
		Double cenaRezervacije = (double) 0;
	
		Vozilo voziloFor = rezervacija.getVozilo();
		for(CenovnikRentACar cenovnik : cenovnici) 
			if(d1.isAfter(cenovnik.getPocetakVazenja()) || d1.isEqual(cenovnik.getPocetakVazenja())) 
				if(d2.isBefore(cenovnik.getPrestanakVazenja()) || d2.isEqual(cenovnik.getPrestanakVazenja()))
					if (cenovnik.getRentACar().getRentACarId() == voziloFor.getRentACar().getRentACarId())
						for (StavkaCenovnikaRent stavkaCenovnika: stavkeCenovnika)
							if (stavkaCenovnika.getCenovnik().getId() == cenovnik.getId())
								if (stavkaCenovnika.getTipVozila() == voziloFor.getTip())
									cenaRezervacije += stavkaCenovnika.getCena() * brojDana ;
		
		rezervacija.setCena(cenaRezervacije);
		rezVozRepository.save(rezervacija);
	
		return rezervacija;
	}

	
	
}
