package com.detector.dao;

import com.detector.entity.Dna;

public interface DnaDao {
	public void save(Dna dna);
	public long countMutant(int isMutant);
}