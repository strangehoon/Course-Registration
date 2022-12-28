package com.strangehoon.courseregistration.service;


import com.strangehoon.courseregistration.domain.PartClass;
import com.strangehoon.courseregistration.domain.Register;
import com.strangehoon.courseregistration.domain.Student;
import com.strangehoon.courseregistration.dto.RegisterDto;
import com.strangehoon.courseregistration.repository.PocketRepository;
import com.strangehoon.courseregistration.repository.RegisterRepository;
import com.strangehoon.courseregistration.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RegisterService {

    public final PocketRepository pocketRepository;
    public final RegisterRepository registerRepository;
    public final StudentRepository studentRepository;

    @Transactional
    public boolean registerPocket(Long studentId) {

        List<PartClass> partClassList = pocketRepository.findPartClasses();
        Student student = studentRepository.findById(studentId).get();

        int count = partClassList.size();

        // 담아두기에 강좌가 없으면 false 반환
        if (count == 0)
            return false;

        // 담아두기에 강좌가 한개라도 있으면 true 반환
        for(int i = 0; i < count; i ++){
            Register register = Register.createRegister(partClassList.get(i), student);
            registerRepository.save(register);
        }
        return true;
    }

    @Transactional
    public void deleteAll(Long studentId) {

        Student student = studentRepository.findById(studentId).get();
        List<Register> registerList = registerRepository.findByStudent(student);
        registerRepository.deleteAll(registerList);
    }

    public List<RegisterDto> findRegisterDto(Long studentId) {
        Student student = studentRepository.findById(studentId).get();
        return registerRepository.findRegisterDtoList(student.getId());
    }

    public Map<String, String> makeTimeTable(List<RegisterDto> registerDtos) {
        HashMap<String, String> timeTable = new HashMap<>();

        // 전체 신청 학점 and 신청 과목 수
        int totalCredit = 0;
        for(int i = 0 ; i < registerDtos.size(); i++) {
            totalCredit += registerDtos.get(i).getCredit();
        }
        timeTable.put("전체 신청 학점", Integer.toString(totalCredit));
        timeTable.put("신청 과목 수", Integer.toString(registerDtos.size()));

        int size = registerDtos.size();

        //set을 List자료형으로 변환 ex) "월1화1수1", "월6화6목6"...
        List<String> keyList = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            String a = registerDtos.get(i).getDayTime();


            keyList.add(registerDtos.get(i).getDayTime());

            for (int j = 0; j < (keyList.get(i).length())/2; j++) {
                String piece = keyList.get(i).substring(j * 2, j * 2 + 2);
//                pieces.add(piece);
                timeTable.put(piece, registerDtos.get(i).getName());
            }
        }
        return timeTable;
    }
}
