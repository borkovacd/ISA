package com.ftn.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.dto.RezervacijaDodatnihUslugaDTO;
import com.ftn.dto.RezervacijaSobaDTO;
import com.ftn.enums.TipDodatneUsluge;
import com.ftn.model.Korisnik;
import com.ftn.model.hotels.CenovnikHotela;
import com.ftn.model.hotels.DodatnaUsluga;
import com.ftn.model.hotels.RezervacijaHotela;
import com.ftn.model.hotels.Soba;
import com.ftn.model.hotels.StavkaCenovnikaHotela;
import com.ftn.repository.CenovnikHotelaRepository;
import com.ftn.repository.DodatnaUslugaRepository;
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
	@Autowired
	private DodatnaUslugaRepository dodatnaUslugaRepository;

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

	public RezervacijaHotela addToReservation(RezervacijaDodatnihUslugaDTO dodatnaRezervacijaDTO, Long idRezervacije) {
		
		String europeanDatePattern = "yyyy-MM-dd";
		DateTimeFormatter europeanDateFormatter = DateTimeFormatter.ofPattern(europeanDatePattern);
		LocalDate d1 = LocalDate.parse(dodatnaRezervacijaDTO.getStartDate(), europeanDateFormatter);
		LocalDate d2 = LocalDate.parse(dodatnaRezervacijaDTO.getEndDate(), europeanDateFormatter);
		
		//Provera datuma
		if(d1.isAfter(d2)) { //da li je pocetni datum posle krajnjeg datuma
			return null;
		}
		
		int brojNocenja =  (int) d1.until(d2, ChronoUnit.DAYS);
		
		RezervacijaHotela rezervacija = rezervacijaHotelaRepository.getOne(idRezervacije);
		DodatnaUsluga dodatnaUslugaT = null;
		if(rezervacija != null) {
			for(int i=0; i<dodatnaRezervacijaDTO.getAdditionalServicesList().size(); i++) {
				if(dodatnaRezervacijaDTO.getAdditionalServicesList().get(i).equals("PARKING")) 
					dodatnaUslugaT=dodatnaUslugaRepository.findByTipDodatneUsluge(TipDodatneUsluge.PARKING);
				else if(dodatnaRezervacijaDTO.getAdditionalServicesList().get(i).equals("TERETANA")) 
					dodatnaUslugaT=dodatnaUslugaRepository.findByTipDodatneUsluge(TipDodatneUsluge.TERETANA);
				else if(dodatnaRezervacijaDTO.getAdditionalServicesList().get(i).equals("BAZEN")) 
					dodatnaUslugaT=dodatnaUslugaRepository.findByTipDodatneUsluge(TipDodatneUsluge.BAZEN);
				else if(dodatnaRezervacijaDTO.getAdditionalServicesList().get(i).equals("RESTORAN")) 
					dodatnaUslugaT=dodatnaUslugaRepository.findByTipDodatneUsluge(TipDodatneUsluge.RESTORAN);
				else if(dodatnaRezervacijaDTO.getAdditionalServicesList().get(i).equals("SOBNI_SERVIS")) 
					dodatnaUslugaT=dodatnaUslugaRepository.findByTipDodatneUsluge(TipDodatneUsluge.SOBNI_SERVIS);
				else if(dodatnaRezervacijaDTO.getAdditionalServicesList().get(i).equals("WELNESS")) 
					dodatnaUslugaT=dodatnaUslugaRepository.findByTipDodatneUsluge(TipDodatneUsluge.WELNESS);
				else if(dodatnaRezervacijaDTO.getAdditionalServicesList().get(i).equals("SPA_CENTAR")) 
					dodatnaUslugaT=dodatnaUslugaRepository.findByTipDodatneUsluge(TipDodatneUsluge.SPA_CENTAR);
				else if(dodatnaRezervacijaDTO.getAdditionalServicesList().get(i).equals("WIFI")) 
					dodatnaUslugaT=dodatnaUslugaRepository.findByTipDodatneUsluge(TipDodatneUsluge.WIFI);
				else if(dodatnaRezervacijaDTO.getAdditionalServicesList().get(i).equals("TRANSFER")) 
					dodatnaUslugaT=dodatnaUslugaRepository.findByTipDodatneUsluge(TipDodatneUsluge.TRANSFER);
				
				if(dodatnaUslugaT != null) {
					rezervacija.getDodatneUsluge().add(dodatnaUslugaT);
					dodatnaUslugaT = null;
				}
			}
		} else {
			return null;
		}
		
		List<CenovnikHotela> cenovnici = cenovnikHotelaRepository.findAll();
		List<StavkaCenovnikaHotela> stavkeCenovnika = stavkaCenovnikaHotelaRepository.findAll();
		Double cenaDodatnih = (double) 0;
		for(DodatnaUsluga dodatnaUsluga: rezervacija.getDodatneUsluge())
			for(CenovnikHotela cenovnik : cenovnici) 
				if(d1.isAfter(cenovnik.getPocetakVazenja()) || d1.isEqual(cenovnik.getPocetakVazenja())) 
					if(d2.isBefore(cenovnik.getPrestanakVazenja()) || d2.isEqual(cenovnik.getPrestanakVazenja())) 
						if(cenovnik.getHotel().getId() == dodatnaUsluga.getHotel().getId())  
							for(StavkaCenovnikaHotela stavkaCenovnika : stavkeCenovnika) 
								if(stavkaCenovnika.getCenovnik().getId() == cenovnik.getId())
									if(stavkaCenovnika.getTipDodatneUsluge() == dodatnaUsluga.getTipDodatneUsluge()) 
										cenaDodatnih += stavkaCenovnika.getCena() * brojNocenja;
		
		Double staraCena = rezervacija.getCena();
		rezervacija.setCena(staraCena+cenaDodatnih);
		rezervacijaHotelaRepository.save(rezervacija);
		return rezervacija;
	}

	public RezervacijaHotela getReservation(Long idRezervacije) {
		RezervacijaHotela rez = rezervacijaHotelaRepository.getOne(idRezervacije);
		return rez;
	}

}
