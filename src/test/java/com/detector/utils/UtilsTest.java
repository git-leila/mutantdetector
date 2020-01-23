package com.detector.utils;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.detector.MutantdetectorApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MutantdetectorApplication.class)
@ActiveProfiles(value = "test")
public class UtilsTest {


	@Value(value = "classpath:static/response/fileNotFound.json")
	private Resource fileNotFound;
	
    @Test
    public void ResourceReaderTest()
    {
    	try {
    		ResourceReader.asString(fileNotFound);
    	}catch (Exception e) {
			assertTrue(e.getMessage().contains("cannot be opened because it does not exist"));
		}
    }
}