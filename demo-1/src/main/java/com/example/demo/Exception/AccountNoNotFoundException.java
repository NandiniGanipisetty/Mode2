package com.example.demo.Exception;


import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)

public class AccountNoNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	   
	   public AccountNoNotFoundException(String exception) {
		      
		      super(exception);
		   }
	   
}
