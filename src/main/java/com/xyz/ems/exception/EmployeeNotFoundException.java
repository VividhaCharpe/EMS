package com.xyz.ems.exception;

public class EmployeeNotFoundException extends RuntimeException{
     public static final long serialversionUID = 1L;
     
	public EmployeeNotFoundException() {
		
	}
	
	public EmployeeNotFoundException(String message) {
	   super(message);
	}
}
