package com.detector;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestBody;

import com.detector.dao.DnaDao;
import com.detector.dao.DnaDaoImpl;
import com.detector.domain.Estadisticas;
import com.detector.entity.Dna;
import com.detector.service.DetectorService;
import com.detector.service.DetectorServiceImpl;
import com.detector.utils.ResourceReader;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.Mockito.*;

import java.io.File;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.assertj.core.api.AbstractCharSequenceAssert;
import org.hibernate.Session;
import org.hibernate.query.Query;

import static org.mockito.ArgumentMatchers.any;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles(value = "local")
public class DetectorRestControllerTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;
	
	/*@MockBean
	private Query query;
	
	@Autowired
	private EntityManager entityManager;*/
	
	@MockBean
	private DnaDao dnaDAO;
	
	@SpyBean 
	private DetectorServiceImpl serviceDetector;
	
	@Value(value = "classpath:static/response/stats.json")
	private Resource responseStats;

	@Value(value = "src\\main\\resources\\static\\request\\dna.json")
	private String requestDna;
	
	@Transactional
	@Test
	public void statsTest() throws Exception {
		/*
		Session currentSession = entityManager.unwrap(Session.class);
		query= currentSession.createQuery("select COUNT(*) from Dna where mutant_dna=:param");
		when(query.setParameter("param", 1).getSingleResult()).thenReturn(40L);		
		when(query.setParameter("param", 0).getSingleResult()).thenReturn(100L);
		 */		
		when(dnaDAO.countMutant(1)).thenReturn(40L);
		when(dnaDAO.countMutant(0)).thenReturn(100L); 
		String jsonEsperado = ResourceReader.asString(responseStats);
		String resultPing =this.restTemplate.getForObject("http://localhost:" + port + "/stats",	String.class);		
		assertThat(resultPing).contains(jsonEsperado);
	}
	

	@Test
	public void isMutantTest() throws Exception {		
			
		ObjectMapper mapper = new ObjectMapper();
		//JSON file to Java object
		Dna data = mapper.readValue(new File(requestDna), Dna.class);		
		
		when(serviceDetector.isMutant(data)).thenReturn(true);
		data.setMutant(true);
		data.setDnaToDb();		
		when(dnaDAO.save(any(Dna.class))).thenReturn(data);
		 
		ResponseEntity<String>  resultPing =this.restTemplate.postForEntity("http://localhost:" + port + "/mutant",	data, String.class);
		
		assertThat(resultPing.getStatusCode()).isEqualTo(HttpStatus.OK);
		//assertThat(resultPing.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
	}
}
