package com.strangehoon.courseregistration.service;


import com.strangehoon.courseregistration.domain.Major;
import com.strangehoon.courseregistration.domain.Manager;
import com.strangehoon.courseregistration.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ManagerService {

    private final ManagerRepository managerRepository;

    //관리자 등록
    @Transactional
    public Long createManager(String loginId, String password) {
        Manager manager = Manager.createManager(loginId, password);
        Manager savedManager = managerRepository.save(manager);
        return savedManager.getId();
    }
}
