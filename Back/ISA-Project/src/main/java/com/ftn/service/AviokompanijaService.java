package com.ftn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.dto.AviokompanijaDTO;
import com.ftn.repository.AviokompanijaRepository;

@Service
public class AviokompanijaService {

	@Autowired
	private AviokompanijaRepository aviokompanijaRepository;

	public boolean registerAviokompanija(AviokompanijaDTO aviokompanijaDTO) {
		
		return false;
	}
}
