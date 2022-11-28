package com.strangehoon.courseregistration.repository;

import com.strangehoon.courseregistration.domain.Register;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisterRepository extends JpaRepository<Register, Long> {
}
