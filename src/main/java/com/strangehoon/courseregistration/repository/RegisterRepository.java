package com.strangehoon.courseregistration.repository;

import com.strangehoon.courseregistration.domain.PartClass;
import com.strangehoon.courseregistration.domain.Register;
import com.strangehoon.courseregistration.domain.Student;
import com.strangehoon.courseregistration.dto.RegisterDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RegisterRepository extends JpaRepository<Register, Long> {

    @Query("select new com.strangehoon.courseregistration.dto.RegisterDto(c.dayTime, c.name, c.credit) " + "from Register r join r.partClass c join r.student s " + "where s.id = :studentId")
    List<RegisterDto> findRegisterDtoList(@Param("studentId") Long studentId);

    List<Register> findByStudent(Student student);

    Optional<Register> findByPartClass(PartClass partClass);

    @Query("select c " + "from Register r join r.partClass c join r.student s " + "where s.id = :studentId")
    List<PartClass> findByRegister(@Param("studentId") Long studentId);

}
