package com.split.splitservice.services;

import com.split.splitservice.model.Expenditure;

public interface ValidationService {

	boolean validateExpense(Expenditure expenditure);
}
