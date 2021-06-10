package com.split.splitservice.model;

import java.util.List;

import lombok.Data;

@Data
public class Expenditure {

	private String id;
	private double amount;
	private int paidBy;
	private String type;
	private String note;
	private List<Share> splits;
}
