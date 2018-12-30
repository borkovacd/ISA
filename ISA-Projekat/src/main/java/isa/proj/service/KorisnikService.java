package isa.proj.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.proj.model.Korisnik;
import isa.proj.repository.KorisnikRepository;

@Service
public class KorisnikService {
	@Autowired
	private KorisnikRepository korisnikRepository;
	
	public Optional<Korisnik> getById(Integer id) {
		return korisnikRepository.findById(id);
	}
}
