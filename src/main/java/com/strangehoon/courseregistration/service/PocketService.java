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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    // 장바구니에 기존 강좌 있는지 체크
    public Boolean checkPocket(PocketClassDto pocketClassDto) {
        PartClass foundPartClass = partClassRepository.findById(pocketClassDto.getPartClassId()).get();
        Student student = studentRepository.findById(pocketClassDto.getStudentId()).get();

        String foundDayTime = foundPartClass.getDayTime();

        ArrayList<String> pieces = new ArrayList<>();

        for(int i = 0; i < foundDayTime.length()/2; i++) {
            String piece = foundDayTime.substring(i*2,i*2+2);
            pieces.add(piece);
        }

        int size = pieces.size();

        Long studentId = student.getId();
        List<PartClass> partClassByStudent = pocketRepository.findByPocket(studentId);
        if(partClassByStudent == null) {
            return true;
        }


        for(PartClass partClass : partClassByStudent) {
            String dayTime = partClass.getDayTime();
            for(int i = 0; i < size ; i++) {
                String a = pieces.get(i);
                int index = dayTime.indexOf(a);
                if (index != -1){
                    return false;
                }
            }
        }
        return true;

    }
    
    

    // 장바구니에 강좌 등록
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


    //장바구니 내역 삭제
    @Transactional
    public void deletePocketClass(Long partClassId) {
        PartClass foundPartClass = partClassRepository.findById(partClassId).get();
        Pocket foundPocketClass = pocketRepository.findByPartClass(foundPartClass).get();
        pocketRepository.delete(foundPocketClass);
    }

    // 장바구니 내역 조회
    public Page<PartClassDto> pocketClassList(Long studentId, Pageable pageable) {
        int initpage = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1); //스프링 데이터 jpa에서는 페이지가 0부터 시작 따라서 이행을 넣음
        pageable = PageRequest.of(initpage, 10, Sort.by(Sort.Direction.ASC,"id")); // <- Sort 추가

        Page<PartClassDto> page = partClassRepository.findPocketAll(studentId, pageable);
        return page;
    }


}
