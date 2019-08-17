package com.ftn.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.enums.UlogaKorisnika;
import com.ftn.model.Korisnik;
import com.ftn.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	

	public List<Korisnik> getAllRegularUsers() {
		
		List<Korisnik> allUsers = userRepository.findAll();
		List<Korisnik> regularUsers = new ArrayList<Korisnik>();
		
		for(int i = 0; i < allUsers.size(); i++) {
			if(allUsers.get(i).getUloga() == UlogaKorisnika.OBICAN_KORISNIK) {
				regularUsers.add(allUsers.get(i));
			}
		}
		
		return regularUsers;
		
		
	}

}
