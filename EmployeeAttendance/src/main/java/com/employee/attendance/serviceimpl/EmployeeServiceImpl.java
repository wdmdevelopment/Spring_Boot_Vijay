package com.employee.attendance.serviceimpl;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.attendance.dto.EmployeeRequest;
import com.employee.attendance.entity.Employee;
import com.employee.attendance.entity.User;
import com.employee.attendance.exception.EmployeeIdNotFoundException;
import com.employee.attendance.exception.EmployeeInputMissingException;
import com.employee.attendance.exception.UserIdNotFoundException;
import com.employee.attendance.repository.CompanyRepository;
import com.employee.attendance.repository.EmployeeRepository;
import com.employee.attendance.repository.UserRepository;
import com.employee.attendance.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	UserRepository userRepository;



	public Employee saveEmployee(EmployeeRequest employeeRequest) {
		try {

			User user = new User();
			user.setName(employeeRequest.getName());
			user.setUserName(employeeRequest.getUserName());
			user.setMailId(employeeRequest.getMailId());
			user.setRole(employeeRequest.getRole());
			user.setPassword(employeeRequest.getPassword());

			Employee employee = new Employee();
			employee.setCompanyName(employeeRequest.getCompanyName());
			employee.setCity(employeeRequest.getCity());
			employee.setDesignation(employeeRequest.getDesignation());
			employee.setJoinDate(employeeRequest.getJoinDate());
			employee.setState(employeeRequest.getState());
			employee.setTeam(employeeRequest.getTeam());
			employee.setPincode(employeeRequest.getPincode());
			employee.setTeam(employeeRequest.getTeam());
			employee.setPhoneNumber(employeeRequest.getPhoneNumber());

			return employeeRepository.save(employee);
		} catch (Exception e) {
			throw new EmployeeInputMissingException("Exception occured in save Employee" + e.getMessage());
		}

	}

	public List<Employee> getAllDetails() {
		return employeeRepository.findAll();
	}

	public Employee getEmployeeById(long id) {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new EmployeeIdNotFoundException("Employee not exist with id: " + id));
		return employee;

	}

	public Employee updateEmployee(long id, EmployeeRequest employeeRequest) {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new EmployeeIdNotFoundException("Employee not exist with id: " + id));

		employee.setCompanyName(employeeRequest.getCompanyName());
		employee.setDesignation(employeeRequest.getDesignation());
		employee.setJoinDate(employeeRequest.getJoinDate());
		employee.setTeam(employeeRequest.getTeam());

		return employeeRepository.save(employee);
	}

	public void deleteEmployee(long id) {

		Optional<Employee> employee = employeeRepository.findById(id);
		if (employee.isPresent()) {
			employeeRepository.deleteById(id);
		} else {
			throw new EmployeeIdNotFoundException("Employee not exist with id: " + id);
		}
	}
}
