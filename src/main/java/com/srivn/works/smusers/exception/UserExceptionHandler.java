package com.srivn.works.smusers.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler{

	@ExceptionHandler(value = {SMException.class})
	public ResponseEntity<ExObject> handleSMEx(SMException ex){
		ExObject exObj = new ExObject();
		exObj.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		exObj.setMessage(ex.getMessage());
		return new ResponseEntity<ExObject>(exObj,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
