package com.ty.shopapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.shopapp.dto.Item;

public interface ItemRepository extends JpaRepository<Item, Integer>{

}
