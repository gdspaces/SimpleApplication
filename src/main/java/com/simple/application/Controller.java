package com.simple.application;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.simple.application.bean.EmployeeBean;
import com.simple.application.exception.EmployeeInfoInvalidRequestException;
import com.simple.application.exception.EmployeeInfoNotFoundException;
import com.simple.application.model.EmployeeInfo;
import com.simple.application.repositories.EmployeeInfoRepository;


@RestController
public class Controller {


	@Autowired
	private SimpleService service;
	
	@Autowired
	private EmployeeInfoRepository employeeRepository;
	
	
	@PostMapping("/employee")
	@ResponseBody
	public ResponseEntity<String> createOutageInformation(@RequestBody EmployeeBean reqBean) {

		return  new ResponseEntity<>(service.create(reqBean),HttpStatus.CREATED);

	}


	@GetMapping("/employee/{id}")
	EmployeeInfo one(@PathVariable int id) {

		return employeeRepository.findById(id)
				.orElseThrow (() -> new EmployeeInfoNotFoundException(id));
	}

	@PutMapping("/employee/{id}")
	@ResponseBody
	public ResponseEntity<String> updateOutageInformation(@RequestBody EmployeeBean reqBean, @PathVariable int id) {

		return new ResponseEntity<>(service.update(reqBean,id),HttpStatus.OK);
	}

	@PatchMapping("/employee/{id}")
	@ResponseBody
	public ResponseEntity<String> patchOutageInformation(@RequestBody EmployeeBean reqBean, @PathVariable int id) {

		return new ResponseEntity<>(service.update(reqBean,id),HttpStatus.OK);
	}


}
