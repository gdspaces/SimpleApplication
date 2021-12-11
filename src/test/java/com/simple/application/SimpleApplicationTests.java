package com.simple.application;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.simple.application.SimpleApplication;
import com.simple.application.bean.EmployeeBean;
import com.simple.application.model.EmployeeInfo;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class SimpleApplicationTests {
	
    @Autowired
    private TestRestTemplate restTemplate;
    
    @LocalServerPort
    int randomServerPort;
    
	private EmployeeBean createEmployee(String name,String email) {
		EmployeeBean req = new EmployeeBean();
		req.setName(name);
		req.setEmail(email);
		req.setDob(new Date());
		req.setMobile("1111111");
		
		return req;
	}
	

	@Sql({ "classpath:schema.sql" })
	@Test
	public void testCreateEmployee() throws URISyntaxException {
		EmployeeBean req = createEmployee("Test Tester1","test@xyz.com");
		
        final String baseUrl = "http://localhost:"+randomServerPort+"/employee/";
        URI uri = new URI(baseUrl);
        
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");      

        HttpEntity<EmployeeBean> request = new HttpEntity<>(req, headers);
        
        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);
        
        //Verify request succeed
        Assert.assertEquals(201, result.getStatusCodeValue());
        Assert.assertTrue(result.getBody().contains("Created Sucessfully. Employee ID is"));
       
	}
	


	@Test
	public void testCreateEmployeeWithWorngEmail() throws URISyntaxException {
		EmployeeBean req = createEmployee("Test Tester2","test.com");
		
        final String baseUrl = "http://localhost:"+randomServerPort+"/employee/";
        URI uri = new URI(baseUrl);
        
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");      

        HttpEntity<EmployeeBean> request = new HttpEntity<>(req, headers);
        
        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);
        
        //Verify request succeed
        Assert.assertEquals(400, result.getStatusCodeValue());
        Assert.assertEquals("Invalid Email Address : test.com",result.getBody());

	}
	
	
	@Test
	public void getEmployee() throws URISyntaxException {
		
		EmployeeBean req = createEmployee("Test Tester3","test@xyz.com");
		
        final String baseUrl = "http://localhost:"+randomServerPort+"/employee/";
        URI uri = new URI(baseUrl);
        
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");      

        HttpEntity<EmployeeBean> request = new HttpEntity<>(req, headers);
        
        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);
        
        //Verify request succeed
        Assert.assertEquals(201, result.getStatusCodeValue());
        Assert.assertTrue(result.getBody().contains("Created Sucessfully. Employee ID is"));
        
        String id= result.getBody().replace("Created Sucessfully. Employee ID is : ", "");
        System.out.println("ID :::: "+id);
        
        final String basegetUrl = "http://localhost:"+randomServerPort+"/employee/"+id;
        uri = new URI(basegetUrl);
        
        headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");      

        ResponseEntity<EmployeeInfo> getResult = this.restTemplate.getForEntity(uri, EmployeeInfo.class);
         
        //Verify request succeed
        Assert.assertEquals(200, getResult.getStatusCodeValue());
        Assert.assertEquals("Test Tester3",getResult.getBody().getName());
        Assert.assertEquals("test@xyz.com",getResult.getBody().getEmail());

	}
	
	@Test
	public void testUpadteEmployee() throws URISyntaxException {
		
		EmployeeBean req = createEmployee("Test Tester4","test@xyz.com");
		
        final String baseUrl = "http://localhost:"+randomServerPort+"/employee/";
        URI uri = new URI(baseUrl);
        
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");      

        HttpEntity<EmployeeBean> request = new HttpEntity<>(req, headers);
        
        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);
        
        //Verify request succeed
        Assert.assertEquals(201, result.getStatusCodeValue());
        Assert.assertTrue(result.getBody().contains("Created Sucessfully. Employee ID is"));
        
        String id= result.getBody().replace("Created Sucessfully. Employee ID is : ", "");
        
        final String basegetUrl = "http://localhost:"+randomServerPort+"/employee/"+id;
        uri = new URI(basegetUrl);
        
        headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");      

        ResponseEntity<EmployeeInfo> getResult = this.restTemplate.getForEntity(uri, EmployeeInfo.class);
         
        //Verify request succeed
        Assert.assertEquals(200, getResult.getStatusCodeValue());
        Assert.assertEquals("Test Tester4",getResult.getBody().getName());
        Assert.assertEquals("test@xyz.com",getResult.getBody().getEmail());
        
		
		
		
        req.setEmail("abc@xyz.com");
		
		
        final String baseupdateUrl = "http://localhost:"+randomServerPort+"/employee/"+id;

        headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true"); 
        
	    HttpEntity<EmployeeBean> requestEntity = new HttpEntity<>(req, headers);
	    ResponseEntity<String> updateResult = restTemplate.exchange(baseupdateUrl, HttpMethod.PUT, requestEntity, String.class);

        Assert.assertEquals(200, updateResult.getStatusCodeValue());
        Assert.assertEquals("Updated Sucessfully", updateResult.getBody());

    
        uri = new URI(basegetUrl);
        
        headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");      

        getResult = this.restTemplate.getForEntity(uri, EmployeeInfo.class);
         
        //Verify request succeed
        Assert.assertEquals(200, getResult.getStatusCodeValue());
        Assert.assertEquals("Test Tester4",getResult.getBody().getName());
        Assert.assertEquals("abc@xyz.com",getResult.getBody().getEmail());
        

	}
	
	

}
