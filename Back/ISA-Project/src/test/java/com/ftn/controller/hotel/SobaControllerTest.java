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
public class SobaControllerTest {
	
private static final String URL_PREFIX = "/api/room";
	
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
	public void testGetAllHotelRooms() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/getAllRooms/10" )).andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.[*].id").value(hasItem(10)))
		.andExpect(jsonPath("$.[*].id").value(hasItem(11)))
		.andExpect(jsonPath("$.[*].id").value(hasItem(12)))
		.andExpect(jsonPath("$.[*].id").value(hasItem(13)))
		.andExpect(jsonPath("$.[*].id").value(hasItem(14)));
	}
	
	@Test
	public void testGetRoom() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/getRoom/12" )).andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.id").value(12));
	}
	
	@Transactional
	@Rollback(true)
	@Test
	public void testCreateRoom() throws Exception {
		SobaDTO soba = new SobaDTO();
		soba.setRoomType("APARTMAN");
		soba.setCapacity(4);
		soba.setFloor(2);
		soba.setHasBalcony(false);
		String json = com.ftn.utils.TestUtil.json(soba);
		this.mockMvc.perform(post(URL_PREFIX + "/createRoom/11").contentType(contentType).content(json)).andExpect(status().isOk());
	}
	
	@Transactional
	@Rollback(true)
	@Test
	public void testEditRoom() throws Exception {
		SobaDTO soba = new SobaDTO();
		soba.setRoomType("APARTMAN");
		soba.setCapacity(4);
		soba.setFloor(2);
		soba.setHasBalcony(false);
		String json = com.ftn.utils.TestUtil.json(soba);
		this.mockMvc.perform(put(URL_PREFIX + "/editRoom/12").contentType(contentType).content(json)).andExpect(status().isOk());
	}
	
	@Test
	public void testGetAvailableRooms() throws Exception {
		VremenskiPeriodDTO vp = new VremenskiPeriodDTO();
		vp.setStartDate("2019-10-01");
		vp.setEndDate("2019-10-05");
		String json = com.ftn.utils.TestUtil.json(vp);
		this.mockMvc.perform(post(URL_PREFIX + "/getAvailableRooms/10").contentType(contentType).content(json)).andExpect(status().isOk())
		.andExpect(jsonPath("$.[*].id").value(hasItem(10)))
		.andExpect(jsonPath("$.[*].id").value(hasItem(11)))
		.andExpect(jsonPath("$.[*].id").value(hasItem(12)));
	}
	
	@Transactional
	@Rollback(true)
	@Test
	public void testDeleteRoom() throws Exception {
		mockMvc.perform(delete(URL_PREFIX + "/deleteRoom/13" )).andExpect(status().isOk());
	}
	
	@Test
	public void testCheckIfRoomReserved() throws Exception {
		MvcResult result = mockMvc.perform(get(URL_PREFIX + "/checkIfReservedRoom/10" )).andExpect(status().isOk()).andReturn();
		String resultAsString = result.getResponse().getContentAsString();
		boolean retVal = Boolean.parseBoolean(resultAsString);
		//System.out.println(retVal);
		assertThat(retVal)
	      .isEqualTo(true);
	}
}
