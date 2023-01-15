package com.strangehoon.courseregistration.controller;


import com.strangehoon.courseregistration.controller.Form.MajorForm;
import com.strangehoon.courseregistration.controller.Form.RegisterForm;
import com.strangehoon.courseregistration.controller.login.SessionConst;
import com.strangehoon.courseregistration.domain.Major;
import com.strangehoon.courseregistration.domain.Student;
import com.strangehoon.courseregistration.dto.ManagerRegisterDto;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class RegisterController {

    private final RegisterService registerService;
    private final PartClassService partClassService;
    private final MajorService majorService;

    //------------------------------------------------관리자 영역-----------------------------------------------

    //수강신청 내역 조회
    @GetMapping("/managerPartClass/register/list")
    public String ListByManager(@PageableDefault Pageable pageable, Model model) {

        // 단순 조회 + 페이징 처리라 편의상 Dto를 뷰에 전달
        Page<ManagerRegisterDto> registerList = registerService.registerListByManager(pageable);

        model.addAttribute("registerList", registerList);
        model.addAttribute("pageNumber", registerList.getNumber());
        return "register/manager/list";
    }

    //------------------------------------------------학생 영역-----------------------------------------------

    //수강신청 등록
    @PostMapping("/register/new") @ResponseBody
    public Boolean post(@RequestParam Long studentId, Model model) {

        // 수강신청 시 기존에 신청했던 내용은 먼저 삭제를 해야함, 안그러면 중복 이슈 발생
        registerService.deleteAll(studentId);

        Boolean flag = registerService.registerPocket(studentId);
        System.out.println("flag" + flag);

        return flag;
    }

    // 수강신청 시간표 조회
    @GetMapping("/register/table")
    public String timeTable(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession();
        Object login = session.getAttribute(SessionConst.LOGIN_STUDENT);
        Student student = (Student)login;

        List<RegisterDto> registerDto = registerService.findRegisterDto(student.getId());
        Map<String, String> timeTable =   registerService.makeTimeTable(registerDto);

        model.addAttribute("timeTable", timeTable);
        return "register/timeTable";
    }

    //수강신청 내역 조회
    @GetMapping("/register/list")
    public String ListByStudent(@PageableDefault Pageable pageable, Model model, HttpServletRequest request) {

        HttpSession session = request.getSession();
        Object login = session.getAttribute(SessionConst.LOGIN_STUDENT);
        Student student = (Student)login;

        // 단순 조회 + 페이징 처리라 편의상 Dto를 뷰에 전달
        Page<PartClassDto> registerList = registerService.registerList(student.getId(), pageable);

        model.addAttribute("studentId", student.getId());
        model.addAttribute("registerList", registerList);
        model.addAttribute("pageNumber", registerList.getNumber());
        return "register/list";
    }

    //수강신청 내역 철회
    @GetMapping("/register/list/{partClassId}/delete")
    public String cancel(@PathVariable("partClassId") Long partClassId, Model model) {

        registerService.deleteRegister(partClassId);

        model.addAttribute("message", "수강신청 내역을 삭제했습니다.");
        model.addAttribute("searchUrl", "/register/list");
        return "message";
    }

    //분반 조회
    @GetMapping("/register/search")
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

        RegisterForm registerForm = new RegisterForm(student.getId());

        model.addAttribute("registerForm", registerForm);       //뷰에 보내기 전에 학생 ID를 담아서 보내자.
        model.addAttribute("partClassSearch", partClassSearch);
        model.addAttribute("majorForm", majorForms);
        model.addAttribute("pageNumber", partClassDtoAll.getNumber());
        model.addAttribute("partClassForm", partClassDtoAll);
        return "register/search";
    }

    // 강좌별 수강신청 등록
    @PostMapping("/register/search/new") @ResponseBody
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
