package com.ftn.rent.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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

import com.ftn.dto.CenovnikRentDTO;
import com.ftn.dto.PretragaRentDTO;
import com.ftn.dto.RentCarDTO;
import com.ftn.dto.VoziloDTO;
import com.ftn.dto.VremenskiPeriodDTO;
import com.ftn.enums.TipVozila;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CenovnikRentACarControllerTest {
	
private static final String URL_PREFIX = "/api/pricelistRent";
	
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
	
	// METODA ZA DODAVANJE NOVOG RENT SERVISA
	@Transactional
	@Rollback(true)
	@Test
	public void testCreatePricelistRent() throws Exception {
		CenovnikRentDTO dto = new CenovnikRentDTO();
		dto.setEndDate("2019-09-09");
		dto.setStartDate("2019-08-09");
		String json = com.ftn.utils.TestUtil.json(dto);
		this.mockMvc.perform(post(URL_PREFIX + "/createPricelistRent/10").contentType(contentType).content(json)).andExpect(status().isOk());
	
	}
	
	@Test
	public void testGetPricelistRent() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/getPricelistRent/10" )).andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.id").value(10));
	}
	
	@Test
	public void testGetActivePricelistRent() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/getActivePricelistRent/10" )).andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.id").value(11));
	}
	
	@Transactional
	@Rollback(true)
	@Test
	public void testObrisiCenovnik() throws Exception {
		mockMvc.perform(delete(URL_PREFIX + "/obrisiCenovnik/10/10" )).andExpect(status().isOk());
	}

	
	
	
	
	

}
