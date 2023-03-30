package com.example.rqchallenge.exception;

import org.springframework.http.HttpStatus;

public class EmployeeAPIException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private HttpStatus status;
	private String message;

	public EmployeeAPIException() {
		// TODO Auto-generated constructor stub
	}

	public EmployeeAPIException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public EmployeeAPIException(String message, HttpStatus status) {
		this.message = message;
		this.status = status;
	}

	public EmployeeAPIException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public EmployeeAPIException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public EmployeeAPIException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	/*
	 * public void setStatus(HttpStatus errorCode) { this.status = status; }
	 */

	public HttpStatus getStatus() {
		return status;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
