package com.detector.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.detector.entity.Dna;
import com.detector.utils.ResourceReader;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles(value = "test")
public class DetectorRestControllerTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Value(value = "classpath:static/response/stats.json")
	private Resource responseStats;

	@Value(value = "classpath:static/request/dna_mutant.json")
	private Resource requestDnaMutant;
	
	@Value(value = "classpath:static/request/dna_no_mutant.json")
	private Resource requestDnaNoMutant;
	
	@Transactional
	@Test
	public void statsTest() throws Exception {
		String jsonEsperado = ResourceReader.asString(responseStats);
		String resultPing =this.restTemplate.getForObject("http://localhost:" + port + "/stats",	String.class);		
		assertThat(resultPing).contains(jsonEsperado);
	}

	@Test
	@Rollback(true)
	public void isMutantTest() throws Exception {		
		ObjectMapper mapper = new ObjectMapper();
		//JSON file to Java object
		Dna data = mapper.readValue(requestDnaMutant.getInputStream(), Dna.class);		
		ResponseEntity<String>  resultPing =this.restTemplate.postForEntity("http://localhost:" + port + "/mutant",	data, String.class);
		assertThat(resultPing.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
	
	@Test
	public void isMutantFalseTest() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		//JSON file to Java object
		Dna data = mapper.readValue(requestDnaNoMutant.getInputStream(), Dna.class);		
		ResponseEntity<String>  resultPing =this.restTemplate.postForEntity("http://localhost:" + port + "/mutant",	data, String.class);
		assertThat(resultPing.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
	}
}
