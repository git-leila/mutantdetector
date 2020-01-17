package com.detector.service;

import com.detector.domain.Estadisticas;
import com.detector.entity.Dna;

public interface DetectorService {

	public boolean isMutant(Dna dna);
	
	public void save(Dna dna);

	public Estadisticas findStats();

}
