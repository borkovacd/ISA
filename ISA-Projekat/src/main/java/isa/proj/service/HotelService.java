package isa.proj.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.proj.model.Hotel;
import isa.proj.repository.HotelRepository;

@Service
public class HotelService {
	
	@Autowired
	private HotelRepository hotelRepository;
	
	public Hotel getHotel(Integer id) {
		return hotelRepository.findById(id).orElse(null);
	}

	public List<Hotel> getAllHotels() {
		List<Hotel> hotels = new ArrayList<Hotel>();
		
		//for each of the elements in the iterable, calling add method in hotels and passing that element
		hotelRepository.findAll()
		.forEach(hotels::add); //method reference
	
		return hotels;
	}
	
	public void addHotel(Hotel hotel) {
		hotelRepository.save(hotel);
	}
	

	public void updateHotel(Hotel hotel) {
		hotelRepository.save(hotel);
	}

	public void deleteHotel(Integer id) {
		hotelRepository.deleteById(id);
	}
	
}
