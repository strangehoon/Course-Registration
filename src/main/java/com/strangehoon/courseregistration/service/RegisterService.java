package com.strangehoon.courseregistration.service;


import com.strangehoon.courseregistration.domain.PartClass;
import com.strangehoon.courseregistration.domain.Register;
import com.strangehoon.courseregistration.domain.Student;
import com.strangehoon.courseregistration.repository.PocketRepository;
import com.strangehoon.courseregistration.repository.RegisterRepository;
import com.strangehoon.courseregistration.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RegisterService {

    public final PocketRepository pocketRepository;
    public final RegisterRepository registerRepository;
    public final StudentRepository studentRepository;

    @Transactional
    public void registerPocket(Long studentId) {

        List<PartClass> partClassList = pocketRepository.findPartClasses();
        Student student = studentRepository.findById(studentId).get();
        int count = partClassList.size();

        for(int i = 0; i < count; i ++){
            Register register = Register.createRegister(partClassList.get(i), student);
            registerRepository.save(register);
        }

    }
}
