package com.strangehoon.courseregistration.repository;

import com.strangehoon.courseregistration.domain.Board;
import com.strangehoon.courseregistration.dto.BoardDto;
import com.strangehoon.courseregistration.dto.PartClassDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query("select new com.strangehoon.courseregistration.dto.BoardDto(b.id, b.author, b.title, b.content, b.createdDate, b.modifiedDate) " + "from Board b")
    Page<BoardDto> findBoardList(Pageable pageable);


}
