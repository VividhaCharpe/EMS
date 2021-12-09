package com.xyz.ems.service;

import java.util.List;

import com.xyz.ems.entity.Employee;

public interface IEmployeeService {
    
	Integer save(Employee employee);
	
	List<Employee> getAllEmployees();
	
	void update(Employee emp);
	
	void delete(Integer id);
	
	Employee getOneEmployee(Integer id);
}
