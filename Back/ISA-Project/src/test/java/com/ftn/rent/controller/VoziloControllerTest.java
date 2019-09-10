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

import com.ftn.dto.PretragaRentDTO;
import com.ftn.dto.RentCarDTO;
import com.ftn.dto.VoziloDTO;
import com.ftn.dto.VremenskiPeriodDTO;
import com.ftn.enums.TipVozila;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VoziloControllerTest {
	
private static final String URL_PREFIX = "/api/vozilo";
	
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
	public void testDodajVozilo() throws Exception {
		VoziloDTO dto = new VoziloDTO();
		dto.setBrojSedista(5);
		dto.setGodinaProizvodnje(2015);
		dto.setMarka("Marka");
		dto.setModel("Model");
		dto.setNaziv("Naziv");
		dto.setTip("KARAVAN");
		String json = com.ftn.utils.TestUtil.json(dto);
		this.mockMvc.perform(post(URL_PREFIX + "/dodajVozilo/10").contentType(contentType).content(json)).andExpect(status().isOk());
	
	}
	
	@Test
	public void testVratiJednoVozilo() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/vratiJednoVozilo/10/10" )).andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.voziloId").value(10));
	}
	
	@Test
	public void testVratiSvaVozila() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/vratiSvaVozila")).andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.[*].voziloId").value(hasItem(10)))
		.andExpect(jsonPath("$.[*].voziloId").value(hasItem(11)))
		.andExpect(jsonPath("$.[*].voziloId").value(hasItem(12)));
	}
	
	@Test
	public void getVozilaRentACar() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/getVozilaRentACar/10")).andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.[*].voziloId").value(hasItem(10)))
		.andExpect(jsonPath("$.[*].voziloId").value(hasItem(11)));
	}
	
	@Transactional
	@Rollback(true)
	@Test
	public void testIzmeniVozilo() throws Exception {
		VoziloDTO dto = new VoziloDTO();
		dto.setBrojSedista(5);
		dto.setGodinaProizvodnje(2015);
		dto.setMarka("Marka");
		dto.setModel("Model");
		dto.setNaziv("Naziv");
		dto.setTip("KARAVAN");
		String json = com.ftn.utils.TestUtil.json(dto);
		this.mockMvc.perform(put(URL_PREFIX + "/izmeniVozilo/10/10").contentType(contentType).content(json)).andExpect(status().isOk());
	}
	
	@Test
	public void testGetAvailableVozila() throws Exception {
		VremenskiPeriodDTO vp = new VremenskiPeriodDTO();
		vp.setStartDate("2019-10-01");
		vp.setEndDate("2019-10-05");
		String json = com.ftn.utils.TestUtil.json(vp);
		this.mockMvc.perform(post(URL_PREFIX + "/getAvailableVozila/10").contentType(contentType).content(json)).andExpect(status().isOk())
		.andExpect(jsonPath("$.[*].voziloId").value(hasItem(10)))
		.andExpect(jsonPath("$.[*].voziloId").value(hasItem(11)));
	}
	
	@Transactional
	@Rollback(true)
	@Test
	public void testObrisiVozilo() throws Exception {
		mockMvc.perform(delete(URL_PREFIX + "/obrisiVozilo/10/10" )).andExpect(status().isOk());
	}
	
	@Test
	public void testCheckIfReservedVozilo() throws Exception {
		MvcResult result = mockMvc.perform(get(URL_PREFIX + "/checkIfReservedVozilo/10" )).andExpect(status().isOk()).andReturn();
		String resultAsString = result.getResponse().getContentAsString();
		boolean retVal = Boolean.parseBoolean(resultAsString);
		//System.out.println(retVal);
		assertThat(retVal)
	      .isEqualTo(false);
	}

	
	
	
	
	
	

}
