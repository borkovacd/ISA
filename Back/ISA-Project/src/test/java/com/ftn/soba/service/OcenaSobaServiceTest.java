package com.ftn.soba.service;

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
import com.ftn.model.hotels.OcenaSoba;
import com.ftn.model.hotels.Soba;
import com.ftn.model.rentacar.OcenaRentACar;
import com.ftn.model.rentacar.OcenaVozilo;
import com.ftn.model.rentacar.RentACar;
import com.ftn.model.rentacar.Vozilo;
import com.ftn.repository.OcenaRentRepository;
import com.ftn.repository.OcenaSobaRepository;
import com.ftn.repository.OcenaVoziloRepository;
import com.ftn.repository.RentCarRepository;
import com.ftn.repository.SobaRepository;
import com.ftn.repository.UserRepository;
import com.ftn.repository.VoziloRepository;
import com.ftn.service.OcenaRentService;
import com.ftn.service.OcenaSobaService;
import com.ftn.service.OcenaVoziloService;
import com.ftn.service.VoziloService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OcenaSobaServiceTest {
	
	@Mock
	private OcenaSoba ocenaMock ;
	
	@Mock
	private OcenaSobaRepository repozitorijumMock ;
	
	@Mock
	private SobaRepository rentRepositoryMock ;
	
	@Mock
	private UserRepository userRepository ;
	
	@InjectMocks
	private OcenaSobaService ocenaService ;
	
	
	@Test
	public void testGetProsecnaOcenaSoba() {
		
		Soba soba = new Soba();
		soba.setId((long) 1);
		Korisnik user = new Korisnik();
		user.setId((long)1);
		
		OcenaSoba ocena = new OcenaSoba();
		ocena.setOcena(5);
		ocena.setUser(user);
		ocena.setSoba(soba);
		
		when(repozitorijumMock.findAll()).thenReturn(Arrays.asList(ocena));
		double ocenaV = ocenaService.getProsecnaOcenaSobaTest((long) 1);
		assertEquals(ocenaV, 5.00, 0.00);
		verify(repozitorijumMock, times(1)).findAll();
		verifyNoMoreInteractions(repozitorijumMock);
		
	}

}
