package com.employee.attendance.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.employee.attendance.dto.LoginRequest;
import com.employee.attendance.dto.UserRequest;
import com.employee.attendance.entity.Attendance;
import com.employee.attendance.entity.User;
import com.employee.attendance.exception.ExceptionResponse;

@Service
public interface UserService {

	public User saveUser(UserRequest UserRequest);

	public List<User> getAllDetails();

	public User getUserById(long id);

	public User updateUser(long id, UserRequest userRequest);

	public void deleteUser(long id);
	
	
	
	public User login(String userName, String password);

	

}
