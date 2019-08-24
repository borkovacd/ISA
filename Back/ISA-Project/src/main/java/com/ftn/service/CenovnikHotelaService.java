package com.ftn.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.dto.CenovnikHotelaDTO;
import com.ftn.enums.TipSobe;
import com.ftn.model.hotels.CenovnikHotela;
import com.ftn.model.hotels.Hotel;
import com.ftn.model.hotels.Soba;
import com.ftn.repository.CenovnikHotelaRepository;
import com.ftn.repository.HotelRepository;

@Service
public class CenovnikHotelaService {
	
	@Autowired
	private CenovnikHotelaRepository cenovnikHotelaRepository;
	@Autowired
	private HotelRepository hotelRepository;

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

}
