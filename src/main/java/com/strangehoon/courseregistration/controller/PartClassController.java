package com.strangehoon.courseregistration.controller;



import com.strangehoon.courseregistration.controller.login.SessionConst;
import com.strangehoon.courseregistration.controller.validation.PartClassSaveForm;
import com.strangehoon.courseregistration.controller.validation.PartClassUpdateForm;
import com.strangehoon.courseregistration.domain.Major;
import com.strangehoon.courseregistration.domain.PartClass;
import com.strangehoon.courseregistration.domain.Student;
import com.strangehoon.courseregistration.dto.MajorDto;
import com.strangehoon.courseregistration.dto.PartClassDto;
import com.strangehoon.courseregistration.dto.PocketClassDto;
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
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
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
        model.addAttribute("partClassSaveForm", new PartClassSaveForm());
        return "partClass/createPartClassForm";
    }

    //분반 등록
    @PostMapping(value = "/managerPartClasses/new")
    public String create(@Validated @ModelAttribute("partClassSaveForm") PartClassSaveForm form, BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()) {
            List<Major> majors = majorService.findAllMajor();
            model.addAttribute("majorForm", majors);
            return "partClass/createPartClassForm";
            //return "redirect:/managerPartClasses/new";   redirectAttributes 공부해보자/
        }
        partClassService.createPartClass(new PartClassDto(form));

        model.addAttribute("message", "신규 분반이 등록되었습니다.");
        model.addAttribute("searchUrl", "/managerPartClasses");
        return "message";
    }

    //분반 조회
    @GetMapping(value = "/managerPartClasses")
    public String ListUsedByManager(@ModelAttribute("partClassSearch") PartClassSearch partClassSearch, @PageableDefault Pageable pageable, Model model) {
        Page<PartClassDto> partClassDtoAll = partClassService.partClassSearchList(partClassSearch, pageable);
        List<Major> majors = majorService.findAllMajor();


//        //검색하지 않았을 때
//        if((partClassSearch.getPartClassName() == null) && (partClassSearch.getMajorName() == null) && (partClassSearch.getSchoolYear() ==null)) {
//            partClassDtoAll = partClassService.partClassList(pageable);
//            log.info("ewgwefwef =", "1111111111111111111111");
//        }
//        //검색했을 때
//        else {
//            partClassDtoAll = partClassService.partClassSearchList(partClassSearch, pageable);
//        }

        log.info("ClassName = {}", partClassSearch.getPartClassName());
        log.info("MajorName = {}", partClassSearch.getMajorName());
        log.info("SchoolYear = {}", partClassSearch.getSchoolYear());
        model.addAttribute("partClassSearch", partClassSearch);
        model.addAttribute("majorForm", majors);
        model.addAttribute("pageNumber", partClassDtoAll.getNumber());
        model.addAttribute("partClassForm", partClassDtoAll);
        return "partClass/managerPartClassList";
    }

    //분반 수정 폼
    @GetMapping(value = "/managerPartClasses/{partClassId}/edit")
    public String updatePartClassForm(@PathVariable("partClassId") Long partClassId, Model model) {
        PartClassDto partClassDto = partClassService.findPartClassOne(partClassId);
        System.out.println("PartClassDtoId" + partClassDto.getId());
        PartClassUpdateForm partClassUpdateForm = new PartClassUpdateForm(partClassDto);
        List<Major> majors = majorService.findAllMajor();

        System.out.println("PartClassUpdateFormId" + partClassUpdateForm.getId());

        model.addAttribute("majorForm", majors);
        model.addAttribute("partClassUpdateForm", partClassUpdateForm);
        return "partClass/updatePartClassForm";
    }


    //분반 수정
    @PostMapping(value = "/managerPartClasses/{partClassId}/edit")
    public String updatePartClass(@Validated @ModelAttribute("partClassUpdateForm") PartClassUpdateForm form, BindingResult bindingResult, Model model) {
        System.out.println("PartClassController.updatePartClass");
        PartClassDto partClassDtoWithId = new PartClassDto(form);

        if(bindingResult.hasErrors()) {
            List<Major> majors = majorService.findAllMajor();
            model.addAttribute("majorForm", majors);
            return "partClass/updatePartClassForm";
        }

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
    public String ListByUsedStudent(@ModelAttribute("partClassSearch") PartClassSearch partClassSearch, @PageableDefault Pageable pageable, HttpServletRequest request, Model model) {
        Page<PartClassDto> partClassDtoAll = partClassService.partClassSearchList(partClassSearch, pageable);
        List<Major> majors = majorService.findAllMajor();

        HttpSession session = request.getSession();
        Object login = session.getAttribute(SessionConst.LOGIN_STUDENT);
        Student student = (Student)login;

//        //검색하지 않았을 때
//        if((partClassSearch.getPartClassName() == null) && (partClassSearch.getMajorName() == null) && (partClassSearch.getSchoolYear() ==null)) {
//            partClassDtoAll = partClassService.partClassList(pageable);
//            log.info("ewgwefwef =", "1111111111111111111111");
//        }
//        //검색했을 때
//        else {
//            partClassDtoAll = partClassService.partClassSearchList(partClassSearch, pageable);
//        }

        log.info("ClassName = {}", partClassSearch.getPartClassName());
        log.info("MajorName = {}", partClassSearch.getMajorName());
        log.info("SchoolYear = {}", partClassSearch.getSchoolYear());
        PocketClassDto pocketClassDto = new PocketClassDto(student.getId());
        model.addAttribute("pocketClassDto", pocketClassDto);       //뷰에 보내기 전에 학생 ID를 담아서 보내자.
        model.addAttribute("partClassSearch", partClassSearch);
        model.addAttribute("majorForm", majors);
        model.addAttribute("pageNumber", partClassDtoAll.getNumber());
        model.addAttribute("partClassForm", partClassDtoAll);
        return "partClass/partClassList";
    }
}


