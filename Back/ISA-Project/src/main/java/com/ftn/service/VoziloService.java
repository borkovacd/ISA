package com.ftn.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.dto.VoziloDTO;
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
	public void dodajVozilo(VoziloDTO dto, Long idRentACar) throws Exception 
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
		v.setTip(dto.getTip());
		
		voziloRepository.save(v);
		
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
		v.setTip(dto.getTip());
		
		v.setVoziloId(idVozila);
		
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
					voziloRepository.deleteByVoziloId(idVozila);
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
		
		for (int i = 0 ; i < rentACar.getSpisakVozila().size(); i++)
		{
			Vozilo v = new Vozilo();
			v.setRentACar(rentACar.getSpisakVozila().get(i).getRentACar());
			v.setBrojSedista(rentACar.getSpisakVozila().get(i).getBrojSedista());
			v.setCena(rentACar.getSpisakVozila().get(i).getCena());
			v.setGodinaProizvodnje(rentACar.getSpisakVozila().get(i).getGodinaProizvodnje());
			v.setMarka(rentACar.getSpisakVozila().get(i).getMarka());
			v.setModel(rentACar.getSpisakVozila().get(i).getModel());
			v.setNaziv(rentACar.getSpisakVozila().get(i).getNaziv());
			v.setRezervisano(rentACar.getSpisakVozila().get(i).isRezervisano());
			v.setTip(rentACar.getSpisakVozila().get(i).getTip());
			
			vozila.add(v);
			voziloRepository.save(v); 	// WARUM
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
		
		for (Vozilo v: vozila)
		{
			if (v.getVoziloId() == idVozila && v.isRezervisano() == true) // ukoliko je vozilo sa tim id-jem i ukoliko je rezervisano
			{
				taken = true ;
			}
		}
			
		return taken;
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
