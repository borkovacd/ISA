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
import com.ftn.dto.OcenaVoziloDTO;
import com.ftn.dto.VoziloDTO;
import com.ftn.model.Korisnik;
import com.ftn.model.rentacar.OcenaRentACar;
import com.ftn.model.rentacar.OcenaVozilo;
import com.ftn.model.rentacar.RentACar;
import com.ftn.model.rentacar.Vozilo;
import com.ftn.repository.OcenaRentRepository;
import com.ftn.repository.OcenaVoziloRepository;
import com.ftn.repository.RentCarRepository;
import com.ftn.repository.UserRepository;
import com.ftn.repository.VoziloRepository;
import com.ftn.service.OcenaRentService;
import com.ftn.service.OcenaVoziloService;
import com.ftn.service.VoziloService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OcenaVoziloServiceTest {
	
	@Mock
	private OcenaVozilo ocenaMock ;
	
	@Mock
	private OcenaVoziloRepository repozitorijumMock ;
	
	@Mock
	private VoziloRepository rentRepositoryMock ;
	
	@Mock
	private UserRepository userRepository ;
	
	@InjectMocks
	private OcenaVoziloService ocenaService ;
	
	// metoda za ocenjivanje rent-a-car servisa
	@Test
	@Transactional
    @Rollback(true)
	public void testOceniVozilo() {
		OcenaVoziloDTO o = new OcenaVoziloDTO();
		o.setOcena("3");

		Vozilo vozilo = new Vozilo();
		vozilo.setVoziloId((long) 1);
		Korisnik user = new Korisnik();
		user.setId((long)1);
		
		OcenaVozilo ocena = new OcenaVozilo();
		ocena.setOcena(5);
		ocena.setUser(user);
		ocena.setVozilo(vozilo);

		when(repozitorijumMock.findAll()).thenReturn(Arrays.asList(ocena));
		
		OcenaVozilo ocenaR = ocenaService.oceniVoziloTest(o, user, (long) 1);
		
		assertEquals(ocenaR.getId(), ocena.getId());
		verify(repozitorijumMock, times(1)).save(ocenaR);
	}
	
	@Test
	public void testGetProsecnaOcenaVozilo() {
		
		Vozilo vozilo = new Vozilo();
		vozilo.setVoziloId((long) 1);
		Korisnik user = new Korisnik();
		user.setId((long)1);
		
		OcenaVozilo ocena = new OcenaVozilo();
		ocena.setOcena(5);
		ocena.setUser(user);
		ocena.setVozilo(vozilo);
		
		when(repozitorijumMock.findAll()).thenReturn(Arrays.asList(ocena));
		double ocenaV = ocenaService.getProsecnaOcenaVozilaTest((long) 1);
		assertEquals(ocenaV, 5.00, 0.00);
		verify(repozitorijumMock, times(1)).findAll();
		verifyNoMoreInteractions(repozitorijumMock);
		
	}
	
	

	

}
