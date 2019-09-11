package com.ftn.controller.hotel;

import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.ftn.dto.HotelDTO;
import com.ftn.dto.PretragaHotelaDTO;
import com.ftn.dto.PretragaRentDTO;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HotelControllerTest {
	
private static final String URL_PREFIX = "/api/hotel";
	
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
	
	@Test
	public void testGetAllHotels() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/getAllHotels" )).andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.[*].id").value(hasItem(10)))
		.andExpect(jsonPath("$.[*].id").value(hasItem(11)));
	}
	
	@Test
	public void testGetHotelsByAdministrator() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/getHotelsByAdministrator/11" )).andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.[*].id").value(hasItem(10)))
		.andExpect(jsonPath("$.[*].id").value(hasItem(11)));
	}
	
	@Test
	public void testGetHotel() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/getHotel/11" )).andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.id").value(11));
	}
	
	@Test
	public void testGetHotelRevenues() throws Exception {
		MvcResult result = this.mockMvc.perform(get(URL_PREFIX + "/getRevenues/10?d1=2019-08-31&d2=2019-09-30")).andExpect(status().isOk())
		.andReturn();
		String resultAsString = result.getResponse().getContentAsString();
		double retVal = Double.parseDouble(resultAsString);
		//System.out.println(retVal);
		assertThat(retVal)
	      .isEqualTo(25000.00);
	}
	
	@Transactional
	@Rollback(true)
	@Test
	public void testRegisterHotel() throws Exception {
		HotelDTO hotel = new HotelDTO();
		hotel.setName("Hotel ZEN");
		hotel.setAddress("Gornjomatejevacka 116");
		hotel.setAdministratorHotela("marko");
		hotel.setDescription("Savrsen za opustanje.");
		String json = com.ftn.utils.TestUtil.json(hotel);
		this.mockMvc.perform(post(URL_PREFIX + "/registerHotel").contentType(contentType).content(json)).andExpect(status().isOk());
	}
	
	@Transactional
	@Rollback(true)
	@Test
	public void testEditHotel() throws Exception {
		HotelDTO hotel = new HotelDTO();
		hotel.setName("Hotel ZEN");
		hotel.setAddress("Gornjomatejevacka 116");
		hotel.setDescription("Savrsen za opustanje.");
		String json = com.ftn.utils.TestUtil.json(hotel);
		this.mockMvc.perform(put(URL_PREFIX + "/izmeniHotel/10").contentType(contentType).content(json)).andExpect(status().isOk());
	}
	
	@Test
	public void testSearchHotels() throws Exception {
		PretragaHotelaDTO p = new PretragaHotelaDTO();
		p.setStartDate("2019-10-01");
		p.setEndDate("2019-10-05");
		p.setHotelName("Hotel Centar");
		p.setHotelLocation("");
		String json = com.ftn.utils.TestUtil.json(p);
		this.mockMvc.perform(post(URL_PREFIX + "/searchHotels").contentType(contentType).content(json)).andExpect(status().isOk())
		.andExpect(jsonPath("$.[*].id").value(hasItem(10)));
	}
	

}
