package com.ftn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.service.RezervacijaHotelaService;

@RestController
public class RezervacijaHotelaController {
	
	@Autowired
	private RezervacijaHotelaService rezervacijaHotelaService;

}
