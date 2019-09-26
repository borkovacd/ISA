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
import com.ftn.model.hotels.Hotel;
import com.ftn.model.hotels.OcenaHotel;
import com.ftn.model.rentacar.OcenaRentACar;
import com.ftn.model.rentacar.OcenaVozilo;
import com.ftn.model.rentacar.RentACar;
import com.ftn.model.rentacar.Vozilo;
import com.ftn.repository.HotelRepository;
import com.ftn.repository.OcenaHotelRepository;
import com.ftn.repository.OcenaRentRepository;
import com.ftn.repository.OcenaVoziloRepository;
import com.ftn.repository.RentCarRepository;
import com.ftn.repository.UserRepository;
import com.ftn.repository.VoziloRepository;
import com.ftn.service.OcenaHotelService;
import com.ftn.service.OcenaRentService;
import com.ftn.service.OcenaVoziloService;
import com.ftn.service.VoziloService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OcenaHotelServiceTest {
	
	@Mock
	private OcenaHotel ocenaMock ;
	
	@Mock
	private OcenaHotelRepository repozitorijumMock ;
	
	@Mock
	private HotelRepository rentRepositoryMock ;
	
	@Mock
	private UserRepository userRepository ;
	
	@InjectMocks
	private OcenaHotelService ocenaService ;
	
	
	@Test
	public void testGetProsecnaOcenaHotel() {
		
		Hotel hotel = new Hotel();
		hotel.setId((long) 1);
		Korisnik user = new Korisnik();
		user.setId((long)1);
		
		OcenaHotel ocena = new OcenaHotel();
		ocena.setOcena(2);
		ocena.setUser(user);
		ocena.setHotel(hotel);
		
		when(repozitorijumMock.findAll()).thenReturn(Arrays.asList(ocena));
		double ocenaV = ocenaService.getProsecnaOcenaHotelTest((long) 1);
		assertEquals(ocenaV, 2.00, 0.00);
		verify(repozitorijumMock, times(1)).findAll();
		verifyNoMoreInteractions(repozitorijumMock);
		
	}

}
