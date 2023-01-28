package com.ty.shopapp.dto;

import java.time.LocalDate;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="bill_collector")
public class BillCollector {

	@Id
	@GenericGenerator(name="billcollectorIdGenerator", strategy = "com.ty.shopapp.dao.BillCollectorIdGenerator")
	@GeneratedValue(generator = "billcollectorIdGenerator")
	@Column(name="bill_collector_id")
	private String billCollectorId;
	@Column(name="name")
	private String name;
	@Column(name="phone_number")
	private long phoneNumber;
	@Column(name="gender")
	private String gender;
	@Column(name="date_of_birth")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate dateOfBirth;
	@Column(name="password")
	private String password;

	public String getBillCollectorId() {
		return billCollectorId;
	}

	public void setBillCollectorId(String billCollectorId) {
		this.billCollectorId = billCollectorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
