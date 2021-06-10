package com.split.splitservice.services;

import java.util.Map;

import com.split.splitservice.model.BalanceResponse;

public interface BalanceService {

	BalanceResponse fetchBalance(int userId);

	Map<Integer, Double> fetchBalanceSheet(int userId);
	
}
