package com.java.springboot.rest1.service;

import java.util.List;
import java.util.Optional;

import com.java.springboot.rest1.model.Employee;

public interface Myservice {

	public List<Employee> getEmployees();
	public Optional<Employee> getEmployeeById(int empid);
	public Employee addNewEmployee(Employee emp);
	public Employee updateEmployee(Employee emp);
	public void deleteEmployeeById(int empid);
	public void deleteAllEmployees();

}