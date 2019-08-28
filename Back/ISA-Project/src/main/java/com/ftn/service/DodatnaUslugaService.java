package com.ftn.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.dto.DodatnaUslugaDTO;
import com.ftn.enums.TipDodatneUsluge;
import com.ftn.enums.TipSobe;
import com.ftn.model.hotels.DodatnaUsluga;
import com.ftn.model.hotels.Hotel;
import com.ftn.model.hotels.Soba;
import com.ftn.repository.DodatnaUslugaRepository;
import com.ftn.repository.HotelRepository;

@Service
public class DodatnaUslugaService {
	
	@Autowired
	private DodatnaUslugaRepository dodatnaUslugaRepository;
	@Autowired
	private HotelRepository hotelRepository;

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
	

}
