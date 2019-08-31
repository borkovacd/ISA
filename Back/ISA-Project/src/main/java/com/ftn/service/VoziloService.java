package com.ftn.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.dto.VoziloDTO;
import com.ftn.dto.VremenskiPeriodDTO;
import com.ftn.enums.TipSobe;
import com.ftn.enums.TipVozila;
import com.ftn.model.Korisnik;
import com.ftn.model.rentacar.RentACar;
import com.ftn.model.rentacar.RezervacijaVozila;
import com.ftn.model.rentacar.Vozilo;
import com.ftn.repository.RentCarRepository;
import com.ftn.repository.RezervacijaVozilaRepository;
import com.ftn.repository.UserRepository;
import com.ftn.repository.VoziloRepository;

@Service
public class VoziloService 
{
	@Autowired
	private VoziloRepository voziloRepository;
	
	@Autowired
	private RentCarRepository rentRepository;
	
	@Autowired
	private UserRepository userRepository ;
	
	@Autowired
	private RezervacijaVozilaRepository rezVoziloRepository ;
	
	
	// Dodavanje vozila
	public Vozilo dodajVozilo(VoziloDTO dto, Long idRentACar)  
	{
		
		String nazivRent = rentRepository.getOne(idRentACar).getNaziv();
		RentACar rentACar = rentRepository.findOneByRentACarId(idRentACar);
		Vozilo v = new Vozilo();
		
		v.setRentACar(rentACar);
		v.setBrojSedista(dto.getBrojSedista());
		v.setCena(dto.getCena());
		v.setGodinaProizvodnje(dto.getGodinaProizvodnje());
		v.setMarka(dto.getMarka());
		v.setModel(dto.getModel());
		v.setNaziv(dto.getNaziv());
		v.setRezervisano(false);
		
		if (dto.getTip().equals("LIMUZINA"))
		{
			v.setTip(TipVozila.LIMUZINA);
		}
		else if(dto.getTip().equals("KARAVAN")) 
		{
			v.setTip(TipVozila.KARAVAN);
		}
		else if(dto.getTip().equals("KUPE")) 
		{
			v.setTip(TipVozila.KUPE);
		}
		else if(dto.getTip().equals("KABRIOLET")) 
		{
			v.setTip(TipVozila.KABRIOLET);
		}
		else if(dto.getTip().equals("MINIVEN")) 
		{
			v.setTip(TipVozila.MINIVEN);
		}
		else if(dto.getTip().equals("DZIP")) 
		{
			v.setTip(TipVozila.DZIP);
		}
		else if(dto.getTip().equals("PICKUP")) 
		{
			v.setTip(TipVozila.PICKUP);
		}
		
		voziloRepository.save(v);
		return v ;
		
	}
	
	// Izmena vozila
	public Vozilo izmeniVozilo(Long idRentACar, Long idVozila, VoziloDTO dto) 
	{
		Vozilo v = voziloRepository.findOneByVoziloId(idVozila);
		
		// ukoliko nije pronasao vozilo sa tim id-jem
		if (v == null)
		{
			return v ;
		}
		
		RentACar rentACar = rentRepository.findOneByRentACarId(idRentACar);
		v.setRentACar(rentACar);
		
		v.setBrojSedista(dto.getBrojSedista());
		v.setCena(dto.getCena());
		v.setGodinaProizvodnje(dto.getGodinaProizvodnje());
		v.setMarka(dto.getMarka());
		v.setModel(dto.getModel());
		v.setNaziv(dto.getNaziv());
		v.setRezervisano(false);
		
		if (dto.getTip().equals("LIMUZINA"))
		{
			v.setTip(TipVozila.LIMUZINA);
		}
		else if(dto.getTip().equals("KARAVAN")) 
		{
			v.setTip(TipVozila.KARAVAN);
		}
		else if(dto.getTip().equals("KUPE")) 
		{
			v.setTip(TipVozila.KUPE);
		}
		else if(dto.getTip().equals("KABRIOLET")) 
		{
			v.setTip(TipVozila.KABRIOLET);
		}
		else if(dto.getTip().equals("MINIVEN")) 
		{
			v.setTip(TipVozila.MINIVEN);
		}
		else if(dto.getTip().equals("DZIP")) 
		{
			v.setTip(TipVozila.DZIP);
		}
		else if(dto.getTip().equals("PICKUP")) 
		{
			v.setTip(TipVozila.PICKUP);
		}
		
		
		voziloRepository.save(v);
		
		return v ;
	}

	// metoda za brisanje vozila
	public boolean obrisiVozilo(Long idRentACar, Long idVozila)
	{
		for (Vozilo vozilo: voziloRepository.findAll())
		{
			if (vozilo.getRentACar().getRentACarId() == idRentACar) // ukoliko je vozilo iz tog rent-a-car
			{
				if (vozilo.getVoziloId() == idVozila)
				{
					// dodaj za brisanje iz cenovnika
					voziloRepository.deleteById(idVozila);
					return true ;
				}
			}
		}
			
		return false; // ne postoji vozilo sa prosledjenim ID-jem, pa se ne moze ni obrisati
	}
	
	// cuvanje
	public void save(Vozilo vozilo)
	{
		voziloRepository.save(vozilo);
	}
	
	// vraca sva vozila
	public List<Vozilo> getAll()
	{
		return voziloRepository.findAll();
	}
	
