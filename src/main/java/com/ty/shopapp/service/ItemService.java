package com.ty.shopapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ty.shopapp.dao.ItemDao;
import com.ty.shopapp.dto.Item;

@Service
public class ItemService {

	@Autowired
	private ItemDao itemDao;

	public Item saveItem(Item item) {
		double totalPrice = item.getPrice() * item.getQuantity();
		item.setTotalPrice(totalPrice);
		return itemDao.saveItem(item);
	}
	
	
	

}
