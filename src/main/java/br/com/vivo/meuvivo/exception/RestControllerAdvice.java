package br.com.vivo.meuvivo.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.RestControllerAdvice
public class RestControllerAdvice {
	
	@ExceptionHandler(HTTPActionRecorderException.class)
	public ResponseEntity<String> handleActionRecorderExceptions(HTTPActionRecorderException ex){
		return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleActionRecorderExceptions(Exception ex){
		return ResponseEntity.status(500).body("Unexpected Error");
	}

}
