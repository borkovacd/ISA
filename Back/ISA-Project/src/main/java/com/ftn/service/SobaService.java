package com.ftn.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.dto.ProveriDostupnostDTO;
import com.ftn.dto.SobaDTO;
import com.ftn.dto.VremenskiPeriodDTO;
import com.ftn.enums.TipSobe;
import com.ftn.model.hotels.CenovnikHotela;
import com.ftn.model.hotels.Hotel;
import com.ftn.model.hotels.RezervacijaHotela;
import com.ftn.model.hotels.Soba;
import com.ftn.model.hotels.StavkaCenovnikaHotela;
import com.ftn.repository.CenovnikHotelaRepository;
import com.ftn.repository.HotelRepository;
import com.ftn.repository.RezervacijaHotelaRepository;
import com.ftn.repository.SobaRepository;
import com.ftn.repository.StavkaCenovnikaHotelaRepository;

@Service
public class SobaService {
	
	@Autowired
	private SobaRepository sobaRepository;
	@Autowired
	private HotelRepository hotelRepository;
	@Autowired
	private RezervacijaHotelaRepository rezervacijaHotelaRepository;
	@Autowired
	private CenovnikHotelaRepository cenovnikHotelaRepository;
	@Autowired
	private StavkaCenovnikaHotelaRepository stavkaCenovnikaHotelaRepository;

	public Soba createRoom(SobaDTO sobaDTO, Long idHotela) {
		Soba soba = new Soba();
		soba.setKapacitet(sobaDTO.getCapacity());
		soba.setSprat(sobaDTO.getFloor());
		if(sobaDTO.getRoomType().equals("JEDNOKREVETNA_SOBA")) 
			soba.setTipSobe(TipSobe.JEDNOKREVETNA_SOBA);
		else if(sobaDTO.getRoomType().equals("DVOKREVETNA_SOBA")) 
			soba.setTipSobe(TipSobe.DVOKREVETNA_SOBA);
		else if(sobaDTO.getRoomType().equals("TROKREVETNA_SOBA")) 
			soba.setTipSobe(TipSobe.TROKREVETNA_SOBA);
		else if(sobaDTO.getRoomType().equals("APARTMAN")) 
			soba.setTipSobe(TipSobe.APARTMAN);
		else if(sobaDTO.getRoomType().equals("STUDIO")) 
			soba.setTipSobe(TipSobe.STUDIO);
		else if(sobaDTO.getRoomType().equals("SUITE")) 
			soba.setTipSobe(TipSobe.SUITE);
		else if(sobaDTO.getRoomType().equals("FAMILY_ROOM")) 
			soba.setTipSobe(TipSobe.FAMILY_ROOM);
		soba.setImaBalkon(sobaDTO.isHasBalcony());
		soba.setOcena(0);
		Hotel hotel = hotelRepository.getOne(idHotela);
		soba.setHotel(hotel);
		sobaRepository.save(soba);
		return soba;
	}

	public ArrayList<Soba> getAllRooms(Long idHotela) {
		ArrayList<Soba> sobe = new ArrayList<Soba>();
		ArrayList<Soba> sveSobe = (ArrayList<Soba>) sobaRepository.findAll();
		Hotel hotel = hotelRepository.getOne(idHotela);
		if(hotel == null) {
			return sobe;
		} else {
			for(Soba soba : sveSobe) {
				if(soba.getHotel().getId() == idHotela) {
					sobe.add(soba);
				}
			}
		}
		return sobe;
	}

	//Provera da li je soba bila rezervisana ili je trenutno rezervisana
	//tacnije da li se soba nalazi u rezervacijama
	public boolean checkIfRoomIsReserved(Long id) {
		boolean taken = false;
		List<RezervacijaHotela> rezervacije = rezervacijaHotelaRepository.findAll();
		for(RezervacijaHotela rezervacija : rezervacije) {
			for(Soba rezervisanaSoba: rezervacija.getSobe()) { //izmena u odnosu na xml, moze se rezervisati vise soba
				if(rezervisanaSoba.getId() == id) {
					taken = true;
				}
			}
		}
		return taken;
	}

	public Soba getRoom(Long idRoom) {
		Soba soba = sobaRepository.getOne(idRoom);
		return soba;
	}

