package com.strangehoon.courseregistration.service;


import com.strangehoon.courseregistration.controller.PartClassForm;

import com.strangehoon.courseregistration.domain.Major;
import com.strangehoon.courseregistration.domain.PartClass;
import com.strangehoon.courseregistration.dto.PartClassDto;
import com.strangehoon.courseregistration.repository.MajorRepository;
import com.strangehoon.courseregistration.repository.PartClassRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Part;
import java.util.List;

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

        PartClass partClass = PartClass.createPartClass(savedMajor, partClassDto.getName(), partClassDto.getCredit(),
                                                        partClassDto.getDayTime(), partClassDto.getClassroom());
        PartClass savedPartClass = partClassRepository.save(partClass);

        return savedPartClass.getId();
    }

    //분반 수정
    @Transactional
    public void updatePartClass(PartClassDto partClassDto) {
        PartClass partClass = partClassRepository.findById(partClassDto.getId()).get();
        partClass.updatePartClass(partClassDto.getMajorName() ,partClassDto.getName(), partClassDto.getCredit(), partClassDto.getDayTime(), partClassDto.getClassroom());

    }

    // 분반 전체 조회
    public List<PartClassDto> findPartClasses() {
        return partClassRepository.findPartClassAll();
    }

    // 특정 분반 조회
    public PartClassDto findPartClassOne(Long partClassId) {
        return partClassRepository.findPartClassOne(partClassId);
    }

}
