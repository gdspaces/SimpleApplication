package com.simple.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class EmployeeInfoNotFoundAdvice {

  @ResponseBody
  @ExceptionHandler(EmployeeInfoNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String tdcnetIInfoNotFoundHandler(EmployeeInfoNotFoundException ex) {
    return ex.getMessage();
  }
}