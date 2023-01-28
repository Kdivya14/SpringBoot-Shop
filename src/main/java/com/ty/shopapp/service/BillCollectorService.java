package com.ty.shopapp.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ty.shopapp.dao.BillCollectorDao;
import com.ty.shopapp.dao.CustomerDao;
import com.ty.shopapp.dto.BillCollector;
import com.ty.shopapp.dto.Customer;
import com.ty.shopapp.dto.Item;
import com.ty.shopapp.exception.IdNotFoundException;

@Service
public class BillCollectorService {

	@Autowired
	private BillCollectorDao billCollectorDao;

	@Autowired
	private CustomerDao customerDao;

	public BillCollector saveBillCollector(BillCollector billCollector) {
		String name = billCollector.getName();
		long phoneNum = billCollector.getPhoneNumber();
		LocalDate dob = billCollector.getDateOfBirth();
		int last4Digits = (int) (phoneNum % 10000);
		int yob = dob.getYear();
		String password = name + "@" + last4Digits + "$" + yob;
		billCollector.setPassword(password);
		return billCollectorDao.saveBillCollector(billCollector);
	}

	public BillCollector searchBillCollector(String billerId) {
		return billCollectorDao.searchBillCollector(billerId);
	}

	public double genrateBill(int customerId) throws IdNotFoundException {
		Customer searchedCustomer = customerDao.searchCustomer(customerId);
		if (searchedCustomer != null) {
			List<Item> items = searchedCustomer.getItems();
			double total = 0.0;
			for (Item item : items) {
				total += item.getTotalPrice();
			}
			return total;
		} else {
			throw new IdNotFoundException("Customer Id Not Present");
		}
	}

}
