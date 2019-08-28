package com.ftn.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.model.hotels.DodatnaUsluga;
import com.ftn.model.hotels.Hotel;
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
	

}
