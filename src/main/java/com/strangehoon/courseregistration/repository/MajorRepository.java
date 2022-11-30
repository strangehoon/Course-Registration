package com.strangehoon.courseregistration.repository;

import com.strangehoon.courseregistration.domain.Major;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MajorRepository extends JpaRepository<Major, Long> {

    Major findByName(String name);
}
