package com.ty.shopapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ty.shopapp.dto.Customer;
import com.ty.shopapp.response.ResponseStructure;
import com.ty.shopapp.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping("/customer/{billerId}")
	public ResponseEntity<ResponseStructure<Customer>> saveCustomer(@RequestBody Customer customer,
			@PathVariable("billerId") String billerId) {
		Customer savedCustomer = customerService.saveCustomer(customer,billerId);
		ResponseStructure<Customer> responseStructure = new ResponseStructure<>();
		if (savedCustomer != null) {
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("Customer Added");
			responseStructure.setData(savedCustomer);

			ResponseEntity<ResponseStructure<Customer>> responseEntity = new ResponseEntity<ResponseStructure<Customer>>(
					responseStructure, HttpStatus.CREATED);
			return responseEntity;
		} else {
			responseStructure.setStatusCode(HttpStatus.BAD_REQUEST.value());
			responseStructure.setMessage("Customer NOT Added");
			responseStructure.setData(null);

			ResponseEntity<ResponseStructure<Customer>> responseEntity = new ResponseEntity<ResponseStructure<Customer>>(
					responseStructure, HttpStatus.BAD_REQUEST);
			return responseEntity;
		}
	}
}
