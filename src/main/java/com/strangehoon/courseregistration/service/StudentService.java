package com.strangehoon.courseregistration.service;


import com.strangehoon.courseregistration.domain.Major;
import com.strangehoon.courseregistration.domain.Manager;
import com.strangehoon.courseregistration.domain.Student;
import com.strangehoon.courseregistration.dto.StudentDto;
import com.strangehoon.courseregistration.repository.MajorRepository;
import com.strangehoon.courseregistration.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final MajorRepository majorRepository;

    //학생 등록
    @Transactional
    public Long createStudent(StudentDto studentDto) {
        Major foundMajor = majorRepository.findByName(studentDto.getMajorName());
        Student student = Student.createStudent(foundMajor, studentDto.getLoginId(), studentDto.getPassword(), studentDto.getSchoolYear(), studentDto.getName(),
                                               studentDto.getPhoneNumber());
        Student savedStudent = studentRepository.save(student);

        return savedStudent.getId();
    }
}
