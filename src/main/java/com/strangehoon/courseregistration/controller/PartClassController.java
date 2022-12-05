package com.strangehoon.courseregistration.controller;



import com.strangehoon.courseregistration.domain.Major;
import com.strangehoon.courseregistration.dto.MajorDto;
import com.strangehoon.courseregistration.dto.PartClassDto;
import com.strangehoon.courseregistration.repository.MajorRepository;
import com.strangehoon.courseregistration.repository.PartClassSearch;
import com.strangehoon.courseregistration.service.MajorService;
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
    private final MajorService majorService;

    //------------------------------------------------관리자 영역-----------------------------------------------
    //분반 등록 폼
    @GetMapping(value = "/managerPartClasses/new")
    public String createForm(Model model) {
        List<Major> majors = majorService.findAllMajor();
        model.addAttribute("majorForm", majors);
        model.addAttribute("partClassForm", new PartClassForm());
        return "partClass/createPartClassForm";
    }

    //분반 등록
    @PostMapping(value = "/managerPartClasses/new")
    public String create(PartClassForm partClassForm, Model model) {
        partClassService.createPartClass(new PartClassDto(partClassForm));

        model.addAttribute("message", "신규 분반이 등록되었습니다.");
        model.addAttribute("searchUrl", "/managerPartClasses");
        return "message";
    }

    //분반 조회
    @GetMapping(value = "/managerPartClasses")
    public String Manage(@ModelAttribute("partClassSearch") PartClassSearch partClassSearch, @PageableDefault Pageable pageable, Model model) {
        Page<PartClassDto> partClassDtoAll = partClassService.findPartClasses(partClassSearch, pageable);
        List<Major> majors = majorService.findAllMajor();

        model.addAttribute("majorForm", majors);
        model.addAttribute("pageNumber", partClassDtoAll.getNumber());
        model.addAttribute("partClassForm", partClassDtoAll);
        return "partClass/managerPartClassList";
    }

    //분반 수정 폼
    @GetMapping(value = "/managerPartClasses/{partClassId}/edit")
    public String updatePartClassForm(@PathVariable("partClassId") Long partClassId, Model model) {
        PartClassDto partClassDto = partClassService.findPartClassOne(partClassId);
        log.info("info log ={}", partClassId);
        PartClassForm partClassForm = new PartClassForm(partClassDto);

        model.addAttribute("partClassForm", partClassForm);
        return "partClass/updatePartClassForm";
    }

    //분반 수정
    @PostMapping(value = "/managerPartClasses/{partClassId}/edit")
    public String updatePartClass(@ModelAttribute("form") PartClassForm partClassForm, Model model) {
        PartClassDto partClassDtoWithId = new PartClassDto(partClassForm, partClassForm.getId());

        partClassService.updatePartClass(partClassDtoWithId);
        model.addAttribute("message", "분반 정보를 수정했습니다.");
        model.addAttribute("searchUrl", "/managerPartClasses");
        return "message";
    }

    //분반 삭제
    @GetMapping(value = "/managerPartClasses/{partClassId}/delete")
    public String deletePartClass(@PathVariable("partClassId") Long partClassId, Model model) {
        partClassService.deletePartClass(partClassId);

        model.addAttribute("message", "분반 정보를 삭제했습니다.");
        model.addAttribute("searchUrl", "/managerPartClasses");
        return "message";
    }

    //------------------------------------------------학생 영역-----------------------------------------------
    //분반 조회
    @GetMapping(value = "/partClasses")
    public String List(@ModelAttribute("partClassSearch") PartClassSearch partClassSearch, @PageableDefault Pageable pageable, Model model) {
        Page<PartClassDto> partClassDtoAll = partClassService.findPartClasses(partClassSearch, pageable);
        List<Major> majors = majorService.findAllMajor();

        model.addAttribute("majorForm", majors);
        model.addAttribute("pageNumber", partClassDtoAll.getNumber());
        model.addAttribute("partClassForm", partClassDtoAll);
        return "partClass/partClassList";
    }
}