	@Transactional
	synchronized public Soba editRoom(Long idRoom, SobaDTO sobaDTO) {
		Soba soba = sobaRepository.getOne(idRoom);
		soba.setKapacitet(sobaDTO.getCapacity());
		soba.setSprat(sobaDTO.getFloor());
		if(sobaDTO.getRoomType().equals("JEDNOKREVETNA_SOBA")) 
			soba.setTipSobe(TipSobe.JEDNOKREVETNA_SOBA);
		else if(sobaDTO.getRoomType().equals("DVOKREVETNA_SOBA")) 
			soba.setTipSobe(TipSobe.DVOKREVETNA_SOBA);
		else if(sobaDTO.getRoomType().equals("TROKREVETNA_SOBA")) 
			soba.setTipSobe(TipSobe.TROKREVETNA_SOBA);
		else if(sobaDTO.getRoomType().equals("APARTMAN")) 
			soba.setTipSobe(TipSobe.APARTMAN);
		else if(sobaDTO.getRoomType().equals("STUDIO")) 
			soba.setTipSobe(TipSobe.STUDIO);
		else if(sobaDTO.getRoomType().equals("SUITE")) 
			soba.setTipSobe(TipSobe.SUITE);
		else if(sobaDTO.getRoomType().equals("FAMILY_ROOM")) 
			soba.setTipSobe(TipSobe.FAMILY_ROOM);
		soba.setImaBalkon(sobaDTO.isHasBalcony());
		soba.setOcena(0);
		sobaRepository.save(soba);
		return soba;
	}
	
	public Soba staviNaPopust(Long idRoom) {
		Soba soba = sobaRepository.getOne(idRoom);
		soba.setNaPopustu(true);
		sobaRepository.save(soba);
		return soba;
	}

	@Transactional
	synchronized public boolean deleteRoom(Long idRoom) {
		sobaRepository.deleteById(idRoom);
		return true;
	}

	public ArrayList<Soba> getAvailableRooms(VremenskiPeriodDTO vpDTO, Long idHotela) {
		
		ArrayList<Soba> slobodneSobe = new ArrayList<Soba>();
		
		String europeanDatePattern = "yyyy-MM-dd";
		DateTimeFormatter europeanDateFormatter = DateTimeFormatter.ofPattern(europeanDatePattern);
		LocalDate d1 = LocalDate.parse(vpDTO.getStartDate(), europeanDateFormatter);
		LocalDate d2 = LocalDate.parse(vpDTO.getEndDate(), europeanDateFormatter);
		
		//Provera datuma
		if(d1.isAfter(d2)) { //da li je pocetni datum posle krajnjeg datuma
			return null;
		}
		
		ArrayList<Soba> sobeHotela = new ArrayList<Soba>();
		ArrayList<Soba> sveSobe = (ArrayList<Soba>) sobaRepository.findAll();
		Hotel hotel = hotelRepository.getOne(idHotela);
		if(hotel == null) {
			return null;
		} else {
			for(Soba soba : sveSobe) {
				if(soba.getHotel().getId() == idHotela) {
					if(soba.isNaPopustu() == false) {
						sobeHotela.add(soba);
					}
				}
			}
		}
		
		List<RezervacijaHotela> rezervacije = rezervacijaHotelaRepository.findAll();
		List<CenovnikHotela> cenovnici = cenovnikHotelaRepository.findAll();
		List<StavkaCenovnikaHotela> stavkeCenovnika = stavkaCenovnikaHotelaRepository.findAll();
		
		for(Soba soba: sobeHotela) {
			boolean slobodna = true;
			for(RezervacijaHotela rezervacija : rezervacije) {
				for(Soba rezervisanaSoba: rezervacija.getSobe()) { 
					if(rezervisanaSoba.getId() == soba.getId()) { //Da li se soba nalazi medju rezervacijama
						if(d1.isBefore(rezervacija.getDatumPocetka())) {
							if(d2.isAfter(rezervacija.getDatumPocetka())) {
								slobodna = false;
							}
						} else if(d1.isAfter(rezervacija.getDatumPocetka())) {
							if(d1.isBefore(rezervacija.getDatumKraja())) {
								slobodna = false;
							}
						} else if(d1.isEqual(rezervacija.getDatumPocetka())) {
							if(d2.isEqual(rezervacija.getDatumKraja())) {
								slobodna = false;
							}
						}
					}
				}
			}
			if(slobodna == true) {
				for(CenovnikHotela cenovnik : cenovnici) 
					if(d1.isAfter(cenovnik.getPocetakVazenja()) || d1.isEqual(cenovnik.getPocetakVazenja())) 
						if(d2.isBefore(cenovnik.getPrestanakVazenja()) || d2.isEqual(cenovnik.getPrestanakVazenja())) 
							if(cenovnik.getHotel().getId() == idHotela)  //ako je cenovnik hotela u kojem je slobodna soba
								for(StavkaCenovnikaHotela stavkaCenovnika : stavkeCenovnika) 
									if(stavkaCenovnika.getCenovnik().getId() == cenovnik.getId()) 
										if(stavkaCenovnika.getTipSobe() == soba.getTipSobe()) {
											slobodneSobe.add(soba);
											
										
										}
			}
		}
		
		return slobodneSobe;
	}

