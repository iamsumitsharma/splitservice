package com.split.splitservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.split.splitservice.constant.Constant;
import com.split.splitservice.model.Expenditure;
import com.split.splitservice.model.Expense;
import com.split.splitservice.model.Share;
import com.split.splitservice.services.ExpenseHandlerService;

@Service
public class PercentageExpenditureService {

	@Autowired
	ExpenseHandlerService expenseHandlerService;

	public Iterable<Expense> handleExpenditure(Expenditure expenditure) {

		calculateAmount(expenditure);
		if(validateExpense(expenditure))
			return expenseHandlerService.saveExpense(expenditure);
			else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Constant.PERCENT_ERROR);
	}

	public void calculateAmount(Expenditure expenditure) {

		List<Share> splits = expenditure.getSplits(); 
		double amount = expenditure.getAmount();
		for (Share split : splits) {
			if(split.getShare()<0) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Constant.INVALID_VALUE+split.getUserId());
			}
			split.setShare((amount*split.getShare())/100.0);
		}
	}

	public boolean validateExpense(Expenditure expenditure) {
		double totalPercent = 100;
		double sharePercentTotal = 0;
		List<Share> splits = expenditure.getSplits(); 
		for (Share split : splits) {
			sharePercentTotal += split.getShare();
		}

		if (totalPercent < sharePercentTotal) {
			return false;
		}

		return true;
	}

}
