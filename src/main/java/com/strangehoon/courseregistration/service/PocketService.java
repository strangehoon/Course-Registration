package com.strangehoon.courseregistration.service;


import com.strangehoon.courseregistration.domain.PartClass;
import com.strangehoon.courseregistration.domain.Pocket;
import com.strangehoon.courseregistration.domain.Student;
import com.strangehoon.courseregistration.dto.PartClassDto;
import com.strangehoon.courseregistration.dto.PocketClassDto;
import com.strangehoon.courseregistration.repository.PartClassRepository;
import com.strangehoon.courseregistration.repository.PocketRepository;
import com.strangehoon.courseregistration.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PocketService {

    private final PocketRepository pocketRepository;
    private final PartClassRepository partClassRepository;
    private final StudentRepository studentRepository;

    public Boolean checkPocket(PocketClassDto pocketClassDto) {
        PartClass partClass = partClassRepository.findById(pocketClassDto.getPartClassId()).get();
        Student student = studentRepository.findById(pocketClassDto.getStudentId()).get();

        Optional<Pocket> pocket = pocketRepository.findByPartClassAndStudent(partClass,student);
        if(pocket.isPresent()){
            System.out.println("Pocket = " + pocket.get().getPartClass());
            System.out.println("Pocket = " + pocket.get().getStudent());
        }
        if(pocket.isEmpty()) {
            System.out.println("PocketService.checkPocket");
            return true;
        }
        else {
            return false;
        }
    }

    @Transactional
    public void savePocket(PocketClassDto pocketClassDto) {
        Student student = studentRepository.findById(pocketClassDto.getStudentId()).get();
        log.info("student id = {}", student.getId());
        PartClass partClass = partClassRepository.findById(pocketClassDto.getPartClassId()).get();
        log.info("partClass id = {}", partClass.getId());
        Pocket pocket = Pocket.createPocket(partClass, student);
        Pocket savedPocket = pocketRepository.save(pocket);
        System.out.println("savedPocket = " + savedPocket.getId());
    }
}
