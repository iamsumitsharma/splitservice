package com.split.splitservice.services;

import java.util.List;

import com.split.splitservice.model.Expenditure;
import com.split.splitservice.model.Expense;
import com.split.splitservice.model.Share;

public interface ExpenseOperation {

	public void calculateAmount(Expenditure expenditure);
//	public void handleExpense(Share share, Expenditure expenditure, List<Expense> expenseList);
//	public void saveExpenditure(List<Expense> expense);
}
