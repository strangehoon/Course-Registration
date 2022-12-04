package com.strangehoon.courseregistration.repository;

import com.strangehoon.courseregistration.domain.Manager;
import com.strangehoon.courseregistration.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ManagerRepository extends JpaRepository<Manager, Long> {

    Manager findByLoginId(String loginId);

}
