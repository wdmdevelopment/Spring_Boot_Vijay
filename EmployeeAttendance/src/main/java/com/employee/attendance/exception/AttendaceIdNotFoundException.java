package com.employee.attendance.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AttendaceIdNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AttendaceIdNotFoundException(String msg) {

		super(msg);
	}

}
