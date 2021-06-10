package com.split.splitservice.constant;

public final class Constant {

	public static final String NEGATIVE_BALANCE = "You Owe, please settle up fast";
	public static final String POSITIVE_BALANCE = "You Don't Owe!!!";
	public static final String EQUAL = "EQUAL";
	public static final String CUSTOM = "CUSTOM";
	public static final String PERCENT = "PERCENT";
	public static final String TYPE_NOT_FOUND = "Type is not found, try with EQUAL,CUSTOM, PERCENT";
	public static final String CUSTOM_ERROR = "Custom Amount is faulty, check it again";
	public static final String PERCENT_ERROR = "Percent Distribution Exceeds 100!!";
	public static final String INVALID_VALUE = "Invalid Value Encountered for User ";
	public enum ExpenditureType {
		EQUAL,
		EXACT,
		PERCENT
	}
	public enum HTTP_METHODS {
		GET, POST, PUT, DELETE
	}

}

