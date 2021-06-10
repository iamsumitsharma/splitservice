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
public class EqualExpenditureService {

	@Autowired
	ExpenseHandlerService expenseHandlerService;
	public Iterable<Expense> handleExpenditure(Expenditure expenditure) {
	
		calculateAmount(expenditure);
		return expenseHandlerService.saveExpense(expenditure);
	}

	public void calculateAmount(Expenditure expenditure) {

		int totalSplits = expenditure.getSplits().size() + 1;
		List<Share> splits = expenditure.getSplits(); 
		double amount = expenditure.getAmount();
		double splitAmount = ((double) Math.round(amount*100/totalSplits))/100.0;
		for (Share split : splits) {
			if(split.getShare()<0) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Constant.INVALID_VALUE+split.getUserId());
			}
			split.setShare(splitAmount);
		}
	}


}
