package com.strangehoon.courseregistration.service;


import com.strangehoon.courseregistration.domain.Manager;
import com.strangehoon.courseregistration.domain.Student;
import com.strangehoon.courseregistration.repository.ManagerRepository;
import com.strangehoon.courseregistration.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service
//@RequiredArgsConstructor
//public class LoginService {
//
//    private final StudentRepository studentRepository;
//
//    public Student login(String loginId, String password) {
//        List<Student> foundStudent = studentRepository.findByLoginId(loginId);
//        for (Student student : foundStudent) {
//            if (student.getPassword().equals(password)) {
//                return student;
//            }
//        }
//        return null;
//    }
//}

@Service
@RequiredArgsConstructor
public class LoginService {

    private final StudentRepository studentRepository;
    private final ManagerRepository managerRepository;

    public Object login(String loginId, String password) {

        Student student = studentRepository.findByLoginId(loginId);
        Manager manager = managerRepository.findByLoginId(loginId);
        if (student != null) {
            if (student.getPassword().equals(password)) {
                return student;
            }
        }
        else if (manager != null) {
            if (manager.getPassword().equals(password)) {
                return manager;
            }
        }
        return null;

    }
}


