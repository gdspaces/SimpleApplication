package com.simple.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class EmployeeInfoInvalidRequestAdvice {

  @ResponseBody
  @ExceptionHandler(EmployeeInfoInvalidRequestException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  String tdcnetIInfoNotFoundHandler(EmployeeInfoInvalidRequestException ex) {
    return ex.getMessage();
  }
}