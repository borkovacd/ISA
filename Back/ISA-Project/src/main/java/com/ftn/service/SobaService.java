package com.ftn.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.dto.SobaDTO;
import com.ftn.enums.TipSobe;
import com.ftn.model.hotels.Hotel;
import com.ftn.model.hotels.RezervacijaHotela;
import com.ftn.model.hotels.Soba;
import com.ftn.repository.HotelRepository;
import com.ftn.repository.RezervacijaHotelaRepository;
import com.ftn.repository.SobaRepository;

@Service
public class SobaService {
	
	@Autowired
	private SobaRepository sobaRepository;
	@Autowired
	private HotelRepository hotelRepository;
	@Autowired
	private RezervacijaHotelaRepository rezervacijaHotelaRepository;

	public Soba createRoom(SobaDTO sobaDTO, Long idHotela) {
		Soba soba = new Soba();
		soba.setKapacitet(sobaDTO.getCapacity());
		soba.setSprat(sobaDTO.getFloor());
		if(sobaDTO.getRoomType().equals("JEDNOKREVETNA_SOBA")) 
			soba.setTipSobe(TipSobe.JEDNOKREVETNA_SOBA);
		else if(sobaDTO.getRoomType().equals("DVOKREVETNA_SOBA")) 
			soba.setTipSobe(TipSobe.DVOKREVETNA_SOBA);
		else if(sobaDTO.getRoomType().equals("TROKREVETNA_SOBA")) 
			soba.setTipSobe(TipSobe.TROKREVETNA_SOBA);
		else if(sobaDTO.getRoomType().equals("APARTMAN")) 
			soba.setTipSobe(TipSobe.APARTMAN);
		else if(sobaDTO.getRoomType().equals("STUDIO")) 
			soba.setTipSobe(TipSobe.STUDIO);
		else if(sobaDTO.getRoomType().equals("SUITE")) 
			soba.setTipSobe(TipSobe.SUITE);
		else if(sobaDTO.getRoomType().equals("FAMILY_ROOM")) 
			soba.setTipSobe(TipSobe.FAMILY_ROOM);
		soba.setImaBalkon(sobaDTO.isHasBalcony());
		Hotel hotel = hotelRepository.getOne(idHotela);
		soba.setHotel(hotel);
		sobaRepository.save(soba);
		return soba;
	}

	public ArrayList<Soba> getAllRooms(Long idHotela) {
		ArrayList<Soba> sobe = new ArrayList<Soba>();
		ArrayList<Soba> sveSobe = (ArrayList<Soba>) sobaRepository.findAll();
		Hotel hotel = hotelRepository.getOne(idHotela);
		if(hotel == null) {
			return sobe;
		} else {
			for(Soba soba : sveSobe) {
				if(soba.getHotel().getId() == idHotela) {
					sobe.add(soba);
				}
			}
		}
		return sobe;
	}

	public boolean checkIfRoomIsReserved(Long id) {
		boolean taken = false;
		List<RezervacijaHotela> rezervacije = rezervacijaHotelaRepository.findAll();
		for(RezervacijaHotela rezervacija : rezervacije) {
			for(Soba rezervisanaSoba: rezervacija.getSobe()) { //izmena u odnosu na xml, moze se rezervisati vise soba
				if(rezervisanaSoba.getId() == id) {
					taken = true;
				}
			}
		}
		return taken;
	}

	public Soba getRoom(Long idRoom) {
		Soba soba = sobaRepository.getOne(idRoom);
		return soba;
	}

	public Soba editRoom(Long idRoom, SobaDTO sobaDTO) {
		Soba soba = sobaRepository.getOne(idRoom);
		soba.setKapacitet(sobaDTO.getCapacity());
		soba.setSprat(sobaDTO.getFloor());
		if(sobaDTO.getRoomType().equals("JEDNOKREVETNA_SOBA")) 
			soba.setTipSobe(TipSobe.JEDNOKREVETNA_SOBA);
		else if(sobaDTO.getRoomType().equals("DVOKREVETNA_SOBA")) 
			soba.setTipSobe(TipSobe.DVOKREVETNA_SOBA);
		else if(sobaDTO.getRoomType().equals("TROKREVETNA_SOBA")) 
			soba.setTipSobe(TipSobe.TROKREVETNA_SOBA);
		else if(sobaDTO.getRoomType().equals("APARTMAN")) 
			soba.setTipSobe(TipSobe.APARTMAN);
		else if(sobaDTO.getRoomType().equals("STUDIO")) 
			soba.setTipSobe(TipSobe.STUDIO);
		else if(sobaDTO.getRoomType().equals("SUITE")) 
			soba.setTipSobe(TipSobe.SUITE);
		else if(sobaDTO.getRoomType().equals("FAMILY_ROOM")) 
			soba.setTipSobe(TipSobe.FAMILY_ROOM);
		soba.setImaBalkon(sobaDTO.isHasBalcony());
		soba.setRezervisana(false);
		sobaRepository.save(soba);
		return soba;
	}

	public boolean deleteRoom(Long idRoom) {
		sobaRepository.deleteById(idRoom);
		return true;
	}

}
