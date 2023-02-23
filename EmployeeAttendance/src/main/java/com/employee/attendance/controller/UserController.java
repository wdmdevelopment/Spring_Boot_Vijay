package com.employee.attendance.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.attendance.dto.LoginRequest;
import com.employee.attendance.dto.UserRequest;
import com.employee.attendance.entity.User;
import com.employee.attendance.service.UserService;


@RequestMapping("/user")
@RestController
@CrossOrigin
public class UserController {
	@Autowired
	UserService userService;
	private static final Logger logger = LogManager.getLogger(UserController.class);

	@PostMapping
	public ResponseEntity<User> saveUser(@Valid @RequestBody UserRequest userRequest) {
		logger.info("Create User");
		return new ResponseEntity<>(userService.saveUser(userRequest), HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<User> login(@Valid LoginRequest loginRequest) {
		logger.info("login User");
		return new ResponseEntity<>(userService.login(loginRequest.getUserName(), loginRequest.getPassword()), HttpStatus.OK);
	    
	}
	
	
	@GetMapping
	public ResponseEntity<List<User>> getAllUserDetails() {
		logger.info("Get all Details For User");
		return new ResponseEntity<>(userService.getAllDetails(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUser(@PathVariable(value="id") long id) {
		logger.info("Get particular Id For User");
		return new ResponseEntity<User>(userService.getUserById(id), HttpStatus.OK);

	}

	

	@PutMapping("/{id}")
	public ResponseEntity<User> updateAttendance(@PathVariable long id, @RequestBody UserRequest userRequest) {
		logger.info("Update User");
		return new ResponseEntity<>(userService.updateUser(id, userRequest), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable(value="id") long id) {
		logger.info("Delete particular Id For User");
		userService.deleteUser(id);
		return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
	}

}
