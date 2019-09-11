package com.ftn.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ftn.dto.CenovnikRentDTO;

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
public class CenovnikRentACarService 
{
	@Autowired
	private CenovnikRentACarRepository cenRentRepository ;
	
	@Autowired
	private StavkaCenovnikaRentRepository stavkaRentRepository ;
	
	@Autowired
	private VoziloRepository voziloRepository ;
	
	@Autowired 
	private RentCarRepository rentRepository ;
	
	// vraca sve cenovnike jednog rent-a-car servisa
	public ArrayList<CenovnikRentACar> getAllPricelists(Long idRent) 
	{
		ArrayList<CenovnikRentACar> cenovnici = new ArrayList<CenovnikRentACar>();
		ArrayList<CenovnikRentACar> sviCenovnici = (ArrayList<CenovnikRentACar>) cenRentRepository.findAll();
		
		RentACar rent = rentRepository.getOne(idRent);
		if(rent == null) 
		{
			return cenovnici;
		} 
		else 
		{
			// ukoliko je to cenovnik tog rent-a-car-a
			for(CenovnikRentACar cenovnik : sviCenovnici) 
			{
				if(cenovnik.getRentACar().getRentACarId() == idRent) 
				{
					cenovnici.add(cenovnik);
				}
			}
		}
		return cenovnici;
	}
	
	// kreira novi cenovnik za rent a car
	public CenovnikRentACar createPricelist(CenovnikRentDTO cenovnikRentDTO, Long idRent) 
	{
		CenovnikRentACar cenovnikRent = new CenovnikRentACar();
		
		String europeanDatePattern = "yyyy-MM-dd";
		DateTimeFormatter europeanDateFormatter = DateTimeFormatter.ofPattern(europeanDatePattern);
		LocalDate d1 = LocalDate.parse(cenovnikRentDTO.getStartDate(), europeanDateFormatter);
		LocalDate d2 = LocalDate.parse(cenovnikRentDTO.getEndDate(), europeanDateFormatter);
		
		//Provera datuma
		if(d1.isAfter(d2)) { //da li je datum pocetka posle datuma prestanka vazenja
			return null;
		}
		
		ArrayList<CenovnikRentACar> sviCenovnici = (ArrayList<CenovnikRentACar>) cenRentRepository.findAll();
		ArrayList<CenovnikRentACar> cenovniciRent = new ArrayList<CenovnikRentACar>();
		
		// ukoliko je to cenovnik tog rent a car
		for(CenovnikRentACar cenovnik : sviCenovnici) 
		{
			if(cenovnik.getRentACar().getRentACarId() == idRent)
				cenovniciRent.add(cenovnik);
		}
		
		for(CenovnikRentACar cenovnik : cenovniciRent) 
		{
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
		
		cenovnikRent.setPocetakVazenja(d1);
		cenovnikRent.setPrestanakVazenja(d2);
		
		RentACar rent = rentRepository.getOne(idRent);
		
		if(rent == null) 
		{
			return null;
		}
		
		cenovnikRent.setRentACar(rent);
		cenRentRepository.save(cenovnikRent);
		return cenovnikRent;
	}
	
	// vraca cenovnik na osnovu id-ja
	public CenovnikRentACar getPricelist(Long idPriceList) 
	{
		CenovnikRentACar cenovnik = cenRentRepository.getOne(idPriceList);
		return cenovnik;
	}
	
	// vraca tipove vozila u rent-a-car servisu
	public ArrayList<TipVozila> getTipoviVozilaRent(Long idPriceList) 
	{
		ArrayList<TipVozila> tipoviVozila = new ArrayList<TipVozila>();
		
		CenovnikRentACar cenovnik = cenRentRepository.getOne(idPriceList);
		
		if(cenovnik == null) 
			return tipoviVozila;
		
		RentACar rent = rentRepository.getOne(cenovnik.getRentACar().getRentACarId());
		
		if(rent == null)
			return tipoviVozila;
		
		ArrayList<Vozilo> svaVozila = (ArrayList<Vozilo>) voziloRepository.findAll();
		ArrayList<Vozilo> vozilaRent = new ArrayList<Vozilo>();
		
		// ukoliko je vozilo iz tog servisa
		for(Vozilo v: svaVozila) 
		{
			if(v.getRentACar().getRentACarId() == rent.getRentACarId())
				vozilaRent.add(v);
		}
		
		
		for(Vozilo v: vozilaRent) 
		{
			if(!tipoviVozila.contains(v.getTip())) // ukoliko taj tip nije vec dodat
				tipoviVozila.add(v.getTip());
		}
		return tipoviVozila;
	}

	// vraca trenutno aktivan cenovnik
	public CenovnikRentACar getActivePricelist(Long idRent) 
	{
		ArrayList<CenovnikRentACar> sviCenovnici = (ArrayList<CenovnikRentACar>) cenRentRepository.findAll();
		ArrayList<CenovnikRentACar> cenovniciRent = new ArrayList<CenovnikRentACar>();
		
		// ukoliko je cenovnik iz tog rent-a-car-a
		for(CenovnikRentACar cenovnik : sviCenovnici) 
		{
			if(cenovnik.getRentACar().getRentACarId() == idRent)
				cenovniciRent.add(cenovnik);
		}
		
		LocalDate currentDate = LocalDate.now();
		System.out.println(" ------ Danasnji datum: " + currentDate + " ----- ");

		if(!cenovniciRent.isEmpty()) {
		for(CenovnikRentACar cenovnik : cenovniciRent) 
		{
			if(currentDate.isEqual(cenovnik.getPocetakVazenja())) { //ako je danasnji datum isti kao pocetak vazenja cenovnika
				return cenovnik;
			} else if(currentDate.isAfter(cenovnik.getPocetakVazenja())) { //ako je posle pocetka vazenja
				if(currentDate.isBefore(cenovnik.getPrestanakVazenja())) { //a pre kraja vazenja
					return cenovnik;
				}
 			} 
		}
		}
		
		CenovnikRentACar tempCenovnik = null;
		if(!cenovniciRent.isEmpty()) {
			tempCenovnik = cenovniciRent.get(0); //uzima prvi cenovnik iz liste cenovnika servisa
		}
		

		
		//Uzima se cenovnik ciji datum prestanka je najkasniji
		if(cenovniciRent.size() > 1) 
		{
			for(int i=1; i<cenovniciRent.size(); i++) 
			{ 
				if(cenovniciRent.get(i).getPrestanakVazenja().isAfter(tempCenovnik.getPrestanakVazenja())) 
				{
					tempCenovnik = cenovniciRent.get(i);
				}
			}
		}
		return tempCenovnik;
	}


	// brise cenovnik
	public boolean obrisiCenovnik(Long idRentACar, Long idCenovnik)
	{
		
		for (CenovnikRentACar cenovnik: cenRentRepository.findAll())
		{
			if (cenovnik.getRentACar().getRentACarId() == idRentACar)
			{
				if (cenovnik.getId() == idCenovnik)
				{
					cenRentRepository.deleteById(idCenovnik);
					return true ;
				}
			}
		}
		
		return false ; // ne postoji cenovnik sa tim id-jem
	}
}
