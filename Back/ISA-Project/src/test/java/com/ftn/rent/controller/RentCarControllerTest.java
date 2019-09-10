package com.ftn.rent.controller;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

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
	
	
	
	
	/*
	 * @Test
	public void testVratiPrihod() throws Exception {
		DatumskiOpsegDTO dt = new DatumskiOpsegDTO();
		dt.setDatum1(new GregorianCalendar(2019, Calendar.SEPTEMBER, 4).getTime());
		dt.setDatum2(new GregorianCalendar(2019, Calendar.SEPTEMBER, 9).getTime());
		String json = ISA.project.utils.TestUtil.json(dt);
		this.mockMvc.perform(post(URL_PREFIX + "/vratiPrihod/7").contentType(contentType).content(json)).andExpect(status().isOk())
		.andExpect(jsonPath("$.iznos").value(14));
	}
	 */
	
	/*
	
	// METODA VRACA PRIHOD NA OSNOVU ID-JA RENT-A-CAR-A
	// U BAZI POSTOJI 1
	@Test
	public void testGetRevenuesRent() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/getRevenuesRent/3" )).andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.rentACarId").value(3));
	}	
	
	*/
	// METODA VRSI PRETRAGU RENT-A-CAR SERVISA
	/*@Test
	public void testPretraziLet() throws Exception {
		PretragaLetDTO l = new PretragaLetDTO();
		l.setBrOsoba(2);
		l.setKlasa("PRVA");
		l.setTip("ONE_WAY");
		l.setMestoPoletanja("Nis");
		l.setMestoSletanja("Beograd");
		l.setVremePovratka(null);
		l.setVremePoletanja(new GregorianCalendar(2019, Calendar.SEPTEMBER, 2).getTime());
		String json = ISA.project.utils.TestUtil.json(l);
		this.mockMvc.perform(post(URL_PREFIX + "/pretraziLet").contentType(contentType).content(json)).andExpect(status().isOk())
		.andExpect(jsonPath("$.[*].id").value(hasItem(1)));
	}
	*/

}
