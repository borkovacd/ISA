package com.ftn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.repository.RezervacijaHotelaRepository;

@Service
public class RezervacijaHotelaService {
	
	@Autowired
	private RezervacijaHotelaRepository rezervacijaHotelaRepository;

}
