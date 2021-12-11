package com.simple.application;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import com.simple.application.bean.EmployeeBean;
import com.simple.application.exception.EmployeeInfoInvalidRequestException;
import com.simple.application.model.EmployeeInfo;
import com.simple.application.repositories.EmployeeInfoRepository;


@Service
public class SimpleService {
	

	@Autowired
	private EmployeeInfoRepository employeeInfoRepository;



	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());



	public String create(EmployeeBean reqBean) {
		if(!isValidEmail(reqBean.getEmail()))
			throw new EmployeeInfoInvalidRequestException("Invalid Email Address : "+reqBean.getEmail());
		EmployeeInfo e = new EmployeeInfo();
		e.setName(reqBean.getName());
		e.setDob(reqBean.getDob());
		e.setEmail(reqBean.getEmail());
		e.setMobile(reqBean.getMobile());
		EmployeeInfo a = employeeInfoRepository.save(e);
		
		return "Created Sucessfully. Employee ID is : "+a.getId();
	}



	private boolean isValidEmail(String email) {
		String regex = "^(.+)@(.+)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		if(matcher.matches())
			return true;
			
		return false;
	}




	public String update(EmployeeBean reqBean, int id) {
		// TODO Auto-generated method stub
		Optional<EmployeeInfo> e_op = employeeInfoRepository.findById(id);
		if(!e_op.isPresent())
			throw new EmployeeInfoInvalidRequestException("Epmloyee id "+id+" not present in the system.");
		EmployeeInfo e = e_op.get();
		
		if(!isValidEmail(reqBean.getEmail()))
			throw new EmployeeInfoInvalidRequestException("Invalid Email Address : "+reqBean.getEmail());
		if(reqBean.getName()!=null)
			e.setName(reqBean.getName());
		if(reqBean.getDob()!=null)
			e.setDob(reqBean.getDob());
		if(reqBean.getEmail()!=null)
			e.setEmail(reqBean.getEmail());
		if(reqBean.getMobile()!=null)
			e.setMobile(reqBean.getMobile());
		employeeInfoRepository.save(e);
		
		return "Updated Sucessfully";
	}
	




}
