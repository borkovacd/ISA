package com.ftn.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.dto.RezervacijaSobaDTO;
import com.ftn.model.Korisnik;
import com.ftn.model.hotels.CenovnikHotela;
import com.ftn.model.hotels.RezervacijaHotela;
import com.ftn.model.hotels.Soba;
import com.ftn.model.hotels.StavkaCenovnikaHotela;
import com.ftn.repository.CenovnikHotelaRepository;
import com.ftn.repository.RezervacijaHotelaRepository;
import com.ftn.repository.SobaRepository;
import com.ftn.repository.StavkaCenovnikaHotelaRepository;
import com.ftn.repository.UserRepository;

@Service
public class RezervacijaHotelaService {
	
	@Autowired
	private RezervacijaHotelaRepository rezervacijaHotelaRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private SobaRepository sobaRepository;
	@Autowired
	private StavkaCenovnikaHotelaRepository stavkaCenovnikaHotelaRepository;
	@Autowired
	private CenovnikHotelaRepository cenovnikHotelaRepository;

	public RezervacijaHotela createReservation(RezervacijaSobaDTO rezervacijaDTO, Long id) {
		RezervacijaHotela rezervacija = new RezervacijaHotela();
		
		String europeanDatePattern = "yyyy-MM-dd";
		DateTimeFormatter europeanDateFormatter = DateTimeFormatter.ofPattern(europeanDatePattern);
		LocalDate d1 = LocalDate.parse(rezervacijaDTO.getStartDate(), europeanDateFormatter);
		LocalDate d2 = LocalDate.parse(rezervacijaDTO.getEndDate(), europeanDateFormatter);
		
		//Provera datuma
		if(d1.isAfter(d2)) { //da li je pocetni datum posle krajnjeg datuma
			return null;
		}
		
		int brojNocenja =  (int) d1.until(d2, ChronoUnit.DAYS);
		
		rezervacija.setDatumPocetka(d1);
		rezervacija.setDatumKraja(d2);
		Korisnik korisnik = userRepository.getOne(id);
		rezervacija.setKorisnik(korisnik);
		
		for(int i=0; i<rezervacijaDTO.getRoomList().size(); i++) {
			Long idSobe = Long.parseLong(rezervacijaDTO.getRoomList().get(i));
			System.out.println("Id sobe: " + idSobe);
			Soba soba = sobaRepository.getOne(idSobe);
			rezervacija.getSobe().add(soba);
		}
		
		
		List<CenovnikHotela> cenovnici = cenovnikHotelaRepository.findAll();
		List<StavkaCenovnikaHotela> stavkeCenovnika = stavkaCenovnikaHotelaRepository.findAll();
		
		Double cenaRezervacije = (double) 0;
		
		for(Soba soba: rezervacija.getSobe())
			for(CenovnikHotela cenovnik : cenovnici) 
				if(d1.isAfter(cenovnik.getPocetakVazenja()) || d1.isEqual(cenovnik.getPocetakVazenja())) 
					if(d2.isBefore(cenovnik.getPrestanakVazenja()) || d2.isEqual(cenovnik.getPrestanakVazenja())) 
						if(cenovnik.getHotel().getId() == soba.getHotel().getId())  
							for(StavkaCenovnikaHotela stavkaCenovnika : stavkeCenovnika) 
								if(stavkaCenovnika.getCenovnik().getId() == cenovnik.getId()) 
									if(stavkaCenovnika.getTipSobe() == soba.getTipSobe()) 
										cenaRezervacije += stavkaCenovnika.getCena() * brojNocenja;
		
		rezervacija.setCena(cenaRezervacije);
		rezervacijaHotelaRepository.save(rezervacija);
	
		return rezervacija;
	}

}
