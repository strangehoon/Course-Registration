package com.strangehoon.courseregistration.controller;



import com.strangehoon.courseregistration.domain.Major;
import com.strangehoon.courseregistration.dto.PartClassDto;
import com.strangehoon.courseregistration.repository.MajorRepository;
import com.strangehoon.courseregistration.service.PartClassService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PartClassController {

    private final PartClassService partClassService;
    private final MajorRepository majorRepository;

    //분반 등록 폼
    @GetMapping(value = "/partClasses/new")
    public String createForm(Model model) {
        List<Major> majors = majorRepository.findAll();

        model.addAttribute("majors", majors);
        model.addAttribute("partClassForm", new PartClassForm());
        return "partClass/createPartClassForm";
    }

    //분반 등록
    @PostMapping(value = "/partClasses/new")
    public String create(PartClassForm partClassForm, Model model) {
        partClassService.createPartClass(new PartClassDto(partClassForm));

        model.addAttribute("message", "신규 분반이 등록되었습니다.");
        model.addAttribute("searchUrl", "/partClasses");
        return "message";
    }

    //분반 조회
    @GetMapping(value = "/partClasses")
    public String List(@PageableDefault Pageable pageable, Model model) {
        Page<PartClassDto> partClassDtoAll = partClassService.findPartClasses(pageable);

        model.addAttribute("pageNumber", partClassDtoAll.getNumber());
        model.addAttribute("partClassForm", partClassDtoAll);
        return "partClass/partClassList";
    }


    //분반 수정 폼
    @GetMapping(value = "/partClasses/{partClassId}/edit")
    public String updatePartClassForm(@PathVariable("partClassId") Long partClassId, Model model) {
        PartClassDto partClassDto = partClassService.findPartClassOne(partClassId);
        log.info("info log ={}", partClassId);
        PartClassForm partClassForm = new PartClassForm(partClassDto);

        model.addAttribute("partClassForm", partClassForm);
        return "partClass/updatePartClassForm";
    }

    //분반 수정
    @PostMapping(value = "/partClasses/{partClassId}/edit")
    public String updatePartClass(@ModelAttribute("form") PartClassForm partClassForm, Model model) {
        PartClassDto partClassDtoWithId = new PartClassDto(partClassForm, partClassForm.getId());

        partClassService.updatePartClass(partClassDtoWithId);
        model.addAttribute("message", "분반 정보를 수정했습니다.");
        model.addAttribute("searchUrl", "/partClasses");
        return "message";
    }

    //분반 삭제
    @GetMapping(value = "/partClasses/{partClassId}/delete")
    public String deletePartClass(@PathVariable("partClassId") Long partClassId, Model model) {
        partClassService.deletePartClass(partClassId);

        model.addAttribute("message", "분반 정보를 삭제했습니다.");
        model.addAttribute("searchUrl", "/partClasses");
        return "message";
    }


//    /*테스트용 데이터*/
//    @PostConstruct
//    public void init() {
//        partClassService.createPartClass(new PartClassDto("106827-1", "창직 IOT 종합설계입문", 1, 3, 18, 18, "신형식", "화2화3화4", "P211", "전자전기공학부"));
//        partClassService.createPartClass(new PartClassDto("106827-2", "창직 IOT 종합설계입문", 1, 3, 18, 18, "신형식", "화6화7화8", "P211", "전자전기공학부"));
//        partClassService.createPartClass(new PartClassDto("106827-3", "창직 IOT 종합설계입문", 1, 3, 18, 18, "노승문", "수1수2수3", "P211", "전자전기공학부"));
//        partClassService.createPartClass(new PartClassDto("106827-4", "창직 IOT 종합설계입문", 1, 3, 18, 18, "노승문", "월1월2월3", "P111", "전자전기공학부"));
//        partClassService.createPartClass(new PartClassDto("106827-5", "창직 IOT 종합설계입문", 1, 3, 18, 18, "최창식", "목2목3목4", "P211", "전자전기공학부"));
//        partClassService.createPartClass(new PartClassDto("013706-1", "전기회로실험및설계", 2, 3, 18, 18, "정하봉", "수6수7", "P205", "전자전기공학부"));
//        partClassService.createPartClass(new PartClassDto("013706-2", "전기회로실험및설계", 2, 3, 18, 18, "박동욱", "월7월8", "P204", "전자전기공학부"));
//        partClassService.createPartClass(new PartClassDto("013706-3", "전기회로실험및설계", 2, 3, 18, 18, "계영철", "금1금2", "P203", "전자전기공학부"));
//        partClassService.createPartClass(new PartClassDto("013706-4", "전기회로실험및설계", 2, 3, 18, 18, "양현석", "월1월2", "P101", "전자전기공학부"));
//        partClassService.createPartClass(new PartClassDto("013706-5", "전기회로실험및설계", 2, 3, 18, 18, "박상주", "목7목8", "P204", "전자전기공학부"));
//        partClassService.createPartClass(new PartClassDto("106302-1", "물리전자", 2, 3, 50, 50, "성혁기", "월1화1수1", "P301", "전자전기공학부"));
//        partClassService.createPartClass(new PartClassDto("106302-2", "물리전자", 2, 3, 50, 50, "성혁기", "월3화3수3", "P301", "전자전기공학부"));
//        partClassService.createPartClass(new PartClassDto("106302-3", "물리전자", 2, 3, 50, 50, "성혁기", "월6화6수6", "P301", "전자전기공학부"));
//        partClassService.createPartClass(new PartClassDto("106302-4", "물리전자", 2, 3, 50, 50, "김영민", "화7목7금7", "P301", "전자전기공학부"));
//        partClassService.createPartClass(new PartClassDto("106302-5", "물리전자", 2, 3, 50, 50, "김영민", "화8목8금8", "P301", "전자전기공학부"));
//        partClassService.createPartClass(new PartClassDto("106404-1", "신호와시스템", 3, 3, 50, 50, "허서원", "월5화5수5", "P302", "전자전기공학부"));
//        partClassService.createPartClass(new PartClassDto("106404-2", "신호와시스템", 3, 3, 50, 50, "허서원", "월6화6수6", "P302", "전자전기공학부"));
//        partClassService.createPartClass(new PartClassDto("106404-3", "신호와시스템", 3, 3, 50, 50, "계영철", "화2화3목7", "P303", "전자전기공학부"));
//        partClassService.createPartClass(new PartClassDto("106404-4", "신호와시스템", 3, 3, 50, 50, "계영철", "월1월2금1", "P303", "전자전기공학부"));
//        partClassService.createPartClass(new PartClassDto("106404-5", "신호와시스템", 3, 3, 50, 50, "계영철", "월5월6목5", "P303", "전자전기공학부"));
//        partClassService.createPartClass(new PartClassDto("106611-1", "반도체공학(2)", 3, 3, 50, 50, "김형탁", "화5화6금1", "P304", "전자전기공학부"));
//        partClassService.createPartClass(new PartClassDto("106611-2", "반도체공학(2)", 3, 3, 50, 50, "김형탁", "월8수8목8", "P304", "전자전기공학부"));
//        partClassService.createPartClass(new PartClassDto("106611-3", "반도체공학(2)", 3, 3, 50, 50, "박상연", "월3월4수1", "P305", "전자전기공학부"));
//        partClassService.createPartClass(new PartClassDto("106611-4", "반도체공학(2)", 3, 3, 50, 50, "박상연", "월7월8수8", "P305", "전자전기공학부"));
//        partClassService.createPartClass(new PartClassDto("106611-5", "반도체공학(2)", 3, 3, 50, 50, "박상연", "화7목7금7", "P305", "전자전기공학부"));
//        partClassService.createPartClass(new PartClassDto("106612-1", "집적회로설계", 3, 3, 50, 50, "김종선", "월1화1수1", "P401", "전자전기공학부"));
//        partClassService.createPartClass(new PartClassDto("106612-2", "집적회로설계", 3, 3, 50, 50, "김종선", "월2화2수2", "P401", "전자전기공학부"));
//        partClassService.createPartClass(new PartClassDto("106612-3", "집적회로설계", 3, 3, 50, 50, "김종선", "월5화5수5", "P401", "전자전기공학부"));
//        partClassService.createPartClass(new PartClassDto("106612-4", "집적회로설계", 3, 3, 50, 50, "김영민", "수6수7목5", "P402", "전자전기공학부"));
//        partClassService.createPartClass(new PartClassDto("106612-5", "집적회로설계", 3, 3, 50, 50, "김영민", "월1금1금2", "P402", "전자전기공학부"));
//        partClassService.createPartClass(new PartClassDto("106825-1", "IT종합설계프로젝트", 4, 3, 18, 18, "강동우", "월1월2월3", "P101", "전자전기공학부"));
//        partClassService.createPartClass(new PartClassDto("106825-2", "IT종합설계프로젝트", 4, 3, 18, 18, "신훈영", "화1화2화3", "P101", "전자전기공학부"));
//        partClassService.createPartClass(new PartClassDto("106825-3", "IT종합설계프로젝트", 4, 3, 18, 18, "차호영", "금7금8금9", "P102", "전자전기공학부"));
//        partClassService.createPartClass(new PartClassDto("106825-4", "IT종합설계프로젝트", 4, 3, 18, 18, "이기성", "월1화1수1", "P102", "전자전기공학부"));
//        partClassService.createPartClass(new PartClassDto("106825-5", "IT종합설계프로젝트", 4, 3, 18, 18, "신형식", "월6목6금6", "P103", "전자전기공학부"));
//
//        partClassService.createPartClass(new PartClassDto("013312-1", "자료구조및프로그래밍", 2, 4, 40, 40, "송하윤", "월2월3목2목3", "T501", "컴퓨터공학부"));
//        partClassService.createPartClass(new PartClassDto("013312-2", "자료구조및프로그래밍", 2, 4, 40, 40, "송하윤", "월6월7목6목7", "T501", "컴퓨터공학부"));
//        partClassService.createPartClass(new PartClassDto("013312-3", "자료구조및프로그래밍", 2, 4, 40, 40, "배성일", "화2화3금2금3", "T502", "컴퓨터공학부"));
//        partClassService.createPartClass(new PartClassDto("013312-4", "자료구조및프로그래밍", 2, 4, 40, 40, "배성일", "월7월8수1수2", "T502", "컴퓨터공학부"));
//        partClassService.createPartClass(new PartClassDto("013312-5", "자료구조및프로그래밍", 2, 4, 40, 40, "배성일", "월5월6목1목2", "T502", "컴퓨터공학부"));
//
//
//
//        partClassService.createPartClass(new PartClassDto("101511-1", "운영체제", 3, 3, 40, 40, "김선일", "월2목3목4", "T503", "컴퓨터공학부"));
//        partClassService.createPartClass(new PartClassDto("101511-2", "운영체제", 3, 3, 40, 40, "김선일", "월1목1목2", "T503", "컴퓨터공학부"));
//        partClassService.createPartClass(new PartClassDto("101511-3", "운영체제", 3, 3, 40, 40, "김선일", "월6월7목7", "T503", "컴퓨터공학부"));
//        partClassService.createPartClass(new PartClassDto("101511-4", "운영체제", 3, 3, 40, 40, "이장호", "수1목1금1", "T502", "컴퓨터공학부"));
//        partClassService.createPartClass(new PartClassDto("101511-5", "운영체제", 3, 3, 40, 40, "이장호", "월2월3수3", "T502", "컴퓨터공학부"));
//
//
//    }
}


