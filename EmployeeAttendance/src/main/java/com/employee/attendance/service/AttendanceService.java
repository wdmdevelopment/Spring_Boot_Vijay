package com.employee.attendance.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.employee.attendance.dto.AttendanceDetails;
import com.employee.attendance.dto.AttendanceRequest;
import com.employee.attendance.dto.OverAllUserReport;
import com.employee.attendance.entity.Attendance;

@Service
public interface AttendanceService {

	public Attendance saveAttendance(AttendanceRequest attendanceRequest);

	public List<Attendance> getAllDetails();

	public Attendance getAttendanceById(long id);

	public Attendance updateAttendance(long id, AttendanceRequest attendanceRequest);

	public void deleteAttendance(long id);
	
	public List<OverAllUserReport> getAllAttendance(long employeeId);
	
	public List<OverAllUserReport> getOverAllDetails();
	
	public List<AttendanceDetails> perDayDetails(long employeeId) ;
	
	
	
}