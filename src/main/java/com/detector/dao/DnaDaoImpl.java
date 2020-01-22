package com.detector.dao;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.detector.entity.Dna;

@Repository
public class DnaDaoImpl implements DnaDao{

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public Dna save(Dna dna) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(dna);
		return dna;
	}
	
	@Override
	public long countMutant(int isMutant) {
		Session currentSession = entityManager.unwrap(Session.class);
		long cant= (long) currentSession.createQuery("select COUNT(*) from Dna where mutant_dna=:param").setParameter("param", isMutant).getSingleResult(); 
		return cant;
	}

	
}