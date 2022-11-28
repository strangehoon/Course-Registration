package com.strangehoon.courseregistration.repository;

import com.strangehoon.courseregistration.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
