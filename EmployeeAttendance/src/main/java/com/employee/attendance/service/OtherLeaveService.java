package com.employee.attendance.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.employee.attendance.dto.OtherLeaveRequest;
import com.employee.attendance.entity.OtherLeave;

@Service
public interface OtherLeaveService {
	
	public OtherLeave saveOtherLeave(OtherLeaveRequest otheraLeaveRequest);
	
	public List<OtherLeave> getAllDetails();
	
	public OtherLeave updateOtherLeave(long id, OtherLeaveRequest otheraLeaveRequest);
	
	public void deleteOtherLeave(long id);
	
	public OtherLeave getOtherLeaveById(long id);

	
	
}
