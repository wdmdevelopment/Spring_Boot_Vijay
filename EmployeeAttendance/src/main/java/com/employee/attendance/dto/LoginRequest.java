package com.employee.attendance.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class LoginRequest {
	
	@NotEmpty
	@Size(min = 2, message = "User Name should have at least 2 characters")
	private String userName;
	
	@NotEmpty
	@Size(min = 8, message = "password should have at least 8 characters")
	private String password;

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

}
