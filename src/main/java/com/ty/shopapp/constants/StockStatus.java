package com.ty.shopapp.constants;

public enum StockStatus {
	DEAD_STOCK("Dead stock"),
	MODERATE("Moderate"),
	HIGH("High");
	
	public String value;
	private StockStatus(String value) {
		this.value = value;
	}
}
