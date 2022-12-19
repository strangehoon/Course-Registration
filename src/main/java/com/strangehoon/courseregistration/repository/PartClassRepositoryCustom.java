package com.strangehoon.courseregistration.repository;

import com.strangehoon.courseregistration.dto.PartClassDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PartClassRepositoryCustom {

    Page<PartClassDto> findPartClassDtoAll(PartClassSearch partClassSearch, Pageable pageable);

    List<PartClassDto> findPocketAll(Long studentId);
}
