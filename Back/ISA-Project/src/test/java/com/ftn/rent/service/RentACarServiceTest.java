package com.ftn.rent.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.ftn.model.Korisnik;
import com.ftn.model.rentacar.RentACar;
import com.ftn.repository.RentCarRepository;
import com.ftn.service.RentACarService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RentACarServiceTest {
	
	@Mock
	private RentCarRepository repozitorijumMock;
	
	@Mock
	private RentACar rentMock;
	
	@InjectMocks
	private RentACarService rentService;
	
	// pronalazi servis na osnovu id-ja
	@Test
	public void testFindByRentACarId() {
		
		when(repozitorijumMock.findOneByRentACarId((long) 1)).thenReturn(rentMock);
		RentACar rent = rentService.findByRentACarId((long) 1);
		assertEquals(rentMock, rent);
		verify(repozitorijumMock, times(1)).findOneByRentACarId((long) 1);
		verifyNoMoreInteractions(repozitorijumMock);
		
	}
	
	// pronalazi servisi na osnovu naziva
	@Test
	public void testFindByNaziv() {
		
		when(repozitorijumMock.findOneByNaziv("Rent A Car 1")).thenReturn(rentMock);
		RentACar rent = rentService.findByNaziv("Rent A Car 1");
		assertEquals(rentMock, rent);
		verify(repozitorijumMock, times(1)).findOneByNaziv("Rent A Car 1");
		verifyNoMoreInteractions(repozitorijumMock);
		
	}
	
	// cuvanje novog servisa
	@Test
	@Transactional
    @Rollback(true)
	public void testSacuvajRentACar() {
		RentACar rent = new RentACar("Naziv", "Adresa", "Opis", 0);
		when(repozitorijumMock.findAll()).thenReturn(Arrays.asList(rent));
		RentACar newRent = new RentACar();
		newRent.setAdresa("Adresa1");
		newRent.setOcena(0);
		newRent.setNaziv("Naziv");
		newRent.setOpis("Opis");
		
		when(repozitorijumMock.save(newRent)).thenReturn(newRent);
		rentService.sacuvajRentACar(newRent);
		int dbSizeBeforeAdd = rentService.vratiSve().size();
		
		
		when(repozitorijumMock.findAll()).thenReturn(Arrays.asList(rent, newRent));
        List<RentACar> servisi = rentService.vratiSve();
        assertEquals(servisi.size(), dbSizeBeforeAdd + 1);
        RentACar rc = servisi.get(servisi.size() - 1);
        assertEquals(rc.getNaziv(), "Naziv");
        verify(repozitorijumMock, times(2)).findAll();
        verify(repozitorijumMock, times(1)).save(newRent);
        verifyNoMoreInteractions(repozitorijumMock);
	}
	
	@Test
	public void testVratiSve() 
	{
	
		when(repozitorijumMock.findAll()).thenReturn(Arrays.asList(new RentACar("Naziv", "Adresa", "Opis", 0)));
		List<RentACar> servisi = rentService.vratiSve();
		assertEquals(servisi.size(), 1);
	
		verify(repozitorijumMock, times(1)).findAll();
        verifyNoMoreInteractions(repozitorijumMock);
	}
	
	@Test
	public void testNadjiRentPoKorisniku() {
		Korisnik k = new Korisnik();
		k.setId((long) 1);
		when(repozitorijumMock.findOneByAdministratorId((long) 1)).thenReturn(rentMock);
		RentACar rent = rentService.nadjiRentPoKorisniku(k);
		assertEquals(rent, rentMock);
		
		verify(repozitorijumMock, times(1)).findOneByAdministratorId((long) 1);
		verifyNoMoreInteractions(repozitorijumMock);
	}
	
	
	
	

}
