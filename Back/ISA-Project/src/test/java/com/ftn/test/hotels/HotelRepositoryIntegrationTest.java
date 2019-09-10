package com.ftn.test.hotels;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.ftn.model.hotels.Hotel;
import com.ftn.repository.HotelRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class HotelRepositoryIntegrationTest {
	
	/*@RunWith(SpringRunner.class) is used to provide a bridge between Spring Boot test features and JUnit. 
	  Whenever we are using any Spring Boot testing features in our JUnit tests, this annotation will be required. */
	
	/*@DataJpaTest provides some standard setup needed for testing the persistence layer:
		- configuring H2, an in-memory database
		- setting Hibernate, Spring Data, and the DataSource
		- performing an @EntityScan
		- turning on SQL logging */
	
	
	@Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private HotelRepository hotelRepository;
    
    
    @Test
    public void whenFindByNaziv_thenReturnHotel() {
        // given
        Hotel hotel = new Hotel();
        hotel.setNaziv("Hotel Centar");
        entityManager.persist(hotel);
        entityManager.flush();
     
        // when
        Hotel found = hotelRepository.findByNaziv(hotel.getNaziv());
     
        // then
        assertThat(found.getNaziv())
          .isEqualTo(hotel.getNaziv());
    }

}
