package com.example.rqchallenge.employees;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.rqchallenge.exception.EmployeeAPIException;
import com.example.rqchallenge.resource.Employee;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public interface IEmployeeController {

	@GetMapping("/employees")
	ResponseEntity<List<Employee>> getAllEmployees() throws IOException, EmployeeAPIException;

	@GetMapping("/employee/search/{searchString}")
	ResponseEntity<List<Employee>> getEmployeesByNameSearch(@PathVariable String searchString)
			throws EmployeeAPIException;

	@GetMapping("/employee/{id}")
	ResponseEntity<Employee> getEmployeeById(@PathVariable String id) throws EmployeeAPIException;

	@GetMapping("/highestSalary")
	ResponseEntity<Integer> getHighestSalaryOfEmployees() throws EmployeeAPIException;

	@GetMapping("/topTenHighestEarningEmployeeNames")
	ResponseEntity<List<String>> getTopTenHighestEarningEmployeeNames() throws EmployeeAPIException;

	@PostMapping("/create")
	ResponseEntity<Employee> createEmployee(@RequestBody Map<String, Object> employeeInput) throws EmployeeAPIException;

	@DeleteMapping("/delete/{id}")
	ResponseEntity<String> deleteEmployeeById(@PathVariable String id) throws EmployeeAPIException;

}
