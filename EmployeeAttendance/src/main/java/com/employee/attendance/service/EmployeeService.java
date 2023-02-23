package com.employee.attendance.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.employee.attendance.dto.EmployeeRequest;
import com.employee.attendance.entity.Employee;

@Service
public interface EmployeeService {
	public Employee saveEmployee(EmployeeRequest employeeRequest);

	public List<Employee> getAllDetails();

	public Employee getEmployeeById(long id);

	public Employee updateEmployee(long id, EmployeeRequest employeeRequest);

	public void deleteEmployee(long id);

}
