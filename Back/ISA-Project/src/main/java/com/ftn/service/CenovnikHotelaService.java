package com.ftn.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.model.hotels.CenovnikHotela;
import com.ftn.model.hotels.Hotel;
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

}
