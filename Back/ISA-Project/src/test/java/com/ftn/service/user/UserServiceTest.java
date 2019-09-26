package com.ftn.service.user;

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

import com.ftn.dto.KorisnikDTO;
import com.ftn.dto.LokacijaDTO;
import com.ftn.dto.VoziloDTO;
import com.ftn.model.Korisnik;
import com.ftn.model.rentacar.Lokacija;
import com.ftn.model.rentacar.RentACar;
import com.ftn.model.rentacar.Vozilo;
import com.ftn.repository.LokacijaRepository;
import com.ftn.repository.RentCarRepository;
import com.ftn.repository.UserRepository;
import com.ftn.repository.VoziloRepository;
import com.ftn.service.LokacijaService;
import com.ftn.service.UserService;
import com.ftn.service.VoziloService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
	
	@Mock 
	private Korisnik korisnikMock ;
	
	@Mock
	private UserRepository repozitorijumMock ;
	
	@InjectMocks
	private UserService userService ;
	
	// metoda za registraciju
			@Test
			@Transactional
		    @Rollback(true)
			public void testRegister() {
				KorisnikDTO k = new KorisnikDTO();
				k.setEmail("savic.olga@gmail.com");
				k.setKorisnickoIme("olga");
				k.setLozinka("olga");
				k.setIme("Olga");
				k.setPrezime("Savic");
				k.setGrad("Novi Sad");
				k.setStatusKorisnika("ok");
				k.setTelefon("063");
				

				Korisnik korisnik = new Korisnik();
				korisnik.setEmail("vujovic.nemanja@gmail.com");
				korisnik.setKorisnickoIme("nemanja");
				k.setLozinka("nemanja");
				k.setIme("Nemanja");
				k.setPrezime("Vujovic");
				k.setGrad("Novi Sad");
				k.setStatusKorisnika("ok");
				k.setTelefon("064");

				when(repozitorijumMock.findAll()).thenReturn(Arrays.asList(korisnik));
				when(repozitorijumMock.findOneByEmail(("vujovic.nemanja@gmail.com"))).thenReturn(korisnik);
				when(repozitorijumMock.findOneByKorisnickoIme(("nemanja"))).thenReturn(korisnik);

				
				String korisnikK = userService.register(k);
				
				assertEquals(korisnikK, "ok");
				//verify(repozitorijumMock, times(1)).save(korisnik);
			}
			
			@Test
			public void testReturnKorisnikById() {
				Korisnik korisnik = new Korisnik();
				korisnik.setId((long) 1);
				when(repozitorijumMock.findOneById((long) 1)).thenReturn(korisnikMock);
				Korisnik korisnikK = userService.returnKorisnikById(korisnik);
				assertEquals(korisnikMock, korisnikK);
				verify(repozitorijumMock, times(1)).findOneById((long) 1);
				verifyNoMoreInteractions(repozitorijumMock);
				
			}
			
			// pronalazi servisi na osnovu naziva
			@Test
			public void testReturnKorisnikByEmail() {
				
				when(repozitorijumMock.findOneByEmail("Rent A Car 1")).thenReturn(korisnikMock);
				Korisnik korisnikK = userService.returnKorisnikByEmail("Rent A Car 1");
				assertEquals(korisnikMock, korisnikK);
				verify(repozitorijumMock, times(1)).findOneByEmail("Rent A Car 1");
				verifyNoMoreInteractions(repozitorijumMock);
				
			}
			
			// pronalazi servisi na osnovu naziva
			@Test
			public void testAktivirajNalog() {
				
				when(repozitorijumMock.findOneByEmail("Rent A Car 1")).thenReturn(korisnikMock);
				String korisnikK = userService.aktivirajNalog("Rent A Car 1");
				assertEquals(korisnikK, "Verifikovali ste mail, mozete posetiti sajt.");
				verify(repozitorijumMock, times(1)).findOneByEmail("Rent A Car 1");
				//verify(repozitorijumMock, times(1)).save(korisnik);
				
			}
				

}
