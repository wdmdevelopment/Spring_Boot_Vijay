package com.employee.attendance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.attendance.entity.LeaveCalendar;

@Repository
public interface LeaveRepository extends JpaRepository<LeaveCalendar, Long> {

}
