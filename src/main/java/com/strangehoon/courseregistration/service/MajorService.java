package com.strangehoon.courseregistration.service;

import com.strangehoon.courseregistration.domain.Major;
import com.strangehoon.courseregistration.repository.MajorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MajorService {

    private final MajorRepository majorRepository;

    //전공 등록
    @Transactional
    public Long createMajor(String name) {
        Major major = Major.createMajor(name);
        Major savedMajor = majorRepository.save(major);
        return savedMajor.getId();
    }
}
