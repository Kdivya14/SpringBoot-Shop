package com.ty.shopapp.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.shopapp.dto.BillCollector;
import com.ty.shopapp.repository.BillCollectorRepository;

@Repository
public class BillCollectorDao {
	
	@Autowired
	private BillCollectorRepository billCollectorRepository;

	public BillCollector saveBillCollector(BillCollector billCollector) {
		return billCollectorRepository.save(billCollector);
	}

	public BillCollector searchBillCollector(String billerId) {
		Optional<BillCollector> optional = billCollectorRepository.findById(billerId);
		if(optional.isPresent())
			return optional.get();
		return null;
	}

}