	public ArrayList<Soba> checkAvailability(ProveriDostupnostDTO pdDTO, Long idHotela) {
		ArrayList<Soba> slobodneSobe = new ArrayList<Soba>();
		
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
		
		
		String europeanDatePattern = "yyyy-MM-dd";
		DateTimeFormatter europeanDateFormatter = DateTimeFormatter.ofPattern(europeanDatePattern);
		LocalDate d1 = LocalDate.parse(pdDTO.getStartDate(), europeanDateFormatter);
		LocalDate d2 = LocalDate.parse(pdDTO.getEndDate(), europeanDateFormatter);
		
		System.out.println("Datumi: " + d1 + "  " + d2);
		int brojNocenja =  (int) d1.until(d2, ChronoUnit.DAYS);
		System.out.println("Broj nocenja: " + brojNocenja);
		
		//Provera datuma
		if(d1.isAfter(d2)) { //da li je pocetni datum posle krajnjeg datuma
			return null;
		}
		
		ArrayList<Soba> sobeHotela = new ArrayList<Soba>();
		ArrayList<Soba> sveSobe = (ArrayList<Soba>) sobaRepository.findAll();
		Hotel hotel = hotelRepository.getOne(idHotela);
		if(hotel == null) {
			return null;
		} else {
			for(Soba soba : sveSobe) {
				if(soba.getHotel().getId() == idHotela) {
					if(soba.isNaPopustu() == false) {
						sobeHotela.add(soba);
					}
				}
			}
		}
		
		List<RezervacijaHotela> rezervacije = rezervacijaHotelaRepository.findAll();
		List<CenovnikHotela> cenovnici = cenovnikHotelaRepository.findAll();
		List<StavkaCenovnikaHotela> stavkeCenovnika = stavkaCenovnikaHotelaRepository.findAll();
		
		for(Soba soba: sobeHotela) {
			boolean slobodna = true;
			for(RezervacijaHotela rezervacija : rezervacije) {
				for(Soba rezervisanaSoba: rezervacija.getSobe()) { 
					if(rezervisanaSoba.getId() == soba.getId()) { //Da li se soba nalazi medju rezervacijama
						if(d1.isBefore(rezervacija.getDatumPocetka())) {
							if(d2.isAfter(rezervacija.getDatumPocetka())) {
								slobodna = false;
							}
						} else if(d1.isAfter(rezervacija.getDatumPocetka())) {
							if(d1.isBefore(rezervacija.getDatumKraja())) {
								slobodna = false;
							}
						} else if(d1.isEqual(rezervacija.getDatumPocetka())) {
							if(d2.isEqual(rezervacija.getDatumKraja())) {
								slobodna = false;
							}
						}
					}
				}
			}
			if(slobodna == true) {
				for(CenovnikHotela cenovnik : cenovnici) 
					if(d1.isAfter(cenovnik.getPocetakVazenja()) || d1.isEqual(cenovnik.getPocetakVazenja())) 
						if(d2.isBefore(cenovnik.getPrestanakVazenja()) || d2.isEqual(cenovnik.getPrestanakVazenja())) 
							if(cenovnik.getHotel().getId() == idHotela)  //ako je cenovnik hotela u kojem je slobodna soba
								for(StavkaCenovnikaHotela stavkaCenovnika : stavkeCenovnika) 
									if(stavkaCenovnika.getCenovnik().getId() == cenovnik.getId()) 
										if(stavkaCenovnika.getTipSobe() == soba.getTipSobe()) {
											if(pdDTO.getPriceRange() != "") {
												if(stavkaCenovnika.getCena() >= minPrice)
													if(maxPrice != null) {
														if(stavkaCenovnika.getCena() <= maxPrice) {
															//dodajem privremenu cenu soba na osnovu datuma zeljene rezervacije i cene po nocenju
															soba.setCena(stavkaCenovnika.getCena() * brojNocenja);
															slobodneSobe.add(soba);
														}
													} else {
														soba.setCena(stavkaCenovnika.getCena() * brojNocenja);
														slobodneSobe.add(soba);
													}
											} else {
												soba.setCena(stavkaCenovnika.getCena() * brojNocenja);
												slobodneSobe.add(soba);
											}
														
															
										}
			}
		}
		
		//Lista 'slobodneSobe' sadrzi sobe koje su odgovarajuce po vremenskom periodu i po cenovnom rangu
		
		/*System.out.println("ISPIS LISTE PRE SORTIRANJA");
		for(Soba slobodnaSoba: slobodneSobe) {
			System.out.println("Soba " + slobodnaSoba.getTipSobe() + " " + slobodnaSoba.getKapacitet());
		}*/
		
		//Sortiranje slobodnih soba po kapacitetu od najmanje do najvece
		int n = slobodneSobe.size();
        for (int i = 0; i < n-1; i++) 
            for (int j = 0; j < n-i-1; j++) 
                if (slobodneSobe.get(j).getKapacitet() > slobodneSobe.get(j+1).getKapacitet()) 
                { 
                    Soba temp = slobodneSobe.get(j);
                    slobodneSobe.set(j, slobodneSobe.get(j+1));
                    slobodneSobe.set(j+1, temp);
    
                } 
        
        
    
        /*System.out.println("ISPIS LISTE POSLE SORTIRANJA");
		for(Soba slobodnaSoba: slobodneSobe) {
			System.out.println("Soba " + slobodnaSoba.getTipSobe() + " " + slobodnaSoba.getKapacitet());
		}*/
        
        
       
        
		
		int brojGostiju = Integer.parseInt(pdDTO.getNumberOfGuests());
		int brojSoba = Integer.parseInt(pdDTO.getNumberOfRooms());
		int tempBrojGostiju = brojGostiju;
		int tempBrojSoba = brojSoba;
		
		int brojSobaFinal = brojSoba;
	
		//System.out.println("Gositju: " + brojGostiju + " Jedinica: " + brojSoba);
		//int kapacitet = 0;
		//int broj = 0;
		ArrayList<Soba> odgovarajuceSobe = new ArrayList<Soba>();
		if(brojSoba == 1) { //ako se trazi jedna soba, neka lista sadrzi samo sobe sa tacnim kapacitetom ili sa vecim kapacitetom
			for(Soba soba: slobodneSobe) {
				if(soba.getKapacitet() == brojGostiju) {
					odgovarajuceSobe.add(soba);
				}
					
			}
		} else {
			for(Soba soba: slobodneSobe) {
				odgovarajuceSobe.add(soba);
				brojGostiju -= soba.getKapacitet();
				brojSoba -= 1;
				if(brojSoba == 0) {
					if(brojGostiju <= 0) {
						return odgovarajuceSobe;
					} else {
						odgovarajuceSobe = new ArrayList<Soba>();
						ArrayList<Soba> obrnutoSlobodneSobe = slobodneSobe;
				        int m = obrnutoSlobodneSobe.size();
				        for (int i = 0; i < m-1; i++) 
				            for (int j = 0; j < m-i-1; j++) 
				                if (obrnutoSlobodneSobe.get(j).getKapacitet() < obrnutoSlobodneSobe.get(j+1).getKapacitet()) 
				                { 
				                    Soba temp = obrnutoSlobodneSobe.get(j);
				                    obrnutoSlobodneSobe.set(j, obrnutoSlobodneSobe.get(j+1));
				                    obrnutoSlobodneSobe.set(j+1, temp);
				    
				                }
				        
				        /*System.out.println("ISPIS LISTE POSLE OBRNTODSSDDS SORTIRANJA");
						for(Soba slobodnaSoba: obrnutoSlobodneSobe) {
							System.out.println("Soba " + slobodnaSoba.getTipSobe() + " " + slobodnaSoba.getKapacitet());
						}*/
				        
				        
						for(Soba soba2: obrnutoSlobodneSobe) {
							odgovarajuceSobe.add(soba2);
							tempBrojGostiju -= soba2.getKapacitet();
							tempBrojSoba -= 1;
							if(tempBrojSoba == 0) {
								if(tempBrojGostiju <= 0) {
									//System.out.println("I OVDE");
									return odgovarajuceSobe;
								} else
									return null;
							}
						}
					}
				} 
			}
			
		}
		
		if(odgovarajuceSobe.size() < brojSobaFinal) {
			return null;
		}
		
		return odgovarajuceSobe;
	}

