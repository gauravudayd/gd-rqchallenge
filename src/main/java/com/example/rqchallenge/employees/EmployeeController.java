package com.example.rqchallenge.employees;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.rqchallenge.exception.EmployeeAPIException;
import com.example.rqchallenge.resource.Employee;
import com.example.rqchallenge.service.IEmployeeService;

@RestController
public class EmployeeController implements IEmployeeController {

	@Autowired
	IEmployeeService employeeService;

	@Override
	public ResponseEntity<List<Employee>> getAllEmployees() throws IOException, EmployeeAPIException {
		List<Employee> employees = employeeService.getAllEmployees();
		return new ResponseEntity<>(employees, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<Employee>> getEmployeesByNameSearch(String searchString) throws EmployeeAPIException {
		return new ResponseEntity<>(employeeService.getEmployeesByName(searchString), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Employee> getEmployeeById(String id) throws EmployeeAPIException {
		return new ResponseEntity<>(employeeService.getEmployeeById(id.trim()), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Integer> getHighestSalaryOfEmployees() throws EmployeeAPIException {
		return new ResponseEntity<>(employeeService.getHighestSalaryOfEmployee(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<String>> getTopTenHighestEarningEmployeeNames() throws EmployeeAPIException {
		return new ResponseEntity<>(employeeService.getTopTenHighestEarningEmployeeNames(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Employee> createEmployee(Map<String, Object> employeeInput) throws EmployeeAPIException {
		return new ResponseEntity<>(employeeService.createEmployee(employeeInput), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> deleteEmployeeById(String id) throws EmployeeAPIException {
		return new ResponseEntity<String>(employeeService.deleteEmployeeById(id.trim()), HttpStatus.OK);
	}

}
