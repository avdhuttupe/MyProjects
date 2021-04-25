package com.pair.assignment.exception;

import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomExceptionResponseHandler extends
		ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex,
			WebRequest request)
	/*     */throws Exception
	/*     */{
		ExceptionResponse response = new ExceptionResponse(new Date(),
				ex.getMessage(), request.getDescription(false));
		return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ExceptionHandler(LowerVersionTradeException.class)
	public final ResponseEntity<Object> handleLowerVersionException(
			Exception ex, WebRequest request)
	/*     */throws Exception
	/*     */{
		ExceptionResponse response = new ExceptionResponse(new Date(),
				ex.getMessage(), request.getDescription(false));
		return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		ExceptionResponse response = new ExceptionResponse(new Date(),
				"Validation Failed", ex
						.getBindingResult()
						.getFieldErrors()
						.stream()
						.map(s -> s.getField().concat(":")
								.concat(s.getDefaultMessage()))
						.collect(Collectors.toList()).toString());
		return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
	}
}
