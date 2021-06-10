package com.split.splitservice.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.split.splitservice.model.BalanceResponse;
import com.split.splitservice.service.impl.BalanceServiceImpl;

@RestController
@RequestMapping("/balance")
public class BalanceController {
	
	@Autowired
	BalanceServiceImpl balanceService;
	
	@GetMapping("/{id}")
	public ResponseEntity<BalanceResponse> getUserBalance(@PathVariable("id")int userId) {
		return new ResponseEntity<>(balanceService.fetchBalance(userId), HttpStatus.OK);
	}
	
	@GetMapping("sheet/{id}")
	public Map<Integer, Double> getCollectBalance(@PathVariable("id")int userId) {
		return balanceService.fetchBalanceSheet(userId);
	}
	
	@GetMapping("pay/{id}")
	public ResponseEntity<BalanceResponse> getPendingBalance(@PathVariable("id")int userId) {
		return new ResponseEntity<>(balanceService.fetchBalance(userId), HttpStatus.OK);
	}
}
