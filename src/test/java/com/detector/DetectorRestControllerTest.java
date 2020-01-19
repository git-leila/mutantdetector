package com.detector;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.detector.dao.DnaDao;
import com.detector.domain.Estadisticas;
import com.detector.service.DetectorService;
import com.detector.utils.ResourceReader;

import static org.mockito.Mockito.*;
import org.springframework.boot.test.mock.mockito.MockBean;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles(value = "local")
public class DetectorRestControllerTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;
	
	@MockBean
	private DnaDao dnaDAO;
	
	@Value(value = "classpath:static/response/stats.json")
	private Resource responseStats;

	@Test
	public void statsTest() throws Exception {
		Estadisticas e = new Estadisticas(6, 8, 0);
		

		when(dnaDAO.countMutant(1)).thenReturn(1L);
		when(dnaDAO.countMutant(0)).thenReturn(0L); 
		String jsonEsperado = ResourceReader.asString(responseStats);
		String resultPing =this.restTemplate.getForObject("http://localhost:" + port + "/stats",	String.class);		
		assertThat(resultPing).contains(jsonEsperado);
	}
}
