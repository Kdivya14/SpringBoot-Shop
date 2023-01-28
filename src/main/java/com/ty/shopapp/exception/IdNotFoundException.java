package com.ty.shopapp.exception;

public class IdNotFoundException extends Exception {

	private String msg;
		
	public IdNotFoundException(String msg) {
		super(msg);
		this.msg = msg;
	}
}
