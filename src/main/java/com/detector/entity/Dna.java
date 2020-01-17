package com.detector.entity;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="dna")
public class Dna {	


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_dna")
	private int id;
	@Column(name="secuencia_dna")
	private String bd_dna;
	@Transient
	private String[] dna;
	@Column(name="mutant_dna")
	private boolean mutant;

	public Dna() {
    }

    public Dna(int id, String[] filas, boolean mutant) {
    	this.id = id;    	
        this.dna=filas;
        this.mutant=mutant;
        this.bd_dna= Arrays.toString(this.dna);
    }
    
	public void setDnaToDb() {
		this.bd_dna= Arrays.toString(this.dna);
	}
	
	public String getBd_dna() {
		return bd_dna;
	}

	public void setBd_dna(String bd_dna) {
		this.bd_dna = bd_dna;
	}

	public String[] getDna() {
		return dna;
	}

	public void setDna(String[] dna) {
		this.dna = dna;
	}
	
	public int length() {
		return this.dna.length;
	}
	
	public String getFila(int fila) {
		return this.dna[fila];
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isMutant() {
		return mutant;
	}

	public void setMutant(boolean mutant) {
		this.mutant = mutant;
	}
}
