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

import com.ftn.dto.StavkaCenovnikaRentDTO;
import com.ftn.dto.VoziloDTO;
import com.ftn.model.rentacar.CenovnikRentACar;
import com.ftn.model.rentacar.RentACar;
import com.ftn.model.rentacar.StavkaCenovnikaRent;
import com.ftn.model.rentacar.Vozilo;
import com.ftn.repository.CenovnikRentACarRepository;
import com.ftn.repository.RentCarRepository;
import com.ftn.repository.StavkaCenovnikaRentRepository;
import com.ftn.repository.VoziloRepository;
import com.ftn.service.StavkaCenovnikaRentService;
import com.ftn.service.VoziloService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StavkaCenovnikaRentTest {
	
	@Mock
	private StavkaCenovnikaRentRepository repozitorijumMock;
	
	@Mock
	private RentCarRepository rentRepositoryMock ;
	
	@Mock
	private CenovnikRentACarRepository cenovnikRepositoryMock ;

	
	@Mock
	private StavkaCenovnikaRent stavkaMock;
	
	
	@InjectMocks
	private StavkaCenovnikaRentService stavkaService;
	
	// metoda za dodavanje vozila
	@Test
	@Transactional
    @Rollback(true)
	public void testCreatePrice() {
		StavkaCenovnikaRentDTO s = new StavkaCenovnikaRentDTO();
		s.setCena("200");
		s.setTipStavke("LIMUZINA");
		
		CenovnikRentACar cenovnik = new CenovnikRentACar();
		cenovnik.setId((long)1);
		
		StavkaCenovnikaRent stavka = new StavkaCenovnikaRent();
		stavka.setCena(200.0);
		stavka.setId((long)1);
		stavka.setCenovnik(cenovnik);

		when(repozitorijumMock.findAll()).thenReturn(Arrays.asList(stavka));
		when(cenovnikRepositoryMock.getOne((long)1)).thenReturn(cenovnik);

		RentACar rent = new RentACar();
		rent.setRentACarId((long) 1);
		
		StavkaCenovnikaRent stavkaC = stavkaService.createPrice(s, (long) 1);
		
		assertEquals(stavkaC.getCena(), stavka.getCena());
		verify(repozitorijumMock, times(1)).save(stavkaC);
	}
	
	
	

}
