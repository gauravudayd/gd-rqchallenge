package com.example.rqchallenge.resource;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EmployeeResponse<T> {

	@JsonProperty("status")
	private String status;

	@JsonProperty("data")
	private T data;

	@JsonProperty("message")
	private String message;

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
