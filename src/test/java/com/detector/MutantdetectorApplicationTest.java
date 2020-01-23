package com.detector;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MutantdetectorApplication.class)
@ActiveProfiles(value = "test")
public class MutantdetectorApplicationTest {

    @Test
    public void contextLoads() {
    	
    }
    
    @Test
    public void ApplicationTest()
    {
    	MutantdetectorApplication.main(new String[]{
                "--spring.main.web-environment=false",
                "--spring.autoconfigure.exclude=blahblahblah",
                // Override any other environment properties according to your needs
        });
    }
}