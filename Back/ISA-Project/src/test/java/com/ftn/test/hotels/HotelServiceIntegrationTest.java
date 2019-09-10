package com.ftn.test.hotels;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.ftn.model.hotels.Hotel;
import com.ftn.repository.CenovnikHotelaRepository;
import com.ftn.repository.HotelRepository;
import com.ftn.repository.RezervacijaHotelaRepository;
import com.ftn.repository.SobaRepository;
import com.ftn.repository.StavkaCenovnikaHotelaRepository;
import com.ftn.repository.UserRepository;
import com.ftn.service.HotelService;

@RunWith(SpringRunner.class)
public class HotelServiceIntegrationTest {
 
    @TestConfiguration
    static class HotelServiceTestContextConfiguration {
  
        @Bean
        public HotelService hotelService() {
            return new HotelService();
        }
    }
 
    @Autowired
    private HotelService hotelService;
 
    @MockBean
    private HotelRepository hotelRepostiory;
 
    @MockBean
    private SobaRepository sobaRepository;
    @MockBean
    private RezervacijaHotelaRepository rezervacijaHotelaRepository;
    @MockBean
	private UserRepository userRepository;
    @MockBean
	private CenovnikHotelaRepository cenovnikHotelaRepository;
    @MockBean
	private StavkaCenovnikaHotelaRepository stavkaCenovnikaHotelaRepository;
    
    @Before
    public void setUp() {
        Hotel hotel = new Hotel();
        hotel.setNaziv("Hotel Centar");
     
        Mockito.when(hotelRepostiory.findByNaziv(hotel.getNaziv()))
          .thenReturn(hotel);
    }
    
    @Test
    public void whenValidNaziv_thenHotelShouldBeFound() {
        String naziv = "Hotel Centar";
        Hotel found = hotelService.getHotelByNaziv(naziv);
      
         assertThat(found.getNaziv())
          .isEqualTo(naziv);
     }
    
    
}
