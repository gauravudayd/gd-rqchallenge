package com.example.rqchallenge.dao;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException.TooManyRequests;
import org.springframework.web.client.RestTemplate;

import com.example.rqchallenge.exception.EmployeeAPIException;
import com.example.rqchallenge.resource.Employee;
import com.example.rqchallenge.resource.EmployeeResponse;

@Repository
public class EmployeeDAO implements IEmployeeDAO {

	@Autowired
	RestTemplate restTemplate;

	@Override
	public EmployeeResponse<List<Employee>> getAllEmployees() throws EmployeeAPIException, TooManyRequests {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity entity = new HttpEntity<>(headers);

		ParameterizedTypeReference<EmployeeResponse<List<Employee>>> paramRef = new ParameterizedTypeReference<EmployeeResponse<List<Employee>>>() {
		};

		ResponseEntity<EmployeeResponse<List<Employee>>> response = restTemplate
				.exchange("https://dummy.restapiexample.com/api/v1/employees", HttpMethod.GET, entity, paramRef);

		return response.getBody();
	}

	@Override
	public EmployeeResponse<Employee> getEmployeeById(String id) throws EmployeeAPIException, TooManyRequests {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity entity = new HttpEntity<>(headers);

		Map<String, String> params = new HashMap<>();
		params.put("id", id);

		ParameterizedTypeReference<EmployeeResponse<Employee>> paramRef = new ParameterizedTypeReference<EmployeeResponse<Employee>>() {
		};
		ResponseEntity<EmployeeResponse<Employee>> response = restTemplate.exchange(
				"https://dummy.restapiexample.com/api/v1/employee/{id}", HttpMethod.GET, entity, paramRef, params);

		return response.getBody();
	}

	@Override
	public EmployeeResponse<Employee> createEmployee(Map<String, Object> employeeInput) throws EmployeeAPIException {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity entity = new HttpEntity<>(headers);

		ParameterizedTypeReference<EmployeeResponse<Employee>> paramRef = new ParameterizedTypeReference<EmployeeResponse<Employee>>() {
		};
		ResponseEntity<EmployeeResponse<Employee>> response = restTemplate
				.exchange("https://dummy.restapiexample.com/api/v1/create", HttpMethod.POST, entity, paramRef);

		return response.getBody();
	}

	@Override
	public String deleteEmployeeById(String id) throws EmployeeAPIException {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity entity = new HttpEntity<>(headers);

		Map<String, String> params = new HashMap<>();
		params.put("id", id);

		ResponseEntity<String> response = restTemplate.exchange("https://dummy.restapiexample.com/api/v1/delete/{id}",
				HttpMethod.DELETE, entity, String.class, params);

		return response.getBody();
	}

}
