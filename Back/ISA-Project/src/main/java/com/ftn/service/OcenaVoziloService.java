package com.ftn.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.repository.OcenaVoziloRepository;
import com.ftn.repository.RezervacijaVozilaRepository;
import com.ftn.repository.VoziloRepository;
import com.ftn.repository.UserRepository;
import com.ftn.model.Korisnik;
import com.ftn.dto.OcenaVoziloDTO;
import com.ftn.model.rentacar.Vozilo;
import com.ftn.model.rentacar.OcenaRentACar;
import com.ftn.model.rentacar.OcenaVozilo;
import com.ftn.model.rentacar.RezervacijaVozila;

@Service
public class OcenaVoziloService 
{
	
	@Autowired
	private RezervacijaVozilaRepository rezVozRepository ;
	
	@Autowired
	private VoziloRepository voziloRepository ;
	
	@Autowired
	private OcenaVoziloRepository ocenaRepository ;
	
	// Korisnik moze da salje poruku agentu jedino ukoliko vec ima kreiranu rezervaciju
		public boolean dozvoljenoOcenjivanje(Long idVozila, Long idUser)
		{
			List<RezervacijaVozila> sveRezervacije = rezVozRepository.findAll();
			List<RezervacijaVozila> reservations = new ArrayList<RezervacijaVozila>();
			List<OcenaVozilo> ocene = ocenaRepository.findAll();
			
			// ukoliko je taj korisnik vec ocenjivao to vozilo
			for (OcenaVozilo o: ocene)
			{
				if (o.getUser().getId() == idUser && o.getVozilo().getVoziloId() == idVozila)
				{
					return false ;
				}
			}
			
			// pronadje rezervacije za to vozilo			
			for (RezervacijaVozila rez: sveRezervacije)
			{
				if (rez.getVozilo().getVoziloId() == idVozila)
				{
					reservations.add(rez);
				}
			}
			
			
			
			List<RezervacijaVozila> myReservations = new ArrayList<RezervacijaVozila>();
			
			if (reservations.size() == 0)
			{
				return false ;
			}
			
			for (RezervacijaVozila res : reservations)
			{
				if ((res.getKorisnik().getId()) == idUser)
				{
					myReservations.add(res);
				}
			}
			
			if (myReservations.size() == 0) // ukoliko nije pronasao nijednu rezervaciju za prosledjen idVozila, kod datog korisnika
			{
				System.out.println("NEMA REZERVACIJA ");
				return false ;
			}
			else // postoji makar jedno vozilo u listi rezervacija sa tim id-jem, za tog User-a
			{
				
				for (RezervacijaVozila r : myReservations)
				{
					System.out.println("DATUM DATUM");
					if (r.getDatumVracanja().isBefore(LocalDate.now())) // krajnji datum rezervacije je pre danasnjeg datuma
					{	
						return true ;
					}
				}
				
				return false ;
				
			}
		}
		
		public void oceniVozilo(OcenaVoziloDTO ratingDTO, Korisnik user, Long idVozila) throws Exception
		{
			
			Vozilo v = voziloRepository.findOneByVoziloId(idVozila);
			
			OcenaVozilo rating = new OcenaVozilo();
			
			int ratingMark = Integer.parseInt(ratingDTO.getOcena());

			rating.setOcena(ratingMark);
			rating.setUser(user);
			rating.setVozilo(v);
			
			ocenaRepository.save(rating);
			
		}
		
		// TEST
		public OcenaVozilo oceniVoziloTest(OcenaVoziloDTO ratingDTO, Korisnik user, Long idVozila)
		{
			
			Vozilo v = voziloRepository.findOneByVoziloId(idVozila);
			
			OcenaVozilo rating = new OcenaVozilo();
			
			int ratingMark = Integer.parseInt(ratingDTO.getOcena());

			rating.setOcena(ratingMark);
			rating.setUser(user);
			rating.setVozilo(v);
			
			ocenaRepository.save(rating);
			return rating ;
			
		}
		
		// vraca prosecnu ocenu vozila
		public double getProsecnaOcenaVozila(Long idVozila)
		{
			double average = 0;
			double ukupno = 0;
			int brojac = 0;
			
			List<OcenaVozilo> ratings = new ArrayList<OcenaVozilo>();
			ratings = ocenaRepository.findByVoziloVoziloId(idVozila); // vrati sve rejtinge tog vozila
			
			if (ratings.size() == 0)
			{
				System.out.println("RATINGS JE NULL");
				return average;
			}
			else
			{
				for (OcenaVozilo r : ratings)
				{
					brojac += 1 ;
					ukupno += r.getOcena();
				}
				
				average = ukupno / brojac ;
				
				return average ;
			}
			
		}
		
		// vraca prosecnu ocenu vozila
		public double getProsecnaOcenaVozilaTest(Long idVozila)
		{
			double average = 0;
			double ukupno = 0;
			int brojac = 0;
			
			List<OcenaVozilo> ratingsRent = new ArrayList<OcenaVozilo>();
			List<OcenaVozilo> allRatings = ocenaRepository.findAll();
			
			if (allRatings.size() == 0)
			{
				return average ;
			}
			
			for (OcenaVozilo r: allRatings)
			{
				if(r.getVozilo().getVoziloId() == idVozila)
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
				for (OcenaVozilo r : ratingsRent)
				{
					brojac += 1 ;
					ukupno += r.getOcena();
				}
				
				average = ukupno / brojac ;
				
				return average ;
			}
			
		}

		
		// vraca listu ocena tog vozila
		public List<OcenaVozilo> vratiListuOcenaVozila(Long idVozilo)
		{
			List<OcenaVozilo> ratings = ocenaRepository.findByVoziloVoziloId(idVozilo);
			return ratings ;
			
		}
		// ****************** ADMIN ************************ 
		
		// vraca listu svih ratinga
		public List<OcenaVozilo> vratiListuSvihOcena()
		{
				
			List<OcenaVozilo> allRatings = ocenaRepository.findAll();
	
			return allRatings ;
		}
		

}
