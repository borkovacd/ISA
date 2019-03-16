package isa.proj.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.http.ResponseEntity;
import isa.proj.model.Hotel;
import isa.proj.coverter.KorisnikToKorisnikDTOConverter;
import isa.proj.dto.KorisnikDTO;
import isa.proj.model.Korisnik;
import isa.proj.service.KorisnikService;

@RestController
@RequestMapping(value = "/korisnik")
public class KorisnikController {
	@Autowired
	private KorisnikService korisnikService;
	
	@Autowired
	private KorisnikToKorisnikDTOConverter korisnikConverter;
	
	@RequestMapping(
			value = "/getById/{idKor}",
			method = RequestMethod.GET
	)
	public KorisnikDTO getById(@PathVariable Integer idKor) {
		Korisnik kor = korisnikService.getById(idKor);
		return korisnikConverter.convert(kor);
		
	}
	
	@RequestMapping(
			value = "/izmeniPodatke",
			method = RequestMethod.POST
	)
	public KorisnikDTO izmeniPodatke(@RequestBody KorisnikDTO korisnikDTO) {
		Korisnik kor = korisnikService.getById(korisnikDTO.getIdKorisnika());
		if(kor != null) {
			kor.setIme(korisnikDTO.getIme());
			kor.setPrezime(korisnikDTO.getPrezime());
			kor.setKorisnickoIme(korisnikDTO.getKorisnickoIme());
		}
		Korisnik sacuvani = korisnikService.save(kor);
		return korisnikConverter.convert(sacuvani);
		
	}
	
	
}
