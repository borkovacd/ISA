package com.ftn.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.dto.SobaDTO;
import com.ftn.model.hotels.Soba;
import com.ftn.repository.SobaRepository;

@Service
public class SobaService {
	
	@Autowired
	private SobaRepository sobaRepository;

	public Soba createRoom(SobaDTO sobaDTO, Long idHotela) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Soba> getAllRooms(Long idHotela) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean checkIfRoomIsReserved(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	public Soba getRoom(Long idRoom) {
		// TODO Auto-generated method stub
		return null;
	}

	public Soba editRoom(Long idRoom, SobaDTO sobaDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean deleteRoom(Long idRoom) {
		// TODO Auto-generated method stub
		return false;
	}

}
