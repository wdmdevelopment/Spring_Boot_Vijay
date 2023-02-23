package com.employee.attendance.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;


public class OtherLeaveRequest {
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@NotNull
	@JsonProperty("date")
	private LocalDate ownLeaveDate;

	private String reason;

	public LocalDate getOwnLeaveDate() {
		return ownLeaveDate;
	}

	public void setOwnLeaveDate(LocalDate ownLeaveDate) {
		this.ownLeaveDate = ownLeaveDate;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
