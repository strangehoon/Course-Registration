package com.strangehoon.courseregistration.repository;

import com.strangehoon.courseregistration.domain.PartClass;
import com.strangehoon.courseregistration.dto.PartClassDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PartClassRepository extends JpaRepository<PartClass, Long> {

    @Query("select new com.strangehoon.courseregistration.dto.PartClassDto(p.id, p.name, p.credit, p.dayTime, p.classroom, m.name) " + "from PartClass p join p.major m")
    Page<PartClassDto> findPartClassAll(Pageable pageable);

    @Query("select new com.strangehoon.courseregistration.dto.PartClassDto(p.id, p.name, p.credit, p.dayTime, p.classroom, m.name) " + "from PartClass p join p.major m " + "where p.id = :partClassId")
    PartClassDto findPartClassOne(@Param("partClassId") Long partClassId);

}
