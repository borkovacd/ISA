package com.ftn.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.repository.OcenaRentRepository;
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
import com.ftn.dto.OcenaRentDTO;
import com.ftn.dto.OcenaSobaDTO;
import com.ftn.dto.OcenaVoziloDTO;
import com.ftn.model.rentacar.Vozilo;
import com.ftn.model.rentacar.OcenaRentACar;
import com.ftn.model.rentacar.OcenaVozilo;
import com.ftn.model.rentacar.RentACar;
import com.ftn.model.rentacar.RezervacijaVozila;

@Service
public class OcenaSobaService 
{
	@Autowired
	private RezervacijaVozilaRepository rezVozRepository ;
	
	@Autowired
	private VoziloRepository voziloRepository ;
	
	@Autowired
	private OcenaSobaRepository ocenaRepository ;
	
	@Autowired 
	private RentCarRepository rentRepository ;
	
	@Autowired
	private SobaRepository sobaRepository ;
	
	@Autowired
	private RezervacijaHotelaRepository rezHotelRepository ;
	

		public boolean dozvoljenoOcenjivanje(Long idSobe, Long idUser)
		{
			List<RezervacijaHotela> sveRezervacije = rezHotelRepository.findAll();
			
			List<RezervacijaHotela> myReservations = new ArrayList<RezervacijaHotela>();
			
			List<OcenaSoba> ocene = ocenaRepository.findAll();
			
			// ukoliko je taj korisnik vec ocenjivao tu sobu
						for (OcenaSoba o: ocene)
						{
							if (o.getUser().getId() == idUser && o.getSoba().getId() == idSobe)
							{
								return false ;
							}
						}
			
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
			
			if (reservations.size() == 0)
			{
				return false ;
			}

			// pronadje rezervacije za to vozilo
			
			for (RezervacijaHotela r: reservations)
			{
				int brojSoba = r.getSobe().size();
				for (int i = 0; i < r.getSobe().size(); i++)
				{
					if (r.getSobe().get(i).getId() == idSobe) // ukoliko je to soba iz tog hotela
					{
						return true ;
					}
				}
			}

			return false ;

		}
		
		public void oceniSobu(OcenaSobaDTO ratingDTO, Korisnik user, Long idSoba) throws Exception
		{
						
			Soba soba = sobaRepository.getOne(idSoba);
			
			OcenaSoba rating = new OcenaSoba();
			
			int ratingMark = Integer.parseInt(ratingDTO.getOcena());

			rating.setOcena(ratingMark);
			rating.setUser(user);
			rating.setSoba(soba);
			
			ocenaRepository.save(rating);
			
		}
		
		// vraca prosecnu ocenu vozila
		public double getProsecnaOcenaSoba(Long idSoba)
		{
			double average = 0;
			double ukupno = 0;
			int brojac = 0;
			
			List<OcenaSoba> ratings = new ArrayList<OcenaSoba>();
			ratings = ocenaRepository.findBySobaId(idSoba); // vrati sve rejtinge tog vozila
			
			if (ratings.size() == 0)
			{
				System.out.println("RATINGS JE NULL");
				return average;
			}
			else
			{
				for (OcenaSoba r : ratings)
				{
					brojac += 1 ;
					ukupno += r.getOcena();
				}
				
				average = ukupno / brojac ;
				
				return average ;
			}
			
		}
}
