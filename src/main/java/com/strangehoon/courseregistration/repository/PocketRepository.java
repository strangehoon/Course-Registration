package com.strangehoon.courseregistration.repository;

import com.strangehoon.courseregistration.domain.PartClass;
import com.strangehoon.courseregistration.domain.Pocket;
import com.strangehoon.courseregistration.domain.Student;
import com.strangehoon.courseregistration.dto.PartClassDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.servlet.http.Part;
import java.util.List;
import java.util.Optional;

public interface PocketRepository extends JpaRepository<Pocket, Long> {

    Optional<Pocket> findByPartClassAndStudent(PartClass partClass, Student student);

//    List<Pocket> findByStudent(Student student);

    Optional<Pocket> findByPartClass(PartClass partClass);

    @Query("select c " + "from Pocket k join k.partClass c join k.student s " + "where s.id = :studentId")
    List<PartClass> findByPocket(@Param("studentId") Long studentId);

}
