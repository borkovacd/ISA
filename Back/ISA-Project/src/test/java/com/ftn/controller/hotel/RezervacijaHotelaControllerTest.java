package com.ftn.controller.hotel;

import org.junit.runner.RunWith;
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

import com.ftn.dto.RezervacijaSobaDTO;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.ArrayList;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RezervacijaHotelaControllerTest {
	
private static final String URL_PREFIX = "/api/hotelReservation";
	
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
	public void testGetReservation() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/getReservation/10" )).andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.id").value(10));
	}
	
	
	@Transactional
	@Rollback(true)
	@Test
	public void testCreateReservation() throws Exception {
		RezervacijaSobaDTO rezervacija = new RezervacijaSobaDTO();
		rezervacija.setStartDate("2019-10-24");
		rezervacija.setEndDate("2019-10-29");
		rezervacija.setNumberOfGuests(Integer.toString(4));
		ArrayList<String> rooms = new ArrayList<String>();
		rooms.add(Integer.toString(11));
		rooms.add(Integer.toString(12));
		rezervacija.setRoomList(rooms);
		String json = com.ftn.utils.TestUtil.json(rezervacija);
		this.mockMvc.perform(post(URL_PREFIX + "/create/13").contentType(contentType).content(json)).andExpect(status().isCreated());
	}
	
	@Transactional
	@Rollback(true)
	@Test
	public void testCreateFastReservation() throws Exception {
		this.mockMvc.perform(post(URL_PREFIX + "/createFast/13/11/10/14").contentType(contentType)).andExpect(status().isCreated());
	}

	
	@Transactional
	@Rollback(true)
	@Test
	public void testCancelReservation() throws Exception {
		MvcResult result = mockMvc.perform(post(URL_PREFIX + "/otkaziRezervacijuHotela/11" )).andExpect(status().isOk()).andReturn();
		String resultAsString = result.getResponse().getContentAsString();
		boolean retVal = Boolean.parseBoolean(resultAsString);
		//System.out.println(retVal);
		assertThat(retVal)
	      .isEqualTo(true);
	}
	
}
