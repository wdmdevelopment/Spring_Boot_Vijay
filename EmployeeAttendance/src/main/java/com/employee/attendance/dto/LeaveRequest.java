package com.employee.attendance.dto;

import java.time.LocalDate;


import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;



public class LeaveRequest {
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@NotNull
	private LocalDate  holidayLeaveDate;
	@NotNull
	private String reason;

	public LocalDate getHolidayLeaveDate() {
		return holidayLeaveDate;
	}

	public void setHolidayLeaveDate(LocalDate holidayLeaveDate) {
		this.holidayLeaveDate = holidayLeaveDate;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
}
