package com.split.splitservice.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.split.splitservice.constant.Constant;
import com.split.splitservice.model.BalanceResponse;
import com.split.splitservice.model.Expense;
import com.split.splitservice.repository.ExpenseRepository;
import com.split.splitservice.services.BalanceService;

@Service
public class BalanceServiceImpl implements BalanceService{

	@Autowired
	ExpenseRepository expenseRepository;
	
	@Override
	public BalanceResponse fetchBalance(int userId) {
		
		double paidBy =	expenseRepository.findBalancePaidBy(userId);
		double paidTo= expenseRepository.findBalancePaidTo(userId);
		double balanceAmount = paidBy-paidTo;
		String message = balanceAmount >= 0 ? Constant.POSITIVE_BALANCE : Constant.NEGATIVE_BALANCE;
		return new BalanceResponse(balanceAmount, message);
	}
	
	@Override
	public Map<Integer, Double> fetchBalanceSheet(int userId) {
		
		List<Expense> paidBy =	expenseRepository.findByPaidBy(userId);
		List<Expense> paidTo= expenseRepository.findByPaidTo(userId);
		Map<Integer, Double> balanceSheet= new HashMap<>();
		
		for(Expense expense: paidBy) {
			int id = expense.getPaidTo();
			if(balanceSheet.containsKey(id)) {
				double amount = balanceSheet.get(id);
				amount+=expense.getAmount();
				balanceSheet.put(id, amount);
			} else {
				balanceSheet.put(id, expense.getAmount());
			}
		}
		
		for(Expense expense: paidTo) {
			int id = expense.getPaidBy();
			if(balanceSheet.containsKey(id)) {
				double amount = balanceSheet.get(id);
				amount-=expense.getAmount();
				balanceSheet.put(id, amount);
			} else {
				balanceSheet.put(id, -expense.getAmount());
			}
		}
		
	
		return balanceSheet;
	}

}
