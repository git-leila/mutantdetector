package com.detector;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.MimeTypeUtils;

import com.detector.controller.DetectorRestController;
import com.detector.domain.Estadisticas;
import com.detector.entity.Dna;
import com.detector.service.DetectorService;
import com.detector.service.DetectorServiceImpl;

import junit.framework.Assert;

@RunWith(MockitoJUnitRunner.class)
public class MutantdetectorApplicationTests {
    
    @InjectMocks
    private DetectorRestController detectorRestController;
 
    @Mock
    private Dna dataService;
    
    @Mock
    private DetectorService service;
    
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    public void testCalculateIsMutant() throws Exception {
    	  // Given
    	/*Estadisticas s = new Estadisticas(1, 1, (float) 0.5); 
    	final List<Estadisticas> mockedStats = Stream.concat(
      	      Repository.LANGUAGES.stream(),
      	      Stream.of(s)).collect(Collectors.toList());
      	  doReturn(mockedStats).when(service).findStats() ;
      	  
    	// When
    	final ResultActions result = mockMvc.perform(
    	        get("/stats")
    	            .accept(MimeTypeUtils.APPLICATION_JSON_VALUE));
    	// Then
    	  result.andExpect(status().isOk());
*/
    	  
    	  //String[] filas= {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};    	
    	//when(dataService.getDna()).thenReturn(filas);
        //Assert.assertEquals(8.0, detectorRestController.detector(dataService));
    }
    
        
}


