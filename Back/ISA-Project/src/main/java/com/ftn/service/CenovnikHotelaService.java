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

}
