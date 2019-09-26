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

import com.ftn.dto.VoziloDTO;
import com.ftn.model.rentacar.RentACar;
import com.ftn.model.rentacar.Vozilo;
import com.ftn.repository.RentCarRepository;
import com.ftn.repository.VoziloRepository;
import com.ftn.service.VoziloService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VoziloServiceTest {
	
	@Mock
	private VoziloRepository repozitorijumMock;
	
	@Mock
	private RentCarRepository rentRepositoryMock ;

	
	@Mock
	private Vozilo voziloMock;
	
	
	@InjectMocks
	private VoziloService voziloService;
	
	
	// metoda za dodavanje vozila
	@Test
	@Transactional
    @Rollback(true)
	public void testDodajVozilo() {
		VoziloDTO v = new VoziloDTO();
		v.setNaziv("Naziv");
		v.setMarka("Marka");
		v.setModel("Model");
		v.setGodinaProizvodnje(2015);
		v.setBrojSedista(4);
		v.setTip("LIMUZINA");
		
		Vozilo vozilo = new Vozilo();
		vozilo.setNaziv("Naziv");
		vozilo.setMarka("Marka");
		vozilo.setModel("Model");
		vozilo.setGodinaProizvodnje(2015);
		vozilo.setBrojSedista(4);
		vozilo.setCena(200);
		vozilo.setRezervisano(false);
		vozilo.setNaPopustu(false);
		vozilo.setOcena(0);

		when(repozitorijumMock.findAll()).thenReturn(Arrays.asList(vozilo));
		
		RentACar rent = new RentACar();
		rent.setRentACarId((long) 1);
		Vozilo voziloV = voziloService.dodajVozilo(v, (long) 1);
		
		assertEquals(voziloV.getVoziloId(), vozilo.getVoziloId());
		verify(repozitorijumMock, times(1)).save(voziloV);
	}
	
	// vraca sva vozila
	@Test
	public void testGetAll() {

		when(repozitorijumMock.findAll()).thenReturn(Arrays.asList(new Vozilo("Vozilo")));
		List<Vozilo> vozila = voziloService.getAll();
		assertEquals(vozila.size(), 1);
		
		verify(repozitorijumMock, times(1)).findAll();
		verifyNoMoreInteractions(repozitorijumMock);
	}
	
	// vraca sva vozila iz rent-a-car-a
	@Test
	public void testGetVozilaRentACar() throws Exception {
		RentACar a = new RentACar();
		a.setRentACarId((long)1);
		when(repozitorijumMock.findByRentACarRentACarId((long)1)).thenReturn(Arrays.asList(new Vozilo("Vozilo")));
		
		List<Vozilo> vozila = voziloService.getVozilaRentACarTest((long)1);
		assertEquals(vozila.size(), 1);
		
		verify(repozitorijumMock, times(1)).findByRentACarRentACarId((long)1);
		verifyNoMoreInteractions(repozitorijumMock);
	}
	
	// metoda za stavljanje vozila na popust
	@Test
	@Transactional
    @Rollback(true)
	public void testStaviVoziloNaPopust() {
		
		Vozilo vozilo = new Vozilo();
		vozilo.setNaziv("Naziv");
		vozilo.setMarka("Marka");
		vozilo.setModel("Model");
		vozilo.setGodinaProizvodnje(2015);
		vozilo.setBrojSedista(4);
		vozilo.setCena(200);
		vozilo.setRezervisano(false);
		vozilo.setOcena(0);

		when(repozitorijumMock.getOne((long) 1)).thenReturn(vozilo);

		Vozilo voziloV = voziloService.staviVoziloNaPopust((long) 1);
		
		assertEquals(voziloV.getVoziloId(), vozilo.getVoziloId());
		verify(repozitorijumMock, times(1)).save(voziloV);
	}
	
	// metoda za skidanje vozila sa popusta
	@Test
	@Transactional
    @Rollback(true)
	public void testSkiniVoziloSaPopusta() {
		
		Vozilo vozilo = new Vozilo();
		vozilo.setNaziv("Naziv");
		vozilo.setMarka("Marka");
		vozilo.setModel("Model");
		vozilo.setGodinaProizvodnje(2015);
		vozilo.setBrojSedista(4);
		vozilo.setCena(200);
		vozilo.setRezervisano(false);
		vozilo.setOcena(0);

		when(repozitorijumMock.getOne((long) 1)).thenReturn(vozilo);

		Vozilo voziloV = voziloService.skiniVoziloSaPopusta((long) 1);
		
		assertEquals(voziloV.getVoziloId(), vozilo.getVoziloId());
		verify(repozitorijumMock, times(1)).save(voziloV);
	}
	
	
	// pronalazi vozilo na osnovu id-ja
	@Test
	public void testFindByVoziloId() {
		
		when(repozitorijumMock.findOneByVoziloId((long) 1)).thenReturn(voziloMock);
		Vozilo vozilo = voziloService.findByVoziloId((long) 1);
		assertEquals(voziloMock, vozilo);
		verify(repozitorijumMock, times(1)).findOneByVoziloId((long) 1);
		verifyNoMoreInteractions(repozitorijumMock);
		
	}
	
	// pronalazi servisi na osnovu naziva
	@Test
	public void testFindByNaziv() {
		
		when(repozitorijumMock.findOneByNaziv("Vozilo 1")).thenReturn(voziloMock);
		Vozilo vozilo = voziloService.findByNaziv("Vozilo 1");
		assertEquals(voziloMock, vozilo);
		verify(repozitorijumMock, times(1)).findOneByNaziv("Vozilo 1");
		verifyNoMoreInteractions(repozitorijumMock);
		
	}
	
	

}
