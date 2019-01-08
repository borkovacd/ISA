package isa.proj.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import isa.proj.model.Hotel;
import isa.proj.service.HotelService;



@RestController
public class HotelController {
	
	//@Autowired means that it need dependency injection
	@Autowired
	private HotelService hotelService;
	
	@RequestMapping(method=RequestMethod.POST, value="/dodajHotel")
	public @ResponseBody ResponseEntity<Hotel> dodajHotel(@RequestBody Hotel hotel, HttpServletRequest request) {
		
		hotelService.addHotel(hotel);
		return new ResponseEntity<>(HttpStatus.OK);
		//return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		/*RestoraniDAO rd = (RestoraniDAO) ctx.getAttribute("restoraniDAO");
		
		if(rd.checkBeforeAdd(r.stringId())) {
			r.setId(r.stringId());
			rd.addRestaurant(r);
			DataDAO dd = (DataDAO) ctx.getAttribute("dataDAO");
			dd.saveData();
			return Response.status(200).build();
		}
		return Response.status(400).build();*/
		
		}
	
	@RequestMapping(method=RequestMethod.GET, value="/hoteli", produces="application/json", consumes="application/json")
	public List<Hotel> getAllHotels() {
		return hotelService.getAllHotels();
	}
	
	/*@RequestMapping("/hotels")
	public List<Hotel> getAllHotels() {
		return hotelService.getAllHotels();
	}*/
	
	@RequestMapping("/hotels/{id}")
	public Hotel getHotel(@PathVariable Integer id) {
		return hotelService.getHotel(id);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/hotels/{id}")
	public void updateHotel(@RequestBody Hotel hotel, @PathVariable Integer id) {  
		hotelService.updateHotel(id,hotel);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/hotels")
	public void addTopic(@RequestBody Hotel hotel) {  //hey string pick this instance from request body
		hotelService.addHotel(hotel);
		
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/hotels/{id}")
	public void deleteHotel(@RequestBody Hotel hotel, @PathVariable Integer id) {  //names like deleteHotel here don't matter
		hotelService.deleteHotel(id,hotel);
		
	}
	
	
	
	

}
