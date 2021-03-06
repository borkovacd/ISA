package com.ftn.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.ftn.dto.RezervacijaVozilaDTO;
import com.ftn.model.Korisnik;
import com.ftn.model.rentacar.CenovnikRentACar;
import com.ftn.model.rentacar.Lokacija;
import com.ftn.model.rentacar.RentACar;
import com.ftn.model.rentacar.RezervacijaVozila;
import com.ftn.model.rentacar.StavkaCenovnikaRent;
import com.ftn.model.rentacar.Vozilo;
import com.ftn.repository.CenovnikRentACarRepository;
import com.ftn.repository.LokacijaRepository;
import com.ftn.repository.RentCarRepository;
import com.ftn.repository.RezervacijaVozilaRepository;

import com.ftn.repository.StavkaCenovnikaRentRepository;
import com.ftn.repository.UserRepository;
import com.ftn.repository.VoziloRepository;

@Service
public class RezervacijaVozilaService 
{
	@Autowired
	private RezervacijaVozilaRepository rezVozRepository ;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private VoziloRepository voziloRepository ;
	
	@Autowired
	private StavkaCenovnikaRentRepository stavkaRentRepository ;
	
	@Autowired
	private CenovnikRentACarRepository cenRentRepository ;
	
	@Autowired
	private LokacijaRepository lokRepository ;
	
	@Autowired
	private RentCarRepository rentRepository ;
	
