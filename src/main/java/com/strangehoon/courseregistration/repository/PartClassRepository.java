package com.strangehoon.courseregistration.repository;

import com.strangehoon.courseregistration.domain.PartClass;
import com.strangehoon.courseregistration.dto.PartClassDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PartClassRepository extends JpaRepository<PartClass, Long>, PartClassRepositoryCustom {

    @Query("select new com.strangehoon.courseregistration.dto.PartClassDto(p.id, p.classNum, p.name, p.grade, p.credit, p.capacity, p.remainNum, p.professorName, p.dayTime, p.classroom, m.name) " + "from PartClass p join p.major m")
    Page<PartClassDto> findPartClassAll(Pageable pageable);

    @Query("select new com.strangehoon.courseregistration.dto.PartClassDto(p.id, p.classNum, p.name, p.grade, p.credit, p.capacity, p.remainNum, p.professorName, p.dayTime, p.classroom, m.name) " + "from PartClass p join p.major m " + "where p.id = :partClassId")
    PartClassDto findPartClassOne(@Param("partClassId") Long partClassId);





    }



//동적쿼리를 만들자..
//학과, 학년, 과목명을 입력하면 이에 따라 리스트가 조회됨.