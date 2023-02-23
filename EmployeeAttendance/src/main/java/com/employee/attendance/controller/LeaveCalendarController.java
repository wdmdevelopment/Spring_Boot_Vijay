package com.employee.attendance.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.employee.attendance.dto.LeaveRequest;
import com.employee.attendance.entity.LeaveCalendar;
import com.employee.attendance.service.LeaveService;

@RequestMapping("/leaveCalendar")
@RestController
@CrossOrigin
public class LeaveCalendarController {
	@Autowired
	LeaveService leaveService;
	
	private static final Logger logger = LoggerFactory.getLogger(LeaveCalendarController.class);

	@PostMapping
	public ResponseEntity<LeaveCalendar> postLeave(@RequestBody LeaveRequest leaveRequest) {
		logger.info("Create LeaveCalendar");
		return new ResponseEntity<>(leaveService.saveLeave(leaveRequest), HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<LeaveCalendar>> getAllLeaveDetails() {
		logger.info("Get all Details For Leave");
		return new ResponseEntity<>(leaveService.getAllDetails(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<LeaveCalendar> getUser(@PathVariable(value="id") long id) {
		logger.info("Get particular Id For Leave");
		return new ResponseEntity<LeaveCalendar>(leaveService.getLeaveById(id), HttpStatus.OK);

	}
	
	@PutMapping("/{id}")
	public ResponseEntity<LeaveCalendar> updateLeave(@PathVariable long id, @RequestBody LeaveRequest leaveRequest) {
		logger.info("Update leave");
		return new ResponseEntity<>(leaveService.updateLeave(id, leaveRequest), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteLeave(@PathVariable(value="id") long id) {
		logger.info("Delete particular Id For Leave");
		leaveService.deleteLeave(id);
		return new ResponseEntity<>("Deleted Success Fully", HttpStatus.OK);
	}
}
