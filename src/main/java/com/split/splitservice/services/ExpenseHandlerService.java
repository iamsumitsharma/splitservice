package com.split.splitservice.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.split.splitservice.model.Expenditure;
import com.split.splitservice.model.Expense;
import com.split.splitservice.model.Share;
import com.split.splitservice.repository.ExpenseRepository;

@Service
public class ExpenseHandlerService {

	@Autowired
	ExpenseRepository expenseRepository;

	public Iterable<Expense> saveExpense(Expenditure expenditure) {
		List<Expense> expenseList = new ArrayList<>();
		for(Share share: expenditure.getSplits())
			handleExpense(share, expenditure, expenseList);
		return saveExpenditure(expenseList);
	}

	private void handleExpense(Share share, Expenditure expenditure, List<Expense> expenseList) {

		Expense expense = new Expense(expenditure.getType(),share.getShare(), 
				expenditure.getPaidBy(),
				share.getUserId(), expenditure.getNote());
		expenseList.add(expense);
	}

	private Iterable<Expense> saveExpenditure(List<Expense> expenseList) {
		return expenseRepository.saveAll(expenseList);
	}

}
