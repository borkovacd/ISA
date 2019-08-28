package com.ftn.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.dto.StavkaCenovnikaHotelaDTO;
import com.ftn.enums.TipDodatneUsluge;
import com.ftn.enums.TipSobe;
import com.ftn.model.hotels.CenovnikHotela;
import com.ftn.model.hotels.StavkaCenovnikaHotela;
import com.ftn.repository.CenovnikHotelaRepository;
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

	public StavkaCenovnikaHotela createPrice(StavkaCenovnikaHotelaDTO stavkaDTO, Long idPriceList) {
		StavkaCenovnikaHotela stavkaCenovnika = new StavkaCenovnikaHotela();
		if(stavkaDTO.getTipStavke().equals("JEDNOKREVETNA_SOBA")) {
			stavkaCenovnika.setTipSobe(TipSobe.JEDNOKREVETNA_SOBA);
			stavkaCenovnika.setTipDodatneUsluge(null);
		} else if (stavkaDTO.getTipStavke().equals("DVOKREVETNA_SOBA")) {
			stavkaCenovnika.setTipSobe(TipSobe.DVOKREVETNA_SOBA);
			stavkaCenovnika.setTipDodatneUsluge(null);
		} else if (stavkaDTO.getTipStavke().equals("TROKREVETNA_SOBA")) {
			stavkaCenovnika.setTipSobe(TipSobe.TROKREVETNA_SOBA);
			stavkaCenovnika.setTipDodatneUsluge(null);
		} else if (stavkaDTO.getTipStavke().equals("APARTMAN")) {
			stavkaCenovnika.setTipSobe(TipSobe.APARTMAN);
			stavkaCenovnika.setTipDodatneUsluge(null);
		} else if (stavkaDTO.getTipStavke().equals("STUDIO")) {
			stavkaCenovnika.setTipSobe(TipSobe.STUDIO);
			stavkaCenovnika.setTipDodatneUsluge(null);
		} else if (stavkaDTO.getTipStavke().equals("SUITE")) {
			stavkaCenovnika.setTipSobe(TipSobe.SUITE);
			stavkaCenovnika.setTipDodatneUsluge(null);
		} else if (stavkaDTO.getTipStavke().equals("FAMILY_ROOM")) {
			stavkaCenovnika.setTipSobe(TipSobe.FAMILY_ROOM);
			stavkaCenovnika.setTipDodatneUsluge(null);
		} else if (stavkaDTO.getTipStavke().equals("PARKING")) {
			stavkaCenovnika.setTipDodatneUsluge(TipDodatneUsluge.PARKING);
			stavkaCenovnika.setTipSobe(null);
		} else if (stavkaDTO.getTipStavke().equals("TRANSFER")) {
			stavkaCenovnika.setTipDodatneUsluge(TipDodatneUsluge.TRANSFER);
			stavkaCenovnika.setTipSobe(null);
		} else if (stavkaDTO.getTipStavke().equals("BAZEN")) {
			stavkaCenovnika.setTipDodatneUsluge(TipDodatneUsluge.BAZEN);
			stavkaCenovnika.setTipSobe(null);
		} else if (stavkaDTO.getTipStavke().equals("RESTORAN")) {
			stavkaCenovnika.setTipDodatneUsluge(TipDodatneUsluge.RESTORAN);
			stavkaCenovnika.setTipSobe(null);
		} else if (stavkaDTO.getTipStavke().equals("SOBNI_SERVIS")) {
			stavkaCenovnika.setTipDodatneUsluge(TipDodatneUsluge.SOBNI_SERVIS);
			stavkaCenovnika.setTipSobe(null);
		} else if (stavkaDTO.getTipStavke().equals("WELNESS")) {
			stavkaCenovnika.setTipDodatneUsluge(TipDodatneUsluge.WELNESS);
			stavkaCenovnika.setTipSobe(null);
		} else if (stavkaDTO.getTipStavke().equals("SPA_CENTAR")) {
			stavkaCenovnika.setTipDodatneUsluge(TipDodatneUsluge.SPA_CENTAR);
			stavkaCenovnika.setTipSobe(null);
		} else if (stavkaDTO.getTipStavke().equals("WIFI")) {
			stavkaCenovnika.setTipDodatneUsluge(TipDodatneUsluge.WIFI);
			stavkaCenovnika.setTipSobe(null);
		} else if (stavkaDTO.getTipStavke().equals("TERETANA")) {
			stavkaCenovnika.setTipDodatneUsluge(TipDodatneUsluge.TERETANA);
			stavkaCenovnika.setTipSobe(null);
		}
		
		stavkaCenovnika.setCena(Double.parseDouble(stavkaDTO.getCena())); 	
		CenovnikHotela cenovnik = cenovnikHotelaRepository.getOne(idPriceList);
		stavkaCenovnika.setCenovnik(cenovnik);
		
		//Provera da li u cenovniku vec postoji cena za unetu stavku
		ArrayList<StavkaCenovnikaHotela> sveStavkeCenovnika = (ArrayList<StavkaCenovnikaHotela>) stavkaCenovnikaHotelaRepository.findAll();
		ArrayList<StavkaCenovnikaHotela> stavkeCenovnika = new ArrayList<StavkaCenovnikaHotela>();
		for(StavkaCenovnikaHotela stavka : sveStavkeCenovnika) {
			if(stavka.getCenovnik().getId() == cenovnik.getId()) {
				if(stavkaCenovnika.getTipSobe() == stavka.getTipSobe() && stavkaCenovnika.getTipSobe() != null) {
					return null;
				} else if (stavkaCenovnika.getTipDodatneUsluge() == stavka.getTipDodatneUsluge() && stavkaCenovnika.getTipDodatneUsluge() != null) {
					return null;
				}
			}
		}
		
		stavkaCenovnikaHotelaRepository.save(stavkaCenovnika);
		return stavkaCenovnika;

	}

}
