package com.xyz.ems.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyz.ems.entity.Employee;
import com.xyz.ems.exception.EmployeeNotFoundException;
import com.xyz.ems.repositry.EmployeeRepositry;
import com.xyz.ems.service.IEmployeeService;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
	
	@Autowired
	private EmployeeRepositry employeeRep ;

	@Override
	public Integer save(Employee employee) {
	  employee = employeeRep.save(employee);
	  Integer id = employee.getId();
	  
		return id;
	}

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> list = employeeRep.findAll();
		
		return list;
	}

	@Override
	public void update(Employee emp) {
		employeeRep.save(emp);
		
	}
	

	@Override
	public void delete(Integer id) {
		//get the employee by id if emp exist with id than get the emp object and pass to dlete method
		
		//employeeRep.deleteById(id);
		
		employeeRep.delete(getOneEmployee(id)); //here we are calling getoneemployee method to get the optional to dleete the employee with specific id
		
	}

	@Override
	public Employee getOneEmployee(Integer id) {
		Employee e = null;
		
		Optional<Employee> optional = employeeRep.findById(id);
		
		if(optional.isPresent()) {
			e = optional.get();
		}else {
			throw new EmployeeNotFoundException("Employee does not exist with Id : "+id);
		}
		return e;
	}

	

	
	
	

}
