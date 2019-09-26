package com.ftn.rent.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.ftn.dto.OcenaRentDTO;
import com.ftn.dto.OcenaVoziloDTO;
import com.ftn.dto.RezervacijaVozilaDTO;
import com.ftn.dto.VoziloDTO;
import com.ftn.enums.TipVozila;
import com.ftn.model.Korisnik;
import com.ftn.model.rentacar.CenovnikRentACar;
import com.ftn.model.rentacar.Lokacija;
import com.ftn.model.rentacar.OcenaRentACar;
import com.ftn.model.rentacar.OcenaVozilo;
import com.ftn.model.rentacar.RentACar;
import com.ftn.model.rentacar.RezervacijaVozila;
import com.ftn.model.rentacar.StavkaCenovnikaRent;
import com.ftn.model.rentacar.Vozilo;
import com.ftn.repository.CenovnikRentACarRepository;
import com.ftn.repository.LokacijaRepository;
import com.ftn.repository.OcenaRentRepository;
import com.ftn.repository.OcenaVoziloRepository;
import com.ftn.repository.RentCarRepository;
import com.ftn.repository.RezervacijaVozilaRepository;
import com.ftn.repository.StavkaCenovnikaRentRepository;
import com.ftn.repository.UserRepository;
import com.ftn.repository.VoziloRepository;
import com.ftn.service.OcenaRentService;
import com.ftn.service.OcenaVoziloService;
import com.ftn.service.RezervacijaVozilaService;
import com.ftn.service.VoziloService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RezervacijaVozilaServiceTest {

	@Mock
	private RezervacijaVozila rezervacijaMock ;
	
	@Mock
	private RezervacijaVozilaRepository repozitorijumMock ;
	
	@Mock
	private VoziloRepository rentRepositoryMock ;
	
	@Mock
	private UserRepository userRepository ;
	
	@Mock
	private VoziloRepository voziloRepository ;
	
	@Mock
	private LokacijaRepository lokacijaRepository ;
	
	@Mock
	private CenovnikRentACarRepository cenovnikRepository ;
	
	@Mock
	private StavkaCenovnikaRentRepository stavkaRepository ;
	
	@InjectMocks
	private RezervacijaVozilaService rezervacijaService ;
	
	// vraca sva vozila iz rent-a-car-a
	@Test
	public void testListaRezervacijaKorisnik() throws Exception {
		
		Vozilo vozilo = new Vozilo();
		vozilo.setVoziloId((long)1);
		
		Korisnik korisnik = new Korisnik();
		korisnik.setId((long)1);
		
		when(repozitorijumMock.findAll()).thenReturn(Arrays.asList(new RezervacijaVozila(vozilo, korisnik, 200.0, 4, 0)));
		when(repozitorijumMock.findByKorisnikId((long)1)).thenReturn(Arrays.asList(new RezervacijaVozila(vozilo, korisnik, 200.0, 4, 0)));
		
		List<RezervacijaVozila> rezervacije = rezervacijaService.listaRezervacijaKorisnikTest((long)1);
		assertEquals(rezervacije.size(), 1);
		
		verify(repozitorijumMock, times(1)).findByKorisnikId((long)1);
		verifyNoMoreInteractions(repozitorijumMock);
	}
	
	@Test
	@Transactional
    @Rollback(true)
	public void testCreateReservationRent() throws Exception {
		RezervacijaVozilaDTO r = new RezervacijaVozilaDTO();
		RentACar rent = new RentACar();
		rent.setRentACarId((long)1);
		
		Vozilo vozilo = new Vozilo();
		vozilo.setVoziloId((long)1);
		vozilo.setNaziv("1");
		vozilo.setRentACar(rent);
		vozilo.setTip(TipVozila.LIMUZINA);
		vozilo.setNaPopustu(false);
		
		Lokacija lokacija = new Lokacija();
		lokacija.setAdresa("Adresa");
		
		r.setStartDate("2019-01-01");
		r.setEndDate("2019-12-12");
		r.setMestoPreuzimanja("Novi Sad");
		r.setMestoVracanja("Novi Sad");
		r.setNumberOfGuests("4");
		r.setVozilo("1");

		Korisnik user = new Korisnik();
		user.setId((long)1);
		
		RezervacijaVozila rezervacija = new RezervacijaVozila();
		rezervacija.setBrojPutnika(4);
		rezervacija.setCena(200);
		rezervacija.setId((long)1);
		
		String europeanDatePattern = "yyyy-MM-dd";
		DateTimeFormatter europeanDateFormatter = DateTimeFormatter.ofPattern(europeanDatePattern);
		LocalDate d1 = LocalDate.parse("2020-10-10");
		LocalDate d2 = LocalDate.parse("2020-12-12");

		CenovnikRentACar cenovnik = new CenovnikRentACar();
		cenovnik.setPocetakVazenja(d1);
		cenovnik.setPrestanakVazenja(d2);
		cenovnik.setRentACar(rent);
		cenovnik.setId((long)1);
		
		StavkaCenovnikaRent stavka = new StavkaCenovnikaRent();
		stavka.setCenovnik(cenovnik);
		stavka.setTipVozila(vozilo.getTip());
		

		when(userRepository.getOne((long)1)).thenReturn(user);
		when(voziloRepository.getOne((long)1)).thenReturn(vozilo);
		when(lokacijaRepository.findOneByAdresa("Adresa")).thenReturn(lokacija);
		when(cenovnikRepository.findAll()).thenReturn(Arrays.asList(cenovnik));
		when(stavkaRepository.findAll()).thenReturn(Arrays.asList(stavka));

		RezervacijaVozila rezervacijaV = rezervacijaService.createReservationRentTest(r, (long) 1);
		
		assertEquals(rezervacijaV.getBrojPutnika(), rezervacija.getBrojPutnika());
		verify(repozitorijumMock, times(1)).save(rezervacijaV);
	}
	
	
	
}
