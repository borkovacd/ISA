package com.ftn.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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

	public Soba editRoom(Long idRoom, SobaDTO sobaDTO) {
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
		soba.setRezervisana(false);
		sobaRepository.save(soba);
		return soba;
	}

	public boolean deleteRoom(Long idRoom) {
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
					sobeHotela.add(soba);
				}
			}
		}
		
		List<RezervacijaHotela> rezervacije = rezervacijaHotelaRepository.findAll();
		
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
							if(d2.isBefore(rezervacija.getDatumKraja())) {
								slobodna = false;
							}
						}
					}
				}
			}
			if(slobodna == true) {
				slobodneSobe.add(soba);
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
					sobeHotela.add(soba);
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
							if(d2.isBefore(rezervacija.getDatumKraja())) {
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
															slobodneSobe.add(soba);
														}
													} else {
														slobodneSobe.add(soba);
													}
											} else {
												slobodneSobe.add(soba);
											}
														
															
										}
			}
		}
		
		for(Soba slobodnaSoba: slobodneSobe) {
			System.out.println("Soba " + slobodnaSoba.getTipSobe() + " " + slobodnaSoba.getKapacitet());
		}
		
		//Lista 'slobodneSobe' sadrzi sobe koje su odgovarajuce po vremenskom periodu i po cenovnom rangu
		
		int brojGostiju = Integer.parseInt(pdDTO.getNumberOfGuests());
		int brojSoba = Integer.parseInt(pdDTO.getNumberOfRooms());
		System.out.println("Gositju: " + brojGostiju + " Jedinica: " + brojSoba);
		int kapacitet = 0;
		int broj = 0;
		ArrayList<Soba> odgovarajuceSobe = new ArrayList<Soba>();
		if(brojSoba == 1) { //ako se trazi jedna soba, neka lista sadrzi samo sobe sa tacnim kapacitetom ili sa vecim kapacitetom
			for(Soba soba: slobodneSobe) {
				if(soba.getKapacitet() >= brojGostiju) {
					System.out.println("STO NISAM OVDE?");
					odgovarajuceSobe.add(soba);
				}
					
			}
		} else {
			System.out.println("OCIGLEDNO SI OVDE");
			for(Soba soba: slobodneSobe) {
				kapacitet += soba.getKapacitet();
				broj++;
			}
			if(kapacitet >= brojGostiju && broj <= brojSoba) {
				return slobodneSobe;
			}
		}
		
		return odgovarajuceSobe;
	}

}
