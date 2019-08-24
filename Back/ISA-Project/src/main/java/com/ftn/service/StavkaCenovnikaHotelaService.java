package com.ftn.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.model.hotels.CenovnikHotela;
import com.ftn.model.hotels.Hotel;
import com.ftn.model.hotels.StavkaCenovnikaHotela;
import com.ftn.repository.CenovnikHotelaRepository;
import com.ftn.repository.HotelRepository;
import com.ftn.repository.StavkaCenovnikaHotelaRepository;

@Service
public class StavkaCenovnikaHotelaService {
	
	@Autowired
	private StavkaCenovnikaHotelaRepository stavkaCenovnikaHotelaRepository;
	@Autowired
	private CenovnikHotelaRepository cenovnikHotelaRepository;

	public ArrayList<StavkaCenovnikaHotela> getAllPrices(Long idCenovnikaHotela) {
		ArrayList<StavkaCenovnikaHotela> stavkeCenovnika = new ArrayList<StavkaCenovnikaHotela>();
		ArrayList<StavkaCenovnikaHotela> sveStavkeCenovnika = (ArrayList<StavkaCenovnikaHotela>) stavkaCenovnikaHotelaRepository.findAll();
		CenovnikHotela cenovnikHotela = cenovnikHotelaRepository.getOne(idCenovnikaHotela);
		if(cenovnikHotela == null) {
			return stavkeCenovnika;
		} else {
			for(StavkaCenovnikaHotela stavkaCenovnika : sveStavkeCenovnika) {
				if(stavkaCenovnika.getCenovnik().getId() == idCenovnikaHotela) {
					stavkeCenovnika.add(stavkaCenovnika);
				}
			}
		}
		return stavkeCenovnika;
	}

}
