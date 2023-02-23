package com.employee.attendance.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.employee.attendance.dto.LeaveRequest;
import com.employee.attendance.entity.LeaveCalendar;

@Service
public interface LeaveService {
	public LeaveCalendar saveLeave(LeaveRequest leaveRequest);
	
	public List<LeaveCalendar> getAllDetails();
	
	public LeaveCalendar getLeaveById(long id);
	
	public LeaveCalendar updateLeave(long id, LeaveRequest leaveRequest);
	
	public void deleteLeave(long id);

}
