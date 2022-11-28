package com.strangehoon.courseregistration.service;

import com.strangehoon.courseregistration.domain.PartClass;
import com.strangehoon.courseregistration.repository.MajorRepository;
import com.strangehoon.courseregistration.repository.PartClassRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class PartClassServiceTest {

    @Autowired
    PartClassService partClassService;
    @Autowired
    PartClassRepository partClassRepository;
    @Autowired
    MajorRepository majorRepository;

    @Test
    @Rollback(value = false)
    public void 분반등록() throws Exception {
        //given
        PartClass partClass1 = PartClass.createPartClass("math", 3, "2012", "301");
        //when
        Long saveId = partClassService.savePartClass(partClass1);
        //then
        assertEquals(partClass1, partClassRepository.findById(saveId).get());

    }


}