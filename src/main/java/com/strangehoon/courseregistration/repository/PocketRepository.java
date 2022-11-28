package com.strangehoon.courseregistration.repository;

import com.strangehoon.courseregistration.domain.Pocket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PocketRepository extends JpaRepository<Pocket, Long> {
}
