package com.split.splitservice.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ValidationUtils {
     
	public static boolean commonValidation(double amount) {
	
		if(amount <= 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Amount is not defined,can not be shared");
		}
		return true;
	}
}
