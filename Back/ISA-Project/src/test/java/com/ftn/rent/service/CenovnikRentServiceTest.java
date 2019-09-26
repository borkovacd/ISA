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

import com.ftn.dto.CenovnikRentDTO;
import com.ftn.dto.VoziloDTO;
import com.ftn.model.rentacar.CenovnikRentACar;
import com.ftn.model.rentacar.RentACar;
import com.ftn.model.rentacar.Vozilo;
import com.ftn.repository.CenovnikRentACarRepository;
import com.ftn.repository.RentCarRepository;
import com.ftn.repository.VoziloRepository;
import com.ftn.service.CenovnikRentACarService;
import com.ftn.service.VoziloService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CenovnikRentServiceTest {
	
	@Mock
	private CenovnikRentACarRepository repozitorijumMock ;
	
	@Mock
	private CenovnikRentACar cenovnikMock ;
	
	@Mock
	private RentCarRepository rentRepositoryMock ;
	
	@InjectMocks
	private CenovnikRentACarService cenovnikService ;
	
	// vraca sve cenovnike jednog servisa
		@Test
		public void testGetAllPricelists() {
			
			RentACar rent = new RentACar();
			rent.setRentACarId((long) 1);

			when(repozitorijumMock.findAll()).thenReturn(Arrays.asList(new CenovnikRentACar(rent)));
			when(rentRepositoryMock.getOne((long)1)).thenReturn(new RentACar());

			List<CenovnikRentACar> cenovnici = cenovnikService.getAllPricelistsTest((long)1);
			assertEquals(cenovnici.size(), 1);
			
			verify(repozitorijumMock, times(1)).findAll();
			verifyNoMoreInteractions(repozitorijumMock);
		}
		
	// vraca cenovnik na osnovu id-ja
		@Test
		public void testGetPricelist() {
			
			when(repozitorijumMock.getOne((long) 1)).thenReturn(cenovnikMock);
			CenovnikRentACar cen = cenovnikService.getPricelist((long) 1);
			assertEquals(cenovnikMock, cen);
			verify(repozitorijumMock, times(1)).getOne((long) 1);
			verifyNoMoreInteractions(repozitorijumMock);
			
		}
		
		// dodavanje novog cenovnika
		@Test
		@Transactional
	    @Rollback(true)
		public void testCreatePricelist() {
			CenovnikRentDTO cDTO = new CenovnikRentDTO();
			cDTO.setEndDate("2019-09-09");
			cDTO.setStartDate("2019-01-01");
			
			RentACar rent = new RentACar();
			rent.setRentACarId((long)1);
			
			String europeanDatePattern = "yyyy-MM-dd";
			DateTimeFormatter europeanDateFormatter = DateTimeFormatter.ofPattern(europeanDatePattern);
			LocalDate d1 = LocalDate.parse("2020-10-10");
			LocalDate d2 = LocalDate.parse("2020-12-12");
			
			CenovnikRentACar cenovnik = new CenovnikRentACar();
			cenovnik.setRentACar(rent);
			cenovnik.setPocetakVazenja(d1);
			cenovnik.setPrestanakVazenja(d2);

			when(rentRepositoryMock.getOne((long)1)).thenReturn(new RentACar());
			when(repozitorijumMock.findAll()).thenReturn(Arrays.asList(cenovnik));

			CenovnikRentACar cen = cenovnikService.createPricelist(cDTO, (long) 1);
			
			assertEquals(cen.getId(), cenovnik.getId());
			verify(repozitorijumMock, times(1)).findAll();
		}	
		
		@Test
		public void testGetActivePricelist() {
			
			RentACar rent = new RentACar();
			rent.setRentACarId((long)1);
			
			String europeanDatePattern = "yyyy-MM-dd";
			DateTimeFormatter europeanDateFormatter = DateTimeFormatter.ofPattern(europeanDatePattern);
			LocalDate d1 = LocalDate.parse("2019-01-01");
			LocalDate d2 = LocalDate.parse("2020-12-12");
			CenovnikRentACar cenovnik = new CenovnikRentACar();
			cenovnik.setRentACar(rent);
			cenovnik.setPocetakVazenja(d1);
			cenovnik.setPrestanakVazenja(d2);

			
			when(repozitorijumMock.findAll()).thenReturn(Arrays.asList(cenovnik));
			CenovnikRentACar cen = cenovnikService.getActivePricelist((long) 1);
			assertEquals(cenovnik, cen);
			verify(repozitorijumMock, times(1)).findAll();
			verifyNoMoreInteractions(repozitorijumMock);
			
		}
		

}