	// formira se rezervacija za korisnika sa prosledjenim id-jem
	@Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
	public RezervacijaVozila createReservationRent(RezervacijaVozilaDTO rezervacijaDTO, Long id) throws Exception 
	{
		RezervacijaVozila rezervacija = new RezervacijaVozila();
		
		String europeanDatePattern = "yyyy-MM-dd";
		DateTimeFormatter europeanDateFormatter = DateTimeFormatter.ofPattern(europeanDatePattern);
		LocalDate d1 = LocalDate.parse(rezervacijaDTO.getStartDate(), europeanDateFormatter);
		LocalDate d2 = LocalDate.parse(rezervacijaDTO.getEndDate(), europeanDateFormatter);
		
		//Provera datuma
		if(d1.isAfter(d2)) { //da li je pocetni datum posle krajnjeg datuma
			return null;
		}
		
		int brojDana =  (int) d1.until(d2, ChronoUnit.DAYS);
		
		
		rezervacija.setDatumPreuzimanja(d1);
		rezervacija.setDatumVracanja(d2);
		
		Korisnik korisnik = userRepository.getOne(id);
		rezervacija.setKorisnik(korisnik);
		
		int brojPutnika = Integer.parseInt(rezervacijaDTO.getNumberOfGuests());
		rezervacija.setBrojPutnika(brojPutnika);
		
		Long idVozila = Long.parseLong(rezervacijaDTO.getVozilo()) ; // u bazu je u toj koloni upisan id vozila
		//Vozilo v = voziloRepository.getOne(idVozila);
		Vozilo v = voziloRepository.vratiVoziloPoId(idVozila);
		
		/***** KONKURENTNOST *****/
		if (v.isNaPopustu() == true) throw new Exception(); 
		boolean slobodno = true ;
		
		List<RezervacijaVozila> sveRezervacije = rezVozRepository.findAll();
		for (RezervacijaVozila rez: sveRezervacije)
		{
			if (rez.getVozilo().getVoziloId() == v.getVoziloId()) // da li se radi o istom vozilu
			{
				if(d1.isBefore(rez.getDatumPreuzimanja()) || d1.equals(rez.getDatumPreuzimanja())) {
					if(d2.isAfter(rez.getDatumPreuzimanja()) || d2.equals(rez.getDatumPreuzimanja())) {
						slobodno = false;
					}
				} else if(d1.isAfter(rez.getDatumPreuzimanja()) || d1.equals(rez.getDatumVracanja())) {
					if(d1.isBefore(rez.getDatumVracanja()) || d1.equals(rez.getDatumVracanja())) {
						slobodno = false;
					}
				}
				else if (d1.isEqual(rez.getDatumPreuzimanja())) {
					if(d2.isEqual(rez.getDatumVracanja())) {
						slobodno = false;
					}
				}
			}
		}
		
		if (slobodno == false) throw new Exception();
		
		List<CenovnikRentACar> sviCenovnici = cenRentRepository.findAll();	
		/***** ****/
		
		
		
		rezervacija.setVozilo(v);
		
		String mestoP = rezervacijaDTO.getMestoPreuzimanja();
		Lokacija mestoPreuzimanja = lokRepository.findOneByAdresa(mestoP);
		rezervacija.setMestoPreuzimanja(mestoPreuzimanja);
		
		String mestoV = rezervacijaDTO.getMestoVracanja();
		Lokacija mestoVracanja = lokRepository.findOneByAdresa(mestoV);
		rezervacija.setMestoVracanja(mestoVracanja);

		List<CenovnikRentACar> cenovnici = cenRentRepository.findAll();
		List<StavkaCenovnikaRent> stavkeCenovnika = stavkaRentRepository.findAll();
		
		Double cenaRezervacije = (double) 0;
	
		Vozilo voziloFor = rezervacija.getVozilo();
		for(CenovnikRentACar cenovnik : cenovnici) 
			if(d1.isAfter(cenovnik.getPocetakVazenja()) || d1.isEqual(cenovnik.getPocetakVazenja())) 
				if(d2.isBefore(cenovnik.getPrestanakVazenja()) || d2.isEqual(cenovnik.getPrestanakVazenja()))
					if (cenovnik.getRentACar().getRentACarId() == voziloFor.getRentACar().getRentACarId())
						for (StavkaCenovnikaRent stavkaCenovnika: stavkeCenovnika)
							if (stavkaCenovnika.getCenovnik().getId() == cenovnik.getId())
								if (stavkaCenovnika.getTipVozila() == voziloFor.getTip())
									cenaRezervacije += stavkaCenovnika.getCena() * brojDana ;
		
		rezervacija.setCena(cenaRezervacije);
		rezVozRepository.save(rezervacija);
	
		return rezervacija;
	}
	
	
	// formira se rezervacija za korisnika sa prosledjenim id-jem
		@Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
		public String createReservationRentNova(RezervacijaVozilaDTO rezervacijaDTO, Long id) throws Exception 
		{
			
			String povratnaVrednost = ""; 
			String europeanDatePattern = "yyyy-MM-dd";
			DateTimeFormatter europeanDateFormatter = DateTimeFormatter.ofPattern(europeanDatePattern);
			LocalDate d1 = LocalDate.parse(rezervacijaDTO.getStartDate(), europeanDateFormatter);
			LocalDate d2 = LocalDate.parse(rezervacijaDTO.getEndDate(), europeanDateFormatter);
			
			//Provera datuma
			if(d1.isAfter(d2)) { //da li je pocetni datum posle krajnjeg datuma
				return null;
			}
			
			int brojDana =  (int) d1.until(d2, ChronoUnit.DAYS);
			
			Long idVozila = Long.parseLong(rezervacijaDTO.getVozilo()) ; // u bazu je u toj koloni upisan id vozila
			Vozilo v = voziloRepository.getOne(idVozila);
			//Vozilo v = voziloRepository.vratiVoziloPoId(idVozila);
			
			/***** KONKURENTNOST *****/
			if (v.isNaPopustu() == true)
			{
				return "greska";
			}
			boolean slobodno = true ;
			
			List<RezervacijaVozila> sveRezervacije = rezVozRepository.findAll();
			for (RezervacijaVozila rez: sveRezervacije)
			{
				if (rez.getVozilo().getVoziloId() == v.getVoziloId()) // da li se radi o istom vozilu
				{
					if(d1.isBefore(rez.getDatumPreuzimanja()) || d1.equals(rez.getDatumPreuzimanja())) {
						if(d2.isAfter(rez.getDatumPreuzimanja()) || d2.equals(rez.getDatumPreuzimanja())) {
							slobodno = false;
						}
					} else if(d1.isAfter(rez.getDatumPreuzimanja()) || d1.equals(rez.getDatumVracanja())) {
						if(d1.isBefore(rez.getDatumVracanja()) || d1.equals(rez.getDatumVracanja())) {
							slobodno = false;
						}
					}
					else if (d1.isEqual(rez.getDatumPreuzimanja())) {
						if(d2.isEqual(rez.getDatumVracanja())) {
							slobodno = false;
						}
					}
				}
			}
			
			if (slobodno == false)
			{
				return "greska" ;
			}
			
			List<CenovnikRentACar> sviCenovnici = cenRentRepository.findAll();	
			/***** ****/
			
			RezervacijaVozila rezervacija = new RezervacijaVozila();
			
			
			
			
			rezervacija.setDatumPreuzimanja(d1);
			rezervacija.setDatumVracanja(d2);
			
			Korisnik korisnik = userRepository.getOne(id);
			rezervacija.setKorisnik(korisnik);
			
			int brojPutnika = Integer.parseInt(rezervacijaDTO.getNumberOfGuests());
			rezervacija.setBrojPutnika(brojPutnika);
			
			
			
			
			rezervacija.setVozilo(v);
			
			String mestoP = rezervacijaDTO.getMestoPreuzimanja();
			Lokacija mestoPreuzimanja = lokRepository.findOneByAdresa(mestoP);
			rezervacija.setMestoPreuzimanja(mestoPreuzimanja);
			
			String mestoV = rezervacijaDTO.getMestoVracanja();
			Lokacija mestoVracanja = lokRepository.findOneByAdresa(mestoV);
			rezervacija.setMestoVracanja(mestoVracanja);

			List<CenovnikRentACar> cenovnici = cenRentRepository.findAll();
			List<StavkaCenovnikaRent> stavkeCenovnika = stavkaRentRepository.findAll();
			
			Double cenaRezervacije = (double) 0;
		
			Vozilo voziloFor = rezervacija.getVozilo();
			for(CenovnikRentACar cenovnik : cenovnici) 
				if(d1.isAfter(cenovnik.getPocetakVazenja()) || d1.isEqual(cenovnik.getPocetakVazenja())) 
					if(d2.isBefore(cenovnik.getPrestanakVazenja()) || d2.isEqual(cenovnik.getPrestanakVazenja()))
						if (cenovnik.getRentACar().getRentACarId() == voziloFor.getRentACar().getRentACarId())
							for (StavkaCenovnikaRent stavkaCenovnika: stavkeCenovnika)
								if (stavkaCenovnika.getCenovnik().getId() == cenovnik.getId())
									if (stavkaCenovnika.getTipVozila() == voziloFor.getTip())
										cenaRezervacije += stavkaCenovnika.getCena() * brojDana ;
			
			rezervacija.setCena(cenaRezervacije);
			rezVozRepository.save(rezervacija);
		
			return "ok";
		}


	
	@Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
	public RezervacijaVozila createReservationRentTest(RezervacijaVozilaDTO rezervacijaDTO, Long id) throws Exception 
	{
		RezervacijaVozila rezervacija = new RezervacijaVozila();
		
		String europeanDatePattern = "yyyy-MM-dd";
		DateTimeFormatter europeanDateFormatter = DateTimeFormatter.ofPattern(europeanDatePattern);
		LocalDate d1 = LocalDate.parse(rezervacijaDTO.getStartDate(), europeanDateFormatter);
		LocalDate d2 = LocalDate.parse(rezervacijaDTO.getEndDate(), europeanDateFormatter);
		
		//Provera datuma
		if(d1.isAfter(d2)) { //da li je pocetni datum posle krajnjeg datuma
			return null;
		}
		
		int brojDana =  (int) d1.until(d2, ChronoUnit.DAYS);
		
		
		rezervacija.setDatumPreuzimanja(d1);
		rezervacija.setDatumVracanja(d2);
		
		Korisnik korisnik = userRepository.getOne(id);
		rezervacija.setKorisnik(korisnik);
		
		int brojPutnika = Integer.parseInt(rezervacijaDTO.getNumberOfGuests());
		rezervacija.setBrojPutnika(brojPutnika);
		
		Long idVozila = Long.parseLong(rezervacijaDTO.getVozilo()) ; // u bazu je u toj koloni upisan id vozila
		Vozilo v = voziloRepository.getOne(idVozila);
		//Vozilo v = voziloRepository.vratiVoziloPoId(idVozila);
		
		/***** KONKURENTNOST *****/
		/***** ****/
		
		rezervacija.setVozilo(v);
		
		String mestoP = rezervacijaDTO.getMestoPreuzimanja();
		Lokacija mestoPreuzimanja = lokRepository.findOneByAdresa(mestoP);
		rezervacija.setMestoPreuzimanja(mestoPreuzimanja);
		
		String mestoV = rezervacijaDTO.getMestoVracanja();
		Lokacija mestoVracanja = lokRepository.findOneByAdresa(mestoV);
		rezervacija.setMestoVracanja(mestoVracanja);

		List<CenovnikRentACar> cenovnici = cenRentRepository.findAll();
		List<StavkaCenovnikaRent> stavkeCenovnika = stavkaRentRepository.findAll();
		
		Double cenaRezervacije = (double) 0;
	
		Vozilo voziloFor = rezervacija.getVozilo();
		for(CenovnikRentACar cenovnik : cenovnici) 
			if(d1.isAfter(cenovnik.getPocetakVazenja()) || d1.isEqual(cenovnik.getPocetakVazenja())) 
				if(d2.isBefore(cenovnik.getPrestanakVazenja()) || d2.isEqual(cenovnik.getPrestanakVazenja()))
					if (cenovnik.getRentACar().getRentACarId() == voziloFor.getRentACar().getRentACarId())
						for (StavkaCenovnikaRent stavkaCenovnika: stavkeCenovnika)
							if (stavkaCenovnika.getCenovnik().getId() == cenovnik.getId())
								if (stavkaCenovnika.getTipVozila() == voziloFor.getTip())
									cenaRezervacije += stavkaCenovnika.getCena() * brojDana ;
		
		rezervacija.setCena(cenaRezervacije);
		rezVozRepository.save(rezervacija);
	
		return rezervacija;
	}

	
	@Transactional(readOnly = true)
	public ArrayList<RezervacijaVozila> listaRezervacijaKorisnik(Long idKorisnik)
	{
		ArrayList<RezervacijaVozila> rezervacijeKorisnik = new ArrayList<RezervacijaVozila>();
		ArrayList<RezervacijaVozila> sveRezervacije = (ArrayList<RezervacijaVozila>) rezVozRepository.findAll();
		
		if (sveRezervacije == null)
		{
			return rezervacijeKorisnik ;
		}
		
		for (RezervacijaVozila r: sveRezervacije)
		{
			if (r.getKorisnik().getId() == idKorisnik)
			{
				rezervacijeKorisnik.add(r);
			}
		}
		
		return rezervacijeKorisnik ;
	}
	
