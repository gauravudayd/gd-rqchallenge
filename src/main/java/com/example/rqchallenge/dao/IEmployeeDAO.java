package com.example.rqchallenge.dao;

import java.util.List;
import java.util.Map;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpClientErrorException.TooManyRequests;

import com.example.rqchallenge.exception.EmployeeAPIException;
import com.example.rqchallenge.resource.Employee;
import com.example.rqchallenge.resource.EmployeeResponse;

@Repository
public interface IEmployeeDAO {

	@Retryable(value = HttpClientErrorException.class, maxAttempts = 5, backoff = @Backoff(delay = 2000))
	EmployeeResponse<List<Employee>> getAllEmployees() throws EmployeeAPIException, TooManyRequests;

	@Retryable(value = HttpClientErrorException.class, maxAttempts = 5, backoff = @Backoff(delay = 2000))
	EmployeeResponse<Employee> getEmployeeById(String id) throws EmployeeAPIException, TooManyRequests;

	EmployeeResponse<Employee> createEmployee(Map<String, Object> employeeInput) throws EmployeeAPIException;

	String deleteEmployeeById(String id) throws EmployeeAPIException;

}
