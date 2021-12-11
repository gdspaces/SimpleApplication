package com.simple.application.exception;

public class EmployeeInfoNotFoundException extends RuntimeException {

	public EmployeeInfoNotFoundException(int id) {
	    super("Could not find Employee id " + id);
	  }
	}