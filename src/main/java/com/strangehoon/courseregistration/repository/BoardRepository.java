package com.strangehoon.courseregistration.repository;

import com.strangehoon.courseregistration.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
