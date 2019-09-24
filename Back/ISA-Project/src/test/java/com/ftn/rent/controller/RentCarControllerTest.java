package com.ftn.rent.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.ftn.dto.PretragaRentDTO;
import com.ftn.dto.RentCarDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RentCarControllerTest {
	
private static final String URL_PREFIX = "/rentCar";
	
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	@Before
	public void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	// METODA VRACA SVE RENT-A-CAR SERVISE
	// U BAZI POSTOJE 3
	@Test
	public void testGetAllRents() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/getAllRents")).andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.[*].rentACarId").value(hasItem(10)))
		.andExpect(jsonPath("$.[*].rentACarId").value(hasItem(11)))
		.andExpect(jsonPath("$.[*].rentACarId").value(hasItem(12)))
		.andExpect(jsonPath("$.[*].rentACarId").value(hasItem(13)));
	}
	
	
	// METODA VRACA RENT-A-CAR NA OSNOVU ID-JA
	// U BAZI POSTOJI 1
	@Test
	public void testGetRent() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/getRent/11" )).andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.rentACarId").value(11));
	}
	
	// METODA ZA DODAVANJE NOVOG RENT SERVISA
	@Transactional
	@Rollback(true)
	@Test
	public void testRegisterRentCar() throws Exception {
		RentCarDTO dto = new RentCarDTO();
		dto.setName("RentCar 1");
		//dto.setAdministratorRentCar(12);
		dto.setDescription("Opis");
		dto.setAddress("Adresa");
		String json = com.ftn.utils.TestUtil.json(dto);
		this.mockMvc.perform(post(URL_PREFIX + "/registerRentCar").contentType(contentType).content(json)).andExpect(status().isOk());
	
	}
	
	// METODA KOJA VRSI PRETRAGU RENT-A-CAR SERVISA
	@Test
	public void testSearchRents() throws Exception {
		PretragaRentDTO p = new PretragaRentDTO();
		p.setEndDate("2019-07-25");
		p.setStartDate("2019-07-01");
		p.setRentName("Rent trans 1");
		p.setRentLocation("Beograd");
		String json = com.ftn.utils.TestUtil.json(p);
		this.mockMvc.perform(post(URL_PREFIX + "/searchRents").contentType(contentType).content(json)).andExpect(status().isOk())
		.andExpect(jsonPath("$.[*].rentACarId").value(hasItem(10)));
	}
	
	// METODA KOJA VRSI IZMENU RENT-A-CAR SERVISA
	@Transactional
	@Rollback(true)
	@Test
	public void testIzmeniRent() throws Exception {
		RentCarDTO s = new RentCarDTO();
		s.setName("Novo Naselje");
		s.setDescription("Moja adresa");
		s.setAddress("Brace Dronjak 10");
		String json = com.ftn.utils.TestUtil.json(s);
		this.mockMvc.perform(put(URL_PREFIX + "/izmeniRent/10").contentType(contentType).content(json)).andExpect(status().isOk());
	}
	
	// METODA KOJA VRACA PRIHODE RENT-A-CAR SERVISA ZA IZABRANI PERIOD
	@Test
	public void testGetRevenuesRent() throws Exception {
		MvcResult result = this.mockMvc.perform(get(URL_PREFIX + "/getRevenuesRent/10?d1=2019-01-01&d2=2019-02-02")).andExpect(status().isOk())
		.andReturn();
		String resultAsString = result.getResponse().getContentAsString();
		double retVal = Double.parseDouble(resultAsString);
		//System.out.println(retVal);
		assertThat(retVal)
	      .isEqualTo(0.00);
	}
	
	@Test
	public void testCheckIfReservedRent() throws Exception {
		MvcResult result = mockMvc.perform(get(URL_PREFIX + "/checkIfReservedRent/10" )).andExpect(status().isOk()).andReturn();
		String resultAsString = result.getResponse().getContentAsString();
		boolean retVal = Boolean.parseBoolean(resultAsString);
		//System.out.println(retVal);
		assertThat(retVal)
	      .isEqualTo(true);
	}
	
	@Test
	public void testGetAllRentsByAddress() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/getAllRentsByAddress/10")).andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.[*].rentACarId").value(hasItem(10)))
		.andExpect(jsonPath("$.[*].rentACarId").value(hasItem(11)));
		
	}
	
	@Test
	public void testGetSortedRents() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/sort/NameA")).andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.[*].rentACarId").value(hasItem(10)))
		.andExpect(jsonPath("$.[*].rentACarId").value(hasItem(11)))
		.andExpect(jsonPath("$.[*].rentACarId").value(hasItem(12)))
		.andExpect(jsonPath("$.[*].rentACarId").value(hasItem(13)));
	}
	
	
	
	
	
	
	


}
