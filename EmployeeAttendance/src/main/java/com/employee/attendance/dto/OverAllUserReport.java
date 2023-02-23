package com.employee.attendance.dto;

import java.util.List;

import java.util.Objects;


import com.employee.attendance.entity.Attendance;


public class OverAllUserReport {
	private String employeeName;
	private String overTotalHours;
	private String actualHoursSpenByEmployee;
	private String balanceHour;
	private String status;
	private List<Attendance> attendances;
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getOverTotalHours() {
		return overTotalHours;
	}
	public void setOverTotalHours(String overTotalHours) {
		this.overTotalHours = overTotalHours;
	}
	public String getActualHoursSpenByEmployee() {
		return actualHoursSpenByEmployee;
	}
	public void setActualHoursSpenByEmployee(String actualHoursSpenByEmployee) {
		this.actualHoursSpenByEmployee = actualHoursSpenByEmployee;
	}
	public String getBalanceHour() {
		return balanceHour;
	}
	public void setBalanceHour(String balanceHour) {
		this.balanceHour = balanceHour;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<Attendance> getAttendances() {
		return attendances;
	}
	public void setAttendances(List<Attendance> attendances) {
		this.attendances = attendances;
	}
	
}
