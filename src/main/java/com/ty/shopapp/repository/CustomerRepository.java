package com.ty.shopapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.shopapp.dto.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
