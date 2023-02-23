package com.employee.attendance.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.attendance.dto.OtherLeaveRequest;
import com.employee.attendance.entity.OtherLeave;
import com.employee.attendance.exception.OtherLeaveIdNotFoundException;
import com.employee.attendance.exception.OtherLeaveInputMissingException;
import com.employee.attendance.repository.OtherLeaveRepository;
import com.employee.attendance.service.OtherLeaveService;

@Service
public class OtherLeaveServiceImpl implements OtherLeaveService {

	@Autowired

	OtherLeaveRepository otherLeaveRepository;

	public OtherLeave saveOtherLeave(OtherLeaveRequest otheraLeaveRequest) {

		try {
			OtherLeave leave = new OtherLeave();
			leave.setOwnLeaveDate(otheraLeaveRequest.getOwnLeaveDate());
			leave.setReason(otheraLeaveRequest.getReason());

			return otherLeaveRepository.save(leave);
		} catch (Exception e) {
			throw new OtherLeaveInputMissingException("Exception occured in save OtherLeave" + e.getMessage());
		}

	}

	public List<OtherLeave> getAllDetails() {
		return otherLeaveRepository.findAll();
	}

	public OtherLeave getOtherLeaveById(long id) {
		OtherLeave leave = otherLeaveRepository.findById(id)
				.orElseThrow(() -> new OtherLeaveIdNotFoundException("Otherleave not exist with id: " + id));
		return leave;
	}

	public OtherLeave updateOtherLeave(long id, OtherLeaveRequest otheraLeaveRequest) {
		OtherLeave leave = otherLeaveRepository.findById(id)
				.orElseThrow(() -> new OtherLeaveIdNotFoundException("OtherLeave not exist with id: " + id));

		leave.setOwnLeaveDate(otheraLeaveRequest.getOwnLeaveDate());
		leave.setReason(otheraLeaveRequest.getReason());

		return otherLeaveRepository.save(leave);
	}

	public void deleteOtherLeave(long id) {

		Optional<OtherLeave> leave = otherLeaveRepository.findById(id);
		if (leave.isPresent()) {
			otherLeaveRepository.deleteById(id);
		} else {
			throw new OtherLeaveIdNotFoundException("Otherleave not exist with id : " + id);
		}
	}

	
}
