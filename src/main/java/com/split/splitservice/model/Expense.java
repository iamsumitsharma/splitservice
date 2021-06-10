package com.split.splitservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name="expense")
@Entity 
public class Expense {

	public Expense() {
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private String id;
	@Column
	private String type;
	@Column
	private double amount;
	@Column
	private int paidBy;
	@Column
	private int paidTo;
	@Column
	private String note;

	public Expense(String type, double amount, int paidBy, int paidTo, String note) {
		this.type = type;
		this.amount = amount;
		this.paidBy = paidBy;
		this.paidTo = paidTo;
		this.note = note;
	}
}
