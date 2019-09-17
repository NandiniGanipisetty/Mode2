

	
package com.example.demo.Exception;



import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@RestController

public class GlobalExceptionHandler {
	@ExceptionHandler(value = AccountNoNotFoundException.class)
	public ResponseEntity<ErrorMessage> handleException(AccountNoNotFoundException exception, WebRequest request) {
		ErrorMessage errorMessage = 
				new ErrorMessage(exception.getMessage(), LocalDate.now(), request.getDescription(false));
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.NOT_FOUND);
	
	}

}
