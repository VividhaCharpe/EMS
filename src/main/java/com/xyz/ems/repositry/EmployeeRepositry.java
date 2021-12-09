package com.xyz.ems.repositry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xyz.ems.entity.Employee;

public interface EmployeeRepositry extends JpaRepository<Employee , Integer>{

}
