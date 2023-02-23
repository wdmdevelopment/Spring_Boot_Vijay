package com.employee.attendance.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table
public class LeaveCalendar {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private long id;

	@DateTimeFormat(pattern = "dd-MM-yyyy")

	@Column(name = "HOLIDAY_LEAVE_DATE")
	private LocalDate  holidayLeaveDate;

	@Column(name = "REASON")
	private String reason;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	

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

	@Override
	public String toString() {
		return "LeaveCalendar [id=" + id + ", holidayLeaveDate=" + holidayLeaveDate + ", reason=" + reason + "]";
	}

}
