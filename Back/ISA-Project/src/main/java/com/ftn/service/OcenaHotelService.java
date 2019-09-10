package com.ftn.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.repository.OcenaSobaRepository;
import com.ftn.repository.OcenaVoziloRepository;
import com.ftn.repository.RentCarRepository;
import com.ftn.repository.RezervacijaHotelaRepository;
import com.ftn.repository.RezervacijaVozilaRepository;
import com.ftn.repository.SobaRepository;
import com.ftn.repository.VoziloRepository;
import com.ftn.repository.UserRepository;
import com.ftn.model.Korisnik;
import com.ftn.model.hotels.OcenaSoba;
import com.ftn.model.hotels.RezervacijaHotela;
import com.ftn.model.hotels.Soba;
import com.ftn.dto.OcenaHotelDTO;
import com.ftn.dto.OcenaSobaDTO;
import com.ftn.dto.OcenaVoziloDTO;
import com.ftn.model.rentacar.Vozilo;
import com.ftn.model.rentacar.OcenaVozilo;
import com.ftn.model.rentacar.RezervacijaVozila;
import com.ftn.model.hotels.Hotel;
import com.ftn.model.hotels.OcenaHotel;
import com.ftn.repository.HotelRepository;
import com.ftn.repository.OcenaHotelRepository;

@Service
public class OcenaHotelService 
{
	@Autowired
	private RezervacijaVozilaRepository rezVozRepository ;
	
	@Autowired
	private VoziloRepository voziloRepository ;
	
	@Autowired
	private OcenaHotelRepository ocenaRepository ;
	
	@Autowired 
	private RentCarRepository rentRepository ;
	
	@Autowired
	private SobaRepository sobaRepository ;
	
	@Autowired
	private RezervacijaHotelaRepository rezHotelRepository ;
	
	@Autowired
	private HotelRepository hotelRepository ;
	

		public boolean dozvoljenoOcenjivanje(Long idHotel, Long idUser)
		{
			List<RezervacijaHotela> sveRezervacije = rezHotelRepository.findAll();
			
			List<RezervacijaHotela> myReservations = new ArrayList<RezervacijaHotela>();
			
			if (sveRezervacije.size() == 0)
			{
				return false ;
			}
			
			for (RezervacijaHotela res : sveRezervacije)
			{
				if ((res.getKorisnik().getId()) == idUser)
				{
					myReservations.add(res);
				}
			}
			
			// izvukla sam moje rezervacije
			List<RezervacijaHotela> reservations = new ArrayList<RezervacijaHotela>();

			
			if (myReservations.size() == 0) // ukoliko nije pronasao nijednu rezervaciju kod datog korisnika
			{
				System.out.println("NEMA REZERVACIJA ");
				return false ;
			}
			else // 
			{
				for (RezervacijaHotela r : myReservations)
				{
					System.out.println("DATUM DATUM");
					if (r.getDatumKraja().isBefore(LocalDate.now())) // krajnji datum rezervacije je pre danasnjeg datuma
					{	
						reservations.add(r);
					}
				}				
				
			}
			
			// izvukla sam rezervacije koje odgovaraju po datumu
			
			if (reservations.size() == 0)
			{
				return false ;
			}

			// sada pokusavam da nadjem rezervacije za taj hotel
			
			for (RezervacijaHotela r: reservations)
			{
				int brojSoba = r.getSobe().size();
				for (int i = 0; i < r.getSobe().size(); i++)
				{
					if (r.getSobe().get(i).getHotel().getId() == idHotel) // ukoliko je to soba iz tog hotela
					{
						return true ;
					}
				}
			}

			return false ;

		}
		
		public void oceniHotel(OcenaHotelDTO ratingDTO, Korisnik user, Long idHotel) throws Exception
		{
						
			Hotel hotel = hotelRepository.getOne(idHotel);
			
			OcenaHotel rating = new OcenaHotel();
			
			int ratingMark = Integer.parseInt(ratingDTO.getOcena());

			rating.setOcena(ratingMark);
			rating.setUser(user);
			rating.setHotel(hotel);
			
			ocenaRepository.save(rating);
			
		}
		
		// vraca prosecnu ocenu vozila
		public double getProsecnaOcenaHotel(Long idHotel)
		{
			double average = 0;
			double ukupno = 0;
			int brojac = 0;
			
			List<OcenaHotel> ratings = new ArrayList<OcenaHotel>();
			ratings = ocenaRepository.findByHotelId(idHotel); // vrati sve rejtinge tog vozila
			
			if (ratings.size() == 0)
			{
				System.out.println("RATINGS JE NULL");
				return average;
			}
			else
			{
				for (OcenaHotel r : ratings)
				{
					brojac += 1 ;
					ukupno += r.getOcena();
				}
				
				average = ukupno / brojac ;
				
				return average ;
			}
			
		}

}
