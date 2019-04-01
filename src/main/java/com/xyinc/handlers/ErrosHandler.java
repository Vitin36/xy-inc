package com.xyinc.handlers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

@ControllerAdvice
public class ErrosHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MultipleErrors> validation(MethodArgumentNotValidException e, HttpServletRequest request) {

		MultipleErrors err = new MultipleErrors("Erro de validação", HttpStatus.BAD_REQUEST.value());
		for (FieldError x : e.getBindingResult().getFieldErrors()) {
			err.add(new InvalidParametersError(x.getDefaultMessage(), x.getField()));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

	@ExceptionHandler(InvalidFormatException.class)
	public ResponseEntity<CommonError> validation(InvalidFormatException e, HttpServletRequest request) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonError(
				"Erro ao converter o campo com o valor " + e.getValue().toString(), request.getRequestURI()));
	}

	@ExceptionHandler(AlreadyExistsError.class)
	public ResponseEntity<CommonError> validation(AlreadyExistsError e, HttpServletRequest request) {
		return ResponseEntity.status(HttpStatus.CONFLICT)
				.body(new CommonError(e.getMessage(), request.getRequestURI()));
	}
	
	@ExceptionHandler(CommonError.class)
	public ResponseEntity<CommonError> validation(CommonError e, HttpServletRequest request) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new CommonError(e.getMessage(),  request.getRequestURI()));
	}

}
