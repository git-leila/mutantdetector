package com.detector.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.detector.domain.Estadisticas;
import com.detector.entity.Dna;
import com.detector.service.DetectorService;



@Controller
public class DetectorRestController {
	@Autowired
	private DetectorService detectorService;
		
	/*petición POST -> cuando se llame a la url 	http://127.0.0.1:8080/mutant/  
	 * body  aplication/json {"dna":["ATGCGA","CAGTGC","TTATTT","AGACGG","GCGTCA","TCACTT"]}       */
	@PostMapping("/mutant")
	public ResponseEntity detector(@RequestBody Dna dna) {
		
		//Este metodo busca si existe mas de 1 secuencia 		
		if(detectorService.isMutant(dna)) {
			dna.setMutant(true);
			dna.setDnaToDb();
			detectorService.save(dna);
			return new ResponseEntity(HttpStatus.OK);
		}			
		else {
			dna.setMutant(false);
			dna.setDnaToDb();
			detectorService.save(dna);
			return new ResponseEntity(HttpStatus.FORBIDDEN);
		}
		
	}
	/*GET -> cuando se llame a la url 	http://127.0.0.1:8080/stats/  */
	@GetMapping("/stats")
	public @ResponseBody Estadisticas getEstaditicas() {
		
		Estadisticas s = this.detectorService.findStats();
		return s;
	}
	
	

	
}
