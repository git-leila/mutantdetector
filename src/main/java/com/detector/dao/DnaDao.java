package com.detector.dao;

import com.detector.entity.Dna;

public interface DnaDao {
	public Dna save(Dna dna);
	public long countMutant(int isMutant);
}