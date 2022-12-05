package com.strangehoon.courseregistration.repository;

import com.strangehoon.courseregistration.dto.PartClassDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PartClassRepositoryCustom {

    Page<PartClassDto> findPartClassDtoAll(PartClassSearch partClassSearch, Pageable pageable);
}
