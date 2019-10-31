package com.bnpparibas.dojo.user.userapp.presentation.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionTranslator {
	/**
	 * logger of type Logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(ExceptionTranslator.class);

	@ExceptionHandler({ NotFoundException.class })
	public ResponseEntity<String> handleNotFoundException(RuntimeException e) {
		return error(HttpStatus.NOT_FOUND, e);
    }

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<String> handleRunTimeException(RuntimeException e) {
		return error(HttpStatus.INTERNAL_SERVER_ERROR, e);
    }

	private ResponseEntity<String> error(HttpStatus status, Exception e) {
		LOG.error("Exception : ", e);
		return ResponseEntity.status(status).body(e.getMessage());
    }
}