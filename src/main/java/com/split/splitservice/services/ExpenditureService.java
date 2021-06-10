package com.split.splitservice.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.split.splitservice.constant.Constant;
import com.split.splitservice.constant.Constant.HTTP_METHODS;
import com.split.splitservice.exception.CustomException;
import com.split.splitservice.model.Expenditure;
import com.split.splitservice.model.Expense;
import com.split.splitservice.service.impl.CustomExpenditureService;
import com.split.splitservice.service.impl.EqualExpenditureService;
import com.split.splitservice.service.impl.PercentageExpenditureService;
import com.split.splitservice.utils.ValidationUtils;

@Service
public class ExpenditureService {

	@Autowired
	EqualExpenditureService equalExpenditureService;

	@Autowired
	PercentageExpenditureService percentageExpenditureService;
	
	@Autowired
	CustomExpenditureService customExpenditureService;
	

    Logger LOGGER = LoggerFactory.getLogger(ExpenditureService.class);

	public Iterable<Expense> createExpense(Expenditure expenditure) throws CustomException {
		
		LOGGER.info("ExpenditureService::createExpense: initializing expense of type {}", expenditure.getType());
		if(ValidationUtils.commonValidation(expenditure.getAmount())) {
		switch (expenditure.getType()) {
		case Constant.EQUAL:
			return equalExpenditureService.handleExpenditure(expenditure);
		case Constant.PERCENT: 
			return percentageExpenditureService.handleExpenditure(expenditure);
		case Constant.CUSTOM:
			return customExpenditureService.handleExpenditure(expenditure);
		default:
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Constant.TYPE_NOT_FOUND);
		}
	}
		return null;
	}
}
