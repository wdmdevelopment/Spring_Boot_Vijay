package com.employee.attendance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.attendance.entity.OtherLeave;
@Repository
public interface OtherLeaveRepository extends JpaRepository<OtherLeave, Long> {
	


}
