package com.employee.attendance.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.attendance.dto.UserRequest;
import com.employee.attendance.entity.Address;
import com.employee.attendance.entity.Employee;
import com.employee.attendance.entity.User;
import com.employee.attendance.exception.UserIdNotFoundException;
import com.employee.attendance.exception.UserInputMissingException;
import com.employee.attendance.repository.EmployeeRepository;
import com.employee.attendance.repository.UserRepository;
import com.employee.attendance.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;

	public User saveUser(UserRequest userRequest) {

		try {
			

			Employee employee =new Employee();
			employee.setCompanyName(userRequest.getCompanyName());
			employee.setCity(userRequest.getCity());
			employee.setDesignation(userRequest.getDesignation());
			employee.setJoinDate(userRequest.getJoinDate());
			employee.setState(userRequest.getState());
			employee.setTeam(userRequest.getTeam());
			employee.setPincode(userRequest.getPincode());			
			employee.setTeam(userRequest.getTeam());
			employee.setPhoneNumber(userRequest.getPhoneNumber());
			
			
			
			User user = new User();
			user.setName(userRequest.getName());
			user.setUserName(userRequest.getUserName());
			user.setMailId(userRequest.getMailId());
			user.setRole(userRequest.getRole());
			user.setPassword(userRequest.getPassword());
			user.setEmployee(employee);
		
			
			
			

			return userRepository.save(user);
		} catch (Exception e) {
			throw new UserInputMissingException("Exception occured in save user " + e.getMessage());
		}
	}

	public List<User> getAllDetails() {
		return userRepository.findAll();
	}

	public User getUserById(long id) {
		User user = userRepository.findById(id);
		return user;
	}

	public User updateUser(long id, UserRequest userRequest) {
		User user = userRepository.findById(id);

		user.setName(userRequest.getName());
		user.setUserName(userRequest.getUserName());
		user.setMailId(userRequest.getMailId());
		user.setRole(userRequest.getRole());
		user.setPassword(userRequest.getPassword());

		return userRepository.save(user);
	}

	public void deleteUser(long id) {

		User user = userRepository.findById(id);
		
			userRepository.deleteById(id);
		
	}

	public User login(String userName, String password){
		User user=userRepository.findByUserNameAndPassword(userName, password);
		if(user==null ) {
			throw new UserIdNotFoundException("User not exist ");
		}
		return user;
		
	}
	

}
