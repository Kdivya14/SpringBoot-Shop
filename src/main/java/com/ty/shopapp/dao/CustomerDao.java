package com.ty.shopapp.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.shopapp.dto.Customer;
import com.ty.shopapp.repository.CustomerRepository;

@Repository
public class CustomerDao {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public Customer saveCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	public Customer searchCustomer(int customerId) {
		Optional<Customer> optional = customerRepository.findById(customerId);
		if(optional.isPresent())
			return optional.get();
		return null;
	}
}
