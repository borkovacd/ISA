package com.ftn.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.dto.DodatnaUslugaDTO;
import com.ftn.enums.TipDodatneUsluge;
import com.ftn.enums.TipSobe;
import com.ftn.model.hotels.CenovnikHotela;
import com.ftn.model.hotels.DodatnaUsluga;
import com.ftn.model.hotels.Hotel;
import com.ftn.model.hotels.RezervacijaHotela;
import com.ftn.model.hotels.Soba;
import com.ftn.model.hotels.StavkaCenovnikaHotela;
import com.ftn.repository.CenovnikHotelaRepository;
import com.ftn.repository.DodatnaUslugaRepository;
import com.ftn.repository.HotelRepository;
import com.ftn.repository.RezervacijaHotelaRepository;
import com.ftn.repository.StavkaCenovnikaHotelaRepository;

@Service
public class DodatnaUslugaService {
	
	@Autowired
	private DodatnaUslugaRepository dodatnaUslugaRepository;
	@Autowired
	private HotelRepository hotelRepository;
	@Autowired
	private RezervacijaHotelaRepository rezervacijaHotelaRepository;
	@Autowired
	private CenovnikHotelaRepository cenovnikHotelaRepository;
	@Autowired
	private StavkaCenovnikaHotelaRepository stavkaCenovnikaHotelaRepository;

	public ArrayList<DodatnaUsluga> getAllAdditionalServices(Long idHotela) {
		ArrayList<DodatnaUsluga> dodatneUsluge = new ArrayList<DodatnaUsluga>();
		ArrayList<DodatnaUsluga> sveDodatneUsluge = (ArrayList<DodatnaUsluga>) dodatnaUslugaRepository.findAll();
		Hotel hotel = hotelRepository.getOne(idHotela);
		if(hotel == null) {
			return dodatneUsluge;
		} else {
			for(DodatnaUsluga dodatnaUsluga : sveDodatneUsluge) {
				if(dodatnaUsluga.getHotel().getId() == idHotela) {
					dodatneUsluge.add(dodatnaUsluga);
				}
			}
		}
		return dodatneUsluge;
	}

	public DodatnaUsluga createAdditionalService(DodatnaUslugaDTO dodatnaUslugaDTO, Long idHotela) {
		DodatnaUsluga dodatnaUsluga = new DodatnaUsluga();
		if(dodatnaUslugaDTO.getTipDodatneUsluge().equals("PARKING")) 
			dodatnaUsluga.setTipDodateneUsluge(TipDodatneUsluge.PARKING);
		else if(dodatnaUslugaDTO.getTipDodatneUsluge().equals("TRANSFER")) 
			dodatnaUsluga.setTipDodateneUsluge(TipDodatneUsluge.TRANSFER);
		else if(dodatnaUslugaDTO.getTipDodatneUsluge().equals("BAZEN")) 
			dodatnaUsluga.setTipDodateneUsluge(TipDodatneUsluge.BAZEN);
		else if(dodatnaUslugaDTO.getTipDodatneUsluge().equals("RESTORAN")) 
			dodatnaUsluga.setTipDodateneUsluge(TipDodatneUsluge.RESTORAN);
		else if(dodatnaUslugaDTO.getTipDodatneUsluge().equals("SOBNI_SERVIS")) 
			dodatnaUsluga.setTipDodateneUsluge(TipDodatneUsluge.SOBNI_SERVIS);
		else if(dodatnaUslugaDTO.getTipDodatneUsluge().equals("WELNESS")) 
			dodatnaUsluga.setTipDodateneUsluge(TipDodatneUsluge.WELNESS);
		else if(dodatnaUslugaDTO.getTipDodatneUsluge().equals("SPA_CENTAR")) 
			dodatnaUsluga.setTipDodateneUsluge(TipDodatneUsluge.SPA_CENTAR);
		else if(dodatnaUslugaDTO.getTipDodatneUsluge().equals("WIFI")) 
			dodatnaUsluga.setTipDodateneUsluge(TipDodatneUsluge.WIFI);
		else if(dodatnaUslugaDTO.getTipDodatneUsluge().equals("TERETANA")) 
			dodatnaUsluga.setTipDodateneUsluge(TipDodatneUsluge.TERETANA);
		else 
			return null;
		
		Hotel hotel = hotelRepository.getOne(idHotela);
		if(hotel == null) 
			return null;
		dodatnaUsluga.setHotel(hotel);
		
		//Provera da li u hotelu vec postoji izabrani tip dodatne usluge
		ArrayList<DodatnaUsluga> sveDodatneUsluge = (ArrayList<DodatnaUsluga>) dodatnaUslugaRepository.findAll();
		for(DodatnaUsluga du : sveDodatneUsluge) {
			if(du.getHotel().getId() == hotel.getId()) {
				if(du.getTipDodatneUsluge() == dodatnaUsluga.getTipDodatneUsluge()) 
					return null;
			}
		}
		
		dodatnaUslugaRepository.save(dodatnaUsluga);
		return dodatnaUsluga;
	}

