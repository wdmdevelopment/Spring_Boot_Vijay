package com.employee.attendance.repository;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.employee.attendance.entity.Attendance;

public interface AttentdanceRepository extends JpaRepository<Attendance, Long> {
	
	Attendance findByDateBetween(Time startTime, Time endTime);



	List<Attendance> findByEmployeeId(long empId);



	
	






}
