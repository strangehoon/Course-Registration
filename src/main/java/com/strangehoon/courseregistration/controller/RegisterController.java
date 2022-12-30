package com.strangehoon.courseregistration.controller;


import com.strangehoon.courseregistration.controller.login.SessionConst;
import com.strangehoon.courseregistration.domain.Major;
import com.strangehoon.courseregistration.domain.Student;
import com.strangehoon.courseregistration.dto.PartClassDto;
import com.strangehoon.courseregistration.dto.PocketClassDto;
import com.strangehoon.courseregistration.dto.RegisterDto;
import com.strangehoon.courseregistration.repository.PartClassSearch;
import com.strangehoon.courseregistration.service.MajorService;
import com.strangehoon.courseregistration.service.PartClassService;
import com.strangehoon.courseregistration.service.RegisterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class RegisterController {

    private final RegisterService registerService;
    private final PartClassService partClassService;
    private final MajorService majorService;

    //수강신청 등록
    @PostMapping(value = "/register/new") @ResponseBody
    public Boolean register(@RequestParam Long studentId, Model model) {

        // 수강신청 시 기존에 신청했던 내영은 먼저 삭제를 해야함, 안그러면 중복 이슈 발생
        registerService.deleteAll(studentId);

        Boolean flag = registerService.registerPocket(studentId);

        return flag;
    }

    // 수강신청 시간표 조회
    @GetMapping(value = "/register/table")
    public String list(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession();
        Object login = session.getAttribute(SessionConst.LOGIN_STUDENT);
        Student student = (Student)login;

        List<RegisterDto> registerDto = registerService.findRegisterDto(student.getId());

        Map<String, String> timeTable =   registerService.makeTimeTable(registerDto);


        model.addAttribute("timeTable", timeTable);
        return "register/timeTable";
    }

    //수강신청 내역 조회
    @GetMapping(value = "/register/list")
    public String ListByUsedStudent(@PageableDefault Pageable pageable, Model model, HttpServletRequest request) {

        HttpSession session = request.getSession();
        Object login = session.getAttribute(SessionConst.LOGIN_STUDENT);
        Student student = (Student)login;

        Page<PartClassDto> registerList = registerService.registerList(student.getId(), pageable);

        model.addAttribute("studentId", student.getId());
        model.addAttribute("registerList", registerList);
        model.addAttribute("pageNumber", registerList.getNumber());
        return "register/registerList";
    }

    //수강신청 내역 철회
    @GetMapping(value = "/registerList/{partClassId}/delete")
    public String cancelRegister(@PathVariable("partClassId") Long partClassId, Model model) {
        registerService.deleteRegister(partClassId);

        model.addAttribute("message", "수강신청 내역을 삭제했습니다.");
        model.addAttribute("searchUrl", "/register/list");
        return "message";
    }

    //분반 조회
    @GetMapping(value = "/register/search")
    public String ListByUsedStudent(@ModelAttribute("partClassSearch") PartClassSearch partClassSearch, @PageableDefault Pageable pageable, HttpServletRequest request, Model model) {
        Page<PartClassDto> partClassDtoAll = partClassService.partClassSearchList(partClassSearch, pageable);
        List<Major> majors = majorService.findAllMajor();

        HttpSession session = request.getSession();
        Object login = session.getAttribute(SessionConst.LOGIN_STUDENT);
        Student student = (Student)login;

        RegisterDto registerDto = new RegisterDto(student.getId());
        model.addAttribute("registerDto", registerDto);       //뷰에 보내기 전에 학생 ID를 담아서 보내자.
        model.addAttribute("partClassSearch", partClassSearch);
        model.addAttribute("majorForm", majors);
        model.addAttribute("pageNumber", partClassDtoAll.getNumber());
        model.addAttribute("partClassForm", partClassDtoAll);
        return "register/addRegister";
    }

    // 강좌별 수강신청 등록
    @PostMapping(value = "/register/search/add") @ResponseBody
    public Long register(@ModelAttribute RegisterDto registerDto, Model model) {
        Long flag = registerService.checkRegisterList(registerDto);
        if (flag == 1) {
            registerService.registerNew(registerDto);
            return flag;
        }
        else {
            return flag;
        }
    }

}
