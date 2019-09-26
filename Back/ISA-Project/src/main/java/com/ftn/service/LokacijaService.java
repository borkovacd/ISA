package com.ftn.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.dto.LokacijaDTO;
import com.ftn.model.hotels.RezervacijaHotela;
import com.ftn.model.rentacar.Lokacija;
import com.ftn.model.rentacar.RentACar;
import com.ftn.model.rentacar.RezervacijaVozila;
import com.ftn.repository.LokacijaRepository;
import com.ftn.repository.RentCarRepository;
import com.ftn.repository.RezervacijaVozilaRepository;

@Service
public class LokacijaService 
{
	@Autowired
	private LokacijaRepository lokRepository;
	
	@Autowired
	private RentCarRepository rentRepository ;
	
	@Autowired
	private RezervacijaVozilaRepository rezVozRepository ;
	
	// Dodavanje filijale
		public void dodajFilijalu(LokacijaDTO dto, Long idRentACar) throws Exception 
		{
			
			String nazivRent = rentRepository.getOne(idRentACar).getNaziv();
			RentACar rentACar = rentRepository.findOneByRentACarId(idRentACar);
			Lokacija lokacija = new Lokacija();
			
			lokacija.setAdresa(dto.getAdresa());
			lokacija.setDrzava(dto.getDrzava());
			lokacija.setGrad(dto.getGrad());
			lokacija.setLatitude(dto.getLatitude());
			lokacija.setLongitude(dto.getLongitude());
			lokacija.setRentACar(rentACar);
			
			lokRepository.save(lokacija);
			
		}
		
		// TEST
		public Lokacija dodajLokaciju(LokacijaDTO dto, Long idRentACar) 
		{
			
			RentACar rentACar = rentRepository.findOneByRentACarId(idRentACar);
			Lokacija lokacija = new Lokacija();
			
			lokacija.setAdresa(dto.getAdresa());
			lokacija.setDrzava(dto.getDrzava());
			lokacija.setGrad(dto.getGrad());
			lokacija.setLatitude(dto.getLatitude());
			lokacija.setLongitude(dto.getLongitude());
			lokacija.setRentACar(rentACar);
			
			lokRepository.save(lokacija);
			
			return lokacija ;
			
		}
		
		// Izmena filijale
		public Lokacija izmeniFilijalu(Long idRentACar, Long idFilijala, LokacijaDTO dto) 
		{	
			Lokacija lok = lokRepository.findOneById(idFilijala);
			
			// ukoliko nije pronasao filijalu sa tim id-jem
			if (lok == null)
			{
				return lok ;
			}
			
			RentACar rentACar = rentRepository.findOneByRentACarId(idRentACar);
			lok.setRentACar(rentACar);
			
			lok.setAdresa(dto.getAdresa());
			lok.setDrzava(dto.getDrzava());
			lok.setGrad(dto.getGrad());
			lok.setLatitude(dto.getLatitude());
			lok.setLongitude(dto.getLongitude());
			
			lok.setId(idFilijala);

			lokRepository.save(lok);
			
			return lok ;
		}

		// metoda za brisanje filijale
		public boolean obrisiFilijalu(Long idRentACar, Long idLok)
		{
			for (Lokacija lokacija: lokRepository.findAll())
			{
				if (lokacija.getRentACar().getRentACarId() == idRentACar) // ukoliko je filijala iz tog rent-a-car
				{
					if (lokacija.getId() == idLok)
					{
						lokRepository.deleteById(idLok);
						return true ;
					}
				}
			}
				
			return false; // ne postoji lokacija sa prosledjenim ID-jem, pa se ne moze ni obrisati
		}
		
		// cuvanje
		public void save(Lokacija lok)
		{
			lokRepository.save(lok);
		}
		
		// vraca sve filijale
		public List<Lokacija> getAll()
		{
			return lokRepository.findAll();
		}
		
		// vraca sve filijale tog rent-a-car servisa
		public ArrayList<Lokacija> getFilijaleRentACar(Long idRentACar) throws Exception 
		{
			
			RentACar rentACar = rentRepository.findOneByRentACarId(idRentACar);
			List<Lokacija> lokacije = new ArrayList<Lokacija>();
			List<Lokacija> sveLokacije = lokRepository.findAll();
			
			for (Lokacija l: sveLokacije )
			{
				if (l.getRentACar().getRentACarId() == idRentACar)
				{
					lokacije.add(l);
				}
			}
			return (ArrayList<Lokacija>) lokacije;
		}
		
		// TEST
		public List<Lokacija> getFilijaleRentACarTest(Long idRentACar) throws Exception 
		{
			return lokRepository.findByRentACarRentACarId(idRentACar);
		}
		

		
		// vraca filijalu na osnovu adrese
		public Lokacija findByAdresa(String adresa)
		{
			return lokRepository.findOneByAdresa(adresa);
		}
		
		// vraca filijalu na osnovu njenog id-ja
		public Lokacija findById(Long id)
		{
			return lokRepository.findOneById(id);
		}
		
		// provera da li je neko vozilo rezervisano
		public boolean checkIfLokacijaIsReserved(Long idLokacije) 
		{	
			boolean taken = false;
			
			List<Lokacija> lokacije = lokRepository.findAll();
			List<Lokacija> lokacijeRent = new ArrayList<Lokacija>();
			
			ArrayList<RezervacijaVozila> rezervacije = (ArrayList<RezervacijaVozila>) rezVozRepository.findAll();

			// prolazi kroz sve rezervacije i gleda da li se to vozilo nalazi u rezervacijama
			// i da li je status tog vozila da je rezervisano
			for (RezervacijaVozila rez : rezervacije)
			{
				if (rez.getMestoPreuzimanja().getId() == idLokacije || rez.getMestoVracanja().getId() == idLokacije)
				{
					taken = true ; 
				}
			}			
			return taken;
		}	
		
}
