package com.ftn.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.repository.OcenaRentRepository;
import com.ftn.repository.OcenaVoziloRepository;
import com.ftn.repository.RentCarRepository;
import com.ftn.repository.RezervacijaVozilaRepository;
import com.ftn.repository.VoziloRepository;
import com.ftn.repository.UserRepository;
import com.ftn.model.Korisnik;
import com.ftn.model.hotels.OcenaSoba;
import com.ftn.dto.OcenaRentDTO;
import com.ftn.dto.OcenaVoziloDTO;
import com.ftn.model.rentacar.Vozilo;
import com.ftn.model.rentacar.OcenaRentACar;
import com.ftn.model.rentacar.OcenaVozilo;
import com.ftn.model.rentacar.RentACar;
import com.ftn.model.rentacar.RezervacijaVozila;

@Service
public class OcenaRentService 
{
	@Autowired
	private RezervacijaVozilaRepository rezVozRepository ;
	
	@Autowired
	private VoziloRepository voziloRepository ;
	
	@Autowired
	private OcenaRentRepository ocenaRepository ;
	
	@Autowired 
	private RentCarRepository rentRepository ;
	
	
	
	// Korisnik moze da salje poruku agentu jedino ukoliko vec ima kreiranu rezervaciju
		public boolean dozvoljenoOcenjivanje(Long idRent, Long idUser)
		{
			List<RezervacijaVozila> sveRezervacije = rezVozRepository.findAll();
			
			List<RezervacijaVozila> myReservations = new ArrayList<RezervacijaVozila>();
			
			List<OcenaRentACar> ocene = ocenaRepository.findAll();
			
			// ukoliko je korisnik vec ocenjivao taj rent-a-car
			for (OcenaRentACar o: ocene)
			{
				if (o.getUser().getId() == idUser && o.getRent().getRentACarId() == idRent)
				{
					return false ;
				}
			}		
			
			if (sveRezervacije.size() == 0)
			{
				return false ;
			}
			
			for (RezervacijaVozila res : sveRezervacije)
			{
				if ((res.getKorisnik().getId()) == idUser)
				{
					myReservations.add(res);
				}
			}
			
			// ovde sam izvukla moje rezervacije
			List<RezervacijaVozila> reservations = new ArrayList<RezervacijaVozila>();

			
			if (myReservations.size() == 0) // ukoliko nije pronasao nijednu rezervaciju kod datog korisnika
			{
				System.out.println("NEMA REZERVACIJA ");
				return false ;
			}
			else // 
			{
				for (RezervacijaVozila r : myReservations)
				{
					System.out.println("DATUM DATUM");
					if (r.getDatumVracanja().isBefore(LocalDate.now())) // krajnji datum rezervacije je pre danasnjeg datuma
					{	
						reservations.add(r);
					}
				}				
				
			}
			
			// sad ima moje rezervacije koje mogu da se ocenjuju
			
			if (reservations.size() == 0)
			{
				return false ;
			}

			// pronadje rezervacije za to vozilo			
			for (RezervacijaVozila rez: reservations)
			{
				if (rez.getVozilo().getRentACar().getRentACarId() == idRent)
				{
					return true ;
				}
			}
			
			
			return false ;

		}
		
		public void oceniRent(OcenaRentDTO ratingDTO, Korisnik user, Long idRent) throws Exception
		{
						
			RentACar rent = rentRepository.getOne(idRent);
			
			OcenaRentACar rating = new OcenaRentACar();
			
			int ratingMark = Integer.parseInt(ratingDTO.getOcena());

			rating.setOcena(ratingMark);
			rating.setUser(user);
			rating.setRent(rent);
			
			ocenaRepository.save(rating);
			
		}
		
		// vraca prosecnu ocenu vozila
		public double getProsecnaOcenaRent(Long idRent)
		{
			double average = 0;
			double ukupno = 0;
			int brojac = 0;
			
			List<OcenaRentACar> ratingsRent = new ArrayList<OcenaRentACar>();
			List<OcenaRentACar> allRatings = ocenaRepository.findAll();
			
			if (allRatings.size() == 0)
			{
				return average ;
			}
			
			for (OcenaRentACar r: allRatings)
			{
				if(r.getRent().getRentACarId() == idRent)
				{
					ratingsRent.add(r);
				}
			}
			
			if (ratingsRent.size() == 0)
			{
				System.out.println("RATINGS JE NULL");
				return average;
			}
			else
			{
				for (OcenaRentACar r : ratingsRent)
				{
					brojac += 1 ;
					ukupno += r.getOcena();
				}
				
				average = ukupno / brojac ;
				
				return average ;
			}
			
		}

}
