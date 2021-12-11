package com.simple.application.model;

import java.util.Date;
import java.util.List;







import javax.persistence.*;

import java.util.Date;

import javax.persistence.EmbeddedId;

@Entity
@Table(name = "Employee") 
public class EmployeeInfo {
	
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_id_gen")
    @SequenceGenerator(name="employee_id_gen", sequenceName="EMPLOYEE_ID_SEQ", allocationSize=1)
	private int id;
	
	String name;
	Date dob;
	String mobile;
	String email;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public EmployeeInfo(int id, String name, Date dob, String mobile,
			String email) {
		super();
		this.id = id;
		this.name = name;
		this.dob = dob;
		this.mobile = mobile;
		this.email = email;
	}
	
	public EmployeeInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	


	
	

}
