package com.ty.shopapp.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ty.shopapp.exception.IdNotFoundException;
import com.ty.shopapp.response.ResponseStructure;

@RestControllerAdvice
public class ExceptionHandling {

	@ExceptionHandler(value = IdNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handelIdNotFoundException(IdNotFoundException e) {
		ResponseStructure<String> responseStructure = new ResponseStructure<>();

		responseStructure.setStatusCode(HttpStatus.BAD_REQUEST.value());
		responseStructure.setMessage("Id Not found Exception handled");
		responseStructure.setData(e.getMessage());

		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.BAD_REQUEST);
	}
}
