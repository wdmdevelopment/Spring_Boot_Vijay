package com.employee.attendance.dto;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EmployeeRequest {

	@NotEmpty
	@Size(min = 3, message = "Name should have at least 3 characters")
	private String name;

	@NotEmpty
	@Size(min = 2, message = "User Name should have at least 2 characters")
	private String userName;

	@NotEmpty
	@Size(min = 8, message = "password should have at least 8 characters")
	private String password;
	@Email
	private String mailId;

	@NotEmpty(message = "Role Should have User and Admin")
	private String role;
	
	@NotNull(message = "Designation Null")
	private String designation;

	@NotNull(message = "Team Null")
	private String team;
	
	@DateTimeFormat(pattern = "dd-MM-yyy")
	@JsonProperty("date")
	private LocalDate joinDate;
	
	@NotNull(message = "Company name Null")
	private String companyName;
	@NotNull(message = "city Null")
	private String city;
	
	@NotNull(message = "state Null")
	private String state;
	
	@NotNull(message = "pincode Null")
	private String pincode;
	
	@NotNull(message = "phone number Null")
	private String phoneNumber;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public LocalDate getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(LocalDate joinDate) {
		this.joinDate = joinDate;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	
	
	
}
