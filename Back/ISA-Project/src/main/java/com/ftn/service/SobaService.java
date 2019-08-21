package com.ftn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.repository.SobaRepository;

@Service
public class SobaService {
	
	@Autowired
	private SobaRepository sobaRepository;

}
