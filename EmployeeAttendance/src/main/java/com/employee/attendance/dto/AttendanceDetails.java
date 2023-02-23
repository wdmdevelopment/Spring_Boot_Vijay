package com.employee.attendance.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

import com.employee.attendance.entity.Attendance;
import com.employee.attendance.entity.Employee;

public class AttendanceDetails {
	private LocalTime startTime;
	private LocalTime endTime;
	private LocalDate date;
	private String actualHour;
	private String targetHour;
	private String status;
	private long attendanceId;
	private long employeeId;


	public LocalTime getStartTime() {
		return startTime;
	}


	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}


	public LocalTime getEndTime() {
		return endTime;
	}


	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}


	public LocalDate getDate() {
		return date;
	}


	public void setDate(LocalDate date) {
		this.date = date;
	}


	public String getActualHour() {
		return actualHour;
	}


	public void setActualHour(String actualHour) {
		this.actualHour = actualHour;
	}


	public String getTargetHour() {
		return targetHour;
	}


	public void setTargetHour(String targetHour) {
		this.targetHour = targetHour;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public long getAttendanceId() {
		return attendanceId;
	}


	public void setAttendanceId(long attendanceId) {
		this.attendanceId = attendanceId;
	}


	public long getEmployeeId() {
		return employeeId;
	}


	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	
}