	// vraca sva vozila tog rent-a-car servisa
	public ArrayList<Vozilo> getVozilaRentACar(Long idRentACar) throws Exception 
	{
		
		RentACar rentACar = rentRepository.findOneByRentACarId(idRentACar);
		List<Vozilo> vozila = new ArrayList<Vozilo>();
		List<Vozilo> svaVozila = voziloRepository.findAll();
		
		for (Vozilo v: svaVozila)
		{
			if (v.getRentACar().getRentACarId() == idRentACar)
			{
				vozila.add(v);
			}
		}
		
		return (ArrayList<Vozilo>) vozila;
	}
	

	
	// vraca vozilo na osnovu imena
	public Vozilo findByNaziv(String naziv)
	{
		return voziloRepository.findOneByNaziv(naziv);
	}
	
	// vraca vozilo na osnocu njegovog id-ja
	public Vozilo findByVoziloId(Long id)
	{
		return voziloRepository.findOneByVoziloId(id);
	}
	
	// provera da li je neko vozilo rezervisano
	public boolean checkIfVoziloIsReserved(Long idVozila) 
	{
		
		boolean taken = false;
	
		List<Vozilo> vozila = voziloRepository.findAll();
		List<RezervacijaVozila> rezervacije = rezVoziloRepository.findAll();
		
		Vozilo vozilo = voziloRepository.findOneByVoziloId(idVozila);
		
		
		// prolazi kroz sve rezervacije i gleda da li se to vozilo nalazi u rezervacijama
		// i da li je status tog vozila da je rezervisano
		for (RezervacijaVozila rez : rezervacije)
		{
			if (rez.getVozilo().getVoziloId() == idVozila && vozilo.isRezervisano() == true)
			{
				taken = true ; 
			}
		}
			
		return taken;
	}
	
	// vraca slobodna vozilaza izabran rent-a-car u datom vremenskom periodu
	public ArrayList<Vozilo> getSlobodnaVozilaPeriod(VremenskiPeriodDTO vpDTO, Long idRent)
	{
		ArrayList<Vozilo> slobodnaVozila = new ArrayList<Vozilo>();
		
		String europeanDatePattern = "yyyy-MM-dd";
		DateTimeFormatter europeanDateFormatter = DateTimeFormatter.ofPattern(europeanDatePattern);
		LocalDate d1 = LocalDate.parse(vpDTO.getStartDate(), europeanDateFormatter);
		LocalDate d2 = LocalDate.parse(vpDTO.getEndDate(), europeanDateFormatter);
		
		//Provera datuma
		if(d1.isAfter(d2)) { //da li je pocetni datum posle krajnjeg datuma
			return null;
		}
		
		ArrayList<Vozilo> vozilaRent = new ArrayList<Vozilo>();
		ArrayList<Vozilo> svaVozila = (ArrayList<Vozilo>) voziloRepository.findAll();
		
		RentACar rent = rentRepository.getOne(idRent);
		
		// ukoliko nije prosledjen dobar rent-a-car
		if (rent == null)
		{
			return null ;
		}
		else
		{
			for (Vozilo v: svaVozila)
			{
				if (v.getRentACar().getRentACarId() == idRent)
				{
					vozilaRent.add(v);
				}
			}
		}
		
		List<RezervacijaVozila> rezervacije = rezVoziloRepository.findAll();
		
		for (Vozilo v: vozilaRent)
		{
			boolean slobodno = true ;
			for (RezervacijaVozila rez: rezervacije)
			{
				if (rez.getVozilo().getVoziloId() == v.getVoziloId()) // da li se radi o istom vozilu
				{
					if (d1.isBefore(rez.getDatumPreuzimanja()))
					{
						if (d2.isAfter(rez.getDatumPreuzimanja()))
						{
							slobodno = false ;
						}
						
						else if(d1.isAfter(rez.getDatumPreuzimanja()))
						{
							if (d2.isBefore(rez.getDatumVracanja()))
							{
								slobodno = false ;
							}
						}
					}
				}
			}
			
			if (slobodno == true)
			{
				slobodnaVozila.add(v);
			}
			
		}
		
		return slobodnaVozila ;
	}
	
	
	
	// vraca vozila izabranog korisnika
	public ArrayList<Vozilo> getVozilaKorisnik(Long idKorisnik) throws Exception 
	{
		ArrayList<Vozilo> vozila = new ArrayList<Vozilo>();
		Korisnik korisnik = userRepository.getOne(idKorisnik);
		
		List<RezervacijaVozila> rezervacijeVozila = rezVoziloRepository.findAll();
		List<Vozilo> svaVozila = voziloRepository.findAll();
		
		
		// ukoliko ne postoji nijedno vozilo
		if (svaVozila.size() == 0) // svaVozila == null
		{
			return vozila ;
		}
		
		// ukoliko ne postoji nijedna rezervacija
		if (rezervacijeVozila.size() == 0) // rezervacijeVozila == null
		{
			return vozila ;
		}
		
		for (int i = 0; i < rezervacijeVozila.size(); i++) // prolazak kroz sve rezervacije
		{
			if (rezVoziloRepository.findAll().get(i).getKorisnik().getId() == idKorisnik) // Da li je to rezervacija ovog korisnika?
			{

				Vozilo v = rezVoziloRepository.findAll().get(i).getVozilo();
				vozila.add(v);
			}
		}
		
		return vozila ;
		
	}
	

}
