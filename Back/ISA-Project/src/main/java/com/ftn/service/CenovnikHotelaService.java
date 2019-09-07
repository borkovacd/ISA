package com.ftn.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.dto.CenovnikHotelaDTO;
import com.ftn.enums.TipDodatneUsluge;
import com.ftn.enums.TipSobe;
import com.ftn.model.hotels.CenovnikHotela;
import com.ftn.model.hotels.DodatnaUsluga;
import com.ftn.model.hotels.Hotel;
import com.ftn.model.hotels.Soba;
import com.ftn.repository.CenovnikHotelaRepository;
import com.ftn.repository.DodatnaUslugaRepository;
import com.ftn.repository.HotelRepository;
import com.ftn.repository.SobaRepository;

@Service
public class CenovnikHotelaService {
	
	@Autowired
	private CenovnikHotelaRepository cenovnikHotelaRepository;
	@Autowired
	private HotelRepository hotelRepository;
	@Autowired
	private SobaRepository sobaRepository;
	@Autowired
	private DodatnaUslugaRepository dodatnaUslugaRepository;

	public ArrayList<CenovnikHotela> getAllPricelists(Long idHotela) {
		ArrayList<CenovnikHotela> cenovnici = new ArrayList<CenovnikHotela>();
		ArrayList<CenovnikHotela> sviCenovnici = (ArrayList<CenovnikHotela>) cenovnikHotelaRepository.findAll();
		Hotel hotel = hotelRepository.getOne(idHotela);
		if(hotel == null) {
			return cenovnici;
		} else {
			for(CenovnikHotela cenovnik : sviCenovnici) {
				if(cenovnik.getHotel().getId() == idHotela) {
					cenovnici.add(cenovnik);
				}
			}
		}
		return cenovnici;
	}

	public CenovnikHotela createPricelist(CenovnikHotelaDTO cenvnikHotelaDTO, Long idHotela) {
		CenovnikHotela cenovnikHotela = new CenovnikHotela();
		String europeanDatePattern = "yyyy-MM-dd";
		DateTimeFormatter europeanDateFormatter = DateTimeFormatter.ofPattern(europeanDatePattern);
		LocalDate d1 = LocalDate.parse(cenvnikHotelaDTO.getStartDate(), europeanDateFormatter);
		LocalDate d2 = LocalDate.parse(cenvnikHotelaDTO.getEndDate(), europeanDateFormatter);
		
		//Provera datuma
		if(d1.isAfter(d2)) { //da li je datum pocetka posle datuma prestanka vazenja
			return null;
		}
		
		ArrayList<CenovnikHotela> sviCenovnici = (ArrayList<CenovnikHotela>) cenovnikHotelaRepository.findAll();
		ArrayList<CenovnikHotela> cenovniciHotela = new ArrayList<CenovnikHotela>();
		for(CenovnikHotela cenovnik : sviCenovnici) {
			if(cenovnik.getHotel().getId() == idHotela)
				cenovniciHotela.add(cenovnik);
		}
		for(CenovnikHotela cenovnik : cenovniciHotela) {
			if(d1.isBefore(cenovnik.getPocetakVazenja())) { //pocetak vazenja pre pocetka postojeceg
				if(d2.isAfter(cenovnik.getPocetakVazenja())) { //kraj vazenja posle pocetka postojeceg
					return null; // preklapanje!
				}
			} else if(d1.isAfter(cenovnik.getPocetakVazenja())) { //pocetak vazenja posle pocetka postojeceg
				if(d1.isBefore(cenovnik.getPrestanakVazenja())) { //pocetak vazenja pre kraja postojeceg
					return null; // preklapanje!
				}
			} else if(d1.isEqual(cenovnik.getPocetakVazenja())) { //pocetak vazenja isti kao pocetak postojeceg
				return null;
			} else if(d1.isEqual(cenovnik.getPrestanakVazenja())) { //pocetak vazenja isti kao kraj postojeceg
				return null;
			} else if(d2.isEqual(cenovnik.getPocetakVazenja())) { //prestanak vazenja isti kao pocetak postojeceg
				return null;
			} else if(d2.isEqual(cenovnik.getPrestanakVazenja())) { //prestanak vazenja isti kao prestanak postojeceg
				return null;
			}
		}
		
		cenovnikHotela.setPocetakVazenja(d1);
		cenovnikHotela.setPrestanakVazenja(d2);
		
		Hotel hotel = hotelRepository.getOne(idHotela);
		if(hotel == null) {
			return null;
		}
		cenovnikHotela.setHotel(hotel);
		cenovnikHotelaRepository.save(cenovnikHotela);
		return cenovnikHotela;
	}

