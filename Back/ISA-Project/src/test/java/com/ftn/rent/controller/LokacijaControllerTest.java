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

import com.ftn.dto.LokacijaDTO;
import com.ftn.dto.PretragaRentDTO;
import com.ftn.dto.RentCarDTO;
import com.ftn.dto.VoziloDTO;
import com.ftn.dto.VremenskiPeriodDTO;
import com.ftn.enums.TipVozila;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LokacijaControllerTest {
	
private static final String URL_PREFIX = "/api/filijala";
	
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
	public void testDodajFilijalu() throws Exception {
		LokacijaDTO dto = new LokacijaDTO();
		dto.setAdresa("Adresa");
		dto.setDrzava("Srbija");
		dto.setGrad("Novi Sad");
		dto.setLatitude(15);
		dto.setLongitude(25);
		String json = com.ftn.utils.TestUtil.json(dto);
		this.mockMvc.perform(post(URL_PREFIX + "/dodajFilijalu/10").contentType(contentType).content(json)).andExpect(status().isOk());
	
	}
	
	@Test
	public void testVratiJednuFilijalu() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/vratiJednuFilijalu/10/10" )).andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.id").value(10));
	}
	
	
	@Test
	public void testGetFilijaleRentACar() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/getFilijaleRentACar/10")).andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.[*].id").value(hasItem(10)))
		.andExpect(jsonPath("$.[*].id").value(hasItem(11)));
	}
	
	@Transactional
	@Rollback(true)
	@Test
	public void testIzmeniFilijalu() throws Exception {
		LokacijaDTO dto = new LokacijaDTO();
		dto.setAdresa("Adresa");
		dto.setDrzava("Srbija");
		dto.setGrad("Novi Sad");
		dto.setLatitude(15);
		dto.setLongitude(25);
		String json = com.ftn.utils.TestUtil.json(dto);
		this.mockMvc.perform(put(URL_PREFIX + "/izmeniFilijalu/10/10").contentType(contentType).content(json)).andExpect(status().isOk());
	}
	
	@Transactional
	@Rollback(true)
	@Test
	public void testObrisiFilijalu() throws Exception {
		mockMvc.perform(delete(URL_PREFIX + "/obrisiFilijalu/10/10" )).andExpect(status().isOk());
	}

	

}
