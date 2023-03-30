package com.example.rqchallenge.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.rqchallenge.exception.EmployeeAPIException;
import com.example.rqchallenge.resource.Employee;

@Service
public interface IEmployeeService {

	List<Employee> getAllEmployees() throws EmployeeAPIException;

	Employee getEmployeeById(String id) throws EmployeeAPIException;

	Employee createEmployee(Map<String, Object> employeeInput) throws EmployeeAPIException;

	String deleteEmployeeById(String id) throws EmployeeAPIException;

	List<Employee> getEmployeesByName(String name) throws EmployeeAPIException;

	Integer getHighestSalaryOfEmployee() throws EmployeeAPIException;

	List<String> getTopTenHighestEarningEmployeeNames() throws EmployeeAPIException;
}
