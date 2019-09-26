package com.ftn.rent.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

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

import com.ftn.dto.LokacijaDTO;
import com.ftn.dto.VoziloDTO;
import com.ftn.model.rentacar.Lokacija;
import com.ftn.model.rentacar.RentACar;
import com.ftn.model.rentacar.Vozilo;
import com.ftn.repository.LokacijaRepository;
import com.ftn.repository.RentCarRepository;
import com.ftn.repository.VoziloRepository;
import com.ftn.service.LokacijaService;
import com.ftn.service.VoziloService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LokacijaServiceTest {
	
	@Mock
	private Lokacija lokacijaMock ;
	
	@Mock
	private LokacijaRepository repozitorijumMock ;
	
	@Mock
	private RentCarRepository rentRepository ;
	
	@InjectMocks
	private LokacijaService lokacijaService ;
	
	// metoda za dodavanje lokacije
		@Test
		@Transactional
	    @Rollback(true)
		public void testDodajFilijalu() {
			LokacijaDTO l = new LokacijaDTO();
			l.setAdresa("Adresa");
			l.setGrad("Grad");
			l.setDrzava("Drzava");
			l.setLatitude(19);
			l.setLongitude(27);
			
			RentACar rent = new RentACar();
			rent.setRentACarId((long) 1);
			
			Lokacija lokacija = new Lokacija();
			lokacija.setAdresa("Adresa2");
			lokacija.setGrad("Grad2");
			lokacija.setDrzava("Drzava2");
			lokacija.setLatitude(20);
			lokacija.setLongitude(28);
			lokacija.setRentACar(rent);
			

			when(repozitorijumMock.findAll()).thenReturn(Arrays.asList(lokacija));
			when(rentRepository.findOneByRentACarId((long)1)).thenReturn(rent);

			
			Lokacija lok = lokacijaService.dodajLokaciju(l, (long) 1);
			
			assertEquals(lok.getId(), lokacija.getId());
			verify(repozitorijumMock, times(1)).save(lok);
		}
		
		// metoda za izmenu lokacije
		@Test
		@Transactional
	    @Rollback(true)
		public void testIzmeniFilijalu() {
			LokacijaDTO l = new LokacijaDTO();
			l.setAdresa("Adresa");
			l.setGrad("Grad");
			l.setDrzava("Drzava");
			l.setLatitude(19);
			l.setLongitude(27);
			
			RentACar rent = new RentACar();
			rent.setRentACarId((long) 1);
			
			Lokacija lokacija = new Lokacija();
			lokacija.setAdresa("Adresa2");
			lokacija.setGrad("Grad2");
			lokacija.setDrzava("Drzava2");
			lokacija.setLatitude(20);
			lokacija.setLongitude(28);
			lokacija.setRentACar(rent);
			

			when(repozitorijumMock.findOneById((long)1)).thenReturn(lokacija);
			when(rentRepository.findOneByRentACarId((long)1)).thenReturn(rent);

			
			Lokacija lok = lokacijaService.izmeniFilijalu((long) 1, (long) 1, l);
			
			assertEquals(lok.getId(), lokacija.getId());
			verify(repozitorijumMock, times(1)).save(lok);
		}
		
		// vraca sve filijale
		@Test
		public void testGetAll() {
			
			RentACar rent = new RentACar();
			rent.setRentACarId((long) 1);

			when(repozitorijumMock.findAll()).thenReturn(Arrays.asList(new Lokacija("Adresa", rent)));
			List<Lokacija> lokacije = lokacijaService.getAll();
			assertEquals(lokacije.size(), 1);
			
			verify(repozitorijumMock, times(1)).findAll();
			verifyNoMoreInteractions(repozitorijumMock);
		}
		
		// vraca sva vozila iz rent-a-car-a
		@Test
		public void testGetFilijaleRentACar() throws Exception {
			RentACar a = new RentACar();
			a.setRentACarId((long)1);
			
			when(repozitorijumMock.findByRentACarRentACarId((long)1)).thenReturn(Arrays.asList(new Lokacija("Adresa", a)));
			
			List<Lokacija> lokacije = lokacijaService.getFilijaleRentACarTest((long)1);
			assertEquals(lokacije.size(), 1);
			
			verify(repozitorijumMock, times(1)).findByRentACarRentACarId((long)1);
			verifyNoMoreInteractions(repozitorijumMock);
		}
		
		
		// pronalazi servisi na osnovu naziva
		@Test
		public void testFindByAdresa() {
			
			when(repozitorijumMock.findOneByAdresa("Adresa")).thenReturn(lokacijaMock);
			Lokacija lokacija = lokacijaService.findByAdresa("Adresa");
			assertEquals(lokacijaMock, lokacija);
			verify(repozitorijumMock, times(1)).findOneByAdresa("Adresa");
			verifyNoMoreInteractions(repozitorijumMock);
			
		}

}
