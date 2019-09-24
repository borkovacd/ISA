package com.ftn.controller.user;

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

import com.ftn.dto.KorisnikDTO;
import com.ftn.dto.KorisnikProfilDTO;
import com.ftn.dto.LokacijaDTO;
import com.ftn.dto.PretragaRentDTO;
import com.ftn.dto.RentCarDTO;
import com.ftn.dto.VoziloDTO;
import com.ftn.dto.VremenskiPeriodDTO;
import com.ftn.enums.TipVozila;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
	
private static final String URL_PREFIX = "/user";
	
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
	public void testCheckIfFreeUser() throws Exception {
		MvcResult result = mockMvc.perform(get(URL_PREFIX + "/checkIfFreeUser/13" )).andExpect(status().isOk()).andReturn();
		String resultAsString = result.getResponse().getContentAsString();
		boolean retVal = Boolean.parseBoolean(resultAsString);
		//System.out.println(retVal);
		assertThat(retVal)
	      .isEqualTo(true);
	}
	
	@Test
	public void testGetRegularUsers() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/getRegularUsers")).andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.[*].id").value(hasItem(13)));
	}
	
	@Test
	public void testGetHotelAdministrators() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/getHotelAdministrators")).andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.[*].id").value(hasItem(11)));
	}
	
	@Test
	public void testGetRentCarAdministrators() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/getRentCarAdministrators")).andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.[*].id").value(hasItem(12)));
	}
	
	@Test
	public void testGetAviokompanijaAdministrators() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/getAviokompanijaAdministrators")).andExpect(status().isOk())
		.andExpect(content().contentType(contentType));
	}
	
	@Test
	public void testGetAdministrators() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/getAdministrators")).andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.[*].id").value(hasItem(10)))
		.andExpect(jsonPath("$.[*].id").value(hasItem(11)))
		.andExpect(jsonPath("$.[*].id").value(hasItem(12)));
	}
	
	@Transactional
	@Rollback(true)
	@Test
	public void testEditUser() throws Exception {
		KorisnikProfilDTO dto = new KorisnikProfilDTO();
		dto.setGrad("Novi Sad");
		dto.setIme("Nemanja");
		dto.setPrezime("Vujovic");
		dto.setLozinka("vuja");
		dto.setPonovljenaLozinka("vuja");
		dto.setTelefon("066");
		
		String json = com.ftn.utils.TestUtil.json(dto);
		this.mockMvc.perform(put(URL_PREFIX + "/editUser/12").contentType(contentType).content(json)).andExpect(status().isOk());
	}
	
	@Transactional
	@Rollback(true)
	@Test
	public void testRegister() throws Exception {
		KorisnikDTO dto = new KorisnikDTO();
		dto.setGrad("Novi Sad");
		dto.setIme("Nemanja");
		dto.setPrezime("Vujovic");
		dto.setLozinka("vuja");
		dto.setTelefon("066");
		dto.setEmail("vujovicnemanja670@gmail.com");
		dto.setStatusKorisnika("prvo");
		dto.setKorisnickoIme("vuja");
		dto.setUloga("OBICAN_KORISNIK");
		
		String json = com.ftn.utils.TestUtil.json(dto);
		this.mockMvc.perform(post(URL_PREFIX + "/register").contentType(contentType).content(json)).andExpect(status().isOk());
	}
	
	@Test
	public void testVerifikujNalog() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/verifikujNalog/sloba@gmail.com" )).andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.email").value("sloba@gmail.com"));
	}
	

	

}

