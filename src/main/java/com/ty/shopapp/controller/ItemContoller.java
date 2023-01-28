package com.ty.shopapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ty.shopapp.dto.BillCollector;
import com.ty.shopapp.dto.Customer;
import com.ty.shopapp.dto.Item;
import com.ty.shopapp.response.ResponseStructure;
import com.ty.shopapp.service.BillCollectorService;
import com.ty.shopapp.service.CustomerService;
import com.ty.shopapp.service.ItemService;

@RestController
public class ItemContoller {
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private BillCollectorService billCollectorService;
	
	@Autowired
	private CustomerService customerService;

	@PostMapping("/item/{customerId}/{billerId}")
	public ResponseEntity<ResponseStructure<Item>> saveItem(@RequestBody Item item, @PathVariable("customerId") int customerId,
			@PathVariable("billerId") String billerId) {
		BillCollector searchedBillCollector = billCollectorService.searchBillCollector(billerId);
		Customer searchedCustomer = customerService.searchCustomer(customerId);
		if(searchedCustomer != null) { 
			if(searchedBillCollector != null) {
				item.setCustomer(searchedCustomer);
				Item savedItem = itemService.saveItem(item);
				if(savedItem != null) {
					ResponseStructure<Item> responseStructure = new ResponseStructure<>();
					responseStructure.setStatusCode(HttpStatus.CREATED.value());
					responseStructure.setMessage("Items Added");
					responseStructure.setData(savedItem);
					
					return new ResponseEntity<ResponseStructure<Item>>(responseStructure,HttpStatus.CREATED);
				} else {
					ResponseStructure<Item> responseStructure = new ResponseStructure<>();
					responseStructure.setStatusCode(HttpStatus.BAD_REQUEST.value());
					responseStructure.setMessage("Items NOT Added");
					responseStructure.setData(null);
					
					return new ResponseEntity<ResponseStructure<Item>>(responseStructure,HttpStatus.BAD_REQUEST);
				}
			} else {
				ResponseStructure<Item> responseStructure = new ResponseStructure<>();
				responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
				responseStructure.setMessage("Bill collector NOT Found");
				responseStructure.setData(null);
				
				return new ResponseEntity<ResponseStructure<Item>>(responseStructure,HttpStatus.NOT_FOUND);
			}
		}
		else {
			ResponseStructure<Item> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("Customer NOT Found");
			responseStructure.setData(null);
			
			return new ResponseEntity<ResponseStructure<Item>>(responseStructure,HttpStatus.NOT_FOUND);
		}
	}
}
