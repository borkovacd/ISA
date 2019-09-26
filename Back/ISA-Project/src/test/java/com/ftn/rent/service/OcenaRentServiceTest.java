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

import com.ftn.dto.OcenaRentDTO;
import com.ftn.dto.VoziloDTO;
import com.ftn.model.Korisnik;
import com.ftn.model.rentacar.OcenaRentACar;
import com.ftn.model.rentacar.RentACar;
import com.ftn.model.rentacar.Vozilo;
import com.ftn.repository.OcenaRentRepository;
import com.ftn.repository.RentCarRepository;
import com.ftn.repository.UserRepository;
import com.ftn.repository.VoziloRepository;
import com.ftn.service.OcenaRentService;
import com.ftn.service.VoziloService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OcenaRentServiceTest {
	
	@Mock
	private OcenaRentACar ocenaMock ;
	
	@Mock
	private OcenaRentRepository repozitorijumMock ;
	
	@Mock
	private RentCarRepository rentRepositoryMock ;
	
	@Mock
	private UserRepository userRepository ;
	
	@InjectMocks
	private OcenaRentService ocenaService ;
	
	// metoda za ocenjivanje rent-a-car servisa
	@Test
	@Transactional
    @Rollback(true)
	public void testOceniRent() {
		OcenaRentDTO o = new OcenaRentDTO();
		o.setOcena("5");

		RentACar rent = new RentACar();
		rent.setRentACarId((long) 1);
		Korisnik user = new Korisnik();
		user.setId((long)1);
		
		OcenaRentACar ocena = new OcenaRentACar();
		ocena.setOcena(5);
		ocena.setUser(user);
		ocena.setRent(rent);

		when(repozitorijumMock.findAll()).thenReturn(Arrays.asList(ocena));
		
		OcenaRentACar ocenaR = ocenaService.oceniRentTest(o, user, (long) 1);
		
		assertEquals(ocenaR.getId(), ocena.getId());
		verify(repozitorijumMock, times(1)).save(ocenaR);
	}
	
	@Test
	public void testGetProsecnaOcenaRent() {
		
		RentACar rent = new RentACar();
		rent.setRentACarId((long) 1);
		Korisnik user = new Korisnik();
		user.setId((long)1);
		
		OcenaRentACar ocena = new OcenaRentACar();
		ocena.setOcena(5);
		ocena.setUser(user);
		ocena.setRent(rent);
		
		when(repozitorijumMock.findAll()).thenReturn(Arrays.asList(ocena));
		double ocenaR = ocenaService.getProsecnaOcenaRent((long) 1);
		assertEquals(ocenaR, 5.00, 0.00);
		verify(repozitorijumMock, times(1)).findAll();
		verifyNoMoreInteractions(repozitorijumMock);
		
	}
	
	

	
	
	

}
