package com.ty.shopapp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.shopapp.dto.Item;
import com.ty.shopapp.repository.ItemRepository;


@Repository
public class ItemDao {
	
	@Autowired
	private ItemRepository itemRepository;

	public Item saveItem(Item item) {
		return itemRepository.save(item);
	}
	
	
}
