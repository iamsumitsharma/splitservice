package com.split.splitservice.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.split.splitservice.constant.Constant;
import com.split.splitservice.model.Expenditure;
import com.split.splitservice.model.Expense;
import com.split.splitservice.model.Share;
import com.split.splitservice.services.ExpenditureService;
import com.split.splitservice.services.ExpenseHandlerService;
import com.split.splitservice.services.ExpenseOperation;

@Service
public class EqualExpenditureService implements ExpenseOperation{

	@Autowired
	ExpenseHandlerService expenseHandlerService;
	
	Logger LOGGER = LoggerFactory.getLogger(ExpenditureService.class);

	public Iterable<Expense> handleExpenditure(Expenditure expenditure) {

		calculateAmount(expenditure);
		return expenseHandlerService.saveExpense(expenditure);
	}

	@Override
	public void calculateAmount(Expenditure expenditure) {

		LOGGER.info("EqualExpenditureService::calculateAmount: initializing calculation of type {}", expenditure.getType());
		int totalSplits = expenditure.getSplits().size() + 1;
		List<Share> splits = expenditure.getSplits(); 
		double amount = expenditure.getAmount();
		double splitAmount = ((double) Math.round(amount*100/totalSplits))/100.0;
		for (Share split : splits) {
			if(split.getShare()<0) {
				LOGGER.info("EqualExpenditureService::calculateAmount::Invalid Value Error for user {}", split.getUserId());
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Constant.INVALID_VALUE+split.getUserId());
			}
			split.setShare(splitAmount);
		}
	}


}
