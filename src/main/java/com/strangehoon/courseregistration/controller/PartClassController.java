package com.strangehoon.courseregistration.controller;



import com.strangehoon.courseregistration.controller.Form.MajorForm;
import com.strangehoon.courseregistration.controller.Form.PocketClassForm;
import com.strangehoon.courseregistration.controller.login.SessionConst;
import com.strangehoon.courseregistration.controller.Form.PartClassSaveForm;
import com.strangehoon.courseregistration.controller.Form.PartClassUpdateForm;
import com.strangehoon.courseregistration.domain.Major;
import com.strangehoon.courseregistration.domain.Student;
import com.strangehoon.courseregistration.dto.PartClassDto;
import com.strangehoon.courseregistration.dto.PocketClassDto;
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
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    @GetMapping("/managerPartClass/new")
    public String postForm(Model model) {

        List<Major> majors = majorService.findAllMajor();

        //엔티티를 Form으로 변환
        List<MajorForm> majorForms = new ArrayList<>();
        for(Major major: majors) {
            majorForms.add(new MajorForm(major.getId(), major.getName()));
        }

        model.addAttribute("majorForm", majorForms);
        model.addAttribute("partClassSaveForm", new PartClassSaveForm());
        return "partClass/manager/postForm";
    }

    //분반 등록
    @PostMapping("/managerPartClass/new")
    public String post(@Validated @ModelAttribute("partClassSaveForm") PartClassSaveForm form, BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()) {
            List<Major> majors = majorService.findAllMajor();

            //엔티티를 Form으로 변환
            List<MajorForm> majorForms = new ArrayList<>();
            for(Major major: majors) {
                majorForms.add(new MajorForm(major.getId(), major.getName()));
            }
            model.addAttribute("majorForm", majorForms);
            return "partClass/manager/postForm";
        }
        partClassService.createPartClass(new PartClassDto(form));

        model.addAttribute("message", "신규 분반이 등록되었습니다.");
        model.addAttribute("searchUrl", "/managerPartClass/list");
        return "message";
    }

    //분반 조회
    @GetMapping("/managerPartClass/list")
    public String ListByManager(@ModelAttribute("partClassSearch") PartClassSearch partClassSearch, @PageableDefault Pageable pageable, Model model) {

        // 단순 조회 + 페이징 처리라 편의상 Dto를 뷰에 전달
        Page<PartClassDto> partClassDtoAll = partClassService.partClassSearchList(partClassSearch, pageable);
        List<Major> majors = majorService.findAllMajor();

        //엔티티를 Form으로 변환
        List<MajorForm> majorForms = new ArrayList<>();
        for(Major major: majors) {
            majorForms.add(new MajorForm(major.getId(), major.getName()));
        }

        model.addAttribute("partClassSearch", partClassSearch);
        model.addAttribute("majorForm", majorForms);
        model.addAttribute("pageNumber", partClassDtoAll.getNumber());
        model.addAttribute("partClassForm", partClassDtoAll);
        return "partClass/manager/list";
    }

    //분반 수정 폼
    @GetMapping("/managerPartClass/{partClassId}/edit")
    public String updateForm(@PathVariable("partClassId") Long partClassId, Model model) {

        PartClassDto partClassDto = partClassService.findPartClassOne(partClassId);
        PartClassUpdateForm partClassUpdateForm = new PartClassUpdateForm(partClassDto);
        List<Major> majors = majorService.findAllMajor();

        //엔티티를 Form으로 변환
        List<MajorForm> majorForms = new ArrayList<>();
        for(Major major: majors) {
            majorForms.add(new MajorForm(major.getId(), major.getName()));
        }

        model.addAttribute("majorForm", majorForms);
        model.addAttribute("partClassUpdateForm", partClassUpdateForm);
        return "partClass/manager/updateForm";
    }

    //분반 수정
    @PostMapping("/managerPartClass/{partClassId}/edit")
    public String update(@Validated @ModelAttribute("partClassUpdateForm") PartClassUpdateForm form, BindingResult bindingResult, Model model) {

        PartClassDto partClassDtoWithId = new PartClassDto(form);
        if(bindingResult.hasErrors()) {
            List<Major> majors = majorService.findAllMajor();

            //엔티티를 Form으로 변환
            List<MajorForm> majorForms = new ArrayList<>();
            for(Major major: majors) {
                majorForms.add(new MajorForm(major.getId(), major.getName()));
            }
            model.addAttribute("majorForm", majorForms);
            return "partClass/manager/updateForm";
        }

        partClassService.updatePartClass(partClassDtoWithId);
        model.addAttribute("message", "분반 정보를 수정했습니다.");
        model.addAttribute("searchUrl", "/managerPartClass/list");
        return "message";
    }

    //분반 삭제
    @GetMapping("/managerPartClass/{partClassId}/delete")
    public String delete(@PathVariable("partClassId") Long partClassId, Model model) {

        partClassService.deletePartClass(partClassId);

        model.addAttribute("message", "분반 정보를 삭제했습니다.");
        model.addAttribute("searchUrl", "/managerPartClass/list");
        return "message";
    }

    //------------------------------------------------학생 영역-----------------------------------------------

    //분반 조회
    @GetMapping("/partClass/list")
    public String ListByStudent(@ModelAttribute("partClassSearch") PartClassSearch partClassSearch, @PageableDefault Pageable pageable, HttpServletRequest request, Model model) {

        // 단순 조회 + 페이징 처리라 편의상 Dto를 뷰에 전달
        Page<PartClassDto> partClassDtoAll = partClassService.partClassSearchList(partClassSearch, pageable);
        List<Major> majors = majorService.findAllMajor();

        //엔티티를 Form으로 변환
        List<MajorForm> majorForms = new ArrayList<>();
        for(Major major: majors) {
            majorForms.add(new MajorForm(major.getId(), major.getName()));
        }

        HttpSession session = request.getSession();
        Object login = session.getAttribute(SessionConst.LOGIN_STUDENT);
        Student student = (Student)login;

        PocketClassForm pocketClassForm = new PocketClassForm(student.getId());

        model.addAttribute("pocketClassForm", pocketClassForm);       //뷰에 보내기 전에 학생 ID를 담아서 보내자.
        model.addAttribute("partClassSearch", partClassSearch);
        model.addAttribute("majorForm", majorForms);
        model.addAttribute("pageNumber", partClassDtoAll.getNumber());
        model.addAttribute("partClassForm", partClassDtoAll);
        return "partClass/list";
    }
}