	// TEST
	public List<RezervacijaVozila> listaRezervacijaKorisnikTest(Long idKorisnik)
	{

		return rezVozRepository.findByKorisnikId(idKorisnik) ;
	}
	
	
	@Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
	public RezervacijaVozila createOrChangeFastVoziloReservation(Long id, Long idRezervacijeLeta, Long idRent, Long idVozila) throws Exception 
	{
		
		//Potrebne informacije za rezervaciju izvuci iz rezervacije leta
		//Privremeno iz rezervacije vozila, dok ne bude uradjena rezervacija leta
		RezervacijaVozila rezervacijaLeta = rezVozRepository.getOne(idRezervacijeLeta);
		if(rezervacijaLeta == null) {
			return null;
		}
		
		LocalDate d1 = rezervacijaLeta.getDatumPreuzimanja();
		LocalDate d2 = rezervacijaLeta.getDatumVracanja();
		int brojGostiju = rezervacijaLeta.getBrojPutnika();
		int brojDana =  (int) d1.until(d2, ChronoUnit.DAYS);
		
		Lokacija mestoPreuzimanja = rezervacijaLeta.getMestoPreuzimanja();
		Lokacija mestoVracanja = rezervacijaLeta.getMestoVracanja();
		
		Korisnik korisnik = userRepository.getOne(id);
		if(korisnik == null) {
			return null;
		}

			RezervacijaVozila novaRezervacija = new RezervacijaVozila();
			novaRezervacija.setKorisnik(korisnik);
			novaRezervacija.setBrojPutnika(brojGostiju);
			novaRezervacija.setDatumPreuzimanja(d1);
			novaRezervacija.setDatumVracanja(d2);
			novaRezervacija.setTipRezervacije(1); //1 oznacava brzu rezervaciju
			
			
			//Vozilo v = voziloRepository.getOne(idVozila);
			Vozilo v = voziloRepository.vratiVoziloPoId(idVozila);
			
			/***** KONKURENTNOST *****/
			if (v.isNaPopustu() == false) throw new Exception(); 
			boolean slobodno = true ;
			
			List<RezervacijaVozila> sveRezervacije = rezVozRepository.findAll();
			for (RezervacijaVozila rez: sveRezervacije)
			{
				if (rez.getVozilo().getVoziloId() == v.getVoziloId()) // da li se radi o istom vozilu
				{
					if(d1.isBefore(rez.getDatumPreuzimanja()) || d1.equals(rez.getDatumPreuzimanja())) {
						if(d2.isAfter(rez.getDatumPreuzimanja()) || d2.equals(rez.getDatumPreuzimanja())) {
							slobodno = false;
						}
					} else if(d1.isAfter(rez.getDatumPreuzimanja()) || d1.equals(rez.getDatumVracanja())) {
						if(d1.isBefore(rez.getDatumVracanja()) || d1.equals(rez.getDatumVracanja())) {
							slobodno = false;
						}
					}
					else if (d1.isEqual(rez.getDatumPreuzimanja())) {
						if(d2.isEqual(rez.getDatumVracanja())) {
							slobodno = false;
						}
					}
				}
			}
			
			if (slobodno == false) throw new Exception();
			
			List<CenovnikRentACar> sviCenovnici = cenRentRepository.findAll();
			/***** ****/
			
			novaRezervacija.setVozilo(v);
			
			RentACar rent = rentRepository.getOne(idRent);
			novaRezervacija.setMestoPreuzimanja(mestoPreuzimanja);
			novaRezervacija.setMestoVracanja(mestoVracanja);
			
			List<CenovnikRentACar> cenovnici = cenRentRepository.findAll();
			List<StavkaCenovnikaRent> stavkeCenovnika = stavkaRentRepository.findAll();
			
			Double cenaRezervacije = (double) 0;
			
				for(CenovnikRentACar cenovnik : cenovnici) 
					if(d1.isAfter(cenovnik.getPocetakVazenja()) || d1.isEqual(cenovnik.getPocetakVazenja())) 
						if(d2.isBefore(cenovnik.getPrestanakVazenja()) || d2.isEqual(cenovnik.getPrestanakVazenja())) 
							if(cenovnik.getRentACar().getRentACarId() == v.getRentACar().getRentACarId())  
								for(StavkaCenovnikaRent stavkaCenovnika : stavkeCenovnika) 
									if(stavkaCenovnika.getCenovnik().getId() == cenovnik.getId()) 
										if(stavkaCenovnika.getTipVozila() == v.getTip()) 
											cenaRezervacije += stavkaCenovnika.getCena() * brojDana;
			
			novaRezervacija.setCena(cenaRezervacije);
			
			Double staraCena = novaRezervacija.getCena();
			novaRezervacija.setCena((staraCena)*0.9);
			rezVozRepository.save(novaRezervacija);
			return novaRezervacija; 
		
		
	}

	
	
}
