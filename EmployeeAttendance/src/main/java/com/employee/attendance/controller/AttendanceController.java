package com.employee.attendance.controller;

import org.slf4j.Logger;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.employee.attendance.dto.AttendanceDetails;
import com.employee.attendance.dto.AttendanceRequest;
import com.employee.attendance.dto.OverAllUserReport;
import com.employee.attendance.entity.Attendance;
import com.employee.attendance.service.AttendanceService;

@RestController
@RequestMapping("/attendance")
@CrossOrigin
public class AttendanceController {
	

	@Autowired
	AttendanceService attendanceService;
	
	

	private static final Logger logger = LoggerFactory.getLogger(AttendanceController.class);

	@PostMapping
	public ResponseEntity<Attendance> postAttendance(@RequestBody AttendanceRequest attendanceRequest) {
		System.out.println(attendanceRequest);
		
		logger.info("save new attendance = Date={},StartTime= {},EndTime= {}  " , attendanceRequest.getDate(), attendanceRequest.getStartTime(),
				attendanceRequest.getEndTime());
		
		Attendance saveAttendance = attendanceService.saveAttendance(attendanceRequest);
		return new ResponseEntity<>(saveAttendance, HttpStatus.CREATED);
	}

	@GetMapping
	public List<Attendance> getAllAttendanceDetails() {
		logger.info("Get all details for attendance");
		List<Attendance> list=attendanceService.getAllDetails();
		
		
		
		List<AttendanceDetails> res = new ArrayList<>();
		for (Attendance att : list) {
			AttendanceDetails atten = new AttendanceDetails();
			atten.setDate(att.getDate());
			atten.setStartTime(att.getEndTime());
			atten.setEndTime(att.getEndTime());
			
			
			
			res.add(atten);
		}
		return list;
		


	}

	@GetMapping("/{id}")
	public ResponseEntity<Attendance> getAttendanceId(@PathVariable(value="id") long id) {
		logger.info("Get Id details for attendance");
		return new ResponseEntity<>(attendanceService.getAttendanceById(id), HttpStatus.OK);
	}
	
	@GetMapping("/range/{id}")
	public ResponseEntity<List<OverAllUserReport>> getRange(@PathVariable(name = "id") long employeeId) {
		return new ResponseEntity<>(attendanceService.getAllAttendance(employeeId), HttpStatus.OK);
	}
	
	@GetMapping("/group")
	public ResponseEntity<List<OverAllUserReport>> getGroupEmployee() {
		return new ResponseEntity<>(attendanceService.getOverAllDetails(), HttpStatus.OK);
	}
	


	@GetMapping("/perDay/{id}")
	public ResponseEntity<List<AttendanceDetails>> getPerDayRange(@PathVariable(value="id")  long employeeId) {		
		
		return new ResponseEntity<>(attendanceService.perDayDetails(employeeId), HttpStatus.OK);
	
	
	}


	@PutMapping("/{id}")
	public ResponseEntity<Attendance> updateAttendance(@PathVariable long id,
			@RequestBody AttendanceRequest attendanceRequest) {
		logger.info("Update attendance");
		return new ResponseEntity<>(attendanceService.updateAttendance(id, attendanceRequest), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAttendance(@PathVariable(value="id") long id) {
		logger.info("Delete particular Id For attendance");
		attendanceService.deleteAttendance(id);
		return new ResponseEntity<>("Deleted Successfully", HttpStatus.NO_CONTENT);

	}

}
