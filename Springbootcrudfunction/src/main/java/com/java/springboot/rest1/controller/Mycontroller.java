package com.java.springboot.rest1.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.java.springboot.rest1.model.Employee;
import com.java.springboot.rest1.service.Myservice;

@RestController
public class Mycontroller {

	@Autowired
	Myservice service;

	@RequestMapping(value= "/employee/listall", method= RequestMethod.GET)
	public List<Employee> getEmployees() {
		System.out.println(this.getClass().getSimpleName() + " - Get all employees service is invoked.");
		return service.getEmployees();
	}

	@RequestMapping(value= "/employee/{index}", method= RequestMethod.GET)
	public Employee getEmployeeById(@PathVariable int index) throws Exception {
		System.out.println(this.getClass().getSimpleName() + " - Get employee details by id is invoked.");

		Optional<Employee> emp =  service.getEmployeeById(index);
		if(!emp.isPresent())
			throw new Exception("Could not find employee with id- " + index);

		return emp.get(); 
	}

	@RequestMapping(value= "/employee/add", method= RequestMethod.POST)
	public Employee createEmployee(@RequestBody Employee newemp) {
		System.out.println(this.getClass().getSimpleName() + " - Create new employee method is invoked.");
		return service.addNewEmployee(newemp);
	}

	@RequestMapping(value= "/employee/update/{id}", method= RequestMethod.PUT)
	public Employee updateEmployee(@RequestBody Employee updemp, @PathVariable int index) throws Exception {
		System.out.println(this.getClass().getSimpleName() + " - Update employee details by id is invoked.");

		Optional<Employee> emp =  service.getEmployeeById(index);
		if (!emp.isPresent())
			throw new Exception("Could not find employee with id- " + index);

		/* IMPORTANT - To prevent the overiding of the existing value of the variables in the database, 
		 * if that variable is not coming in the @RequestBody annotation object. */		
		if(updemp.getName() == null || updemp.getName().isEmpty())
			updemp.setName(emp.get().getName());
		if(updemp.getDepartment() == null || updemp.getDepartment().isEmpty())
			updemp.setDepartment(emp.get().getDepartment());
		if(updemp.getSalary() == 0)
			updemp.setSalary(emp.get().getSalary());

		// Required for the "where" clause in the sql query template.
		updemp.setId(index);
		return service.updateEmployee(updemp);
	}

	@RequestMapping(value= "/employee/delete/{index}", method= RequestMethod.DELETE)
	public void deleteEmployeeById(@PathVariable int index) throws Exception {
		System.out.println(this.getClass().getSimpleName() + " - Delete employee by id is invoked.");

		Optional<Employee> emp =  service.getEmployeeById(index);
		if(!emp.isPresent())
			throw new Exception("Could not find employee with id- " + index);

		service.deleteEmployeeById(index);
	}

	@RequestMapping(value= "/employee/deleteall", method= RequestMethod.DELETE)
	public void deleteAll() {
		System.out.println(this.getClass().getSimpleName() + " - Delete all employees is invoked.");
		service.deleteAllEmployees();
	}
}