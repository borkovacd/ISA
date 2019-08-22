package com.ftn.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.dto.LokacijaDTO;
import com.ftn.model.rentacar.Lokacija;
import com.ftn.model.rentacar.RentACar;
import com.ftn.repository.LokacijaRepository;
import com.ftn.repository.RentCarRepository;

@Service
public class LokacijaService 
{
	@Autowired
	private LokacijaRepository lokRepository;
	
	@Autowired
	private RentCarRepository rentRepository ;
	
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
			
			for (int i = 0 ; i < rentACar.getLokacije().size(); i++)
			{
				Lokacija l = new Lokacija();
				
				l.setAdresa(rentACar.getLokacije().get(i).getAdresa());
				l.setDrzava(rentACar.getLokacije().get(i).getDrzava());
				l.setGrad(rentACar.getLokacije().get(i).getGrad());
				l.setLatitude(rentACar.getLokacije().get(i).getLatitude());
				l.setLongitude(rentACar.getLokacije().get(i).getLongitude());
				l.setRentACar(rentACar);
				
				lokacije.add(l);
				lokRepository.save(l); // WARUM
				
			}
			
			
			return (ArrayList<Lokacija>) lokacije;
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
		
		
}
