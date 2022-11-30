package com.strangehoon.courseregistration.service;


import com.strangehoon.courseregistration.controller.PartClassForm;

import com.strangehoon.courseregistration.domain.Major;
import com.strangehoon.courseregistration.domain.PartClass;
import com.strangehoon.courseregistration.dto.PartClassDto;
import com.strangehoon.courseregistration.repository.MajorRepository;
import com.strangehoon.courseregistration.repository.PartClassRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Part;
import java.util.List;


@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PartClassService {

    private final PartClassRepository partClassRepository;
    private final MajorRepository majorRepository;

    // 분반 등록
    @Transactional
    public Long createPartClass(PartClassDto partClassDto) {

        Major major = Major.createMajor(partClassDto.getMajorName());
        Major savedMajor = majorRepository.save(major);

        PartClass partClass = PartClass.createPartClass(savedMajor, partClassDto.getClassNum(), partClassDto.getName(), partClassDto.getGrade(), partClassDto.getCredit(), partClassDto.getCapacity(),
                                                        partClassDto.getRemainNum(), partClassDto.getProfessorName(), partClassDto.getDayTime(), partClassDto.getClassroom());
        PartClass savedPartClass = partClassRepository.save(partClass);

        return savedPartClass.getId();
    }

    //분반 수정
    @Transactional
    public void updatePartClass(PartClassDto partClassDto) {
        PartClass partClass = partClassRepository.findById(partClassDto.getId()).get();
        partClass.updatePartClass(partClassDto.getMajorName(), partClassDto.getClassNum(), partClassDto.getName(), partClassDto.getGrade(), partClassDto.getCredit(), partClassDto.getCapacity(),
                partClassDto.getRemainNum(), partClassDto.getProfessorName(), partClassDto.getDayTime(), partClassDto.getClassroom());

    }
    //분반 삭제
    @Transactional
    public void deletePartClass(Long partClassId) {
        PartClass foundPartClass = partClassRepository.findById(partClassId).get();
        partClassRepository.delete(foundPartClass);

//        List<PartClass> partClassList = partClassRepository.findAll();
//        for(PartClass partClass : partClassList) {
//
//            if (partClass.getId() > partClassId) {
//                partClass.updateId();
//            }
//        }
    }

    // 분반 전체 조회
    public Page<PartClassDto> findPartClasses(Pageable pageable) {
        int initpage = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1); //스프링 데이터 jpa에서는 페이지가 0부터 시작 따라서 이행을 넣음
        pageable = PageRequest.of(initpage, 10, Sort.by(Sort.Direction.ASC,"id")); // <- Sort 추가

        Page<PartClassDto> page = partClassRepository.findPartClassAll(pageable);
        log.info("page.getTotalElements() = " + page.getTotalElements());
        log.info("page.getNumber() = " + page.getNumber());
        return page;
    }

    // 특정 분반 조회
    public PartClassDto findPartClassOne(Long partClassId) {
        return partClassRepository.findPartClassOne(partClassId);
    }

}
