package com.ty.shopapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ty.shopapp.dao.CustomerDao;
import com.ty.shopapp.dto.BillCollector;
import com.ty.shopapp.dto.Customer;

@Service
public class CustomerService {

	@Autowired
	private CustomerDao customerDao;

	@Autowired
	private BillCollectorService billCollectorService;

	public Customer saveCustomer(Customer customer, String billerId) {
		BillCollector searchedBillCollector = billCollectorService.searchBillCollector(billerId);
		if (searchedBillCollector != null) {
			customer.setBillCollector(searchedBillCollector);
			return customerDao.saveCustomer(customer);
		} else
			return null;
	}

	public Customer searchCustomer(int customerId) {
		return customerDao.searchCustomer(customerId);
	}

}
