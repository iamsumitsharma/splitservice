package com.split.splitservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.split.splitservice.exception.CustomException;
import com.split.splitservice.model.Expenditure;
import com.split.splitservice.model.Expense;
import com.split.splitservice.services.ExpenditureService;

@RestController
@RequestMapping("/split")
public class SplitController {

	@Autowired
	private ExpenditureService expenditureService;

	@PostMapping("")
	public ResponseEntity<Iterable<Expense>> splitExpenditure(@RequestBody Expenditure expenditure ) throws CustomException {

		Iterable<Expense> expense = expenditureService.createExpense(expenditure);
		return  new ResponseEntity<>(expense, HttpStatus.OK);
	}
}