	public CenovnikHotela getPricelist(Long idPriceList) {
		CenovnikHotela cenovnik = cenovnikHotelaRepository.getOne(idPriceList);
		return cenovnik;
	}

	public ArrayList<TipSobe> getRoomTypesInHotel(Long idPriceList) {
		ArrayList<TipSobe> tipoviSoba = new ArrayList<TipSobe>();
		CenovnikHotela cenovnik = cenovnikHotelaRepository.getOne(idPriceList);
		if(cenovnik == null) 
			return tipoviSoba;
		Hotel hotel = hotelRepository.getOne(cenovnik.getHotel().getId());
		if(hotel == null)
			return tipoviSoba;
		ArrayList<Soba> sveSobe = (ArrayList<Soba>) sobaRepository.findAll();
		ArrayList<Soba> sobeHotela = new ArrayList<Soba>();
		for(Soba soba: sveSobe) {
			if(soba.getHotel().getId() == hotel.getId())
				sobeHotela.add(soba);
		}
		for(Soba soba: sobeHotela) {
			if(!tipoviSoba.contains(soba.getTipSobe()))
				tipoviSoba.add(soba.getTipSobe());
		}
		return tipoviSoba;
	}

	public ArrayList<TipDodatneUsluge> getAdditionalServiceTypesInHotel(Long idPriceList) {
		ArrayList<TipDodatneUsluge> tipoviDodatnihUsluga = new ArrayList<TipDodatneUsluge>();
		CenovnikHotela cenovnik = cenovnikHotelaRepository.getOne(idPriceList);
		if(cenovnik == null) 
			return tipoviDodatnihUsluga;
		Hotel hotel = hotelRepository.getOne(cenovnik.getHotel().getId());
		if(hotel == null)
			return tipoviDodatnihUsluga;
		ArrayList<DodatnaUsluga> sveDodatneUsluge = (ArrayList<DodatnaUsluga>) dodatnaUslugaRepository.findAll();
		ArrayList<DodatnaUsluga> dodatneUslugeHotela = new ArrayList<DodatnaUsluga>();
		for(DodatnaUsluga du: sveDodatneUsluge) {
			if(du.getHotel().getId() == hotel.getId())
				dodatneUslugeHotela.add(du);
		}
		for(DodatnaUsluga du: dodatneUslugeHotela) {
			if(!tipoviDodatnihUsluga.contains(du.getTipDodatneUsluge()))
				tipoviDodatnihUsluga.add(du.getTipDodatneUsluge());
		}
		return tipoviDodatnihUsluga;
	}

	public CenovnikHotela getActivePricelist(Long idHotela) {
		ArrayList<CenovnikHotela> sviCenovnici = (ArrayList<CenovnikHotela>) cenovnikHotelaRepository.findAll();
		ArrayList<CenovnikHotela> cenovniciHotela = new ArrayList<CenovnikHotela>();
		for(CenovnikHotela cenovnik : sviCenovnici) {
			if(cenovnik.getHotel().getId() == idHotela)
				cenovniciHotela.add(cenovnik);
		}
		
		LocalDate currentDate = LocalDate.now();
		System.out.println(" ------ Danasnji datum: " + currentDate + " ----- ");
		if(!cenovniciHotela.isEmpty()) {
			for(CenovnikHotela cenovnik : cenovniciHotela) {
				if(currentDate.isEqual(cenovnik.getPocetakVazenja())) { //ako je danasnji datum isti kao pocetak vazenja cenovnika
					return cenovnik;
				} else if(currentDate.isAfter(cenovnik.getPocetakVazenja())) { //ako je posle pocetka vazenja
					if(currentDate.isBefore(cenovnik.getPrestanakVazenja())) { //a pre kraja vazenja
						return cenovnik;
					}
				} 
			}
		}
		
		CenovnikHotela tempCenovnik = null;
		if(!cenovniciHotela.isEmpty()) { 
			tempCenovnik = cenovniciHotela.get(0); //uzima prvi cenovnik iz liste cenovnika hotela
		}
		//Uzima se cenovnik ciji datum prestanka je najkasniji
		if(cenovniciHotela.size() > 1) {
			for(int i=1; i<cenovniciHotela.size(); i++) { 
				if(cenovniciHotela.get(i).getPrestanakVazenja().isAfter(tempCenovnik.getPrestanakVazenja())) {
					tempCenovnik = cenovniciHotela.get(i);
				}
			}
		}
		return tempCenovnik;
	}

}
