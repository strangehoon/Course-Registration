package com.strangehoon.courseregistration.service;


import com.strangehoon.courseregistration.domain.PartClass;
import com.strangehoon.courseregistration.domain.Pocket;
import com.strangehoon.courseregistration.domain.Register;
import com.strangehoon.courseregistration.domain.Student;
import com.strangehoon.courseregistration.dto.ManagerRegisterDto;
import com.strangehoon.courseregistration.dto.PartClassDto;
import com.strangehoon.courseregistration.dto.PocketClassDto;
import com.strangehoon.courseregistration.dto.RegisterDto;
import com.strangehoon.courseregistration.repository.PartClassRepository;
import com.strangehoon.courseregistration.repository.PocketRepository;
import com.strangehoon.courseregistration.repository.RegisterRepository;
import com.strangehoon.courseregistration.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public final PartClassRepository partClassRepository;

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
            System.out.println("RegisterService.registerPocket" + partClassList.size());
            registerRepository.save(register);
        }

        for(PartClass partClass : partClassList) {
            partClass.decreaseRemainNum();
        }
        return true;
    }

    @Transactional
    public void deleteAll(Long studentId) {

        Student student = studentRepository.findById(studentId).get();
        List<Register> registerList = registerRepository.findByStudent(student);
        System.out.println("RegisterService.deleteAll" + registerList.size());
        List<PartClass> partClassDtoList = registerRepository.findByRegister(studentId);
        System.out.println("RegisterService.deleteAll" + partClassDtoList.size());
        if (partClassDtoList.size() != 0) {
            for(PartClass partClass: partClassDtoList) {
                partClass.addRemainNum();
            }
        }

        registerRepository.deleteAll(registerList);
    }

    // 장바구니 내역 삭제
    @Transactional
    public void deleteRegister(Long partClassId) {
        PartClass foundPartClass = partClassRepository.findById(partClassId).get();
        foundPartClass.addRemainNum();
        Register foundRegister = registerRepository.findByPartClass(foundPartClass).get();
        registerRepository.delete(foundRegister);
    }

    public List<RegisterDto> findRegisterDto(Long studentId) {
        Student student = studentRepository.findById(studentId).get();
        return registerRepository.findRegisterDtoList(student.getId());
    }


    public Page<PartClassDto> registerList(Long studentId, Pageable pageable) {
        int initpage = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1); //스프링 데이터 jpa에서는 페이지가 0부터 시작 따라서 이행을 넣음
        pageable = PageRequest.of(initpage, 10, Sort.by(Sort.Direction.ASC,"id")); // <- Sort 추가


        Page<PartClassDto> page = partClassRepository.findRegisterAll(studentId, pageable);
        return page;
    }

    // 수강신청 가능한지 체크.
    // 1 -> 신청 가능, 2-> 19학점 초과, 3-> 동일한 이름의 강좌 존재, 4-> 시간 중복
    public Long checkRegisterList(RegisterDto registerDto) {
        return check(registerDto);
    }

    // 장바구니에 강좌 등록
    @Transactional
    public void registerNew(RegisterDto registerDto) {
        Student student = studentRepository.findById(registerDto.getStudentId()).get();
        PartClass partClass = partClassRepository.findById(registerDto.getPartClassId()).get();

        Register register = Register.createRegister(partClass, student);
        Register savedRegister = registerRepository.save(register);
        partClass.decreaseRemainNum();
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

    //------------------------------------------------관리자 영역-----------------------------------------------

    public Page<ManagerRegisterDto> registerListByManager(Pageable pageable) {
        int initpage = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1); //스프링 데이터 jpa에서는 페이지가 0부터 시작 따라서 이행을 넣음
        pageable = PageRequest.of(initpage, 10, Sort.by(Sort.Direction.ASC,"id")); // <- Sort 추가

        Page<ManagerRegisterDto> page = partClassRepository.findManagerRegisterAll(pageable);
        return page;
    }

    //------------------------------------------------메서드-----------------------------------------------
    private long check(RegisterDto registerDto) {
        PartClass foundPartClass = partClassRepository.findById(registerDto.getPartClassId()).get();
        Student student = studentRepository.findById(registerDto.getStudentId()).get();
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
        List<PartClass> partClassByStudent = registerRepository.findByRegister(studentId);

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
