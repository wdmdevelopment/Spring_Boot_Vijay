package com.employee.attendance.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.attendance.dto.LeaveRequest;
import com.employee.attendance.dto.UserRequest;
import com.employee.attendance.entity.LeaveCalendar;
import com.employee.attendance.entity.User;
import com.employee.attendance.exception.LeaveIdNotFoundException;
import com.employee.attendance.exception.LeaveInputMissingException;
import com.employee.attendance.exception.UserIdNotFoundException;
import com.employee.attendance.repository.LeaveRepository;
import com.employee.attendance.service.LeaveService;

@Service
public class LeaveServiceImpl implements LeaveService {
	@Autowired
	LeaveRepository leaveRepository;
	public LeaveCalendar saveLeave(LeaveRequest leaveRequest) {
		try {
			LeaveCalendar leave = new LeaveCalendar();
			leave.setHolidayLeaveDate(leaveRequest.getHolidayLeaveDate());
			leave.setReason(leaveRequest.getReason());

			return leaveRepository.save(leave);
		} catch (Exception e) {
			throw new LeaveInputMissingException("Exception occured in save Leave" + e.getMessage());
		}
		
	}
	public List<LeaveCalendar> getAllDetails() {
		return leaveRepository.findAll();
	}
	
	public LeaveCalendar getLeaveById(long id) {
		LeaveCalendar leave = leaveRepository.findById(id)
				.orElseThrow(() -> new LeaveIdNotFoundException("leave not exist with id: " + id));
		return leave;
	}
	
	public LeaveCalendar updateLeave(long id, LeaveRequest leaveRequest) {
		LeaveCalendar leave = leaveRepository.findById(id)
				.orElseThrow(() -> new LeaveIdNotFoundException("Leave not exist with id: " + id));

		leave.setHolidayLeaveDate(leaveRequest.getHolidayLeaveDate());
		leave.setReason(leaveRequest.getReason());

		return leaveRepository.save(leave);
	}
	
	public void deleteLeave(long id) {

		Optional<LeaveCalendar> leave = leaveRepository.findById(id);
		if (leave.isPresent()) {
			leaveRepository.deleteById(id);
		} else {
			throw new LeaveIdNotFoundException("leave not exist with id : " + id);
		}
	}


}
