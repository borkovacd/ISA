package com.ftn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.service.SobaService;

@RestController
public class SobaController {
	
	@Autowired
	private SobaService sobaService;

}
