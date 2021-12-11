package com.simple.application.exception;

public class EmployeeInfoInvalidRequestException extends RuntimeException {

	public EmployeeInfoInvalidRequestException(String msg) {
	    super(msg);
	  }
	}