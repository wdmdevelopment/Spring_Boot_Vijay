package com.employee.attendance.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AttendanceRequest {

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull
	@JsonProperty("date")
	private LocalDate date;

	@NotNull
	@JsonProperty("startTime")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
	private LocalTime startTime;

	@NotNull
	@JsonProperty("endTime")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
	private LocalTime endTime;

	@JsonProperty("employeeId")
	private long employeeId;
	
	@JsonProperty("userId")
	private long UserId;

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

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

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public long getUserId() {
		return UserId;
	}

	public void setUserId(long userId) {
		UserId = userId;
	}

	
	
}
