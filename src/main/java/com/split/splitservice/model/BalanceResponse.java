package com.split.splitservice.model;

import lombok.Data;

@Data
public class BalanceResponse {

	private double amount;
	private String message;
	public BalanceResponse(double amount, String message) {
		super();
		this.amount = amount;
		this.message = message;
	}
}
