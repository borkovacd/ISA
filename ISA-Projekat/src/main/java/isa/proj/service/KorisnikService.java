package isa.proj.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.proj.model.AdministratorHotela;
import isa.proj.model.Hotel;
import isa.proj.model.Korisnik;
import isa.proj.repository.KorisnikRepository;

@Service
public class KorisnikService {
	@Autowired
	private KorisnikRepository korisnikRepository;
	
	public Korisnik getById(Integer id) {
		return korisnikRepository.findById(id).orElse(null);
	}
	
	public List<Korisnik> getAllUsers() {
		List<Korisnik> korisnici = new ArrayList<Korisnik>();
		
		//for each of the elements in the iterable, calling add method in korisnici and passing that element
		korisnikRepository.findAll()
		.forEach(korisnici::add); //method reference
	
		return korisnici;
	}

	public void promeniTipKorisnika(Korisnik korisnik) {
		Korisnik k = korisnikRepository.findById(korisnik.getIdKorisnika()).get();
		k.setUloga(korisnik.getUloga());
		korisnikRepository.save(k);
	}
	
	
}
