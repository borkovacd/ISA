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

import com.ftn.dto.CenovnikHotelaDTO;
import com.ftn.dto.HotelDTO;
import com.ftn.dto.PretragaHotelaDTO;
import com.ftn.dto.PretragaRentDTO;
import com.ftn.dto.SobaDTO;
import com.ftn.dto.VremenskiPeriodDTO;
import com.ftn.enums.TipSobe;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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
public class CenovnikHotelaControllerTest {
	
private static final String URL_PREFIX = "/api/pricelist";
	
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
	public void testGetAllHotelPricelists() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/getAllPricelists/10" )).andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.[*].id").value(hasItem(10)))
		.andExpect(jsonPath("$.[*].id").value(hasItem(11)));
	}
	
	@Test
	public void testGetPricelist() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/getPricelist/11" )).andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.id").value(11));
	}
	
	@Test
	public void testGetActivePricelist() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/getActivePricelist/10" )).andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.id").value(10));
	}
	
	@Transactional
	@Rollback(true)
	@Test
	public void testCreatePricelist() throws Exception {
		CenovnikHotelaDTO cenovnik = new CenovnikHotelaDTO();
		cenovnik.setStartDate("2020-06-01");
		cenovnik.setEndDate("2020-08-31");
		String json = com.ftn.utils.TestUtil.json(cenovnik);
		this.mockMvc.perform(post(URL_PREFIX + "/createPricelist/11").contentType(contentType).content(json)).andExpect(status().isOk());
	}
	
	
}