	public boolean deleteAdditionalService(Long idAdditionalService) {
		dodatnaUslugaRepository.deleteById(idAdditionalService);
		return true;
	}

	public ArrayList<DodatnaUsluga> getAvailableAdditionalServices(Long idHotela, Long idRezervacije) {
		
		RezervacijaHotela rezervacija = rezervacijaHotelaRepository.getOne(idRezervacije);
		if(rezervacija == null) {
			return null;
		}
		
		LocalDate d1 = rezervacija.getDatumPocetka();
		LocalDate d2 = rezervacija.getDatumKraja();
		
		int brojNocenja =  (int) d1.until(d2, ChronoUnit.DAYS);
		
		
		ArrayList<DodatnaUsluga> dodatneUsluge = new ArrayList<DodatnaUsluga>();
		ArrayList<DodatnaUsluga> sveDodatneUsluge = (ArrayList<DodatnaUsluga>) dodatnaUslugaRepository.findAll();
		Hotel hotel = hotelRepository.getOne(idHotela);
		if(hotel == null) {
			return dodatneUsluge;
		} else {
			for(DodatnaUsluga dodatnaUsluga : sveDodatneUsluge) {
				if(dodatnaUsluga.getHotel().getId() == idHotela) {
					dodatneUsluge.add(dodatnaUsluga);
				}
			}
		}
		
		List<CenovnikHotela> cenovnici = cenovnikHotelaRepository.findAll();
		List<StavkaCenovnikaHotela> stavkeCenovnika = stavkaCenovnikaHotelaRepository.findAll();
		
		ArrayList<DodatnaUsluga> odgovarajuceDodatneUsluge = new ArrayList<DodatnaUsluga>();
		
		for(DodatnaUsluga dodatnaUsluga: dodatneUsluge) {
			for(CenovnikHotela cenovnik : cenovnici) 
				if(d1.isAfter(cenovnik.getPocetakVazenja()) || d1.isEqual(cenovnik.getPocetakVazenja())) 
					if(d2.isBefore(cenovnik.getPrestanakVazenja()) || d2.isEqual(cenovnik.getPrestanakVazenja())) 
						if(cenovnik.getHotel().getId() == idHotela)  //ako je cenovnik hotela u kojem je slobodna soba
							for(StavkaCenovnikaHotela stavkaCenovnika : stavkeCenovnika) 
								if(stavkaCenovnika.getCenovnik().getId() == cenovnik.getId()) 
									if(stavkaCenovnika.getTipDodatneUsluge() == dodatnaUsluga.getTipDodatneUsluge()) {
										dodatnaUsluga.setCena(stavkaCenovnika.getCena() * brojNocenja);
										odgovarajuceDodatneUsluge.add(dodatnaUsluga);
									}
		}

		return odgovarajuceDodatneUsluge;
	}
	

}
