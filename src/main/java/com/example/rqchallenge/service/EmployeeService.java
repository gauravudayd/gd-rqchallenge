package com.example.rqchallenge.service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.TooManyRequests;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;

import com.example.rqchallenge.dao.IEmployeeDAO;
import com.example.rqchallenge.exception.EmployeeAPIException;
import com.example.rqchallenge.resource.Employee;
import com.example.rqchallenge.resource.EmployeeResponse;

@Service
public class EmployeeService implements IEmployeeService {

	@Autowired
	IEmployeeDAO employeeDAO;

	public static Logger logger = LogManager.getLogger(EmployeeService.class);

	@Override
	public List<Employee> getAllEmployees() throws EmployeeAPIException {
		try {
			EmployeeResponse<List<Employee>> response = employeeDAO.getAllEmployees();
			if (response.getData().isEmpty()) {
				logger.info("Could not fetch employees from data source");
				throw new EmployeeAPIException("Could not fetch employees from data source", HttpStatus.NO_CONTENT);
			}
			return response.getData();
		} catch (TooManyRequests e) {
			logger.info("Too many requests", e.getMessage(), e);
			throw new EmployeeAPIException("Too many requests: " + HttpStatus.TOO_MANY_REQUESTS,
					HttpStatus.TOO_MANY_REQUESTS);
		} catch (InternalServerError e) {
			logger.info("Could not fetch employees from data source", e.getMessage(), e);
			throw new EmployeeAPIException(
					"Could not fetch employees from data source due to " + HttpStatus.INTERNAL_SERVER_ERROR,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public Employee getEmployeeById(String id) throws EmployeeAPIException {
		try {
			EmployeeResponse<Employee> response = employeeDAO.getEmployeeById(id);
			if (response.getData() == null) {
				logger.info("Could not fetch employees from data source");
				throw new EmployeeAPIException("Could not get employee with id: " + id + " from data source ",
						HttpStatus.NOT_FOUND);
			}
			return response.getData();
		} catch (TooManyRequests e) {
			logger.info("Too many requests", e.getMessage(), e);
			throw new EmployeeAPIException("Too many requests: " + HttpStatus.TOO_MANY_REQUESTS,
					HttpStatus.TOO_MANY_REQUESTS);
		} catch (InternalServerError e) {
			logger.info("Could not fetch employees from data source", e.getMessage(), e);
			throw new EmployeeAPIException(
					"Could not fetch employee details from data source due to " + HttpStatus.INTERNAL_SERVER_ERROR,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public Employee createEmployee(Map<String, Object> employeeInput) throws EmployeeAPIException {
		try {
			EmployeeResponse<Employee> response = employeeDAO.createEmployee(employeeInput);
			return response.getData();
		} catch (Exception e) {
			logger.info("Could not create employee in data source", e.getMessage(), e);
			throw new EmployeeAPIException("Could not create an employee in data source ",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public String deleteEmployeeById(String id) throws EmployeeAPIException {
		try {
			return employeeDAO.deleteEmployeeById(id);
		} catch (Exception e) {
			logger.info("Could not delete employee from data source", e.getMessage(), e);
			throw new EmployeeAPIException("Could not delete an employee with id " + id + " from data source ",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public List<Employee> getEmployeesByName(String name) throws EmployeeAPIException {
		try {
			EmployeeResponse<List<Employee>> response = employeeDAO.getAllEmployees();
			List<Employee> employees = response.getData();
			List<Employee> employeeNames = employees.stream()
					.filter(e -> e.getName().toLowerCase().matches("^.*" + name.toLowerCase() + ".*$"))
					.collect(Collectors.toList());
			if (employeeNames.isEmpty()) {
				logger.info("Could not fetch employees from data source");
				throw new EmployeeAPIException("Could not get employee by name " + name + " from data source ",
						HttpStatus.NOT_FOUND);
			}
			return employeeNames;
		} catch (TooManyRequests e) {
			logger.info("Too many requests", e.getMessage(), e);
			throw new EmployeeAPIException("Too many requests: " + HttpStatus.TOO_MANY_REQUESTS,
					HttpStatus.TOO_MANY_REQUESTS);
		} catch (InternalServerError e) {
			logger.info("Could not fetch employees from data source", e.getMessage(), e);
			throw new EmployeeAPIException(
					"Could not fetch employee details from data source due to " + HttpStatus.INTERNAL_SERVER_ERROR,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public Integer getHighestSalaryOfEmployee() throws EmployeeAPIException {
		try {
			EmployeeResponse<List<Employee>> response = employeeDAO.getAllEmployees();
			List<Employee> employees = response.getData();
			return employees.stream().map(Employee::getSalary).max(Integer::compare).get();
		} catch (TooManyRequests e) {
			logger.info("Too many requests", e.getMessage(), e);
			throw new EmployeeAPIException("Too many requests: " + HttpStatus.TOO_MANY_REQUESTS,
					HttpStatus.TOO_MANY_REQUESTS);
		} catch (Exception e) {
			throw new EmployeeAPIException("Could not fetch highest employee salary from data source due to "
					+ HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public List<String> getTopTenHighestEarningEmployeeNames() throws EmployeeAPIException {
		try {
			EmployeeResponse<List<Employee>> response = employeeDAO.getAllEmployees();
			List<Employee> employees = response.getData();
			return employees.stream().sorted(Comparator.comparing(Employee::getSalary, Comparator.reverseOrder()))
					.limit(10).map(Employee::getName).collect(Collectors.toList());
		} catch (TooManyRequests e) {
			logger.info("Too many requests", e.getMessage(), e);
			throw new EmployeeAPIException("Too many requests: " + HttpStatus.TOO_MANY_REQUESTS,
					HttpStatus.TOO_MANY_REQUESTS);
		} catch (Exception e) {
			logger.info("Could not fetch employees from data source", e.getMessage(), e);
			throw new EmployeeAPIException("Could not fetch top ten highest earning employees from data source due to "
					+ HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
