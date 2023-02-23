package com.employee.attendance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.attendance.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUserNameAndPassword(String userName, String password);

	User findByName(String name);

	User findById(long UserId);

}
