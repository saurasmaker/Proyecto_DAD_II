package edu.ucam.enums;

public enum ErrorType {
	ERROR_TYPE,
	NO_ERROR,
	ERROR,
	PARAMETER_NULL,
	JDBC_ERROR_CONNECTION,
	
	LOGIN_ERROR,
	PASSWORD_DONT_MATCHES,
	ACCESS_DENIED,
	
	USER_EXISTS,
	USER_DONT_EXISTS,
	PRODUCT_EXISTS, 
	VIDEOGAME_EXISTS,
	
	DATABASE_STATEMENT_ERROR,
	DATABASE_CONNECTION_ERROR,
	
	CREATE_VIDEOGAME_ERROR,
	CREATE_VIDEOGAME_IMAGE_ERROR,
	CREATE_VIDEOGAME_CATEGORY_ERROR,
	CREATE_USER_ERROR,
	CREATE_RENTAL_ERROR,
	CREATE_PURCHASE_ERROR,
	CREATE_CATEGORY_ERROR,
	CREATE_BILL_ERROR,
	CREATE_ASSESSMENT_ERROR,
	
	SELECTED_OBTION_DOES_NOT_EXISTS;
}