	public ArrayList<Soba> getRoomsAtDiscount(Long idRezervacijeLeta, Long idHotela) {
		ArrayList<Soba> odgovarajuceSobe = new ArrayList<Soba>();
		
		//Sve potrebne informacije za proveru dostupnosti soba treba izvuci iz podataka o rezervaciji leta 
		//Privremeno podaci ce se izvlaciti na osnovu rezervacije hotela
		//TO DO
		
		RezervacijaHotela rezervacijaLeta = rezervacijaHotelaRepository.getOne(idRezervacijeLeta); //ZAMENITI ZA REZERVACIJU LETA kada bude odradjeno!
		LocalDate d1 = rezervacijaLeta.getDatumPocetka();
		LocalDate d2 = rezervacijaLeta.getDatumKraja();
		int brojGostiju = rezervacijaLeta.getBrojGostiju();
		int brojNocenja =  (int) d1.until(d2, ChronoUnit.DAYS);
		
		ArrayList<Soba> sobeHotelaNaPopustu = new ArrayList<Soba>();
		//PROVERA 1 -> Da li je soba u zeljenom hotelu
		//PROVERA 2 -> Da li je u pitanju soba na popustu
		ArrayList<Soba> sveSobe = (ArrayList<Soba>) sobaRepository.findAll();
		Hotel hotel = hotelRepository.getOne(idHotela);
		if(hotel == null) {
			return null;
		} else {
			for(Soba soba : sveSobe) {
				if(soba.getHotel().getId() == idHotela) {
					if(soba.isNaPopustu() == true)
						sobeHotelaNaPopustu.add(soba);
				}
			}
		}
		
		List<RezervacijaHotela> rezervacije = rezervacijaHotelaRepository.findAll();
		List<CenovnikHotela> cenovnici = cenovnikHotelaRepository.findAll();
		List<StavkaCenovnikaHotela> stavkeCenovnika = stavkaCenovnikaHotelaRepository.findAll();
		
		// PROVERA 3 -> Da li je soba slobodna u odgovarajucem periodu
		for(Soba soba: sobeHotelaNaPopustu) {
			boolean slobodna = true;
			for(RezervacijaHotela rezervacija : rezervacije) {
				for(Soba rezervisanaSoba: rezervacija.getSobe()) { 
					if(rezervisanaSoba.getId() == soba.getId()) { //Da li se soba nalazi medju rezervacijama
						if(d1.isBefore(rezervacija.getDatumPocetka())) {
							if(d2.isAfter(rezervacija.getDatumPocetka())) {
								slobodna = false;
							}
						} else if(d1.isAfter(rezervacija.getDatumPocetka())) {
							if(d2.isBefore(rezervacija.getDatumKraja())) {
								slobodna = false;
							}
						} else if(d1.isEqual(rezervacija.getDatumPocetka())) {
							if(d2.isEqual(rezervacija.getDatumKraja())) {
								slobodna = false;
							}
						}
					}
				}
			}
			// PROVERA 4 -> Da li je soba ima definisanu cenu (da li je aktivna u trazenom periodu)
			if(slobodna == true) {
				for(CenovnikHotela cenovnik : cenovnici) 
					if(d1.isAfter(cenovnik.getPocetakVazenja()) || d1.isEqual(cenovnik.getPocetakVazenja())) 
						if(d2.isBefore(cenovnik.getPrestanakVazenja()) || d2.isEqual(cenovnik.getPrestanakVazenja())) 
							if(cenovnik.getHotel().getId() == idHotela)  //ako je cenovnik hotela u kojem je slobodna soba
								for(StavkaCenovnikaHotela stavkaCenovnika : stavkeCenovnika) 
									if(stavkaCenovnika.getCenovnik().getId() == cenovnik.getId()) 
										if(stavkaCenovnika.getTipSobe() == soba.getTipSobe()) {
											soba.setCena(stavkaCenovnika.getCena() * brojNocenja);
													odgovarajuceSobe.add(soba);
										}
			}
		}
		
		return odgovarajuceSobe;
	}
}
