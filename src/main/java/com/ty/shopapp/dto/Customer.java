package com.ty.shopapp.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer_table")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id")
	private int customerId;
	@Column(name = "name")
	private String name;
	@Column(name = "mobile_number")
	private long mobileNumber;

	@OneToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "bill_collector_id")
	private BillCollector billCollector;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<Item> items;

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public BillCollector getBillCollector() {
		return billCollector;
	}

	public void setBillCollector(BillCollector billCollector) {
		this.billCollector = billCollector;
	}

	public List<Item> getItems() {
		return items;
	}

	@JsonIgnore
	public void setItems(List<Item> items) {
		this.items = items;
	}

}
