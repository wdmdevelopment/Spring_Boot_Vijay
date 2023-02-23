package com.employee.attendance.controller;

import org.slf4j.Logger;import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.employee.attendance.dto.AttendanceRequest;
import com.employee.attendance.dto.OtherLeaveRequest;
import com.employee.attendance.entity.Attendance;
import com.employee.attendance.entity.OtherLeave;
import com.employee.attendance.service.AttendanceService;
import com.employee.attendance.service.OtherLeaveService;

@RestController
@RequestMapping("/otherleave")

public class OtherLeaveController {

	@Autowired
	OtherLeaveService otherLeaveService;

	private static final Logger logger = LoggerFactory.getLogger(OtherLeaveController.class);

	@PostMapping
	public ResponseEntity<OtherLeave> postOtherLeave(@RequestBody OtherLeaveRequest otherLeaveRequest) {
		
		return new ResponseEntity<>(otherLeaveService.saveOtherLeave(otherLeaveRequest), HttpStatus.CREATED);
	}

	@GetMapping
	public List<OtherLeave> getAllOtherLeaveDetails() {
		logger.info("Get all details for attendance");

		return otherLeaveService.getAllDetails();

	}

	@GetMapping("/{id}")
	public ResponseEntity<OtherLeave> getOtherLeaveId(@PathVariable(value="id") long id) {
		logger.info("Get Id details for attendance");
		return new ResponseEntity<>(otherLeaveService.getOtherLeaveById(id), HttpStatus.OK);
	}
	
	

	@PutMapping
	public ResponseEntity<OtherLeave> updateOtherLeave(@PathVariable long id,
			@RequestBody OtherLeaveRequest otherLeaveRequest) {
		logger.info("Update attendance");
		return new ResponseEntity<>(otherLeaveService.updateOtherLeave(id, otherLeaveRequest), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteOtherLeave(@PathVariable(value="id") long id) {
		logger.info("Delete particular Id For attendance");
		otherLeaveService.deleteOtherLeave(id);
		return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);

	}

}
