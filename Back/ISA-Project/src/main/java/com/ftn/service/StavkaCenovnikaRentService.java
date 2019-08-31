package com.ftn.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.dto.CenovnikRentDTO;

import com.ftn.dto.StavkaCenovnikaRentDTO;
import com.ftn.enums.TipSobe;
import com.ftn.enums.TipVozila;

import com.ftn.model.rentacar.CenovnikRentACar;
import com.ftn.model.rentacar.RentACar;
import com.ftn.model.rentacar.StavkaCenovnikaRent;
import com.ftn.model.rentacar.Vozilo;
import com.ftn.repository.CenovnikRentACarRepository;
import com.ftn.repository.RentCarRepository;
import com.ftn.repository.StavkaCenovnikaRentRepository;
import com.ftn.repository.VoziloRepository;

@Service
public class StavkaCenovnikaRentService 
{

	@Autowired
	private StavkaCenovnikaRentRepository stavkaRentRepository ;
	
	@Autowired
	private CenovnikRentACarRepository cenRentRepository ;
	
	// vraca sve cene iz cenovnika
	public ArrayList<StavkaCenovnikaRent> getAllPrices(Long idCenovnik) 
	{
		ArrayList<StavkaCenovnikaRent> stavkeCenovnika = new ArrayList<StavkaCenovnikaRent>();
		ArrayList<StavkaCenovnikaRent> sveStavkeCenovnika = (ArrayList<StavkaCenovnikaRent>) stavkaRentRepository.findAll();
		
		CenovnikRentACar cenovnikRent = cenRentRepository.getOne(idCenovnik);
		
		if(cenovnikRent == null) 
		{
			return stavkeCenovnika;
		} else {
			for(StavkaCenovnikaRent stavkaCenovnika : sveStavkeCenovnika) 
			{
				if(stavkaCenovnika.getCenovnik().getId() == idCenovnik) 
				{
					stavkeCenovnika.add(stavkaCenovnika);
				}
			}
		}
		return stavkeCenovnika;
	}
	
	// kreira nocu cenu za stavku
	public StavkaCenovnikaRent createPrice(StavkaCenovnikaRentDTO dto, Long idPriceList) 
	{
		StavkaCenovnikaRent stavkaCenovnika = new StavkaCenovnikaRent();
		
		if (dto.getTipStavke().equals("LIMUZINA"))
		{
			stavkaCenovnika.setTipVozila(TipVozila.LIMUZINA);
		}
		else if(dto.getTipStavke().equals("KARAVAN")) 
		{
			stavkaCenovnika.setTipVozila(TipVozila.KARAVAN);
		}
		else if(dto.getTipStavke().equals("KUPE")) 
		{
			stavkaCenovnika.setTipVozila(TipVozila.KUPE);
		}
		else if(dto.getTipStavke().equals("KABRIOLET")) 
		{
			stavkaCenovnika.setTipVozila(TipVozila.KABRIOLET);
		}
		else if(dto.getTipStavke().equals("MINIVEN")) 
		{
			stavkaCenovnika.setTipVozila(TipVozila.MINIVEN);
		}
		else if(dto.getTipStavke().equals("DZIP")) 
		{
			stavkaCenovnika.setTipVozila(TipVozila.DZIP);
		}
		else if(dto.getTipStavke().equals("PICKUP")) 
		{
			stavkaCenovnika.setTipVozila(TipVozila.PICKUP);
		}
		
		stavkaCenovnika.setCena(Double.parseDouble(dto.getCena())); 	
		CenovnikRentACar cenovnik = cenRentRepository.getOne(idPriceList);
		stavkaCenovnika.setCenovnik(cenovnik);
		
		//Provera da li u cenovniku vec postoji cena za unetu stavku
		ArrayList<StavkaCenovnikaRent> sveStavkeCenovnika = (ArrayList<StavkaCenovnikaRent>) stavkaRentRepository.findAll();
		ArrayList<StavkaCenovnikaRent> stavkeCenovnika = new ArrayList<StavkaCenovnikaRent>();
		
		for(StavkaCenovnikaRent stavka : sveStavkeCenovnika) 
		{
			if(stavka.getCenovnik().getId() == cenovnik.getId()) // ukoliko se radi o istom cenovniku
			{
				// ukoliko je vec dodata ta stavka u cenovnik
				if(stavkaCenovnika.getTipVozila() == stavka.getTipVozila() && stavkaCenovnika.getTipVozila() != null) 
				{
					return null;
				}
			}
		}
		
		stavkaRentRepository.save(stavkaCenovnika);
		return stavkaCenovnika;

	}


}
