package isa.proj.coverter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import isa.proj.dto.KorisnikDTO;
import isa.proj.model.Korisnik;


@Component
public class KorisnikToKorisnikDTOConverter implements Converter<Korisnik,KorisnikDTO>{

	@Override
	public KorisnikDTO convert(Korisnik source) {
		if(source == null) {
			return null;
		}
		KorisnikDTO korisnikDTO = new KorisnikDTO();
		korisnikDTO.setIdKorisnika(source.getIdKorisnika());
		korisnikDTO.setIme(source.getIme());
		korisnikDTO.setPrezime(source.getPrezime());
		korisnikDTO.setKorisnickoIme(source.getKorisnickoIme());
		korisnikDTO.setLozinka(source.getLozinka());
		return korisnikDTO;
	}
	
}
