package com.split.splitservice.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.split.splitservice.constant.Constant;
import com.split.splitservice.exception.CustomException;
import com.split.splitservice.model.Expenditure;
import com.split.splitservice.model.Expense;
import com.split.splitservice.model.Share;
import com.split.splitservice.services.ExpenseHandlerService;
import com.split.splitservice.services.ExpenseOperation;
import com.split.splitservice.services.ValidationService;

@Service
public class CustomExpenditureService implements ExpenseOperation, ValidationService{

	@Autowired
	ExpenseHandlerService expenseHandlerService;
	
	Logger LOGGER = LoggerFactory.getLogger(CustomExpenditureService.class);


	public Iterable<Expense> handleExpenditure(Expenditure expenditure) throws CustomException {

		calculateAmount(expenditure);
		if(validateExpense(expenditure))
			return expenseHandlerService.saveExpense(expenditure);
			else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Constant.CUSTOM_ERROR);
	}

	@Override
	public void calculateAmount(Expenditure expenditure) {

		List<Share> splits = expenditure.getSplits(); 
		for (Share split : splits) {
			split.setShare(split.getShare());
		}
	}

	@Override
	public boolean validateExpense(Expenditure expenditure) {
		double totalAmount = expenditure.getAmount();
		double shareAmountTotal = 0;
		List<Share> splits = expenditure.getSplits(); 
		for (Share split : splits) {
			if(split.getShare()<0) {
				LOGGER.info("CustomExpenditureService::validateExpense::Invalid Value Error for user {}", split.getUserId());
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Constant.INVALID_VALUE+split.getUserId());
			}
			shareAmountTotal += split.getShare();
		}
		if (totalAmount < shareAmountTotal) {
			return false;
		}
		return true;
	}
}
