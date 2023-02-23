package com.employee.attendance.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee.attendance.dto.EmployeeRequest;
import com.employee.attendance.dto.LoginRequest;
import com.employee.attendance.entity.Employee;
import com.employee.attendance.entity.User;
import com.employee.attendance.service.EmployeeService;


@RequestMapping("/employee")
@RestController
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;

	private static final Logger logger = LoggerFactory.getLogger(AttendanceController.class);

	@PostMapping
	public ResponseEntity<Employee> postEmployee(@Valid @RequestBody EmployeeRequest employeeRequest) {
		logger.info("Create Employee");
		return new ResponseEntity<>(employeeService.saveEmployee(employeeRequest), HttpStatus.CREATED);
	}
	
	

	@GetMapping
	public List<Employee> getAllEmployeeDetails() {
		logger.info("Get all Details for Employee");
		return employeeService.getAllDetails();

	}

	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeId(@PathVariable(value="id") long id) {
		logger.info("Get particular Id For Employee");
		Employee employee = employeeService.getEmployeeById(id);
		if (employee == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(employee);

	}

	@PutMapping("/{id}")
	public ResponseEntity<Employee> updateAttendance(@Valid @PathVariable long id,
			@RequestBody EmployeeRequest employeeRequest) {
		logger.info("Update Employee");
		return new ResponseEntity<>(employeeService.updateEmployee(id, employeeRequest), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable(value="id") long id) {
		logger.info("Delete particular Id For Employee");
		employeeService.deleteEmployee(id);
		return new ResponseEntity<>("Deleted Success Fully", HttpStatus.OK);
	}

}
