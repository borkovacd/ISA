package com.ftn.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.dto.ProveriDostupnostRentDTO;
import com.ftn.dto.VoziloDTO;
import com.ftn.dto.VremenskiPeriodDTO;
import com.ftn.enums.TipVozila;
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
	
	@Autowired
	private CenovnikRentACarRepository cenRentRepository ;
	
	@Autowired
	private StavkaCenovnikaRentRepository stavkaRentRepository ;
	
	@Autowired
	private LokacijaRepository lokRepository ;
	
	
	// Dodavanje vozila
	public Vozilo dodajVozilo(VoziloDTO dto, Long idRentACar)  
	{
		
		String nazivRent = rentRepository.getOne(idRentACar).getNaziv();
		RentACar rentACar = rentRepository.findOneByRentACarId(idRentACar);
		Vozilo v = new Vozilo();
		
		v.setRentACar(rentACar);
		v.setBrojSedista(dto.getBrojSedista());
		v.setCena(0);
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
		v.setCena(0);
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
	
	public ArrayList<Vozilo> checkAvailability(ProveriDostupnostRentDTO pdDTO, Long idRent) 
	{
		ArrayList<Vozilo> slobodnaVozila = new ArrayList<Vozilo>();
		
		Double minPrice = null;
		Double maxPrice = null;
		if(pdDTO.getPriceRange() != "") {
			if(pdDTO.getPriceRange().equals("RSD 0 - RSD 5.000")) {
				minPrice = 0.0;
				maxPrice = 5000.0;
			} else if(pdDTO.getPriceRange().equals("RSD 5.000 - RSD 10.000")) {
				minPrice = 5000.0;
				maxPrice = 10000.0;
			} else if(pdDTO.getPriceRange().equals("RSD 10.000 - RSD 20.000")) {
				minPrice = 10000.0;
				maxPrice = 20000.0;
			} else if(pdDTO.getPriceRange().equals("RSD 20.000+")) {
				minPrice = 20000.0;
				maxPrice = null;
			} 
		}
		
		String tipic = pdDTO.getTipVozila().toString();
		
		
		String europeanDatePattern = "yyyy-MM-dd";
		DateTimeFormatter europeanDateFormatter = DateTimeFormatter.ofPattern(europeanDatePattern);
		LocalDate d1 = LocalDate.parse(pdDTO.getStartDate(), europeanDateFormatter);
		LocalDate d2 = LocalDate.parse(pdDTO.getEndDate(), europeanDateFormatter);
		
		System.out.println("Datumi: " + d1 + "  " + d2);
		int brojDana =  (int) d1.until(d2, ChronoUnit.DAYS);
		System.out.println("Broj dana: " + brojDana);
		
		//Provera datuma
		if(d1.isAfter(d2)) { //da li je pocetni datum posle krajnjeg datuma
			return null;
		}
		
		ArrayList<Vozilo> vozilaRent = new ArrayList<Vozilo>();
		ArrayList<Vozilo> svaVozila = (ArrayList<Vozilo>) voziloRepository.findAll();
		
		RentACar rent = rentRepository.getOne(idRent);
				
		if(rent == null) 
		{
			return null;
		} else {
			for(Vozilo v: svaVozila) {
				if(v.getRentACar().getRentACarId() == idRent) {
					vozilaRent.add(v);
				}
			}
		}
		ArrayList<Vozilo> vozilaRentTip = new ArrayList<Vozilo>();
		
		for (Vozilo v: vozilaRent)
		{
			if (v.getTip().toString().equals(pdDTO.getTipVozila()))
			{
				vozilaRentTip.add(v);
			}
		}
		
		// sada radimo sa vozilima koja su iz tog rent-a-car servisa
		
		List<RezervacijaVozila> rezervacije = rezVoziloRepository.findAll();
		List<CenovnikRentACar> cenovnici = cenRentRepository.findAll();
		List<StavkaCenovnikaRent> stavkeCenovnika = stavkaRentRepository.findAll();
		
		for(Vozilo v: vozilaRentTip) 
		{
			boolean slobodno = true;
			for(RezervacijaVozila rezervacija : rezervacije) 
			{
				if (rezervacija.getVozilo().getVoziloId() == v.getVoziloId()) // ukoliko se to vozilo nalazi medju rezervacijama
				{
					// provera slobodno TRUE - FALSE
					if(d1.isBefore(rezervacija.getDatumPreuzimanja()) || d1.equals(rezervacija.getDatumPreuzimanja())) {
						if(d2.isAfter(rezervacija.getDatumPreuzimanja()) || d2.equals(rezervacija.getDatumPreuzimanja())) {
							slobodno = false;
						}
					} else if(d1.isAfter(rezervacija.getDatumPreuzimanja()) || d1.equals(rezervacija.getDatumVracanja())) {
						if(d2.isBefore(rezervacija.getDatumVracanja()) || d2.equals(rezervacija.getDatumVracanja())) {
							slobodno = false;
						}
					}
				}
					
			}
			if(slobodno == true) // utvrdjeno je da je vozilo slobodno
			{
				// utvrdjivanje vazeceg cenovnika
				for(CenovnikRentACar cenovnik : cenovnici) 
					if(d1.isAfter(cenovnik.getPocetakVazenja()) || d1.isEqual(cenovnik.getPocetakVazenja())) 
						if(d2.isBefore(cenovnik.getPrestanakVazenja()) || d2.isEqual(cenovnik.getPrestanakVazenja())) 
							if(cenovnik.getRentACar().getRentACarId() == idRent)  //ako je cenovnik servisa u kojem je slobodno vozilo
								for(StavkaCenovnikaRent stavkaCenovnika : stavkeCenovnika) 
									if(stavkaCenovnika.getCenovnik().getId() == cenovnik.getId()) // ukoliko je stavka iz tog cenovnika
										if(stavkaCenovnika.getTipVozila() == v.getTip()) { // ukoliko je se stavka cenovnika odnosi na bas to vozilo
											if(pdDTO.getPriceRange() != "") {
												if(stavkaCenovnika.getCena() >= minPrice)
													if(maxPrice != null) { // ukoliko je korisnik definisao maksimalnu cenu
														if(stavkaCenovnika.getCena() <= maxPrice) {
															//dodajem privremenu cenu vozila na osnovu datuma zeljene rezervacije i cene po danu
															v.setCena(stavkaCenovnika.getCena() * brojDana);
															slobodnaVozila.add(v);
															System.out.println("Cena je: " + v.getCena());		

														}
													} else { // ukoliko je korisnik izabrao opciju 20 000+
														v.setCena(stavkaCenovnika.getCena() * brojDana);
														slobodnaVozila.add(v);
														System.out.println("Cena je: " + v.getCena());		

													}
											} else { // ukoliko korisnik uopste nije definisao cenovni rang
												v.setCena(stavkaCenovnika.getCena() * brojDana);
												slobodnaVozila.add(v);
												System.out.println("Cena je: " + v.getCena());		

											}
														
															
										}
			}
		}
		
		//Lista 'slobodnaVozila' sadrzi vozila koje su odgovarajuce po vremenskom periodu i po cenovnom rangu
		
		// Sortiranje slobodnih vozila po kapacitetu od najmanjeg do najveceg
		int n = slobodnaVozila.size();
		
        for (int i = 0; i < n-1; i++) 
            for (int j = 0; j < n-i-1; j++) 
                if (slobodnaVozila.get(j).getBrojSedista() > slobodnaVozila.get(j+1).getBrojSedista()) 
                { 
                    Vozilo temp = slobodnaVozila.get(j);
                    slobodnaVozila.set(j, slobodnaVozila.get(j+1));
                    slobodnaVozila.set(j+1, temp);
    
                } 
        
		
		int brojGostiju = Integer.parseInt(pdDTO.getNumberOfGuests());
		int brojVozila = 1;
		
		int tempBrojGostiju = brojGostiju;
		
		ArrayList<Vozilo> odgovarajucaVozila = new ArrayList<Vozilo>();
		if(brojVozila == 1) { //ako se trazi jedno vozilo, neka lista sadrzi samo vozila sa tacnim kapacitetom ili sa vecim kapacitetom
			for(Vozilo voz: slobodnaVozila) 
			{
				if(voz.getBrojSedista() >= brojGostiju) 
				{
					odgovarajucaVozila.add(voz);
				}
					
			}
		} else 
		{
			System.out.println("Ne bi trebao ovde da udje, jer se uvek trazi jedno vozilo!");
		}
		
		return odgovarajucaVozila;
	}
	
	
	// BRZA REZERVACIJA
	
	public Vozilo staviVoziloNaPopust(Long idVozila) 
	{
		Vozilo v = voziloRepository.getOne(idVozila);
		v.setNaPopustu(true);
		voziloRepository.save(v);
		return v;
	}
	
	public ArrayList<Vozilo> vratiVozilaNaPopustu(Long idRezervacijeLeta, Long idRent) 
	{
		ArrayList<Vozilo> odgovarajucaVozila = new ArrayList<Vozilo>();
		
		//Sve potrebne informacije za proveru dostupnosti vozila treba izvuci iz podataka o rezervaciji leta 
		//Privremeno podaci ce se izvlaciti na osnovu rezervacije vozila
		//TO DO
		
		RezervacijaVozila rezervacijaLeta = rezVoziloRepository.getOne(idRezervacijeLeta); //ZAMENITI ZA REZERVACIJU LETA kada bude odradjeno!
		LocalDate d1 = rezervacijaLeta.getDatumPreuzimanja();
		LocalDate d2 = rezervacijaLeta.getDatumVracanja();
		int brojGostiju = rezervacijaLeta.getBrojPutnika();
		int brojDana =  (int) d1.until(d2, ChronoUnit.DAYS);
		
		System.out.println("Broj dana je: " + brojDana);
		
		ArrayList<Vozilo> vozilaRentNaPopustu = new ArrayList<Vozilo>();
		//PROVERA 1 -> Da li je vozilo u zeljenom servisu
		//PROVERA 2 -> Da li je u pitanju vozilo na popustu
		
		ArrayList<Vozilo> svaVozila = (ArrayList<Vozilo>) voziloRepository.findAll();
		RentACar rent = rentRepository.getOne(idRent);
		
		if(rent == null) {
			return null;
		} else {
			for(Vozilo v : svaVozila) {
				if(v.getRentACar().getRentACarId() == idRent) {
					if(v.isNaPopustu() == true)
						vozilaRentNaPopustu.add(v);
				}
			}
		}
		
		List<RezervacijaVozila> rezervacije = rezVoziloRepository.findAll();
		List<CenovnikRentACar> cenovnici = cenRentRepository.findAll();
		List<StavkaCenovnikaRent> stavkeCenovnika = stavkaRentRepository.findAll();
		
		// PROVERA 3 -> Da li je vozilo slobodno u odgovarajucem periodu
		for(Vozilo v: vozilaRentNaPopustu) {
			boolean slobodno = true;
			for(RezervacijaVozila rezervacija : rezervacije) {
					if(rezervacija.getVozilo().getVoziloId() == v.getVoziloId()) { //Da li se vozilo nalazi medju rezervacijama
						if(d1.isBefore(rezervacija.getDatumPreuzimanja())) {
							if(d2.isAfter(rezervacija.getDatumVracanja())) {
								slobodno = false;
							}
						} else if(d1.isAfter(rezervacija.getDatumPreuzimanja())) {
							if(d2.isBefore(rezervacija.getDatumVracanja())) {
								slobodno = false;
							}
						} else if(d1.isEqual(rezervacija.getDatumPreuzimanja())) {
							if(d2.isEqual(rezervacija.getDatumVracanja())) {
								slobodno = false;
							}
						}
					}
				
			}
			// PROVERA 4 -> Da li je vozilo ima definisanu cenu (da li je aktivno u trazenom periodu)
			if(slobodno == true) {
				for(CenovnikRentACar cenovnik : cenovnici) 
					if(d1.isAfter(cenovnik.getPocetakVazenja()) || d1.isEqual(cenovnik.getPocetakVazenja())) 
						if(d2.isBefore(cenovnik.getPrestanakVazenja()) || d2.isEqual(cenovnik.getPrestanakVazenja())) 
							if(cenovnik.getRentACar().getRentACarId() == idRent)  //ako je cenovnik rent u kom je slobodno vozilo
								for(StavkaCenovnikaRent stavkaCenovnika : stavkeCenovnika) 
									if(stavkaCenovnika.getCenovnik().getId() == cenovnik.getId()) 
										if(stavkaCenovnika.getTipVozila() == v.getTip()) {
											v.setCena(stavkaCenovnika.getCena() * brojDana);
													odgovarajucaVozila.add(v);
										}
			}
		}
		
		return odgovarajucaVozila;
	}


}
