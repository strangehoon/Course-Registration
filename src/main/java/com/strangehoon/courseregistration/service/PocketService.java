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

    // 장바구니에 담을 수 있는지 체크.
    // 1 -> 저장 가능, 2-> 19학점 초과, 3-> 동일한 이름의 강좌 존재, 4-> 시간 중복
    public Long checkPocket(PocketClassDto pocketClassDto) {
        return check(pocketClassDto);
    }

    // 장바구니에 강좌 등록
    @Transactional
    public void savePocket(PocketClassDto pocketClassDto) {
        Student student = studentRepository.findById(pocketClassDto.getStudentId()).get();
        PartClass partClass = partClassRepository.findById(pocketClassDto.getPartClassId()).get();

        Pocket pocket = Pocket.createPocket(partClass, student);
        Pocket savedPocket = pocketRepository.save(pocket);
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



    private long check(PocketClassDto pocketClassDto) {
        PartClass foundPartClass = partClassRepository.findById(pocketClassDto.getPartClassId()).get();
        Student student = studentRepository.findById(pocketClassDto.getStudentId()).get();
        List<Integer> creditList = pocketRepository.findTotalCreditByStudent(student.getId());

        String foundDayTime = foundPartClass.getDayTime();
        String partClassName = foundPartClass.getName();

        ArrayList<String> pieces = new ArrayList<>();

        for(int i = 0; i < foundDayTime.length()/2; i++) {
            String piece = foundDayTime.substring(i*2,i*2+2);
            pieces.add(piece);
        }

        int size = pieces.size();

        Long studentId = student.getId();
        List<PartClass> partClassByStudent = pocketRepository.findByPocket(studentId);

        // 장바구니가 비어있으면 담기 가능이므로 1반환
        if(partClassByStudent == null) {
            return 1L;
        }

        // 장바구니에 19학점을 초과하면 2반환
        int totalCredit = 0;
        for(int i = 0; i < creditList.size(); i++) {
            totalCredit += creditList.get(i);
        }
        if (totalCredit + foundPartClass.getCredit() > 19) {
            return 2L;
        }

        // 장바구니에 기존의 동일한 강좌가 존재하면 3반환
        for(PartClass partClass : partClassByStudent) {
            if(partClassName.equals(partClass.getName())) {
                return 3L;
            }
        }
        // 장바구니에 시간이 중복되는 강좌가 존재하면 4반환
        for(PartClass partClass : partClassByStudent) {
            String dayTime = partClass.getDayTime();
            for(int i = 0; i < size ; i++) {
                String a = pieces.get(i);
                int index = dayTime.indexOf(a);
                if (index != -1){
                    return 4L;
                }
            }
        }

        // 그 밖의 경우에는 담기 가능이라 1반환
        return 1L;
    }


}
